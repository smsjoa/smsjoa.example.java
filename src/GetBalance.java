
import com.innosms.api.*;

public class GetBalance {

	public static void main(String[] args) {
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);
		String money = messagingService.getBalance();
		
		/******************************************************************************
		- 결과값
		money : 잔여금액
		******************************************************************************/ 
		System.out.println(money);
	}

}
