package cz.vse.chan01.mi.api.document.service;

import java.util.List;
import java.util.Optional;

import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

public interface DocumentService {

	List<Document> documents(Optional<Long> caseId, Optional<Long> customerId);

	Document documentById(String documentId);

	String document(final Document document);

	Document document(final Contract contract);
}
