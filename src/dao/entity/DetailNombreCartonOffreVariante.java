package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the detail_commande database table.
 * 
 */

@Entity
@Table(name="Detail_Nombre_Carton_Offre_Variante")
@NamedQuery(name="DetailNombreCartonOffreVariante.findAll", query="SELECT d FROM DetailNombreCartonOffreVariante d")
public class DetailNombreCartonOffreVariante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn( name="id_nombreCartonOffreVariant")
	private  NombreCartonOffreVariante nombreCartonOffreVariant;
	
	@ManyToOne
	@JoinColumn( name="id_matierePremiere")
	private  MatierePremier matierePremier;
	
	private BigDecimal estimation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NombreCartonOffreVariante getNombreCartonOffreVariant() {
		return nombreCartonOffreVariant;
	}

	public void setNombreCartonOffreVariant(NombreCartonOffreVariante nombreCartonOffreVariant) {
		this.nombreCartonOffreVariant = nombreCartonOffreVariant;
	}

	public MatierePremier getMatierePremier() {
		return matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public BigDecimal getEstimation() {
		return estimation;
	}

	public void setEstimation(BigDecimal estimation) {
		this.estimation = estimation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	
	

}