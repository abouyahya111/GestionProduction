package dao.daoImplManager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.DetailNombreCartonOfferVariantDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.daoManager.NombreCartonOfferVariantDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.DetailEstimation;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailNombreCartonOffreVariante;
import dao.entity.MotifPerteMP;
import dao.entity.NombreCartonOffreVariante;

public class DetailNombreCartonOffreVariantDAOImpl implements DetailNombreCartonOfferVariantDAO{
	Session session=HibernateUtil.openSession();

	public void add(DetailNombreCartonOffreVariante e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailNombreCartonOffreVariante edit(DetailNombreCartonOffreVariante e) {
		
	session.beginTransaction();
	DetailNombreCartonOffreVariante p= (DetailNombreCartonOffreVariante)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailNombreCartonOffreVariante p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailNombreCartonOffreVariante> findAll() {
		return session.createQuery("select c from DetailNombreCartonOffreVariante c").list();
		}

	public  DetailNombreCartonOffreVariante findById(int id) {
		return (DetailNombreCartonOffreVariante)session.get(DetailNombreCartonOffreVariante.class, id);
		}

	 

    public List<DetailNombreCartonOffreVariante> findDetailNombreCartonOffreVarianteByNombreCartonOffreVariant(NombreCartonOffreVariante  nombreCartonOffreVariante) {
	Query query = session.createQuery("select c from DetailNombreCartonOffreVariante c where c.nombreCartonOffreVariant.id=:nombreCartonOffreVariante");
             query.setParameter("nombreCartonOffreVariante",nombreCartonOffreVariante.getId());

              return  query.list();
    }
	
    
	public void deleteDetailNombrecartonOffreVarianteByNombreCartonOffreVariante(NombreCartonOffreVariante nombreCartonOffreVariante) {
			
		session.beginTransaction();
		Query query = session.createQuery("delete from DetailNombreCartonOffreVariante c where c.nombreCartonOffreVariant.id=:nombreCartonOffreVariante");		
		query.setParameter("nombreCartonOffreVariante",nombreCartonOffreVariante.getId());
		 int rowsAffected = query.executeUpdate();
		 System.out.println(rowsAffected);
		 session.getTransaction().commit();
	}
 
    
    
    
    
	

}
