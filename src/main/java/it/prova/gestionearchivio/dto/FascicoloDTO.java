package it.prova.gestionearchivio.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.gestionearchivio.model.Documento;
import it.prova.gestionearchivio.model.Fascicolo;

public class FascicoloDTO {

	private Long id;

	@NotBlank(message = "{codice.notblank}")
	private String codice;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	private Date dataCreazione;

	private Date dataChiusura;

	private Set<Documento> documenti = new HashSet<>();

	public FascicoloDTO() {
		super();
	}

	
	public FascicoloDTO(Long id,String codice, String descrizione, Date dataCreazione, Date dataChiusura,
			Set<Documento> documenti) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataChiusura = dataChiusura;
		this.documenti = documenti;
	}


	public FascicoloDTO(String codice, String descrizione, Date dataCreazione, Date dataChiusura,
			Set<Documento> documenti) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataChiusura = dataChiusura;
		this.documenti = documenti;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataChiusura() {
		return dataChiusura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}

	public Set<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(Set<Documento> documenti) {
		this.documenti = documenti;
	}

	public static FascicoloDTO buildFascicoloDTOFromModel(Fascicolo fascicoloModel) {
		FascicoloDTO result = new FascicoloDTO(fascicoloModel.getId(), fascicoloModel.getCodice(),
				fascicoloModel.getDescrizione(), fascicoloModel.getDataCreazione(), fascicoloModel.getDataChiusura(), fascicoloModel.getDocumenti());
		return result;
	}

	public static List<FascicoloDTO> createFascicoloDTOListFromModelList(List<Fascicolo> modelListInput) {
		return modelListInput.stream().map(registaEntity -> {
			return FascicoloDTO.buildFascicoloDTOFromModel(registaEntity);
		}).collect(Collectors.toList());
	}

	public Fascicolo buildFascicoloModel() {
		return new Fascicolo(this.id, this.codice, this.descrizione, this.dataCreazione, this.dataChiusura,
				this.documenti);
	}
}
