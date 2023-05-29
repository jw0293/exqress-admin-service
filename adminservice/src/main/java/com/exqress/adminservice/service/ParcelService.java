package com.exqress.adminservice.service;

import com.exqress.adminservice.entity.QRinfo;
import com.exqress.adminservice.kafkadto.KafkaQRinfo;
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

    public KafkaQRinfo createQRInfo(QRinfo qRinfo){
        KafkaQRinfo kafkaQRinfo = modelMapper.map(qRinfo, KafkaQRinfo.class);
        kafkaQRinfo.setCurState("ready");
        kafkaQRinfo.setUserId(qRinfo.getUserEntity().getUserId());

        return kafkaQRinfo;
    }
}
