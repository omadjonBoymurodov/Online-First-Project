package uz.com.RESTAPI.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.CardDto;

import java.util.List;

@Service
public interface CardService {

    ApiResponse<CardDto> createCard(CardDto cardDto);
    ApiResponse<CardDto> getCard(Integer cardId);
    ApiResponse<List<CardDto>> getAllCards();
}
