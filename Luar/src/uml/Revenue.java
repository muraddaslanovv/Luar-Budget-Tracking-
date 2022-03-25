package uml;

import java.sql.Date;

public class Revenue extends CashFlow {
	public Revenue(String name, Date date, double amount) {
		super(name, date, amount);
	}
	
	public String printRevenue() {
		return this.getName()+" "+this.getAmount()+" AZN "+this.getDate();
	}
}
