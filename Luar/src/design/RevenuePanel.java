package design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import uml.Expense;
import uml.Revenue;
import uml.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RevenuePanel extends JPanel {
	public JFrame frame;
	private User user;
	private JTextField searchField;
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtTotal;

	public RevenuePanel(JFrame frame, User user) {
		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.user = user;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
		luar1();
	}

	// this method takes the model of the table from revenue file
	public void addRows(ArrayList<Revenue> arr) {
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Deadline", "Amount" });
		table.setModel(model);
		for (int i = 0; i < arr.size(); i++) {
			Revenue rev = arr.get(i);
			model.addRow(new Object[] { rev.getName(), rev.getDate(), rev.getAmount() });
		}
		recalculateTotal(arr);
	}

	// this method calculates the total amount of items listed on the table

	public void recalculateTotal(ArrayList<Revenue> arr) {
		double total = 0.0;
		for (int i = 0; i < arr.size(); i++) {
			total = total + arr.get(i).getAmount();
		}
		txtTotal.setText("Total: " + total);
	}

	public void luar1() {

		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.user = user;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 123, 869, 388);
		add(scrollPane);
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setDefaultEditor(Object.class, null);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Date", "Amount" });
		table.setModel(model);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setText("Total: ");
		txtTotal.setBounds(745, 73, 121, 39);
		add(txtTotal);
		txtTotal.setColumns(10);

		addRows(user.getRevenue());

		JButton addBtn = new JButton("Add data");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addBtn.setFocusable(false);
		addBtn.setBounds(745, 16, 134, 35);
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddRevenue addRev = new AddRevenue(frame, user);
			}
		});
		add(addBtn);

		JTextArea txtrRevenue = new JTextArea();
		txtrRevenue.setText("Revenue");
		txtrRevenue.setOpaque(false);
		txtrRevenue.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtrRevenue.setEditable(false);
		txtrRevenue.setBounds(402, 11, 121, 38);
		add(txtrRevenue);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.setBounds(10, 22, 89, 23);
		btnGoBack.setFocusable(false);
		add(btnGoBack);
		btnGoBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnGoBack) {
					setVisible(false);
					ApplicationsPanel applicationsPanel = new ApplicationsPanel(frame, user);
				}
			}
		});

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == resetButton) {
					
					//when the reset button is clicked table restores itself back and the searchfield goes blank
					searchField.setText("");
					addRows(user.getRevenue());
				}
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetButton.setBounds(244, 84, 96, 28);
		resetButton.setFocusable(false);
		add(resetButton);

		JTextArea txtrSearch = new JTextArea();
		txtrSearch.setText("Search");
		txtrSearch.setOpaque(false);
		txtrSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrSearch.setBounds(10, 85, 64, 23);
		add(txtrSearch);

		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(84, 86, 150, 20);
		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			//this method will be responsible to search up the entered item in the table
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') || (e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z')
						|| e.getKeyChar() == ' ') {
					String s = searchField.getText();
					ArrayList<Revenue> resultsOfSearch = new ArrayList<Revenue>();
					for (int i = 0; i < user.getRevenue().size(); i++) {
						if ((user.getRevenue().get(i).getName()).contains(s)) {
							resultsOfSearch.add(user.getRevenue().get(i));
						}
					}
					addRows(resultsOfSearch);
				}
			}
		});
		searchField.setColumns(10);
		searchField.setBounds(84, 86, 150, 20);
		add(searchField);
		add(searchField);

		JButton btnUpcoming = new JButton("Show upcoming Revenues");
		btnUpcoming.addActionListener(new ActionListener() {
			//here with the help of this method when the btnUpcoming is clicked table will only display the items that their dates haven't expired
			public void actionPerformed(ActionEvent e) {
				ArrayList<Revenue> resultsOfSearch = new ArrayList<Revenue>();
				for (int i = 0; i < user.getRevenue().size(); i++) {
					if (Calendar.getInstance().getTime().before((user.getRevenue().get(i).getDate()))) {
						resultsOfSearch.add(user.getRevenue().get(i));
					}
				}
				addRows(resultsOfSearch);
			}
		});
		btnUpcoming.setBounds(350, 89, 187, 23);
		add(btnUpcoming);
	}

}
