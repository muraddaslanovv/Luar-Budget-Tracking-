package design;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import uml.User;

public class ApplicationsPanel extends JPanel {
	public JFrame frame;
	private JPanel expensesPanel;
	private JTextField txtWelcome;
	private JTextField txtUsersFirstlastName;
	private User user;
	private JButton addExp;
	private JButton addRev;

	public ApplicationsPanel(JFrame frame, User user) {

		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.user = user;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
		luar();
	}

	public void luar() {
		JButton expensesButton = new JButton("Expenses");
		expensesButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		expensesButton.setBounds(50, 307, 156, 52);
		expensesButton.setFocusable(false);
		add(expensesButton);
		expensesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == expensesButton) {
					setVisible(false);
					ExpensesPanel expensesPanel = new ExpensesPanel(frame, user);
				}
			}

		});

		JButton revenueButton = new JButton("Revenue");
		revenueButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		revenueButton.setBounds(700, 307, 156, 52);
		revenueButton.setFocusable(false);
		add(revenueButton);
		revenueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == revenueButton) {
					setVisible(false);
					RevenuePanel revenuePanel = new RevenuePanel(frame, user);
				}
			}

		});

		JTextArea txtrApplications = new JTextArea();
		txtrApplications.setEditable(false);
		txtrApplications.setFont(new Font("Monospaced", Font.PLAIN, 32));
		txtrApplications.setText("Applications");
		txtrApplications.setOpaque(false);
		txtrApplications.setBounds(319, 92, 231, 52);
		add(txtrApplications);

		JTextArea txtrEnterAndUpdate = new JTextArea();
		txtrEnterAndUpdate.setEditable(false);
		txtrEnterAndUpdate.setForeground(Color.WHITE);
		txtrEnterAndUpdate.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtrEnterAndUpdate.setText("The finance is yours");
		txtrEnterAndUpdate.setOpaque(false);
		txtrEnterAndUpdate.setBounds(339, 143, 205, 22);
		add(txtrEnterAndUpdate);
		setVisible(true);

		JButton btnGoBack = new JButton("Sign out");
		btnGoBack.setBounds(767, 38, 89, 23);
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

		txtWelcome = new JTextField();
		txtWelcome.setEditable(false);
		txtWelcome.setBackground(Color.WHITE);
		txtWelcome.setText("Welcome,");
		txtWelcome.setBounds(10, 11, 62, 20);
		txtWelcome.setForeground(null);
		txtWelcome.setFocusable(false);
		add(txtWelcome);
		txtWelcome.setColumns(10);

//Showing a welcome message on the left corner of the panel, the message varies based on the user logged in
		txtUsersFirstlastName = new JTextField();
		txtUsersFirstlastName.setEditable(false);
		txtUsersFirstlastName.setBackground(Color.WHITE);
		String name = user.getName();
		String lastName = user.getLastname();
		txtUsersFirstlastName.setText(name + " " + lastName);
		// after coming back to this panel again, the name and lastname doesn't show up
		txtUsersFirstlastName.setBounds(10, 39, 137, 20);
		add(txtUsersFirstlastName);
		txtUsersFirstlastName.setColumns(10);

		addExp = new JButton("Add Expense");
		addExp.setFocusable(false);
		addExp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddExpense addPanel = new AddExpense(frame, user);
			}
		});
		addExp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addExp.setBounds(65, 403, 115, 40);

		add(addExp);

		addRev = new JButton("Add Revenue");

		addRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addRev) {
					setVisible(false);
					new AddRevenue(frame, user);
				}
			}
		});
		addRev.setFont(new Font("Tahoma", Font.PLAIN, 13));
		addRev.setBounds(722, 403, 115, 40);
		addRev.setFocusable(false);
		add(addRev);

		if (user.getPosition().equals("Cashier")) {
			expensesButton.setVisible(false);
			addExp.setVisible(false);
			addRev.setBounds(360, 400, 115, 40);
			revenueButton.setBounds(360, 307, 156, 52);
		}

	}
}
