package controllers

import javax.inject._
import messages.{CreateProcessInstance, ParseCustomerEmail, TaskCompleted}
import play.api.Configuration
import play.api.mvc._
import producers.KafkaMessageProducer

import scala.concurrent.Future

class KafkaMessageController @Inject()(cc: ControllerComponents, kafkaMessageProducer: KafkaMessageProducer, configuration: Configuration) extends AbstractController(cc) {

  def emailRequest = Action.async { implicit request =>

//    val obj = CreateProcessInstance
//    val obj = ParseCustomerEmail

//    kafkaMessageProducer.send(obj.topic, obj.message, obj.key)


    // For Task Completed ==>
    val tc = TaskCompleted
    val action = TaskCompleted.email_parsed_delete
//    val action = TaskCompleted.calculated_delete_terms

    kafkaMessageProducer.send(tc.topic, action, tc.key)

    Future.successful(Ok("Successful"))
  }
}
