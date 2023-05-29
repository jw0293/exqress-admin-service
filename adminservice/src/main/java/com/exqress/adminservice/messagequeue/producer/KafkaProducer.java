package com.exqress.adminservice.messagequeue.producer;

import com.exqress.adminservice.kafkadto.KafkaQRinfoToDelivery;
import com.exqress.adminservice.kafkadto.KafkaQRinfoToUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class KafkaProducer {

    private ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    private void initMapper(){
        objectMapper = new ObjectMapper();
    }

    public void sendQRinfoToUserSerivce(String kafkaTopic, KafkaQRinfoToUser kafkaQRinfoToUser){
        String jsonInString = "";
        try{
            jsonInString = objectMapper.writeValueAsString(kafkaQRinfoToUser);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
    }

    public void sendQRinfoToDeliveryService(String kafkaTopic, KafkaQRinfoToDelivery kafkaQRinfoToDelivery){
        String jsonInString = "";
        try{
            jsonInString = objectMapper.writeValueAsString(kafkaQRinfoToDelivery);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        kafkaTemplate.send(kafkaTopic, jsonInString);
    }

}
