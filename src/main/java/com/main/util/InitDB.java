package com.main.util;

import com.main.persistence.domain.Role;
import com.main.persistence.domain.Test;
import com.main.persistence.repository.RoleRepository;
import com.main.persistence.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * created by ersya 30/03/2019
 */
@PropertySource("classpath:data.properties")
@Component
public class InitDB {
    public static final Logger logger = LoggerFactory.getLogger(InitDB.class);

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TestRepository testRepository;

    @Value("#{'${role.name}'.split(',')}")
    List<String> roleNames;

    @PostConstruct
    public void init(){
        initRoles();
//        initTest();
    }

    @Transactional
    public void initRoles(){
        logger.info("---------- Init Roles ----------");
        List<Role> addRoles = new ArrayList<>();
        for(String roleName  : roleNames){
            if(!roleRepository.findByName(roleName).isPresent()){
                logger.info("--------"+roleName + " doesn't exists, creating new one ---");
                Role role = new Role();
                role.setName(roleName);
                addRoles.add(role);
            }
        }
        if(addRoles.size() > 0){
            roleRepository.saveAll(addRoles);
            logger.info("{} roles has been created", addRoles.size());
        }
        logger.info("--------------------------------");
    }

    @Transactional
    public void initTest(){
        logger.info("============================ INIT TEST DOMAIN DATA ============================");
        if(testRepository.findAll().size() != 100000){
            for(int i=0; i < 100000; i++){
                testRepository.save(new Test());
            }
        }

    }
}
