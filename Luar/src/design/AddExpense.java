package design;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uml.CashFlow;
import uml.Expense;
import uml.User;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AddExpense extends JPanel {

	private JFrame frame;
	private User user;
	private JTextField nameField;
	private JTextField amountField;
	private JTextField dateField;
	private InputOutput io = new InputOutput();
	private Integer ex;

	/**
	 * Launch the application.
	 */
	public AddExpense(JFrame frame, User user) {
		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.user = user;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);
		luar1();
	}

	public void luar1() {

		JTextArea txtrEmployeeName = new JTextArea();
		txtrEmployeeName.setEditable(false);
		txtrEmployeeName.setText("Name");
		txtrEmployeeName.setOpaque(false);
		txtrEmployeeName.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrEmployeeName.setBounds(147, 188, 82, 23);
		add(txtrEmployeeName);

		JTextArea txtrAmount = new JTextArea();
		txtrAmount.setEditable(false);
		txtrAmount.setText("Amount");
		txtrAmount.setOpaque(false);
		txtrAmount.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrAmount.setBounds(147, 235, 74, 25);
		add(txtrAmount);

		JTextArea txtrDeadline = new JTextArea();
		txtrDeadline.setToolTipText("");
		txtrDeadline.setEditable(false);
		txtrDeadline.setText("Deadline");
		txtrDeadline.setOpaque(false);
		txtrDeadline.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrDeadline.setBounds(147, 282, 82, 23);
		add(txtrDeadline);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(245, 191, 150, 20);
		add(nameField);

		amountField = new JTextField();
		amountField.setColumns(10);
		amountField.setBounds(245, 240, 150, 20);
		add(amountField);

		dateField = new JTextField();
		dateField.setToolTipText("Format is YYYY-MM-DD");
		dateField.setColumns(10);
		dateField.setBounds(245, 287, 150, 20);
		add(dateField);

		JTextArea txtrAddExpenses = new JTextArea();
		txtrAddExpenses.setText("Add Expenses");
		txtrAddExpenses.setOpaque(false);
		txtrAddExpenses.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtrAddExpenses.setEditable(false);
		txtrAddExpenses.setBounds(355, 36, 189, 38);
		add(txtrAddExpenses);

		ex = user.getExpenses().size() + 1;
//		boolean status=

		JButton addBtn = new JButton("Add data");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d =dateField.getText();
				if (e.getSource() == addBtn) {
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter("src\\expenses.txt", true));
						if (nameField.getText().equals("") || nameField.getText().equals(" ")
								|| amountField.getText().equals("") || amountField.getText().equals(" ")
								|| dateField.getText().equals("") || dateField.getText().equals(" ")) {
							JOptionPane.showMessageDialog(getParent(), "Boxes can't be left empty", "Expenses",
									JOptionPane.ERROR_MESSAGE);
						} else if(!(d.length()==10 && d.charAt(4)=='-' && d.charAt(7)=='-') || hasLetters(d)){
							JOptionPane.showMessageDialog(getParent(), "Date is in incorrect format.", "Expenses",
									JOptionPane.ERROR_MESSAGE);
						}	else {
						
							String toWrite = "\n" + user.getId() + "," + nameField.getText() + "," + dateField.getText()
									+ "," + amountField.getText() + ",true";
							bw.write(toWrite);
							bw.newLine();
							bw.close();
							user.addExpenses(new Expense(nameField.getText(), Date.valueOf(dateField.getText()),
									Double.parseDouble(amountField.getText()), true));
							JOptionPane.showMessageDialog(getParent(), "Expense added", "Expenses",
									JOptionPane.INFORMATION_MESSAGE);
							nameField.setText("");
							amountField.setText("");
							dateField.setText("");
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		addBtn.setFocusable(false);
		addBtn.setBounds(641, 213, 169, 67);
		add(addBtn);

		JButton allExp = new JButton("Check out the all expenses");
		allExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == allExp) {
					setVisible(false);
					ExpensesPanel expensesPanel = new ExpensesPanel(frame, user);

				}
			}
		});
		allExp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		allExp.setBounds(306, 396, 296, 67);
		add(allExp);
		setVisible(true);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.setBounds(767, 38, 89, 23);
		btnGoBack.setFocusable(false);
		add(btnGoBack);

		JLabel yearArea = new JLabel("Format is YYYY-MM-DD");
		yearArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		yearArea.setBounds(245, 318, 150, 23);
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
