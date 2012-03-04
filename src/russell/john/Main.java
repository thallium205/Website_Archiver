package russell.john;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;

public class Main
{
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	
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
			LOG.info("The application has terminated gracefully.");
		}

		catch (MalformedURLException e)
		{
			LOG.log(Level.SEVERE, "The Fetcher has probably encountered an error.", e);
		}

		catch (IOException e)
		{
			LOG.log(Level.SEVERE, "The Fetcher has probably encountered an error.", e);
		}

		catch (JSONException e)
		{
			LOG.log(Level.SEVERE, "The Parser has probably encountered an error.", e);
		} 
		
		catch (URISyntaxException e)
		{
			LOG.log(Level.SEVERE, "The Fetcher has probably encountered an error.", e);
		} 
		
		catch (SQLException e)
		{
			LOG.log(Level.SEVERE, "The Database has probably encountered an error.", e);
		} 
		
		catch (ClassNotFoundException e)
		{
			LOG.log(Level.SEVERE, "The Database has probably encountered an error.", e);
		}
	}
}
