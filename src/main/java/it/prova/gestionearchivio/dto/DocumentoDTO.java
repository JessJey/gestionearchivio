package it.prova.gestionearchivio.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.gestionearchivio.model.Documento;

public class DocumentoDTO {

	private Long id;

	// @NotBlank(message = "{codice.notblank}")
	private String codice;
	// @NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	private Date dataCreazione;

	private Date dataUltimaModifica;

	private boolean riservato;

	public DocumentoDTO() {
	}

	public DocumentoDTO(String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica,
			boolean riservato) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
		this.riservato = riservato;
	}

	public DocumentoDTO(Long id, String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica,
			boolean riservato) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
		this.riservato = riservato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
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

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public boolean isRiservato() {
		return riservato;
	}

	public void setRiservato(boolean riservato) {
		this.riservato = riservato;
	}
	
	public Documento buildDocumentoModel() {
		Documento result = new Documento(this.id, this.codice, this.descrizione, this.dataCreazione, this.dataUltimaModifica, this.riservato);
		return result;
	}

	public static DocumentoDTO buildDocumentoDTOFromModel(Documento documentoModel) {
		return new DocumentoDTO(documentoModel.getId(), documentoModel.getCodice(), documentoModel.getDescrizione(),
				documentoModel.getDataCreazione(), documentoModel.getDataUltimaModifica(),
				documentoModel.isRiservato());
	}
	
	public static List<DocumentoDTO> createDocumentoDTOListFromModelList(List<Documento> modelListInput) {
		return modelListInput.stream().map(utenteEntity -> {
			return DocumentoDTO.buildDocumentoDTOFromModel(utenteEntity);
		}).collect(Collectors.toList());
	}

}
