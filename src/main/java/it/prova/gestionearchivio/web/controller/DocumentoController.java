package it.prova.gestionearchivio.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.model.Documento;
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
	
	@GetMapping("/search")
	public String searchTavolo(Model model) {
		model.addAttribute("search_documento_attr", new DocumentoDTO());
		return "documento/search";
	}
	
	@PostMapping("/list")
	public String listDocumenti(DocumentoDTO documentoExample, ModelMap model) {
		List<Documento> documenti = documentoService.findByExample(documentoExample);
		model.addAttribute("documenti_list_attribute", DocumentoDTO.createDocumentoDTOListFromModelList(documenti));
		return "documento/list";
	}
	
	
	
}
