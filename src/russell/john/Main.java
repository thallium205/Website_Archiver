package russell.john;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;

import russell.john.domain.ItemType;

public class Main
{
	static private Fetcher fetcher;

	/**
	 * This application takes 3 required parameters and 0 optional parameter to
	 * store a google reader feed into sqllite
	 * 
	 * @param args
	 *            args[0] = the feed url args[1] = start time in unix timestamp,
	 *            0 for the very begining of the feed args[2] = pass
	 *            in the path and name to a database. ex: /home/john/mydatabase.db or c:/users/john/desktop/mydatabase.db If it doesn't exist, it will create one there
	 *             */
	public static void main(String[] args)
	{
		fetcher = new Fetcher(args[0], args[1]);		
		
		try
		{
			Database db = new Database(args[2]);
			
			while (fetcher.hasNext())
			{
				db.addItems(fetcher.next());				
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
		
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
