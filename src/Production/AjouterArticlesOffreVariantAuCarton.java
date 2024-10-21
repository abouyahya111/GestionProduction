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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main.Main;
import main.ProdLauncher;

import org.jdesktop.swingx.JXTable;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DetailNombreCartonOffreVariantDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.NombreCartonOffreVariantDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DetailNombreCartonOfferVariantDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.NombreCartonOfferVariantDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.CoutMP;
import dao.entity.DetailNombreCartonOffreVariante;
import dao.entity.DetailProduction;
import dao.entity.Employe;
import dao.entity.MatierePremier;
import dao.entity.NombreCartonOffreVariante;
import dao.entity.Production;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AjouterArticlesOffreVariantAuCarton extends JFrame implements Constantes{
	
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleEmploye;
	private DefaultTableModel	 modeleDetailNombreCartonOffreVariante;
	private JXTable  tableEmploye=new JXTable();
	
	private JXTable tableNombreCarton;
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupp1;
	
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnValiderDelai;
	private JButton btnRechercher;
	

	
	private Production production = new Production();
	private List<Employe> listEmployer =new ArrayList<Employe>();
	private List<DetailProduction> listDetailProduction =new ArrayList<DetailProduction>();
	private List<NombreCartonOffreVariante> listNombreCartonOffre =new ArrayList<NombreCartonOffreVariante>();
	private List<DetailNombreCartonOffreVariante> listDetailNombreCartonOffre =new ArrayList<DetailNombreCartonOffreVariante>();
	
	
	private Map< String, MatierePremier> mapmatierePremier = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp25 = new HashMap<>();
	private Map< String, BigDecimal> mapHeureSupp50 = new HashMap<>();
	private Map< String,Boolean> mapEmployeAbsent = new HashMap<>();
	private Map< String,Boolean> mapEmployeSortie = new HashMap<>();
	
	private Map< Integer, Employe> mapEmployeDelai = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp25 = new HashMap<>();
	private Map< Integer, Employe> mapEmployeHeureSupp50 = new HashMap<>();
	
	private Map< String, Employe> mapEmployeGlobal = new HashMap<>();
	
	DetailNombreCartonOfferVariantDAO detailNombreCartonOfferVariantDAO;
	NombreCartonOfferVariantDAO nombreCartonOfferVariantDAO;
	
	 JComboBox comboMP = new JComboBox();
	
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	
	private EmployeDAO employeDAO;
	private ProductionDAO productionDAO;
	private CoutMPDAO coutMPDAO;
	private JLabel lblNumDossier;
	private JTextField txtNombreCarton;
	
	private int selectedRow ;
	
	private int compteur=0;
	String quantite;
	String nbreHeure;
	private JLabel lblEmployesProduction;
	private JTable tableDetailNombreCarton =new JXTable();;
	private JTextField txtQuantiteEstime;
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	private JButton button;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public AjouterArticlesOffreVariantAuCarton(final Production productionParame, final String quantiteParam, final String nbreHeureParam) {
		
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1058, 716);
        try {
        	javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try{
        	
        	tableNombreCarton=new JXTable();
        	tableNombreCarton.addMouseListener(new MouseAdapter() {
        		@Override
        		public void mouseClicked(MouseEvent arg0) {
        			
        			if(tableNombreCarton.getSelectedRow()!=-1)
        			{
        			
        				NombreCartonOffreVariante nombreCartonOffreVariante=listNombreCartonOffre.get(tableNombreCarton.getSelectedRow());
        				txtNombreCarton.setText(nombreCartonOffreVariante.getNombreCarton().toString());
        				listDetailNombreCartonOffre=detailNombreCartonOfferVariantDAO.findDetailNombreCartonOffreVarianteByNombreCartonOffreVariant(nombreCartonOffreVariante);
        				afficher_tableDetailNombreCartonOffreVariant(listDetailNombreCartonOffre);
        				
        				 
        			}
        			
        			
        			
        		}
        	});
        	   DefaultCellEditor ce = (DefaultCellEditor) tableNombreCarton.getDefaultEditor(Object.class);
		        JTextComponent textField = (JTextComponent) ce.getComponent();
		        util.Utils.copycollercell(textField);
        	compteur=0;
        	
        	
        	listEmployer =new ArrayList<Employe>();
        	
        	tableEmploye=new JXTable();
        	

        	production=productionParame;
        	quantite=quantiteParam;
        	nbreHeure= nbreHeureParam ;
        	coutMPDAO=new CoutMPDAOImpl();
         
        	productionDAO= new ProductionDAOImpl();
        	detailNombreCartonOfferVariantDAO=new DetailNombreCartonOffreVariantDAOImpl();
        	nombreCartonOfferVariantDAO=new NombreCartonOffreVariantDAOImpl();
        	/*compteurAbsenceEmployeDAO=ProdLauncher.compteurAbsenceEmployeDAO;
        	ficheEmployeDAO=ProdLauncher.ficheEmployeDAO;*/
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
       System.exit(0);
}
		

        initialiserTableauEmploye();
        initialiserTableauDetailNombreCartonOffreVariant();
       
		
             listCoutMP= productionDAO.listeCoutMP(productionParame.getId());
             
		     
		    
				  		 
				  		  
				  		/*##################################################################
				  		     * FIN AJOUT LA LSITES DES PERSONNES A LA LISTE D'AFFICHAGE 
				  		    *###################################################################*/
				  		     getContentPane().setLayout(null);
				  		 
				  		     
				  		     JScrollPane scrollPane = new JScrollPane(tableNombreCarton);
				  		     scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane.setBounds(10, 113, 891, 199);
				  		     getContentPane().add(scrollPane);
				  		     
				  		     JButton btnValider = new JButton();
				  		     btnValider.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		 
 
				  		     		Main.contentPane=new TerminerOrdreFabrication(production,quantite,nbreHeure);
				  		     		
				  		     		Main.generalScrollPane.setViewportView(Main.contentPane);
				  		     		Main.contentPane.setOpaque(true);		
				  		     		//terminerorderfabrication.productioon
				  		     		//terminerorderfabrication.txtNumOF.setText("ghghghg");
				  		     		dispose();
				  		     		
				  		     		 
				  		     	
				  		     	}
				  		     });
				  		     btnValider.setText("Valider");
				  		   btnValider.setIcon(null);
				  		   btnValider.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     btnValider.setBounds(365, 626, 114, 40);
				  		     getContentPane().add(btnValider);
				  		     
				  		     lblNumDossier = new JLabel("Nombre Carton");
				  		     lblNumDossier.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		     lblNumDossier.setBounds(10, 51, 122, 24);
				  		     getContentPane().add(lblNumDossier);
				  		     
				  		   txtNombreCarton=new JTextField();
				  		 util.Utils.copycoller(txtNombreCarton);
				  		   txtNombreCarton.addKeyListener(new KeyAdapter() {
				  		   	@Override
				  		   	public void keyPressed(KeyEvent arg0) {
				  		   		


		  		     			
				  		   	}
				  		   });
				  		     txtNombreCarton.setBounds(187, 52, 158, 24);
				  		     getContentPane().add(txtNombreCarton);
				  		     txtNombreCarton.setColumns(10);
				  		    
				  		   
				  		     
				  		     txtNombreCarton.setBounds(114, 51, 189, 24);
				  		     getContentPane().add(txtNombreCarton);
				  		     txtNombreCarton.setColumns(10);
				  		     
				  		     lblEmployesProduction = new JLabel("Nombre Carton");
				  		     lblEmployesProduction.setForeground(Color.BLUE);
				  		     lblEmployesProduction.setFont(new Font("Tahoma", Font.BOLD, 15));
				  		     lblEmployesProduction.setBounds(10, 11, 241, 24);
				  		     getContentPane().add(lblEmployesProduction);
				  		     
				  		     JButton btnAjouter = new JButton();
				  		     btnAjouter.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent arg0) {
				  		     		if(!txtNombreCarton.getText().equals(""))
				  		     		{
				  		     			NombreCartonOffreVariante nombreCartonOffreVariante=new NombreCartonOffreVariante();
				  		     			nombreCartonOffreVariante.setProduction(productionParame);
				  		     			nombreCartonOffreVariante.setNombreCarton(new BigDecimal(txtNombreCarton.getText()));
				  		     		 nombreCartonOfferVariantDAO.add(nombreCartonOffreVariante);
				  		     			listNombreCartonOffre.add(nombreCartonOffreVariante);
				  		     			afficher_tableEmploye(listNombreCartonOffre);
				  		     			ViderNombreCartonOffreVariante();
				  		     		}
				  		     		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     btnAjouter.setText("Ajouter");
				  		     btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
				  		     btnAjouter.setBounds(386, 47, 114, 32);
				  		     getContentPane().add(btnAjouter);
				  		     
				  		     JScrollPane scrollPane_1 = new JScrollPane((Component) null);
				  		     scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     scrollPane_1.setBounds(10, 411, 891, 199);
				  		     getContentPane().add(scrollPane_1);
				  		     
				  		     tableDetailNombreCarton = new JTable();
				  		     tableDetailNombreCarton.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		

				        			
				        			if(tableDetailNombreCarton.getSelectedRow()!=-1)
				        			{
				        			
				        				DetailNombreCartonOffreVariante detailnombreCartonOffreVariante=listDetailNombreCartonOffre.get(tableDetailNombreCarton.getSelectedRow());
				        				 txtQuantiteEstime.setText(detailnombreCartonOffreVariante.getEstimation().toString());
				        				 comboMP.setSelectedItem(detailnombreCartonOffreVariante.getMatierePremier().getNom());
				        				 
				        			 
				        				
				        				 
				        			}
				        			
				        			
				        			
				        		
				  		     		
				  		     		
				  		     	}
				  		     });
				  		     tableDetailNombreCarton.setModel(new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     		"Nombre Carton", "Code MP", "MP", "Estimation"
				  		     	}
				  		     ));
				  		     tableDetailNombreCarton.setFillsViewportHeight(true);
				  		     scrollPane_1.setViewportView(tableDetailNombreCarton);
				  		     
				  		     JLabel lblArticleOffre = new JLabel("Article OFFre");
				  		     lblArticleOffre.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		     lblArticleOffre.setBounds(20, 333, 77, 24);
				  		     getContentPane().add(lblArticleOffre);
				  		     
				  		       comboMP = new JComboBox();
				  		     comboMP.setBounds(107, 335, 285, 24);
				  		     getContentPane().add(comboMP);
				  		     
				  		     JLabel lblEstimation = new JLabel("Estimation");
				  		     lblEstimation.setFont(new Font("Tahoma", Font.BOLD, 12));
				  		     lblEstimation.setBounds(426, 333, 88, 24);
				  		     getContentPane().add(lblEstimation);
				  		     
				  		     txtQuantiteEstime = new JTextField();
				  		     txtQuantiteEstime.setColumns(10);
				  		     txtQuantiteEstime.setBounds(524, 333, 154, 24);
				  		     getContentPane().add(txtQuantiteEstime);
	
				  		     
				  		    
				comboMP.addItem("");
				
				button = new JButton();
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(!comboMP.getSelectedItem().equals("") && !txtQuantiteEstime.getText().equals(""))
						{
							try {
								
								
								if(new BigDecimal(txtQuantiteEstime.getText()).compareTo(BigDecimal.ZERO)<= 0)
								{
									JOptionPane.showMessageDialog(null, "la Quantite Doit Etre Superieur a 0");
									return;
								}
								
								
						MatierePremier matierePremier=mapmatierePremier.get(comboMP.getSelectedItem().toString());
						if(matierePremier==null)
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'Article SVP !!!");
							return;
							
						}
						
						NombreCartonOffreVariante nombreCartonOffreVariante=listNombreCartonOffre.get(tableNombreCarton.getSelectedRow());
						if(nombreCartonOffreVariante==null)
						{
							JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Nombre De Carton SVP !!!");
							return;
						}
						
								
							DetailNombreCartonOffreVariante detailNombreCartonOffreVariante=new DetailNombreCartonOffreVariante();	
							detailNombreCartonOffreVariante.setEstimation(new BigDecimal(txtQuantiteEstime.getText()));
							detailNombreCartonOffreVariante.setMatierePremier(matierePremier);
							detailNombreCartonOffreVariante.setNombreCartonOffreVariant(nombreCartonOffreVariante);
							detailNombreCartonOfferVariantDAO.add(detailNombreCartonOffreVariante);
							listDetailNombreCartonOffre.add(detailNombreCartonOffreVariante);
							afficher_tableDetailNombreCartonOffreVariant(listDetailNombreCartonOffre);
							ViderDetailNombreCartonOffreVariante();
								
								
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "La Quantite Doit Etre en chiffre SVP");
								return;
							}
							
							
							
							
							
						}
						
						
						
						
					}
				});
				button.setText("Ajouter");
				button.setFont(new Font("Tahoma", Font.BOLD, 13));
				button.setBounds(714, 329, 114, 32);
				getContentPane().add(button);
				
				btnModifier = new JButton();
				btnModifier.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						if(tableNombreCarton.getSelectedRow()!=-1)
	        			{
	        			
	        				NombreCartonOffreVariante nombreCartonOffreVariante=listNombreCartonOffre.get(tableNombreCarton.getSelectedRow());
	        				
	        				 if(!txtNombreCarton.getText().equals(""))
	        				 {
	        					 try {
									
	        						 
	        						 nombreCartonOffreVariante.setNombreCarton(new BigDecimal(txtNombreCarton.getText())); 
		        					 nombreCartonOfferVariantDAO.edit(nombreCartonOffreVariante);
		        					 listNombreCartonOffre.set(tableNombreCarton.getSelectedRow(), nombreCartonOffreVariante);
 		        					 afficher_tableEmploye(listNombreCartonOffre);
		        					 initialiserTableauDetailNombreCartonOffreVariant();
	        						 
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(null, "Nombre de Carton Doit Etre En Chiffre SVP");
									return;
								}
	        					
	        				 }
	        				
	        				 
	        			}else
	        			{
	        				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Nombre De carton SVP");
	        				return;
	        			}
						
						
						
					}
				});
				btnModifier.setText("Modifier");
				btnModifier.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnModifier.setBounds(911, 146, 114, 32);
				getContentPane().add(btnModifier);
				
				btnSupprimer = new JButton();
				btnSupprimer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						if(tableNombreCarton.getSelectedRow()!=-1)
	        			{
							
							
	        			NombreCartonOffreVariante nombreCartonOffreVariante=listNombreCartonOffre.get(tableNombreCarton.getSelectedRow());
	        				detailNombreCartonOfferVariantDAO.deleteDetailNombrecartonOffreVarianteByNombreCartonOffreVariante(nombreCartonOffreVariante);
	        				nombreCartonOfferVariantDAO.delete(nombreCartonOffreVariante.getId());
listNombreCartonOffre.remove(tableNombreCarton.getSelectedRow());

afficher_tableEmploye(listNombreCartonOffre);
initialiserTableauDetailNombreCartonOffreVariant();
ViderNombreCartonOffreVariante();

	        				
	        				 
	        			}else
	        			{
	        				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Nombre De carton SVP");
	        				return;
	        			}
						
						
						
						
					}
				});
				btnSupprimer.setText("Supprimer");
				btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnSupprimer.setBounds(911, 196, 114, 32);
				getContentPane().add(btnSupprimer);
				
				button_1 = new JButton();
				button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
 
						
						if(tableDetailNombreCarton.getSelectedRow()!=-1)
	        			{
	        			 
							
							
							if(!comboMP.getSelectedItem().equals("") && !txtQuantiteEstime.getText().equals(""))
							{
								try {
									
									
									if(new BigDecimal(txtQuantiteEstime.getText()).compareTo(BigDecimal.ZERO)<= 0)
									{
										JOptionPane.showMessageDialog(null, "la Quantite Doit Etre Superieur a 0");
										return;
									}
									
									
							MatierePremier matierePremier=mapmatierePremier.get(comboMP.getSelectedItem().toString());
							if(matierePremier==null)
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner l'Article SVP !!!");
								return;
								
							}
							
							DetailNombreCartonOffreVariante detailNombreCartonOffreVariante=listDetailNombreCartonOffre.get(tableDetailNombreCarton.getSelectedRow());
							if(detailNombreCartonOffreVariante==null)
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner Detail Nombre De Carton SVP !!!");
								return;
							}
							
									
								 
								detailNombreCartonOffreVariante.setEstimation(new BigDecimal(txtQuantiteEstime.getText()));
								detailNombreCartonOffreVariante.setMatierePremier(matierePremier);
						 detailNombreCartonOfferVariantDAO.edit(detailNombreCartonOffreVariante);
								listDetailNombreCartonOffre.set(tableDetailNombreCarton.getSelectedRow(),detailNombreCartonOffreVariante);
								
								afficher_tableDetailNombreCartonOffreVariant(listDetailNombreCartonOffre);
								ViderDetailNombreCartonOffreVariante();
									
									
								} catch (NumberFormatException ex) {
									JOptionPane.showMessageDialog(null, "La Quantite Doit Etre en chiffre SVP");
									return;
								}
								
								
								
								
								
							}
							
							
							
	        				
	        				 
	        			}else
	        			{
	        				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Nombre De carton SVP");
	        				return;
	        			}
						
						
						
					
						
						
						
					}
				});
				button_1.setText("Modifier");
				button_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				button_1.setBounds(911, 430, 114, 32);
				getContentPane().add(button_1);
				
				button_2 = new JButton();
				button_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						

						
 
						
						if(tableDetailNombreCarton.getSelectedRow()!=-1)
	        			{
	        			 
							
					 
							
							DetailNombreCartonOffreVariante detailNombreCartonOffreVariante=listDetailNombreCartonOffre.get(tableDetailNombreCarton.getSelectedRow());
							if(detailNombreCartonOffreVariante==null)
							{
								JOptionPane.showMessageDialog(null, "Veuillez Selectionner Detail Nombre De Carton SVP !!!");
								return;
							}
							
								 detailNombreCartonOfferVariantDAO.delete(detailNombreCartonOffreVariante.getId());
								listDetailNombreCartonOffre.remove(tableDetailNombreCarton.getSelectedRow());								 								 								
								afficher_tableDetailNombreCartonOffreVariant(listDetailNombreCartonOffre);
								ViderDetailNombreCartonOffreVariante();
									
								  
							
	        				
	        				 
	        			}else
	        			{
	        				JOptionPane.showMessageDialog(null, "Veuillez Selectionner le Nombre De carton SVP");
	        				return;
	        			}
						
						
						
					
						
						
						
					
						
						
						
					}
				});
				button_2.setText("Supprimer");
				button_2.setFont(new Font("Tahoma", Font.BOLD, 13));
				button_2.setBounds(911, 480, 114, 32);
				getContentPane().add(button_2);
				
				JButton btnVider = new JButton();
				btnVider.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ViderDetailNombreCartonOffreVariante();
					}
				});
				btnVider.setText("Vider");
				btnVider.setFont(new Font("Tahoma", Font.BOLD, 13));
				btnVider.setBounds(838, 329, 114, 32);
				getContentPane().add(btnVider);
				
				button_3 = new JButton();
				button_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ViderNombreCartonOffreVariante();
					}
				});
				button_3.setText("Vider");
				button_3.setFont(new Font("Tahoma", Font.BOLD, 13));
				button_3.setBounds(526, 47, 114, 32);
				getContentPane().add(button_3);
				for(int i=0;i<listCoutMP.size();i++)
				{
					CoutMP coutMP=listCoutMP.get(i);
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						
{
						
						
						comboMP.addItem(coutMP.getMatierePremier().getNom());	
						mapmatierePremier.put(coutMP.getMatierePremier().getNom(), coutMP.getMatierePremier());
	
}
					
					
				}
				
			listNombreCartonOffre=nombreCartonOfferVariantDAO.findByProduction(productionParame);	
				
		afficher_tableEmploye(listNombreCartonOffre);		
				
				  		     
				  		     
				  		     
				}
	
 

void afficher_tableEmploye(List<NombreCartonOffreVariante> listNombreCartonOffreVariante)
	{
	initialiserTableauEmploye();	
	 
		
		  int i=0;
			while(i<listNombreCartonOffreVariante.size())
			{	
				NombreCartonOffreVariante NombreCartonOffreVariante=listNombreCartonOffreVariante.get(i);
				
				//mapEmployeGlobal.put(employer.getNumDossier(), employer);
			 
				
				Object []ligne={NombreCartonOffreVariante.getId(), NombreCartonOffreVariante.getNombreCarton() };

				modeleEmploye.addRow(ligne);
				i++;
			}
		}


void afficher_tableDetailNombreCartonOffreVariant(List<DetailNombreCartonOffreVariante> listDetailNombreCartonOffreVariante)
{
initialiserTableauDetailNombreCartonOffreVariant();;
 
	
	  int i=0;
		while(i<listDetailNombreCartonOffreVariante.size())
		{	
			DetailNombreCartonOffreVariante DetailNombreCartonOffreVariante=listDetailNombreCartonOffreVariante.get(i);
			
			//mapEmployeGlobal.put(employer.getNumDossier(), employer);
		 
			
			Object []ligne={DetailNombreCartonOffreVariante.getNombreCartonOffreVariant().getNombreCarton(), DetailNombreCartonOffreVariante.getMatierePremier().getCode(),DetailNombreCartonOffreVariante.getMatierePremier().getNom(),DetailNombreCartonOffreVariante.getEstimation() };

			modeleDetailNombreCartonOffreVariante.addRow(ligne);
			i++;
		}
	}

void ViderDetailNombreCartonOffreVariante()
{
	txtQuantiteEstime.setText("");
	comboMP.setSelectedItem("");
}

void ViderNombreCartonOffreVariante()
{
	txtNombreCarton.setText("");
}

void initialiserTableauEmploye(){
	

	 modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Nombre Carton"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false ,false
		     	};
		    
		     	 
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		
		     tableNombreCarton.setModel(modeleEmploye);
		  	tableNombreCarton.getColumnModel().getColumn(0).setPreferredWidth(60);
		  	tableNombreCarton.getColumnModel().getColumn(1).setPreferredWidth(60);
		     
		     
					
				
		  				
		    
}


void initialiserTableauDetailNombreCartonOffreVariant(){
	

	modeleDetailNombreCartonOffreVariante =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Nombre Carton","Code MP", "MP","Estimation"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false ,false,	false ,false
		     	};
		    
		     	 
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		
		     tableDetailNombreCarton.setModel(modeleDetailNombreCartonOffreVariante);
		     tableDetailNombreCarton.getColumnModel().getColumn(0).setPreferredWidth(60);
		     tableDetailNombreCarton.getColumnModel().getColumn(1).setPreferredWidth(60);
		     tableDetailNombreCarton.getColumnModel().getColumn(2).setPreferredWidth(60);
		     tableDetailNombreCarton.getColumnModel().getColumn(3).setPreferredWidth(60);
		     
		     
					
				
		  				
		    
}
}
