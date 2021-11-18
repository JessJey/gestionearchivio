package it.prova.gestionearchivio.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionearchivio.dto.FascicoloDTO;
import it.prova.gestionearchivio.model.Fascicolo;

public class CustomFascicoloRepositoryImpl implements CustomFascicoloRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Fascicolo> findByExample(FascicoloDTO example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select r from Fascicolo r where r.id = r.id ");

		if (StringUtils.isNotBlank(example.getCodice())) {
			whereClauses.add(" r.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotBlank(example.getDescrizione())) {
			whereClauses.add(" r.descrizione like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (example.getDataCreazione() != null) {
			whereClauses.add("r.dataCreazione >= :dataCreazione ");
			paramaterMap.put("dataCreazione", example.getDataCreazione());
		}
		if (example.getDataChiusura() != null) {
			whereClauses.add("r.dataChiusura >= :dataChiusura ");
			paramaterMap.put("dataChiusura", example.getDataChiusura());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Fascicolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Fascicolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}	

}
