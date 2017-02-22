package pl.dmcs.dszubertServer;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface StudentLotteryService {

	String getNewMessagesFromServer(String message) throws FileNotFoundException, IOException;
	void SendNewMessageToServer(String message) throws IOException;
}
