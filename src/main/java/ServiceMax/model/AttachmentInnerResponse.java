package ServiceMax.model;

import com.amazonaws.services.s3.model.PutObjectResult;

	public class AttachmentInnerResponse {
	private String error_message;
	private PutObjectResult putObjectResult;
	
	
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public PutObjectResult getPutObjectResult() {
		return putObjectResult;
	}
	public void setPutObjectResult(PutObjectResult putObjectResult) {
		this.putObjectResult = putObjectResult;
	}
}
