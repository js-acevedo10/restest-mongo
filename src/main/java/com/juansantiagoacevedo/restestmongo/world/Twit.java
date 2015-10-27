package com.juansantiagoacevedo.restestmongo.world;

public class Twit {
	
	public String created_at;
	public String id;
	public String text;
	public String source;
	public boolean truncated;
	public String in_reply_to_status_id;
	public String in_reply_to_user_id;
	public String in_reply_to_screen_name;
	public UsuarioTwitter usuario;
	public String geo;
	public String coordinates;
	public String place;
	public Boolean is_quote_status;
	public int retweet_count;
	public int favorite_count;
	public String[] hashtags ;
	public String[] urls ;
	public Boolean favorited;
	public Boolean retweeted;
	public String lang;
	public String timestamp_ms;
	
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isTruncated() {
		return truncated;
	}
	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}
	public String getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}
	public void setIn_reply_to_status_id(String in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}
	public String getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}
	public void setIn_reply_to_user_id(String in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}
	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}
	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}
	public UsuarioTwitter getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioTwitter usuario) {
		this.usuario = usuario;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Boolean getIs_quote_status() {
		return is_quote_status;
	}
	public void setIs_quote_status(Boolean is_quote_status) {
		this.is_quote_status = is_quote_status;
	}
	public int getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}
	public int getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}
	public String[] getHashtags() {
		return hashtags;
	}
	public void setHashtags(String[] hashtags) {
		this.hashtags = hashtags;
	}
	public String[] getUrls() {
		return urls;
	}
	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	public Boolean getFavorited() {
		return favorited;
	}
	public void setFavorited(Boolean favorited) {
		this.favorited = favorited;
	}
	public Boolean getRetweeted() {
		return retweeted;
	}
	public void setRetweeted(Boolean retweeted) {
		this.retweeted = retweeted;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getTimestamp_ms() {
		return timestamp_ms;
	}
	public void setTimestamp_ms(String timestamp_ms) {
		this.timestamp_ms = timestamp_ms;
	}
}
