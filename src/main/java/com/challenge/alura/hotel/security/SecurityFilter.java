package com.challenge.alura.hotel.security;
import com.challenge.alura.hotel.domain.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.challenge.alura.hotel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UsuarioRepository usuarioRepository;

    //Para no generar Autowire
    public SecurityFilter(UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.tokenService = tokenService;
        this.usuarioRepository=usuarioRepository;
    }



    //Implementar la lógica personalizada que debe ejecutarse para cada solicitud (request) entrante antes de que la solicitud alcance su destino final,
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {                                          //FilterChain= Este parámetro representa la cadena de filtros que deben ejecutarse para procesar la solicitud.

        //En este caso vamos a extraer el Auth quien es quien traera el token
        String autHeader = request.getHeader("Authorization");
        String token = autHeader.replace("Bearer ", "");

        // y extraemos del header el nombre y numero de Session
        String name = request.getHeader("nombre");
        String id = request.getHeader("id");

        if(token != null & name != null & id != null){

            //Con el token busco que corresponda a un usuario y traigo el usuario
            var nombreUsuario = tokenService.getSubject(name, id, token);

            if (nombreUsuario!=null){

                //Token valido
                UserDetails usuario = usuarioRepository.findByUserName(nombreUsuario);
                ********
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        //!!!IMPORTANTE PARA CONTINUAR
        filterChain.doFilter(request, response); //este objeto para permitir que la solicitud continúe su flujo normal hacia el siguiente filtro o destino final



    }
}
