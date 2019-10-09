package ServiceMax.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.sobject.Attachment;

import ServiceMax.model.AttachmentInnerResponse;
import ServiceMax.model.AttachmentRequest;
import ServiceMax.model.AttachmentResponse;
import ServiceMax.service.FileUploadService;
import ServiceMax.service.SalesforceService;




@RestController
public class AttachmentUploadController {
	@Autowired
	private SalesforceService salesforceService;
	
	@Autowired
    private FileUploadService fileUploadService;
	
	@RequestMapping(value="/uploadAttachment", method=RequestMethod.POST)
    public ResponseEntity<AttachmentResponse> uploadSalesforceAttachment(@RequestBody AttachmentRequest attachmentReqeust) {
		AttachmentResponse attachmentResponse = new AttachmentResponse();
		try{
			EnterpriseConnection connection = salesforceService.createConnectionToSalesforceOrg(attachmentReqeust);
			System.out.println("connection=====" + connection.toString());
			List<AttachmentInnerResponse> listAttachmentResponse = new ArrayList<AttachmentInnerResponse>();
			if(connection != null){
				List<Attachment> listAttachments = salesforceService.fetchAttachments(connection, attachmentReqeust.getAttachmentIds());
				//Attachment attach = listAttachments.get(0);
				for(Attachment attach : listAttachments){
					AttachmentInnerResponse aResponse = new AttachmentInnerResponse();
					MultipartFile result = new MockMultipartFile(
												attach.getName(),
												attach.getName(), 
												attach.getContentType(), 
												attach.getBody());
					PutObjectResult putObjectResult = fileUploadService.uploadFile(result, attach.getParentId(), attachmentReqeust);
					aResponse.setPutObjectResult(putObjectResult);
					listAttachmentResponse.add(aResponse);
				}
				attachmentResponse.setAttachment(listAttachmentResponse);
			}
		}catch(Exception e){
			e.printStackTrace();
			attachmentResponse.setError_message("Error from uploadAttachment! " + e.getMessage());
			return new ResponseEntity<>(attachmentResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(attachmentResponse, HttpStatus.OK);
    }
	
	@RequestMapping(value="/testService", method=RequestMethod.GET)
	public String testService() {
		try{
			return "Successful call";
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return "Congrats";
	}
}
