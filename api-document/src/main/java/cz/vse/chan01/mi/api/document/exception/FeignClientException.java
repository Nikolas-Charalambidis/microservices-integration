package cz.vse.chan01.mi.api.document.exception;

public class FeignClientException extends RuntimeException {

	public FeignClientException(final String message) {
		super(message);
	}
}
