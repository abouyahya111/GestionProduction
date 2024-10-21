package Production;

import java.awt.Color;
import java.awt.Component;
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
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import java.util.Vector;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

import ProductionCarton.ListeEmployeProdCarton;
import config.Connect;
import util.CheckableItem;
import util.CheckedComboBox;
import util.Constantes;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CoutArticlePFDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCoutArticlePFDAOImpl;
import dao.daoImplManager.DetailCoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.DetailNombreCartonOffreVariantDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ManqueDechetFournisseurDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.NombreCartonOffreVariantDAOImpl;
import dao.daoImplManager.OffreProductionDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
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
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCoutArticlePFDAO;
import dao.daoManager.DetailCoutHorsProdEnAttentDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailManqueDechetFournisseurDAO;
import dao.daoManager.DetailNombreCartonOfferVariantDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ManqueDechetFournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.NombreCartonOfferVariantDAO;
import dao.daoManager.OffreProductionDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CoutArticlePF;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailCoutArticlePF;
import dao.entity.DetailCoutHorsProdEnAttent;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationPromo;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailManqueDechetFournisseur;
import dao.entity.DetailNombreCartonOffreVariante;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.FactureProduction;
import dao.entity.FicheEmploye;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.ManqueDechetFournisseur;
import dao.entity.MatierePremier;
import dao.entity.NombreCartonOffreVariante;
import dao.entity.OffreProduction;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.Promotion;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;


public class ModifierHeuresEmployeeProductionTerminer extends JLayeredPane implements Constantes{
	
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleFournisseur;
	private DefaultTableModel	 modeleEmploye;
	private DefaultTableModel	 modeleTableOffreVariante;
	private DefaultTableModel	 modeleEquipeEm;
	private DefaultTableModel	 modeleEquipeGen;
	private DefaultTableModel	 modeleCoutHorsProductionEnAttent;
	private JXTable table_1= new JXTable();
	private JXTable tableEmploye= new JXTable();
	private JXTable tableEmployeGen= new JXTable();
	private JTable tableOffreVariante= new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	private JButton btnTerminerOF;
	private JButton btnRechercher;
	private JTextField txtPrixServiceProd;
	private JTextField codeArticle;
	JComboBox comboBox = new JComboBox();
	private JComboBox<String> comboMachine;
	private JComboBox<String> comboLigneMachine;
	private JComboBox categorie;
	private List<Production> listProduction=new ArrayList<Production>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private List<Employe> listEmploye=new ArrayList<Employe>();
	private List<DetailTransferStockMP> listDetailTransfertMPRetour =new ArrayList<DetailTransferStockMP>();
	private List<DetailProduction> listDetailProduction =new ArrayList<DetailProduction>();
	private List<DetailProduction> listDetailProductionTMP=new ArrayList<DetailProduction>();
	List<DetailProdGen> listDetailProdGenTmp=new ArrayList<DetailProdGen>();
	private List<DetailProdGen> listeDetailProdGen=new ArrayList<DetailProdGen>();
	List<DetailProdRes> listDetailResponsableProdTmp=new ArrayList<DetailProdRes>();
	List<DetailProdRes> listDetailResponsableProdTmpVerification=new ArrayList<DetailProdRes>();
	List<DetailProdRes> listDetailProdResponsable=new ArrayList<DetailProdRes>();
	List<NombreCartonOffreVariante> listNombreCartonOfferVariante=new ArrayList<NombreCartonOffreVariante>();
	List<DetailNombreCartonOffreVariante> listDetailNombreCartonOfferVariante=new ArrayList<DetailNombreCartonOffreVariante>();
	 
	
	List< CoutHorsProdEnAttent> listCoutHorsProductionEnAttenteAfficher=new ArrayList< CoutHorsProdEnAttent>();
	private Production production = new Production();
	private Map< String, Production> mapProduction = new HashMap<>();
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapDelaiEmployeEmabalage = new HashMap<>();
	private Map< String, String> mapDelaiEmployeHorsProduction = new HashMap<>();
	private Map< String, Employe> mapMatriculeEmploye = new HashMap<>();
	private Map< String, Employe> mapNomEmploye = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeProd = new HashMap<>();
	private Map< String, String> mapHeureSupp25EmployeHorsProduction= new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeProd = new HashMap<>();
	private Map< String, String> mapHeureSupp50EmployeHorsProduction= new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeEmbalage = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeEmbalage = new HashMap<>();
	JCheckBox chckbxRetourDepot = new JCheckBox("Retour Depot");
	
	private Map< String, String> mapQuantiteDechet = new HashMap<>();
	private Map< String, String> mapQuantiteReste = new HashMap<>();
	private Map< String, String> mapQuantiteDechetFour = new HashMap<>();
	private Map< String, String> mapCodeFournisseurMP = new HashMap<>();
	private Map< String, String> mapCodeFournisseurDechet = new HashMap<>();
	private Map< String, String> mapQuantiteManquante = new HashMap<>();
	private Map< String, String> mapQuantiteManquanteFrPlus = new HashMap<>();
	private Map< String, String> mapQuantiteOffre = new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, CoutHorsProdEnAttent> mapDeatailCoutHorsProductionEnAttente = new HashMap<>();
	
	private JComboBox<String> comboEquipe;
	private JTextField txtQuantiteRealise;
	private JLabel lblQuantitRalise;
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalEmployeEmbalage=BigDecimal.ZERO;
	private BigDecimal coutTotalHorsProductionEnAttent=BigDecimal.ZERO;
	private BigDecimal coutTotalAutreEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	private BigDecimal coutTotalDechet=BigDecimal.ZERO;
	private JButton btnSaisieDelaiEquipeGen;
	private BigDecimal delaiTotal=BigDecimal.ZERO;
	private BigDecimal delaiTotalEquipeEmbalage;
	 private TransferStockMPDAO transferstockmpDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockPFDAO;
	private ProductionDAO productionDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private  EquipeDAO equipeDAO;
	private FactureProductionDAO factureProductionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private CompteStockMPDAO compteStockMPDAO;
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	 
	private String codeDepot;
	private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private DetailTransferMPDAO detailtransferMPDAO;
	private DepotDAO depotdao;
	private FournisseurMPDAO fournisseurMPDAO;
	private DetailProdResDAO detailProdResDAO;
	int position=-1;
	private PromotionDAO PromotionDAO;
	 JComboBox txtNumOF = new JComboBox();
	 private JTextField txtcodeemployeproduction;
	 private JTextField txtdelaiproduction;
	 private JTextField txthsupp50production;
	 private JTextField txthsupp25production;
	 private JTextField txtcodeemployegenerique;
	 private JTextField txtdelaigenerique;
	 private JTextField txthsupp50generique;
	 private JTextField txthsupp25generique;
	 private JTextField txtcodeemployeemballage;
	 private JTextField txtdelaiemballage;
	 private JTextField txthsupp50emballage;
	 private JTextField txthsupp25emballage;
	 private EmployeDAO employeDAO;
	  
	 private JCheckBox checkEnpaneproduction= new JCheckBox("En Panne");
	 JCheckBox checksortieproduction = new JCheckBox("Sortie");
	 JComboBox comboemployeproduction = new JComboBox();
	 JComboBox comboemployeemballage = new JComboBox();
	  JComboBox comboemployegenerique = new JComboBox();
	   
	  private JCheckBox checkEnPaneemballage= new JCheckBox("En Panne");
	     JCheckBox checksortieemballage = new JCheckBox("Sortie");
	     JCheckBox checkabsentgenerique = new JCheckBox("Absent");
		     JCheckBox checksortiegenerique = new JCheckBox("Sortie");
		     JCheckBox checkretardproduction = new JCheckBox("Retard");
		     JCheckBox checkretardemballage = new JCheckBox("Retard");
		     JCheckBox checkretardgenerique = new JCheckBox("Retard");
		     private int compteur=0;
		     private ManqueDechetFournisseurDAO manqueDechetFournisseurDAO;
		     private DetailManqueDechetFournisseurDAO detailManqueDechetFournisseurDAO;
		     private List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur=new ArrayList<DetailManqueDechetFournisseur>();
		     private List<DetailTransferStockMP> listDetailTransfertStockMP=new ArrayList<DetailTransferStockMP>();
		     private List<CoutMP> listCoutMPEnVrac =new ArrayList<CoutMP>();
		     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		     
		     private List <Object[]> Mindate=new ArrayList<Object[]>();
			 private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
				private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
				private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
				private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
				private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
				private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
				private List<EtatStockMP> listEtatStockMPAfficherMagasinStockage=new ArrayList<EtatStockMP>();
				//private List<EtatStockMP> listEtatStockMPAfficherMagasinProduction=new ArrayList<EtatStockMP>();

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
				private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteThe=new ArrayList<DetailTransferStockMP>();
				private List <DetailTransferStockMP> listeEtatStockTransfertEnAttenteNonThe=new ArrayList<DetailTransferStockMP>();
				private List <Object[]> listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=new ArrayList<Object[]>();
		     private Utilisateur utilisateur;
		     
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		     
		     boolean ValiderSaisi=false;
		     private ParametreDAO ParametreDAO;
		     private JXTable TableDetailCoutHorsProductionEnAttent;
		     private List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttent=new ArrayList<CoutHorsProdEnAttent>();
		     private List<StatistiqueFinanciaireMP> listStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		     private List<StatistiqueFinanciaireMP> listStatistiqueFinanciereMPTmp=new ArrayList<StatistiqueFinanciaireMP>();
		    
		 private CoutHorsProdEnAttentDAO CoutHorsProdEnAttentDAO;
		 private ParametreDAO parametreDAO;
		  private CoutMPDAO    CoutMPDAO;
			private DetailTransferMPDAO detailTransferStockMPDAO;
			SubCategorieMPDAO SubCategorieMPDAO;
			private JTextField txtQuantiteOffre =new JTextField();
			private JTextField txtQuantitePlus=new JTextField();
			private JTextField txtQuantiteMoins=new JTextField();
			JRadioButton radioMoins = new JRadioButton("Moins");
			JRadioButton radioPlus = new JRadioButton("Plus");
			  ButtonGroup group = new ButtonGroup();
			  DetailCoutHorsProdEnAttentDAO  detailCoutHorsProdEnAttentDAO;
			  private DetailEstimationDAO detailestimationDAO;
			  private List<DetailEstimation> lisDetailEstimation = new ArrayList<DetailEstimation>() ;
			  private Map< String, BigDecimal> mapQuantiteResterConsommer = new HashMap<>();  
			  private Map< String, Boolean> mapMPClientSaisir = new HashMap<>();  
			  String msgErreur="";
			  private List<SubCategorieMp> listSubCategorieMPClientNonSaisir = new ArrayList<SubCategorieMp>() ;
			  private List<String> listMPOffrePFMixte = new ArrayList<String>() ;
			  private List<String> listMPOffrePFMixteNonSaisir = new ArrayList<String>() ;
			  private Map< String, MatierePremier> mapMPOffrePFMixte = new HashMap<>();  
			  BigDecimal QuantiteTotalOffreMixtPFConsomme=BigDecimal.ZERO;
			  boolean OffrePFMixte=false;
			  private OffreProductionDAO offreProductionDAO;
			  private Map< String, BigDecimal> mapNombreCartonPourOffreVariante = new HashMap<>();
			  private Map< String, BigDecimal> mapGrammageOffreVariante = new HashMap<>();
			  private List <Object[]> SommeMontantTotalProductionPF=new ArrayList<Object[]>();
				 
			  
			  StatistiqueFinanciereMPDAO StatistiqueFinanciereMPDAO; 
			  private List<Production> listProductionsTerminer=new ArrayList<Production>();
			  private CoutArticlePFDAO coutArticlePFDAO;
			  private DetailCoutArticlePFDAO detailCoutArticlePFDAO;
			  private NombreCartonOfferVariantDAO nombreCartonOfferVariantDAO;
			  private DetailNombreCartonOfferVariantDAO detailNombreCartonOfferVariantDAO;
			  JButton btnNombreCartonPar = new JButton("Nombre Carton Par Offre");
			  JLabel labelTotalEquipeProduction = new JLabel("");
			  JLabel labelTotalEquipeEmballage = new JLabel("");
			  JLabel labelTotalEquipeGenerique = new JLabel("");
	////////////////////////////////////////////////////////////////////////////////// Connecter Au Base Production Raja     ////////////////////////////////////////////////////////////////////////////////////////////////////////
			 
			  private ResultSet rset;
				private Statement stx;
				
				private Connection con;	  
				 
			  
			  
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  
		boolean ErreurImporterHeure=false;	  
		private JTextField txtNombrePersonneTravailler;
		
		private BigDecimal TotalHeuresProductionEtEmballage=BigDecimal.ZERO;
	 
		private BigDecimal TotalEmployeeProductionEtEmballage=BigDecimal.ZERO;
		
		
			  
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public ModifierHeuresEmployeeProductionTerminer(Production productionP,String quantite, String nbreHeure) {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1924, 873);
        
        
        try{
        	utilisateur=AuthentificationView.utilisateur;
        	delaiTotalEquipeEmbalage=BigDecimal.ZERO;
        	delaiTotal=BigDecimal.ZERO;
        	coutTotalEmployeEmbalage=BigDecimal.ZERO;
        	coutTotalDechet=BigDecimal.ZERO;
        	coutTotalMP=BigDecimal.ZERO;
        	 employeDAO=new EmployeDAOImpl();
        	listCoutMP =new ArrayList<CoutMP>();
        	listEmploye=new ArrayList<Employe>();
        	listDetailProduction =new ArrayList<DetailProduction>();
        	listeDetailProdGen=new ArrayList<DetailProdGen>();
        	detailtransferMPDAO=new DetailTransferMPDAOImpl();
        	detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	mapDelaiEmploye = new HashMap<>();
        	mapDelaiEmployeEmabalage= new HashMap<>();
        //	mapQuantiteConsomme = new HashMap<>();
        	mapQuantiteDechet = new HashMap<>();
        	mapQuantiteReste = new HashMap<>();
        	mapQuantiteDechetFour= new HashMap<>();
        	mapCodeFournisseurMP= new HashMap<>();
        	mapQuantiteManquante= new HashMap<>();
        	mapQuantiteOffre= new HashMap<>();
        	mapQuantiteManquanteFrPlus=	new HashMap<>();
        	mapHeureSupp25EmployeEmbalage= new HashMap<>();
        	mapHeureSupp50EmployeEmbalage= new HashMap<>();
        	mapHeureSupp25EmployeProd= new HashMap<>();
        	mapHeureSupp50EmployeProd= new HashMap<>();
        	productionDAO=new ProductionDAOImpl();
        	detailProdGenDAO=new DetailProdGenDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	transferStockPFDAO= new TransferStockPFDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	ficheEmployeDAO= new FicheEmployeDAOImpl();
        	compteurResponsableProdDAO= new CompteurResponsableProdDAOImpl();
        	compteurEmployeProdDAO= new CompteurEmployeProdDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	factureProductionDAO= new FactureProductionDAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	compteStockMPDAO=new CompteStockMPDAOImpl();
        	detailProductionDAO=new DetailProductionDAOImpl();
        	detailResponsableDAO= new DetailResponsableProdDAOImpl();
        	txtQuantiteRealise=new JTextField();
        	util.Utils.copycoller(txtQuantiteRealise);
			 txtPrixServiceProd = new JTextField();
			 util.Utils.copycoller(txtPrixServiceProd);
			 codeArticle=new JTextField();
			 util.Utils.copycoller(codeArticle);
			 categorie= new JComboBox();
			 comboEquipe=new JComboBox<String>();
			 comboLigneMachine=new JComboBox<String>();
			 transferstockmpDAO= new TransferStockMPDAOImpl();
			 detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
			 depotdao=new DepotDAOImpl();
			 fournisseurMPDAO=new FournisseurMPDAOImpl();
			 comboMachine=new JComboBox<String>();
			 detailProdResDAO=new DetailProdResDAOImpl();
			 PromotionDAO=new PromotionDAOImpl();
			 manqueDechetFournisseurDAO=new ManqueDechetFournisseurDAOImpl();
			 detailManqueDechetFournisseurDAO=new DetailManqueDechetFournisseurDAOImpl();
			 ParametreDAO=new ParametreDAOImpl();
			 detailestimationDAO= new DetailEstimationDAOImpl();
			 offreProductionDAO=new OffreProductionDAOImpl();
			 txtNumOF = new JComboBox();
			  
			  txtNumOF.addItem("");
			 
			  listEmploye=employeDAO.findByDepot(AuthentificationView.utilisateur.getCodeDepot());
			  CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
			  parametreDAO=new ParametreDAOImpl();;
			  CoutMPDAO=new CoutMPDAOImpl();
			  SubCategorieMPDAO=new SubCategorieMPAOImpl();		
			  productionDAO.ViderSession();
			  CoutMPDAO.ViderSession();
			  detailCoutHorsProdEnAttentDAO=new DetailCoutHorsProdEnAttentDAOImpl();
			  StatistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
			  coutArticlePFDAO=new CoutArticlePFDAOImpl();
			  detailCoutArticlePFDAO=new DetailCoutArticlePFDAOImpl();
			  nombreCartonOfferVariantDAO=new NombreCartonOffreVariantDAOImpl();
			  detailNombreCartonOfferVariantDAO=new DetailNombreCartonOffreVariantDAOImpl();
	////////////////////////////////////////////////////////////////////////////////// Connecter Au Base Production Raja     ////////////////////////////////////////////////////////////////////////////////////////////////////////
				 			  
			  Connect.connecToProduction();
	    		con=Connect.getConnexion();
	        	stx=con.createStatement();
	        	 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		  
			  
			  txtNumOF.addMouseListener(new MouseAdapter() {
			  	@Override
			  	public void mouseEntered(MouseEvent arg0) {}
			  });
	  		    
			  if(AuthentificationView.utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_SIEGE))
			  {
				  listProduction=productionDAO.listeProductionEtatCreer(Constantes.ETAT_OF_LANCER,Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());
			  }else
			  {
				  listProduction=productionDAO.listeProductionEtatCreer(Constantes.ETAT_OF_LANCER,Constantes.ETAT_OF_TERMINER, AuthentificationView.utilisateur.getCodeDepot());  
			  }
			 
			
			
		
		
        	
        	listFournisseurMP=fournisseurMPDAO.findAll();
        	
       }catch(Exception exp){
       exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
		
      
        mapParametre=Utils.listeParametre();	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();
          }
        codeDepot= AuthentificationView.utilisateur.getCodeDepot();
				  		initialiserTableOffreVariante();
				  		  		 
				  		  		initialiserTableauEmploye();
				  		  		initialiserTableauEquipeEmbalage();
				  		  		initialiserTableauEmployeGen();
				  		    
				  		    btnTerminerOF = new JButton("Valider");
				  		    btnTerminerOF.setBounds(687, 821, 112, 24);
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
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir le Delai Production!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}else if(production.getListDetailProdGen()==null || production.getListDetailProdGen().size()<0){
				  		     			JOptionPane.showMessageDialog(null, "Il faut valider les équipes avant de terminer OF!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}
				  		     		else {
				  		     		if(production.getStatut().equals(Constantes.ETAT_OF_TERMINER )) {
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			QuantiteTotalOffreMixtPFConsomme=BigDecimal.ZERO;
				  		     			
				  		     			//ficheEmployeDAO.viderSession();
				  		     		 
				  		     				
				  		     				
		//////////////////////////////////////////////////////////////////////////////////////// Comparer Le Nombre Personnes Travailler Avec le Nombre Declarer  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		     				
				  		     				
				  		     				  TotalHeuresProductionEtEmballage=BigDecimal.ZERO;
				  		     				 
				  		     				TotalEmployeeProductionEtEmballage=BigDecimal.ZERO;
				  		     				VerifierLesHeuresEmballageEtProduction();
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				if(txtNombrePersonneTravailler.getText().equals(""))
				  		     				{
				  		     					JOptionPane.showMessageDialog(null, "Veuillez Saisir Le Nombre Des Employees Travaillons Dans L'OF SVP");
				  		     					return;
				  		     				}
				  		     				
				  		     				
				  		     				try {
				  		     					
				  		     					if(TotalEmployeeProductionEtEmballage.compareTo(new BigDecimal(txtNombrePersonneTravailler.getText()))!=0)
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "Le Nombre Des Employees Entrer et different aux Employee Declarant ","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     					return;
				  		     					}
				  		     					
				  		     					
				  		     					if(TotalHeuresProductionEtEmballage .compareTo(new BigDecimal(txtNombrePersonneTravailler.getText()).multiply(new BigDecimal(txtPrixServiceProd.getText())))!=0)
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "Le Nombre Des heures des Employees est Incorrecte !!!!! ","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     					return;
				  		     					}
				  		     					
												
											} catch (NumberFormatException e2) {
												JOptionPane.showMessageDialog(null, "Veuillez Saisir Le Nombre Des Employees Travaillons En Chiffre SVP");
				  		     					return;
											}
				  		     				
				  		     				
				  		     				
				  		     				
				  		     				
		////////////////////////////////////////////////////////////////////////////////////  Verifier Les Heures Employee Avec Delai De Production   //////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		     				
				  		     				boolean erreurHeureEmployee=false;
				  		     				for(int t=0;t<listDetailProductionTMP.size();t++)
				  		     				{
				  		     					
				  		     					 
				  		     					if(listDetailProductionTMP.get(t).getDelaiEmploye().compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
				  		     					{
				  		     						
				  		     						erreurHeureEmployee=true;	
				  		     						
				  		     						
				  		     						
				  		     					}
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     				
				  		     				for(int t=0;t<listDetailProdGenTmp.size();t++)
				  		     				{
				  		     					
				  		     					 
				  		     					if(listDetailProdGenTmp.get(t).getDelaiEmploye().compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
				  		     					{
				  		     						
				  		     						erreurHeureEmployee=true;	
				  		     						
				  		     						
				  		     						
				  		     					}
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				if(erreurHeureEmployee==true)
				  		     				{
				  		     					JOptionPane.showMessageDialog(null, "Les Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
					  		     				return;
				  		     				}
				  		     				
				  		     				
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				  		     				
				  		     				
				  		     				
				 
				  		     				
				  		     				
				  		     				
				  		     				listCoutHorsProductionEnAttent.clear();
				  		     				Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
				  		     				 
				  		     				
				  		     		 		/* délai des employés Hors Production*/
				  		     				
				  		     				boolean ErreurTotalHeursHorsproductionEnAttente=false;
				  		     				String EmployeMatriculeErreur="";
						  		     		for(int j=0;j<TableDetailCoutHorsProductionEnAttent.getRowCount();j++){
						  		     			boolean selected=Boolean.valueOf(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 7).toString()) ;
						  		     			
						  		     			if(selected==true)
						  		     			{
						  		     				if(!TableDetailCoutHorsProductionEnAttent.getValueAt(j, 4).toString().equals("")){
								  		     			mapDelaiEmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), TableDetailCoutHorsProductionEnAttent.getValueAt(j, 4).toString());
								  		     			//delaiTotalEqu=delaiTotalEquipeEmbalage.add(new BigDecimal(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 3).toString())) ;
								  		     			}
								  		     			else 
								  		     				mapDelaiEmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), String.valueOf(0));
								  		     			
								  		     			if(!TableDetailCoutHorsProductionEnAttent.getValueAt(j, 5).toString().equals("")){
								  		     				mapHeureSupp25EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), TableDetailCoutHorsProductionEnAttent.getValueAt(j, 5).toString());
								  		     			}else 
								  		     				mapHeureSupp25EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), String.valueOf(0));
							  		     			
							  		     			if(!TableDetailCoutHorsProductionEnAttent.getValueAt(j, 6).toString().equals("")){
							  		     				mapHeureSupp50EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), TableDetailCoutHorsProductionEnAttent.getValueAt(j, 6).toString());
							  		     			}else 
							  		     				mapHeureSupp50EmployeHorsProduction.put(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString(), String.valueOf(0));
								  		     			
								  		     		
							  		     			
							  		     			
							  		     			
							  		     			Employe employe=mapMatriculeEmploye.get(TableDetailCoutHorsProductionEnAttent.getValueAt(j, 2).toString());
							  		     			
							  		     			
							  		     			
							  		     			
							  		     			
							  		     			if(employe!=null)
							  		     			{
							  		     				
							  		     				CoutHorsProdEnAttent coutHorsProdEnAttent=mapDeatailCoutHorsProductionEnAttente.get(employe.getMatricule()+TableDetailCoutHorsProductionEnAttent.getValueAt(j, 0).toString());
							  		     				
							  		     	 if(coutHorsProdEnAttent!=null)
							  		     	 {
							  		     		 
							  		     		 
							  		     		 
							  		     			coutHorsProdEnAttent.setEtat(ETAT_VALIDER);
							  		     			coutHorsProdEnAttent.setProduction(production);
							  		     			CoutHorsProdEnAttentDAO.edit(coutHorsProdEnAttent);
							  		     		 BigDecimal remise=BigDecimal.ZERO;
							  		     			if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							  		     			{
							  		     				remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							  		     			}
														
							  		     			else if(coutHorsProdEnAttent.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							  		     			{
							  		     				remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
							  		     			}
														
													
													 remise=(remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP)).multiply(coutHorsProdEnAttent.getDelaiEmploye());	
							  		     		
							  		     		coutTotalHorsProductionEnAttent=coutTotalHorsProductionEnAttent.add(coutHorsProdEnAttent.getDelaiEmploye().multiply(coutHorsProdEnAttent.getCoutHoraire()).add(coutHorsProdEnAttent.getHeure25().multiply(coutHorsProdEnAttent.getCoutHoraire25())).add(coutHorsProdEnAttent.getHeure50().multiply(coutHorsProdEnAttent.getCoutHoraire50())).add(remise));	
							  		     		
							  		     		 
							  		     	 }
							  		     			
							  		     			}
							  		     			
						  		     			}
						  		     			
						  		     
					  		     			
					  		     			
						  		     		
						  		     		}
						  		     		
						  				  		     			
				  		     	 production.setNbrePersonneTravailler(new BigDecimal(txtNombrePersonneTravailler.getText()));
				  		     		production.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		
				  		     		/* délai des employés Production*/
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
				  		     		
				  		     		/* délai des employés Emabalege*/
				  		     		for(int j=0;j<table_1.getRowCount();j++){
				  		     			if(!table_1.getValueAt(j, 4).toString().equals("")){
				  		     			mapDelaiEmployeEmabalage.put(Integer.parseInt(table_1.getValueAt(j, 1).toString()), table_1.getValueAt(j, 4).toString());
				  		     			delaiTotalEquipeEmbalage=delaiTotalEquipeEmbalage.add(new BigDecimal(table_1.getValueAt(j, 4).toString())) ;
				  		     			}
				  		     			else 
				  		     				mapDelaiEmployeEmabalage.put(Integer.parseInt(table_1.getValueAt(j, 1).toString()), String.valueOf(0));
				  		     			
				  		     			if(!table_1.getValueAt(j, 5).toString().equals("")){
				  		     				mapHeureSupp25EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 1).toString()), table_1.getValueAt(j, 5).toString());
				  		     			}else 
				  		     				mapHeureSupp25EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 1).toString()), String.valueOf(0));
			  		     			
			  		     			if(!table_1.getValueAt(j, 6).toString().equals("")){
			  		     				mapHeureSupp50EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 1).toString()), table_1.getValueAt(j, 6).toString());
			  		     			}else 
			  		     				mapHeureSupp50EmployeEmbalage.put(Integer.parseInt(table_1.getValueAt(j, 1).toString()), String.valueOf(0));
				  		     			}
				  		     		
 				  		     		listeDetailProdGen=productionDAO.listeDetailProdGen(production.getId());
				  		     		listDetailProduction=productionDAO.listeDetailProduction(production.getId());
				  		     		
				  		     		
				  		     		production.setDetailProductions(calculeCoutEmploye(listDetailProduction,mapDelaiEmploye));
				  		     		production.setListDetailProdGen(calculeCoutEmployeEmbalage(listeDetailProdGen,mapDelaiEmployeEmabalage));



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	     		
				  		     		
				  		     		
				  		     		//production.setListDetailResponsableProd(listDetailResponsableProd);
				  		     		production.setCoutTotalHorsProductionEnAttent(coutTotalHorsProductionEnAttent);
				  		     		production.setCoutTotalEmployeGen(coutTotalAutreEmploye);
				  		     		production.setCoutTotalEmployeEmbalage( coutTotalEmployeEmbalage);
				  		     		production.setCoutTotalEmploye(coutTotalEmploye);
				  		     		production.setCoutTotal(production.getCoutDechet().add(production.getCoutTotalMP().add(production.getCoutTotalEmploye().add(production.getCoutTotalEmployeEmbalage().add(production.getCoutTotalEmployeGen().add(production.getCoutTotalHorsProductionEnAttent()))))));
 
				  		     		productionDAO.edit(production);
				  		     		
	  		     		
				  		 
					  					
					  					calculRemiseResponsableProduction(production.getDate(), production.getPeriode());
					  										  			  
					  				
					  				calculerStockCoutProduitFini();
					  				
					  				
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
					  			
					  		 
		 listStatistiqueFinanciereMPTmp=StatistiqueFinanciereMPDAO.listeStatistiqueFinanciereMPLimitByCodeTransfertByEtaTransfert(production.getNumOF(), Constantes.ETAT_OF_TERMINER);	
		 BigDecimal differenceCoutEmployee=BigDecimal.ZERO;
		 if(listStatistiqueFinanciereMPTmp.size()!=0)
		 {
			 
			 differenceCoutEmployee= listStatistiqueFinanciereMPTmp.get(listStatistiqueFinanciereMPTmp.size()-1).getCoutEmployeeProduction().subtract(listStatistiqueFinanciereMPTmp.get(listStatistiqueFinanciereMPTmp.size()-2).getCoutEmployeeProduction());
			 
			 
			 
		 }
		 
		 
		 
	    listStatistiqueFinanciereMP=StatistiqueFinanciereMPDAO.findAll();
	    StatistiqueFinanciaireMP statistiqueFinanciaireMPTmp=listStatistiqueFinanciereMP.get(listStatistiqueFinanciereMP.size()-1);
				  		      
				  		     		
				  		     		
				  		     		StatistiqueFinanciaireMP statistiqueFinanciaireMP=new StatistiqueFinanciaireMP();
			  		     			
			  		     			statistiqueFinanciaireMP.setAlimentation(statistiqueFinanciaireMPTmp.getAlimentation());
			  		     			statistiqueFinanciaireMP.setStockEmballage(statistiqueFinanciaireMPTmp.getStockEmballage());
			  		     			statistiqueFinanciaireMP.setStockEnVrac(statistiqueFinanciaireMPTmp.getStockEnVrac());
			  		     			statistiqueFinanciaireMP.setCoutProduction(statistiqueFinanciaireMPTmp.getCoutProduction());
			  		     			statistiqueFinanciaireMP.setCodeTransfer(production.getNumOF());
			  		     			statistiqueFinanciaireMP.setDate(new Date());
			  		     			statistiqueFinanciaireMP.setDateOperation(production.getDate());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeCarton(statistiqueFinanciaireMPTmp.getCoutEmployeeCarton());
			  		     			statistiqueFinanciaireMP.setCoutEmployeeProduction(statistiqueFinanciaireMPTmp.getCoutEmployeeProduction().subtract(differenceCoutEmployee).add(production.getCoutTotalEmployeGen().add(coutTotalEmploye).add(coutTotalEmployeEmbalage).add(coutTotalHorsProductionEnAttent)));
			  		     			statistiqueFinanciaireMP.setCOUTEntre(statistiqueFinanciaireMPTmp.getCOUTEntre());
			  		     			statistiqueFinanciaireMP.setCoutFabricationCarton(statistiqueFinanciaireMPTmp.getCoutFabricationCarton());
			  		     			statistiqueFinanciaireMP.setCOUTPF(MontantTotalPF);
			  		     			statistiqueFinanciaireMP.setCoutProductionCarton(statistiqueFinanciaireMPTmp.getCoutProductionCarton());
			  		     			statistiqueFinanciaireMP.setCoutReception(statistiqueFinanciaireMPTmp.getCoutReception());
			  		     			statistiqueFinanciaireMP.setCoutSortie(statistiqueFinanciaireMPTmp.getCoutSortie());
			  		     			statistiqueFinanciaireMP.setCoutTransfertMPPF(statistiqueFinanciaireMPTmp.getCoutTransfertMPPF());
			  		     			statistiqueFinanciaireMP.setCoutVente(statistiqueFinanciaireMPTmp.getCoutVente());
			  		     			statistiqueFinanciaireMP.setCoutRetour(statistiqueFinanciaireMPTmp.getCoutRetour());
			  		     			statistiqueFinanciaireMP.setEtat(Constantes.ETAT_OF_TERMINER+" "+Constantes.PROD_PF+" "+Constantes.ETAT_MODIFIER);
			  		     			statistiqueFinanciaireMP.setEtatTransfer(Constantes.ETAT_MODIFIER);
			  		     			StatistiqueFinanciereMPDAO.add(statistiqueFinanciaireMP);
				  		     		
				  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication Modifier avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     	
									 
									
				  		     			 
				  		     		
				  		     		}else{
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication n'est pas encore lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
				  		     	  }
				  		     	 }
				  		     	}
				  		     });
				  		    	btnTerminerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table_1.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		if(table_1.getSelectedRow()!=-1)
				  		     		{

				  						 
				  		     			DetailProdGen DetailProdGen= detailProdGenDAO.findById(Integer.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 0).toString()) );	
				  						txtcodeemployeemballage.setText(DetailProdGen.getEmploye().getMatricule());
				  						comboemployeemballage.setSelectedItem(DetailProdGen.getEmploye().getNomafficher());
				  						txtdelaiemballage.setText(DetailProdGen.getDelaiEmploye()+"");
				  						txthsupp25emballage.setText(DetailProdGen.getHeureSupp25()+"");
				  						txthsupp50emballage.setText(DetailProdGen.getHeureSupp50()+"");
				  						checkEnPaneemballage.setSelected(DetailProdGen.isPanne());
				  						checksortieemballage.setSelected(DetailProdGen.isSortie());
				  						checkretardemballage.setSelected(DetailProdGen.isRetard());
				  						 
				  					
				  		     		}
				  		     	}
				  		     });
				  		     
				  		     table_1.setShowVerticalLines(true);
				  		     table_1.setSelectionBackground(new Color(51, 204, 255));
				  		     table_1.setRowHeightEnabled(true);
				  		     table_1.setBackground(new Color(255, 255, 255));
				  		     table_1.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     table_1.setColumnControlVisible(true);
				  		     table_1.setForeground(Color.BLACK);
				  		     table_1.setGridColor(new Color(0, 0, 255));
				  		     table_1.setAutoCreateRowSorter(true);
				  		     table_1.setBounds(2, 27, 411, 198);
				  		     table_1.setRowHeight(20);
				  		
		
		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 7, 89, 24);
		add(lblNumOF);
		 tableEmploye.setColumnSelectionAllowed(false);
		tableEmploye.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(tableEmploye.getSelectedRow()!=-1)
				{
					 
					DetailProduction detailProduction= detailProductionDAO.findById(Integer.valueOf(tableEmploye.getValueAt(tableEmploye.getSelectedRow(), 0).toString()) );	
					txtcodeemployeproduction.setText(detailProduction.getEmploye().getMatricule());
					comboemployeproduction.setSelectedItem(detailProduction.getEmploye().getNomafficher());
					txtdelaiproduction.setText(detailProduction.getDelaiEmploye()+"");
					txthsupp25production.setText(detailProduction.getHeureSupp25()+"");
					txthsupp50production.setText(detailProduction.getHeureSupp50()+"");
					checkEnpaneproduction.setSelected(detailProduction.isPanne());
					checksortieproduction.setSelected(detailProduction.isSortie());
					checkretardproduction.setSelected(detailProduction.isRetard());
					 
				}
 				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane(tableEmploye);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(7, 142, 877, 401);
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
		tableEmploye.setColumnSelectionAllowed(false);
	//	scrollPane_1.setViewportView(tableEmploye);
		 DefaultCellEditor ce2 = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
	        JTextComponent textField2 = (JTextComponent) ce2.getComponent();
	        util.Utils.copycollercell(textField2);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Saisir D\u00E9lai Equipe Production");
		titledSeparator_1.setBounds(9, 74, 859, 17);
		add(titledSeparator_1);
				  		    		  		     	
				  		    		  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		    		  		     	layeredPane.setBounds(9, 34, 1403, 29);
				  		    		  		     	add(layeredPane);
				  		    		  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		    
				  		  
				  		    txtQuantiteRealise.setBounds(345, 6, 120, 26);
				  		    add(txtQuantiteRealise);
				  		    txtQuantiteRealise.setColumns(10);
				  		    
				  		    lblQuantitRalise = new JLabel("Quantit\u00E9 r\u00E9alis\u00E9e:");
				  		    lblQuantitRalise.setBounds(242, 6, 102, 26);
				  		    add(lblQuantitRalise);
				  		    lblQuantitRalise.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    JScrollPane scrollPane_2 = new JScrollPane(table_1);
				  		    scrollPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		    scrollPane_2.setBounds(963, 142, 887, 388);
				  		    add(scrollPane_2);
				  		    
				  		    JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
				  		    GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
				  		    gridBagLayout_1.rowWeights = new double[]{0.0};
				  		    gridBagLayout_1.rowHeights = new int[]{0};
				  		    gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		    gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
				  		    titledSeparator_2.setTitle("Saisir D\u00E9lai Equipe Embalage");
				  		    titledSeparator_2.setBounds(961, 74, 932, 17);
				  		    add(titledSeparator_2);
				  		    
				  		   
				  		    txtPrixServiceProd.setBounds(561, 6, 77, 26);
				  		    add(txtPrixServiceProd);
				  		    txtPrixServiceProd.setColumns(10);
				  		    
				  		    JLabel lblQuantite = new JLabel("Delai Production :");
				  		    lblQuantite.setBounds(475, 6, 96, 26);
				  		    add(lblQuantite);
				  		    lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    comboEquipe.setBounds(1065, 36, 157, 24);
				  		    add(comboEquipe);
				  		    
				  		    JLabel lblEquipe = new JLabel("Equipe");
				  		    lblEquipe.setBounds(1020, 35, 51, 26);
				  		    add(lblEquipe);
				  		   
				  		    comboLigneMachine.setBounds(853, 36, 157, 24);
				  		    add(comboLigneMachine);
				  		    
				  		   JLabel lblGroupe = new JLabel("Ligne Machine");
				  		   lblGroupe.setBounds(764, 35, 77, 24);
				  		   add(lblGroupe);
				  		   lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   comboMachine.setBounds(597, 36, 157, 24);
				  		   add(comboMachine);
				  		   
				  		   JLabel lblMachine = new JLabel("Machine");
				  		   lblMachine.setBounds(538, 35, 58, 24);
				  		   add(lblMachine);
				  		   lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  
				  		   categorie.setBounds(319, 35, 194, 26);
				  		   add(categorie);
				  		   
				  		   categorie.setForeground(Color.BLACK);
				  		   categorie.setBackground(Color.WHITE);
				  		   
				  		   JLabel label = new JLabel("Article:");
				  		   label.setBounds(262, 34, 102, 26);
				  		   add(label);
				  		   label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   codeArticle.setBounds(83, 35, 153, 26);
				  		   add(codeArticle);
				  		   
				  		   codeArticle.setDisabledTextColor(Color.BLACK);
				  		   codeArticle.setBackground(Color.WHITE);
				  		   codeArticle.setEnabled(false);
				  		   codeArticle.setColumns(10);
				  		   
				  		     JLabel lblCodeArticle = new JLabel("Code Article");
				  		     lblCodeArticle.setBounds(9, 35, 82, 26);
				  		     add(lblCodeArticle);
				  		     lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     
				  		     JScrollPane scrollPane_3 = new JScrollPane(tableEmployeGen);
				  		     
				  		     scrollPane_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane_3.setBounds(970, 641, 887, 169);
				  		     add(scrollPane_3);
				  		   
				  		     tableEmployeGen.setHighlighters(HighlighterFactory.createSimpleStriping());
				  		     tableEmployeGen.setShowVerticalLines(true);
				  		     tableEmployeGen.setSelectionBackground(new Color(51, 204, 255));
				  		     tableEmployeGen.setRowHeightEnabled(true);
				  		     tableEmployeGen.setRowHeight(20);
				  		     tableEmployeGen.setGridColor(new Color(0, 0, 255));
				  		     tableEmployeGen.setForeground(Color.BLACK);
				  		     tableEmployeGen.setColumnControlVisible(true);
				  		     tableEmployeGen.setBackground(new Color(255, 255, 255));
				  		     tableEmployeGen.setAutoCreateRowSorter(true);
				  		     
				  		     
				  		     JButton supprimerEquipeProduction = new JButton("");
				  		     supprimerEquipeProduction.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     	
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
								 
							
				  		     		if(listDetailProductionTMP.size()!=0)
				  		     		{
				  		     			if(tableEmploye.getSelectedRow()!=-1)
					  		     		{
				  		     				DetailProduction detailProduction= detailProductionDAO.findById(Integer.valueOf(tableEmploye.getValueAt(tableEmploye.getSelectedRow(), 0).toString()) );;
					  		     			
					  		     			
					  		     			Production productionTmp=productionDAO.findByNumOFStatut(detailProduction.getProduction().getNumOF(), Constantes.ETAT_OF_TERMINER);
					  		     			if(productionTmp!=null)
					  		     			{
					  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
												{
													 
													 
													
												  detailProductionDAO.delete(detailProduction);
												  
												  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
												 for(int y=0;y<listDetailProductionTMP.size();y++)
												 {
													 if(listDetailProductionTMP.get(y).getId()==detailProduction.getId())
													 {
														  listDetailProductionTMP.remove(y);
													 }
												 }
												
												  productionTmp.setDetailProductions(listDetailProductionTMP);
												  productionDAO.edit(productionTmp);
													
												  afficher_tableEmploye(listDetailProductionTMP);
													
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
				  		     supprimerEquipeProduction.setBounds(894, 247, 58, 23);
				  		   supprimerEquipeProduction.setIcon(imgSupprimer);
				  		     add(supprimerEquipeProduction);
				  		     
				  		     JButton supprimeremployeempllage = new JButton("");
				  		     supprimeremployeempllage.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
								 
							
				  		     		
				  		     		if(listDetailProdGenTmp.size()!=0)
				  		     		{
				  		     			if(table_1.getSelectedRow()!=-1)
					  		     		{
				  		     				
				  		     				DetailProdGen detailProdGen= detailProdGenDAO.findById(Integer.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 0).toString()) );
					  		     			 
					  		     			
					  		     			Production productionTmp=productionDAO.findByNumOFStatut(detailProdGen.getProductionGen().getNumOF(), Constantes.ETAT_OF_TERMINER);
					  		     			if(productionTmp!=null)
					  		     			{
					  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
														"Satisfaction", JOptionPane.YES_NO_OPTION);
												 
												if(reponse == JOptionPane.YES_OPTION )
												{
													
													detailProdGenDAO.delete(detailProdGen);
												  
												  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
												 for(int y=0;y<listDetailProdGenTmp.size();y++)
												 {
													 if(listDetailProdGenTmp.get(y).getId()==detailProdGen.getId())
													 {
														 
														 listDetailProdGenTmp.remove(y);
													 }
												 }
												  
												  productionTmp.setListDetailProdGen(listDetailProdGenTmp);
												  productionDAO.edit(productionTmp);
													
												  afficher_tableEmployeEmabalage(listDetailProdGenTmp);
													
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
				  		     supprimeremployeempllage.setBounds(1860, 258, 58, 23);
				  		   supprimeremployeempllage.setIcon(imgSupprimer);
				  		     add(supprimeremployeempllage);
				  		     
				  		     JButton supprimeremployergen = new JButton("");
				  		     supprimeremployergen.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		

				  		     		
				  		     		
				  		     		if(listDetailResponsableProdTmp.size()!=0)
				  		     		{
				  		     			
				  		     			
				  		     			// int numberproduction=productionDAO.NombreProductionTerminerParDate(production.getDate(), Constantes.ETAT_OF_TERMINER)+1;
				  		     		Production production=	productionDAO.findByNumOFStatut(txtNumOF.getSelectedItem().toString(), Constantes.ETAT_OF_TERMINER);
						  				if(production==null)
						  				{
						  				
						  					if(tableEmployeGen.getSelectedRow()!=-1)
						  		     		{
						  		     			DetailProdRes detailResponsableProd=listDetailResponsableProdTmp.get(tableEmployeGen.getSelectedRow());
					  		     				
						  		     			
						  		     		
						  		     			  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Supprimer l'employer ?", 
															"Satisfaction", JOptionPane.YES_NO_OPTION);
													 
													if(reponse == JOptionPane.YES_OPTION )
													{
														
														
													  
													  JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ","Satisfaction",JOptionPane.INFORMATION_MESSAGE);
													  detailProdResDAO.delete(detailResponsableProd.getId());
													  listDetailResponsableProdTmp.remove(tableEmployeGen.getSelectedRow());
													 // productionTmp.setListDetailResponsableProd(listDetailResponsableProdTmp);
													 // productionDAO.edit(productionTmp);
														
													  afficher_tableEmployeGen(listDetailResponsableProdTmp);
														
													}
						  		     				
						  		     		}
						  					
						  				}else
						  				{
						  					
						  					JOptionPane.showMessageDialog(null, "Impossible de supprimer employé dont le OF est terminé !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
				  		     				return;
						  					
						  				}
				  		     			
				  		     			
				       			
				  		     		
					/*
					 * if(tableEmployeGen.getSelectedRow()!=-1) { DetailResponsableProd
					 * detailResponsableProd=production.getListDetailResponsableProd().get(
					 * tableEmployeGen.getSelectedRow());
					 * 
					 * 
					 * Production
					 * productionTmp=productionDAO.findByNumOFStatut(detailResponsableProd.
					 * getProduction().getNumOF(), Constantes.ETAT_OF_LANCER);
					 * if(productionTmp!=null) { int reponse = JOptionPane.showConfirmDialog(null,
					 * "Vous voulez vraiment Supprimer l'employer ?", "Satisfaction",
					 * JOptionPane.YES_NO_OPTION);
					 * 
					 * if(reponse == JOptionPane.YES_OPTION ) {
					 * 
					 * detailResponsableDAO.delete(detailResponsableProd.getId());
					 * 
					 * JOptionPane.showMessageDialog(null,
					 * "Employé supprimer avec succée ","Satisfaction",JOptionPane.
					 * INFORMATION_MESSAGE);
					 * detailProdResDAO.delete(listDetailResponsableProdTmp.get(tableEmployeGen.
					 * getSelectedRow()).getId());
					 *listDetailResponsableProdTmp.remove(tableEmployeGen.getSelectedRow()); //
					 * productionTmp.setListDetailResponsableProd(listDetailResponsableProdTmp); //
					 * productionDAO.edit(productionTmp);
					 * 
					 * afficher_tableEmployeGen(listDetailResponsableProdTmp);
					 * 
					 * }
					 * 
					 * 
					 * }else { JOptionPane.showMessageDialog(null,
					 * "Impossible de supprimer employé dont le OF est terminé !!!!","Erreur",
					 * JOptionPane.ERROR_MESSAGE ); return; }
					 * 
					 * 
					 * 
					 * }
					 */
				  		     				  		     			
				  		     		}else
				  		     		{
				  		     			JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!","Erreur",JOptionPane.ERROR_MESSAGE );
			  		     				return;
				  		     		}
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     		
				  		     	
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     supprimeremployergen.setBounds(1867, 725, 58, 23);
				  		   supprimeremployergen.setIcon(imgSupprimer);
				  		     add(supprimeremployergen);
				  		   supprimeremployergen.setVisible(false);
				  		     
				  		     JXTitledSeparator titledSeparator_3 = new JXTitledSeparator();
				  		     GridBagLayout gridBagLayout_2 = (GridBagLayout) titledSeparator_3.getLayout();
				  		     gridBagLayout_2.rowWeights = new double[]{0.0};
				  		     gridBagLayout_2.rowHeights = new int[]{0};
				  		     gridBagLayout_2.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     gridBagLayout_2.columnWidths = new int[]{0, 0, 0};
				  		     titledSeparator_3.setTitle("Saisir D\u00E9lai Equipe Generique");
				  		     titledSeparator_3.setBounds(963, 569, 803, 17);
				  		     add(titledSeparator_3);
				  		     
				  		     txtcodeemployeproduction = new JTextField();
				  		     txtcodeemployeproduction.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		

				  					
				  					if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployeproduction.getText());
				  						if(employe!=null)
				  						{
				  							comboemployeproduction.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  				
				  					
				  					
				  					
				  					
				  				
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     txtcodeemployeproduction.setColumns(10);
				  		     txtcodeemployeproduction.setBounds(36, 105, 71, 26);
				  		     add(txtcodeemployeproduction);
				  		     
				  		     JLabel Code = new JLabel("Code :");
				  		     Code.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     Code.setBounds(0, 102, 37, 26);
				  		     add(Code);
				  		     
				  		     JLabel lblEmploye = new JLabel("Employe :");
				  		     lblEmploye.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblEmploye.setBounds(117, 105, 64, 26);
				  		     add(lblEmploye);
				  		     
				  		     txtdelaiproduction = new JTextField();
				  		     txtdelaiproduction.setColumns(10);
				  		     txtdelaiproduction.setBounds(381, 111, 58, 26);
				  		     add(txtdelaiproduction);
				  		     
				  		     JLabel lblDelaiH = new JLabel("Delai H :");
				  		     lblDelaiH.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblDelaiH.setBounds(332, 112, 61, 26);
				  		     add(lblDelaiH);
				  		     
				  		     txthsupp50production = new JTextField();
				  		     txthsupp50production.setColumns(10);
				  		     txthsupp50production.setBounds(517, 112, 58, 26);
				  		     add(txthsupp50production);
				  		     
				  		     JLabel lblHSupp = new JLabel("H Supp 50% :");
				  		     lblHSupp.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblHSupp.setBounds(445, 112, 77, 26);
				  		     add(lblHSupp);
				  		     
				  		     JLabel lblHSupp_1 = new JLabel("H Supp 25% :");
				  		     lblHSupp_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     lblHSupp_1.setBounds(585, 112, 77, 26);
				  		     add(lblHSupp_1);
				  		     
				  		     txthsupp25production = new JTextField();
				  		     txthsupp25production.setColumns(10);
				  		     txthsupp25production.setBounds(659, 111, 58, 26);
				  		     add(txthsupp25production);
				  		     
				  		      checkEnpaneproduction = new JCheckBox("En Panne");
				  		     checkEnpaneproduction.setBounds(723, 112, 76, 24);
				  		     add(checkEnpaneproduction);
				  		   checkEnpaneproduction.setVisible(true);
				  		      checksortieproduction = new JCheckBox("Sortie");
				  		     checksortieproduction.setBounds(801, 112, 75, 23);
				  		     add(checksortieproduction);
				  		     
				  		     JLabel label_1 = new JLabel("Code :");
				  		     label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_1.setBounds(963, 597, 37, 26);
				  		     add(label_1);
				  		     
				  		     txtcodeemployegenerique = new JTextField();
				  		     txtcodeemployegenerique.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		
				  		     		if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployegenerique.getText());
				  						if(employe!=null)
				  						{
				  							comboemployegenerique.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     txtcodeemployegenerique.setColumns(10);
				  		     txtcodeemployegenerique.setBounds(1000, 597, 77, 26);
				  		     add(txtcodeemployegenerique);
				  		     
				  		     JLabel label_2 = new JLabel("Employe :");
				  		     label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_2.setBounds(1087, 598, 61, 26);
				  		     add(label_2);
				  		     
				  		     JLabel label_3 = new JLabel("Delai H :");
				  		     label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_3.setBounds(1312, 597, 61, 26);
				  		     add(label_3);
				  		     
				  		     txtdelaigenerique = new JTextField();
				  		     txtdelaigenerique.setColumns(10);
				  		     txtdelaigenerique.setBounds(1361, 600, 58, 26);
				  		     add(txtdelaigenerique);
				  		     
				  		     JLabel label_4 = new JLabel("H Supp 50% :");
				  		     label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_4.setBounds(1425, 597, 77, 26);
				  		     add(label_4);
				  		     
				  		     txthsupp50generique = new JTextField();
				  		     txthsupp50generique.setColumns(10);
				  		     txthsupp50generique.setBounds(1498, 597, 58, 26);
				  		     add(txthsupp50generique);
				  		     
				  		     JLabel label_5 = new JLabel("H Supp 25% :");
				  		     label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_5.setBounds(1566, 598, 77, 26);
				  		     add(label_5);
				  		     
				  		     txthsupp25generique = new JTextField();
				  		     txthsupp25generique.setColumns(10);
				  		     txthsupp25generique.setBounds(1639, 600, 58, 26);
				  		     add(txthsupp25generique);
				  		     
				  		      checkabsentgenerique = new JCheckBox("Absent");
				  		     checkabsentgenerique.setBounds(1702, 603, 71, 23);
				  		     add(checkabsentgenerique);
				  		   checkabsentgenerique.setVisible(false);
				  		      checksortiegenerique = new JCheckBox("Sortie");
				  		     checksortiegenerique.setBounds(1774, 603, 71, 23);
				  		     add(checksortiegenerique);
				  		     
				  		     JLabel label_6 = new JLabel("Code :");
				  		     label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_6.setBounds(963, 108, 37, 26);
				  		     add(label_6);
				  		     
				  		     txtcodeemployeemballage = new JTextField();
				  		     txtcodeemployeemballage.addKeyListener(new KeyAdapter() {
				  		     	@Override
				  		     	public void keyPressed(KeyEvent e) {
				  		     		
				  		     		
				  					if(e.getKeyCode()==e.VK_ENTER)
				  		      		{
				  						Employe employe=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
				  						if(employe!=null)
				  						{
				  							comboemployeemballage.setSelectedItem(employe.getNomafficher());
				  						}else
				  						{
				  							JOptionPane.showMessageDialog(null, "Employe Introuvable !!!!!");
				  							return;
				  						}
				  						
				  		      		}
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     txtcodeemployeemballage.setColumns(10);
				  		     txtcodeemployeemballage.setBounds(999, 108, 77, 26);
				  		     add(txtcodeemployeemballage);
				  		     
				  		     JLabel label_7 = new JLabel("Employe :");
				  		     label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_7.setBounds(1084, 107, 61, 26);
				  		     add(label_7);
				  		     
				  		     JLabel label_8 = new JLabel("Delai H :");
				  		     label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_8.setBounds(1317, 106, 61, 26);
				  		     add(label_8);
				  		     
				  		     txtdelaiemballage = new JTextField();
				  		     txtdelaiemballage.setColumns(10);
				  		     txtdelaiemballage.setBounds(1360, 108, 58, 26);
				  		     add(txtdelaiemballage);
				  		     
				  		     JLabel label_9 = new JLabel("H Supp 50% :");
				  		     label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_9.setBounds(1428, 108, 77, 26);
				  		     add(label_9);
				  		     
				  		     txthsupp50emballage = new JTextField();
				  		     txthsupp50emballage.setColumns(10);
				  		     txthsupp50emballage.setBounds(1497, 108, 58, 26);
				  		     add(txthsupp50emballage);
				  		     
				  		     JLabel label_10 = new JLabel("H Supp 25% :");
				  		     label_10.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     label_10.setBounds(1564, 105, 77, 26);
				  		     add(label_10);
				  		     
				  		     txthsupp25emballage = new JTextField();
				  		     txthsupp25emballage.setColumns(10);
				  		     txthsupp25emballage.setBounds(1637, 108, 58, 26);
				  		     add(txthsupp25emballage);
				  		     
				  		      checkEnPaneemballage = new JCheckBox("En Panne");
				  		     checkEnPaneemballage.setBounds(1701, 111, 71, 23);
				  		     add(checkEnPaneemballage);
				  		   checkEnPaneemballage.setVisible(true);
				  		  
				  		      checksortieemballage = new JCheckBox("Sortie");
				  		     checksortieemballage.setBounds(1774, 111, 64, 23);
				  		     add(checksortieemballage);
				  		     
				  		     JButton btnAjouterEmployeProduction = new JButton("");
				  		     btnAjouterEmployeProduction.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
				  		     	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
							
				  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  		  		boolean absent=false;
				  		  		boolean sortie=false;
				  		     	boolean retard=false;
				  		     	boolean enpanne=false;
				  		     	 int idEmploye;
				  		     		
				  		     		boolean existe=false;
				  		     		
				  		     		
				  		     		if(txtdelaiproduction.getText().isEmpty()==false)
				  		     		{
				  		     			
				  		     			if(!txtdelaiproduction.getText().trim().equals(""))
					  		     		{
				  		     				
				  		     				if(new BigDecimal(txtdelaiproduction.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
				  		     				{
				  		     					
				  		     					JOptionPane.showMessageDialog(null, "Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
					  		     				return;
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     				if(new BigDecimal(txtdelaiproduction.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))<0)
				  		     				{
				  		     					if(checksortieproduction.isSelected()==false && checkretardproduction.isSelected()==false && checkEnpaneproduction.isSelected()==false)
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "Veuillez Cocher Sortie , Retard Ou En Panne Pour Les heures Inferieur Au Delais De production SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
						  		     				return;
				  		     					}
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				
					  		     		}
				  		     			
				  		     			
				  		     		}
				  		     		
				  		     		
			  		     		
				  		      	if(!txtcodeemployeproduction.getText().equals("") && !comboemployeproduction.getSelectedItem().equals(""))
				  		      	{
				  		      		
				  		      	Employe employeTmp=mapMatriculeEmploye.get( txtcodeemployeproduction.getText());
				  		      		for(int t=0;t<listDetailProductionTMP.size();t++)
				  		      		{
				  		      			
				  		      			if(listDetailProductionTMP.get(t).getEmploye().getId()==employeTmp.getId())
				  		      			{
				  		      			existe=true;
				  		      			}
				  		      			
				  		      			
				  		      			
				  		      		}
				  		      		
				  		      		if(existe==true)
				  		      		{
				  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
				  		      			return;
				  		      		}
				  		      		
				  		      		
				  		      		
				  		      	DetailProduction detailproduction=new DetailProduction();
		  		  		    	detaildelai=BigDecimal.ZERO;
		  		  		    	detailheur25=BigDecimal.ZERO;
		  		  		    	detailheur50=BigDecimal.ZERO;
		  		  		    	absent=false;
		  		  		    	sortie=false;
		  		  		    enpanne=false;
		  		  		    retard=false;
		  		  		    //	int key=lsiteInt.get(i);
		  		  		    	Employe employe=mapMatriculeEmploye.get( txtcodeemployeproduction.getText());
		  		  		    	
		  		  		    	if(employe!=null)
		  		  		    	{
		  		  		    		idEmploye=employe.getId();
		  		  		    	
		  		  		    
		  		  		    	
		  		  		    		if(!txtdelaiproduction.getText().equals(""))
		  		  		    		{
		  		  		    			
		  		  		    			if(new BigDecimal(txtdelaiproduction.getText()).compareTo(BigDecimal.ZERO) >0)
		  		  		    			{
		  		  		    				
		  		  		    				
		  		  		    				if(checksortieproduction.isSelected()==true)
		  		  		    				{
		  		  		    				sortie=true;
		  		  		    				}
 if(checkretardproduction.isSelected()==true)
		  		  		    				{
		  		  		    					retard=true;
		  		  		    				}
		  		  		    				
 if(checkEnpaneproduction.isSelected()==true)
 {
	 enpanne=true;
 }
		  		  		    				
		  		  		    				
		  		  		    				detaildelai=new BigDecimal(txtdelaiproduction.getText());
		  		  		    				if(!txthsupp25production.getText().equals(""))
		  		  		    				{
		  		  		    					if(new BigDecimal(txthsupp25production.getText()).compareTo(BigDecimal.ZERO) >0)
		  		  		    					{
		  		  		    					detailheur25=new BigDecimal(txthsupp25production.getText());
		  		  		    					}
		  		  		    					
		  		  		    					
		  		  		    				}
		  		  		    		    		
		  		  		    			if(!txthsupp50production.getText().equals(""))
	  		  		    				{
	  		  		    					if(new BigDecimal(txthsupp50production.getText()).compareTo(BigDecimal.ZERO) >0)
	  		  		    					{
	  		  		    					detailheur50=new BigDecimal(txthsupp50production.getText());
	  		  		    					}
	  		  		    					
	  		  		    					
	  		  		    				}
		  		  		    		
		  		  		    		    	
		  		  		    		    	detailproduction.setEmploye(employe);
		  		  		    		    detailproduction.setTypeResEmploye(employe.getTypeResEmploye());
		  		  					    	detailproduction.setDelaiEmploye(detaildelai);
		  		  					    	detailproduction.setHeureSupp25(detailheur25);
		  		  					    	detailproduction.setHeureSupp50(detailheur50);
		  		  					    	detailproduction.setAbsent(absent);
		  		  					   
		  		  					        detailproduction.setCoutTotal(detaildelai.multiply(heure.getValeur()));
		  		  					        detailproduction.setCoutSupp25(detailheur25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))));
		  		  				            detailproduction.setCoutSupp50(detailheur50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))));
		  		  				            
		  		  				        detailproduction.setCoutHoraire(heure.getValeur());
		  		  				        detailproduction.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
		  		  				      detailproduction.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
		  		  				            
		  		  					    	detailproduction.setSortie(sortie);
		  		  					        detailproduction.setRetard(retard);
		  		  					    detailproduction.setPanne(enpanne);
		  		  					        detailproduction.setAutorisation(false);
		  		  					    	detailproduction.setProduction(production);
		  		  					    listDetailProductionTMP.add(detailproduction);
		  		  		    		
		  		  		    			}
		  		  		    			
		  		  		    			}
		  		  		    		 
		  		  		    	
		  		  		    	}
		  		  		   
		  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
		  		  		     		
		  		  		     		production.setDetailProductions(listDetailProductionTMP);
		  		  		     		//production.getDetailProductions().addAll(listDetailProduction);
		  		  		     		productionDAO.edit(production);
		  		  		     	listDetailProductionTMP.clear();
		  		  		    listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
		  		  		     	 afficher_tableEmploye(listDetailProductionTMP);
		  		  		  ViderEmployeProduction();
				  		      		
				  		      	}
				  		  		   
				  		  		    
				  		  	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		   btnAjouterEmployeProduction.setIcon(imgAjouter);
				  		     btnAjouterEmployeProduction.setBounds(894, 183, 58, 23);
				  		     add(btnAjouterEmployeProduction);
				  		     
				  		     JButton btnAjouterEmployeEmballage = new JButton("");
				  		     btnAjouterEmployeEmballage.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		
				  		     		
				  		     		
				  		     	 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
								 
								
								 
								 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
								 
								 
									if(txtdelaiemballage.getText().isEmpty()==false)
				  		     		{
				  		     			
				  		     			if(!txtdelaiemballage.getText().trim().equals(""))
					  		     		{
				  		     				
				  		     				if(new BigDecimal(txtdelaiemballage.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
				  		     				{
				  		     					
				  		     					JOptionPane.showMessageDialog(null, "Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
					  		     				return;
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     				if(new BigDecimal(txtdelaiemballage.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))<0)
				  		     				{
				  		     					if(checksortieemballage.isSelected()==false && checkretardemballage.isSelected()==false && checkEnPaneemballage.isSelected()==false)
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "Veuillez Cocher Sortie , Retard ou En Panne Pour Les heures Inferieur Au Delais De production SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
						  		     				return;
				  		     					}
				  		     					
				  		     					
				  		     				}
				  		     				
				  		     				
					  		     		}
				  		     			
				  		     			
				  		     		}
								 
								 
								 
								 
								 
								 
				  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  		  		boolean absent=false;
				  		  		boolean sortie=false;
				  		  	boolean Enpanne=false;
				  		  	boolean retard=false;
				  		     		int idEmploye;
				  		     		boolean existe=false;
				  		      	
				  		     		Employe employeTmp=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
				  		      		for(int t=0;t<listDetailProdGenTmp.size();t++)
				  		      		{
				  		      			
				  		      			if(listDetailProdGenTmp.get(t).getEmploye().getId()==employeTmp.getId())
				  		      			{
				  		      			existe=true;
				  		      			}
				  		      			
				  		      			
				  		      			
				  		      		}
				  		      		
				  		      		if(existe==true)
				  		      		{
				  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
				  		      			return;
				  		      		}
				  		     		
				  		     		
				  		  		  
				  		  		    	DetailProdGen detailprodGen=new DetailProdGen();
				  		  		    	detaildelai=BigDecimal.ZERO;
				  		  		    	detailheur25=BigDecimal.ZERO;
				  		  		    	detailheur50=BigDecimal.ZERO;
				  		  		    	absent=false;
				  		  		    	sortie=false;
				  		  		    Enpanne=false;
				  		  		    retard=false;
				  		  		    //	int key=lsiteInt.get(i);
				  		  		    	Employe employe=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
				  		  		    	
				  		  		    	if(employe!=null)
				  		  		    	{
				  		  		    		idEmploye=employe.getId();
				  		  		    	
				  		  		   
				  		  		    	 
				  		  		    	
				  		  		    		if(!txtdelaiemballage.getText().equals(""))
				  		  		    		{
				  		  		    			
				  		  		    			if( new BigDecimal(txtdelaiemballage.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    			{
				  		  		    				
				  		  		    				if(checksortieemballage.isSelected()==true)
				  		  		    				{
				  		  		    				sortie=true;
				  		  		    				}
				  		  		    				
				  		  		    			if(checkEnPaneemballage.isSelected()==true)
			  		  		    				{
			  		  		    				Enpanne=true;
			  		  		    				}
				  		  		    					
				  		  		    			if(checkretardemballage.isSelected()==true)
			  		  		    				{
			  		  		    				retard=true;
			  		  		    				}
				  		  		    				
				  		  		    				detaildelai=new BigDecimal(txtdelaiemballage.getText());
				  		  		    				if(!txthsupp25emballage.getText().equals(""))
				  		  		    				{
				  		  		    				if( new BigDecimal(txthsupp25emballage.getText()).compareTo(BigDecimal.ZERO) >0)	
				  		  		    				{
				  		  		    				detailheur25=new BigDecimal(txthsupp25emballage.getText());
				  		  		    				}
				  		  		    					
				  		  		    					
				  		  		    				}
				  		  		    		    		
				  		  		    		    	
				  		  		    			if(!txthsupp50emballage.getText().equals(""))
			  		  		    				{
			  		  		    				if( new BigDecimal(txthsupp50emballage.getText()).compareTo(BigDecimal.ZERO) >0)	
			  		  		    				{
			  		  		    				detailheur50=new BigDecimal(txthsupp50emballage.getText());
			  		  		    				}
			  		  		    					
			  		  		    					
			  		  		    				}
				  		  		    		    	
				  		  		    		    	detailprodGen.setEmploye(employe);
				  		  		    		    detailprodGen.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    		    	detailprodGen.setDelaiEmploye(detaildelai);
				  		  		    		    	detailprodGen.setHeureSupp25(detailheur25);
				  		  		    		    	detailprodGen.setHeureSupp50(detailheur50);
				  		  		    		    	detailprodGen.setAbsent(absent);
				  		  		    		    	detailprodGen.setSortie(sortie);
				  		  		    		    detailprodGen.setCoutTotal(detaildelai.multiply(heure.getValeur()));
								  		  		detailprodGen.setCoutSupp25(detailheur25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))));
								  		  	detailprodGen.setCoutSupp50(detailheur50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))));
								  		  	
								  		  detailprodGen.setCoutHoraire(heure.getValeur());
									  		detailprodGen.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
									  		detailprodGen.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
									  		detailprodGen.setPanne(Enpanne);
				  		  		    		    detailprodGen.setRetard(retard);
				  		  		    		detailprodGen.setAutorisation(false);
				  		  		    		    	detailprodGen.setProductionGen(production);
				  		  		    		    listDetailProdGenTmp.add(detailprodGen);
				  		  		    		
				  		  		    			}
				  		  		    			
				  		  		    			}
				  		  		    		 
				  		  		    	
				  		  		    	}
				  		  		     
				  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
				  		  		     		
				  		  		     		production.setListDetailProdGen(listDetailProdGenTmp);
				  		  		     		productionDAO.edit(production);
				  		  		     	listDetailProdGenTmp.clear();
				  		  		    listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
				  		  		     	afficher_tableEmployeEmabalage(listDetailProdGenTmp);
				  		     		ViderEmployeEmballage();
				  		     		
				  		     	}
				  		     });
				  		   btnAjouterEmployeEmballage.setIcon(imgAjouter);
				  		     btnAjouterEmployeEmballage.setBounds(1860, 193, 58, 23);
				  		     add(btnAjouterEmployeEmballage);
				  		     
				  		     JButton btnAjouterEmployeGenerique = new JButton("");
				  		     btnAjouterEmployeGenerique.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		

				  		     	 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
				  		  		
				  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
				  		  		boolean absent=false;
				  		  		boolean sortie=false;
				  		  	boolean retard=false;
				  		     		int idEmploye;
				  		     		BigDecimal delaiComplet = BigDecimal.ZERO,detailheurComplet25=BigDecimal.ZERO,detailheurComplet50=BigDecimal.ZERO;
				  		  		CompteurProduction compteurProduction=new CompteurProduction(); 

				  		     		BigDecimal coutSupp25=BigDecimal.ZERO;
				  		  		BigDecimal coutSupp50=BigDecimal.ZERO;
				  		  	//	BigDecimal coutHoraire=0;
				  		  		BigDecimal coutHoraireComplet=BigDecimal.ZERO;
				  		  		BigDecimal coutHoraireTotal=BigDecimal.ZERO;
				  		  		 compteurProduction=compteurProductionDAO.findByDateProdPeriode(production.getDate(),production.getPeriode());
				  		  		 int compteurProd=compteurProduction.getCompteur();
				  		  		 if(compteurProd<=0)
				  		  			 compteurProd=1;
				  		  		   
				  		  		    	DetailProdRes detailResponsableProd=new DetailProdRes();
				  		  		    	detaildelai=BigDecimal.ZERO;
				  		  		    	detailheur25=BigDecimal.ZERO;
				  		  		    	detailheur50=BigDecimal.ZERO;
				  		  		    	absent=false;
				  		  		    	sortie=false;
				  		  		    retard=false;
				  		  		    //	int key=lsiteInt.get(i);
				  		  		    	Employe employe=mapMatriculeEmploye.get(txtcodeemployegenerique.getText());
				  		  		    	
				  		  		    	if(employe!=null)
				  		  		    	{
				  		  		    		idEmploye=employe.getId();
				  		  		    	
				  		  		   
				  		  		    	if(checkabsentgenerique.isSelected()==true)
				  		  		    	{
				  		  		    		
				  		  		    	
				  		  		    	
				  		  		    	if(checkabsentgenerique.isSelected()==true)
				  		  		    	absent=true;
				  		  		    	
				  		  		    	detailResponsableProd.setCoutSupp25(BigDecimal.ZERO);
				  		  	    		detailResponsableProd.setCoutSupp50(BigDecimal.ZERO);
				  		  	    		detailResponsableProd.setCoutTotal(BigDecimal.ZERO);
				  		  		    	detailResponsableProd.setDelaiEmploye(BigDecimal.ZERO);
				  		  		    	detailResponsableProd.setHeureSupp25(BigDecimal.ZERO);
				  		  		    	detailResponsableProd.setHeureSupp50(BigDecimal.ZERO);
				  		  		    	
				  		  		        detailResponsableProd.setCoutHoraire(heure.getValeur());
	  		  		    		        detailResponsableProd.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
	  		  		    	            detailResponsableProd.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
				  		  		    	
				  		  		    	detailResponsableProd.setEmploye(employe);
				  		  		        detailResponsableProd.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    	detailResponsableProd.setAbsent(absent);
				  		  		        detailResponsableProd.setSortie(false);
				  		  		        detailResponsableProd.setRetard(false);
				  		  		        detailResponsableProd.setAutorisation(false);
				  		  		        detailResponsableProd.setValider(Constantes.ETAT_INVALIDER);
				  		  		    	detailResponsableProd.setDateProduction(production.getDate());
				  		  		    	//detailResponsableProd.setProduction(production);
				  		  		    	//listDetailResponsableProd.add(detailResponsableProd);
				  		  		    	detailProdResDAO.add(detailResponsableProd);


				  		  		    		
				  		  		    	}else if(checkabsentgenerique.isSelected()==false){
				  		  		    	
				  		  		    		
				  		  		    	
				  		  		    		if(!txtdelaigenerique.getText().equals(""))
				  		  		    		{
				  		  		    			
				  		  		    			if(new BigDecimal(txtdelaigenerique.getText()).compareTo(BigDecimal.ZERO)  >0)
				  		  		    			{
				  		  		    				detaildelai=new BigDecimal(txtdelaigenerique.getText());
				  		  		    				delaiComplet=detaildelai;///compteurProd;
				  		  		    				if(!employe.isSalarie()){
				  		  		    				
				  		  		    					
				  		  		    					if(checksortiegenerique.isSelected()==true)
				  		  		    					{
				  		  		    					sortie=true;
				  		  		    					}
				  		  			    				
				  		  		    				if(checkretardgenerique.isSelected()==true)
			  		  		    					{
			  		  		    					retard=true;
			  		  		    					}
				  		  		    					
				  		  		    				detaildelai=new BigDecimal(txtdelaigenerique.getText());
				  		  		    				
				  		  		    			
				  		  		    				
				  		  		    				
				  		  				    		coutHoraireComplet=heure.getValeur().multiply(detaildelai);
				  		  				    		coutHoraireTotal=coutHoraireTotal.add(coutHoraireComplet);
				  		  		    				
				  		  		    				
				  		  		    				
				  		  		    				if(!txthsupp25generique.getText().equals(""))
				  		  		    					
				  		  		    				{
				  		  		    					if(new BigDecimal(txthsupp25generique.getText()).compareTo(BigDecimal.ZERO)  >0)
				  		  		    					{
				  		  		    					detailheur25=new BigDecimal(txthsupp25generique.getText());
				  		  		    					detailheurComplet25=detailheur25;///compteurProd;
				  		  		    		    		coutSupp25=detailheur25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
				  		  		    		    		coutHoraireTotal=coutHoraireTotal.add(coutSupp25);
				  		  		    					}
				  		  		    					
				  		  		    		    		
				  		  		    				}
				  		  		    				
				  		  		    		    	if(!txthsupp50generique.getText().equals(""))
				  		  		    		    		
				  		  		    		    	{
				  		  		    		    	if(new BigDecimal(txthsupp50generique.getText()).compareTo(BigDecimal.ZERO)  >0)
			  		  		    					{
				  		  		    		    	detailheur50=new BigDecimal(txthsupp50generique.getText());
			  		  		    		    		detailheurComplet50=detailheur50;//compteurProd;
			  		  		    		    		coutSupp50=detailheur50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);
			  		  		    		    		coutHoraireTotal=coutHoraireTotal.add(coutSupp50);
			  		  		    					}
				  		  		    		    		
				  		  		    		    	}
				  		  		    		    	
				  		  		    			}
				  		  		    		    	/*
				  		  		    		    	 * */
				  		  		    			
				  		  		    				
				  		  		    				
				  		  		    		    	detailResponsableProd.setCoutSupp25(coutSupp25);
				  		  				    		detailResponsableProd.setCoutSupp50(coutSupp50);
				  		  				    		detailResponsableProd.setCoutTotal(coutHoraireComplet);
				  		  		    		    	detailResponsableProd.setDelaiEmploye(delaiComplet);
				  		  		    		    	detailResponsableProd.setHeureSupp25(detailheurComplet25);
				  		  		    		    	detailResponsableProd.setHeureSupp50(detailheurComplet50);
				  		  		    		    	
				  		  		    		    detailResponsableProd.setCoutHoraire(heure.getValeur());
				  		  		    		    detailResponsableProd.setCoutHoraireSupp25(COUT_HEURE_SUPPLEMENTAIRE_25);
				  		  		    	        detailResponsableProd.setCoutHoraireSupp50(COUT_HEURE_SUPPLEMENTAIRE_50);
				  		  		    		
				  		  		    		    	detailResponsableProd.setEmploye(employe);
				  		  		    		       detailResponsableProd.setTypeResEmploye(employe.getTypeResEmploye());
				  		  		    		    	detailResponsableProd.setAbsent(absent);
				  		  		    		    	detailResponsableProd.setSortie(sortie);
				  		  		    		        detailResponsableProd.setRetard(retard);
				  		  		    		    detailResponsableProd.setAutorisation(false);
				  		  		    		    	//detailResponsableProd.setProduction(production);
				  		  		    		    	detailResponsableProd.setDateProduction(production.getDate());
				  		  		    		    	detailProdResDAO.add(detailResponsableProd);
				  		  		    		
				  		  		    			 }
				  		  		    			
				  		  		    			
				  		  		    			CompteurEmployeProd compteurEmployeProd =compteurEmployeProdDAO.findByDateProdPeriode(production.getDate(),production.getPeriode(), detailResponsableProd.getEmploye().getId());
				  		  		   			 if(compteurEmployeProd !=null){
				  		  		   				 compteur=compteurEmployeProd.getCompteur()+1;
				  		  		   				 compteurEmployeProd.setCompteur(compteur);	
				  		  		   				 compteurEmployeProdDAO.edit(compteurEmployeProd);
				  		  		   				 
				  		  		   			 }else{
				  		  		   				 compteurEmployeProd= new CompteurEmployeProd();
				  		  		   				 compteurEmployeProd.setDateProd(production.getDate());
				  		  		   				 compteurEmployeProd.setPeriode(production.getPeriode());
				  		  		   				 compteurEmployeProd.setEmploye(detailResponsableProd.getEmploye());
				  		  		   				 compteurEmployeProd.setCompteur(1);
				  		  		   				 compteurEmployeProdDAO.add(compteurEmployeProd);
				  		  		   				 
				  		  		   			 	}
				  		  		    			
				  		  		    		   }
				  		  		    		
				  		  		    		  
				  		  		    		}
				  		  		    	
						  				listDetailResponsableProdTmp=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");

						  				 afficher_tableEmployeGen(listDetailResponsableProdTmp);
				  		  		    	
				  		  		    	ViderEmployeGenerique();
				  		  		    	
				  		  		    	
				  		  		    	}
				  		  		    
				  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
				  		  		     		
				  		   //production.setListDetailResponsableProd(listDetailResponsableProd);
				  		   //productionDAO.edit(production);	
				  		       		 
				  		  	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnAjouterEmployeGenerique.setBounds(1867, 693, 58, 23);
				  		   btnAjouterEmployeGenerique.setIcon(imgAjouter);
				  		     add(btnAjouterEmployeGenerique);
				  		   btnAjouterEmployeGenerique.setVisible(false);
				  		     
				  		      comboemployeproduction = new JComboBox();
				  		      comboemployeproduction.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent arg0) {
				  		      		

				  		   	 		
				  		   	 		if(!comboemployeproduction.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployeproduction.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployeproduction.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 		
				  		   	 	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     comboemployeproduction.setSelectedIndex(-1);
				  		     comboemployeproduction.setBounds(168, 110, 157, 24);
				  		     add(comboemployeproduction);
				  		   AutoCompleteDecorator.decorate(comboemployeproduction);
				  		      comboemployeemballage = new JComboBox();
				  		      comboemployeemballage.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		      		

				  		      		

				  		   	 		
				  		   	 		if(!comboemployeemballage.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployeemballage.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployeemballage.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 		
				  		   	 	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	
				  		      		
				  		      		
				  		      		
				  		      	}
				  		      });
				  		     comboemployeemballage.setSelectedIndex(-1);
				  		     comboemployeemballage.setBounds(1135, 110, 170, 24);
				  		     add(comboemployeemballage);
				  		   AutoCompleteDecorator.decorate(comboemployeemballage);
				  		      comboemployegenerique = new JComboBox();
				  		      comboemployegenerique.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		   	 		if(!comboemployegenerique.getSelectedItem().equals(""))
				  		   	 		{
				  		   	 			
				  		   	 			Employe employe=mapNomEmploye.get(comboemployegenerique.getSelectedItem());
				  		   	 			if(employe!=null)
				  		   	 			{
				  		   	 				txtcodeemployegenerique.setText(employe.getMatricule());
				  		   	 			}else
				  		   	 			{
				  		   	 				JOptionPane.showMessageDialog(null, "Employe Introuvable");
				  		   	 				return;
				  		   	 			}
				  		   	 			
				  		   	 			
				  		   	 			
				  		   	 		}
				  		   	 						  		      	}
				  		      });
				  		     comboemployegenerique.setSelectedIndex(-1);
				  		     comboemployegenerique.setBounds(1138, 598, 170, 24);
				  		     add(comboemployegenerique);
				  		   AutoCompleteDecorator.decorate(comboemployegenerique);
				  		    
				  		   CheckableItem[] m = {
					  			      new CheckableItem("aaa", false),
					  			      new CheckableItem("bbbbb", false),
					  			      new CheckableItem("111", false),
					  			      new CheckableItem("33333", false),
					  			      new CheckableItem("2222", false),
					  			      new CheckableItem("ccccccc", false)
					  			    };
				  		  
				  	  
				  	  
				  		   comboMachine.addItem("");
				  		    comboEquipe.addItem("");
				  		    
				  		  afficher_tableEmploye(production.getDetailProductions());
				  		 
				  		  afficher_tableEmployeEmabalage(production.getListDetailProdGen());
				  		  if(production.getDate()!=null)
				  		  {
						  		listDetailProdResponsable=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
  
				  		  }
				  		/*  
				  		int numberproduction=productionDAO.NombreProductionTerminerParDate(production.getDate(), Constantes.ETAT_OF_TERMINER)+1;
		  				if(numberproduction==1)
		  				{
		  				
		  					 btnSaisieDelaiEquipeGen.setEnabled(true);
		  					
		  				}else
		  				{
		  					
		  					 btnSaisieDelaiEquipeGen.setEnabled(false);
		  					
		  				}
		  				*/	
				  		 
				  		comboemployeproduction.addItem("");
						comboemployeemballage.addItem("");
						comboemployegenerique.addItem("");
						
						JButton btnViderEmployeProduction = new JButton("Vider");
						btnViderEmployeProduction.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
								ViderEmployeProduction();
								
							}
						});
						btnViderEmployeProduction.setBounds(890, 149, 58, 23);
						add(btnViderEmployeProduction);
						
						JButton btnVideremployeemballage = new JButton("Vider");
						btnVideremployeemballage.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								ViderEmployeEmballage();
							}
						});
						btnVideremployeemballage.setBounds(1860, 149, 58, 23);
						add(btnVideremployeemballage);
						
						JButton btnVideremployegenerique = new JButton("Vider");
						btnVideremployegenerique.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
							ViderEmployeGenerique();	
								
							}
						});
						btnVideremployegenerique.setBounds(1867, 641, 58, 23);
						add(btnVideremployegenerique);
						
						 checkretardproduction = new JCheckBox("Retard");
						checkretardproduction.setBounds(878, 111, 71, 24);
						add(checkretardproduction);
						
						 checkretardemballage = new JCheckBox("Retard");
						checkretardemballage.setBounds(1841, 110, 77, 24);
						add(checkretardemballage);
						
						 checkretardgenerique = new JCheckBox("Retard");
						checkretardgenerique.setBounds(1853, 602, 78, 24);
						add(checkretardgenerique);
						
						JXTitledSeparator titledSeparator_4 = new JXTitledSeparator();
						GridBagLayout gridBagLayout_3 = (GridBagLayout) titledSeparator_4.getLayout();
						gridBagLayout_3.rowWeights = new double[]{0.0};
						gridBagLayout_3.rowHeights = new int[]{0};
						gridBagLayout_3.columnWeights = new double[]{0.0, 0.0, 0.0};
						gridBagLayout_3.columnWidths = new int[]{0, 0, 0};
						titledSeparator_4.setTitle("Saisir Cout Hors production En Attent");
						titledSeparator_4.setBounds(8, 583, 875, 17);
						add(titledSeparator_4);
						
						JScrollPane scrollPane_5 = new JScrollPane();
						scrollPane_5.setBounds(8, 617, 875, 193);
						add(scrollPane_5);
						
						TableDetailCoutHorsProductionEnAttent = new JXTable();
						TableDetailCoutHorsProductionEnAttent.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent e) {
}
						});
						scrollPane_5.setViewportView(TableDetailCoutHorsProductionEnAttent);
						TableDetailCoutHorsProductionEnAttent.setColumnSelectionAllowed(true);
						TableDetailCoutHorsProductionEnAttent.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
									"Code",	"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%","Valider"
							}
						));
						
						
						
						 txtNumOF.addActionListener(new ActionListener() {
							  	public void actionPerformed(ActionEvent arg0) {
							  		
							  	 
							  		
							  		if(txtNumOF.getSelectedIndex()!=-1)
							  		{
							  			
							  			if(!txtNumOF.getSelectedItem().equals(""))
							  			{
							  				
							  				production=productionDAO.findByNumOFStatut(txtNumOF.getSelectedItem().toString(), Constantes.ETAT_OF_TERMINER);
								  			if(production!=null)
								  			{
								  				
								  				Depot depot=depotdao.findByCode(production.getCodeDepot());
								  				
								  				txtNumOF.setToolTipText(production.getStatut());
								  			txtQuantiteRealise.setText(production.getQuantiteReel()+"");
								  			txtPrixServiceProd.setText(production.getNbreHeure()+"");
								  			txtQuantiteOffre.setText(production.getQuantiteOffre()+"");
								  			txtQuantitePlus.setText(production.getQuantitePlus()+"");
								  			txtQuantiteMoins.setText(production.getQuantiteMoins()+"");
								  			if(production.getNbrePersonneTravailler()!=null)
								  			{
								  				txtNombrePersonneTravailler.setText(production.getNbrePersonneTravailler()+"");
								  			}
								  			if(production.getQuantitePlus().compareTo(BigDecimal.ZERO)!=0)
								  			{
								  				radioPlus.setSelected(true);
								  			}
								  			
								  			if(production.getQuantiteMoins().compareTo(BigDecimal.ZERO)!=0)
								  			{
								  				radioMoins.setSelected(true);
								  			}
								  			
								  			List<CoutMP>	listCoutMPTmp=productionDAO.listeCoutMP(production.getId());
								  			
							  				categorie.removeAllItems();
							  				comboLigneMachine.removeAllItems();
							  				comboMachine.removeAllItems();
							  				codeArticle.setText(production.getArticles().getCodeArticle());
							  				categorie.addItem(production.getArticles().getLiblle());
							  				categorie.setSelectedItem(production.getArticles().getLiblle());
							  				
							  				//comboEquipe.addItem(production.getEquipe().getNomEquipe());
							  				//comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
							  				
							  				comboLigneMachine.addItem(production.getLigneMachine().getNom());
							  				comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
							  				
							  				comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
							  				comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
							  				
							  				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  				

							  				 listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
							  				 listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
							  				 //listDetailResponsableProdTmp=productionDAO.listeDetailResponsableProd(production.getId());
							  				listDetailResponsableProdTmp=detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
							  				
							  				
							  				afficher_tableEmploye(listDetailProductionTMP);
							  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
							  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
							  				listCoutHorsProductionEnAttenteAfficher=CoutHorsProdEnAttentDAO.findByProduction(production);
							  				 
						  					afficher_tableDetailCoutHorsProductionEnAttent(listCoutHorsProductionEnAttenteAfficher);	
								  			 
								  			
								  			
								  			}
							  				
							  			}else
							  			{

							  				List<CoutMP>	listCoutMPTmp=new ArrayList<CoutMP>();
							  				listDetailProductionTMP.clear();
							  				listDetailProdGenTmp.clear();
							  				listDetailResponsableProdTmp.clear();
							  				listCoutHorsProductionEnAttent.clear();
							  				txtQuantiteRealise.setText("");
								  			txtPrixServiceProd.setText("");
								  			txtQuantiteOffre.setText("");
								  			txtQuantitePlus.setText("");
								  			txtQuantiteMoins.setText("");
								  			group.clearSelection();
								  			
								  			categorie.removeAllItems();
							  				comboLigneMachine.removeAllItems();
							  				comboMachine.removeAllItems();
							  				codeArticle.setText("");
								  			
							  				
							  				afficher_tableEmploye(listDetailProductionTMP);
							  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
							  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
							  				 
							  				afficher_tableCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);
								  			
							  			
							  			}
							  			
							  			
							
							  			
							  		}else
							  		{
						  				List<CoutMP>	listCoutMPTmp=new ArrayList<CoutMP>();
						  				listDetailProductionTMP.clear();
						  				listDetailProdGenTmp.clear();
						  				listDetailResponsableProdTmp.clear();
						  				listCoutHorsProductionEnAttent.clear();
						  				txtQuantiteRealise.setText("");
							  			txtPrixServiceProd.setText("");
							  			txtQuantiteOffre.setText("");
							  			txtQuantitePlus.setText("");
							  			txtQuantiteMoins.setText("");
							  			group.clearSelection();
							  			
							  			categorie.removeAllItems();
						  				comboLigneMachine.removeAllItems();
						  				comboMachine.removeAllItems();
						  				codeArticle.setText("");
							  			
						  				
						  				afficher_tableEmploye(listDetailProductionTMP);
						  				afficher_tableEmployeEmabalage(listDetailProdGenTmp);
						  				afficher_tableEmployeGen(listDetailResponsableProdTmp);
						  				 
						  				afficher_tableCoutHorsProductionEnAttent(listCoutHorsProductionEnAttent);
							  			
						  			}
							  		
							  		
							  		
							  		
							  		
							  		
							  	}
							  });
							  
							 
					  		     txtNumOF.setBounds(75, 7, 157, 24);
					  		     add(txtNumOF);
					  		   AutoCompleteDecorator.decorate(txtNumOF); 
					  		 txtNumOF.setSelectedIndex(-1);
						
						JLabel lblQuantiteOffre = new JLabel("Quantite Offre :");
						lblQuantiteOffre.setFont(new Font("Tahoma", Font.PLAIN, 11));
						lblQuantiteOffre.setBounds(648, 6, 89, 26);
						add(lblQuantiteOffre);
						
						txtQuantiteOffre = new JTextField();
						txtQuantiteOffre.setEditable(false);
						txtQuantiteOffre.setColumns(10);
						txtQuantiteOffre.setBounds(747, 6, 96, 26);
						add(txtQuantiteOffre);
						
						txtQuantitePlus = new JTextField();
						txtQuantitePlus.setEditable(false);
						txtQuantitePlus.setColumns(10);
						txtQuantitePlus.setBounds(928, 6, 82, 26);
						add(txtQuantitePlus);
						
						 radioPlus = new JRadioButton("Plus");
						 radioPlus.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent arg0) {
						 		
						 		if(radioPlus.isSelected()==true)
						 		{
						 			txtQuantiteMoins.setText("0");
						 			txtQuantiteMoins.setEditable(false);
						 			txtQuantitePlus.setText("");
						 			txtQuantitePlus.setEditable(true);
						 			
						 		}
						 		
						 		
						 		
						 	}
						 });
						radioPlus.setBounds(847, 9, 64, 23);
						
						add(radioPlus);
						group.add(radioPlus);
						
						 radioMoins = new JRadioButton("Moins");
						 radioMoins.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		
						 		if(radioMoins.isSelected()==true)
						 		{
						 			txtQuantitePlus.setText("0");
						 			txtQuantitePlus.setEditable(false);
						 			txtQuantiteMoins.setText("");
						 			txtQuantiteMoins.setEditable(true);
						 			
						 		}
						 		
						 	}
						 });
						radioMoins.setBounds(1020, 8, 74, 23);
						add(radioMoins);
						group.add(radioMoins);
						txtQuantiteMoins = new JTextField();
						txtQuantiteMoins.setEditable(false);
						txtQuantiteMoins.setColumns(10);
						txtQuantiteMoins.setBounds(1100, 6, 71, 26);
						add(txtQuantiteMoins);
						
						 labelTotalEquipeProduction = new JLabel("");
						labelTotalEquipeProduction.setFont(new Font("Tahoma", Font.BOLD, 15));
						labelTotalEquipeProduction.setBounds(8, 545, 325, 26);
						add(labelTotalEquipeProduction);
						
						 labelTotalEquipeEmballage = new JLabel("");
						labelTotalEquipeEmballage.setFont(new Font("Tahoma", Font.BOLD, 15));
						labelTotalEquipeEmballage.setBounds(963, 532, 420, 26);
						add(labelTotalEquipeEmballage);
						
						 labelTotalEquipeGenerique = new JLabel("");
						labelTotalEquipeGenerique.setFont(new Font("Tahoma", Font.BOLD, 15));
						labelTotalEquipeGenerique.setBounds(963, 814, 437, 26);
						add(labelTotalEquipeGenerique);
						
						
				  		  for(int i=0;i<listEmploye.size();i++)
						  {
							  
							Employe employe=listEmploye.get(i);  
							comboemployeproduction.addItem(employe.getNomafficher());
							comboemployeemballage.addItem(employe.getNomafficher());
							comboemployegenerique.addItem(employe.getNomafficher());
							  mapMatriculeEmploye.put(employe.getMatricule(), employe);
						 mapNomEmploye.put(employe.getNomafficher(), employe);
						  
						  
						  }  
				  		
				  		  
				  		  
				  		listDetailResponsableProdTmp.clear();
				  		listDetailResponsableProdTmp.addAll(listDetailProdResponsable);
				  		if(txtPrixServiceProd.getText().isEmpty()==false)
				  		{
				  			afficher_tableEmployeGen(listDetailProdResponsable); 
				  		}
				  	     
				  		
				  		 for(int i=0;i<listProduction.size();i++)
						 {
							 
						Production production= listProduction.get(i);
							 
							   txtNumOF.addItem (production.getNumOF());
								mapProduction.put(production.getNumOF(), production);
						 }
						 txtNumOF.setSelectedIndex(-1);
						 
						 JLabel lblNombrePersonneTravailler = new JLabel("Nombre Personne Travailler :");
						 lblNombrePersonneTravailler.setFont(new Font("Tahoma", Font.PLAIN, 11));
						 lblNombrePersonneTravailler.setBounds(1181, 8, 153, 26);
						 add(lblNombrePersonneTravailler);
						 
						 txtNombrePersonneTravailler = new JTextField();
						 txtNombrePersonneTravailler.setColumns(10);
						 txtNombrePersonneTravailler.setBounds(1325, 9, 112, 26);
						 add(txtNombrePersonneTravailler);
						 
						 JButton btnModifierEmployeProduction = new JButton("");
						 btnModifierEmployeProduction.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent arg0) {
						 		
						 		if(tableEmploye.getSelectedRow()!=-1)
								{
									DetailProduction detailProduction= detailProductionDAO.findById(Integer.valueOf(tableEmploye.getValueAt(tableEmploye.getSelectedRow(), 0).toString()) );	
									
									 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
						  		     	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
									
						  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
						  		  		boolean absent=false;
						  		  		boolean sortie=false;
						  		     	boolean retard=false;
						  		     	boolean enpanne=false;
						  		     	 int idEmploye;
						  		     		
						  		     		boolean existe=false;
									
						  		     		if(txtdelaiproduction.getText().isEmpty()==false)
						  		     		{
						  		     			
						  		     			if(!txtdelaiproduction.getText().trim().equals(""))
							  		     		{
						  		     				
						  		     				if(new BigDecimal(txtdelaiproduction.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
						  		     				{
						  		     					
						  		     					JOptionPane.showMessageDialog(null, "Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
							  		     				return;
						  		     					
						  		     				}
						  		     				
						  		     				
						  		     				if(new BigDecimal(txtdelaiproduction.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))<0)
						  		     				{
						  		     					if(checksortieproduction.isSelected()==false && checkretardproduction.isSelected()==false && checkEnpaneproduction.isSelected()==false)
						  		     					{
						  		     						JOptionPane.showMessageDialog(null, "Veuillez Cocher Sortie , Retard Ou En Panne Pour Les heures Inferieur Au Delais De production SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
								  		     				return;
						  		     					}
						  		     					
						  		     					
						  		     				}
						  		     				
						  		     				
							  		     		}
						  		     			
						  		     			
						  		     		}	
						  		     		
						  		     		
						  		     		
							  		      	if(!txtcodeemployeproduction.getText().equals("") && !comboemployeproduction.getSelectedItem().equals(""))
							  		      	{
							  		      		
							  		      	Employe employeTmp=mapMatriculeEmploye.get( txtcodeemployeproduction.getText());
							  		      		for(int t=0;t<listDetailProductionTMP.size();t++)
							  		      		{
							  		      			
							  		      			if(listDetailProductionTMP.get(t).getEmploye().getId()==employeTmp.getId() && listDetailProductionTMP.get(t).getId()!=detailProduction.getId())
							  		      			{
							  		      			existe=true;
							  		      			}
							  		      			
							  		      			
							  		      			
							  		      		}
							  		      		
							  		      		if(existe==true)
							  		      		{
							  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
							  		      			return;
							  		      		}
							  		      		
							  		      		
							  		      		
							  		      	 
					  		  		    	detaildelai=BigDecimal.ZERO;
					  		  		    	detailheur25=BigDecimal.ZERO;
					  		  		    	detailheur50=BigDecimal.ZERO;
					  		  		    	absent=false;
					  		  		    	sortie=false;
					  		  		    enpanne=false;
					  		  		    retard=false;
					  		  		    //	int key=lsiteInt.get(i);
					  		  		    	Employe employe=mapMatriculeEmploye.get( txtcodeemployeproduction.getText());
					  		  		    	
					  		  		    	if(employe!=null)
					  		  		    	{
					  		  		    		idEmploye=employe.getId();
					  		  		    	
					  		  		    
					  		  		    	
					  		  		    		if(!txtdelaiproduction.getText().equals(""))
					  		  		    		{
					  		  		    			
					  		  		    			if(new BigDecimal(txtdelaiproduction.getText()).compareTo(BigDecimal.ZERO) >0)
					  		  		    			{
					  		  		    				
					  		  		    				
					  		  		    				if(checksortieproduction.isSelected()==true)
					  		  		    				{
					  		  		    				sortie=true;
					  		  		    				}
			if(checkretardproduction.isSelected()==true)
					  		  		    				{
					  		  		    					retard=true;
					  		  		    				}
					  		  		    				
			if(checkEnpaneproduction.isSelected()==true)
			{
				 enpanne=true;
			}
					  		  		    				
					  		  		    				
					  		  		    				detaildelai=new BigDecimal(txtdelaiproduction.getText());
					  		  		    				if(!txthsupp25production.getText().equals(""))
					  		  		    				{
					  		  		    					if(new BigDecimal(txthsupp25production.getText()).compareTo(BigDecimal.ZERO) >0)
					  		  		    					{
					  		  		    					detailheur25=new BigDecimal(txthsupp25production.getText());
					  		  		    					}
					  		  		    					
					  		  		    					
					  		  		    				}
					  		  		    		    		
					  		  		    			if(!txthsupp50production.getText().equals(""))
				  		  		    				{
				  		  		    					if(new BigDecimal(txthsupp50production.getText()).compareTo(BigDecimal.ZERO) >0)
				  		  		    					{
				  		  		    					detailheur50=new BigDecimal(txthsupp50production.getText());
				  		  		    					}
				  		  		    					
				  		  		    					
				  		  		    				}
					  		  		    		
					  		  		    		    	
					  		  		    		detailProduction.setEmploye(employe);
					  		  		    	detailProduction.setTypeResEmploye(employe.getTypeResEmploye());
					  		  		    detailProduction.setDelaiEmploye(detaildelai);
					  		  		detailProduction.setHeureSupp25(detailheur25);
					  		  	detailProduction.setHeureSupp50(detailheur50);
					  		  detailProduction.setAbsent(absent);
					  		  					   
					  		detailProduction.setCoutTotal(detaildelai.multiply(heure.getValeur()));
					  		detailProduction.setCoutSupp25(detailheur25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))));
					  		detailProduction.setCoutSupp50(detailheur50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))));
					  		  				            
					  		detailProduction.setCoutHoraire(heure.getValeur());
					  		detailProduction.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
					  		detailProduction.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
					  		  				            
					  		detailProduction.setSortie(sortie);
					  		detailProduction.setRetard(retard);
					  		detailProduction.setPanne(enpanne);
					  		detailProduction.setAutorisation(false);
					  		detailProduction.setProduction(production);
					  		for(int d=0;d<listDetailProductionTMP.size();d++)
					  		{
					  			if(listDetailProductionTMP.get(d).getId()==detailProduction.getId())
					  			{
					  				 listDetailProductionTMP.set(d, detailProduction);
					  			}
					  		}
					  		  					   
					  		  		    		
					  		  		    			}
					  		  		    			
					  		  		    			}
					  		  		    		 
					  		  		    	
					  		  		    	}
					  		  		   
					  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
					  		  		     		
					  		  		     		production.setDetailProductions(listDetailProductionTMP);
					  		  		     		//production.getDetailProductions().addAll(listDetailProduction);
					  		  		     		productionDAO.edit(production);
					  		  		     	listDetailProductionTMP.clear();
					  		  		    listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
					  		  		     	 afficher_tableEmploye(listDetailProductionTMP);
					  		  		  ViderEmployeProduction();
							  		      		
							  		      	}		
						  		     		
						  		     		
						  		     		
									
									
									 
								}else
								{
									JOptionPane.showMessageDialog(null, "Veuillez Selectionner Un Enregistrement SVP");
									return;
								}
			  		     		
				  		     	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
			  	
				  		  		   
				  		  		    
				  		  	
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     		
				  		     	
						 		
						 		
						 	}
						 });
						 btnModifierEmployeProduction.setBounds(894, 213, 58, 23);
						 btnModifierEmployeProduction.setIcon(imgModifier);
						 add(btnModifierEmployeProduction);
						 
						 JButton btnModifierEmployeEmballage = new JButton("");
						 btnModifierEmployeEmballage.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent arg0) {
						 		

						 		if(table_1.getSelectedRow()!=-1)
								{
						 			DetailProdGen DetailProdGen= detailProdGenDAO.findById(Integer.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 0).toString()) );	
						 			
						 			 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
									 
										
									 
									 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
									 
									 
										if(txtdelaiemballage.getText().isEmpty()==false)
					  		     		{
					  		     			
					  		     			if(!txtdelaiemballage.getText().trim().equals(""))
						  		     		{
					  		     				
					  		     				if(new BigDecimal(txtdelaiemballage.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))==1)
					  		     				{
					  		     					
					  		     					JOptionPane.showMessageDialog(null, "Heures Travail Employee Incompatible Au Delai De Productin SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
						  		     				return;
					  		     					
					  		     				}
					  		     				
					  		     				
					  		     				if(new BigDecimal(txtdelaiemballage.getText()).compareTo(new BigDecimal(txtPrixServiceProd.getText()))<0)
					  		     				{
					  		     					if(checksortieemballage.isSelected()==false && checkretardemballage.isSelected()==false && checkEnPaneemballage.isSelected()==false)
					  		     					{
					  		     						JOptionPane.showMessageDialog(null, "Veuillez Cocher Sortie , Retard ou En Panne Pour Les heures Inferieur Au Delais De production SVP!", "Erreur", JOptionPane.ERROR_MESSAGE);
							  		     				return;
					  		     					}
					  		     					
					  		     					
					  		     				}
					  		     				
					  		     				
						  		     		}
					  		     			
					  		     			
					  		     		}
									 
									 
									 
									 
									 
									 
					  		  		BigDecimal detaildelai,detailheur25=BigDecimal.ZERO,detailheur50=BigDecimal.ZERO;
					  		  		boolean absent=false;
					  		  		boolean sortie=false;
					  		  	boolean Enpanne=false;
					  		  	boolean retard=false;
					  		     		int idEmploye;
					  		     		boolean existe=false;
					  		      	
					  		     		Employe employeTmp=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
					  		      		for(int t=0;t<listDetailProdGenTmp.size();t++)
					  		      		{
					  		      			
					  		      			if(listDetailProdGenTmp.get(t).getEmploye().getId()==employeTmp.getId() && DetailProdGen.getId()!=listDetailProdGenTmp.get(t).getId())
					  		      			{
					  		      			existe=true;
					  		      			}
					  		      			
					  		      			
					  		      			
					  		      		}
					  		      		
					  		      		if(existe==true)
					  		      		{
					  		      			JOptionPane.showMessageDialog(null, "Employé déja existant !!!!");
					  		      			return;
					  		      		}
					  		     		
					  		     		
					  		  		  
					  		  		    	 
					  		  		    	detaildelai=BigDecimal.ZERO;
					  		  		    	detailheur25=BigDecimal.ZERO;
					  		  		    	detailheur50=BigDecimal.ZERO;
					  		  		    	absent=false;
					  		  		    	sortie=false;
					  		  		    Enpanne=false;
					  		  		    retard=false;
					  		  		    //	int key=lsiteInt.get(i);
					  		  		    	Employe employe=mapMatriculeEmploye.get(txtcodeemployeemballage.getText());
					  		  		    	
					  		  		    	if(employe!=null)
					  		  		    	{
					  		  		    		idEmploye=employe.getId();
					  		  		    	
					  		  		   
					  		  		    	 
					  		  		    	
					  		  		    		if(!txtdelaiemballage.getText().equals(""))
					  		  		    		{
					  		  		    			
					  		  		    			if( new BigDecimal(txtdelaiemballage.getText()).compareTo(BigDecimal.ZERO) >0)
					  		  		    			{
					  		  		    				
					  		  		    				if(checksortieemballage.isSelected()==true)
					  		  		    				{
					  		  		    				sortie=true;
					  		  		    				}
					  		  		    				
					  		  		    			if(checkEnPaneemballage.isSelected()==true)
				  		  		    				{
				  		  		    				Enpanne=true;
				  		  		    				}
					  		  		    					
					  		  		    			if(checkretardemballage.isSelected()==true)
				  		  		    				{
				  		  		    				retard=true;
				  		  		    				}
					  		  		    				
					  		  		    				detaildelai=new BigDecimal(txtdelaiemballage.getText());
					  		  		    				if(!txthsupp25emballage.getText().equals(""))
					  		  		    				{
					  		  		    				if( new BigDecimal(txthsupp25emballage.getText()).compareTo(BigDecimal.ZERO) >0)	
					  		  		    				{
					  		  		    				detailheur25=new BigDecimal(txthsupp25emballage.getText());
					  		  		    				}
					  		  		    					
					  		  		    					
					  		  		    				}
					  		  		    		    		
					  		  		    		    	
					  		  		    			if(!txthsupp50emballage.getText().equals(""))
				  		  		    				{
				  		  		    				if( new BigDecimal(txthsupp50emballage.getText()).compareTo(BigDecimal.ZERO) >0)	
				  		  		    				{
				  		  		    				detailheur50=new BigDecimal(txthsupp50emballage.getText());
				  		  		    				}
				  		  		    					
				  		  		    					
				  		  		    				}
					  		  		    		    	
					  		  		    		DetailProdGen.setEmploye(employe);
					  		  		    	DetailProdGen.setTypeResEmploye(employe.getTypeResEmploye());
					  		  		    DetailProdGen.setDelaiEmploye(detaildelai);
					  		  		DetailProdGen.setHeureSupp25(detailheur25);
					  		  	DetailProdGen.setHeureSupp50(detailheur50);
					  		  DetailProdGen.setAbsent(absent);
					  		DetailProdGen.setSortie(sortie);
					  		DetailProdGen.setCoutTotal(detaildelai.multiply(heure.getValeur()));
					  		DetailProdGen.setCoutSupp25(detailheur25.multiply(heure.getValeur().multiply(new BigDecimal(1.25))));
					  		DetailProdGen.setCoutSupp50(detailheur50.multiply(heure.getValeur().multiply(new BigDecimal(1.5))));
									  		  	
					  		DetailProdGen.setCoutHoraire(heure.getValeur());
					  		DetailProdGen.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
					  		DetailProdGen.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
					  		DetailProdGen.setPanne(Enpanne);
					  		DetailProdGen.setRetard(retard);
					  		DetailProdGen.setAutorisation(false);
					  		DetailProdGen.setProductionGen(production);
					  		  		    		     
					  		  		    		for(int d=0;d<listDetailProdGenTmp.size();d++)
										  		{
										  			if(listDetailProdGenTmp.get(d).getId()==DetailProdGen.getId())
										  			{
										  				listDetailProdGenTmp.set(d, DetailProdGen);
										  			}
										  		}
					  		  		    			}
					  		  		    			
					  		  		    			}
					  		  		    		 
					  		  		    	
					  		  		    	}
					  		  		     
					  		  		     	//	JOptionPane.showMessageDialog(null, listDetailProduction.size());
					  		  		     		
					  		  		     		production.setListDetailProdGen(listDetailProdGenTmp);
					  		  		     		productionDAO.edit(production);
					  		  		     	listDetailProdGenTmp.clear();
					  		  		    listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
					  		  		     	afficher_tableEmployeEmabalage(listDetailProdGenTmp);
					  		     		ViderEmployeEmballage();
					  		     		
								}
			  		     		
			  		     		
				  		     	
				  		     	
						 		
						 	}
						 });
						 btnModifierEmployeEmballage.setBounds(1860, 224, 58, 23);
						 btnModifierEmployeEmballage.setIcon(imgModifier);
						 add(btnModifierEmployeEmballage);
						 
						 if(productionP!=null)
				        	{
								if(productionP.getNumOF()!=null)
					        	{
					        		
					        		production=productionP;
					        		txtNumOF.setSelectedItem(production.getNumOF());
					        		txtQuantiteRealise.setText(quantite);
					        		txtPrixServiceProd.setText(nbreHeure);
					        		
					        		
					        		//	AfficherMatierePremiere();
					        		
					        		
					        		
					        		
					        	}
					        	else {	
					        	production = new Production();
					        	}
				        	}
			        
				 	  		
	}
	
	

	
	
	
	  void AfficherMatierePremiere()
	  {
			
		 
			  
		  	List<CoutMP>	listCoutMPTmp=productionDAO.listeCoutMP(production.getId());
		  	afficherDetailPorduction(production.getArticles().getDetailEstimation(),listCoutMPTmp);
			
			
			codeArticle.setText(production.getArticles().getCodeArticle());
			categorie.addItem(production.getArticles().getLiblle());
			categorie.setSelectedItem(production.getArticles().getLiblle());
			
			
			comboLigneMachine.addItem(production.getLigneMachine().getNom());
			comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
			
			comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
			comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
			String dateFinPrev=dateFormat.format(production.getDateFinFabPre());
			
			List<DetailProdGen> listDetailProdGen=productionDAO.listeDetailProdGen(production.getId());
			List<DetailProduction> listDetailProduction=productionDAO.listeDetailProduction(production.getId());
			List<DetailProdRes> listDetailResponsableProd= detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
			afficher_tableEmployeGen(listDetailResponsableProd);
			
			afficher_tableEmploye(listDetailProduction);
			afficher_tableEmployeEmabalage(listDetailProdGen);
			
			
	  }
	

	

	

void afficher_tableEmploye(List<DetailProduction> listDetailProduction)
	{
	initialiserTableauEmploye();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
	boolean panne=false;
	int nbrPersonnesTravailler=0;
	
		  int i=0;
			while(i<listDetailProduction.size())
			{	
				DetailProduction detailProduction=listDetailProduction.get(i);
				delai=detailProduction.getDelaiEmploye();
				heureSupp25=detailProduction.getHeureSupp25();
				heureSupp50=detailProduction.getHeureSupp50();
				absent=detailProduction.isAbsent();
				sortie=detailProduction.isSortie();
				retard=detailProduction.isRetard();
				panne=detailProduction.isPanne();
				if(absent==false)
				{
					nbrPersonnesTravailler=nbrPersonnesTravailler+1;
					
				}
				Object []ligne={detailProduction.getId(), detailProduction.getEmploye().getId(),detailProduction.getEmploye().getMatricule(),detailProduction.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard,panne};

				modeleEmploye.addRow(ligne);
				i++;
			}
			 
			tableEmploye.setModel(modeleEmploye);
			
			
			
			
			
			labelTotalEquipeProduction.setText("Total Equipe Production : "+nbrPersonnesTravailler);
			
	}



void afficher_tableOffreVariante(List<OffreProduction> listOffreProduction)
{
initialiserTableOffreVariante();
 mapGrammageOffreVariante.clear();

	  int i=0;
		while(i<listOffreProduction.size())
		{	
			OffreProduction offreProduction=listOffreProduction.get(i);
			
			mapGrammageOffreVariante.put(offreProduction.getConditionOffre().getConditionOffre(), offreProduction.getConditionOffre().getValeur());
			
			Object []ligne={offreProduction.getConditionOffre().getConditionOffre(), ""};

			modeleTableOffreVariante.addRow(ligne);
			
		 
		
			i++;
		}
		tableOffreVariante.setModel(modeleTableOffreVariante);
}




void afficher_tableEmployeEmabalage(List<DetailProdGen> listDetailProdGen)
{
	initialiserTableauEquipeEmbalage();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
	boolean sortie=false;
	boolean retard=false;
	boolean panne=false;
	int nbrPersonnesTravailler=0;
	  int i=0;
		while(i<listDetailProdGen.size())
		{	
			DetailProdGen detailProdGen=listDetailProdGen.get(i);
			
			delai =detailProdGen.getDelaiEmploye();
			heureSupp25=detailProdGen.getHeureSupp25();
			heureSupp50=detailProdGen.getHeureSupp50();
			absent=detailProdGen.isAbsent();
			sortie=detailProdGen.isSortie();
			retard=detailProdGen.isRetard();
			panne=detailProdGen.isPanne();
			if(absent==false)
			{
				nbrPersonnesTravailler=nbrPersonnesTravailler+1;
				
			}
			Object []ligne={detailProdGen.getId(), detailProdGen.getEmploye().getId(),detailProdGen.getEmploye().getMatricule(),detailProdGen.getEmploye().getNomafficher(),delai,heureSupp25,heureSupp50,absent,sortie,retard,panne};

			modeleEquipeEm.addRow(ligne);
			i++;
		}
		table_1.setModel(modeleEquipeEm);
		
		labelTotalEquipeEmballage.setText("Total Equipe Emballage : "+nbrPersonnesTravailler);
}

void afficher_tableEmployeGen(List<DetailProdRes> listDetailResponsableProd)
{
initialiserTableauEmployeGen();
BigDecimal delai=BigDecimal.ZERO; 
BigDecimal heureSupp25=BigDecimal.ZERO; 
BigDecimal heureSupp50=BigDecimal.ZERO; 
boolean absent=false;
boolean sortie=false;
boolean retard=false;
int nbrPersonnesTravailler=0;
	  int i=0;
	  boolean erreur=false;
		while(i<listDetailResponsableProd.size())
		{
			
			DetailProdRes detailResponsableProd=listDetailResponsableProd.get(i);
			if(detailResponsableProd.getTotalHeuresProduction().compareTo(BigDecimal.ZERO)!=0)
			{
				delai=(detailResponsableProd.getDelaiEmploye().divide( detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.FLOOR)).setScale(6, RoundingMode.FLOOR).multiply(new BigDecimal(txtPrixServiceProd.getText()));
			}else
			{
				erreur=true;
			}
			if(detailResponsableProd.getTotalHeuresProduction().compareTo(BigDecimal.ZERO)!=0)
			{
				heureSupp25=detailResponsableProd.getHeureSupp25().divide(detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.FLOOR).multiply(new BigDecimal(txtPrixServiceProd.getText()));
			} 
			if(detailResponsableProd.getTotalHeuresProduction().compareTo(BigDecimal.ZERO)!=0)
			{
				heureSupp50=detailResponsableProd.getHeureSupp50().divide(detailResponsableProd.getTotalHeuresProduction(), 6, RoundingMode.FLOOR).multiply(new BigDecimal(txtPrixServiceProd.getText()));
			} 
			
			if(absent==false)
			{
				nbrPersonnesTravailler=nbrPersonnesTravailler+1;
			}
			
			absent=detailResponsableProd.isAbsent();
			sortie=detailResponsableProd.isSortie();
			retard=detailResponsableProd.isRetard();
			Object []ligne={detailResponsableProd.getEmploye().getId(),detailResponsableProd.getEmploye().getMatricule(),detailResponsableProd.getEmploye().getNomafficher(),delai.setScale(4, RoundingMode.FLOOR),heureSupp25.setScale(4, RoundingMode.FLOOR),heureSupp50.setScale(4, RoundingMode.FLOOR),absent,sortie,retard};

			modeleEquipeGen.addRow(ligne);
			i++;
		}
		
		if(erreur==true)
		{
			JOptionPane.showMessageDialog(null, "Le Nombre De Production Declarer dans l'equipe Generique Est Zero veuillez le Changer SVP","Erreur",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tableEmployeGen.setModel(modeleEquipeGen);
}


void afficher_tableCoutHorsProductionEnAttent(List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent)
{
initialiserTableauCoutHorsProductionEnAttent();

int i=0;


while(i<listCoutHorsProdEnAttent.size())
{
	
	 
	CoutHorsProdEnAttent coutHorsProdEnAttent=listCoutHorsProdEnAttent.get(i);
	
 
	 mapDeatailCoutHorsProductionEnAttente.put(coutHorsProdEnAttent.getEmploye().getMatricule()+coutHorsProdEnAttent.getCode(), coutHorsProdEnAttent);
	 
	 
	 Object []ligne={coutHorsProdEnAttent.getCode(), coutHorsProdEnAttent.getEmploye().getId(),coutHorsProdEnAttent.getEmploye().getMatricule(),coutHorsProdEnAttent.getEmploye().getNom(),coutHorsProdEnAttent.getDelaiEmploye(),coutHorsProdEnAttent.getHeure25(),coutHorsProdEnAttent.getHeure50(),false};

		modeleCoutHorsProductionEnAttent.addRow(ligne); 
		
		 	
			 
			
			i++;
} 
		TableDetailCoutHorsProductionEnAttent.setModel(modeleCoutHorsProductionEnAttent);
}


void afficher_tableDetailCoutHorsProductionEnAttent(List<CoutHorsProdEnAttent> listCoutHorsProdEnAttent)
{
initialiserTableauCoutHorsProductionEnAttent();


		int i=0;
		while(i<listCoutHorsProdEnAttent.size())
		{
			 CoutHorsProdEnAttent  coutHorsProdEnAttent= listCoutHorsProdEnAttent.get(i);
			
			 
											
			Object []ligne={coutHorsProdEnAttent.getEmploye().getId(),coutHorsProdEnAttent.getEmploye().getMatricule(),coutHorsProdEnAttent.getEmploye().getNom(),coutHorsProdEnAttent.getDelaiEmploye(),coutHorsProdEnAttent.getHeure25(),coutHorsProdEnAttent.getHeure50()};

			modeleCoutHorsProductionEnAttent.addRow(ligne);
			i++;
		}
	


	  
		TableDetailCoutHorsProductionEnAttent.setModel(modeleCoutHorsProductionEnAttent);
}






	
List<DetailProduction> calculeCoutEmploye(List<DetailProduction> listDetailProduction,Map< Integer, String> mapDelaiEmploye){
		

	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	
	BigDecimal delai=BigDecimal.ZERO;
	 
	 
		BigDecimal remise=BigDecimal.ZERO;
		BigDecimal coutHoraire=BigDecimal.ZERO;
		BigDecimal heureSupp25; 
		BigDecimal heureSupp50; 
		
		BigDecimal coutSupp25=BigDecimal.ZERO;
		BigDecimal coutSupp50=BigDecimal.ZERO;
		
		List<DetailProduction> listDetailProductionTmp= new ArrayList<DetailProduction>();
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
				
				delai=new BigDecimal(mapDelaiEmploye.get(detailProduction.getEmploye().getId()));
				heureSupp25=new BigDecimal(mapHeureSupp25EmployeProd.get(detailProduction.getEmploye().getId()));
				heureSupp50=new BigDecimal(mapHeureSupp50EmployeProd.get(detailProduction.getEmploye().getId()));
			
			if(detailProduction.isAbsent()==true){
	    		
		   		// String code=Utils.genereCodeDateMoisAnnee(production.getDate());
					 
		   		// Utils.compterAbsenceEmploye(code, detailProduction.getEmploye(), production.getDate());
		   		 
		   		 delai=BigDecimal.ZERO;
		   		 heureSupp25=BigDecimal.ZERO;
		   		 heureSupp50=BigDecimal.ZERO;
		   		 
		   		 
		   		 
		   		}
			 if(detailProduction.isAbsent()==false && detailProduction.isSortie()==false && detailProduction.isRetard()==false){
				 	 
				   		if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
						if(detailProduction.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
				   
			 }else
			   	{
			   		remise=BigDecimal.ZERO;
			   	}

			
			
			coutHoraire=delai.multiply(heure.getValeur());	
			//coutHoraire=detailProduction.getEmploye().getCoutHoraire().multiply(delai);
			coutSupp25=heureSupp25.multiply(heure.getValeur().multiply(new BigDecimal(1.25)));
			coutSupp50=heureSupp50.multiply(heure.getValeur().multiply(new BigDecimal(1.5)));
			
			
			 
			 remise=(remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP)).multiply(delai);			 
			
			coutTotalEmploye=coutTotalEmploye.add(coutHoraire).add(coutSupp25).add(coutSupp50).add(remise);
			detailProduction.setCoutTotal(coutHoraire);
			detailProduction.setDelaiEmploye(delai);
			detailProduction.setHeureSupp25(heureSupp25);
			detailProduction.setHeureSupp50(heureSupp50);
			detailProduction.setCoutSupp25(coutSupp25);
			detailProduction.setCoutSupp50(coutSupp50);
			detailProduction.setRemise(remise);
			
			
			if(!detailProduction.getEmploye().isSalarie()){
			FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailProduction.getEmploye().getId());
			if(ficheEmploye!=null){
				/*Remplir fiche programme*/
				
				
				if(delai.compareTo(production.getNbreHeure())<0){
					if(detailProduction.isSortie())
						delaiProd=ficheEmploye.getDelaiProd().add(production.getNbreHeure());
					else 
						delaiProd=ficheEmploye.getDelaiProd().add(delai);
				}else {
					delaiProd=ficheEmploye.getDelaiProd().add(delai);
				}
				
			//	coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
				delai=delai.add(ficheEmploye.getDelaiEmploye()) ;
				String numOF=ficheEmploye.getNumOF()+"-"+production.getNumOF();
				
				
				
		/*	ficheEmploye.setDateSituation(production.getDate());
			
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
			ficheEmploye.setCoutSupp25(coutSupp25);
			ficheEmploye.setCoutSupp50(coutSupp50);*/
			
			ficheEmploye.setNumOF(numOF);
		//	ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setDelaiProd(delaiProd);
			
			ficheEmploye.setDelaiEmploye(delai);
			
			 if(detailProduction.isAbsent()==false && ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd()) >=0){
		   			
		   	
					
					if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 
					remise= remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP).multiply(delai) ;
				 
			 
			 ficheEmploye.setRemise(remise);
			ficheEmployeDAO.edit(ficheEmploye);
			} else{
				ficheEmploye =new FicheEmploye();
				
				ficheEmploye.setNumOF(production.getNumOF());
				ficheEmploye.setDateSituation(production.getDate());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setEmploye(detailProduction.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
			
				if(delai.compareTo(production.getNbreHeure())<0){
					if(detailProduction.isSortie())
						delaiProd=production.getNbreHeure();
					else 
						delaiProd=delai;
				}else {
					delaiProd=delai;
				}
				
				
				 if(detailProduction.isAbsent()==false && delai.compareTo(delaiProd) >=0){
			   			
			   		
						
						if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
						if(detailProduction.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 
				 
				 remise=remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP).multiply(delai) ;
					 
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setDelaiProd(delaiProd);
				ficheEmployeDAO.add(ficheEmploye);
				
			}
			}
			
			listDetailProductionTmp.add(detailProduction);
		}
		}	
		return listDetailProductionTmp;
		
	}











List<DetailProdGen> calculeCoutEmployeEmbalage(List<DetailProdGen> listDetailProdGen,Map< Integer, String> mapDelaiEmployeEmabalage){
	
 	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	 
	
 
	BigDecimal delai=BigDecimal.ZERO;
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutHoraire=BigDecimal.ZERO;
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	
	BigDecimal coutSupp25=BigDecimal.ZERO;
	BigDecimal coutSupp50=BigDecimal.ZERO;
	List<DetailProdGen> listDetailDetailProdGenTmp= new ArrayList<DetailProdGen>();
	for(int i=0;i<listDetailProdGen.size();i++){
		
		DetailProdGen detailProdGen =listDetailProdGen.get(i);
		
		remise=BigDecimal.ZERO;
		delai=BigDecimal.ZERO;
		coutHoraire=BigDecimal.ZERO;
		heureSupp25=BigDecimal.ZERO;
		heureSupp50=BigDecimal.ZERO;
		coutSupp25=BigDecimal.ZERO;
		coutSupp50=BigDecimal.ZERO;
		
		if(!detailProdGen.getEmploye().isSalarie()){
			
			delai=new BigDecimal(mapDelaiEmployeEmabalage.get(detailProdGen.getEmploye().getId()));
			heureSupp25=new BigDecimal(mapHeureSupp25EmployeEmbalage.get(detailProdGen.getEmploye().getId()));
			heureSupp50=new BigDecimal(mapHeureSupp50EmployeEmbalage.get(detailProdGen.getEmploye().getId()));
		
		if(detailProdGen.isAbsent()==true){
    		
   		 String code=Utils.genereCodeDateMoisAnnee(production.getDate());
   		 Utils.compterAbsenceEmploye(code, detailProdGen.getEmploye(), production.getDate());
   		 delai=BigDecimal.ZERO;
   		 heureSupp25=BigDecimal.ZERO;
   		 heureSupp50=BigDecimal.ZERO;
   	} 
		
		 
		coutHoraire=delai.multiply(heure.getValeur());	
		//coutHoraire=detailProdGen.getEmploye().getCoutHoraire().multiply(delai);
		coutSupp25=heureSupp25.multiply(heure.getValeur().multiply(new BigDecimal(1.25)));
		coutSupp50=heureSupp50.multiply(heure.getValeur().multiply(new BigDecimal(1.5)));
		
		 if(detailProdGen.isAbsent()==false && detailProdGen.isSortie()==false && detailProdGen.isRetard()==false){
			 
			   
			   		if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProdGen.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
			    
			
			
		}else {
			remise=BigDecimal.ZERO;
		}
		
		 
			remise=(remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP)).multiply(delai) ;
		 
		coutTotalEmployeEmbalage=coutTotalEmployeEmbalage.add(coutHoraire).add(coutSupp25).add(coutSupp50).add(remise);
		detailProdGen.setCoutTotal(coutHoraire);
		detailProdGen.setDelaiEmploye(delai);
		detailProdGen.setRemise(remise);
		detailProdGen.setHeureSupp25(heureSupp25);
		detailProdGen.setHeureSupp50(heureSupp50);
		detailProdGen.setCoutSupp25(coutSupp25);
		detailProdGen.setCoutSupp50(coutSupp50);
		
		if(!detailProdGen.getEmploye().isSalarie()){
		FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailProdGen.getEmploye().getId());
		if(ficheEmploye!=null){
			/*Remplir fiche programme*/
			//coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal());
			delai=delai.add(ficheEmploye.getDelaiEmploye());
			String numOF=ficheEmploye.getNumOF()+"-"+production.getNumOF();
			BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(production.getNbreHeure()) ;
	/*	ficheEmploye.setDateSituation(production.getDate());
		
		ficheEmploye.setEmploye(detailProdGen.getEmploye());;
		
		ficheEmploye.setHeureSupp25(heureSupp25);
		ficheEmploye.setHeureSupp50(heureSupp50);
		ficheEmploye.setCoutSupp25(coutSupp25);
		ficheEmploye.setCoutSupp50(coutSupp50);*/
		
		ficheEmploye.setNumOF(numOF);
	//	ficheEmploye.setCoutTotal(coutHoraire);
		ficheEmploye.setDelaiProd(delaiProd);
		
		ficheEmploye.setDelaiEmploye(delai);
		
		 if(detailProdGen.isAbsent()==false && detailProdGen.isSortie()==false && detailProdGen.isRetard()==false){
	   			
				
				if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
					remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
				if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
					remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
	   			
	   		}else {
	   			remise=BigDecimal.ZERO;
	   		}
		 
			 
				remise=(remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP)).multiply(delai) ;
			 
			
		 ficheEmploye.setRemise(remise);
		ficheEmployeDAO.edit(ficheEmploye);
		}else {
			ficheEmploye =new FicheEmploye();
		//	ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setNumOF(production.getNumOF());
			ficheEmploye.setDateSituation(production.getDate());
			ficheEmploye.setDelaiEmploye(delai);
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
		//	ficheEmploye.setCoutSupp25(coutSupp25);
		//	ficheEmploye.setCoutSupp50(coutSupp50);
			
			
			 if(detailProdGen.isAbsent()==false && detailProdGen.isSortie()==false && detailProdGen.isRetard()==false){
		   			
					
					if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					if(detailProdGen.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=mapParametre.get(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 
				 
			 remise=remise.divide(new BigDecimal(8), 6, RoundingMode.HALF_UP).multiply(delai) ;
				 
			 
			 ficheEmploye.setRemise(remise);
			 ficheEmploye.setDelaiProd(production.getNbreHeure());
			ficheEmployeDAO.add(ficheEmploye);
			
		}
		
		}
		listDetailDetailProdGenTmp.add(detailProdGen);
	}
	}
	return listDetailDetailProdGenTmp;
	
}




void afficherDetailPorduction(List<DetailEstimation> lisDetailEstimation,List<CoutMP> listCoutMP){
	
	 
	
	
	for(int d=0;d<listCoutMP.size();d++){
		
		
		if(listCoutMP.get(d).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		
		{
				
				listCoutMP.get(d).setQuantite(listCoutMP.get(d).getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
			
	
		}
		
		
	
		
	}
	
	
	
	/*
	
	for(int k=0;k<lisDetailEstimation.size();k++){
		
		detailEstimation=lisDetailEstimation.get(k);
		for(int l=0;l<listCoutMP.size();l++){
			coutMP=listCoutMP.get(l);
			
			if(detailEstimation.getMatierePremier().getId()==coutMP.getMatierePremier().getId()){
			
					quantiteConsommme=detailEstimation.getQuantite().multiply(new BigDecimal(txtQuantiteRealise.getText()));
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)){
						
						
						if(coutMP.getEstimation()!=null)
						{
							
							if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
							{
								
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0 ,RoundingMode.DOWN);
								
							}else
							{
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0 ,RoundingMode.DOWN);
							}
							
							
						}else
						{
							quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0 ,RoundingMode.DOWN);
						}
						
						
						
						
						quantitecarton=quantiteConsommme.setScale(0,RoundingMode.DOWN );
						quantiteCalculerCarton=coutMP.getQuantite();
						
						
							
						} 
				
			
			}
			
			
			
			
		}
	}
	/*
	 * 
	 */
	
	
	/*
	quantiteConsommme=BigDecimal.ZERO;
	
	
	
	
	for(int i=0;i<lisDetailEstimation.size();i++){
		
		detailEstimation=lisDetailEstimation.get(i);
		for(int j=0;j<listCoutMP.size();j++){
			coutMP=listCoutMP.get(j);
			
			if(detailEstimation.getMatierePremier().getId()==coutMP.getMatierePremier().getId()){
			
					
				
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
					
					if(articleMixte==true){
						if(detailEstimation.getPriorite()==PRIORITE_ESTIMATION_2){
							
							if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
							{
								
								if( coutMP.getMoinsPlus()==null)
								{
									
									
									if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
									{
										if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
										{
											
											coutMP.setQuantConsomme(coutMP.getQuantExistante());
											listCoutMP.set(j,coutMP);

										}else
										{
											coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
											listCoutMP.set(j,coutMP);
										}
										
									
									
									
									}else
									{
										coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
										listCoutMP.set(j,coutMP);
									}
									
									
								
									
								}else
								{
									
									if(coutMP.getMoinsPlus().equals(""))
									{
										
										
										if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
										{
											if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
											{
												
												coutMP.setQuantConsomme(coutMP.getQuantExistante());
												listCoutMP.set(j,coutMP);

											}else
											{
												coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
												listCoutMP.set(j,coutMP);
											}
											
										
										
										}else
										{
											coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
											listCoutMP.set(j,coutMP);
										}
										
									
										
										
										
										
										
									}else
									{
										
										if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
										{
											
											if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) <0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_MOINS))
												{
													
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
												}else
												{
													
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
														}
														
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
												
													
												}
												
												
												
											}else 	if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) >0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_PLUS))
												{
													
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
												}else
												{
													
													
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
														}
														
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
													
												}
												
												
												
											}
											
											
											
										}
										
									}
									
								
								
								}
								
								
								
							}else
							{
								
								
								quantiteConsommme=coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
								listCoutMP.set(j,coutMP);
								
								
								
							}
							
							
							
						
							
							
							
						}
					}else{
						
						if(detailEstimation.getPriorite()==PRIORITE_ESTIMATION_1){
							
							if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
							{
								
								
								if( coutMP.getMoinsPlus()==null)
								{
									if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
									{
										if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
										{
											
											coutMP.setQuantConsomme(coutMP.getQuantExistante());
											listCoutMP.set(j,coutMP);

										}else
										{
											coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
											listCoutMP.set(j,coutMP);
										}
										
									
									
									}else
									{
										coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
										listCoutMP.set(j,coutMP);
									}
									
								
									
								}else
								{
									
									if(coutMP.getMoinsPlus().equals(""))
									{
										
										if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
										{
											if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
											{
												
												coutMP.setQuantConsomme(coutMP.getQuantExistante());
												listCoutMP.set(j,coutMP);

											}else
											{
												coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
												listCoutMP.set(j,coutMP);
											}
											
											
										
										
										}else
										{
											coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
											listCoutMP.set(j,coutMP);
										}
										
										
										
									}else
									{
										
										if(NombreDeMonqueEtPlus.compareTo(BigDecimal.ZERO)!=0)
										{
											
											if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) <0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_MOINS))
												{
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
													
												}else
												{
													
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
															
														}
														
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
												
												}
												
												
												
												
											}else if(new BigDecimal(txtQuantiteRealise.getText()).compareTo(listCoutMP.get(j).getProdcutionCM().getQuantiteEstime()) >0)
											{
												if(listCoutMP.get(j).getMoinsPlus().equals(Constantes.MANQUE_PLUS))
												{
													quantiteConsommme=(new BigDecimal(txtQuantiteRealise.getText()).subtract(quantiteChargerSansMoinsOuPlus)).multiply(coutMP.getEstimation().divide(PercentageMonqueEtPlus, 6, RoundingMode.HALF_DOWN));
													if(quantiteConsommme.compareTo(BigDecimal.ZERO)<0)
													{
														quantiteConsommme=quantiteConsommme.multiply(new BigDecimal(-1));
														
													}
														
														coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													
													
												}else
												{
													if(coutMP.getQuantCharge().compareTo(BigDecimal.ZERO)==0)
													{
														if(coutMP.getQuantExistante().compareTo(BigDecimal.ZERO)!=0)
														{
															
															coutMP.setQuantConsomme(coutMP.getQuantExistante());
															listCoutMP.set(j,coutMP);

														}else
														{
															coutMP.setQuantConsomme(coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText())));
															listCoutMP.set(j,coutMP);
														
															
														}
														
													
													
													}else
													{
														coutMP.setQuantConsomme(coutMP.getQuantCharge().add(coutMP.getQuantExistante()));
														listCoutMP.set(j,coutMP);
													}
													
												
												}
												
												
												
												
											}
											
									
											
											
											
											
											
											
											
										}
										
									}
									
								
								
								}
								
								
							}else
							{
								
								quantiteConsommme=coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								coutMP.setQuantConsomme(quantiteConsommme.add(coutMP.getQuantExistante()));
								listCoutMP.set(j,coutMP);
								
							}
							
								
							
						}
					}
				
				
				}else {
					
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SACHET) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC))
					{
						
						if( detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) )
						{
							
								if(coutMP.getEstimation()!=null)
								{
									
									if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
									{
										
										quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0, RoundingMode.DOWN);
									}else
									{
										quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
									}
									
								}else
								{
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								}
								
							
							
						}else if( detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC) )
						{
							if(coutMP.getEstimation()!=null)
							{
								if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
								{
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0, RoundingMode.DOWN);
									
								}else
								{
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								}
								
								
							}else
							{
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								
							}
							
							

							
						}else
						{
							if(coutMP.getEstimation()!=null)
							{
								if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
								{
									
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(coutMP.getEstimation(), 0, RoundingMode.DOWN);
									
								}else
								{
									
									quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
									
								}
								
							}else
							{
								
								quantiteConsommme=new BigDecimal(txtQuantiteRealise.getText()).divide(detailEstimation.getQuantite(), 0, RoundingMode.DOWN);
								
							}
							
						}
						
						
						
						
						
						
					}else
					{
						if(coutMP.getEstimation()!=null)
						{
							if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
							{
								
								quantiteConsommme=coutMP.getEstimation().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								
							}else
							{
								
								quantiteConsommme=detailEstimation.getQuantite().multiply(new BigDecimal(txtQuantiteRealise.getText()));
								
							}
							
						}else
						{
							
							quantiteConsommme=detailEstimation.getQuantite().multiply(new BigDecimal(txtQuantiteRealise.getText()));
						}
						
					}
					
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SACHET) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(UNITE_PIECE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_SAC)){
						
						
						
						quantiteConsommme=quantiteConsommme.setScale(0,RoundingMode.DOWN);
						
							
						} 
					
					if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_ADHESIF))
						{
						
						if(coutMP.getEstimation()!=null)
						{
							if(coutMP.getEstimation().compareTo(BigDecimal.ZERO)!=0)
							{
								quantiteConsommme=(coutMP.getEstimation().multiply(quantitecarton)).setScale(0, RoundingMode.HALF_UP);
								
							}else
							{
								quantiteConsommme=(detailEstimation.getQuantite().multiply(quantitecarton)).setScale(0, RoundingMode.HALF_UP);
							}
							
						}else
						{
							
							quantiteConsommme=(detailEstimation.getQuantite().multiply(quantitecarton)).setScale(0, RoundingMode.HALF_UP);
							
						}
							
							
							
							
							
						}
					
					
					
					// Calculer La Quantite Consomme des Offres = (QuantiteCalculerOffre / QuantiteCalculerCartons ) * QuantiteConsommeCarton
						 if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						{
							
							
							quantiteConsommme=coutMP.getQuantite().divide(quantiteCalculerCarton, 6, RoundingMode.HALF_UP).multiply(quantitecarton);
							
							
						}
					
					coutMP.setQuantConsomme(quantiteConsommme);
					listCoutMP.set(j,coutMP);
				}
				
				
				
				
				}
		
				
			
			}
			
		}
	
	
	

	
	
	
	
	
	
	
	
	for(int k=0; k<listCoutMP.size();k++)
	{
		boolean trouve=false;
		
		
		CoutMP coutMPTemp=listCoutMP.get(k);
		for(int d=0;d<lisDetailEstimation.size();d++)
		{
			
			DetailEstimation detailEstimationtemp=lisDetailEstimation.get(d);
			
			if(coutMPTemp.getMatierePremier().getId()==detailEstimationtemp.getMatierePremier().getId())
			{
				trouve=true;
			}
			
		}
		
		if(trouve==false)
		{
			coutMPTemp.setQuantConsomme(BigDecimal.ZERO);
			
			
		}
		
		
		
	}
	
	*/
	
//	remplirQuantiteOffreMP( listCoutMP);
	
	
/*	if(quantiteRealise>0){
		
			coutMP=listCoutMP.get(position);
			BigDecimal quantite=coutMP.getQuantConsomme()+quantiteRealise;
			coutMP.setQuantConsomme(quantite);
			listCoutMP.set(position, coutMP);
		
	}*/
		
	}






void calculerStockCoutProduitFini(){
	
	
	
	transferStockPFDAO.ViderSession();
	
	BigDecimal coutPF=BigDecimal.ZERO;
	BigDecimal nouveauCout=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO ;
	BigDecimal coutStock=BigDecimal.ZERO;
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	CoutArticlePF coutArticlePF=coutArticlePFDAO.CoutArticlePFByArticle(production.getArticles());
	
	if(coutArticlePF!=null)
	{
		
			
		
		DetailCoutArticlePF detailCoutArticlePF=detailCoutArticlePFDAO.DetailCoutArticlePFParCodeTransfert(production.getNumOF());
		if(detailCoutArticlePF!=null)
		{
			coutArticlePF.setCoutEquipeGenerique(coutArticlePF.getCoutEquipeGenerique().subtract(detailCoutArticlePF.getCoutEquipeGenerique()).add(production.getCoutTotalEmployeGen()));
			coutArticlePF.setCoutEquipeEmballage(coutArticlePF.getCoutEquipeEmballage().subtract(detailCoutArticlePF.getCoutEquipeEmballage()).add(production.getCoutTotalEmployeEmbalage()));
			coutArticlePF.setCoutEquipeProduction(coutArticlePF.getCoutEquipeProduction().subtract(detailCoutArticlePF.getCoutEquipeProduction()).add( production.getCoutTotalEmploye().add(production.getCoutTotalHorsProductionEnAttent())));
			coutArticlePF.setTotal(coutArticlePF.getCoutEquipeEmballage().add(coutArticlePF.getCoutEquipeGenerique().add(coutArticlePF.getCoutEquipeProduction().add(coutArticlePF.getCoutMP()))));
			coutArticlePF.setCout(coutArticlePF.getTotal().divide(coutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			coutArticlePFDAO.edit(coutArticlePF);
			 
			detailCoutArticlePF.setCoutEquipeGenerique(production.getCoutTotalEmployeGen());
			detailCoutArticlePF.setCoutEquipeEmballage(production.getCoutTotalEmployeEmbalage());
			detailCoutArticlePF.setCoutEquipeProduction(production.getCoutTotalEmploye().add(production.getCoutTotalHorsProductionEnAttent()));
			detailCoutArticlePF.setTotal(detailCoutArticlePF.getCoutEquipeEmballage().add(detailCoutArticlePF.getCoutEquipeGenerique().add(detailCoutArticlePF.getCoutEquipeProduction().add(detailCoutArticlePF.getCoutMP()))));
			detailCoutArticlePF.setCout(detailCoutArticlePF.getTotal().divide(detailCoutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			detailCoutArticlePF.setCoutArticlePF(coutArticlePF);
			detailCoutArticlePF.setDate(production.getDate());	
			detailCoutArticlePFDAO.edit(detailCoutArticlePF);	
			
			
			
			
		}else
		{
			coutArticlePF.setCoutEquipeGenerique(coutArticlePF.getCoutEquipeGenerique().add(production.getCoutTotalEmployeGen()));
			coutArticlePF.setCoutEquipeEmballage(coutArticlePF.getCoutEquipeEmballage().add(production.getCoutTotalEmployeEmbalage()));
			coutArticlePF.setCoutEquipeProduction(coutArticlePF.getCoutEquipeProduction().add( production.getCoutTotalEmploye().add(production.getCoutTotalHorsProductionEnAttent())));
			coutArticlePF.setCoutMP(coutArticlePF.getCoutMP().add(production.getCoutTotalMP().add(production.getCoutDechet())) );
			coutArticlePF.setQuantiteRealiser(coutArticlePF.getQuantiteRealiser().add(production.getQuantiteReel()) );
			coutArticlePF.setTotal(coutArticlePF.getCoutEquipeEmballage().add(coutArticlePF.getCoutEquipeGenerique().add(coutArticlePF.getCoutEquipeProduction().add(coutArticlePF.getCoutMP()))));
			coutArticlePF.setCout(coutArticlePF.getTotal().divide(coutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			coutArticlePFDAO.add(coutArticlePF);
			
			
			
			DetailCoutArticlePF detailCoutArticlePFTmp=new DetailCoutArticlePF();
			detailCoutArticlePFTmp.setArticles(production.getArticles());
			detailCoutArticlePFTmp.setCoutEquipeGenerique(production.getCoutTotalEmployeGen());
			detailCoutArticlePFTmp.setCoutEquipeEmballage(production.getCoutTotalEmployeEmbalage());
			detailCoutArticlePFTmp.setCoutEquipeProduction(production.getCoutTotalEmploye().add(production.getCoutTotalHorsProductionEnAttent()));
			detailCoutArticlePFTmp.setCoutMP(production.getCoutTotalMP().add(production.getCoutDechet()));
			detailCoutArticlePFTmp.setQuantiteRealiser(production.getQuantiteReel());
			detailCoutArticlePFTmp.setTotal(detailCoutArticlePFTmp.getCoutEquipeEmballage().add(detailCoutArticlePFTmp.getCoutEquipeGenerique().add(detailCoutArticlePFTmp.getCoutEquipeProduction().add(detailCoutArticlePFTmp.getCoutMP()))));
			detailCoutArticlePFTmp.setCout(detailCoutArticlePFTmp.getTotal().divide(detailCoutArticlePFTmp.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			detailCoutArticlePFTmp.setCoutArticlePF(coutArticlePF);
			detailCoutArticlePFTmp.setDate(production.getDate());	
			detailCoutArticlePFTmp.setCodeTransfer(production.getNumOF());
			detailCoutArticlePFDAO.add(detailCoutArticlePFTmp);	
			
			
		}
		
		
		
		
	}else
	{
		
		CoutArticlePF coutArticlePFTmp=new CoutArticlePF();	
		coutArticlePFTmp.setArticles(production.getArticles());
		coutArticlePFTmp.setCoutEquipeGenerique(production.getCoutTotalEmployeGen());
		coutArticlePFTmp.setCoutEquipeEmballage(production.getCoutTotalEmployeEmbalage());
		coutArticlePFTmp.setCoutEquipeProduction(production.getCoutTotalEmploye().add(production.getCoutTotalHorsProductionEnAttent()));
		coutArticlePFTmp.setCoutMP(production.getCoutTotalMP().add(production.getCoutDechet()));
		coutArticlePFTmp.setQuantiteRealiser(production.getQuantiteReel());
		coutArticlePFTmp.setTotal(coutArticlePFTmp.getCoutEquipeEmballage().add(coutArticlePFTmp.getCoutEquipeGenerique().add(coutArticlePFTmp.getCoutEquipeProduction().add(coutArticlePFTmp.getCoutMP()))));
		coutArticlePFTmp.setCout(coutArticlePFTmp.getTotal().divide(coutArticlePFTmp.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
		coutArticlePFDAO.add(coutArticlePFTmp);
	
	DetailCoutArticlePF detailCoutArticlePF=new DetailCoutArticlePF();
	detailCoutArticlePF.setArticles(production.getArticles());
	detailCoutArticlePF.setCoutEquipeGenerique(production.getCoutTotalEmployeGen());
	detailCoutArticlePF.setCoutEquipeEmballage(production.getCoutTotalEmployeEmbalage());
	detailCoutArticlePF.setCoutEquipeProduction(production.getCoutTotalEmploye().add(production.getCoutTotalHorsProductionEnAttent()));
	detailCoutArticlePF.setCoutMP(production.getCoutTotalMP().add(production.getCoutDechet()));
	detailCoutArticlePF.setQuantiteRealiser(production.getQuantiteReel());
	detailCoutArticlePF.setTotal(detailCoutArticlePF.getCoutEquipeEmballage().add(detailCoutArticlePF.getCoutEquipeGenerique().add(detailCoutArticlePF.getCoutEquipeProduction().add(detailCoutArticlePF.getCoutMP()))));
	detailCoutArticlePF.setCout(detailCoutArticlePF.getTotal().divide(detailCoutArticlePF.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
	detailCoutArticlePF.setCoutArticlePF(coutArticlePFTmp);
	detailCoutArticlePF.setDate(production.getDate());	
	detailCoutArticlePF.setCodeTransfer(production.getNumOF());
	detailCoutArticlePFDAO.add(detailCoutArticlePF);
	
	}
	
	 
	
	 
 
	
	
}

/*BigDecimal determinerRemiseEmploye(Equipe equipe){
	BigDecimal remiseEmploye = BigDecimal.ZERO;
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	Articles article=production.getArticles();
	
	if(delaiTotal.compareTo(BigDecimal.ZERO) !=0){
		if(article.getConditionnement().compareTo(new BigDecimal(2))  ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_500G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(5))  ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_200G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(10)) ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_100G);
			quantiteTounage=parametre.getValeur();
		}
	
	if(production.getQuantiteReel().compareTo(quantiteTounage)>=0)
		remiseEmploye=equipe.getRemise().divide(delaiTotal, 6, BigDecimal.ROUND_HALF_UP) ;
	}
	
	return remiseEmploye;
	
}*/

/*BigDecimal determinerRemiseEmployeEmbalage(Equipe equipe){
	BigDecimal remiseEmploye = BigDecimal.ZERO;
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	Articles article=production.getArticles();
	if(delaiTotalEquipeEmbalage.compareTo(BigDecimal.ZERO)  !=0){
		if(article.getConditionnement() .compareTo(new BigDecimal(2)) ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_500G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(5)) ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_200G);
			quantiteTounage=parametre.getValeur();
		}
		if(article.getConditionnement().compareTo(new BigDecimal(10))  ==0){ 
			Parametre parametre = parametreDAO.findByCode(PARAMETRE_CODE_QUANTITE_TOUNAGE_100G);
			quantiteTounage=parametre.getValeur();
		}
			
		if(production.getQuantiteReel().compareTo(quantiteTounage) >=0)
			remiseEmploye=equipe.getRemise().divide(delaiTotalEquipeEmbalage, 6, BigDecimal.ROUND_HALF_UP);
	}
	
	return remiseEmploye;
	
}*/

void calculRemiseResponsableProduction(Date dateProd, String periode){
	
	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	BigDecimal coutResponsableProd=BigDecimal.ZERO;
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	BigDecimal coutTotal=BigDecimal.ZERO;

	 
    
	coutTotalAutreEmploye=BigDecimal.ZERO;

 
	//	 if(production.getStatut().equals(ETAT_OF_TERMINER) ){
			 List<DetailProdRes> listeDetailResponsableProd =detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
			 
			 
			 for(int j=0;j<listeDetailResponsableProd.size();j++){

				 DetailProdRes detailResponsableProd=listeDetailResponsableProd.get(j);
				 
				 Employe employe=detailResponsableProd.getEmploye();
				 remise=BigDecimal.ZERO;				 				

				 if(!employe.isSalarie()){
					 
					 if(detailResponsableProd.isAbsent()==true){
				    		
				   		 String code=Utils.genereCodeDateMoisAnnee(production.getDate());
							 
				   		 Utils.compterAbsenceEmploye(code, detailResponsableProd.getEmploye(), production.getDate());
				   		}else if(detailResponsableProd.isAbsent()==false && detailResponsableProd.isSortie()==false && detailResponsableProd.isRetard()==false){
							
							if(detailResponsableProd.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							{
								Parametre parametre=ParametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);   
								if(parametre!=null)
								{
									remise=  parametre.getValeur();
								}else
								{
									remise=  BigDecimal.ZERO;
								}
								
							}
								
							if(detailResponsableProd.getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							{
								
								Parametre parametre=ParametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);   
								
								if(parametre!=null)
								{
									remise=  parametre.getValeur();
								}else
								{
									remise=  BigDecimal.ZERO;
								}
								
							}
							
				   			
				   		}
					 
					 
					 
					 detailResponsableProd.setCoutTotal(detailResponsableProd.getDelaiEmploye().multiply(heure.getValeur()));
					 detailResponsableProd.setCoutSupp25(detailResponsableProd.getHeureSupp25().multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25)));
					 detailResponsableProd.setCoutSupp50(detailResponsableProd.getHeureSupp50().multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50)));					
					 detailResponsableProd.setRemise(remise);					 
					 detailProdResDAO.edit(detailResponsableProd);
					 
					 
				 }
				 }
					 
			 ModifierCoutDetailProdresponsable( dateProd,  periode); 
					 
					 
					 
					/*
						
					//	coutTotalAutreEmploye=coutTotalAutreEmploye+detailResponsableProd.getCoutTotal()+detailResponsableProd.getCoutSupp25()+detailResponsableProd.getCoutSupp50();
						
						BigDecimal coutTotal=detailResponsableProd.getEmploye().getCoutHoraire().multiply(detailResponsableProd.getDelaiEmploye()) ;
						
							FicheEmploye ficheEmploye=ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailResponsableProd.getEmploye().getId());
							
							if(ficheEmploye!=null){
								
								
							//	ficheEmploye.setCoutTotal(coutTotal);
								ficheEmploye.setNumOF(production.getNumOF());
								ficheEmploye.setDateSituation(production.getDate());
								ficheEmploye.setDelaiEmploye(detailResponsableProd.getDelaiEmploye());
								ficheEmploye.setHeureSupp25(detailResponsableProd.getHeureSupp25());
								ficheEmploye.setHeureSupp50(detailResponsableProd.getHeureSupp50());
							//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
							//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
								ficheEmploye.setRemise(remise);
								ficheEmploye.setEmploye(detailResponsableProd.getEmploye());
								
						
								ficheEmployeDAO.edit(ficheEmploye);
								}else {
									 ficheEmploye=new FicheEmploye();
									
								//	ficheEmploye.setCoutTotal(coutTotal);
									ficheEmploye.setNumOF(production.getNumOF());
									ficheEmploye.setDateSituation(production.getDate());
									ficheEmploye.setDelaiEmploye(detailResponsableProd.getDelaiEmploye());
									ficheEmploye.setHeureSupp25(detailResponsableProd.getHeureSupp25());
									ficheEmploye.setHeureSupp50(detailResponsableProd.getHeureSupp50());
								//	ficheEmploye.setCoutSupp25(detailResponsableProd.getCoutSupp25());
								//	ficheEmploye.setCoutSupp50(detailResponsableProd.getCoutSupp50());
									ficheEmploye.setRemise(remise);
									ficheEmploye.setEmploye(detailResponsableProd.getEmploye());
									
									ficheEmployeDAO.add(ficheEmploye);
									
								}
							*/
					//	listeDetailResponsableProdTmp.add(detailResponsableProd);
						
			/*#############################################
			  Metter à jour les délais des employés Génériques
			   ###########################################*/
			// majDelaiEmployeGenerique(listeProduction);
			 /*###########################################*/
				 
			// }
	
//	return listeDetailResponsableProdTmp;
}



public void ModifierCoutDetailProdresponsable(Date dateProd, String periode)
{
	
	Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
	BigDecimal quantiteTounage=BigDecimal.ZERO; 
	BigDecimal coutResponsableProd=BigDecimal.ZERO;
	BigDecimal remise=BigDecimal.ZERO;
	BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	BigDecimal coutTotalEmployeGenParProduction=BigDecimal.ZERO;
	BigDecimal coutTotal=BigDecimal.ZERO;
	 
	 
	 List<DetailProdRes> listeDetailResponsableProd =detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");

	
	 ////////////////////////////////////////////////////////////////////////Modifier Cout DetailProdResponsable //////////////////////////////////////////////////////////////////////////////////////////////////
	
	 BigDecimal delaiEmploye=BigDecimal.ZERO;
	 BigDecimal coutHeure25=BigDecimal.ZERO;
	 BigDecimal delaiHeure25=BigDecimal.ZERO;
	 BigDecimal coutHeure50=BigDecimal.ZERO;
	 BigDecimal delaiHeure50=BigDecimal.ZERO;
	 BigDecimal TotalCoutHeureCalculer=BigDecimal.ZERO;
	 BigDecimal TotalCoutHeure25Calculer=BigDecimal.ZERO;
	 BigDecimal TotalCoutHeure50Calculer=BigDecimal.ZERO;
	 BigDecimal TotalRemiseCalculer=BigDecimal.ZERO;
	 
	 BigDecimal totalHeuresproduction=BigDecimal.ZERO;
	  
	 
	 
	 for(int j=0;j<listeDetailResponsableProd.size();j++)
	 {
		 totalHeuresproduction=listeDetailResponsableProd.get(j).getTotalHeuresProduction();
	 }
	 if(totalHeuresproduction.compareTo(BigDecimal.ZERO)==0)
	 {
		 totalHeuresproduction=BigDecimal.ONE; 
	 }
	 
	 
	 
			coutTotalEmployeGen=BigDecimal.ZERO;
				 
				 
					
					 
					 
					 for(int k=0;k<listeDetailResponsableProd.size();k++){

						 DetailProdRes detailResponsableProdTmp=listeDetailResponsableProd.get(k);
						 
						 if(!detailResponsableProdTmp.getEmploye().isSalarie()){
 							 
							 TotalCoutHeureCalculer=TotalCoutHeureCalculer.add(detailResponsableProdTmp.getDelaiEmploye().multiply(heure.getValeur()));
							 TotalCoutHeure25Calculer=TotalCoutHeure25Calculer.add(detailResponsableProdTmp.getHeureSupp25().multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50)));
							 TotalCoutHeure50Calculer=TotalCoutHeure50Calculer.add(detailResponsableProdTmp.getHeureSupp50().multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25)));
							 if(detailResponsableProdTmp.getRemise()!=null)
							 {
								 
								 TotalRemiseCalculer=TotalRemiseCalculer.add((detailResponsableProdTmp.getRemise().divide(new BigDecimal(8), 6, BigDecimal.ROUND_HALF_UP)).multiply(detailResponsableProdTmp.getDelaiEmploye()));

								 
							 }
							  
						
						 }
						 
					 }
					 
					 
					 coutTotalEmployeGen=coutTotalEmployeGen.add(TotalCoutHeureCalculer).add(TotalCoutHeure25Calculer).add(TotalCoutHeure50Calculer).add(TotalRemiseCalculer);
					 
					 coutTotalEmployeGenParProduction=(coutTotalEmployeGen.divide(totalHeuresproduction,6,BigDecimal.ROUND_HALF_UP));
					 
					 
					 
					 coutTotal=production.getCoutTotalMP().add(production.getCoutDechet()).add(production.getCoutTotalEmploye()).add(production.getCoutTotalEmployeEmbalage().add(production.getCoutTotalHorsProductionEnAttent())).add(coutTotalEmployeGenParProduction.multiply(production.getNbreHeure()))  ;
					 
					
					 production.setCoutTotalEmployeGen(coutTotalEmployeGenParProduction.multiply(production.getNbreHeure()));
					 production.setCoutTotal(coutTotal);
					 productionDAO.edit(production);
						 
				 
			
				 
	 
	 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		 

	
	
}







void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","ID EMPLOYE","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard","Panne"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,false,true,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class,Boolean.class,Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
	    	    
	    	   
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		
     		if(column==4 || column==5 || column==6 || column==7 || column==8 || column==9 || column==10)
     		{
     			return false;
     		}else
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
		   tableEmploye.getTableHeader().setReorderingAllowed(false);
		   tableEmploye.setColumnSelectionAllowed(false);
}


void initialiserTableOffreVariante(){
	 
		    
		   modeleTableOffreVariante =new DefaultTableModel(
				     	new Object[][] {
				     	},
				     	new String[] {
				     			"OffrePF", "Nombre Carton"
				     	}
				     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,true
		     	};
		    
		     	Class[] columnTypes = new Class[] {
		     			String.class,String.class
					};
		      	
			       public Class getColumnClass(int columnIndex) {
							return columnTypes[columnIndex];
						}
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
	   
		   
		   
}


void initialiserTableauCoutHorsProductionEnAttent(){
	modeleCoutHorsProductionEnAttent =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     		"Code",	"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%" ,"Valider"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,false,false,false,false ,true 
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,String.class,String.class,BigDecimal.class,BigDecimal.class ,Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
		   TableDetailCoutHorsProductionEnAttent.setModel(modeleCoutHorsProductionEnAttent); 
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(0).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(1).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(2).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(3).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(4).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(5).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(6).setPreferredWidth(60);
		   TableDetailCoutHorsProductionEnAttent.getColumnModel().getColumn(7).setPreferredWidth(60);
		    
		   TableDetailCoutHorsProductionEnAttent.getTableHeader().setReorderingAllowed(false);
}

void initialiserTableauEmployeGen(){
	modeleEquipeGen =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class,Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		
     		if(column==3 || column==4 || column==5 || column==6 || column==7 || column==8)
     		{
     			return false;
     		}else
     		return columnEditables[column];
     	}
     };
		   tableEmployeGen.setModel(modeleEquipeGen); 
		   tableEmployeGen.getColumnModel().getColumn(0).setPreferredWidth(1);
		   tableEmployeGen.getColumnModel().getColumn(1).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(2).setPreferredWidth(160);
		   tableEmployeGen.getColumnModel().getColumn(3).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(4).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(5).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(6).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(7).setPreferredWidth(60);
		   tableEmployeGen.getColumnModel().getColumn(8).setPreferredWidth(60);
		   tableEmployeGen.getTableHeader().setReorderingAllowed(false);
}

void initialiserTableauEquipeEmbalage(){
	
	modeleEquipeEm =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","ID Employee","Matricule","Nom","Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent","Sortie","Retard","Panne"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,false,true,true,true,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		
     		if(column==4 || column==5 || column==6 || column==7 || column==8 || column==9 || column==10)
     		{
     			return false;
     		}else
     		return columnEditables[column];
     	}
     };
		     
		     
		     table_1.setModel(modeleEquipeEm); 
		     table_1.getColumnModel().getColumn(0).setPreferredWidth(1);
		     table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(2).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(3).setPreferredWidth(160);
		     table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(5).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(6).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(7).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(8).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(9).setPreferredWidth(60);
		     table_1.getColumnModel().getColumn(10).setPreferredWidth(60);
		     table_1.getTableHeader().setReorderingAllowed(false);
}

/*List<DetailProdGen> remplieDetailProdGen(List<Employe> listEmploye){
	List<DetailProdGen> listDetailProdGen=new ArrayList<DetailProdGen>();
	for(int i=0;i<listEmploye.size();i++){
		DetailProdGen detailProdGen= new DetailProdGen();
		Employe employe =listEmploye.get(i);
		detailProdGen.setCoutTotal(BigDecimal.ZERO);
		detailProdGen.setRemise(employe.getRemise());
		detailProdGen.setEmploye(employe);
		detailProdGen.setProductionGen(production);
		
		listDetailProdGen.add(detailProdGen);
	}
	
	production.setListDetailProdGen(listDetailProdGen);
	productionDAO.edit(production);
	return listDetailProdGen;
  }*/

/*List<DetailProduction>  remplieDetailProdcution(List<Employe> listEmploye){
	List<DetailProduction> listDetailProdcution=new ArrayList<DetailProduction>();

	
	for(int i=0;i<listEmploye.size();i++){
		DetailProduction detailProd= new DetailProduction();
		Employe employe =listEmploye.get(i);
		detailProd.setCoutTotal(BigDecimal.ZERO);
		detailProd.setRemise(employe.getRemise());
		detailProd.setEmploye(employe);
		detailProd.setProduction(production);
		production.getDetailProductions().add(detailProd);
	}

	productionDAO.edit(production);
	
	return production.getDetailProductions();
  }*/


void  annulerStockMatierePremiere(List<CoutMP> listCoutMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteCharge=BigDecimal.ZERO;
	BigDecimal quantiteStockmp=BigDecimal.ZERO;
	BigDecimal quantiteARetournerCompteMP=BigDecimal.ZERO;
	for(int i=0;i<listCoutMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutMP coutMP=listCoutMP.get(i);
	
		
		 quantiteCharge=coutMP.getQuantCharge();
		 BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
		 BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
		 BigDecimal quantiteExistante=coutMP.getQuantExistante();
		 BigDecimal quantiteDechet=coutMP.getQuantDechet();
		 BigDecimal quantiteDechetFour=coutMP.getQuantDechetFournisseur();
		 BigDecimal quantiteManquante=coutMP.getQuantiteManquante();
		 BigDecimal quantiteOffre=coutMP.getQuantiteOffre();
		 BigDecimal quantiteReste=coutMP.getQuantReste();
		 
		StockMP stockMPProd=null;
		StockMP stockMPStockage=null;
		
		if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
		{
			if(coutMP.getFournisseurMP()!=null)
			{
				
				stockMPProd=stockMPDAO.findStockByMagasinMPByFournisseurMP (coutMP.getMatierePremier().getId(),idMagasinProd,coutMP.getFournisseurMP().getId() );		
				stockMPStockage=stockMPDAO.findStockByMagasinMPByFournisseurMP (coutMP.getMatierePremier().getId(),idMagasinStockage ,coutMP.getFournisseurMP().getId());
				
				
			}else
			{
				
				stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinProd );		
				stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinStockage );
			}
		}else
		{
			stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinProd );		
			stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinStockage );
		}
	
		
		
		
		
		quantiteStockmp=quantiteExistante.add(stockMPProd.getStock());
		
		quantiteStockage=stockMPStockage.getStock().add(quantiteCharge.add(quantitechargeSupp));
		 
		BigDecimal ecart=(quantiteCharge.add(quantitechargeSupp).add(quantiteExistante)).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste) );

			 Calendar cal = Calendar.getInstance();
		     cal.setTime(production.getDate());
		     int  annee = cal.get(Calendar.YEAR);
		     int mois = cal.get(Calendar.MONTH)+1;
		     
		
		CompteStockMP compteStockMP =compteStockMPDAO.findByCodeMPAnneeMois(coutMP.getMatierePremier().getCode(),mois,annee);
		
		if(compteStockMP!=null){
			quantiteARetournerCompteMP=compteStockMP.getQuantite().subtract(ecart);
			compteStockMP.setQuantite(quantiteARetournerCompteMP);
			compteStockMPDAO.edit(compteStockMP);
		}
		
		stockMPProd.setStock(quantiteStockmp);
		stockMPStockage.setStock(quantiteStockage);
		
		
		/*coutMP.setCoutDechet(0);
		coutMP.setQuantCharge(0);
		coutMP.setQuantChargeSupp(0);
		coutMP.setQuantConsomme(0);
		coutMP.setQuantDechet(0);
		coutMP.setQuantExistante(0);
		coutMP.setQuantite(0);
		coutMP.setQuantReste(0);
		coutMP.setQuantDechetFournisseur(0);
		coutMP.setQuantiteManquante(0);
		cou*/
		listCoutMP.set(i, coutMP);
	//	listCoutMP.remove(i);

		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
		
	}
	List<DetailTransferStockMP> listDetailTransferStockMP =new ArrayList<DetailTransferStockMP>();
	
	TransferStockMP transferStockMPCharge=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE);
	
	if(transferStockMPCharge!=null)
	{
		/*
		
		listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPCharge.getId());
		
		for(int i=0;i<listDetailTransferStockMP.size();i++)
		{
			
		DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
			
		detailtransferMPDAO.delete(detailTransferStockMP.getId());
			
			
			
		}
		
		*/
		
		transferstockmpDAO.deleteObject(transferStockMPCharge);
		
		
	}
	
	listDetailTransferStockMP.clear();
	
TransferStockMP transferStockMPChargeSupp=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.ETAT_TRANSFER_STOCK_CHARGE_SUPP);
	
	if(transferStockMPChargeSupp!=null)
	{
		
		/*
		listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPChargeSupp.getId());
		
		for(int i=0;i<listDetailTransferStockMP.size();i++)
		{
			
		DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
			
		detailtransferMPDAO.delete(detailTransferStockMP.getId());
			
			
			
		}
		*/
		
		transferstockmpDAO.deleteObject(transferStockMPChargeSupp);
		
		
		
	}
	
	
	listDetailTransferStockMP.clear();
	
	TransferStockMP transferStockMPDechetFournisseur=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.TYPE_DECHET_FOURNISSEUR);
		
		if(transferStockMPDechetFournisseur!=null)
		{
			
			/*
			listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPDechetFournisseur.getId());
			
			for(int i=0;i<listDetailTransferStockMP.size();i++)
			{
				
			DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
				
			detailtransferMPDAO.delete(detailTransferStockMP.getId());
				
				
				
			}
			*/
			
			transferstockmpDAO.deleteObject(transferStockMPDechetFournisseur);
			
			
			
		}
		
		listDetailTransferStockMP.clear();
		
		TransferStockMP transferStockMPDechet=transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(), Constantes.TYPE_DECHET);
			
			if(transferStockMPDechet!=null)
			{
				
				/*
				listDetailTransferStockMP=detailtransferMPDAO.findByTransferStockMP(transferStockMPDechet.getId());
				
				for(int i=0;i<listDetailTransferStockMP.size();i++)
				{
					
				DetailTransferStockMP  detailTransferStockMP=	listDetailTransferStockMP.get(i);
					
				detailtransferMPDAO.delete(detailTransferStockMP.getId());
					
					
					
				}
				
				*/
				
				
				transferstockmpDAO.deleteObject(transferStockMPDechet);
				
			}
			
	
	
	
	
	List<ManqueDechetFournisseur> listManqueDechetFournisseur =new ArrayList<ManqueDechetFournisseur>();
	
	List<DetailManqueDechetFournisseur> listDetailManqueDechetFournisseur =new ArrayList<DetailManqueDechetFournisseur>();
	
	listManqueDechetFournisseur=manqueDechetFournisseurDAO.listeManqueDechetFournisseurByCode(production.getNumOF());
	
	/*
	for(int j=0;j<listManqueDechetFournisseur.size();j++)
	{
		
		ManqueDechetFournisseur manqueDechetFournisseur=listManqueDechetFournisseur.get(j);
		
	listDetailManqueDechetFournisseur=detailManqueDechetFournisseurDAO.findByManqueDechetFournisseur(manqueDechetFournisseur.getId());
		
		for(int i=0;i<listDetailManqueDechetFournisseur.size();i++)
		{
			
		DetailManqueDechetFournisseur detailManqueDechetFournisseur=	listDetailManqueDechetFournisseur.get(i);
		
		detailManqueDechetFournisseurDAO.delete(detailManqueDechetFournisseur.getId());
			
			
		}
		
		
	
		
		
		
	}
	*/
	
	
	for(int j=0;j<listManqueDechetFournisseur.size();j++)
	{
		
		ManqueDechetFournisseur manqueDechetFournisseur=listManqueDechetFournisseur.get(j);
		
		manqueDechetFournisseurDAO.deleteObject(manqueDechetFournisseur);
		
		
		
	}
	
	
	
	
	
	
	//production.setListCoutMP(listCoutMP);
  }
void  annulerStockProduitFini(){
	BigDecimal quantiteAannuler=BigDecimal.ZERO;
	BigDecimal quantite=BigDecimal.ZERO;
	
			StockPF stockPF = stockPFDAO.findStockByMagasinPF(production.getArticles().getId(),production.getMagasinPF().getId());
			TransferStockPF transferStockPF=transferStockPFDAO.findByCodeTransfert(production.getNumOF());
			quantiteAannuler=production.getQuantiteReel();
			quantite=stockPF.getStock().subtract(quantiteAannuler);

			stockPF.setStock(quantite);

			transferStockPFDAO.deleteObject(transferStockPF);

			stockPFDAO.edit(stockPF);

}


void  annulerDetailCoutHorsproductionEnAttent(){


listCoutHorsProductionEnAttent= CoutHorsProdEnAttentDAO.findByProduction(production);

	for(int t=0;t<listCoutHorsProductionEnAttent.size();t++)
	{
		CoutHorsProdEnAttent CoutHorsProdEnAttent=listCoutHorsProductionEnAttent.get(t);
		
		 
		 
			 
			CoutHorsProdEnAttent.setEtat(COUT_HORS_PRODUCTION_EN_ATTENT);
			CoutHorsProdEnAttentDAO.edit(CoutHorsProdEnAttent);
			
			
		 
		
		
		 
	}
	

}




void deleteListeObject(List<FicheEmploye> listFicheEmploye){
	
	for(int i=0;i<listFicheEmploye.size();i++){
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		ficheEmployeDAO.deleteObject(ficheEmploye);
	}
}



void majDelaiEmployeGenerique(List<Production> listeProduction){
	
	// List<Production> listeProduction =productionDAO.listeProductionByDateByPeriode(dateProd,periode);
	 BigDecimal coutResponsableProd=BigDecimal.ZERO;
	 BigDecimal delaiEmploye=BigDecimal.ZERO;
	 BigDecimal coutHeure25=BigDecimal.ZERO;
	 BigDecimal delaiHeure25=BigDecimal.ZERO;
	 BigDecimal coutHeure50=BigDecimal.ZERO;
	 BigDecimal delaiHeure50=BigDecimal.ZERO;
	 BigDecimal coutTotalEmployeGen=BigDecimal.ZERO;
	 BigDecimal remise=BigDecimal.ZERO;
	 BigDecimal coutTotal=BigDecimal.ZERO;
	 
	 int compteur=1;
for(int i=0;i<listeProduction.size();i++){
	
	coutTotalEmployeGen=BigDecimal.ZERO;
		 Production production =listeProduction.get(i);
		 if(production.getStatut().equals(ETAT_OF_TERMINER) ){
			 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
			 List<DetailResponsableProd> listeDetailResponsableProd =production.getListDetailResponsableProd();
			 
			 
			 for(int j=0;j<listeDetailResponsableProd.size();j++){

				 DetailResponsableProd detailResponsableProd=listeDetailResponsableProd.get(j);
				 
				 if(!detailResponsableProd.getEmploye().isSalarie()){
				 
				 Employe employe=detailResponsableProd.getEmploye();
				 FicheEmploye ficheEmploye=ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(), detailResponsableProd.getEmploye().getId());
				 
				 CompteurEmployeProd compteurEmployeProd=compteurEmployeProdDAO.findByDateProdPeriode(production.getDate(),production.getPeriode(), employe.getId());
				 
				 if(compteurEmployeProd!=null)
				  compteur=compteurEmployeProd.getCompteur();
				 else 
					 compteur=1;
				 if(ficheEmploye!=null){
					 
					// coutResponsableProd=ficheEmploye.getCoutTotal().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP) ;
					 delaiEmploye=ficheEmploye.getDelaiEmploye().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
				//	 coutHeure25=ficheEmploye.getCoutSupp25().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 delaiHeure25=ficheEmploye.getHeureSupp25().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					// ficheEmploye.getCoutSupp50().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 delaiHeure50=ficheEmploye.getHeureSupp50().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 remise=ficheEmploye.getRemise().divide(new BigDecimal(compteur) , 6, BigDecimal.ROUND_HALF_UP);
					 
					 coutResponsableProd=delaiEmploye.multiply(heure.getValeur());
					 coutHeure50=delaiHeure50.multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_50));
					 coutHeure25=delaiHeure25.multiply(mapParametre.get(PARAMETRE_CODE_COUT_HEURE_SUPP_25));
					 
					 detailResponsableProd.setCoutTotal(coutResponsableProd);
					 detailResponsableProd.setDelaiEmploye(delaiEmploye);
					 detailResponsableProd.setCoutSupp25(coutHeure25);
					 detailResponsableProd.setHeureSupp25(delaiHeure25);
					 detailResponsableProd.setHeureSupp50(delaiHeure50);
					 detailResponsableProd.setCoutSupp50(coutHeure50);
					 detailResponsableProd.setRemise(remise);
					 
					 coutTotalEmployeGen=coutTotalEmployeGen.add(coutResponsableProd).add(coutHeure25).add(coutHeure50);
				 }
				 
				
				 
				 listeDetailResponsableProd.set(j, detailResponsableProd);
				 }
				 
			 }
			 
			 coutTotal=production.getCoutTotalMP().add(production.getCoutDechet()).add(production.getCoutTotalEmploye()).add(production.getCoutTotalEmployeEmbalage()).add(coutTotalEmployeGen)  ;
			 
			 production.setListDetailResponsableProd(listeDetailResponsableProd);
			 production.setCoutTotalEmployeGen(coutTotalEmployeGen);
			 production.setCoutTotal(coutTotal);
			 productionDAO.edit(production);
				 
			 }
		
	
		}
	}




////////////////////////////////////////// Initialiser Table Fournisseur ////////////////////////////////////////////////////










public void ViderEmployeProduction()
{
	txtcodeemployeproduction.setText("");
	comboemployeproduction.setSelectedItem("");
	txtdelaiproduction.setText("");
	txthsupp25production.setText("");
	txthsupp50production.setText("");
	checkEnpaneproduction.setSelected(false);
	checksortieproduction.setSelected(false);
	checkretardproduction.setSelected(false);
	txtcodeemployeproduction.requestFocus();
}

public void ViderEmployeGenerique()
{
	
	txtcodeemployegenerique.setText("");
	comboemployegenerique.setSelectedItem("");
	txtdelaigenerique.setText("");
	txthsupp25generique.setText("");
	txthsupp50generique.setText("");
	checkabsentgenerique.setSelected(false);
	checksortiegenerique.setSelected(false);
	checkretardgenerique.setSelected(false);
	txtcodeemployegenerique.requestFocus();
	
}

public void ViderEmployeEmballage()
{
	txtcodeemployeemballage.setText("");
	comboemployeemballage.setSelectedItem("");
	txtdelaiemballage.setText("");
	txthsupp25emballage.setText("");
	txthsupp50emballage.setText("");
	checkEnPaneemballage.setSelected(false);
	checksortieemballage.setSelected(false);
	checkretardemballage.setSelected(false);
	txtcodeemployeemballage.requestFocus();
}


////////////////////////////////////////////////////////////////////////////////////////////////////////    CALCULER STOCK FINALE             /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void CalculerStockFinale( Magasin magasin , Date dat)
{

	detailTransferStockMPDAO.ViderSession();
	
	MatierePremier matierePremier=null;
	
	
		
		SubCategorieMp subCategorieMp=null;
			CategorieMp categorieMp=null;
			MatierePremier mp=null;
		
			
		  	FournisseurMP fournisseurMP=null;
		
		  	if(magasin==null)
		  	{
		  		JOptionPane.showMessageDialog(null, "veuillez Selectionner le magasin SVP");
		  		return;
		  	}
		  	
		  	
		  	
			if(dat==null)
		  	{
		  		JOptionPane.showMessageDialog(null, "veuillez entrer la date de situation SVP");
		  		return;
		  	}
			
			
			
			Date mindate=null;
			
			Mindate=detailTransferStockMPDAO.MinDate(magasin);
			
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
				String year = simpleDateFormatyear.format(dat);
				
				try {
				mindate=simpleDateFormatDate.parse(year+"-01-01");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
			
			
			
			SubCategorieMp subCategorieMpthe=SubCategorieMPDAO.findByCode(SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		// listStockMP=stockMPDAO.findSockNonVideByMagasinBySubCategorieByCategorieByMPByFournisseur(magasin,subCategorieMp , categorieMp,mp,fournisseurMP);
			
			
		  	 
/////////////////////////////////////////////////////////////////////////////////// Les MP Non the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			//listeObjectStockFinaleGroupByMP=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPNonThe(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);
		  	  				  	
			
				listeObjectStockInitialGroupByMP=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate, magasin, subCategorieMpthe, null, null) ;

			
				
				
			
			listeObjectEtatStockGroupByMP=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPNonThe(mindate,dat, magasin, subCategorieMpthe, null, null);
				listeEtatStockTransfertEnAttenteNonThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteNonThe(mindate,dat, magasin, subCategorieMpthe, null, null);

			
////////////////////////////////////////////////////////////////////////////////////////////////////Les MP the //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		//	listeObjectStockFinaleGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockFinaleMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(dateSituation.getDate(), magasin, subCategorieMpthe, null, null);

			
			
				listeObjectStockInitialGroupByMPByFournisseur=detailTransferStockMPDAO.listeStockInitialMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseur(mindate, magasin, subCategorieMpthe, null, null) ;
				
			

listeEtatStockTransfertEnAttenteThe=detailTransferStockMPDAO.listeEtatStockMPTransfertEnAttenteThe(mindate,dat, magasin, subCategorieMpthe, null, null);
					
listeObjectEtatStockGroupByMPByFournisseurReception=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurReception(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurEntrer=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurEntrer(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurSortie(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurCharger=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurCharger(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurRetour(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortie=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortie(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurResterMachine=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurResterMachine(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurFabrique=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurFabriquer(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurExistante=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurExistante(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortiePF=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortiePF(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieSortieEnAttent=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieSortieEnAttente(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortiePerte=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortiePerte(mindate,dat, magasin, subCategorieMpthe, null, null);
	listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetour=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRetour(mindate,dat, magasin, subCategorieMpthe, null, null);
		listeObjectEtatStockGroupByMPByFournisseurAutreSortieRetourFournisseurAnnule=detailTransferStockMPDAO.listeEtatStockMPByMagasinBySubCategorieByCategorieBayMPByTheGroupByFournisseurAutreSortieRETOURFOURNISSEURANNULER(mindate,dat, magasin, subCategorieMpthe, null, null);

			
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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Reception ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
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

////////////////////////////////////////////////////////////////////////////////////////////////////////Entrer ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	   
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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


////////////////////////////////////////////////////////////////////////////////////////////////////////Charge et Charge Supp  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Retour  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
existe=false;
BigDecimal retour=BigDecimal.ZERO;
BigDecimal retourFournisseurAnnule=BigDecimal.ZERO;
for(int j=0;j<listeObjectEtatStockGroupByMPByFournisseurRetour.size() ; j++) {
retour=BigDecimal.ZERO;
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
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Autres Sorties   Sortie ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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



////////////////////////////////////////////////////////////////////////////////////////////////////////Rester Machine ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

////////////////////////////////////////////////////////////////////////////////////////////////////////Fabriquer  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

////////////////////////////////////////////////////////////////////////////////////////////////////////Existante  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
  
////////////////////////////////////////////////////////////////////////////////////////////////////////Sortie En Attente ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
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
	
	  etatStockMP.setQuantiteFinale((etatStockMP.getQuantiteInitial().add( etatStockMP.getQuantiteReception().add( etatStockMP.getQuantiteRetour()).add(etatStockMP.getTransferEntrer().add(etatStockMP.getQuantiteResterMachine().add(etatStockMP.getQuantiteFabriquer()))))).subtract(etatStockMP.getQuantiteCharger().add(etatStockMP.getQuantiteChargerSupp().add(etatStockMP.getQuantiteAutreSortie()).add(etatStockMP.getTransferSortie()).add(etatStockMP.getQuantiteExistante()))));
	 
	  listEtatStockMP.set(k, etatStockMP);
	  
	  
	  }




}



boolean remplirMapNombreCartonPourChaqueConditionOffre(){
	boolean trouve=true;
 
	int j=0;	
	 
	for( j=0;j<tableOffreVariante.getRowCount();j++){
		
		if(!tableOffreVariante.getValueAt(j, 1).toString().equals("")  ){
			
			mapNombreCartonPourOffreVariante.put(tableOffreVariante.getValueAt(j, 0).toString(), new BigDecimal(tableOffreVariante.getValueAt(j, 1).toString()));
			 
		}else
		{
			
			trouve=false;
			
		}
	}
	
	 
	return trouve;
}



public boolean EcarValider()
{
	
	boolean valider=true;
	
	
	  int i=0;
	 
	  


	  
	 
	  
	
		while(i<production.getListCoutMP().size())
		{	
			CoutMP coutMP=production.getListCoutMP().get(i);
		
			BigDecimal quantiteTotal=coutMP.getQuantite();
			BigDecimal quantiteExistante=coutMP.getQuantExistante();
			BigDecimal quantiteCharge=coutMP.getQuantCharge();
			BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
			BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
			BigDecimal quantiteDechet=coutMP.getQuantDechet();
			BigDecimal quantiteDechetFour=coutMP.getQuantDechetFournisseur();
			BigDecimal quantiteManquante=coutMP.getQuantiteManquante();
			BigDecimal quantiteOffre=coutMP.getQuantiteOffre();
			BigDecimal quantiteReste=coutMP.getQuantReste();
			BigDecimal quantiteManquanteFrPlus=coutMP.getQuantiteManquanteFrPlus();
			 
	 
			
			BigDecimal ecart=BigDecimal.ZERO;
			BigDecimal ecartConsomme=BigDecimal.ZERO;
			
		
			
			
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) && quantiteOffre.setScale(6, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN))!=0)
			{
				 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract((quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste)).add(quantiteManquanteFrPlus);

				 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);
			}else if(!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) && quantiteOffre.setScale(6, RoundingMode.HALF_DOWN).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_DOWN))!=0)
			{
				 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre).add(quantiteReste)).add(quantiteManquanteFrPlus);

				 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+ quantiteConsomme +": "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);

			}
			
			else
			{
				 ecart=quantiteCharge.add(quantitechargeSupp).add(quantiteExistante).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(coutMP.getQuantiteManquante()).add(quantiteOffre).add(quantiteReste)).add(coutMP.getQuantiteManquanteFrPlus());

				 System.out.println(coutMP.getMatierePremier().getNom() +": "+ quantiteCharge+": "+quantitechargeSupp+": "+quantiteExistante+": - "+ quantiteConsomme +": "+quantiteDechet+": "+quantiteDechetFour+": "+quantiteManquante+": "+quantiteOffre+": "+quantiteReste+": "+quantiteManquanteFrPlus);

			}
			
			
			
			
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				{
					ecartConsomme=coutMP.getQuantite().subtract(coutMP.getQuantConsomme());
					 if(ecartConsomme.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(BigDecimal.ZERO)!=0)
					 {
						 valider=false;
					 }
						 
				}
				
				 if(ecart.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(BigDecimal.ZERO)!=0)
				 {
					 valider=false;
				 } 
				 
				if(OffrePFMixte==true)
				{
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
					{
						if(!coutMP.getMatierePremier().getNom().contains("BOX") && !coutMP.getMatierePremier().getCode().equals(Constantes.CODE_MP_THERRES) && !coutMP.getMatierePremier().getNom().contains(Constantes.MP_CONTIENT_VERRE))
							{
							QuantiteTotalOffreMixtPFConsomme=QuantiteTotalOffreMixtPFConsomme.add(coutMP.getQuantConsomme());	
							}
						
						
						
					}
					
					
				}
				 
				 
				 
			
			
			
			i++;
		}
	
	
	
	
	
	return valider;
}


void AjouterLesEmployee()
{
	
	if(txtNumOF.getSelectedItem().equals(""))
	{
		JOptionPane.showMessageDialog(null, "veuillez Selectionner Num OF Production Raja SVP !!!!!");
		ErreurImporterHeure=true;
		return;
	}else
	{
		
		
		
		
		
		
		
		
		
		listDetailProdGenTmp.clear();
		listDetailProductionTMP.clear();	
		
		try {
			
			
			 Production production=productionDAO.findByNumOF(txtNumOF.getSelectedItem().toString(), utilisateur.getCodeDepot() );
			 
			 
			 if(production.getStatut().equals(Constantes.ETAT_OF_TERMINER))
			 {
				 JOptionPane.showMessageDialog(null, "OF est  Déja Terminer", "Erreur",JOptionPane.ERROR_MESSAGE);
				 ErreurImporterHeure=true;
				 return;
			 }
			 
			 if(con.isClosed()==true)
 			 {
 				 
 				 Connect.connecToProduction();
 		    		con=Connect.getConnexion();
 		        	stx=con.createStatement();
 				 
 			 }	
			boolean existe=true;
				String	 querytmp="select  p.NUM_OF,  a.CODE_ARTICLE, p.quantite_reel, p.NOMBRE_HEURE, l.ID_MACHINE , p.`date`, p.id_magasin from production p , articles a, ligne_machine l where p.id_article=a.id and p.id_ligne=l.id and p.NUM_OF='"+txtNumOF.getSelectedItem().toString().trim()+"'";
				rset=stx.executeQuery(querytmp); 
			
				while(rset.next())
				{
					
					if(!rset.getString(2).equals(production.getArticles().getCodeArticle()) || rset.getBigDecimal(3).compareTo(new BigDecimal(txtQuantiteRealise.getText()))!=0 ||  rset.getBigDecimal(4).compareTo(new BigDecimal(txtPrixServiceProd.getText()))!=0 || rset.getInt(5)!=production.getLigneMachine().getMachine().getId() || rset.getDate(6).compareTo(production.getDate())!=0  || rset.getInt(7)!=production.getMagasinProd().getId()   )
					{
						existe=false;
					}
					
					
				}
			
			
			if(existe==false)
			{
				
				JOptionPane.showMessageDialog(null, "OF Selectionner est different au OF Creer dans l'Autre version !!!!!");
				ErreurImporterHeure=true;
				return;
				
			}
			
			
			
			
			
			
			
			 if(con.isClosed()==true)
 			 {
 				 
 				 Connect.connecToProduction();
 		    		con=Connect.getConnexion();
 		        	stx=con.createStatement();
 				 
 			 }	
			 
			 
			
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			
			 
			 
			 Parametre heure=parametreDAO.findByDateByLibelle(production.getDate(), Constantes.PARAMETRE_LIBELLE_COUT_HORAIRE_CNSS);
			 
			 
			String msg=""; 
			
////////////////////////////////////////////////// supprimer les Employees Déja existante  //////////////////////////////////////////////////////////////////				
			
			detailProductionDAO.SupprimerDetailProductionByProduction(production);
			 
			 

//////////////////////////////////////////////////////////////////////////////////////////////Equipe Production             ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////										
			
		String	 query="select d.ABSENT, d.COUT_SUPP25, d.COUT_SUPP50, d.cout_total, d.delai_employe, d.HEURE_SUPP25, d.HEURE_SUPP50, d.remise,e.matricule , d.SORTIE from detail_production d, employe e , production p where d.id_employe= e.id and d.id_production= p.id and p.NUM_OF='"+txtNumOF.getSelectedItem().toString().trim()+"'";
				rset=stx.executeQuery(query); 
			 
				while(rset.next())
					{
					
					Employe employe=mapMatriculeEmploye.get(rset.getString(9));
					if(employe!=null)
					{

	  		   DetailProduction detailProduction=    detailProductionDAO.DetailProductionByProductionByEmploye(production, employe);
		  		      		if(detailProduction!=null)
		  		      		{
		  		      		 
		  		  		      
		  		      		detailProduction.setEmploye(employe);
		  		      	detailProduction.setTypeResEmploye(employe.getTypeResEmploye());
		  		      detailProduction.setDelaiEmploye(rset.getBigDecimal(5));
		  		    detailProduction.setHeureSupp25(rset.getBigDecimal(6));
		  		  detailProduction.setHeureSupp50(rset.getBigDecimal(7));
		  		detailProduction.setAbsent(rset.getBoolean(1));
		  		detailProduction.setCoutTotal(rset.getBigDecimal(4));
		  		detailProduction.setCoutSupp25(rset.getBigDecimal(2));
		  		detailProduction.setCoutSupp50(rset.getBigDecimal(3));
		  		detailProduction.setRemise(rset.getBigDecimal(8));
		  		detailProduction.setCoutHoraire(heure.getValeur());
		  		detailProduction.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)).setScale(2, RoundingMode.FLOOR));
		  		detailProduction.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)).setScale(2, RoundingMode.FLOOR));
  				      
		  		detailProduction.setSortie(rset.getBoolean(10));
		  		detailProduction.setRetard(false);
		  		detailProduction.setAutorisation(false);
		  		detailProduction.setValider(Constantes.ETAT_INVALIDER);
		  		detailProduction.setProduction(production);
	  		  		    	listDetailProductionTMP.add(detailProduction);
		  		      			
		  		      		}else
		  		      		{
		  		      			
		  		      		DetailProduction detailproduction=new DetailProduction();
		  		  		      
	  		  		    	detailproduction.setEmploye(employe);
	  		  		    detailproduction.setTypeResEmploye(employe.getTypeResEmploye());
	  		  		    	detailproduction.setDelaiEmploye(rset.getBigDecimal(5));
	  		  		    	detailproduction.setHeureSupp25(rset.getBigDecimal(6));
	  		  		    	detailproduction.setHeureSupp50(rset.getBigDecimal(7));
	  		  		    	detailproduction.setAbsent(rset.getBoolean(1));
	  		  		    detailproduction.setCoutTotal(rset.getBigDecimal(4));
  					        detailproduction.setCoutSupp25(rset.getBigDecimal(2));
  				            detailproduction.setCoutSupp50(rset.getBigDecimal(3));
  				          detailproduction.setRemise(rset.getBigDecimal(8));
  				          detailproduction.setCoutHoraire(heure.getValeur());
  				        detailproduction.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)).setScale(2, RoundingMode.FLOOR));
  				      detailproduction.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)).setScale(2, RoundingMode.FLOOR));
  				      
	  		  		 detailproduction.setSortie(rset.getBoolean(10));
	  		  		detailproduction.setRetard(false);
	  		  	detailproduction.setAutorisation(false);
	  		  detailproduction.setValider(Constantes.ETAT_INVALIDER);
	  		   detailproduction.setProduction(production);
	  		  		    	listDetailProductionTMP.add(detailproduction);
		  		      			
		  		      			
		  		      		}
		  		      
  		  		    	 
  		  		      	
						
					}else
					{
						msg=msg+rset.getString(9).toString()+"\n";
					}
					
					
					}
				
				if(listDetailProductionTMP.size()!=0)
				{
					
					 
	  		     		production.setDetailProductions(listDetailProductionTMP);
	  		     	 
	  		     		productionDAO.edit(production);
	  		     	listDetailProductionTMP.clear();
	  		    listDetailProductionTMP=productionDAO.listeDetailProduction(production.getId());
	  		     	 afficher_tableEmploye(listDetailProductionTMP);
	  		  ViderEmployeProduction();
					
					
					
				}
				

//////////////////////////////////////////////////////////////////////////////////////////////Equipe Emballage             ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////										
				
				
//////////////////////////////////////////////////supprimer les Employees Déja existante  //////////////////////////////////////////////////////////////////				
				
				detailProdGenDAO.SupprimerDetailProductionEmballageByProduction(production);
		 				    						
				String	 query1="select d.ABSENT, d.COUT_SUPP25, d.COUT_SUPP50, d.cout_total, d.delai_employe, d.HEURE_SUPP25, d.HEURE_SUPP50, d.remise,e.matricule , d.SORTIE from detail_prod_gen d, employe e , production p where d.id_employe= e.id and d.id_production= p.id and p.NUM_OF='"+txtNumOF.getSelectedItem().toString().trim()+"'";
				rset=stx.executeQuery(query1); 
			 
				while(rset.next())
					{
					
					
					
					
					
		  		    	 
					Employe employe=mapMatriculeEmploye.get(rset.getString(9));
					if(employe!=null)
					{
					
						
						DetailProdGen detailprodGenTmp=	detailProdGenDAO.DetailProdGenByProductionByEmploye(production, employe);
						if(detailprodGenTmp!=null)
						{
							
							 
							
							detailprodGenTmp.setEmploye(employe);
							detailprodGenTmp.setTypeResEmploye(employe.getTypeResEmploye());
							detailprodGenTmp.setDelaiEmploye(rset.getBigDecimal(5));
							detailprodGenTmp.setHeureSupp25(rset.getBigDecimal(6));
							detailprodGenTmp.setHeureSupp50(rset.getBigDecimal(7));
							detailprodGenTmp.setAbsent(rset.getBoolean(1));
		  		  		    	
							detailprodGenTmp.setCoutTotal(rset.getBigDecimal(4));
							detailprodGenTmp.setCoutSupp25(rset.getBigDecimal(2));
							detailprodGenTmp.setCoutSupp50(rset.getBigDecimal(3));
							detailprodGenTmp.setRemise(rset.getBigDecimal(8));
							detailprodGenTmp.setCoutHoraire(heure.getValeur());
							detailprodGenTmp.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
							detailprodGenTmp.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
		  		  	
							detailprodGenTmp.setSortie(rset.getBoolean(10));
							detailprodGenTmp.setRetard(false);
							detailprodGenTmp.setAutorisation(false);
							detailprodGenTmp.setValider(Constantes.ETAT_INVALIDER);
							detailprodGenTmp.setProductionGen(production);
		  		  		    listDetailProdGenTmp.add(detailprodGenTmp);
							
							
						}else
						{
							
							DetailProdGen detailprodGen=new DetailProdGen();
							
							detailprodGen.setEmploye(employe);
		  		  		    detailprodGen.setTypeResEmploye(employe.getTypeResEmploye());
		  		  		    	detailprodGen.setDelaiEmploye(rset.getBigDecimal(5));
		  		  		    	detailprodGen.setHeureSupp25(rset.getBigDecimal(6));
		  		  		    	detailprodGen.setHeureSupp50(rset.getBigDecimal(7));
		  		  		    	detailprodGen.setAbsent(rset.getBoolean(1));
		  		  		    	
		  		  		    detailprodGen.setCoutTotal(rset.getBigDecimal(4));
		  		  		detailprodGen.setCoutSupp25(rset.getBigDecimal(2));
		  		  	detailprodGen.setCoutSupp50(rset.getBigDecimal(3));
		  		  detailprodGen.setRemise(rset.getBigDecimal(8));
		  		  detailprodGen.setCoutHoraire(heure.getValeur());
		  		detailprodGen.setCoutHoraireSupp25(heure.getValeur().multiply(new BigDecimal(1.25)));
		  		detailprodGen.setCoutHoraireSupp50(heure.getValeur().multiply(new BigDecimal(1.5)));
		  		  	
		  		  		        detailprodGen.setSortie(rset.getBoolean(10));
		  		  		        detailprodGen.setRetard(false);
		  		  		        detailprodGen.setAutorisation(false);
		  		  		        detailprodGen.setValider(Constantes.ETAT_INVALIDER);
		  		  		    	detailprodGen.setProductionGen(production);
		  		  		    listDetailProdGenTmp.add(detailprodGen);
						}
						
						
						
					}else
					{
						msg=msg+rset.getString(9).toString()+"\n";
					}
		  		    	
		  		     
		  		    	
		  		    	
		  		    
					
					}
				
				
				if(listDetailProdGenTmp.size()!=0)
				{
					production.setListDetailProdGen(listDetailProdGenTmp);
	  		     		productionDAO.edit(production);
	  		     	listDetailProdGenTmp.clear();
	  		    listDetailProdGenTmp=productionDAO.listeDetailProdGen(production.getId());
	  		     	afficher_tableEmployeEmabalage(listDetailProdGenTmp);
	     		ViderEmployeEmballage();
				}
			
	  		     
				
				
				
				
				
				
				
				
				
				
				
				
				
				if(!msg.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Les Employees De Matrcules Suivantes est Introuvable : "+msg);
					ErreurImporterHeure=true;
					return;
				}
				
				
				
			 
			 
			
		} catch (Exception ex) {
			
			 
			
		}
		
		
		
		
		
		
		
	}
	

}


void VerifierLesHeuresEmballageEtProduction()
{

	for(int i=0;i<listDetailProductionTMP.size();i++)
	{
		
		TotalEmployeeProductionEtEmballage=TotalEmployeeProductionEtEmballage.add(BigDecimal.ONE);
		
		 if(listDetailProductionTMP.get(i).getDelaiEmploye().compareTo(new BigDecimal(txtPrixServiceProd.getText()))!=0)
		 {
			 
			 TotalHeuresProductionEtEmballage=TotalHeuresProductionEtEmballage.add(listDetailProductionTMP.get(i).getDelaiEmploye().add(new BigDecimal(txtPrixServiceProd.getText()).subtract(listDetailProductionTMP.get(i).getDelaiEmploye())));
			 
			 
		 }else
		 {
			 TotalHeuresProductionEtEmballage= TotalHeuresProductionEtEmballage.add(listDetailProductionTMP.get(i).getDelaiEmploye());
		 }
		
		
		
		
	}
	
	for(int i=0;i<listDetailProdGenTmp.size();i++)
	{
		
		TotalEmployeeProductionEtEmballage=TotalEmployeeProductionEtEmballage.add(BigDecimal.ONE);
		
		 if(listDetailProdGenTmp.get(i).getDelaiEmploye().compareTo(new BigDecimal(txtPrixServiceProd.getText()))!=0)
		 {
			 
			 TotalHeuresProductionEtEmballage=TotalHeuresProductionEtEmballage.add(listDetailProdGenTmp.get(i).getDelaiEmploye().add(new BigDecimal(txtPrixServiceProd.getText()).subtract(listDetailProdGenTmp.get(i).getDelaiEmploye())));
			 
			 
		 }else
		 {
			 TotalHeuresProductionEtEmballage= TotalHeuresProductionEtEmballage.add(listDetailProdGenTmp.get(i).getDelaiEmploye());
		 }
		
		
		
		
	}	
	
	
	
	
	
	
}
}
