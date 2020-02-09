package cz.vse.chan01.mi.api.document;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import cz.vse.chan01.swagger.contract.model.Contract;
import cz.vse.chan01.swagger.document.api.DocumentApi;
import cz.vse.chan01.swagger.document.model.Document;
import cz.vse.chan01.swagger.document.model.VersionedDocument;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("/")
public class DocumentController implements DocumentApi {

	private final MongoTemplate mongoTemplate;

	private final DocumentRepository documentRepository;

	@Autowired
	private  ModelMapper modelMapper;

	@Autowired
		@Qualifier("webApplicationContext")
	ResourceLoader resourceLoader;



	public DocumentController(final MongoTemplate mongoTemplate,
		final DocumentRepository documentRepository) {
		this.mongoTemplate = mongoTemplate;
		this.documentRepository = documentRepository;
	}


	@PostConstruct
	void postConstruct() {
		System.out.println("POST CONSTRUCT");
		try {
			Resource resource = resourceLoader.getResource("classpath:mongo-data.json");
			File f = resource.getFile();
			System.out.println("FILE IS OKEY");
			//InputStream inJson = DocumentServiceImpl.class.getResourceAsStream("classpath:mongo-data.json");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			DocumentEntity[] sample = objectMapper.readValue(f, DocumentEntity[].class);
			//DocumentEntity[] sample = objectMapper.readValue(inJson, DocumentEntity[].class);
			System.out.println(Arrays.deepToString(sample));
			Iterable<DocumentEntity> iterable = Arrays.asList(sample);
			System.out.println("INITIALIZING MONGO");
			this.documentRepository.saveAll(iterable);
		} catch (IOException e) {
			System.out.println("INITIALIZING MONGO FAILED: " + e.toString());
		}
		//this.documentRepository.save(
		//	new DocumentEntity("1", "abc", "def", "fgh",
		//		LocalDateTime.now(), LocalDateTime.now(),
		//		Collections.singletonList(
		//			new VersionedDocumentEntity(
		//				"1.0", LocalDateTime.now(), "1.0.0", "PDF", new byte[]{0x6}))));
	}

	@Override
	public ResponseEntity<List<Document>> documents() {
		System.out.println("FINDING IN MONGO");
		List<DocumentEntity> documentEntityList = documentRepository.findAll();
		List<Document> d = documentEntityList.stream().map(de -> modelMapper.map(de, Document.class))
			.collect(Collectors.toList());
		System.out.println("RESULT");
		System.out.println(documentEntityList);
		//final List<Document> documents = mongoTemplate.findAll(Document.class, "documents");
		return ResponseEntity.ok(d);
	}

	@Override
	public ResponseEntity<Document> documentById(final Long documentId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<Void> postDocument(final Document document) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<Void> postDocument(final VersionedDocument versionedDocument) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<VersionedDocument> versionedDocumentById(final Long versionedDocumentId) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<List<VersionedDocument>> versionedDocuments() {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}


	/*
	@RabbitListener(queues = "file-queue.post")
	@PostMapping
	public Document createFile(Contract contract) {
		System.out.println(" [x] Received request for " + contract);
		Document file = null; //new Document(contract.getContractId(), contract.getCustomerId(), contract.getCustomerLabel());
		System.out.println(" [.] Returned " + file);


		DBObject objectToSave = BasicDBObjectBuilder.start()
			.add("key", "value")
			.get();

		// when
		mongoTemplate.save(objectToSave, "collection");
		mongoTemplate.findAll(DBObject.class, "collection");

		return file;
	}

	@RabbitListener(queues = "file-queue.post")
	public File createFile(
		@Payload Contract contract,
		@Header(AmqpHeaders.CHANNEL) Channel channel,
		@Header(AmqpHeaders.DELIVERY_TAG) Long tag,
		@Header(AmqpHeaders.CORRELATION_ID) String correlationId,
		Message message) throws InterruptedException, IOException {

		System.out.println("Channel: " + channel.getChannelNumber() + " deliveryTag: " + tag + "=: " + message.getMessageProperties().getDeliveryTag());
		StopWatch watch = new StopWatch();
		watch.start();
		System.out.println(" [localhost:" + environment.getProperty("server.port") +"] CorrelationId: " + correlationId +  " Received " + contract);
		Random random = new Random();
		Thread.sleep(5000 + random.nextInt(2000));

		System.out.println(" [x] Received request for " + contract);
		File file = new File(contract.getContractId(), contract.getCustomerId(), contract.getLabel());
		System.out.println(" [.] Returned " + file);

		try {
			channel.basicAck(tag, false);
			System.out.println("Acknowledged");
		} catch (Exception e) {
			channel.basicNack(tag, false, true);
			System.out.println("NOT-Acknowledged");
		}

		watch.stop();
		System.out.println(" [localhost:" + environment.getProperty("server.port") +"][" + watch.getTotalTimeMillis() + "ms] CorrelationId: " + correlationId + " Done " + file);
		return file;
	}
	*/

}
