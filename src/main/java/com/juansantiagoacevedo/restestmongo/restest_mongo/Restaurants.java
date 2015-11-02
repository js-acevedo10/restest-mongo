
package com.juansantiagoacevedo.restestmongo.restest_mongo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("/restaurants")
public class Restaurants {
	
	public static final String DB_NAME = "test";
	public static final String DB_COLLECTION_NAME = "restaurants";
    
    @GET 
    @Produces(MediaType.TEXT_HTML)
    public String getIt() throws JSONException { 
    	String r = "";
    	final JSONArray array = new JSONArray();
    	MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> collection = db.getCollection(DB_COLLECTION_NAME);
		FindIterable<Document> iterable = collection.find(new Document("grades.score", new Document("$gt", 30)));
		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				array.put(document);
			}
		});
		for(int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			String name = "<h3>" + obj.getString("name") + "</h3>";
			String loc = obj.getString("borough");
			String location = "<a href=\"https://www.google.com.co/maps/place/" + loc + "\">" + loc + "</a>";
			r = r + name + location;
		}
		mongoClient.close();
        return r;
    }
    
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public String postIt(JSONObject user) throws JSONException {
    	if(user != null) {
    		String name = user.getString("name");
    		int edad = user.getInt("edad");
    		boolean isVip = user.getBoolean("isVip");
    		if(name.equals("juan") && edad == 20 && isVip) {
    			return "Bienvenido al sistema " + name;
    		}
    	}
    	return "Error";
    }
    
    @Path("/users/{screen-name}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    public String showUserIngo(@PathParam("screen-name") String userName) {
    	String response = "<h1>" + userName +  "</h1>";
    	return response;
    }
    
    @Path("twitter-stream")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getStreamItems() {
    	
    	return "<h1>Tweet Count: " + Integer.toString(TwitterStreamConsumer.getTweetCount()) + "</h1>" +
        "<h2>Latest Payload: " + TwitterStreamConsumer.getLatestTweet() + "</h2>";    	
    }
}
