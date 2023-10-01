package com.proyect.employee.employee.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.employee.employee.database.config.EnvironmentConfig;
import com.proyect.employee.employee.database.model.PermitData;
import com.proyect.employee.employee.entities.Permission;
import com.proyect.employee.employee.entities.Role;
import com.proyect.employee.employee.entities.enums.EPermission;
import com.proyect.employee.employee.mappers.PermitDataMapper;
import com.proyect.employee.employee.repositories.PermitRepository;
import com.proyect.employee.employee.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
@Slf4j
public class InitDatabase implements InitializingBean {

    PermitData [] permitJson;
    @Autowired
    private PermitRepository permitRepository;
    @Autowired
    private PermitDataMapper permitDataMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private EnvironmentConfig environmentConfig;

    public void validatePermit(){
        Set<String> idData = new HashSet<>();
        int cantValidPermit = EPermission.values().length;
        if(permitJson.length != cantValidPermit){
            throw new RuntimeException(String.format("The files do not contain the same number of permissions valid-permit => %d | permit => %d", cantValidPermit, permitJson.length));
        }
        for (PermitData permit : permitJson) {
            String code = permit.getCode();
            if(EPermission.contains(code)){
                if (idData.contains(code))throw new RuntimeException("Permissions are repeated");
                idData.add(code);
            }else {
                throw new RuntimeException("Permit not found with code '" + permit.getCode() + " - " + permit.getName() + "'");
            }
        }
        log.info("Permissions are correct");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        chargePermitData();
        validatePermit();
        registerPermits();
        initUser();
    }

    private void chargePermitData() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:assets/permits.json");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        permitJson = objectMapper.readValue(inputStream, PermitData[].class);
    }

    private void registerPermits(){
        List<Permission> permissionData = Arrays.stream(permitJson).map(permitDataMapper).toList();
        List<Permission> savePermission =  new ArrayList<>();
        permissionData.forEach(permit -> {
            if (!permitRepository.existsByCode(permit.getCode())) {
                savePermission.add(permit);
            }
        });
        permitRepository.saveAll(savePermission);
        log.info("Permissions registered successfully");
    }


    @Transactional
    private void initUser(){
        Permission permission = permitRepository.findByCode(EPermission.SUPER_ADMIN);
        String roleAdmin = environmentConfig.getRole_admin();
        String userAdmin = environmentConfig.getUser_admin();
        Role role = roleRepository.findByName(roleAdmin);
        if(role == null){
            role = roleRepository.save(new Role(roleAdmin,Set.of(permission)));
        }

    }
}
