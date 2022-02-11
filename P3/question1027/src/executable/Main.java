package executable;

import elements.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Main Class
 * 
 * @author Ali Alperen Sönmez
 * 
 */
public class Main {
	
	/**
	 * Random object for giving random rewards to the traders.
	 */
	public static Random myRandom;
	
	/**
	 * <p>
	 * Main method
	 * 
	 * @param args Main receives two arguments: path to input file and path to
	 *             output file.
	 */


	public static void main(String[] args) throws FileNotFoundException {

		/**
		 * Scanner object to read input file.
		 */
		Scanner in = new Scanner(new File(args[0]));

		/**
		 * PrintStream object to write to output file.
		 */
		PrintStream out = new PrintStream(new File(args[1]));

		/**
		 * For generating IDs of the traders.
		 */
		int traderID = 0;
		
		/**
		 * Random seed for giving random rewards to the traders.
		 */
		int seed = in.nextInt();

		myRandom = new Random(seed);
		
		/**
		 * transaction fee of the market
		 */
		int fee = in.nextInt();
		
		/**
		 * the market
		 */
		Market market = new Market(fee);
		
		/**
		 * number of traders in the market
		 */
		int nofUsers = in.nextInt();
		
		/**
		 *number of queries in the input
		 */
		int nofQueries = in.nextInt();
		
		/**
		 *ArrayList of the traders
		 */
		ArrayList<Trader> traders = new ArrayList<Trader>();

		for (int i = 0; i < nofUsers; i++) {
			double dollars = in.nextDouble();
			double coins = in.nextDouble();
			Trader trader = new Trader(dollars, coins);
			trader.setID(traderID);
			traders.add(trader);
			traderID++;
		}

		for (int i = 0; i < nofQueries; i++) {
			int action = in.nextInt();

			if (action == 10) {
				int trader_id = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				traders.get(trader_id).buy(amount, price, market);
				market.checkTransactions(traders);
			}

			else if (action == 11) {
				int trader_id = in.nextInt();
				double amount = in.nextDouble();
				traders.get(trader_id).buyAtMarketPrice(amount, market);
				market.checkTransactions(traders);
			}

			else if (action == 20) {
				int trader_id = in.nextInt();
				double price = in.nextDouble();
				double amount = in.nextDouble();
				traders.get(trader_id).sell(amount, price, market);
				market.checkTransactions(traders);
			}

			else if (action == 21) {
				int trader_id = in.nextInt();
				double amount = in.nextDouble();
				traders.get(trader_id).sellAtMarketPrice(amount, market);
				market.checkTransactions(traders);
			}

			else if (action == 3) {
				int trader_id = in.nextInt();
				double amount = in.nextDouble();
				traders.get(trader_id).depositDollars(amount);
			}

			else if (action == 4) {
				int trader_id = in.nextInt();
				double amount = in.nextDouble();
				traders.get(trader_id).withdrawDollars(amount);
			}

			else if (action == 5) {
				int trader_id = in.nextInt();
				Trader trader = traders.get(trader_id);
				out.println("Trader " + trader_id + ": " + trader.getWallet().getDollars() + "$ "
						+ trader.getWallet().getCoins() + "PQ");
			}

			else if (action == 777) {
				for (Trader t : traders) {
					t.giveReward(myRandom.nextDouble() * 10);
				}
			}

			else if (action == 666) {
				double price = in.nextDouble();
				market.makeOpenMarketOperation(price, traders);
				market.checkTransactions(traders);
			}

			else if (action == 500) {
				out.println("Current market size: " + String.format("%.5f", market.totalDollarsForBuying()) + " "
						+ String.format("%.5f", market.totalCoinsForSale()));
			}

			else if (action == 501) {
				out.println("Number of successful transactions: " + market.getTransactions().size());
			}

			else if (action == 502) {
				out.println("Number of invalid queries: " + Trader.numberOfInvalidQueries);
			}

			else if (action == 505) {
				out.println("Current prices: " + String.format("%.5f", market.currentBuyingPrice()) + " "
						+ String.format("%.5f", market.currentSellingPrice()) + " "
						+ String.format("%.5f", market.averagePrice()));
			}

			else if (action == 555) {
				for (Trader t : traders) {
					out.println("Trader " + t.getID() + ": " + String.format("%.5f", t.getWallet().getDollars()) + "$ "
							+ String.format("%.5f", t.getWallet().getCoins()) + "PQ");
				}
			}
		}
	}
}
