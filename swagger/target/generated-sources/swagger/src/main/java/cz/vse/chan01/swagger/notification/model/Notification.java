package cz.vse.chan01.swagger.notification.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import cz.vse.chan01.swagger.notification.model.NotificationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Notification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-11T17:13:58.603+01:00")

public class Notification  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("notificationId")
  private String notificationId = null;

  @JsonProperty("notificationType")
  @Valid
  private List<NotificationType> notificationType = null;

  @JsonProperty("creationDate")
  private LocalDate creationDate = null;

  @JsonProperty("source")
  private String source = null;

  @JsonProperty("label")
  private String label = null;

  @JsonProperty("message")
  private String message = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    REQUESTED("REQUESTED"),
    
    SENT("SENT");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

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

  public Notification notificationType(List<NotificationType> notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  public Notification addNotificationTypeItem(NotificationType notificationTypeItem) {
    if (this.notificationType == null) {
      this.notificationType = new ArrayList<>();
    }
    this.notificationType.add(notificationTypeItem);
    return this;
  }

  /**
   * Get notificationType
   * @return notificationType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<NotificationType> getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(List<NotificationType> notificationType) {
    this.notificationType = notificationType;
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

  public Notification status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
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
        Objects.equals(this.notificationType, notification.notificationType) &&
        Objects.equals(this.creationDate, notification.creationDate) &&
        Objects.equals(this.source, notification.source) &&
        Objects.equals(this.label, notification.label) &&
        Objects.equals(this.message, notification.message) &&
        Objects.equals(this.status, notification.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationId, notificationType, creationDate, source, label, message, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Notification {\n");
    
    sb.append("    notificationId: ").append(toIndentedString(notificationId)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

