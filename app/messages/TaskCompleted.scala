package messages

import play.api.libs.json.{JsTrue, Json}

case class TaskCompleted (processId: String, taskId: String) extends KafkaMsgCommon {

  override def topic: String = "adis.cip.task_completed"

  def email_parsed_success(action: String) = {
    val customerInput = """{"_class":"CustomerInput","creationDate":"2020-02-13T10:13:36.463Z","isSigModification":false,"customerDrugData":[{"_class":"CustomerDrugData","customerDrugTerm":"Drug 1","roa":null,"drugFormulation":null,"country":null},{"_class":"CustomerDrugData","customerDrugTerm":"Drug 2","roa":null,"drugFormulation":null,"country":null}],"action":"Add","subscription":{"_class":"Subscription","id":"2","isSigModification":false,"name":"Novartis ICSR-EA","type":["ValidICSR","SpecialSituationsICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T06:33:00.005Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"realtime_filter_by_drug","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"RealTime","manifests":[{"_class":"Manifest","id":"123","isSigModification":false,"startDatePattern":"0 */5 * ? * *","endDatePattern":"0 */5 * ? * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com","deliveryTimePattern":"0 54 08 ? * *"}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","active":true,"reConciliationEnabled":true,"deliverWorldWideIdDuplicate":false}}"""
    generateMessage(
      processVars = Json.obj(
        "customerAction" -> action,
        "customerInput" -> customerInput
      ),
      Option("adis.cip.email_parsed")
    )
  }

  def email_parsed_failed = {
    generateMessage(
      processVars = Json.obj(
        "_class" -> "Variables",
        "processInstanceStatus" -> "Failed",
        "processErrors" -> Json.arr(
          Json.obj(
            "_class" -> "Error",
            "message" -> "No Customer Action matched. Possible CustomerActions : [add , delete]. Filename: novartis_earlyAlert_03022020.xls",
            "errorCode" -> "InvalidFileName"
          )
        )
      ),
      Option("adis.cip.email_parsed-failed")
    )
  }

  def calculated_delete_terms = {
    val customerInput = """{"_class":"CustomerInput","_ident":3,"creationDate":"2020-02-20T10:20:47.620Z","customerDrugData":[{"_class":"CustomerDrugData","_ident":4,"customerDrugTerm":"d1","mappedDrugs":[],"suggestedDrugs":[]},{"_class":"CustomerDrugData","_ident":5,"customerDrugTerm":"D90000","mappedDrugs":[],"suggestedDrugs":[]}],"action":"Delete","subscription":{"_class":"Subscription","_ident":6,"id":"101","name":"Novartis-MAH ICSR-EA","type":["ValidICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T06:33:00.005Z","backboundaryDate":"2018-01-01T00:00:00Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"novartis_mah","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"Batch","deliveryTimePattern":["0 52 10 ? * MON,TUE,WED,THU,FRI *"],"manifests":[{"_class":"Manifest","_ident":7,"id":"123","startDatePattern":"0 0 0 ? * * *","endDatePattern":"0 59 23 ? * * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 50 10 ? * * *","isSigModification":false},{"_class":"Manifest","_ident":8,"id":"456","startDatePattern":"0 0 0 1 * ? *","endDatePattern":"0 59 23 L * ? *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrMonthlyManifest&body=NovartisEaMonthlyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 21 10 ? * * *","isSigModification":false}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","active":true,"reConciliationEnabled":false,"deliverWorldWideIdDuplicate":false,"isSigModification":false},"isSigModification":false}"""
    generateMessage(
      Json.obj(
        "customerInput" -> customerInput
      ),
      Option("adis.cip.calculated_delete_terms")
    )
  }

  def terms_suggested = {
    val customerInput = """{"_class":"CustomerInput","_ident":3,"creationDate":"2020-02-20T10:20:47.620Z","customerDrugData":[{"_class":"CustomerDrugData","_ident":4,"customerDrugTerm":"d1","mappedDrugs":[],"suggestedDrugs":[]},{"_class":"CustomerDrugData","_ident":5,"customerDrugTerm":"D90000","mappedDrugs":[],"suggestedDrugs":[]}],"action":"Delete","subscription":{"_class":"Subscription","_ident":6,"id":"101","name":"Novartis-MAH ICSR-EA","type":["ValidICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T06:33:00.005Z","backboundaryDate":"2018-01-01T00:00:00Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"novartis_mah","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"Batch","deliveryTimePattern":["0 52 10 ? * MON,TUE,WED,THU,FRI *"],"manifests":[{"_class":"Manifest","_ident":7,"id":"123","startDatePattern":"0 0 0 ? * * *","endDatePattern":"0 59 23 ? * * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 50 10 ? * * *","isSigModification":false},{"_class":"Manifest","_ident":8,"id":"456","startDatePattern":"0 0 0 1 * ? *","endDatePattern":"0 59 23 L * ? *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrMonthlyManifest&body=NovartisEaMonthlyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 21 10 ? * * *","isSigModification":false}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","active":true,"reConciliationEnabled":false,"deliverWorldWideIdDuplicate":false,"isSigModification":false},"isSigModification":false}"""
    generateMessage(
      Json.obj(
        "customerInput" -> customerInput
      ),
      Option("adis.cip.terms-suggested")
    )
  }

  def terms_mapped = {
    val customerInput = """{"_class":"CustomerInput","_ident":3,"creationDate":"2020-03-03T06:10:38.527Z","customerDrugData":[{"_class":"CustomerDrugData","_ident":4,"customerDrugTerm":"pindolol","mappedDrugs":[{"vocabularyUris":["http://km.springernature.com/adis/Drug"],"normalizedLabel":"Pindolol","prefLabel":"pindolol","thesIds":["96296"],"_class":"Vocabulary","_id":"http://km.springernature.com/adis/96296","uri":"http://km.springernature.com/adis/96296","altLabels":["LB-46","Prindolol","Prinodolol"]},{"vocabularyUris":["http://km.springernature.com/adis/Drug"],"normalizedLabel":"Bopindolol","prefLabel":"bopindolol","thesIds":["20786"],"_class":"Vocabulary","_id":"http://km.springernature.com/adis/20786","uri":"http://km.springernature.com/adis/20786","altLabels":["Bopindolol-malonate","LT-31200"]}],"exactSuggestedDrugs":[],"fuzzySuggestedDrugs":[]}],"action":"Add","subscription":{"_class":"Subscription","_ident":5,"id":"101","name":"Novartis-MAH ICSR-EA","type":["ValidICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T00:00:00Z","backboundaryDate":"2018-01-01T00:00:00Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"novartis_mah","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"Batch","deliveryTimePattern":["0 52 10 ? * MON,TUE,WED,THU,FRI *"],"manifests":[{"_class":"Manifest","_ident":6,"id":"123","startDatePattern":"0 0 0 ? * * *","endDatePattern":"0 59 23 ? * * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 50 10 ? * * *","isSigModification":false},{"_class":"Manifest","_ident":7,"id":"456","startDatePattern":"0 0 0 1 * ? *","endDatePattern":"0 59 23 L * ? *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrMonthlyManifest&body=NovartisEaMonthlyManifest","destination":"mailto:shelendra.kumar@springernature.com?cc=nayan.mathur@springernature.com","deliveryTimePattern":"0 21 10 ? * * *","isSigModification":false}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","productBundle":"core_icsr_e2b","active":true,"reConciliationEnabled":false,"deliverWorldWideIdDuplicate":false,"isSigModification":false},"isSigModification":false}"""
    generateMessage(
      Json.obj(
        "allTermMapped" -> JsTrue,
        "customerInput" -> customerInput
      ),
      Option("adis.cip.terms_mapped")
    )
  }
}
