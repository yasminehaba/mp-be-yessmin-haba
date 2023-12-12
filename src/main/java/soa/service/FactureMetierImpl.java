package soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import soa.entities.Facture;
import soa.repository.FactureRepository;
@Service
public class FactureMetierImpl implements FactureMetierInterface {

    @Autowired
    private FactureRepository factureRepository;
    @Transactional
    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return factureRepository.existsById(id);
    }

    @Override
    public Facture findById(Long id) {
        Optional<Facture> optionalFacture = factureRepository.findById(id);
        return optionalFacture.orElseThrow();
    }
}