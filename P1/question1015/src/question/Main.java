
package question;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {

		Customer[] customers;
		Operator[] operators;

		int C, O, N;

		File inFile = new File(args[0]); // args[0] is the input file
		File outFile = new File(args[1]); // args[1] is the output file
		try {
			PrintStream outstream = new PrintStream(outFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Scanner reader;
		try {
			reader = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find input file");
			return;
		}

		C = reader.nextInt();
		O = reader.nextInt();
		N = reader.nextInt();

		customers = new Customer[C];
		operators = new Operator[O];

		// DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
		
		PrintStream outstream1;
		try {
			outstream1 = new PrintStream(outFile);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
			return;
		}
		int cId = 0;
		int oId = 0;
		for (int i = 0; i < N; i++) {

			int action = reader.nextInt();
			if (action == 1) {
				customers[cId] = new Customer(cId, reader.next(), reader.nextInt(), operators[reader.nextInt()],
						reader.nextDouble());
				cId += 1;
			} else if (action == 2) {
				operators[oId] = new Operator(oId, reader.nextDouble(), reader.nextDouble(), reader.nextDouble(),
						reader.nextInt());
				oId += 1;
			} else if (action == 3) {
				Customer customer = customers[reader.nextInt()];
				Customer other = customers[reader.nextInt()];
				int minute = reader.nextInt();
				double talkingCost = customer.getOperator().calculateTalkingCost(minute, customer);

				if (customer.getBill().check(talkingCost)
						&& customer.ID != other.ID) {
					customer.talk(minute, other);
					customer.addDebt(talkingCost);
					customer.getOperator().talkingTime += minute;
					other.getOperator().talkingTime += minute;
				}
			} else if (action == 4) {
				Customer customer = customers[reader.nextInt()];
				Customer other = customers[reader.nextInt()];
				int quantity = reader.nextInt();
				double messageCost = customer.getOperator().calculateMessageCost(quantity, customer, other);
				if (customer.getBill().check(messageCost)
						&& customer.ID != other.ID) {
					customer.message(quantity, other);
					customer.addDebt(messageCost);
					customer.getOperator().nofMessages += quantity;
				}
			} else if (action == 5) {
				Customer customer = customers[reader.nextInt()];
				double amount = reader.nextDouble();
				double networkCost = customer.getOperator().calculateNetworkCost(amount);

				if (customer.getBill().check(networkCost)) {
					customer.connection(amount);
					customer.addDebt(networkCost);
					customer.getOperator().internetAmount += amount;
				}
			} else if (action == 6) {
				Customer customer = customers[reader.nextInt()];
				double amount = reader.nextDouble();
				if (amount>customer.getBill().getCurrentDebt()) {
					customer.payDebt(customer.getBill().getCurrentDebt());}
				else {customer.payDebt(amount);}
			} else if (action == 7) {
				Customer customer = customers[reader.nextInt()];
				Operator operator = operators[reader.nextInt()];
				customer.setOperator(operator);
			} else if (action == 8) {
				Customer customer = customers[reader.nextInt()];
				double newLimit = reader.nextDouble();
				customer.changeLimit(newLimit);
			}

		}
		int talkMost = -1;
		int messageMost = -1;
		double connectMost = -1;
		int talkMostId = -1;
		int messageMostId = -1;
		int connectMostId = -1;

		for (Customer cust : customers) {
			if (cust.talkingTime > talkMost) {
				talkMost = cust.talkingTime;
				talkMostId = cust.ID;
			} else if (cust.talkingTime == talkMost && cust.ID < talkMostId) {
				talkMostId = cust.ID;
			}

			if (cust.totalMessages > messageMost) {
				messageMost = cust.totalMessages;
				messageMostId = cust.ID;
			} else if (cust.totalMessages == messageMost && cust.ID < messageMostId) {
				messageMostId = cust.ID;
			}

			if (cust.connectionAmount > connectMost) {
				connectMost = cust.connectionAmount;
				connectMostId = cust.ID;
			} else if (cust.connectionAmount == connectMost && cust.ID < connectMostId) {
				connectMostId = cust.ID;
			}
		}
		
		for (Operator oper:operators) {
			outstream1.printf("Operator "+oper.ID+" : "+oper.talkingTime+" "+oper.nofMessages+" "+ String.format("%.2f", oper.internetAmount) +"\n");
		}
		for (Customer cust:customers) {
			outstream1.printf("Customer "+cust.ID+" : "+String.format("%.2f",cust.totalMoneySpent)+" "+String.format("%.2f",cust.getBill().getCurrentDebt())+"\n");
		}
		outstream1.printf(customers[talkMostId].name+" : "+talkMost+"\n");
		outstream1.printf(customers[messageMostId].name+" : "+messageMost+"\n");
		outstream1.printf(customers[connectMostId].name+" : "+String.format("%.2f",connectMost));

		// DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	}
}
