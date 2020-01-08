package edu.edgewood.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


import edu.edgewood.model.Posting;
import edu.edgewood.model.Tag;
import edu.edgewood.model.User;

/**Contains all of the methods that directly interact with the database
 */
public class MainDao extends AbstractJdbcDao{

	/*returns a list of all postings in the database*/
	public List<Posting> getAll() throws Exception{
		String sql = "select * from posting order by created_date desc";	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<Posting> postings = new ArrayList<>();
			
			while(rs.next()) {
				Posting posting = createPosting(rs);
				postings.add(posting);
			}
			return postings;
		}finally {
			releaseResources(conn, stmt, rs);
		}
	}
	
	/*returns a list of all the postings in the database with tags that match the name provided*/
	public List<Posting> getPostingByTag(String tagName) throws Exception{
		String sql = "select " + 
				"p.id," + 
				"p.created_date," + 
				"p.created_by," + 
				"p.title," + 
				"p.body," + 
				"p.last_modified," + 
				"p.last_modified_by," + 
				"t.tag_name " +
				"from posting p inner join tags t on t.posting_id = p.id where t.tag_name = ?";	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tagName);
			rs = stmt.executeQuery();
			List<Posting> postings = new ArrayList<>();
			
			while(rs.next()) {
				Posting posting = createPosting(rs);
				postings.add(posting);
			}
			
			return postings;
		}finally {
			releaseResources(conn, stmt, rs);
		}
		
	}
	
	//returns the tag that matches the name provided
	public Tag getTagByName(String tagName) throws Exception{
		String sql = "select * from tag where tag_name = ?";	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tagName);
			rs = stmt.executeQuery();
			Tag t = new Tag();
			if(rs.next()) {
				t = createTag(rs);
			}
			return t;
			
			
		}finally {
			releaseResources(conn, stmt, rs);
		}
		
	}
	
	//returns the user that matches the user id
	public User getUserById(String id) throws Exception {
		String sql = "select * from user where id = ?";	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			User u = new User();
			
			if(rs.next()) {
				u = createUser(rs);
			}
			return u;
			
			
			
		}finally {
			releaseResources(conn, stmt, rs);
		}
		
	}
	
	//returns the user who's userName and password matches those provided
	public User getUserByLogin(String userName, String password) throws Exception{
		String sql = "select * from user where id = ? and password = ?";	  
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			rs = stmt.executeQuery();			
			User u = new User();
			if(rs.next()) {
				u = createUser(rs);
			}
			return u;                                 

			
		}finally {
			releaseResources(conn, stmt, rs);
		}
	}
	
	//Instantiates and returns an object of the Posting class given a result set from a db query
	private Posting createPosting(ResultSet rs) throws Exception{
		String id = rs.getString("id");
		LocalDate createdDate = rs.getDate("created_date").toLocalDate();
		User createdBy = this.getUserById(rs.getString("created_by"));
		String title = rs.getString("title");
		String body = rs.getString("body");
		LocalDateTime lastModified = null;
		if(rs.getTimestamp("last_modified") != null) {
			lastModified = rs.getTimestamp("last_modified").toLocalDateTime();
		};
		
		User lastModifiedBy = null;
		if(rs.getString("last_modified_by") != null) {
			lastModifiedBy = this.getUserById(rs.getString("last_modified_by"));
		};
		
		List<Tag> tags = getTagsByPostId(id);
		
		
		Posting posting = new Posting();
		posting.setId(id);
		posting.setCreatedDate(createdDate);
		posting.setCreatedBy(createdBy);
		posting.setTitle(title);
		posting.setBody(body);
		posting.setLastModified(lastModified);
		posting.setLastModifiedBy(lastModifiedBy);
		posting.setTags(tags);
		
		return posting;
	}
	
	//returns a list of tags that are associated with the given posting id
	public List<Tag> getTagsByPostId(String id) throws Exception{
		String sql = "select * from tags where posting_id = ?";	
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			List<Tag> tags = new ArrayList<>();
			
			while(rs.next()) {
				Tag tag = createTag(rs);
				tags.add(tag);
			}
			return tags;
		}finally {
			releaseResources(conn, stmt, rs);
		}
		
	}
	
	//instantiates and returns an object of the Tag class given a result set from a db query
	private Tag createTag(ResultSet rs) throws Exception{
		String name = rs.getString("tag_name");
		Tag tag = new Tag();
		tag.setName(name);	
		return tag;	
	}
	
	//instantiates and returns an object of the User class given a result set from a db query
	private User createUser(ResultSet rs) throws Exception{
		String id = rs.getString("id");
		String password = rs.getString("password");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		Timestamp ts = new Timestamp(rs.getDate("create_date").getTime());
		LocalDateTime createdDate = ts.toLocalDateTime();
		
		
		User user = new User();
		
		user.setUserId(id);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setCreatedDate(createdDate);
		
		return user;
	}

//updates provided posting into the database
public void update(Posting posting) throws Exception{
	
	String sql = "update posting set title = ?, "
			+ "body = ? ,"
			+ "last_modified_by = ? ,"
			+ "last_modified = ? "
			+ "where id = ? ";
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, posting.getTitle());
		stmt.setString(2, posting.getBody());
		stmt.setString(3, posting.getLastModifiedBy().getUserId());
		stmt.setTimestamp(4, Timestamp.valueOf(posting.getLastModified()));			
		stmt.setString(5, posting.getId());
		
		stmt.execute();
		
	}finally {
		releaseResources(conn,stmt,null);
	}
			
}

//retrieves posting by provided id
public Posting getPostingById(String postingId) throws Exception{
	String sql = "select * from posting where id = ?";
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1,postingId);
		rs = stmt.executeQuery();		
		Posting p = new Posting();
		
		if(rs.next()) {
			p = createPosting(rs);
		};
			
		return p;
	
	}finally {
		releaseResources(conn,stmt,rs);
	}
	
}

//inserts provided tag into database
public void insertTag(Tag tag) throws Exception{
	String sql = "insert into tags (tag_name, posting_id) value(?,?)";
	Connection conn = null;
	PreparedStatement stmt = null;
	
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, tag.getName());
		stmt.setString(2, tag.getPostingId());

		stmt.execute();
		
	}finally {
		releaseResources(conn,stmt,null);
	}
}

//inserts provided posting into database
public void insertPosting(Posting posting) throws Exception{
	String sql = "insert into posting(id,created_date,"
			+ "created_by,"
			+ "title,"
			+ "body,"
			+ "last_modified,"
			+ "last_modified_by)"
			+ " value(?,?,?,?,?,?,?) ";

Connection conn = null;
PreparedStatement stmt = null;

try {
	conn = getConnection();
	stmt = conn.prepareStatement(sql);
	stmt.setString(1, posting.getId());
	stmt.setTimestamp(2, Timestamp.valueOf(posting.getLastModified()));
	String userId = posting.getCreatedBy().getUserId();
	stmt.setString(3, userId);
	stmt.setString(4, posting.getTitle());
	stmt.setString(5, posting.getBody());
	stmt.setTimestamp(6, Timestamp.valueOf(posting.getLastModified()));
	stmt.setString(7, posting.getLastModifiedBy().getUserId());
	
	stmt.execute();
	
}finally {
	releaseResources(conn,stmt,null);
}

}

//inserts provided posting into database
public void insertUser(User user) throws Exception{
	String sql = "insert into user(id, password, first_name, last_name, create_date) value(?, ?, ?, ?, ?)";
	
	Connection conn = null;
	PreparedStatement stmt = null;

	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, user.getUserId());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getFirstName());
		stmt.setString(4, user.getLastName());
		stmt.setTimestamp(5, Timestamp.valueOf(user.getCreatedDate()));
		
		
		stmt.execute();
		
	}finally {
		releaseResources(conn,stmt,null);
	}
}

//deletes posting from database
public void delete (String id) throws Exception{
	String sql ="delete from posting where id = ?";
	Connection conn = null;
	PreparedStatement stmt = null;
	try {
		conn = getConnection();
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		stmt.execute();
		
	}finally {
		releaseResources(conn,stmt,null);
	}
			
}

}



