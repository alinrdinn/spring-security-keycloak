package org.ali.keycloakspring.springbootkeycloak.proxies;

import java.util.Map;

import org.ali.keycloakspring.springbootkeycloak.dto.TokenDTO;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(name="refresh-token", url = "localhost:8080", configuration = RefreshTokenProxy.Configuration.class)
public interface RefreshTokenProxy {
    
    @PostMapping(value = "/realms/{realm}/protocol/openid-connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    TokenDTO getRefreshToken(@PathVariable String realm, @RequestBody Map<String, String> form);
    
    class Configuration {
        @Bean
        Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }
    
}
