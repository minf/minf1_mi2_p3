package Rules;

public class Rule 
{
	
	protected Integer _option_id;
	protected Integer _referenced_option_id;
	protected Integer _whitelist;
	protected Integer _value;
	
	public Rule()
	{
		this._option_id = 0;
		this._referenced_option_id = 0;
		this._whitelist = 1;
		this._value = 0;
	}
	
	public boolean isAllowed()
	{
		return (((this._whitelist.equals(1)) && (this._value.equals(1))) || 
				((this._whitelist.equals(0)) && (this._value.equals(0))) );
	}
	
	public Integer getOptionID()
	{
		return this._option_id;
	}
	
	public void setOptionID(Integer id)
	{
		this._option_id = id;
	}

	public Integer getReferencedOptionID()
	{
		return this._referenced_option_id;
	}
	
	public void setReferencedOptionID(Integer id)
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
