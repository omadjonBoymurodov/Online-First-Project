package uz.com.RESTAPI.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.CardDto;
import uz.com.RESTAPI.dto.ErrorDto;
import uz.com.RESTAPI.model.Card;
import uz.com.RESTAPI.model.User;
import uz.com.RESTAPI.repository.CardRpository;
import uz.com.RESTAPI.repository.UserRepository;
import uz.com.RESTAPI.service.CardService;
import uz.com.RESTAPI.service.mapper.CardMapper;
import uz.com.RESTAPI.service.validation.CardValidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardServiceIpml implements CardService {

    private final CardMapper cardMapper;
    private final CardValidation cardValidation;
    private final CardRpository cardRpository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<CardDto> createCard(CardDto cardDto) {

        try {
            List<ErrorDto> errors = this.cardValidation.cardValidation(cardDto);

            if (!errors.isEmpty()) {
                return ApiResponse.<CardDto>builder()
                        .successfully(true)
                        .message("Validation error!")
                        .code(-3)
                        .model((CardDto) errors)
                        .build();
            }
            Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(cardDto.getUserId());

            Card card = this.cardMapper.toModel(cardDto);
            card.setCreatedAt(LocalDateTime.now());

            if (optionalUser.isEmpty()) {
                User user = optionalUser.get();
                card.setHolderName(user.getFirstName() + " " + user.getLastName());
            }
            Card savedCard = this.cardRpository.save(card);


            this.cardRpository.save(card);
            return ApiResponse.<CardDto>builder()
                    .successfully(true)
                    .message(String.format("This %d card id successfully created!",
                            savedCard.getCardId()))
                    .code(0)
                    .model(this.cardMapper.toDto(savedCard))
                    .build();
        }catch (Exception e){
         return ApiResponse.<CardDto>builder()
                 .code(-2)
                 .message(String.format("Card while saving error! Message %s", e.getMessage() ))
                 .build();
        }
    }


    @Override
    public ApiResponse<CardDto> getCard(Integer cardId) {
        Optional<Card> card = this.cardRpository.findByCardIdAndDeletedAtIsNull(cardId);

        if (card.isEmpty()) {
            return ApiResponse.<CardDto>builder()
                    .message(String.format("This %d card id not found!", cardId))
                    .code(-1)
                    .build();
        }

        return ApiResponse.<CardDto>builder()
                .successfully(true)
                .message("OK")
                .code(0)
                .model(this.cardMapper.toDto(card.get()))
                .build();
    }

    @Override
    public ApiResponse<List<CardDto>> getAllCards() {
        return ApiResponse.<List<CardDto>>builder()
                .successfully(true)
                .message("OK")
                .code(0)
                .model(this.cardMapper.convertToDto(
                        this.cardRpository.findAllByDeletedAtIsNullOrderByCardId()
                ))
                .build();
    }

}
