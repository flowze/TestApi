package com.example.demo.services;

import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CalorieCalculatorService сalorieCalculatorService;
    private final ModelMapper modelMapper;

    public UserResponseDto createUser(UserRequestDto userRequest){
        User user = modelMapper.map(userRepository, User.class);
        сalorieCalculatorService.calculateDailyCalories(user);
        userRepository.save(user);
        return modelMapper.map(user, UserResponseDto.class);
    }


    @SneakyThrows
    public UserResponseDto getUserById(Long id)  {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));
        return modelMapper.map(user, UserResponseDto.class);
    }
}
