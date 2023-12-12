package soa.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@Entity

public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date dateF;
    
    @ManyToOne()
    @JoinColumn(name="id_client")
    private Client client;
    
    private double totale;
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetailsFacture> detailsfactures = new ArrayList<>();

    // Initialisation ici

    // Constructeurs, getters, setters...

    // Ajoutez le constructeur par défaut pour JPA
    public Facture() {
    }

    // Autres constructeurs...

    // Getter et Setter pour detailsfactures
    public List<DetailsFacture> getDetailsfactures() {
        return detailsfactures;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateF() {
		return dateF;
	}

	public void setDateF(Date dateF) {
		this.dateF = dateF;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public void setDetailsfactures(List<DetailsFacture> detailsfactures) {
        this.detailsfactures = detailsfactures;
    }
}
