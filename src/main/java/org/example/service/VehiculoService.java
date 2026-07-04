package org.example.service;

import org.example.entity.Vehiculo;
import org.example.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        vehiculo.setPlaca(vehiculo.getPlaca().toUpperCase());

        if (vehiculoRepository.existsByPlacaIgnoreCase(vehiculo.getPlaca())) {
            throw new RuntimeException("Ya existe un vehiculo registrado con la placa: " + vehiculo.getPlaca());
        }

        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> listar() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo buscarPorId(String id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con ID: " + id));
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlacaIgnoreCase(placa)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado con placa: " + placa));
    }

    public Vehiculo actualizar(String id, Vehiculo vehiculo) {
        Vehiculo vehiculoExistente = buscarPorId(id);
        String nuevaPlaca = vehiculo.getPlaca().toUpperCase();

        vehiculoRepository.findByPlacaIgnoreCase(nuevaPlaca)
                .filter(encontrado -> !encontrado.getId().equals(id))
                .ifPresent(encontrado -> {
                    throw new RuntimeException("Ya existe otro vehiculo con la placa: " + nuevaPlaca);
                });

        vehiculoExistente.setPlaca(nuevaPlaca);
        vehiculoExistente.setColor(vehiculo.getColor());
        vehiculoExistente.setTipo(vehiculo.getTipo());
        vehiculoExistente.setProblema(vehiculo.getProblema());
        vehiculoExistente.setTiempoDias(vehiculo.getTiempoDias());
        vehiculoExistente.setCosto(vehiculo.getCosto());

        return vehiculoRepository.save(vehiculoExistente);
    }

    public void eliminar(String id) {
        Vehiculo vehiculo = buscarPorId(id);
        vehiculoRepository.delete(vehiculo);
    }
}
