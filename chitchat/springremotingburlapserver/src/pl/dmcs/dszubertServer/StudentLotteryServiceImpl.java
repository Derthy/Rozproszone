package pl.dmcs.dszubertServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StudentLotteryServiceImpl implements StudentLotteryService{

	private static FileWriter fw;

	@Override
	public String getNewMessagesFromServer(String message) throws IOException{
		File f = new File("C:\\Users\\ddsb\\Workspace_DS\\chitchat\\springremotingburlapserver\\bin\\messages.txt");
		if(f.exists() && !f.isDirectory()) { 
			String content = readFile(f.toString(), StandardCharsets.UTF_8);
			return content;
		}
		return null;
	}

	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
	}

	@Override
	public void SendNewMessageToServer(String message) throws IOException {
		fw = new FileWriter("C:\\Users\\ddsb\\Workspace_DS\\chitchat\\springremotingburlapserver\\bin\\messages.txt", true);
		fw.append(message + "\r\n");
		fw.close();
	}

}
