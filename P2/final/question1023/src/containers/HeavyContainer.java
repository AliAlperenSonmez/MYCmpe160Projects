
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
* HeavyContainer is a subclass of the abstract class Container.
* 
* @author Ali Alperen Sönmez
* 
*/

public class HeavyContainer extends Container{

	
	/**
	 * <p>Constructor of the HeavyContainer
	 * @param ID ID of the container
	 * @param weight Weight of the container
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	/**
	 * <p>Calculates the consumption of the container. Overrides the abstract method of its parent.
	 * @return a double indicating fuel consumption per KM
	 */
	@Override
	public double consumption() {
		return this.weight*3;
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

