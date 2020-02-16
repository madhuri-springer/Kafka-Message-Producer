package producers


import java.nio.charset.StandardCharsets
import java.util.{Map => JavaMap}

import akka.Done
import akka.actor.ActorSystem
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.Materializer
import akka.stream.scaladsl.Source
import javax.inject.Inject
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.Serializer
import play.api.libs.json.JsValue
import play.api.{Configuration, Logger}

import scala.concurrent.{ExecutionContext, Future}

class KafkaMessageProducer @Inject()(configuration: Configuration)(implicit system: ActorSystem, executionContext: ExecutionContext, materializer: Materializer) {

  private val logger = Logger(this.getClass)
  private val producerSettings: ProducerSettings[String, JsValue] = ProducerSettings(system, None, Some(JsValueSerializer))
  private val kafkaProducer: KafkaProducer[String, JsValue] = ProducerSettings.createKafkaProducer(producerSettings)
  private val topic = configuration.get[String]("kafka.topic.names.in.customer_input_request")

  def send(payload: JsValue, key: String): Future[Done] = {
    logger.info(s"***** Sending Message Payload to topic $topic, server: ${configuration.get[String]("kafka.server")}")
    val record = new ProducerRecord[String, JsValue](topic, key, payload)
    Source.single(record).runWith(Producer.plainSink(producerSettings.withProducer(kafkaProducer)))
  }

  object JsValueSerializer extends Serializer[JsValue] {

    override def configure(configs: JavaMap[String, _], isKey: Boolean): Unit = {}

    override def serialize(topic: String, data: JsValue): Array[Byte] = data.toString().getBytes(StandardCharsets.UTF_8)

    override def close(): Unit = {}

  }

}
