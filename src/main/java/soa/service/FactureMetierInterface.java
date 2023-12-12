package soa.service;


import java.util.List;
import java.util.Optional;

import soa.entities.Facture;

public interface FactureMetierInterface {  // Replaced "ProduitMetierInterface" with "FactureMetierInterface"
	 Facture saveFacture(Facture facture);
	    Facture getFactureById(Long id);
	    List<Facture> getAllFactures();
	    void deleteFacture(Long id);
	    boolean existsById(Long id);
	    Facture findById(Long id);
	      
}
