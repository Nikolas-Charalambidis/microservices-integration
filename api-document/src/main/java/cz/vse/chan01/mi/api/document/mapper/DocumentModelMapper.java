package cz.vse.chan01.mi.api.document.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DocumentModelMapper extends ModelMapper {

	public DocumentModelMapper(
		final DocumentEntityToDtoConverter documentEntityToDtoConverter,
		final DocumentDtoToEntityConverter documentDtoToEntityConverter
	) {
		super.addConverter(documentEntityToDtoConverter);
		super.addConverter(documentDtoToEntityConverter);
	}
}
