package design;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import uml.Expense;
import uml.User;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

public class ExpensesPanel extends JPanel {
	public JFrame frame;
	private JTable table_1;
	private User user;
	private JTextField searchField;
	private DefaultTableModel model;
	private JTextField txtTotal;

	public ExpensesPanel(JFrame frame, User user) {

		setBounds(0, 0, 889, 522);
		setLayout(null);
		this.frame = frame;
		this.user = user;
		frame.getContentPane().add(this);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
		luar1();
	}

//this method takes the model of the table from expenses file
	public void addRows(ArrayList<Expense> arr) {
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Deadline", "Amount", "Status" });
		table_1.setModel(model);
		for (int i = 0; i < arr.size(); i++) {
			Expense ex = arr.get(i);
			model.addRow(
					new Object[] { ex.getName(), ex.getDate(), ex.getAmount(), ex.getStatus() ? "Active" : "Paid" });
		}
		recalculateTotal(arr);
	}

//this method calculates the total amount of items listed on the table
	public void recalculateTotal(ArrayList<Expense> arr) {
		double total = 0.0;
		for (int i = 0; i < arr.size(); i++) {

			total = total + arr.get(i).getAmount();
		}
		txtTotal.setText("Total: " + total);
	}

	public void luar1() {
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(10, 123, 869, 388);
		add(scrollPane);
		table_1 = new JTable();
		table_1.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_1);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_1.setDefaultEditor(Object.class, null);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Name", "Deadline", "Amount", "Status" });
		table_1.setModel(model);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setText("Total: ");
		txtTotal.setBounds(745, 73, 121, 39);
		add(txtTotal);
		txtTotal.setColumns(10);

		addRows(user.getExpenses());

		table_1.addMouseListener(new MouseAdapter() {
			@Override
			//changing the status of an expense
			public void mouseClicked(MouseEvent e) {
				//make sure user clicked twice
				if (e.getClickCount() == 2) {
					//ask if user really wants to change the status to opposite one
					int result = JOptionPane.showOptionDialog(getParent(),
							"Do you want to change status of this expense", "Change status",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
							new String[] { "YES", "NO", "Cancel" }, "Cancel");
					//result value keeps the selection of user -> 0 means user clicked YES
					if (result == 0) { // YES
						int row = table_1.getSelectedRow();
						//find the row in which expense is selected
						Expense ex = user.getExpenses().get(row);
						ex.changeStatus();
						table_1.getModel().setValueAt(ex.getStatus() ? "Active" : "Paid", row, 3);
					}
					//no need to handle if user click NO or CANCEL because the same panel is shown
				}
			}
		});

		JButton addBtn = new JButton("Add data");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == addBtn) {
					setVisible(false);
					AddExpense expensesPanel = new AddExpense(frame, user);
				}
			}
		});
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addBtn.setBounds(745, 11, 121, 35);
		addBtn.setFocusable(false);
		add(addBtn);

		JTextArea txtrExpenses = new JTextArea();
		txtrExpenses.setText("Expenses");
		txtrExpenses.setOpaque(false);
		txtrExpenses.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtrExpenses.setEditable(false);
		txtrExpenses.setBounds(402, 25, 121, 38);
		add(txtrExpenses);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGoBack.setBounds(10, 36, 89, 23);
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
					searchField.setText("");
					addRows(user.getExpenses());
				}
			}
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resetButton.setFocusable(false);
		resetButton.setBounds(244, 84, 96, 28);
		add(resetButton);

		JTextArea txtrSearch = new JTextArea();
		txtrSearch.setText("Search");
		txtrSearch.setOpaque(false);
		txtrSearch.setFont(new Font("Tahoma", Font.BOLD, 17));
		txtrSearch.setBounds(10, 85, 64, 23);
		add(txtrSearch);

		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') || (e.getKeyChar() >= 'A' && e.getKeyChar() <= 'Z')
						|| e.getKeyChar() == ' ') {
					String s = searchField.getText();
					ArrayList<Expense> resultsOfSearch = new ArrayList<Expense>();
					for (int i = 0; i < user.getExpenses().size(); i++) {
						if ((user.getExpenses().get(i).getName()).contains(s)) {
							resultsOfSearch.add(user.getExpenses().get(i));
						}
					}
					addRows(resultsOfSearch);
				}
			}
		});
		searchField.setColumns(10);
		searchField.setBounds(84, 86, 150, 20);
		add(searchField);

		JButton btnUpcoming = new JButton("Show upcoming expenses");
		btnUpcoming.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Expense> resultsOfSearch = new ArrayList<Expense>();
				for (int i = 0; i < user.getExpenses().size(); i++) {
					if (Calendar.getInstance().getTime().before((user.getExpenses().get(i).getDate()))) {
						resultsOfSearch.add(user.getExpenses().get(i));
					}
				}
				addRows(resultsOfSearch);
			}
		});
		btnUpcoming.setBounds(350, 89, 187, 23);
		add(btnUpcoming);

	}
}
