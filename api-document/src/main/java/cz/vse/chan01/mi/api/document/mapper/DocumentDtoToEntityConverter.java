package cz.vse.chan01.mi.api.document.mapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import cz.vse.chan01.mi.api.document.entity.DocumentEntity;
import cz.vse.chan01.mi.api.document.entity.VersionedDocumentEntity;
import cz.vse.chan01.swagger.document.model.Document;

@Component
public class DocumentDtoToEntityConverter implements Converter<Document, DocumentEntity> {

	private final ModelMapper modelMapper;

	public DocumentDtoToEntityConverter(final ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public DocumentEntity convert(final MappingContext<Document, DocumentEntity> mappingContext) {
		if (mappingContext.getSource() != null) {
			final Document document = mappingContext.getSource();
			final DocumentEntity dest = new DocumentEntity(
				document.getDocumentId(),
				document.getName(),
				document.getDocumentStatus() == null ? null : document.getDocumentStatus().name(),
				document.getCaseId(),
				document.getArchivationDate(),
				document.getCreationDate(),
				new ArrayList<>()
			);
			if (document.getVersions() != null) {
				Type listType = new TypeToken<List<VersionedDocumentEntity>>() {}.getType();
				System.out.println("CONVERT: " + document.getVersions());
				List<VersionedDocumentEntity> items = modelMapper.map(document.getVersions(), listType);
				dest.setVersionedDocumentEntityList(items);
				System.out.println("CONVERTED: " + dest.getVersionedDocumentEntityList());
			}

			return dest;
		}
		return new DocumentEntity();
	}
}
