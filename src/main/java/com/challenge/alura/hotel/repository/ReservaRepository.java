package com.challenge.alura.hotel.repository;

import com.challenge.alura.hotel.domain.Reserva;
import com.challenge.alura.hotel.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {



}
