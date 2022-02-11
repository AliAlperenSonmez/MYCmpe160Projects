
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;
import interfaces.IPort;
import containers.*;
import ships.*;
import java.util.*;


/**
* Port is the class of ports. Implements the port interface.
* 
* @author Ali Alperen Sönmez
* 
*/
public class Port implements IPort{
	
	/**
	 * ID of the Port
	 */
	private int ID;
	
	/**
	 * X coordinate of the Port
	 */
	private double X;
	
	/**
	 * Y coordinate of the Port
	 */
	private double Y;
	
	/**
	 * Number of BasicContainers that the port has
	 */
	private int nofBasic;
	
	/**
	 * Number of HeavyContainers that the port has
	 */
	private int nofHeavy;
	
	/**
	 * Number of RefrigeratedContainers that the port has
	 */
	private int nofRefrigerated;
	
	/**
	 * Number of LiquidContainers that the port has
	 */
	private int nofLiquid;
	
	/**
	 * ArrayList of all containers that the port has
	 */
	ArrayList<Container> containers = new ArrayList<Container>();
	
	/**
	 * ArrayList of all BasicContainers that the port has
	 */
	ArrayList<Container> basics = new ArrayList<Container>();
	
	/**
	 * ArrayList of all HeavyContainers that the port has
	 */
	ArrayList<Container> heavies = new ArrayList<Container>();
	
	/**
	 * ArrayList of all RefrigeratedContainers that the port has
	 */
	ArrayList<Container> refrigerateds = new ArrayList<Container>();
	
	/**
	 * ArrayList of all LiquidContainers that the port has
	 */
	ArrayList<Container> liquids = new ArrayList<Container>();
	
	/**
	 * ArrayList of the ships that have visited the port
	 */
	ArrayList<Ship> history = new ArrayList<Ship>();
	
	/**
	 * ArrayList of the ships that are currently at the port
	 */
	ArrayList<Ship> current = new ArrayList<Ship>();
	
	/**
	 * <p>Constructor of the Port
	 * @param ID ID of the port
	 * @param X The X coordinate of the port.
	 * @param Y The Y coordinate of the port.
	 */
	public Port(int id, double X, double Y){
		ID = id;
		this.X = X;
		this.Y = Y;
	}
	
	/**
	 * <p>A method for adding Ship to the Port. Overrides IPort.java's method.
	 * @param s The incoming ship.
	 */
	@Override
	public void incomingShip(Ship s) {
		if (!history.contains(s)) {
			history.add(s);
		}
		current.add(s);
	}
	
	/**
	 * <p>A method for removing Ship from the Port. Overrides IPort.java's method.
	 * @param s The outgoing ship.
	 */
	@Override
	public void outgoingShip(Ship s) {
		current.remove(s);
	}
	
	/**
	 * <p>A method for calculating the distance between two ports.
	 * @param other The other port
	 * @return double Distance
	 */
	public double getDistance(Port other) {
		return Math.pow(Math.pow(other.getX()-X,2)+Math.pow(other.getY()-Y, 2),0.5);
				
	}
	
	/**
	 * <p>Getter for the X
	 * @return double X
	 */
	public double getX() {
		return X;
	}
	
	/**
	 * <p>Getter for the Y
	 * @return double Y
	 */
	public double getY() {
		return Y;
	}
	
	/**
	 * <p>Getter for the ID
	 * @return int ID
	 */
	public int getId() {
		return ID;
	}
	
	/**
	 * <p>A method for adding container to the Port.
	 * @param container The container to add.
	 */
	public void addContainer(Container container) {
		containers.add(container);
		if (container instanceof BasicContainer) {
			nofBasic+=1;
			basics.add(container);
		}
		
		else if (container instanceof RefrigeratedContainer) {
			nofRefrigerated+=1;
			refrigerateds.add(container);
		}
		
		else if (container instanceof LiquidContainer) {
			nofLiquid+=1;
			liquids.add(container);
		}
		
		else if (container instanceof HeavyContainer) {
			nofHeavy+=1;
			heavies.add(container);
		}
	}
	
	/**
	 * <p>A method for removing container from the Port.
	 * @param container The container to remove.
	 */
	public void removeContainer(Container container) {
		containers.remove(container);
		if (container instanceof BasicContainer) {
			nofBasic-=1;
			basics.remove(container);
		}
		
		else if (container instanceof RefrigeratedContainer) {
			nofRefrigerated-=1;
			refrigerateds.remove(container);
		}
		
		else if (container instanceof LiquidContainer) {
			nofLiquid-=1;
			liquids.remove(container);
		}
		
		else if (container instanceof HeavyContainer) {
			nofHeavy-=1;
			heavies.remove(container);
		}
	}
	
	/**
	 * <p>Getter for containers. Sorts them first.
	 * @return Sorted ArrayList containers
	 */
	public ArrayList<Container> getContainers() {
		Collections.sort(containers, new CompareContainer());
		return containers;
	}
	
	/**
	 * <p>Getter for ships currently in the port. Sorts them first.
	 * @return Sorted ArrayList current
	 */
	public ArrayList<Ship> getCurrent() {
		Collections.sort(current,new CompareShip());
		return current;
	}
	
	/**
	 * <p>Getter for nofBasic
	 * @return int nofBasic
	 */
	public int getNofBasic() {
		return nofBasic;
	}
	
	/**
	 * <p>Getter for nofHeavy
	 * @return int nofHeavy
	 */
	public int getNofHeavy() {
		return nofHeavy;
	}
	
	/**
	 * <p>Getter for nofLiquid
	 * @return int nofliquid
	 */
	public int getNofLiquid() {
		return nofLiquid;
	}
	
	/**
	 * <p>Getter for nofRefrigerated
	 * @return int nofRefrigerated
	 */
	public int getNofRefrigerated() {
		return nofRefrigerated;
	}
	
	/**
	 * <p>Getter for basics. Sorts them first.
	 * @return Sorted ArrayList basics
	 */
	public ArrayList<Container> getBasics() {
		Collections.sort(basics, new CompareContainer());
		return basics;
	}
	
	/**
	 * <p>Getter for refrigerateds. Sorts them first.
	 * @return Sorted ArrayList refrigerateds
	 */
	public ArrayList<Container> getRefrigerateds() {
		Collections.sort(refrigerateds, new CompareContainer());
		return refrigerateds;
	}
	
	/**
	 * <p>Getter for liquids. Sorts them first.
	 * @return Sorted ArrayList liquids
	 */
	public ArrayList<Container> getLiquids() {
		Collections.sort(liquids, new CompareContainer());
		return liquids;
	}
	
	/**
	 * <p>Getter for heavies. Sorts them first.
	 * @return Sorted ArrayList heavies
	 */
	public ArrayList<Container> getHeavies() {
		Collections.sort(heavies, new CompareContainer());
		return heavies;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

