package com.juansantiagoacevedo.restestmongo.world;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class TwitAnalyzer {
	
	public JSONObject filtros;

	public TwitAnalyzer() {
		
	}
	
	public void analyze(String line) throws JSONException {
		JSONObject income = new JSONObject(line);
		String text = income.getString("text");
		
//		String newLine = text.replaceAll("[^\\p{L}\\p{Z}]","");
//		newLine = newLine.replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
//		newLine = newLine.toLowerCase();
//		String[] words = newLine.split(" ");
		
		
//		System.out.print("{");
//		for(String word : words) {
//			System.out.print(word + "-");
//		}
//		System.out.println("}");
		
		System.out.println(text);
	}

}
