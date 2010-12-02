package Rules;


/**
 * @author jdepke
 * @version 1.0
 * @created 02-Dez-2010 14:47:38
 */
public class Category {

	private int id;
	private int parent_id;
	private String name;
	private String description;
	public Option m_Option;
	public Category m_Category;

	public Category(){

	}

	public void finalize() throws Throwable {

	}

}