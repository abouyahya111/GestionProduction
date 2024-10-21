package ProductionCarton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;


import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.CheckableItem;
import util.CheckedComboBox;
import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CoutArticlePFDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.CoutProdMPDAOImpl;
import dao.daoImplManager.DetailCoutArticlePFDAOImpl;
import dao.daoImplManager.DetailProductionMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.FournisseurAdhesiveDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CoutArticlePFDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.CoutProductionMPDAO;
import dao.daoManager.DetailCoutArticlePFDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailProductionMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FournisseurAdhesiveDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CoutArticlePF;
import dao.entity.CoutMP;
import dao.entity.CoutProdMP;
import dao.entity.DetailCoutArticlePF;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.EtatStockMP;
import dao.entity.FactureProduction;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurAdhesive;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JTable;


public class ModifierHeuresEmployeeProductionCarton extends JLayeredPane implements Constantes{

	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleFournisseur;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmploye;
	private DefaultTableModel	 modeleEquipeEm;
	private DefaultTableModel	 modeleEquipeGen;
	private JXTable tableEmploye= new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private ImageIcon imgSupprimer;
	private JButton btnChercherOF;
	private JButton btnTerminerOF;
	private JButton btnRechercher;
	private JTextField txtPrixServiceProd;
	private List<ProductionMP> listProductionMP=new ArrayList<ProductionMP>();
	//private List<DetailProductionMP> listDetailProductionMPTMP=new ArrayList<DetailProductionMP>();
	private List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<Employe> listEmploye=new ArrayList<Employe>();
	private List<FournisseurAdhesive> listFournisseurAdhesive =new ArrayList<FournisseurAdhesive>();
	private List<DetailProductionMP> listDetailProductionMP =new ArrayList<DetailProductionMP>();
	private ProductionMP productionMP = new ProductionMP();
	private Map< String, ProductionMP> mapProductionMP = new HashMap<>();
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapDelaiEmployeEmabalage = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeProd = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeProd = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeEmbalage = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeEmbalage = new HashMap<>();
	
	private Map< String, String> mapQuantiteConsomme = new HashMap<>();
	private Map< String, String> mapQuantiteDechet = new HashMap<>();
	private Map< String, String> mapQuantiteDechetFournisseur = new HashMap<>();
	private Map< String, String> mapQuantiteReste = new HashMap<>();
	private JTextField txtQuantiteRealise;
	private JLabel lblQuantitRalise;
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	 
	 
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	private BigDecimal coutTotalDechet=BigDecimal.ZERO;
	private BigDecimal delaiTotal=BigDecimal.ZERO;
	private BigDecimal delaiTotalEquipeEmbalage;
	
	private DetailProductionMPDAO detailproductionMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockPFDAO;
	private ProductionMPDAO productionMPDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private ParametreDAO parametreDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private  EquipeDAO equipeDAO;
	private FactureProductionDAO factureProductionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private CompteStockMPDAO compteStockMPDAO;
	private boolean validerSaisie=false;
	private String codeDepot;
	private Map< String, String> mapCodeFournisseurMP = new HashMap<>();
	private Map< String, String> mapQuantiteManquante = new HashMap<>();
	private Map< String, String> mapQuantiteManquanteFrPlus = new HashMap<>();
	private FournisseurAdhesiveDAO fournisseurAdhesiveDAO;
	
	 JComboBox txtNumOF = new JComboBox();
	 private JTextField txtcodeemployeproductioncarton;
	 private JTextField txtdelaiproductioncarton;
	 private JTextField txthsupp50productioncarton;
	 private JTextField txthsupp25productioncarton;
		JComboBox comboemployeproductioncarton = new JComboBox();
		 
		private JCheckBox checkEnpanneproductioncarton= new JCheckBox("En Panne");
		JCheckBox checksortieproductioncarton = new JCheckBox("Sortie");
		JCheckBox checkretardproductioncarton = new JCheckBox("Retard");
		private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
		private Map< String, Employe> mapNomEmploye = new HashMap<>();
		private EmployeDAO employeDAO;
		private Map< String, BigDecimal> mapParametre = new HashMap<>();
		private TransferStockMPDAO transferStockMPDAO;
		private DetailTransferMPDAO detailTransfertMPDAO ;
		 private CoutProductionMPDAO coutProductionMPDAO;
		
		
	/////////////////////////////////////////////////////////////////////// Listes des etats Stock Finale MP	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
		
		
		private List <Object[]> listeObjectStockFinaleGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockFinaleGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurReception=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurEntrer=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurSortie=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurCharger=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurRetour=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortie=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurResterMachine=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurFabrique=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurExistante=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
		
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
		private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
		private List <DetailTransferStockMP> listeDetailTransferStockMP=new ArrayList<DetailTransferStockMP>();
		
		private List <Object[]> Mindate=new ArrayList<Object[]>();
		private SubCategorieMPDAO subcategoriempdao;
		int position=-1;
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		  private List<StatistiqueFinanciaireMP> listStatistiqueFinanciereMPTmp=new ArrayList<StatistiqueFinanciaireMP>();
		private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;	
		private ProductionDAO productionDAO;
		private CoutMPDAO coutMPDAO;
		
		JLabel labelTotalEmployeTravail = new JLabel("");
		 private CoutArticlePFDAO coutArticlePFDAO;
		  private DetailCoutArticlePFDAO detailCoutArticlePFDAO;	
		  private List <Object[]> SommeMontantTotalProductionPF=new ArrayList<Object[]>();
		
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public ModifierHeuresEmployeeProductionCarton(final ProductionMP productionMPP,String quantite, String nbreHeure) {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1620, 716);
        
       
        
        try{
        	
        	
        	
        	
        	
        	delaiTotalEquipeEmbalage=BigDecimal.ZERO;
        	delaiTotal=BigDecimal.ZERO;
        	 
        	coutTotalDechet=BigDecimal.ZERO;
        	coutTotalMP=BigDecimal.ZERO;
        	 employeDAO=new EmployeDAOImpl();
        	listCoutProdMP =new ArrayList<CoutProdMP>();
        	listEmploye=new ArrayList<Employe>();
        	listDetailProductionMP =new ArrayList<DetailProductionMP>();
        	listEmploye=employeDAO.findByDepot(AuthentificationView.utilisateur.getCodeDepot());
        	subcategoriempdao=new SubCategorieMPAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	mapDelaiEmploye = new HashMap<>();
        	mapDelaiEmployeEmabalage= new HashMap<>();
        	mapQuantiteConsomme = new HashMap<>();
        	mapQuantiteDechet = new HashMap<>();
        	mapQuantiteReste = new HashMap<>();
        	mapCodeFournisseurMP= new HashMap<>();
        	mapQuantiteManquante= new HashMap<>();
            mapQuantiteManquanteFrPlus=	new HashMap<>();
        	mapHeureSupp25EmployeEmbalage= new HashMap<>();
        	mapHeureSupp50EmployeEmbalage= new HashMap<>();
        	mapHeureSupp25EmployeProd= new HashMap<>();
        	mapHeureSupp50EmployeProd= new HashMap<>();
        	productionMPDAO=new ProductionMPDAOImpl();
        	detailproductionMPDAO=new DetailProductionMPDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	transferStockPFDAO=new TransferStockPFDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	stockPFDAO=new StockPFDAOImpl();
        	parametreDAO=new ParametreDAOImpl();
        	ficheEmployeDAO=new FicheEmployeDAOImpl();
        	compteurResponsableProdDAO=new CompteurResponsableProdDAOImpl();
        	compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	factureProductionDAO=new FactureProductionDAOImpl();
        	fournisseurAdhesiveDAO=new FournisseurAdhesiveDAOImpl();
        	coutProductionMPDAO=new CoutProdMPDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	compteStockMPDAO=new CompteStockMPDAOImpl();
        	txtQuantiteRealise=new JTextField();
        	 util.Utils.copycoller(txtQuantiteRealise);
			 txtPrixServiceProd = new JTextField();
			 util.Utils.copycoller(txtPrixServiceProd);
			 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
			 productionDAO=new ProductionDAOImpl();
			 coutMPDAO=new CoutMPDAOImpl();
			 coutArticlePFDAO=new CoutArticlePFDAOImpl();
			  detailCoutArticlePFDAO=new DetailCoutArticlePFDAOImpl();
			  
			 txtNumOF = new JComboBox();
			 txtNumOF.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		
			  		
			  		if(txtNumOF.getSelectedIndex()!=-1)
			  		{
			  			
			  			
			  			productionMP=productionMPDAO.findByNumOFStatut(txtNumOF.getSelectedItem().toString(), Constantes.ETAT_OF_TERMINER) ;  
			  			if(productionMP!=null)
			  			{
			  				txtNumOF.setToolTipText(productionMP.getStatut());
			  				
			  				if(productionMP.getStatut().equals(Constantes.ETAT_OF_TERMINER))
			  				{
			  					
			  					txtQuantiteRealise.setText(productionMP.getQuantiteReel()+"");	
			  				txtPrixServiceProd.setText(productionMP.getNbreHeure()+"");	
			  					
			  				listDetailProductionMP.clear();  
	  						  
			  			   
			  			  	 
			  			 
			  			  	
			  			  listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId()) ;
			  				
			  			 
			  				afficher_tableEmploye(listDetailProductionMP);	
			  				}
			  					
			  					
			  				
			  				
			  				
			  				
			  			}else
			  			{
			  				txtQuantiteRealise.setText("");	
			  				txtPrixServiceProd.setText("");	
			  			 
			  				listDetailProductionMP.clear();
			  				 
			  				afficher_tableEmploye(listDetailProductionMP);	
			  			}
			  			
			  		}
			  		
			  		
			  		
			  	
			 		
			 		
			 		
			 	
			 		
			 		
			 		
			 	}
			 });
			 txtNumOF.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseEntered(MouseEvent e) {}
			 });
			    txtNumOF.setBounds(81, 8, 249, 24);
			
			    add(txtNumOF);
			  AutoCompleteDecorator.decorate(txtNumOF); 
			
			 listProductionMP=productionMPDAO.listeProductionMPEtatCreer(Constantes.ETAT_OF_TERMINER,Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());
			 txtNumOF.addItem("");
		
			 for(int i=0;i<listProductionMP.size();i++)
			 {
				 
			ProductionMP productionMP= listProductionMP.get(i);
				 
				   txtNumOF.addItem(productionMP.getNumOFMP());
					mapProductionMP.put(productionMP.getNumOFMP(), productionMP);
			 }
			 txtNumOF.setSelectedIndex(-1);
			 
			 if(productionMPP!=null)
			 {
				  	if(productionMPP.getNumOFMP()!=null)
		        	{
		        		
		        		productionMP=productionMPP;
		        		txtNumOF.setSelectedItem(productionMP.getNumOFMP());
		        		txtQuantiteRealise.setText(quantite);
		        		txtPrixServiceProd.setText(nbreHeure);
		        		
		        		
		        		 
		        		
		        		
		        	}
		        	else {	
		        		productionMP = new ProductionMP();
		        	} 
			 }
      
        	
        	listFournisseurAdhesive=fournisseurAdhesiveDAO.findAll();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
        mapParametre=Utils.listeParametre();
        validerSaisie=false;
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();
          }
        codeDepot= AuthentificationView.utilisateur.getCodeDepot();
				  		
				  		  		intialiserTableMP();
				  		  		initialiserTableauEmploye();
				  		  	
				  		  btnChercherOF = new JButton("Chercher OF");
				  		  btnChercherOF.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnChercherOF.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		
				  		  		
				  		productionMP=mapProductionMP.get(txtNumOF.getSelectedItem().toString());
				  		
				  		
				  				if(productionMP!=null){
				  				    
				  			  		 if(txtQuantiteRealise.getText().equals("")){
				  			  			JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					  }	else {
				  						  
				  						listDetailProductionMP.clear();  
				  						  
				  			 
				  			//	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  			//	String dateProduction=dateFormat.format(productionMP.getDateProduction());
				  				
				  				//List<DetailProductionMP> listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());
				  			  	
				  			  listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());
				  				
				  			 
				  				afficher_tableEmploye(listDetailProductionMP);
				  			
				  				
				  					  }
				  				}else{
				  				  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					
				  				}
				  		
				  		  	}
				  		  });
				  		  
				  		  
				  	
				  		btnChercherOF.setIcon(new ImageIcon(CreerOrdreFabricationMP.class.getResource("/img/chercher.png")));
				  		 btnChercherOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnChercherOF.setBounds(1028, 9, 120, 23);
				  		 add(btnChercherOF);
				  		    
				  		    btnTerminerOF = new JButton("Valider");
				  		    btnTerminerOF.setBounds(502, 488, 112, 24);
				  		    add(btnTerminerOF);
				  		    btnTerminerOF.setIcon(imgAjouter);
				  		    btnTerminerOF.addActionListener(new ActionListener() {
		  		     	public void actionPerformed(ActionEvent e) {
			  		     	
				  		     	  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Terminer cet Ordre de Fabrication?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										{
				  		     		if(txtQuantiteRealise.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}else if(txtPrixServiceProd.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir le Prix Unitaire :Service Production!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     	
			  		     		}
				  		     		else {
				  		     		if(productionMP.getStatut().equals(Constantes.ETAT_OF_TERMINER)) {
				  		     			
				  		     						  		     				boolean erreurHeureEmployee=false;
				  		     						  		     				for(int t=0;t<listDetailProductionMP.size();t++)
				  		     						  		     				{
				  		     						  		     					
				  		     						  		     					 
				  		     						  		     					if(listDetailProductionMP.get(t).getDelaiEmploye().compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
				  		     						  		     					{
				  		     						  		     						
				  		     						  		     						erreurHeureEmployee=true;	
				  		     						  		     						
				  		     						  		     						
				  		     						  		     						
				  		     						  		     					}
				  		     						  		     					
				  		     						  		     					
				  		     						  		     				}
				  		     				
				  		     						  		     			if(erreurHeureEmployee==true)
				  		     					  		     				{
				  		     					  		     					JOptionPane.showMessageDialog(null, "Les Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     						  		     				return;
				  		     					  		     				}
				  		     				
				  		     				
				  		     				
			  		     			
			  		     		 
			  		     			productionMP.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		
				  		     		 /*délai des employés Production*/
				  		     		
				  		     		for(int j=0;j<tableEmploye.getRowCount();j++){
			  		     			
				  		     			if(!tableEmploye.getValueAt(j, 4).toString().equals("")){
				  		     			mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 1).toString()), tableEmploye.getValueAt(j, 4).toString());
				  		     			delaiTotal=delaiTotal.add(new BigDecimal(tableEmploye.getValueAt(j, 4).toString())) ;
				  		     			}else 
			  		     				mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 1).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 5).toString().equals("")){
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 1).toString()), tableEmploye.getValueAt(j, 5).toString());
					  		     			}else 
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 1).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 6).toString().equals("")){
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 1).toString()), tableEmploye.getValueAt(j, 6).toString());
				  		     			}else 
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 1).toString()), String.valueOf(0));
			  		     		}
			  		     		
				  		     		calculeCoutEmploye(listDetailProductionMP, mapDelaiEmploye);
 
				  		     		 
				  		     		
				  		     		productionMP.setCoutTotalEmploye(coutTotalEmploye);
				  		     		productionMP.setCoutTotal(productionMP.getCoutTotalMP().add(productionMP.getCoutTotalEmploye().add(productionMP.getCoutDechet())));
 
				  		     		productionMP.setStatut(Constantes.ETAT_OF_TERMINER);
				  		     		productionMPDAO.edit(productionMP);
				  		     		calculerStockCoutProduitFini();
				  		     		
				  		     		
	 MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());			  		     		
				  		     		
	  String req="";
	  
	  req=req+" where d.matierePremier.id='"+matierePremier.getId()+"' and (d.magasinSouce.id='"+productionMP.getMagasinStockage().getId() +"' or d.magasinDestination.id='"+productionMP.getMagasinStockage().getId()+"') order by d.transferStockMP.id,d.transferStockMP.dateTransfer";
				  		     		
	listeDetailTransferStockMP=detailTransfertMPDAO.findDetailTransferMPByRequete(req)	;  		     		 
		BigDecimal prixmoyenne=BigDecimal.ZERO;		
		BigDecimal stockFinale=BigDecimal.ZERO;
		BigDecimal DifferenceCoutMP=BigDecimal.ZERO;
		
		
		boolean fabriquerExiste=false;
	for(int d=0;d<listeDetailTransferStockMP.size();d++)
	{
		
	DetailTransferStockMP detailTransferStockMP=listeDetailTransferStockMP.get(d);	
		
	if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL)	)
			{
		prixmoyenne=detailTransferStockMP.getPrixUnitaire();
			}
	
	 if(detailTransferStockMP.getTransferStockMP().getDateTransfer().compareTo(productionMP.getDateProduction()) <0)
	 {
		if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_FABRIQUE)) 
		{
		 Date datetransfert= util.DateUtils.ajoutNbJours(detailTransferStockMP.getTransferStockMP().getDateTransfer(), -1) ;
			CalculerStockFinaleMPByDate(datetransfert);			
			for(int j=0;j<listEtatStockMPAfficher.size();j++)
			{
				
				EtatStockMP etatStockMP=listEtatStockMPAfficher.get(j);
				if(etatStockMP.getMp().getId()==matierePremier.getId())
				{					 
					
					stockFinale=etatStockMP.getQuantiteFinale();
					
				}
 				
			}	

			prixmoyenne=(((stockFinale.multiply(prixmoyenne)).add(detailTransferStockMP.getQuantite().multiply(detailTransferStockMP.getPrixUnitaire()))).divide(stockFinale.add(detailTransferStockMP.getQuantite()), 12, RoundingMode.HALF_UP));			
			
 			
		}
 		 
	 }else if(detailTransferStockMP.getTransferStockMP().getDateTransfer().compareTo(productionMP.getDateProduction()) == 0)
	 {
		 
		 fabriquerExiste=false;
		 
			if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_FABRIQUE)) 
			{
				fabriquerExiste=true;
			 Date datetransfert= util.DateUtils.ajoutNbJours(detailTransferStockMP.getTransferStockMP().getDateTransfer(), -1) ;
				CalculerStockFinaleMPByDate(datetransfert);			
				for(int j=0;j<listEtatStockMPAfficher.size();j++)
				{
					
					EtatStockMP etatStockMP=listEtatStockMPAfficher.get(j);
					if(etatStockMP.getMp().getId()==matierePremier.getId())
					{					 
						
						stockFinale=etatStockMP.getQuantiteFinale();
						
					}
	 				
				}
				
				
				
				ProductionMP productionMP=productionMPDAO.findByNumOFStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_OF_TERMINER);
				detailTransferStockMP.setQuantite(productionMP.getQuantiteReel());
				detailTransferStockMP.setPrixUnitaire(productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 12, RoundingMode.HALF_UP));
				prixmoyenne=(((stockFinale.multiply(prixmoyenne)).add(productionMP.getCoutTotal())).divide(stockFinale.add(productionMP.getQuantiteReel()), 12, RoundingMode.HALF_UP));			
				
	 			
			} 
			
			if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_CHARGE)) 
			{
				if(fabriquerExiste==true)
				{
					
					Production productionTmp=productionDAO.findByNumOFStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_OF_TERMINER);
					if(productionTmp!=null)
					{
						BigDecimal coutTotalMP=BigDecimal.ZERO;
						BigDecimal coutTotalDechet=BigDecimal.ZERO;
						
						
						listCoutMP=productionTmp.getListCoutMP();	
						for(int j=0;j<listCoutMP.size();j++)
						{
							CoutMP coutMP=listCoutMP.get(j);
							if(coutMP.getMatierePremier().getId()==matierePremier.getId())
							{
								coutMP.setPrixUnitaire(prixmoyenne);
								coutMP.setPrixTotal(coutMP.getQuantConsomme().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutDechet(coutMP.getQuantDechet().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutDechetFournisseur(coutMP.getQuantDechetFournisseur().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutManquante(coutMP.getQuantiteManquante().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutOffre(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));
								coutMPDAO.edit(coutMP);
								coutTotalMP=coutTotalMP.add(coutMP.getPrixTotal());
								
								coutTotalDechet=coutTotalDechet.add(coutMP.getCoutDechet().add(coutMP.getCoutDechetFournisseur().add(coutMP.getCoutManquante().add(coutMP.getCoutOffre()))));
								
								
								
							}else
							{
								
                                coutTotalMP=coutTotalMP.add(coutMP.getPrixTotal());
								
								coutTotalDechet=coutTotalDechet.add(coutMP.getCoutDechet().add(coutMP.getCoutDechetFournisseur().add(coutMP.getCoutManquante().add(coutMP.getCoutOffre()))));
							
								
							}
							
							
							
						}
						
						
						productionTmp.setCoutTotalMP(coutTotalMP);	
						productionTmp.setCoutDechet(coutTotalDechet);
						productionTmp.setCoutTotal(productionTmp.getCoutDechet().add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalEmployeEmbalage().add(productionTmp.getCoutTotalEmployeGen().add(productionTmp.getCoutTotalHorsProductionEnAttent()))))));
						productionDAO.edit(productionTmp);
						
						detailTransferStockMP.setPrixUnitaire(prixmoyenne);
						detailTransfertMPDAO.edit(detailTransferStockMP);
						
						
						CoutArticlePF coutArticlePF=coutArticlePFDAO.CoutArticlePFByArticle(productionTmp.getArticles());
						
						if(coutArticlePF!=null)
						{
							
								
							
							DetailCoutArticlePF detailCoutArticlePF=detailCoutArticlePFDAO.DetailCoutArticlePFParCodeTransfert(productionTmp.getNumOF());
							if(detailCoutArticlePF!=null)
							{
								
								DifferenceCoutMP=DifferenceCoutMP.add(detailCoutArticlePF.getCoutMP().subtract(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet())));
								
								coutArticlePF.setCoutMP(coutArticlePF.getCoutMP().subtract(detailCoutArticlePF.getCoutMP()).add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet())));
 								coutArticlePF.setTotal(coutArticlePF.getCoutEquipeEmballage().add(coutArticlePF.getCoutEquipeGenerique().add(coutArticlePF.getCoutEquipeProduction().add(coutArticlePF.getCoutMP()))));
								coutArticlePF.setCout(coutArticlePF.getTotal().divide(coutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								coutArticlePFDAO.edit(coutArticlePF);
								 
								detailCoutArticlePF.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));;							 
								detailCoutArticlePF.setTotal(detailCoutArticlePF.getCoutEquipeEmballage().add(detailCoutArticlePF.getCoutEquipeGenerique().add(detailCoutArticlePF.getCoutEquipeProduction().add(detailCoutArticlePF.getCoutMP()))));
								detailCoutArticlePF.setCout(detailCoutArticlePF.getTotal().divide(detailCoutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								detailCoutArticlePF.setCoutArticlePF(coutArticlePF);
								detailCoutArticlePFDAO.edit(detailCoutArticlePF);	
								
								
								
								
							}else
							{
								
								DifferenceCoutMP=DifferenceCoutMP.add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
								coutArticlePF.setCoutEquipeGenerique(coutArticlePF.getCoutEquipeGenerique().add(productionTmp.getCoutTotalEmployeGen()));
								coutArticlePF.setCoutEquipeEmballage(coutArticlePF.getCoutEquipeEmballage().add(productionTmp.getCoutTotalEmployeEmbalage()));
								coutArticlePF.setCoutEquipeProduction(coutArticlePF.getCoutEquipeProduction().add( productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent())));
								coutArticlePF.setCoutMP(coutArticlePF.getCoutMP().add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet())) );
								coutArticlePF.setQuantiteRealiser(coutArticlePF.getQuantiteRealiser().add(productionTmp.getQuantiteReel()) );
								coutArticlePF.setTotal(coutArticlePF.getCoutEquipeEmballage().add(coutArticlePF.getCoutEquipeGenerique().add(coutArticlePF.getCoutEquipeProduction().add(coutArticlePF.getCoutMP()))));
								coutArticlePF.setCout(coutArticlePF.getTotal().divide(coutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								coutArticlePFDAO.add(coutArticlePF);
																								
								DetailCoutArticlePF detailCoutArticlePFTmp=new DetailCoutArticlePF();
								detailCoutArticlePFTmp.setArticles(productionTmp.getArticles());
								detailCoutArticlePFTmp.setCoutEquipeGenerique(productionTmp.getCoutTotalEmployeGen());
								detailCoutArticlePFTmp.setCoutEquipeEmballage(productionTmp.getCoutTotalEmployeEmbalage());
								detailCoutArticlePFTmp.setCoutEquipeProduction(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent()));
								detailCoutArticlePFTmp.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
								detailCoutArticlePFTmp.setQuantiteRealiser(productionTmp.getQuantiteReel());
								detailCoutArticlePFTmp.setTotal(detailCoutArticlePFTmp.getCoutEquipeEmballage().add(detailCoutArticlePFTmp.getCoutEquipeGenerique().add(detailCoutArticlePFTmp.getCoutEquipeProduction().add(detailCoutArticlePFTmp.getCoutMP()))));
								detailCoutArticlePFTmp.setCout(detailCoutArticlePFTmp.getTotal().divide(detailCoutArticlePFTmp.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								detailCoutArticlePFTmp.setCoutArticlePF(coutArticlePF);
								detailCoutArticlePFTmp.setDate(productionTmp.getDate());	
								detailCoutArticlePFTmp.setCodeTransfer(productionTmp.getNumOF());
								detailCoutArticlePFDAO.add(detailCoutArticlePFTmp);	
								
								
							}
							
							
							
							
						}else
						{
							DifferenceCoutMP=DifferenceCoutMP.add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
							CoutArticlePF coutArticlePFTmp=new CoutArticlePF();	
							coutArticlePFTmp.setArticles(productionTmp.getArticles());
							coutArticlePFTmp.setCoutEquipeGenerique(productionTmp.getCoutTotalEmployeGen());
							coutArticlePFTmp.setCoutEquipeEmballage(productionTmp.getCoutTotalEmployeEmbalage());
							coutArticlePFTmp.setCoutEquipeProduction(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent()));
							coutArticlePFTmp.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
							coutArticlePFTmp.setQuantiteRealiser(productionTmp.getQuantiteReel());
							coutArticlePFTmp.setTotal(coutArticlePFTmp.getCoutEquipeEmballage().add(coutArticlePFTmp.getCoutEquipeGenerique().add(coutArticlePFTmp.getCoutEquipeProduction().add(coutArticlePFTmp.getCoutMP()))));
							coutArticlePFTmp.setCout(coutArticlePFTmp.getTotal().divide(coutArticlePFTmp.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
							coutArticlePFDAO.add(coutArticlePFTmp);
						
						DetailCoutArticlePF detailCoutArticlePF=new DetailCoutArticlePF();
						detailCoutArticlePF.setArticles(productionTmp.getArticles());
						detailCoutArticlePF.setCoutEquipeGenerique(productionTmp.getCoutTotalEmployeGen());
						detailCoutArticlePF.setCoutEquipeEmballage(productionTmp.getCoutTotalEmployeEmbalage());
						detailCoutArticlePF.setCoutEquipeProduction(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent()));
						detailCoutArticlePF.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
						detailCoutArticlePF.setQuantiteRealiser(productionTmp.getQuantiteReel());
						detailCoutArticlePF.setTotal(detailCoutArticlePF.getCoutEquipeEmballage().add(detailCoutArticlePF.getCoutEquipeGenerique().add(detailCoutArticlePF.getCoutEquipeProduction().add(detailCoutArticlePF.getCoutMP()))));
						detailCoutArticlePF.setCout(detailCoutArticlePF.getTotal().divide(detailCoutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
						detailCoutArticlePF.setCoutArticlePF(coutArticlePFTmp);
						detailCoutArticlePF.setDate(productionTmp.getDate());	
						detailCoutArticlePF.setCodeTransfer(productionTmp.getNumOF());
						detailCoutArticlePFDAO.add(detailCoutArticlePF);
						
						}
						
						
						
						
					}else
					{
						BigDecimal coutTotalMP=BigDecimal.ZERO;
						BigDecimal coutTotalDechet=BigDecimal.ZERO;
						
						ProductionMP productionMP=productionMPDAO.findByNumOFStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_OF_TERMINER);
						
						if(productionMP!=null)
						{
							listCoutProdMP=productionMP.getListCoutProdMP();
							for(int y=0;y<listCoutProdMP.size();y++)
							{
								
								CoutProdMP coutProdMP=listCoutProdMP.get(y);
								
								if(coutProdMP.getMatierePremier().getId()==matierePremier.getId())
								{
									
									coutProdMP.setPrixUnitaire(prixmoyenne);
									coutProdMP.setPrixTotal(coutProdMP.getQuantConsomme().multiply(coutProdMP.getPrixUnitaire()));
									coutProdMP.setCoutDechet(coutProdMP.getQuantDechet().multiply(coutProdMP.getPrixUnitaire()));
									coutProdMP.setCoutDechetFournisseur(coutProdMP.getQuantDechetFournisseur().multiply(coutProdMP.getPrixUnitaire()));
									coutTotalMP=coutTotalMP.add(coutProdMP.getPrixTotal());
									coutTotalDechet=coutTotalDechet.add(coutProdMP.getCoutDechet().add(coutProdMP.getCoutDechetFournisseur()));
									coutProductionMPDAO.edit(coutProdMP);
									
								}else
								{
									coutTotalMP=coutTotalMP.add(coutProdMP.getPrixTotal());
									coutTotalDechet=coutTotalDechet.add(coutProdMP.getCoutDechet().add(coutProdMP.getCoutDechetFournisseur()));
									
								}
								
							}
							
							DifferenceCoutMP=DifferenceCoutMP.add((productionMP.getCoutDechet().add(productionMP.getCoutTotalMP())).subtract(coutTotalMP.add(coutTotalDechet)));
							
							productionMP.setCoutTotalMP(coutTotalMP);
							productionMP.setCoutDechet(coutTotalDechet);
							productionMP.setCoutTotal(productionMP.getCoutDechet().add(productionMP.getCoutTotalEmploye().add(productionMP.getCoutTotalMP())));
							productionMPDAO.edit(productionMP);
							
							detailTransferStockMP.setPrixUnitaire(prixmoyenne);
							detailTransfertMPDAO.edit(detailTransferStockMP);	
							
							
						}
						
						
						
					}
					
					
					
				}
				
				
			}
			
			
		 
		 
		 
	 }else
	 {
		 

		 
		 
		 
			if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_FABRIQUE)) 
			{
				 
			 Date datetransfert= util.DateUtils.ajoutNbJours(detailTransferStockMP.getTransferStockMP().getDateTransfer(), -1) ;
				CalculerStockFinaleMPByDate(datetransfert);			
				for(int j=0;j<listEtatStockMPAfficher.size();j++)
				{
					
					EtatStockMP etatStockMP=listEtatStockMPAfficher.get(j);
					if(etatStockMP.getMp().getId()==matierePremier.getId())
					{					 
						
						stockFinale=etatStockMP.getQuantiteFinale();
						
					}
	 				
				}
				
				
				
				ProductionMP productionMP=productionMPDAO.findByNumOFStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_OF_TERMINER);
				detailTransferStockMP.setQuantite(productionMP.getQuantiteReel());
				detailTransferStockMP.setPrixUnitaire(productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 12, RoundingMode.HALF_UP));
				prixmoyenne=(((stockFinale.multiply(prixmoyenne)).add(productionMP.getCoutTotal())).divide(stockFinale.add(productionMP.getQuantiteReel()), 12, RoundingMode.HALF_UP));			
				
	 			
			} 
			
			if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_CHARGE)) 
			{
				
				
 					Production productionTmp=productionDAO.findByNumOFStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_OF_TERMINER);
					if(productionTmp!=null)
					{
						BigDecimal coutTotalMP=BigDecimal.ZERO;
						BigDecimal coutTotalDechet=BigDecimal.ZERO;
						
						
						listCoutMP=productionTmp.getListCoutMP();	
						for(int j=0;j<listCoutMP.size();j++)
						{
							CoutMP coutMP=listCoutMP.get(j);
							if(coutMP.getMatierePremier().getId()==matierePremier.getId())
							{
								coutMP.setPrixUnitaire(prixmoyenne);
								coutMP.setPrixTotal(coutMP.getQuantConsomme().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutDechet(coutMP.getQuantDechet().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutDechetFournisseur(coutMP.getQuantDechetFournisseur().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutManquante(coutMP.getQuantiteManquante().multiply(coutMP.getPrixUnitaire()));
								coutMP.setCoutOffre(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));
								coutMPDAO.edit(coutMP);
								coutTotalMP=coutTotalMP.add(coutMP.getPrixTotal());
								
								coutTotalDechet=coutTotalDechet.add(coutMP.getCoutDechet().add(coutMP.getCoutDechetFournisseur().add(coutMP.getCoutManquante().add(coutMP.getCoutOffre()))));
								
								
								
							}else
							{
								
                                coutTotalMP=coutTotalMP.add(coutMP.getPrixTotal());
								
								coutTotalDechet=coutTotalDechet.add(coutMP.getCoutDechet().add(coutMP.getCoutDechetFournisseur().add(coutMP.getCoutManquante().add(coutMP.getCoutOffre()))));
							
								
							}
							
							
							
						}
						
						
						productionTmp.setCoutTotalMP(coutTotalMP);	
						productionTmp.setCoutDechet(coutTotalDechet);
						productionTmp.setCoutTotal(productionTmp.getCoutDechet().add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalEmployeEmbalage().add(productionTmp.getCoutTotalEmployeGen().add(productionTmp.getCoutTotalHorsProductionEnAttent()))))));
						productionDAO.edit(productionTmp);
						
						detailTransferStockMP.setPrixUnitaire(prixmoyenne);
						detailTransfertMPDAO.edit(detailTransferStockMP);
						
						
						CoutArticlePF coutArticlePF=coutArticlePFDAO.CoutArticlePFByArticle(productionTmp.getArticles());
						
						if(coutArticlePF!=null)
						{
							
								
							
							DetailCoutArticlePF detailCoutArticlePF=detailCoutArticlePFDAO.DetailCoutArticlePFParCodeTransfert(productionTmp.getNumOF());
							if(detailCoutArticlePF!=null)
							{
								
								DifferenceCoutMP=DifferenceCoutMP.add(detailCoutArticlePF.getCoutMP().subtract(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet())));
								
								coutArticlePF.setCoutMP(coutArticlePF.getCoutMP().subtract(detailCoutArticlePF.getCoutMP()).add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet())));
 								coutArticlePF.setTotal(coutArticlePF.getCoutEquipeEmballage().add(coutArticlePF.getCoutEquipeGenerique().add(coutArticlePF.getCoutEquipeProduction().add(coutArticlePF.getCoutMP()))));
								coutArticlePF.setCout(coutArticlePF.getTotal().divide(coutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								coutArticlePFDAO.edit(coutArticlePF);
								 
								detailCoutArticlePF.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));;							 
								detailCoutArticlePF.setTotal(detailCoutArticlePF.getCoutEquipeEmballage().add(detailCoutArticlePF.getCoutEquipeGenerique().add(detailCoutArticlePF.getCoutEquipeProduction().add(detailCoutArticlePF.getCoutMP()))));
								detailCoutArticlePF.setCout(detailCoutArticlePF.getTotal().divide(detailCoutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								detailCoutArticlePF.setCoutArticlePF(coutArticlePF);
								detailCoutArticlePFDAO.edit(detailCoutArticlePF);	
								
								
								
								
							}else
							{
								
								DifferenceCoutMP=DifferenceCoutMP.add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
								coutArticlePF.setCoutEquipeGenerique(coutArticlePF.getCoutEquipeGenerique().add(productionTmp.getCoutTotalEmployeGen()));
								coutArticlePF.setCoutEquipeEmballage(coutArticlePF.getCoutEquipeEmballage().add(productionTmp.getCoutTotalEmployeEmbalage()));
								coutArticlePF.setCoutEquipeProduction(coutArticlePF.getCoutEquipeProduction().add( productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent())));
								coutArticlePF.setCoutMP(coutArticlePF.getCoutMP().add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet())) );
								coutArticlePF.setQuantiteRealiser(coutArticlePF.getQuantiteRealiser().add(productionTmp.getQuantiteReel()) );
								coutArticlePF.setTotal(coutArticlePF.getCoutEquipeEmballage().add(coutArticlePF.getCoutEquipeGenerique().add(coutArticlePF.getCoutEquipeProduction().add(coutArticlePF.getCoutMP()))));
								coutArticlePF.setCout(coutArticlePF.getTotal().divide(coutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								coutArticlePFDAO.add(coutArticlePF);
																								
								DetailCoutArticlePF detailCoutArticlePFTmp=new DetailCoutArticlePF();
								detailCoutArticlePFTmp.setArticles(productionTmp.getArticles());
								detailCoutArticlePFTmp.setCoutEquipeGenerique(productionTmp.getCoutTotalEmployeGen());
								detailCoutArticlePFTmp.setCoutEquipeEmballage(productionTmp.getCoutTotalEmployeEmbalage());
								detailCoutArticlePFTmp.setCoutEquipeProduction(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent()));
								detailCoutArticlePFTmp.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
								detailCoutArticlePFTmp.setQuantiteRealiser(productionTmp.getQuantiteReel());
								detailCoutArticlePFTmp.setTotal(detailCoutArticlePFTmp.getCoutEquipeEmballage().add(detailCoutArticlePFTmp.getCoutEquipeGenerique().add(detailCoutArticlePFTmp.getCoutEquipeProduction().add(detailCoutArticlePFTmp.getCoutMP()))));
								detailCoutArticlePFTmp.setCout(detailCoutArticlePFTmp.getTotal().divide(detailCoutArticlePFTmp.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
								detailCoutArticlePFTmp.setCoutArticlePF(coutArticlePF);
								detailCoutArticlePFTmp.setDate(productionTmp.getDate());	
								detailCoutArticlePFTmp.setCodeTransfer(productionTmp.getNumOF());
								detailCoutArticlePFDAO.add(detailCoutArticlePFTmp);	
								
								
							}
							
							
							
							
						}else
						{
							DifferenceCoutMP=DifferenceCoutMP.add(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
							CoutArticlePF coutArticlePFTmp=new CoutArticlePF();	
							coutArticlePFTmp.setArticles(productionTmp.getArticles());
							coutArticlePFTmp.setCoutEquipeGenerique(productionTmp.getCoutTotalEmployeGen());
							coutArticlePFTmp.setCoutEquipeEmballage(productionTmp.getCoutTotalEmployeEmbalage());
							coutArticlePFTmp.setCoutEquipeProduction(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent()));
							coutArticlePFTmp.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
							coutArticlePFTmp.setQuantiteRealiser(productionTmp.getQuantiteReel());
							coutArticlePFTmp.setTotal(coutArticlePFTmp.getCoutEquipeEmballage().add(coutArticlePFTmp.getCoutEquipeGenerique().add(coutArticlePFTmp.getCoutEquipeProduction().add(coutArticlePFTmp.getCoutMP()))));
							coutArticlePFTmp.setCout(coutArticlePFTmp.getTotal().divide(coutArticlePFTmp.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
							coutArticlePFDAO.add(coutArticlePFTmp);
						
						DetailCoutArticlePF detailCoutArticlePF=new DetailCoutArticlePF();
						detailCoutArticlePF.setArticles(productionTmp.getArticles());
						detailCoutArticlePF.setCoutEquipeGenerique(productionTmp.getCoutTotalEmployeGen());
						detailCoutArticlePF.setCoutEquipeEmballage(productionTmp.getCoutTotalEmployeEmbalage());
						detailCoutArticlePF.setCoutEquipeProduction(productionTmp.getCoutTotalEmploye().add(productionTmp.getCoutTotalHorsProductionEnAttent()));
						detailCoutArticlePF.setCoutMP(productionTmp.getCoutTotalMP().add(productionTmp.getCoutDechet()));
						detailCoutArticlePF.setQuantiteRealiser(productionTmp.getQuantiteReel());
						detailCoutArticlePF.setTotal(detailCoutArticlePF.getCoutEquipeEmballage().add(detailCoutArticlePF.getCoutEquipeGenerique().add(detailCoutArticlePF.getCoutEquipeProduction().add(detailCoutArticlePF.getCoutMP()))));
						detailCoutArticlePF.setCout(detailCoutArticlePF.getTotal().divide(detailCoutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
						detailCoutArticlePF.setCoutArticlePF(coutArticlePFTmp);
						detailCoutArticlePF.setDate(productionTmp.getDate());	
						detailCoutArticlePF.setCodeTransfer(productionTmp.getNumOF());
						detailCoutArticlePFDAO.add(detailCoutArticlePF);
						
						}
						
						
						
						
					}else
					{
						BigDecimal coutTotalMP=BigDecimal.ZERO;
						BigDecimal coutTotalDechet=BigDecimal.ZERO;
						
						ProductionMP productionMP=productionMPDAO.findByNumOFStatut(detailTransferStockMP.getTransferStockMP().getCodeTransfer(), Constantes.ETAT_OF_TERMINER);
						
						if(productionMP!=null)
						{
							listCoutProdMP=productionMP.getListCoutProdMP();
							for(int y=0;y<listCoutProdMP.size();y++)
							{
								
								CoutProdMP coutProdMP=listCoutProdMP.get(y);
								
								if(coutProdMP.getMatierePremier().getId()==matierePremier.getId())
								{
									
									coutProdMP.setPrixUnitaire(prixmoyenne);
									coutProdMP.setPrixTotal(coutProdMP.getQuantConsomme().multiply(coutProdMP.getPrixUnitaire()));
									coutProdMP.setCoutDechet(coutProdMP.getQuantDechet().multiply(coutProdMP.getPrixUnitaire()));
									coutProdMP.setCoutDechetFournisseur(coutProdMP.getQuantDechetFournisseur().multiply(coutProdMP.getPrixUnitaire()));
									coutTotalMP=coutTotalMP.add(coutProdMP.getPrixTotal());
									coutTotalDechet=coutTotalDechet.add(coutProdMP.getCoutDechet().add(coutProdMP.getCoutDechetFournisseur()));
									coutProductionMPDAO.edit(coutProdMP);
									
								}else
								{
									coutTotalMP=coutTotalMP.add(coutProdMP.getPrixTotal());
									coutTotalDechet=coutTotalDechet.add(coutProdMP.getCoutDechet().add(coutProdMP.getCoutDechetFournisseur()));
									
								}
								
							}
							
							DifferenceCoutMP=DifferenceCoutMP.add((productionMP.getCoutDechet().add(productionMP.getCoutTotalMP())).subtract(coutTotalMP.add(coutTotalDechet)));
							
							productionMP.setCoutTotalMP(coutTotalMP);
							productionMP.setCoutDechet(coutTotalDechet);
							productionMP.setCoutTotal(productionMP.getCoutDechet().add(productionMP.getCoutTotalEmploye().add(productionMP.getCoutTotalMP())));
							productionMPDAO.edit(productionMP);
							
							detailTransferStockMP.setPrixUnitaire(prixmoyenne);
							detailTransfertMPDAO.edit(detailTransferStockMP);	
							
							
						}
						
						
						
					}
					
					
				 
				
				
			}
			
			
 	 }
	
	
		
		
	}
				  		     		
		if(prixmoyenne.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0)	
		{
			
			  matierePremier.setPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()) , prixmoyenne); 
		      matierePremiereDAO.edit(matierePremier);
		
		
		}
		
		
		listStatistiqueFinanciereMPTmp=statistiqueFinanciereMPDAO.listeStatistiqueFinanciereMPLimitByCodeTransfertByEtaTransfert(productionMP.getNumOFMP(), Constantes.ETAT_OF_TERMINER);	
		 BigDecimal differenceCoutEmployee=BigDecimal.ZERO;
		 if(listStatistiqueFinanciereMPTmp.size()!=0)
		 {
			 
			 differenceCoutEmployee= listStatistiqueFinanciereMPTmp.get(listStatistiqueFinanciereMPTmp.size()-1).getCoutEmployeeCarton().subtract(listStatistiqueFinanciereMPTmp.get(listStatistiqueFinanciereMPTmp.size()-2).getCoutEmployeeCarton());
			 
			 
			 
		 }
 
		 BigDecimal MontantTotalPF=BigDecimal.ZERO;
			
		  	SommeMontantTotalProductionPF = coutArticlePFDAO.SommeTotalMontantArticlePF() ;
			 
			for(int d=0;d<SommeMontantTotalProductionPF.size();d++)
			{
				Object[] object=SommeMontantTotalProductionPF.get(d);
				

				if((BigDecimal)object[1]!=null)
				{
					MontantTotalPF=MontantTotalPF.add((BigDecimal)object[1]) ;

				}
			}
		 
		 
        listeStatistiqueFinanciereMP=statistiqueFinanciereMPDAO.findAll();
	    StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listeStatistiqueFinanciereMP.get(listeStatistiqueFinanciereMP.size()-1);
				  		      
				  		     		
				  		     		
				  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
			  		     			
			  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
			  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage().subtract(differenceCoutEmployee).add(coutTotalEmploye).add(DifferenceCoutMP));
			  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac());
			  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
			  		     			statistiqueFinanciaireMP.setCodeTransfer(productionMP.getNumOFMP());
			  		     			statistiqueFinanciaireMP.setDate(new Date());
			  		     			statistiqueFinanciaireMP.setDateOperation(productionMP.getDateProduction());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton().subtract(differenceCoutEmployee).add(coutTotalEmploye));
			  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction());
			  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
			  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton().subtract(differenceCoutEmployee).add(coutTotalEmploye));
			  		     			statistiqueFinanciaireMP.setCOUTPF(MontantTotalPF);
			  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
			  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
			  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
			  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
			  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
			  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
			  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_OF_TERMINER+" "+Constantes.PROD_CARTON+" "+Constantes.ETAT_MODIFIER);
			  		     			statistiqueFinanciaireMP.setEtatTransfer(Constantes.ETAT_MODIFIER);
			  		     		 
			  		     			statistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);

				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
			  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication Terminé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		
			  		     		
			  		     		
			  		     		}else{
			  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication n'est pas encore lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
			  		     	  }
			  		     	 }
				  		     
				  		     	}
				  		     });
				  		    	btnTerminerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
					         
		
		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 7, 89, 24);
		add(lblNumOF);
		tableEmploye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tableEmploye.getSelectedRow()!=-1)
				{
					DetailProductionMP detailproductionMP=detailproductionMPDAO.findById(Integer.valueOf(tableEmploye.getValueAt(tableEmploye.getSelectedRow(), 0).toString()) );
					
					txtcodeemployeproductioncarton.setText(detailproductionMP.getEmploye().getMatricule());
					comboemployeproductioncarton.setSelectedItem(detailproductionMP.getEmploye().getNomafficher());
					txtdelaiproductioncarton.setText(detailproductionMP.getDelaiEmploye()+"");
					txthsupp25productioncarton.setText(detailproductionMP.getHeureSupp25()+"");
					txthsupp50productioncarton.setText(detailproductionMP.getHeureSupp50()+"");
					checksortieproductioncarton.setSelected(detailproductionMP.isSortie());
					checkretardproductioncarton.setSelected(detailproductionMP.isRetard());
					checkEnpanneproductioncarton.setSelected(detailproductionMP.isPanne());
				}
				
				
				
			}
		});
		tableEmploye.setSortable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableEmploye);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 148, 1078, 308);
		add(scrollPane_1);
		tableEmploye.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEmploye.setShowVerticalLines(true);
		tableEmploye.setSelectionBackground(new Color(51, 204, 255));
		tableEmploye.setRowHeightEnabled(true);
		tableEmploye.setRowHeight(20);
		tableEmploye.setGridColor(new Color(0, 0, 255));
		tableEmploye.setForeground(Color.BLACK);
		tableEmploye.setColumnControlVisible(true);
		tableEmploye.setBackground(new Color(255, 255, 255));
		tableEmploye.setAutoCreateRowSorter(true);
	//	scrollPane_1.setViewportView(tableEmploye);
		  DefaultCellEditor ce1 = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
	        JTextComponent textField1 = (JTextComponent) ce1.getComponent();
	        util.Utils.copycollercell(textField1);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Saisir D\u00E9lai Equipe Production");
		titledSeparator_1.setBounds(10, 68, 1300, 17);
		add(titledSeparator_1);
				  		    
				  		  
				  		    txtQuantiteRealise.setBounds(502, 7, 153, 26);
				  		    add(txtQuantiteRealise);
				  		    txtQuantiteRealise.setColumns(10);
				  		    
				  		    lblQuantitRalise = new JLabel("Quantit\u00E9 r\u00E9alis\u00E9e:");
				  		    lblQuantitRalise.setBounds(399, 7, 102, 26);
				  		    add(lblQuantitRalise);
				  		    lblQuantitRalise.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		   
				  		    txtPrixServiceProd.setBounds(849, 7, 153, 26);
				  		    add(txtPrixServiceProd);
				  		    txtPrixServiceProd.setColumns(10);
				  		    
				  		    JLabel lblQuantite = new JLabel("Delai Service Production :");
				  		    lblQuantite.setBounds(696, 7, 143, 26);
				  		    add(lblQuantite);
				  		    lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    JButton button = new JButton("");
				  		    button.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		     		if(listDetailProductionMP.size()!=0)
				  		     		{
				  		     			if(tableEmploye.getSelectedRow()!=-1)
					  		     		{
				  		     				DetailProductionMP detailProduction=detailproductionMPDAO.findById(Integer.valueOf(tableEmploye.getValueAt(tableEmploye.getSelectedRow(), 0).toString()) );
					  		     		 
					  		     			
					  		     			ProductionMP productionTmp=productionMPDAO.findByNumOFStatut(detailProduction.getProductionMP().getNumOFMP(), Constantes.ETAT_OF_LANCER);
					  		     			
					  		     			if(productionTmp!=null)
					  		     			{
					  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
												{
													
												  detailproductionMPDAO.delete(detailProduction.getId());
												  
												  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
												 for(int t=0;t<listDetailProductionMP.size();t++)
												 {
													 if(listDetailProductionMP.get(t).getId()==detailProduction.getId())
													 {
														 listDetailProductionMP.remove(t);
													 }
												 }
												 
												  productionTmp.setDetailProductionsMP(listDetailProductionMP);
												  productionMPDAO.edit(productionTmp);
													
												  afficher_tableEmploye(listDetailProductionMP);
													
												}
					  		     				
					  		     				
					  		     			}else
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Impossible de supprimer employé dont le OF n'est pas lancé !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
					  		     				return;
					  		     			}
					  		     			
					  		     		}
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		    	}
				  		    });
				  		    button.setBounds(1110, 247, 58, 23);
				  		  button.setIcon(imgSupprimer);
				  		    add(button);
				  		    
				  		   
				  		txtNumOF.setSelectedIndex(-1);
				  		
				  		JLabel label = new JLabel("Code :");
				  		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label.setBounds(9, 96, 37, 26);
				  		add(label);
				  		
				  		txtcodeemployeproductioncarton = new JTextField();
				  		txtcodeemployeproductioncarton.addKeyListener(new KeyAdapter() {
				  			@Override
				  			public void keyPressed(KeyEvent e) {
				  				

			  		     		

			  					
			  					if(e.getKeyCode()==e.VK_ENTER)
			  		      		{
			  						Employe employe=mapMatriculeEmploye.get(txtcodeemployeproductioncarton.getText());
			  						if(employe!=null)
			  						{
			  							comboemployeproductioncarton.setSelectedItem(employe.getNomafficher());
			  						}else
			  						{
			  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
			  							return;
			  						}
			  						
			  		      		}
			  				
			  					
			  					
			  					
			  					
			  				
			  		     		
			  		     		
			  		     		
			  		     	
				  				
				  				
				  				
				  			}
				  		});
				  		txtcodeemployeproductioncarton.setColumns(10);
				  		txtcodeemployeproductioncarton.setBounds(45, 99, 71, 26);
				  		add(txtcodeemployeproductioncarton);
				  		
				  		JLabel label_1 = new JLabel("Employe :");
				  		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_1.setBounds(126, 99, 64, 26);
				  		add(label_1);
				  		
				  		 comboemployeproductioncarton = new JComboBox();
				  		 comboemployeproductioncarton.addItemListener(new ItemListener() {
				  		 	public void itemStateChanged(ItemEvent arg0) {
				  		 		

			  		   	 		
			  		   	 		if(!comboemployeproductioncarton.getSelectedItem().equals(""))
			  		   	 		{
			  		   	 			
			  		   	 			Employe employe=mapNomEmploye.get(comboemployeproductioncarton.getSelectedItem());
			  		   	 			if(employe!=null)
			  		   	 			{
			  		   	 				txtcodeemployeproductioncarton.setText(employe.getMatricule());
			  		   	 			}else
			  		   	 			{
			  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
			  		   	 				return;
			  		   	 			}
			  		   	 			
			  		   	 			
			  		   	 			
			  		   	 		}
			  		   	 		
			  		   	 	
				  		 		
				  		 	}
				  		 });
				  		comboemployeproductioncarton.setSelectedIndex(-1);
				  		comboemployeproductioncarton.setBounds(177, 104, 233, 24);
				  		add(comboemployeproductioncarton);
				  		 AutoCompleteDecorator.decorate(comboemployeproductioncarton);
				  		JLabel label_2 = new JLabel("Delai H :");
				  		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_2.setBounds(420, 106, 61, 26);
				  		add(label_2);
				  		
				  		txtdelaiproductioncarton = new JTextField();
				  		txtdelaiproductioncarton.setColumns(10);
				  		txtdelaiproductioncarton.setBounds(469, 105, 58, 26);
				  		add(txtdelaiproductioncarton);
				  		
				  		JLabel label_3 = new JLabel("H Supp 50% :");
				  		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_3.setBounds(533, 106, 77, 26);
				  		add(label_3);
				  		
				  		txthsupp50productioncarton = new JTextField();
				  		txthsupp50productioncarton.setColumns(10);
				  		txthsupp50productioncarton.setBounds(605, 106, 58, 26);
				  		add(txthsupp50productioncarton);
				  		
				  		JLabel label_4 = new JLabel("H Supp 25% :");
				  		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		label_4.setBounds(673, 106, 77, 26);
				  		add(label_4);
				  		
				  		txthsupp25productioncarton = new JTextField();
				  		txthsupp25productioncarton.setColumns(10);
				  		txthsupp25productioncarton.setBounds(747, 105, 58, 26);
				  		add(txthsupp25productioncarton);
				  		
				  		 checkEnpanneproductioncarton = new JCheckBox("En Panne");
				  		checkEnpanneproductioncarton.setBounds(810, 108, 76, 23);
				  		add(checkEnpanneproductioncarton);
				  		checkEnpanneproductioncarton.setVisible(true);
				  		 checksortieproductioncarton = new JCheckBox("Sortie");
				  		checksortieproductioncarton.setBounds(886, 108, 61, 23);
				  		add(checksortieproductioncarton);
				  		
				  		 checkretardproductioncarton = new JCheckBox("Retard");
				  		checkretardproductioncarton.setBounds(963, 104, 71, 24);
				  		add(checkretardproductioncarton);
				  		
				  		JButton button_1 = new JButton("Vider");
				  		button_1.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent arg0) {
				  				
				  			ViderEmployeProductionCarton();	
				  				
				  			}
				  		});
				  		button_1.setBounds(1095, 103, 81, 32);
				  		add(button_1);
				  		
				  		JButton button_2 = new JButton("");
				  		button_2.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent e) {
				  				
				  				Parametre heure=parametreDAO.findByDateByLibelle(productionMP.getDateProduction(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);

				  				BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  				boolean absent=false;
				  		   		int idEmploye;
				  		   		boolean sortie=false;
				  		   		boolean retard=false;
				  		   	boolean Panne=false;
				  		   		
				  		  	if(txtdelaiproductioncarton.getText().isEmpty()==false)
		  		     		{
		  		     			
		  		     			if(!txtdelaiproductioncarton.getText().trim().equals(""))
			  		     		{
		  		     				
		  		     				if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
		  		     				{
		  		     					
		  		     					JOptionPane.showMessageDialog(null, "Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
			  		     				return;
		  		     					
		  		     				}
		  		     				
		  		     				
		  		     				if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))<0)
		  		     				{
		  		     					if(checksortieproductioncarton.isSelected()==false && checkretardproductioncarton.isSelected()==false && checkEnpanneproductioncarton.isSelected()==false)
		  		     					{
		  		     						JOptionPane.showMessageDialog(null, "Veuillez Cocher Sortie , Retard Ou En Panne Pour Les heures Inferieur Au Delais De production SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     				return;
		  		     					}
		  		     					
		  		     					
		  		     				}
		  		     				
		  		     				
			  		     		}
		  		     			
		  		     			
		  		     		}
				  		   		
				  		    	
				  		  	if(!txtcodeemployeproductioncarton.getText().equals("") && !comboemployeproductioncarton.getSelectedItem().equals(""))
			  		      	{
				  				    	DetailProductionMP detailproductionMP=new DetailProductionMP();
				  				    	detaildelai=BigDecimal.ZERO;
				  				    	detailheur25=BigDecimal.ZERO;
				  				    	detailheur50=BigDecimal.ZERO;
				  				    	absent=false;
				  				    	sortie=false;
				  				    	retard=false;
				  				    	Panne=false;
				  				    //	int key=lsiteInt.get(i);
				  				    	Employe employe= mapMatriculeEmploye.get(txtcodeemployeproductioncarton.getText());
				  				    	
				  				    	if(employe!=null)
				  				    	{
				  				    		idEmploye=employe.getId();
				  				    	
				  				    
				  				    	
				  				    		if(!txtdelaiproductioncarton.getText().equals(""))
				  				    		{
				  				    			
				  				    			if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
				  				    			{
				  				    				
				  				    				if(checksortieproductioncarton.isSelected()==true)
				  		  		    				{
				  		  		    				sortie=true;
				  		  		    				}
				  				    				
				  				    				if(checkEnpanneproductioncarton.isSelected()==true)
				  		  		    				{
				  		  		    				Panne=true;
				  		  		    				}
		                                              if(checkretardproductioncarton.isSelected()==true)
				  		  		    				{
				  		  		    					retard=true;
				  		  		    				}
		 
				  				    				detaildelai=new BigDecimal(txtdelaiproductioncarton.getText());
				  				    				
				  				    				if(!txthsupp25productioncarton.getText().equals(""))
				  		  		    				{
				  				    					
				  				    					if(new BigDecimal(txthsupp25productioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    					{
				  		  		    					detailheur25=new BigDecimal(txthsupp25productioncarton.getText());
				  		  		    					}
				  				    					
				  		  		    				}
				  				    		    		
				  				    		    	
				  				    				if(!txthsupp50productioncarton.getText().equals(""))
				  		  		    				{
				  		  		    					if(new BigDecimal(txthsupp50productioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    					{
				  		  		    					detailheur50=new BigDecimal(txthsupp50productioncarton.getText());
				  		  		    					}
				  		  		    					
				  		  		    					
				  		  		    				}
				  				    		    	
				  				    		    	detailproductionMP.setEmploye(employe);
				  				    		    	detailproductionMP.setTypeResEmploye(employe.getTypeResEmploye());
				  				    		    	detailproductionMP.setDelaiEmploye(detaildelai);
				  				    		    	detailproductionMP.setHeureSupp25(detailheur25);
				  				    		    	detailproductionMP.setHeureSupp50(detailheur50);
				  				    		    	
				  				    		   	detailproductionMP.setCoutTotal(detaildelai.multiply(heure.getValeur()));
						  				    	detailproductionMP.setCoutSupp25(detailheur25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))));
						  				    	detailproductionMP.setCoutSupp50(detailheur50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))));
						  				    	
						  				    	detailproductionMP.setCoutHoraire(heure.getValeur());
						  				    	detailproductionMP.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
						  				    	detailproductionMP.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
						  				    	
						  				    	detailproductionMP.setPanne(Panne);
				  				    		    	detailproductionMP.setAbsent(absent);
				  				    		    	detailproductionMP.setSortie(sortie);
				  				    		    	detailproductionMP.setRetard(retard);
				  				    		    	detailproductionMP.setAutorisation(false);
				  				    		    	detailproductionMP.setProductionMP(productionMP);
				  							    	listDetailProductionMP.add(detailproductionMP);
				  				    		
				  				    			}
				  				    			
				  				    			}
				  				    		 
				  				    	
				  				    	}
				  				     }
				  				     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
				  				     		
				  				     		productionMP.setDetailProductionsMP(listDetailProductionMP);
				  				     		productionMPDAO.edit(productionMP);
				  				     		listDetailProductionMP.clear();
				  				     		listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());;
						  		  		     	 afficher_tableEmploye(listDetailProductionMP);
						  		  		  ViderEmployeProductionCarton();
				  			
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  			}
				  		});
				  		button_2.setBounds(1110, 179, 58, 23);
				  		button_2.setIcon(imgAjouter);
				  		add(button_2);
				  		if(productionMP!=null)
				  		{
				  			afficher_tableEmploye(productionMP.getDetailProductionsMP());
				  		}
				  		  
				  		  
				  		
				  		
				 	
				  		comboemployeproductioncarton.addItem("");
				  		
				  		  labelTotalEmployeTravail = new JLabel("");
				  		labelTotalEmployeTravail.setFont(new Font("Tahoma", Font.BOLD, 16));
				  		labelTotalEmployeTravail.setBounds(8, 658, 325, 26);
				  		add(labelTotalEmployeTravail);
				  		
				  		JButton btnModifier = new JButton("");
				  		btnModifier.addActionListener(new ActionListener() {
				  			public void actionPerformed(ActionEvent arg0) {
				  				
				  				if(tableEmploye.getSelectedRow()!=-1)
								{
									DetailProductionMP detailproductionMP=detailproductionMPDAO.findById(Integer.valueOf(tableEmploye.getValueAt(tableEmploye.getSelectedRow(), 0).toString()) );
							
									Parametre heure=parametreDAO.findByDateByLibelle(productionMP.getDateProduction(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);

					  				BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
					  				boolean absent=false;
					  		   		int idEmploye;
					  		   		boolean sortie=false;
					  		   		boolean retard=false;
					  		   	boolean Panne=false;
					  		   		
					  		  	if(txtdelaiproductioncarton.getText().isEmpty()==false)
			  		     		{
			  		     			
			  		     			if(!txtdelaiproductioncarton.getText().trim().equals(""))
				  		     		{
			  		     				
			  		     				if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
			  		     				{
			  		     					
			  		     					JOptionPane.showMessageDialog(null, "Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     				return;
			  		     					
			  		     				}
			  		     				
			  		     				
			  		     				if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))<0)
			  		     				{
			  		     					if(checksortieproductioncarton.isSelected()==false && checkretardproductioncarton.isSelected()==false && checkEnpanneproductioncarton.isSelected()==false)
			  		     					{
			  		     						JOptionPane.showMessageDialog(null, "Veuillez Cocher Sortie , Retard Ou En Panne Pour Les heures Inferieur Au Delais De production SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
					  		     				return;
			  		     					}
			  		     					
			  		     					
			  		     				}
			  		     				
			  		     				
				  		     		}
			  		     			
			  		     			
			  		     		}
					  		   		
					  		    	
					  		  	if(!txtcodeemployeproductioncarton.getText().equals("") && !comboemployeproductioncarton.getSelectedItem().equals(""))
				  		      	{
					  				    
					  		  		
					  		  		
					  		  		
					  		  		
					  		  		
					  		  		
					  		  		
					  				    	detaildelai=BigDecimal.ZERO;
					  				    	detailheur25=BigDecimal.ZERO;
					  				    	detailheur50=BigDecimal.ZERO;
					  				    	absent=false;
					  				    	sortie=false;
					  				    	retard=false;
					  				    	Panne=false;
					  				    //	int key=lsiteInt.get(i);
					  				    	Employe employe= mapMatriculeEmploye.get(txtcodeemployeproductioncarton.getText());
					  				    	
					  				    	if(employe!=null)
					  				    	{
					  				    		idEmploye=employe.getId();
					  				    	
					  				    		
					  				    		boolean existe=false;
								  		      	
							  		     		 
							  		      		for(int t=0;t<listDetailProductionMP.size();t++)
							  		      		{
							  		      			
							  		      			if(listDetailProductionMP.get(t).getEmploye().getId()==employe.getId() && detailproductionMP.getId()!=listDetailProductionMP.get(t).getId())
							  		      			{
							  		      			existe=true;
							  		      			}
							  		      			
							  		      			
							  		      			
							  		      		}
							  		      		
							  		      		if(existe==true)
							  		      		{
							  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
							  		      			return;
							  		      		}
							  		     		
					  				    		
					  				    		
					  				    
					  				    	
					  				    		if(!txtdelaiproductioncarton.getText().equals(""))
					  				    		{
					  				    			
					  				    			if(new BigDecimal(txtdelaiproductioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
					  				    			{
					  				    				
					  				    				if(checksortieproductioncarton.isSelected()==true)
					  		  		    				{
					  		  		    				sortie=true;
					  		  		    				}
					  				    				
					  				    				if(checkEnpanneproductioncarton.isSelected()==true)
					  		  		    				{
					  		  		    				Panne=true;
					  		  		    				}
			                                              if(checkretardproductioncarton.isSelected()==true)
					  		  		    				{
					  		  		    					retard=true;
					  		  		    				}
			 
					  				    				detaildelai=new BigDecimal(txtdelaiproductioncarton.getText());
					  				    				
					  				    				if(!txthsupp25productioncarton.getText().equals(""))
					  		  		    				{
					  				    					
					  				    					if(new BigDecimal(txthsupp25productioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
					  		  		    					{
					  		  		    					detailheur25=new BigDecimal(txthsupp25productioncarton.getText());
					  		  		    					}
					  				    					
					  		  		    				}
					  				    		    		
					  				    		    	
					  				    				if(!txthsupp50productioncarton.getText().equals(""))
					  		  		    				{
					  		  		    					if(new BigDecimal(txthsupp50productioncarton.getText()).compareTo(BigDecimal.ZERO) >0)
					  		  		    					{
					  		  		    					detailheur50=new BigDecimal(txthsupp50productioncarton.getText());
					  		  		    					}
					  		  		    					
					  		  		    					
					  		  		    				}
					  				    		    	
					  				    		    	detailproductionMP.setEmploye(employe);
					  				    		    	detailproductionMP.setTypeResEmploye(employe.getTypeResEmploye());
					  				    		    	detailproductionMP.setDelaiEmploye(detaildelai);
					  				    		    	detailproductionMP.setHeureSupp25(detailheur25);
					  				    		    	detailproductionMP.setHeureSupp50(detailheur50);
					  				    		    	
					  				    		   	detailproductionMP.setCoutTotal(detaildelai.multiply(heure.getValeur()));
							  				    	detailproductionMP.setCoutSupp25(detailheur25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))));
							  				    	detailproductionMP.setCoutSupp50(detailheur50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))));
							  				    	
							  				    	detailproductionMP.setCoutHoraire(heure.getValeur());
							  				    	detailproductionMP.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
							  				    	detailproductionMP.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
							  				    	
							  				    	detailproductionMP.setPanne(Panne);
					  				    		    	detailproductionMP.setAbsent(absent);
					  				    		    	detailproductionMP.setSortie(sortie);
					  				    		    	detailproductionMP.setRetard(retard);
					  				    		    	detailproductionMP.setAutorisation(false);
					  				    		    	detailproductionMP.setProductionMP(productionMP);
					  							    	 
					  							    	
					  							  	for(int d=0;d<listDetailProductionMP.size();d++)
											  		{
											  			if(listDetailProductionMP.get(d).getId()==detailproductionMP.getId())
											  			{
											  				listDetailProductionMP.set(d, detailproductionMP);
											  			}
											  		}
					  				    		
					  				    			}
					  				    			
					  				    			}
					  				    		 
					  				    	
					  				    	}
					  				     }
					  				     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
					  				     		
					  				     		productionMP.setDetailProductionsMP(listDetailProductionMP);
					  				     		productionMPDAO.edit(productionMP);
					  				     		listDetailProductionMP.clear();
					  				     		listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());;
							  		  		     	 afficher_tableEmploye(listDetailProductionMP);
							  		  		  ViderEmployeProductionCarton();
					  			
					  				
								
								
								
								}

				  				
				  	
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  				
				  			
				  				
				  				
				  				
				  			}
				  		});
				  		btnModifier.setBounds(1110, 213, 58, 23);
				  		btnModifier.setIcon(imgModifier);
				  		add(btnModifier);
				  		  
				  	  for(int i=0;i<listEmploye.size();i++)
					  {
						  
						Employe employe=listEmploye.get(i);  
						comboemployeproductioncarton.addItem(employe.getNomafficher());
						
					mapMatriculeEmploye.put(employe.getMatricule(), employe);
					 mapNomEmploye.put(employe.getNomafficher(), employe);
					  
					  
					  }  		  
				  		  
				  		  
				  		  
	}
	
 

	
void	intialiserTableMP(){
	
	/*
	
	JComboBox<CheckableItem> jcombobox=new JComboBox();
	
	
	  final CheckableItem[] codefournisseur =new CheckableItem[listFournisseurAdhesive.size()];
	  
	  for(int i=0;i<listFournisseurAdhesive.size();i++) {
	  
	  FournisseurAdhesive fournisseurAdhesive= listFournisseurAdhesive.get(i);
	  codefournisseur[i]=  new CheckableItem(fournisseurAdhesive.getCodeFournisseur(),false);
	  
	  }


jcombobox=new CheckedComboBox<CheckableItem>(new DefaultComboBoxModel<CheckableItem>(codefournisseur));
jcombobox.setEditable(false);
final ListModel<CheckableItem> model=jcombobox.getModel();
*/
		 modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP","Quantité Calculée","Quantité Existante","Quantité Chargée","Charge Supp", "Quantité Consommée", "Quantité Déchet","Quantité Déchet Fournisseur", "Quantité Restée", "QTE MOINS ","QTE PLUS","Code Fournisseur MP", "Ecart"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false, true, true, true,true,true,true,true,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		 
	 
		  		    
	  		 
	  		  
	  		 
	  		 
	  		 
	  		 /*
	  		   jcombobox.addItemListener(new ItemListener() {

	 			  @Override public void itemStateChanged(ItemEvent e) { if (e.getStateChange()
	 			  == ItemEvent.SELECTED) {
	 			  
	 			  List<String> sl = new ArrayList<>(); 
	 			  for (int i = 0; i < model.getSize(); i++)
	 			  { 
	 				  CheckableItem v = model.getElementAt(i); 
	 				  if (v.isSelected()) 
	 				  {
	 			  sl.add(v.toString()); 
	 			  } 
	 				  } 
	 			  if (sl.isEmpty()) {
	 			  //JOptionPane.showMessageDialog(null, "Vide"); // When returning the empty  string, the height of JComboBox may become 0 in some cases. 
	 				  } else {
	 			  
	 			  table.setValueAt(String.valueOf(sl.stream().sorted().collect(Collectors.
	 			  joining(", "))), table.getSelectedRow(), table.getSelectedColumn()); 
	 			  
	 			   JOptionPane.showMessageDialog(null,sl.stream().sorted().collect(Collectors.joining(", ")));
	 			  
	 			  }
	 			  
	 			  
	 			 
	 			  } }
	 			 
	 	  		
	 	        }); */
	  		   
	  		   
	}
	

	

void afficher_tableEmploye(List<DetailProductionMP> listDetailProductionMP)
	{
	initialiserTableauEmploye();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
	boolean panne=false;
	int nbrEmployeTravaill=0;
		  int i=0;
			while(i<listDetailProductionMP.size())
			{	
				DetailProductionMP detailProductionMP=listDetailProductionMP.get(i);
				delai=detailProductionMP.getDelaiEmploye();
				heureSupp25=detailProductionMP.getHeureSupp25();
				heureSupp50=detailProductionMP.getHeureSupp50();
				absent=detailProductionMP.isAbsent();
				sortie=detailProductionMP.isSortie();
				retard=detailProductionMP.isRetard();
				panne=detailProductionMP.isPanne();
				if(absent==false)
				{
					nbrEmployeTravaill=nbrEmployeTravaill+1;
				}
				Object []ligne={detailProductionMP.getId(), detailProductionMP.getEmploye().getId(),detailProductionMP.getEmploye().getMatricule(),detailProductionMP.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard,panne};

				modeleEmploye.addRow(ligne);
				i++;
			}
			
			tableEmploye.setModel(modeleEmploye);
			
			
			labelTotalEmployeTravail.setText("Total Employee Travail : "+nbrEmployeTravaill);
			
	}





	
List<DetailProductionMP> calculeCoutEmploye(List<DetailProductionMP> listDetailProductionMP,Map< Integer, String> mapDelaiEmploye){
		
	Parametre heure=parametreDAO.findByDateByLibelle(productionMP.getDateProduction(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	BigDecimal delai=BigDecimal.ZERO;
		
		BigDecimal remise=BigDecimal.ZERO;
		BigDecimal coutHoraire=BigDecimal.ZERO;
		BigDecimal heureSupp25; 
		BigDecimal heureSupp50; 
		
		BigDecimal coutSupp25=BigDecimal.ZERO;
		BigDecimal coutSupp50=BigDecimal.ZERO;
		
		List<DetailProductionMP> listDetailProductionMPTmp= new ArrayList<DetailProductionMP>();
		for(int i=0;i<listDetailProductionMP.size();i++){
			
			DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
			
			if(!detailProductionMP.getEmploye().isSalarie()){
			
			if(detailProductionMP.isAbsent()==true){
	    		
		   		 String code=Utils.genereCodeDateMoisAnnee(productionMP.getDateProduction());
					 
		   		 Utils.compterAbsenceEmploye(code, detailProductionMP.getEmploye(), productionMP.getDateProduction());
		   		}
			
			delai=new BigDecimal(mapDelaiEmploye.get(detailProductionMP.getEmploye().getId()));
			heureSupp25=new BigDecimal(mapHeureSupp25EmployeProd.get(detailProductionMP.getEmploye().getId()));
			heureSupp50=new BigDecimal(mapHeureSupp50EmployeProd.get(detailProductionMP.getEmploye().getId()));
			
				
			coutHoraire=heure.getValeur().multiply(delai);
			coutSupp25=heureSupp25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))) ;
			coutSupp50=heureSupp50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))) ;
			 if(detailProductionMP.isAbsent()==false && detailProductionMP.isSortie()==false && detailProductionMP.isRetard()==false){
		   			
					
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			
		   		}else {
		   			
		   			remise=BigDecimal.ZERO;
		   		}
			 
			 
			 remise=(remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP)).multiply(delai) ;
			coutTotalEmploye=coutTotalEmploye.add(coutHoraire).add(coutSupp25).add(coutSupp50).add(remise) ;
			detailProductionMP.setCoutTotal(coutHoraire);
			detailProductionMP.setDelaiEmploye(delai);
			detailProductionMP.setHeureSupp25(heureSupp25);
			detailProductionMP.setHeureSupp50(heureSupp50);
			detailProductionMP.setCoutSupp25(coutSupp25);
			detailProductionMP.setCoutSupp50(coutSupp50);
			detailProductionMP.setRemise(remise);
			
			
			if(!detailProductionMP.getEmploye().isSalarie()){
			FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(productionMP.getDateProduction(), detailProductionMP.getEmploye().getId());
			if(ficheEmploye!=null){
				/*Remplir fiche programme*/
				//coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal()) ;
				delai=delai.add(ficheEmploye.getDelaiEmploye());
				String numOF=ficheEmploye.getNumOF()+"-"+productionMP.getNumOFMP();
				BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(productionMP.getNbreHeure()) ;
		/*	ficheEmploye.setDateSituation(production.getDate());
			
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
			ficheEmploye.setCoutSupp25(coutSupp25);
			ficheEmploye.setCoutSupp50(coutSupp50);*/
			
			ficheEmploye.setNumOF(numOF);
			//ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setDelaiProd(delaiProd);
			
			ficheEmploye.setDelaiEmploye(delai);
			
			 if(detailProductionMP.isAbsent()==false){
		   			
		   		 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
				 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
					
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=parametre_remise_ouvrier.getValeur();
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=parametre_remise_ouvrier_vrac.getValeur();
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 ficheEmploye.setRemise(remise);
			ficheEmployeDAO.edit(ficheEmploye);
			} else{
				ficheEmploye =new FicheEmploye();
				//ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setNumOF(productionMP.getNumOFMP());
				ficheEmploye.setDateSituation(productionMP.getDateProduction());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				//ficheEmploye.setCoutSupp25(coutSupp25);
				//ficheEmploye.setCoutSupp50(coutSupp50);
				
				
				 if(detailProductionMP.isAbsent()==false){
			   			
			   		 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
						
						if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=parametre_remise_ouvrier.getValeur();
						if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=parametre_remise_ouvrier_vrac.getValeur();
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setDelaiProd(productionMP.getNbreHeure());
				ficheEmployeDAO.add(ficheEmploye);
				
			}
			}
			
			listDetailProductionMPTmp.add(detailProductionMP);
		}
		}	
		return listDetailProductionMPTmp;
		
	}







List<CoutProdMP>  calculeCoutMatierePremiere(List<CoutProdMP> listCoutProdMP){
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteDechetFournisseur=BigDecimal.ZERO;
	BigDecimal quantiteConsomme=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	BigDecimal quantiteMP=BigDecimal.ZERO;
	
	BigDecimal prixMP=BigDecimal.ZERO;
	BigDecimal coutDechet=BigDecimal.ZERO;
	BigDecimal coutDechetFournisseur=BigDecimal.ZERO;
	List<DetailTransferStockMP> listdetailtransfertStockMP =new ArrayList<DetailTransferStockMP>();
	TransferStockMP transferStockMP=null;
	if(productionMP!=null)
	{
		transferStockMP=transferStockMPDAO.findTransferByCodeStatut (productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE) ;
	}
	
	if(transferStockMP!=null)
	{
		listdetailtransfertStockMP=detailTransfertMPDAO.findByTransferStockMP(transferStockMP.getId());
	}
	
	 
	List<CoutProdMP> listCoutProdMPTmp=new ArrayList<CoutProdMP>();
	for(int i=0;i<listCoutProdMP.size();i++){ 
		
		CoutProdMP coutProdMP=listCoutProdMP.get(i);

		 
		
		//quantiteConsomme=Integer.parseInt(mapQuantiteConsomme.get(coutMP.getMatierePremier().getCode()));
		quantiteConsomme=coutProdMP.getQuantConsomme();
		if((quantiteConsomme.setScale(6, RoundingMode.HALF_UP).subtract(quantiteConsomme.setScale(0,RoundingMode.FLOOR ))).compareTo(new BigDecimal(0.5))>=0)
		{
			quantiteConsomme=quantiteConsomme.add(BigDecimal.ONE).setScale(0,RoundingMode.FLOOR);
		}else
		{
			quantiteConsomme=quantiteConsomme.setScale(0,RoundingMode.FLOOR );	
		}
		
		
		quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutProdMP.getMatierePremier().getCode()));
		quantiteReste=new BigDecimal(mapQuantiteReste.get(coutProdMP.getMatierePremier().getCode()));
		quantiteDechetFournisseur=new BigDecimal(mapQuantiteDechetFournisseur.get(coutProdMP.getMatierePremier().getCode()));
		//quantiteReste=stockmp.getStock()-(quantiteConsomme+quantiteDechet);
		coutProdMP.setQuantConsomme(quantiteConsomme);
		coutProdMP.setQuantDechet(quantiteDechet);
		
		if(mapCodeFournisseurMP.get(coutProdMP.getMatierePremier().getCode())!=null)
		{
			coutProdMP.setCodeFournisseur(mapCodeFournisseurMP.get(coutProdMP.getMatierePremier().getCode()));
		}
		
		
		//quantiteMP=quantiteConsomme+coutMP.getQuantChargeSupp();
		prixMP=quantiteConsomme.multiply(coutProdMP.getPrixUnitaire()) ;
		coutDechet=quantiteDechet.multiply(coutProdMP.getPrixUnitaire()) ;
		coutDechetFournisseur=quantiteDechetFournisseur.multiply(coutProdMP.getPrixUnitaire()) ;
		coutProdMP.setPrixTotal(prixMP);
		coutProdMP.setCoutDechet(coutDechet);
		coutTotalMP=coutTotalMP.add(prixMP) ;
		coutTotalDechet=coutTotalDechet.add(coutDechet).add(coutDechetFournisseur)  ;
		//quantiteReste=stockmp.getStock()-quantiteConsomme;
		
	
		for(int j=0;j<listdetailtransfertStockMP.size();j++)
		{
			
			DetailTransferStockMP detailTransferStockMP=listdetailtransfertStockMP.get(j);
			if(detailTransferStockMP.getMatierePremier().getId()==coutProdMP.getMatierePremier().getId())
			{
				detailTransferStockMP.setQuantiteRetour(quantiteReste);
				detailTransferStockMP.setQuantiteDechet(quantiteDechet);
				
				detailTransfertMPDAO.edit(detailTransferStockMP);
				
			}
		}
		
		
	}
	return listCoutProdMPTmp;
  }
void afficherDetailPorduction(List<DetailEstimationMP> lisDetailEstimationMP,List<CoutProdMP> listCoutProdMP){
	DetailEstimationMP detailEstimationMP=new DetailEstimationMP();
	CoutProdMP coutProdMP=new CoutProdMP();
	CoutProdMP coutProdMPTmp=new CoutProdMP();
	int position=-1;
	BigDecimal quantiteConsommme=BigDecimal.ZERO;
	BigDecimal quantiteRealise=new BigDecimal(txtQuantiteRealise.getText());
	boolean trouve =false;
	int priorite=0;
	BigDecimal quantiteCarton=BigDecimal.ZERO;
	
	for(int i=0;i<lisDetailEstimationMP.size();i++){
		trouve =false;
		detailEstimationMP=lisDetailEstimationMP.get(i);
		for(int j=0;j<listCoutProdMP.size();j++){
			coutProdMP=listCoutProdMP.get(j);
			
			if(detailEstimationMP.getMatierePremier().getId()==coutProdMP.getMatierePremier().getId()){
				
					if(lisDetailEstimationMP.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
					{
						
						quantiteConsommme=detailEstimationMP.getQuantite().multiply(quantiteRealise) ;
						quantiteCarton=quantiteCarton.add(quantiteConsommme);
						coutProdMP.setQuantConsomme(quantiteConsommme);
						listCoutProdMP.set(j,coutProdMP);
						
					}else
					{
						quantiteConsommme=detailEstimationMP.getQuantite().multiply(quantiteRealise) ;
						
						coutProdMP.setQuantConsomme(quantiteConsommme);
						listCoutProdMP.set(j,coutProdMP);
						
					}
				
				
				
				
				
				}
				
			
			}
			
		}
	
	
	for(int k=0;k<listCoutProdMP.size();k++)
	{
		CoutProdMP coutProdMPTMP=listCoutProdMP.get(k);
		
		if(lisDetailEstimationMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
		{
			
			if(quantiteCarton.compareTo(quantiteRealise)!=0)
			{
				
				quantiteConsommme=coutProdMPTMP.getQuantEstime().divide(coutProdMPTMP.getProdcutionCM().getQuantiteReel(), 6, RoundingMode.HALF_UP).multiply(quantiteRealise);
				
				if(quantiteConsommme.setScale(6, RoundingMode.HALF_UP).subtract(quantiteConsommme.setScale(0,RoundingMode.FLOOR )).compareTo((new BigDecimal(0.2)))>=0)
				{
					coutProdMPTMP.setQuantConsomme(coutProdMPTMP.getQuantConsomme().add(BigDecimal.ONE));
					listCoutProdMP.set(k, coutProdMPTMP);
					
					quantiteCarton=quantiteCarton.add(BigDecimal.ONE);
				}
				
			}
			
			
			
		}
	
		
		
	}
	
	
	
	
	
	

		
	}













void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","ID Employee","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent", "Sortie", "Retard", "Panne"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,false,true,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class,Boolean.class, Boolean.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		   tableEmploye.setModel(modeleEmploye); 
		   tableEmploye.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableEmploye.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(2).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(3).setPreferredWidth(160);
		   tableEmploye.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(5).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(6).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(7).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(8).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(9).setPreferredWidth(60);
		   tableEmploye.getColumnModel().getColumn(10).setPreferredWidth(60);
}

void initialiserTableauEmployeGen(){
	modeleEquipeGen =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent", "Sortie", "Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class,BigDecimal.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
}





List<DetailProductionMP>  remplieDetailProdcution(List<Employe> listEmploye){
	List<DetailProductionMP> listDetailProdcutionMP=new ArrayList<DetailProductionMP>();

	
	for(int i=0;i<listEmploye.size();i++){
		DetailProductionMP detailProdMP= new DetailProductionMP();
		Employe employe =listEmploye.get(i);
		detailProdMP.setCoutTotal(BigDecimal.ZERO);
		detailProdMP.setRemise(employe.getRemise());
		detailProdMP.setEmploye(employe);
		detailProdMP.setProductionMP(productionMP);
		
		//listDetailProdcution.add(detailProd);
		productionMP.getDetailProductionsMP().add(detailProdMP);
	}
//	production.setDetailProductions(listDetailProdcution);
	
	
/*	List<DetailProduction> listDetailProdcutionTmp=production.getDetailProductions();
	DetailProduction detailProdDeleted=new DetailProduction();
	if(listDetailProdcutionTmp!=null && listDetailProdcutionTmp.size()>0){
	for(int j=0;j<listDetailProdcutionTmp.size();j++){
		DetailProduction detailProd= listDetailProdcutionTmp.get(j);
		if(!listEmploye.contains(detailProd.getEmploye())){
			detailProdDeleted=production.removeDetailProduction(detailProd);
			System.out.println("######"+detailProdDeleted.getId());
		}
	}
	
	}*/
	productionMPDAO.edit(productionMP);
	
	return productionMP.getDetailProductionsMP();
  }


void  annulerStockMatierePremiere(List<CoutProdMP> listCoutProdMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteCharge=BigDecimal.ZERO;
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	for(int i=0;i<listCoutProdMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutProdMP coutProdMP=listCoutProdMP.get(i);
	
		
		 quantiteCharge=coutProdMP.getQuantCharge();
		StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),idMagasinProd );
		StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),idMagasinStockage );
		quantiteExistante=coutProdMP.getQuantExistante().add(stockMPProd.getStock()) ;
		
		quantiteStockage=stockMPStockage.getStock().add(quantiteCharge) ;
		
		
		stockMPProd.setStock(quantiteExistante);
		stockMPStockage.setStock(quantiteStockage);
		
		coutProdMP.setCoutDechet(BigDecimal.ZERO);
		coutProdMP.setQuantCharge(BigDecimal.ZERO);
		coutProdMP.setQuantChargeSupp(BigDecimal.ZERO);
		coutProdMP.setQuantConsomme(BigDecimal.ZERO);
		coutProdMP.setQuantDechet(BigDecimal.ZERO);
		coutProdMP.setQuantExistante(BigDecimal.ZERO);
		coutProdMP.setQuantite(BigDecimal.ZERO);
		coutProdMP.setQuantReste(BigDecimal.ZERO);
		listCoutProdMP.set(i, coutProdMP);
	//	listCoutMP.remove(i);

		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
		
	}
	
	
	List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	
	TransferStockMP transferStockMPCharge=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
	
	if(transferStockMPCharge!=null)
	{

		
		transferStockMPDAO.deleteObject(transferStockMPCharge);
		
		
	}
	
	listDetailTransferStockMP.clear();
	
TransferStockMP transferStockMPChargeSupp=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
	
	if(transferStockMPChargeSupp!=null)
	{
		

		
		transferStockMPDAO.deleteObject(transferStockMPChargeSupp);
		
		
		
	}
	
	
	TransferStockMP transferStockMPFabriquer=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
	
	if(transferStockMPFabriquer!=null)
	{
		
		
		
		transferStockMPDAO.deleteObject(transferStockMPFabriquer);
		
		
	}

	
	
	
	productionMP.setListCoutProdMP(listCoutProdMP);
  }
void  annulerStockProduitFini(){

	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
	
	 StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(),productionMP.getMagasinStockage().getId());
	 
	 BigDecimal p1=productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP)  ;
	 BigDecimal q1=productionMP.getQuantiteReel();
	 
	 BigDecimal montantQ1P1=q1.multiply(p1) ;
	 
	 BigDecimal q2=stockMP.getStock();
	 BigDecimal p2=stockMP.getPrixUnitaire();
	 
	 BigDecimal montantQ2P2=q2.multiply(p2) ;
	 
	 BigDecimal q=q2.subtract(q1) ;
	
	 BigDecimal p = (montantQ2P2 .subtract(montantQ1P1) ).divide(q, 6, BigDecimal.ROUND_HALF_UP)  ;
	 
	 stockMP.setStock(q);
	 stockMP.setPrixUnitaire(p);
	 stockMPDAO.edit(stockMP);
}

void deleteListeObject(List<FicheEmploye> listFicheEmploye){
	
	for(int i=0;i<listFicheEmploye.size();i++){
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		ficheEmployeDAO.deleteObject(ficheEmploye);
	}
}


void calculerStockCoutProduitFini(){

	
 TransferStockMP transferStockMPTmp=transferStockMPDAO.findTransferByCodeStatut(productionMP.getNumOFMP(), Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
	if(transferStockMPTmp==null)
	{
		BigDecimal prixPF=BigDecimal.ZERO;
		BigDecimal nouveauprix=BigDecimal.ZERO;
		BigDecimal quantiteTotal=BigDecimal.ZERO ;
		BigDecimal prixStock=BigDecimal.ZERO;
		BigDecimal prixOld=BigDecimal.ZERO;
		BigDecimal QuantiteOld=BigDecimal.ZERO;
		BigDecimal prixNew=BigDecimal.ZERO;
		
		
		
		
		CalculerStockFinaleMP();
		
		
	 
		
		MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
		
		for(int j=0;j<listEtatStockMPAfficher.size();j++)
		{
			
			EtatStockMP etatStockMP=listEtatStockMPAfficher.get(j);
			if(etatStockMP.getMp().getId()==matierePremier.getId())
			{
				if(etatStockMP.getMp().getPrixByYear(  DateUtils.getAnnee(productionMP.getDateProduction()) )!=null)
				{
					prixOld=etatStockMP.getMp().getPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()));
				}else
				{
					prixOld=BigDecimal.ZERO;
				}
				
				
				QuantiteOld=etatStockMP.getQuantiteFinale();
				
			}
			
			
			
		}
		
		
		
		/*
		 StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(),productionMP.getMagasinStockage().getId());
		 
		 quantiteTotal=stockMP.getStock().add(productionMP.getQuantiteReel());
		 prixStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire());
		 
		 	if(prixStock.compareTo(BigDecimal.ZERO)>0)
		 		nouveauprix=(prixTotal.add(prixStock) ).divide(quantiteTotal, 6, BigDecimal.ROUND_HALF_UP) ;
		 	else {
		 		nouveauprix= prixPF;
		 	}
		 	
		 	stockMP.setStock(quantiteTotal);
		 	stockMP.setPrixUnitaire(nouveauprix);
		 	stockMPDAO.edit(stockMP);
		 	*/
		 	TransferStockMP transferStockMP=new TransferStockMP();
		 	
		 	transferStockMP.setCodeTransfer(productionMP.getNumOFMP());
		 	transferStockMP.setCreerPar(productionMP.getUtilisateurCreation());
		 	transferStockMP.setDate(new Date());
		 	transferStockMP.setDateTransfer(productionMP.getDateProduction());
		 	transferStockMP.setDepot(productionMP.getMagasinStockage().getDepot());
		 	transferStockMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
		 	transferStockMPDAO.add(transferStockMP);
		 	
		 	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		 	detailTransferStockMP.setMagasinDestination(productionMP.getMagasinStockage());
		 	detailTransferStockMP.setMatierePremier(matierePremier);
		 	detailTransferStockMP.setQuantite(productionMP.getQuantiteReel());
		 	detailTransferStockMP.setTransferStockMP(transferStockMP);	
		 	detailTransferStockMP.setPrixUnitaire(productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP));
	       detailTransfertMPDAO.add(detailTransferStockMP);
	       
	       if(productionMP.getQuantiteReel().add(QuantiteOld).compareTo(BigDecimal.ZERO)!=0)
	       {
	           prixNew=  (((productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP)).multiply(productionMP.getQuantiteReel()) ).add(prixOld.multiply(QuantiteOld))).divide(productionMP.getQuantiteReel().add(QuantiteOld), 6, RoundingMode.HALF_UP);

	       }
	       
	       matierePremier.setPrixByYear( DateUtils.getAnnee(productionMP.getDateProduction()) , prixNew); 
	       matierePremiereDAO.edit(matierePremier);
	}else
	{
		
		listeDetailTransferStockMP =detailTransfertMPDAO.findByTransferStockMP(transferStockMPTmp.getId());
		
		DetailTransferStockMP detailTransferStockMP=listeDetailTransferStockMP.get(0);
		if(detailTransferStockMP!=null)
		{
			detailTransferStockMP.setPrixUnitaire(productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP));
			detailTransfertMPDAO.edit(detailTransferStockMP);
		}
		
		
		
	}
	
	
					  					
      
	 	
}



public void CalculerStockFinaleMPByDate(Date dateTransfert)
{
	
	

	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
		
	
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=null;
				Magasin magasin=productionMP.getMagasinStockage();
				
			  	FournisseurMP fournisseurMP=null;
			
			 
			  	
			  	
			  	
			
				
				
				
				Date mindate=null;
				
				Mindate=detailTransfertMPDAO.MinDate(magasin);
				
				for(int i=0;i<Mindate.size();i++)
				{
					
					 Object[] object= Mindate.get(i);
					
					
					if(Mindate.get(i)!=null)
					{
						mindate=(Date)object[0];
					}
					
				}
				
				String patternYear = "yyyy";
				String patternDate = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
				SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);

				
				
				
				if(mindate!=null)
				{
					
					String date = simpleDateFormatDate.format(mindate);
					
					
					try {
					mindate=simpleDateFormatDate.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}else
				{
					String year = simpleDateFormatyear.format(dateTransfert);
					
					try {
					mindate=simpleDateFormatDate.parse(year+"-01-01");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
				
				SubCategorieMp subCategorieMpthe=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
				
				
			  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransfertMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransfertMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
			
				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransfertMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

				//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteThe=detailTransfertMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				
				listeObjectEtatStockGroupByMPByFournisseurReception=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dateTransfert, magasin, subCategorieMpthe, null, null);

				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
			
		listEtatStockMP.clear();
		listEtatStockMPAfficher.clear();
		CalculerStockMPThe();	
		CalculerStockMPNonThe();		
			
			
		for(int j=0;j<listEtatStockMP.size();j++)
		{
			
		EtatStockMP etatStockMP=listEtatStockMP.get(j);	
			
		if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
			listEtatStockMPAfficher.add(etatStockMP);
			}
			
			
			
			
		}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
		{
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else
		{
			
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
			
			
		}
			
			
		
		
		
		
		
		
		
			
			
			
			
			
		}
		

		
		
		
		
			 
	
		
		

  
	
	
	
	
	
}





public void CalculerStockFinaleMP()
{
	
	

	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
		
	
		
		
			
			SubCategorieMp subCategorieMp=null;
				CategorieMp categorieMp=null;
				MatierePremier mp=null;
				Magasin magasin=productionMP.getMagasinStockage();
				
			  	FournisseurMP fournisseurMP=null;
			
			 
			  	
			  	
			  	
			
				
				
				
				Date mindate=null;
				
				Mindate=detailTransfertMPDAO.MinDate(magasin);
				
				for(int i=0;i<Mindate.size();i++)
				{
					
					 Object[] object= Mindate.get(i);
					
					
					if(Mindate.get(i)!=null)
					{
						mindate=(Date)object[0];
					}
					
				}
				
				String patternYear = "yyyy";
				String patternDate = "yyyy-MM-dd";
				SimpleDateFormat simpleDateFormatyear = new SimpleDateFormat(patternYear);
				SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);

				
				
				
				if(mindate!=null)
				{
					
					String date = simpleDateFormatDate.format(mindate);
					
					
					try {
					mindate=simpleDateFormatDate.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}else
				{
					String year = simpleDateFormatyear.format(productionMP.getDateProduction());
					
					try {
					mindate=simpleDateFormatDate.parse(year+"-01-01");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				}
				
				
				
				SubCategorieMp subCategorieMpthe=subcategoriempdao.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
			// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
				
				
			  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				
				//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
			  	  				  	
				
					listeObjectStockInitialGroupByMP=detailTransfertMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

				
					
					
				
				listeObjectEtatStockGroupByMP=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransfertMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
			
				
//////////////////////////////////////////////////////////////////////////////////////////////////// Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
			//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

				
				
					listeObjectStockInitialGroupByMPByFournisseur=detailTransfertMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
					
				

				//listeObjectEtatStockGroupByMPByFournisseur=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate,dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteThe=detailTransfertMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				
				listeObjectEtatStockGroupByMPByFournisseurReception=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);
				listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransfertMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,productionMP.getDateProduction(), magasin, subCategorieMpthe, null, null);

				
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
			
		listEtatStockMP.clear();
		listEtatStockMPAfficher.clear();
		CalculerStockMPThe();	
		CalculerStockMPNonThe();		
			
			
		for(int j=0;j<listEtatStockMP.size();j++)
		{
			
		EtatStockMP etatStockMP=listEtatStockMP.get(j);	
			
		if( subCategorieMp!=null && categorieMp==null && mp==null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
			listEtatStockMPAfficher.add(etatStockMP);
			}
			
			
			
			
		}else if(subCategorieMp!=null && categorieMp!=null && mp==null && fournisseurMP==null)
		{
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP==null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId())
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp!=null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getMp().getId()==mp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(subCategorieMp !=null && categorieMp==null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else if(categorieMp!=null && subCategorieMp!=null && mp==null && fournisseurMP!=null)
		{
			
			if(etatStockMP.getMp().getCategorieMp().getId()==categorieMp.getId() && etatStockMP.getMp().getCategorieMp().getSubCategorieMp().getId()==subCategorieMp.getId() && etatStockMP.getFournisseurMP().getId()==fournisseurMP.getId() )
			{
				
				listEtatStockMPAfficher.add(etatStockMP);	
				
			}
			
		}else
		{
			
			
			listEtatStockMPAfficher.add(etatStockMP);	
			
			
			
		}
			
			
		
		
		
		
		
		
		
			
			
			
			
			
		}
		

		
		
		
		
			 
	
		
		

  
	
	
	
	
	
}



public void CalculerStockMPNonThe()
{
	
	
	
	
		
		for(int i=0;i<listeObjectStockInitialGroupByMP.size() ; i++)
		{
			
			 Object[] object=listeObjectStockInitialGroupByMP.get(i);
			EtatStockMP etatStockMP=new EtatStockMP();
			MatierePremier mp= (MatierePremier)object[0];
			etatStockMP.setMp(mp);
									
			etatStockMP.setQuantiteInitial((BigDecimal)object[1]);
			
		
			 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
			
			//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
			listEtatStockMP.add(etatStockMP);
			
			
		}
	
	
	boolean existe=false;
	
	  for(int j=0;j<listeObjectEtatStockGroupByMP.size() ; j++) {
	  
		  existe=false;
		  
	  Object[] object=listeObjectEtatStockGroupByMP.get(j);
	  MatierePremier mp=(MatierePremier)object[0];
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==mp.getId()) {
		  if(listEtatStockMP.get(k).getFournisseurMP()==null)
		  {
			 
			  
			  
			  existe=true;
			  
			  EtatStockMP etatStockMP=listEtatStockMP.get(k);
			  if(etatStockMP.getMp().getCode().equals("MP_703"))
			  {
				  
				System.out.println(etatStockMP.getMp().getCode());  
				  
			  }
			 
			  if((BigDecimal)object[1] != null)
			  {
				  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
			  }else
			  {
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  }
			  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
			  {
				  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
			  }else
			  {
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  }
			 
			  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
			  {
				  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
			  }else
			  {
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  }
			 if((BigDecimal)object[4]!=null)
			 {
				 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
			 }else
			 {
				 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
			 }
			  
			 if((BigDecimal)object[5]!=null)
			 {
				 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
			 }else
			 {
				 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			 }
			 
			 if((BigDecimal)object[8]!=null)
			 {
				 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
			 }else
			 {
				 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			 }
			 
			 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
			 {
				  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

			 }else
			 {
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

			 }
			 if(((BigDecimal)object[10])!=null)
			 {
				  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
			 }else
			 {
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			 }
			
			 if((BigDecimal)object[11]!=null)
			 {
				  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
			 }else
			 {
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			 }
			if((BigDecimal)object[12]!=null)
			{
				 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
			}else
			{
				 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			}
			 
			  
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
			 
			  listEtatStockMP.set(k, etatStockMP);
		  }

	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(existe==false)
		{
			
			
			
			  EtatStockMP etatStockMP=new EtatStockMP();
			 
			  etatStockMP.setMp(mp);
			  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
			  
			  if((BigDecimal)object[1] != null)
			  {
				  etatStockMP.setQuantiteReception((BigDecimal)object[1]);
			  }else
			  {
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  }
			  if(((BigDecimal)object[2]).add((BigDecimal)object[3]) != null)
			  {
				  etatStockMP.setTransferEntrer(((BigDecimal)object[2]).add((BigDecimal)object[3]));
			  }else
			  {
				  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  }
			 
			  if(((BigDecimal)object[6]).add((BigDecimal)object[7])!=null)
			  {
				  etatStockMP.setTransferSortie(((BigDecimal)object[6]).add((BigDecimal)object[7]));
			  }else
			  {
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  }
			 if((BigDecimal)object[4]!=null)
			 {
				 etatStockMP.setQuantiteCharger((BigDecimal)object[4]);
			 }else
			 {
				 etatStockMP.setQuantiteCharger(BigDecimal.ZERO); 
			 }
			  
			 if((BigDecimal)object[5]!=null)
			 {
				 etatStockMP.setQuantiteChargerSupp((BigDecimal)object[5]); 
			 }else
			 {
				 etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			 }
			 
			 if((BigDecimal)object[8]!=null)
			 {
				 etatStockMP.setQuantiteRetour((BigDecimal)object[8]);
			 }else
			 {
				 etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			 }
			 
			 if(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17])!=null)
			 {
				  etatStockMP.setQuantiteAutreSortie(((BigDecimal)object[9]).add((BigDecimal)object[13]).add((BigDecimal)object[14]).add((BigDecimal)object[15]).add((BigDecimal)object[16]).add((BigDecimal)object[17]));

			 }else
			 {
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);

			 }
			 if(((BigDecimal)object[10])!=null)
			 {
				  etatStockMP.setQuantiteResterMachine(((BigDecimal)object[10]));
			 }else
			 {
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			 }
			
			 if((BigDecimal)object[11]!=null)
			 {
				  etatStockMP.setQuantiteFabriquer((BigDecimal)object[11]);
			 }else
			 {
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			 }
			if((BigDecimal)object[12]!=null)
			{
				 etatStockMP.setQuantiteExistante((BigDecimal)object[12]);
			}else
			{
				 etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			}
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
			 
			  listEtatStockMP.add(etatStockMP);	
			
			
			
		}
	  
	  
	  
	  
	  }
	 
	/////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	  
		boolean trouver=false;
		
		  for(int j=0;j<listeEtatStockTransfertEnAttenteNonThe.size() ; j++) {
		  
			  trouver=false;
			  
		 DetailTransferStockMP  detailTransferStockMP=listeEtatStockTransfertEnAttenteNonThe.get(j);
		
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId()) {
			  if(listEtatStockMP.get(k).getFournisseurMP()==null)
			  {
				 if(detailTransferStockMP.getFournisseur()==null) 
				 {
					 
						if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
							
						{
						if(detailTransferStockMP.getMagasinSouce()!=null)	
						{
							
							if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
							{
								
								
								  trouver=true;
								  
								  EtatStockMP etatStockMP=listEtatStockMP.get(k);
								  
								
								  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
								 		  listEtatStockMP.set(k, etatStockMP);
								
								
								
								
							}
							
							
							
						}
							
							
							
							
							
						}
					 
					 
					 
				 }
				 
			  }

		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(trouver==false)
			{
				
				 if(detailTransferStockMP.getFournisseur()==null) 
				 {
					 
						if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
							
						{
						if(detailTransferStockMP.getMagasinSouce()!=null)	
						{
							
							if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
							{
								
								
								
								  EtatStockMP etatStockMP=new EtatStockMP();
									 
								  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
								  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
								  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
								  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
								  etatStockMP.setTransferSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
								  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
								  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
								  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
								  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
								  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
								  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
								 
								  listEtatStockMP.add(etatStockMP);	
								
								
								
								
								
								
								
							}
						}
						}
				 }
				
				
				
				
				
			}
		  
		  
		  
		  
		  }
	  
	  
	  
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	  
	  
	  
	  
	  
	  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  }

	
	
	
}

public void CalculerStockMPThe()
{
	

	
	

	
	
		
		for(int i=0;i<listeObjectStockInitialGroupByMPByFournisseur.size() ; i++)
		{
			
			 Object[] object=listeObjectStockInitialGroupByMPByFournisseur.get(i);
			EtatStockMP etatStockMP=new EtatStockMP();
			MatierePremier mp= (MatierePremier)object[0];
			
			FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
			
			etatStockMP.setMp(mp);
									
			etatStockMP.setQuantiteInitial((BigDecimal)object[2]);
			
			etatStockMP.setFournisseurMP(fournisseurMP);
			 etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			etatStockMP.setQuantiteFinale(BigDecimal.ZERO);
			etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			//etatStockMP.setQuantiteFinale((BigDecimal)object[10]);
			listEtatStockMP.add(etatStockMP);
			
			
		}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	boolean existe=false;
	
	  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurReception.size() ; j++) {
	  
		  existe=false;
		  
	  Object[] object=listeObjectEtatStockGroupByMPByFournisseurReception.get(j);
	  MatierePremier mp=(MatierePremier)object[0];
	  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
		  
	  for(int k=0;k<listEtatStockMP.size();k++) {
	  
	  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
		  existe=true;
	  
	  EtatStockMP etatStockMP=listEtatStockMP.get(k);
	  
	 
	  if((BigDecimal)object[2]!=null)
	  {
		  etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add((BigDecimal)object[2]) ); 
	  }
	 
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  
	  }
	  

	  
	  
	  
	  
	  
	  }
	  
		if(existe==false)
		{
			
			
			
			  EtatStockMP etatStockMP=new EtatStockMP();
			 
			  etatStockMP.setMp(mp);
			  etatStockMP.setFournisseurMP(fournisseurMP);
			  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
			  if((BigDecimal)object[2]!=null)
			  {
				  etatStockMP.setQuantiteReception((BigDecimal)object[2]);
			  }else
			  {
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
			  }
			 
			  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
			  etatStockMP.setTransferSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
			  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
			  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
			  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
			  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
			  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
			  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
			  
			  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
			 
			  listEtatStockMP.add(etatStockMP);	
			
			
		}
	  
	  
	  
	  
	  }

//////////////////////////////////////////////////////////////////////////////////////////////////////// Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 existe=false;
		 
		 BigDecimal enter=BigDecimal.ZERO;
		 BigDecimal transfert=BigDecimal.ZERO;
		
		  for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurEntrer.size() ; j++) {
		  
			  existe=false;
			  
		  Object[] object=listeObjectEtatStockGroupByMPByFournisseurEntrer.get(j);
		  MatierePremier mp=(MatierePremier)object[0];
		  FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];
		  
		  enter=BigDecimal.ZERO;
		  transfert=BigDecimal.ZERO;
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
			  enter=BigDecimal.ZERO;
			  transfert=BigDecimal.ZERO;
		  if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
			  existe=true;
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		  
		 
		  if(((BigDecimal)object[2])!=null)
		  {
			  enter=(BigDecimal)object[2];
			 
		  }
		  if(((BigDecimal)object[3])!=null)
		  {
			  transfert=(BigDecimal)object[3];
			 
		  }
		  
		  
		  etatStockMP.setTransferEntrer (etatStockMP.getTransferEntrer().add(enter.add(transfert)));
		 
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(existe==false)
			{
				
				
				
				  EtatStockMP etatStockMP=new EtatStockMP();
				 
				  etatStockMP.setMp(mp);
				  etatStockMP.setFournisseurMP(fournisseurMP);
				  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
				  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
				  if(((BigDecimal)object[2])!=null)
				  {
					  enter=(BigDecimal)object[2];
					 
				  }
				  if(((BigDecimal)object[3])!=null)
				  {
					  transfert=(BigDecimal)object[3];
					 
				  }  
				  
				  
				 
				etatStockMP.setTransferEntrer (enter.add(transfert));
				 
				  etatStockMP.setTransferSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
				  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
				  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
				  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
				  etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
				  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
				  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
				  
				  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
				 
				  listEtatStockMP.add(etatStockMP);	
				
				
			}
		  
		  
		  
		  
		  }
		   
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortie=BigDecimal.ZERO;
 transfert=BigDecimal.ZERO;

for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurSortie.size() ; j++) {

existe=false;
sortie=BigDecimal.ZERO;
transfert=BigDecimal.ZERO;
Object[] object=listeObjectEtatStockGroupByMPByFournisseurSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	sortie=BigDecimal.ZERO;
	transfert=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if(((BigDecimal)object[2])!=null)
{
	sortie=(BigDecimal)object[2];
	 
}
if(((BigDecimal)object[3])!=null)
{
	  transfert=(BigDecimal)object[3];
	 
}  
etatStockMP.setTransferSortie (etatStockMP.getTransferSortie(). add(sortie.add(transfert)));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
	sortie=(BigDecimal)object[2];
	 
}
if(((BigDecimal)object[3])!=null)
{
	  transfert=(BigDecimal)object[3];
	 
} 


	etatStockMP.setTransferSortie(sortie.add(transfert));


etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


//////////////////////////////////////////////////////////////////////////////////////////////////////// Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal charge=BigDecimal.ZERO;
BigDecimal chargesupp=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurCharger.size() ; j++) {
	 charge=BigDecimal.ZERO;
	 chargesupp=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurCharger.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	charge=BigDecimal.ZERO;
	 chargesupp=BigDecimal.ZERO;

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if((BigDecimal)object[2]!=null)
{
	charge=(BigDecimal)object[2];
	
}
etatStockMP.setQuantiteCharger(etatStockMP.getQuantiteCharger().add(charge));
if((BigDecimal)object[3]!=null)
{
	chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteChargerSupp(etatStockMP.getQuantiteChargerSupp().add(chargesupp));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);

if((BigDecimal)object[2]!=null)
{
	charge=(BigDecimal)object[2];
	
}

if((BigDecimal)object[3]!=null)
{
chargesupp=(BigDecimal)object[3];
}

etatStockMP.setQuantiteCharger(charge);

etatStockMP.setQuantiteChargerSupp(chargesupp);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
	retour=BigDecimal.ZERO;
	retourFournisseurAnnule=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	retour=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);


if((BigDecimal)object[2]!=null)
{
	retour=(BigDecimal)object[2];
	
}
etatStockMP.setQuantiteRetour (etatStockMP.getQuantiteRetour().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
if((BigDecimal)object[2]!=null)
{
	retour=(BigDecimal)object[2];
	
}

	etatStockMP.setQuantiteRetour(retour);


etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
 sortie=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortie.size() ; j++) {
	 sortie=BigDecimal.ZERO;
	

existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortie.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	 sortie=BigDecimal.ZERO;
	

if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortie));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{
	
	System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
sortie=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortie));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   SortiePF ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;

BigDecimal sortiePF=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.size() ; j++) {

sortiePF=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {

sortiePF=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	sortiePF=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortiePF));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	sortiePF=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortiePF));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal sortieEnAttente=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.size() ; j++) {
	sortieEnAttente=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	sortieEnAttente=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	sortieEnAttente=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(sortieEnAttente));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	sortieEnAttente=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((sortieEnAttente));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Perte////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal perte=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.size() ; j++) {
	perte=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	perte=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	perte=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(perte));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	perte=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((perte));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}

////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
 retour=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.size() ; j++) {
	retour=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	retour=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
	retour=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retour));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
	retour=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retour));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}


////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Retour Fournisseur Annule////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
retourFournisseurAnnule=BigDecimal.ZERO;


for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.size() ; j++) {
retourFournisseurAnnule=BigDecimal.ZERO;


existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
retourFournisseurAnnule=BigDecimal.ZERO;


if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;


EtatStockMP etatStockMP=listEtatStockMP.get(k);



if(((BigDecimal)object[2])!=null)
{
retourFournisseurAnnule=((BigDecimal)object[2]);
}


etatStockMP.setQuantiteAutreSortie(etatStockMP.getQuantiteAutreSortie().add(retourFournisseurAnnule));


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
if(etatStockMP.getMp().getCode().equals("MP_1126") && etatStockMP.getFournisseurMP().getId()==10 )
{

System.out.println(etatStockMP.getMp().getCode());
}
if(((BigDecimal)object[2])!=null)
{
retourFournisseurAnnule=((BigDecimal)object[2]);
}

etatStockMP.setQuantiteAutreSortie((retourFournisseurAnnule));

etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}





//////////////////////////////////////////////////////////////////////////////////////////////////////// Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





existe=false;
BigDecimal resteMachine=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurResterMachine.size() ; j++) {
	resteMachine=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurResterMachine.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	resteMachine=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
	resteMachine=((BigDecimal)object[2]);
	
}
etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(resteMachine));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
	resteMachine=((BigDecimal)object[2]);
	
}
	etatStockMP.setQuantiteResterMachine(resteMachine);


etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	  

////////////////////////////////////////////////////////////////////////////////////////////////////////   Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal fabriquer=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurFabrique.size() ; j++) {
	fabriquer=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurFabrique.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	fabriquer=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
	fabriquer=((BigDecimal)object[2]);
	
}
etatStockMP.setQuantiteFabriquer (etatStockMP.getQuantiteFabriquer().add(fabriquer));

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
	fabriquer=((BigDecimal)object[2]);
	
}
	etatStockMP.setQuantiteFabriquer(fabriquer);


etatStockMP.setQuantiteExistante(BigDecimal.ZERO);

etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}	

//////////////////////////////////////////////////////////////////////////////////////////////////////// Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal existante=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurExistante.size() ; j++) {
	existante=BigDecimal.ZERO;
existe=false;

Object[] object=listeObjectEtatStockGroupByMPByFournisseurExistante.get(j);
MatierePremier mp=(MatierePremier)object[0];
FournisseurMP 	fournisseurMP=(FournisseurMP)object[1];

for(int k=0;k<listEtatStockMP.size();k++) {
	existante=BigDecimal.ZERO;
if(listEtatStockMP.get(k).getMp().getId()==mp.getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==fournisseurMP.getId()) {
existe=true;

EtatStockMP etatStockMP=listEtatStockMP.get(k);

if(((BigDecimal)object[2])!=null)
{
	existante=((BigDecimal)object[2]);
	
}

etatStockMP.setQuantiteExistante(etatStockMP.getQuantiteExistante().add(existante));
etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add(etatStockMP.getQuantiteExistante())))));
listEtatStockMP.set(k, etatStockMP);



}







}

if(existe==false)
{



EtatStockMP etatStockMP=new EtatStockMP();

etatStockMP.setMp(mp);
etatStockMP.setFournisseurMP(fournisseurMP);
etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
etatStockMP.setQuantiteReception(BigDecimal.ZERO);
etatStockMP.setTransferEntrer(BigDecimal.ZERO);
etatStockMP.setTransferSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
etatStockMP.setQuantiteResterMachine(BigDecimal.ZERO);
etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
if(((BigDecimal)object[2])!=null)
{
existante=((BigDecimal)object[2]);
	
}
etatStockMP.setQuantiteExistante(existante);


etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));

listEtatStockMP.add(etatStockMP);	


}




}
	  
//////////////////////////////////////////////////////////////////////////////////////////////////////// Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
		boolean trouver=false;
		
		  for(int j=0;j<listeEtatStockTransfertEnAttenteThe.size() ; j++) {
		  
			  trouver=false;
			  
		  DetailTransferStockMP detailTransferStockMP=listeEtatStockTransfertEnAttenteThe.get(j);
		 
			  
		  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  if(listEtatStockMP.get(k).getMp().getId()==detailTransferStockMP.getMatierePremier().getId() && listEtatStockMP.get(k).getFournisseurMP().getId()==detailTransferStockMP.getFournisseur().getId()) {
			
			if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
				
				
			{
			if(detailTransferStockMP.getMagasinSouce()!=null)	
			{
				
				if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
					
				{
					
					
					  trouver=true;
					  
					  EtatStockMP etatStockMP=listEtatStockMP.get(k);
					  
					 
					  etatStockMP.setQuantiteResterMachine(etatStockMP.getQuantiteResterMachine().add(detailTransferStockMP.getQuantite()));
					 		  listEtatStockMP.set(k, etatStockMP);
					
					
					
					
				}
				
				
				
			}
				
				
				
				
				
			}
			  
		
		  
		  
		  
		  }
		  

		  
		  
		  
		  
		  
		  }
		  
			if(trouver==false)
			{
				
				 if(detailTransferStockMP.getFournisseur()!=null) 
				 {
					 
						if(detailTransferStockMP.getMagasinDestination().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
							
							
						{
						if(detailTransferStockMP.getMagasinSouce()!=null)	
						{
							
							if(detailTransferStockMP.getMagasinSouce().getCatMagasin().equals(Constantes.MAGASIN_CODE_CATEGORIE_PRODUCTION) )  
								
							{
								
								  EtatStockMP etatStockMP=new EtatStockMP();
									 
								  etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
								  etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
								  etatStockMP.setQuantiteInitial(BigDecimal.ZERO);
								  etatStockMP.setQuantiteReception(BigDecimal.ZERO);
								  etatStockMP.setTransferEntrer(BigDecimal.ZERO);
								  etatStockMP.setTransferSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteCharger(BigDecimal.ZERO);
								  etatStockMP.setQuantiteChargerSupp(BigDecimal.ZERO);
								  etatStockMP.setQuantiteRetour(BigDecimal.ZERO);
								  etatStockMP.setQuantiteAutreSortie(BigDecimal.ZERO);
								  etatStockMP.setQuantiteResterMachine(detailTransferStockMP.getQuantite());
								  etatStockMP.setQuantiteFabriquer(BigDecimal.ZERO);
								  etatStockMP.setQuantiteExistante(BigDecimal.ZERO);
								  
								  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie().add( etatStockMP.getQuantiteExistante())))));
								 
								  listEtatStockMP.add(etatStockMP);	
								
							}
								
							}
						}
						}
				 
			
				
				
			}
		  
		  
		  
		  
		  }
	  
	  
	  
	  
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  
	  
	  
	  
	  
	  
	  
	 
	  
	  for(int k=0;k<listEtatStockMP.size();k++) {
		  
		  EtatStockMP etatStockMP=listEtatStockMP.get(k);
		
		  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
		 
		  if(etatStockMP.getFournisseurMP()!=null)
		  {
			  System.out.println(etatStockMP.getMp().getCode() +" *** "+etatStockMP.getFournisseurMP().getCodeFournisseur() + "****"+etatStockMP.getQuantiteInitial()+" *** "+etatStockMP.getQuantiteReception()+" *** "+ etatStockMP.getQuantiteRetour()+" *** "+etatStockMP.getTransferEntrer()+" *** "+etatStockMP.getQuantiteResterMachine()+" *** "+etatStockMP.getQuantiteFabriquer() +" ---- "+ etatStockMP.getQuantiteCharger() +" *****" + etatStockMP.getQuantiteChargerSupp()+" ***** "+etatStockMP.getQuantiteAutreSortie() +" ***** "+ etatStockMP.getTransferSortie() +" ***** "+ etatStockMP.getQuantiteExistante());

		  }
		  
		  
		  listEtatStockMP.set(k, etatStockMP);
		  
		  
		  }

		
	
}






public void ViderEmployeProductionCarton()
{
	txtcodeemployeproductioncarton.setText("");
	comboemployeproductioncarton.setSelectedItem("");
	txtdelaiproductioncarton.setText("");
	txthsupp25productioncarton.setText("");
	txthsupp50productioncarton.setText("");
	checkEnpanneproductioncarton.setSelected(false);
	checksortieproductioncarton.setSelected(false);
	checkretardproductioncarton.setSelected(false);
	txtcodeemployeproductioncarton.requestFocus();
}
}
