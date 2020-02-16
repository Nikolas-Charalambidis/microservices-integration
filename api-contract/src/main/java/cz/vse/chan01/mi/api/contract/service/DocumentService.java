package cz.vse.chan01.mi.api.contract.service;

import java.util.List;

import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.model.Document;

public interface DocumentService {

	void document(final Contract contract);

	List<cz.vse.chan01.swagger.contract.model.Document> documentsByCustomerId(final Long customerId);
}
