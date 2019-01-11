
public class HighScores {
	private int firstScore;
	private int secondScore;
	private int thirdScore;
	private String firstPlayer;
	private String secondPlayer;
	private String thirdPlayer;
	public HighScores() {
		firstPlayer = null;
		secondPlayer = null;
		thirdPlayer = null;
		firstScore = Integer.MAX_VALUE;
		secondScore = Integer.MAX_VALUE;
		thirdScore = Integer.MAX_VALUE;
	}
		
	public void addHighScore(String playerName, int score) {
		if (score < firstScore) {
			thirdScore = secondScore;
			secondScore = firstScore;
			firstScore = score;
			thirdPlayer = secondPlayer;
			secondPlayer = firstPlayer;
			firstPlayer = playerName;
		}
		
		else if (score < secondScore) {
			thirdScore = secondScore;
			secondScore = score;
			thirdPlayer = secondPlayer;
			secondPlayer = playerName;
		}
		
		else if (score < thirdScore) {
			thirdScore = score;
			thirdPlayer = secondPlayer;
			secondPlayer = playerName;
		}
		
		
	}
	
	public String getFirst() {
		String highScoreEntry;
		if (firstPlayer == null) {
			return null;
		}
		highScoreEntry = (firstPlayer + ": " + firstScore);
		return highScoreEntry;
	}
	
	public String getSecond() { 
		String highScoreEntry;
		if (secondPlayer == null) {
			return null;
		}
		highScoreEntry = (secondPlayer + ": " + secondScore);
		return highScoreEntry;
	}
	
	public String getThird() {
		String highScoreEntry;
		if (thirdPlayer == null) {
			return null;
		}
		highScoreEntry = (thirdPlayer + ": " + thirdScore);
		return highScoreEntry;
	
	}
	
	public void setFirstPlayer(String player) {
		firstPlayer = player;
	}
	
	public void setFirstScore(int score) {
		firstScore = score;
		
	}
	public void setSecondPlayer(String player) {
		secondPlayer = player;
	}
	
	public void setSecondScore(int score) {
		secondScore = score;
		
	}
	public void setThirdPlayer(String player) {
		thirdPlayer = player;
	}
	
	public void setThirdScore(int score) {
		thirdScore = score;
		
	}
	
}