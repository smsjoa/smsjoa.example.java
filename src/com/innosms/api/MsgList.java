package com.innosms.api;

public class MsgList {
	String msgSerial = null; 
	int listCount = 20;
	int page = 1;
	
	public String getMsgSerial() {
		return msgSerial;
	}
	public void setMsgSerial(String msgSerial) {
		this.msgSerial = msgSerial;
	}
	
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}


}
