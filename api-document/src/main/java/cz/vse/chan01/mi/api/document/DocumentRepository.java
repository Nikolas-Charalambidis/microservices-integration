package cz.vse.chan01.mi.api.document;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cz.vse.chan01.mi.api.document.entity.DocumentEntity;

@Repository
interface DocumentRepository extends MongoRepository<DocumentEntity, String> {

}