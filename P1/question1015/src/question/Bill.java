
package question;

public class Bill {

	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	private double limitingAmount;
	private double currentDebt = 0;
	
	public Bill(double _limitingAmount) {
		limitingAmount = _limitingAmount;
	}
	
	public boolean check(double amount) {
		if (amount+this.currentDebt<=limitingAmount){
			return true;
		}
		else {
			return false;
		}
	}
	
	public void add(double amount) {
		currentDebt+=amount;
	}
	
	public void pay(double amount) {
		currentDebt-=amount;
	}
	
	public void changeTheLimit(double amount) {
		if(amount>=currentDebt) {
			limitingAmount = amount;
		}
	}
	
	public double getLimitingAmount() {
		return limitingAmount;
	}
	
	public double getCurrentDebt() {
		return currentDebt;
	}

	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

