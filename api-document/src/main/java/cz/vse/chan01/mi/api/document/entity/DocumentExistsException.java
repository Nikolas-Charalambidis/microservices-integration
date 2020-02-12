package cz.vse.chan01.mi.api.document.entity;

public class DocumentExistsException extends RuntimeException {

	public DocumentExistsException(final String message) {
		super(message);
	}
}
