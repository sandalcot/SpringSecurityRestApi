package com.daria.demospring.repository;

import com.daria.demospring.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Phone findByPhoneNumber(String number);

}
