package russell.john;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONException;

import russell.john.domain.ItemType;

public class Main
{
	static private Fetcher fetcher;

	/**
	 * This application takes 2 required parameters and 1 optional parameter to
	 * store a google reader feed into sqllite
	 * 
	 * @param args
	 *            args[0] = the feed url args[1] = start time in unix timestamp,
	 *            0 for the very begining of the feed args[2] = (optional) pass
	 *            in a database name to create one
	 */
	public static void main(String[] args)
	{
		ArrayList<ItemType> items = new ArrayList<ItemType>();

		fetcher = new Fetcher(args[0], args[1]);
		try
		{
			while (fetcher.hasNext())
			{
				items.addAll(fetcher.next());
			}
		}

		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (JSONException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
