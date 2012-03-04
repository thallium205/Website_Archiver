package russell.john;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.json.JSONException;

import russell.john.domain.ItemType;
import russell.john.domain.MetadataType;

/**
 * This class downloads the feed. Since google reader limits results to 1000
 * entries per request, the fetcher has a public Next() function which retrieves
 * the next 1000 results.
 * 
 * @author John
 * 
 */
public class Fetcher
{
	private static final Logger LOG = Logger.getLogger(Fetcher.class.getName());	
	
	private String feedUrl;
	private String startTime;
	private MetadataType metadata;

	private StringBuilder data;

	protected Fetcher()
	{

	}

	public Fetcher(String feed, String start)
	{
		this.feedUrl = feed;
		this.startTime = start;
		this.metadata = null;
	}

	/**
	 * Checks to see if there are more items which need to be returned. Metadata
	 * is null if it is the first time it has been called.
	 * 
	 * @return true if there is a continuation, false if there is not.
	 */
	public boolean hasNext()
	{
		if (metadata == null)
			return true;
		else if (metadata.getContinuation().isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * Downloads the next set of items from Google Reader
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws JSONException
	 * @throws URISyntaxException
	 */
	public ArrayList<ItemType> next() throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException, URISyntaxException
	{
		// Download the feed
		LOG.info("Downloading at most 1000 Items...");
		data = downloadFeed();

		// Parse the feed
		LOG.info("Parsing 1000 Items...");
		Parser parser = new Parser(data);

		// Store the metadata
		LOG.info("Storing the metadata...");
		metadata = parser.getMetadata();

		// return the items
		LOG.info("Parser done.");
		return parser.getItems();
	}

	/**
	 * If the feed has more than 1000 items, it will have a continuation
	 * parameter that is to be used to collect the next items.
	 * 
	 * @param hasContinuation
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private StringBuilder downloadFeed() throws MalformedURLException, UnsupportedEncodingException, IOException, URISyntaxException
	{		
		URL url;
		URLConnection conn;
		BufferedReader reader;
		StringBuilder stringData = new StringBuilder();

		if (startTime.contains("0"))
		{
			if (metadata != null && !metadata.getContinuation().isEmpty())
			{
				url = new URI("http", "www.google.com", "/reader/api/0/stream/contents/feed/" + this.feedUrl, "n=1000&c="
						+ metadata.getContinuation(), null).toURL();
			} else
			{
				url = new URI("http", "www.google.com", "/reader/api/0/stream/contents/feed/" + this.feedUrl, "n=1000", null).toURL();
			}
		}

		else
		{
			if (metadata != null && !metadata.getContinuation().isEmpty())
			{
				url = new URI("http", "www.google.com", "/reader/api/0/stream/contents/feed/" + this.feedUrl, "n=1000&ot=" + startTime + "&c="
						+ metadata.getContinuation(), null).toURL();
			} else
			{
				url = new URI("http", "www.google.com", "/reader/api/0/stream/contents/feed/" + this.feedUrl, "n=1000&ot=" + startTime, null).toURL();
			}
		}

		conn = url.openConnection();
		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null)
		{
			stringData.append(line);
		}

		return stringData;
	}
}
