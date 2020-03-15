package com.waracle.cakemanager;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.jpa.CakeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class CakeManagerApplication {

    @Autowired ResourceLoader resourceLoader;

    private static final Logger logger = LoggerFactory.getLogger(CakeManagerApplication.class);
    private static final String JSON_DATA_URL = "cake.json";

    public static void main(String[] args) {
        SpringApplication.run(CakeManagerApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CakeRepository repository) {
        return args -> {
            logger.info("Starting loading cakes json");
            try (InputStream inputStream = resourceLoader
                    .getResource("classpath:/" + JSON_DATA_URL)
                    .getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                List cakeTitles = new ArrayList();

                StringBuffer buffer = new StringBuffer();
                String line = reader.readLine();
                while (line != null) {
                    buffer.append(line);
                    line = reader.readLine();
                }

                logger.debug("parsing cake json");
                JsonParser parser = new JsonFactory().createParser(buffer.toString());
                if (JsonToken.START_ARRAY != parser.nextToken()) {
                    throw new Exception("bad token");
                }

                JsonToken nextToken = parser.nextToken();
                while (nextToken == JsonToken.START_OBJECT) {

                    Cake cake = new Cake();
                    logger.debug(parser.nextFieldName());
                    cake.setName(parser.nextTextValue());

                    logger.debug(parser.nextFieldName());
                    cake.setImageUrl(parser.nextTextValue());

                    logger.debug(parser.nextFieldName());
                    cake.setComment(parser.nextTextValue());

                    logger.debug(parser.nextFieldName());
                    cake.setYumFactor(Integer.parseInt(parser.nextTextValue()));

                    if (!cakeTitles.contains(cake.getName())) {
                        repository.save(cake);
                        cakeTitles.add(cake.getName());
                    }

                    nextToken = parser.nextToken();
                    logger.debug(String.valueOf(nextToken));

                    nextToken = parser.nextToken();
                    logger.debug(String.valueOf(nextToken));
                }

            } catch (Exception ex) {
                logger.error("Loading cake json error");
                throw new Exception(ex);
            }
            logger.info("Finished loading cakes json");
        };
    }

}
