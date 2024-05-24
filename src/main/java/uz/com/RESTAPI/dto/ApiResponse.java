package uz.com.RESTAPI.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean successfully;
    private String message;
    private int code;
    private T model;
    private List<ErrorDto> errorDtoList;
 }
