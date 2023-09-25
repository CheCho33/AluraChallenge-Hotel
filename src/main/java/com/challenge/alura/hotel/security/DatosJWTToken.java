package com.challenge.alura.hotel.security;

//El record me ayuda a generar un metodo con un constructor, metodos getters y
//equals(), hashCode(), toString() y copy(). de forma rapida, se usa para objetos inmutables.

public record DatosJWTToken(String jwttoken){
}
