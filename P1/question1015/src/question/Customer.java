
package question;

public class Customer {
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	int ID;
	String name;
	private int age;
	private Operator operator;
	private Bill bill;
	double totalMoneySpent = 0;
	int talkingTime = 0;
	int totalMessages = 0;
	double connectionAmount = 0.0;
	
	public Customer(int _id, String _name, int _age, Operator _operator, double limitingAmount) {
		ID = _id;
		name = _name;
		age = _age;
		operator = _operator;
		bill = new Bill(limitingAmount);
	}
	
	public void talk(int minute, Customer other) {
		talkingTime += minute;
		other.talkingTime += minute;
	}
	
	public void message(int quantity, Customer other) {
		totalMessages += quantity;
	}
	
	public void connection(double amount) {
		connectionAmount += amount;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Operator getOperator() {
		return operator;
	}
	
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public Bill getBill() {
		return bill;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public void addDebt(double amount) {
		this.bill.add(amount);
	}
	
	public void payDebt(double amount) {
		this.bill.pay(amount);
		totalMoneySpent+=amount;
	}
	

	public void changeLimit(double newLimit) {
		this.bill.changeTheLimit(newLimit);
		
	}


	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}

