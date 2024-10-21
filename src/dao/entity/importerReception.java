package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="importerReception.findAll", query="SELECT f FROM importerReception f")
public class importerReception implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="code_transfert")
	private String code_transfert;

	 private BigDecimal montantEmballage;
	 private BigDecimal montantEnVrac;
	 private BigDecimal montantTotal;
	 
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode_transfert() {
		return code_transfert;
	}
	public void setCode_transfert(String code_transfert) {
		this.code_transfert = code_transfert;
	}
	public BigDecimal getMontantEmballage() {
		return montantEmballage;
	}
	public void setMontantEmballage(BigDecimal montantEmballage) {
		this.montantEmballage = montantEmballage;
	}
	public BigDecimal getMontantEnVrac() {
		return montantEnVrac;
	}
	public void setMontantEnVrac(BigDecimal montantEnVrac) {
		this.montantEnVrac = montantEnVrac;
	}
	public BigDecimal getMontantTotal() {
		return montantTotal;
	}
	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}