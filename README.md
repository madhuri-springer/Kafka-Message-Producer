# Kafka-Message-Producer

This Application can be used to send long messages on Kafka.

In case of TaskCompleted msg, add processId and taskId in [KafkaMsgCommon.scala](/app/messages/KafkaMsgCommon.scala)
And then hit below API to send the message

You can use GET /sendMessage API call to send message on kafka.

Currently Supported Messages along with the commands:
Run these commands from project folder (Kafka-Message-Producer)

| Message | Command |
| ------- | ------- |
| Create Process Instance : Add | make command/create_process_instance_add  |
| Create Process Instance : Delete | make command/create_process_instance_delete  |
| Parse Customer Email | make command/parse_customer_email pid=[processid] tid=[taskid]|
| Task Completed (email_parsed : Add) | make task_completed/email_parsed_add pid=[processid] tid=[taskid]|
| Task Completed (email_parsed : Delete) | make task_completed/email_parsed_delete pid=[processid] tid=[taskid]|
| Task Completed (calculated_delete_terms) | make task_completed/calculated_delete_terms pid=[processid] tid=[taskid]|
| Task Completed (terms_suggested) | make task_completed/terms_suggested pid=[processid] tid=[taskid]|
