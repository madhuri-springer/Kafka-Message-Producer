package controllers

import javax.inject._
import messages.{CreateProcessInstance, ParseCustomerEmail, TaskCompleted}
import play.api.Configuration
import play.api.mvc._
import producers.KafkaMessageProducer

import scala.concurrent.Future

class KafkaMessageController @Inject()(cc: ControllerComponents, kafkaMessageProducer: KafkaMessageProducer, configuration: Configuration) extends AbstractController(cc) {

  def sendMessage(action: String, processId:String, taskId:String) = Action.async { implicit request =>

    action match {
      case "create_process_instance" => kafkaMessageProducer.send(CreateProcessInstance.topic, CreateProcessInstance.message, CreateProcessInstance.key)
      case "parse_customer_email" => {
        validate(processId, taskId)
        val parseCustomerEmail = ParseCustomerEmail(processId, taskId)
        kafkaMessageProducer.send(parseCustomerEmail.topic, parseCustomerEmail.message, parseCustomerEmail.key)
      }
      case "email_parsed_delete" => {
        sendEmailParsedMessage("Delete", processId, taskId)
      }
      case "email_parsed_add" => {
        sendEmailParsedMessage("Add", processId, taskId)
      }
      case "calculated_delete_terms" => {
        validate(processId, taskId)
        val taskCompleted = TaskCompleted(processId, taskId)
        kafkaMessageProducer.send(taskCompleted.topic, taskCompleted.calculated_delete_terms, taskCompleted.key)
      }
      case "terms_suggested" => {
        validate(processId, taskId)
        val taskCompleted = TaskCompleted(processId, taskId)
        kafkaMessageProducer.send(taskCompleted.topic, taskCompleted.terms_suggested, taskCompleted.key)
      }
    }

    Future.successful(Ok("Successful"))
  }

  private def sendEmailParsedMessage(action: String, processId: String, taskId: String) = {
    validate(processId, taskId)
    val taskCompleted = TaskCompleted(processId, taskId)
    kafkaMessageProducer.send(taskCompleted.topic, taskCompleted.email_parsed(action), taskCompleted.key)
  }

  private def validate(processId: String, taskId: String) = {
    require(processId.nonEmpty && taskId.nonEmpty, "Process Id and Task Id are required for this operation")
  }
}
