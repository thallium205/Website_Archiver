package russell.john.domain;

import java.util.Date;

/**
 * This class represents the data that is returned with the stream but does not contain any relevant data to the feed itself
 * @author John
 *
 */
public class MetadataType
{
	String direction;
	String id;
	String title;
	String description;
	String continuation;	
	Date updated;
	
	public MetadataType()
	{
		direction = "";
		id = "";
		title = "";
		description = "";
		continuation = "";
		updated = null;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContinuation()
	{
		return continuation;
	}

	public void setContinuation(String continuation)
	{
		this.continuation = continuation;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}	
}