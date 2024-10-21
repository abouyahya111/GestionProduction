package presentation.stockPF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

import org.apache.commons.collections4.functors.MapTransformer;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CoutArticlePFDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CoutArticlePFDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CoutArticlePF;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockPF;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.Sequenceur;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class SortirStockPF extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private JButton btnIntialiserOF;
	
	
	
	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	List<DetailTransferProduitFini> listDetailTransferProduitFiniAnnulee= new ArrayList<DetailTransferProduitFini>();
	List<DetailTransferProduitFini> listDesArticlesDejaSortie= new ArrayList<DetailTransferProduitFini>();
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
	
	TransferStockPF transferStock ;
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboClient=new JComboBox();;
	
	private JLabel lblMagasinSource;
	private JLabel labelclient;
	private JLabel lblMagasinDstination;
	private ClientDAO clientDAO;
	private DepotDAO depotDAO;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtRefTransfere;
	JDateChooser dateChooser = new JDateChooser();
	SequenceurDAO sequenceurDAO;
	   
	  private JComboBox comboSortieAnnule;
	  JComboBox comboDepotDestination = new JComboBox();
	  JComboBox comboMagasinDestination = new JComboBox();
	  JLabel labeldepotdestination = new JLabel("D\u00E9pot Destination");
	  JLabel labelmagasindestination = new JLabel("Magasin Destination");
	  private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	  private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	  private List<EtatStockPF> listEtatStockPFAfficher =new ArrayList<EtatStockPF>();
	  private List<EtatStockPF> listEtatStockPFFinAnnee =new ArrayList<EtatStockPF>();
	  private List<Production> listProduction =new ArrayList<Production>();
	  boolean sourceProduction=false;
	  boolean sourceEnvrac=false;
	  CoutArticlePFDAO coutArticlePFDAO;
	  ProductionDAO productionDAO;
	  private List<TransferStockPF> listSortieAnnule =new ArrayList<TransferStockPF>();
	  private Map< String, TransferStockPF> mapSortieAnnule = new HashMap<>();
	  boolean SortieAnnule=false;
	  long date;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SortirStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 651);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockPFDAO= new StockPFDAOImpl();
        	transferStockPFDAO= new TransferStockPFDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	clientDAO=new ClientDAOImpl();
        	  sequenceurDAO=new SequenceurDAOImpl();
        	  detailTransferStockPFDAO= new DetailTransferProduitFiniDAOImpl();
        	  coutArticlePFDAO=new CoutArticlePFDAOImpl();
        	  productionDAO=new ProductionDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
  
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(380, 602, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 229, 877, 362);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		     
					  		      
					  		      
				  		     
				  		  
					  		      	
					  		    
					  		      


				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Des Produits Fini");
				  		     	titledSeparator.setBounds(9, 188, 877, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 877, 164);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 69, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		      labelmagasindestination = new JLabel("Magasin Destination");
				  			labelmagasindestination.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  			labelmagasindestination.setBounds(326, 107, 121, 24);
				  			layeredPane.add(labelmagasindestination);
				  			
				  			  comboMagasinDestination = new JComboBox();
				  			comboMagasinDestination.setBounds(455, 108, 288, 24);
				  			layeredPane.add(comboMagasinDestination);
				  			
				  			  labeldepotdestination = new JLabel("D\u00E9pot Destination");
				  			labeldepotdestination.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  			labeldepotdestination.setBounds(289, 69, 114, 24);
				  			layeredPane.add(labeldepotdestination);
				  			
				  			 comboDepotDestination = new JComboBox();
				  			 comboDepotDestination.addActionListener(new ActionListener() {
				  			 	public void actionPerformed(ActionEvent e) {
				  			 		
				  			 		if(!comboDepotDestination.getSelectedItem().equals(""))
				  			 		{
				  			 		    Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
				  			 		 comboMagasinDestination.removeAllItems();
				  			 		    if(depot!=null)
				  			 		    {
				  			 		      List<Magasin> listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
				  			 		    }
							  		
				  			 		}
				  			 		
				  			 	}
				  			 });
				  			comboDepotDestination.setBounds(418, 69, 214, 24);
				  			layeredPane.add(comboDepotDestination);
				  			
				  			labelclient = new JLabel("Client");
				  			labelclient.setBounds(289, 68, 108, 26);
				  			layeredPane.add(labelclient);
				  			comboClient.setBounds(418, 69, 216, 24);
				  			layeredPane.add(comboClient);
				  		     	 comboDepotSource.setBounds(103, 69, 176, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 108, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 109, 213, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 46, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(308, 46, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JLabel lblCodeTrnsafer = new JLabel("N\u00B0 de Sortie :");
				  		  lblCodeTrnsafer.setBounds(649, 69, 94, 24);
				  		  layeredPane.add(lblCodeTrnsafer);
				  		  
				  		  txtRefTransfere = new JTextField();
				  		util.Utils.copycoller(txtRefTransfere);
				  		  txtRefTransfere.setBounds(725, 69, 131, 24);
				  		  layeredPane.add(txtRefTransfere);
				  		  txtRefTransfere.setColumns(10);
				  		  
				  		  JLabel lblDateSortie = new JLabel("Date Sortie");
				  		  lblDateSortie.setBounds(327, 9, 77, 26);
				  		  layeredPane.add(lblDateSortie);
				  		  
				  		    dateChooser = new JDateChooser();
				  		  dateChooser.setLocale(Locale.FRANCE);
				  		  dateChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateChooser.setBounds(403, 9, 159, 26);
				  		  layeredPane.add(dateChooser);
				  		  
				  		    comboSortieAnnule = new JComboBox();
				  		    comboSortieAnnule.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
	 
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		  comboSortieAnnule.setBounds(103, 11, 176, 24);
				  		  layeredPane.add(comboSortieAnnule);
				  		  
				  		  JLabel lblSortieVers = new JLabel("Sortie Annule :");
				  		  lblSortieVers.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  lblSortieVers.setBounds(10, 11, 114, 24);
				  		  layeredPane.add(lblSortieVers);
		
		JButton btnValiderTransfer = new JButton("Valider Transfer PF");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
				if(magasinSource==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Magasin Source SVP","Erreur",JOptionPane.ERROR_MESSAGE);
					return;
				}
					
					if(dateChooser.getDate()==null)
					{
						JOptionPane.showMessageDialog(null, "Veuillez entrer la date de sortie SVP !!!!! , Veuillez Afficher Stock SVP");
						return;
					}
					
				 
					
					
					
					
					
					
					if(listDetailTransferProduitFini.size()!=0)
					{
						JOptionPane.showMessageDialog(null, "Transfert Stock PF deja Sortie !!!!! , Veuillez Afficher Stock SVP");
						return;
					}
					
					
					 if(SortieAnnule==false)
					 {
						 
							transferStock = new TransferStockPF();
							remplirDetailTransfer();
							String dateyear =""; 
							SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
							dateyear= dcn.format(dateChooser.getDate());
							
							if(listDetailTransferProduitFini.size()!=0)
							{
								String codeTransfert=Utils.genererCodeSortiePF(depot.getCode(),dateyear);
								txtRefTransfere.setText(CODE_NUM_SORTIE_PF+codeTransfert);
								transferStock.setCodeTransfer(txtRefTransfere.getText());
								transferStock.setCreerPar(AuthentificationView.utilisateur);
								transferStock.setDate(new Date());
								transferStock.setDateTransfer(dateChooser.getDate());
								transferStock.setStatut(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE);
								
								if(sourceEnvrac==true && sourceProduction==false)
								{
									transferStock.setSourceProduction(SOURCE_ENVRAC);
								}
								
								if(sourceEnvrac==false && sourceProduction==true)
								{
									transferStock.setSourceProduction(SOURCE_PRODUCTION);
								}
								
								if(sourceEnvrac==true && sourceProduction==true)
								{
									transferStock.setSourceProduction(SOURCE_MIXTE);
								}
								
								//transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
								transferStockPFDAO.add(transferStock);
								BigDecimal QuantiteTotal=BigDecimal.ZERO;
								BigDecimal MontantTotal=BigDecimal.ZERO;
								BigDecimal Prix=BigDecimal.ZERO;
								for(int y=0;y<listDetailTransferProduitFini.size();y++)
								{
									
									  QuantiteTotal=BigDecimal.ZERO;
									  MontantTotal=BigDecimal.ZERO;
									  Prix=BigDecimal.ZERO;
									DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(y);
									detailTransferProduitFini.setTransferStockPF(transferStock);
									
									
									
									/*
			  						CoutArticlePF coutArticlePF=coutArticlePFDAO.CoutArticlePFByArticle(detailTransferProduitFini.getArticle());
			  						if(coutArticlePF!=null)
			  						{
			  							detailTransferProduitFini.setPrixUnitaire(coutArticlePF.getCout());
			  						} 
			  						*/
			  						
			  						
			  						listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDateByArticle(dateChooser.getDate(),dateChooser.getDate(),Constantes.ETAT_OF_TERMINER,utilisateur.getCodeDepot(),detailTransferProduitFini.getArticle());	
			  						
			  						for(int t=0;t<listProduction.size();t++)
			  						{
			  							Production production=listProduction.get(t);
			  							QuantiteTotal=QuantiteTotal.add(production.getQuantiteReel());
			  							MontantTotal=MontantTotal.add(production.getCoutTotal());
			  							
			  						}
			  						
			  						if(MontantTotal.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0 && QuantiteTotal.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0)
			  						{
			  							Prix=MontantTotal.divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
			  							detailTransferProduitFini.setPrixUnitaire(Prix);
			  							
			  							
			  						}
			  						
			  						
			  						
									detailTransferStockPFDAO.add(detailTransferProduitFini);
								}
								
								
								
								JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
								
								Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(dateyear,ETAT_TRANSFER_STOCK_SORTIE_PF+"_"+depot.getCode());
								if(sequenceur!=null)
								{
									int valeur=sequenceur.getValeur()+1;
									sequenceur.setValeur(valeur);
									sequenceurDAO.edit(sequenceur);
								}else
								{
									Sequenceur  sequenceurTmp=new Sequenceur();
									sequenceurTmp.setCode(dateyear);
									sequenceurTmp.setLibelle(ETAT_TRANSFER_STOCK_SORTIE_PF+"_"+depot.getCode());
									sequenceurTmp.setValeur(1);
									sequenceurDAO.add(sequenceurTmp);
								}
								
								
								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  
								
								 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(0);
								 
									String date=dateFormat.format(detailTransferStockPF.getTransferStockPF().getDateTransfer());
								Map parameters = new HashMap();
								parameters.put("numTransfer", detailTransferStockPF.getTransferStockPF().getCodeTransfer());
								parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
								parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
								 
									 
										parameters.put("depotdestination", "Depot Destination :");	
										parameters.put("destination", "Magasin Destination :");	
										parameters.put("client", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
										parameters.put("magasindestination", detailTransferStockPF.getMagasinSouce().getLibelle());	
								 
								 
												
								parameters.put("dateTransfer", date);
								JasperUtils.imprimerBonSortiePF(listDetailTransferProduitFini,parameters,transferStock.getCodeTransfer());
								
								
								
								
								intialiser();
								
								
							}else
							{
								return;
						
					
						}
						 
					 }else
					 {
						 TransferStockPF transferStockPF=mapSortieAnnule.get(comboSortieAnnule.getSelectedItem().toString());
						 
							remplirDetailTransfer();
							String dateyear =""; 
							SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
							dateyear= dcn.format(dateChooser.getDate());
							
							if(listDetailTransferProduitFini.size()!=0)
							{
								 
								txtRefTransfere.setText(transferStockPF.getCodeTransfer());
								 
								transferStockPF.setCreerPar(AuthentificationView.utilisateur);
								transferStockPF.setDate(new Date());
								transferStockPF.setDateTransfer(dateChooser.getDate());
								transferStockPF.setStatut(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE);
								
								if(sourceEnvrac==true && sourceProduction==false)
								{
									transferStockPF.setSourceProduction(SOURCE_ENVRAC);
								}
								
								if(sourceEnvrac==false && sourceProduction==true)
								{
									transferStockPF.setSourceProduction(SOURCE_PRODUCTION);
								}
								
								if(sourceEnvrac==true && sourceProduction==true)
								{
									transferStockPF.setSourceProduction(SOURCE_MIXTE);
								}
								
								 
								 listDetailTransferProduitFiniAnnulee=detailTransferStockPFDAO.findByTransferStockPF(transferStockPF.getId());
								for(int y=0;y<listDetailTransferProduitFiniAnnulee.size();y++)
								{
									DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFiniAnnulee.get(y);
									detailTransferStockPFDAO.delete(detailTransferProduitFini.getId());
								}
								
								//transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini);
								transferStockPFDAO.edit(transferStockPF);
								BigDecimal QuantiteTotal=BigDecimal.ZERO;
								BigDecimal MontantTotal=BigDecimal.ZERO;
								BigDecimal Prix=BigDecimal.ZERO;
								for(int y=0;y<listDetailTransferProduitFini.size();y++)
								{
									  QuantiteTotal=BigDecimal.ZERO;
									  MontantTotal=BigDecimal.ZERO;
									  Prix=BigDecimal.ZERO;
									DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFini.get(y);
									detailTransferProduitFini.setTransferStockPF(transferStockPF);
									
									
									
									/*
			  						CoutArticlePF coutArticlePF=coutArticlePFDAO.CoutArticlePFByArticle(detailTransferProduitFini.getArticle());
			  						if(coutArticlePF!=null)
			  						{
			  							detailTransferProduitFini.setPrixUnitaire(coutArticlePF.getCout());
			  						} 
			  						*/
			  						
			  						listProduction=productionDAO.listeProductionTerminerbyDepotEntreDeuxDateByArticle(dateChooser.getDate(),dateChooser.getDate(),Constantes.ETAT_OF_TERMINER,utilisateur.getCodeDepot(),detailTransferProduitFini.getArticle());	
			  						
			  						for(int t=0;t<listProduction.size();t++)
			  						{
			  							Production production=listProduction.get(t);
			  							QuantiteTotal=QuantiteTotal.add(production.getQuantiteReel());
			  							MontantTotal=MontantTotal.add(production.getCoutTotal());
			  							
			  						}
			  						
			  						if(MontantTotal.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0 && QuantiteTotal.setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0)
			  						{
			  							Prix=MontantTotal.divide(QuantiteTotal, 6, RoundingMode.HALF_UP);
			  							detailTransferProduitFini.setPrixUnitaire(Prix);
			  							
			  							
			  						}
			  						
									detailTransferStockPFDAO.add(detailTransferProduitFini);
								}
								
								
								
								JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
								 
								
								DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						  		  
								
								 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(0);
								 
									String date=dateFormat.format(detailTransferStockPF.getTransferStockPF().getDateTransfer());
								Map parameters = new HashMap();
								parameters.put("numTransfer", detailTransferStockPF.getTransferStockPF().getCodeTransfer());
								parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
								parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
								 
									 
										parameters.put("depotdestination", "Depot Destination :");	
										parameters.put("destination", "Magasin Destination :");	
										parameters.put("client", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());	
										parameters.put("magasindestination", detailTransferStockPF.getMagasinSouce().getLibelle());	
									 
									
									
								 
												
								parameters.put("dateTransfer", date);
								JasperUtils.imprimerBonSortiePF(listDetailTransferProduitFini,parameters,transferStockPF.getCodeTransfer());
								
								
								
								
								intialiser();
								
								
							}else
							{
								return;
						
					
						}
					 }
					
			
			}
			
			ChargerSortieAnnule();
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(212, 602, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(933, 48, 102, 24);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				    sourceProduction=false;
				    sourceEnvrac=false;
							if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {
				  					
				  					
				  					 
				  					
				  					if(listSortieAnnule.size()!=0)
				  					{
				  						if(comboSortieAnnule.getSelectedItem().toString().equals(""))
				  						{
				  							JOptionPane.showMessageDialog(null, "Veuillez Valider Les Sortie Annules SVP !!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  							return;
				  						}else
				  						{
				  							SortieAnnule=true; 
				  							
				  							TransferStockPF transferStockPF=mapSortieAnnule.get(comboSortieAnnule.getSelectedItem().toString());
				  							SimpleDateFormat dformat=new SimpleDateFormat("dd/MM/yyyy");
				  							
				  						String date=dformat.format(transferStockPF.getDateTransfer());
				  						System.out.println(date);
												try {
													dateChooser.setDate( dformat.parse(date));
												} catch (ParseException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
											
				  							 
				  							txtRefTransfere.setText("");
						  					listDetailTransferProduitFini.clear();
						  					
						  					listEtatStockPFAfficher.clear();
						  					 CalculerStockPF();
						  					 
						  					for(int j=0;j<listEtatStockPF.size();j++)
						  					{
						  						if(listEtatStockPF.get(j).getQuantiteFinale().setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0)
						  						{
						  							
						  							if(utilisateur.getType()!=null)
						  							{
						  								if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_PRODUCTION))
						  								{
						  									if(listEtatStockPF.get(j).getQuantiteProduction().compareTo(BigDecimal.ZERO)!=0)
								  							{
								  								sourceProduction=true;
								  								
								  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
								  								
								  							}
						  									
						  								}
						  								
						  								if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_ENVRAC))
						  								{
						  									if(listEtatStockPF.get(j).getQuantiteProduction().compareTo(BigDecimal.ZERO)==0)
								  							{
						  										sourceEnvrac=true;
								  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
								  								
								  							}
						  									
						  								}
						  								
						  								
						  								
						  							}else
						  							{
						  								if(listEtatStockPF.get(j).getQuantiteProduction().compareTo(BigDecimal.ZERO)!=0)
							  							{
							  								sourceProduction=true;
							  								
							  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
							  								
							  							}else
							  							{
							  								sourceEnvrac=true;
							  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
							  							}
						  							}
						  							 
						  							
						  							
						  							
						  							
						  						}
						  						
						  					}
						  					
						  					listDetailTransferProduitFiniAnnulee=detailTransferStockPFDAO.findByTransferStockPF(transferStockPF.getId());
						  					boolean existe=false;
						  					for(int d=0;d<listEtatStockPFAfficher.size();d++)
						  					{
						  						existe=false;
						  						EtatStockPF EtatStockPF=listEtatStockPFAfficher.get(d);
						  						
						  						for(int j=0;j<listDetailTransferProduitFiniAnnulee.size();j++)
							  					{
						  							
						  							DetailTransferProduitFini detailTransferProduitFini=listDetailTransferProduitFiniAnnulee.get(j);
						  							if(EtatStockPF.getArticle().getId()==detailTransferProduitFini.getArticle().getId())
						  							{
						  								EtatStockPF.setQuantiteSortieAnnulee(detailTransferProduitFini.getQuantite());
						  								existe=true;
						  								
						  							}
							  						
							  					}
						  						
						  						if(existe==false)
						  						{
						  							EtatStockPF.setQuantiteSortieAnnulee(BigDecimal.ZERO);
						  						}
						  						listEtatStockPFAfficher.set(d, EtatStockPF);
						  						
						  					}
						  					
						  					
						  					afficher_tableMP_SortieAnnule(listEtatStockPFAfficher);
						  			 		
				  					}
				  					}else
				  					{
				  						
				  						SortieAnnule=false;
				  						
				  						txtRefTransfere.setText("");
					  					listDetailTransferProduitFini.clear();
					  					
					  					listEtatStockPFAfficher.clear();
					  					 CalculerStockPF();
					  					 
					  					for(int j=0;j<listEtatStockPF.size();j++)
					  					{
					  						if(listEtatStockPF.get(j).getQuantiteFinale().setScale(6, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO.setScale(6, RoundingMode.HALF_UP))!=0)
					  						{
					  							
					  							if(utilisateur.getType()!=null)
					  							{
					  								if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_PRODUCTION))
					  								{
					  									if(listEtatStockPF.get(j).getQuantiteProduction().compareTo(BigDecimal.ZERO)!=0)
							  							{
							  								sourceProduction=true;
							  								
							  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
							  								
							  							}
					  									
					  								}
					  								
					  								if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_ENVRAC))
					  								{
					  									if(listEtatStockPF.get(j).getQuantiteProduction().compareTo(BigDecimal.ZERO)==0)
							  							{
					  										sourceEnvrac=true;
							  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
							  								
							  							}
					  									
					  								}
					  								
					  								
					  								
					  							}else
					  							{
					  								if(listEtatStockPF.get(j).getQuantiteProduction().compareTo(BigDecimal.ZERO)!=0)
						  							{
						  								sourceProduction=true;
						  								
						  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
						  								
						  							}else
						  							{
						  								sourceEnvrac=true;
						  								listEtatStockPFAfficher.add(listEtatStockPF.get(j));
						  							}
					  							}
					  							 
					  							
					  							
					  							
					  							
					  						}
					  						
					  					}
					  					
					  					
					  					afficher_tableMP(listEtatStockPFAfficher);
				  						
				  					}
				  					
				  			
				  					
				  					 
				  					
				  					
				  					
				  					
				  					
				  				}
				  			  }
		});
		btnAfficherStock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listDetailTransferProduitFini.size()!=0){
		  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  
					
					 DetailTransferProduitFini detailTransferStockPF=listDetailTransferProduitFini.get(0);
					 
						String date=dateFormat.format(detailTransferStockPF.getTransferStockPF().getDateTransfer());
					Map parameters = new HashMap();
					parameters.put("numTransfer", detailTransferStockPF.getTransferStockPF().getCodeTransfer());
					parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
					parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());
					parameters.put("client", detailTransferStockPF.getClient().getNom());					
					parameters.put("dateTransfer", date);
					JasperUtils.imprimerBonSortiePF(listDetailTransferProduitFini,parameters,transferStock.getCodeTransfer());
					
				//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
		  		  	}else {
		  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
		  		  	}
			}
		});
		btnImprimer.setBounds(508, 602, 112, 23);
		add(btnImprimer);
		listClient=clientDAO.findAll();
		for(int j=0;j<listClient.size();j++)
		{
			
		Client 	client =listClient.get(j);
		comboClient.addItem(client.getNom());
		mapClient.put(client.getNom(), client);
			
			
		}
				  		     
		 
		 
		
		
		
		comboClient.addItem("");
		
		comboClient.setVisible(false);
		labelclient.setVisible(false);
		comboDepotDestination.setVisible(false);
		comboMagasinDestination.setVisible(false);
		labeldepotdestination.setVisible(false);
		labelmagasindestination.setVisible(false);
		
		
	       
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
        
        comboMagasinSource.addItem("");	
        List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
	      if(listMagasin!=null){
	    	  
	    	  int j=0;
		      	while(j<listMagasin.size())
		      		{	
		      			Magasin magasin=listMagasin.get(j);
		      			if(magasin.getId()==2)
		      			{
		      				comboMagasinSource.addItem(magasin.getLibelle());
			      			mapMagasinSource.put(magasin.getLibelle(), magasin);
		      			}
		      			
		      			j++;
		      		}
	      }
	      comboMagasinSource.setSelectedItem("");    
		ChargerSortieAnnule();
	}
	
	void ChargerSortieAnnule()
	{
		
		if(utilisateur.getType()!=null)
			{
				if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_PRODUCTION))
				{
					listSortieAnnule=transferStockPFDAO.findStatutByType(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ANNULE, SOURCE_PRODUCTION);
					
				}
				
				if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_ENVRAC))
				{
					listSortieAnnule=transferStockPFDAO.findStatutByType(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ANNULE, SOURCE_ENVRAC); 
					
				}
				
				
				
			}
		comboSortieAnnule.removeAllItems();
		comboSortieAnnule.addItem("");
		
		for(int i=0;i<listSortieAnnule.size();i++)
		{
			TransferStockPF transferStockPF=listSortieAnnule.get(i);
			comboSortieAnnule.addItem(transferStockPF.getCodeTransfer());
			mapSortieAnnule.put(transferStockPF.getCodeTransfer(), transferStockPF);
			
		}
		
		comboSortieAnnule.setSelectedItem("");
		
		
	}
	
	
	
	void intialiser()
	{
		comboClient.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		
		comboMagasinSource.setSelectedItem("");
comboDepotDestination.setSelectedItem("");
		
		comboMagasinDestination.setSelectedItem("");
		
		for(int j=0;j<table.getRowCount();j++){
			table.setValueAt("", j, 2);
						
		}
				
		
	}
	
	void afficher_tableMP(List<EtatStockPF> listStockPF)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listStockPF.size())
			{	
				
				EtatStockPF stockPF=listStockPF.get(i);
				mapArticleTmp.put(stockPF.getArticle().getCodeArticle(), stockPF.getArticle());
				Object []ligne={stockPF.getArticle().getCodeArticle(),stockPF.getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(stockPF.getQuantiteFinale()),""};

				modeleMP.addRow(ligne);
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	
	
	void afficher_tableMP_SortieAnnule(List<EtatStockPF> listStockPF)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listStockPF.size())
			{	
				
				EtatStockPF stockPF=listStockPF.get(i);
				mapArticleTmp.put(stockPF.getArticle().getCodeArticle(), stockPF.getArticle());
				Object []ligne={stockPF.getArticle().getCodeArticle(),stockPF.getArticle().getLiblle(),NumberFormat.getNumberInstance(Locale.FRANCE).format(stockPF.getQuantiteFinale()),stockPF.getQuantiteSortieAnnulee()};

				modeleMP.addRow(ligne);
				
				i++;
			}
			table.setModel(modeleMP); 
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


List<DetailTransferProduitFini> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	boolean erreur=false;
	listDetailTransferProduitFini.clear();
	
	CalculerStockPF();
	CalculerStockPFFinAnnee();
	BigDecimal QuantiteStock=BigDecimal.ZERO;
	BigDecimal QuantiteStockFinAnnee=BigDecimal.ZERO;
	for(int i=0;i<mapArticle.size();i++){
		prixMoyen=BigDecimal.ZERO;
		QuantiteStock=BigDecimal.ZERO;
		QuantiteStockFinAnnee=BigDecimal.ZERO;
		
		DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		
		Articles article =mapArticle.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(article.getCodeArticle()));
		 
	if(quantite.setScale(9).compareTo(BigDecimal.ZERO.setScale(9))!=0)
	{
		for(int t=0;t<listEtatStockPFAfficher.size();t++)
		{
			if(listEtatStockPFAfficher.get(t).getArticle().getId()==article.getId())
			{
				QuantiteStock=listEtatStockPFAfficher.get(t).getQuantiteFinale();
				prixMoyen=listEtatStockPFAfficher.get(t).getPrixFinale();
			}
		}
		
		
		for(int t=0;t<listEtatStockPFFinAnnee.size();t++)
		{
			if(listEtatStockPFFinAnnee.get(t).getArticle().getId()==article.getId())
			{
				QuantiteStockFinAnnee=listEtatStockPFFinAnnee.get(t).getQuantiteFinale();
			 
			}
		}
		 
		
		if(QuantiteStock.compareTo(quantite)>=0 && QuantiteStockFinAnnee.compareTo(quantite)>=0){
			
			/*
			if(SortieAnnule==false)
			{
				listDesArticlesDejaSortie.clear();
				
				listDesArticlesDejaSortie=detailTransferStockPFDAO.lesArticleSortieBydateByArticleByTypeBayMagasin(article, Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE,Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE, magasinSource, dateChooser.getDate());
				
				System.out.println(listDesArticlesDejaSortie.size());
				
				if(listDesArticlesDejaSortie.size()!=0)
				{
					JOptionPane.showMessageDialog(null, "Stock de : «"+article.getLiblle()+"»  est Déja Sortie dans cette Journee !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					erreur=true;
				}
			}
	
			*/
			
			
			if(comboSortieAnnule.getSelectedItem().equals(Constantes.SORTIE_PF_DEPOT))
			{
			 
				 Depot depot=mapDepotDestionation.get(comboDepotDestination.getSelectedItem().toString());
				 
		   Magasin magasin=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem().toString());
		   if(magasin.getId()!=magasinSource.getId())
		   {
			   detailTransferStockPF.setMagasinDestination(magasin);
			   
		   }
		   
				
			}else if(comboSortieAnnule.getSelectedItem().equals(Constantes.SORTIE_PF_CLIENT))
			{
				 
			 Client client=mapClient.get(comboClient.getSelectedItem().toString());
						
			 detailTransferStockPF.setClient(client);
			 
			  
			}
			
			
	
		
		 
	
		detailTransferStockPF.setDateTransfer(dateChooser.getDate());
		detailTransferStockPF.setTypeTransfer(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE);
		
		detailTransferStockPF.setMagasinSouce(magasinSource);
		detailTransferStockPF.setArticle(article);
		detailTransferStockPF.setQuantite(quantite);
		detailTransferStockPF.setPrixUnitaire(prixMoyen);
		if(SortieAnnule==false)
		{
			detailTransferStockPF.setTransferStockPF(transferStock);
			
		}
		
		listDetailTransferProduitFini.add(detailTransferStockPF);
		
	}else {
		
		JOptionPane.showMessageDialog(null, "Stock de : «"+article.getLiblle()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
		erreur=true;
	
	}
	}
		

		
	}
	
	
	if(erreur==true)
	{
 
		
		listDetailTransferProduitFini.clear();
		
	}
	


	
	return listDetailTransferProduitFini;
	
}



void CalculerStockPF()
{
	 

	try {
		 URL url = new URL("http://www.google.com");
		
		  // opening the connection
	     
		try {
			HttpURLConnection httpCon = (HttpURLConnection)url.openConnection();
			
			 // getting the date of URL connection
		       date = httpCon.getDate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	 Date datedebut=null;
 
	try {
		datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	
	
 
	
	
	
	
	Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
	
	

	
	detailTransferStockPFDAO.ViderSession();
	
	
	listEtatStockPF.clear();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFReception =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFReceptionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortieEnAttente =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortieEnAttenteGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	
	

	BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	BigDecimal quantiteTotalSortie=new BigDecimal(0);
	
	BigDecimal quantiteTotalproduction=new BigDecimal(0);
	
	BigDecimal montantTotalEntrer=new BigDecimal(0);
	BigDecimal  montantTotalSortie=new BigDecimal(0);
	
	BigDecimal montantTotalproduction=new BigDecimal(0);

	BigDecimal quantiteTotalFinale=new BigDecimal(0);
	
	boolean trouve=false;
	//FamilleArticlePF familleArticle=mapFamilleArticles.get(comboFamille.getSelectedItem());
	Articles article=null;
	
	if(magasin!=null)
	{
		

		
				    		
	
		
	Client client=null;

			listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, new Date(date), article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
			listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, new Date(date), article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
			

			listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, new Date(date), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
			listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, new Date(date), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
			
			listDetailTransferStockPFReception=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutReception(datedebut, new Date(date), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
			listDetailTransferStockPFReceptionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutReception(datedebut, new Date(date), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		
			
		
			listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, new Date(date), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
			listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, new Date(date), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		
			listDetailTransferStockPFSortieEnAttente=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, new Date(date), article, Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE,magasin,client);
			listDetailTransferStockPFSortieEnAttenteGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, new Date(date), article, Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE_ENATTENTE,magasin,client);
	
		
		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		
		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		{
			
			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
			
			if(article==null)
			{
				EtatStockPF etatstock=new EtatStockPF();
   			etatstock.setArticle(detailtransferstockpf.getArticle());
   			
   			etatstock.setQuantiteProduction(BigDecimal.ZERO);
   			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
   			etatstock.setQuantiteSortie(BigDecimal.ZERO);
   			
   			etatstock.setMontantProduction (BigDecimal.ZERO);
   			etatstock.setMontantEntrer (BigDecimal.ZERO);
   			etatstock.setMontantSortie(BigDecimal.ZERO);
   			etatstock.setQuantiteSortieAnnulee(BigDecimal.ZERO);
   			etatstock.setQuantiteFinale(BigDecimal.ZERO);
   			
   			
   			
   			listEtatStockPF.add(etatstock);
			}else
			{
				if(detailtransferstockpf.getArticle().getId()==article.getId())
				{
					EtatStockPF etatstock=new EtatStockPF();
	    			etatstock.setArticle(detailtransferstockpf.getArticle());
	    			
	    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
	    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
	    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
	    			etatstock.setMontantProduction(BigDecimal.ZERO);
	    			etatstock.setMontantEntrer(BigDecimal.ZERO);
	    			etatstock.setMontantSortie(BigDecimal.ZERO);
	    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
	    			listEtatStockPF.add(etatstock);
				}
				
				
				
			}
			
			
		}
		
		
		
		
		// calculer la quantite production
		
		
	for(int j=0;j<listDetailTransferStockPFProductionGroupebyPF.size();j++)
	{
		
		quantiteTotalproduction=new BigDecimal(0);
		montantTotalproduction=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFProduction.size();k++)
	{
		
		if(listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().getId() ==listDetailTransferStockPFProduction.get(k).getArticle().getId() )
		{
			
			quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(k).getQuantite());
			montantTotalproduction=montantTotalproduction.add(listDetailTransferStockPFProduction.get(k).getQuantite().multiply(listDetailTransferStockPFProduction.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalproduction.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPF.size();i++)
	    	{
				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPF.get(i);
   				etatstockpf.setQuantiteProduction(quantiteTotalproduction);
   				etatstockpf.setMontantProduction(montantTotalproduction);
   				listEtatStockPF.set(i, etatstockpf);
   			
   				
   			}
	    	}
			
			
			
		}
		
	}
	

		// calculer la quantite Entrer
	
	
	for(int j=0;j<listDetailTransferStockPFEntrerGroupebyPF.size();j++)
	{
		
		quantiteTotalEntrer=new BigDecimal(0);
		montantTotalEntrer=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFEntrer.size();k++)
	{
		
		if(listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFEntrer.get(k).getArticle().getId() )
		{
			
			quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(k).getQuantite());
			montantTotalEntrer=montantTotalEntrer.add(listDetailTransferStockPFEntrer.get(k).getQuantite().multiply(listDetailTransferStockPFEntrer.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPF.size();i++)
	    	{
				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPF.get(i);
   				etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
   				etatstockpf.setMontantEntrer(montantTotalEntrer);
   				 
   				listEtatStockPF.set(i, etatstockpf);
   			
   				
   			}
				
	    	}
			
			
			
		}
		
	}
	
	/// RECEPTION
	
	for(int j=0;j<listDetailTransferStockPFReceptionGroupebyPF.size();j++)
	{
		
		quantiteTotalEntrer=new BigDecimal(0);
		montantTotalEntrer=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFReception.size();k++)
	{
		
		if(listDetailTransferStockPFReceptionGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFReception.get(k).getArticle().getId() )
		{
			
			quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFReception.get(k).getQuantite());
			montantTotalEntrer=montantTotalEntrer.add(listDetailTransferStockPFReception.get(k).getQuantite().multiply(listDetailTransferStockPFReception.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPF.size();i++)
	    	{
				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFReceptionGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPF.get(i);
   				etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
   				etatstockpf.setMontantEntrer(etatstockpf.getMontantEntrer().add(montantTotalEntrer) );
   				listEtatStockPF.set(i, etatstockpf);
   			
   				
   			}
				
	    	}
			
			
			
		}
		
	}
	
	
	
//calculer la quantite Sortie
	
	
	for(int j=0;j<listDetailTransferStockPFSortieGroupebyPF.size();j++)
	{
		
		quantiteTotalSortie=new BigDecimal(0);
		montantTotalSortie=new BigDecimal(0);
		 
			
	for(int k=0;k<listDetailTransferStockPFSortie.size();k++)
	{
		
		if(listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFSortie.get(k).getArticle().getId() )
		{
			if(listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId()==334)
			{
				System.out.println("ok");
			}
			quantiteTotalSortie=quantiteTotalSortie.add(listDetailTransferStockPFSortie.get(k).getQuantite());
			montantTotalSortie=montantTotalSortie.add(listDetailTransferStockPFSortie.get(k).getQuantite().multiply(listDetailTransferStockPFSortie.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalSortie.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPF.size();i++)
	    	{
				if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId())
   			{
					if(listEtatStockPF.get(i).getArticle().getId()==334)
					{
						System.out.println("ok");
					}	
					
   				EtatStockPF etatstockpf=listEtatStockPF.get(i);
   				etatstockpf.setQuantiteSortie(quantiteTotalSortie);
   				etatstockpf.setMontantSortie(montantTotalSortie);
   				listEtatStockPF.set(i, etatstockpf);
   			
   				
   			}
	    	}
			
			
			
		}
		
	}
	
	
	//calculer la quantite Sortie En Attente
	if(utilisateur.getType()!=null)
	{
		if(utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_PRODUCTION) || utilisateur.getType().equals(Constantes.UTILISATEUR_SORTIE_ENVRAC))
			{
				 
			for(int j=0;j<listDetailTransferStockPFSortieEnAttenteGroupebyPF.size();j++)
			{
				
				quantiteTotalSortie=new BigDecimal(0);
				montantTotalSortie=new BigDecimal(0);
				 
					
			for(int k=0;k<listDetailTransferStockPFSortieEnAttente.size();k++)
			{
				
				if(listDetailTransferStockPFSortieEnAttenteGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFSortieEnAttente.get(k).getArticle().getId() )
				{
					
					quantiteTotalSortie=quantiteTotalSortie.add(listDetailTransferStockPFSortieEnAttente.get(k).getQuantite());
					montantTotalSortie=montantTotalSortie.add(listDetailTransferStockPFSortieEnAttente.get(k).getQuantite().multiply(listDetailTransferStockPFSortieEnAttente.get(k).getPrixUnitaire()));
					//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
					
				}
				
				
			}
				if( !quantiteTotalSortie.equals(BigDecimal.ZERO))
				{
					
					for(int i=0;i<listEtatStockPF.size();i++)
			    	{
						if(listEtatStockPF.get(i).getArticle().getId()== listDetailTransferStockPFSortieEnAttenteGroupebyPF.get(j).getArticle().getId())
		   			{
		   				EtatStockPF etatstockpf=listEtatStockPF.get(i);
		   				etatstockpf.setQuantiteSortie(etatstockpf.getQuantiteSortie().add(quantiteTotalSortie) );
		   				etatstockpf.setMontantSortie(etatstockpf.getMontantSortie().add(montantTotalSortie) );
		   				
		   				listEtatStockPF.set(i, etatstockpf);
		   			
		   				
		   			}
			    	}
					
					
					
				}
				
			}
			
			}
			
			 
	
	}
	
		
		
		
		
		
	
	
	// Calculer Stock Finale
	
	
  	for(int i=0;i<listEtatStockPF.size();i++)
	{
  	 BigDecimal prixMoyen=BigDecimal.ZERO;
  	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
  		   
			EtatStockPF etatstockpf=listEtatStockPF.get(i);
			if(etatstockpf.getArticle().getId()==334)
			{
				System.out.println("Yes");
			}
			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteEntrer().add(etatstockpf.getQuantiteProduction())).subtract(etatstockpf.getQuantiteSortie()));
			if(etatstockpf.getQuantiteFinale().compareTo(BigDecimal.ZERO)!=0)

					{
						
				prixMoyen=((etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer())).subtract(etatstockpf.getMontantSortie())).divide(etatstockpf.getQuantiteFinale(), 6, RoundingMode.HALF_UP);
				
					}
					
			etatstockpf.setPrixFinale(prixMoyen);
			listEtatStockPF.set(i, etatstockpf);
		
	}
  	

  	
	 
	
	
		
	}else
	{
		

		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	


	
	/*
	
	String requet="";
	Articles articles=mapLibelleAricle.get(comboBox.getSelectedItem());
	if(articles!=null)
	{
		
		requet=requet+" and articles.id='"+articles.getId()+"'";
	}
	
	List<StockPF> listStockPF=stockPFDAO.findStockProduitFiniByMagasin(mapMagasin.get(comboMagasin.getSelectedItem()), requet);
	
	afficher_tableMP(listStockPF);
	*/
	
	
	
	

	
}


void CalculerStockPFFinAnnee()
{
	
	
	 Date datedebut=null;
	try {
		datedebut = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2019");
	} catch (ParseException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		 
		 
		String dateFin="31/12/"+sdf.format(dateChooser.getDate())+"";
 
		Date dateFinAnne= new Date(dateFin);
 
	
	
	
	
	Magasin magasin=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
	
	

	
	detailTransferStockPFDAO.ViderSession();
	
	
	listEtatStockPFFinAnnee.clear();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFReception =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFReceptionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	
	

	BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	BigDecimal quantiteTotalSortie=new BigDecimal(0);
	
	BigDecimal quantiteTotalproduction=new BigDecimal(0);
	
	BigDecimal montantTotalEntrer=new BigDecimal(0);
	BigDecimal  montantTotalSortie=new BigDecimal(0);
	
	BigDecimal montantTotalproduction=new BigDecimal(0);

	BigDecimal quantiteTotalFinale=new BigDecimal(0);
	
	boolean trouve=false;
	//FamilleArticlePF familleArticle=mapFamilleArticles.get(comboFamille.getSelectedItem());
	Articles article=null;
	
	if(magasin!=null)
	{
		

		
				    		
	
		
	Client client=null;

			listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut,dateFinAnne, article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
			listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut,dateFinAnne, article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin,client);
			

			listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateFinAnne, article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
			listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateFinAnne, article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin,client);
			
			listDetailTransferStockPFReception=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutReception(datedebut, dateFinAnne, article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
			listDetailTransferStockPFReceptionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutReception(datedebut,dateFinAnne, article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		
			
		
			listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut, dateFinAnne, article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
			listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut, dateFinAnne, article, Constantes.ETAT_TRANSFER_STOCK_SORTIE,magasin,client);
		
		
		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		
		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		{
			
			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
			
			if(article==null)
			{
				EtatStockPF etatstock=new EtatStockPF();
   			etatstock.setArticle(detailtransferstockpf.getArticle());
   			
   			etatstock.setQuantiteProduction(BigDecimal.ZERO);
   			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
   			etatstock.setQuantiteSortie(BigDecimal.ZERO);
   			
   			etatstock.setMontantProduction (BigDecimal.ZERO);
   			etatstock.setMontantEntrer (BigDecimal.ZERO);
   			etatstock.setMontantSortie(BigDecimal.ZERO);
   			
   			etatstock.setQuantiteFinale(BigDecimal.ZERO);
   			
   			
   			
   			listEtatStockPFFinAnnee.add(etatstock);
			}else
			{
				if(detailtransferstockpf.getArticle().getId()==article.getId())
				{
					EtatStockPF etatstock=new EtatStockPF();
	    			etatstock.setArticle(detailtransferstockpf.getArticle());
	    			
	    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
	    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
	    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
	    			etatstock.setMontantProduction(BigDecimal.ZERO);
	    			etatstock.setMontantEntrer(BigDecimal.ZERO);
	    			etatstock.setMontantSortie(BigDecimal.ZERO);
	    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
	    			listEtatStockPFFinAnnee.add(etatstock);
				}
				
				
				
			}
			
			
		}
		
		
		
		
		// calculer la quantite production
		
		
	for(int j=0;j<listDetailTransferStockPFProductionGroupebyPF.size();j++)
	{
		
		quantiteTotalproduction=new BigDecimal(0);
		montantTotalproduction=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFProduction.size();k++)
	{
		
		if(listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().getId() ==listDetailTransferStockPFProduction.get(k).getArticle().getId() )
		{
			
			quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(k).getQuantite());
			montantTotalproduction=montantTotalproduction.add(listDetailTransferStockPFProduction.get(k).getQuantite().multiply(listDetailTransferStockPFProduction.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalproduction.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPFFinAnnee.size();i++)
	    	{
				if(listEtatStockPFFinAnnee.get(i).getArticle().getId()== listDetailTransferStockPFProductionGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPFFinAnnee.get(i);
   				etatstockpf.setQuantiteProduction(quantiteTotalproduction);
   				etatstockpf.setMontantProduction(montantTotalproduction);
   				listEtatStockPFFinAnnee.set(i, etatstockpf);
   			
   				
   			}
	    	}
			
			
			
		}
		
	}
	

		// calculer la quantite Entrer
	
	
	for(int j=0;j<listDetailTransferStockPFEntrerGroupebyPF.size();j++)
	{
		
		quantiteTotalEntrer=new BigDecimal(0);
		montantTotalEntrer=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFEntrer.size();k++)
	{
		
		if(listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFEntrer.get(k).getArticle().getId() )
		{
			
			quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(k).getQuantite());
			montantTotalEntrer=montantTotalEntrer.add(listDetailTransferStockPFEntrer.get(k).getQuantite().multiply(listDetailTransferStockPFEntrer.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPFFinAnnee.size();i++)
	    	{
				if(listEtatStockPFFinAnnee.get(i).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPFFinAnnee.get(i);
   				etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
   				etatstockpf.setMontantEntrer(montantTotalEntrer);
   				listEtatStockPFFinAnnee.set(i, etatstockpf);
   			
   				
   			}
				
	    	}
			
			
			
		}
		
	}
	
	/// RECEPTION
	
	for(int j=0;j<listDetailTransferStockPFReceptionGroupebyPF.size();j++)
	{
		
		quantiteTotalEntrer=new BigDecimal(0);
		montantTotalEntrer=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFReception.size();k++)
	{
		
		if(listDetailTransferStockPFReceptionGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFReception.get(k).getArticle().getId() )
		{
			
			quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFReception.get(k).getQuantite());
			montantTotalEntrer=montantTotalEntrer.add(listDetailTransferStockPFReception.get(k).getQuantite().multiply(listDetailTransferStockPFReception.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalEntrer.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPFFinAnnee.size();i++)
	    	{
				if(listEtatStockPFFinAnnee.get(i).getArticle().getId()== listDetailTransferStockPFReceptionGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPFFinAnnee.get(i);
   				etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
   				etatstockpf.setMontantEntrer(etatstockpf.getMontantEntrer().add(montantTotalEntrer) );
   				listEtatStockPFFinAnnee.set(i, etatstockpf);
   			
   				
   			}
				
	    	}
			
			
			
		}
		
	}
	
	
	
//calculer la quantite Sortie
	
	
	for(int j=0;j<listDetailTransferStockPFSortieGroupebyPF.size();j++)
	{
		
		quantiteTotalSortie=new BigDecimal(0);
		montantTotalSortie=new BigDecimal(0);
		boolean existe=false;
			
	for(int k=0;k<listDetailTransferStockPFSortie.size();k++)
	{
		
		if(listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId()== listDetailTransferStockPFSortie.get(k).getArticle().getId() )
		{
			
			quantiteTotalSortie=quantiteTotalSortie.add(listDetailTransferStockPFSortie.get(k).getQuantite());
			montantTotalSortie=montantTotalSortie.add(listDetailTransferStockPFSortie.get(k).getQuantite().multiply(listDetailTransferStockPFSortie.get(k).getPrixUnitaire()));
			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			
		}
		
		
	}
		if( !quantiteTotalSortie.equals(BigDecimal.ZERO))
		{
			
			for(int i=0;i<listEtatStockPFFinAnnee.size();i++)
	    	{
				if(listEtatStockPFFinAnnee.get(i).getArticle().getId()== listDetailTransferStockPFSortieGroupebyPF.get(j).getArticle().getId())
   			{
   				EtatStockPF etatstockpf=listEtatStockPFFinAnnee.get(i);
   				etatstockpf.setQuantiteSortie(quantiteTotalSortie);
   				
   				listEtatStockPFFinAnnee.set(i, etatstockpf);
   			
   				
   			}
	    	}
			
			
			
		}
		
	}
	
	
	// Calculer Stock Finale
	
	
  	for(int i=0;i<listEtatStockPFFinAnnee.size();i++)
	{
  	 BigDecimal prixMoyen=BigDecimal.ZERO;
  	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
  		   
			EtatStockPF etatstockpf=listEtatStockPFFinAnnee.get(i);
			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteEntrer().add(etatstockpf.getQuantiteProduction())).subtract(etatstockpf.getQuantiteSortie()));
			if(etatstockpf.getQuantiteFinale().compareTo(BigDecimal.ZERO)!=0)

					{
						
				prixMoyen=((etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer()))).divide(etatstockpf.getQuantiteFinale(), 6, RoundingMode.HALF_UP);
				
					}
					
			etatstockpf.setPrixFinale(prixMoyen);
			listEtatStockPFFinAnnee.set(i, etatstockpf);
		
	}
  	

  	
	 
	
	
		
	}else
	{
		

		JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		return;
	}
	


	
	/*
	
	String requet="";
	Articles articles=mapLibelleAricle.get(comboBox.getSelectedItem());
	if(articles!=null)
	{
		
		requet=requet+" and articles.id='"+articles.getId()+"'";
	}
	
	List<StockPF> listStockPF=stockPFDAO.findStockProduitFiniByMagasin(mapMagasin.get(comboMagasin.getSelectedItem()), requet);
	
	afficher_tableMP(listStockPF);
	*/
	
	
	
	

	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article","Quantité En Stock","Quantité a Tranférer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}
