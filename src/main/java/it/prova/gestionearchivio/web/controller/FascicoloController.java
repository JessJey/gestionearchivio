package it.prova.gestionearchivio.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.service.DocumentoService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import it.prova.gestionearchivio.model.Fascicolo;
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
	public String saveFascicolo(@Valid @ModelAttribute("insert_fascicolo_attr") FascicoloDTO fascicoloDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "fascicolo/insert";
		}
		fascicoloDTO.setDataCreazione(new Date());
		fascicoloService.inserisciNuovo(fascicoloDTO.buildFascicoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo";
	}
	
	@GetMapping
	public ModelAndView listAllFascicoli() {
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
	
	@GetMapping(value = "/searchFascicoliAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchFascicolo(@RequestParam String term) {

		List<Fascicolo> listaFascicoloByTerm = fascicoloService.cercaByCodiceEDescrizioneLike(term);
		return buildJsonResponse(listaFascicoloByTerm);
	}

	private String buildJsonResponse(List<Fascicolo> listaFascicoli) {
		JsonArray ja = new JsonArray();

		for (Fascicolo fascicoloItem : listaFascicoli) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", fascicoloItem.getId());
			jo.addProperty("label", fascicoloItem.getCodice() + " " + fascicoloItem.getDescrizione());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}
	
	@GetMapping("/edit/{idFascicolo}")
	public String edit(@PathVariable(required = true) Long idFascicolo, Model model) {
		Fascicolo fascicoloModel = fascicoloService.caricaSingoloFascicolo(idFascicolo);
		model.addAttribute("update_fascicolo_attr", FascicoloDTO.buildFascicoloDTOFromModel(fascicoloModel));
		return "fascicolo/edit";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("update_fascicolo_attr") FascicoloDTO fascicoloDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "fascicolo/edit";
		}
		fascicoloService.aggiorna(fascicoloDTO.buildFascicoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo";
	}
	
	@GetMapping("/show/{idFascicolo}")
	public String showFascicolo(@PathVariable(required = true) Long idFascicolo, Model model) {
		model.addAttribute("show_fascicolo_attr", fascicoloService.caricaSingoloElemento(idFascicolo));
		return "fascicolo/show";
	}
	
	@GetMapping("/remove/{idFascicolo}")
	public String remove(@PathVariable(required = true) Long idFascicolo, Model model) {
		model.addAttribute("remove_fascicolo_attr", fascicoloService.caricaSingoloElemento(idFascicolo));
		return "fascicolo/delete";
	}

	@PostMapping("/delete")
	public String rimozione(@RequestParam Long idFascicolo, RedirectAttributes redirectAttrs) {

		if (fascicoloService.caricaSingoloElemento(idFascicolo).getDocumenti().size() == 0) {
			fascicoloService.rimuoviId(idFascicolo);
			redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
			return "redirect:/fascicolo";
		} else {
			redirectAttrs.addFlashAttribute("errorMessage",
					"Non è possibile eliminare il fascicolo perché ha dei film associati!");
			return "redirect:/fascicolo";
		}
	}

}
