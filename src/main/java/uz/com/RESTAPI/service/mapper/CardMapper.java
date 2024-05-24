package uz.com.RESTAPI.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.com.RESTAPI.dto.CardDto;
import uz.com.RESTAPI.model.Card;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CardMapper {
     @Mapping(target = "user",ignore = true)
    @Mapping(target = "cardId", ignore = true)
    public abstract Card toModel(CardDto cardDto);

    @Mapping(target = "user",ignore = true)
    public abstract CardDto toDto(Card card);

    public abstract List<CardDto> convertToDto(List<Card> cards);
}
