package com.innosms.api;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class MessagingService {	
	final String ServiceURL = "https://api.smsjoa.com";
	final String Version = "1";
	
	private String ClientID;
	private String APIKey;
	private String Token;
	
	public MessagingService(String ClientID, String APIKey){
		this.ClientID = ClientID;
		this.APIKey = APIKey;
		this.Token = this.getToken();		
	}

	private JSONObject requestHTTP(HTTPSet set){
		int http_status = 0;
		String response = null;
		HttpsURLConnection conn = null;
		DataOutputStream out = null;
	    BufferedReader reader = null;	    
	    String inputLine = null;
	    
	    JSONObject resultObj = null;
	    
	    try {
	    	String uri = set.getUri();
	    	String method = set.getMethod();	    	
	    	String apiUrl = ServiceURL + "/" + Version + "/" + uri;
	    	
	    	if(set.getUrlParam() != null){
	    		apiUrl = apiUrl + set.getUrlParam();
	    	}	
	    	
		    final URL url = new URL(apiUrl);
		    boolean isPost = (method.equals("POST"))?true:false;
		    
		    conn = (HttpsURLConnection) url.openConnection();
		    conn.setRequestProperty("Accept-Charset","UTF-8");
		    
		    String boundary = "----------------------------"+random();
		    String delimiter = "\r\n--" + boundary + "\r\n";
		    
		    if(set.isMultiPart()){
		    	conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
		    }else{		    	
		    	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8;");
		    }
		    
		    conn.setRequestProperty("Authorization", set.getAuth());
		    conn.setRequestMethod(method);
	    	
		    if(isPost){		    	
		    	conn.setDoOutput(isPost);		    	
		    	
			    out = new DataOutputStream(new BufferedOutputStream(conn.getOutputStream()));
			    String content = null;
			    
			    if(uri.equals("send")){
			    	
			    	MsgContent data = set.getContent();
			    	
			    	if(data.getMsgType() != null && data.getMsgType() == "mms"){//MMS 전송처리		    		
			    		StringBuffer postDataBuilder = new StringBuffer();
			    		if(data.getMsgType() != null){
			    			postDataBuilder.append(delimiter);
			    			postDataBuilder.append(setValue("msg_type",data.getMsgType()));
			    		}
			    		if(data.getPhone() != null){
			    			postDataBuilder.append(delimiter);
			    			postDataBuilder.append(setValue("phone",data.getPhone()));
			    		}
			    		if(data.getCallback() != null){
			    			postDataBuilder.append(delimiter);
			    			postDataBuilder.append(setValue("callback",data.getCallback()));
			    		}
			    		if(data.getSubject() != null){
			    			postDataBuilder.append(delimiter);
			    			postDataBuilder.append(setValue("subject",data.getSubject()));
			    		}
			    		if(data.getMsg() != null){			    		
			    			postDataBuilder.append(delimiter);			    		
			    			postDataBuilder.append(setValue("msg",data.getMsg()));
			    		}		    		
			    		if(data.getImage() != null){
			    			postDataBuilder.append(delimiter);
			    			postDataBuilder.append(setFile("image",data.getImage()));
			    			postDataBuilder.append("\r\n");
			    		}
			    		
			    		out.writeUTF(postDataBuilder.toString());
			    		
			    		FileInputStream in = new FileInputStream(data.getImage());
			    		// 파일 복사 작업 시작
			            int maxBufferSize = 1024;
			            int bufferSize = Math.min(in.available(), maxBufferSize);
			            byte[] buffer = new byte[bufferSize];
			     
			            // 버퍼 크기만큼 파일로부터 바이트 데이터를 읽는다.
			            int byteRead = in.read(buffer, 0, bufferSize);
			     
			            // 전송
			            while (byteRead > 0) {
			            	out.write(buffer);
			                bufferSize = Math.min(in.available(), maxBufferSize);
			                byteRead = in.read(buffer, 0, bufferSize);
			            }
			            
			            in.close();
			            //content = delimiter;
			            out.writeBytes(delimiter);
			    	}else{//SMS,LMS 전송처리			    		
				    	if(data.getMsgType() != null){
				    		content = URLEncoder.encode("msg_type","UTF8")+"="+URLEncoder.encode(data.getMsgType(), "UTF8");
				    	}
				    			    	
					    if(data.getPhone() != null){
					    	content += "&"+URLEncoder.encode("phone","UTF8")+"="+URLEncoder.encode(data.getPhone(), "UTF8");
					    }
					    
					    if(data.getCallback() != null){					    
					    	content += "&"+URLEncoder.encode("callback","UTF8")+"="+URLEncoder.encode(data.getCallback(), "UTF8");
					    }				    
					    
					    if(data.getSubject() != null){
					    	content += "&"+URLEncoder.encode("subject", "UTF8")+"="+URLEncoder.encode(data.getSubject(), "UTF8");				    	
					    }
					    
				    	if(data.getMsgList() != null)
				    	{
				    		content += "&"+URLEncoder.encode("msg_list","UTF8")+"="+URLEncoder.encode(data.msgList.toString(), "UTF8");
				    	}
				    	
					    if(data.getMsg() != null){					    
					    	content += "&"+URLEncoder.encode("msg", "UTF8")+"="+URLEncoder.encode(data.getMsg(), "UTF8");
					    }
					    
					    if(data.getTrandate() != null){
					    	content += "&"+URLEncoder.encode("trandate", "UTF8")+"="+URLEncoder.encode(data.getTrandate(), "UTF8");				    	
					    }
			    	}
				}
			    
			    if(content != null){
			    	out.writeBytes(content);
			    }
			    
			    out.flush();
			    out.close();		    	
		    }	   
		    
		    http_status = conn.getResponseCode();
		    
		    if(http_status == 200){		    	
		    	reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));	    
		    	while ((inputLine = reader.readLine()) != null) {
					response = inputLine;
				}	    	
		    	reader.close();
		    	
		    	if(response != null){		  
		    		resultObj = (JSONObject)JSONValue.parse(response);
		    	}
		    }else{	  
		    	reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		    	while ((inputLine = reader.readLine()) != null) {
					response = inputLine;
				}	    	
		    	reader.close();
		    	if(response != null){		  
		    		resultObj = (JSONObject)JSONValue.parse(response);
		    	}
		    	
		    	throw new APIException(resultObj.get("code").toString(),resultObj.get("message").toString());
		    }		    
		    	    	
		} catch (Exception e) {
			System.out.println("Error:"+e.getMessage());
		}
		
		return resultObj;		
	}
	

	private String getToken(){
		String token = null;
		HTTPSet set = new HTTPSet();
		set.setUri("token");
		set.setAuth("Basic "+new String(Base64.encodeBase64((this.ClientID+":"+this.APIKey).getBytes())));
		set.setMethod("POST");
		
		JSONObject result = this.requestHTTP(set);
		
		try{
			token = result.get("token").toString();
		}catch(Exception e){	
			token =  e.getMessage();
		}
		
		return token;
	}
	
	public void deleteToken(){
		HTTPSet set = new HTTPSet();
		set.setUri("token");
		set.setAuth("Bearer "+this.Token);
		set.setMethod("DELETE");
		
		this.requestHTTP(set);
	}
	
	public String getBalance(){	
		String money = null;
		HTTPSet set = new HTTPSet();
		set.setUri("balance");
		set.setAuth("Bearer "+this.Token);
		set.setMethod("GET");
		
		JSONObject result = this.requestHTTP(set);
		
		money = result.get("money").toString();

		//this.deleteToken();
		return money;
	}
	
	public String sendMessage(MsgContent content){		
		JSONObject result = null;
		HTTPSet set = new HTTPSet();
		set.setUri("send");
		set.setAuth("Bearer "+this.Token);
		set.setMethod("POST");				
		set.setContent(content);
		if(content.getMsgType() != null && content.getMsgType().equals("mms")){			
			set.setMultiPart(true);
		}
				
		result = this.requestHTTP(set);
				
		return result.toString();
	}
	
	public String getMessage(MsgList param) {
		JSONObject result = null;
		HTTPSet set = new HTTPSet();
		set.setUri("send");
		set.setAuth("Bearer "+this.Token);
		set.setMethod("GET");				
		set.setUrlParam("/"+param.getMsgSerial()+"/"+param.getListCount()+"/"+param.getPage());
		
		result = this.requestHTTP(set);
		
		return result.toString();
	}
	
	public String cancelReservation(String msg_serial) {
		JSONObject result = null;
		HTTPSet set = new HTTPSet();
		set.setUri("reservation");
		set.setAuth("Bearer "+this.Token);
		set.setMethod("DELETE");
		set.setUrlParam("/"+msg_serial);
		
		result = this.requestHTTP(set);
		
		return result.toString();
	}
	
	private String setValue(String key, String value) {
		return "Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n"+ value;
	}
	
	private String setFile(String key, String fileName) {
        return "Content-Disposition: form-data; name=\"" + key + "\";filename=\"" + fileName + "\"\r\n";
    }	
	
	private String random() {
		char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();
        Random rn = new Random();
        for( int i = 0 ; i < 10 ; i++ ){
            sb.append( charaters[ rn.nextInt( charaters.length ) ] );
        }
        
        return sb.toString();
	}
	
}