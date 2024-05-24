package uz.com.RESTAPI.exseption;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class CustmExseptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> methodArgumentValid(
            MethodArgumentNotValidException e){

        List<ErrorDto> errors = new ArrayList<>();

        List<FieldError> fieldErrors = (List<FieldError>) e.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {

            String fieldName = fieldError.getField();

            String defaultMessage = fieldError.getDefaultMessage();

            String rejactionValue = String.valueOf(fieldError.getRejectedValue());

            errors.add(new ErrorDto(fieldName,String.format("%s,Rejaction Value %s",defaultMessage,rejactionValue)));

        }
        return ResponseEntity.badRequest()
                .body(ApiResponse.<Void>builder()
                        .message("Validation error!")
                        .code(-3)
                        .errorDtoList(errors)
                .build());
    }
}
