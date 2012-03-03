package russell.john;

public class Main
{

	/**
	 * This application takes 2 required parameters and 1 optional parameter to store a google reader feed into sqllite
	 * 
	 * @param args
	 * args[0] = the feed url
	 * args[1] = start time in unix timestamp, 0 for the very begining of the feed
	 * args[2] = (optional) pass in a database name to create one
	 */
	public static void main(String[] args)
	{			
		Fetcher fetcher = new Fetcher();
		
	}

}
