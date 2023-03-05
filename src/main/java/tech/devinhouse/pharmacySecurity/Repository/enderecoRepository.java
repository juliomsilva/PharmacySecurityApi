package tech.devinhouse.pharmacySecurity.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.devinhouse.pharmacySecurity.Entity.enderecoEntity;

@Repository
public interface enderecoRepository extends JpaRepository<enderecoEntity,Long> {
}
