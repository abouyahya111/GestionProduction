package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutArticlePF;
import dao.entity.CoutMP;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;

public interface CoutArticlePFDAO {
	
	public  void add(CoutArticlePF e);
	
	public  CoutArticlePF edit(CoutArticlePF e);
	
	public  void delete(int id); 
	
	public List<CoutArticlePF> findAll();
	
	public CoutArticlePF findById(int id);
	public  CoutArticlePF CoutArticlePFByArticle(Articles article);
	public List<Object[]> SommeTotalMontantArticlePF();
	
	public void ViderSession();
	
	

}
