package controllers

import javax.inject._
import messages.TaskCompleted
import play.api.Configuration
import play.api.mvc._
import producers.KafkaMessageProducer

import scala.concurrent.Future

class KafkaMessageController @Inject()(cc: ControllerComponents, kafkaMessageProducer: KafkaMessageProducer, configuration: Configuration) extends AbstractController(cc) {

  def emailRequest = Action.async { implicit request =>

    // Command to initiate Parse Customer Email
    // kafkaMessageProducer.send(CustomerInputRequest.topic_parseCustomerEmail, CustomerInputRequest.parse_customer_email, CustomerInputRequest.key)

    // Command to sent task completed message when email parsing is done
    // Customer Action - Delete
    kafkaMessageProducer.send(TaskCompleted.topic, TaskCompleted.email_parsed_delete, TaskCompleted.key)

    Future.successful(Ok("Successful"))
  }
}
