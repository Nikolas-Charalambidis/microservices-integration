package cz.vse.chan01.mi.api.document.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import cz.vse.chan01.mi.api.document.DocumentRepository;
import cz.vse.chan01.mi.api.document.entity.DocumentEntity;
import cz.vse.chan01.mi.api.document.entity.DocumentExistsException;
import cz.vse.chan01.mi.api.document.entity.VersionedDocumentEntity;
import cz.vse.chan01.mi.api.document.mapper.DocumentModelMapper;
import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;
import cz.vse.chan01.swagger.document.model.Document.DocumentStatusEnum;

@Service
public class DocumentServiceImpl implements DocumentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentServiceImpl.class);

	private final ResourceLoader resourceLoader;

	private final DocumentRepository documentRepository;

	private final DocumentModelMapper documentModelMapper;

	public DocumentServiceImpl(
		final DocumentRepository documentRepository,
		@Qualifier("webApplicationContext") final ResourceLoader resourceLoader,
		final DocumentModelMapper documentModelMapper
	) {
		this.resourceLoader = resourceLoader;
		this.documentRepository = documentRepository;
		this.documentModelMapper = documentModelMapper;
	}

	@Override
	public List<Document> documents(Optional<Long> caseId, Optional<Long> customerId) {

		return caseId
			.or(() -> customerId)
			.map(value -> {
				if (caseId.isEmpty()) {
					return documentRepository.findAllByCustomerId(value);
				}
				if (customerId.isEmpty()) {
					return documentRepository.findAllByCaseId(value);
				}
				return documentRepository.findAllByCaseIdAndCustomerId(caseId.get(), customerId.get());
			})
			.orElse(documentRepository.findAll())
			.stream()
			.map(de -> documentModelMapper.map(de, Document.class))
			.collect(Collectors.toList());
	}

	@Override
	public Document documentById(final String documentId) {
		return this.documentRepository.findById(documentId)
			.map(ce -> documentModelMapper.map(ce, Document.class))
			.orElseThrow(() -> new EntityNotFoundException(String.format("Document {id: %s} not found", documentId)));
	}

	@Override
	public String document(final Document document) {
		this.documentRepository.findById(document.getDocumentId())
			.ifPresent(de -> {
				final String message = String.format("The document {id: %s} already exists", de.getId());
				LOGGER.error(message);
				throw new DocumentExistsException(message);});
		final DocumentEntity documentEntity = this.documentModelMapper.map(document, DocumentEntity.class);
		final DocumentEntity saved = this.documentRepository.save(documentEntity);
		return saved.getId();
	}

	@Override
	public Document document(final Contract contract) {
		final LocalDateTime now = LocalDateTime.now();
		final String id = String.format("%s_%s_%s",
			contract.getCustomerId(), contract.getContractType().name(), contract.getContractId());
		final String version = "1.0.0";

		this.documentRepository.findById(id)
			.ifPresent(de -> {
				final String message = String.format("The document {id: %s} already exists", de.getId());
				LOGGER.error(message);
				throw new DocumentExistsException(message);});

		final DocumentEntity documentEntity = new DocumentEntity(
			id,
			contract.getContractId(),
			contract.getCustomerId(),
			String.format("%s: %s", contract.getCustomerLabel().toUpperCase(), contract.getContractType().name()),
			DocumentStatusEnum.CREATED.name(),
			now.toLocalDate(),
			null,
			Collections.singletonList(new VersionedDocumentEntity(
				String.format("%s_%s", id, version), now, "1.0.0", "PDF",
					"JVBERi0xLjUKJYCBgo==")));
		final DocumentEntity saved = this.documentRepository.save(documentEntity);
		return this.documentModelMapper.map(saved, Document.class);
	}

	@PostConstruct
	void postConstruct() {
		try {
			//final Resource resource = resourceLoader.getResource("classpath*:mongo-data.json");
			//final File file = resource.getFile();

			final ClassPathResource classPathResource = new ClassPathResource("mongo-data.json");
			final byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
			final String jsonString = new String(binaryData, StandardCharsets.UTF_8);
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			final DocumentEntity[] sample = objectMapper.readValue(jsonString, DocumentEntity[].class);
			final Iterable<DocumentEntity> iterable = Arrays.asList(sample);
			this.documentRepository.saveAll(iterable);
		} catch (IOException e) {
			LOGGER.error("Filling MongoDB with data failed", e);
		}
	}
}
