package com.daria.demospring.service.impl;

import com.daria.demospring.model.Phone;
import com.daria.demospring.repository.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    private Phone phone = new Phone();

    @Test
    void register() {
        phoneService.register(phone);
        verify(phoneRepository).save(phone);
    }

    @Test
    void findPhoneNumber() {
        phoneService.findPhoneNumber(phone.getPhoneNumber());
        verify(phoneRepository).findByPhoneNumber(phone.getPhoneNumber());
    }
}