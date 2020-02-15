package cz.vse.chan01.mi.api.document.mapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import cz.vse.chan01.mi.api.document.entity.DocumentEntity;
import cz.vse.chan01.swagger.document.model.Document;
import cz.vse.chan01.swagger.document.model.Document.DocumentStatusEnum;
import cz.vse.chan01.swagger.document.model.VersionedDocument;

@Component
public class DocumentEntityToDtoConverter implements Converter<DocumentEntity, Document> {

	private final ModelMapper modelMapper;

	public DocumentEntityToDtoConverter(final ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public Document convert(final MappingContext<DocumentEntity, Document> mappingContext) {
		if (mappingContext.getSource() != null) {
			final DocumentEntity de = mappingContext.getSource();
			final Document dest = new Document()
				.documentId(de.getId())
				.name(de.getName())
				.caseId(de.getCaseId())
				.customerId(de.getCustomerId())
				.documentStatus(DocumentStatusEnum.fromValue(de.getDocumentStatus()))
				.archivationDate(de.getArchivationDate())
				.creationDate(de.getCreationDate());
			if (de.getVersionedDocumentEntityList() != null) {
				Type listType = new TypeToken<List<VersionedDocument>>() {
				}.getType();
				List<VersionedDocument> items = modelMapper
					.map(de.getVersionedDocumentEntityList(), listType);
				dest.setVersions(items);
			}
			return dest;
		}
		return new Document();
	}
}
