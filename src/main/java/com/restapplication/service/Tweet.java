package com.restapplication.service;

import java.util.ArrayList;
import java.util.List;

//hold tweet details 
public class Tweet {

		String id;
		String created_at;
		String text;
		String lang;
		//hold user information details
		Profile user;
		//hold links extracted from tweet text
		List<String> linkURLs;

		public String getID() {
			return id;
		}
		public String getDate() {
			return created_at;
		}
		public String getText() {
			return text;
		}
		public Profile getProfiles() {
			return user;
		}
		public String getLanguage() {
			return lang;
		}
		public void addLink(String x) {
			if(linkURLs == null)
				linkURLs = new ArrayList<String>();
			
			linkURLs.add(x);
		}
		
		public List<String> getLinks(){
			return linkURLs;
		}
		
	
}
