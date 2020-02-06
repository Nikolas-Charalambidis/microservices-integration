package cz.vse.chan01.mi.api.contract.service;

import java.util.List;

import cz.vse.chan01.swagger.contract.model.Contract;

public interface ContractService {

	List<Contract> contracts();

	Contract contract(long contractId);

	long contract(Contract contract);
}
