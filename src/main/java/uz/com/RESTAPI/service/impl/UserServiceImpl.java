package uz.com.RESTAPI.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.com.RESTAPI.dto.ApiResponse;
import uz.com.RESTAPI.dto.ErrorDto;
import uz.com.RESTAPI.dto.UserDto;
import uz.com.RESTAPI.model.Card;
import uz.com.RESTAPI.model.User;
import uz.com.RESTAPI.repository.CardRpository;
import uz.com.RESTAPI.repository.UserRepository;
import uz.com.RESTAPI.service.UserService;
import uz.com.RESTAPI.service.mapper.UserMapper;
import uz.com.RESTAPI.service.validation.UserValidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private  final UserRepository userRepository;
    private final CardRpository cardRpository;


    @Override
    public ApiResponse<UserDto> createUser(UserDto dto) {

        try {

            UserDto userDto = this.userMapper.toDto(
                    this.userRepository.save(
                            this.userMapper.toModel(dto)));

            return ApiResponse.<UserDto>builder()
                    .successfully(true)
                    .message("OK")
                    .code(0)
                    .model(userDto)
                    .build();

        } catch (Exception e){
          return ApiResponse.<UserDto>builder()
                  .code(-2)
                  .message(String.format("User while saving error.Massage %s",e.getMessage()))
                  .build();
        }
    }

    @Override
    public ApiResponse<UserDto> getUser(Integer userId) {

        Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(userId);

        if(optionalUser.isEmpty()){
            return ApiResponse.<UserDto>builder()
                    .message(String.format("This %d user id not found!",userId))
                    .code(-1)
                    .build();
        }
        return ApiResponse.<UserDto>builder()
                .successfully(true)
                .message("OK")
                .code(0)
                .model(this.userMapper.toDtoWithCard(optionalUser.get()))
                .build();
    }

    @Override
    public ApiResponse<UserDto> updateUser(Integer userId, UserDto dto) {

        try {

            Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(userId);
            if(optionalUser.isEmpty()){
                return ApiResponse.<UserDto>builder()
                        .message(String.format("This %d user id not found!",userId))
                        .code(-1)
                        .build();
            }

            User savedUser = this.userRepository.save(this.userMapper.updateUserAllFields(optionalUser.get(), dto));

            List<Card> cardList = this.cardRpository.findAllByUserIdAndDeletedAtIsNull(savedUser.getUserId());
            for (Card card : cardList){
                int cardIndex = cardList.indexOf(card);
                String newHolderName = savedUser.getFirstName() + " " + savedUser.getLastName();
                card.setHolderName(newHolderName);
                cardList.set(cardIndex,card);
            }
            this.cardRpository.saveAll(cardList);

            return ApiResponse.<UserDto>builder()
                    .successfully(true)
                    .message("OK")
                    .code(0)
                    .model(this.userMapper.toDto(savedUser))
                    .build();

        }catch (Exception e){

            return ApiResponse.<UserDto>builder()
                    .code(-2)
                    .message(String.format("User while updaeting error.Massage %s",e.getMessage()))
                    .build();

        }
    }


    @Override
    public ApiResponse<UserDto> deleteUser(Integer userId) {
        try {
            Optional<User> optionalUser = this.userRepository.findByUserIdAndDeletedAtIsNull(userId);
            if(optionalUser.isEmpty()){
                return ApiResponse.<UserDto>builder()
                        .message(String.format("This %d user id not found!",userId))
                        .code(-1)
                        .build();
            }
            User user = optionalUser.get();
            user.setDeletedAt(LocalDateTime.now());
            User savedUser = this.userRepository.save(user);
            return ApiResponse.<UserDto>builder()
                    .successfully(true)
                    .message(String.format("This %d user id successfully deleted!",user.getUserId()))
                    .code(0)
                    .model(this.userMapper.toDto(savedUser))
                    .build();

        }catch (Exception e){
            return ApiResponse.<UserDto>builder()
                    .code(-2)
                    .message(String.format("User while deleting error.Massage %s",e.getMessage()))
                    .build();
        }
    }

    @Override
    public ApiResponse<List<UserDto>> getAllUsers() {
        return ApiResponse.<List<UserDto>>builder()
                .successfully(true)
                .message("OK")
                .code(0)
                .model(this.userMapper.convertList(
                        this.userRepository.findAllByDeletedAtIsNull()))
                .build();
    }
    }

