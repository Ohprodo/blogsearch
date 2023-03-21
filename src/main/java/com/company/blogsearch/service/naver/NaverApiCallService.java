package com.company.blogsearch.service.naver;

import com.company.blogsearch.constant.ErrorCode;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallResponseDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallRequestDto;
import com.company.blogsearch.dto.naver.client.NaverApiCallResponseDto;
import com.company.blogsearch.exception.GeneralException;
import com.company.blogsearch.properties.NaverApiProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.company.blogsearch.constant.Const.NAVER_HEADER_ID_KEY;
import static com.company.blogsearch.constant.Const.NAVER_HEADER_SECRET_KEY;

@Slf4j
@Service
public class NaverApiCallService {

    private final RestTemplate restTemplate;
    private final NaverApiProperties naverApiProperties;

    public NaverApiCallService(RestTemplate restTemplate, NaverApiProperties naverApiProperties) {
        this.restTemplate = restTemplate;
        this.naverApiProperties = naverApiProperties;
    }

    public String httpCall(Map<String, Object> params) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(NAVER_HEADER_ID_KEY, naverApiProperties.getClientId());
            headers.set(NAVER_HEADER_SECRET_KEY, naverApiProperties.getClientSecret());

            HttpEntity<Object> request = new HttpEntity<>(headers);
            String searchUrl = naverApiProperties.getBaseUrl() + naverApiProperties.getSearchApi();

            return restTemplate.exchange(searchUrl + "?" + mapToUrlParam(params), HttpMethod.GET, request, String.class).getBody();
        } catch (Exception e) {
            log.error("[NaverApiCallService][httpCall] Exception : {}", e.getMessage());
            throw new GeneralException(ErrorCode.NAVER_HTTP_CONN_ERROR, e);
        }
    }

    public NaverApiCallResponseDto callSearchApi(NaverApiCallRequestDto naverApiCallRequestDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> params = objectMapper.convertValue(naverApiCallRequestDto, Map.class);
            String response = httpCall(params);

            Gson gson = new Gson();
            return gson.fromJson(response, NaverApiCallResponseDto.class);
        } catch (GeneralException e) {
            log.error("[NaverApiCallService][callSearchApi] GeneralException : {}", e.getErrorCode().toString());
            throw e;
        }
    }

    private static String mapToUrlParam(Map<String, Object> params) {
        StringBuilder paramData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (paramData.length() != 0) {
                paramData.append('&');
            }
            paramData.append(param.getKey());
            paramData.append('=');
            paramData.append(String.valueOf(param.getValue()));
        }
        return paramData.toString();
    }
}
