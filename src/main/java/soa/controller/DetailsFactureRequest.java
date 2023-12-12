package soa.controller;

public class DetailsFactureRequest {

	 private Long factureId;
	    private int quantite;
	    private Long produitId;
		public DetailsFactureRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		public DetailsFactureRequest(Long factureId, int quantite, Long produitId) {
			super();
			this.factureId = factureId;
			this.quantite = quantite;
			this.produitId = produitId;
		}
		public Long getFactureId() {
			return factureId;
		}
		public void setFactureId(Long factureId) {
			this.factureId = factureId;
		}
		public int getQuantite() {
			return quantite;
		}
		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}
		public Long getProduitId() {
			return produitId;
		}
		public void setProduitId(Long produitId) {
			this.produitId = produitId;
		}

}
