package russell.john;

import org.json.JSONException;
import org.json.JSONObject;

public class Parser
{
	JSONObject data;
	protected Parser()
	{
		
	}
	
	public Parser (StringBuilder feed) throws JSONException
	{
		data = new JSONObject(feed.toString());	
	}
}
