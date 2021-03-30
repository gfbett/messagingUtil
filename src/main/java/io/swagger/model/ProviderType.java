package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ProviderName
 */
public enum ProviderType {
  
  ZEROMQ("ZEROMQ"),
  
  KAFKA("KAFKA"),
  
  PULSAR("PULSAR"),
  
  RABBIT("RABBIT"),
  
  IBM("IBM");

  private String value;

  ProviderType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ProviderType fromValue(String text) {
    for (ProviderType b : ProviderType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

