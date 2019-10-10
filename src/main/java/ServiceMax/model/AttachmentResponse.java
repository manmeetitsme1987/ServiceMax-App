package ServiceMax.model;

import java.util.List;


public class AttachmentResponse {
	private String error_message;
	private List<AttachmentInnerResponse> attachment;
	
	
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public List<AttachmentInnerResponse> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<AttachmentInnerResponse> attachment) {
		this.attachment = attachment;
	}
	
}
