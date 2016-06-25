package org.salesforce

import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.{BasicResponseHandler, DefaultHttpClient}

import com.google.gson.Gson
import com.typesafe.config._;

case class Token(access_token: String, instance_url: String, 
	id: String,
	token_type: String,
	issued_at: String,
	signature: String)

class Util {
  var host = ""
  var id = ""
  var baseUrl = ""
  var queryUrl = ""
  var waveBaseUrl = ""
  var accessToken = ""
  var userName = "";
  var password = "";
  var loginUrl = "";
  var grantService = "";
  var clientId = "";
  var clientSecret = "";
  var key = "force";
  val conf = ConfigFactory.load()
  /*
  val UserName = conf.getString("force.UserName")
  val PassWord = conf.getString("force.PassWord")
  val LoginURL = conf.getString("force.LoginURL")
  val GrantService = conf.getString("force.GrantService")
  val ClientID = conf.getString("force.ClientID")
  val ClientSecret = conf.getString("force.ClientSecret")
   */

	def getHost() : String = {
    if(host.equals("")) {
      host = conf.getString(key + ".Host")
    }
    return host
  }

  def getBaseUrl() : String = {
    if (baseUrl.equals("")) {
      baseUrl = conf.getString(key + ".BaseUrl")
    }
    return baseUrl
  }

  def getQueryUrl() : String = {
    if (queryUrl.equals("")) {
      queryUrl = conf.getString(key + ".QueryUrl")
    }
    return queryUrl
  }

  def getWaveBaseUrl() : String = {
    if(waveBaseUrl.equals("")) {
      waveBaseUrl = conf.getString(key + ".WaveBaseUrl")
    }
    return waveBaseUrl
  }

  def getAccessToken(): String = {
    //val login = "https://login.salesforce.com/services/oauth2/token"
    if (accessToken.equals("")) {
      try {
        val UserName = conf.getString(key + ".UserName")
        val PassWord = conf.getString(key + ".PassWord")
        val LoginURL = conf.getString(key + ".LoginURL")
        val GrantService = conf.getString(key + ".GrantService")
        val ClientID = conf.getString(key + ".ClientID")
        val ClientSecret = conf.getString(key + ".ClientSecret")

        val loginURL = LoginURL +
          GrantService +
          "&client_id=" + ClientID +
          "&client_secret=" + ClientSecret +
          "&username=" + UserName +
          "&password=" + PassWord

        val client = new DefaultHttpClient
        val post = new HttpPost(loginURL)
        val handler = new BasicResponseHandler();
        val response = client.execute(post)
        val body = handler.handleResponse(response);
        println(response)
        val gson = new Gson
        val jsonObject = gson.fromJson(body, classOf[Token])
        accessToken = jsonObject.access_token
        println("access_token: " + accessToken)
      } catch {
        case ioe: java.io.IOException =>
        case ste: java.net.SocketTimeoutException =>
      }
    }
    return accessToken

  }
}