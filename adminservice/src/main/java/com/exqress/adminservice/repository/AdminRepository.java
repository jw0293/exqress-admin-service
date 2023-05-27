package com.exqress.adminservice.repository;

import com.exqress.adminservice.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
}
