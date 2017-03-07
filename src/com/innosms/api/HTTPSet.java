package com.innosms.api;


public class HTTPSet {
	String uri;
	String method;
	String auth; 
	String header = null;
	String urlParam = null;
	MsgContent content = null;
	boolean isMultiPart=false;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getUrlParam() {
		return urlParam;
	}
	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
	
	public MsgContent getContent() {
		return content;
	}
	public void setContent(MsgContent content) {
		this.content = content;
	}
	
	public boolean isMultiPart() {
		return isMultiPart;
	}
	public void setMultiPart(boolean isMultiPart) {
		this.isMultiPart = isMultiPart;
	}
}