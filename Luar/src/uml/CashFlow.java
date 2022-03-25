package uml;

import java.sql.Date;

public class CashFlow {
	private String name;
	private Date date;
	private double amount;
	
	public CashFlow(String name, Date date, double amount) {
		this.name = name;
		this.date = date;
		this.amount = amount;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
