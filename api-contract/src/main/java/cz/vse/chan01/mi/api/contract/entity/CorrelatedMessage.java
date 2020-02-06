package cz.vse.chan01.mi.api.contract.entity;

import java.util.Objects;

import org.springframework.amqp.core.Message;

public class CorrelatedMessage extends Message {

	private final String uuid;

	public CorrelatedMessage(final Message message, final String uuid) {
		super(message.getBody(), message.getMessageProperties());
		this.uuid = uuid;
		super.getMessageProperties().setCorrelationId(this.uuid);
	}

	public String uuid() {
		return this.uuid;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		final CorrelatedMessage that = (CorrelatedMessage) o;
		return Objects.equals(uuid, that.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), uuid);
	}
}
