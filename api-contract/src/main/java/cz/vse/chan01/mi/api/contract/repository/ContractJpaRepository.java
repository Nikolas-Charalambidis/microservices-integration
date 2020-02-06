package cz.vse.chan01.mi.api.contract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import cz.vse.chan01.mi.api.contract.entity.ContractEntity;

@Transactional
public interface ContractJpaRepository extends JpaRepository<ContractEntity, Long> {
}
