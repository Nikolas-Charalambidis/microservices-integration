package cz.vse.chan01.mi.api.contract.exception;

public class FeignClientException extends RuntimeException {

	public FeignClientException(final String message) {
		super(message);
	}
}
