package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the detail_commande database table.
 * 
 */

@Entity
@Table(name="Detail_Cout_Article_PF")
@NamedQuery(name="DetailCoutArticlePF.findAll", query="SELECT d FROM DetailCoutArticlePF d")
public class DetailCoutArticlePF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn( name="id_article")
	private Articles articles;
	
	@ManyToOne
	@JoinColumn( name="id_coutArticlePF")
	private CoutArticlePF coutArticlePF;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="coutMP", precision = 30, scale = 12)
	private  BigDecimal coutMP;
	
	@Column(name="cout_Equipe_Generique", precision = 30, scale = 12)
	private  BigDecimal coutEquipeGenerique;
	
	@Column(name="cout_Equipe_Production", precision = 30, scale = 12)
	private  BigDecimal coutEquipeProduction;
	
	@Column(name="cout_Equipe_Emballage", precision = 30, scale = 12)
	private  BigDecimal coutEquipeEmballage;
	
	@Column(name="total", precision = 30, scale = 12)
	private  BigDecimal total;
	
	@Column(name="quantite_Realiser", precision = 30, scale = 12)
	private  BigDecimal quantiteRealiser;
	
	 
	@JoinColumn(name="CODE_TRANSFER")
	private String codeTransfer;
	 
	@Column(name="cout", precision = 30, scale = 12)
	private  BigDecimal cout;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Articles getArticles() {
		return articles;
	}


	public void setArticles(Articles articles) {
		this.articles = articles;
	}


	public CoutArticlePF getCoutArticlePF() {
		return coutArticlePF;
	}


	public void setCoutArticlePF(CoutArticlePF coutArticlePF) {
		this.coutArticlePF = coutArticlePF;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public BigDecimal getCoutMP() {
		return coutMP;
	}


	public void setCoutMP(BigDecimal coutMP) {
		this.coutMP = coutMP;
	}


	public BigDecimal getCoutEquipeGenerique() {
		return coutEquipeGenerique;
	}


	public void setCoutEquipeGenerique(BigDecimal coutEquipeGenerique) {
		this.coutEquipeGenerique = coutEquipeGenerique;
	}


	public BigDecimal getCoutEquipeProduction() {
		return coutEquipeProduction;
	}


	public void setCoutEquipeProduction(BigDecimal coutEquipeProduction) {
		this.coutEquipeProduction = coutEquipeProduction;
	}


	public BigDecimal getCoutEquipeEmballage() {
		return coutEquipeEmballage;
	}


	public void setCoutEquipeEmballage(BigDecimal coutEquipeEmballage) {
		this.coutEquipeEmballage = coutEquipeEmballage;
	}


	public BigDecimal getTotal() {
		return total;
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public BigDecimal getQuantiteRealiser() {
		return quantiteRealiser;
	}


	public void setQuantiteRealiser(BigDecimal quantiteRealiser) {
		this.quantiteRealiser = quantiteRealiser;
	}


	public BigDecimal getCout() {
		return cout;
	}


	public void setCout(BigDecimal cout) {
		this.cout = cout;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

 
	public String getCodeTransfer() {
		return codeTransfer;
	}


	public void setCodeTransfer(String codeTransfer) {
		this.codeTransfer = codeTransfer;
	}
 

	 
	

}