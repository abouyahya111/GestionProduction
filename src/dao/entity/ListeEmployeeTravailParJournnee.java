package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the detail_production database table.
 * 
 */
@Entity
@Table(name="ListeEmployeeTravailParJournne")
@NamedQuery(name="ListeEmployeeTravailParJournne.findAll", query="SELECT d FROM ListeEmployeeTravailParJournne d")
public class ListeEmployeeTravailParJournnee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="total_heures")
	private BigDecimal totalHeures=BigDecimal.ZERO;;
	
	
	
	@Column(name="remise")
	private BigDecimal remise;
 

	//bi-directional many-to-one association to Employe
	@ManyToOne
	@JoinColumn(name="id_employe")
	private Employe employe;

	@Temporal(TemporalType.DATE)
	@JoinColumn(name="DATE_SITUTAION")
	private Date dateSituation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTotalHeures() {
		return totalHeures;
	}

	public void setTotalHeures(BigDecimal totalHeures) {
		this.totalHeures = totalHeures;
	}

	public BigDecimal getRemise() {
		return remise;
	}

	public void setRemise(BigDecimal remise) {
		this.remise = remise;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Date getDateSituation() {
		return dateSituation;
	}

	public void setDateSituation(Date dateSituation) {
		this.dateSituation = dateSituation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 
	
	 
	
	
	

}