package design;

import java.awt.Color;

import java.awt.Event;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import uml.User;

public class RegisterPanel extends JPanel {
	public JFrame frame;
	public JTextField userNamet;
	JPanel LoginPanel;
	public JPasswordField passwordField;
	public JPasswordField confirmPassword;
	public String characters = "&*._=>,+-%'";
	private JTextField lastname;
	private JTextField firstname;
	private InputOutput io = new InputOutput();
	private JComboBox comboBox;

	public RegisterPanel(JFrame frame, InputOutput io) {
		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.io = io;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
		buildRegisterPage();

	}

	public boolean containsChar(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (characters.contains("" + s.charAt(i)))
				return true;
		}
		return false;
	}

	public void buildRegisterPage() {

		JTextArea txtrBecomeAPart = new JTextArea();
		txtrBecomeAPart.setText("Become a part of Luar");
		txtrBecomeAPart.setOpaque(false);
		txtrBecomeAPart.setFont(new Font("MS Gothic", Font.BOLD, 50));
		txtrBecomeAPart.setFocusable(false);
		txtrBecomeAPart.setEditable(false);
		txtrBecomeAPart.setBounds(117, 25, 566, 54);
		add(txtrBecomeAPart);

		passwordField = new JPasswordField();
		passwordField.setBounds(293, 306, 319, 29);

		add(passwordField);

		userNamet = new JTextField();
		userNamet.setColumns(10);
		userNamet.setBounds(293, 193, 319, 30);
		String userNameString = userNamet.getText();
		add(userNamet);

		JTextArea txtrFirstName = new JTextArea();
		txtrFirstName.setText("First name:");
		txtrFirstName.setOpaque(false);
		txtrFirstName.setFont(new Font("MS Gothic", Font.BOLD, 25));
		txtrFirstName.setEditable(false);
		txtrFirstName.setBounds(131, 110, 152, 30);
		add(txtrFirstName);

		JTextArea txtrLastName = new JTextArea();
		txtrLastName.setText("Last name:");
		txtrLastName.setOpaque(false);
		txtrLastName.setFont(new Font("MS Gothic", Font.BOLD, 25));
		txtrLastName.setEditable(false);
		txtrLastName.setBounds(145, 151, 138, 30);
		add(txtrLastName);

		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(293, 151, 319, 30);
		add(lastname);

		firstname = new JTextField();
		firstname.setColumns(10);
		firstname.setBounds(293, 115, 319, 30);
		add(firstname);

		JButton btnRegister = new JButton("Register");
		btnRegister.setVerticalAlignment(SwingConstants.TOP);
		btnRegister.setOpaque(false);
		btnRegister.setFont(new Font("Yu Gothic", Font.BOLD, 30));
		btnRegister.setFocusable(false);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setBounds(204, 421, 408, 54);
		add(btnRegister);
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader("src\\positions.txt"));
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ArrayList<String> strings = new ArrayList<String>();

		String line = null;
		try {
			while ((line = input.readLine()) != null) {
				strings.add(line);
			}
			input.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String[] lineArray = strings.toArray(new String[] {});

		comboBox = new JComboBox(lineArray);

		// assign strings from position file
		// comboBox.setModel(
		// new DefaultComboBoxModel(new String[] { "Cashier", "CEO", "Sales Manager",
		// "Store Manager" }));
		comboBox.setBounds(293, 242, 319, 37);
		add(comboBox);

		JTextArea txtrPosition = new JTextArea();
		txtrPosition.setText("Position:");
		txtrPosition.setOpaque(false);
		txtrPosition.setFont(new Font("MS Gothic", Font.BOLD, 25));
		txtrPosition.setEditable(false);
		txtrPosition.setBounds(158, 249, 125, 30);
		add(txtrPosition);
		setVisible(true);

		btnRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkAndRegister();
			}

		});

		JTextArea txtrUsername = new JTextArea();
		txtrUsername.setText("Username:");
		txtrUsername.setOpaque(false);
		txtrUsername.setFont(new Font("MS Gothic", Font.BOLD, 25));
		txtrUsername.setEditable(false);
		txtrUsername.setBounds(158, 188, 125, 30);
		add(txtrUsername);

		JTextArea passWord = new JTextArea();
		passWord.setText("Password:");
		passWord.setOpaque(false);
		passWord.setFont(new Font("MS Gothic", Font.BOLD, 25));
		passWord.setEditable(false);
		passWord.setBounds(158, 300, 125, 30);
		add(passWord);

		JTextArea txtrConfirmPassword = new JTextArea();
		txtrConfirmPassword.setText("Confirm Password:");
		txtrConfirmPassword.setOpaque(false);
		txtrConfirmPassword.setFont(new Font("MS Gothic", Font.BOLD, 25));
		txtrConfirmPassword.setEditable(false);
		txtrConfirmPassword.setBounds(50, 366, 244, 30);
		add(txtrConfirmPassword);

		JButton btnGoBack = new JButton("Already have account?");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGoBack.setBounds(693, 38, 186, 41);
		btnGoBack.setFocusable(false);
		add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGoBack) {
					setVisible(false);
					Main.getLoginPanel().setVisible(true);
				}
			}
		});
		confirmPassword = new JPasswordField();
		confirmPassword.setBounds(293, 367, 319, 29);
		add(confirmPassword);

	}

//This method checks the validity of the inputted data based on the requirements set, and only in the case where entered data satisfies the requirements users are registered to the system
	public void checkAndRegister() {
		String confirmPasswordString = confirmPassword.getText();
		String passwordString = passwordField.getText();
		String nameString = firstname.getText();
		String lastString = lastname.getText();
		String userNameString = userNamet.getText();
		String positions = comboBox.getSelectedItem().toString();

		lastname.setBackground(Color.WHITE);
		firstname.setBackground(Color.WHITE);
		passwordField.setBackground(Color.WHITE);
		confirmPassword.setBackground(Color.WHITE);
		userNamet.setBackground(Color.WHITE);

		// are textfields empty?
		boolean userEmpty = userNameString.isEmpty();
		boolean passEmpty = passwordString.isEmpty();
		boolean nameEmpty = nameString.isEmpty();
		boolean lastEmpty = lastString.isEmpty();
		boolean confEmpty = confirmPasswordString.equals("");

		// password's length should be between 8 and 16 characters
		boolean passLength = passwordString.length() >= 8 && passwordString.length() <= 16;

		// Is confirm password equal to the password ?
		boolean confEquals = confirmPasswordString.equals(passwordString);

		// first and lastname should only contain letters
		boolean firstLetter = nameString.matches("^[a-zA-Z]*$");
		boolean lastLetter = lastString.matches("^[a-zA-Z]*$");

		// checking if the fields are empty and making the system react to it
		if (userEmpty || passEmpty || confEmpty || nameEmpty || lastEmpty) {
			JOptionPane.showMessageDialog(getParent(), "You have to fill all blanks", "Error message",
					JOptionPane.ERROR_MESSAGE);
			if (userEmpty)
				userNamet.setBackground(Color.RED);
			if (passEmpty)
				passwordField.setBackground(Color.RED);
			if (confEmpty)
				confirmPassword.setBackground(Color.RED);
			if (nameEmpty)
				firstname.setBackground(Color.RED);
			if (lastEmpty)
				lastname.setBackground(Color.RED);
		}
		// checking if the username contains any irrelevant characters
		else if (containsChar(userNameString)) {
			userNamet.setBackground(Color.RED);
			JOptionPane.showMessageDialog(getParent(), "Irrelevant characters", "Username", JOptionPane.ERROR_MESSAGE);
		}
		// check if the username is not taken
		else if (io.getUsers().contains(userNameString)) {
			userNamet.setBackground(Color.RED);
			JOptionPane.showMessageDialog(getParent(), "Username exists", "Username", JOptionPane.ERROR_MESSAGE);
		}
		// check if password's length is between the accepted range
		else if (!passLength) {
			passwordField.setBackground(Color.RED);
			JOptionPane.showMessageDialog(getParent(), "Password should be between 8 to 16 characters", "Password",
					JOptionPane.ERROR_MESSAGE);
		} // checking if the confirm password matches the password
		else if (!confEquals) {
			JOptionPane.showMessageDialog(getParent(), "Confirm Password doesn't match the Password",
					"Confirm Password", JOptionPane.ERROR_MESSAGE);
			confirmPassword.setBackground(Color.RED);
		}
		// checking if first or last names contains any non-letter characters
		else if (!firstLetter || !lastLetter) {
			if (!firstLetter) {
				firstname.setBackground(Color.RED);
				JOptionPane.showMessageDialog(getParent(), "First name must only contain letters", "Name",
						JOptionPane.ERROR_MESSAGE);
			} else if (!lastLetter) {
				lastname.setBackground(Color.RED);
				JOptionPane.showMessageDialog(getParent(), "Last name must only contain letters", "Name",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			// if the code reached this line, this means that there is no error
			JOptionPane.showMessageDialog(getParent(), "Welcome to Luar!", "Succesfull registration",
					JOptionPane.INFORMATION_MESSAGE);
			setVisible(false);

			Main.getLoginPanel().setVisible(true);
			try {
				// writing the data inputted by the user on the fields
				BufferedWriter bw = new BufferedWriter(new FileWriter("src\\users.txt", true));
				int id = io.getUsers().size() + 1;
				String toWrite = "" + id + "," + userNamet.getText() + "," + passwordField.getText() + ","
						+ firstname.getText() + "," + lastname.getText() + "," + 0.0 + "," + positions;
				bw.write(toWrite);
				bw.newLine();
				bw.close();
				User user = new User(id, userNamet.getText(), passwordField.getText(), firstname.getText(),
						lastname.getText(), 0.0, positions);
				System.out.println(io.getUsers().size());
				io.getUsers().add(user);

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}