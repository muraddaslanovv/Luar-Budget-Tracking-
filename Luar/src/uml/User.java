package uml;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String lastname;
	private double balance;
	private String position;
	private ArrayList<Expense> expenses;
	private ArrayList<Revenue> revenue;

//The constructor calls all the variables here
	public User(int id, String username, String password, String name, String lastname, double balance,
			String position) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.balance = balance;
		this.position = position;
		expenses = new ArrayList<Expense>();
		revenue = new ArrayList<Revenue>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void addExpenses(Expense ex) {
		expenses.add(ex);
	}

	public void setExpenses(ArrayList<Expense> expenses) {
		this.expenses = expenses;
	}

	public ArrayList<Expense> getExpenses() {
		return expenses;
	}

	public void addRevenue(Revenue rev) {
		revenue.add(rev);
	}

	public void setRevenue(ArrayList<Revenue> revenue) {
		this.revenue = revenue;
	}

	public ArrayList<Revenue> getRevenue() {
		return revenue;
	}
}
