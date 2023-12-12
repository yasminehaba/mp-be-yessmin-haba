package soa.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
public class DetailsFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantite;
    private double prixUnitaire;
    
    @ManyToOne
    @JoinColumn(name = "facture_id")
    @JsonBackReference
    private Facture facture;
    
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;
    public DetailsFacture() {
        super();
    }
    public Double getMontantTotal() {
        return quantite * prixUnitaire;
    }

    public DetailsFacture(int quantite, double prixUnitaire, Facture facture, Produit produit) {
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.facture = facture;
        this.produit = produit;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
    
}
