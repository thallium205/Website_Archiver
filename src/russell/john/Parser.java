package russell.john;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import russell.john.domain.AlternateType;
import russell.john.domain.CategoryType;
import russell.john.domain.ItemType;
import russell.john.domain.MetadataType;

public class Parser
{
	private static final Logger LOG = Logger.getLogger(Parser.class.getName());
	
	private JSONObject data;
	private MetadataType metadata;
	private ArrayList<ItemType> items;
	
	protected Parser()
	{
		
	}
	
	public Parser (StringBuilder feed) throws JSONException
	{		
		items = new ArrayList<ItemType>();
		data = new JSONObject(feed.toString());
		
		// Collect the metadata first		
		metadata = new MetadataType();
		
		if (data.has("direction"))
			metadata.setDirection(data.getString("direction"));
		if (data.has("id"))
			metadata.setId(data.getString("id"));
		if (data.has("title"))
			metadata.setTitle(data.getString("title"));
		if (data.has("continuation"))
			metadata.setContinuation(data.getString("continuation"));	
		
		// Collect the items
		JSONArray itemsArray = data.getJSONArray("items");
		ItemType item;
		for (int i = 0; i < itemsArray.length(); i++)
		{
			item = new ItemType();
			
			if (itemsArray.getJSONObject(i).has("crawlTimeMsec"))
				item.setCrawlTime(itemsArray.getJSONObject(i).getLong("crawlTimeMsec"));
			if (itemsArray.getJSONObject(i).has("timestampUsec"))
				item.setTimeStamp(itemsArray.getJSONObject(i).getLong("timestampUsec"));
			if (itemsArray.getJSONObject(i).has("id"))
				item.setId(itemsArray.getJSONObject(i).getString("id"));
			
			// Get categories
			JSONArray catArray = itemsArray.getJSONObject(i).getJSONArray("categories");
			ArrayList<CategoryType> categories = new ArrayList<CategoryType>();
			for (int j = 0; j < catArray.length(); j++)
			{				
				categories.add(new CategoryType(catArray.getString(j)));				
			}
			item.setCategories(categories);
			 
			// Continue getting base item types
			if (itemsArray.getJSONObject(i).has("title"))
				item.setTitle(itemsArray.getJSONObject(i).getString("title"));
			if (itemsArray.getJSONObject(i).has("published"))
				item.setPublished(itemsArray.getJSONObject(i).getLong("published"));
			if (itemsArray.getJSONObject(i).has("updated"))
				item.setUpdated(itemsArray.getJSONObject(i).getLong("updated"));
			
			// Get alternate array
			JSONArray altArray = itemsArray.getJSONObject(i).getJSONArray("alternate");
			ArrayList<AlternateType> alternates = new ArrayList<AlternateType>();
			for (int j = 0; j < altArray.length(); j++)
			{
				alternates.add(new AlternateType(altArray.getJSONObject(j).getString("href"), altArray.getJSONObject(j).getString("type")));
			}			
			item.setAlternates(alternates);
			
			// Continue getting base item types			
			// Sometimes google reader returns "summary" tags and sometimes it returns "content" tags
			if (itemsArray.getJSONObject(i).has("summary"))
			{
				if (itemsArray.getJSONObject(i).getJSONObject("summary").has("direction"))
					item.setDirection(itemsArray.getJSONObject(i).getJSONObject("summary").getString("direction"));
				if (itemsArray.getJSONObject(i).getJSONObject("summary").has("content"))
					item.setContent(itemsArray.getJSONObject(i).getJSONObject("summary").getString("content"));				
			}
			
			else if (itemsArray.getJSONObject(i).has("content"))
			{
				if (itemsArray.getJSONObject(i).getJSONObject("content").has("direction"))
					item.setDirection(itemsArray.getJSONObject(i).getJSONObject("content").getString("direction"));
				if (itemsArray.getJSONObject(i).getJSONObject("content").has("content"))
					item.setContent(itemsArray.getJSONObject(i).getJSONObject("content").getString("content"));	
			}
			
			// Get liking users array TODO
			
			// Get comments array TODO
			
			// Get annotations array TODO
			
			// Continue getting base item types
			if (itemsArray.getJSONObject(i).has("origin"))
			{
				if (itemsArray.getJSONObject(i).getJSONObject("origin").has("streamId"))
					item.setStreamId(itemsArray.getJSONObject(i).getJSONObject("origin").getString("streamId"));
				if (itemsArray.getJSONObject(i).getJSONObject("origin").has("title"))
					item.setStreamTitle(itemsArray.getJSONObject(i).getJSONObject("origin").getString("title"));
				if (itemsArray.getJSONObject(i).getJSONObject("origin").has("htmlUrl"))
					item.setHtmlUrl(itemsArray.getJSONObject(i).getJSONObject("origin").getString("htmlUrl"));				
			}
			
			// Store the ItemType to the arraylist
			items.add(item);
		}
	}
	
	/**
	 * Returns the metadata of the parsed feed
	 */
	public MetadataType getMetadata()
	{
		return metadata;
	}
	
	public ArrayList<ItemType> getItems()
	{
		return items;
	}
}
