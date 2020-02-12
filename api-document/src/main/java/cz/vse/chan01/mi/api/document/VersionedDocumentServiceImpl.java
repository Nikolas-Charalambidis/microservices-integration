package cz.vse.chan01.mi.api.document;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.vse.chan01.mi.api.document.entity.DocumentEntity;
import cz.vse.chan01.mi.api.document.entity.DocumentExistsException;
import cz.vse.chan01.mi.api.document.entity.VersionedDocumentEntity;
import cz.vse.chan01.swagger.document.model.VersionedDocument;

@Service
public class VersionedDocumentServiceImpl implements VersionedDocumentService {

	private final DocumentService documentService;

	private final DocumentRepository documentRepository;

	private final ModelMapper modelMapper;

	public VersionedDocumentServiceImpl(
		final DocumentService documentService,
		final DocumentRepository documentRepository,
		final ModelMapper modelMapper
	) {
		this.documentService = documentService;
		this.documentRepository = documentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public VersionedDocument versionedDocumentById(final String documentId, final String versionedDocumentId) {
		return this.documentService.documentById(documentId)
			.getVersions()
			.stream()
			.filter(vs -> vs.getVersionedDocumentId().equals(versionedDocumentId))
			.findFirst()
			.orElseThrow(() -> new EntityNotFoundException(
				String.format("Versioned document {id: %s} of document {id: %s} not found", versionedDocumentId, documentId)));
	}

	@Override
	@Transactional
	public void versionedDocument(final String documentId, final VersionedDocument versionedDocument) {

		final DocumentEntity documentEntity = this.documentRepository
			.findById(documentId)
			.orElseThrow(() -> new EntityNotFoundException(String.format("Document {id: %s} not found", documentId)));
		final VersionedDocumentEntity versionedDocumentEntity = this.modelMapper
			.map(versionedDocument, VersionedDocumentEntity.class);
		//documentEntity.getVersionedDocumentEntityList().stream().filter(vde -> vde.getId().equals(versionedDocument.))

			// TODO ID GENERATION
			// TODO VERSION VALIDATION?
		versionedDocument.setVersionedDocumentId(String.format("%s_%s", documentEntity.getId(), versionedDocument.getVersion()));
		documentEntity.getVersionedDocumentEntityList().add(versionedDocumentEntity);
		this.documentRepository.save(documentEntity);
	}
}
