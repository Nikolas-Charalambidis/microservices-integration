package cz.vse.chan01.mi.api.document.service;

import cz.vse.chan01.swagger.document.model.VersionedDocument;

public interface VersionedDocumentService {

	VersionedDocument versionedDocumentById(final String documentId, final String versionedDocumentId);

	void versionedDocument(final String documentId, final VersionedDocument versionedDocument);
}
