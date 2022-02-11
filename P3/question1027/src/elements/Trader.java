package elements;

/**
 * Trader class is the class of traders.
 * 
 * @author Ali Alperen Sönmez
 * 
 */
public class Trader {
	/**
	 * ID of the trader
	 */
	private int ID;

	/**
	 * Wallet of the trader
	 */
	private final Wallet wallet;

	/**
	 * Keeps the total number of traders in the program
	 */
	public static int numberOfUsers = 0;

	/**
	 * Keeps the total number of invalid queries in the program.
	 */
	public static int numberOfInvalidQueries = 0;

	/**
	 * <p>
	 * Constructor of the Traider
	 * 
	 * @param dollars initial amount of dollars in trader's wallet
	 * @param coins   initial amount of coins in trader's wallet
	 */
	public Trader(double dollars, double coins) {
		wallet = new Wallet(dollars, coins);
	}

	/**
	 * <p>
	 * A method for giving selling order for trader's coins.
	 * 
	 * @param amount amount of coins to sell
	 * @param price  The selling price of coins
	 * @param market the market in which the selling order is given
	 * @return int 1 if the order is valid -1 if the order is invalid
	 */
	public int sell(double amount, double price, Market market) {
		if (amount <= this.wallet.getCoins() - this.wallet.getBlockedCoins()) {
			this.wallet.blockCoins(amount);
			SellingOrder sOrder = new SellingOrder(ID, amount, price);
			market.giveSellOrder(sOrder);
			return 1;
		}

		else {
			numberOfInvalidQueries += 1;
			return -1;
		}
	}

	/**
	 * <p>
	 * A method for giving selling order for market price.
	 * 
	 * @param amount amount of coins to sell
	 * @param market the market in which the selling order is given
	 */
	public void sellAtMarketPrice(double amount, Market market) {
		if (market.getBuyingOrders().size() > 0) {
			double price = market.getBuyingOrders().peek().getPrice();
			sell(amount, price, market);
		} else {
			numberOfInvalidQueries += 1;
		}
	}

	/**
	 * <p>
	 * A method for giving buying order.
	 * 
	 * @param amount amount of coins to buy
	 * @param price  The buying price of coins
	 * @param market the market in which the buying order is given
	 * @return int 1 if the order is valid -1 if the order is invalid
	 */
	public int buy(double amount, double price, Market market) {

		if (amount * price <= this.wallet.getDollars() - this.wallet.getBlockedDollars()) {
			this.wallet.blockDollars(amount * price);
			BuyingOrder bOrder = new BuyingOrder(ID, amount, price);
			market.giveBuyOrder(bOrder);
			return 1;
		}

		else {
			numberOfInvalidQueries += 1;
			return -1;
		}
	}

	/**
	 * <p>
	 * A method for giving buying order for market price.
	 * 
	 * @param amount amount of coins to buy
	 * @param market the market in which the buying order is given
	 */
	public void buyAtMarketPrice(double amount, Market market) {
		if (market.getSellingOrders().size() > 0) {
			double price = market.getSellingOrders().peek().getPrice();
			sell(amount, price, market);
		} else {
			numberOfInvalidQueries += 1;
		}
	}

	/**
	 * <p>
	 * Getter for trader's ID
	 * 
	 * @return int ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * <p>
	 * Setter for trader's ID
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * <p>
	 * Getter for trader's Wallet
	 * 
	 * @return Wallet wallet
	 */
	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * <p>
	 * This method tells the wallet that a buying order is fulfilled
	 * 
	 * @param amount amount of coins that are bought
	 * @param price  purchase price of coins
	 */
	public void buyed(double amount, double price) {
		this.wallet.buyed(amount, price);
	}

	/**
	 * <p>
	 * This method tells the wallet to release some of its blocked dollars when a
	 * transaction happens at a price that is less then the buying order price
	 * 
	 * @param amount amount of blocked dollars that are released
	 */
	public void releaseBlockedDollars(double amount) {
		this.wallet.releaseDollars(amount);
	}

	/**
	 * <p>
	 * This method tells the wallet that a selling order is fulfilled
	 * 
	 * @param amount amount of coins that are sold
	 * @param price  selling price of coins
	 */
	public void sold(double amount, double price) {
		this.wallet.sold(amount, price);
	}
	
	/**
	 * <p>
	 * This method withdraws some dollars from the wallet
	 * 
	 * @param amount amount of dollars that trader tries to withdraw
	 */
	public void withdrawDollars(double amount) {
		if (wallet.getDollars() - wallet.getBlockedDollars() >= amount) {
			wallet.withdrawDollars(amount);
		} else {
			numberOfInvalidQueries += 1;
		}
	}

	/**
	 * <p>
	 * This method deposits some dollars to the wallet
	 * 
	 * @param amount amount of dollars that trader tries to deposit
	 */
	public void depositDollars(double amount) {
		this.wallet.depositDollars(amount);
	}
	
	/**
	 * <p>
	 * This method gives some reward coins to the trader
	 * 
	 * @param amount amount of coins that are given as rewards
	 */
	public void giveReward(double amount) {
		this.wallet.giveReward(amount);
	}

}
