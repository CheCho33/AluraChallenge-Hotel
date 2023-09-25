package com.challenge.alura.hotel.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.challenge.alura.hotel.domain.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //Genero un token
    public String generarToken(Usuario usuario, String apiSecret){

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //el token se firma utilizando un algoritmo especificado en la variable 'algorithm'

            return JWT.create()
                    .withIssuer("HotelAlura")  // entidad que emite el codigo token
                    .withSubject(usuario.getUsername())  //El sujeto del token es el nombre de usuario del usuario
                    .withClaim("id", usuario.getId()) //Reclamo personalizado: son información adicional que puede incluir en el token según tus necesidades.
                    .withClaim("Username", usuario.getUsername())
                    .withExpiresAt(generarFechaExpiracion()) //Fecha de expiracion del token
                    .sign(algorithm);  //La firma garantiza la integridad del token y permite que las partes confíen en su contenido.
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    //Verifico El token, junto con un numero de session.
    public String getSubject(String name, String id, String token){

        if(token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(id); //validando firma

            verifier = JWT.require(algorithm) //rea una instancia del verificador JWT que verificará el token utilizando el algoritmo especificado. La variable 'algorithm' debe contener el mismo algoritmo de firma que se utilizó para crear el token.
                    .withIssuer("HotelAlura")  //: Establece una regla para que el emisor del token
                    .withClaim("id", id) //Reclamo personalizado: son información adicional que puede incluir en el token según tus necesidades.
                    .withClaim("Username", name) //Reclamo personalizado: son información adicional que puede incluir en el token según tus necesidades.
                    .build()  //: Construye el verificador JWT con las reglas especificadas.
                    .verify(token); //: Finalmente, se llama al método verify() con el token que se desea verificar ('token')

        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }

        if(verifier.getSubject() == null){
            throw new RuntimeException("Verificacion del password es invalido");
        }

        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-05:00"));
    }


}
