package com.hufsSchedule.hufsScheduleSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HufsScheduleSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HufsScheduleSystemApplication.class, args);
	}

}
