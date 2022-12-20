package br.com.erudio.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonMapper {
	  @Bean
	  public ObjectMapper build() {
		 
		 var objectMapper = new ObjectMapper();
		     objectMapper.registerModule(new SimpleModule());

		     objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		     objectMapper.configure(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID, false);
		     objectMapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
		    /*final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		     objectMapper.setDateFormat(dateFormat);*/
		 return objectMapper;
	  }
}
