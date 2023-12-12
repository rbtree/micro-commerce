package com.microcommerce.userservice.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
@Setter
@Getter
@AllArgsConstructor
@Builder
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private UUID token;

    @Column(name = "expiration_date")
    private OffsetDateTime expirationDate;

    @OneToOne(mappedBy = "confirmationToken")
    private User user;

    @Column(name = "used")
    private boolean used;

    public ConfirmationToken() {
        this.token = UUID.randomUUID();
        this.expirationDate = OffsetDateTime.now().plusHours(2);
    }

    public boolean isExpired() {
        return expirationDate.isBefore(OffsetDateTime.now());
    }
}
