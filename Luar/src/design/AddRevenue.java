package design;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uml.Expense;
import uml.Revenue;
import uml.User;
import javax.swing.JLabel;

public class AddRevenue extends JPanel {

	private JFrame frame;
	private User user;

	public AddRevenue(JFrame frame, User user) {
		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.user = user;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);
		luar1();
	}

	private void luar1() {
		JTextArea txtrEmployeeName = new JTextArea();
		txtrEmployeeName.setText("Name");
		txtrEmployeeName.setOpaque(false);
		txtrEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrEmployeeName.setBounds(147, 188, 82, 23);
		add(txtrEmployeeName);

		JTextArea txtrAmount = new JTextArea();
		txtrAmount.setText("Amount");
		txtrAmount.setOpaque(false);
		txtrAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrAmount.setBounds(147, 235, 74, 25);
		add(txtrAmount);

		JTextArea txtrDeadline = new JTextArea();
		txtrDeadline.setText("Date");
		txtrDeadline.setOpaque(false);
		txtrDeadline.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrDeadline.setBounds(147, 282, 82, 23);
		add(txtrDeadline);

		JTextField nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(245, 191, 150, 20);
		add(nameField);

		JTextField amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(245, 240, 150, 20);
		add(amountField);

		JTextField dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(245, 287, 150, 20);
		add(dateField);

		JTextArea txtrAddExpenses = new JTextArea();
		txtrAddExpenses.setText("Add Revenue");
		txtrAddExpenses.setOpaque(false);
		txtrAddExpenses.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtrAddExpenses.setEditable(false);
		txtrAddExpenses.setBounds(355, 36, 189, 38);
		add(txtrAddExpenses);

		JButton addBtn = new JButton("Add data");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setFocusable(false);
		addBtn.setBounds(641, 213, 169, 67);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addBtn) {
					try {
						String d =dateField.getText();
						BufferedWriter bw = new BufferedWriter(new FileWriter("src\\revenue.txt", true));
						if (nameField.getText().equals("") || nameField.getText().equals(" ")
								|| amountField.getText().equals("") || amountField.getText().equals(" ")
								|| dateField.getText().equals("") || dateField.getText().equals(" ")) {
							JOptionPane.showMessageDialog(getParent(), "Boxes can't be left empty", "Revenue",
									JOptionPane.ERROR_MESSAGE);
						} else if(!(d.length()==10 && d.charAt(4)=='-' && d.charAt(7)=='-') || hasLetters(d)){
							JOptionPane.showMessageDialog(getParent(), "Date is in incorrect format.", "Expenses",
									JOptionPane.ERROR_MESSAGE);
						}	else {
							String toWrite = "\n" + user.getId() + "," + nameField.getText() + "," + dateField.getText()
									+ "," + amountField.getText();
							bw.write(toWrite);
							bw.newLine();
							bw.close();
							user.addRevenue(new Revenue(nameField.getText(), Date.valueOf(dateField.getText()),
									Double.parseDouble(amountField.getText())));
							JOptionPane.showMessageDialog(getParent(), "Revenue added", "Revenue",
									JOptionPane.INFORMATION_MESSAGE);
							nameField.setText("");
							amountField.setText("");
							dateField.setText("");
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		add(addBtn);

		JButton allRev = new JButton("Check out the all revenue");
		allRev.setFont(new Font("Tahoma", Font.PLAIN, 20));
		allRev.setBounds(306, 396, 296, 67);
		add(allRev);
		setVisible(true);
		allRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == allRev) {
					setVisible(false);
					RevenuePanel revPanel = new RevenuePanel(frame, user);

				}
			}
		});

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.setBounds(767, 38, 89, 23);
		btnGoBack.setFocusable(false);
		add(btnGoBack);
		
		JLabel yearArea = new JLabel("Format is YYYY-MM-DD");
		yearArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yearArea.setBounds(245, 320, 150, 23);
		add(yearArea);
		btnGoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGoBack) {
					setVisible(false);
					ApplicationsPanel applicationsPanel = new ApplicationsPanel(frame, user);
				}
			}
		});
	}
	public boolean hasLetters(String d) {
		for(int i =0;i<d.length();i++) {
			if(i==4 || i==7) continue;
			char c = d.charAt(i);
			if(!(c>='0' && c<='9')) return true;
		}
		return false;
	}

}
