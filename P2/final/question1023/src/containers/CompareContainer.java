package containers;
import java.util.*;

/**
* CompareContainer is used to sort containers with respect to their ID's.
* 
* @author Ali Alperen Sönmez
* 
*/

public class CompareContainer implements Comparator<Container>{
	
	/**
	 * <p>Compares the ID's of two containers.
	 * @param s1 container1
	 * @param s2 container2
	 * @return an integer which is the difference of the ID's of two containers
	 */
		public int compare(Container s1, Container s2) {
	        return (s1.getID() - s2.getID());
	    }
	}
