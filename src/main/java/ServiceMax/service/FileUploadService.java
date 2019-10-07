package ServiceMax.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import ServiceMax.model.AttachmentRequest;
import ServiceMax.model.FileName;


@Service
public class FileUploadService {
	
	private AmazonS3Client amazonS3Client;
	
	/*
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	*/
	
	public FileName uploadFile(MultipartFile fileUploadReq, String attachParentId, AttachmentRequest attachmentReqeust) throws IOException{
		PutObjectResult putObjectResult = null;
		String fileName = null;
		fileName = fileUploadReq.getOriginalFilename();
		putObjectResult = upload(fileUploadReq.getInputStream(), attachParentId + "/" +fileUploadReq.getOriginalFilename(), attachmentReqeust);
		FileName fileNameObj = new FileName();
		if(putObjectResult != null){
			fileNameObj.setFileName(fileName);
		}
		fileNameObj.setFileName(fileName);
		return fileNameObj;

	}
	
	
	private PutObjectResult upload(InputStream inputStream, String uploadKey, AttachmentRequest attachmentReqeust) {
		String accessKey = attachmentReqeust.getAws_accesskey();
		String secretKey = attachmentReqeust.getAws_secretkey();
		amazonS3Client = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
		PutObjectRequest putObjectRequest = new PutObjectRequest(attachmentReqeust.getAws_bucket_name(), uploadKey, inputStream, new ObjectMetadata());
		putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
		putObjectRequest.getRequestClientOptions().setReadLimit(8000000);
	
		PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
		IOUtils.closeQuietly(inputStream);
		return putObjectResult;
	}
	

}
