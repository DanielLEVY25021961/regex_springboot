package levy.daniel.application.controllers.web.dto;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE RequeteDto :<br/>
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
public class RequeteDto {

	// ************************ATTRIBUTS************************************/
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(RequeteDto.class);

	// *************************METHODES************************************/
	
	 /**
	 * .<br/>
	 * <br/>
	 *
	 */
	public RequeteDto() {
		this(null, null);
	}
	
	
	
	 /**
	 * .<br/>
	 * <br/>
	 *
	 * @param pTexte
	 * @param pMotifJava
	 */
	public RequeteDto(String pTexte, String pMotifJava) {
		super();
		this.texte = pTexte;
		this.motifJava = pMotifJava;
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



}
