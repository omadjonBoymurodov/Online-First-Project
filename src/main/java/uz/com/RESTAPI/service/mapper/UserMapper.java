package uz.com.RESTAPI.service.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import uz.com.RESTAPI.dto.UserDto;
import uz.com.RESTAPI.model.User;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected CardMapper cardMapper;

    @Mapping(target = "userId",ignore = true)
    @Mapping(target = "lastName",source = "surName")
    public abstract User toModel(UserDto userDto);

    @Mapping(target = "cards",ignore = true)
    @Mapping(target = "surName",source = "lastName")
    public abstract UserDto toDto(User user);

    @Mapping(target = "surName", source = "lastName")
    @Mapping(target = "cards",expression = "java(this.cardMapper.convertToDto(user.getCardList()))")
    public abstract UserDto toDtoWithCard(User user);


    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "lastName",source = "surName")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User updateUserAllFields(@MappingTarget User user, UserDto dto);

  public  List<UserDto> convertList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(this.toDto(user));
        }
        return userDtos;
    }
}
