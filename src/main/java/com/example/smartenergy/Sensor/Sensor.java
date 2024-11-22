package com.example.smartenergy.Sensor;

import com.example.smartenergy.Sala.Sala;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@Table(name = "tb_sensores")
@AllArgsConstructor
@NoArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sensores;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "sala_id", referencedColumnName = "idSala")
    private Sala sala_id;

}
