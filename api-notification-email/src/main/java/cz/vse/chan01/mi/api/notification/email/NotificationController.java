package cz.vse.chan01.mi.api.notification.email;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.chan01.swagger.document.model.Document;
import cz.vse.chan01.swagger.notification.api.NotificationApi;
import cz.vse.chan01.swagger.notification.model.Notification;

@RestController
public class NotificationController implements NotificationApi {

	//@Autowired
	//private RedisTemplate<String, Document> redisTemplate;

	@Autowired
	private Producer producer;

	public ResponseEntity<Notification> postNotification(Notification document) {
		producer.sendMessage(LocalDateTime.now().toString());
		return ResponseEntity.ok(null);
	}



	//@GetMapping("/notification")
	//public ResponseEntity<Document> documents() {
	//	System.out.println("ahoj_pre");
//
	//	redisTemplate.opsForList().leftPush("ahoj", new Document());
	//	System.out.println("ahoj_post");
	//	Document f = redisTemplate.opsForList().index("ahoj", 1);
	//	System.out.println(f);
	//	return new ResponseEntity<>(f, HttpStatus.OK);
	//}
}
