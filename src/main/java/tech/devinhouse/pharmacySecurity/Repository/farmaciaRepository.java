package tech.devinhouse.pharmacySecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.pharmacySecurity.Entity.farmaciaEntity;

@Repository
public interface farmaciaRepository extends JpaRepository<farmaciaEntity,Long> {
    Boolean existsByCnpj(String cnpj);
}

