package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.OperatoreValidator;
import com.example.demo.model.Operatore;
import com.example.demo.service.OperatoreService;




@Controller
public class OperatoreController {

	@Autowired
	OperatoreService operatoreService;
	
	@Autowired 
	OperatoreValidator operatoreValidator;

	
	

	@PostMapping("/operatore") 
	public String addOperatore(@Valid @ModelAttribute("operatore") Operatore operatore, 
			BindingResult bindingResult, Model model) {

		
		
		operatoreValidator.validate(operatore, bindingResult);
		
		
		
		if(!bindingResult.hasErrors()) {
			operatoreService.save(operatore);
			model.addAttribute("operatore", operatore);
			model.addAttribute("elencoOperatore", operatoreService.findAll());

			model.addAttribute("role", operatoreService.getCredentialsService().getRoleAuthenticated());			


			return "operatore.html";
		}

		return "operatoreForm.html";
	}


	//form dell'operatore
	@GetMapping("/operatoreForm") 
	public String getOperatoreForm(Model model) {
		model.addAttribute("operatore", new Operatore());

		return "operatoreForm.html";
	}


	//prendo un operatore per il suo id
	@GetMapping("/operatore/{id}") 
	public String getOperatore(@PathVariable("id") Long id, Model model) {
		Operatore operatore = operatoreService.findById(id);
		model.addAttribute("operatore", operatore);

		return "operatore.html";
	}


	//prendo l'elenco degli operatori senza id
	@GetMapping("/elencoOperatori")
	public String getElencoOperatori(Model model) {

		List<Operatore> elencoOperatori = operatoreService.findAll();
		model.addAttribute("elencoOperatori", elencoOperatori);
		model.addAttribute("role", operatoreService.getCredentialsService().getRoleAuthenticated());			


		return "elencoOperatori.html";
	}



	//se clicco su cancella mi porta alla pagina di conferma
	@GetMapping("/admin/toDeleteOperatore/{id}")
	public String toDeleteChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("operatore", operatoreService.findById(id));

		return "toDeleteOperatore.html";
	}


	//confermo la cancellazione dell'operatore tramite id
	@GetMapping("/admin/deleteOperatore/{id}") 
	public String deleteOPeratore(@PathVariable("id") Long id, Model model) {
		operatoreService.deleteById(id);
		model.addAttribute("elencoBuffet", operatoreService.findAll());
		model.addAttribute("role", operatoreService.getCredentialsService().getRoleAuthenticated());			

		return "redirect:/elencoOperatori";
	}






}
