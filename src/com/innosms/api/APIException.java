package com.innosms.api;

public class APIException extends Exception{
	private static final long serialVersionUID = 3578113825594387534L;
	private String code;
	private String message;
	
	public APIException(String code, String message){
		this.code = code;
		this.message = message;
	}
	
	public String getMessage(){
		return "["+this.code+"]"+this.message;
	}
}
