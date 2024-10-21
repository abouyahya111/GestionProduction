package util;
import java.util.Comparator;

import dao.entity.EtatStockMP;
import dao.entity.FicheEmploye;
import dao.entity.ProductionArticleParMois;
public class ComparateurProductionArticleParMois implements Comparator<ProductionArticleParMois>{
	
	
	
	

	
	    public int compare(ProductionArticleParMois IdMP1, ProductionArticleParMois IdMP2) {
	    	
	    	 if (IdMP1.getMp().getId() > IdMP2.getMp().getId())  
	    	 {
	    		 return 1;  
	    	 }
	             
	         else  if(IdMP1.getMp().getId() < IdMP2.getMp().getId())
	         {
	        	 return -1;
	         }else
	        	 return 0;
	            
	    	
	    	  
	    }
	
	
	
	

}
