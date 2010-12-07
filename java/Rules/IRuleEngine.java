package Rules;
import java.util.*;

/**
 * @author jdepke
 * @version 1.0
 * @created 02-Dez-2010 14:47:40
 */
public interface IRuleEngine {

	/**
	 * 
	 * @param list
	 */
	public ArrayList<String> calculate(List<String> list);

}