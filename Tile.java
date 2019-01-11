import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.*;

@SuppressWarnings("serial")
public class Tile {
	//private static final long serialVersionUID = 1L;
	private boolean hasMine = false;
	private boolean clicked = false;
	private int numMines;
	private int x;
	private int y;
	
	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, 100, 100);
		g.setColor(Color.BLACK);
		g.drawLine(x, y, x + 100, y);
		g.drawLine(x, y + 100, x + 100, y + 100);
		g.drawLine(x + 100, y + 100, x + 100, y);
		g.drawLine(x, y, x, y + 100);
		
		if (this.hasMine && clicked) {
			//draw a red x over it if it has a mine
			g.setColor(Color.RED);
			g.drawLine(x, y, x + 100, y + 100);
			g.drawLine(x, y + 100, x + 100, y);
		}
		
		else if (clicked && numMines == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(x, y, 100, 100);
		}
		else if (clicked) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString(String.valueOf(numMines), x + 40, y + 60);
		}
	}

	/* ORGINAL
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, 100, 100);
		g.setColor(Color.BLACK);
		g.drawLine(0, 0, 100, 0);
		g.drawLine(0, 100, 100, 100);
		g.drawLine(100, 100, 100, 0);
		g.drawLine(0, 0, 0, 100);
	*/
	
	/*

	public void actionPerformed(ActionEvent e) {
		this.setText(Integer.toString(numMines));
	}
	*/
		/* ORIGNAL
		if (this.hasMine && clicked) {
			//draw a red x over it if it has a mine
			g.setColor(Color.RED);
			g.drawLine(0, 0, 100, 100);
			g.drawLine(0, 100, 100, 0);
		}
		// 
		else if (clicked && numMines == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0 , 100, 100);
		}
		else if (clicked) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString(String.valueOf(numMines), 40, 60);
			
		}
		
	}
	*/
	
	public Dimension getPreferredSize() { 
		return new Dimension(100, 100);
	}
	
	public void placeMine() { 
		hasMine = true;
		//this.repaint();
	}
	
	public void clicked() {
		clicked = true;
		if (hasMine == true) {
			 
		}
		//this.repaint(); {
			
	}
	
	public boolean hasMine() {
		return hasMine;
	}
	
	public int getNumMines() {
		return numMines;
	}
	
	public void setNumMines(int mines) {
		numMines = mines;
		
	}
	
	public void setX (int value) {
		x = value;
		
	}
	public void setY (int value) {
		y = value;
		
	}
	public int getX () {
		return x;
	}
	public int getY () {
		return y;
	}
	
	public void noMine() {
		hasMine = false;
	}
	
	public boolean isClicked() {
		return clicked;
	}
	
	/*
	private class Mouse extends MouseAdapter implements MouseListener {
		
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (hasMine) {
					//draw a red x over it if it has a mine
					clicked = true;
				}
			}
			repaint();
	}
	*/
	
}
	