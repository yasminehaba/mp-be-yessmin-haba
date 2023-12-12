package soa.service;

import java.util.List;

import soa.entities.DetailsFacture;

public interface DetailsFactureServiceInterface  {

	 DetailsFacture saveDetailsFacture(DetailsFacture detailsFacture);
	    DetailsFacture getDetailsFactureById(Long id);
	    List<DetailsFacture> getAllDetailsFactures();
	    void deleteDetailsFacture(Long id);
}
