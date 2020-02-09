package cz.vse.chan01.mi.api.document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Document
public class DocumentEntity {

	@Id
	private String id;

	private String name;

	private String documentStatus;

	private String contractType;

	//@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	//@JsonSerialize(using = LocalDateTimeSerializer.class)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate creationDate;

	//@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	//@JsonSerialize(using = LocalDateTimeSerializer.class)
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate archivationDate;

	private List<VersionedDocumentEntity> versionedDocumentEntityList;

	public DocumentEntity() {

	}

	public DocumentEntity(final String id, final String name, final String documentStatus, final String contractType,
		final LocalDate creationDate, final LocalDate archivationDate,
		final List<VersionedDocumentEntity> versionedDocumentEntityList) {
		this.id = id;
		this.name = name;
		this.documentStatus = documentStatus;
		this.contractType = contractType;
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

	public String getContractType() {
		return contractType;
	}

	public void setContractType(final String contractType) {
		this.contractType = contractType;
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
			Objects.equals(getName(), that.getName()) &&
			Objects.equals(getDocumentStatus(), that.getDocumentStatus()) &&
			Objects.equals(getContractType(), that.getContractType()) &&
			Objects.equals(getCreationDate(), that.getCreationDate()) &&
			Objects.equals(getArchivationDate(), that.getArchivationDate()) &&
			Objects.equals(getVersionedDocumentEntityList(), that.getVersionedDocumentEntityList());
	}

	@Override
	public int hashCode() {
		return Objects
			.hash(getId(), getName(), getDocumentStatus(), getContractType(), getCreationDate(), getArchivationDate(),
				getVersionedDocumentEntityList());
	}

	@Override
	public String toString() {
		return "DocumentEntity{" +
			"id='" + id + '\'' +
			", name='" + name + '\'' +
			", documentStatus='" + documentStatus + '\'' +
			", contractType='" + contractType + '\'' +
			", creationDate=" + creationDate +
			", archivationDate=" + archivationDate +
			", versionedDocumentEntityList=" + versionedDocumentEntityList +
			'}';
	}
}
