package com.company.blogsearch.service.kakao;

import com.company.blogsearch.constant.ErrorCode;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallRequestDto;
import com.company.blogsearch.dto.kakao.client.KakaoApiCallResponseDto;
import com.company.blogsearch.exception.GeneralException;
import com.company.blogsearch.properties.KakaoApiProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.company.blogsearch.constant.Const.KAKAO_HEADER_KEY;
import static com.company.blogsearch.constant.Const.KAKAO_HEADER_VALUE_PREFIX;

@Slf4j
@Service
public class KakaoApiCallService {

    private final RestTemplate restTemplate;
    private final KakaoApiProperties kakaoApiProperties;

    public KakaoApiCallService(RestTemplate restTemplate, KakaoApiProperties kakaoApiProperties) {
        this.restTemplate = restTemplate;
        this.kakaoApiProperties = kakaoApiProperties;
    }

    public String httpCall(Map<String, Object> params) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(KAKAO_HEADER_KEY, KAKAO_HEADER_VALUE_PREFIX + kakaoApiProperties.getRestApiKey());
            HttpEntity<Object> request = new HttpEntity<>(headers);
            String searchUrl = kakaoApiProperties.getBaseUrl() + kakaoApiProperties.getSearchApi();

            return restTemplate.exchange(searchUrl + "?" + mapToUrlParam(params), HttpMethod.GET, request, String.class).getBody();
        } catch (Exception e) {
            log.error("[KakaoApiCallService][httpCall] Exception : {}", e.getMessage());
            throw new GeneralException(ErrorCode.KAKAO_HTTP_CONN_ERROR, e);
        }
    }

    public KakaoApiCallResponseDto callSearchApi(KakaoApiCallRequestDto kakaoApiCallRequestDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> params = objectMapper.convertValue(kakaoApiCallRequestDto, Map.class);
            String response = httpCall(params);

            Gson gson = new Gson();
            return gson.fromJson(response, KakaoApiCallResponseDto.class);
        } catch (GeneralException e) {
            log.info("[KakaoApiCallService][callSearchApi] GeneralException : {}", e.getErrorCode().toString());
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
