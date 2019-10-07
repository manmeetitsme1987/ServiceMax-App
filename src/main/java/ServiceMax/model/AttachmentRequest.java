package ServiceMax.model;

import java.util.List;

public class AttachmentRequest {
	private List<AttachmentWrapper> attachmentIds;
	private String salesforce_username;
	private String salesforce_password;
	private String salesforce_securitytoken;
	private String salesforce_instanceURL;
	private String aws_bucket_name;
	private String aws_accesskey;
	private String aws_secretkey;
	
	
	public List<AttachmentWrapper> getAttachmentIds() {
		return attachmentIds;
	}

	public void setAttachmentIds(List<AttachmentWrapper> attachmentIds) {
		this.attachmentIds = attachmentIds;
	}

	public String getSalesforce_username() {
		return salesforce_username;
	}

	public void setSalesforce_username(String salesforce_username) {
		this.salesforce_username = salesforce_username;
	}

	public String getSalesforce_password() {
		return salesforce_password;
	}

	public void setSalesforce_password(String salesforce_password) {
		this.salesforce_password = salesforce_password;
	}

	public String getSalesforce_securitytoken() {
		return salesforce_securitytoken;
	}

	public void setSalesforce_securitytoken(String salesforce_securitytoken) {
		this.salesforce_securitytoken = salesforce_securitytoken;
	}

	public String getSalesforce_instanceURL() {
		return salesforce_instanceURL;
	}

	public void setSalesforce_instanceURL(String salesforce_instanceURL) {
		this.salesforce_instanceURL = salesforce_instanceURL;
	}

	public String getAws_bucket_name() {
		return aws_bucket_name;
	}

	public void setAws_bucket_name(String aws_bucket_name) {
		this.aws_bucket_name = aws_bucket_name;
	}

	public String getAws_accesskey() {
		return aws_accesskey;
	}

	public void setAws_accesskey(String aws_accesskey) {
		this.aws_accesskey = aws_accesskey;
	}

	public String getAws_secretkey() {
		return aws_secretkey;
	}

	public void setAws_secretkey(String aws_secretkey) {
		this.aws_secretkey = aws_secretkey;
	}
}
