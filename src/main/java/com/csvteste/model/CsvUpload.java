package com.csvteste.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
