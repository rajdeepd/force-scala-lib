package org.salesforce

import org.apache.http.client.methods.{HttpDelete, HttpGet, HttpPatch, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

class SObject(sObjectN : String) {
	var sObjectName: String = sObjectN
	val HOST = "https://ap2.salesforce.com"
	val BASE_URL = "/services/data/v35.0/sobjects/"

	def getList() : String = {
		val host = "https://ap2.salesforce.com"
		val baseUrl = "/services/data/v35.0/sobjects/"
		val util = new Util()

		val access_token = util.getAccessToken()
		println(access_token)
	    val url = host + baseUrl + sObjectName
		val request = new HttpGet(url)

		request.addHeader("Authorization", "Bearer " + access_token)
		request.addHeader("Content-type", "application/json")
		val client = new DefaultHttpClient
		val response = client.execute(request)
		val handler = new BasicResponseHandler()
		val body = handler.handleResponse(response)
		return body
    }

    def createSObject(jsonData : String)  =  {
    	val host = "https://ap2.salesforce.com"
		val baseUrl = "/services/data/v35.0/sobjects/"
		val util = new Util()

		val access_token = util.getAccessToken()
		println(access_token)
		val url = host + baseUrl + sObjectName
		val post = new HttpPost(url)
 
		// set the Content-type
		post.addHeader("Authorization", "Bearer " + access_token)
		post.setHeader("Content-type", "application/json")
		 
		// add the JSON as a StringEntity
		post.setEntity(new StringEntity(jsonData))
	
		// send the post request
		val response = (new DefaultHttpClient).execute(post)
		println(response)

    }

    def deleteSObject(objectId: String) {
    	val host = "https://ap2.salesforce.com"
		val baseUrl = "/services/data/v35.0/sobjects/"
		val util = new Util()

		val accessToken = util.getAccessToken()
		println(accessToken)
		val url = host + baseUrl + sObjectName + "/" + objectId
		val delete = new HttpDelete(url);
		delete.addHeader("Authorization", "Bearer " + accessToken)
		delete.setHeader("Content-type", "application/json")

		val response = (new DefaultHttpClient).execute(delete)
		println(response)
    }

    def patchSObject(objectId: String , jsonData: String) {
    	val host = "https://ap2.salesforce.com"
		val baseUrl = "/services/data/v35.0/sobjects/"
		val util = new Util()

		val accessToken = util.getAccessToken()
		println(accessToken)
		val url = host + baseUrl + sObjectName + "/" + objectId
		val patch = new HttpPatch(url);
		patch.addHeader("Authorization", "Bearer " + accessToken)
		patch.setHeader("Content-type", "application/json")
		patch.setEntity(new StringEntity(jsonData))
		val response = (new DefaultHttpClient).execute(patch)
		println(response)
    }

    def executeSOQL(soql: String): String = {
    	val host = "https://ap2.salesforce.com"
		val baseUrl = "/services/data/v35.0/query/?q="
		val util = new Util()

		val accessToken = util.getAccessToken()
		println("accessToken: " + accessToken)
		val url = host + baseUrl  + soql
		println("url: " + url)
		val request = new HttpGet(url)
		request.addHeader("Authorization", "Bearer " + accessToken)
		request.addHeader("Content-type", "application/json")
		val client = new DefaultHttpClient
		val response = client.execute(request)
		val handler = new BasicResponseHandler()
		val body = handler.handleResponse(response)
		return body
    }
}