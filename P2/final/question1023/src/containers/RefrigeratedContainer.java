
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

/**
* RefrigeratedContainer is a subclass of the HeavyContainer class.
* 
* @author Ali Alperen Sönmez
* 
*/

public class RefrigeratedContainer extends HeavyContainer{
	
	/**
	 * <p>Constructor of the RefrigeratedContainer
	 * @param ID ID of the container
	 * @param weight Weight of the container
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	
	/**
	 * <p>Calculates the consumption of the container. Overrides the method of its parent.
	 * @return a double indicating fuel consumption per KM
	 */
	@Override
	public double consumption() {
		return this.weight*5;
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

