package cz.vse.chan01.mi.model.file;

import java.io.Serializable;

public class File implements Serializable {

	private long contractId;
	private long customerId;
	private String label;

	public File() { }

	public File(final long contractId, final long customerId, final String label) {
		this.contractId = contractId;
		this.customerId = customerId;
		this.label = label;
	}

	public long getContractId() {
		return contractId;
	}

	public void setContractId(final long contractId) {
		this.contractId = contractId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(final long customerId) {
		this.customerId = customerId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "File{" +
			"contractId=" + contractId +
			", customerId=" + customerId +
			", label='" + label + '\'' +
			'}';
	}
}
