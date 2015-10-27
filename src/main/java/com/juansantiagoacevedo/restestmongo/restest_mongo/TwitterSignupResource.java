package com.juansantiagoacevedo.restestmongo.restest_mongo;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Path("twitter")
public class TwitterSignupResource {
	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
	private final TwitterAppConfig config = new TwitterAppConfig();
	private static final String DB_NAME = "test";
	private static final String DB_COLLECTION_NAME = "users";
	private static final String SERVER_LOCATION = "http://localhost:9998";
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response redirectToAuthorization() {
		OAuthService service = createService()
				.callback(SERVER_LOCATION + "/twitter/continue")
				.build();
		Token requestToken = service.getRequestToken();
		String authURL = service.getAuthorizationUrl(requestToken);
		return Response.seeOther(URI.create(authURL)).build();
	}
	
	@GET
	@Path("continue")
	@Produces(MediaType.TEXT_HTML)
	public Response redirectToApp(@QueryParam("oauth_token") String oauthToken,
									@QueryParam("oauth_verifier") String oauthVerifier) throws JSONException {
		OAuthService service = createService().build();
		Token requestToken = new Token(oauthToken, oauthVerifier);
		Verifier verifier = new Verifier(oauthVerifier);
		
		Token accesToken = service.getAccessToken(requestToken, verifier);
		OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
		service.signRequest(accesToken, request);
		org.scribe.model.Response response = request.send();
		
		String x = response.getBody();
		JSONObject obj = new JSONObject(x);
		String name = obj.getString("name");
		String userName = obj.getString("screen_name");
		String followers = obj.getString("followers_count");
		x = "<h1>Name: " + name + "</h1>" 
				+ "<p>Username: " + userName + "</p>" 
				+ "<p>Followers: " + followers + "</p>";
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase(DB_NAME);
		MongoCollection<Document> mongoCollection = db.getCollection(DB_COLLECTION_NAME);
		mongoCollection.insertOne(
				new Document("name", name)
				.append("screen_name", userName)
				.append("followers", followers)
				.append("oauth", new Document()
								.append("oauth_token", oauthToken)
								.append("oauth_verifier", oauthVerifier))
				.append("access_token", accesToken.toString()));
		mongoClient.close();
		return Response.seeOther(URI.create(SERVER_LOCATION + "/restaurants/users/"+ userName)).build();
	}
	
	private ServiceBuilder createService() {
        return new ServiceBuilder()
                .provider(TwitterApi.class)
                .apiKey(config.getConsumerKey())
                .apiSecret(config.getConsumerSecret());
    }
}
