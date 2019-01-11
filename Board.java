import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import java.io.*;

public class Board {
	// initialize a board state that contains the integer values of number of mines around tile
	/*
		private boolean[][] boardState; 
		private int width;
		private int height;
		public Board() {
			width = 10;
			height = 10;
			boardState = new boolean[width][height];
			//TODO be more systematic in randomly assigning mines to the board
			/*
			 * Currently placing mines as:
			 * X 0 X 0 X 0 X 0 X 0
			 * 0 0 0 0 0 0 0 0 0 0
			 * X 0 X 0 X 0 X 0 X 0
			 * 0 0 0 0 0 0 0 0 0 0
			 * X 0 X 0 X 0 X 0 X 0
			 * 0 0 0 0 0 0 0 0 0 0
			 * X 0 X 0 X 0 X 0 X 0
			 * 0 0 0 0 0 0 0 0 0 0
			 * X 0 X 0 X 0 X 0 X 0
			 * 0 0 0 0 0 0 0 0 0 0
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if ((i % 2 == 0) && (j % 2 == 0)) {
						boardState[i][j] = true;
					}
					else {
						boardState[i][j] = false; 
					}
				}
			}	
		}
		
		public int getHeight() {
			return height;
		}
		
		public int getWidth() {
			return width;
		}
		
		public boolean[][] getBoardState() {
			return boardState;
		}
		
		public int getNumMines(int x, int y) {
			int counter = 0;
			for (int i = Math.max(x - 1,  0); i <= Math.min(x + 1, (boardState.length) - 1); i++) {
				for (int j = Math.max((y - 1), 0); j <= Math.min(y + 1, boardState[0].length - 1); j++) {
					if (boardState[i][j]) {
						counter++;
					}
				}
			}
			return counter;
			
		}
}
*/
		/*
		public void surroundingEmpty(int x, int y) {
			for (int i = Math.max(x - 1,  0); i <= Math.min(x + 1, (boardState.length) - 1); i++) {
				for (int j = Math.max((y - 1), 0); j <= Math.min(y + 1, boardState[0].length - 1); j++) {
					if (getNumMines(i, j == 0)
						counter++;
					}
				}
			}
		}
	*/
	/*
	 * 
	 * This class effectively holds the board state independent of the GUI at any given time
	 */
	private Tile[][] boardState;
	private int width;
	private int height;
	private boolean gameStarted;
	private int nonMines;
	private boolean gameWon;
	private boolean gameLost;
	private String playerName;
	private HighScores currentHighScores;
	private TimeScore timeScore; 
	private BufferedReader bufferedReader;
	private BufferedWriter bw;
	String highScore1;
	String highScore2;
	String highScore3;
	File highScoreFile;
	public Board() {
		gameStarted = false;
		width = 10;
		height = 10;
		nonMines = 0;
		boardState = new Tile[width][height];
		timeScore = new TimeScore(); 
		currentHighScores = new HighScores();
		//System.out.println(currentHighScores.getFirst());
		//get current directory
	
		try {
			//try to read the existing file and put 
			FileReader reader = new FileReader("highScores.txt");
			bufferedReader = new BufferedReader(reader);
			try {
				String line = bufferedReader.readLine();
				while (line != null) { 
					int index = line.indexOf(":");
					if ((index < 1) || (index == line.length() - 1)) {
						throw new IOException("improperly formatted line");
					}
					String playerName = line.substring(0, index).trim();
					int score = Integer.parseInt(line.substring(index + 1, line.length()).trim());
					currentHighScores.addHighScore(playerName, score);
					line = bufferedReader.readLine();
				}
			}
			catch (IOException ex) {
				System.out.println("Error");
			}			
	
		}
		
		catch (FileNotFoundException e) { 
			System.out.println("File not found");
			highScoreFile = new File("highScores.txt");
		}
		
		
		
		//Initialize the board with the coordinates of each 
		/*
		JOptionPane.showMessageDialog(null, "Welcome to Minesweeper! Upon closing of this instruction window, a new popup window will prompt you to enter your player name.\n"
				+ "Then after entering your player name, the minesweeper game board will be displayed. In order to begin the game, you must press the start button\n"
				+ "at the bottom of the screen. Upon clicking the start button, the timer will begin and you will be challenged to select all of the non-mines\n"
				+ "without accidentally clicking on a mine. If you click on a mine, you lose! The normal game details of minesweeper apply. Clicking on a tile that\n"
				+ "has no surrounding mines around will change its color to white, and will also change all surrounding open tile. If you click on a tile that has\n"
				+ "surrounding mines, the number of surrounding mines will be displayed on the tile. Use these numbers to help you identify which tiles have mines\n"
				+ "and which do not. IMPORTANT: you are unable to right click on a tile to flag it for a mine, so this will challenge your memory skills. Additionally,\n"
				+ "the game is laid out as 10 X 10 for faster game play!. Good luck!");
		playerName = JOptionPane.showInputDialog("Please Enter Name");
		*/
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardState[i][j] = new Tile();
				//x and y oriented in the upper left corner of the tile
				(boardState[i][j]).setX(i * 100);
				(boardState[i][j]).setY(j * 100);
			}
		}
		
		//place mines on the board and update the board state
		placeMines(boardState);
		
		//after mines have been placed update the number of mines surrounding each place
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boardState[i][j].setNumMines(getNumMines(i, j));	
				
			}
		}
		
		
		// set the high scores
		
	}
	
	public void placeMines(Tile[][] gameBoard) {
		nonMines = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				//roughly 30% of tile should have mines
				if (Math.random() > .85) {
					boardState[i][j].placeMine();
				}
				else {
					boardState[i][j].noMine(); 
					nonMines++;
				}
				/*
				if (Math.random() > .7) {
					gameBoard[i][j].placeMine();
				}
				else {
					gameBoard[i][j].noMine();
				}
				*/
				
			}
		}
		
	}
	public void setPlayerName(String name) {
		playerName = name;
	}
	
	public void setNonMines(int value) {
		nonMines = value;
	}
	public int getHeight() {
		//System.out.println(height);
		return height;
	}
	
	public int getWidth() {
		//System.out.println(width);
		return width;
		
	}
	
	public int getNumMines(int x, int y) {
		int counter = 0;
		for (int i = Math.max(x - 1,  0); i <= Math.min(x + 1, (boardState.length) - 1); i++) {
			for (int j = Math.max((y - 1), 0); j <= Math.min(y + 1, boardState[0].length - 1); j++) {
				if ((boardState[i][j]).hasMine()) {
					counter++;
				}
			}
		}
		return counter;
		
	}
	
	public Tile[][] getBoardState() {
		return boardState;
			
	}
	
	public void startGame() {
		gameStarted = true;
		
	}
	
	public boolean isStarted() {
		return gameStarted;
	}
	public void incrementNonMines() {
		nonMines++;
	}
	
	public void decrementNonMines() {
		nonMines--;
		
	}
	public int getNonMines () {
		//System.out.println(nonMines);
		return nonMines;
	}

	public void endGameWin() {
		gameWon = true;
		System.out.println(playerName);
		timeScore.stop();
		currentHighScores.addHighScore(playerName, timeScore.getElapsedSeconds());
		try {
			bw = new BufferedWriter(new FileWriter("highScores.txt")); 
			
		}
		catch (IOException e){
			System.out.println("error");
		}
		System.out.println(currentHighScores.getFirst());
		try {
			if (currentHighScores.getFirst() != null) {
				bw.write(currentHighScores.getFirst());
				bw.newLine();
			}
				
			if (currentHighScores.getSecond() != null) {
				bw.write(currentHighScores.getSecond());
				bw.newLine();
			}

			if (currentHighScores.getThird() != null) {
				bw.write(currentHighScores.getThird());
			}
			bw.close();
		}
		catch (IOException e) {
			System.out.println("Error");
		}
		
	}
	
	public boolean isGameWon() {
		return gameWon;
	}
	
	public boolean isGameLost() {
		return gameLost;
	}
	
	public void endGameLost() {
		timeScore.stop();
		gameLost = true;
		
	}
	
	public TimeScore getTimeScore() {
		return timeScore; 
		
	}
	
	public void draw(Graphics g) {
		//System.out.println(nonMines);
		if (gameWon) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
			g.drawString("You Win!", 375, 500);
		}
		
		else if (gameLost) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
			g.drawString("You Lose!", 375, 500);
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.drawString("HIGH SCORES", 40, 1250);
		if (currentHighScores.getFirst() != null) {
			g.drawString("1.) " + currentHighScores.getFirst(), 50, 1300);
		}
			
		if (currentHighScores.getSecond() != null) {
			g.drawString("2.) " + currentHighScores.getSecond(), 250, 1300);
		}

		if (currentHighScores.getThird() != null) {
			g.drawString("3.) " + currentHighScores.getThird(), 450, 1300);
		}
		
			
	
		
			
	}
		
	

	
	/*
	public void surroundingEmpty(int x, int y) {
		for (int i = Math.max(x-1, 0); i <= Math.min(x + 1, (boardState.length) - 1); i++) {
			for (int j = Math.max((y - 1), 0); j <
			
		}
		*/
		
	}
	
		
