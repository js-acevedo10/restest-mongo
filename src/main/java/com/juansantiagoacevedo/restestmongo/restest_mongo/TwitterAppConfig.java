package com.juansantiagoacevedo.restestmongo.restest_mongo;

public class TwitterAppConfig {
	
	private String consumerKey = "bGPtpsnIx0eQJuVtXtHncfZUA";
	private String consumerSecret = "dAg6JZB84XXlKn21VGqxGSofaJtkIaD8c9pUrqCJJbyWSMZ2bD";
	
	public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }
}
