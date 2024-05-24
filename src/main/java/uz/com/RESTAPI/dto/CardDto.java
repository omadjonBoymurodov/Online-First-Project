package uz.com.RESTAPI.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Integer cardId;
    private String holderName;
    @NotBlank(message = "Card number cannot be null or empty!")
    @NotNull(message = "Card number be just number!")
    @Digits(integer = 16,fraction = 2)
    private String cardNumber;
    @NotBlank(message = "Card taype cannot be null or empty!")
    private String cardTaype;
    @Digits(integer = 4,fraction = 2)
    private String cardCode;
    @Digits(message = "User id cannot be null or empty!", integer = 1, fraction = 2)
    private Integer userId;

    private UserDto user;
}
