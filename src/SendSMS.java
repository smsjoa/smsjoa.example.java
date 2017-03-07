

import com.innosms.api.*;

public class SendSMS {

	public static void main(String[] args) {
	
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);

		//���ϳ��� ����
		MsgContent content = new MsgContent();		
		content.setMsgType("sms"); //�޼��� ����
		content.setCallback("0623500704"); //ȸ�Ź�ȣ
		content.setPhone("01111111111"); //���Ź�ȣ, ���������� ��� ��ǥ�� ���� ex)0111111111,0111111111		
		content.setMsg("abc 123!@#\n123�ѱ�"); //���۸޼���				

		//�������� ����(SMS���ĸ� ����)
		/*
		JSONObject msgList = new JSONObject();
		msgList.put("0111111111", "msg ����1\n 123");
		msgList.put("0111111112", "msg 2\n 333");
		
		MsgContent content = new MsgContent();
		content.setMsgType("sms"); //�޼��� ����
		content.setCallback("0623500704");
		content.setMsgList(msgList);
		*/
		
		//���������� ��� ����ð� �߰�
		//content.setTrandate("20141130193011"); //�ð� ���� : YYYYMMDDHHMMSS
		
		/******************************************************************************
		- �����(JSON String)
		msg_serial : ���� �޼��� ����Ű(���۳��� ��ȸ �� ���� ��Ҹ� ���� DB�� �����ؼ� �����ϼ���)
		total_count : �� ���۰Ǽ�
		cost : ���� ���� �ݾ�
		******************************************************************************/
		String result = messagingService.sendMessage(content);
		System.out.println(result);
		
	}
	
}