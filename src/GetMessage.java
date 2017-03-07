

import com.innosms.api.*;

public class GetMessage {

	public static void main(String[] args) {
		
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);

		MsgList param = new MsgList();
		param.setMsgSerial("M100001_14164530429913"); //�޽��� �ø���
		param.setListCount(100); //����Ʈ ��� ����
		param.setPage(1); //������
				
		String result = messagingService.getMessage(param);
		
		/******************************************************************************
		- �����(JSON String)
		data : ���۳���
			trandate : ���۽ð�
			phone : ���Ź�ȣ
			status : ���ۻ���
			rsltcode : ����ڵ�
			msg_type : ����Ÿ��
			telecom : �̵���Ż�
			rsltdate : ��� ó�� �ð�
		total_count : �� ���� �Ǽ�
		******************************************************************************/		
		System.out.println(result);
		
	}
	
}