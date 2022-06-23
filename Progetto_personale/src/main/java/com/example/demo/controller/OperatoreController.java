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

import com.example.demo.model.Operatore;
import com.example.demo.service.OperatoreService;

@Controller
public class OperatoreController {

	@Autowired
	OperatoreService operatoreService;


	@PostMapping("/operatore") 
	public String addOperatore(@Valid @ModelAttribute("operatore") Operatore operatore, 
			BindingResult bindingResult, Model model) {

		if(!bindingResult.hasErrors()) {
			operatoreService.save(operatore);
			model.addAttribute("operatore", operatore);

			return "operatore.html";
		}

		return "operatoreForm.html";
	}


	//form dell'operatore
	@GetMapping("/operatoreForm") 
	public String getOperatoreForm(Model model) {
		model.addAttribute("operatore", new Operatore());

		return "admin/operatoreForm.html";
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

		List<Operatore> operatori = operatoreService.findAll();
		model.addAttribute("operatore", operatori);

		return "elencoOperatori.html";
	}



	//se clicco su cancella mi porta alla pagina di conferma
	@GetMapping("/toDeleteOperatore/{id}")
	public String toDeleteChef(@PathVariable("id") Long id, Model model) {
		model.addAttribute("operatore", operatoreService.findById(id));

		return "admin/toDeleteOperatore.html";
	}


	//confermo la cancellazione dell'operatore tramite id
	@GetMapping("/deleteOperatore/{id}") 
	public String deleteOPeratore(@PathVariable("id") Long id, Model model) {
		operatoreService.deleteById(id);
		model.addAttribute("elencoBuffet", operatoreService.findAll());

		return "admin/elencoOperatore.html";
	}






}
