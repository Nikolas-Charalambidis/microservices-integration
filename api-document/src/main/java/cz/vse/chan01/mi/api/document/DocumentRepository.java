package cz.vse.chan01.mi.api.document;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface DocumentRepository extends MongoRepository<DocumentEntity, String> {


}