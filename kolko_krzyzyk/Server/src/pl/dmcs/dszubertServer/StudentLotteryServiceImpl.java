package pl.dmcs.dszubertServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentLotteryServiceImpl implements StudentLotteryService{

	public List<String> usernames = new ArrayList<String>();
	Map<Integer,ArrayList<String>> mapy = new HashMap<Integer,ArrayList<String>>();
	String[] match = new String[]{"","","","","","","","",""};
	int turn = 0;

	@Override
	public String getMatchStatus(String message){
		return ""+this.usernames.size();
	}

	@Override
	public int SendNewMessageToServer(String message) {
		for (String username: usernames){
			if(username.equals(message))
				return 0;
		}
		this.usernames.add(message);
		return 1;
	}

	@Override
	public void removeUser(String user) {
		this.usernames.remove(user);
	}

	@Override
	public String[] getMatch() {
		return this.match;
	}
	
	@Override
	public String getSign(String username){
		int index = this.usernames.indexOf(username);
		if (index==0)
			return "x";
		else
			return "o";
	}
	
	@Override
	public void setMatch(String[] match) {
		this.match=match;
	}
	
	@Override
	public int getTurn() {
		return this.turn;
	}
	
	@Override
	public void setTurn(int turn) {
		this.turn=turn;
	}
	
	@Override
	public int checkIfEnd() {
		if(match[0].equals("x") && match[1].equals("x") && match[2].equals("x"))
			return 0;
		else if(match[3].equals("x") && match[4].equals("x") && match[5].equals("x"))
			return 0;
		else if(match[6]=="x" && match[7]=="x" && match[8]=="x")
			return 0;
		else if(match[0]=="x" && match[3]=="x" && match[6]=="x")
			return 0;
		else if(match[1]=="x" && match[4]=="x" && match[7]=="x")
			return 0;
		else if(match[2]=="x" && match[5]=="x" && match[8]=="x")
			return 0;
		else if(match[0]=="x" && match[4]=="x" && match[8]=="x")
			return 0;
		else if(match[2]=="x" && match[4]=="x" && match[6]=="x")
			return 0;
		else if(match[0]=="o" && match[1]=="o" && match[2]=="o")
			return 0;
		else if(match[3]=="o" && match[4]=="o" && match[5]=="o")
			return 1;
		else if(match[6]=="o" && match[7]=="o" && match[8]=="o")
			return 1;
		else if(match[0]=="o" && match[3]=="o" && match[6]=="o")
			return 1;
		else if(match[1]=="o" && match[4]=="o" && match[7]=="o")
			return 1;
		else if(match[2]=="o" && match[5]=="o" && match[8]=="o")
			return 1;
		else if(match[0]=="o" && match[4]=="o" && match[8]=="o")
			return 1;
		else if(match[2]=="o" && match[4]=="o" && match[6]=="o")
			return 1;
		else 
			return 2;
	}
	
	@Override
	public void resetMap() {
		this.match = new String[]{"","","","","","","","",""};
	}
	
}
