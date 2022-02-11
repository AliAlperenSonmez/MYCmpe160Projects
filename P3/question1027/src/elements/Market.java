package elements;

/**
 * Market class is the class of Markets.
 * 
 * @author Ali Alperen Sönmez
 * 
 */
import java.util.*;

public class Market {
	/**
	 * Priority queue of selling orders
	 */
	private PriorityQueue<SellingOrder> sellingOrders = new PriorityQueue<SellingOrder>();

	/**
	 * Priority queue of buying orders
	 */
	private PriorityQueue<BuyingOrder> buyingOrders = new PriorityQueue<BuyingOrder>();

	/**
	 * ArrayList of transactions
	 */
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	/**
	 * transaction fee of the market
	 */
	private final int fee;

	/**
	 * <p>
	 * Constructor of the Market
	 * 
	 * @param int fee transaction fee of the market
	 */
	public Market(int fee) {
		this.fee = fee;
	}

	/**
	 * <p>
	 * method for adding SellingOrder to sellingOrders
	 * 
	 * @param SellingOrder order
	 */
	public void giveSellOrder(SellingOrder order) {
		this.sellingOrders.add(order);
	}

	/**
	 * <p>
	 * method for adding BuyingOrder to buyingOrders
	 * 
	 * @param BuyingOrder order
	 */
	public void giveBuyOrder(BuyingOrder order) {
		this.buyingOrders.add(order);
	}

	/**
	 * <p>
	 * method for manipulating the price by buying and selling in the market
	 * 
	 * @param Double            price the price that we want market to converge
	 * @param ArrayList<Trader> traders
	 */
	public void makeOpenMarketOperation(double price, ArrayList<Trader> traders) {
		if (sellingOrders.size()>0) {
			while (sellingOrders.peek().getPrice()<=price) {
				SellingOrder sOrder = sellingOrders.poll();
				BuyingOrder bOrder = new BuyingOrder(0, sOrder.getAmount(), sOrder.getPrice());
				traders.get(sOrder.getTraderID()).sold(sOrder.getAmount(),
						sOrder.getPrice() * (double) (1.00 - fee / 1000.00));
				transactions.add(new Transaction(sOrder, bOrder));
			}
		}
		
		else if (buyingOrders.size()>0) {
			while (buyingOrders.peek().getPrice()>=price) {
				BuyingOrder bOrder = buyingOrders.poll();
				SellingOrder sOrder = new SellingOrder(0, bOrder.getAmount(), bOrder.getPrice());
				traders.get(bOrder.getTraderID()).buyed(bOrder.getAmount(), bOrder.getPrice());
				transactions.add(new Transaction(sOrder, bOrder));
			}
		}
	}
	


	/**
	 * <p>
	 * method for fulfilling overlaping transactions
	 * 
	 * @param ArrayList<Trader> traders
	 */
	public void checkTransactions(ArrayList<Trader> traders) {
		while (sellingOrders.size() > 0 && buyingOrders.size() > 0
				&& sellingOrders.peek().getPrice() <= buyingOrders.peek().getPrice()) {
			if (sellingOrders.peek().getPrice() == buyingOrders.peek().getPrice()) {
				if (sellingOrders.peek().getAmount() == buyingOrders.peek().getAmount()) {
					SellingOrder sOrder = sellingOrders.poll();
					BuyingOrder bOrder = buyingOrders.poll();

					Double transactionAmount = bOrder.getAmount();
					Double transactionPrice = sOrder.getPrice();

					Transaction transaction = new Transaction(sOrder, bOrder);

					traders.get(sOrder.getTraderID()).sold(transactionAmount,
							transactionPrice * (double) (1.00 - fee / 1000.00));
					traders.get(bOrder.getTraderID()).buyed(transactionAmount, transactionPrice);
					transactions.add(transaction);
				} else if (sellingOrders.peek().getAmount() > buyingOrders.peek().getAmount()) {
					SellingOrder sOrder = sellingOrders.poll();
					BuyingOrder bOrder = buyingOrders.poll();

					Double transactionAmount = bOrder.getAmount();
					Double remainingAmount = sOrder.getAmount() - bOrder.getAmount();
					Double transactionPrice = sOrder.getPrice();

					SellingOrder transactionSorder = new SellingOrder(sOrder.getTraderID(), transactionAmount,
							transactionPrice);
					SellingOrder remainingSorder = new SellingOrder(sOrder.getTraderID(), remainingAmount,
							sOrder.getPrice());

					Transaction transaction = new Transaction(transactionSorder, bOrder);
					traders.get(sOrder.getTraderID()).sold(transactionAmount,
							transactionPrice * (double) (1.00 - fee / 1000.00));
					traders.get(bOrder.getTraderID()).buyed(transactionAmount, transactionPrice);
					transactions.add(transaction);
					sellingOrders.add(remainingSorder);
				} else if (sellingOrders.peek().getAmount() < buyingOrders.peek().getAmount()) {
					SellingOrder sOrder = sellingOrders.poll();
					BuyingOrder bOrder = buyingOrders.poll();

					Double transactionAmount = sOrder.getAmount();
					Double remainingAmount = bOrder.getAmount() - sOrder.getAmount();
					Double transactionPrice = sOrder.getPrice();

					BuyingOrder transactionBorder = new BuyingOrder(bOrder.getTraderID(), transactionAmount,
							transactionPrice);
					BuyingOrder remainingBorder = new BuyingOrder(bOrder.getTraderID(), remainingAmount,
							sOrder.getPrice());

					Transaction transaction = new Transaction(sOrder, transactionBorder);
					traders.get(sOrder.getTraderID()).sold(transactionAmount,
							transactionPrice * (double) (1.00 - fee / 1000.00));
					traders.get(bOrder.getTraderID()).buyed(transactionAmount, transactionPrice);
					transactions.add(transaction);
					buyingOrders.add(remainingBorder);
				}
			}

			else if (sellingOrders.peek().getPrice() < buyingOrders.peek().getPrice()) {
				if (sellingOrders.peek().getAmount() == buyingOrders.peek().getAmount()) {
					SellingOrder sOrder = sellingOrders.poll();
					BuyingOrder bOrder = buyingOrders.poll();

					Double transactionPrice = sOrder.getPrice();
					Double transactionAmount = bOrder.getAmount();

					Transaction transaction = new Transaction(sOrder, bOrder);
					traders.get(sOrder.getTraderID()).sold(transactionAmount,
							transactionPrice * (double) (1.00 - fee / 1000.00));
					traders.get(bOrder.getTraderID()).buyed(transactionAmount, transactionPrice);
					traders.get(bOrder.getTraderID())
							.releaseBlockedDollars(transactionAmount * (bOrder.getPrice() - transactionPrice));
					transactions.add(transaction);
				} else if (sellingOrders.peek().getAmount() > buyingOrders.peek().getAmount()) {
					SellingOrder sOrder = sellingOrders.poll();
					BuyingOrder bOrder = buyingOrders.poll();

					Double transactionAmount = bOrder.getAmount();
					Double remainingAmount = sOrder.getAmount() - bOrder.getAmount();
					Double transactionPrice = sOrder.getPrice();

					SellingOrder transactionSorder = new SellingOrder(sOrder.getTraderID(), transactionAmount,
							transactionPrice);
					SellingOrder remainingSorder = new SellingOrder(sOrder.getTraderID(), remainingAmount,
							sOrder.getPrice());

					Transaction transaction = new Transaction(transactionSorder, bOrder);
					traders.get(sOrder.getTraderID()).sold(transactionAmount,
							transactionPrice * (double) (1.00 - fee / 1000.00));
					traders.get(bOrder.getTraderID()).buyed(transactionAmount, transactionPrice);
					traders.get(bOrder.getTraderID())
							.releaseBlockedDollars(transactionAmount * (bOrder.getPrice() - transactionPrice));
					transactions.add(transaction);
					sellingOrders.add(remainingSorder);
				} else if (sellingOrders.peek().getAmount() < buyingOrders.peek().getAmount()) {
					SellingOrder sOrder = sellingOrders.poll();
					BuyingOrder bOrder = buyingOrders.poll();

					Double transactionAmount = sOrder.getAmount();
					Double remainingAmount = bOrder.getAmount() - sOrder.getAmount();
					Double transactionPrice = sOrder.getPrice();

					BuyingOrder transactionBorder = new BuyingOrder(bOrder.getTraderID(), transactionAmount,
							transactionPrice);
					BuyingOrder remainingBorder = new BuyingOrder(bOrder.getTraderID(), remainingAmount,
							sOrder.getPrice());

					Transaction transaction = new Transaction(sOrder, transactionBorder);
					traders.get(sOrder.getTraderID()).sold(transactionAmount,
							transactionPrice * (double) (1.00 - fee / 1000.00));
					traders.get(bOrder.getTraderID()).buyed(transactionAmount, transactionPrice);
					traders.get(bOrder.getTraderID())
							.releaseBlockedDollars(transactionAmount * (bOrder.getPrice() - transactionPrice));
					transactions.add(transaction);
					buyingOrders.add(remainingBorder);
				} else {
					break;
				}
			} else {
				break;
			}
		}
	}

	/**
	 * <p>
	 * method for calculating total coins for sale in the market
	 * 
	 * @return Double amount of coins in the market
	 */
	public double totalCoinsForSale() {
		double total = 0;
		if (sellingOrders.size() == 0) {
			return 0;
		}

		else {
			for (SellingOrder s : this.sellingOrders) {
				total += s.getAmount();
			}
			return total;
		}
	}

	/**
	 * <p>
	 * method for calculating total dollars in the buyingOrders
	 * 
	 * @return Double amount of dollars in the market
	 */
	public double totalDollarsForBuying() {
		double total = 0;
		if (buyingOrders.size() == 0) {
			return 0;
		}

		else {
			for (BuyingOrder b : buyingOrders) {
				total += b.getAmount() * b.getPrice();
			}
			return total;
		}
	}

	/**
	 * <p>
	 * method that returns current selling price of the market
	 * 
	 * @return Double price
	 */
	public double currentSellingPrice() {
		if (this.sellingOrders.size() == 0) {
			return 0;
		} else {
			return this.sellingOrders.peek().getPrice();
		}
	}

	/**
	 * <p>
	 * method that returns current buying price of the market
	 * 
	 * @return Double price
	 */
	public double currentBuyingPrice() {
		if (this.buyingOrders.size() == 0) {
			return 0;
		} else {
			return this.buyingOrders.peek().getPrice();
		}
	}

	/**
	 * <p>
	 * method that returns average price of the market
	 * 
	 * @return Double price
	 */
	public double averagePrice() {
		return (currentBuyingPrice() + currentSellingPrice()) / 2;
	}

	/**
	 * <p>
	 * Getter for the fee
	 * 
	 * @return int fee
	 */
	public int getFee() {
		return fee;
	}

	/**
	 * <p>
	 * Getter for the buyingOrders
	 * 
	 * @return PriorityQueue<BuyingOrder> buyingOrders
	 */
	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}

	/**
	 * <p>
	 * Getter for the sellingOrders
	 * 
	 * @return PriorityQueue<SellingOrder> sellingOrders
	 */
	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	/**
	 * <p>
	 * Getter for the transactions
	 * 
	 * @return ArrayList<Transaction> transactions
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

}
