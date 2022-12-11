package com.pasdaven.backend.service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ManagerService {

    final ManagerService managerService;

    public ManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }
}
