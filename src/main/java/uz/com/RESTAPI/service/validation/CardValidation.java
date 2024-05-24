package uz.com.RESTAPI.service.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.com.RESTAPI.dto.CardDto;
import uz.com.RESTAPI.dto.ErrorDto;
import uz.com.RESTAPI.repository.UserRepository;
import uz.com.RESTAPI.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CardValidation {

    private final UserRepository userRepository;

    public List<ErrorDto> cardValidation(CardDto cardDto){
        List<ErrorDto> errorDtoList = new ArrayList<>();
        if(!userRepository.existsByUserIdAndDeletedAtIsNull(cardDto.getUserId())){
            errorDtoList.add(new ErrorDto("userId",String.format("This %d user id not found!",cardDto.getUserId())));
        }

        return errorDtoList;
    }
}
