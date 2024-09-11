package com.mycompany.acctdatpkg.cucumber;

import com.mycompany.acctdatpkg.AcctdatmicroApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = AcctdatmicroApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
