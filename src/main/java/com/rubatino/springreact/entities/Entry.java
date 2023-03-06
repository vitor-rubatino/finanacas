package com.rubatino.springreact.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rubatino.springreact.enums.EntryStatus;
import com.rubatino.springreact.enums.EntryType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_entry")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer month;
    private Integer year;
    @ManyToOne
    @JoinColumn(name = "tb_user_id")
    private User user;
    private BigDecimal value;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate registrationDate;
    @Enumerated(value = EnumType.STRING)
    private EntryType entryType;
    @Enumerated(value = EnumType.STRING)
    private EntryStatus entryStatus;

}
