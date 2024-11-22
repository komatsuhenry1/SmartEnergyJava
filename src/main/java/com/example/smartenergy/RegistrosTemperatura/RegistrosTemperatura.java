package com.example.smartenergy.RegistrosTemperatura;

import com.example.smartenergy.Sensor.Sensor;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name = "tb_registros_temperatura")
@AllArgsConstructor
@NoArgsConstructor
public class RegistrosTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEventoTemperatura;

    private Integer temperatura;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id_sensores")
    private Sensor sensor_id;

}
