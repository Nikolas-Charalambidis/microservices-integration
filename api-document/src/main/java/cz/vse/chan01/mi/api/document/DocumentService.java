package cz.vse.chan01.mi.api.document;

import java.util.List;

import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

interface DocumentService {

	List<Document> documents();

	Document documentById(String documentId);

	String document(final Document document);

	String document(final Contract contract);
}
