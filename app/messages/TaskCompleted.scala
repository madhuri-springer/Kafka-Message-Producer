package messages

import java.time.Instant
import java.util.UUID

import play.api.libs.json.{JsObject, Json}

object TaskCompleted {

  val topic = "adis.cip.task_completed"

  private def processId = "125017"

  private def taskId = "125017"

  private def id = UUID.randomUUID().toString

  private def rootId = UUID.randomUUID().toString

  def key = processId

  private def generateMessage(msgType: String, processVars: JsObject) = {
    Json.obj(
      "name" -> topic,
      "id" -> id,
      "root_id" -> rootId,
      "parent_id" -> "64ad1440-a8cc-4f05-8fc3-feac718Feb01",
      "type" -> msgType,
      "timestamp" -> Instant.now,
      "data" -> Json.obj(
        "process_id" -> processId,
        "task_id" -> taskId,
        "process_vars" -> processVars
      )
    )
  }

  def email_parsed_delete = {

    val customerInput = """{"_class":"CustomerInput","creationDate":"2020-02-13T10:13:36.463Z","isSigModification":false,"customerDrugData":[{"_class":"CustomerDrugData","customerDrugTerm":"Drug 1","roa":null,"drugFormulation":null,"country":null},{"_class":"CustomerDrugData","customerDrugTerm":"Drug 2","roa":null,"drugFormulation":null,"country":null}],"action":"Add","subscription":{"_class":"Subscription","id":"2","isSigModification":false,"name":"Novartis ICSR-EA","type":["ValidICSR","SpecialSituationsICSR"],"content":"EarlyAlert","odysseyDeliveryCode":"news.novartis_ea","odysseyCustomerDrugCode":"nl.novartis","serviceStartDate":"2019-10-30T06:33:00.005Z","articleTypes":[],"drugData":[],"additionalFilteringRules":[],"customerCriteria":[],"ruleName":"realtime_filter_by_drug","subscriptionIdsForResend":[],"exclusionSubscriptionIds":[],"databaseName":[],"journalName":[],"ageGroup":[],"deliveryMode":"RealTime","manifests":[{"_class":"Manifest","id":"123","isSigModification":false,"startDatePattern":"0 */5 * ? * *","endDatePattern":"0 */5 * ? * *","template":"http://www.springernature.com/vocabulary/Template?preProcess=PassThru&fork=PassThru&transform=NovartisEaIcsrDailyManifest&body=NovartisEaDailyManifest","destination":"mailto:shelendra.kumar@springernature.com","deliveryTimePattern":"0 54 08 ? * *"}],"allowedPublishersForAbstract":["SpringerNature"],"toEmailName":"novartis","active":true,"reConciliationEnabled":true,"deliverWorldWideIdDuplicate":false}}"""
    generateMessage("email_parsed",
      Json.obj(
        "customerAction" -> "Delete",
        "customerInput" -> customerInput
      )
    )
  }
}
