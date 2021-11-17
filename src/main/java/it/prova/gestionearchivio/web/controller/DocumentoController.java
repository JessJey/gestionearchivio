package it.prova.gestionearchivio.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionearchivio.service.DocumentoService;

@Controller
@RequestMapping(value = "/documento")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentoService;
	
	@GetMapping("/show/{idDocumento}")
	public String showFilm(@PathVariable(required = true) Long idDocumento, Model model) {
		model.addAttribute("show_documento_attr", documentoService.caricaSingoloElementoEager(idDocumento));
		return "documento/show";
	}
	
}
