package com.ngueno.microservices.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ngueno.microservices.currencyexchangeservice.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from, String to);
	
}
