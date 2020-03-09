package cz.vse.chan01.mi.api.document.service;

import cz.vse.chan01.swagger.document.model.Document;

public interface NotificationService {

	void sendNotification(Document document);
}
