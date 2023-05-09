package com.restapplication.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.google.gson.Gson;


@RestController
public class TweetService {
	
	//class to hold data parsed from the file
	Tweet [] tweets;
	
	 
	//Retrieve data from the provided URL
	public void readFile () throws IOException{
		
		//instantiate url
		URL url = new URL("https://foyzulhassan.github.io/files/favs.json");
		URLConnection yc = url.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		//transfer data based on variable names into the class object
	    tweets = new Gson().fromJson(reader, Tweet[].class);
	}
	
	
	//Return all tweet
	@RequestMapping(value="/tweet",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAllTweets() throws IOException{
	
	String details = "[";
	String pattern = "{\"createTime\":\"%s\",\n \"id\":\"%s\",\n \"text\":\"%s\"}";
	
	//retrieve data 
	readFile();
	
	for(Tweet x : tweets) {
	
		String json = String.format(pattern,x.getDate(),x.getID(),x.getText().replace("\n", "\\n"));
		
		//the last tweet does not need a comma after it
		if(x == tweets[tweets.length-1]) {
		details = details + json;
		break;
		}
		
		details = details + json + ",";
	}
	return details + "]";
	}
	
	//return a list of external links 
	@RequestMapping(value="/links", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getList() throws IOException {	

		String linkList = "";
		String linkPattern = "{\"link\":\"%s\"}";
		String json = "";
		readFile();
		
		//extract links from text using regular expression
		for(Tweet x : tweets) {
			String tweetText = x.getText();
			String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
			Pattern patternLink = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
			Matcher urlMatcher = patternLink.matcher(tweetText);

			    while (urlMatcher.find())
			    {
			        x.addLink(tweetText.substring(urlMatcher.start(0),urlMatcher.end(0)));
			    }
		}
		
		//return links as JSON format
		for(Tweet x : tweets) {
			if(x.getLinks()!= null) {
				 List<String>list = x.getLinks();
				 
				 //this variable counts the number of elements inside the list
			     int count = 0;
				for(String y : list){
					
					//link format
					linkList = linkList + String.format(linkPattern, y);
					count++;
					
					//if the link is not the last element in the list, add comma.
					if(count != list.size()) {
						linkList = linkList + ",";
					
					}
					
				
				}
			}
			//group the links with the specified tweet id
			json = json + "{\"id\":\"%s\",\n\"links:\"[" + linkList + "]},";
			json = String.format(json,  x.getID());
			//reset the format for the next set of links. 
			linkList = "";
		}
	
			
		return "[" + json + "]";
		
			
	}
	
	//return tweet details based on id given
	@RequestMapping(value="/tweetDetails", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String getTweetDetails(String id) throws IOException {
	
		
	String pattern = "{\"create_at\":\"%s\",\n \"text\":\"%s\",\n \"screenName\":\"%s\",\n \"lang\":\"%s\"}";
	String json = "";
	//retrieve data
	readFile();
	
	//find the tweet id and retrieve its details
	for(Tweet x : tweets) {
		
		if(x.getID().equals(id)) 
			json = String.format(pattern,x.getDate(),x.getText().replace("\n", "\\n"),x.user.getScreenName(),x.getLanguage());
			
	}
	
	return json;
	
	}

	//return user information based on his screen name
	@RequestMapping(value="/profileInfo",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getProfileInfo(String screenName) throws IOException {
		
		String pattern = "{\"name\":\"%s\",\n \"location\":\"%s\",\n \"description\":\"%s\"}";
		String json = "";
		//retrieve data
		readFile();
		//find the specified user screen name
		for(Tweet x : tweets) {
			Profile profile = x.getProfiles();
			if(profile.getScreenName().equals(screenName)) 
				json = String.format(pattern,profile.getName(),profile.getLocation(),profile.getDescription());		
						
		}
			
		return json;
	
	}
	
	

}

