package com.blank.ilia;

import com.blank.ilia.service.CityService;
import com.blank.ilia.service.ClientService;
import com.blank.ilia.utils.CityUtils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IliaApplicationTests {
	public Faker faker = new Faker();
	@Autowired
	protected CityService cityService;
	@Autowired
	protected ClientService clientService;
	@Test
	void contextLoads() {
	}

}
