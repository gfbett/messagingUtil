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
 * MessagingProducerConfig
 */
@Validated
@javax.annotation.Generated(value = "io.io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T12:50:03.016+02:00")




public class MessagingProducerConfig   {
  @JsonProperty("sizeFrom")
  private Integer sizeFrom = null;

  @JsonProperty("sizeTo")
  private Integer sizeTo = null;

  @JsonProperty("quantity")
  private Integer quantity = null;

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

  public MessagingProducerConfig sizeFrom(Integer sizeFrom) {
    this.sizeFrom = sizeFrom;
    return this;
  }

  /**
   * Get sizeFrom
   * @return sizeFrom
  **/
  @ApiModelProperty(value = "")


  public Integer getSizeFrom() {
    return sizeFrom;
  }

  public void setSizeFrom(Integer sizeFrom) {
    this.sizeFrom = sizeFrom;
  }

  public MessagingProducerConfig sizeTo(Integer sizeTo) {
    this.sizeTo = sizeTo;
    return this;
  }

  /**
   * Get sizeTo
   * @return sizeTo
  **/
  @ApiModelProperty(value = "")


  public Integer getSizeTo() {
    return sizeTo;
  }

  public void setSizeTo(Integer sizeTo) {
    this.sizeTo = sizeTo;
  }

  public MessagingProducerConfig quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * Get quantity
   * @return quantity
  **/
  @ApiModelProperty(value = "")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public MessagingProducerConfig provider(ProviderEnum provider) {
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

  public MessagingProducerConfig properties(Map<String, String> properties) {
    this.properties = properties;
    return this;
  }

  public MessagingProducerConfig putPropertiesItem(String key, String propertiesItem) {
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
    MessagingProducerConfig messagingProducerConfig = (MessagingProducerConfig) o;
    return Objects.equals(this.sizeFrom, messagingProducerConfig.sizeFrom) &&
        Objects.equals(this.sizeTo, messagingProducerConfig.sizeTo) &&
        Objects.equals(this.quantity, messagingProducerConfig.quantity) &&
        Objects.equals(this.provider, messagingProducerConfig.provider) &&
        Objects.equals(this.properties, messagingProducerConfig.properties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sizeFrom, sizeTo, quantity, provider, properties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessagingProducerConfig {\n");

    sb.append("    sizeFrom: ").append(toIndentedString(sizeFrom)).append("\n");
    sb.append("    sizeTo: ").append(toIndentedString(sizeTo)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

