package com.exqress.adminservice.repository;

import com.exqress.adminservice.entity.QRinfo;
import org.springframework.data.repository.CrudRepository;

public interface QRinfoRepository extends CrudRepository<QRinfo, Long> {

    QRinfo findByQrId(String qrId);
}
