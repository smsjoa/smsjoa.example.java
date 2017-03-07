

import com.innosms.api.*;

public class SendMMS {
	
	public static void main(String[] args) {
		
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);

		MsgContent content = new MsgContent();		
		content.setMsgType("mms"); //메세지 형식
		content.setCallback("0623500704"); //회신번호
		content.setPhone("0111111111"); //수신번호, 동보전송일 경우 쉼표로 구분 ex)0111111111,0111111112
		content.setSubject("메세지 제목입니다."); //메세지 제목
		content.setMsg("abc 123!@#\n123 장문전송테스트입니다."); //전송메세지				
		content.setImage("D:\\eclipse\\workspace\\java_example\\mms\\image_test.jpg"); //이미지 절대경로
		//예약전송일 경우 예약시간 추가
		//content.setTrandate("20141130193011"); //시간 형식 : YYYYMMDDHHMMSS
		
		String result = messagingService.sendMessage(content);
		
		/******************************************************************************
		- 결과값(JSON String)
		msg_serial : 전송 메세지 고유키(전송내역 조회 및 예약 취소를 위해 DB에 저장해서 보관하세요)
		total_count : 총 전송건수
		cost : 전송 차감 금액
		******************************************************************************/		
		System.out.println(result);
		
	}

}