

import com.innosms.api.*;

public class GetMessage {

	public static void main(String[] args) {
		
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);

		MsgList param = new MsgList();
		param.setMsgSerial("M100001_14164530429913"); //메시지 시리얼
		param.setListCount(100); //리스트 출력 갯수
		param.setPage(1); //페이지
				
		String result = messagingService.getMessage(param);
		
		/******************************************************************************
		- 결과값(JSON String)
		data : 전송내역
			trandate : 전송시간
			phone : 수신번호
			status : 전송상태
			rsltcode : 결과코드
			msg_type : 문자타입
			telecom : 이동통신사
			rsltdate : 결과 처리 시간
		total_count : 총 전송 건수
		******************************************************************************/		
		System.out.println(result);
		
	}
	
}