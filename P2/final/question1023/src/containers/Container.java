
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
* Container is an abstract class of containers.
* 
* @author Ali Alperen Sönmez
* 
*/

public abstract class Container {
	/**
	 * ID of the container
	 */
	protected int ID;
	
	/**
	 * Weight of the container
	 */
	protected int weight;
	
	
	/**
	 * <p>Constructor of the Container
	 * @param ID ID of the container
	 * @param weight Weight of the container
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
	}
	
	/**
	 * <p>Abstract method for calculating the consumption of the container
	 * @return a double indicating fuel consumption per KM
	 */
	public abstract double consumption();
	
	/**
	 * <p>Checks if two containers are identical 
	 * @param other other container
	 * @return boolean true if they are identical, false if not.
	 */
	public boolean equals(Container other) {
		return this==other;
	}
	
	/**
	 * <pGetter for weight
	 * @return weight of the container.
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * <p>Getter for ID
	 * @return ID of the container
	 */
	public int getID() {
		return ID;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

