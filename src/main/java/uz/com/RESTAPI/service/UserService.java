package uz.com.RESTAPI.service;

import org.springframework.stereotype.Service;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.UserDto;
import uz.com.RESTAPI.model.User;

import java.util.List;

@Service
public interface UserService {

    ApiResponse<UserDto> createUser(UserDto dto);
    ApiResponse<UserDto> getUser(Integer userId);
    ApiResponse<UserDto> updateUser(Integer userId,UserDto dto);
    ApiResponse<UserDto> deleteUser(Integer userId);
    ApiResponse<List<UserDto>> getAllUsers();
}
