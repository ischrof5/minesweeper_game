import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
public class GameLayout implements Runnable {
	public void run() {
		JFrame frame = new JFrame("Minesweeper!");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		BoardPanel boardPanel = new BoardPanel();
		//GameFeaturesPanel featurePanel = new GameFeaturesPanel();
		mainPanel.add(boardPanel, BorderLayout.CENTER);
		
		frame.setContentPane(mainPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
		/*
		JPanel panel = new JPanel();
        //panel.setLayout(new GridLayout(10, 10));
        frame.add(panel);
        Board board = new Board();
        Tile[][] boardState = board.getBoardState();
       // int counter = 0;
        //System.out.println(board.getWidth());
        //System.out.println(board.getHeight());
        for (int i = 0; i < board.getWidth(); i ++) {
        	for (int j = 0; j < board.getHeight(); j++) {
        		Tile currTile = boardState[i][j];
        		//counter++;
        		//System.out.println(board.getNumMines(i, j));
        		//currTile.setNumMines(board.getNumMines(i, j));
        		currTile.addMouseListener(new MouseAdapter() { 
        			public void mouseClicked(MouseEvent e) {
        				currTile.clicked();
        				if (!currTile.hasMine() && currTile.getNumMines() == 0) {
        					
        				}
        			}
        		});
        			
        		if (boardState[i][j].hasMine()) {
        			currTile.placeMine();
        		}
        		panel.add(currTile);
        	}

        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);  
	}
	//recursive function to fill the surrounding empties
	
}
*/
/*
	private class Mouse extends MouseAdapter implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (tile1.hasMine) {
				//draw a red x over it if it has a mine
				clicked = true;
			}
		}
		repaint();

}
	*/