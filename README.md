# Kafka-Message-Producer

This Application can be used to send long messages on Kafka.

In case of TaskCompleted msg, add processId and taskId in [KafkaMsgCommon.scala](/app/messages/KafkaMsgCommon.scala)
And then hit below API to send the message

You can use GET /sendMessage API call to send message on kafka.

Currently Supported Messages:
1. Create Process Instance
2. Parse Customer Email
3. Task Completed (email_parsed, calcualated_delete_terms)