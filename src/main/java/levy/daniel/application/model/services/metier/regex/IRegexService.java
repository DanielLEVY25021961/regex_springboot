package levy.daniel.application.model.services.metier.regex;

import java.util.List;

import levy.daniel.application.model.metier.regex.IOccurence;
import levy.daniel.application.model.metier.regex.IRegex;

/**
 * INTERFACE <b>IRegexService</b> :<br/>
 * Interface factorisant les comportements des RegexService.<br/>
 * <br/>
 * <br/>
 * Les fonctionnalités des Regex sont les suivantes :
 * <br/>
 * <br/>
 * <img src="../../../../../../../../../../javadoc/images/fonctionnalités_Regex.png" 
 * alt="fonctionnalités des Regex" border="1" align="center" />
 * <br/>
 * <br/>
 * <img src="../../../../../../../../../../javadoc/images/fonctionnalités_edition.png" 
 * alt="fonctionnalités d'édition des Regex" border="1" align="center" />
 * <br/><br/>
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
public interface IRegexService {

	
	
	/**
	 * retourne une String pour l'affichage 
	 * d'une List&lt;IOccurence&gt;.<br/>
	 * <ul>
	 * <li>retourne une String vide si pList est vide.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;IOccurence&gt; : 
	 * Liste {@link IOccurence}.
	 * 
	 * @return : String : String pour affichage.<br/>
	 */
	String afficherListOccurences(List<IOccurence> pList);
	

	
	/**
	 * Getter de la chaine de caractères (éventuellement multi-lignes) 
	 * dont on veut savoir si elle contient ou matche le motif 
	 * RegEx Java de l'expression régulière.<br/>
	 *
	 * @return this.texte : String.<br/>
	 */
	String getTexte();



	/**
	* Setter de la chaine de caractères (éventuellement multi-lignes) 
	* dont on veut savoir si elle contient ou matche le motif 
	* RegEx Java de l'expression régulière.<br/>
	* <ul>
	* <li>délègue au METIER (Regex) le rafraîchissement 
	* de tous les attributs calculés.</li>
	* <li>tous les <b>attributs calculés : </b><br/>
	* <code>
	* <ul> 
	* <br/>
	* <li>this.texteCommenceParMotif,</li>
	* <li>this.texteContientMotif,</li> 
	* <li>this.listeOccurencesMotif,</li>
	* <li>this.texteMatcheMotif,</li> 
	* <li>this.listeOccurencesGroupesCapturant</li>
	* </ul> 
	* </code>
	* <b>sont automatiquement mis à jour par le METIER 
	* après appel de ce Setter</b>.</li>
	* <br/>
	* <ul>
	* <li>ne fait rien si this.texte == pTexte.</li>
	* <li>passe le paramètre à l'attribut.</li>
	* <li>passe l'attribut au METIER Regex.</li>
	* <li>délègue au métier le rafraîchissement 
	* de tous les attributs calculés.</li>
	* </ul>
	* </ul>
	* - ne fait rien si <code>this.regex</code> est null.<br/>
	* <br/>
	*
	* @param pTexte : String : 
	* valeur à passer à <code>this.texte</code>.<br/>
	* 
	* @throws Exception si le pattern <code>this.motifJava</code> 
	* n'est pas conforme à la syntaxe des Regex Java.<br/>
	*/
	void setTexte(String pTexte) throws Exception;



	/**
	 * Getter du Motif de l'expression régulière applicable en Java.<br/>
	 * Par exemple : "[0-9]" ou "\\d" en java pour un chiffre.
	 *
	 * @return this.motifJava : String.<br/>
	 */
	String getMotifJava();



	/**
	* Setter du Motif de l'expression régulière applicable en Java.<br/>
	* Par exemple : "[0-9]" ou "\\d" en java pour un chiffre.
	* <ul>
	* <li>délègue au METIER (Regex) le rafraîchissement 
	* de tous les attributs calculés.</li>
	* <li>tous les <b>attributs calculés : </b><br/>
	* <code>
	* <ul> 
	* <br/>
	* <li>this.texteCommenceParMotif,</li>
	* <li>this.texteContientMotif,</li> 
	* <li>this.listeOccurencesMotif,</li>
	* <li>this.texteMatcheMotif,</li> 
	* <li>this.listeOccurencesGroupesCapturant</li>
	* </ul> 
	* </code>
	* <b>sont automatiquement mis à jour par le METIER 
	* après appel de ce Setter</b>.</li>
	* <br/>
	* <ul>
	* <li>ne fait rien si this.texte == pTexte.</li>
	* <li>passe le paramètre à l'attribut.</li>
	* <li>passe l'attribut au METIER Regex.</li>
	* <li>délègue au métier le rafraîchissement 
	* de tous les attributs calculés.</li>
	* </ul>
	* </ul>
	* - ne fait rien si <code>this.regex</code> est null.<br/>
	* <br/>
	* 
	* @param pMotifJava : String : 
	* valeur à passer à <code>this.motifJava</code>.<br/>
	* 
	* @throws Exception si le pattern <code>this.motifJava</code> 
	* n'est pas conforme à la syntaxe des Regex Java.<br/>
	*/
	void setMotifJava(String pMotifJava) throws Exception;



	/**
	 * Getter du Motif de l'expression régulière applicable 
	 * en JavaScript.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 * Par exemple : 
	 *
	 * @return this.motifJavaScript : String.<br/>
	 */
	String getMotifJavaScript();



	/**
	 * Getter de la Signification du motif de l'expression régulière.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 * Par exemple :
	 *
	 * @return this.significationMotif : String.<br/>
	 */
	String getSignificationMotif();



	/**
	 * Getter du boolean qui stipule si <code>this.motifJava</code> 
	 * <b>respecte la syntaxe</b> des expressions régulières 
	 * (RegEx) Java.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 *
	 * @return this.motifJavaRespecteSyntaxe : boolean.<br/>
	 */
	boolean isMotifJavaRespecteSyntaxe();



	/**
	 * Getter du boolean qui stipule si <code>this.texte</code> 
	 * <b>commence</b> par <code>this.motifJava</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 *
	 * @return this.texteCommenceParMotif : boolean.<br/>
	 */
	boolean isTexteCommenceParMotif();



	/**
	 * Getter du boolean qui stipule si <code>this.texte</code> 
	 * <b>contient</b> <code>this.motifJava</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 *
	 * @return this.texteContientMotif : boolean.<br/>
	 */
	boolean isTexteContientMotif();



	/**
	 * Getter de la Liste des occurences de <code>this.motifJava</code> 
	 * dans <code>this.texte</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 *
	 * @return listeOccurencesMotif : List&lt;IOccurence&gt;.<br/>
	 */
	List<IOccurence> getListeOccurencesMotif();



	/**
	 * Getter du boolean qui stipule si <code>this.texte</code> 
	 * <b>matche entièrement</b> avec <code>this.motifJava</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 *
	 * @return this.texteMatcheMotif : boolean.<br/>
	 */
	boolean isTexteMatcheMotif();



	/**
	 * Getter de la Liste des occurences des sous-groupes capturant de 
	 * <code>this.motifJava</code> 
	 * dans <code>this.texte</code>.<br/>
	 * <b>attribut calculé par le METIER</b>.<br/>
	 *
	 * @return listeOccurencesGroupesCapturant : 
	 * List&lt;IOccurence&gt;.<br/>
	 */
	List<IOccurence> getListeOccurencesGroupesCapturant();



	/**
	 * Getter du METIER Regex.
	 *
	 * @return this.regex : IRegex.<br/>
	 */
	IRegex getRegex();



	/**
	* Setter du METIER Regex.
	*
	* @param pRegex : IRegex : 
	* valeur à passer à this.regex.<br/>
	*/
	void setRegex(IRegex pRegex);
	

	
} // FIN DE L'INTERFACE IRegexService.---------------------------------------
