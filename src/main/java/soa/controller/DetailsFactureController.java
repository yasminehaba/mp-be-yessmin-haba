package soa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import soa.entities.DetailsFacture;
import soa.entities.Facture;
import soa.entities.Produit;
import soa.repository.DetailsFactureRepository;
import soa.repository.FactureRepository;
import soa.repository.ProduitRepository;
import soa.service.DetailsFactureService;
import soa.service.DetailsFactureServiceInterface;
import soa.service.FactureMetierInterface;
import soa.service.ProduitMetierImpl;
import soa.service.ProduitMetierInterface;
@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/detailsFactures")

public class DetailsFactureController {

    @Autowired
    private DetailsFactureService detailsFactureService;
    @Autowired
    private DetailsFactureRepository detailsFactureRepository;
    @Autowired
    private ProduitMetierInterface produitService;
    @Autowired
    private FactureMetierInterface factureService;
    @Autowired
    private FactureRepository factureRepo;
    @Autowired
    private ProduitRepository produitRepo;

    // Create a new DetailsFacture
   /* @PostMapping("/add")
    public ResponseEntity<DetailsFacture> addDetailsFacture(@RequestBody DetailsFacture detailsFacture) {
        DetailsFacture savedDetailsFacture = detailsFactureService.save(detailsFacture);
        return new ResponseEntity<>(savedDetailsFacture, HttpStatus.CREATED);
    }
*/
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailsFacture> addDetailsFacture(@RequestBody DetailsFactureRequest request) {
        // Extract parameters from the request object
        Long factureId = request.getFactureId();
        int quantite = request.getQuantite();
        Long produitId = request.getProduitId();

        // Récupérer la facture à partir de la base de données
        Facture facture = factureService.findById(factureId);

        // Vérifier si la facture existe
        if (facture == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Récupérer le produit à partir de la base de données
        Produit produit = produitService.findById(produitId);

        // Vérifier si le produit existe
        if (produit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Récupérer le prix unitaire du produit
        double prixUnitaire = produit.getPrix();

        // Créer un nouvel objet DetailsFacture
        DetailsFacture detailsFacture = new DetailsFacture();
        detailsFacture.setQuantite(quantite);
        detailsFacture.setPrixUnitaire(prixUnitaire);
        detailsFacture.setFacture(facture);
        detailsFacture.setProduit(produit);

        // Sauvegarder le DetailsFacture
        DetailsFacture savedDetailsFacture = detailsFactureService.save(detailsFacture);

        // Ajouter le DetailsFacture à la liste des détails de la facture
        facture.getDetailsfactures().add(savedDetailsFacture);

        // Mettre à jour le montant total de la facture
        double montantTotalFacture = facture.getTotale() + savedDetailsFacture.getMontantTotal();
        facture.setTotale(montantTotalFacture);

        // Sauvegarder la facture mise à jour
        factureService.saveFacture(facture);

        return new ResponseEntity<>(savedDetailsFacture, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsFacture> getDetailsFactureById(@PathVariable Long id) {
        DetailsFacture detailsFacture = detailsFactureService.findById(id);
        if (detailsFacture != null) {
            return new ResponseEntity<>(detailsFacture, HttpStatus.OK);
        } else {
            // Add logging for debugging
            System.out.println("DetailsFacture not found for ID: " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    // Update DetailsFacture by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<DetailsFacture> updateDetailsFacture(@PathVariable Long id, @RequestBody DetailsFacture updatedDetailsFacture) {
        DetailsFacture existingDetailsFacture = detailsFactureService.findById(id);
        if (existingDetailsFacture != null) {
            // Set the ID of the existing DetailsFacture to the updated one to ensure the correct item is updated
            updatedDetailsFacture.setId(existingDetailsFacture.getId());
            DetailsFacture savedDetailsFacture = detailsFactureService.save(updatedDetailsFacture);
            return new ResponseEntity<>(savedDetailsFacture, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete DetailsFacture by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDetailsFacture(@PathVariable Long id) {
        DetailsFacture detailsFacture = detailsFactureService.findById(id);
        if (detailsFacture != null) {
            detailsFactureService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/allf")
    public List<DetailsFacture> getAllFactures() {
        return detailsFactureRepository.findAll();
    }
    @GetMapping(

            
            value= "/p/{id}" ,
// spécifier le format de retour en XML
            produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public Produit getaProduit(@PathVariable Long id) {
        Produit p =produitRepo.findById(id).get();
        return p;
    }
   
}
