package Rules;
import java.util.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author jdepke
 * @version 1.0
 * @created 02-Dez-2010 14:47:41
 */
public class RuleEngine implements IRuleEngine 
{
	//private static String _xsd_filename = "src/rules.xsd";
	private static String _xml_filename = "ruleset.xml";
	
	private File _xml_file;
	private ArrayList<Rule> _ruleset;

	
	public RuleEngine()
	{
		this._ruleset = new ArrayList<Rule>();
		this._xml_file = new File(RuleEngine._xml_filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try
		{
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(this._xml_file);
			doc.getDocumentElement().normalize();
			NodeList ruleNodes = doc.getElementsByTagName("rule");
			for(int i = 0; i < ruleNodes.getLength(); i++)
			{
			    Node tmpNode = ruleNodes.item(i);
			    if (tmpNode.getNodeType() == Node.ELEMENT_NODE) 
			    {
			    	Rule r = new Rule();
				    Element tmpElement = (Element) tmpNode;
				    NodeList tmpNodeList = tmpElement.getElementsByTagName("option_id");
				    Element fstNmElmnt = (Element) tmpNodeList.item(0);
				    NodeList fstNm = fstNmElmnt.getChildNodes();
				    r.setOptionID(new String(((Node) fstNm.item(0)).getNodeValue()));

				    tmpNodeList = tmpElement.getElementsByTagName("referenced_option_id");
				    fstNmElmnt = (Element) tmpNodeList.item(0);
				    fstNm = fstNmElmnt.getChildNodes();
				    r.setReferencedOptionID(new String(((Node) fstNm.item(0)).getNodeValue()));
				    
				    tmpNodeList = tmpElement.getElementsByTagName("whitelist");
				    fstNmElmnt = (Element) tmpNodeList.item(0);
				    fstNm = fstNmElmnt.getChildNodes();
				    r.setWhitelistState(new Integer(((Node) fstNm.item(0)).getNodeValue()));
				    
				    tmpNodeList = tmpElement.getElementsByTagName("value");
				    fstNmElmnt = (Element) tmpNodeList.item(0);
				    fstNm = fstNmElmnt.getChildNodes();
				    r.setValue(new Integer(((Node) fstNm.item(0)).getNodeValue()));
				    
				    this._ruleset.add(r);
			    }
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//System.out.print(this._ruleset);
	}
	
	private ArrayList<Rule> getAllowedOptionsByOptionID(String id)
	{
		ArrayList<Rule> output = new ArrayList<Rule>();
		for(int i = 0; i < this._ruleset.size(); i++)
		{
			if(  (((Rule)this._ruleset.get(i)).getOptionID().equals(id)) &&
				 (((Rule)this._ruleset.get(i)).isAllowed() )	)
			{
				output.add(((Rule)this._ruleset.get(i)));
			}
		}
		return output;
	}
	
	private ArrayList<Rule> mergeLists(ArrayList<Rule> r1, ArrayList<Rule> r2)
	{
		ArrayList<Rule> output = new ArrayList<Rule>();
		for(int i = 0; i < r1.size(); i++)
		{
			String rOID = r1.get(i).getReferencedOptionID();
			for(int k = 0; k < r2.size(); k++)
			{
				if(r2.get(k).getReferencedOptionID().equals(rOID))
				{
					output.add(r1.get(i));
					break;
				}
			}
		}
		return output;		
	}
	
	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param list
	 */
	public ArrayList<String> calculate(List<String> list)
	{
		ArrayList<String> output = new ArrayList<String>();
		ArrayList<Rule> tmpList = null;
		for(int i = 0; i < list.size(); i++)
		{
			if(tmpList != null)
			{
				tmpList = this.mergeLists(tmpList, this.getAllowedOptionsByOptionID(list.get(i)));
			}
			else
			{
				tmpList = this.getAllowedOptionsByOptionID(list.get(i));	
			}			
		}
		for(int i = 0; i < tmpList.size(); i++)
		{
			output.add(tmpList.get(i)._referenced_option_id);
		}
		return output;
	}

	public ArrayList<String> getAll() {
		ArrayList<String> output = new ArrayList<String>();

		for(int i = 0; i < this._ruleset.size(); i++)
		{
			output.add(this._ruleset.get(i).getOptionID());
		}
		return output;
	}

	
	
	public static void main(String [ ] args)
	{
		RuleEngine re = new RuleEngine();
		ArrayList<String>  input = new ArrayList<String>();
		//input.add(new String("1"));
		input.add(new String("2")); 
		//input.add(new String("3"));
		System.out.print(re.calculate(input));
	}
	

	
}
