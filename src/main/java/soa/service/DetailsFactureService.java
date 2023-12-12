package soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.entities.DetailsFacture;
import soa.repository.DetailsFactureRepository;

@Service
public class DetailsFactureService {

    @Autowired
    private DetailsFactureRepository detailsFactureRepository;

    // Save or Update DetailsFacture
    public DetailsFacture save(DetailsFacture detailsFacture) {
        return detailsFactureRepository.save(detailsFacture);
    }

    public DetailsFacture findById(Long id) {
        System.out.println("Attempting to find DetailsFacture with ID: " + id);
        Optional<DetailsFacture> detailsFactureOptional = detailsFactureRepository.findById(id);

        if (detailsFactureOptional.isPresent()) {
            System.out.println("DetailsFacture found: " + detailsFactureOptional.get());
            return detailsFactureOptional.get();
        } else {
            System.out.println("DetailsFacture not found for ID: " + id);
            return null;
        }
    }

    // Delete DetailsFacture by ID
    public void delete(Long id) {
        detailsFactureRepository.deleteById(id);
    }

 

}
