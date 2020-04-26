package cz.vse.chan01.mi.api.notification.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cz.vse.chan01.swagger.notification.api.NotificationApi;
import cz.vse.chan01.swagger.notification.model.Notification;

@RestController
public class NotificationController implements NotificationApi {

	@Autowired
	private Producer producer;

	public ResponseEntity<Notification> postNotification(Notification document) {
		producer.sendMessage(new Notification());
		return ResponseEntity.ok(null);
	}
}
