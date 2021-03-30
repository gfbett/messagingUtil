package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MessagingConsumerConfig
 */
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T12:50:03.016+02:00")




public class MessagingConsumerConfig   {
  /**
   * Gets or Sets provider
   */
  public enum ProviderEnum {
    ZEROMQ("ZEROMQ"),
    
    KAFKA("KAFKA"),
    
    PULSAR("PULSAR"),
    
    RABBIT("RABBIT"),
    
    IBM("IBM");

    private String value;

    ProviderEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProviderEnum fromValue(String text) {
      for (ProviderEnum b : ProviderEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("provider")
  private ProviderEnum provider = null;

  @JsonProperty("properties")
  @Valid
  private Map<String, String> properties = null;

  public MessagingConsumerConfig provider(ProviderEnum provider) {
    this.provider = provider;
    return this;
  }

  /**
   * Get provider
   * @return provider
  **/
  @ApiModelProperty(value = "")


  public ProviderEnum getProvider() {
    return provider;
  }

  public void setProvider(ProviderEnum provider) {
    this.provider = provider;
  }

  public MessagingConsumerConfig properties(Map<String, String> properties) {
    this.properties = properties;
    return this;
  }

  public MessagingConsumerConfig putPropertiesItem(String key, String propertiesItem) {
    if (this.properties == null) {
      this.properties = new HashMap<String, String>();
    }
    this.properties.put(key, propertiesItem);
    return this;
  }

  /**
   * Get properties
   * @return properties
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, String> properties) {
    this.properties = properties;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessagingConsumerConfig messagingConsumerConfig = (MessagingConsumerConfig) o;
    return Objects.equals(this.provider, messagingConsumerConfig.provider) &&
        Objects.equals(this.properties, messagingConsumerConfig.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(provider, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessagingConsumerConfig {\n");

    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

