package dao.daoImplManager;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoManager.ActionMPDAO;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CoutArticlePFDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DetailCoutArticlePFDAO;
import dao.daoManager.MotifPerteMPDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutArticlePF;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailCoutArticlePF;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;

public class DetailCoutArticlePFDAOImpl implements DetailCoutArticlePFDAO {
	Session session=HibernateUtil.openSession();

	public void add(DetailCoutArticlePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public DetailCoutArticlePF edit(DetailCoutArticlePF e) {
		
	session.beginTransaction();
	DetailCoutArticlePF p= (DetailCoutArticlePF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		DetailCoutArticlePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<DetailCoutArticlePF> findAll() {
		return session.createQuery("select c from DetailCoutArticlePF c").list();
		}

	public DetailCoutArticlePF findById(int id) {
		return (DetailCoutArticlePF)session.get(DetailCoutArticlePF.class, id);
		}
	
	
	public  DetailCoutArticlePF DetailCoutArticlePFParCodeTransfert(String codeTransfert) {
		
		Query query=null;
		
		 
			query= session.createQuery("select  p FROM DetailCoutArticlePF p   where p.codeTransfer=:codeTransfert");

			query.setParameter("codeTransfert", codeTransfert);
			 
		return (DetailCoutArticlePF) query.uniqueResult();
		
		
	} 
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	
	

	
	
	
	
	

}
