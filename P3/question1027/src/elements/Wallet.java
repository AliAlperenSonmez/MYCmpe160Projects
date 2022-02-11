package elements;
/**
 * Wallet class is the class of wallets.
 * 
 * @author Ali Alperen Sönmez
 * 
 */
public class Wallet {
	/**
	 * Total amount of dollars in the wallet
	 */
	private double dollars;
	
	/**
	 * Total amount of coins in the wallet
	 */
	private double coins;
	
	/**
	 * Amount of blocked dollars in the wallet
	 */
	private double blockedDollars = 0;
	
	/**
	 * Amount of blocked coins in the wallet
	 */
	private double blockedCoins = 0;
	
	
	/**
	 * <p>
	 * Constructor of the Wallet
	 * 
	 * @param dollars initial amount of dollars in the wallet
	 * @param coins   initial amount of coins in the wallet
	 */
	public Wallet(double dollars, double coins) {
		this.dollars=dollars;
		this.coins = coins;
	}
	
	/**
	 * <p>
	 * Getter for total number of dollars in the wallet
	 * 
	 * @return double dollars
	 */
	public double getDollars() {
		return dollars;
	}
	
	/**
	 * <p>
	 * Getter for total number of coins in the wallet
	 * 
	 * @return double coins
	 */
	public double getCoins() {
		return coins;
	}
	
	/**
	 * <p>
	 * Getter for number of blocked coins in the wallet
	 * 
	 * @return double blockedCoins
	 */
	public double getBlockedCoins() {
		return blockedCoins;
	}
	
	/**
	 * <p>
	 * Getter for number of blocked dollars in the wallet
	 * 
	 * @return double blockedDollars
	 */
	public double getBlockedDollars() {
		return blockedDollars;
	}
	
	/**
	 * <p>
	 * This method is for blocking dollars when a buying order is given
	 * @param amount amount of dollars that are being blocked
	 */
	public void blockDollars(double amount) {
		blockedDollars+=amount;
	}
	
	/**
	 * <p>
	 * This method is for blocking coins when a selling order is given
	 * @param amount amount of coins that are being blocked
	 */
	public void blockCoins(double amount) {
		blockedCoins+=amount;
	}
	
	/**
	 * <p>
	 * This method deposits some dollars to the wallet
	 * 
	 * @param amount amount of dollars to deposit
	 */
	public void depositDollars(double amount) {
		this.dollars+=amount;
	}
	
	/**
	 * <p>
	 * This method withdraws some dollars from the wallet
	 * 
	 * @param amount amount of dollars to withdraw
	 */
	public void withdrawDollars(double amount) {
			this.dollars-=amount;
	}
	
	/**
	 * <p>
	 * This method adjust the amount of dollars and coins when a buying order is fulfilled
	 * 
	 * @param amount amount of coins that are buyed
	 * @param price purchasing price of the coins
	 */
	public void buyed(double amount, double price) {
		this.dollars-=(double)amount*price;
		this.coins+=(double)amount;
		this.blockedDollars-=(double)amount*price;
	}
	
	/**
	 * <p>
	 * This method adjust the amount of dollars and coins when a selling order is fulfilled
	 * 
	 * @param amount amount of coins that are sold
	 * @param price selling price of the coins
	 */
	public void sold(double amount, double price) {
		this.dollars+= (double)amount*price;
		this.blockedCoins-=(double)amount;
		this.coins-=(double)amount;
	}
	
	/**
	 * <p>
	 * This method tells the wallet to release some of its blocked dollars when a
	 * transaction happens at a price that is less then the buying order price
	 * 
	 * @param amount amount of blocked dollars that are released
	 */
	public void releaseDollars(double amount) {
		this.blockedDollars-=amount;
	}
	
	/**
	 * <p>
	 * This method gives some reward coins to the trader
	 * 
	 * @param amount amount of coins that are given as rewards
	 */
	public void giveReward(double amount) {
		this.coins+=amount;
	}
}
