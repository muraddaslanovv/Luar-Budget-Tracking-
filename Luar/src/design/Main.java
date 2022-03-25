package design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JTextField;

import uml.User;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

	private JFrame frame;
	JPanel applicationsPanel;
	public static JPanel LoginPanel;
	private JTextField userTxt;
	private JPasswordField passwordTxt;
	private InputOutput io = new InputOutput();
	private User chosenUser;

	public static JPanel getLoginPanel() {
		return LoginPanel;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		io.readUsers();
		io.readPositions();
		io.readRevenue();
		io.readExpenses();

		frame = new JFrame();
		frame.setBounds(50, 50, 889, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		LoginPanel = new JPanel();
		LoginPanel.setBackground(Color.LIGHT_GRAY);
		LoginPanel.setBounds(0, 0, 880, 489);
		frame.getContentPane().add(LoginPanel);
		LoginPanel.setLayout(null);

		JTextArea txtrUserLogin = new JTextArea();
		txtrUserLogin.setText("Welcome to Luar");
		txtrUserLogin.setOpaque(false);
		txtrUserLogin.setFont(new Font("MS Gothic", Font.BOLD, 50));
		txtrUserLogin.setFocusable(false);
		txtrUserLogin.setEditable(false);
		txtrUserLogin.setBounds(144, 99, 406, 54);
		LoginPanel.add(txtrUserLogin);

		JButton loginButton = new JButton("Login");

		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.setOpaque(false);
		loginButton.setFont(new Font("Yu Gothic", Font.BOLD, 30));
		loginButton.setFocusable(false);
		loginButton.setBounds(144, 372, 406, 54);
		LoginPanel.add(loginButton);

// Add a key listener(Enter)

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = userTxt.getText();
				String password = passwordTxt.getText();
				if (checkUser(username, password)) {
					LoginPanel.setVisible(false);
					resetLoginPanel();
					ApplicationsPanel applicationsPanel = new ApplicationsPanel(frame, chosenUser);

				} else {
					JOptionPane.showMessageDialog(getParent(), "Either password or username is wrong", "Login error",
							JOptionPane.ERROR_MESSAGE);
					userTxt.setBackground(Color.RED);
					passwordTxt.setBackground(Color.RED);
				}
			}

			private Component getParent() {
				// TODO Auto-generated method stub
				return null;
			}

		});

		JTextArea txtrUsername = new JTextArea();

		txtrUsername.setText("Username:");
		txtrUsername.setOpaque(false);
		txtrUsername.setFont(new Font("MS Gothic", Font.BOLD, 25));
		txtrUsername.setEditable(false);
		txtrUsername.setBounds(96, 202, 125, 30);
		LoginPanel.add(txtrUsername);

		userTxt = new JTextField();
		userTxt.setColumns(10);
		userTxt.setBounds(231, 202, 319, 30);
		LoginPanel.add(userTxt);
		userTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					passwordTxt.grabFocus();
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});

		JTextArea passWord = new JTextArea();
		passWord.setText("Password:");
		passWord.setOpaque(false);
		passWord.setFont(new Font("MS Gothic", Font.BOLD, 25));
		passWord.setEditable(false);
		passWord.setBounds(96, 285, 125, 30);
		LoginPanel.add(passWord);

		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(231, 291, 319, 29);
		LoginPanel.add(passwordTxt);
		passwordTxt.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}

			}
		});

		JButton btnRegister = new JButton("New User?");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setFocusable(false);
		btnRegister.setBounds(745, 11, 125, 42);
		LoginPanel.add(btnRegister);
		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == btnRegister) {
					LoginPanel.setVisible(false);
					resetLoginPanel();
					RegisterPanel registerPanel = new RegisterPanel(frame, io);
				}
			}
		});
	}

	public void resetLoginPanel() {
		passwordTxt.setText("");
		userTxt.setText("");
		userTxt.setBackground(Color.WHITE);
		passwordTxt.setBackground(Color.WHITE);
	}

	// this methods is needed for ....
	// returns true if there is such user, and false if not
	public boolean checkUser(String username, String password) {
		User u = null;
		for (int i = 0; i < io.getUsers().size(); i++) {
			u = io.getUsers().get(i);
			if (u.getUsername().equals(username)) {
				if (u.getPassword().equals(password)) {
					chosenUser = u;
					return true;
				}
			}
		}
		return false;
	}
}
