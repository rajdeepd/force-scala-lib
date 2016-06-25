package org.salesforce.wave
import org.salesforce.SObject
import org.salesforce.Util

class WaveClient {
  val util = new Util()
  def listResources() : String = {
    val sObject = new SObject("", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getList()
    return body
  }

  def listDataSets(): String = {
    //val util = new Util()
    val sObject = new SObject("datasets", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getList()
    return body
  }

  def listFolders(): String = {
    val util = new Util()
    val sObject = new SObject("folders", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getList()
    return body
  }

  def listLenses(): String = {
    val util = new Util()
    val sObject = new SObject("lenses", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getList()
    return body
  }

  //InsightsExternalData

  def listInsightsExternalData() : String = {
    val sObject = new SObject("InsightsExternalData", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getList()
    return body
  }

  def getDataSetDetails(id : String) : String = {
    val sObject = new SObject("datasets", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getSObjectDetails(id)
    return body
  }

  def getLenseDetails(id : String) : String = {
    val sObject = new SObject("lenses", util)
    sObject.baseUrl = util.getWaveBaseUrl()
    val body = sObject.getSObjectDetails(id)
    return body
  }

}