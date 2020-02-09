package cz.vse.chan01.mi.api.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DocumentServiceImpl {

	private final DocumentRepository documentRepository;

	private final ModelMapper modelMapper;

	public DocumentServiceImpl(
		final DocumentRepository documentRepository,
		final ModelMapper modelMapper
	) {
		this.documentRepository = documentRepository;
		this.modelMapper = modelMapper;
	}

}
