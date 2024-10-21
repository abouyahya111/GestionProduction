package dao.daoManager;

import java.util.Date;
import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutArticlePF;
import dao.entity.CoutMP;
import dao.entity.DetailCoutArticlePF;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;

public interface DetailCoutArticlePFDAO {
	
	public  void add(DetailCoutArticlePF e);
	
	public  DetailCoutArticlePF edit(DetailCoutArticlePF e);
	
	public  void delete(int id); 
	
	public List<DetailCoutArticlePF> findAll();
	
	public DetailCoutArticlePF findById(int id);
	public  DetailCoutArticlePF DetailCoutArticlePFParCodeTransfert(String codeTransfert);
	 
	
	public void ViderSession();
	
	

}
