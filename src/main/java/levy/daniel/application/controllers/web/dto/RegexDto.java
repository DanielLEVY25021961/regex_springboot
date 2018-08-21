package levy.daniel.application.controllers.web.dto;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.regex.IOccurence;
import levy.daniel.application.model.services.metier.regex.IRegexService;

/**
 * CLASSE RegexDto :<br/>
 * .<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 21 août 2018
 *
 */
public class RegexDto {

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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(RegexDto.class);

	// *************************METHODES************************************/
	
	 /**
	 * .<br/>
	 * <br/>
	 *
	 */
	public RegexDto() {
		this(null, null, null, null, true, true, true, null, true, null);
	}
	
	
	 /**
	 * .<br/>
	 * <br/>
	 *
	 * @param pTexte
	 * @param pMotifJava
	 * @param pMotifJavaScript
	 * @param pSignificationMotif
	 * @param pMotifJavaRespecteSyntaxe
	 * @param pTexteCommenceParMotif
	 * @param pTexteContientMotif
	 * @param pListeOccurencesMotif
	 * @param pTexteMatcheMotif
	 * @param pListeOccurencesGroupesCapturant
	 */
	public RegexDto(String pTexte, String pMotifJava, String pMotifJavaScript, String pSignificationMotif,
			boolean pMotifJavaRespecteSyntaxe, boolean pTexteCommenceParMotif, boolean pTexteContientMotif,
			List<IOccurence> pListeOccurencesMotif, boolean pTexteMatcheMotif,
			List<IOccurence> pListeOccurencesGroupesCapturant) {
		super();
		this.texte = pTexte;
		this.motifJava = pMotifJava;
		this.motifJavaScript = pMotifJavaScript;
		this.significationMotif = pSignificationMotif;
		this.motifJavaRespecteSyntaxe = pMotifJavaRespecteSyntaxe;
		this.texteCommenceParMotif = pTexteCommenceParMotif;
		this.texteContientMotif = pTexteContientMotif;
		this.listeOccurencesMotif = pListeOccurencesMotif;
		this.texteMatcheMotif = pTexteMatcheMotif;
		this.listeOccurencesGroupesCapturant = pListeOccurencesGroupesCapturant;
	}

	
	 /**
	 * .<br/>
	 * <br/>
	 *
	 * @param pRegexService
	 */
	public RegexDto(IRegexService pRegexService) {
		
		this.texte = pRegexService.getTexte();
		this.motifJava = pRegexService.getMotifJava();
		this.motifJavaScript = pRegexService.getMotifJavaScript();
		this.significationMotif = pRegexService.getSignificationMotif();
		this.motifJavaRespecteSyntaxe = pRegexService.isMotifJavaRespecteSyntaxe();
		this.texteCommenceParMotif = pRegexService.isTexteCommenceParMotif();
		this.texteContientMotif = pRegexService.isTexteContientMotif();
		this.listeOccurencesMotif = pRegexService.getListeOccurencesMotif();
		this.texteMatcheMotif = pRegexService.isTexteMatcheMotif();
		this.listeOccurencesGroupesCapturant = pRegexService.getListeOccurencesGroupesCapturant();
		
	}
	
	
	
	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return texte : String : this.texte.<br/>
	 */
	public String getTexte() {
		return this.texte;
	}


	/**
	* .<br/>
	* <br/>
	*
	* @param pTexte : String : 
	* valeur à passer à this.texte.<br/>
	*/
	public void setTexte(String pTexte) {
		this.texte = pTexte;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return motifJava : String : this.motifJava.<br/>
	 */
	public String getMotifJava() {
		return this.motifJava;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pMotifJava : String : 
	* valeur à passer à this.motifJava.<br/>
	*/
	public void setMotifJava(String pMotifJava) {
		this.motifJava = pMotifJava;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return motifJavaScript : String : this.motifJavaScript.<br/>
	 */
	public String getMotifJavaScript() {
		return this.motifJavaScript;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pMotifJavaScript : String : 
	* valeur à passer à this.motifJavaScript.<br/>
	*/
	public void setMotifJavaScript(String pMotifJavaScript) {
		this.motifJavaScript = pMotifJavaScript;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return significationMotif : String : this.significationMotif.<br/>
	 */
	public String getSignificationMotif() {
		return this.significationMotif;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pSignificationMotif : String : 
	* valeur à passer à this.significationMotif.<br/>
	*/
	public void setSignificationMotif(String pSignificationMotif) {
		this.significationMotif = pSignificationMotif;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return motifJavaRespecteSyntaxe : boolean : this.motifJavaRespecteSyntaxe.<br/>
	 */
	public boolean isMotifJavaRespecteSyntaxe() {
		return this.motifJavaRespecteSyntaxe;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pMotifJavaRespecteSyntaxe : boolean : 
	* valeur à passer à this.motifJavaRespecteSyntaxe.<br/>
	*/
	public void setMotifJavaRespecteSyntaxe(boolean pMotifJavaRespecteSyntaxe) {
		this.motifJavaRespecteSyntaxe = pMotifJavaRespecteSyntaxe;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return texteCommenceParMotif : boolean : this.texteCommenceParMotif.<br/>
	 */
	public boolean isTexteCommenceParMotif() {
		return this.texteCommenceParMotif;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pTexteCommenceParMotif : boolean : 
	* valeur à passer à this.texteCommenceParMotif.<br/>
	*/
	public void setTexteCommenceParMotif(boolean pTexteCommenceParMotif) {
		this.texteCommenceParMotif = pTexteCommenceParMotif;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return texteContientMotif : boolean : this.texteContientMotif.<br/>
	 */
	public boolean isTexteContientMotif() {
		return this.texteContientMotif;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pTexteContientMotif : boolean : 
	* valeur à passer à this.texteContientMotif.<br/>
	*/
	public void setTexteContientMotif(boolean pTexteContientMotif) {
		this.texteContientMotif = pTexteContientMotif;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return listeOccurencesMotif : List<IOccurence> : this.listeOccurencesMotif.<br/>
	 */
	public List<IOccurence> getListeOccurencesMotif() {
		return this.listeOccurencesMotif;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pListeOccurencesMotif : List<IOccurence> : 
	* valeur à passer à this.listeOccurencesMotif.<br/>
	*/
	public void setListeOccurencesMotif(List<IOccurence> pListeOccurencesMotif) {
		this.listeOccurencesMotif = pListeOccurencesMotif;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return texteMatcheMotif : boolean : this.texteMatcheMotif.<br/>
	 */
	public boolean isTexteMatcheMotif() {
		return this.texteMatcheMotif;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pTexteMatcheMotif : boolean : 
	* valeur à passer à this.texteMatcheMotif.<br/>
	*/
	public void setTexteMatcheMotif(boolean pTexteMatcheMotif) {
		this.texteMatcheMotif = pTexteMatcheMotif;
	}

	/**
	 * Getter .<br/>
	 * <br/>
	 *
	 * @return listeOccurencesGroupesCapturant : List<IOccurence> : this.listeOccurencesGroupesCapturant.<br/>
	 */
	public List<IOccurence> getListeOccurencesGroupesCapturant() {
		return this.listeOccurencesGroupesCapturant;
	}

	/**
	* .<br/>
	* <br/>
	*
	* @param pListeOccurencesGroupesCapturant : List<IOccurence> : 
	* valeur à passer à this.listeOccurencesGroupesCapturant.<br/>
	*/
	public void setListeOccurencesGroupesCapturant(List<IOccurence> pListeOccurencesGroupesCapturant) {
		this.listeOccurencesGroupesCapturant = pListeOccurencesGroupesCapturant;
	}


	
	
	
	

}
