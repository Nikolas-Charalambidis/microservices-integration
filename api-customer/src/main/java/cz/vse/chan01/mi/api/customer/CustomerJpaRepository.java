package cz.vse.chan01.mi.api.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
