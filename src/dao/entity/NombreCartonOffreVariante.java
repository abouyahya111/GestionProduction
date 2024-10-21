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
@Table(name="Nombre_Carton_Offre_Variante")
@NamedQuery(name="NombreCartonOffreVariante.findAll", query="SELECT d FROM NombreCartonOffreVariante d")
public class NombreCartonOffreVariante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private BigDecimal nombreCarton;
	
	@ManyToOne
	@JoinColumn( name="id_production")
	private  Production production;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getNombreCarton() {
		return nombreCarton;
	}

	public void setNombreCarton(BigDecimal nombreCarton) {
		this.nombreCarton = nombreCarton;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}
	
	 

	
	
	

}