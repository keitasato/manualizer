package com.example.manualizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.manualizer.entity.Content;
import com.example.manualizer.service.ContentService;

import java.util.Date;

@SpringBootApplication
public class ManualizerApplication {
	/** 起動メソッド */
	public static void main(String[] args) {
		SpringApplication.run(ManualizerApplication.class, args);
		// SpringApplication.run(ManualizerApplication.class, args).getBean(ManualizerApplication.class).execute();
	}
	
}
