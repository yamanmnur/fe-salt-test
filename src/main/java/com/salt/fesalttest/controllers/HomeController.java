package com.salt.fesalttest.controllers;

import com.salt.fesalttest.core.response.GenericListResponse;
import com.salt.fesalttest.core.response.GenericResponse;
import com.salt.fesalttest.dto.data.ConsumersData;
import com.salt.fesalttest.dto.requests.ConsumerRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;


@Controller
@Slf4j
public class HomeController {
    @Value("${spring.application.name}")
    String appName;
    @Value("${salt.be.baseurl}")
    String baseurl;

    private final RestTemplate restTemplate;

    public HomeController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute ConsumerRequest consumerRequest) {

        String apiUrl = baseurl+"/api/v1/consumers";

        // Prepare the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create an HttpEntity with the request body and headers
        HttpEntity<ConsumerRequest> request = new HttpEntity<>(consumerRequest, headers);

        GenericResponse<ConsumersData> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<GenericResponse<ConsumersData>>() {}
        ).getBody();
        assert responseEntity != null;
        log.info("message "+responseEntity.getMetaData().getMessage());
        log.info("message "+consumerRequest.getRegistrationDate());

        return "redirect:/";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        GenericListResponse<ConsumersData> dataConsumers = restTemplate.exchange(
                baseurl+"/api/v1/consumers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<GenericListResponse<ConsumersData>>() {}
        ).getBody();

        assert dataConsumers != null;
        log.info("message "+dataConsumers.getMetaData().getMessage());

        model.addAttribute("appName", appName);
        model.addAttribute("consumers", dataConsumers.getData());
        model.addAttribute("consumerRequest", new ConsumerRequest());

        return "home";
    }
}
