package soa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import soa.entities.Client;
import soa.entities.Facture;
import soa.repository.ClientRepository;  // Assurez-vous d'importer le repository approprié
import soa.repository.FactureRepository; // Assurez-vous d'importer le repository approprié

import java.util.Date;

@SpringBootApplication
public class SpringJpaApplication {

    static FactureRepository factureRepos;
    static ClientRepository clientRepos;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringJpaApplication.class, args);

        factureRepos = context.getBean(FactureRepository.class);
        clientRepos = context.getBean(ClientRepository.class);

        /*// Création d'un client
        Client client = new Client();
        client.setNom("NomClient");
        client.setAdresse("AdresseClient");

        // Ajout du client à la base de données
        clientRepos.save(client);

        // Création d'une facture
        Facture facture1 = new Facture();
        facture1.setDateF(new Date());
        facture1.setClient(client);  // Associer la facture au client créé
        facture1.setTotale(100.0);   // Remplacez 100.0 par le montant réel

        // Ajout de la facture à la base de données
        factureRepos.save(facture1);

        // Vous pouvez répéter ce processus pour ajouter d'autres factures avec le même client ou différents clients.

        // Exemple d'ajout d'une autre facture avec le même client
        Facture facture2 = new Facture();
        facture2.setDateF(new Date());
        facture2.setClient(client);
        facture2.setTotale(200.0);   // Remplacez 200.0 par le montant réel

        factureRepos.save(facture2);*/
    }
}
