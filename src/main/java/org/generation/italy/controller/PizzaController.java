package org.generation.italy.controller;

import org.generation.italy.model.Pizza;
import org.generation.italy.services.IngredientiService;
import org.generation.italy.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	private PizzaService service;
	
	@Autowired
	private IngredientiService ingredientiService;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAllSortedByNome());
		return"/pizze/list";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredientiList", ingredientiService.findAllSortByNome());
		return "/pizze/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@ModelAttribute("pizza") Pizza formPizza, Model model) {
		
		service.create(formPizza);
		return "redirect:/pizze";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
	model.addAttribute("edit", true);
		model.addAttribute("pizza", service.getById(id));
		model.addAttribute("ingredientiList", ingredientiService.findAllSortByNome());
		return "/pizze/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate (@ModelAttribute("pizza") Pizza formPizza, Model model) {
		service.update(formPizza);
		return "redirect:/pizze";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id) {
		service.deleteById(id);
		return "redirect:/pizze";
	}

	
	
	
}
