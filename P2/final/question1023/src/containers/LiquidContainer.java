
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;


/**
* LiquidContainer is a subclass of the HeavyContainer class.
* 
* @author Ali Alperen Sönmez
* 
*/

public class LiquidContainer extends HeavyContainer{
	
	/**
	 * <p>Constructor of the LiquidContainer
	 * @param ID ID of the container
	 * @param weight Weight of the container
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID,weight);
	}
	
	
	/**
	 * <p>Calculates the consumption of the container. Overrides the method of its parent.
	 * @return a double indicating fuel consumption per KM
	 */
	@Override
	public double consumption() {
		return this.weight*4;
	}
	
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

