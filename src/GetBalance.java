
import com.innosms.api.*;

public class GetBalance {

	public static void main(String[] args) {
		MessagingService messagingService = new MessagingService(Config.CLIENT_ID,Config.API_KEY);
		String money = messagingService.getBalance();
		
		/******************************************************************************
		- �����
		money : �ܿ��ݾ�
		******************************************************************************/ 
		System.out.println(money);
	}

}
