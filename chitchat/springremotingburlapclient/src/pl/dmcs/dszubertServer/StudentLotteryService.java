package pl.dmcs.dszubertServer;

public interface StudentLotteryService {

	String getNewMessagesFromServer(String oldMessages);
	void SendNewMessageToServer(String message);
}
