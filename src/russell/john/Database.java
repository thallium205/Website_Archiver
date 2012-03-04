package russell.john;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import russell.john.domain.AlternateType;
import russell.john.domain.CategoryType;
import russell.john.domain.ItemType;

public class Database
{
	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");

	String filePath;
	Connection conn;

	/**
	 * The path and name of the database to be modified/created
	 * 
	 * @param filePath
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Database(String filePath) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		this.filePath = filePath;
		if (!new File(filePath).exists())
		{
			conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
			buildDatabase();
		} else
		{
			conn = DriverManager.getConnection("jdbc:sqlite:" + filePath);
		}
	}

	public void addItems(ArrayList<ItemType> items) throws SQLException
	{
		String query;
		ItemType item = null;;
		CategoryType cat = null;
		AlternateType alt = null;
		
		PreparedStatement statement;
		

		for (Iterator<ItemType> itemiter = items.iterator(); itemiter.hasNext();)
		{
			item = itemiter.next();
			
			// Insert Category first due to many to one relationship to Item		
			query = "INSERT OR IGNORE INTO Category (category) VALUES (?)";
			for (Iterator<CategoryType> catiter = item.getCategories().iterator(); catiter.hasNext();)
			{
				statement = conn.prepareStatement(query);
				cat = catiter.next();
				statement.setString(1, cat.getCategory());
				statement.execute();
			}
			
			// Insert Alternate first due to many to one relationship
			query = "INSERT OR IGNORE INTO Alternate (href, type) VALUES (?,?)";
			for (Iterator<AlternateType> altiter = item.getAlternates().iterator(); altiter.hasNext();)
			{
				statement = conn.prepareStatement(query);
				alt = altiter.next();
				statement.setString(1, alt.getHref());
				statement.setString(2, alt.getType());
				statement.execute();
			}
			
			// Insert Item 
			query = "INSERT INTO Item (isreadstatelocked, crawltime, timestamp, id, title, published, updated, direction, content, author, streamid, streamtitle, htmlurl, Category_category, Alternate_href) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, item.getIsReadStateLocked() ? 1 : 0);
			statement.setString(2, DATEFORMAT.format(item.getCrawlTime()));
			statement.setString(3, DATEFORMAT.format(item.getTimeStamp()));
			statement.setString(4, item.getId());
			statement.setString(5, item.getTitle());
			statement.setString(6, DATEFORMAT.format(item.getPublished()));
			statement.setString(7, DATEFORMAT.format(item.getUpdated()));
			statement.setString(8, item.getDirection());
			statement.setString(9, item.getContent());
			statement.setString(10, item.getAuthor());
			statement.setString(11, item.getStreamId());
			statement.setString(12, item.getStreamTitle());
			statement.setString(13, item.getHtmlUrl());
			// Set foreign keys
			statement.setString(14, cat.getCategory());
			statement.setString(15, alt.getHref());			
			statement.execute();
			item.setIdItem(statement.getGeneratedKeys().getInt(1));
			
			// One to many relationships to Item are added here
			// TODO LikingUser

			// TODO Comment

			// TODO Annotation
		}

	}

	private void buildDatabase() throws SQLException
	{
		Statement statement = conn.createStatement();
		statement
				.executeUpdate("CREATE TABLE Category (category TEXT, PRIMARY KEY(category))");
		statement
				.executeUpdate("CREATE TABLE Alternate (href TEXT, type TEXT, Item_idItem INTEGER, PRIMARY KEY (href))");
		statement
				.executeUpdate("CREATE TABLE Item (idItem INTEGER, isreadstatelocked INTEGER, crawltime TEXT, timestamp TEXT, id INTEGER, title TEXT, published TEXT, updated TEXT, direction TEXT, content TEXT, author TEXT, streamid TEXT, streamtitle TEXT, htmlurl TEXT, Category_category TEXT, Alternate_href TEXT, PRIMARY KEY(idItem), FOREIGN KEY (Category_category) REFERENCES Category(category), FOREIGN KEY (Alternate_href) REFERENCES Alternate(href))");
		statement
				.executeUpdate("CREATE TABLE LikingUser (idLikingUser INTEGER, Item_idItem INTEGER, PRIMARY KEY (idLikingUser), FOREIGN KEY (Item_idItem) REFERENCES Item(idItem))");
		statement
				.executeUpdate("CREATE TABLE Comment (idComment INTEGER, Item_idItem INTEGER, PRIMARY KEY (idComment), FOREIGN KEY (Item_idItem) REFERENCES Item(idItem))");
		statement
				.executeUpdate("CREATE TABLE Annotation (idAnnotation INTEGER, Item_idItem INTEGER, PRIMARY KEY (idAnnotation), FOREIGN KEY (Item_idItem) REFERENCES Item(idItem))");
	}

}
