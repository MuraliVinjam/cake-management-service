package com.waracle.cakemanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ResourceLoader;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.cakemanager.jpa.Cake;
import com.waracle.cakemanager.jpa.CakeRepository;

@SpringBootApplication
public class CakeManagerApplication extends SpringBootServletInitializer {

    @Autowired
    ResourceLoader resourceLoader;

    private static final Logger logger = LoggerFactory.getLogger(CakeManagerApplication.class);
    private static final String JSON_DATA_URL = "cake.json";

    public static void main(String[] args) {
        SpringApplication.run(CakeManagerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CakeManagerApplication.class);
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

                    Cake cake = Cake.builder().build();
                    logger.debug(parser.nextFieldName());
                    cake.setTitle(parser.nextTextValue());

                    logger.debug(parser.nextFieldName());
                    cake.setImage(parser.nextTextValue());

                    logger.debug(parser.nextFieldName());
                    cake.setDescription(parser.nextTextValue());

                    if (!cakeTitles.contains(cake.getTitle())) {
                        repository.save(cake);
                        cakeTitles.add(cake.getTitle());
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
