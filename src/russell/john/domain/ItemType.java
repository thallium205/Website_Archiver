package russell.john.domain;

import java.util.ArrayList;
import java.util.Date;

/**
 * The information object returned from a google reader feed
 * @author John
 *
 */
public class ItemType
{
	Integer idItem; // Primary key
	Boolean isReadStateLocked;
	Date crawlTime;
	Date timeStamp;
	String id;
	String title;
	Date published;
	Date updated;
	String direction;
	String content;
	String author;
	String streamId;
	String streamTitle;
	String htmlUrl;
	Integer Category_idCategory; // Foreign key
	Integer Alternate_idAlternate; // Foreign key
	
	ArrayList<CategoryType> categories;
	ArrayList<AlternateType> alternates;
	ArrayList<LikingUserType> likingUsers;
	ArrayList<CommentType> comments;
	ArrayList<AnnotationType> annotations;
	
	public ItemType()
	{
		isReadStateLocked = false;
		crawlTime = new Date();
		timeStamp = new Date();
		id = "";
		title = "";
		published = new Date();
		updated = new Date();
		direction = "";
		content = "";
		author = "";
		streamId = "";
		streamTitle = "";
		htmlUrl = "";		
	}

	/**
	 * Primary key
	 * @return
	 */
	public Integer getIdItem()
	{
		return idItem;
	}

	/**
	 * Primary key 
	 * @param idItem
	 */
	public void setIdItem(Integer idItem)
	{
		this.idItem = idItem;
	}

	public Boolean getIsReadStateLocked()
	{
		return isReadStateLocked;
	}

	public void setIsReadStateLocked(Boolean isReadStateLocked)
	{
		this.isReadStateLocked = isReadStateLocked;
	}

	public Date getCrawlTime()
	{
		return crawlTime;
	}

	public void setCrawlTime(Date crawlTime)
	{
		this.crawlTime = crawlTime;
	}

	public Date getTimeStamp()
	{
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp)
	{
		this.timeStamp = timeStamp;
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

	public Date getPublished()
	{
		return published;
	}

	public void setPublished(Date published)
	{
		this.published = published;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getStreamId()
	{
		return streamId;
	}

	public void setStreamId(String streamId)
	{
		this.streamId = streamId;
	}

	public String getStreamTitle()
	{
		return streamTitle;
	}

	public void setStreamTitle(String streamTitle)
	{
		this.streamTitle = streamTitle;
	}

	public String getHtmlUrl()
	{
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl)
	{
		this.htmlUrl = htmlUrl;
	}

	public ArrayList<CategoryType> getCategories()
	{
		return categories;
	}

	public void setCategories(ArrayList<CategoryType> categories)
	{
		this.categories = categories;
	}

	public ArrayList<AlternateType> getAlternates()
	{
		return alternates;
	}

	public void setAlternates(ArrayList<AlternateType> alternates)
	{
		this.alternates = alternates;
	}

	public ArrayList<LikingUserType> getLikingUsers()
	{
		return likingUsers;
	}

	public void setLikingUsers(ArrayList<LikingUserType> likingUsers)
	{
		this.likingUsers = likingUsers;
	}

	public ArrayList<CommentType> getComments()
	{
		return comments;
	}

	public void setComments(ArrayList<CommentType> comments)
	{
		this.comments = comments;
	}

	public ArrayList<AnnotationType> getAnnotations()
	{
		return annotations;
	}

	public void setAnnotations(ArrayList<AnnotationType> annotations)
	{
		this.annotations = annotations;
	}

	public Integer getCategory_idCategory()
	{
		return Category_idCategory;
	}

	public void setCategory_idCategory(Integer category_idCategory)
	{
		Category_idCategory = category_idCategory;
	}

	public Integer getAlternate_idAlternate()
	{
		return Alternate_idAlternate;
	}

	public void setAlternate_idAlternate(Integer alternate_idAlternate)
	{
		Alternate_idAlternate = alternate_idAlternate;
	}	
}