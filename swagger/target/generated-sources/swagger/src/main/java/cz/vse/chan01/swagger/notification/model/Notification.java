package cz.vse.chan01.swagger.notification.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Notification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-04T00:31:53.924+01:00")

public class Notification  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("notificationId")
  private String notificationId = null;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    SMS("SMS"),
    
    EMAIL("EMAIL");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String text) {
      for (TypeEnum b : TypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("type")
  private TypeEnum type = null;

  @JsonProperty("creationDate")
  private LocalDate creationDate = null;

  @JsonProperty("source")
  private String source = null;

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("message")
  private String message = null;

  public Notification notificationId(String notificationId) {
    this.notificationId = notificationId;
    return this;
  }

  /**
   * Get notificationId
   * @return notificationId
  **/
  @ApiModelProperty(readOnly = true, value = "")


  public String getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(String notificationId) {
    this.notificationId = notificationId;
  }

  public Notification type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public Notification creationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public Notification source(String source) {
    this.source = source;
    return this;
  }

  /**
   * Get source
   * @return source
  **/
  @ApiModelProperty(value = "")


  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Notification label(String label) {
    this.label = label;
    return this;
  }

  /**
   * Get label
   * @return label
  **/
  @ApiModelProperty(value = "")


  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public Notification message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
  **/
  @ApiModelProperty(value = "")


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Notification notification = (Notification) o;
    return Objects.equals(this.notificationId, notification.notificationId) &&
        Objects.equals(this.type, notification.type) &&
        Objects.equals(this.creationDate, notification.creationDate) &&
        Objects.equals(this.source, notification.source) &&
        Objects.equals(this.label, notification.label) &&
        Objects.equals(this.message, notification.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationId, type, creationDate, source, label, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notification {\n");
    
    sb.append("    notificationId: ").append(toIndentedString(notificationId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

