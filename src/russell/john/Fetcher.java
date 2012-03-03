package russell.john;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * This class downloads the feed.  Since google reader limits results to 1000 entries per request, the fetcher has a public Next() function which retrieves the next 1000 results.
 * @author John
 *
 */
public class Fetcher
{
	private String feedUrl;
	
	private StringBuilder data;
	private Boolean hasNext;
	private String continuation;		
	
	protected Fetcher()
	{
		
	}
	
	public Fetcher(String feed, String start) throws MalformedURLException, UnsupportedEncodingException, IOException
	{
		this.feedUrl = feed;
		// Download the feed
		data = downloadFeed(start);
		
		// Parse the feed
		
		// Store the continuation string and/or set the hasNext
	}
	
	/**
	 * Checks to see if there are more items which need to be returned.  
	 * @return true if there is a continuation, false if there is not.
	 */
	public boolean hasNext()
	{
		return false;
	}
	
	public StringBuilder Next()
	{
		return null;
	}
	
	private StringBuilder downloadFeed(String start) throws MalformedURLException, UnsupportedEncodingException, IOException
	{
		URL url;	
		URLConnection conn;
		BufferedReader reader;	
		StringBuilder stringData = new StringBuilder();
		if (start.contains("0"))		
			url = new URL(URLEncoder.encode("http://www.google.com/reader/api/0/stream/contents/feed/" + this.feedUrl + "?n=1000", "US-ASCII"));	
		else		
			url = new URL(URLEncoder.encode("http://www.google.com/reader/api/0/stream/contents/feed/" + this.feedUrl + "?n=1000&ot=" + start, "US-ASCII"));
		
		conn = url.openConnection();
		reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));		
		while ((data.append(reader.readLine())) != null);
		
		return stringData;		
	}
	
	
}
