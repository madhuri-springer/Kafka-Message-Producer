package controllers

import javax.inject._
import messages.{CreateProcessInstance, ParseCustomerEmail, TaskCompleted}
import play.api.Configuration
import play.api.mvc._
import producers.KafkaMessageProducer

import scala.concurrent.Future

class KafkaMessageController @Inject()(cc: ControllerComponents, kafkaMessageProducer: KafkaMessageProducer, configuration: Configuration) extends AbstractController(cc) {

  def sendMessage(msgType: String, processId:String, taskId:String) = Action.async { implicit request =>

    msgType match {
      case "create_process_instance" => kafkaMessageProducer.send(CreateProcessInstance.topic, CreateProcessInstance.message, CreateProcessInstance.key)
      case "parse_customer_email" => {
        require(processId.nonEmpty && taskId.nonEmpty, "Process Id and Task Id are required for Parse Customer Email")
        val parseCustomerEmail = ParseCustomerEmail(processId, taskId)
        kafkaMessageProducer.send(parseCustomerEmail.topic, parseCustomerEmail.message, parseCustomerEmail.key)
      }
      case "email_parsed" => {
        require(processId.nonEmpty && taskId.nonEmpty, "Process Id and Task Id are required for Task Completed - Email Parsed")
        val taskCompleted = TaskCompleted(processId, taskId)
        kafkaMessageProducer.send(taskCompleted.topic, taskCompleted.email_parsed_delete, taskCompleted.key)
      }
      case "calculated_delete_terms" => {
        require(processId.nonEmpty && taskId.nonEmpty, "Process Id and Task Id are required for Task Completed - Calculated Delete Terms")
        val taskCompleted = TaskCompleted(processId, taskId)
        kafkaMessageProducer.send(taskCompleted.topic, taskCompleted.calculated_delete_terms, taskCompleted.key)
      }
    }

    Future.successful(Ok("Successful"))
  }
}
