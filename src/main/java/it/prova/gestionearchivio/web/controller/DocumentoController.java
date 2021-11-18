package it.prova.gestionearchivio.web.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionearchivio.dto.DocumentoDTO;
import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.model.Documento;
import it.prova.gestionearchivio.service.DocumentoService;
import it.prova.gestionearchivio.service.FascicoloService;

@Controller
@RequestMapping(value = "/documento")
public class DocumentoController {
	
	@Autowired
	private DocumentoService documentoService;
	@Autowired
	private FascicoloService fascicoloService;
	
	@GetMapping("/show/{idDocumento}")
	public String showFilm(@PathVariable(required = true) Long idDocumento, Model model) {
		model.addAttribute("show_documento_attr", documentoService.caricaSingoloElementoEager(idDocumento));
		return "documento/show";
	}
	
	@GetMapping("/insert")
	public String createDocumento(Model model) {
		model.addAttribute("insert_documento_attr", new DocumentoDTO());
		return "documento/insert";
	}
	
	@PostMapping("/save")
	public String saveDocumento(@Valid @ModelAttribute("insert_documento_attr") DocumentoDTO documentoDTO, BindingResult result,
			RedirectAttributes redirectAttrs, HttpServletRequest request) {

		// se fosse un entity questa operazione sarebbe inutile perche provvederebbe
		// da solo fare il binding dell'intero oggetto. Essendo un dto dobbiamo pensarci
		// noi 'a mano'. Se validazione risulta ok devo caricare l'oggetto per 
		// visualizzarne nome e cognome nel campo testo
		if (documentoDTO.getFascicoloProprietario() == null || documentoDTO.getFascicoloProprietario().getId() == null)
			result.rejectValue("fascicoloProprietario", "fascicoloProprietario.notnull");
		else
			documentoDTO.setFascicoloProprietario(FascicoloDTO
					.buildFascicoloDTOFromModel(fascicoloService.caricaSingoloElemento(documentoDTO.getFascicoloProprietario().getId())));

		if (result.hasErrors()) {
			return "film/insert";
		}
		documentoService.inserisciNuovo(documentoDTO.buildDocumentoModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/documento";
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
