
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;
import interfaces.IShip;
import java.util.*;
import ports.Port;
import containers.*;

/**
* Ship is the class of ships. Implements the ship interface.
* 
* @author Ali Alperen Sönmez
* 
*/

public class Ship implements IShip{
	
	/**
	 * ID of the Ship
	 */
	private int ID;
	
	/**
	 * Amount of the fuel Ship has.
	 */
	private double fuel;
	
	/**
	 * The port that the Ship is currently in.
	 */
	private Port currentPort;
	
	/**
	 * Current total weight of the ship.
	 */
	private int currentTotalWeight = 0;
	
	/**
	 * Total weight capacity of the ship.
	 */
	private int totalWeightCapacity;
	
	/**
	 * Maximum number of containers ship can carry.
	 */
	private int maxNumberOfAllContainers;
	
	/**
	 * Maximum number of HeavyContainers ship can carry.
	 */
	private int maxNumberOfHeavyContainers;
	
	/**
	 * Maximum number of RefrigeratedContainers ship can carry.
	 */
	private int maxNumberOfRefrigeratedContainers;
	
	/**
	 * Maximum number of LiquidContainers ship can carry.
	 */
	private int maxNumberOfLiquidContainers;
	
	/**
	 * Fuel consumption of the ship per KM.
	 */
	private double fuelConsumptionPerKM;
	
	/**
	 * ArrayList of the containers that ship currently has
	 */
	ArrayList<Container> allContainers = new ArrayList<Container>();
	
	/**
	 * ArrayList of the BasicContainers that ship currently has
	 */
	ArrayList<Container> basics = new ArrayList<Container>();
	
	/**
	 * ArrayList of the HeavyContainers that ship currently has
	 */
	ArrayList<Container> heavies = new ArrayList<Container>();
	
	/**
	 * ArrayList of the RefrigeratedContainers that ship currently has
	 */
	ArrayList<Container> refrigerateds = new ArrayList<Container>();
	
	/**
	 * ArrayList of the LiquidContainers that ship currently has
	 */
	ArrayList<Container> liquids = new ArrayList<Container>();
	
	/**
	 * Number of the HeavyContainers that ship currently has
	 */
	private int nofHeavyContainers = 0;
	
	/**
	 * Number of the RefrigeratedContainers that ship currently has
	 */
	private int nofRefrigeratedContainers = 0;
	
	/**
	 * Number of the LiquidContainers that ship currently has
	 */
	private int nofLiquidContainers = 0;
	
	/**
	 * <p>Constructor of the Ship
	 * @param ID ID of the ship
	 * @param p The port that the Ship is currently in.
	 * @param totalWeightCapacity Total weight capacity of the ship.
	 * @param maxNumberOfAllContainers Maximum number of containers ship can carry.
	 * @param maxNumberOfHeavyContainers Maximum number of HeavyContainers ship can carry.
	 * @param maxNumberOfRefrigeratedContainers Maximum number of RefrigeratedContainers ship can carry.
	 * @param maxNumberOfLiquidContainers Maximum number of LiquidContainers ship can carry.
	 * @param fuelConsumptionPerKM Fuel consumption of the ship per KM.
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, 
			int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers,
			int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) 
	{
		this.ID = ID;
		currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		
	}
	
	/**
	 * <p>A method for loading container into the ship. Overrides IShip.java's method.
	 * @param cont The container to load to the ship
	 * @return boolean true if load is successful, false otherwise
	 */
	@Override
	public boolean load(Container cont) {
		if (allContainers.size()<maxNumberOfAllContainers && currentTotalWeight+cont.getWeight()<=totalWeightCapacity &&
				!allContainers.contains(cont)) {
			if (cont instanceof BasicContainer) {
				basics.add(cont);
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}

			else if (cont instanceof LiquidContainer && nofLiquidContainers<maxNumberOfLiquidContainers && nofHeavyContainers<maxNumberOfHeavyContainers) {
				nofLiquidContainers+=1;
				liquids.add(cont);
				nofHeavyContainers+=1;
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}
			else if (cont instanceof RefrigeratedContainer && nofRefrigeratedContainers<maxNumberOfRefrigeratedContainers && nofHeavyContainers<maxNumberOfHeavyContainers) {
				nofRefrigeratedContainers+=1;
				nofHeavyContainers+=1;
				refrigerateds.add(cont);
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}
			else if (cont instanceof HeavyContainer && nofHeavyContainers<maxNumberOfHeavyContainers && !(cont instanceof LiquidContainer) && !(cont instanceof RefrigeratedContainer)) {
				nofHeavyContainers+=1;
				heavies.add(cont);
				allContainers.add(cont);
				currentTotalWeight+=cont.getWeight();
				return true;
			}
			else {return false;}
			
		}
		else {return false;}
	}
	
	/**
	 * <p>A method for unloading container from the ship. Overrides IShip.java's method.
	 * @param cont The container to unload from the ship
	 * @return boolean true if unload is successful, false otherwise
	 */
	@Override
	public boolean unLoad(Container cont) {
		if (allContainers.contains(cont)) {
			currentPort.addContainer(cont);
			allContainers.remove(cont);
			if (cont instanceof BasicContainer) {
				basics.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else if (cont instanceof LiquidContainer) {
				nofLiquidContainers-=1;
				nofHeavyContainers-=1;
				liquids.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else if (cont instanceof RefrigeratedContainer) {
				nofRefrigeratedContainers-=1;
				nofHeavyContainers-=1;
				refrigerateds.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else if (cont instanceof HeavyContainer) {
				nofHeavyContainers-=1;
				heavies.remove(cont);
				currentTotalWeight-=cont.getWeight();
				return true;
			}
			else {return false;}
		}
		else {return false;}
	}
	
	/**
	 * <p>A method for adding fuel to the ship. Overrides its IShip.java's method.
	 * @param newFuel A double that indicates the amount of fuel to add.
	 */
	@Override
	public void reFuel(double newFuel) {
		fuel+=newFuel;
	}
	
	/**
	 * <p>A method for sailing to another port. Overrides its IShip.java's method.
	 * @param p The port to sail.
	 * @return boolean true if sail is successful, false otherwise
	 */
	@Override
	public boolean sailTo(Port p) {
		double distance = currentPort.getDistance(p);
		double consumptionAmount = 0.0;
		for(Container container:allContainers) {
			consumptionAmount+=container.consumption();
		}
		consumptionAmount=(this.fuelConsumptionPerKM+consumptionAmount)*distance;
		
		if (consumptionAmount<=fuel) {
			currentPort.outgoingShip(this);
			p.incomingShip(this);
			currentPort = p;
			fuel-=consumptionAmount;
			return true;
		}
		else {return false;}
		
	}
	
	/**
	 * <p> Getter for the ArrayList allContainers. Sorts them first.
	 * @return Sorted ArrayList allContainers
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(allContainers, new CompareContainer());
		return allContainers;
	}
	
	/**
	 * <p> Getter for the ID of the ship
	 * @return int ID
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * <p> Getter for the currentPort
	 * @return Port currentPort
	 */
	public Port getCurrentPort() {
		return currentPort;
	}
	
	/**
	 * <p> Getter for the ArrayList of basics. Sorts them first.
	 * @return Sorted ArrayList basics
	 */
	public ArrayList<Container> getBasics() {
		Collections.sort(basics, new CompareContainer());
		return basics;
	}
	
	/**
	 * <p> Getter for the ArrayList of heavies. Sorts them first.
	 * @return Sorted ArrayList heavies
	 */
	public ArrayList<Container> getHeavies() {
		Collections.sort(heavies, new CompareContainer());
		return heavies;
	}
	
	/**
	 * <p> Getter for the ArrayList of refrigerateds. Sorts them first.
	 * @return Sorted ArrayList refrigerateds
	 */
	public ArrayList<Container> getRefrigerateds() {
		Collections.sort(refrigerateds, new CompareContainer());
		return refrigerateds;
	}
	
	/**
	 * <p> Getter for the ArrayList of liquids. Sorts them first.
	 * @return Sorted ArrayList liquids
	 */
	public ArrayList<Container> getLiquids() {
		Collections.sort(liquids, new CompareContainer());
		return liquids;
	}
	
	/**
	 * <p> Getter for the fuel
	 * @return double fuel
	 */
	public double getFuel() {
		return fuel;
	}
	
	public int getMaxNumberOfAllContainers() {
		return maxNumberOfAllContainers;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

