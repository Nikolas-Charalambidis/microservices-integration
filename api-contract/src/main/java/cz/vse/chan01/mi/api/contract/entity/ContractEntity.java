package cz.vse.chan01.mi.api.contract.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class ContractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="contract_id")
	private Long contractId;

	@Column(name="customer_id")
	private Long customerId;

	@Column(name="file_id")
	private Long fileId;

	@Column(name="customer_label")
	private String customerLabel;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="contract_type")
	private ContractType contractType;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="contract_status")
	private ContractStatus contractStatus;

	@Column(name="creationDate")
	private LocalDate creationDate;

	@Column(name="expirationDate")
	private LocalDate expirationDate;

	public ContractEntity() { }

	public ContractEntity(final Long customerId, final Long fileId, final String customerLabel,
		final ContractType contractType, final ContractStatus contractStatus, final LocalDate creationDate,
		final LocalDate expirationDate) {
		this.customerId = customerId;
		this.fileId = fileId;
		this.customerLabel = customerLabel;
		this.contractType = contractType;
		this.contractStatus = contractStatus;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(final Long contractId) {
		this.contractId = contractId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(final Long fileId) {
		this.fileId = fileId;
	}

	public String getCustomerLabel() {
		return customerLabel;
	}

	public void setCustomerLabel(final String customerLabel) {
		this.customerLabel = customerLabel;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(final ContractType contractType) {
		this.contractType = contractType;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(final ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(final LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ContractEntity)) {
			return false;
		}
		final ContractEntity that = (ContractEntity) o;
		return Objects.equals(getContractId(), that.getContractId()) &&
			Objects.equals(getCustomerId(), that.getCustomerId()) &&
			Objects.equals(getFileId(), that.getFileId()) &&
			Objects.equals(getCustomerLabel(), that.getCustomerLabel()) &&
			getContractType() == that.getContractType() &&
			getContractStatus() == that.getContractStatus() &&
			Objects.equals(getCreationDate(), that.getCreationDate()) &&
			Objects.equals(getExpirationDate(), that.getExpirationDate());
	}

	@Override
	public int hashCode() {
		return Objects
			.hash(getContractId(), getCustomerId(), getFileId(), getCustomerLabel(), getContractType(),
				getContractStatus(),
				getCreationDate(), getExpirationDate());
	}

	@Override
	public String toString() {
		return "ContractEntity{" +
			"contractId=" + contractId +
			", customerId=" + customerId +
			", fileId=" + fileId +
			", customerLabel='" + customerLabel + '\'' +
			", contractType=" + contractType +
			", contractStatus=" + contractStatus +
			", creationDate=" + creationDate +
			", expirationDate=" + expirationDate +
			'}';
	}
}
