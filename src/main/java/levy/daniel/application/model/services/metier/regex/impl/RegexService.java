package levy.daniel.application.model.services.metier.regex.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.regex.IOccurence;
import levy.daniel.application.model.metier.regex.IRegex;
import levy.daniel.application.model.metier.regex.impl.Regex;
import levy.daniel.application.model.services.metier.regex.IRegexService;


/**
 * CLASSE RegexService :<br/>
 * Classe fournissant les <b>services</b> des 
 * Expressions Régulières (RegEx) en Java.<br/>
 * <br/>
 * Les fonctionnalités des Regex sont les suivantes :
 * <br/>
 * <br/>
 * <img src="../../../../../../../../../../../javadoc/images/fonctionnalités_Regex.png" 
 * alt="fonctionnalités des Regex" border="1" align="center" />
 * <br/>
 * <br/>
 * <img src="../../../../../../../../../../../javadoc/images/fonctionnalités_edition.png" 
 * alt="fonctionnalités d'édition des Regex" border="1" align="center" />
 * <br/><br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 20 août 2018
 *
 */
public class RegexService implements IRegexService {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");

	/**
	 * chaine de caractères (éventuellement multi-lignes) 
	 * dont on veut savoir si elle contient ou matche le motif 
	 * RegEx Java de l'expression régulière.<br/>
	 */
	private String texte;
	
	/**
	 * Motif de l'expression régulière applicable en Java.<br/>
	 * Par exemple : "[0-9]" ou "\\d" en java pour un chiffre. 
	 */
	private String motifJava;
	
	/**
	 * Motif de l'expression régulière applicable en JavaScript.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 * Par exemple : 
	 */
	private transient String motifJavaScript;
	
	/**
	 * Signification du motif de l'expression régulière.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 * Par exemple : 
	 */
	private transient String significationMotif;
	
	/**
	 * boolean qui stipule si <code>this.motifJava</code> 
	 * <b>respecte la syntaxe</b> des expressions régulières 
	 * (RegEx) Java.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 */
	private transient boolean motifJavaRespecteSyntaxe;
	
	/**
	 * boolean qui stipule si <code>this.texte</code> 
	 * <b>commence</b> par <code>this.motifJava</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 */
	private transient boolean texteCommenceParMotif;
	
	/**
	 * boolean qui stipule si <code>this.texte</code> 
	 * <b>contient</b> <code>this.motifJava</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 */
	private transient boolean texteContientMotif;
	
	/**
	 * Liste des occurences de <code>this.motifJava</code> 
	 * dans <code>this.texte</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 */
	private transient List<IOccurence> listeOccurencesMotif;
	
	/**
	 * boolean qui stipule si <code>this.texte</code> 
	 * <b>matche entièrement</b> avec <code>this.motifJava</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 */
	private transient boolean texteMatcheMotif;
	
	/**
	 * Liste des occurences des <b>sous-groupes capturant</b> de 
	 * <code>this.motifJava</code> 
	 * dans <code>this.texte</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 */
	private transient List<IOccurence> listeOccurencesGroupesCapturant;
	
	/**
	 * METIER Regex.<br/>
	 */
	private IRegex regex;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(RegexService.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <ul>
	 * <li>instancie le METIER Regex.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 * si il n'est pas possible d'instancier un Regex.<br/>
	 */
	public RegexService() throws Exception {
		
		super();
		
		/* instancie le METIER Regex. */
		this.regex = new Regex();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>rafraîchit tous les attributs calculés</b>.<br/>
	 * <ul>
	 * <li>délègue au METIER (Regex) le rafraîchissement 
	 * de tous les attributs calculés.</li>
	 * <li>tous les <b>attributs calculés : </b><br/>
	 * <code>
	 * <ul> 
	 * <br/>
	 * <li>this.motifJavaRespecteSyntaxe</li>
	 * <li>this.texteCommenceParMotif,</li>
	 * <li>this.texteContientMotif,</li> 
	 * <li>this.listeOccurencesMotif,</li>
	 * <li>this.texteMatcheMotif,</li> 
	 * <li>this.listeOccurencesGroupesCapturant</li>
	 * </ul> 
	 * </code>
	 * <b>sont automatiquement mis à jour par le METIER 
	 * après appel de cette méthode</b>.</li>
	 * </ul>
	 * <br/>
	 * 
	 *@throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	private void rafraichirAttributsCalcules() throws Exception {
		
		this.motifJavaRespecteSyntaxe 
			= this.regex.isMotifJavaRespecteSyntaxe();
		this.texteCommenceParMotif 
		= this.regex.texteCommenceParMotif();
		this.texteContientMotif 
			= this.regex.texteContientMotif();
		this.listeOccurencesMotif 
			= this.regex.getListeOccurencesMotif();
		this.texteMatcheMotif 
			= this.regex.texteCorrespondEntierementAMotif();
		this.listeOccurencesGroupesCapturant 
			= this.regex.getListeOccurencesGroupesCapturant();
		
	} // Fin de rafraichirAttributsCalcules()._____________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListOccurences(
			final List<IOccurence> pList)	{
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IOccurence occurence : pList) {
			stb.append(occurence.toString());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherListOccurences(...)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTexte() {
		return this.texte;
	} // Fin de getTexte().________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTexte(
			final String pTexte) throws Exception {
		
		/* ne fait rien si this.texte == pTexte. */
		if (StringUtils.equals(this.texte, pTexte)) {
			return;
		}
		
		/* ne fait rien si this.regex est null. */
		if (this.regex != null) {
			
			/* passe le paramètre à l'attribut. */
			this.texte = pTexte;
			
			/* passe l'attribut au METIER Regex. */
			this.regex.setChaineATester(this.texte);
			
			/* délègue au métier le rafraîchissement 
			 * de tous les attributs calculés. */
			this.rafraichirAttributsCalcules();
			
		}
				
	} // Fin de setTexte(...)._____________________________________________
	

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMotifJava() {
		return this.motifJava;
	} // Fin de getMotifJava().____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMotifJava(
			final String pMotifJava) throws Exception {
		
		/* ne fait rien si this.motifJava == pMotifJava. */
		if (StringUtils.equals(this.motifJava, pMotifJava)) {
			return;
		}
		
		/* ne fait rien si this.regex == null. */
		if (this.regex != null) {
			
			/* passe le paramètre à l'attribut. */
			this.motifJava = pMotifJava;
			
			/* passe l'attribut au METIER Regex. */
			this.regex.setMotifJava(this.motifJava);
			
			/* délègue au métier le rafraîchissement 
			 * de tous les attributs calculés. */
			this.rafraichirAttributsCalcules();
			
		}
		
	} // Fin de setMotifJava(...)._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMotifJavaScript() {
		return this.motifJavaScript;
	} // Fin de getMotifJavaScript().______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSignificationMotif() {
		return this.significationMotif;
	} // Fin de getSignificationMotif().___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMotifJavaRespecteSyntaxe() {
		return this.motifJavaRespecteSyntaxe;
	} // Fin de isMotifJavaRespecteSyntaxe().______________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTexteCommenceParMotif() {
		return this.texteCommenceParMotif;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTexteContientMotif() {
		return this.texteContientMotif;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IOccurence> getListeOccurencesMotif() {
		return this.listeOccurencesMotif;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTexteMatcheMotif() {
		return this.texteMatcheMotif;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IOccurence> getListeOccurencesGroupesCapturant() {
		return this.listeOccurencesGroupesCapturant;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IRegex getRegex() {
		return this.regex;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRegex(
			final IRegex pRegex) {
		this.regex = pRegex;
	}
	
	
		
} // FIN DE LA CLASSE RegexService.------------------------------------------
