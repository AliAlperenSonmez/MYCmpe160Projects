
package question;

public class Operator {
	// DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	int ID;
	private double talkingCharge;
	private double messageCost;
	private double networkCharge;
	private int discountRate;
	int talkingTime = 0;
	int nofMessages = 0;
	double internetAmount = 0.0;
	

	public Operator(int _id, double _talkingCharge, double _messageCost, double _networkCharge, int _discountRate) {
		ID = _id;
		talkingCharge = _talkingCharge;
		messageCost = _messageCost;
		networkCharge = _networkCharge;
		discountRate = _discountRate;
	}

	public double calculateTalkingCost(int minute, Customer customer) {
		if (customer.getAge() < 18 || customer.getAge() > 65) {
			return minute * talkingCharge *  (1 - (double)discountRate/100);
		} else {
			return minute * talkingCharge;
		}
	}

	public double calculateMessageCost(int quantity, Customer customer, Customer other) {
		if (customer.getOperator() == other.getOperator()) {
			return quantity * messageCost *  (1 - (double) discountRate/100);
		} else {
			return quantity * messageCost;
		}
	}
	


	public double calculateNetworkCost(double amount) {
		return amount * networkCharge;
	}

	public double getTalkingCharge() {
		return talkingCharge;
	}

	public void setTalkingCharge(double talkingCharge) {
		this.talkingCharge = talkingCharge;
	}

	public double getMessageCost() {
		return messageCost;
	}

	public void setMessageCost(double messageCost) {
		this.messageCost = messageCost;
	}

	public double getNetworkCharge() {
		return networkCharge;
	}

	public void setNetworkCharge(double networkCharge) {
		this.networkCharge = networkCharge;
	}

	public int getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}

	// DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
}
