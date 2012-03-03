package russell.john.domain;

/**
 * An object representing an Alternate type 
 * @author John
 *
 */
public class AlternateType
{
	String href;
	String type;
	
	protected AlternateType()
	{
		
	}

	public String getHref()
	{
		return href;
	}

	public void setHref(String href)
	{
		this.href = href;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}	
}