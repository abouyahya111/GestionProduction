package presentation.stockPF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.AuthentificationView;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;

import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockPF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ConsulterSortiePF extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleDetailSortiePF;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	
	
	
	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	List<DetailTransferProduitFini> listDetailTransferProduitFiniImprimer= new ArrayList<DetailTransferProduitFini>();
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< Integer, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapArticleTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	private Map< String, Client> mapClient = new HashMap<>();
	
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Client> listClient =new ArrayList<Client>();
	
	TransferStockPF transferStock ;;
	
	ArticlesDAO articlesDAO;
	private Map< String, Articles> mapArticleProduction = new HashMap<>();
	private Map< String, Articles> mapCodeArticleProduction = new HashMap<>();
	private ClientDAO clientDAO;
	private DepotDAO depotDAO;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	JDateChooser dateChooser = new JDateChooser();
	private JDateChooser dateDu;
	SequenceurDAO sequenceurDAO;
	  JComboBox comboTypeSortie = new JComboBox();
	  JComboBox comboDepotDestination = new JComboBox();
	  JComboBox comboMagasinDestination = new JComboBox();
	  JLabel labeldepotdestination = new JLabel("D\u00E9pot Destination");
	  JLabel labelmagasindestination = new JLabel("Magasin Destination");
	  private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	  private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	  private List<EtatStockPF> listEtatStockPFFinAnnee =new ArrayList<EtatStockPF>();
	  private List<Articles> listArticles =new ArrayList<Articles>();
	  private JTextField txtCodeArticle;
	  private ProductionDAO productionDAO;
	  JComboBox comboArticle = new JComboBox();
	  JDateChooser dateAu = new JDateChooser();
	  private JTable tableDetailSortie;
	  private List<TransferStockPF> listTransfertStockPF =new ArrayList<TransferStockPF>();
	  private List<Production> listProduction =new ArrayList<Production>();
	  JComboBox comboNumSortie = new JComboBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterSortiePF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 941);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	transferStockPFDAO= new TransferStockPFDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	clientDAO=new ClientDAOImpl();
        	  sequenceurDAO=new SequenceurDAOImpl();
        	  detailTransferStockPFDAO= new DetailTransferProduitFiniDAOImpl();
        	  productionDAO=new ProductionDAOImpl();
        	  articlesDAO=new ArticlesDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
  
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(319, 888, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     	if(table.getSelectedRow()!=-1)
				  		     	{
				  		     		BigDecimal QuantiteTotal=BigDecimal.ZERO;
									BigDecimal MontantTotal=BigDecimal.ZERO;
									BigDecimal Prix=BigDecimal.ZERO;
				  		     		listProduction.clear();
				  		     		DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(table.getSelectedRow());
				  		     		if(detailTransferProduitFini!=null)
				  		     		{
				  		     			
				  		     			listDetailTransferProduitFiniImprimer=detailTransferStockPFDAO.findByTransferStockPF(detailTransferProduitFini.getTransferStockPF().getId());
				  		     			
				  		     			for(int j=0;j<listDetailTransferProduitFiniImprimer.size();j++)
				  		     			{
				  		     				DetailTransferProduitFini detailTransferProduitFiniImprimer=listDetailTransferProduitFiniImprimer.get(j);
				  		     				
				  		     				  QuantiteTotal=BigDecimal.ZERO;
											  MontantTotal=BigDecimal.ZERO;
											  Prix=BigDecimal.ZERO;
											  
				  		     				listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDateByArticle(detailTransferProduitFini.getTransferStockPF().getDateTransfer(),detailTransferProduitFini.getTransferStockPF().getDateTransfer(),Constantes.ETAT_OF_TERMINER,utilisateur.getCodeDepot(),detailTransferProduitFiniImprimer.getArticle());	
					  						
					  						for(int t=0;t<listProduction.size();t++)
					  						{
					  							Production production=listProduction.get(t);
					  							QuantiteTotal=QuantiteTotal.add(production.getQuantiteReel());
					  							MontantTotal=MontantTotal.add(production.getCoutTotal());
					  							
					  						}
					  						
					  						if(MontantTotal.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0 && QuantiteTotal.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0)
					  						{
					  							Prix=MontantTotal.divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
					  							detailTransferProduitFiniImprimer.setPrixUnitaire(Prix);
					  							detailTransferStockPFDAO.edit(detailTransferProduitFiniImprimer);
					  							
					  						}
				  		     			}
				  						

				  		     			
				  		     			
				  		     			listDetailTransferProduitFiniImprimer=detailTransferStockPFDAO.findByTransferStockPF(detailTransferProduitFini.getTransferStockPF().getId());
				  		     			afficher_tableDetailSortiePF(listDetailTransferProduitFiniImprimer);
				  		     			
				  		     		}
				  		     		
				  		     		
				  		     	}
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     table.setModel(new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     		"Date Sortie", "Code Sortie"
				  		     	}
				  		     ));
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 279, 1022, 250);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		     
					  		      
					  		      
				  		     
				  		  
					  		      	
					  		    
					  		      


				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Sortie PF");
				  		     	titledSeparator.setBounds(9, 238, 877, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 961, 214);
				  		     	add(layeredPane);
				  		  
				  		  JLabel lblCodeTrnsafer = new JLabel("N\u00B0 de Sortie :");
				  		  lblCodeTrnsafer.setBounds(632, 70, 74, 24);
				  		  layeredPane.add(lblCodeTrnsafer);
				  		  
				  		  JLabel lblDateSortie = new JLabel("Date Du");
				  		  lblDateSortie.setBounds(3, 27, 56, 26);
				  		  layeredPane.add(lblDateSortie);
				  		  
				  		    dateDu = new JDateChooser();
				  		  dateDu.setLocale(Locale.FRANCE);
				  		  dateDu.setDateFormatString("dd/MM/yyyy");
				  		  dateDu.setBounds(57, 27, 159, 26);
				  		  layeredPane.add(dateDu);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listDetailTransferProduitFiniImprimer.size()!=0){

					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFiniImprimer.get(0);
					 
						String date=dateFormat.format(detailTransferStockPF.getTransferStockPF().getDateTransfer());
					Map parameters = new HashMap();
					parameters.put("numTransfer", detailTransferStockPF.getTransferStockPF().getCodeTransfer());
					parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
					parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
					if(detailTransferStockPF.getMagasinDestination()!=null)
					{
						
						 
						parameters.put("depotdestination", "Depot Destination :");	
						parameters.put("destination", "Magasin Destination :");	
						parameters.put("client", detailTransferStockPF.getMagasinDestination().getDepot().getLibelle());	
						parameters.put("magasindestination", detailTransferStockPF.getMagasinDestination().getLibelle());	
						
						
						
						
						
						
					} else
					{
						parameters.put("depotdestination", "Depot Destination :");	
						parameters.put("destination", "Magasin Destination :");	
						parameters.put("client", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
						parameters.put("magasindestination", detailTransferStockPF.getMagasinSouce().getLibelle());	
					}
					
					
					if(detailTransferStockPF.getClient()!=null)
					{
						
						parameters.put("depotdestination", "Client :");	
						parameters.put("client", detailTransferStockPF.getClient().getNom());	
					}
									
					parameters.put("dateTransfer", date);
					JasperUtils.imprimerBonSortiePF(listDetailTransferProduitFiniImprimer,parameters,detailTransferStockPF.getTransferStockPF().getCodeTransfer());
				
				
				}else {
		  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
		  		  	}
			}
		});
		btnImprimer.setBounds(447, 888, 112, 23);
		btnImprimer.setIcon(imgImprimer);
		add(btnImprimer);
		
		
	     /*  
        if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
   		 	depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	     		comboDepotSource.addItem(depot.getLibelle());
	     		listDepot = depotDAO.findAll();	
	  	      int i=0;
	  	    comboDepotDestination.addItem("");
	  	      	while(i<listDepot.size())
	  	      		{	
	  	      			Depot depot=listDepot.get(i);
	  	      			mapDepotDestionation.put(depot.getLibelle(), depot);
	  	      			comboDepotDestination.addItem(depot.getLibelle());
	  	      			i++;
	  	      		}	
	     		
	     		
   }else {
   	
   	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotSource.put(depot.getLibelle(), depot);
	      			comboDepotSource.addItem(depot.getLibelle());
	      			i++;
	      		}
	      	i=0;
	    	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotDestionation.put(depot.getLibelle(), depot);
	      			comboDepotDestination.addItem(depot.getLibelle());
	      			i++;  	
	      	
	      	
   	
   }
    		
   }
        List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      if(listMagasin!=null){
	    	  
	    	  int j=0;
		      	while(j<listMagasin.size())
		      		{	
		      			Magasin magasin=listMagasin.get(j);
		      			comboMagasinSource.addItem(magasin.getLibelle());
		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
		      			j++;
		      		}
	      }
	       */
		
	      JLabel lblDateAu = new JLabel("Date Au");
	      lblDateAu.setBounds(244, 27, 56, 26);
	      layeredPane.add(lblDateAu);
	      
	        dateAu = new JDateChooser();
	      dateAu.setLocale(Locale.FRANCE);
	      dateAu.setDateFormatString("dd/MM/yyyy");
	      dateAu.setBounds(298, 27, 159, 26);
	      layeredPane.add(dateAu);
	      
	      JLabel lblCodeArticle = new JLabel("Code Article :");
	      lblCodeArticle.setBounds(3, 70, 94, 24);
	      layeredPane.add(lblCodeArticle);
	      
	      txtCodeArticle = new JTextField();
	      txtCodeArticle.addKeyListener(new KeyAdapter() {
	      	@Override
	      	public void keyPressed(KeyEvent e) {

	 		  	
  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					Articles articles=mapCodeArticleProduction.get(txtCodeArticle.getText().toUpperCase());
					if(articles!=null)
					{
						comboArticle.setSelectedItem(articles.getLiblle());
					}else
					{
						JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
						return;
					}
					
	      		}
			
				
  		  		
  		  
			
			
			
		
	      	}
	      });
	      txtCodeArticle.setColumns(10);
	      txtCodeArticle.setBounds(79, 70, 94, 24);
	      layeredPane.add(txtCodeArticle);
	      
	        comboArticle = new JComboBox();
	        comboArticle.addItemListener(new ItemListener() {
	        	public void itemStateChanged(ItemEvent e) {
	        		
	        		 if(e.getStateChange() == ItemEvent.SELECTED) {
	        		     	
	 	     	 		
		     	 		 Articles articles=mapArticleProduction.get(comboArticle.getSelectedItem());
		     	 		 
		     	 		if(articles!=null)
		     	 		{
		     	 			
				  			txtCodeArticle.setText(articles.getCodeArticle());	
				  	
				
		     	 		}else
		     	 		{
		     	 			txtCodeArticle.setText(""); ;	
		     	 		}
	              
		     	 	 	}
	        	}
	        });
	      comboArticle.setBounds(229, 70, 383, 25);
	      layeredPane.add(comboArticle);
	      AutoCompleteDecorator.decorate(comboArticle);
	      JLabel lblArticle = new JLabel("Article :");
	      lblArticle.setBounds(183, 69, 56, 24);
	      layeredPane.add(lblArticle);
	      
	      comboArticle.addItem("");
	      listArticles=articlesDAO.findAll();
	      for(int i=0;i<listArticles.size();i++)
	      {
	    	 Articles articles= listArticles.get(i);
	    	 comboArticle.addItem(articles.getLiblle());
	    	  mapArticleProduction.put(articles.getLiblle(), articles);
	    	  mapCodeArticleProduction.put(articles.getCodeArticle(), articles);
	    	  
	      }
	      
	      comboArticle.setSelectedItem("");
	      
	      JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
	      GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
	      gridBagLayout.rowWeights = new double[]{0.0};
	      gridBagLayout.rowHeights = new int[]{0};
	      gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	      gridBagLayout.columnWidths = new int[]{0, 0, 0};
	      titledSeparator_1.setTitle("Liste Detail Sortie PF");
	      titledSeparator_1.setBounds(9, 540, 1022, 30);
	      add(titledSeparator_1);
	      
	      JScrollPane scrollPane_1 = new JScrollPane((Component) null);
	      scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	      scrollPane_1.setBounds(9, 581, 1022, 274);
	      add(scrollPane_1);
	      
	      tableDetailSortie = new JTable();
	      tableDetailSortie.setModel(new DefaultTableModel(
	      	new Object[][] {
	      	},
	      	new String[] {
	      		"Code Article", "Article", "Quantite Sortie", "Magasin Source", "Magasin Destination", "Client"
	      	}
	      ));
	      tableDetailSortie.getColumnModel().getColumn(0).setPreferredWidth(115);
	      tableDetailSortie.getColumnModel().getColumn(1).setPreferredWidth(146);
	      tableDetailSortie.getColumnModel().getColumn(2).setPreferredWidth(95);
	      tableDetailSortie.getColumnModel().getColumn(3).setPreferredWidth(122);
	      tableDetailSortie.getColumnModel().getColumn(4).setPreferredWidth(122);
	      tableDetailSortie.getColumnModel().getColumn(5).setPreferredWidth(136);
	      tableDetailSortie.setFillsViewportHeight(true);
	      scrollPane_1.setViewportView(tableDetailSortie);
	      
	      JButton button = new JButton("Initialiser");
	      button.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {

	    	 
	    		comboArticle.setSelectedItem("");
	    		comboTypeSortie.setSelectedItem("");
	    		comboNumSortie.setSelectedItem("");
	    				
	    		
	    	
	      	}
	      });
	      button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	      button.setBounds(474, 117, 112, 23);
	      button.setIcon(imgInit);
	      layeredPane.add(button);
	      
	      JButton btnAfficherStock = new JButton("Afficher");
	      btnAfficherStock.setBounds(322, 116, 112, 24);
	      layeredPane.add(btnAfficherStock);
	      
	        comboNumSortie = new JComboBox();
	      comboNumSortie.setBounds(705, 71, 246, 25);
	      layeredPane.add(comboNumSortie);
	      AutoCompleteDecorator.decorate(comboNumSortie);
	      btnAfficherStock.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
	      		
	      		intialiserTableauDetailSortiePF();;
	      		
	      		if(dateDu.getDate()==null && dateAu.getDate()==null)	
	      		{
	      			JOptionPane.showMessageDialog(null, "Veuillez Selectionner la Date SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	      			return;
	      		}
	      			
	      		
	      		if(dateDu.getDate()==null && dateAu.getDate()!=null)
	      		{
	      			dateDu.setDate(dateAu.getDate());
	      		}
	      		
	      		if(dateDu.getDate()!=null && dateAu.getDate()==null)
	      		{
	      			dateAu.setDate(dateDu.getDate());
	      		}
	      		
	      		Articles articles=mapArticleProduction.get(comboArticle.getSelectedItem());
	      		 
	      		Depot depotSource=null;
	      		 
	      		
	      		 
	      		Magasin magasinSource=null;
	      		
 	      		
	      		 
	      		
	      		String req="";
	      		
	      		SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
	      		if(utilisateur.getType()!=null)
	      		{ 
	      			
	      			if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_ENVRAC))
	      			{
	      				req=req+"where d.transferStockPF.sourceProduction='"+SOURCE_ENVRAC+"' ";
	      				
	      			}else 
	      			
	      			if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_PRODUCTION))
	      			{
	      				req=req+"where d.transferStockPF.sourceProduction='"+SOURCE_PRODUCTION+"' ";
	      			}else
	      			{
	      				req=req+"where d.transferStockPF.sourceProduction='"+SOURCE_MIXTE+"' ";
	      			}
	      			
	      			
	      			req=req+"and d.transferStockPF.dateTransfer >='"+dcn.format(dateDu.getDate()) +"' and d.transferStockPF.dateTransfer<='"+dcn.format(dateAu.getDate())+"' ";
	      			
	      		} else
	      		{
	      			req=req+"where d.transferStockPF.dateTransfer >='"+dcn.format(dateDu.getDate())+"' and d.transferStockPF.dateTransfer<='"+dcn.format(dateAu.getDate())+"' ";
	      		}
	      		
	      		
	      		 
	      		
	      		if(!comboNumSortie.getSelectedItem().toString().equals(""))
	      		{
	      			req=req+"and d.transferStockPF.CodeTransfer='"+comboNumSortie.getSelectedItem().toString()+"' ";
	      		}
	      		
	      				
	      		if(articles!=null)
	      		{
	      			req=req+" and d.article.id='"+articles.getId()+"' ";
	      		}
	      			
	      	 
	      		
	      		if(depotSource!=null)
	      		{
	      			req=req+" and d.magasinSouce.depot.id='"+depotSource.getId()+"' ";
	      		}
	      		
	      	 
	      		
	      		if(magasinSource!=null)
	      		{
	      			req=req+" and d.magasinSouce.id='"+magasinSource.getId()+"' ";
	      		}
	      		
	      	 
	      		
	      		
	      		if(!req.equals(""))
	      		{
	      			
	      			req=req+" and (d.transferStockPF.statut='"+Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE+"' or d.transferStockPF.statut='"+Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE+"')  group by d.transferStockPF.id";
	      			
	      			listDetailTransferProduitFini=detailTransferStockPFDAO.findDetailTransferStockPFByRequette(req);
	      			afficher_tableMP(listDetailTransferProduitFini);
	      			
	      			
	      		}else
	      		{
	      			JOptionPane.showMessageDialog(null, "Veuillez Selectionner un Ou Plusieur Condition SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	      			return;
	      		}
	      		
	      		
	      					
	      }
	      });
	      
	      if(utilisateur.getType()!=null)
    		{ 
	    	 	if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_ENVRAC))
				{
		  		
		  		 listTransfertStockPF=transferStockPFDAO.findStatutOrStatutEnAttenteByType(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE,Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE, SOURCE_ENVRAC) ;   
				 
					
				}else 
				
				if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_PRODUCTION))
				{
					 listTransfertStockPF=transferStockPFDAO.findStatutOrStatutEnAttenteByType(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE,Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE, SOURCE_PRODUCTION) ;   
					 
				}else
				{
					 listTransfertStockPF=transferStockPFDAO.findStatutOrStatutEnAttenteByType(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE,Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE, SOURCE_MIXTE) ;   
					 
				}
    		}else
    		{
    			 listTransfertStockPF=transferStockPFDAO.findStatutOrStatutEnAttente(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE,Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE) ;   
    		}
	 
	      comboNumSortie.addItem("");
	 for(int i=0;i<listTransfertStockPF.size();i++)
	 {
		 TransferStockPF transferStockPF= listTransfertStockPF.get(i);
		 
		 comboNumSortie.addItem(transferStockPF.getCodeTransfer());
		 
	 }
	 
	 comboNumSortie.setSelectedItem("");
	 
		
	}
	
	
	void intialiser()
	{
		intialiserTableau();
		intialiserTableauDetailSortiePF();
		listDetailTransferProduitFiniImprimer.clear();
		
		
	}
	
	void afficher_tableMP(List<DetailTransferProduitFini> listDetailTransfertPF)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listDetailTransfertPF.size())
			{	
				
				DetailTransferProduitFini detailTransferProduitFini=listDetailTransfertPF.get(i);
				
				 
				Object []ligne={detailTransferProduitFini.getTransferStockPF().getDateTransfer(), detailTransferProduitFini.getTransferStockPF().getCodeTransfer()};

				modeleMP.addRow(ligne);
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	
	
	
	void afficher_tableDetailSortiePF(List<DetailTransferProduitFini> listDetailTransfertPF)
	{

		
		intialiserTableauDetailSortiePF();;
	
		String magasinDestination="";
		String clientPF="";
	        
		  int i=0;
			while(i<listDetailTransfertPF.size())
			{	
				  magasinDestination="";
				  clientPF="";
				
				DetailTransferProduitFini detailTransferProduitFini=listDetailTransfertPF.get(i);
				 
				
				if(detailTransferProduitFini.getMagasinDestination()!=null)
				{
					magasinDestination=detailTransferProduitFini.getMagasinDestination().getLibelle();
				}
				
				if(detailTransferProduitFini.getClient()!=null)
				{
					clientPF=detailTransferProduitFini.getClient().getNom();
				}
				
				Object []ligne={detailTransferProduitFini.getArticle().getCodeArticle(),detailTransferProduitFini.getArticle().getLiblle(),detailTransferProduitFini.getQuantite(),detailTransferProduitFini.getMagasinSouce().getLibelle(),magasinDestination,clientPF};

				modeleDetailSortiePF.addRow(ligne);
				
				i++;
			}
			
			
			tableDetailSortie.setModel(modeleDetailSortiePF); 
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
	mapQuantiteMP.clear();
	mapArticle.clear();
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapArticle.put(i, mapArticleTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}












void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Date Sortie","Code Sortie" 
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false 
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 
		 tableDetailSortie.getColumnModel().getColumn(0).setPreferredWidth(115);
	      tableDetailSortie.getColumnModel().getColumn(1).setPreferredWidth(146);
	      tableDetailSortie.getColumnModel().getColumn(2).setPreferredWidth(95);
	      tableDetailSortie.getColumnModel().getColumn(3).setPreferredWidth(122);
	      tableDetailSortie.getColumnModel().getColumn(4).setPreferredWidth(122);
	      tableDetailSortie.getColumnModel().getColumn(5).setPreferredWidth(136);
		 
		 
		 
   
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}




void intialiserTableauDetailSortiePF(){
	
	
	modeleDetailSortiePF =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article", "Article", "Quantite Sortie", "Magasin Source", "Magasin Destination", "Client"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false ,false,false,false ,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 tableDetailSortie.setModel(modeleDetailSortiePF); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
     table.getColumnModel().getColumn(1).setPreferredWidth(60);
   
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}
