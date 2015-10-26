
package com.juansantiagoacevedo.restestmongo.restest_mongo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray getIt() throws JSONException { 
    	JSONArray array = new JSONArray();
    	MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoCollection<Document> collection = db.getCollection("restaurants");
		FindIterable<Document> iterable = collection.find(new Document("grades.grade", new Document("$eq", "C")));
		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
				array.put(document);
			}
		});
		mongoClient.close();
        return array;
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
}
