package messages

import play.api.libs.json.Json

object TaskCompleted extends KafkaMsgCommon {

  val topic = "adis.cip.task_completed"

  def email_parsed_delete = {
    val customerInput = """{"_class":"CustomerInput","creationDate":"2020-02-13T10:13:36.463Z","isSigModification":false,"customerDrugData":[{"_class":"CustomerDrugData","customerDrugTerm":"Drug 1","roa":null,"drugFormulation":null,"country":null},{"_class":"CustomerDrugData","customerDrugTerm":"Drug 2","roa":null,"drugFormulation":null,"country":null}],"action":"Add","subscription":{"_class":"Subscription","id":"2","isSigModification":false,"name":"Novartis ICSR-EA","type":["ValidICSR","SpecialSituationsICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T06:33:00.005Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"realtime_filter_by_drug","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"RealTime","manifests":[{"_class":"Manifest","id":"123","isSigModification":false,"startDatePattern":"0 */5 * ? * *","endDatePattern":"0 */5 * ? * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com","deliveryTimePattern":"0 54 08 ? * *"}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","active":true,"reConciliationEnabled":true,"deliverWorldWideIdDuplicate":false}}"""
    generateMessage(
      Json.obj(
        "customerAction" -> "Delete",
        "customerInput" -> customerInput
      ),
      Option("email_parsed")
    )
  }

  def calculated_delete_terms = {
    val customerInput = """{"_class":"CustomerInput","_ident":3,"creationDate":"2020-02-20T10:20:47.620Z","customerDrugData":[{"_class":"CustomerDrugData","_ident":4,"customerDrugTerm":"d1","mappedDrugs":[],"suggestedDrugs":[]},{"_class":"CustomerDrugData","_ident":5,"customerDrugTerm":"D90000","mappedDrugs":[],"suggestedDrugs":[]}],"action":"Delete","subscription":{"_class":"Subscription","_ident":6,"id":"101","name":"Novartis-MAH ICSR-EA","type":["ValidICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T06:33:00.005Z","backboundaryDate":"2018-01-01T00:00:00Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"novartis_mah","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"Batch","deliveryTimePattern":["0 52 10 ? * MON,TUE,WED,THU,FRI *"],"manifests":[{"_class":"Manifest","_ident":7,"id":"123","startDatePattern":"0 0 0 ? * * *","endDatePattern":"0 59 23 ? * * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 50 10 ? * * *","isSigModification":false},{"_class":"Manifest","_ident":8,"id":"456","startDatePattern":"0 0 0 1 * ? *","endDatePattern":"0 59 23 L * ? *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrMonthlyManifest&body=NovartisEaMonthlyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 21 10 ? * * *","isSigModification":false}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","active":true,"reConciliationEnabled":false,"deliverWorldWideIdDuplicate":false,"isSigModification":false},"isSigModification":false}"""
    generateMessage(
      Json.obj(
        "customerInput" -> customerInput
      ),
      Option("calculated_delete_terms")
    )
  }
}
