package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

 

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

 

import util.Constantes;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
 
import dao.daoImplManager.ArticlesDAOImpl;
 
import dao.daoImplManager.DepotDAOImpl;
 
import dao.daoImplManager.MatierePremierDAOImpl;
 
import dao.daoImplManager.StockPFDAOImpl;
 
import dao.daoManager.ArticlesDAO;
 
import dao.daoManager.ClientDAO;
 
import dao.daoManager.DepotDAO;
 
import dao.daoManager.MatierePremiereDAO;
 import dao.daoManager.StockPFDAO;
import dao.entity.Articles;
 import dao.entity.Client;
 
import dao.entity.Depot;
 
import dao.entity.DetailEstimation;
 
 
import dao.entity.Magasin;
import dao.entity.MatierePremier;
 
import dao.entity.StockPF;
import dao.entity.Utilisateur;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.toedter.calendar.JDateChooser;
import java.util.Locale;
import javax.swing.JRadioButton;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class ListeCodeMPParArticle extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private DefaultTableModel	 modele;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	
	 
	private DepotDAO depotDAO;
	 
	private JComboBox comboArticle = new JComboBox();

 
	private Map< String, Depot> mapDepot = new HashMap<>();
 
	private Map< String, Magasin> mapMagasin = new HashMap<>();
	private Map< String, Articles> mapArticlePF = new HashMap<>();
	private Map< String, Articles> mapCodeArticlePF = new HashMap<>();
	private Map< String, MatierePremier> mapMP = new HashMap<>();
	private Map< String, MatierePremier> mapCodeMP = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<Articles> listArticle =new ArrayList<Articles>();
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	 
	 
	private JLabel labelcodearticle;
	private Utilisateur utilisateur;
	private JXTable table;
	 JComboBox comboDepot = new JComboBox();
	 JComboBox comboMagasin = new JComboBox();
	 JComboBox comboClientPF = new JComboBox();
	 ArticlesDAO articlesDAO;
	 
	 JButton btnModifier = new JButton();
	 JButton btnSupprimer = new JButton();
	 JButton buttonvalider = new JButton("Valider");
	 JButton bttnRechercher = new JButton();
	 private ImageIcon imgModifierr;
	 private ImageIcon imgRechercher;
		private ImageIcon imgSupprimer;
		private ImageIcon imgExcel;
		private ImageIcon imgValider;
		 
		 
		 
		JComboBox combofamille = new JComboBox();
		JComboBox combosousfamille = new JComboBox();
		private StockPFDAO stockpfDAO;
		 
		private MatierePremiereDAO matierePremiereDAO;
	 
		private JTextField txtCodeArticle;
		JLabel labelcodeMP = new JLabel("Code MP");
		JLabel labelMP = new JLabel("MP:");
		JLabel labelArticle = new JLabel("Article:");
		JComboBox comboMP = new JComboBox();
		 
		JComboBox comboFournisseur = new JComboBox();
		JDateChooser dateimportation = new JDateChooser();
		 
		JLabel lblFamille = new JLabel("Famille :");
		JComboBox comboFamille = new JComboBox();
		JComboBox comboSousFamille = new JComboBox();
		 JLabel lblSousFamille = new JLabel("Sous Famille :");
		 
		
	/**
	 * Create the application.
	 */
	public ListeCodeMPParArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 734);
        try{
        	
        	 
        	depotDAO=new DepotDAOImpl();
        	  
        	 articlesDAO=new ArticlesDAOImpl();
        	 
        	 
        	 
           	stockpfDAO=new StockPFDAOImpl();
            
           	matierePremiereDAO=new MatierePremierDAOImpl();
           
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
       	 imgModifierr= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
       	imgRechercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider= new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
            
          } catch (Exception exp){exp.printStackTrace();}
        
        /*
        listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot.getCode());
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
	      		*/
        
   
	      	
	      	
	     final Utilisateur utilCreation=main.AuthentificationView.utilisateur;
        
				  		  btnAjouter = new JButton("Afficher");
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {  
				  		  		
				  		  		listArticle=articlesDAO.findAll();
				  		  		
				  		  		afficher_Detail(listArticle);
				  		  		
				  		  	}
				  		  });
				  		btnAjouter.setIcon(imgAjouter);
				  		 btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnAjouter.setBounds(389, 172, 114, 26);
				  		 add(btnAjouter);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(513, 172, 112, 26);
				  		 add(btnInitialiser);
				  		   			
				  		   			JScrollPane scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(29, 234, 1185, 415);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JXTable();
				  		   			table.addInputMethodListener(new InputMethodListener() {
				  		   				public void caretPositionChanged(InputMethodEvent arg0) {
				  		   				}
				  		   				public void inputMethodTextChanged(InputMethodEvent arg0) {
				  		   					
				  		   					
				  		   				 
				  		   					
				  		   				}
				  		   			});
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) { }
				  		   			});
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Code Article", "Libelle","Box", "Carton", "En Vrac", "Film Gold", "Film Normal"
				  		   				}
				  		   			));
				  		   			table.setFillsViewportHeight(true);
				  		   			scrollPane.setViewportView(table);
				  		   			
				  		   			
				  		   			
				  		   		 JXTitledSeparator titledSeparator1 = new JXTitledSeparator();
				  		  	 GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator1.getLayout();
				  		  	 gridBagLayout.rowWeights = new double[]{0.0};
				  		  	 gridBagLayout.rowHeights = new int[]{0};
				  		  	 gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		  	 gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		  	titledSeparator1.setTitle("Information Importation");
				  		  titledSeparator1.setBounds(10, 10, 1229, 24);
				  		  	 add(titledSeparator1);

				  		  	 JLayeredPane layeredPane1 = new JLayeredPane();
				  		  	layeredPane1.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		  layeredPane1.setBounds(10, 42, 1242, 91);
				  		  	 add(layeredPane1);


				  		   			
				  		   		
				  		 
				  		      
				  		      labelcodearticle = new JLabel("Code Article:");
				  		      labelcodearticle.setBounds(60, 25, 88, 26);
				  		      layeredPane1.add(labelcodearticle);
				  		      labelcodearticle.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		      
				  		        labelArticle = new JLabel("Article:");
				  		      labelArticle.setBounds(286, 25, 59, 26);
				  		      layeredPane1.add(labelArticle);
				  		      labelArticle.setFont(new Font("Times New Roman", Font.BOLD, 13));
				  		      comboArticle.setBounds(355, 25, 336, 26);
				  		      layeredPane1.add(comboArticle);
				  		      comboArticle.addItemListener(new ItemListener() {
				  		      	public void itemStateChanged(ItemEvent e) {
				  		      		 
					  		  	  	 if(e.getStateChange() == ItemEvent.SELECTED)
				  		  	 		 {
					  		  	  		 
					  		  	  		 if(!comboArticle.getSelectedItem().equals(""))
					  		  	  		 {
					  		  	  			 
					  		  	  			 Articles articles=mapArticlePF.get(comboArticle.getSelectedItem());
					  		  	  			 if(articles!=null)
					  		  	  			 {
					  		  	  				 txtCodeArticle.setText(articles.getCodeArticle());
					  		  	  			 }else
					  		  	  			 {
					  		  	  			txtCodeArticle.setText("");
					  		  	  			 }
					  		  	  			 
					  		  	  			   
					  		  	  		 }
					  		  	  		  
				  		  	 		 }
			 	  		      		
				  		      	}
				  		      });
				  		    
				 
	
	listArticle=articlesDAO.findAll();
	listMatierePremiere=matierePremiereDAO.findAll();
	
	txtCodeArticle = new JTextField();
	txtCodeArticle.setColumns(10);
	txtCodeArticle.setCaretColor(Color.RED);
	txtCodeArticle.setBackground(Color.WHITE);
	txtCodeArticle.setBounds(158, 25, 97, 26);
	layeredPane1.add(txtCodeArticle);
	
	comboArticle.addItem("");
	for(int i=0;i<listArticle.size();i++)
	{
		Articles articles=listArticle.get(i);
		comboArticle.addItem(articles.getLiblle());
		
		mapCodeArticlePF.put(articles.getCodeArticle(), articles);
		mapArticlePF.put(articles.getLiblle(), articles);
		
	}
	for(int i=0;i<listMatierePremiere.size();i++)
	{
		MatierePremier matierePremier=listMatierePremiere.get(i);
		comboMP.addItem(matierePremier.getNom());
		
		mapCodeMP.put(matierePremier.getCode(), matierePremier);
		mapMP.put(matierePremier.getNom(), matierePremier);
		
	}
			
			 txtCodeArticle.setText("");
			 comboArticle.setSelectedItem("");
			 
			 JButton btnExporterExcel = new JButton("Exporter Excel");
			 btnExporterExcel.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		

		     		

		    		 
		    		if(table.getRowCount()!=0)
		    		{
		    		
		    		String titre="Liste Code MP Par Article PF";
		    		String titrefeuille="Liste Code MP Par Article PF";
		    		ExporterTableVersExcel.tabletoexcelListeCodeMPParArticlePFF(table, titre,titrefeuille);
		    		
		    		}else
		    		{


		    			JOptionPane.showMessageDialog(null, "Veuillez Afficher Detail SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		
		    		
		    		}
		    	

			 		
			 	}
			 });
			 btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
			 btnExporterExcel.setBounds(511, 660, 141, 26);
			 btnExporterExcel.setIcon(imgExcel);
			 add(btnExporterExcel);
		 
		
	
	
	
	}
	
	

 

	
void intialiser (){
		  
		comboArticle.setSelectedItem("");
	txtCodeArticle.setText("");
		
	}


void afficher_Detail(List<Articles> listArticles)
{
	
	modele=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Code Article", "Libelle","Box", "Carton", "En Vrac", "Film Gold", "Film Normal"
			}
		){
		  	boolean[] columnEditables = new boolean[] {
		  			false, false,false,false,false,false,false
			  	};
		  	
			  	public boolean isCellEditable(int row, int column) {
			  		 
			  		return columnEditables[column];
			  	}
			  };
		setLayout(null);
	
	String box="";
	String carton="";
	String envrac="";
	String filmgold="";
	String filmnormal="";
		
int i=0;
	while(i<listArticles.size()){
		
		Articles articles=listArticles.get(i);
		
		  box="";
		  carton="";
		  envrac="";
		  filmgold="";
		  filmnormal="";
		Articles articlesTmp=mapArticlePF.get(comboArticle.getSelectedItem());
		  
		if(articles.getDetailEstimation().size()!=0)
		{
			for(int d=0;d<articles.getDetailEstimation().size();d++)
			{
				
				DetailEstimation detailEstimation=articles.getDetailEstimation().get(d);
				
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
				{
					String[] cartons = carton.split(" - ");
					boolean existe=false;
					for(int s=0;s<cartons.length;s++)
					{
						if(cartons[s].equals(detailEstimation.getMatierePremier().getCode()))
						{
							existe=true;
							
						}
					}
					
					if(existe==false)
					{
						carton=carton+" - "+detailEstimation.getMatierePremier().getCode();
					}
					
					
				}
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOITE) || detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_BOX_METALIQUE))
				{
					
					String[] boxs = box.split(" - ");
					boolean existe=false;
					for(int s=0;s<boxs.length;s++)
					{
						if(boxs[s].equals(detailEstimation.getMatierePremier().getCode()))
						{
							
								existe=true;
								
							
						}
					
					
				}
					if(existe==false)
					{
						box=box+" - "+detailEstimation.getMatierePremier().getCode();
					}
					
				}
				
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE) )
				{
					String[] envracs = envrac.split(" - ");
					
					boolean existe=false;
					for(int s=0;s<envracs.length;s++)
					{
						if(envracs[s].equals(detailEstimation.getMatierePremier().getCode()))
						{
							
								existe=true;
								
							
						}
					
					
				}
					if(existe==false)
					{
						envrac=envrac+" - "+detailEstimation.getMatierePremier().getCode();
					}
				 
					
				}
				
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_GOLD) )
				{
					String[] filmgolds = filmgold.split(" - ");
					boolean existe=false;
					for(int s=0;s<filmgolds.length;s++)
					{
						if(filmgolds[s].equals(detailEstimation.getMatierePremier().getCode()))
						{
							
								existe=true;
								
							
						}
					
					
				}
					if(existe==false)
					{
						filmgold=filmgold+" - "+detailEstimation.getMatierePremier().getCode();
					}
					
					
				}
				
				if(detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_FILM_NORMAL) )
				{
					
					String[] filmnormals = filmnormal.split(" - ");
					boolean existe=false;
					for(int s=0;s<filmnormals.length;s++)
					{
						if(filmnormals[s].equals(detailEstimation.getMatierePremier().getCode()))
						{
							
								existe=true;
								
							
						}
					
					
				}
					if(existe==false)
					{
						filmnormal=filmnormal+" - "+detailEstimation.getMatierePremier().getCode();
					}
					
					
				}
				
			}
			
			if(articlesTmp!=null)
			{
				if(articlesTmp.getId()==articles.getId())
				{
					Object []ligne={articles.getCodeArticle(),articles.getLiblle(),box,carton,envrac,filmgold,filmnormal};
				       modele.addRow(ligne);
				}
			}else
			{
				Object []ligne={articles.getCodeArticle(),articles.getLiblle(),box,carton,envrac,filmgold,filmnormal};
			       modele.addRow(ligne);
			}

				
			
		}
		
		
		 
		 
			 
		 
	
              
               
i++;
      
	}
	 table.setModel(modele); 

	
} 
}
