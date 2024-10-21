package util;
import java.util.Comparator;

import dao.entity.FicheEmploye;
import dao.entity.ListeEmployeeTravailParJournnee;
public class ComparateurEmployeeTravailParJournnee implements Comparator<ListeEmployeeTravailParJournnee>{
	
	
	
	

	
	    @Override
	    public int compare(ListeEmployeeTravailParJournnee nom1, ListeEmployeeTravailParJournnee nom2) {
	        return nom1.getEmploye().getNom().compareTo(nom2.getEmploye().getNom());
	    }
	
	
	
	

}
