package org.salesforce

import org.apache.http.client.methods.{HttpDelete, HttpGet, HttpPatch, HttpPost}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

class SObject(sObjectN  : String, utilN: Util) {
	var sObjectName: String = sObjectN
  var baseUrl: String = utilN.getBaseUrl()
	var util:Util = utilN


	def getList() : String = {
		//val util = new Util()
		val host = util.getHost()
		//baseUrl = util.getBaseUrl()

		val access_token = util.getAccessToken()
		//println("access_token: " + access_token)
    var url = ""
    if(sObjectName != "") {
      url = host + baseUrl + sObjectName
    }else {
      url = host + baseUrl
    }
    println("url: " + url)
		val request = new HttpGet(url)

		request.addHeader("Authorization", "Bearer " + access_token)
		request.addHeader("Content-type", "application/json")
		val client = new DefaultHttpClient
		val response = client.execute(request)
		val handler = new BasicResponseHandler()
		val body = handler.handleResponse(response)
		return body
	}

  def getSObjectDetails(id : String) : String = {
    //val util = new Util()
    val host = util.getHost()
    val baseUrl = util.getBaseUrl()
    val access_token = util.getAccessToken()
    val url = host + baseUrl + sObjectName + "/" + id
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
    //val util = new Util()
    val host = util.getHost()
    val baseUrl = util.getBaseUrl()

		val access_token = util.getAccessToken()
		//println(access_token)
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
    //val util = new Util()
    val host = util.getHost()
    val baseUrl = util.getBaseUrl()

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
    //val util = new Util()
    val host = util.getHost()
    val baseUrl = util.getBaseUrl()

		val accessToken = util.getAccessToken()
		//println(accessToken)
		val url = host + baseUrl + sObjectName + "/" + objectId
		val patch = new HttpPatch(url);
		patch.addHeader("Authorization", "Bearer " + accessToken)
		patch.setHeader("Content-type", "application/json")
		patch.setEntity(new StringEntity(jsonData))
		val response = (new DefaultHttpClient).execute(patch)
		println(response)
  }

  def executeSOQL(soql: String): String = {
    val util = new Util()
    val host = util.getHost()
    val baseUrl = util.getQueryUrl()
	val sql = java.net.URLEncoder.encode(soql, "UTF-8") // need to add this function to fix the urlEncoder

		val accessToken = util.getAccessToken()
		println("accessToken: " + accessToken)
		val url = host + baseUrl  + sql
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