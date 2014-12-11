package com.tasks.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tasks.config.AppConfig;
import com.tasks.repositories.UserTaskRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class UserTaskRepositoryTest {
	
	@Autowired
	UserTaskRepository utr;

	@Test
	public void contextLoads() {	        
	        utr.findOne(1L);	        
	}

}
