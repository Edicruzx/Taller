package org.example.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "vehiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehiculo {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "La placa debe tener 3 letras y 3 numeros. Ejemplo: ABC123")
    private String placa; // Placa del vehiculo

    @NotBlank(message = "El color es obligatorio")
    private String color;
    @NotBlank(message = "El tipo es obligatorio")
    @Pattern(regexp = "^(Auto|Camioneta|Camion)$", message = "El tipo debe ser Auto, Camioneta o Camion")
    private String tipo;

    @NotBlank(message = "El problema es obligatorio")
    private String problema;

    @Min(value = 0, message = "El tiempo aproximado no puede ser negativo")
    private Integer tiempoDias;

    @PositiveOrZero(message = "El costo no puede ser negativo")
    private BigDecimal costo;
}
