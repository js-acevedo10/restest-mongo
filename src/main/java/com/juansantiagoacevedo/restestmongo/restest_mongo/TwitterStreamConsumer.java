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

public class TwitterStreamConsumer {
	
	private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";
	public static String ultimoTweet;
	public static int numTweets;
	public static final String TRACK_PARAM = "alcalde";
	
	public static String getLatestTweet() {
		return ultimoTweet;
	}
	
	public static int getTweetCount() {
		return numTweets;
	}
	
	public void run() {
		TwitterAppConfig config = new TwitterAppConfig();
		try {
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey(config.getConsumerKey())
					.apiSecret(config.getConsumerSecret())
					.build();
			Token accessToken = new Token("2334095631-QwupYxwRjThfNx7s4j24Vo28ylS64NFs7LA4Fqh","4XVrQTuvs02XO6WTqhwJz8Pq2P9m4B00JI4X0lVZnafV9");
			
			System.out.println("Conectando a Twitter Publico...");
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
            int bueno = 1;
            int malo = 1;
            while ((line = reader.readLine()) != null) {
                numTweets++;
                if(line.contains("Alcalde") || line.contains("alcalde")) {
                	if(line.contains("solucion") || line.contains("bueno") || line.contains("mejorar") || line.contains("buen")) {
                		System.out.println("Buenos - " + bueno + ") " + line);
                		bueno++;
                		ultimoTweet = "Bueno - " + line;
                	} else if(line.contains("malo") || line.contains("terrible") || line.contains("lamentable") || line.contains("fraude") || line.contains("fracaso")) {
                		System.out.println("Malos - "+ malo + ") " + line);
                		malo++;
                		ultimoTweet = "Malo - " + line;
                	}
                	if(numTweets%10 == 0) {
                		System.out.println(numTweets);
                	}
                }
            }
		} catch(Exception e) {
			
		}
	}
	
	/*
	 * {"created_at":"Mon Oct 26 23:18:37 +0000 2015","id":658784828079390720,"id_str":"658784828079390720","text":"@empar11 I can send a longer list once I get out of seeing Steve Jobs, which I highly recommend if you haven't seen it already.","source":"\u003ca href=\"http:\/\/twitter.com\/download\/iphone\" rel=\"nofollow\"\u003eTwitter for iPhone\u003c\/a\u003e","truncated":false,"in_reply_to_status_id":658783258000388097,"in_reply_to_status_id_str":"658783258000388097","in_reply_to_user_id":59005165,"in_reply_to_user_id_str":"59005165","in_reply_to_screen_name":"JustinMercado","user":{"id":59005165,"id_str":"59005165","name":"Justin Mercado","screen_name":"JustinMercado","location":"Earth, Dimension C-137","url":null,"description":"He saved friendship with a song.","protected":false,"verified":false,"followers_count":390,"friends_count":235,"listed_count":3,"favourites_count":2292,"statuses_count":6846,"created_at":"Wed Jul 22 02:21:44 +0000 2009","utc_offset":-18000,"time_zone":"Quito","geo_enabled":false,"lang":"en","contributors_enabled":false,"is_translator":false,"profile_background_color":"022330","profile_background_image_url":"http:\/\/abs.twimg.com\/images\/themes\/theme15\/bg.png","profile_background_image_url_https":"https:\/\/abs.twimg.com\/images\/themes\/theme15\/bg.png","profile_background_tile":false,"profile_link_color":"00B354","profile_sidebar_border_color":"A8C7F7","profile_sidebar_fill_color":"C0DFEC","profile_text_color":"333333","profile_use_background_image":true,"profile_image_url":"http:\/\/pbs.twimg.com\/profile_images\/631308171496267776\/HpZm8A8v_normal.jpg","profile_image_url_https":"https:\/\/pbs.twimg.com\/profile_images\/631308171496267776\/HpZm8A8v_normal.jpg","profile_banner_url":"https:\/\/pbs.twimg.com\/profile_banners\/59005165\/1430244183","default_profile":false,"default_profile_image":false,"following":null,"follow_request_sent":null,"notifications":null},"geo":null,"coordinates":null,"place":null,"contributors":null,"is_quote_status":false,"retweet_count":0,"favorite_count":0,"entities":{"hashtags":[],"urls":[],"user_mentions":[{"screen_name":"empar11","name":"Emily Parham","id":59658374,"id_str":"59658374","indices":[0,8]}],"symbols":[]},"favorited":false,"retweeted":false,"filter_level":"low","lang":"en","timestamp_ms":"1445901517301"}
	 */

}
