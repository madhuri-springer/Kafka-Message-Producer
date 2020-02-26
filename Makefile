command/create_process_instance:
	curl localhost:9000/sendMessage/create_process_instance

command/parse_customer_email:
	curl localhost:9000/sendMessage/parse_customer_email

task_completed/email_parsed:
	curl "localhost:9000/sendMessage/email_parsed?processId=$(pid)&taskId=$(tid)"

task_completed/calculated_delete_terms:
	curl "localhost:9000/sendMessage/calculated_delete_terms?processId=$(pid)&taskId=$(tid)"
