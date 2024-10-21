package Equipe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.ConverterNumberToWords;
import util.ExcelUtils;
import util.JasperUtils;
import util.Utils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EmployeReposDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EmployeReposDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CompteurEmployeProd;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EmployeRepos;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SituationDesEmployeesAbsents;
import dao.entity.SituationDesEmployeesAbsentsParJour;
import dao.entity.Utilisateur;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AutorisationDesAbsents extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JComboBox comboDepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapEmploye = new HashMap<>();
	private List<SituationDesEmployeesAbsents> listFicheEmploye=new ArrayList<SituationDesEmployeesAbsents>();
	private List<FicheEmployeGlobale> listFicheEmployeGlobale=new ArrayList<FicheEmployeGlobale>();
	private List< Depot> listDepot = new ArrayList<Depot>();
	private DepotDAO depotDAO ;
	private Utilisateur utilisateur;
	
	private FicheEmployeDAO ficheEmployeDAO;
	private EmployeDAO employeDAO;
	private CompteurAbsenceEmployeDAO compteurabsenceemployedao;
	private BigDecimal totalHoraire=BigDecimal.ZERO;
	private List<SituationDesEmployeesAbsents> listFicheEmployeAbsentTmp=new ArrayList<SituationDesEmployeesAbsents>();
	private List<SituationDesEmployeesAbsentsParJour> listFicheEmployeAbsentParJour=new ArrayList<SituationDesEmployeesAbsentsParJour>();
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private DetailProdResDAO detailProdResDAO;
	private DetailProductionMPDAO detailProductionMPDAO;
	private ParametreDAO parametreDAO;
	JLabel labelmatricule = new JLabel("Matricule :");
	JLabel labelemploye = new JLabel("Employe :");
	JCheckBox chckbxAbsentsParJour = new JCheckBox("Absents Par Jour");
	JComboBox comboemploye = new JComboBox();
	private ProductionDAO productionDAO;
	private EmployeReposDAO employeReposDAO;
	 private CoutHorsProdEnAttentDAO coutHorsProdEnAttentDAO;
 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	String message="";
	public AutorisationDesAbsents() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1446, 794);
        try{
        	
        	
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	compteurabsenceemployedao=new CompteurAbsenceEmployeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	utilisateur=AuthentificationView.utilisateur;
        	detailProductionDAO=new DetailProductionDAOImpl();
        	
        	detailProdGenDAO=new DetailProdGenDAOImpl();
        	compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
        	detailProdResDAO=new DetailProdResDAOImpl();
        	detailProductionMPDAO=new DetailProductionMPDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	productionDAO= new ProductionDAOImpl();
        	employeReposDAO=new EmployeReposDAOImpl();
        	coutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	listEmploye=employeDAO.findAll();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
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
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent e) {
				  		     	  int column = table.columnAtPoint( e.getPoint() );
				  		     		int row =table.rowAtPoint(e.getPoint());
				  		     		
				  		     		if(column==5)
				  		     		{
				  		     			
				  		     			boolean absent=(boolean) table.getValueAt(row, 5);	
				  		     			if(absent==true)
				  		     			{
				  		     				modeleMP.setValueAt(false, row, 6);
				  		     				modeleMP.setValueAt(false, row, 4);
				  		     				modeleMP.setValueAt("ABSENT", row, 3);
				  		     				
				  		     				
				  		     			} else
				  		     			{
				  		     				modeleMP.setValueAt(false, row, 6);
				  		     				modeleMP.setValueAt(false, row, 4);
				  		     				modeleMP.setValueAt("", row, 3);
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		if(column==6)
				  		     		{
				  		     			
				  		     			boolean absent=(boolean) table.getValueAt(row, 6);	
				  		     			if(absent==true)
				  		     			{
				  		     				modeleMP.setValueAt(false, row, 5);
				  		     				modeleMP.setValueAt(false, row, 4);
				  		     				modeleMP.setValueAt("", row, 3);
				  		     				
				  		     				
				  		     			} 
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     		if(column==4)
				  		     		{
				  		     			 
				  		     			
				  		     			boolean autorisation=(boolean) table.getValueAt(row, 4);	
				  		     			if(autorisation==false)
				  		     			{
				  		     				modeleMP.setValueAt("ABSENT", row, 3);
				  		     				 
				  		     				
				  		     				
				  		     			} else
				  		     			{
				  		     				modeleMP.setValueAt("", row, 3);
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     	}
				  		     });
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
					  		     			"Matricule","Nom Employer", "Equipe","Motif", "Autoriser"					  		     			
					  		     	}
					  		  
				  				   ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     		 false,false, false, true, true
					  		     	};
					  		     	
					  		     	Class[] columnTypes = new Class[] {
											String.class,String.class,String.class,String.class, Boolean.class
										};
									  public Class getColumnClass(int columnIndex) {
											return columnTypes[columnIndex];
										}
					  		     	
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  			 table.setModel(new DefaultTableModel(
								  		 	new Object[][] {
								  		 	},
								  		 	new String[] {
								  		 			"Date","Matricule","Nom Employer", "Equipe","Motif", "Autoriser"								  		 			
								  		 	}
								  		 ) {
								  		 	boolean[] columnEditables = new boolean[] {
								  		 		 false,false,false, false, true, true
								  		 	};
								  		 	Class[] columnTypes = new Class[] {
								  		 			Date.class,String.class,String.class,String.class,String.class, Boolean.class
												};
											  public Class getColumnClass(int columnIndex) {
													return columnTypes[columnIndex];
												}
											  
								  		 	public boolean isCellEditable(int row, int column) {
								  		 		return columnEditables[column];
								  		 	}
								  		 });
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					  		 table.getColumnModel().getColumn(1).setPreferredWidth(60);
					  		 table.getTableHeader().setReorderingAllowed(false);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 156, 1427, 497);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 0, 1427, 91);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Date d\u00E9but :");
				  		     	lblDateDebut.setBounds(10, 36, 96, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(527, 36, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listFicheEmploye.clear();
				listFicheEmployeGlobale.clear();
				listFicheEmployeAbsentParJour.clear();
				
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				 
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
				return;
			}  
			
			
			
			/*else if(!verifierdate())
					{
				JOptionPane.showMessageDialog(null,message, "Erreur", JOptionPane.ERROR_MESSAGE);
					
						return;	
			
				}*/else {
					

			
	
					
					//List<Object> listObject=ficheEmployeDAO.findByDateSitutaionAgregation(dateDebutChooser.getDate(), dateFinChooser.getDate(), txtCNI.getText());
					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					if(depot==null)
					{
						JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//	listFicheEmploye=ficheEmployeDAO.findByDateSitutaionGlobale(dateDebutChooser.getDate(), dateFinChooser.getDate(),depot.getId());
					listFicheEmploye=calculeCoutEmploye();
			
					
					/*
					Collections.sort(listFicheEmploye, new Comparator<SituationDesEmployeesAbsents>() {
						  @Override
						  public int compare(SituationDesEmployeesAbsents u1, SituationDesEmployeesAbsents u2) {
						    return u1.getEmploye().getNom().compareTo(u2.getEmploye().getNom());
						  }
						});
					
					*/
					//AjouterDetailProdResponsable();
					
					//calculerTotaux(listFicheEmploye);
					
					
					if(listFicheEmploye==null ||  listFicheEmploye.size()==0){
						JOptionPane.showMessageDialog(null, "Il n'existe pas aucune activité pour cet employé dans cette période!!", "Erreur", JOptionPane.ERROR_MESSAGE);
						intialiserTableau();
						
					}else {
					
						afficher_tableMP(listFicheEmploye);
						
					}
					
				
					
				
					
					
				}
			
			}
		  }
		);
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(76, 36, 130, 24);
		layeredPane.add(dateDebutChooser);
		
		JLabel label = new JLabel("Depot :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(216, 37, 73, 26);
		layeredPane.add(label);
		
		 comboDepot = new JComboBox();
		comboDepot.setBounds(275, 36, 191, 26);
		layeredPane.add(comboDepot);
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
	
		JButton btnImprimer = new JButton("Valider");
		btnImprimer.setIcon(new ImageIcon(AutorisationDesAbsents.class.getResource("/img/valider.png")));
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Depot depot=mapDepot.get(comboDepot.getSelectedItem().toString());
				boolean erreurcoche=false;
				boolean erreurMotif=false;
		  		
		  		for(int i=0;i<table.getRowCount();i++)
		  		{
		  			boolean absent=(boolean) table.getValueAt(i, 5);
		  			boolean repos=(boolean) table.getValueAt(i, 6);
		  			
		  		
		  			if( (absent==false && repos==false) || (absent==true && repos==true)) 
		  			{
		  				erreurcoche=true;
		  			}
		  			
		  			if(absent==true)
		  			{
		  				if(table.getValueAt(i, 3).toString().isEmpty()==true)
		  				{
		  					erreurMotif=true;
		  				}
		  			}
		  		}
		  		
		  		
		  		
		  		if(erreurcoche==true)
		  		{
		  			JOptionPane.showMessageDialog(null, "Veuillez Cocher Juste Absent Ou Repos Pour Un Employee SVP !!!!");
		  			return;
		  		}
		  		if(erreurMotif==true)
		  		{
		  			JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Motif Pour Un Employe Absent SVP !!!!");
		  			return;
		  		}
				
				
				Parametre heure=parametreDAO.findByDateByLibelle(dateDebutChooser.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
				  List<Production> listProductionsTerminer=new ArrayList<Production>();
			
		  		listProductionsTerminer=productionDAO.LesProductionTerminerParDateSpecifierParDepotGroupByDate(dateDebutChooser.getDate(), Constantes.ETAT_OF_TERMINER, utilisateur.getCodeDepot()) ;
	  		  boolean ajouter=false;
		  		
		  		for(int i=0;i<table.getRowCount();i++)
		  		{
		  			
		  			
		  			
		  			 Employe employe=mapMatriculeEmploye.get(table.getValueAt(i, 1));
		  			boolean absent=(boolean) table.getValueAt(i, 5);
		  			boolean repos=(boolean) table.getValueAt(i, 6);
		  			if(absent==true)
		  			{
		  				boolean autorisation=(boolean) table.getValueAt(i, 4);
			  			if(listProductionsTerminer.size()!=0)
			  		  	{
			  				
			  			
			  				
			  		  	Production productionTmp=listProductionsTerminer.get(listProductionsTerminer.size()-1)	;
			  		  	
			  		  	
			  		  	if(productionTmp!=null)
			  		  	{
			  		  	ajouter=true;
				  		    	
			  		  	DetailProduction detailproduction=new DetailProduction();	
			  		  	
				  		    	detailproduction.setEmploye(employe);
				  		       detailproduction.setTypeResEmploye(employe.getTypeResEmploye());
				  		    	detailproduction.setDelaiEmploye(BigDecimal.ZERO);
				  		    	detailproduction.setHeureSupp25(BigDecimal.ZERO);
				  		    	detailproduction.setHeureSupp50(BigDecimal.ZERO);
				  		    	detailproduction.setAbsent(true);
				  		        detailproduction.setCoutTotal(BigDecimal.ZERO);
				  		      detailproduction.setRemise(BigDecimal.ZERO);
						        detailproduction.setCoutSupp25(BigDecimal.ZERO);
					            detailproduction.setCoutSupp50(BigDecimal.ZERO);
					            
					          detailproduction.setCoutHoraire(heure.getValeur());
					        detailproduction.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
					      detailproduction.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
					      
				  		    detailproduction.setSortie(false);
				  		detailproduction.setRetard(false);
				  	detailproduction.setAutorisation(autorisation);
				  detailproduction.setValider(Constantes.ETAT_VALIDER);
				  detailproduction.setMotif(table.getValueAt(i, 3).toString());
				  detailproduction.setProduction(productionTmp);
				  detailProductionDAO.add(detailproduction);
				  		    		
				  		    	
			  		  		
			  		  		
			  		  		
			  		  		
			  		  		
			  		  	}
			  		  	
			  		   
			  		  	
			  		  	
			  		  	}
		  			}else if(repos==true)
		  			{
		  			  	ajouter=true;
		  				
		  				EmployeRepos employeRepos=new EmployeRepos();
						employeRepos.setEmploye(employe);
						employeRepos.setDateSituation(dateDebutChooser.getDate());
						employeRepos.setDepot(depot);
						employeRepos.setEquipe("");
						employeRepos.setUtilCreation(utilisateur);
						employeReposDAO.add(employeRepos);	
		  				
		  				
		  			}
		  		
		  			
		  			
		  		}
		  		
		  	if(ajouter==true)
		  		{
		  		
		  		JOptionPane.showMessageDialog(null, "Opération effectué Avec Succee", "Bravo",JOptionPane.INFORMATION_MESSAGE);
		  		listFicheEmploye=calculeCoutEmploye();
		  		afficher_tableMP(listFicheEmploye);
		  		
		  		}
		  	
			
			
			
			
			
			
			}
		});
		btnImprimer.setBounds(615, 676, 155, 24);
		add(btnImprimer);
			
			for(int i=0;i<listEmploye.size();i++)
			{
				
				Employe employe=listEmploye.get(i);
				comboemploye.addItem(employe.getNomafficher());
				mapEmploye.put(employe.getNomafficher(), employe);
				mapMatriculeEmploye.put(employe.getMatricule(), employe);
				
			} 
		 
		 
		 
		 
		
			  		 
	}
	
void afficher_tableMP(List<SituationDesEmployeesAbsents> listFicheEmploye )
	{
		intialiserTableau();
		

		
		int j=0;
		while(j<listFicheEmploye.size())
		{
			SituationDesEmployeesAbsents situationDesEmployeesAbsents=listFicheEmploye.get(j);
			 SimpleDateFormat simpledate = new SimpleDateFormat("dd/MM/yyyy");
			 boolean autoriser=false;
			 String motif="";
			if(situationDesEmployeesAbsents.getMotif()!=null)
			{
				if(!situationDesEmployeesAbsents.getMotif().equals(""))
				{
					motif=situationDesEmployeesAbsents.getMotif();
				}
				
				
			}
			
			autoriser=situationDesEmployeesAbsents.isAutoriser();

		
			mapMatriculeEmploye.put(situationDesEmployeesAbsents.getEmploye().getMatricule(), situationDesEmployeesAbsents.getEmploye());
			
			
				Object []ligne={simpledate.format(situationDesEmployeesAbsents.getDateSituation()) , situationDesEmployeesAbsents.getEmploye().getMatricule(),situationDesEmployeesAbsents.getEmploye().getNomafficher(),situationDesEmployeesAbsents.getEquipe(),Constantes.EMPLOYEE_ABSENT,autoriser,false, false};

				modeleMP.addRow( ligne);
				j++;
			}
			
			
	}











//void validerAvance(List<FicheEmploye> listFicheEmploye){
//	BigDecimal avance=0;
//	BigDecimal totalAvance=0;
//	BigDecimal totalPrime=0;
//	BigDecimal totalCout=0;
//	BigDecimal totalDu=0;
//	for(int i=0;i<listFicheEmploye.size();i++){	
//		
//		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
//		
//		avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
//		
//		ficheEmploye.setAvance(avance);
//		
//		avance=BigDecimal.parseBigDecimal(mapAvance.get(ficheEmploye.getId()));
//		totalAvance=totalAvance+avance;
//		totalPrime=totalPrime+ficheEmploye.getRemise();
//		totalCout=totalCout+ficheEmploye.getCoutTotal();
//		
//		ficheEmployeDAO.edit(ficheEmploye);
//		
//	}
//	
//	
//	totalDu=(totalPrime+totalCout)-totalAvance;
//	 DecimalFormat format = new DecimalFormat("#.00");
//	
//	
//	
//}

void intialiserTableau(){
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date","Matricule","Nom Employer","Motif", "Autoriser", "Absent", "Repos"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false, false, true, true, true
		     	};
		    	Class[] columnTypes = new Class[] {
		    			Date.class,String.class,String.class,String.class, Boolean.class, Boolean.class, Boolean.class
					};
				  public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
		     	public boolean isCellEditable(int row, int column) {
		     		
		     if(column==4 && (boolean)table.getValueAt(row, 5)==false)
		     {
		    	 return false;
		     }else if(column==3 && (boolean)table.getValueAt(row, 4)==true)
		     {
		    	 return true; 
		     } 
		      
		     	
		     		
		     		
		     		return columnEditables[column];
		     		
		     		
		     	}
		     };
		     
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(80);
		 table.getColumnModel().getColumn(1).setPreferredWidth(80);
      table.getColumnModel().getColumn(2).setPreferredWidth(80);
      table.getColumnModel().getColumn(3).setPreferredWidth(80);
      table.getColumnModel().getColumn(4).setPreferredWidth(150);
      table.getColumnModel().getColumn(5).setPreferredWidth(80);
      table.getTableHeader().setReorderingAllowed(false);
      //table.getColumnModel().getColumn(6).setPreferredWidth(90);
}









@SuppressWarnings("deprecation")
	List<SituationDesEmployeesAbsents> calculeCoutEmploye() {
		/*
		 * Depot depot=mapDepot.get(comboDepot.getSelectedItem()); String matricule="";
		 * listFicheEmployeAbsentTmp.clear(); List<DetailProduction>
		 * listDetailProduction=new ArrayList<DetailProduction>(); List<DetailProdGen>
		 * listDetailProdGenerique=new ArrayList<DetailProdGen>(); List<DetailProdRes>
		 * listDetailResponsableProd=new ArrayList<DetailProdRes>();
		 * List<DetailProductionMP> listDetailProductionMP=new
		 * ArrayList<DetailProductionMP>();
		 * 
		 * if(!txtmatricule.getText().equals("")) { matricule=txtmatricule.getText(); }
		 * 
		 * if(comboequipe.getSelectedItem().equals("")) { listDetailProduction
		 * =detailProductionDAO.ListHeursDetailProductionParDepotparJour(
		 * dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule,
		 * depot.getId(),Constantes.ETAT_OF_TERMINER);
		 * listDetailProdGenerique=detailProdGenDAO.
		 * ListHeursDetailProdGenParDepotParJour(dateDebutChooser.getDate(),
		 * dateFinChooser.getDate(),matricule,
		 * depot.getId(),Constantes.ETAT_OF_TERMINER);
		 * listDetailResponsableProd=detailProdResDAO.
		 * ListHeursDetailResponsableProdParDepotParJour(dateDebutChooser.getDate(),
		 * dateFinChooser.getDate(), depot.getId(),matricule);
		 * listDetailProductionMP=detailProductionMPDAO.
		 * ListHeursDetailProductionMPParDepotParJour(dateDebutChooser.getDate(),
		 * dateFinChooser.getDate(),matricule,
		 * depot.getId(),Constantes.ETAT_OF_TERMINER); }else {
		 * 
		 * if(comboequipe.getSelectedItem().equals(Constantes.
		 * TYPE_EQUIPE_CODE_PRPDUCTION)) {
		 * 
		 * listDetailProduction
		 * =detailProductionDAO.ListHeursDetailProductionParDepotparJour(
		 * dateDebutChooser.getDate(), dateFinChooser.getDate(),matricule,
		 * depot.getId(),Constantes.ETAT_OF_TERMINER);
		 * 
		 * }else
		 * if(comboequipe.getSelectedItem().equals(Constantes.TYPE_EQUIPE_CODE_GENERIQUE
		 * )) { listDetailProdGenerique=detailProdGenDAO.
		 * ListHeursDetailProdGenParDepotParJour(dateDebutChooser.getDate(),
		 * dateFinChooser.getDate(),matricule,
		 * depot.getId(),Constantes.ETAT_OF_TERMINER);
		 * 
		 * }else if(comboequipe.getSelectedItem().equals(Constantes.
		 * TYPE_EQUIPE_CODE_RESPONSABLE)) { listDetailResponsableProd=detailProdResDAO.
		 * ListHeursDetailResponsableProdParDepotParJour(dateDebutChooser.getDate(),
		 * dateFinChooser.getDate(), depot.getId(),matricule);
		 * 
		 * }else if(comboequipe.getSelectedItem().equals(Constantes.
		 * TYPE_EQUIPE_CODE_PRPDUCTION_MP)) {
		 * listDetailProductionMP=detailProductionMPDAO.
		 * ListHeursDetailProductionMPParDepotParJour(dateDebutChooser.getDate(),
		 * dateFinChooser.getDate(),matricule,
		 * depot.getId(),Constantes.ETAT_OF_TERMINER);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * /////////////////////////////////////////////////////////////////////////////
		 * //////////////// Detail production
		 * /////////////////////////////////////////////////////////////////////
		 * 
		 * for(int i=0;i<listDetailProduction.size();i++){
		 * 
		 * 
		 * DetailProduction detailProduction =listDetailProduction.get(i);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(!detailProduction.getEmploye().isSalarie()){
		 * 
		 * Boolean Trouve=false;
		 * 
		 * for(int j=0;j<listFicheEmployeAbsentTmp.size();j++) {
		 * 
		 * SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
		 * 
		 * if(detailProduction.getEmploye().getId()==ficheEmploye.getEmploye().getId()
		 * && detailProduction.getProduction().getDate().equals(ficheEmploye.
		 * getDateSituation()) ) { Trouve=true;
		 * 
		 * if(detailProduction.isAbsent()==true ) {
		 * 
		 * if(detailProduction.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * 
		 * ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
		 * if(detailProduction.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailProduction.getMotif().equals("")) {
		 * ficheEmploye.setMotif(detailProduction.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmploye.setAutoriser(detailProduction.isAutorisation());
		 * 
		 * 
		 * 
		 * 
		 * listFicheEmployeAbsentTmp.set(j, ficheEmploye);
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if(Trouve==false){
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(detailProduction.isAbsent()==true ) {
		 * 
		 * if(detailProduction.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * SituationDesEmployeesAbsents ficheEmployeTmp =new
		 * SituationDesEmployeesAbsents();
		 * 
		 * ficheEmployeTmp.setEmploye(detailProduction.getEmploye());
		 * ficheEmployeTmp.setDateSituation(detailProduction.getProduction().getDate());
		 * ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION);
		 * 
		 * if(detailProduction.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailProduction.getMotif().equals("")) {
		 * ficheEmployeTmp.setMotif(detailProduction.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmployeTmp.setAutoriser(detailProduction.isAutorisation());
		 * 
		 * 
		 * listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * /////////////////////////////////////////////////////////////////////////////
		 * ///////////////////////////////// Detail ProductionMP
		 * /////////////////////////////////////////////////////////////////////////////
		 * /////////
		 * 
		 * 
		 * for(int i=0;i<listDetailProductionMP.size();i++){
		 * 
		 * 
		 * 
		 * DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(!detailProductionMP.getEmploye().isSalarie()){
		 * 
		 * Boolean Trouve=false;
		 * 
		 * for(int j=0;j<listFicheEmployeAbsentTmp.size();j++) {
		 * 
		 * SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
		 * 
		 * if(detailProductionMP.getEmploye().getId()==ficheEmploye.getEmploye().getId()
		 * &&
		 * detailProductionMP.getProductionMP().getDateProduction().equals(ficheEmploye.
		 * getDateSituation())) { Trouve=true; if(detailProductionMP.isAbsent()==true )
		 * {
		 * 
		 * if(detailProductionMP.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * 
		 * ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
		 * 
		 * if(detailProductionMP.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailProductionMP.getMotif().equals("")) {
		 * ficheEmploye.setMotif(detailProductionMP.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmploye.setAutoriser(detailProductionMP.isAutorisation());
		 * 
		 * 
		 * listFicheEmployeAbsentTmp.set(j, ficheEmploye); }
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * }
		 * 
		 * if(Trouve==false){
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(detailProductionMP.isAbsent()==true ) {
		 * if(detailProductionMP.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * SituationDesEmployeesAbsents ficheEmploye =new
		 * SituationDesEmployeesAbsents();
		 * ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
		 * ficheEmploye.setDateSituation(detailProductionMP.getProductionMP().
		 * getDateProduction());
		 * ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_PRPDUCTION_MP);
		 * 
		 * if(detailProductionMP.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailProductionMP.getMotif().equals("")) {
		 * ficheEmploye.setMotif(detailProductionMP.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmploye.setAutoriser(detailProductionMP.isAutorisation());
		 * 
		 * listFicheEmployeAbsentTmp.add(ficheEmploye); }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * /////////////////////////////////////////////////////////////////////////////
		 * ///////////////////////////////// DetailProdGen
		 * /////////////////////////////////////////////////////////////////////////////
		 * //
		 * 
		 * 
		 * for(int i=0;i<listDetailProdGenerique.size();i++){
		 * 
		 * DetailProdGen detailProdGen =listDetailProdGenerique.get(i);
		 * 
		 * 
		 * 
		 * if(!detailProdGen.getEmploye().isSalarie()){
		 * 
		 * Boolean Trouve=false;
		 * 
		 * for(int j=0;j<listFicheEmployeAbsentTmp.size();j++) {
		 * 
		 * SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
		 * 
		 * if(detailProdGen.getEmploye().getId()==ficheEmploye.getEmploye().getId() &&
		 * detailProdGen.getProductionGen().getDate().equals(ficheEmploye.
		 * getDateSituation())) { Trouve=true; if(detailProdGen.isAbsent()==true ) {
		 * if(detailProdGen.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
		 * 
		 * if(detailProdGen.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailProdGen.getMotif().equals("")) {
		 * ficheEmploye.setMotif(detailProdGen.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmploye.setAutoriser(detailProdGen.isAutorisation());
		 * 
		 * listFicheEmployeAbsentTmp.set(j, ficheEmploye); }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * if(Trouve==false) {
		 * 
		 * 
		 * 
		 * 
		 * if(detailProdGen.isAbsent()==true ) {
		 * 
		 * if( detailProdGen.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * SituationDesEmployeesAbsents ficheEmployeTmp =new
		 * SituationDesEmployeesAbsents();
		 * ficheEmployeTmp.setEmploye(detailProdGen.getEmploye());;
		 * ficheEmployeTmp.setDateSituation(detailProdGen.getProductionGen().getDate());
		 * ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_GENERIQUE);
		 * if(detailProdGen.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailProdGen.getMotif().equals("")) {
		 * ficheEmployeTmp.setMotif(detailProdGen.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmployeTmp.setAutoriser(detailProdGen.isAutorisation());
		 * listFicheEmployeAbsentTmp.add(ficheEmployeTmp); }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * /////////////////////////////////////////////////////////////////////////
		 * DetailResponsableProd
		 * /////////////////////////////////////////////////////////////////////////////
		 * /////
		 * 
		 * 
		 * for(int i=0;i<listDetailResponsableProd.size();i++){
		 * 
		 * DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
		 * 
		 * 
		 * 
		 * Boolean Trouve=false;
		 * 
		 * for(int j=0;j<listFicheEmployeAbsentTmp.size();j++) {
		 * 
		 * SituationDesEmployeesAbsents ficheEmploye=listFicheEmployeAbsentTmp.get(j);
		 * 
		 * if(detailResponsableProd.getEmploye().getId()==ficheEmploye.getEmploye().
		 * getId() && detailResponsableProd.getDateProduction().equals(ficheEmploye.
		 * getDateSituation())) { Trouve=true;
		 * if((detailResponsableProd.isAbsent()==true ||
		 * detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0 )) {
		 * if( detailResponsableProd.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * ficheEmploye.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
		 * if(detailResponsableProd.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailResponsableProd.getMotif().equals("")) {
		 * ficheEmploye.setMotif(detailResponsableProd.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmploye.setAutoriser(detailResponsableProd.isAutorisation());
		 * 
		 * listFicheEmployeAbsentTmp.set(j, ficheEmploye);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * if(Trouve==false) {
		 * 
		 * if((detailResponsableProd.isAbsent()==true ||
		 * detailResponsableProd.getDelaiEmploye().compareTo(BigDecimal.ZERO)==0 )) {
		 * if(detailResponsableProd.getValider().equals(Constantes.ETAT_INVALIDER)) {
		 * 
		 * SituationDesEmployeesAbsents ficheEmployeTmp=new
		 * SituationDesEmployeesAbsents();
		 * 
		 * ficheEmployeTmp.setEmploye(detailResponsableProd.getEmploye());
		 * ficheEmployeTmp.setDateSituation(detailResponsableProd.getDateProduction());
		 * ficheEmployeTmp.setEquipe(Constantes.TYPE_EQUIPE_CODE_RESPONSABLE);
		 * if(detailResponsableProd.getMotif()!=null) {
		 * 
		 * 
		 * if(!detailResponsableProd.getMotif().equals("")) {
		 * ficheEmployeTmp.setMotif(detailResponsableProd.getMotif());
		 * 
		 * }
		 * 
		 * } ficheEmployeTmp.setAutoriser(detailResponsableProd.isAutorisation());
		 * listFicheEmployeAbsentTmp.add(ficheEmployeTmp);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * } }
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * return listFicheEmployeAbsentTmp;
		 * 
		 */
	


	
	 listEmploye=employeDAO.findByDepot(AuthentificationView.utilisateur.getCodeDepot());
	 Depot depot=mapDepot.get(comboDepot.getSelectedItem());
	String matricule="";
	listFicheEmployeAbsentTmp.clear();
	List<DetailProduction> listDetailProduction=new ArrayList<DetailProduction>();
	List<DetailProdGen> listDetailProdGenerique=new ArrayList<DetailProdGen>();
	List<DetailProdRes> listDetailResponsableProd=new ArrayList<DetailProdRes>();
	List<DetailProductionMP> listDetailProductionMP=new ArrayList<DetailProductionMP>();
	List<EmployeRepos> listEmployeRepos=new ArrayList<EmployeRepos>();
	listEmployeRepos=employeReposDAO.findByBepotByDateGroupByEmployee(dateDebutChooser.getDate(), depot);
	 List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttente = new ArrayList<CoutHorsProdEnAttent>();
	    	
		 listDetailProduction =detailProductionDAO.ListHeursDetailProductionParDepotparJourGroupByEmployer(dateDebutChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER) ;
	     listDetailProdGenerique=detailProdGenDAO.ListHeursDetailProdGenParDepotParJourGroupByEmployee(dateDebutChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER);
		 listDetailResponsableProd=detailProdResDAO.ListHeursDetailResponsableProdParDepotParJourGroupByEmployee(dateDebutChooser.getDate(), depot.getId());
		 listDetailProductionMP=detailProductionMPDAO.ListHeursDetailProductionMPParDepotParJourGroupByEmployee(dateDebutChooser.getDate(), depot.getId(),Constantes.ETAT_OF_TERMINER);
		 listCoutHorsProductionEnAttente=coutHorsProdEnAttentDAO.findBydateByDepotGroupByEmploye(dateDebutChooser.getDate(), depot);
		 
boolean existe=false; 	
for(int d=0;d<listEmploye.size();d++)
{
	
	Employe employe=listEmploye.get(d);
	if(!employe.getTypeResEmploye().getCode().equals(Constantes.TYPE_EMPLOYE_MAIN_OUVRE_MENAGE))
	{
		if(!employe.isSalarie())
		{
			existe=false;
			
			for(int i=0;i<listCoutHorsProductionEnAttente.size();i++)
			{
				if(listCoutHorsProductionEnAttente.get(i).getEmploye().getId()==employe.getId())
				{
					existe=true;
				}
				
			}
			

			for(int i=0;i<listEmployeRepos.size();i++)
			{
			if(listEmployeRepos.get(i).getEmploye().getId()==employe.getId())
			{
				existe=true;
			}

			}
			

			for(int i=0;i<listDetailProduction.size();i++)
			{
			if(listDetailProduction.get(i).getEmploye().getId()==employe.getId())
			{
				existe=true;
			}

			}

			for(int i=0;i<listDetailProdGenerique.size();i++)
			{
			if(listDetailProdGenerique.get(i).getEmploye().getId()==employe.getId())
			{
				existe=true;
			}

			}

			for(int i=0;i<listDetailResponsableProd.size();i++)
			{
			if(listDetailResponsableProd.get(i).getEmploye().getId()==employe.getId())
			{
				existe=true;
			}

			}


			for(int i=0;i<listDetailProductionMP.size();i++)
			{
			if(listDetailProductionMP.get(i).getEmploye().getId()==employe.getId())
			{
				existe=true;
			}

			}


			if(existe==false)
			{

			SituationDesEmployeesAbsents situationDesEmployeesAbsents=new SituationDesEmployeesAbsents();
			situationDesEmployeesAbsents.setEmploye(employe);	
			situationDesEmployeesAbsents.setDateSituation(dateDebutChooser.getDate());
			listFicheEmployeAbsentTmp.add(situationDesEmployeesAbsents);
			}
			
		}
 	
	}
 

}

	
	
	
	
	
	return listFicheEmployeAbsentTmp;
	


}










}
