package tech.devinhouse.pharmacySecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.pharmacySecurity.Entity.medicamentoEntity;

@Repository
public interface medicamentoRepository extends JpaRepository<medicamentoEntity,Long> {
}

