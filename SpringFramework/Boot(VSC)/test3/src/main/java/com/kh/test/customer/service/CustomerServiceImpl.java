package com.kh.test.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.test.customer.mapper.CustomerMapper;

@Transactional
@Service 
public class CustomerServiceImpl implements CustomerService {
 
  @Autowired 
  private CustomerMapper mapper;

  @Override
  public int deleteCustomer(int customerNo) {
    return mapper.deleteCustomer(customerNo);
  }

}
