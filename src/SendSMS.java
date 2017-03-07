

import com.innosms.api.*;

public class SendSMS {

	public static void main(String[] args) {
	
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);

		//동일내용 전송
		MsgContent content = new MsgContent();		
		content.setMsgType("sms"); //메세지 형식
		content.setCallback("0623500704"); //회신번호
		content.setPhone("01111111111"); //수신번호, 동보전송일 경우 쉼표로 구분 ex)0111111111,0111111111		
		content.setMsg("abc 123!@#\n123한글"); //전송메세지				

		//개별내용 전송(SMS형식만 지원)
		/*
		JSONObject msgList = new JSONObject();
		msgList.put("0111111111", "msg 내용1\n 123");
		msgList.put("0111111112", "msg 2\n 333");
		
		MsgContent content = new MsgContent();
		content.setMsgType("sms"); //메세지 형식
		content.setCallback("0623500704");
		content.setMsgList(msgList);
		*/
		
		//예약전송일 경우 예약시간 추가
		//content.setTrandate("20141130193011"); //시간 형식 : YYYYMMDDHHMMSS
		
		/******************************************************************************
		- 결과값(JSON String)
		msg_serial : 전송 메세지 고유키(전송내역 조회 및 예약 취소를 위해 DB에 저장해서 보관하세요)
		total_count : 총 전송건수
		cost : 전송 차감 금액
		******************************************************************************/
		String result = messagingService.sendMessage(content);
		System.out.println(result);
		
	}
	
}