package tech.devinhouse.pharmacySecurity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.devinhouse.pharmacySecurity.Entity.usuarioEntity;

@Repository
public interface usuarioRepository extends JpaRepository<usuarioEntity,Long> {
    usuarioEntity findByEmailAndSenha(String email, String senha);

    Boolean existsByEmail(String email);


    @Query("select u from usuarioEntity u where u.email = ?1")
    usuarioEntity findUserByLogin(String login);
}
