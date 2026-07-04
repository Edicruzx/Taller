package org.example.repository;

import org.example.entity.Vehiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
    Optional<Vehiculo> findByPlacaIgnoreCase(String placa);

    boolean existsByPlacaIgnoreCase(String placa);
}
