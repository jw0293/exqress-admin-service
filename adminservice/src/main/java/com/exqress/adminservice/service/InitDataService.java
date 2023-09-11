package com.exqress.adminservice.service;

import com.exqress.adminservice.entity.Admin;
import com.exqress.adminservice.entity.QRinfo;
import com.exqress.adminservice.entity.UserEntity;
import com.exqress.adminservice.repository.AdminRepository;
import com.exqress.adminservice.repository.QRinfoRepository;
import com.exqress.adminservice.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class InitDataService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final QRinfoRepository qRinfoRepository;


    @PostConstruct
    private void pushData(){
        Admin admin1 = new Admin();
        admin1.setLoginId("max99kim@naver.com");
        admin1.setName("김중원");
        admin1.setPassword("1234");

        Admin admin2 = new Admin();
        admin2.setLoginId("k38836919@gmail.com");
        admin2.setName("홍길동");
        admin2.setPassword("5678");

        adminRepository.save(admin1);
        adminRepository.save(admin2);

        UserEntity user1 = createUserEntity("user1", "이명박", "010-3515-2531");
        UserEntity user2 = createUserEntity("user2", "박근혜", "010-6312-5332");
        UserEntity user3 = createUserEntity("user3", "문재인", "010-8513-7443");
        UserEntity user4 = createUserEntity("user4", "윤석열", "010-1135-7314");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);

        QRinfo qRinfo1 = getQRInfo("컴퓨터", "1", "서울", "롯데");
        QRinfo qRinfo2 = getQRInfo("마우스", "2", "부산", "CJ");
        QRinfo qRinfo3 = getQRInfo("스피커", "3", "인천", "LG");
        QRinfo qRinfo4 = getQRInfo("모니터", "4", "안양", "SK");
        QRinfo qRinfo5 = getQRInfo("패드", "5", "철원", "OP");
        QRinfo qRinfo6 = getQRInfo("볼펜", "6", "포천", "DS");

        connectUser(qRinfo1, user1);
        connectUser(qRinfo2, user1);
        connectUser(qRinfo3, user1);
        connectUser(qRinfo4, user1);
        connectUser(qRinfo5, user1);
        connectUser(qRinfo6, user1);

        QRinfo qRinfo11 = getQRInfo("컴퓨터", "12", "서울", "롯데");
        QRinfo qRinfo22 = getQRInfo("마우스", "23", "부산", "CJ");
        QRinfo qRinfo33 = getQRInfo("스피커", "34", "인천", "LG");
        QRinfo qRinfo44 = getQRInfo("모니터", "45", "안양", "SK");
        QRinfo qRinfo55 = getQRInfo("패드", "56", "철원", "OP");
        QRinfo qRinfo66 = getQRInfo("볼펜", "67", "포천", "DS");

        connectUser(qRinfo11, user2);
        connectUser(qRinfo22, user2);
        connectUser(qRinfo33, user2);
        connectUser(qRinfo44, user2);
        connectUser(qRinfo55, user2);
        connectUser(qRinfo66, user2);

        QRinfo qRinfo111 = getQRInfo("컴퓨터", "123", "서울", "롯데");
        QRinfo qRinfo222 = getQRInfo("마우스", "234", "부산", "CJ");
        QRinfo qRinfo333 = getQRInfo("스피커", "345", "인천", "LG");
        QRinfo qRinfo444 = getQRInfo("모니터", "456", "안양", "SK");
        QRinfo qRinfo555 = getQRInfo("패드", "567", "철원", "OP");
        QRinfo qRinfo666 = getQRInfo("볼펜", "678", "포천", "DS");

        connectUser(qRinfo111, user3);
        connectUser(qRinfo222, user3);
        connectUser(qRinfo333, user3);
        connectUser(qRinfo444, user3);
        connectUser(qRinfo555, user3);
        connectUser(qRinfo666, user3);

        QRinfo qRinfo1111 = getQRInfo("컴퓨터", "1234", "서울", "롯데");
        QRinfo qRinfo2222 = getQRInfo("마우스", "2345", "부산", "CJ");
        QRinfo qRinfo3333 = getQRInfo("스피커", "3456", "인천", "LG");
        QRinfo qRinfo4444 = getQRInfo("모니터", "4567", "안양", "SK");
        QRinfo qRinfo5555 = getQRInfo("패드", "5678", "철원", "OP");
        QRinfo qRinfo6666 = getQRInfo("볼펜", "6789", "포천", "DS");

        connectUser(qRinfo1111, user4);
        connectUser(qRinfo2222, user4);
        connectUser(qRinfo3333, user4);
        connectUser(qRinfo4444, user4);
        connectUser(qRinfo5555, user4);
        connectUser(qRinfo6666, user4);
    }

    private void connectUser(QRinfo qRinfo, UserEntity user){
        user.assginQRInfo(qRinfo);
        qRinfo.assignedUser(user);

        userRepository.save(user);
        qRinfoRepository.save(qRinfo);
    }

    private QRinfo getQRInfo(String productName, String invoiceNo, String address, String company){
        QRinfo qRinfo = new QRinfo();
        qRinfo.setQrId(UUID.randomUUID().toString());
        qRinfo.setProductName(productName);
        qRinfo.setInvoiceNo(invoiceNo);
        qRinfo.setAddress(address);
        qRinfo.setCompany(company);

        return qRinfo;
    }

    private UserEntity createUserEntity(String userId, String userName, String phoneNumber){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        userEntity.setName(userName);
        userEntity.setPhoneNumber(phoneNumber);

        return userEntity;
    }
}
