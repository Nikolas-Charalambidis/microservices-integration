package cz.vse.chan01.mi.api.document.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cz.vse.chan01.mi.api.document.entity.DocumentEntity;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentEntity, String> {

	List<DocumentEntity> findAllByCaseId(Long caseId);

	List<DocumentEntity> findAllByCustomerId(Long customerId);

	List<DocumentEntity> findAllByCaseIdAndCustomerId(Long caseId, Long customerId);
}