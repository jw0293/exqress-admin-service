package com.exqress.adminservice.messagequeue.consumer;

import com.exqress.adminservice.entity.UserEntity;
import com.exqress.adminservice.messagequeue.topic.KafkaTopic;
import com.exqress.adminservice.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @PostConstruct
    public void initMapper(){
        objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = KafkaTopic.CREATE_USER)
    public void assignUser(String kafkaMessage){
        log.info("ComeIn User Assign to Kafka");
        Map<Object, Object> map = new HashMap<>();
        try{
            map = objectMapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException ex){
            ex.printStackTrace();
        }
        UserEntity user = getUserEntity(map);
        userRepository.save(user);
    }

    private UserEntity getUserEntity(Map<Object, Object> map){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId((String) map.get("userId"));
        userEntity.setName((String) map.get("name"));
        userEntity.setPhoneNumber((String) map.get("phoneNumber"));

        return userEntity;
    }
}
