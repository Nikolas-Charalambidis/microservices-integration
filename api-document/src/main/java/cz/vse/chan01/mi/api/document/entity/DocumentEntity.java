package cz.vse.chan01.mi.api.document.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Entity
public class DocumentEntity {

	@Id
	private String id;

	@NotNull
	private Long caseId;

	@NotNull
	private Long customerId;

	@NotBlank
	private String name;

	@NotBlank
	private String documentStatus;

	@NotNull
	private LocalDate creationDate;

	private LocalDate archivationDate;

	@NotNull
	@Valid
	private List<VersionedDocumentEntity> versionedDocumentEntityList;

	public DocumentEntity() {

	}

	public DocumentEntity(final String id, @NotNull final Long caseId, @NotNull final Long customerId,
		@NotBlank final String name, @NotBlank final String documentStatus,
		@NotNull final LocalDate creationDate, final LocalDate archivationDate,
		@NotNull @Valid final List<VersionedDocumentEntity> versionedDocumentEntityList) {
		this.id = id;
		this.caseId = caseId;
		this.customerId = customerId;
		this.name = name;
		this.documentStatus = documentStatus;
		this.creationDate = creationDate;
		this.archivationDate = archivationDate;
		this.versionedDocumentEntityList = versionedDocumentEntityList;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(final Long caseId) {
		this.caseId = caseId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(final String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(final LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getArchivationDate() {
		return archivationDate;
	}

	public void setArchivationDate(final LocalDate archivationDate) {
		this.archivationDate = archivationDate;
	}

	public List<VersionedDocumentEntity> getVersionedDocumentEntityList() {
		return versionedDocumentEntityList;
	}

	public void setVersionedDocumentEntityList(
		final List<VersionedDocumentEntity> versionedDocumentEntityList) {
		this.versionedDocumentEntityList = versionedDocumentEntityList;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof DocumentEntity)) {
			return false;
		}
		final DocumentEntity that = (DocumentEntity) o;
		return Objects.equals(getId(), that.getId()) &&
			Objects.equals(getCaseId(), that.getCaseId()) &&
			Objects.equals(getCustomerId(), that.getCustomerId()) &&
			Objects.equals(getName(), that.getName()) &&
			Objects.equals(getDocumentStatus(), that.getDocumentStatus()) &&
			Objects.equals(getCreationDate(), that.getCreationDate()) &&
			Objects.equals(getArchivationDate(), that.getArchivationDate()) &&
			Objects.equals(getVersionedDocumentEntityList(), that.getVersionedDocumentEntityList());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getCaseId(), getCustomerId(), getName(), getDocumentStatus(), getCreationDate(),
			getArchivationDate(), getVersionedDocumentEntityList());
	}

	@Override
	public String toString() {
		return "DocumentEntity{" +
			"id='" + id + '\'' +
			", caseId=" + caseId +
			", customerId=" + customerId +
			", name='" + name + '\'' +
			", documentStatus='" + documentStatus + '\'' +
			", creationDate=" + creationDate +
			", archivationDate=" + archivationDate +
			", versionedDocumentEntityList=" + versionedDocumentEntityList +
			'}';
	}
}
