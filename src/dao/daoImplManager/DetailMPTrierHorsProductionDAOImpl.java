package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Depot;
import dao.entity.DetailMPTrierHorsProduction;
import dao.entity.DetailTypeSortie;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.TypeSortie;
import dao.entity.Utilisateur;
import main.AuthentificationView;

public class DetailMPTrierHorsProductionDAOImpl implements TransferStockMPDAO {
	/*
	Session session=HibernateUtil.openSession();
	
	

	public void add(DetailMPTrierHorsProduction e) {
		
		
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailMPTrierHorsProduction edit(DetailMPTrierHorsProduction e) {
		
	session.beginTransaction();
	DetailMPTrierHorsProduction p= (DetailMPTrierHorsProduction)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailMPTrierHorsProduction p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailMPTrierHorsProduction> findAll() {
		return session.createQuery("select c from DetailMPTrierHorsProduction c").list();
		}

	public DetailMPTrierHorsProduction findById(int id) {
		return (DetailMPTrierHorsProduction)session.get(DetailMPTrierHorsProduction.class, id);
		}
	
 
 
 
public void ViderSession()
{
	if(session!=null)
	{
		session.clear();
	}
}
	


 
*/

}
