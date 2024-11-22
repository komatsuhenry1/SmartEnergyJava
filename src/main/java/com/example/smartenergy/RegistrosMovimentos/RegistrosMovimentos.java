package com.example.smartenergy.RegistrosMovimentos;


import com.example.smartenergy.Sensor.Sensor;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name = "tb_registros_movimento")
@AllArgsConstructor
@NoArgsConstructor
public class RegistrosMovimentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEventoMovimento;

    private Boolean detectado;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id_sensores")
    private Sensor sensor_id;
}
