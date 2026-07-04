package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Vehiculo;
import org.example.service.VehiculoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/api/vehiculos", "/vehiculo"})
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    public Vehiculo guardar(@Valid @RequestBody Vehiculo vehiculo) {
        return vehiculoService.guardar(vehiculo);
    }

    @GetMapping
    public List<Vehiculo> listar() {
        return vehiculoService.listar();
    }

    @GetMapping("/{id}")
    public Vehiculo buscarPorId(@PathVariable String id) {
        return vehiculoService.buscarPorId(id);
    }

    @GetMapping("/placa/{placa}")
    public Vehiculo buscarPorPlaca(@PathVariable String placa) {
        return vehiculoService.buscarPorPlaca(placa);
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(@PathVariable String id, @Valid @RequestBody Vehiculo vehiculo) {
        return vehiculoService.actualizar(id, vehiculo);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        vehiculoService.eliminar(id);
        return "Vehiculo eliminado correctamente";
    }
}
