package dao.daoManager;

import java.util.List;

import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailNombreCartonOffreVariante;
import dao.entity.MotifPerteMP;
import dao.entity.NombreCartonOffreVariante;

public interface DetailNombreCartonOfferVariantDAO {
	
	public  void add(DetailNombreCartonOffreVariante e);
	
	public  DetailNombreCartonOffreVariante edit(DetailNombreCartonOffreVariante e);
	
	public    void delete(int id); 
	
	public List<DetailNombreCartonOffreVariante> findAll();
	
	public DetailNombreCartonOffreVariante findById(int id);
	 public List<DetailNombreCartonOffreVariante> findDetailNombreCartonOffreVarianteByNombreCartonOffreVariant(NombreCartonOffreVariante  nombreCartonOffreVariante);
	 public void deleteDetailNombrecartonOffreVarianteByNombreCartonOffreVariante(NombreCartonOffreVariante nombreCartonOffreVariante);
	

}
