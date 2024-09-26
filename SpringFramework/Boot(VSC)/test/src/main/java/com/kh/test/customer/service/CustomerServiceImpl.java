package com.kh.test.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.test.customer.dto.Customer;
import com.kh.test.customer.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {
 
  @Autowired // CustomerMapper를 구현한 Bean을 의존성 주입(DI) 받기 위한 @Autowired 어노테이션이 누락됨
  private CustomerMapper mapper;


  // @Override
  // public int insertCustomer(Customer customer) {
  //   return mapper.insertCustomer(customer);
  // }

  @Override
  public int updateCustomer(Customer customer) {
		return mapper.updateCustomer(customer);
	}

}
