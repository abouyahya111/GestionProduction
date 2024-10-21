package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.hibernate.type.YesNoType;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.ComboBoxRenderer;
import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StatistiqueFinanciereMPDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StatistiqueFinanciereMPDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdRes;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.StatistiqueFinanciaireMP;
import dao.entity.StockMP;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjouterNombrePersonneTravailerDansLaProduction extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleOF;
	private JXTable TableOF = new JXTable();
	private ImageIcon imgImprimer;
	private ImageIcon imgValider;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnAnnulerOF;
	private JButton btnLancerOF;
	private JButton btnRechercher;
	
	private JTextField quantite;
	private JTextField codeArticle;
	private DepotDAO depotDAO;
	
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO ;
	private	List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp= new ArrayList<DetailTransferStockMP>();
	private	List<DetailTransferStockMP> listDetailTransferStockMPCharge= new ArrayList<DetailTransferStockMP>();
	private List<Production> listProductions=new ArrayList<Production>();
	private List<Production> listProductionsLance=new ArrayList<Production>();
	
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	
	private JComboBox<String> categorie;
	private JComboBox<String> comboMachine;
	private  JComboBox<String> comboLigneMachine;
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	
	private Map< String, String> mapChargeSupp = new HashMap<>();
	
	private static Production production = new Production();
	private JTextField txtDateDebutPrev;
	private JTextField txtDateFinPrev;
	private Utilisateur utilisateur;
	SubCategorieMPDAO SubCategorieMPDAO;
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	List<DetailProdRes> listDetailResponsableProdTmp=new ArrayList<DetailProdRes>();
	List<Production> listProductionsterminerGroupByDate=new ArrayList<Production>();
	private DetailProdResDAO detailProdResDAO;
	String datesProductionErreur="";
	private CoutMPDAO coutMPDAO;
	private StatistiqueFinanciereMPDAO statistiqueFinanciereMPDAO;
	
/////////////////////////////////////////////////////////////////////////// POUR CALCULER STOCK FINALE   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	 private List <Object[]> Mindate=new ArrayList<Object[]>();
	 private List <Object[]> listeObjectStockInitialGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectStockInitialGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMP=new ArrayList<Object[]>();
		private List <Object[]> listeObjectEtatStockGroupByMPByFournisseur=new ArrayList<Object[]>();
		private List<EtatStockMP> listEtatStockMP=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficher=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficherMagasinStockage=new ArrayList<EtatStockMP>();
		private List<EtatStockMP> listEtatStockMPAfficherMagasinProduction=new ArrayList<EtatStockMP>();
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
		private List <StatistiqueFinanciaireMP> listeStatistiqueFinanciereMP=new ArrayList<StatistiqueFinanciaireMP>();
		 
		private DetailTransferMPDAO detailTransferStockMPDAO;
		private JTextField txtNombrePersonne;

	
	 
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
 
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterNombrePersonneTravailerDansLaProduction() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 756);
        try{
        	
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	stockMPDAO= new StockMPDAOImpl();
        	compteurProductionDAO= new CompteurProductionDAOImpl();
        	detailTransfertMPDAO= new DetailTransferMPDAOImpl();
        	transferStockMPDAO= new TransferStockMPDAOImpl();
        	depotDAO= new DepotDAOImpl();
        	SubCategorieMPDAO=new SubCategorieMPAOImpl();
        	fournisseurMPDAO=new FournisseurMPDAOImpl();
        	 detailProdResDAO=new DetailProdResDAOImpl();
        	 detailTransferStockMPDAO =  new DetailTransferMPDAOImpl();
        	 coutMPDAO=new CoutMPDAOImpl();
        	 statistiqueFinanciereMPDAO=new StatistiqueFinanciereMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
		
		 	
	final String codeDepot=AuthentificationView.utilisateur.getCodeDepot();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		  
				  		modeleOF =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"Date OF","Num OF", "Etat"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
				  		   TableOF.addMouseListener(new MouseAdapter() {
				  		   	@Override
				  		   	public void mouseClicked(MouseEvent arg0) {
				  		   		
				  		   		
				  		   		

				  		  		
if(TableOF.getSelectedRow()!=-1)
{
	production=listProductions.get(TableOF.getSelectedRow());
	
		if(production!=null){
		
			
			
  			
  	
  			
  			
  			
  			
  			
  			
  			
  			//txtDescription.setText(production.getDescription());
  			quantite.setText(""+NumberFormat.getNumberInstance(Locale.FRANCE).format(production.getQuantiteEstime()));
  			codeArticle.setText(production.getArticles().getCodeArticle());
  			categorie.addItem(production.getArticles().getLiblle());
  			categorie.setSelectedItem(production.getArticles().getLiblle());
  			
  		//	comboEquipe.addItem(production.getEquipe().getNomEquipe());
  		//	comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
  			
  			comboLigneMachine.addItem(production.getLigneMachine().getNom());
  			comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
  			
  			comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
  			comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
  			
  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
				String dateFinPrev=dateFormat.format(production.getDateFinFabPre());
  			txtDateDebutPrev.setText(dateDebutPrev);
  			txtDateFinPrev.setText(dateFinPrev);

  		
  			
		}else{
			
  			  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
  				
  			}
	
	
}
					  			
					  		
					 

					  		
				  		  		
				  		  	
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	}
				  		   });
				  		   TableOF.setModel(modeleOF); 
				  		 TableOF.getColumnModel().getColumn(0).setPreferredWidth(10);
				  		TableOF.getColumnModel().getColumn(1).setPreferredWidth(300);
				  		 
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		  
				  		modeleMP =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"Code","Nom Matière Première   ","Fournisseur", "Quantité","Quantite Existante","Quantite Charge" , "Charge Supp", "Ajouter Charge Supp"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false,false,false,false,false, true
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
				  		 
				  		   // Charger La Liste Des OF en Etat Creation 
				  		afficher_tableOF(ChargerOF(Constantes.ETAT_OF_LANCER, codeDepot));
				  		    
				  		    btnLancerOF = new JButton("Valider");
				  		    btnLancerOF.setBounds(172, 529, 107, 24);
				  		    add(btnLancerOF);
				  		    btnLancerOF.setIcon(imgAjouter);
				  		    btnLancerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {


					  		   		
					  		   		
					  		   		

					  		  		
if(TableOF.getSelectedRow()!=-1)
{
	production=listProductions.get(TableOF.getSelectedRow());
	
		if(production!=null){

		if(txtNombrePersonne.getText().isEmpty()==false)
		{
			
		 
			
		try {
			
			
			if(new BigDecimal(txtNombrePersonne.getText()).compareTo(BigDecimal.ZERO)<=0)
			{
				JOptionPane.showMessageDialog(null, "Le Nombre De Personne Travailler Dans Cette Production Doit Etre Supérieur a 0 SVP !!!");
				return;
			}else
			{
				
				production.setNbrePersonneTravailler(new BigDecimal(txtNombrePersonne.getText()));
				productionDAO.edit(production);
			afficher_tableOF(	ChargerOF(Constantes.ETAT_OF_LANCER, codeDepot));
			
  			//txtDescription.setText(production.getDescription());
  			quantite.setText("");
  			codeArticle.setText("");
  			categorie.addItem("");
  			categorie.setSelectedItem("");
  			
  		//	comboEquipe.addItem(production.getEquipe().getNomEquipe());
  		//	comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
  			
  			comboLigneMachine.addItem("");
  			comboLigneMachine.setSelectedItem("");
  			
  			comboMachine.addItem("");
  			comboMachine.setSelectedItem("");
  			
  			 
  			txtDateDebutPrev.setText("");
  			txtDateFinPrev.setText("");
  			txtNombrePersonne.setText("");
 
			JOptionPane.showMessageDialog(null, "Le Nombre De Personne Travailler Dans Cette Production Valider avec Succee");	
				
			}
			
			
		} catch (NumberFormatException e2) {
			 
			JOptionPane.showMessageDialog(null, "Le Nombre De Personne Travailler Dans Cette Production Doit Etre en Chiffre SVP !!!");
			return;
			
			
		}	
			
			
			
			
			
		}else
		{
			JOptionPane.showMessageDialog(null, "Veuillez Entrer Le Nombre De Personne Travailler Dans Cette Production SVP !!!");
			return;
		}
		
		
		
		
		
		}else{
			
  			  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
  				return;
  			}
	
	
}
					  			
					  		
					 

					  		
				  		  		
				  		  	
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   		
				  		   	
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	
				  		     	}
				  		     });
				  		    btnLancerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     btnAnnulerOF = new JButton("Initialiser");
				  		     btnAnnulerOF.setBounds(282, 529, 106, 23);
				  		     add(btnAnnulerOF);
				  		     btnAnnulerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnAnnulerOF.setIcon(imgInit);
				  		     btnAnnulerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
					     
				  		     	 
				  		     	comboMachine = new JComboBox();
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 279, 971, 149);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Machine");
				  		     	lblMachine.setBounds(10, 86, 58, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 
				  		     	 comboMachine.setBounds(119, 87, 136, 24);
				  		     	 layeredPane.add(comboMachine);
				  		     	 comboMachine.addItem("");
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Ligne Machine");
				  		     	 lblGroupe.setBounds(283, 86, 77, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 comboLigneMachine = new JComboBox();
				  		     	 comboLigneMachine.setBounds(367, 87, 144, 24);
				  		     	 layeredPane.add(comboLigneMachine);
				  		  
				  		  JLabel lblDatePrevue = new JLabel("Date D\u00E9but Pr\u00E9vue");
				  		  lblDatePrevue.setBounds(10, 48, 102, 26);
				  		  layeredPane.add(lblDatePrevue);
				  		  
				  		  JLabel lblDateFin = new JLabel("Date Fin pr\u00E9vue");
				  		  lblDateFin.setBounds(283, 48, 77, 26);
				  		  layeredPane.add(lblDateFin);
				  		  codeArticle = new JTextField();
				  		  codeArticle.setEnabled(false);
				  		util.Utils.copycoller(codeArticle);
				  		  codeArticle.setBounds(119, 10, 136, 26);
				  		  layeredPane.add(codeArticle);
				  		  categorie = new JComboBox();
				  		  categorie.setEnabled(false);
				  		  categorie.setEditable(true);
				  		  categorie.setBackground(Color.WHITE);
				  		  categorie.addItem("");
		codeArticle.setColumns(10);
		
		
 
		
		
		
		  JLabel lblCodeArticle = new JLabel("Code Article");
		  lblCodeArticle.setBounds(8, 10, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		  categorie.setBounds(331, 13, 386, 26);
		  layeredPane.add(categorie);
		  categorie.addItem(""); 
		  
		  quantite = new JTextField();
		  quantite.setEnabled(false);
		  util.Utils.copycoller(quantite);
		  quantite.setBounds(805, 10, 131, 26);
		  layeredPane.add(quantite);
		  quantite.setColumns(10);
		  
		  JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
		  lblQuantite.setBounds(727, 10, 68, 26);
		  layeredPane.add(lblQuantite);
		  lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		    
		    JLabel lblArticle = new JLabel("Article:");
		    lblArticle.setBounds(283, 11, 102, 26);
		    layeredPane.add(lblArticle);
		    lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    
		    txtDateDebutPrev = new JTextField();
		    txtDateDebutPrev.setEnabled(false);
		    util.Utils.copycoller(txtDateDebutPrev);
		    txtDateDebutPrev.setColumns(10);
		    txtDateDebutPrev.setBounds(119, 51, 136, 26);
		    layeredPane.add(txtDateDebutPrev);
		    
		    txtDateFinPrev = new JTextField();
		    txtDateFinPrev.setEnabled(false);
		    util.Utils.copycoller(txtDateFinPrev);
		    txtDateFinPrev.setColumns(10);
		    txtDateFinPrev.setBounds(367, 51, 144, 26);
		    layeredPane.add(txtDateFinPrev);
		
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("   Les OF En Cours     ");
		titledSeparator_1.setBounds(11, 13, 970, 30);
		add(titledSeparator_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 46, 971, 229);
		add(scrollPane_1);
		
		
		TableOF.setShowVerticalLines(false);
		TableOF.setSelectionBackground(new Color(51, 204, 255));
		TableOF.setRowHeightEnabled(true);
		TableOF.setRowHeight(20);
		TableOF.setGridColor(Color.BLUE);
		TableOF.setForeground(Color.BLACK);
		TableOF.setColumnControlVisible(true);
		TableOF.setBackground(Color.WHITE);
		TableOF.setAutoCreateRowSorter(true);
		TableOF.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(TableOF);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane_1.setBounds(10, 439, 971, 62);
		add(layeredPane_1);
		
		txtNombrePersonne = new JTextField();
		txtNombrePersonne.setColumns(10);
		txtNombrePersonne.setBounds(167, 10, 179, 26);
		layeredPane_1.add(txtNombrePersonne);
		
		JLabel lblNombrePersonneTravailler = new JLabel("Nombre Personne Travailler :");
		lblNombrePersonneTravailler.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNombrePersonneTravailler.setBounds(8, 10, 149, 26);
		layeredPane_1.add(lblNombrePersonneTravailler);
	   
	   
	   SubCategorieMp subCategorieMp=SubCategorieMPDAO.findByCode(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE);
		
		listFournisseurMP=fournisseurMPDAO.findByCategorie(subCategorieMp);
		for(int j=0;j<listFournisseurMP.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseurMP.get(j);
			
			mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		}
		
		
	
		
		
		
				  		 
	}
	
	
	void intialiser()
	{
		txtNombrePersonne.setText("");
		
	}
	



void 	intialiserTableauOF() {
	
	modeleOF =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date OF","Num OF" , "Etat"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false, false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		   TableOF.setModel(modeleOF); 
		   TableOF.getColumnModel().getColumn(0).setPreferredWidth(30);
		   TableOF.getColumnModel().getColumn(1).setPreferredWidth(300);
		   TableOF.getTableHeader().setReorderingAllowed(false);
}


	

	
	
	
	
	void afficher_tableOF(List<Production> listProduction)
	{
	      
		  int i=0;
		  intialiserTableauOF();
			while(i<listProduction.size())
			{	
				
				Production production=listProduction.get(i);			
				Object []ligne={production.getDate() , production.getNumOF(), production.getStatut()};

				modeleOF.addRow(ligne);
				i++;
			}
	}	
	
	
	
	
	
	
	
	
	
	
	
	









List<Production> ChargerOF(String statutLancer, String depot)
{
	listProductions.clear();
	 listProductions=productionDAO.listeProductionEtatLancerAndNombreEmployerTravaillerEgaleAzero(statutLancer, depot);
		
	return listProductions;
}


public void InitialzeTous()
{

codeArticle.setText("");
quantite.setText("");
categorie.setSelectedIndex(-1);	
txtDateDebutPrev.setText("");
txtDateFinPrev.setText("");

comboLigneMachine.setSelectedIndex(-1);
comboMachine.setSelectedIndex(-1);
	
}




}
