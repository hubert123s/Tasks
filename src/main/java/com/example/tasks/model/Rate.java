package com.example.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Rate {
    private static final DateTimeFormatter CREATED_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static final ZoneId ZONE_ID = ZoneId.of("Europe/Warsaw");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private String name;
    private OffsetDateTime date = OffsetDateTime.parse(OffsetDateTime.now(ZONE_ID).format(CREATED_DATE_FORMATTER));
    @Column(precision = 10, scale = 4)
    private BigDecimal value;
}
