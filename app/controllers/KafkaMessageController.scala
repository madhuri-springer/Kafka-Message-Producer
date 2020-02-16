package controllers


import javax.inject._
import play.api.Configuration
import play.api.libs.json.Json
import play.api.mvc._
import producers.KafkaMessageProducer

import scala.concurrent.Future

class KafkaMessageController @Inject()(cc: ControllerComponents, kafkaMessageProducer: KafkaMessageProducer, configuration: Configuration) extends AbstractController(cc) {

  def emailRequest = Action.async { implicit request =>

    val message = Json.parse(
      """
        |{
        |  "name": "adis.cip.customer_input_request",
        |  "id": "41f7a3d5-9726-462a-aa87-5f0aa9612af4",
        |  "root_id": "3936bfbc-2ce0-4f8c-9ae8-c76429715f67",
        |  "parent_id": "64ad1440-a8cc-4f05-8fc3-feac755d29de",
        |  "data": {
        |    "variables": {
        |      "rawEmailMessage": "{\"rcpt_to\":\"novartis@adis-qa.springernature.com\",\"friendly_from\":\"madhurikulkarni16@gmail.com\",\"customer_id\":\"17638\",\"content\":{\"subject\":\"Novartis Case Summary\",\"text\":\"\\r\\n\\r\\n\\r\\nRegards,\\r\\nMadhuri\\r\\n\",\"headers\":[{\"Return-Path\":\"<madhurikulkarni16@gmail.com>\"},{\"Received\":\"from [10.92.61.78] ([10.92.61.78:13684] helo=mail-pl1-f175.google.com) by aa.mta1vsmtp.cc.prd.sparkpost (envelope-from <madhurikulkarni16@gmail.com>) (ecelerity 4.3.1.82635 r(Core:4.3.1.8)) with ESMTPS (cipher=AES-128-GCM)  id 89/4E-04558-CE7764E5; Fri, 14 Feb 2020 10:35:24 +0000\"},{\"Received\":\"by mail-pl1-f175.google.com with SMTP id y8so3572407pll.13 for <novartis@adis-qa.springernature.com>; Fri, 14 Feb 2020 02:35:24 -0800 (PST)\"},{\"DKIM-Signature\":\"v=1; a=rsa-sha256; c=relaxed/relaxed; d=gmail.com; s=20161025; h=from:mime-version:subject:message-id:date:to; bh=+17tX9mbzSfau7+c0Y/rDod0CU8oYBy5ZQ/LRZeMraM=; b=uEo7+MrZc00wwokXL8S+8qqx7LFc1f6WOiYHWlNsQm9UDFVCs27KWqa0bIOsA7uxe5 3/1/ag0KC5Cjd+BCFfcii8wpJ2JEPdlGaHVRg51R5cEaIg3aqbTQfGRkdrYIs+lxDCqt 8vysQqY1Q9FbdHEwfCZoZClDU4o2uTphhm1ovB0Fzttv5OJwa+ImeqmggTSJqLbEDYvV X/v/oWy51atNGRemPyu6PrAUMSAOTaw4y3Vv5srxrEi/WmbzIB6wrLHfGeytnfLEf55v XkhlZRP4O7VbJKg33TxvFpyZ8WmwvYktFrZ1JVe0FHQqQlAOer9hBKeXny41omQZX5yG v9mg==\"},{\"X-Google-DKIM-Signature\":\"v=1; a=rsa-sha256; c=relaxed/relaxed; d=1e100.net; s=20161025; h=x-gm-message-state:from:mime-version:subject:message-id:date:to; bh=+17tX9mbzSfau7+c0Y/rDod0CU8oYBy5ZQ/LRZeMraM=; b=tzg82EDuca9OybJY8So5UmvTb8HSF+tOL1AUMB2gK5jy/LDgPPckOZbRO36tjc0RHs XOIY6lvllNA8cAEjoJ/22WMh2GypAjX00M7Nl1QRTmUCHPx6wp+o9WSmreElU2CZTTwP qLZn5Qj2gIHePeAaWAR/rnU5mUXzbOd7p2oEhFaPr+3wDQvNXjRUteqKKl0ZpHaCPsdq T5K7ObmW9FkFlWcbwGw0B6Rkqk5OQxO3N2b/epc0ajSLhVgfr34W+50zV4CGIa45WEQu ASyL+mVFTEqn5xuBlnmFXkbfC5+ZRQNNN4369LXC3FRbeKY4ReoI89jlx+i6UtBgHp5p uF4A==\"},{\"X-Gm-Message-State\":\"APjAAAXlZ0ndQ6PoeHrmHZ1G+ukM5yvExqLOENwPZut4zhvdSee7ZS24 2pdzfkS//jk/xBf41SN0Swp19cfn\"},{\"X-Google-Smtp-Source\":\"APXvYqyarepInPC4CCFTUFKVHOUx9VrknZUhSrJ6FUjnii99bjpc2cmSgiFeIYKNmMYlZXhavi6GLA==\"},{\"X-Received\":\"by 2002:a17:90a:9416:: with SMTP id r22mr2820782pjo.2.1581676524082; Fri, 14 Feb 2020 02:35:24 -0800 (PST)\"},{\"Received\":\"from [192.168.92.180] ([103.82.76.9]) by smtp.gmail.com with ESMTPSA id p24sm6415363pgk.19.2020.02.14.02.35.22 for <novartis@adis-qa.springernature.com> (version=TLS1_2 cipher=ECDHE-RSA-AES128-GCM-SHA256 bits=128/128); Fri, 14 Feb 2020 02:35:23 -0800 (PST)\"},{\"From\":\"Madhuri Kulkarni <madhurikulkarni16@gmail.com>\"},{\"Content-Type\":\"multipart/mixed; boundary=\\\"Apple-Mail=_C814EFF5-758F-4ECD-B8FD-1D6B5DE92160\\\"\"},{\"Mime-Version\":\"1.0 (Mac OS X Mail 10.3 \\\\(3273\\\\))\"},{\"Subject\":\"Novartis Case Summary\"},{\"Message-Id\":\"<407842E6-A364-4568-9C16-35DC2479C60A@gmail.com>\"},{\"Date\":\"Fri, 14 Feb 2020 16:05:20 +0530\"},{\"To\":\"novartis@adis-qa.springernature.com\"},{\"X-Mailer\":\"Apple Mail (2.3273)\"}],\"email_rfc822\":\"Return-Path: <madhurikulkarni16@gmail.com>\\r\\nReceived: from [10.92.61.78] ([10.92.61.78:13684] helo=mail-pl1-f175.google.com)\\r\\n\\tby aa.mta1vsmtp.cc.prd.sparkpost (envelope-from <madhurikulkarni16@gmail.com>)\\r\\n\\t(ecelerity 4.3.1.82635 r(Core:4.3.1.8)) with ESMTPS (cipher=AES-128-GCM) \\r\\n\\tid 89/4E-04558-CE7764E5; Fri, 14 Feb 2020 10:35:24 +0000\\r\\nReceived: by mail-pl1-f175.google.com with SMTP id y8so3572407pll.13\\r\\n        for <novartis@adis-qa.springernature.com>; Fri, 14 Feb 2020 02:35:24 -0800 (PST)\\r\\nDKIM-Signature: v=1; a=rsa-sha256; c=relaxed/relaxed;\\r\\n        d=gmail.com; s=20161025;\\r\\n        h=from:mime-version:subject:message-id:date:to;\\r\\n        bh=+17tX9mbzSfau7+c0Y/rDod0CU8oYBy5ZQ/LRZeMraM=;\\r\\n        b=uEo7+MrZc00wwokXL8S+8qqx7LFc1f6WOiYHWlNsQm9UDFVCs27KWqa0bIOsA7uxe5\\r\\n         3/1/ag0KC5Cjd+BCFfcii8wpJ2JEPdlGaHVRg51R5cEaIg3aqbTQfGRkdrYIs+lxDCqt\\r\\n         8vysQqY1Q9FbdHEwfCZoZClDU4o2uTphhm1ovB0Fzttv5OJwa+ImeqmggTSJqLbEDYvV\\r\\n         X/v/oWy51atNGRemPyu6PrAUMSAOTaw4y3Vv5srxrEi/WmbzIB6wrLHfGeytnfLEf55v\\r\\n         XkhlZRP4O7VbJKg33TxvFpyZ8WmwvYktFrZ1JVe0FHQqQlAOer9hBKeXny41omQZX5yG\\r\\n         v9mg==\\r\\nX-Google-DKIM-Signature: v=1; a=rsa-sha256; c=relaxed/relaxed;\\r\\n        d=1e100.net; s=20161025;\\r\\n        h=x-gm-message-state:from:mime-version:subject:message-id:date:to;\\r\\n        bh=+17tX9mbzSfau7+c0Y/rDod0CU8oYBy5ZQ/LRZeMraM=;\\r\\n        b=tzg82EDuca9OybJY8So5UmvTb8HSF+tOL1AUMB2gK5jy/LDgPPckOZbRO36tjc0RHs\\r\\n         XOIY6lvllNA8cAEjoJ/22WMh2GypAjX00M7Nl1QRTmUCHPx6wp+o9WSmreElU2CZTTwP\\r\\n         qLZn5Qj2gIHePeAaWAR/rnU5mUXzbOd7p2oEhFaPr+3wDQvNXjRUteqKKl0ZpHaCPsdq\\r\\n         T5K7ObmW9FkFlWcbwGw0B6Rkqk5OQxO3N2b/epc0ajSLhVgfr34W+50zV4CGIa45WEQu\\r\\n         ASyL+mVFTEqn5xuBlnmFXkbfC5+ZRQNNN4369LXC3FRbeKY4ReoI89jlx+i6UtBgHp5p\\r\\n         uF4A==\\r\\nX-Gm-Message-State: APjAAAXlZ0ndQ6PoeHrmHZ1G+ukM5yvExqLOENwPZut4zhvdSee7ZS24\\r\\n\\t2pdzfkS//jk/xBf41SN0Swp19cfn\\r\\nX-Google-Smtp-Source: APXvYqyarepInPC4CCFTUFKVHOUx9VrknZUhSrJ6FUjnii99bjpc2cmSgiFeIYKNmMYlZXhavi6GLA==\\r\\nX-Received: by 2002:a17:90a:9416:: with SMTP id r22mr2820782pjo.2.1581676524082;\\r\\n        Fri, 14 Feb 2020 02:35:24 -0800 (PST)\\r\\nReceived: from [192.168.92.180] ([103.82.76.9])\\r\\n        by smtp.gmail.com with ESMTPSA id p24sm6415363pgk.19.2020.02.14.02.35.22\\r\\n        for <novartis@adis-qa.springernature.com>\\r\\n        (version=TLS1_2 cipher=ECDHE-RSA-AES128-GCM-SHA256 bits=128/128);\\r\\n        Fri, 14 Feb 2020 02:35:23 -0800 (PST)\\r\\nFrom: Madhuri Kulkarni <madhurikulkarni16@gmail.com>\\r\\nContent-Type: multipart/mixed;\\r\\n boundary=\\\"Apple-Mail=_C814EFF5-758F-4ECD-B8FD-1D6B5DE92160\\\"\\r\\nMime-Version: 1.0 (Mac OS X Mail 10.3 \\\\(3273\\\\))\\r\\nSubject: Novartis Case Summary\\r\\nMessage-Id: <407842E6-A364-4568-9C16-35DC2479C60A@gmail.com>\\r\\nDate: Fri, 14 Feb 2020 16:05:20 +0530\\r\\nTo: novartis@adis-qa.springernature.com\\r\\nX-Mailer: Apple Mail (2.3273)\\r\\n\\r\\n\\r\\n--Apple-Mail=_C814EFF5-758F-4ECD-B8FD-1D6B5DE92160\\r\\nContent-Disposition: attachment;\\r\\n\\tfilename=novartis_caseSummary_add_11022020.xlsx\\r\\nContent-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;\\r\\n\\tx-unix-mode=0644;\\r\\n\\tname=\\\"novartis_caseSummary_add_11022020.xlsx\\\"\\r\\nContent-Transfer-Encoding: base64\\r\\n\\r\\nUEsDBBQABgAIAAAAIQAe/sVJYAEAABgFAAATAAgCW0NvbnRlbnRfVHlwZXNdLnhtbCCiBAIooAAC\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADE\\r\\nVM1OAjEQvpv4DptezW6BgzGGhYM/RyURH6BuZ9mGbtt0BoS3d7agMYYsEkm8bLNtv5+Z9JvxdNPa\\r\\nbA0RjXelGBYDkYGrvDZuUYrX+WN+IzIk5bSy3kEptoBiOrm8GM+3ATBjtMNSNEThVkqsGmgVFj6A\\r\\n45Pax1YR/8aFDKpaqgXI0WBwLSvvCBzl1HGIyfgearWylD1seHvnhOEiu9vd66RKoUKwplLERmV3\\r\\nKg/iIljsAa6d/uEu3zsrGJnIsTEBr/YKz9yaaDRkMxXpSbXsQ26sfPdx+eb9sui3eUDN17WpQPtq\\r\\n1XIHCgwRlMYGgFpbpLVolXG/0E+XUaZleGYjXX2J+EQfo3/yQfzuQKbv31uRaI4UjrS1gGeudkd6\\r\\nTLlREfQLRU7o2Q185+7zwe93Fn1ATnKE07vwGbkOnQcmgkgGekP3pchj4HTBH6mDbs5o0Ae0ZZpr\\r\\nkw8AAAD//wMAUEsDBBQABgAIAAAAIQBQfE7B9gAAAEwCAAALAAgCX3JlbHMvLnJlbHMgogQCKKAA\\r\\nAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\r\\njJLPSgMxEIfvgu8Q5t7NtoKINNuLCL2J1AcYk9k/7G4mJNO6fXuDoLiw1h6TzHzzzY9sd9M4qBPF\\r\\n1LE3sC5KUOQtu843Bt4Oz6sHUEnQOxzYk4EzJdhVtzfbVxpQclNqu5BUpvhkoBUJj1on29KIqeBA\\r\\nPr/UHEeUfIyNDmh7bEhvyvJex98MqGZMtXcG4t6tQR3OIU/+n8113Vl6YnscycvCCD2vyGSMDYmB\\r\\nadAfHPt35r7IwqCXXTbXu/y9px5J0KGgthxpFWJOKUqXc/3RcWxf8nX6qrgkdHe90Hz1pXBoEvKO\\r\\n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\\r\\n--Apple-Mail=_C814EFF5-758F-4ECD-B8FD-1D6B5DE92160\\r\\nContent-Transfer-Encoding: 7bit\\r\\nContent-Type: text/plain;\\r\\n\\tcharset=us-ascii\\r\\n\\r\\n\\r\\n\\r\\n\\r\\nRegards,\\r\\nMadhuri\\r\\n--Apple-Mail=_C814EFF5-758F-4ECD-B8FD-1D6B5DE92160--\\r\\n\",\"email_rfc822_is_base64\":false,\"to\":[\"novartis@adis-qa.springernature.com\"]},\"msg_from\":\"madhurikulkarni16@gmail.com\",\"webhook_id\":\"15072403689686748838\",\"protocol\":\"smtp\"}"
        |    },
        |    "process_id": "41074",
        |    "task_id": "41091"
        |  },
        |  "timestamp": "2020-02-14T10:35:27.930Z"
        |}
        |""".stripMargin)

    kafkaMessageProducer.send(message, "adis.cip.customer_input_request")
    Future.successful(Ok("Successful"))
  }

}
