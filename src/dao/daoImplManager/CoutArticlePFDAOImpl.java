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
import dao.daoManager.MotifPerteMPDAO;
import dao.entity.ActionMP;
import dao.entity.Articles;
import dao.entity.CoutArticlePF;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.FournisseurMP;
import dao.entity.MatierePremier;
import dao.entity.MotifPerteMP;
import dao.entity.Production;
import dao.entity.ProductionMP;

public class CoutArticlePFDAOImpl implements CoutArticlePFDAO {
	Session session=HibernateUtil.openSession();

	public void add(CoutArticlePF e) {
		session.beginTransaction();
		session.save(e);
		
		session.getTransaction().commit();
		//return p;
	}

	public CoutArticlePF edit(CoutArticlePF e) {
		
	session.beginTransaction();
	CoutArticlePF p= (CoutArticlePF)session.merge(e);
	session.getTransaction().commit();
	
	return p;
	}

	public void delete(int id) {
		
		session.beginTransaction();
		CoutArticlePF p= findById(id);
		session.delete(p);
		session.getTransaction().commit();
		
	}

	public List<CoutArticlePF> findAll() {
		return session.createQuery("select c from CoutArticlePF c").list();
		}

	public CoutArticlePF findById(int id) {
		return (CoutArticlePF)session.get(CoutArticlePF.class, id);
		}
	
	
	public  CoutArticlePF CoutArticlePFByArticle(Articles article) {
		
		Query query=null;
		
		 
			query= session.createQuery("select  p FROM CoutArticlePF p   where p.articles.id=:article ");

			query.setParameter("article", article.getId());
			 
		return (CoutArticlePF) query.uniqueResult();
		
		
	} 	
	
	
	
	
	@Override
	public List<Object[]> SommeTotalMontantArticlePF() {
		// TODO Auto-generated method stub
		Query query=null;
		
			
			query= session.createQuery("select c.id, sum(c.quantiteRealiser * c.cout) FROM CoutArticlePF c");
		
	 
		
		return query.list();

	}
	
	
	public void ViderSession()
	{
		if(session!=null)
		{
			session.clear();
		}
	}
	
	
	
	

	
	
	
	
	

}
