package dao.daoManager;

import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MotifPerteMP;
import dao.entity.NombreCartonOffreVariante;
import dao.entity.Production;

public interface NombreCartonOfferVariantDAO {
	
	public  void add(NombreCartonOffreVariante e);
	
	public  NombreCartonOffreVariante edit(NombreCartonOffreVariante e);
	
	public  void delete  (int id); 
	
	public List<NombreCartonOffreVariante> findAll();
	
	public NombreCartonOffreVariante findById(int id);
	public  List<NombreCartonOffreVariante> findByProduction(Production production);
	
	 
	

}
