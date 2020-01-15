package cz.vse.chan01.mi.api.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.chan01.mi.model.file.File;

@RestController
public class NotificationController {

	@Autowired
	private RedisTemplate<String, File> redisTemplate;

	@GetMapping("/notification")
	public ResponseEntity<File> files() {
		System.out.println("ahoj_pre");

		redisTemplate.opsForList().leftPush("ahoj", new File(1,1,"ahoj"));
		System.out.println("ahoj_post");
		File f = redisTemplate.opsForList().index("ahoj", 1);
		System.out.println(f);
		return new ResponseEntity<>(f, HttpStatus.OK);
	}
}
