package presentation.stockMP;

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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main.AuthentificationView;
import main.ProdLauncher;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.ComparateurStockDechetMP;
import util.ComparateurStockMP;
import util.Constantes;
import util.JasperUtils;
import util.NumberUtils;

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FournisseurMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FournisseurMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.EtatStockDechetMP;
import dao.entity.EtatStockMP;
import dao.entity.FournisseurMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SubCategorieMp;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsulterEtatStockDechetMP extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private DefaultTableModel modeleEtatStock;
	private DefaultTableModel modeleDetailEtatStock;

	private JXTable tableEtatStock = new JXTable();

	private List<Depot> listDepot = new ArrayList<Depot>();
	private List<Depot> listparDepot = new ArrayList<Depot>();
	private List<Magasin> listMagasin = new ArrayList<Magasin>();
	private List <DetailTransferStockMP> listeObjectStockFinale=new ArrayList<DetailTransferStockMP>();
	private List <Object[]> listeObjectStockFinaleAvecFournisseurNull=new ArrayList<Object[]>();
	private List <Object[]> listeObjectStockInitial=new ArrayList<Object[]>();
	private List <Object[]> listeObjectEtatStock=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertSortieAncienne=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertSortieActuel=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertEntrerAncienne=new ArrayList<Object[]>();
	private List <Object[]> listeObjectTransfertEntrerActuel=new ArrayList<Object[]>();
	private List<EtatStockDechetMP> listEtatStockDechetMP = new ArrayList<EtatStockDechetMP>();
	private List<EtatStockDechetMP> listEtatStockDechetMPGroupByMP = new ArrayList<EtatStockDechetMP>();
	private List<EtatStockDechetMP> listEtatStockDechetMPDetailMP = new ArrayList<EtatStockDechetMP>();

	// ******************************************* Listes Pour Mouvement de Stock Mp
	// **********************************************//

	private List<DetailTransferStockMP> listDetailTransferStockMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPBytypetransfer = new ArrayList<DetailTransferStockMP>();
	private List<DetailMouvementStock> listMouvementStockMP = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficher = new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listMouvementStockMPAfficherMouvementTmp = new ArrayList<DetailMouvementStock>();

	// *******************************************************************************************************************************//

	// ************************************************

	private List<DetailTransferStockMP> listDetailTransferStockMPReception = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPReceptionGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPCharge = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortie = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPSortieGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPChargeSuppGroupebyMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPEnter = new ArrayList<DetailTransferStockMP>();
	private List<DetailTransferStockMP> listDetailTransferStockMPEntrerGroupebyMP = new ArrayList<DetailTransferStockMP>();

	private List<DetailTransferStockMP> listDetailTransferStockMPAllMP = new ArrayList<DetailTransferStockMP>();
	

	// ***************************************************

	private List<DetailMouvementStock> listMouvementStockMPAfficherTmp = new ArrayList<DetailMouvementStock>();
	private List<MatierePremier> listeMatierePremiereCombo=new ArrayList<MatierePremier>();
	private List<MatierePremier> listMP = new ArrayList<MatierePremier>();

	private Map<String, Depot> mapDepot = new HashMap<>();
	private Map<String, Depot> mapparDepot = new HashMap<>();
	private Map<String, Magasin> mapMagasin = new HashMap<>();

	private Map<String, MatierePremier> mapMP = new HashMap<>();
	private Map<String, MatierePremier> mapCodeMP = new HashMap<>();
	private Map< String, MatierePremier> MapMatierPremiere = new HashMap<>();
	private Map< String, MatierePremier> MapCodeMatierPremiere = new HashMap<>();
	private ImageIcon imgModifierr;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;


	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnRechercher;
	private Utilisateur utilisateur;
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;

	private JTextField txtlibelle = new JTextField();

	private DepotDAO depotdao;
	
	
	private JDateChooser dateChooser = new JDateChooser();

	JButton btnSupprimer = new JButton();
	private JRadioButton rdbtnDateFacture;
	private StockPFDAO stockpfDAO;

	private MatierePremiereDAO MatierPremiereDAO;
	String titre = "";
	Workbook workbook = new HSSFWorkbook();
	private JTextField txtCodeMP;
	JComboBox soucategoriempcombo = new JComboBox();
	JComboBox categoriempcombo = new JComboBox();
	JComboBox comboMP = new JComboBox();
	JComboBox combomagasin = new JComboBox();
	List<CategorieMp> listecategoriemp =new ArrayList<CategorieMp>();
	List<SubCategorieMp> listsubcategoriemp= new ArrayList<SubCategorieMp>();
	private Map< String, SubCategorieMp> subcatMap = new HashMap<>();
	private Map< String, CategorieMp> catMap = new HashMap<>();
	private CategorieMpDAO categoriempdao;
	JDateChooser dateChooserdebut = new JDateChooser();
	JDateChooser dateChooserfin = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private SubCategorieMPDAO subcategoriempdao;
	private MatierePremiereDAO matierePremiereDAO;
	JComboBox comboFournisseur = new JComboBox();
	private Map< String, FournisseurMP> mapFournisseurMP = new HashMap<>();
	private List<FournisseurMP> listFournisseurMP =new ArrayList<FournisseurMP>();
	private FournisseurMPDAO fournisseurMPDAO;
	private JTable table;
	JButton ImprimerDetailEtatStockDechetMP = new JButton("Imprimer");
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public ConsulterEtatStockDechetMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1485, 1029);

		try {

			imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgModifierr = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
			imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
			imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
			imgValider = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgChercher = new ImageIcon(this.getClass().getResource("/img/chercher.png"));
			imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
			utilisateur = AuthentificationView.utilisateur;
			depotdao =  new DepotDAOImpl();
			mouvementStockGlobaleDAO =  new MouvementStockGlobalDAOImpl();
			MatierPremiereDAO =  new MatierePremierDAOImpl();
			detailTransferStockMPDAO = new DetailTransferMPDAOImpl();
			listMP = MatierPremiereDAO.findAll();
			categoriempdao= new CategorieMpDAOImpl();
        	subcategoriempdao= new SubCategorieMPAOImpl();
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	listsubcategoriemp=subcategoriempdao.findAll();
        	 fournisseurMPDAO=new FournisseurMPDAOImpl();
        	 transferStockMPDAO=new TransferStockMPDAOImpl();
		} catch (Exception exp) {
			exp.printStackTrace();
			 System.exit(0);
		}
		tableEtatStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				
				if(tableEtatStock.getSelectedRow()!=-1)
				{
					
					listEtatStockDechetMPDetailMP.clear();
					
				EtatStockDechetMP etatStockDechetMP=listEtatStockDechetMPGroupByMP.get(tableEtatStock.getSelectedRow())	;
				
				
				
					
					
					for(int i=0;i<listEtatStockDechetMP.size();i++)
					{
						
					if(listEtatStockDechetMP.get(i).getMp().getId()==etatStockDechetMP.getMp().getId())
					{
						
						listEtatStockDechetMPDetailMP.add(listEtatStockDechetMP.get(i));
						
					}
						
						
						
						
						
						
					}
					
					
					
					if(listEtatStockDechetMPDetailMP.size()!=0)
					{
						
						afficher_tableDetailEtatStock(listEtatStockDechetMPDetailMP);
						
					}
					
					
					
					
				}
				
				
				
			}
		});

		tableEtatStock.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {  "Code","Matiere Premiere","INITIAL", "RECEPTION","DECHET USINE", "DECHET FOURNISSEUR", "PERTE","VENTE","Retour Dechet Fournisseur En Attent","Retour Dechet Fournisseur Annul�","Autres Sortie",
						  "Quantite Finale" }));
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
		tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);
		
		tableEtatStock.setShowVerticalLines(false);
		tableEtatStock.setSelectionBackground(new Color(51, 204, 255));
		tableEtatStock.setRowHeightEnabled(true);
		tableEtatStock.setBackground(new Color(255, 255, 255));
		tableEtatStock.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEtatStock.setColumnControlVisible(true);
		tableEtatStock.setForeground(Color.BLACK);
		tableEtatStock.setGridColor(new Color(0, 0, 255));
		tableEtatStock.setAutoCreateRowSorter(true);
		tableEtatStock.setBounds(2, 27, 411, 198);
		tableEtatStock.setRowHeight(20);

		JScrollPane scrollPane = new JScrollPane(tableEtatStock);
		scrollPane.setBounds(10, 250, 1465, 317);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Etat de Stock");
		titledSeparator.setBounds(10, 209, 1465, 30);
		add(titledSeparator);
		
		
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Code","Matiere Premiere","INITIAL", "RECEPTION","DECHET USINE", "DECHET FOURNISSEUR", "PERTE","Retour Dechet Fournisseur En Attent","Retour Dechet Fournisseur D�finitive ","Retour Dechet Fournisseur Annul�","VENTE","Autres Sortie",
					  "Quantite Finale"			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(91);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(102);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		table.getColumnModel().getColumn(4).setPreferredWidth(123);
		table.getColumnModel().getColumn(5).setPreferredWidth(118);
		table.getColumnModel().getColumn(6).setPreferredWidth(132);
		table.getColumnModel().getColumn(7).setPreferredWidth(92);		
		table.getColumnModel().getColumn(8).setPreferredWidth(92);
		table.getColumnModel().getColumn(9).setPreferredWidth(92);
		table.getColumnModel().getColumn(10).setPreferredWidth(92);
		table.getColumnModel().getColumn(11).setPreferredWidth(92);
		table.getColumnModel().getColumn(12).setPreferredWidth(92);
		JScrollPane scrollPane_1 = new JScrollPane(table);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(10, 621, 1465, 300);
		add(scrollPane_1);
		
		
		
		

		JButton buttonvalider = new JButton("Imprimer");
		buttonvalider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			if(magasin!=null)
			{
				 if(listEtatStockDechetMPGroupByMP.size()!=0)
				 {
				
						Map parameters = new HashMap();
						String datedu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
						String dateau=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
						parameters.put("magasin",magasin.getLibelle() );
						parameters.put("date","  Du :"+datedu +" Au : "+dateau);	
						Collections.sort(listEtatStockDechetMPGroupByMP, new ComparateurStockDechetMP());
						JasperUtils.imprimerEtatStockDechetMP(listEtatStockDechetMPGroupByMP,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
			}
				
			
			
			}
		});

		buttonvalider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonvalider.setBounds(704, 578, 112, 32);
		buttonvalider.setIcon(imgImprimer);
		add(buttonvalider);

		JLabel lblConslterLesFactures = new JLabel("           Consulter Etat de Stock Dechet MP :");
		lblConslterLesFactures.setBackground(Color.WHITE);
		lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
		lblConslterLesFactures.setBounds(332, 11, 897, 35);
		add(lblConslterLesFactures);
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		JButton btnAfficher = new JButton("Consulter");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				listEtatStockDechetMP.clear();
				listEtatStockDechetMPGroupByMP.clear();
				detailTransferStockMPDAO.ViderSession();
				transferStockMPDAO.ViderSession();
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				SubCategorieMp subCategorieMp=subcatMap.get(soucategoriempcombo.getSelectedItem());
				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem());
				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
				FournisseurMP fournisseurMP=mapFournisseurMP.get(comboFournisseur.getSelectedItem());
				boolean existe=false;
				if(dateChooserdebut.getDate()==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez entrer la date debut SVP");
					return;
				}
				
				if((dateChooserfin.getDate()==null))
				{
					dateChooserfin.setDate(dateChooserdebut.getDate());
				}
				
				if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez selectinner le Magasin  SVP");
					return;
				}
				
				
				/*if(fournisseurMP!=null)
				{*/
					
					listeObjectStockFinale=detailTransferStockMPDAO.SituationStockFinaleMPMagasinDechet(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin, matierePremier, fournisseurMP) ;
					
					for(int i=0;i<listeObjectStockFinale.size();i++)
					{
						DetailTransferStockMP detailTransferStockMP=listeObjectStockFinale.get(i);
						
						BigDecimal initial=BigDecimal.ZERO;
						BigDecimal reception=BigDecimal.ZERO;
						BigDecimal dechetusine=BigDecimal.ZERO;	
						BigDecimal dechetfournisseur=BigDecimal.ZERO;
						BigDecimal perte=BigDecimal.ZERO;
						BigDecimal vente=BigDecimal.ZERO;
						BigDecimal dechetfournisseurEnAttent=BigDecimal.ZERO;
						BigDecimal dechetfournisseurAnnule=BigDecimal.ZERO;
						BigDecimal dechetfournisseurDefinitive=BigDecimal.ZERO;
						BigDecimal autresSorties=BigDecimal.ZERO;
						
						
						
						boolean trouve=false;
						for(int d=0;d<listEtatStockDechetMP.size();d++)
						{
							
							EtatStockDechetMP etatStockMP=listEtatStockDechetMP.get(d);
							
							if(etatStockMP.getMp().getId()==detailTransferStockMP.getMatierePremier().getId())
							{
								
								if(detailTransferStockMP.getFournisseur()!=null)
									
								{
									if(etatStockMP.getFournisseurMP()!=null)
									{
										if(detailTransferStockMP.getFournisseur().getId()==etatStockMP.getFournisseurMP().getId())
										{
											
											trouve=true;
											if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
											{
												
												initial=detailTransferStockMP.getQuantite();
												if(initial==null)
												{
													initial=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
											{
												reception=detailTransferStockMP.getQuantite();
												if(reception==null)
												{
													reception=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET))
											{
												dechetusine=detailTransferStockMP.getQuantiteDechet();
												if(dechetusine==null)
												{
													dechetusine=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET_FOURNISSEUR))
											{
												dechetfournisseur=detailTransferStockMP.getQuantiteDechet();
												if(dechetfournisseur==null)
												{
													dechetfournisseur=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_PERTE))
											{
												perte=detailTransferStockMP.getQuantite();
												if(perte==null)
												{
													perte=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_VENTE))
											{
												vente=detailTransferStockMP.getQuantite();
												if(vente==null)
												{
													vente=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT) &&  detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
											{
												dechetfournisseurEnAttent=detailTransferStockMP.getQuantite();
												if(dechetfournisseurEnAttent==null)
												{
													dechetfournisseurEnAttent=BigDecimal.ZERO;
												}
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
											{
												
												if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().trim().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_DEFINITIF.trim()))
												{
													
													dechetfournisseurDefinitive=detailTransferStockMP.getQuantite();
											
													if(dechetfournisseurDefinitive==null)
													{
														dechetfournisseurDefinitive=BigDecimal.ZERO;
													}
													
												}else
												{
													autresSorties=detailTransferStockMP.getQuantite();
													if(autresSorties==null)
													{
														autresSorties=BigDecimal.ZERO;
													}
													
												}
												
											
												
												
												
												
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
											{
												
												
												
												dechetfournisseurAnnule=detailTransferStockMP.getQuantite();
											
												if(dechetfournisseurAnnule==null)
												{
													dechetfournisseurAnnule=BigDecimal.ZERO;
												}
												
											
												
												
												
											}
											
											
											

											etatStockMP.setQuantiteInitial(etatStockMP.getQuantiteInitial().add(initial) );		
											etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add(reception)  );			
											etatStockMP.setQuantiteDechet(etatStockMP.getQuantiteDechet().add(dechetusine)  );				
											etatStockMP.setQuantiteDechetFournisseur(etatStockMP.getQuantiteDechetFournisseur().add(dechetfournisseur)  );				
											etatStockMP.setQuantitePerte(etatStockMP.getQuantitePerte().add(perte)  );	
											etatStockMP.setQuantiteVente(etatStockMP.getQuantiteVente().add(vente)  );
											etatStockMP.setQuantiteDechetFournisseurEnAttent(etatStockMP.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent)  );
											etatStockMP.setQuantiteDechetFournisseurAnnule(etatStockMP.getQuantiteDechetFournisseurAnnule().add(dechetfournisseurAnnule)  );
											etatStockMP.setQuantiteDechetFournisseurDefinitive (etatStockMP.getQuantiteDechetFournisseurDefinitive().add(dechetfournisseurDefinitive)  );
											etatStockMP.setQuantiteautresSortie(etatStockMP.getQuantiteautresSortie().add(autresSorties)  );
											//etatStockMP.setQuantiteDechetFournisseurEnAttent(etatStockMP.getQuantiteDechetFournisseurEnAttent().add(etatStockMP.getQuantiteDechetFournisseurDefinitive()));
											etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte()).add(etatStockMP.getQuantiteDechetFournisseurAnnule())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
											listEtatStockDechetMP.set(d, etatStockMP) 	;	
											
											
											
											
											
											
											
											
											
											
										}
										
										
										
										
									}
									
									
								}else
								{
									
									if(etatStockMP.getFournisseurMP()==null)
									{
										
										trouve=true;
										
										if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
										{
											
											initial=detailTransferStockMP.getQuantite();
											if(initial==null)
											{
												initial=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
										{
											reception=detailTransferStockMP.getQuantite();
											if(reception==null)
											{
												reception=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET))
										{
											dechetusine=detailTransferStockMP.getQuantiteDechet();
											if(dechetusine==null)
											{
												dechetusine=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET_FOURNISSEUR))
										{
											dechetfournisseur=detailTransferStockMP.getQuantiteDechet();
											if(dechetfournisseur==null)
											{
												dechetfournisseur=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_PERTE))
										{
											perte=detailTransferStockMP.getQuantite();
											if(perte==null)
											{
												perte=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_VENTE))
										{
											vente=detailTransferStockMP.getQuantite();
											if(vente==null)
											{
												vente=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT) &&  detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
										{
											dechetfournisseurEnAttent=detailTransferStockMP.getQuantite();
											if(dechetfournisseurEnAttent==null)
											{
												dechetfournisseurEnAttent=BigDecimal.ZERO;
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
										{
											if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().trim().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_DEFINITIF.trim()))
											{
												
												dechetfournisseurDefinitive=detailTransferStockMP.getQuantite();
												
												
												if(dechetfournisseurDefinitive==null)
												{
													dechetfournisseurDefinitive=BigDecimal.ZERO;
												}
												
											
												
											}else
											{
												autresSorties=detailTransferStockMP.getQuantite();
												if(autresSorties==null)
												{
													autresSorties=BigDecimal.ZERO;
												}
												
											}
											
										}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
										{
											
											
											

											
											
											
											dechetfournisseurAnnule=detailTransferStockMP.getQuantite();
											
											if(dechetfournisseurAnnule==null)
											{
												dechetfournisseurAnnule=BigDecimal.ZERO;
											}
											
											
											
											
											
										
											
											
											
											
										}
										
										etatStockMP.setQuantiteInitial(etatStockMP.getQuantiteInitial().add(initial) );		
										etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add(reception)  );			
										etatStockMP.setQuantiteDechet(etatStockMP.getQuantiteDechet().add(dechetusine)  );				
										etatStockMP.setQuantiteDechetFournisseur(etatStockMP.getQuantiteDechetFournisseur().add(dechetfournisseur)  );				
										etatStockMP.setQuantitePerte(etatStockMP.getQuantitePerte().add(perte)  );	
										etatStockMP.setQuantiteVente(etatStockMP.getQuantiteVente().add(vente)  );
										etatStockMP.setQuantiteDechetFournisseurEnAttent(etatStockMP.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent)  );
										etatStockMP.setQuantiteDechetFournisseurAnnule(etatStockMP.getQuantiteDechetFournisseurAnnule().add(dechetfournisseurAnnule)  );
										etatStockMP.setQuantiteDechetFournisseurDefinitive (etatStockMP.getQuantiteDechetFournisseurDefinitive().add(dechetfournisseurDefinitive)  );
										etatStockMP.setQuantiteautresSortie(etatStockMP.getQuantiteautresSortie().add(autresSorties)  );
										//etatStockMP.setQuantiteDechetFournisseurEnAttent(etatStockMP.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurDefinitive));
										etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte()).add(etatStockMP.getQuantiteDechetFournisseurAnnule())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
										listEtatStockDechetMP.set(d, etatStockMP) 	;	
										
										
										
										
										
										
										
									}
									
									
									
								}
								
							}
							
						
							
							
						}
						
						
						
						if(trouve==false)
						{
							
							EtatStockDechetMP etatStockMP=new EtatStockDechetMP();
							
							etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
							
							if(detailTransferStockMP.getFournisseur()!=null)
							{
								etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
							}
							
							if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
							{
								
								initial=detailTransferStockMP.getQuantite();
								if(initial==null)
								{
									initial=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
							{
								reception=detailTransferStockMP.getQuantite();
								if(reception==null)
								{
									reception=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET))
							{
								dechetusine=detailTransferStockMP.getQuantiteDechet();
								if(dechetusine==null)
								{
									dechetusine=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET_FOURNISSEUR))
							{
								dechetfournisseur=detailTransferStockMP.getQuantiteDechet();
								if(dechetfournisseur==null)
								{
									dechetfournisseur=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_PERTE))
							{
								perte=detailTransferStockMP.getQuantite();
								if(perte==null)
								{
									perte=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_VENTE))
							{
								vente=detailTransferStockMP.getQuantite();
								if(vente==null)
								{
									vente=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_SORTIE_ENATTENT) &&  detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
							{
								dechetfournisseurEnAttent=detailTransferStockMP.getQuantite();
								if(dechetfournisseurEnAttent==null)
								{
									dechetfournisseurEnAttent=BigDecimal.ZERO;
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
							{
								if(detailTransferStockMP.getTransferStockMP().getSoustype().getSousType().equals(Constantes.SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_DEFINITIF))
								{
									
									dechetfournisseurDefinitive=detailTransferStockMP.getQuantite();
									if(dechetfournisseurDefinitive==null)
									{
										dechetfournisseurDefinitive=BigDecimal.ZERO;
									}
									
							
									
								}else
								{
									autresSorties=detailTransferStockMP.getQuantite();
									if(autresSorties==null)
									{
										autresSorties=BigDecimal.ZERO;
									}
									
								}
								
							}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(SOUS_TYPE_SORTIE_RETOUR_FOURNISSEUR_ANNULER) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
							{
								
								
								

								
								
								
								dechetfournisseurAnnule=detailTransferStockMP.getQuantite();
							
								if(dechetfournisseurAnnule==null)
								{
									dechetfournisseurAnnule=BigDecimal.ZERO;
								}
								
							
								
								
								
							
								
								
								
								
							}
							
							etatStockMP.setQuantiteInitial(initial);		
							etatStockMP.setQuantiteReception(reception);			
							etatStockMP.setQuantiteDechet(dechetusine);				
							etatStockMP.setQuantiteDechetFournisseur(dechetfournisseur);				
							etatStockMP.setQuantitePerte(perte);	
							etatStockMP.setQuantiteVente(vente);
							etatStockMP.setQuantiteDechetFournisseurEnAttent(dechetfournisseurEnAttent);
							etatStockMP.setQuantiteDechetFournisseurAnnule(dechetfournisseurAnnule);
 							etatStockMP.setQuantiteDechetFournisseurDefinitive (dechetfournisseurDefinitive);
							etatStockMP.setQuantiteautresSortie(autresSorties);
							//etatStockMP.setQuantiteDechetFournisseurEnAttent(dechetfournisseurEnAttent.add(dechetfournisseurDefinitive));
							etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte()).add(etatStockMP.getQuantiteDechetFournisseurAnnule())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
							listEtatStockDechetMP.add(etatStockMP)	;	
							
							
							
							
							
							
						}
						
						
						
							
							
					

		
		existe=false;
		
		for(int j=0;j<listEtatStockDechetMPGroupByMP.size();j++)
		{
			
			EtatStockDechetMP etatStockMPTmp=listEtatStockDechetMPGroupByMP.get(j);
			
			if(etatStockMPTmp.getMp().getId()==detailTransferStockMP.getMatierePremier().getId())
			{
				existe=true;
				
				etatStockMPTmp.setQuantiteInitial(etatStockMPTmp.getQuantiteInitial().add(initial) );		
				etatStockMPTmp.setQuantiteReception(etatStockMPTmp.getQuantiteReception().add(reception)  );			
				etatStockMPTmp.setQuantiteDechet(etatStockMPTmp.getQuantiteDechet().add(dechetusine)   );				
				etatStockMPTmp.setQuantiteDechetFournisseur(etatStockMPTmp.getQuantiteDechetFournisseur().add(dechetfournisseur));				
				etatStockMPTmp.setQuantitePerte(etatStockMPTmp.getQuantitePerte().add(perte));	
				etatStockMPTmp.setQuantiteVente(etatStockMPTmp.getQuantiteVente().add(vente));	
				etatStockMPTmp.setQuantiteDechetFournisseurEnAttent(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent));
				etatStockMPTmp.setQuantiteDechetFournisseurAnnule(etatStockMPTmp.getQuantiteDechetFournisseurAnnule().add(dechetfournisseurAnnule));
				etatStockMPTmp.setQuantiteDechetFournisseurDefinitive(etatStockMPTmp.getQuantiteDechetFournisseurDefinitive().add(dechetfournisseurDefinitive));
				etatStockMPTmp.setQuantiteautresSortie(etatStockMPTmp.getQuantiteautresSortie().add(autresSorties));
				//etatStockMPTmp.setQuantiteDechetFournisseurEnAttent(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurDefinitive));

				etatStockMPTmp.setQuantiteFinale(etatStockMPTmp.getQuantiteInitial().add(etatStockMPTmp.getQuantiteReception().add(etatStockMPTmp.getQuantiteDechet().add(etatStockMPTmp.getQuantiteDechetFournisseur().add(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent()).add(etatStockMPTmp.getQuantitePerte()).add(etatStockMPTmp.getQuantiteDechetFournisseurAnnule())))).subtract(etatStockMPTmp.getQuantiteVente().add(etatStockMPTmp.getQuantiteautresSortie())));
				listEtatStockDechetMPGroupByMP.set(j, etatStockMPTmp) ;	
				
				
				
				
				
			}
			
			
			
			
		}
		
		if(existe==false)
		{
			EtatStockDechetMP etatStockMPTmp=new EtatStockDechetMP();
			
			etatStockMPTmp.setMp(detailTransferStockMP.getMatierePremier());
			
			etatStockMPTmp.setQuantiteInitial(initial);		
			etatStockMPTmp.setQuantiteReception(reception);			
			etatStockMPTmp.setQuantiteDechet(dechetusine);				
			etatStockMPTmp.setQuantiteDechetFournisseur(dechetfournisseur);				
			etatStockMPTmp.setQuantitePerte(perte);	
			etatStockMPTmp.setQuantiteVente(vente);	
			etatStockMPTmp.setQuantiteDechetFournisseurEnAttent(dechetfournisseurEnAttent);
			etatStockMPTmp.setQuantiteDechetFournisseurAnnule(dechetfournisseurAnnule);
			etatStockMPTmp.setQuantiteDechetFournisseurDefinitive (dechetfournisseurDefinitive);
			etatStockMPTmp.setQuantiteautresSortie(autresSorties);
			//etatStockMPTmp.setQuantiteDechetFournisseurEnAttent(dechetfournisseurEnAttent.add(dechetfournisseurDefinitive));
			etatStockMPTmp.setQuantiteFinale(etatStockMPTmp.getQuantiteInitial().add(etatStockMPTmp.getQuantiteReception().add(etatStockMPTmp.getQuantiteDechet().add(etatStockMPTmp.getQuantiteDechetFournisseur().add(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent()).add(etatStockMPTmp.getQuantitePerte()).add(etatStockMPTmp.getQuantiteDechetFournisseurAnnule())))).subtract(etatStockMPTmp.getQuantiteVente().add(etatStockMPTmp.getQuantiteautresSortie())));
			listEtatStockDechetMPGroupByMP.add(etatStockMPTmp)	;	
			
			
		}
		



	
					
					}
					/*	
				}else
				{
					
					listeObjectStockFinale=detailTransferStockMPDAO.SituationStockFinaleMPMagasinDechet(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin, matierePremier, fournisseurMP) ;
					
					JOptionPane.showMessageDialog(null, listeObjectStockFinale.size());
					
					for(int i=0;i<listeObjectStockFinale.size();i++)
					{
						 DetailTransferStockMP detailTransferStockMP=listeObjectStockFinale.get(i);
						
							DetailTransferStockMP detailTransferStockMP=listeObjectStockFinale.get(i);
							
							BigDecimal initial=BigDecimal.ZERO;
							BigDecimal reception=BigDecimal.ZERO;
							BigDecimal dechetusine=BigDecimal.ZERO;	
							BigDecimal dechetfournisseur=BigDecimal.ZERO;
							BigDecimal perte=BigDecimal.ZERO;
							BigDecimal vente=BigDecimal.ZERO;
							BigDecimal dechetfournisseurEnAttent=BigDecimal.ZERO;
							BigDecimal autresSorties=BigDecimal.ZERO;
							
							
							
							boolean trouve=false;
							for(int d=0;d<listEtatStockDechetMP.size();d++)
							{
								
								EtatStockDechetMP etatStockMP=listEtatStockDechetMP.get(d);
								
								if(etatStockMP.getMp().getId()==detailTransferStockMP.getMatierePremier().getId())
								{
									
									if(detailTransferStockMP.getFournisseur()!=null)
										
									{
										if(etatStockMP.getFournisseurMP()!=null)
										{
											if(detailTransferStockMP.getFournisseur().getId()==etatStockMP.getFournisseurMP().getId())
											{
												
												trouve=true;
												if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
												{
													
													initial=detailTransferStockMP.getQuantite();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
												{
													reception=detailTransferStockMP.getQuantite();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET))
												{
													dechetusine=detailTransferStockMP.getQuantiteDechet();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET_FOURNISSEUR))
												{
													dechetfournisseur=detailTransferStockMP.getQuantiteDechet();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_PERTE))
												{
													perte=detailTransferStockMP.getQuantite();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_VENTE))
												{
													vente=detailTransferStockMP.getQuantite();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_TRANSFERE))
												{
													dechetfournisseurEnAttent=detailTransferStockMP.getQuantite();
													
												}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
												{
													autresSorties=detailTransferStockMP.getQuantite();
													
												}		
												
												
												

												etatStockMP.setQuantiteInitial(etatStockMP.getQuantiteInitial().add(initial) );		
												etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add(reception)  );			
												etatStockMP.setQuantiteDechet(etatStockMP.getQuantiteDechet().add(dechetusine)  );				
												etatStockMP.setQuantiteDechetFournisseur(etatStockMP.getQuantiteDechetFournisseur().add(dechetfournisseur)  );				
												etatStockMP.setQuantitePerte(etatStockMP.getQuantitePerte().add(perte)  );	
												etatStockMP.setQuantiteVente(etatStockMP.getQuantiteVente().add(vente)  );
												etatStockMP.setQuantiteDechetFournisseurEnAttent(etatStockMP.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent)  );
												etatStockMP.setQuantiteautresSortie(etatStockMP.getQuantiteautresSortie().add(autresSorties)  );
												etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
												listEtatStockDechetMP.set(d, etatStockMP) 	;	
												
												
												
												
												
												
												
												
												
												
											}
											
											
											
											
										}
										
										
									}else
									{
										
										if(etatStockMP.getFournisseurMP()==null)
										{
											
											trouve=true;
											
											if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
											{
												
												initial=detailTransferStockMP.getQuantite();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
											{
												reception=detailTransferStockMP.getQuantite();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET))
											{
												dechetusine=detailTransferStockMP.getQuantiteDechet();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET_FOURNISSEUR))
											{
												dechetfournisseur=detailTransferStockMP.getQuantiteDechet();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_PERTE))
											{
												perte=detailTransferStockMP.getQuantite();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_VENTE))
											{
												vente=detailTransferStockMP.getQuantite();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_TRANSFERE))
											{
												dechetfournisseurEnAttent=detailTransferStockMP.getQuantite();
												
											}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
											{
												autresSorties=detailTransferStockMP.getQuantite();
												
											}
											
											
											etatStockMP.setQuantiteInitial(etatStockMP.getQuantiteInitial().add(initial) );		
											etatStockMP.setQuantiteReception(etatStockMP.getQuantiteReception().add(reception)  );			
											etatStockMP.setQuantiteDechet(etatStockMP.getQuantiteDechet().add(dechetusine)  );				
											etatStockMP.setQuantiteDechetFournisseur(etatStockMP.getQuantiteDechetFournisseur().add(dechetfournisseur)  );				
											etatStockMP.setQuantitePerte(etatStockMP.getQuantitePerte().add(perte)  );	
											etatStockMP.setQuantiteVente(etatStockMP.getQuantiteVente().add(vente)  );
											etatStockMP.setQuantiteDechetFournisseurEnAttent(etatStockMP.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent)  );
											etatStockMP.setQuantiteautresSortie(etatStockMP.getQuantiteautresSortie().add(autresSorties)  );
											etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
											listEtatStockDechetMP.set(d, etatStockMP) 	;	
											
											
											
											
											
											
											
										}
										
										
										
									}
									
								}
								
							
								
								
							}
							
							
							
							if(trouve==false)
							{
								
								EtatStockDechetMP etatStockMP=new EtatStockDechetMP();
								
								etatStockMP.setMp(detailTransferStockMP.getMatierePremier());
								
								if(detailTransferStockMP.getFournisseur()!=null)
								{
									etatStockMP.setFournisseurMP(detailTransferStockMP.getFournisseur());
								}
								
								if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_INITIAL))
								{
									
									initial=detailTransferStockMP.getQuantite();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_AJOUT))
								{
									reception=detailTransferStockMP.getQuantite();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET))
								{
									dechetusine=detailTransferStockMP.getQuantiteDechet();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_DECHET_FOURNISSEUR))
								{
									dechetfournisseur=detailTransferStockMP.getQuantiteDechet();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_PERTE))
								{
									perte=detailTransferStockMP.getQuantite();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.TYPE_VENTE))
								{
									vente=detailTransferStockMP.getQuantite();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_TRANSFERE))
								{
									dechetfournisseurEnAttent=detailTransferStockMP.getQuantite();
									
								}else if(detailTransferStockMP.getTransferStockMP().getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE) && detailTransferStockMP.getTransferStockMP().getSoustype()!=null)
								{
									autresSorties=detailTransferStockMP.getQuantite();
									
								}
								
								etatStockMP.setQuantiteInitial(initial);		
								etatStockMP.setQuantiteReception(reception);			
								etatStockMP.setQuantiteDechet(dechetusine);				
								etatStockMP.setQuantiteDechetFournisseur(dechetfournisseur);				
								etatStockMP.setQuantitePerte(perte);	
								etatStockMP.setQuantiteVente(vente);
								etatStockMP.setQuantiteDechetFournisseurEnAttent(dechetfournisseurEnAttent);
								etatStockMP.setQuantiteautresSortie(autresSorties);
								etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
								listEtatStockDechetMP.add(etatStockMP)	;	
								
								
								
								
								
								
							}
							
		
existe=false;
		
		for(int j=0;j<listEtatStockDechetMPGroupByMP.size();j++)
		{
			
			EtatStockDechetMP etatStockMPTmp=listEtatStockDechetMPGroupByMP.get(j);
			
			if(etatStockMPTmp.getMp().getId()==detailTransferStockMP.getMatierePremier().getId())
			{
				existe=true;
				
				etatStockMPTmp.setQuantiteInitial(etatStockMPTmp.getQuantiteInitial().add(initial) );		
				etatStockMPTmp.setQuantiteReception(etatStockMPTmp.getQuantiteReception().add(reception)  );			
				etatStockMPTmp.setQuantiteDechet(etatStockMPTmp.getQuantiteDechet().add(dechetusine)   );				
				etatStockMPTmp.setQuantiteDechetFournisseur(etatStockMPTmp.getQuantiteDechetFournisseur().add(dechetfournisseur));				
				etatStockMPTmp.setQuantitePerte(etatStockMPTmp.getQuantitePerte().add(perte));	
				etatStockMPTmp.setQuantiteVente(etatStockMPTmp.getQuantiteVente().add(vente));	
				etatStockMPTmp.setQuantiteDechetFournisseurEnAttent(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent));
				etatStockMPTmp.setQuantiteautresSortie(etatStockMPTmp.getQuantiteautresSortie().add(autresSorties));
				etatStockMPTmp.setQuantiteFinale(etatStockMPTmp.getQuantiteInitial().add(etatStockMPTmp.getQuantiteReception().add(etatStockMPTmp.getQuantiteDechet().add(etatStockMPTmp.getQuantiteDechetFournisseur().add(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent()).add(etatStockMPTmp.getQuantitePerte())))).subtract(etatStockMPTmp.getQuantiteVente().add(etatStockMPTmp.getQuantiteautresSortie())));
				listEtatStockDechetMPGroupByMP.set(j, etatStockMPTmp) ;	
				
				
				
				
				
			}
			
			
			
			
		}
		
		if(existe==false)
		{
			EtatStockDechetMP etatStockMPTmp=new EtatStockDechetMP();
			
			etatStockMPTmp.setMp(detailTransferStockMP.getMatierePremier());
			
			etatStockMPTmp.setQuantiteInitial(initial);		
			etatStockMPTmp.setQuantiteReception(reception);			
			etatStockMPTmp.setQuantiteDechet(dechetusine);				
			etatStockMPTmp.setQuantiteDechetFournisseur(dechetfournisseur);				
			etatStockMPTmp.setQuantitePerte(perte);	
			etatStockMPTmp.setQuantiteVente(vente);	
			etatStockMPTmp.setQuantiteDechetFournisseurEnAttent(dechetfournisseurEnAttent);
			etatStockMPTmp.setQuantiteautresSortie(autresSorties);
			etatStockMPTmp.setQuantiteFinale(etatStockMPTmp.getQuantiteInitial().add(etatStockMPTmp.getQuantiteReception().add(etatStockMPTmp.getQuantiteDechet().add(etatStockMPTmp.getQuantiteDechetFournisseur().add(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent()).add(etatStockMPTmp.getQuantitePerte())))).subtract(etatStockMPTmp.getQuantiteVente().add(etatStockMPTmp.getQuantiteautresSortie())));
			listEtatStockDechetMPGroupByMP.add(etatStockMPTmp)	;	
			
			
		}
		
		
		
		


	
					}
					
	////////////////////////////////////// Les MP de Fournisseur Null ///////////////////////////////////////////////////////////////////////////				
					
					listeObjectStockFinaleAvecFournisseurNull	=detailTransferStockMPDAO.SituationStockFinaleMPMagasinDechetSansFournisseur(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin, matierePremier, fournisseurMP) ;
					
					for(int i=0;i<listeObjectStockFinaleAvecFournisseurNull.size();i++)
					{
						 Object[] object=listeObjectStockFinaleAvecFournisseurNull.get(i);
						
							EtatStockDechetMP etatStockMP=new EtatStockDechetMP();
							MatierePremier mp= (MatierePremier)object[0];
							etatStockMP.setMp(mp);
						BigDecimal initial=BigDecimal.ZERO;
						BigDecimal reception=BigDecimal.ZERO;
						BigDecimal dechetusine=BigDecimal.ZERO;	
						BigDecimal dechetfournisseur=BigDecimal.ZERO;
						BigDecimal perte=BigDecimal.ZERO;
						BigDecimal vente=BigDecimal.ZERO;
						BigDecimal dechetfournisseurEnAttent=BigDecimal.ZERO;
						BigDecimal autreSorties=BigDecimal.ZERO;
	
		
		if(object[1]!=null)
		{
			initial=(BigDecimal)object[1];
		}
		
		if(object[2]!=null)
		{
			reception=(BigDecimal)object[2];
		}
		
		if(object[3]!=null)
		{
			dechetusine=(BigDecimal)object[3];
		}
		if(object[4]!=null)
		{
			dechetfournisseur=(BigDecimal)object[4];
		}
		
		if(object[5]!=null)
		{
			perte=(BigDecimal)object[5];
		}
		
		if(object[6]!=null)
		{
			vente=(BigDecimal)object[6];
		}
		
		if(object[7]!=null)
		{
			dechetfournisseurEnAttent=(BigDecimal)object[7];
		}
		
		if(object[8]!=null)
		{
			autreSorties=(BigDecimal)object[8];
		}
		
existe=false;
		
		for(int j=0;j<listEtatStockDechetMPGroupByMP.size();j++)
		{
			
			EtatStockDechetMP etatStockMPTmp=listEtatStockDechetMPGroupByMP.get(j);
			
			if(etatStockMPTmp.getMp().getId()==mp.getId())
			{
				existe=true;
				
				etatStockMPTmp.setQuantiteInitial(etatStockMPTmp.getQuantiteInitial().add(initial) );		
				etatStockMPTmp.setQuantiteReception(etatStockMPTmp.getQuantiteReception().add(reception)  );			
				etatStockMPTmp.setQuantiteDechet(etatStockMPTmp.getQuantiteDechet().add(dechetusine)   );				
				etatStockMPTmp.setQuantiteDechetFournisseur(etatStockMPTmp.getQuantiteDechetFournisseur().add(dechetfournisseur));				
				etatStockMPTmp.setQuantitePerte(etatStockMPTmp.getQuantitePerte().add(perte));	
				etatStockMPTmp.setQuantiteVente(etatStockMPTmp.getQuantiteVente().add(vente));	
				etatStockMPTmp.setQuantiteDechetFournisseurEnAttent (etatStockMPTmp.getQuantiteDechetFournisseurEnAttent().add(dechetfournisseurEnAttent));	
				etatStockMPTmp.setQuantiteautresSortie(etatStockMPTmp.getQuantiteautresSortie().add(autreSorties));
				etatStockMPTmp.setQuantiteFinale(etatStockMPTmp.getQuantiteInitial().add(etatStockMPTmp.getQuantiteReception().add(etatStockMPTmp.getQuantiteDechet().add(etatStockMPTmp.getQuantiteDechetFournisseur().add(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent()).add(etatStockMPTmp.getQuantitePerte())))).subtract(etatStockMPTmp.getQuantiteVente().add(etatStockMPTmp.getQuantiteautresSortie())));
				listEtatStockDechetMPGroupByMP.set(j, etatStockMPTmp) ;	
				
				
				
				
				
			}
			
			
			
			
		}
		
		if(existe==false)
		{
			EtatStockDechetMP etatStockMPTmp=new EtatStockDechetMP();
			
			etatStockMPTmp.setMp(mp);
			
			etatStockMPTmp.setQuantiteInitial(initial);		
			etatStockMPTmp.setQuantiteReception(reception);			
			etatStockMPTmp.setQuantiteDechet(dechetusine);				
			etatStockMPTmp.setQuantiteDechetFournisseur(dechetfournisseur);				
			etatStockMPTmp.setQuantitePerte(perte);	
			etatStockMPTmp.setQuantiteVente(vente);	
			etatStockMPTmp.setQuantiteDechetFournisseurEnAttent (dechetfournisseurEnAttent);
			etatStockMPTmp.setQuantiteautresSortie(autreSorties);
			etatStockMPTmp.setQuantiteFinale(etatStockMPTmp.getQuantiteInitial().add(etatStockMPTmp.getQuantiteReception().add(etatStockMPTmp.getQuantiteDechet().add(etatStockMPTmp.getQuantiteDechetFournisseur().add(etatStockMPTmp.getQuantiteDechetFournisseurEnAttent()).add(etatStockMPTmp.getQuantitePerte())))).subtract(etatStockMPTmp.getQuantiteVente().add(etatStockMPTmp.getQuantiteautresSortie())));
			listEtatStockDechetMPGroupByMP.add(etatStockMPTmp)	;	
			
			
		}


	etatStockMP.setQuantiteInitial(initial);		
	etatStockMP.setQuantiteReception(reception);			
	etatStockMP.setQuantiteDechet(dechetusine);				
	etatStockMP.setQuantiteDechetFournisseur(dechetfournisseur);				
	etatStockMP.setQuantitePerte(perte);	
	etatStockMP.setQuantiteVente(vente);
	etatStockMP.setQuantiteDechetFournisseurEnAttent (dechetfournisseurEnAttent);	
	etatStockMP.setQuantiteautresSortie(autreSorties);
	etatStockMP.setQuantiteFinale(etatStockMP.getQuantiteInitial().add(etatStockMP.getQuantiteReception().add(etatStockMP.getQuantiteDechet().add(etatStockMP.getQuantiteDechetFournisseur().add(etatStockMP.getQuantiteDechetFournisseurEnAttent()).add(etatStockMP.getQuantitePerte())))).subtract(etatStockMP.getQuantiteVente().add(etatStockMP.getQuantiteautresSortie())));
	listEtatStockDechetMP.add(etatStockMP)	;		
					
					}		
					
					
					
						
				}
				*/
				
		
				
				
				
				
					Collections.sort(listEtatStockDechetMPGroupByMP, new ComparateurStockDechetMP());
				
			
			afficher_tableEtatStock(listEtatStockDechetMPGroupByMP);
			
			
			
			}
		});
		btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAfficher.setBounds(590, 174, 107, 24);
		btnAfficher.setIcon(imgChercher);
		add(btnAfficher);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		layeredPane.setBounds(10, 57, 1349, 106);
		add(layeredPane);
		
		JLabel lblDu = new JLabel("Du  :");
		lblDu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblDu.setBounds(10, 11, 67, 24);
		layeredPane.add(lblDu);
		
		JLabel label_1 = new JLabel("Code MP:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(335, 55, 67, 24);
		layeredPane.add(label_1);
		
		txtCodeMP = new JTextField();
		txtCodeMP.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				

	 		  	
  		  		

				
				if(e.getKeyCode()==e.VK_ENTER)
	      		{
					MatierePremier mp=MapCodeMatierPremiere.get(txtCodeMP.getText().toUpperCase());
					if(mp!=null)
					{
						comboMP.setSelectedItem(mp.getNom());
					}else
					{
						JOptionPane.showMessageDialog(null, "Code MP Introuvable !!!!!");
						return;
					}
					
	      		}
			
							}
		});
		txtCodeMP.setText("");
		txtCodeMP.setColumns(10);
		txtCodeMP.setBounds(395, 55, 118, 26);
		layeredPane.add(txtCodeMP);
		
		 dateChooserdebut = new JDateChooser();
		dateChooserdebut.setLocale(Locale.FRANCE);
		dateChooserdebut.setDateFormatString("dd/MM/yyyy");
		dateChooserdebut.setBounds(48, 9, 163, 26);
		layeredPane.add(dateChooserdebut);
		
		JLabel label_2 = new JLabel("MP :");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(523, 57, 37, 24);
		layeredPane.add(label_2);
		
		 comboMP = new JComboBox();
		 comboMP.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {

	  		 	
  		 		if(comboMP.getSelectedIndex()!=-1)
  		 		{
  		 			
  		 			if(!comboMP.getSelectedItem().equals(""))
  		 			{
  		 				
  		 				MatierePremier matierePremier=MapMatierPremiere.get(comboMP.getSelectedItem());
  		 				txtCodeMP.setText(matierePremier.getCode());
  		 				
  		 					
  		 				
  		 			}else
  		 			{
  		 				txtCodeMP.setText(Constantes.CODE_MP);
  		 			}
  		 			
  		 				
  		 			
  		 		}else
  		 		{
  		 			txtCodeMP.setText(Constantes.CODE_MP);
  		 		}
  		 		
  		 		
  		 		
  		 		
  		 		
  		 	
		 		
		 		
		 		
		 	}
		 });
		 comboMP.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 	}
		 });
		comboMP.setSelectedIndex(-1);
		comboMP.setBounds(557, 57, 232, 24);
		layeredPane.add(comboMP);
		AutoCompleteDecorator.decorate(comboMP);
		JLabel label_3 = new JLabel("Magasin  :");
		label_3.setFont(new Font("Verdana", Font.BOLD, 12));
		label_3.setBounds(755, 10, 85, 26);
		layeredPane.add(label_3);
		
		 combomagasin = new JComboBox();
		combomagasin.setBounds(836, 13, 202, 27);
		layeredPane.add(combomagasin);
		
		JLabel label_4 = new JLabel("Sous-Categorie Mp");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(1048, 16, 144, 24);
		layeredPane.add(label_4);
		
		 soucategoriempcombo = new JComboBox();
		 soucategoriempcombo.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		

		 		

  		  		int i=0;
  		  		if(soucategoriempcombo.getSelectedIndex()!=-1 )
  		  		{
  		  			if(!soucategoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  			categoriempcombo.removeAllItems();
  		  			listecategoriemp=categoriempdao.findBySubcategorie(subcatMap.get(soucategoriempcombo.getSelectedItem()).getId());
  		  			if(listecategoriemp!=null)
  		  			{
  		  			categoriempcombo.addItem("");
  		  				while(i<listecategoriemp.size())
  		  				{
  		  					catMap.put(listecategoriemp.get(i).getNom(), listecategoriemp.get(i));
  		  					categoriempcombo.addItem(listecategoriemp.get(i).getNom());
  		  					i++;
  		  				}
  		  				
  		  			}
  		  				
  		  			}else
  		  			{
  		  			listecategoriemp.clear();
  		  			categoriempcombo.removeAllItems();
  		  		categoriempcombo.addItem("");
  		  			comboMP.removeAllItems();
  		  			}
  		  	
  		  			
  		  		}else
  		  		{
  		  		listecategoriemp.clear();
  		  		categoriempcombo.removeAllItems();
  		  	categoriempcombo.addItem("");
  		  comboMP.removeAllItems();
  		  		}
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 		
		 	}
		 });
		soucategoriempcombo.setBounds(1155, 16, 184, 24);
		layeredPane.add(soucategoriempcombo);
		AutoCompleteDecorator.decorate(soucategoriempcombo);
		JLabel label_5 = new JLabel("Categorie Mp");
		label_5.setBounds(20, 55, 80, 26);
		layeredPane.add(label_5);
		
		 categoriempcombo = new JComboBox();
		 categoriempcombo.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent arg0) {

		 		

  		  		
  		  		if(categoriempcombo.getSelectedIndex()!=-1)
  		  		{
  		  			
  		  			if(!categoriempcombo.getSelectedItem().equals(""))
  		  			{
  		  				CategorieMp categorieMp=catMap.get(categoriempcombo.getSelectedItem().toString());
  		  				listeMatierePremiereCombo.clear();
  		  			txtCodeMP.setText("");
  		  		comboMP.removeAllItems();
		  			comboMP.addItem("");
		  			
		  		listeMatierePremiereCombo=matierePremiereDAO.listeMatierePremierByidcategorie(categorieMp.getId());
  		  			for(int i=0;i<listeMatierePremiereCombo.size();i++)	
  		  			{
  		  				
  		  				MatierePremier matierePremier=listeMatierePremiereCombo.get(i);
  		  			comboMP.addItem(matierePremier.getNom());
  		  				MapMatierPremiere.put(matierePremier.getNom(), matierePremier);
  		  				MapCodeMatierPremiere.put(matierePremier.getCode(), matierePremier);
  		  				
  		  			}
  		  				
  		  				
  		  				
  		  			}else
  		  			{
  		  			listeMatierePremiereCombo.clear();
  		  				txtCodeMP.setText("");
  		  				comboMP.removeAllItems();
  		  			comboMP.addItem("");
  		  				
  		  			}
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  			
  		  		}else
  		  		{
  		  		listeMatierePremiereCombo.clear();
	  				txtCodeMP.setText("");
	  				comboMP.removeAllItems();
	  				comboMP.addItem("");
  		  			
  		  		}
  		  		
  		  		
  		  		
  		  		
  		  		
  		  		
  		  	
		 		
		 		
		 		
		 	
		 		
		 	}
		 });
		categoriempcombo.setBounds(95, 56, 208, 24);
		layeredPane.add(categoriempcombo);
		AutoCompleteDecorator.decorate(categoriempcombo);

		try {

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String) util.DateUtils.getCurrentYear() + "-01-01");

		} catch (Exception e) {
			// TODO: handle exception
		}

		JButton button = new JButton("Initialiser");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				combodepot.setSelectedItem("");
				soucategoriempcombo.setSelectedItem("");
				dateChooserdebut.setCalendar(null);
			dateChooserfin.setCalendar(null);
			comboFournisseur.setSelectedItem("");

			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(724, 174, 107, 24);
		add(button);


		
		 categoriempcombo.removeAllItems();
 		  categoriempcombo.addItem("");
 		soucategoriempcombo.removeAllItems();
 		soucategoriempcombo.addItem("");
 		
 		JLabel lblAu = new JLabel("Au  :");
 		lblAu.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
 		lblAu.setBounds(227, 13, 67, 24);
 		layeredPane.add(lblAu);
 		
 		 dateChooserfin = new JDateChooser();
 		dateChooserfin.setLocale(Locale.FRANCE);
 		dateChooserfin.setDateFormatString("dd/MM/yyyy");
 		dateChooserfin.setBounds(265, 11, 163, 26);
 		layeredPane.add(dateChooserfin);
 		
 		JLabel lblDepot = new JLabel("Depot  :");
 		lblDepot.setFont(new Font("Verdana", Font.BOLD, 12));
 		lblDepot.setBounds(457, 11, 85, 26);
 		layeredPane.add(lblDepot);
 		
 		 combodepot = new JComboBox();
 		 combodepot.addActionListener(new ActionListener() {
 		 	public void actionPerformed(ActionEvent arg0) {
 		 		
 		 		if(!combodepot.getSelectedItem().equals(""))
 		 		{
 		 			
 		 		combomagasin.removeAllItems();
 		 		
 		 		

 			
 					Depot depot = mapDepot.get(combodepot.getSelectedItem());
 					
 					if (depot != null) {
 					
 						
 						listMagasin = depotdao.listeMagasinByTypeMagasinDepot(depot.getId(), MAGASIN_CODE_TYPE_DECHET_MP);
 						int k = 0;
 						combomagasin.addItem("");
 						while (k < listMagasin.size()) {
 							Magasin magasin = listMagasin.get(k);

 							combomagasin.addItem(magasin.getLibelle());

 							mapMagasin.put(magasin.getLibelle(), magasin);

 							k++;

 						}

 					}
 				
 		 		
 		 		}else
 		 		{
 		 			combomagasin.removeAllItems();
 		 			combomagasin.addItem("");
 		 			
 		 		}
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 		
 		 	}
 		 });
 		combodepot.setBounds(530, 13, 202, 27);
 		layeredPane.add(combodepot);
 		
 		 comboFournisseur = new JComboBox();
 		comboFournisseur.setSelectedIndex(-1);
 		comboFournisseur.setBounds(896, 55, 232, 24);
 		layeredPane.add(comboFournisseur);
 		
 		JLabel lblFournisseur = new JLabel("Fournisseur :");
 		lblFournisseur.setFont(new Font("Tahoma", Font.PLAIN, 11));
 		lblFournisseur.setBounds(814, 55, 85, 24);
 		layeredPane.add(lblFournisseur);
		  int i=0;
  		  while(i<listsubcategoriemp.size())
  		  {
  			  subcatMap.put(listsubcategoriemp.get(i).getNom(), listsubcategoriemp.get(i));
  			  soucategoriempcombo.addItem(listsubcategoriemp.get(i).getNom());
  			  i++;
  		  }
  		  
  		  
		if (utilisateur.getLogin().equals("admin")) {
			
			listDepot =depotdao.findAll();
			combodepot.removeAllItems();
			combodepot.addItem("");
			
			for(int d=0;d<listDepot.size();d++)
			{
				
			Depot depot=listDepot.get(d);
			combodepot.addItem(depot.getLibelle());
			mapDepot.put(depot.getLibelle(), depot);
				
				
				
				
			}
			
	

		} else {
			Depot depot = depotdao.findByCode(utilisateur.getCodeDepot());
			
			if (depot != null) {
				combodepot.removeAllItems();
				combodepot.addItem("");
				combodepot.addItem(depot.getLibelle());
				mapDepot.put(depot.getLibelle(), depot);
	

			}
		}
  		 
		
		
		
		listFournisseurMP=fournisseurMPDAO.findAll();
		comboFournisseur.addItem("");
		
		 ImprimerDetailEtatStockDechetMP = new JButton("Imprimer");
		ImprimerDetailEtatStockDechetMP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
			if(magasin!=null)
			{
				 if(listEtatStockDechetMPDetailMP.size()!=0)
				 {
				
						Map parameters = new HashMap();
						String datedu=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
						String dateau=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
						parameters.put("magasin",magasin.getLibelle() );
						parameters.put("date","  Du :"+datedu +" Au : "+dateau);	
						
						JasperUtils.imprimerEtatDetailStockDechetMP(listEtatStockDechetMPDetailMP,parameters);
						
				 } else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun Etat Stock  ", "Erreur", JOptionPane.ERROR_MESSAGE); 
					 return;
				 }
			}
				
			
			
			
				
				
			}
		});
		ImprimerDetailEtatStockDechetMP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ImprimerDetailEtatStockDechetMP.setBounds(704, 932, 112, 32);
		ImprimerDetailEtatStockDechetMP.setIcon(imgImprimer);
		add(ImprimerDetailEtatStockDechetMP);
		
		
		
		
		
		for(int j=0;j<listFournisseurMP.size();j++)
		{
			FournisseurMP fournisseurMP=listFournisseurMP.get(j);	
			comboFournisseur.addItem(fournisseurMP.getCodeFournisseur());
			mapFournisseurMP.put(fournisseurMP.getCodeFournisseur(), fournisseurMP);
			
		} 
		

	}

	void InitialiseTableauDetailMouvementStock() {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] { "Code","Matiere Premiere","Quantite Initial", "Quantite Reception","Transfert Entre", "Quantite Charger", "Quantite Charge Supp",
						 "Quantite Retour","Quantite Sortie", "Quantite Finale", }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		tableEtatStock.getColumnModel().getColumn(0).setPreferredWidth(258);
		tableEtatStock.getColumnModel().getColumn(1).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableEtatStock.getColumnModel().getColumn(3).setPreferredWidth(91);
		tableEtatStock.getColumnModel().getColumn(4).setPreferredWidth(123);
		tableEtatStock.getColumnModel().getColumn(5).setPreferredWidth(118);
		tableEtatStock.getColumnModel().getColumn(6).setPreferredWidth(132);
		tableEtatStock.getColumnModel().getColumn(7).setPreferredWidth(92);

	}

	/*
	 * void InitialiseTableauMouvementStock() { modeleMouvementStock =new
	 * DefaultTableModel( new Object[][] { }, new String[] { "Date Mouvement",
	 * "Depot", "Magasin" } ) { boolean[] columnEditables = new boolean[] {
	 * false,false,false }; public boolean isCellEditable(int row, int column) {
	 * return columnEditables[column]; } }; table.setModel(modeleMouvementStock);
	 * table.getColumnModel().getColumn(0).setPreferredWidth(121);
	 * table.getColumnModel().getColumn(1).setPreferredWidth(106);
	 * table.getColumnModel().getColumn(2).setPreferredWidth(111);
	 * 
	 * 
	 * 
	 * }
	 */

	void afficher_tableEtatStock(List<EtatStockDechetMP> listEtatStockMP) {
		modeleEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] {  "Code","Matiere Premiere","INITIAL", "RECEPTION","DECHET USINE", "DECHET FOURNISSEUR", "PERTE","Retour Dechet Fournisseur En Attent","Retour Dechet Fournisseur D�finitive ","Retour Dechet Fournisseur Annul�","VENTE","Autres Sortie",
						  "Quantite Finale" }) {
			boolean[] columnEditables = new boolean[] {  false, false, false, false, false, false, false,false,false,false,false,false,false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEtatStock.setModel(modeleEtatStock);
		int i = 0;

		while (i < listEtatStockMP.size()) {
			EtatStockDechetMP EtatStockMP = listEtatStockMP.get(i);

	Object[] ligne = {EtatStockMP.getMp().getCode(), EtatStockMP.getMp().getNom(),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteReception().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechet().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseur().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantitePerte().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurEnAttent().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurDefinitive().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurAnnule().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteVente().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteautresSortie().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)) };
	
	

	modeleEtatStock.addRow(ligne);

		

			i++;
		}
	}
	
	
	void afficher_tableDetailEtatStock(List<EtatStockDechetMP> listEtatStockMP) {
		modeleDetailEtatStock = new DefaultTableModel(new Object[][] {},
				new String[] {  "Code","Matiere Premiere","FOURNISSEUR","INITIAL", "RECEPTION","DECHET USINE", "DECHET FOURNISSEUR", "PERTE","Retour Dechet Fournisseur En Attent","Retour Dechet Fournisseur D�finitive ","Retour Dechet Fournisseur Annul�","VENTE","Autres Sortie",
						  "Quantite Finale" }) {
			boolean[] columnEditables = new boolean[] {  false, false,false, false, false, false, false, false,false,false,false,false,false,false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(modeleDetailEtatStock);
		int i = 0;

		while (i < listEtatStockMP.size()) {
			EtatStockDechetMP EtatStockMP = listEtatStockMP.get(i);
if(EtatStockMP.getFournisseurMP()!=null)
{
	Object[] ligne = {EtatStockMP.getMp().getCode(), EtatStockMP.getMp().getNom(),EtatStockMP.getFournisseurMP().getCodeFournisseur(),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteReception().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechet().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseur().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantitePerte().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurEnAttent().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurDefinitive().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurAnnule().setScale(6, RoundingMode.HALF_UP)),
			
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteVente().setScale(6, RoundingMode.HALF_UP)),
									
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteautresSortie().setScale(6, RoundingMode.HALF_UP)),
			
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)) };
	
	

	modeleDetailEtatStock.addRow(ligne);
	
}else
{
	Object[] ligne = {EtatStockMP.getMp().getCode(), EtatStockMP.getMp().getNom(),"",
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteInitial().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteReception().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechet().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseur().setScale(6, RoundingMode.HALF_UP)),
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantitePerte().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurEnAttent().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurDefinitive().setScale(6, RoundingMode.HALF_UP)),
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteDechetFournisseurAnnule().setScale(6, RoundingMode.HALF_UP)),
			
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteVente().setScale(6, RoundingMode.HALF_UP)),
									
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteautresSortie().setScale(6, RoundingMode.HALF_UP)),
			
			
			NumberFormat.getNumberInstance(Locale.FRANCE).format(
					EtatStockMP.getQuantiteFinale().setScale(6, RoundingMode.HALF_UP)) };
	
	

	modeleDetailEtatStock.addRow(ligne);
	
}
	

		

			i++;
		}
	}
}
