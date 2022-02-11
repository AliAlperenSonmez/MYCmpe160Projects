
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import containers.*;
import ports.*;
import ships.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
* Main Class
* 
* @author Ali Alperen Sönmez
* 
*/
public class Main {
	/**
	 * <p>Main method
	 * @param args Main receives two arguments: path to input file and path to output file.
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
		 * ArrayList of all ships in the program
		 */
		ArrayList<Ship> ships = new ArrayList<Ship>();
		
		/**
		 * ArrayList of all ports in the program
		 */
		ArrayList<Port> ports = new ArrayList<Port>();
		
		/**
		 * ArrayList of all containers in the program
		 */
		ArrayList<Container> conts = new ArrayList<Container>();
		
		/**
		 * Number of input lines
		 */
		int N = in.nextInt();
		
		/**
		 * ID of the container that initialized first
		 */
		int contID = 0;
		
		/**
		 * ID of the port that initialized first
		 */
		int portID = 0;
		
		/**
		 * ID of the ship that initialized first
		 */
		int shipID = 0;
		in.nextLine();

		for (int i = 0; i < N; i++) {
			
			/**
			 * Input line
			 */
			String line = in.nextLine();
			
			/**
			 * Parsing the line
			 */
			String[] parts = line.split("\\s+");
			
			/**
			 * Action to take
			 */
			int action = Integer.parseInt(parts[0]);

			if (action == 1) {
				
				/**
				 * The port that the container initially in
				 */
				int portOfCont = Integer.parseInt(parts[1]);
				
				/**
				 * Weight of the container
				 */
				int weightOfCont = Integer.parseInt(parts[2]);

				if (parts.length == 3) {
					if (weightOfCont <= 3000) {
						
						/**
						 * New BasicContainer object
						 */
						BasicContainer container = new BasicContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					} else if (weightOfCont > 3000) {
						
						/**
						 * New HeavyContainer object
						 */
						HeavyContainer container = new HeavyContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					}

				}

				else if (parts.length == 4) {
					
					/**
					 * Type of the container (liquid or refrigerated)
					 */
					String contType = parts[3];

					if (contType.equals("R")) {
						
						/**
						 * New RefrigeratedContainer object
						 */
						RefrigeratedContainer container = new RefrigeratedContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					}

					else if (contType.equals("L")) {
						
						/**
						 * New LiquidContainer object
						 */
						LiquidContainer container = new LiquidContainer(contID, weightOfCont);
						
						ports.get(portOfCont).addContainer(container);
						conts.add(container);
						contID += 1;
					}
				}
			}

			else if (action == 2) {
				
				/**
				 * Initial port of the ship
				 */
				int portOfShip = Integer.parseInt(parts[1]);
				
				/**
				 * Maximum weight capacity of the ship
				 */
				int maxWeight = Integer.parseInt(parts[2]);
				
				/**
				 * Maximum number of containers ship can carry
				 */
				int maxNofConts = Integer.parseInt(parts[3]);
				
				/**
				 * Maximum number of HeavyContainers ship can carry
				 */
				int maxNofHeavy = Integer.parseInt(parts[4]);
				
				/**
				 * Maximum number of RefrigeratedContainers ship can carry
				 */
				int maxNofRefrigerated = Integer.parseInt(parts[5]);
				
				/**
				 * Maximum number of LiquidContainers ship can carry
				 */
				int maxNofLiquid = Integer.parseInt(parts[6]);
				
				/**
				 * Fuel consumption of the ship
				 */
				double fuelConsumption = Double.parseDouble(parts[7]);
				
				/**
				 * New ship object
				 */
				Ship ship = new Ship(shipID, ports.get(portOfShip), maxWeight, maxNofConts, maxNofHeavy,
						maxNofRefrigerated, maxNofLiquid, fuelConsumption);
				
				ships.add(ship);
				ports.get(portOfShip).incomingShip(ship);
				shipID += 1;

			}

			else if (action == 3) {
				
				/**
				 * X coordinate of the Port
				 */
				double x = Double.parseDouble(parts[1]);
				
				/**
				 * Y coordinate of the Port
				 */
				double y = Double.parseDouble(parts[2]);
				
				/**
				 * New port object
				 */
				Port port = new Port(portID, x, y);
				ports.add(port);
				portID += 1;
			}

			else if (action == 4) {
				
				/**
				 * The ship that the container is being loaded
				 */
				Ship shipToLoad = ships.get(Integer.parseInt(parts[1]));
				
				/**
				 * The container that is being loaded
				 */
				Container contToLoad = conts.get(Integer.parseInt(parts[2]));

				if (shipToLoad.getCurrentPort().getContainers().contains(contToLoad)) {
					if (shipToLoad.load(contToLoad)) {
						shipToLoad.getCurrentPort().removeContainer(contToLoad);
					}
				}
			}

			else if (action == 5) {
				
				/**
				 * The ship that the container is being unloaded
				 */
				Ship shipToUnload = ships.get(Integer.parseInt(parts[1]));
				
				/**
				 * The container that is being loaded
				 */
				Container contToUnload = conts.get(Integer.parseInt(parts[2]));
				if (shipToUnload.getCurrentContainers().contains(contToUnload)) {
					shipToUnload.unLoad(contToUnload);
				}
			}

			else if (action == 6) {
				
				/**
				 * The ship that is sailing
				 */
				Ship shipToTravel = ships.get(Integer.parseInt(parts[1]));
				
				/**
				 * The port that the ship is sailing to
				 */
				Port destinationPort = ports.get(Integer.parseInt(parts[2]));
				shipToTravel.sailTo(destinationPort);
			}

			else if (action == 7) {
				
				/**
				 * The ship that is being fueled
				 */
				Ship shipiFulle = ships.get(Integer.parseInt(parts[1]));
				
				/**
				 * The amount of fuel
				 */
				double amount = Double.parseDouble(parts[2]);
				shipiFulle.reFuel(amount);				
				
			}
		}

		for (Port port : ports) {
			out.printf("Port " + port.getId() + ": (" + String.format("%.2f", port.getX()) + ", "
					+ String.format("%.2f", port.getY()) + ")\n");
			if (port.getNofBasic() > 0) {
				out.print("  BasicContainer:");
				for (Container basic : port.getBasics()) {
					out.print(" "+basic.getID());
				}
				out.println();
			}
			if (port.getNofHeavy() > 0) {
				out.print("  HeavyContainer:");
				for (Container heavy : port.getHeavies()) {
					out.print(" "+heavy.getID());
				}
				out.println();
			}
			if (port.getNofRefrigerated() > 0) {
				out.print("  RefrigeratedContainer:");
				for (Container ref : port.getRefrigerateds()) {
					out.print(" "+ref.getID());
				}
				out.println();
			}
			if(port.getNofLiquid()>0) {
				out.print("  LiquidContainer:");
				for (Container liquid:port.getLiquids()) {
					out.print(" "+liquid.getID());
				}
				out.println();
			}
			
			if (port.getCurrent().size()!=0) {
				for(Ship ship:port.getCurrent()) {
					out.println("  Ship "+ship.getID()+": "+String.format("%.2f", ship.getFuel()));
					if (ship.getBasics().size()>0) {
						out.print("    BasicContainer:");
						for (Container basic: ship.getBasics()) {
							out.print(" "+basic.getID());
						}
						out.println();
					}
					if (ship.getHeavies().size()>0) {
						out.print("    HeavyContainer:");
						for (Container heavy: ship.getHeavies()) {
							out.print(" "+heavy.getID());
						}
						out.println();
					}
					if (ship.getRefrigerateds().size()>0) {
						out.print("    RefrigeratedContainer:");
						for (Container ref: ship.getRefrigerateds()) {
							out.print(" "+ref.getID());
						}
						out.println();
					}
					if (ship.getLiquids().size()>0) {
						out.print("    LiquidContainer:");
						for (Container liq: ship.getLiquids()) {
							out.print(" "+liq.getID());
						}
						out.println();
					}
				}
			}

		}

		in.close();
		out.close();
	}
}

//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
