package com.online.flowers.dto;
// DTO defines user payment confirmed flowers. Values shall be populated in this class when user pays for flowers

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.online.flowers.model.CustomerModel;
import com.online.flowers.model.FlowersModel;

public class Sales {
	
	private CustomerModel customerModel;
	
	private List<FlowersModel> flowersModel;

	public CustomerModel getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(CustomerModel customerModel) {
		this.customerModel = customerModel;
	}

	public List<FlowersModel> getFlowersModel() {
		return flowersModel;
	}

	public void setFlowersModel(List<FlowersModel> flowersModel) {
		this.flowersModel = flowersModel;
	}

	
	
	
	
}

