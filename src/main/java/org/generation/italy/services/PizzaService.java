package org.generation.italy.services;

import java.util.List;

import org.generation.italy.model.Pizza;
import org.generation.italy.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
	
	@Autowired
	private PizzaRepository repository;
	
	public List<Pizza> findAllSortedByNome(){
		return repository.findAll(Sort.by("nome"));
	}
	
	public Pizza save(Pizza pizza) {
		
		return repository.save(pizza);
	}

}
