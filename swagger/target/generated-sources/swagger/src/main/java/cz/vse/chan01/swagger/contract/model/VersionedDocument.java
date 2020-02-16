package cz.vse.chan01.swagger.contract.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.io.Serializable;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VersionedDocument
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-02-16T23:38:49.717+01:00")

public class VersionedDocument  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("versionedDocumentId")
  private String versionedDocumentId = null;

  @JsonProperty("date")
  private java.time.LocalDateTime date = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("content")
  private String content = null;

  /**
   * Gets or Sets format
   */
  public enum FormatEnum {
    PDF("PDF"),
    
    DOCX("DOCX"),
    
    TXT("TXT"),
    
    XML("XML");

    private String value;

    FormatEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static FormatEnum fromValue(String text) {
      for (FormatEnum b : FormatEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("format")
  private FormatEnum format = null;

  public VersionedDocument versionedDocumentId(String versionedDocumentId) {
    this.versionedDocumentId = versionedDocumentId;
    return this;
  }

  /**
   * Get versionedDocumentId
   * @return versionedDocumentId
  **/
  @ApiModelProperty(readOnly = true, value = "")


  public String getVersionedDocumentId() {
    return versionedDocumentId;
  }

  public void setVersionedDocumentId(String versionedDocumentId) {
    this.versionedDocumentId = versionedDocumentId;
  }

  public VersionedDocument date(java.time.LocalDateTime date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")

  @Valid

  public java.time.LocalDateTime getDate() {
    return date;
  }

  public void setDate(java.time.LocalDateTime date) {
    this.date = date;
  }

  public VersionedDocument version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(value = "")

@Pattern(regexp="^\\d+\\.\\d+\\.\\d+$") 
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public VersionedDocument content(String content) {
    this.content = content;
    return this;
  }

  /**
   * Get content
   * @return content
  **/
  @ApiModelProperty(value = "")


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public VersionedDocument format(FormatEnum format) {
    this.format = format;
    return this;
  }

  /**
   * Get format
   * @return format
  **/
  @ApiModelProperty(value = "")


  public FormatEnum getFormat() {
    return format;
  }

  public void setFormat(FormatEnum format) {
    this.format = format;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VersionedDocument versionedDocument = (VersionedDocument) o;
    return Objects.equals(this.versionedDocumentId, versionedDocument.versionedDocumentId) &&
        Objects.equals(this.date, versionedDocument.date) &&
        Objects.equals(this.version, versionedDocument.version) &&
        Objects.equals(this.content, versionedDocument.content) &&
        Objects.equals(this.format, versionedDocument.format);
  }

  @Override
  public int hashCode() {
    return Objects.hash(versionedDocumentId, date, version, content, format);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VersionedDocument {\n");
    
    sb.append("    versionedDocumentId: ").append(toIndentedString(versionedDocumentId)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    format: ").append(toIndentedString(format)).append("\n");
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

