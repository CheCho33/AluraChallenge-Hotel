package com.challenge.alura.hotel.security;

import com.challenge.alura.hotel.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacionService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busco el usuario en la base de adatos
        UserDetails user = usuarioRepository.findByLogin(username);
        //Verifico que el usuario halla sido entregado
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        // y regreso el usario encontrado
        return user;
    }
}
