package cz.vse.chan01.mi.api.document.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VersionedDocumentEntity {

	@Id
	private String id;

	private LocalDateTime date;

	private String version;

	private String format;

	private String content;

	public VersionedDocumentEntity() {

	}

	public VersionedDocumentEntity(final String id, final LocalDateTime date, final String version, final String format,
		final String content) {
		this.id = id;
		this.date = date;
		this.version = version;
		this.format = format;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(final LocalDateTime date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(final String format) {
		this.format = format;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof VersionedDocumentEntity)) {
			return false;
		}
		final VersionedDocumentEntity that = (VersionedDocumentEntity) o;
		return Objects.equals(getId(), that.getId()) &&
			Objects.equals(getDate(), that.getDate()) &&
			Objects.equals(getVersion(), that.getVersion()) &&
			Objects.equals(getFormat(), that.getFormat()) &&
			Objects.equals(getContent(), that.getContent());
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getId(), getDate(), getVersion(), getFormat(), getContent());
		result = 31 * result;
		return result;
	}

	@Override
	public String toString() {
		return "VersionedDocumentEntity{" +
			"id='" + id + '\'' +
			", date=" + date +
			", version='" + version + '\'' +
			", format='" + format + '\'' +
			", content=" + content +
			'}';
	}
}
