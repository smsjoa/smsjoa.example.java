

import com.innosms.api.*;

public class CancelReservation {

	public static void main(String[] args) {
		
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);
		
		String result = messagingService.cancelReservation("M100001_14164530429913");
		
		/******************************************************************************
		- �����(JSON String)
		msg_serial : ����� �޼��� ����Ű
		******************************************************************************/		
		System.out.println(result);
		
	}

}
