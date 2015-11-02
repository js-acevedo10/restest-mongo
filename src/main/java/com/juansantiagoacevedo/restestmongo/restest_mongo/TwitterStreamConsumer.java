package com.juansantiagoacevedo.restestmongo.restest_mongo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.juansantiagoacevedo.restestmongo.world.TwitAnalyzer;

public class TwitterStreamConsumer {
	
	private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";
	public static String ultimoTweet;
	public static int numTweets;
	public static final String TRACK_PARAM = "luisospinarestr";
	public TwitAnalyzer analyzer;
	
	public static String getLatestTweet() {
		return ultimoTweet;
	}
	
	public static int getTweetCount() {
		return numTweets;
	}
	
	public void run() {
		analyzer = new TwitAnalyzer();
		TwitterAppConfig config = new TwitterAppConfig();
		try {
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(config.getConsumerKey())
					.apiSecret(config.getConsumerSecret())
					.build();
			Token accessToken = new Token("2334095631-QwupYxwRjThfNx7s4j24Vo28ylS64NFs7LA4Fqh","4XVrQTuvs02XO6WTqhwJz8Pq2P9m4B00JI4X0lVZnafV9");
			
			System.out.println("Conectando a Twitter [Público]...");
			System.out.println("Buscando: " + TRACK_PARAM + "...");
			OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
			request.addHeader("version", "HTTP/1.1");
            request.addHeader("host", "stream.twitter.com");
            request.setConnectionKeepAlive(true);
            request.addHeader("user-agent", "Twitter Stream Reader");
            request.addBodyParameter("track", TRACK_PARAM); // Set keywords you'd like to track here
            service.signRequest(accessToken, request);
            Response response = request.send();

            // Create a reader to read Twitter's stream
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                numTweets++;
                ultimoTweet = line;
                analyzer.analyze(line);
            }
		} catch(Exception e) {
			
		}
	}
}
