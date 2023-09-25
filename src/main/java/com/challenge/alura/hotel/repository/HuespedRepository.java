package com.challenge.alura.hotel.repository;

import com.challenge.alura.hotel.domain.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {



}
