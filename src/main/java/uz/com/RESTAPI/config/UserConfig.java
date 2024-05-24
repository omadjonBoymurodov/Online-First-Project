package uz.com.RESTAPI.config;

import org.apache.catalina.UserDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.com.RESTAPI.dto.UserDto;

@Configuration
public class UserConfig {

    @Bean
    public UserDto getFirstName(){
        return UserDto.builder()
                .firstName("Omadjon")
                .surName("Boymurodov")
                .password("root")
                .emile("omadjon@mail.com")
                .age(24)
                .build();
    }

    @Bean
    public UserDto getsurName(){
        return UserDto.builder()
                .firstName("Mashrabjon")
                .surName("Boymurodov")
                .password("save")
                .emile("mashrabjon@mail.com")
                .age(20)
                .build();
    }
}
