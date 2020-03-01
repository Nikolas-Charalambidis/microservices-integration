package cz.vse.chan01.swagger.contract.model;

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
 * Contract
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-01T22:49:51.114+01:00")

public class Contract  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("contractId")
  private Long contractId = null;

  @JsonProperty("customerId")
  private Long customerId = null;

  @JsonProperty("customerLabel")
  private String customerLabel = null;

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

  /**
   * Gets or Sets contractStatus
   */
  public enum ContractStatusEnum {
    NEW("NEW"),
    
    CREATED("CREATED"),
    
    APPROVED("APPROVED"),
    
    DENIED("DENIED");

    private String value;

    ContractStatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ContractStatusEnum fromValue(String text) {
      for (ContractStatusEnum b : ContractStatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("contractStatus")
  private ContractStatusEnum contractStatus = null;

  @JsonProperty("creationDate")
  private LocalDate creationDate = null;

  @JsonProperty("expirationDate")
  private LocalDate expirationDate = null;

  public Contract contractId(Long contractId) {
    this.contractId = contractId;
    return this;
  }

  /**
   * Get contractId
   * @return contractId
  **/
  @ApiModelProperty(value = "")


  public Long getContractId() {
    return contractId;
  }

  public void setContractId(Long contractId) {
    this.contractId = contractId;
  }

  public Contract customerId(Long customerId) {
    this.customerId = customerId;
    return this;
  }

  /**
   * Get customerId
   * @return customerId
  **/
  @ApiModelProperty(value = "")


  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Contract customerLabel(String customerLabel) {
    this.customerLabel = customerLabel;
    return this;
  }

  /**
   * Get customerLabel
   * @return customerLabel
  **/
  @ApiModelProperty(value = "")


  public String getCustomerLabel() {
    return customerLabel;
  }

  public void setCustomerLabel(String customerLabel) {
    this.customerLabel = customerLabel;
  }

  public Contract contractType(ContractTypeEnum contractType) {
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

  public Contract contractStatus(ContractStatusEnum contractStatus) {
    this.contractStatus = contractStatus;
    return this;
  }

  /**
   * Get contractStatus
   * @return contractStatus
  **/
  @ApiModelProperty(value = "")


  public ContractStatusEnum getContractStatus() {
    return contractStatus;
  }

  public void setContractStatus(ContractStatusEnum contractStatus) {
    this.contractStatus = contractStatus;
  }

  public Contract creationDate(LocalDate creationDate) {
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

  public Contract expirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  /**
   * Get expirationDate
   * @return expirationDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Contract contract = (Contract) o;
    return Objects.equals(this.contractId, contract.contractId) &&
        Objects.equals(this.customerId, contract.customerId) &&
        Objects.equals(this.customerLabel, contract.customerLabel) &&
        Objects.equals(this.contractType, contract.contractType) &&
        Objects.equals(this.contractStatus, contract.contractStatus) &&
        Objects.equals(this.creationDate, contract.creationDate) &&
        Objects.equals(this.expirationDate, contract.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractId, customerId, customerLabel, contractType, contractStatus, creationDate, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Contract {\n");
    
    sb.append("    contractId: ").append(toIndentedString(contractId)).append("\n");
    sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
    sb.append("    customerLabel: ").append(toIndentedString(customerLabel)).append("\n");
    sb.append("    contractType: ").append(toIndentedString(contractType)).append("\n");
    sb.append("    contractStatus: ").append(toIndentedString(contractStatus)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
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

