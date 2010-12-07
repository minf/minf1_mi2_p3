package Rules;

public class Rule 
{
	
	protected String _option_id;
	protected String _referenced_option_id;
	protected Integer _whitelist;
	protected Integer _value;
	
	public Rule()
	{
		this._option_id = "";
		this._referenced_option_id = "";
		this._whitelist = 1;
		this._value = 0;
	}
	
	public boolean isAllowed()
	{
		return (((this._whitelist.equals(1)) && (this._value.equals(1))) || 
				((this._whitelist.equals(0)) && (this._value.equals(0))) );
	}
	
	public String getOptionID()
	{
		return this._option_id;
	}
	
	public void setOptionID(String id)
	{
		this._option_id = id;
	}

	public String getReferencedOptionID()
	{
		return this._referenced_option_id;
	}
	
	public void setReferencedOptionID(String id)
	{
		this._referenced_option_id = id;
	}
	
	public Integer getWhitelistState()
	{
		return this._whitelist;
	}
	
	public void setWhitelistState(Integer state)
	{
		this._whitelist = state; 
	}

	public Integer getValue()
	{
		return this._value;
	}
	
	public void setValue(Integer val)
	{
		this._value = val;
	}

	public String toString()
	{
		String output = "\n\n";
		output += "OptionID: " + this._option_id + "\n"; 
		output += "ReferencedOptionID: " + this._referenced_option_id + "\n"; 
		output += "Whitelist: " + this._whitelist + "\n";
		output += "Value: " + this._value + "\n\n"; 
		return output;
	}
}
