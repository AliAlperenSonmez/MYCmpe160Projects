
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
* BasicContainer is a subclass of the abstract class Container.
* 
* @author Ali Alperen Sönmez
* 
*/

public class BasicContainer extends Container{
	
	
	/**
	 * <p>Constructor of the BasicContainer
	 * @param ID ID of the container
	 * @param weight Weight of the container
	 */
	public BasicContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	
	/**
	 * <p>Calculates the consumption of the container. Overrides the abstract method of its parent.
	 * @return a double indicating fuel consumption per KM
	 */
	@Override
	public double consumption() {
		return this.weight*2.5;
	}
	
}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

