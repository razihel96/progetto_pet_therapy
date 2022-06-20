package com.example.demo.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controller.validator.CaneValidator;
import com.example.demo.model.Cane;
import com.example.demo.service.CaneService;
import com.example.demo.upload.FileUploadUtil;


@Controller
public class CaneController {
	
	@Autowired 
	private CaneService caneService;
	
	@Autowired 
	private CaneValidator caneValidator;

	
	@PostMapping("/cane")
	public String addCane(@Valid @ModelAttribute ("cane") Cane cane, 
			@RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult, Model model) throws IOException {
		
		/*
		 * codice ridondante ma che serve per il passaggio dei parametri
		 */
//		Long id = Long.valueOf(idOperatore);
//		Operatore operatore = operatoreService.findById(id);
//		cane.setOperatore(operatore);
		
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
	
}
