**Assignment 2**

Program Name: JSON Data Retriever (REST APis).

Author: Muhammad Firdaus Fadlizam, Software Engineering student.

Description: This program is capable of reading JSON file deployed on a server,
returning data to the client using Representational State Transfer (REST). 

<h2>How to run the program?</h2>

You need Springboot framework to run the application (Server). Also, you need to use mock 
framework to retrieve data from the server (Client). You can use Postman software 
for that. I have included link to download Postman below.


**Download and install Springboot framework**  
Go to the this link, https://spring.io/tools and select Spring tools based on
your desired IDE. 
As for me, I chose Spring Tools 4 Eclipse. 

**Download and install Postman**  
You can download and install Postman from this link,
https://www.postman.com/downloads/. You may have
to register first before downloading the software. 

**Run SpringToolSuite4.exe**  
Once you have downloaded and installed Spring tools software. Open and run 
SpringToolSuite4.exe. You may be asked to select directory as workspace. 
Select your desired directory and click launch.  

**Add the project**  
Once the tool has been launched, click on File, then select Import.
Select Maven, and click on Existing Maven Projects. 
Hover to Root Directory and click on browse. Find the project folder that you have 
unzipped. Select the folder. Then, click finish. The project will be added to your 
workspace. 

**Add/Change Dependencies**  
You may not need to add or change any dependencies because I have included all of them
inside pom.xml in the project folder. However, if any problems arise, you can install 
GSON dependency from this link,
https://mvnrepository.com/artifact/com.google.code.gson/gson

Select GSON version that you want, I recommend you pick most downloaded, latest version
that has no vulnerability. Click on the version. Then, you select Maven because the 
program is a Maven-based project. Copy the dependency URLs and open pom.xml.
Find dependencies tag, and paste the URLs inside the tag body. 


**Run the application**  
Right click the selected project on Package Explorer. Hover over Run as and select 
Spring boot app. The server will be binded and started on port 8080. You may have
different port number. Please check which port the application runs on under the 
console tab. 



<h2>How to use REST APIs?</h2>
Now the program has been running, you may want to retrieve data from the server. 

**Run Postman**  
Open and run Postman. Select plus symbol to add a new request. 
Since you are allowed to retrieve JSON data only, you will be using GET command. 

Basically, there are four actions that you may perform.
1. Retrieve all tweets in the text archive.
2. Retrieve a list of external links.
3. Retrieve details about a given tweet. 
4. Retrieve detailed profile information.

**Note: 8080 in the commands below is my server port number. You may have to change it
if the program was running in different port number.**

**Retrieve all tweets**   
On the request url field, enter "localhost:8080/tweet" and click send.
All tweets will be received in the format of JSON file if you successfully retrieved
them. 

**Retrieve a list of external links**  
On the request url field, enter "localhost:8080/links" and click send.
All external links in a text will be retrieved and grouped according to tweet ID.

**Retrieve details about a given tweet**  
On the request url field, enter "localhost:8080/tweetDetails". Then, go to query
Params to add a new key. In the key field, enter "id". Then, you may enter the
tweet id you want to retrieve its details on the value field and click send. 

**Retrieve detailed profile information**  
On the request url field, enter "localhost:8080/profileInfo". Then, go to query
Params to add a new key. In the key field, enter "screenName". Then, you may enter the
user's screen name you want to retrieve its details on the value field and click send.

<h2> Changing inputs/JSON file URL </h2>  

**Remarks: I am reading JSON file using the provided URL, not locally saved. Although
it is not a good practice to hard code file path, Prof Foyzul said it is fine for those
who uses the provided URL.**

1. Open SpringToolSuite4.exe. 
2. Select the application folder on package explorer to expand. 
3. Click on "src/main/java" and select package name, "com.restapplication.service". 
4. Select "TweetService.java" and find readFile method. 
5. Inside the method, there is an object called url with URL as
its type. The object is instantiated with url link passed as its argument. 
6. Change the url link with the url that contains your selected JSON file. 







