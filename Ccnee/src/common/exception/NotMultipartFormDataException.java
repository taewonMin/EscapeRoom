package common.exception;

public class NotMultipartFormDataException extends Exception {

	public NotMultipartFormDataException() {
		super("Request의 유형이 multipart/form-data가 아닙니다.");
	}
}
