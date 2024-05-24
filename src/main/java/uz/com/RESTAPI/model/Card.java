package uz.com.RESTAPI.model;

import jakarta.persistence.*;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;
    @Column(nullable = false)
    private String holderName;
    @NotBlank(message = "Card number cannot be null or empty!")
    @Column(unique = true,length = 16,nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    @NotBlank(message = "Card taype cannot be null or empty!")
    private String cardTaype;
    @Column(nullable = false,length = 4,unique = true)
    @NotBlank(message = "Card code cannot be null or empty!")
    private String cardCode;
    @Column(nullable = false)
    @NotNull(message = "User id cannot be null or empty!")
    private Integer userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="userId",insertable = false,updatable = false)
    private User user;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
