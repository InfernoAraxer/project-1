package org.atan;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.atan.views.LoginView;

public class GUI extends JFrame{

	public GUI() {
		super("UCVTS Course Management Software");
	}
	
	private void init() {
		JPanel views = new JPanel(new CardLayout());
		
		views.add(new LoginView(), "LOGIN_VIEW");
		
		this.add(views);
		this.setBounds(100, 100, 500, 500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI().init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
