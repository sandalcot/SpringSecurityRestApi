package com.daria.demospring.service.impl;

import com.daria.demospring.model.Phone;
import com.daria.demospring.repository.PhoneRepository;
import com.daria.demospring.service.PhoneService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @PostConstruct
    public void twilioInit() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public Phone register(Phone phone) {
        phoneRepository.save(phone);
        phoneRepository.flush();
        log.info("In save - phone: {} successfully saved", phone);
        return phone;
    }

    @Override
    public String getVerificationCode() {
        return new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public Phone findPhoneNumber(String number) {
        Phone phone = phoneRepository.findByPhoneNumber(number);
        log.info("In findByPhoneNumber - phone: {} found by phoneNumber: {}", phone, number);
        return phone;
    }

    @Override
    public void sendCodeSms(Phone phone) {
        Message.creator(
                new PhoneNumber(phone.getPhoneNumber()),
                new PhoneNumber(twilioPhoneNumber),
                "Your registration code: " + phone.getVerificationCode())
                .create();
    }
}
