package ServiceMax.model;

import com.amazonaws.services.s3.model.PutObjectResult;

	public class AttachmentInnerResponse {
	private String error_message;
	private PutObjectResult putObjectResult;
	private String attachmentId;
	private String parentName;
	private String attachName;
	
	
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
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
