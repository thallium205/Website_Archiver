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
	
	public AlternateType(String href, String type)
	{
		this.href = href;
		this.type = type;
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