command/create_process_instance_add:
	curl "localhost:9000/sendMessage/create_process_instance_add"

command/create_process_instance_delete:
	curl "localhost:9000/sendMessage/create_process_instance_delete"

command/parse_customer_email:
	curl "localhost:9000/sendMessage/parse_customer_email?processId=$(pid)&taskId=$(tid)"

task_completed/email_parsed_delete:
	curl "localhost:9000/sendMessage/email_parsed_delete?processId=$(pid)&taskId=$(tid)"

task_completed/email_parsed_add:
	curl "localhost:9000/sendMessage/email_parsed_add?processId=$(pid)&taskId=$(tid)"

task_completed/calculated_delete_terms:
	curl "localhost:9000/sendMessage/calculated_delete_terms?processId=$(pid)&taskId=$(tid)"

task_completed/terms_suggested:
	curl "localhost:9000/sendMessage/terms_suggested?processId=$(pid)&taskId=$(tid)"

task_completed/terms_mapped:
	curl "localhost:9000/sendMessage/terms_mapped?processId=$(pid)&taskId=$(tid)"

command/feeds_update_subscription:
	curl "localhost:9000/sendMessage/feeds_update_subscription"