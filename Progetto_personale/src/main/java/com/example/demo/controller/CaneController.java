package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controller.validator.CaneValidator;
import com.example.demo.model.Cane;
import com.example.demo.model.Operatore;
import com.example.demo.service.CaneService;
import com.example.demo.service.OperatoreService;
import com.example.demo.upload.FileUploadUtil;


@Controller
public class CaneController {

	@Autowired 
	private CaneService caneService;

	@Autowired 
	private CaneValidator caneValidator;

	@Autowired 
	private OperatoreService operatoreService;


	@PostMapping("/cane")
	public String addCane(@RequestParam("idOperatore") String idOperatore, @Valid @ModelAttribute ("cane") Cane cane, 
			@RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult, Model model) throws IOException {

		/*
		 * codice ridondante ma che serve per il passaggio dei parametri
		 */
		Long id = Long.valueOf(idOperatore);
		Operatore operatore = operatoreService.findById(id);
		cane.setOperatore(operatore);

		
		caneValidator.validate(cane, bindingResult);

		if(!bindingResult.hasErrors()) {

			caneService.save(cane);

			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			cane.setPhotos(fileName);
			Cane salvaCane = this.caneService.inserisci(cane);
			String uploadDir = "cane-photos/" + salvaCane.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

			model.addAttribute("cane", cane);

			return "admin/cane.html";
		}
		//se qualcosa è andato storto, torno alla form
		return "admin/caneForm.html";

	}


	@GetMapping ("/cane/{id}") 
	public String getCane(@PathVariable("id") Long id, Model model) {
		Cane cane = caneService.findById(id);
		model.addAttribute("cane", cane);

		return "cane.html";
	}


	//elenco dei cani senza id operatore
	@GetMapping("/elencoCani") 
	public String getElencoCani(Model model) {
		List<Cane> cani = caneService.findAll();
		model.addAttribute("cani", cani);

		return "elencoCani.html";
	}


	//elenco dei cani con id operatore
	@GetMapping("/elencoCani/{id}") 
	public String getElencoCaniId(@PathVariable("id") Long id, Model model) {
		Operatore operatore = operatoreService.findById(id);		
		List<Cane> cani = caneService.getByOperatore(operatore);
		model.addAttribute("cani", cani);

		return "elencoCani.html";
	}


	//collego il cane al suo operatore tramite l'id di quest'ultimo
	@GetMapping("/cane/{id}/caneForm") 
	public String creaCane(@PathVariable("id") Long id, Model model) {

		Operatore operatore = operatoreService.findById(id);
		Cane cane = new Cane();
		cane.setOperatore(operatore);

		model.addAttribute("cane", cane);

		return "caneForm.html";
	}


	//mi chiede la conferma per cancellare un cane
	@GetMapping("/toDeleteCane/{id}") 
	public String toDeleteCane(@PathVariable("id") Long id, Model model) {

		model.addAttribute("cane", caneService.findById(id));

		return "toDeleteCane.html";
	}



	//mi cancella il cane tramite il suo id
	@GetMapping("deleteCane/{id}")
	public String deleteCane(@PathVariable("id") Long id, Model model) {
		caneService.deleteById(id);

		return "deleteCane.html";
	}




}




