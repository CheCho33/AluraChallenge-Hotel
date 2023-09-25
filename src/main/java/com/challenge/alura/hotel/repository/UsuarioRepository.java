package com.challenge.alura.hotel.repository;

import com.challenge.alura.hotel.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        //Con jpaRepositoy ya tengo forma de buscar en la base de datos
    // pero puedo agregar mis propias consultas,
    UserDetails findByUserName(String userName);

}
