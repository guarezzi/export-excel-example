package com.example.exportExcel.services;

import java.io.IOException;
import java.util.List;

import com.example.exportExcel.DTO.ExampleDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GenerateExcelServiceTests {

	@Autowired
	GenerateExcelService service;

	@Test
	void contextLoads() throws IOException {
		ExampleDTO example = new ExampleDTO();
		example.setId(1L);
		example.setName("Luigi");
		example.setCountry("Italy");

		this.service.generate(List.of(example));
	}

}
