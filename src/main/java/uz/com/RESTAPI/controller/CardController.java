package uz.com.RESTAPI.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.CardDto;
import uz.com.RESTAPI.service.CardService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "card")
public class CardController {

    private final CardService cardService;

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse<CardDto> createCard(@RequestBody @Valid CardDto cardDto){
        return cardService.createCard(cardDto);
    }

    @RequestMapping(method = GET)
    public ApiResponse<CardDto> getCard(@RequestParam(name = "id") Integer cardId){
        return cardService.getCard(cardId);
    }

    @RequestMapping(value = "/get-all", method = GET )
    public ApiResponse<List<CardDto>> getAllCards(){
        return cardService.getAllCards();
    }

}
