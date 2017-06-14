package com.travelexperts;

import com.travelexperts.controllers.PackageController;
import com.travelexperts.repositories.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = PackageController.class)
public class RestapiApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}
}
