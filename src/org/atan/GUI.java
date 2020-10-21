package org.atan;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.atan.model.ClassList;
import org.atan.model.ClassPanels;
import org.atan.views.ClassView;
import org.atan.views.LoginView;
import org.atan.users.*;

/*
 * 		JScrollPane scrollPane = new JScrollPane(views);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 50, 500);
 */
public class GUI extends JFrame{

	public static ArrayList<Classes> classes;
	
	@SuppressWarnings("static-access")
	public GUI() {
		super("UCVTS Course Management Software");
		
		classes = new ArrayList<Classes>();
		
		classes.add(new Classes("Advanced Software Development"));
		classes.add(new Classes("AP Computer Science"));
		classes.add(new Classes("AP Microeconomics"));
		
		System.out.print(classes.get(1).getClassID());
		
	}
	
	private void init() {
		JPanel views = new JPanel(new CardLayout());
		
		views.add(new LoginView(), "CLASS_VIEW");
		
		
		this.add(views);
		this.setBounds(100, 100, 500, 1000);
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
