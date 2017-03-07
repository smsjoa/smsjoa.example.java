

import com.innosms.api.*;

public class SendMMS {
	
	public static void main(String[] args) {
		
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);

		MsgContent content = new MsgContent();		
		content.setMsgType("mms"); //�޼��� ����
		content.setCallback("0623500704"); //ȸ�Ź�ȣ
		content.setPhone("0111111111"); //���Ź�ȣ, ���������� ��� ��ǥ�� ���� ex)0111111111,0111111112
		content.setSubject("�޼��� �����Դϴ�."); //�޼��� ����
		content.setMsg("abc 123!@#\n123 �幮�����׽�Ʈ�Դϴ�."); //���۸޼���				
		content.setImage("D:\\eclipse\\workspace\\java_example\\mms\\image_test.jpg"); //�̹��� ������
		//���������� ��� ����ð� �߰�
		//content.setTrandate("20141130193011"); //�ð� ���� : YYYYMMDDHHMMSS
		
		String result = messagingService.sendMessage(content);
		
		/******************************************************************************
		- �����(JSON String)
		msg_serial : ���� �޼��� ����Ű(���۳��� ��ȸ �� ���� ��Ҹ� ���� DB�� �����ؼ� �����ϼ���)
		total_count : �� ���۰Ǽ�
		cost : ���� ���� �ݾ�
		******************************************************************************/		
		System.out.println(result);
		
	}

}