package soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import soa.entities.Client;
import soa.entities.Facture;

public interface FactureRepository extends JpaRepository<Facture,Long> {
	
	 List<Facture> findByClient(Client client);
}
