package com.exqress.adminservice.service;

import com.exqress.adminservice.entity.QRinfo;
import com.exqress.adminservice.entity.UserEntity;
import com.exqress.adminservice.kafkadto.KafkaQRinfoToDelivery;
import com.exqress.adminservice.kafkadto.KafkaQRinfoToUser;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ParcelService {

    private ModelMapper modelMapper;

    @PostConstruct
    public void initMapper(){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public KafkaQRinfoToUser createQRInfoToUserService(QRinfo qRinfo){
        KafkaQRinfoToUser kafkaQRinfo = modelMapper.map(qRinfo, KafkaQRinfoToUser.class);
        kafkaQRinfo.setCurState("ready");
        kafkaQRinfo.setUserId(qRinfo.getUserEntity().getUserId());

        return kafkaQRinfo;
    }

    public KafkaQRinfoToDelivery createQRInfoToDeliveryService(QRinfo qRinfo, UserEntity user){
        KafkaQRinfoToDelivery kafkaQRinfoToDelivery = modelMapper.map(qRinfo, KafkaQRinfoToDelivery.class);
        kafkaQRinfoToDelivery.setCurState("ready");
        kafkaQRinfoToDelivery.setReceiverName(user.getName());
        kafkaQRinfoToDelivery.setReceiverPhoneNumber(user.getPhoneNumber());

        return kafkaQRinfoToDelivery;
    }
}
