package it.prova.gestionearchivio.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.model.Fascicolo;
import it.prova.gestionearchivio.service.FascicoloService;

@Controller
@RequestMapping(value = "/fascicolo")
public class FascicoloController {

	@Autowired
	FascicoloService fascicoloService;
	
	@GetMapping
	public ModelAndView listAllTavoli() {
		ModelAndView mv = new ModelAndView();
		List<Fascicolo> tavoli = fascicoloService.listAllFascicoli();
		mv.addObject("fascicolo_list_attribute", FascicoloDTO.createFascicoloDTOListFromModelList(tavoli));
		mv.setViewName("fascicolo/list");
		return mv;
	}
	
	@GetMapping("/search")
	public String searchFascicolo(Model model) {
		return "fascicolo/search";
	}
	
	@PostMapping("/list")
	public String listFascicolo(FascicoloDTO fascicoloExample, ModelMap model, HttpServletRequest request) {
		List<Fascicolo> fascicoli = fascicoloService.findByExample(fascicoloExample);
		model.addAttribute("fascicolo_list_attribute", fascicoli);
		return "fascicolo/list";
	}
	
}
