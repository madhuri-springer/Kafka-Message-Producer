package messages

import java.time.Instant
import java.util.UUID

import play.api.libs.json.{JsValue, Json}

trait KafkaMsgCommon {

  def topic:String

  def id = UUID.randomUUID().toString

  def rootId = UUID.randomUUID().toString

  def parent_id = UUID.randomUUID().toString

  def timestamp = Instant.now.toString

  def processId:String = ""

  def taskId: String = ""

  def key = processId

  def generateMessage(processVars: JsValue, msgType: Option[String] = None) = {
    Json.obj(
      "name" -> topic,
      "id" -> id,
      "root_id" -> rootId,
      "parent_id" -> "64ad1440-a8cc-4f05-8fc3-feac718Feb01",
      "type" -> msgType,
      "timestamp" -> timestamp,
      "data" -> Json.obj(
        "process_id" -> processId,
        "task_id" -> taskId,
        "process_vars" -> processVars
      )
    )
  }
}
