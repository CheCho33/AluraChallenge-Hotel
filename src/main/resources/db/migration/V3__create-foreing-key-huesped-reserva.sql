ALTER TABLE `alura-hotel`.huespedes
    ADD CONSTRAINT fk_huespedes_reservas
        FOREIGN KEY (id_reserva) REFERENCES reservas(id);