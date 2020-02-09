package cz.vse.chan01.swagger.document.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import cz.vse.chan01.swagger.document.model.VersionedDocument;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Document
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-02-09T22:14:12.998+01:00")

public class Document   {
  @JsonProperty("documentId")
  private Long documentId = null;

  @JsonProperty("name")
  private String name = null;

  /**
   * Gets or Sets contractType
   */
  public enum ContractTypeEnum {
    A("A"),
    
    B("B"),
    
    C("C"),
    
    D("D"),
    
    E("E");

    private String value;

    ContractTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ContractTypeEnum fromValue(String text) {
      for (ContractTypeEnum b : ContractTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("contractType")
  private ContractTypeEnum contractType = null;

  @JsonProperty("versions")
  @Valid
  private List<VersionedDocument> versions = null;

  /**
   * Gets or Sets documentStatus
   */
  public enum DocumentStatusEnum {
    CREATED("CREATED"),
    
    ARCHIVED("ARCHIVED");

    private String value;

    DocumentStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DocumentStatusEnum fromValue(String text) {
      for (DocumentStatusEnum b : DocumentStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("documentStatus")
  private DocumentStatusEnum documentStatus = null;

  @JsonProperty("creationDate")
  private LocalDate creationDate = null;

  @JsonProperty("archivationDate")
  private LocalDate archivationDate = null;

  public Document documentId(Long documentId) {
    this.documentId = documentId;
    return this;
  }

  /**
   * Get documentId
   * @return documentId
  **/
  @ApiModelProperty(value = "")


  public Long getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  public Document name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Document contractType(ContractTypeEnum contractType) {
    this.contractType = contractType;
    return this;
  }

  /**
   * Get contractType
   * @return contractType
  **/
  @ApiModelProperty(value = "")


  public ContractTypeEnum getContractType() {
    return contractType;
  }

  public void setContractType(ContractTypeEnum contractType) {
    this.contractType = contractType;
  }

  public Document versions(List<VersionedDocument> versions) {
    this.versions = versions;
    return this;
  }

  public Document addVersionsItem(VersionedDocument versionsItem) {
    if (this.versions == null) {
      this.versions = new ArrayList<>();
    }
    this.versions.add(versionsItem);
    return this;
  }

  /**
   * Get versions
   * @return versions
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<VersionedDocument> getVersions() {
    return versions;
  }

  public void setVersions(List<VersionedDocument> versions) {
    this.versions = versions;
  }

  public Document documentStatus(DocumentStatusEnum documentStatus) {
    this.documentStatus = documentStatus;
    return this;
  }

  /**
   * Get documentStatus
   * @return documentStatus
  **/
  @ApiModelProperty(value = "")


  public DocumentStatusEnum getDocumentStatus() {
    return documentStatus;
  }

  public void setDocumentStatus(DocumentStatusEnum documentStatus) {
    this.documentStatus = documentStatus;
  }

  public Document creationDate(LocalDate creationDate) {
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

  public Document archivationDate(LocalDate archivationDate) {
    this.archivationDate = archivationDate;
    return this;
  }

  /**
   * Get archivationDate
   * @return archivationDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getArchivationDate() {
    return archivationDate;
  }

  public void setArchivationDate(LocalDate archivationDate) {
    this.archivationDate = archivationDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.documentId, document.documentId) &&
        Objects.equals(this.name, document.name) &&
        Objects.equals(this.contractType, document.contractType) &&
        Objects.equals(this.versions, document.versions) &&
        Objects.equals(this.documentStatus, document.documentStatus) &&
        Objects.equals(this.creationDate, document.creationDate) &&
        Objects.equals(this.archivationDate, document.archivationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(documentId, name, contractType, versions, documentStatus, creationDate, archivationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    
    sb.append("    documentId: ").append(toIndentedString(documentId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    contractType: ").append(toIndentedString(contractType)).append("\n");
    sb.append("    versions: ").append(toIndentedString(versions)).append("\n");
    sb.append("    documentStatus: ").append(toIndentedString(documentStatus)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    archivationDate: ").append(toIndentedString(archivationDate)).append("\n");
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

