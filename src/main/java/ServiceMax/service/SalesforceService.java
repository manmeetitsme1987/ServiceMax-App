package ServiceMax.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Attachment;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

import ServiceMax.model.AttachmentRequest;
import ServiceMax.model.AttachmentWrapper;


@Service
public class SalesforceService {
	
	public  EnterpriseConnection createConnectionToSalesforceOrg(AttachmentRequest attachmentReqeust) throws NumberFormatException, IOException, URISyntaxException{
		//Create a new connectionconfig to your Salesforce Org
	    ConnectorConfig sfconfig = new ConnectorConfig();
	    //Use your salesforce username = email
	    sfconfig.setUsername(attachmentReqeust.getSalesforce_username());
	    //Use your saleforce password with your security token look like: passwordjeIzBAQKkR6FBW8bw5HbVkkkk
	    sfconfig.setPassword(attachmentReqeust.getSalesforce_password()+""+attachmentReqeust.getSalesforce_securitytoken());
	    sfconfig.setAuthEndpoint(attachmentReqeust.getSalesforce_instanceURL());
	    sfconfig.setServiceEndpoint(attachmentReqeust.getSalesforce_instanceURL());
	    
	    System.out.println("attachmentReqeust.getSalesforce_username()======" + attachmentReqeust.getSalesforce_username());
	    System.out.println("attachmentReqeust.getSalesforce_password()======" + attachmentReqeust.getSalesforce_password());
	    System.out.println("attachmentReqeust.getSalesforce_securitytoken()======" + attachmentReqeust.getSalesforce_securitytoken());
	    System.out.println("attachmentReqeust.getSalesforce_instanceURL()======" + attachmentReqeust.getSalesforce_instanceURL());
	    
	    EnterpriseConnection partnercon = null;
	    try {
	    	 
	        // create a salesforce connection object with the credentials supplied in your connectionconfig
	        partnercon = Connector.newConnection(sfconfig);
	    }catch (ConnectionException e) {
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
	    }
	    return partnercon;
	}
	
	public  List<Attachment> fetchAttachments(EnterpriseConnection partnercon, List<AttachmentWrapper> attadhmentRequests, Map<String, String> mapAttachmentsWithParent){
		List<Attachment> listAttachments = new ArrayList<Attachment>();
		try{
			String query = "Select Id, Parent.Name, ParentId, Name, Description, ContentType, BodyLength, Body, OwnerId, "+
							" Description from Attachment ";
			
			String attachmentIds = "";
			for(AttachmentWrapper reqObj : attadhmentRequests){
	            if(attachmentIds.equals("")){
	            	attachmentIds = "'" + reqObj.getAttachmentId() + "'";
	            }else{
	            	attachmentIds += ",'" + reqObj.getAttachmentId() + "'";
	            }
	            mapAttachmentsWithParent.put(reqObj.getAttachmentId(), reqObj.getParentName());
	        }
			query += " where id in (" + attachmentIds + ")";
			QueryResult describeGlobalResult = partnercon.query(query);
			System.out.println("Records length : =" + describeGlobalResult.getRecords().length);
	        boolean done = false;
        	
	        while(!done){
	        	for (int k = 0; k < describeGlobalResult.getRecords().length; k++){
	        	    Attachment a = (Attachment)describeGlobalResult.getRecords()[k];
	        	    listAttachments.add(a);
	        	}
        	
	        	if (describeGlobalResult.isDone()) {
	               done = true;
	            } else {
	           	 describeGlobalResult = partnercon.queryMore(describeGlobalResult.getQueryLocator());
	            }
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return listAttachments;
	}

}
