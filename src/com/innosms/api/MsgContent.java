package com.innosms.api;

import org.json.simple.JSONObject;

public class MsgContent{
	String msgType;
	String phone;
	String callback;
	String trandate;
	String subject;
	String msg;
	JSONObject msgList;
	String image;
	
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msg_type) {
		this.msgType = msg_type;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	
	public String getTrandate() {
		return trandate;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public JSONObject getMsgList() {
		return msgList;
	}
	public void setMsgList(JSONObject msgList) {
		this.msgList = msgList;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	
}