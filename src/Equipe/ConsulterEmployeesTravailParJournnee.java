package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.codehaus.groovy.syntax.Reduction;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Comparateur;
import util.ComparateurEmployeeTravailParJournnee;
import util.Constantes;
import util.ConverterNumberToWords;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.jaspersoft.ireport.designer.menu.preview.TXTPreviewAction;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailEmployeMenageDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.PrimeAnciennteDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailEmployeMenageDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.PrimeAnciennteDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteurAbsenceEmploye;
import dao.entity.CompteurEmployeProd;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.DetailEmployeMenage;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.ListeEmployeeTravailParJournnee;
import dao.entity.Parametre;
import dao.entity.PrimeAnciennte;
import dao.entity.Production;
import dao.entity.RecapFicheEmploye;
import dao.entity.Utilisateur;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;


public class ConsulterEmployeesTravailParJournnee extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<FicheEmploye> listFicheEmploye=new ArrayList<FicheEmploye>();
	private List<ListeEmployeeTravailParJournnee> listEmployeeTravailParJournneTmp=new ArrayList<ListeEmployeeTravailParJournnee>();
	private List<ListeEmployeeTravailParJournnee> listEmployeeTravailParJournneTmpAfficher=new ArrayList<ListeEmployeeTravailParJournnee>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private Utilisateur utilisateur;
	private JComboBox comboDepot = new JComboBox();
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private DepotDAO depotDAO;
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private DetailProdResDAO detailProdResDAO;
	private DetailProductionMPDAO detailProductionMPDAO;
	private ParametreDAO parametreDAO;
private DetailEmployeMenageDAO detailEmployeMenageDAO;
private CoutHorsProdEnAttentDAO coutHorsProdEnAttentDAO;
BigDecimal coutHeurs=BigDecimal.ZERO;
private PrimeAnciennteDAO primeAnciennteDAO;
private ProductionDAO productionDAO;
private List<Production> listProductionTerminer=new ArrayList<Production>();
JCheckBox checkTous = new JCheckBox("Tous");
JCheckBox check8 = new JCheckBox("8 H");
JCheckBox checkMoins8 = new JCheckBox("-8 H");
JCheckBox checkPlus8 = new JCheckBox("+8 H");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterEmployeesTravailParJournnee() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1321, 680);
        try{
        	
        	
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	detailProductionDAO=new DetailProductionDAOImpl();
        	detailResponsableDAO=new DetailResponsableProdDAOImpl();
        	detailProdGenDAO=new DetailProdGenDAOImpl();
        	compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
        	detailProdResDAO=new DetailProdResDAOImpl();
        	detailProductionMPDAO=new DetailProductionMPDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	detailEmployeMenageDAO=new DetailEmployeMenageDAOImpl();
        	coutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	primeAnciennteDAO=new PrimeAnciennteDAOImpl();
        	productionDAO=new ProductionDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.setShowVerticalLines(false);
				  		     table.setSelectionBackground(new Color(51, 204, 255));
				  		     table.setRowHeightEnabled(true);
				  		     table.setBackground(new Color(255, 255, 255));
				  		     table.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table.setColumnControlVisible(true);
				  		     table.setForeground(Color.BLACK);
				  		     table.setGridColor(new Color(0, 0, 255));
				  		     table.setAutoCreateRowSorter(true);
				  		     table.setBounds(2, 27, 411, 198);
				  		     table.setRowHeight(20);
				  		   table.getTableHeader().setReorderingAllowed(false);
				  		   modeleMP =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Matricule","Employee", "Total Heures"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false, false, false 
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		 table.setModel(new DefaultTableModel(
					  		 	new Object[][] {
					  		 	},
					  		 	new String[] {
					  		 			"Matricule","Employee", "Total Heures"
					  		 	}
					  		 ) {
					  		 	boolean[] columnEditables = new boolean[] {
					  		 			false, false, false 
					  		 	};
					  		 	public boolean isCellEditable(int row, int column) {
					  		 		return columnEditables[column];
					  		 	}
					  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(2).setPreferredWidth(160);
					  		 
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1302, 383);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1053, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Journnee  :");
				  		     	lblDateDebut.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1012, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				 
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
			}  else if(comboDepot.getSelectedItem().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le depot ", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else {
				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
				 
			 
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
				//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaion(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					
					listEmployeeTravailParJournneTmp=calculeCoutEmploye();
					
					 
					
				 
						 Collections.sort(listEmployeeTravailParJournneTmp, new ComparateurEmployeeTravailParJournnee());

						  listEmployeeTravailParJournneTmpAfficher.clear();
						 for(int d=0;d<listEmployeeTravailParJournneTmp.size();d++)
						 {
							 if(check8.isSelected()==true)
							 {
								 if(listEmployeeTravailParJournneTmp.get(d).getTotalHeures().compareTo(new BigDecimal(8))==0)
								 {
									 listEmployeeTravailParJournneTmpAfficher.add(listEmployeeTravailParJournneTmp.get(d));
								 }
								 
							 }  if(checkPlus8.isSelected()==true)
							 {
								 
								 if(listEmployeeTravailParJournneTmp.get(d).getTotalHeures().compareTo(new BigDecimal(8))==1)
								 {
									 listEmployeeTravailParJournneTmpAfficher.add(listEmployeeTravailParJournneTmp.get(d));
								 }
								 
							 } if(checkMoins8.isSelected()==true)
							 {
								 
								 if(listEmployeeTravailParJournneTmp.get(d).getTotalHeures().compareTo(new BigDecimal(8))==-1)
								 {
									 listEmployeeTravailParJournneTmpAfficher.add(listEmployeeTravailParJournneTmp.get(d));
								 }
								 
							 }
							
							 if(checkTous.isSelected()==true)
							 {
								 
									 listEmployeeTravailParJournneTmpAfficher.add(listEmployeeTravailParJournneTmp.get(d));
								 
								 
							 } 
							 
							 if(check8.isSelected()==false && checkPlus8.isSelected()==false && checkMoins8.isSelected()==false && checkTous.isSelected()==false)
							 {
								 listEmployeeTravailParJournneTmpAfficher.add(listEmployeeTravailParJournneTmp.get(d));
								  
							 }
							 
							 
							 
							 
						 }
						 
						 
						 
						 afficher_tableMP(listEmployeeTravailParJournneTmpAfficher);
					 
					 
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 11, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(228, 11, 130, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(297, 13, 191, 26);
		layeredPane.add(comboDepot);
		
		  checkPlus8 = new JCheckBox("+8 H");
		checkPlus8.setFont(new Font("Tahoma", Font.BOLD, 14));
		checkPlus8.setBounds(507, 14, 66, 23);
		layeredPane.add(checkPlus8);
		
		  checkMoins8 = new JCheckBox("-8 H");
		checkMoins8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		checkMoins8.setBounds(575, 14, 66, 23);
		layeredPane.add(checkMoins8);
		
		  check8 = new JCheckBox("8 H");
		check8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		check8.setBounds(643, 14, 66, 23);
		layeredPane.add(check8);
		
		  checkTous = new JCheckBox("Tous");
		  checkTous.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		if(checkTous.isSelected()==true)
		  		{
		  			
		  			check8.setSelected(false);
		  			checkMoins8.setSelected(false);
		  			checkPlus8.setSelected(false);
		  			
		  		}
		  	}
		  });
		checkTous.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		checkTous.setBounds(711, 12, 66, 23);
		layeredPane.add(checkTous);
	
		int k=0;
	      
      	if(utilisateur.getNom().equals("admin"))
      	{
      		
      		listDepot=depotDAO.findAll();
      		
      		while(k<listDepot.size())
      		{
      			
      			Depot depot=listDepot.get(k);
      			mapDepot.put(depot.getLibelle(), depot);
      			comboDepot.addItem(depot.getLibelle());
      			k++;
      		}
      		
      		
      	}else
      	{
      		
      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
      		
      		if(depot!=null)
      		{
      			comboDepot.addItem(depot.getLibelle());
      			mapDepot.put(depot.getLibelle(), depot);
      		}
      	}
	
	   		     
				  		 
	}
	
void afficher_tableMP(List<ListeEmployeeTravailParJournnee> listEmployeTravailParJournnee)
	{
		intialiserTableau();
		  int i=0;
		 
		
		  
		  
		   
		  
		  
		  
			 
			while(i<listEmployeTravailParJournnee.size())
			{	
				
				ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeTravailParJournnee.get(i);
					
					
				Object []ligne={ListeEmployeeTravailParJournnee.getEmploye().getMatricule(),ListeEmployeeTravailParJournnee.getEmploye().getNom(),ListeEmployeeTravailParJournnee.getTotalHeures()};

				modeleMP.addRow( ligne);
				i++;
			}
			
	 
			
				
			
	}

boolean remplirMapAvance(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("")){
			mapAvance.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 9).toString());
			trouve=true;
		}else {
			mapAvance.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}

void validerAvance(List<FicheEmploye> listFicheEmploye){/*
	BigDecimal avance=BigDecimal.ZERO;
	BigDecimal totalAvance=BigDecimal.ZERO;
	BigDecimal totalPrime=BigDecimal.ZERO;
	BigDecimal totalCout=BigDecimal.ZERO;
	BigDecimal totalDu=BigDecimal.ZERO;
	for(int i=0;i<listFicheEmploye.size();i++){	
		
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		
		avance=new BigDecimal(mapAvance.get(ficheEmploye.getId()));
		
		ficheEmploye.setAvance(avance);
		
		avance=new BigDecimal(mapAvance.get(ficheEmploye.getId()));
		totalAvance=totalAvance.add(avance);
		totalPrime=totalPrime.add(ficheEmploye.getRemise());
		totalCout=totalCout.add(ficheEmploye.getCoutTotal());
		
		ficheEmployeDAO.edit(ficheEmploye);
		
	}
	
	
	totalDu=(totalPrime.add(totalCout)).subtract(totalAvance);
	
	  DecimalFormat format = new DecimalFormat("#0.00");
	txtTotalDelai.setText(totalAvance+"");
	txtTotalCout.setText(totalCout+"");
	txtTotalPrime.setText(totalPrime+""); 
	txtTotalDu.setText(format.format(totalDu));
	
*/}

void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Matricule","Employee", "Total Heures"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false, false, false 
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      
      table.getTableHeader().setReorderingAllowed(false);
      //table.getColumnModel().getColumn(6).setPreferredWidth(90);
}




 



List<ListeEmployeeTravailParJournnee> calculeCoutEmploye(){
	Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	
	listEmployeeTravailParJournneTmp.clear();
	BigDecimal delai=BigDecimal.ZERO;
	
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	
	
	
	
	List<DetailProduction> listDetailProduction=detailProductionDAO.ListHeursDetailProductionParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,"");
	List<DetailProdGen> listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,"");
	List<DetailProdRes> listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(),"");
	List<DetailProductionMP> listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER,"");
	List<DetailEmployeMenage> listDetailEmployeMenage=detailEmployeMenageDAO.ListHeursDetailEmployeMenageParDepot(dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(),"");
	List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent=coutHorsProdEnAttentDAO.ListHeursCoutHorsProdEnAttentParDepotParDate (dateDebutChooser.getDate(), dateDebutChooser.getDate(), depot.getId(),"");

	///////////////////////////////////////////////////////////////////////////////////////////// Detail production /////////////////////////////////////////////////////////////////////
	
	for(int i=0;i<listDetailProduction.size();i++){
		
		remise=BigDecimal.ZERO;
		delai=BigDecimal.ZERO;
		coutHoraire=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		coutSupp25=BigDecimal.ZERO;
		coutSupp50=BigDecimal.ZERO;
		BigDecimal delaiProd=BigDecimal.ZERO;
		DetailProduction detailProduction =listDetailProduction.get(i);
		
		if(!detailProduction.getEmploye().isSalarie()){
			
			delai=detailProduction.getDelaiEmploye() ;
			heureSupp25=detailProduction.getHeureSupp25();
			heureSupp50=detailProduction.getHeureSupp50();
			
		
		
		if(!detailProduction.getEmploye().isSalarie()){
			
			Boolean Trouve=false;
			
			for(int j=0;j<listEmployeeTravailParJournneTmp.size();j++)
			{
				
				ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeeTravailParJournneTmp.get(j);
				
				if(detailProduction.getEmploye().getId()==ListeEmployeeTravailParJournnee.getEmploye().getId() &&  detailProduction.getProduction().getDate().equals(ListeEmployeeTravailParJournnee.getDateSituation()))
				{
					Trouve=true;
					
					
					ListeEmployeeTravailParJournnee.setTotalHeures(ListeEmployeeTravailParJournnee.getTotalHeures().add(detailProduction.getDelaiEmploye().add(detailProduction.getHeureSupp25().add(detailProduction.getHeureSupp50()))));
				 
					listEmployeeTravailParJournneTmp.set(j, ListeEmployeeTravailParJournnee);
				
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
			ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
	 
		ListeEmployeeTravailParJournneeTmp.setDateSituation(detailProduction.getProduction().getDate());
		ListeEmployeeTravailParJournneeTmp.setTotalHeures(detailProduction.getDelaiEmploye().add(detailProduction.getHeureSupp25().add(detailProduction.getHeureSupp50())));
		ListeEmployeeTravailParJournneeTmp.setEmploye(detailProduction.getEmploye());;
  
		listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);
			
		}
		}
		
		
	}
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////// Cout Hors production En Attent /////////////////////////////////////////////////////////////////////
	
	for(int i=0;i<listCoutHorsProdEnAttent.size();i++){
		
		remise=BigDecimal.ZERO;
		delai=BigDecimal.ZERO;
		coutHoraire=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		coutSupp25=BigDecimal.ZERO;
		coutSupp50=BigDecimal.ZERO;
		BigDecimal delaiProd=BigDecimal.ZERO;
		CoutHorsProdEnAttent coutHorsProdEnAttent =listCoutHorsProdEnAttent.get(i);
		
		if(!coutHorsProdEnAttent.getEmploye().isSalarie()){
			
			delai=coutHorsProdEnAttent.getDelaiEmploye() ;
			heureSupp25=coutHorsProdEnAttent.getHeure25();
			heureSupp50=coutHorsProdEnAttent.getHeure50();
		
		 
	
		
		
		if(coutHorsProdEnAttent.getEtat().equals(ETAT_VALIDER)){
			
			Boolean Trouve=false;
			
			for(int j=0;j<listEmployeeTravailParJournneTmp.size();j++)
			{
				
				ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeeTravailParJournneTmp.get(j);
				
				if(coutHorsProdEnAttent.getEmploye().getId()==ListeEmployeeTravailParJournnee.getEmploye().getId() &&  coutHorsProdEnAttent.getDateSituation().equals(ListeEmployeeTravailParJournnee.getDateSituation()))
				{
					Trouve=true;
					
					
					ListeEmployeeTravailParJournnee.setTotalHeures(ListeEmployeeTravailParJournnee.getTotalHeures().add(coutHorsProdEnAttent.getDelaiEmploye().add(coutHorsProdEnAttent.getHeure25().add(coutHorsProdEnAttent.getHeure50()))));
					 
					listEmployeeTravailParJournneTmp.set(j, ListeEmployeeTravailParJournnee);
				 
				
					
					
				}
				
				
				
			}
			
		if(Trouve==false){
	
	 
			ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
			 
			ListeEmployeeTravailParJournneeTmp.setDateSituation(coutHorsProdEnAttent.getProduction().getDate());
			ListeEmployeeTravailParJournneeTmp.setTotalHeures(coutHorsProdEnAttent.getDelaiEmploye().add(coutHorsProdEnAttent.getHeure25().add(coutHorsProdEnAttent.getHeure50())));
			ListeEmployeeTravailParJournneeTmp.setEmploye(coutHorsProdEnAttent.getEmploye());;
	  
			listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);
				
			
		}
		}
		
		
	}
	}
	
	
	
	
//////////////////////////////////////////////////////////////////////////////// Detail ProductionMP /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	for(int i=0;i<listDetailProductionMP.size();i++){
		
     delai=BigDecimal.ZERO;
		
		 remise=BigDecimal.ZERO;
		 coutHoraire=BigDecimal.ZERO;
	
		
		 coutSupp25=BigDecimal.ZERO;
		 coutSupp50=BigDecimal.ZERO;
		
		DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
		
		if(!detailProductionMP.getEmploye().isSalarie()){
			
			delai=detailProductionMP.getDelaiEmploye();
			heureSupp25=detailProductionMP.getHeureSupp25();
			heureSupp50=detailProductionMP.getHeureSupp50();
	 
	 
		
		
		
		
		if(!detailProductionMP.getEmploye().isSalarie()){
			
			Boolean Trouve=false;
			
			for(int j=0;j<listEmployeeTravailParJournneTmp.size();j++)
			{
				
				ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeeTravailParJournneTmp.get(j);
				
				if(detailProductionMP.getEmploye().getId()==ListeEmployeeTravailParJournnee.getEmploye().getId() &&  detailProductionMP.getProductionMP().getDateProduction().equals(ListeEmployeeTravailParJournnee.getDateSituation()))
				{
					Trouve=true;
				 
					ListeEmployeeTravailParJournnee.setTotalHeures(ListeEmployeeTravailParJournnee.getTotalHeures().add(detailProductionMP.getDelaiEmploye().add(detailProductionMP.getHeureSupp25().add(detailProductionMP.getHeureSupp50()))));
					 
					listEmployeeTravailParJournneTmp.set(j, ListeEmployeeTravailParJournnee);
				
				
					
					
				}
			
			}
		
		if(Trouve==false){
	
			ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
			 
			ListeEmployeeTravailParJournneeTmp.setDateSituation(detailProductionMP.getProductionMP().getDateProduction());
			ListeEmployeeTravailParJournneeTmp.setTotalHeures(detailProductionMP.getDelaiEmploye().add(detailProductionMP.getHeureSupp25().add(detailProductionMP.getHeureSupp50())));
			ListeEmployeeTravailParJournneeTmp.setEmploye(detailProductionMP.getEmploye());;
	  
			listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);
			  
			
		}
		}
		
		
	}
	}
	
	
	
	
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////// DetailProdGen ///////////////////////////////////////////////////////////////////////////////
	
	
	for(int i=0;i<listDetailProdGenerique.size();i++){
		
		DetailProdGen detailProdGen =listDetailProdGenerique.get(i);
		
		remise=BigDecimal.ZERO;
		delai=BigDecimal.ZERO;
		coutHoraire=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		coutSupp25=BigDecimal.ZERO;
		coutSupp50=BigDecimal.ZERO;
		
		if(!detailProdGen.getEmploye().isSalarie()){
			
			delai=detailProdGen.getDelaiEmploye();
			heureSupp25=detailProdGen.getHeureSupp25();
			heureSupp50=detailProdGen.getHeureSupp50();
	  
		
		if(!detailProdGen.getEmploye().isSalarie()){
			
	Boolean Trouve=false;
			
			for(int j=0;j<listEmployeeTravailParJournneTmp.size();j++)
			{
				
				ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeeTravailParJournneTmp.get(j);
				
				if(detailProdGen.getEmploye().getId()==ListeEmployeeTravailParJournnee.getEmploye().getId() &&  detailProdGen.getProductionGen().getDate().equals(ListeEmployeeTravailParJournnee.getDateSituation()))
				{
					Trouve=true;
					
					ListeEmployeeTravailParJournnee.setTotalHeures(ListeEmployeeTravailParJournnee.getTotalHeures().add(detailProdGen.getDelaiEmploye().add(detailProdGen.getHeureSupp25().add(detailProdGen.getHeureSupp50()))));
					 
					listEmployeeTravailParJournneTmp.set(j, ListeEmployeeTravailParJournnee);
				
				
					
					
				}
				
				
				
				
			}
		
		if(Trouve==false) {
			ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
			 
			ListeEmployeeTravailParJournneeTmp.setDateSituation(detailProdGen.getProductionGen().getDate());
			ListeEmployeeTravailParJournneeTmp.setTotalHeures(detailProdGen.getDelaiEmploye().add(detailProdGen.getHeureSupp25().add(detailProdGen.getHeureSupp50())));
			ListeEmployeeTravailParJournneeTmp.setEmploye(detailProdGen.getEmploye());;
	  
			listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);
			 
			
		}
		
		}
		
	}
	}
	
	///////////////////////////////////////////////////////////////////////// DetailResponsableProd //////////////////////////////////////////////////////////////////////////////////
	
	BigDecimal delaiEmployerResponsable=BigDecimal.ZERO;
	BigDecimal heuresupp25EmployerResponsable=BigDecimal.ZERO;
	BigDecimal heuresupp50EmployerResponsable=BigDecimal.ZERO;
	BigDecimal remiseEmployerResponsable=BigDecimal.ZERO;
	
	 for(int i=0;i<listDetailResponsableProd.size();i++){
		 
		  delaiEmployerResponsable=BigDecimal.ZERO;
		  heuresupp25EmployerResponsable=BigDecimal.ZERO;
		  heuresupp50EmployerResponsable=BigDecimal.ZERO;
		  remiseEmployerResponsable=BigDecimal.ZERO;

		 DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
		 
		 /*
			listProductionTerminer=productionDAO.LesProductionTerminerParDateSpecifierParDepotGroupByDate(detailResponsableProd.getDateProduction(), Constantes.ETAT_OF_TERMINER, depot.getCode());

			
			
			
			
		 					FicheEmploye ficheEmployeTmp=new FicheEmploye();
						
						//	ficheEmploye.setCoutTotal(coutTotal);
							//ficheEmployeTmp.setNumOF(detailResponsableProd.getProduction().getNumOF());
							ficheEmployeTmp.setDateSituation(detailResponsableProd.getDateProduction());
							
							ficheEmployeTmp.setSortie(detailResponsableProd.isSortie());
							ficheEmployeTmp.setAbsent(detailResponsableProd.isAbsent());
							ficheEmployeTmp.setRetard(detailResponsableProd.isRetard());
							
							for(int d=0;d<listProductionTerminer.size();d++)
							{
								if(detailResponsableProd.getTotalHeuresProduction().compareTo(BigDecimal.ZERO)!=0)
								{
									

									delaiEmployerResponsable= detailResponsableProd.getDelaiEmploye().divide( detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.HALF_UP).multiply(listProductionTerminer.get(d).getNbreHeure());
									heuresupp25EmployerResponsable= detailResponsableProd.getHeureSupp25().divide( detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.HALF_UP).multiply(listProductionTerminer.get(d).getNbreHeure());
									heuresupp50EmployerResponsable= detailResponsableProd.getHeureSupp50().divide( detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.HALF_UP).multiply(listProductionTerminer.get(d).getNbreHeure());
									if(detailResponsableProd.getRemise()!=null)
									{
										remiseEmployerResponsable=remiseEmployerResponsable.add((((detailResponsableProd.getRemise().divide(new BigDecimal(8),6,BigDecimal.ROUND_HALF_UP)).multiply(detailResponsableProd.getDelaiEmploye())).divide(detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.HALF_UP)).multiply(listProductionTerminer.get(d).getNbreHeure()));

									}
									
									Boolean Trouve=false;
									
									for(int j=0;j<listEmployeeTravailParJournneTmp.size();j++)
									{
										
										ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeeTravailParJournneTmp.get(j);
										
										if(detailResponsableProd.getEmploye().getId()==ListeEmployeeTravailParJournnee.getEmploye().getId() &&  detailResponsableProd.getDateProduction().equals(ListeEmployeeTravailParJournnee.getDateSituation()))
										{
											Trouve=true;
											
											ListeEmployeeTravailParJournnee.setTotalHeures(ListeEmployeeTravailParJournnee.getTotalHeures().add(delaiEmployerResponsable.setScale(4, RoundingMode.HALF_UP).add(heuresupp25EmployerResponsable.setScale(4, RoundingMode.HALF_UP).add(heuresupp50EmployerResponsable.setScale(4, RoundingMode.HALF_UP)))));
											 
											listEmployeeTravailParJournneTmp.set(j, ListeEmployeeTravailParJournnee);
										
										
											
											
										}
										
										
										
										
									}
								
								if(Trouve==false) {
									ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
									 
									ListeEmployeeTravailParJournneeTmp.setDateSituation(detailResponsableProd.getDateProduction());
									ListeEmployeeTravailParJournneeTmp.setTotalHeures(delaiEmployerResponsable.setScale(4, RoundingMode.HALF_UP).add(heuresupp25EmployerResponsable.setScale(4, RoundingMode.HALF_UP).add(heuresupp50EmployerResponsable.setScale(4, RoundingMode.HALF_UP))));
									ListeEmployeeTravailParJournneeTmp.setEmploye(detailResponsableProd.getEmploye());;
							  
									listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);
									 
									
								}	
									
									
									
									
								
								
								}
								
								
							}*/
							
							ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
							 
							ListeEmployeeTravailParJournneeTmp.setDateSituation(detailResponsableProd.getDateProduction());
							ListeEmployeeTravailParJournneeTmp.setTotalHeures(detailResponsableProd.getDelaiEmploye().add(detailResponsableProd.getHeureSupp25().add(detailResponsableProd.getHeureSupp50())));
							ListeEmployeeTravailParJournneeTmp.setEmploye(detailResponsableProd.getEmploye());;
					  
							listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);				
				 
		}
	
	
		///////////////////////////////////////////////////////////////////////// DetailemployeMenage //////////////////////////////////////////////////////////////////////////////////
		
		
	 for(int i=0;i<listDetailEmployeMenage.size();i++){

		 DetailEmployeMenage detailEmployeMenage=listDetailEmployeMenage.get(i);

	Boolean Trouve=false;
			
			for(int j=0;j<listEmployeeTravailParJournneTmp.size();j++)
			{
				
				ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournnee=listEmployeeTravailParJournneTmp.get(j);
				
				if(detailEmployeMenage.getEmploye().getId()==ListeEmployeeTravailParJournnee.getEmploye().getId() &&  detailEmployeMenage.getDateTravail().equals(ListeEmployeeTravailParJournnee.getDateSituation()))
				{
					Trouve=true;
					
					ListeEmployeeTravailParJournnee.setTotalHeures(ListeEmployeeTravailParJournnee.getTotalHeures().add(detailEmployeMenage.getDelaiEmploye()));
					 
					listEmployeeTravailParJournneTmp.set(j, ListeEmployeeTravailParJournnee);
				
				
					
					
				}
				
				
				
				
			}
		
		if(Trouve==false) {
			ListeEmployeeTravailParJournnee ListeEmployeeTravailParJournneeTmp =new ListeEmployeeTravailParJournnee();
			 
			ListeEmployeeTravailParJournneeTmp.setDateSituation(detailEmployeMenage.getDateTravail());
			ListeEmployeeTravailParJournneeTmp.setTotalHeures(detailEmployeMenage.getDelaiEmploye());
			ListeEmployeeTravailParJournneeTmp.setEmploye(detailEmployeMenage.getEmploye());;
	  
			listEmployeeTravailParJournneTmp.add(ListeEmployeeTravailParJournneeTmp);
			 
			
		}
								 
		}	 
	 
	 
	 
	 
	 
	
	return listEmployeeTravailParJournneTmp;
	
}
}
