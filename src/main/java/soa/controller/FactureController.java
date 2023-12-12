package soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import soa.entities.Client;
import soa.entities.Facture;
import soa.repository.ClientRepository;
import soa.repository.FactureRepository;
import soa.service.FactureMetierInterface;

import java.util.Collections;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200") // Mettez à jour avec l'URL de votre application Angular
@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureRepository factureRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FactureMetierInterface factureService;

    // Welcome message
    @GetMapping("/index")
    public String accueil() {
        return "Bienvenue au service Web REST 'factures'.....";
    }

    // Get all factures
    @GetMapping(value = "/all")
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    // Get a facture by specifying its 'id'
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Facture getFacture(@PathVariable Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    // Delete a facture by 'id' using the 'GET' method
    @DeleteMapping(value = "/delete/{id}")
    public void deleteFacture(@PathVariable Long id) {
        factureRepository.deleteById(id);
    }
    //http://localhost:8081/factures/post?idClient=1
    /*@PostMapping("/post")
    public ResponseEntity<Facture> saveFacture(@RequestBody Facture facture, @RequestParam(name = "idClient") Long idClient) {
        if (idClient != null) {
            Client client = clientRepository.findById(idClient).orElse(null);
            if (client != null) {
                facture.setClient(client);
                Facture savedFacture = factureRepository.save(facture);
                return new ResponseEntity<>(savedFacture, HttpStatus.CREATED);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }*/
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Facture> saveFacture(@RequestBody Facture facture) {
    	if (facture.getClient() != null && facture.getClient().getId() != 0) {            // Récupérer le client depuis la base de données en utilisant l'ID
            Client client = clientRepository.findById(facture.getClient().getId()).orElse(null);

            // Vérifier si le client existe
            if (client != null) {
                // Affecter le client à la facture
                facture.setClient(client);

                // Enregistrer la facture dans la base de données
                Facture savedFacture = factureRepository.save(facture);

                return new ResponseEntity<>(savedFacture, HttpStatus.OK);
            } else {
                // Gérer le cas où le client n'est pas trouvé
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            // Gérer le cas où le client ou son ID est null
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable Long id, @RequestBody Facture updatedFacture) {
        // Check if the Facture with the given ID exists
        if (!factureService.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update the existing Facture
        Facture existingFacture = factureService.findById(id);
        existingFacture.setDateF(updatedFacture.getDateF());
        existingFacture.setClient(updatedFacture.getClient());
        existingFacture.setTotale(updatedFacture.getTotale());
        // Update other fields as needed

        // Save the updated Facture
        Facture savedFacture = factureRepository.save(existingFacture);

        // Return the updated Facture and OK status
        return new ResponseEntity<>(savedFacture, HttpStatus.OK);
    }



    /*// Update a facture using the "PUT" method
    @PutMapping(value = "/put", produces = { MediaType.APPLICATION_JSON_VALUE})
    public Facture updateFacture(@RequestBody Facture facture) {
        return factureRepository.save(facture);
    }*/

    // Delete a facture using the 'DELETE' method
    @DeleteMapping("/")
    public void deleteFacture(@RequestBody Facture facture) {
        factureRepository.delete(facture);
    }
    @GetMapping(value = "/byClient/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Facture> getFacturesByClient(@PathVariable Long clientId) {
        // Retrieve the client from the database
        Client client = clientRepository.findById(clientId).orElse(null);

        // Check if the client exists
        if (client != null) {
            // Retrieve all factures associated with the client
            return factureRepository.findByClient(client);
        } else {
            // Handle the case where the client is not found
            return Collections.emptyList(); // Or you can return null or throw an exception as needed
        }
    }
    @GetMapping("/allClient")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
