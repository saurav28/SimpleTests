package org.saurav.simpletests.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HelloWorldSwing {
	
	
	public static void main (String[] args) {
		
		
		
	
		
		JFrame  frame  = new JFrame("Swing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 frame.getContentPane().setLayout(new FlowLayout());
		
		JLabel usernameLabel = new JLabel("Username");
		frame.getContentPane().add(usernameLabel);
		
		JTextField userText = new JTextField(10);
		frame.getContentPane().add(userText);
		
		
		JLabel passwordLabel = new JLabel("password");
		frame.getContentPane().add(passwordLabel);
		
		JTextField passwordText = new JTextField(10);
		frame.getContentPane().add(passwordText);
		
		JButton button = new JButton("Submit");
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userName = userText.getText();
				
				String password = passwordText.getText();
				
				System.out.println("User name" + userName);
				
				System.out.println("User name" + password);
				
				//HttpClientTester httpClientTester = new HttpClientTester();
				//httpClientTester.executeHttp(Constants.URL, userName, password);
			}
		});
		
		frame.pack();
		frame.setVisible(true);
		
		
	}

}
