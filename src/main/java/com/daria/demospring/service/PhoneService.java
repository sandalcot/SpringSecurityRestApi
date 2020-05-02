package com.daria.demospring.service;

import com.daria.demospring.model.Phone;

public interface PhoneService {

    Phone register(Phone phone);

    String getVerificationCode();

    Phone findPhoneNumber(String number);

    void sendCodeSms(Phone phone);
}
