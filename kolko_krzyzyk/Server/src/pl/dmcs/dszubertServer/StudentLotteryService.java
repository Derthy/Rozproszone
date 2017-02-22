package pl.dmcs.dszubertServer;

public interface StudentLotteryService {

	String getMatchStatus(String message);
	int SendNewMessageToServer(String message);
	void removeUser(String user);
	public String[] getMatch();
	public void setMatch(String[] match);
	public String getSign(String username);
	public int getTurn();
	public void setTurn(int turn);
	public int checkIfEnd();
	public void resetMap();
}
