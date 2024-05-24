package uz.com.RESTAPI.service.validation;

import org.springframework.stereotype.Component;
import uz.com.RESTAPI.dto.ErrorDto;
import uz.com.RESTAPI.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {

    public List<ErrorDto> userValidate(UserDto userDto){
        List<ErrorDto> errorDtoList = new ArrayList<>();

        if(userDto.getFirstName() == null){
            errorDtoList.add(new ErrorDto("firstName","First name cannot be null or empty!"));
        }
        if(userDto.getSurName() == null){
            errorDtoList.add(new ErrorDto("surName","surName cannot be null or empty!"));
        }
        if(userDto.getPassword() == null){
            errorDtoList.add(new ErrorDto("password","password cannot be null or empty!"));
        }
        if(userDto.getEmile() == null){
            errorDtoList.add(new ErrorDto("emile","Emile cannot be null or empty!"));
        }
        if(userDto.getAge() == null){
            errorDtoList.add(new ErrorDto("age","Age cannot be null or empty!"));
        }
        return errorDtoList;
    }
}
