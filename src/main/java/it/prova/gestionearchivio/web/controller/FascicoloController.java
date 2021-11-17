package it.prova.gestionearchivio.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.service.DocumentoService;
import it.prova.gestionearchivio.service.FascicoloService;

@Controller
@RequestMapping(value = "/fascicolo")
public class FascicoloController {

	@Autowired
	FascicoloService fascicoloService;

	@Autowired
	DocumentoService documentoService;

	@GetMapping("/insert")
	public String createFascicolo(Model model) {
		model.addAttribute("insert_fascicolo_attr", new FascicoloDTO());
		return "fascicolo/insert";
	}

	@PostMapping("/save")
	public String saveFascicoloa(@Valid @ModelAttribute("insert_fascicolo_attr") FascicoloDTO fascicoloDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "fascicolo/insert";
		}
		fascicoloDTO.setDataCreazione(new Date());
		fascicoloService.inserisciNuovo(fascicoloDTO.buildFascicoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo/list";
	}
}
