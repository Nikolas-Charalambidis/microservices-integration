package cz.vse.chan01.mi.api.document.entity;

public class DocumentNotFoundException extends RuntimeException {

	public DocumentNotFoundException(final String message) {
		super(message);
	}
}
