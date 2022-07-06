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

import com.example.demo.controller.validator.PercorsoValidator;
import com.example.demo.model.Percorso;
import com.example.demo.service.CredentialsService;
import com.example.demo.service.PercorsoService;
import com.example.demo.session.SessionDataUser;

@Controller
public class PercorsoController {

	@Autowired
	private PercorsoService percorsoService;

	@Autowired
	private PercorsoValidator percorsoValidator;

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private SessionDataUser sessionDataUser;

	@PostMapping("/percorso")
	public String addPercorso(@Valid @ModelAttribute("percorso") Percorso percorso, BindingResult bindingResult,
			Model model) {

		percorsoValidator.validate(percorso, bindingResult);

		if (!bindingResult.hasErrors()) {

			percorsoService.save(percorso);
			model.addAttribute("percorso", percorso);
			model.addAttribute("elencoPercorsi", percorsoService.findAll());
			model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

			return "elencoPercorsi.html";
		}
		return "percorsoForm.html";

	}

	// form del percorso
	@GetMapping("/percorsoForm")
	public String getPercorsoForm(Model model) {
		model.addAttribute("percorso", new Percorso());

		return "percorsoForm.html";
	}

	@GetMapping("/percorso/{id}")
	public String getPercorso(@PathVariable("id") Long id, Model model) {
		Percorso percorso = percorsoService.findById(id);
		model.addAttribute("percorso", percorso);

		return "percorso.html";
	}

	@GetMapping("/elencoPercorsi")
	public String getElencoPercorsi(Model model) {

		List<Percorso> elencoPercorsi = percorsoService.findAll();
		model.addAttribute("elencoPercorsi", elencoPercorsi);
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

		return "elencoPercorsi.html";
	}

	// se clicco su cancella mi porta alla pagina di conferma
	@GetMapping("/admin/toDeletePercorso/{id}")
	public String toDeletePercorso(@PathVariable("id") Long id, Model model) {
		model.addAttribute("percorso", percorsoService.findById(id));

		return "toDeletePercorso.html";
	}

	// confermo la cancellazione
	@GetMapping("/admin/deletePercorso/{id}")
	public String deletePercorso(@PathVariable("id") Long id, Model model) {
		percorsoService.deleteById(id);
		model.addAttribute("elencoPercorsi", percorsoService.findAll());
		model.addAttribute("loggedCredential", sessionDataUser.getLoggedCredentials());

		return "elencoPercorsi.html";
	}

}
