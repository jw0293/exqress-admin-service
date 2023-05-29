package com.exqress.adminservice.controller;

import com.exqress.adminservice.entity.QRinfo;
import com.exqress.adminservice.entity.UserEntity;
import com.exqress.adminservice.kafkadto.KafkaQRinfo;
import com.exqress.adminservice.messagequeue.producer.KafkaProducer;
import com.exqress.adminservice.messagequeue.topic.KafkaTopic;
import com.exqress.adminservice.repository.QRinfoRepository;
import com.exqress.adminservice.repository.UserRepository;
import com.exqress.adminservice.service.ParcelService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/parcel")
public class ParcelController {

    private final KafkaProducer kafkaProducer;
    private final ParcelService parcelService;
    private final UserRepository userRepository;
    private final QRinfoRepository qRinfoRepository;

    @GetMapping("/{userId}/assign")
    public String inputParcelForm(@PathVariable String userId, Model model) {
        log.info("Get User Id : {}", userId);
        //model.addAttribute("userId", userId);
        model.addAttribute("qRinfo", new QRinfo());
        return "form/inputParcel";
    }

    @PostMapping("/{userId}/assign")
    public String savePacel(@PathVariable String userId, @ModelAttribute QRinfo qRinfo, RedirectAttributes redirectAttributes) {
        qRinfo.setQrId(UUID.randomUUID().toString());

        /* Log
        log.info("User Id :{}", userId);
        log.info("Parcel invoiceNo : {}", qRinfo.getInvoiceNo());
        log.info("Parcel productName : {}", qRinfo.getProductName());
        log.info("Parcel address : {}", qRinfo.getAddress());
        log.info("Parcel QR ID : {}", qRinfo.getQrId());
        */
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.assginQRInfo(qRinfo);
        qRinfo.assignedUser(userEntity);

        userRepository.save(userEntity);
        qRinfoRepository.save(qRinfo);

        KafkaQRinfo kafkaQRinfo = parcelService.createQRInfo(qRinfo);
        kafkaProducer.sendQRinfo(KafkaTopic.QRINFO_TO_USER_SERVICE, kafkaQRinfo);

        return "redirect:/users";
    }

    @GetMapping("/{qrId}")
    public String createQRcode(@PathVariable String qrId, Model model) throws IOException, WriterException {
        log.info("Create QRcode : {}", qrId);
        String qrCodeImage = getQRCodeImage(qrId, 200, 200);
        model.addAttribute("img", qrCodeImage);

        return "form/qrCode";
    }

    private String getQRCodeImage(String qrId, int width, int height) throws WriterException, IOException{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrId, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutPutStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutPutStream);

        return Base64.getEncoder().encodeToString(pngOutPutStream.toByteArray());
    }
}
