package org.salesforce.wave
import org.salesforce.SObject
import org.salesforce.Util

class WaveClient {
  val util = new Util()
  def listResources() : String = {
    val sObject = new SObject("", util.getWaveBaseUrl())
    val body = sObject.getList()
    return body
  }

  def listDataSets(): String = {
    //val util = new Util()
    val sObject = new SObject("datasets", util.getWaveBaseUrl())
    val body = sObject.getList()
    return body
  }

  def listFolders(): String = {
    val util = new Util()
    val sObject = new SObject("folders", util.getWaveBaseUrl())
    val body = sObject.getList()
    return body
  }

  //InsightsExternalData

  def listInsightsExternalData() : String = {
    val sObject = new SObject("InsightsExternalData", util.getBaseUrl())
    val body = sObject.getList()
    return body
  }

}