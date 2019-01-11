import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;

public class TimeScore {
	private long startTime = 0;
	private long stopTime = 0;
	private int timeScore;
	private boolean isRunning = false;
	
	
	
	public void start() {
		this.startTime = System.currentTimeMillis();
		this.isRunning = true;
	}
	
	public void stop() {
		this.stopTime = System.currentTimeMillis();
		this.isRunning = false; 
	}
	
	public int getElapsedSeconds () {
		if (this.isRunning) {
			timeScore = (int) (System.currentTimeMillis() - this.startTime) / 1000;
		}
		else {
			timeScore = (int) (this.stopTime - this.startTime) / 1000;
		}
		return timeScore;
		
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(390, 1060, 220, 50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString("Time : " + String.valueOf(this.getElapsedSeconds()), 400, 1100);
		//g.drawRect(350, 1140, 150, 50);
		//g.drawRect(420, 1140, 150, 50);
		g.drawRect(400, 1130, 160, 50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 34));
		g.drawString("Start Game", 404, 1165);
		
	}
	
		
}
