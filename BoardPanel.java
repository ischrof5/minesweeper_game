import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.*;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private Board board;
	private Tile[][] gameBoard;
	private TimeScore timeScore;
	private String playerName; 
	BoardPanel () { 
		board = new Board();
		gameBoard = board.getBoardState();
		timeScore = board.getTimeScore();
		JOptionPane.showMessageDialog(null, "Welcome to Minesweeper! Upon closing of this instruction window, a new popup window will prompt you to enter your player name.\n"
				+ "Then after entering your player name, the minesweeper game board will be displayed. In order to begin the game, you must press the start button\n"
				+ "at the bottom of the screen. Upon clicking the start button, the timer will begin and you will be challenged to select all of the non-mines\n"
				+ "without accidentally clicking on a mine. If you click on a mine, you lose! The normal game details of minesweeper apply. Clicking on a tile that\n"
				+ "has no surrounding mines around will change its color to white, and will also change all surrounding open tile. If you click on a tile that has\n"
				+ "surrounding mines, the number of surrounding mines will be displayed on the tile. Use these numbers to help you identify which tiles have mines\n"
				+ "and which do not. IMPORTANT: you are unable to right click on a tile to flag it for a mine, so this will challenge your memory skills. Additionally,\n"
				+ "the game is laid out as 10 X 10 for faster game play!. Good luck!");
		playerName = JOptionPane.showInputDialog("Please Enter Name");
		board.setPlayerName(playerName);
		addMouseListener(new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
				//System.out.println(e.getX());
				//System.out.println(e.getY());
				int xValue = e.getX() / 100;
				int yValue = e.getY() / 100;
				//System.out.println(xValue);
				//System.out.println(yValue);
				//mouse click out of bounds
				//
				if (e.getX() > 400 && e.getX() < 560 && e.getY() > 1130 && e.getY() < 1180) {
					timeScore.start();
					board.startGame();
				}
				if (xValue > 9 || yValue > 9) {
				}	
				else if (board.isStarted() && !board.isGameWon() && !board.isGameLost()){ 
					if ((!gameBoard[xValue][yValue].hasMine()) && (gameBoard[xValue][yValue].getNumMines() == 0)) {
						fillSurroundingEmpty(xValue, yValue);
					}
					else if (gameBoard[xValue][yValue].hasMine()) {
						gameBoard[xValue][yValue].clicked();
						board.endGameLost();
						
					}
					else if (!gameBoard[xValue][yValue].isClicked()) {
						gameBoard[xValue][yValue].clicked();
						board.decrementNonMines();
						if (board.getNonMines() == 0) {
							board.endGameWin();
							timeScore.stop();
						}
					}
					repaint();
				}
				
			}
		});
		Timer timer = new Timer(35, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start();
		
		
			
		}

		void tick() {
			if (board.getNonMines() == 0) {
				//System.out.println("Stopped");
				//String name = JOptionPane.showMessageDialog("")
			}
			repaint();
		
		
	}
	//recursive function to fill the surrounding empty tiles if an empty tile is selected
	public void fillSurroundingEmpty (int x, int y) {
		//base case when recursive algorithm reaches the edge of the board
		//System.out.println(x);
		//System.out.println(y);
		if (x >= 10 || x < 0 || y < 0 || y >= 10 || (gameBoard[x][y].hasMine()) || (gameBoard[x][y].isClicked()) || board.isGameWon() || board.isGameLost()) {
			return;
		}
		else if (!(gameBoard[x][y].hasMine()) && (gameBoard[x][y].getNumMines() != 0) && (!gameBoard[x][y].isClicked())) {
			gameBoard[x][y].clicked();
			board.decrementNonMines();
			if (board.getNonMines() == 0) {
				board.endGameWin();
			}
		}
			
		else {
			
			gameBoard[x][y].clicked();
			board.decrementNonMines();
			if (board.getNonMines() == 0) {
				board.endGameWin();
			}
			//recursively check for 0 tiles around
			fillSurroundingEmpty(x, y - 1);
			fillSurroundingEmpty(x, y + 1);
			fillSurroundingEmpty(x + 1, y);
			fillSurroundingEmpty(x - 1, y);
			fillSurroundingEmpty(x+1, y+1);
			fillSurroundingEmpty(x-1, y-1);
			fillSurroundingEmpty(x-1, y+1);
			fillSurroundingEmpty(x+1, y-1);
		}
		
		
	}
	
		
	@Override
	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				//each tile draws itself
				gameBoard[i][j].draw(g); 
				
			}
		}
		g.setColor(Color.BLACK);
		board.draw(g);
		timeScore.draw(g);
		
		
	}
}