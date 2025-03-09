package com.lamdnt.cryptotrading.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HttpHelper {
  private final ObjectMapper objectMapper;

  public String buildParam(List<String> params) {
    try {
      return objectMapper.writeValueAsString(params);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
