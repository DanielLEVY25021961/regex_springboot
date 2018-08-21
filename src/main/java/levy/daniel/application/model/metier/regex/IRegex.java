package levy.daniel.application.model.metier.regex;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import levy.daniel.application.model.metier.regex.impl.Occurence;

/**
 * INTERFACE <b>IRegex</b> :<br/>
 * Interface factorisant les comportements des RegEx.<br/>
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
 * @since 6 août 2018
 *
 */
public interface IRegex {
	

	
	/**
	 * Détermine si un motif (pattern) pMotif <b>respecte la syntaxe</b> 
	 * des Expressions régulières (regex) en Java.<br/>
	 * <ul>
	 * <li>retourne true si pMotif respecte la syntaxe RegEx Java.</li>
	 * <li>utilise {@link Pattern}</li>
	 * <li>utilise <code>Pattern.compile(pMotif);</code></li>
	 * <li>traite la <code>PatternSyntaxException</code> levée 
	 * si la syntaxe est incorrecte.</li>
	 * </ul>
	 * - retourne false si pMotif est blank.<br/>
	 * <br/>
	 *
	 * @param pMotif : String : le pattern regex Java dont on veut savoir 
	 * si la syntaxe est correcte.<br/>
	 * 
	 * @return : boolean : true si la syntaxe est régulière.<br/>
	 */
	boolean motifRespecteSyntaxeRegex(String pMotif);
	

	
	/**
	 * Détermine si le texte <code>this.chaineATester</code> 
	 * <b><i>commence par</i></b> 
	 * le motif (pattern) <code>this.motifJava</code>.<br/>
	 * Le texte vérifie alors <b>lookingAt()</b> avec le motif.<br/>
	 * <ul>
	 * <li><b>alimente <code>this.listeOccurencesMotif</code></b>.</li>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise <code>matcher.lookingAt();</code></li>
	 * <li>retourne true si le texte commence par le pattern.</li>
	 * </ul>
	 * - retourne false si <code>this.chaineATester</code> est blank.<br/>
	 * - retourne false si <code>this.motifJava</code> est blank.<br/>
	 * - retourne false si <code>this.motifJava</code> ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @return : boolean : true si le texte 
	 * <i>commence par</i> le pattern.<br/>
	 * 
	 * @throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	boolean texteCommenceParMotif() throws Exception;
	

	
	/**
	 * Détermine si le texte pTexte <b><i>commence par</i></b> 
	 * le motif (pattern) pMotif.<br/>
	 * Le texte vérifie alors <b>lookingAt()</b> avec le motif.<br/>
	 * <ul>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise <code>matcher.lookingAt();</code></li>
	 * <li>retourne true si le texte commence par le pattern.</li>
	 * </ul>
	 * - retourne false si pTexte est blank.<br/>
	 * - retourne false si pMotif est blank.<br/>
	 * - retourne false si pMotif ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @param pTexte : String : Le texte dont on veut savoir 
	 * si il <i>commence</i> par le pattern pMotif.<br/>
	 * @param pMotif : String : le pattern regex.<br/>
	 * 
	 * @return : boolean : true si le texte 
	 * <i>commence par</i> le pattern.<br/>
	 * 
	 * @throws Exception si le pattern pMotif n'est pas 
	 * conforme à la syntaxe des Regex Java.<br/>
	 */
	boolean texteCommenceParMotif(
			String pTexte
				, String pMotif) 
						throws Exception;
	
	
	
	/**
	 * Détermine si le texte <code>this.chaineATester</code> 
	 * <b>contient au moins une occurence</b> 
	 * du motif (pattern) <code>this.motifJava</code>.<br/>
	 * Utilise la méthode <code>find()</code> de Matcher.
	 * <ul>
	 * <li><b>alimente <code>this.listeOccurencesMotif</code></b>.</li>
	 * <li>retourne true si le texte contient 
	 * au moins une occurrence du motif.</li>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise : <br/>
	 * <code>Pattern pattern 
	 * = Pattern.compile(this.motifJava);</code><br/>
	 * <code>Matcher matcher 
	 * = pattern.matcher(this.chaineATester);</code><br/>
	 * <code>resultat = matcher.find();</code>
	 * </li>
	 * </ul>
	 * - return false si <code>this.chaineATester</code> est blank.<br/>
	 * - return false si <code>this.motifJava</code> est blank.<br/>
	 * - retourne false si <code>this.motifJava</code> ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @return : boolean : true si le texte contient 
	 * au moins une occurence du motif.<br/>
	 * 
	 * @throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	boolean texteContientMotif() throws Exception;


	
	/**
	 * Détermine si le texte pTexte 
	 * <b>contient au moins une occurence</b> 
	 * du motif (pattern) pMotif.<br/>
	 * Utilise la méthode <code>find()</code> de Matcher.
	 * <ul>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>retourne true si le texte contient 
	 * au moins une occurrence du motif.</li>
	 * <li>utilise : <br/>
	 * <code>Pattern pattern = Pattern.compile(pMotif);</code><br/>
	 * <code>Matcher matcher = pattern.matcher(pTexte);</code><br/>
	 * <code>resultat = matcher.find();</code>
	 * </li>
	 * </ul>
	 * - return false si pTexte est blank.<br/>
	 * - return false si pMotif est blank.<br/>
	 * - retourne false si pMotif ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @param pTexte : String : texte dont on veut savoir 
	 * si il contient le motif (pattern) pMotif.<br/>
	 * @param pMotif : String : le pattern regex.<br/>
	 * 
	 * @return : boolean : true si le texte contient 
	 * au moins une occurence du motif.<br/>
	 * 
	 * @throws Exception si le pattern pMotif n'est pas 
	 * conforme à la syntaxe des Regex Java.<br/>
	 */
	boolean texteContientMotif(
			String pTexte
				, String pMotif) 
						throws Exception;
	

	
	/**
	 * fournit la liste des {@link Occurence} du motif Regex Java 
	 * <code>this.motifJava</code> dans le texte 
	 * <code>this.chaineATester</code>.<br/>
	 * <b>retourne <code>this.listeOccurencesMotif</code></b><br/>
	 * Utilise la méthode <code>find()</code> de Matcher.
	 * <ul>
	 * <li><b>alimente <code>this.listeOccurencesMotif</code></b>.</li>
	 * <li>retourne une <b>liste vide</b> 
	 * si aucune occurence n'est trouvée.</li>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise une boucle <code>while (matcher.find())</code> 
	 * pour trouver toute les occurences.</li>
	 * <li>utilise <code>matcher.group()</code> 
	 * pour extraire le contenu de l'occurence.</li>
	 * <li>utilise <code>matcher.start()</code> 
	 * pour trouver la position de début de l'occurence.</li>
	 * <li>utilise <code>matcher.end()</code> 
	 * pour trouver la position de fin de l'occurence.</li>
	 * </ul>
	 * - return null si <code>this.chaineATester</code> est blank.<br/>
	 * - return null si <code>this.motifJava</code> est blank.<br/>
	 * - return null si <code>this.motifJava</code> ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 * 
	 * @return List&lt;IOccurence&gt; : liste des {@link Occurence} 
	 * de <code>this.motifJava</code> dans 
	 * <code>this.chaineATester</code>.<br/>
	 * 
	 * @throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	List<IOccurence> trouverOccurences() throws Exception;
	
	
	
	/**
	 * fournit la liste des {@link Occurence} du motif Regex Java 
	 * pMotif dans le texte pTexte.<br/>
	 * Utilise la méthode <code>find()</code> de Matcher.
	 * <ul>
	 * <li>retourne une <b>liste vide</b> 
	 * si aucune occurence n'est trouvée.</li>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise une boucle <code>while (matcher.find())</code> 
	 * pour trouver toute les occurences.</li>
	 * <li>utilise <code>matcher.group()</code> 
	 * pour extraire le contenu de l'occurence.</li>
	 * <li>utilise <code>matcher.start()</code> 
	 * pour trouver la position de début de l'occurence.</li>
	 * <li>utilise <code>matcher.end()</code> 
	 * pour trouver la position de fin de l'occurence.</li>
	 * </ul>
	 * - return null si pTexte est blank.<br/>
	 * - return null si pMotif est blank.<br/>
	 * - return null si pMotif ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @param pTexte : String : texte dans lequel on veut 
	 * trouver les occurences de pMotif.<br/>
	 * @param pMotif : String : motif Regex Java.<br/>
	 * 
	 * @return List&lt;IOccurence&gt; : liste des {@link Occurence} 
	 * de pMotif dans pTexte.<br/>
	 * 
	 * @throws Exception si le pattern pMotif n'est pas 
	 * conforme à la syntaxe des Regex Java.<br/>
	 */
	List<IOccurence> trouverOccurences(
			String pTexte, String pMotif) throws Exception;
	
	
	
	/**
	 * Détermine si le texte <code>this.chaineATester</code> 
	 * <b><i>respecte entièrement</i></b> 
	 * le motif (pattern) <code>this.motifJava</code>.<br/>
	 * Le texte <b>matches</b> alors le motif.<br/>
	 * Utilise la méthode <code>matches()</code> de Matcher.
	 * <ul>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise <code>Pattern.matches(pMotif, pTexte);</code></li>
	 * <li>retourne true si le texte respecte entièrement le pattern.</li>
	 * </ul>
	 * - retourne false si <code>this.chaineATester</code> est blank.<br/>
	 * - retourne false si <code>this.motifJava</code> est blank.<br/>
	 * - retourne false si <code>this.motifJava</code> ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *	 
	 * @return : boolean : true si le texte respecte 
	 * <i>entièrement</i> le pattern.<br/>
	 * 
	 * @throws Exception si le pattern this.motifJava n'est pas 
	 * conforme à la syntaxe des Regex Java.<br/>
	 */
	boolean texteCorrespondEntierementAMotif() 
			throws Exception;
	
	
	
	/**
	 * Détermine si le texte pTexte <b><i>respecte entièrement</i></b> 
	 * le motif (pattern) pMotif.<br/>
	 * Le texte <b>matches</b> alors le motif.<br/>
	 * Utilise la méthode <code>matches()</code> de Matcher.
	 * <ul>
	 * <li>utilise {@link Pattern} et {@link Matcher}.</li>
	 * <li>utilise <code>Pattern.matches(pMotif, pTexte);</code></li>
	 * <li>retourne true si le texte respecte entièrement le pattern.</li>
	 * </ul>
	 * - retourne false si pTexte est blank.<br/>
	 * - retourne false si pMotif est blank.<br/>
	 * - retourne false si pMotif ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @param pTexte : String : Le texte dont on veut savoir 
	 * si il respecte <i>entièrement</i> le pattern pMotif.<br/>
	 * @param pMotif : String : le pattern regex.<br/>
	 * 
	 * @return : boolean : true si le texte respecte 
	 * <i>entièrement</i> le pattern.<br/>
	 * 
	 * @throws Exception si le pattern pMotif n'est pas 
	 * conforme à la syntaxe des Regex Java.<br/>
	 */
	boolean texteCorrespondEntierementAMotif(
			String pTexte
				, String pMotif) 
							throws Exception;


	
	/**
	 * retourne la liste des occurences 
	 * des <b>sous-groupes capturant</b> de <code>this.motifJava</code> 
	 * dans <code>this.chaineATester</code><br/>
	 * Par exemple : <br/>
	 * Texte : 27 ans<br/>
	 * motif : "(\d{2}) (ans)"<br/>
	 * <code>
	 * retourne :<br/>
	 * Liste des groupes capturant : <br/>
	 * Occurence [numero=0, contenu=27 ans, motifRegex=(\d{2}) (ans), indexDebut=0, indexFin=6]<br/>
	 * Occurence [numero=1, contenu=27, motifRegex=(\d{2}), indexDebut=0, indexFin=2]<br/>
	 * Occurence [numero=2, contenu=ans, motifRegex=(ans), indexDebut=3, indexFin=6]<br/>
	 * </code>
	 * <ul>
	 * <li><b>retourne une liste vide si le matcher 
	 * ne matches pas</b>, 
	 * c'est à dire si <code>this.chaineATester</code> ne correspond 
	 * pas entièrement à <code>this.motifJava</code>.</li>
	 * <li>ATTENTION, motifRegex dans les Occurence contient le 
	 * sous-groupes capturant d'indice (1-based) i.</li>
	 * <li>retire les éventuelles parenthèses englobantes de 
	 * <code>this.motifJava</code> 
	 * pour trouver les sous-groupes capturants.</li>
	 * <li>utilise <code>matcher.matches()</code></li>
	 * <li>utilise <code>matcher.groupCount()</code></li>
	 * <li>utilise <code>matcher.group(i)</code></li>
	 * </ul>
	 * - retourne null si <code>this.chaineATester</code> est blank.<br/>
	 * - retourne null si <code>this.motifJava</code> est blank.<br/>
	 * - retourne null si <code>this.motifJava</code> ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @return : List&lt;IOccurence&gt; : 
	 * liste des occurences des <b>groupes capturant</b> de 
	 * <code>this.motifJava</code> dans <code>this.chaineATester</code>.<br/>
	 * 
	 * @throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	List<IOccurence> texteMatcheMotif() 
									throws Exception;
	
	
	
	/**
	 * retourne la liste des occurences 
	 * des <b>sous-groupes capturant</b> de pMotif dans pTexte<br/>
	 * Par exemple : <br/>
	 * Texte : 27 ans<br/>
	 * motif : "(\d{2}) (ans)"<br/>
	 * <code>
	 * retourne :<br/>
	 * Liste des groupes capturant : <br/>
	 * Occurence [numero=0, contenu=27 ans, motifRegex=(\d{2}) (ans), indexDebut=0, indexFin=6]<br/>
	 * Occurence [numero=1, contenu=27, motifRegex=(\d{2}), indexDebut=0, indexFin=2]<br/>
	 * Occurence [numero=2, contenu=ans, motifRegex=(ans), indexDebut=3, indexFin=6]<br/>
	 * </code>
	 * <ul>
	 * <li><b>retourne une liste vide si le matcher 
	 * ne matches pas</b>, 
	 * c'est à dire si pTexte ne correspond pas entièrement à pMotif.</li>
	 * <li>ATTENTION, motifRegex dans les Occurence contient le 
	 * sous-groupes capturant d'indice (1-based) i.</li>
	 * <li>retire les éventuelles parenthèses englobantes de pMotif 
	 * pour trouver les sous-groupes capturants.</li>
	 * <li>utilise <code>matcher.matches()</code></li>
	 * <li>utilise <code>matcher.groupCount()</code></li>
	 * <li>utilise <code>matcher.group(i)</code></li>
	 * </ul>
	 * - retourne null si pTexte est blank.<br/>
	 * - retourne null si pMotif est blank.<br/>
	 * - retourne null si pMotif ne respecte pas 
	 * la syntaxe des Regex Java.<br/>
	 * <br/>
	 *
	 * @param pTexte : String : 
	 * texte dont on veut savoir si il matche entièrement avec pMotif.<br/>
	 * @param pMotif String : motif Regex Java.<br/>
	 * 
	 * @return : List&lt;IOccurence&gt; : 
	 * liste des occurences des <b>groupes capturant</b> de pMotif dans pTexte.<br/>
	 * 
	 * @throws Exception si le pattern pMotif n'est pas 
	 * conforme à la syntaxe des Regex Java.<br/>
	 */
	List<IOccurence> texteMatcheMotif(
			String pTexte
				, String pMotif) 
						throws Exception;
	
	
	
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
	 * Getter de la chaine de caractères dont on veut savoir 
	 * si elle contient ou matche le motif 
	 * de l'expression régulière.<br/>
	 *
	 * @return this.chaineATester : String.<br/>
	 */
	String getChaineATester();
	

	
	/**
	* Setter de la chaine de caractères dont on veut savoir 
	* si elle contient ou matche le motif 
	* de l'expression régulière.<br/>
	* <ul>
	* <li><b>alimente 
	* <code>this.listeOccurencesMotif</code></b>.</li>
	* <li><b>alimente 
	* <code>this.listeOccurencesGroupesCapturant</code>.</b></li>
	* </ul>
	* - ne fait rien si pChaineATester.equals(this.chaineATester).<br/>
	* <br/>
	*
	* @param pChaineATester : String : 
	* valeur à passer à this.chaineATester.<br/>
	* 
	* @throws Exception si le pattern pMotif n'est pas 
	* conforme à la syntaxe des Regex Java.<br/>
	*/
	void setChaineATester(String pChaineATester) throws Exception;
	
	
	
	/**
	 * Getter du Motif de l'expression régulière 
	 * applicable en Java.<br/>
	 * Par exemple : "[0-9]" ou "\\d" en java pour un chiffre. 
	 *
	 * @return motifJava : String.<br/>
	 */
	String getMotifJava();



	/**
	* Setter du Motif de l'expression régulière 
	* applicable en Java.<br/>
	* Par exemple : "[0-9]" ou "\\d" en java pour un chiffre. 
	* <ul>
	* <li>alimente <code>this.motifJavaRespecteSyntaxe</code> à true 
	* si <code>this.motifJava</code> respecte la syntaxe 
	* des RegEx Java.</li>
	* <li><b>alimente 
	* <code>this.listeOccurencesMotif</code></b>.</li>
	* <li><b>alimente 
	* <code>this.listeOccurencesGroupesCapturant</code>.</b></li>
	* </ul>
	* - ne fait rien si pMotifJava.equals(this.motifJava).<br/>
	* <br/>
	*
	* @param pMotifJava : String : 
	* valeur à passer à <code>this.motifJava</code>.<br/>
	* 
	* @throws Exception si le pattern pMotif n'est pas 
	* conforme à la syntaxe des Regex Java.<br/>
	*/
	void setMotifJava(String pMotifJava) throws Exception;



	/**
	 * Getter .<br/>
	 * <b>champ calculé</b>.<br/>
	 * <br/>
	 *
	 * @return motifJavaScript : String.<br/>
	 */
	String getMotifJavaScript();



	/**
	 * Getter .<br/>
	 * <b>champ calculé</b>.<br/>
	 * <br/>
	 *
	 * @return significationMotif : String.<br/>
	 */
	String getSignificationMotif();

	
	
	/**
	 * Getter du boolean qui détermine si <code>this.motifJava</code> 
	 * respecte la syntaxe des expressions régulières (RegEx) Java.<br/>
	 * <b>champ calculé</b>.<br/>
	 *
	 * @return this.motifJavaRespecteSyntaxe : boolean.<br/>
	 */
	boolean isMotifJavaRespecteSyntaxe();
	
	
	
	/**
	 * Getter de la Liste des occurences de <code>this.motifJava</code> 
	 * dans <code>this.chaineATester</code>.<br/>
	 * <b>champ calculé</b>.<br/>
	 *
	 * @return this.listeOccurencesMotif : List&lt;IOccurence&gt;.<br/>
	 */
	List<IOccurence> getListeOccurencesMotif();
	

	
	/**
	 * Getter de la Liste des occurences des sous-groupes capturant de 
	 * <code>this.motifJava</code> 
	 * dans <code>this.chaineATester</code>.<br/>
	 * <b>champ calculé</b>.<br/>
	 *
	 * @return this.listeOccurencesGroupesCapturant : 
	 * List&lt;IOccurence&gt;.<br/>
	 */
	List<IOccurence> getListeOccurencesGroupesCapturant();
	
	
	
} // FIN DE L'INTERFACE IRegex.----------------------------------------------
