# Kafka-Message-Producer

This Application can be used to send long messages on Kafka.

In case of TaskCompleted msg, add processId and taskId in [KafkaMsgCommon.scala](/app/messages/KafkaMsgCommon.scala)
And then hit below API to send the message

You can use GET /sendMessage API call to send message on kafka.

Currently Supported Messages along with the commands:
Run these commands from project folder (Kafka-Message-Producer)

| Message | Command |
| ------- | ------- |
| Create Process Instance | make command/create_process_instance |
| Parse Customer Email | make command/parse_customer_email |
| Task Completed (email_parsed) | make task_completed/email_parsed pid=[processid] tid=[taskid]|
| Task Completed (calculated_delete_terms) | make task_completed/calculated_delete_terms pid=[processid] tid=[taskid]|
