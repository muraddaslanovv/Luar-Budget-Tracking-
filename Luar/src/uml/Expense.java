package uml;

import java.sql.Date;

public class Expense extends CashFlow {
	// 1=active, 0=paid
	private boolean status;

	public Expense(String name, Date date, double amount, boolean status) {
		super(name, date, amount);
		this.status = status;

	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void changeStatus() {
		this.status = (!status);
		//change status in expenses file too
	}

}
