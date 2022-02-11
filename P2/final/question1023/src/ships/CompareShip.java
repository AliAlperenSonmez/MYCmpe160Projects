package ships;
import java.util.*;

/**
* CompareShip is used to sort ships with respect to their ID's.
* 
* @author Ali Alperen Sönmez
* 
*/


public class CompareShip implements Comparator<Ship>{
	
	/**
	 * <p>Compares the ID's of two containers.
	 * @param s1 ship1
	 * @param s2 ship2
	 * @return an integer which is the difference of the ID's of two ships
	 */
	public int compare(Ship s1, Ship s2) {
        return (s1.getID() - s2.getID());
    }
}
