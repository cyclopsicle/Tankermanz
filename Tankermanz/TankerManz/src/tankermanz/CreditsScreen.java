package tankermanz;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreditsScreen extends JPanel implements MouseMotionListener, MouseListener {
	Font ARBERKLEY;
	int mouseX = 0; int mouseY = 0;
	int tankX1 = 200; int tankY1 = 400;
	int tankArmAngle1 = 330;
	int tankX2 = 700; int tankY2 = 400;
	int tankArmAngle2 = 190;
	final int tankHeight = 60;
	int tankAngle = 0;
	
	int creditLabelLength = 950;
	int creditLabelHeight = 100;
	int creditLabelX = 0;
	int creditLabelY = 200;

	int backLabelLength = 120;
	int backLabelHeight = 50;
	int backLabelX = 815;
	int backLabelY = 405;

	boolean inBackButton = false;
	final int TEXT_SIZE = 50;

	JLabel credits; 
	JLabel backButton;

	public CreditsScreen () {
		try {
			ARBERKLEY = Font.createFont(Font.TRUETYPE_FONT, new File ("ARBERKLEY.ttf"));
			GraphicsEnvironment ge = 
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(ARBERKLEY);
		} catch (FontFormatException e) {
			System.out.println("no font found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(null);
		MenuScreen.setTitle(); add(MenuScreen.gameTitleTanker); add(MenuScreen.gameTitleManz);

		setCreditLabels(); add(credits); 
		setBackButton(); add(backButton);


		setFocusable(true);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// Draw background
		g2.setPaint(new GradientPaint(0, 0, new Color (1, 1, 13), 0, Terrain.HEIGHT, new Color (4, 8, 55)));	
		g.fillRect(0, 0, Terrain.LENGTH, Terrain.HEIGHT);
		// Draw land
		g2.setPaint(new GradientPaint(0, MenuScreen.landDrawY, new Color (124, 203, 255), 0, Terrain.HEIGHT, new Color (0, 0, 75)));
		g2.fillRect(0, MenuScreen.landDrawY, Terrain.LENGTH, Terrain.HEIGHT - MenuScreen.landDrawY);

		// Draw a default tank
		DrawTank.colorGreen();
		DrawTank.drawDefaultTank(g2, 400, tankY1, tankHeight, tankAngle, tankArmAngle1);
		// Draw a green tank
		DrawTank.colorGreen();
		DrawTank.drawCircleTank(g, tankX1, tankY1, tankHeight, tankAngle, tankArmAngle1);

		// Draw red tank
		DrawTank.colorRed();
		DrawTank.drawMountainTank(g, tankX2, tankY2, tankHeight, tankAngle, tankArmAngle2);
		

	}

	public void setCreditLabels () {
		credits = new JLabel("Game Developed by: Andi Li & Max Gao");
		credits.setFont(new Font("AR BERKLEY", Font.BOLD, TEXT_SIZE));
		credits.setForeground(new Color (0, 105, 149));
		credits.setSize(creditLabelLength, creditLabelHeight);
		credits.setLocation(creditLabelX, creditLabelY);
	}

	public void setBackButton () {
		backButton = new JLabel("Back");
		backButton.setFont(new Font("AR BERKLEY", Font.BOLD, TEXT_SIZE));
		backButton.setForeground(new Color (2, 10, 33));
		backButton.setSize(backLabelLength, backLabelHeight);
		backButton.setLocation(backLabelX, backLabelY);
	}

	public void setColorBackButton () {
		if (inBackButton)
			backButton.setForeground(new Color (191, 208, 255));
		else
			backButton.setForeground(new Color (2, 10, 33));
	}
	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		if (inBackButton)
			Screen.changeScreen(Screen.MENU_SCREEN);

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX(); int mouseY = e.getY();
		setMouseXY(mouseX, mouseY);
		setInBackButton(mouseX, mouseY);
		setColorBackButton();
	}

	// Getters
	public boolean getInBackButton () { return inBackButton; }

	// Setters
	public void setMouseXY (int mouseX, int mouseY) { this.mouseX = mouseX; this.mouseY = mouseY; }
	public void setInBackButton (int mouseX, int mouseY) {
		if (mouseX > backLabelX && mouseX < backLabelX + backLabelLength) {
			if (mouseY > backLabelY && mouseY < backLabelY + backLabelHeight)
				inBackButton = true;
			else 
				inBackButton = false;
		}
		else 
			inBackButton = false;
	}
}

