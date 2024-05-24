package uz.com.RESTAPI.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.UserDto;
import uz.com.RESTAPI.model.User;
import uz.com.RESTAPI.repository.UserRepository;
import uz.com.RESTAPI.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "user")
public class UserController {

   private UserService userService;
   private UserRepository userRepository;


   public UserController(UserService userService){
       this.userService = userService;
   }

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponse<UserDto> createUser(@RequestBody @Valid UserDto dto){
     return this.userService.createUser(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ApiResponse<UserDto> getUser(@RequestParam(name = "id") Integer userId){
        return this.userService.getUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ApiResponse<UserDto> updateUser(@RequestParam(name = "id") Integer userId,
                                        @RequestBody UserDto dto){
     return this.userService.updateUser(userId,dto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ApiResponse<UserDto> deleteUser(@RequestParam(name = "id") Integer userId){
        return this.userService.deleteUser(userId);
    }

    @RequestMapping(value = "/get-value",method = RequestMethod.GET)
    public ApiResponse<List<UserDto>> getAllUser(){
       return this.userService.getAllUsers();
    }
}
