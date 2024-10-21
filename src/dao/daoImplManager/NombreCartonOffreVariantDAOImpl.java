package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.NombreCartonOfferVariantDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.MatierePremierModifier;
import dao.entity.MotifPerteMP;
import dao.entity.NombreCartonOffreVariante;
import dao.entity.Production;

public class NombreCartonOffreVariantDAOImpl implements NombreCartonOfferVariantDAO{
	Session session=HibernateUtil.openSession();

	public void add(dao.entity.NombreCartonOffreVariante e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public dao.entity.NombreCartonOffreVariante edit(dao.entity.NombreCartonOffreVariante e) {
		
	session.beginTransaction();
	dao.entity.NombreCartonOffreVariante p= (dao.entity.NombreCartonOffreVariante)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		dao.entity.NombreCartonOffreVariante p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<dao.entity.NombreCartonOffreVariante> findAll() {
		return session.createQuery("select c from NombreCartonOffreVariante c").list();
		}

	public  dao.entity.NombreCartonOffreVariante findById(int id) {
		return (dao.entity.NombreCartonOffreVariante)session.get(dao.entity.NombreCartonOffreVariante.class, id);
		}

	 
	 
	public  List<NombreCartonOffreVariante>  findByProduction(Production production) {
		// TODO Auto-generated method stub
		
		Query query= session.createQuery("select c from NombreCartonOffreVariante c where production.id=:production");
		query.setParameter("production", production.getId());
		
		
		
		return   query.list();
	}
	
 
	

}
