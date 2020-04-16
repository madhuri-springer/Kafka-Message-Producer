package messages

import play.api.libs.json.{JsNull, Json}

object FeedsUpdateSubscription extends KafkaMsgCommon {
  val topic = "adis.feeds.update_subscription"


  def message  = {
    val data = Json.parse("""{"_class":"UpdateSubscriptionDrugs","_ident":1,"drugData":[{"_class":"DrugData","_ident":2,"drug":{"_class":"Vocabulary","_id":"http://km.springernature.com/adis/96296","_ident":"http://km.springernature.com/adis/96296","altLabels":["LB-46","Prindolol","Prinodolol"],"normalizedLabel":"Pindolol","prefLabel":"pindolol","thesIds":["96296"],"uri":"http://km.springernature.com/adis/96296","vocabularyUris":["http://km.springernature.com/adis/Drug"]},"names":[],"occurrenceCountry":[],"reportingCountry":[]},{"_class":"DrugData","_ident":3,"drug":{"_class":"Vocabulary","_id":"http://km.springernature.com/adis/76504","_ident":"http://km.springernature.com/adis/76504","altLabels":["LF-17895","SHE-222"],"normalizedLabel":"Mepindolol","prefLabel":"mepindolol","thesIds":["76504"],"uri":"http://km.springernature.com/adis/76504","vocabularyUris":["http://km.springernature.com/adis/Drug"]},"names":[],"occurrenceCountry":[],"reportingCountry":[]}],"isSigModification":false,"operation":"Add","subscriptionIds":["8","3"],"subscriptions":[{"_class":"Subscription","content":"E2B","id":"19","odysseyCustomerDrugCode":"test2drugcode","odysseyDeliveryCode":""},{"_class":"Subscription","content":"E2B","id":"16","odysseyCustomerDrugCode":"test1drugcode","odysseyDeliveryCode":""}]}""")
    Json.obj(
      "name" -> topic,
      "id" -> id,
      "root_id" -> rootId,
      "parent_id" -> parent_id,
      "type" -> "adis.feeds.update_subscription",
      "timestamp" -> timestamp,
      "data" -> data
    )
  }

  override def processId: String = ""

  override def taskId: String = ""
}
