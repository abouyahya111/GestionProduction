package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

 
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.CoutArticlePFDAOImpl;
import dao.daoImplManager.CoutHorsProdEnAttentDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailProdResDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutArticlePFDAO;
import dao.daoManager.CoutHorsProdEnAttentDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailProdResDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.Articles;
import dao.entity.CompteStockMP;
import dao.entity.CoutArticlePF;
import dao.entity.CoutHorsProdEnAttent;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProdRes;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.EtatCoutProduction;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTabbedPane;


public class CoutProductionParArticleStocker extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	

	
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<Production> listProductions=new ArrayList<Production>();
	 List<DetailProdRes> listEmployeGesnerique=new ArrayList<DetailProdRes>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<EtatCoutProduction> listEtatCoutProduction =new ArrayList<EtatCoutProduction>();
	private List<Object[]> listObjetCoutMPParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetQuantiteReelMPParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutDetailProductionParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutDetailProdResParArticle =new ArrayList<Object[]>();
	private List<Object[]> listObjetCoutDetailProdGenParArticle =new ArrayList<Object[]>();
	 private List<CoutHorsProdEnAttent> listCoutHorsProductionEnAttent=new ArrayList<CoutHorsProdEnAttent>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private CoutMPDAO coutMPDAO;
	private DepotDAO depotdao;
	List < Articles> listArticles = new ArrayList<Articles>();
	 JComboBox combocodearticle = new JComboBox();
	 JComboBox comboBoxArticle = new JComboBox();
	 private Map< String, Articles> mapCodeArticle= new HashMap<>();
		private Map< String, Articles> mapLibelleAricle = new HashMap<>();
		private ArticlesDAO articlesDAO;
		private JXTable table;
		 private CoutHorsProdEnAttentDAO CoutHorsProdEnAttentDAO;
		 JLabel labelTotalCoutMp = new JLabel("");
		 JLabel labeltotalCoutGenerique = new JLabel("");
		 JLabel labelTotalCoutProduction = new JLabel("");
		  JLabel labelTotalCoutEmballage = new JLabel("");
		  JLabel labelTotal = new JLabel("");
		  JLabel labelTotalrealiser = new JLabel("");
		  JLabel labelCout = new JLabel("");
		  private ImageIcon imgExcel;
		  
		  
		  private DetailProdResDAO detailProdResDAO;
			private DetailProductionDAO detailProductionDAO;
			
			 private List<CoutArticlePF> listCoutArticlePF=new ArrayList<CoutArticlePF>();	
			 private CoutArticlePFDAO coutArticlePFDAO;
			
		 
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public CoutProductionParArticleStocker() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	coutMPDAO=new CoutMPDAOImpl();
        	depotdao= new DepotDAOImpl();
        	articlesDAO=new ArticlesDAOImpl();
        	 CoutHorsProdEnAttentDAO=new CoutHorsProdEnAttentDAOImpl();
        	 detailProdResDAO=new DetailProdResDAOImpl();
       	  detailProductionDAO=new DetailProductionDAOImpl();
       	coutArticlePFDAO=new CoutArticlePFDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Num OF","Date", "Depot","Article","Statut"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false
				  			     	};
				  			     	public boolean isCellEditable(int row, int column) {
				  			     		return columnEditables[column];
				  			     	}
				  			     };
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 1120, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1120, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(158, 10, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		 
		dateDebutChooser.setBounds(37, 11, 111, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(191, 11, 124, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(325, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(373, 12, 149, 24);
		layeredPane.add(combodepot);
		
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		combodepot.addItem(depot.getLibelle());
		     		mapDepot.put(depot.getLibelle(), depot);
		     		k++;
		     		
		     	}
		      
		  }else{
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JLabel label = new JLabel("Code Article");
		  label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  label.setBounds(533, 10, 67, 24);
		  layeredPane.add(label);
		  
		   combocodearticle = new JComboBox();
		   combocodearticle.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		

		 		
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		
	     	 		 Articles articles=mapCodeArticle.get(combocodearticle.getSelectedItem());
	     	 		 
	     	 		if(articles!=null)
	     	 		{
	     	 			
			  			comboBoxArticle.setSelectedItem (articles.getLiblle());	
			  	
			
	     	 		}else
	     	 		{
	     	 			comboBoxArticle.setSelectedItem ("");	
	     	 		}
             
	     	 	 	}
	     	 	
	  		
	  		
	  		
	  		
	  	
		 		
		 		
		 	
		   		
		   		
		   	}
		   });
		  combocodearticle.setBounds(610, 12, 138, 24);
		  layeredPane.add(combocodearticle);
		  AutoCompleteDecorator.decorate(combocodearticle);
		  JLabel label_1 = new JLabel("Libelle Article");
		  label_1.setBounds(775, 10, 108, 26);
		  layeredPane.add(label_1);
		  
		   comboBoxArticle = new JComboBox();
		   comboBoxArticle.addItemListener(new ItemListener() {
		   	public void itemStateChanged(ItemEvent e) {
		   		

		 		

	     	 	
	     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
	     	
	     	 		
	     	 		 Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
	     	 		 
	     	 		if(articles!=null)
	     	 		{
	     	 			

		  			combocodearticle.setSelectedItem (articles.getCodeArticle());	
		  		
	     	 			
	     	 		}else
	     	 		{
	     	 			combocodearticle.setSelectedItem ("");	
	     	 		}
                 
	     	 	 	}
	     	 	
	 		
	 		
	 		
	 		
	 	
		   		
		   	}
		   });
		  comboBoxArticle.setBounds(858, 11, 252, 24);
		  layeredPane.add(comboBoxArticle);
		  AutoCompleteDecorator.decorate(comboBoxArticle);
		  
		  JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		  GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		  gridBagLayout.rowWeights = new double[]{0.0};
		  gridBagLayout.rowHeights = new int[]{0};
		  gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		  gridBagLayout.columnWidths = new int[]{0, 0, 0};
		  titledSeparator_1.setTitle("Cout Production");
		  titledSeparator_1.setBackground(Color.RED);
		  titledSeparator_1.setBounds(9, 76, 1120, 30);
		  add(titledSeparator_1);
		  
		  JButton btnAfficherStock = new JButton();
		  btnAfficherStock.setBounds(1160, 21, 31, 31);
		  add(btnAfficherStock);
		  btnAfficherStock.setIcon(imgRechercher);
		  btnAfficherStock.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		
		  		String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
		  		String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
		  	if(dateDebut.equals(""))	{
		  		JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
		  	} else if(dateFin.equals("")){
		  		JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
		  		
		  	}else if(combodepot.getSelectedIndex()==-1)
		  	{
		  		JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
		  	}else
		  	
		  	{
		  		
		  		listCoutArticlePF=coutArticlePFDAO.findAll();	
		  		
		  	  
		  		
		  	  	afficher_tableMP(listCoutArticlePF); 
		  		
		  		
		  	  /*
		  		CalculerCoutProductionParArticle();
		  		
		  		
		  		Depot depot=mapDepot.get(combodepot.getSelectedItem());
		  		Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
		  		listEtatCoutProduction.clear();
		  		listObjetCoutDetailProdGenParArticle.clear();
		  		listObjetCoutMPParArticle.clear();
		  		listObjetCoutDetailProdResParArticle.clear();
		  		listObjetCoutDetailProductionParArticle.clear();
		  		listObjetQuantiteReelMPParArticle.clear();
		  		
		  		listObjetQuantiteReelMPParArticle=coutMPDAO.listeSumQuantiteReelParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutMPParArticle=coutMPDAO.listeCoutMPParArticle(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutDetailProdGenParArticle=coutMPDAO.listeCoutDetailProdGenParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutDetailProdResParArticle=coutMPDAO.listeCoutDetailProdResParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
		  		listObjetCoutDetailProductionParArticle=coutMPDAO.listeCoutDetailProductionParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);

		  		listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.ListHeursCoutHorsProdEnAttentParDepot(dateDebutChooser.getDate(), dateFinChooser.getDate(), depot.getId(), Constantes.ETAT_OF_TERMINER, "");
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Quantite Realise des Articles   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
		  		
		  		for(int j=0;j<listObjetQuantiteReelMPParArticle.size();j++)
				{
					 Object[] object=listObjetQuantiteReelMPParArticle.get(j);	
					if(object[0]!=null)
					{
						Articles article=(Articles)object[0];
						
						EtatCoutProduction etatCoutProduction=new EtatCoutProduction();
						
						etatCoutProduction.setArticle(article);
						etatCoutProduction.setCoutMP(BigDecimal.ZERO);
						etatCoutProduction.setCoutEmployeGenerique(BigDecimal.ZERO);
						etatCoutProduction.setCoutEmployeProduction (BigDecimal.ZERO);
						etatCoutProduction.setCoutEmployeEmballage(BigDecimal.ZERO);
						etatCoutProduction.setQuantiteRealiser(new BigDecimal(object[1].toString()));
						etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
						etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
						listEtatCoutProduction.add(etatCoutProduction);
						
					}
					
				
				
				}
		  		
		  		
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts MP   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
		  		
		  		
		  		for(int j=0;j<listObjetCoutMPParArticle.size();j++)
				{
					 Object[] object=listObjetCoutMPParArticle.get(j);	
					if(object[0]!=null)
					{
						Articles article=(Articles)object[0];
						
					for(int d=0;d<listEtatCoutProduction.size();d++)
					{
						
						EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);
						
						
					if(article.getId()==etatCoutProduction.getArticle().getId())	
					{
						
						if(object[1]!=null)
						{
							
							etatCoutProduction.setCoutMP(etatCoutProduction.getCoutMP().add(new BigDecimal(object[1].toString())));
							etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
							etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			
							listEtatCoutProduction.set(d, etatCoutProduction);
						}
						
						
						
						
						
					}
						
						
					}
						
						
						
						
						
						
						
					}
					
				
				
				}
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Prod Res   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
		  	
		  		
		  		BigDecimal coutTotal=BigDecimal.ZERO;
		  		BigDecimal cout25=BigDecimal.ZERO;
		  		BigDecimal cout50=BigDecimal.ZERO;
		 		for(int j=0;j<listObjetCoutDetailProdResParArticle.size();j++)
				{
		 			
		 			 coutTotal=BigDecimal.ZERO;
			  		 cout25=BigDecimal.ZERO;
			  		 cout50=BigDecimal.ZERO;	
					 Object[] object=listObjetCoutDetailProdResParArticle.get(j);	
					if(object[0]!=null)
					{
						Articles article=(Articles)object[0];
						
						if(object[1]!=null)
						{
							coutTotal=new BigDecimal(object[1].toString());

						}
						
						if(object[2]!=null)
						{
						cout25=new BigDecimal(object[2].toString());
						
						}
						if(object[3]!=null)
						{
							cout50=new BigDecimal(object[3].toString());
						}
						
						
						
					for(int d=0;d<listEtatCoutProduction.size();d++)
					{
						
						EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);
						
						
					if(article.getId()==etatCoutProduction.getArticle().getId())	
					{
						
						
							
							etatCoutProduction.setCoutEmployeGenerique(etatCoutProduction.getCoutEmployeGenerique().add(coutTotal.add(cout50.add(cout25))));
							etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
							etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));
			
							listEtatCoutProduction.set(d, etatCoutProduction);
						
						
						
						
						
						
					}
						
						
					}
						
						
						
						
						
						
						
					}
					
				
				
				}
		  		
		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Production   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
			  	
		  		

for(int j=0;j<listObjetCoutDetailProductionParArticle.size();j++)
{

coutTotal=BigDecimal.ZERO;
cout25=BigDecimal.ZERO;
cout50=BigDecimal.ZERO;	
Object[] object=listObjetCoutDetailProductionParArticle.get(j);	
if(object[0]!=null)
{
Articles article=(Articles)object[0];

if(object[1]!=null)
{
coutTotal=new BigDecimal(object[1].toString());

}

if(object[2]!=null)
{
cout25=new BigDecimal(object[2].toString());

}
if(object[3]!=null)
{
cout50=new BigDecimal(object[3].toString());
}



for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


if(article.getId()==etatCoutProduction.getArticle().getId())	
{



etatCoutProduction.setCoutEmployeProduction (etatCoutProduction.getCoutEmployeProduction().add(coutTotal.add(cout50.add(cout25))));
etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

listEtatCoutProduction.set(d, etatCoutProduction);






}


}



}



}	



/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Employer Travailler hors production   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
	
	

for(int j=0;j<listCoutHorsProductionEnAttent.size();j++)
{

coutTotal=BigDecimal.ZERO;
cout25=BigDecimal.ZERO;
cout50=BigDecimal.ZERO;	
CoutHorsProdEnAttent coutHorsProdEnAttent=listCoutHorsProductionEnAttent.get(j);	


for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


if(coutHorsProdEnAttent.getProduction().getArticles().getId()==etatCoutProduction.getArticle().getId())	
{



etatCoutProduction.setCoutEmployeProduction (etatCoutProduction.getCoutEmployeProduction().add(coutHorsProdEnAttent.getCoutTotal().add(coutHorsProdEnAttent.getCoutHoraire50().add(coutHorsProdEnAttent.getCoutHoraire25()))));
etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

listEtatCoutProduction.set(d, etatCoutProduction);






}


}







}



		  		
/////////////////////////////////////////////////////////////////////////////////////////////////  les Couts Emballage (prodgen)   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  		
	
	

for(int j=0;j<listObjetCoutDetailProdGenParArticle.size();j++)
{

coutTotal=BigDecimal.ZERO;
cout25=BigDecimal.ZERO;
cout50=BigDecimal.ZERO;	
Object[] object=listObjetCoutDetailProdGenParArticle.get(j);	
if(object[0]!=null)
{
Articles article=(Articles)object[0];

if(object[1]!=null)
{
coutTotal=new BigDecimal(object[1].toString());

}

if(object[2]!=null)
{
cout25=new BigDecimal(object[2].toString());

}
if(object[3]!=null)
{
cout50=new BigDecimal(object[3].toString());
}



for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


if(article.getId()==etatCoutProduction.getArticle().getId())	
{



etatCoutProduction.setCoutEmployeEmballage(etatCoutProduction.getCoutEmployeEmballage().add(coutTotal.add(cout50.add(cout25))));
etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

listEtatCoutProduction.set(d, etatCoutProduction);






}


}



}



}



for(int d=0;d<listEtatCoutProduction.size();d++)
{

EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(d);


etatCoutProduction.setTotal(etatCoutProduction.getCoutMP().add(etatCoutProduction.getCoutEmployeGenerique().add(etatCoutProduction.getCoutEmployeProduction().add(etatCoutProduction.getCoutEmployeEmballage()))));
if(etatCoutProduction.getQuantiteRealiser().compareTo(BigDecimal.ZERO)!=0)
{
	etatCoutProduction.setCout(etatCoutProduction.getTotal().divide(etatCoutProduction.getQuantiteRealiser(), 6, RoundingMode.HALF_UP));

}

listEtatCoutProduction.set(d, etatCoutProduction);









}



afficher_tableMP(listEtatCoutProduction);




*/
		  		
		
		  	}
		    }
		  });
		  btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(9, 117, 1233, 604);
		  add(scrollPane);
		  
		  table = new JXTable();
		  table.setModel(new DefaultTableModel(
		  	new Object[][] {
		  	},
		  	new String[] {
		  		"CODE ARTICLE", "ARTICLE", "COUT MP", "COUT G.GENERIQUE", "COUT G.PRODUCTION", "G.EMBALLAGE", "TOTAL", "QTE REALISEE", "Cout / KG"
		  	}
		  ));
		  table.setColumnSelectionAllowed(true);
		  scrollPane.setViewportView(table);
		  
		  JButton button = new JButton();
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		
		  		if(listEtatCoutProduction.size()!=0)
		  		{
		  		
					if(dateDebutChooser.getDate()!=null && dateFinChooser.getDate()==null)
					{
						dateFinChooser.setDate(dateDebutChooser.getDate());
					}else if(dateDebutChooser.getDate() == null && dateFinChooser.getDate()!=null)
					{
						dateDebutChooser.setDate(dateFinChooser.getDate());
					}
					
					
					String dateDu=dateFormat.format(dateDebutChooser.getDate());
					String	dateAu=dateFormat.format(dateFinChooser.getDate());
					
					
		  			Map parameters = new HashMap();
			 		parameters.put("magasin", combodepot.getSelectedItem().toString());			  		
			 		parameters.put("date", "Du : "+dateDu +" Au : "+dateAu);
					JasperUtils.imprimerCoutProductionParArticle(parameters,  listEtatCoutProduction); 
		  		}
		  		
		  		
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(424, 743, 104, 31);
		  button.setIcon(imgImprimer);
		  add(button);
		  
		   labelTotalCoutMp = new JLabel("");
		  labelTotalCoutMp.setForeground(Color.RED);
		  labelTotalCoutMp.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutMp.setBounds(10, 743, 550, 24);
		  add(labelTotalCoutMp);
		  
		   labeltotalCoutGenerique = new JLabel("");
		  labeltotalCoutGenerique.setForeground(Color.RED);
		  labeltotalCoutGenerique.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labeltotalCoutGenerique.setBounds(10, 779, 550, 24);
		  add(labeltotalCoutGenerique);
		  
		   labelTotalCoutProduction = new JLabel("");
		  labelTotalCoutProduction.setForeground(Color.RED);
		  labelTotalCoutProduction.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutProduction.setBounds(10, 814, 550, 24);
		  add(labelTotalCoutProduction);
		  
		   labelTotalCoutEmballage = new JLabel("");
		  labelTotalCoutEmballage.setForeground(Color.RED);
		  labelTotalCoutEmballage.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalCoutEmballage.setBounds(10, 849, 550, 24);
		  add(labelTotalCoutEmballage);
		  
		   labelTotal = new JLabel("");
		  labelTotal.setForeground(Color.RED);
		  labelTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotal.setBounds(10, 885, 550, 24);
		  add(labelTotal);
		  
		   labelTotalrealiser = new JLabel("");
		  labelTotalrealiser.setForeground(Color.RED);
		  labelTotalrealiser.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelTotalrealiser.setBounds(10, 924, 550, 24);
		  add(labelTotalrealiser);
		  
		  JButton button_1 = new JButton("Exporter Excel");
		  button_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		

		  		
		  		

				


			 	
				
				if(table.getRowCount()!=0)
				{
				 
					
					
					
					
					String titre="Couts Productions Par Articles ";
		    		String titrefeuilleexcel="Couts Productions Par Articles ";
		    		ExporterTableVersExcel.tabletoexcelCoutProductionParArticle (table, titre,titrefeuilleexcel);
					
					
				}else
				{
					
					JOptionPane.showMessageDialog(null, "la table est vide !!!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
					
					
				}
			
		
		
				
				
				
				
				
				
			
				
				
				
				
				
			
				
			
		  		
		  		
		  		
		  	
		  		
		  	}
		  });
		  button_1.setBounds(543, 743, 156, 34);
		  button_1.setIcon(imgExcel);
		  add(button_1);
		  
		   labelCout = new JLabel("");
		  labelCout.setForeground(Color.RED);
		  labelCout.setFont(new Font("Tahoma", Font.BOLD, 13));
		  labelCout.setBounds(9, 969, 550, 24);
		  add(labelCout);
		
		
	
		ChargerComboArticle();		  		     
				  		 
	}
	
void CalculerCoutProductionParArticle()
{
	
	/*
	 listEtatCoutProduction.clear();
	Depot depot=mapDepot.get(combodepot.getSelectedItem());
		Articles articles=mapLibelleAricle.get(comboBoxArticle.getSelectedItem());
	listObjetQuantiteReelMPParArticle.clear();
		
		listObjetQuantiteReelMPParArticle=coutMPDAO.listeSumQuantiteReelParArticle (dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),articles);
  		for(int j=0;j<listObjetQuantiteReelMPParArticle.size();j++)
		{
			 Object[] object=listObjetQuantiteReelMPParArticle.get(j);	
			if(object[0]!=null)
			{
				Articles article=(Articles)object[0];
				
				EtatCoutProduction etatCoutProduction=new EtatCoutProduction();
				
				etatCoutProduction.setArticle(article);
				etatCoutProduction.setCoutMP(BigDecimal.ZERO);
				etatCoutProduction.setCoutEmployeGenerique(BigDecimal.ZERO);
				etatCoutProduction.setCoutEmployeProduction (BigDecimal.ZERO);
				etatCoutProduction.setCoutEmployeEmballage(BigDecimal.ZERO);
				etatCoutProduction.setQuantiteRealiser(BigDecimal.ZERO);
				etatCoutProduction.setTotal(BigDecimal.ZERO);
				etatCoutProduction.setCout(BigDecimal.ZERO);
				listEtatCoutProduction.add(etatCoutProduction);
				
			}
			
		
		
		}
  		
  		
  		BigDecimal coutTotalDechetMPTmp= BigDecimal.ZERO;
		BigDecimal coutTotalDechetFournisseurMPTmp= BigDecimal.ZERO;
		BigDecimal coutTotalOffreMPTmp= BigDecimal.ZERO;
		BigDecimal coutTotalManquanteMPTmp= BigDecimal.ZERO;
		BigDecimal coutTotalConsommeMPTmp= BigDecimal.ZERO;
		BigDecimal QuantiteConsomme= BigDecimal.ZERO;
		BigDecimal QuantiteOffre= BigDecimal.ZERO;
		BigDecimal QuantiteDechet= BigDecimal.ZERO;
		 
		BigDecimal QuantiteManquante= BigDecimal.ZERO;
		BigDecimal QuantiteDechetFour= BigDecimal.ZERO;
  	for(int s=0;s<listEtatCoutProduction.size();s++)
  	{
  		
  		EtatCoutProduction etatCoutProduction=listEtatCoutProduction.get(s);	
  		
  		BigDecimal coutTotalEmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalEmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalEmployeEmballage= BigDecimal.ZERO;
		BigDecimal coutTotalSupp50EmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalSupp50EmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalSupp50EmployeEmballage= BigDecimal.ZERO;
		BigDecimal coutTotalSupp25EmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalSupp25EmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalSupp25EmployeEmballage= BigDecimal.ZERO;
		BigDecimal coutTotalDechetMP= BigDecimal.ZERO;
		BigDecimal coutTotalDechetFournisseurMP= BigDecimal.ZERO;
		BigDecimal coutTotalOffreMP= BigDecimal.ZERO;
		BigDecimal coutTotalManquanteMP= BigDecimal.ZERO;
		BigDecimal coutTotalPlus= BigDecimal.ZERO;
		BigDecimal coutTotalCoutQuantiteConsommeMP= BigDecimal.ZERO;
		BigDecimal QuantiteReel= BigDecimal.ZERO;
		BigDecimal quantiteConsommeSoustraireQuantitePlus=BigDecimal.ZERO;
		BigDecimal TotalHeuresProduction= BigDecimal.ZERO;
		BigDecimal TotalCoutEmployerResponsableParProduction= BigDecimal.ZERO;
		BigDecimal TotalCoutPrimeEmployerResponsableParProduction= BigDecimal.ZERO;
		BigDecimal coutTotalPrimeEmployeGenerique= BigDecimal.ZERO;
		BigDecimal coutTotalPrimeEmployeProduction= BigDecimal.ZERO;
		BigDecimal coutTotalPrimeEmployeEmballage= BigDecimal.ZERO;
		
  		listProductions=productionDAO.listeProductionTerminerbyDepotEntreDeuxDateByArticle(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot.getCode(),etatCoutProduction.getArticle());
		
		for(int n=0;n<listProductions.size();n++)
		{
			coutTotalEmployeGenerique= BigDecimal.ZERO;
			coutTotalSupp50EmployeGenerique= BigDecimal.ZERO;
			coutTotalSupp25EmployeGenerique= BigDecimal.ZERO;
			
			TotalHeuresProduction= BigDecimal.ZERO;
			quantiteConsommeSoustraireQuantitePlus=BigDecimal.ZERO;
			Production production=	listProductions.get(n);
			
			QuantiteReel=QuantiteReel.add(production.getQuantiteReel());
			listCoutMP=production.getListCoutMP();
			listEmployeGesnerique= detailProdResDAO.ListHeursDetailResponsableProdParDepot(production.getDate(), production.getDate(), production.getMagasinStockage().getDepot().getId(),"");
			listEmployeEmballage=production.getListDetailProdGen();
			listEmployeProduction=detailProductionDAO.ListEmployeDetailProductionByProduction(production);
			
			
			listCoutHorsProductionEnAttent=CoutHorsProdEnAttentDAO.findByProduction(production);
			
			for(int k=0;k<listCoutMP.size();k++)
			{
				
				coutTotalDechetMP=coutTotalDechetMP.add(listCoutMP.get(k).getQuantDechet().multiply(listCoutMP.get(k).getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))));
				if(!listCoutMP.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
				{
					QuantiteOffre=QuantiteOffre.add(listCoutMP.get(k).getQuantiteOffre());
					coutTotalOffreMP=coutTotalOffreMP.add(listCoutMP.get(k).getQuantiteOffre().multiply(listCoutMP.get(k).getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))));
				}
				
				quantiteConsommeSoustraireQuantitePlus=listCoutMP.get(k).getQuantConsomme().subtract(listCoutMP.get(k).getQuantiteManquanteFrPlus());
				QuantiteConsomme=QuantiteConsomme.add(listCoutMP.get(k).getQuantConsomme().subtract(listCoutMP.get(k).getQuantiteManquanteFrPlus()));
				QuantiteDechet=QuantiteDechet.add(listCoutMP.get(k).getQuantDechet() );
				QuantiteDechetFour=QuantiteDechetFour.add(listCoutMP.get(k).getQuantDechetFournisseur() );
				QuantiteManquante=QuantiteManquante.add(listCoutMP.get(k).getQuantiteManquante());
				
				coutTotalCoutQuantiteConsommeMP=coutTotalCoutQuantiteConsommeMP.add(quantiteConsommeSoustraireQuantitePlus.multiply(listCoutMP.get(k).getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))));
				coutTotalDechetFournisseurMP=coutTotalDechetFournisseurMP.add(listCoutMP.get(k).getQuantDechetFournisseur().multiply(listCoutMP.get(k).getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))));
				coutTotalManquanteMP=coutTotalManquanteMP.add(listCoutMP.get(k).getQuantiteManquante().multiply(listCoutMP.get(k).getMatierePremier().getPrixByYear( DateUtils.getAnnee(dateDebutChooser.getDate()))));
			
			}
			
		
			for(int i=0;i<listEmployeGesnerique.size();i++)
			{
				TotalHeuresProduction=listEmployeGesnerique.get(i).getTotalHeuresProduction();
				coutTotalEmployeGenerique=coutTotalEmployeGenerique.add((listEmployeGesnerique.get(i).getDelaiEmploye().multiply(listEmployeGesnerique.get(i).getCoutHoraire())));
				coutTotalSupp25EmployeGenerique=coutTotalSupp25EmployeGenerique.add((listEmployeGesnerique.get(i).getHeureSupp25().multiply(listEmployeGesnerique.get(i).getCoutHoraireSupp25())));
				coutTotalSupp50EmployeGenerique=coutTotalSupp50EmployeGenerique.add((listEmployeGesnerique.get(i).getHeureSupp50().multiply(listEmployeGesnerique.get(i).getCoutHoraireSupp50())));
				if(listEmployeGesnerique.get(i).getRemise()!=null)
				{
					coutTotalPrimeEmployeGenerique= coutTotalPrimeEmployeGenerique.add(listEmployeGesnerique.get(i).getRemise()) ;
				}
			}
			
			TotalCoutEmployerResponsableParProduction=TotalCoutEmployerResponsableParProduction.add(((coutTotalEmployeGenerique.add(coutTotalSupp25EmployeGenerique.add(coutTotalSupp50EmployeGenerique).add(coutTotalPrimeEmployeGenerique))).divide(TotalHeuresProduction, 6, RoundingMode.HALF_UP)).multiply(production.getNbreHeure())) ;

			
			
			
			for(int j=0;j<listEmployeProduction.size();j++)
			{
				coutTotalEmployeProduction=coutTotalEmployeProduction.add(listEmployeProduction.get(j).getCoutHoraire().multiply(listEmployeProduction.get(j).getDelaiEmploye()));
				coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listEmployeProduction.get(j).getCoutHoraireSupp25().multiply(listEmployeProduction.get(j).getHeureSupp25()));
				coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listEmployeProduction.get(j).getCoutHoraireSupp50().multiply(listEmployeProduction.get(j).getHeureSupp50()));
				if(listEmployeProduction.get(j).getRemise()!=null)
				{
					coutTotalPrimeEmployeProduction=coutTotalPrimeEmployeProduction.add(listEmployeProduction.get(j).getRemise());
					
				}
			
			}
			
			for(int j=0;j<listCoutHorsProductionEnAttent.size();j++)
			{
				coutTotalEmployeProduction=coutTotalEmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutTotal());
				coutTotalSupp25EmployeProduction=coutTotalSupp25EmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutSupp25());
				coutTotalSupp50EmployeProduction=coutTotalSupp50EmployeProduction.add(listCoutHorsProductionEnAttent.get(j).getCoutSupp50());
				
			}
			
			
			 
			
			
			
			
			for(int j=0;j<listEmployeEmballage.size();j++)
			{
				coutTotalEmployeEmballage=coutTotalEmployeEmballage.add(listEmployeEmballage.get(j).getCoutTotal());
				coutTotalSupp25EmployeEmballage=coutTotalSupp25EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp25());
				coutTotalSupp50EmployeEmballage=coutTotalSupp50EmployeEmballage.add(listEmployeEmballage.get(j).getCoutSupp50());
				if(listEmployeEmballage.get(j).getRemise()!=null)
				{
					coutTotalPrimeEmployeEmballage=coutTotalPrimeEmployeEmballage.add(listEmployeEmballage.get(j).getRemise());
				}
			}
			
		}
  		
  		 
		etatCoutProduction.setQuantiteRealiser(QuantiteReel);
		etatCoutProduction.setCoutMP(coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP)))));
		etatCoutProduction.setCoutEmployeGenerique(TotalCoutEmployerResponsableParProduction);
		etatCoutProduction.setCoutEmployeProduction (coutTotalEmployeProduction.add(coutTotalSupp25EmployeProduction.add(coutTotalSupp50EmployeProduction).add(coutTotalPrimeEmployeProduction)));
		etatCoutProduction.setCoutEmployeEmballage(coutTotalEmployeEmballage.add(coutTotalSupp25EmployeEmballage).add(coutTotalSupp50EmployeEmballage).add(coutTotalPrimeEmployeEmballage));
		etatCoutProduction.setQuantiteRealiser(QuantiteReel);
		etatCoutProduction.setTotal( (coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))))).add(TotalCoutEmployerResponsableParProduction.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP).add(coutTotalPrimeEmployeProduction.setScale(2, RoundingMode.HALF_UP))).add(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP).add(coutTotalPrimeEmployeEmballage.setScale(2, RoundingMode.HALF_UP))));
		etatCoutProduction.setCout(((coutTotalCoutQuantiteConsommeMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalDechetFournisseurMP.setScale(2, RoundingMode.HALF_UP)).add(coutTotalOffreMP.setScale(2, RoundingMode.HALF_UP).add(coutTotalManquanteMP.setScale(2, RoundingMode.HALF_UP))))).add(TotalCoutEmployerResponsableParProduction.setScale(2, RoundingMode.HALF_UP)).add(coutTotalEmployeProduction.setScale(2, RoundingMode.HALF_UP).add(coutTotalPrimeEmployeProduction.setScale(2, RoundingMode.HALF_UP))).add(coutTotalEmployeEmballage.setScale(2, RoundingMode.HALF_UP).add(coutTotalPrimeEmployeEmballage.setScale(2, RoundingMode.HALF_UP)))) .divide(etatCoutProduction.getQuantiteRealiser(), 2, RoundingMode.HALF_UP));	
		
		listEtatCoutProduction.set(s, etatCoutProduction);
		
		coutTotalConsommeMPTmp=coutTotalConsommeMPTmp.add(coutTotalCoutQuantiteConsommeMP);
		  coutTotalDechetMPTmp=coutTotalDechetMPTmp.add(coutTotalDechetMP) ;
		  coutTotalDechetFournisseurMPTmp=coutTotalDechetFournisseurMPTmp.add(coutTotalDechetFournisseurMP) ;
		  coutTotalOffreMPTmp=coutTotalOffreMPTmp.add(coutTotalOffreMP) ;
		  coutTotalManquanteMPTmp= coutTotalManquanteMPTmp.add(coutTotalManquanteMP);	
   	}
  	
   
  	System.out.println("coutTotalMPConsomme : "+coutTotalConsommeMPTmp);
  	System.out.println("coutTotalDechetMPTmp : "+coutTotalDechetMPTmp);
  	System.out.println("coutTotalDechetFournisseurMPTmp : "+coutTotalDechetFournisseurMPTmp);	
	System.out.println("coutTotalOffreMPTmp : "+coutTotalOffreMPTmp);
	System.out.println("coutTotalManquanteMPTmp : "+coutTotalManquanteMPTmp);

	System.out.println("QuantiteConsomme : "+QuantiteConsomme);
  	System.out.println("QuantiteDechet : "+QuantiteDechet);	
	System.out.println("QuantiteOffre : "+QuantiteOffre);
	System.out.println("QuantiteManquante : "+QuantiteManquante);
	System.out.println("QuantiteDechetFour : "+QuantiteDechetFour);
	
  	afficher_tableMP(listEtatCoutProduction);
  	*/	
  		
}

	void ChargerComboArticle()
	{
		 comboBoxArticle.removeAllItems();
		 combocodearticle.removeAllItems();
		 combocodearticle.addItem("");
		 comboBoxArticle.addItem("");
		   listArticles=articlesDAO.findAll();
	        int i=0;
		      	while(i<listArticles.size())
		      		{	
		      			Articles article=listArticles.get(i);
		      			mapCodeArticle.put(article.getCodeArticle(), article);
		      			mapLibelleAricle.put( article.getLiblle(),article);
		      			
		      			comboBoxArticle.addItem(article.getLiblle());
		      			combocodearticle.addItem(article.getCodeArticle());
		      			i++;
		      		}
			 
		 
		 
		 
	}
	
	
	
	void afficher_tableMP(List<CoutArticlePF> listCoutArticlePF)
	{
	intialiserTableau();
		 
	
	DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setGroupingSeparator(' ');
	DecimalFormat dfDecimal = new DecimalFormat("###########0.00####");
	dfDecimal.setDecimalFormatSymbols(symbols);
	dfDecimal.setGroupingSize(3);
	dfDecimal.setGroupingUsed(true);
		int i=0;
		BigDecimal TotalcoutMP=BigDecimal.ZERO;
		BigDecimal TotalcoutGenerique=BigDecimal.ZERO;
		BigDecimal TotalcoutProduction=BigDecimal.ZERO;
		BigDecimal TotalcoutEmballage=BigDecimal.ZERO;
		BigDecimal TotalcoutTotal=BigDecimal.ZERO;
		BigDecimal TotalRealiser=BigDecimal.ZERO;
		BigDecimal cout=BigDecimal.ZERO;
		
		
		while(i<listCoutArticlePF.size())
		{	
			CoutArticlePF coutArticlePF=listCoutArticlePF.get(i);
		
		
			
			
			
			TotalcoutMP=TotalcoutMP.add(coutArticlePF.getCoutMP());
			TotalcoutGenerique=TotalcoutGenerique.add(coutArticlePF.getCoutEquipeGenerique());
			TotalcoutProduction=TotalcoutProduction.add(coutArticlePF.getCoutEquipeProduction());
			TotalcoutEmballage=TotalcoutEmballage.add(coutArticlePF.getCoutEquipeEmballage());
			TotalcoutTotal=TotalcoutTotal.add(coutArticlePF.getTotal());
			TotalRealiser=TotalRealiser.add(coutArticlePF.getQuantiteRealiser());
			
				
					 Object []ligne={coutArticlePF.getArticles().getCodeArticle(),coutArticlePF.getArticles().getLiblle(),dfDecimal.format(coutArticlePF.getCoutMP().setScale(4, RoundingMode.HALF_UP)),dfDecimal.format(coutArticlePF.getCoutEquipeGenerique().setScale(4, RoundingMode.HALF_UP)),dfDecimal.format(coutArticlePF.getCoutEquipeProduction().setScale(4, RoundingMode.HALF_UP)),dfDecimal.format(coutArticlePF.getCoutEquipeEmballage().setScale(4, RoundingMode.HALF_UP)),dfDecimal.format(coutArticlePF.getTotal().setScale(4, RoundingMode.HALF_UP)),dfDecimal.format(coutArticlePF.getQuantiteRealiser().setScale(4, RoundingMode.HALF_UP)),dfDecimal.format(coutArticlePF.getCout().setScale(4, RoundingMode.HALF_UP))};
					 modeleMP.addRow(ligne);
					
							 
			
			
			
			i++;
		}
		
		labelTotalCoutMp.setText("Total Cout MP : "+dfDecimal.format( TotalcoutMP.setScale(4, RoundingMode.HALF_UP)));
		labeltotalCoutGenerique.setText("Total Cout Generique : "+dfDecimal.format(TotalcoutGenerique.setScale(4, RoundingMode.HALF_UP)));
		labelTotalCoutProduction.setText("Total Cout production : "+dfDecimal.format(TotalcoutProduction.setScale(4, RoundingMode.HALF_UP)));
		labelTotalCoutEmballage.setText("Total Cout Emballage : "+dfDecimal.format(TotalcoutEmballage.setScale(4, RoundingMode.HALF_UP)));
		labelTotal.setText("Total Cout Total : "+dfDecimal.format(TotalcoutTotal.setScale(4, RoundingMode.HALF_UP)));
		labelTotalrealiser.setText("Total R�aliser : "+dfDecimal.format(TotalRealiser.setScale(4, RoundingMode.HALF_UP)));
			if(TotalRealiser.compareTo(BigDecimal.ZERO)!=0)
			{
				labelCout.setText("Cout Moyen : "+TotalcoutTotal.divide(TotalRealiser, 4, RoundingMode.HALF_UP)+"");
			}
			
		
	}
	
	
	void intialiserTableau(){
		 modeleMP =new DefaultTableModel(
			     	new Object[][] {
			     	},
			     	new String[] {
			     			"CODE ARTICLE", "ARTICLE", "COUT MP", "COUT G.GENERIQUE", "COUT G.PRODUCTION", "G.EMBALLAGE", "TOTAL", "QTE REALISEE", "Cout / KG"
			     	}
			     ) {
			     	boolean[] columnEditables = new boolean[] {
			     			false,false,false,false,false,false,false,false,false
			     	};
			     	public boolean isCellEditable(int row, int column) {
			     		return columnEditables[column];
			     	}
			     };
			     
			     table.setModel(modeleMP); 
			     table.getColumnModel().getColumn(0).setPreferredWidth(60);
			     table.getColumnModel().getColumn(1).setPreferredWidth(120);
			     table.getColumnModel().getColumn(2).setPreferredWidth(60);
			     table.getColumnModel().getColumn(3).setPreferredWidth(60);
			     table.getColumnModel().getColumn(4).setPreferredWidth(60);
			     table.getColumnModel().getColumn(5).setPreferredWidth(60);
			     table.getColumnModel().getColumn(6).setPreferredWidth(60);
			     table.getColumnModel().getColumn(7).setPreferredWidth(60);
			     table.getColumnModel().getColumn(8).setPreferredWidth(60);
	}
}
