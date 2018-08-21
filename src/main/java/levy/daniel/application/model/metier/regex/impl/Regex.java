package levy.daniel.application.model.metier.regex.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.regex.IExplicateurRegex;
import levy.daniel.application.model.metier.regex.IOccurence;
import levy.daniel.application.model.metier.regex.IRegex;


/**
 * CLASSE <b>Regex</b> :<br/>
 * Classe fournissant le <b>métier</b> des 
 * Expressions Régulières (RegEx) en Java.<br/>
 * <br/>
 * <ul>
 * <li>le CONSTRUCTEUR COMPLET permet d'instancier un Regex 
 * en lui passant le texte et le motif Regex.<br/>
 * Le constructeur calcule automatiquement :
 * <ul>
 * <li><code>this.motifJavaScript</code>,</li>
 * <li><code>this.significationMotif</code>,</li>
 * <li><code>this.motifJavaRespecteSyntaxe</code>,</li> 
 * <li><code>this.listeOccurencesMotif</code>.</li>
 * </ul>
 * </li>
 * <li>Les Setters utilisés avec le CONSTRUCTEUR D'ARITE NULLE 
 * font exactement la même chose.</li>
 * <li>la méthode <code>determinerSiMotifConforme()</code> 
 * permet de savoir si le Pattern Regex <code>this.motifJava</code> 
 * est <b>conforme à la syntaxe des Regex Java</b>.</li>
 * <li>la méthode <code>motifRespecteSyntaxeRegex(String pMotif)</code> 
 * permet de savoir si le motif pMotif <b>respecte 
 * la syntaxe des Regex Java</b>.</li>
 * <li>la méthode <code>texteCommenceParMotif(...)</code> 
 * permet de savoir si un texte <b>commence</b> par un motif.</li>
 * <li>la méthode <code>texteContientMotif(...)</code> 
 * permet de savoir si un texte <b>contient</b> un motif.</li>
 * <li>la méthode <code>trouverOccurences(...)</code> 
 * permet de <b>trouver toutes les occurences</b> 
 * d'un motif dans un texte.<br/> 
 * Utilise des {@link IOccurence}</li>
 * <li>la méthode <code>texteCorrespondEntierementAMotif(...)</code> 
 * permet de savoir si un texte <b>correspond entièrement</b> 
 * à un motif.</li>
 * <li>la méthode <code></code> </li>
 * <li>la méthode <code></code> </li>
 * <li></li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code><i>// instancie un Regex en lui passant le texte et le motif à rechercher.</i></code><br/>
 * <code><b>IRegex regex = new Regex(texte, motif);</b></code><br/>
 * <code><i>// Détermine si le motif respecte la syntaxe RegEx Java.</i></code><br/>
 * <code><b>boolean respecteMotif = regex.isMotifJavaRespecteSyntaxe();</b></code><br/>
 * <code><i>// Détermine si le texte contient le motif.</i></code><br/>
 * <code><b>boolean contientMotif = regex.texteContientMotif();</b></code><br/>
 * <code><i>// Récupère la liste des occurences du motif.</i></code><br/>
 * <code><b>List&lt;IOccurence&gt; occurences = regex.trouverOccurences();</b></code><br/>
 * <code><i>// .</i></code><br/>
 * <code><b></b></code><br/>
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
public class Regex implements IRegex {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");

	/**
	 * "^(\\^{0,1})(?:\\({1})(.*)(?:\\){1})(\\${0,1})$".<br/>
	 * Motif permettant de capturer le motif à l'intérieur 
	 * de parenthèses englobantes.<br/>
	 * Le groupe capturant ZERO correspond à la totalité du motif.<br/>
	 * <ul>
	 * <li>1 accent optionnel en début de motif pour exprimer le début de motif -------------> ^(\\^{0,1})</li>
	 * <li>1 parenthèse de début obligatoire pour grouper la totalité du motif ---------------> (\\({1})</li>
	 * <li>n'importe quels caractères décrivant le motif -----------------------------------------> (.*)</li>
	 * <li>1 parenthèse de fin pbligatoire pour grouper la totalité du motif -------------------> (\\){1})</li>
	 * <li>1 dollar optionnel en fin de motif pour exprimer la fin du motif ---------------------> (\\${0,1})$</li>
	 * </ul>
	 */
	public static final String MOTIF_PARENTHESES_ENGLOBANTES 
		= "^(\\^{0,1})(?:\\({1})(.*)(?:\\){1})(\\${0,1})$";
	
	/**
	 * "^(\\^{0,1})(\\({0,1})(.*)(\\){0,1})(\\${0,1})$".<br/>
	 * Motif permettant de capturer le coeur du motif à l'intérieur 
	 * d'eventuelles ancres.<br/>
	 * <ul>
	 * <li>1 accent optionnel en début de motif pour exprimer le début de motif -------------> ^(\\^{0,1})</li>
	 * <li>n'importe quels caractères décrivant le motif -----------------------------------------> (.*?)</li>
	 * <li>1 dollar optionnel en fin de motif pour exprimer la fin du motif ---------------------> (\\${0,1})$</li>
	 * </ul>
	 */
	public static final String MOTIF_COEUR_MOTIF 
		= "^(\\^{0,1})(.*?)(\\${0,1})$";
	
	/**
	 * chaine de caractères dont on veut savoir 
	 * si elle contient ou matche le motif 
	 * de l'expression régulière.<br/>
	 */
	private String chaineATester;
	
	/**
	 * Motif de l'expression régulière applicable en Java.<br/>
	 * Par exemple : "[0-9]" ou "\\d" en java pour un chiffre. 
	 */
	private String motifJava;
	
	/**
	 * Motif de l'expression régulière applicable en JavaScript.<br/>
	 * <b>champ calculé</b>.<br/>
	 * Par exemple : 
	 */
	private transient String motifJavaScript;
	
	/**
	 * Signification du motif de l'expression régulière.<br/>
	 * <b>champ calculé</b>.<br/>
	 * Par exemple : 
	 */
	private transient String significationMotif;
	
	/**
	 * boolean qui détermine si <code>this.motifJava</code> 
	 * respecte la syntaxe des expressions régulières (RegEx) Java.<br/>
	 * <b>champ calculé</b>.<br/>
	 */
	private transient boolean motifJavaRespecteSyntaxe;
	
	/**
	 * Liste des occurences de <code>this.motifJava</code> 
	 * dans <code>this.chaineATester</code>.<br/>
	 * <b>champ calculé</b>.<br/>
	 */
	private transient List<IOccurence> listeOccurencesMotif;
	
	/**
	 * Liste des occurences des sous-groupes capturant de 
	 * <code>this.motifJava</code> 
	 * dans <code>this.chaineATester</code>.<br/>
	 * <b>champ calculé</b>.<br/>
	 */
	private transient List<IOccurence> listeOccurencesGroupesCapturant;
	
	/**
	 * ExplicateurRegex.<br/>
	 */
	private final transient IExplicateurRegex explicateur 
		= new ExplicateurRegex();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Regex.class);
	
	
	// *************************METHODES************************************/

	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * 
	 * @throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	public Regex() throws Exception {
		this(null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>alimente <code>this.motifJavaRespecteSyntaxe</code> à true 
	 * si <code>this.motifJava</code> respecte la syntaxe 
	 * des RegEx Java.</li>
	 * <li>alimente <code>this.listeOccurencesMotif</code>.</li>
	 * <li>alimente <code>this.listeOccurencesGroupesCapturant</code>.</li>
	 * </ul>
	 *
	 * @param pChaineATester : String : 
	 * chaine de caractères dont on veut savoir 
	 * si elle contient ou matche le motif 
	 * de l'expression régulière.<br/>
	 * @param pMotifJava : String : 
	 * Motif de l'expression régulière applicable en Java.<br/>
	 * Par exemple : "[0-9]" ou "\\d" en java pour un chiffre.
	 *  
	 * @throws Exception si le pattern <code>this.motifJava</code> 
	 * n'est pas conforme à la syntaxe des Regex Java.<br/>
	 */
	public Regex(
			final String pChaineATester
				, final String pMotifJava) throws Exception {
		
		super();
		
		this.chaineATester = pChaineATester;
		this.motifJava = pMotifJava;
		
		/* rafraîchit tous les attributs calculés. */
		this.rafraichirAttributsCalcules();
		
	} // Fin du  CONSTRUCTEUR COMPLET._____________________________________
	

	
	/**
	 * <b>rafraîchit tous les attributs calculés 
	 * à chaque appel de cette méthode</b>.<br/>
	 * <ul>
	 * <li>alimente <code>this.motifJavaRespecteSyntaxe</code> à true 
	 * si <code>this.motifJava</code> respecte la syntaxe 
	 * des RegEx Java.</li>
	 * <li>alimente <code>this.listeOccurencesMotif</code>.</li>
	 * <li>alimente <code>this.listeOccurencesGroupesCapturant</code>.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private void rafraichirAttributsCalcules() throws Exception {
		
		/* alimente this.motifJavaRespecteSyntaxe à true si 
		 * this.motifJava respecte la syntaxe des RegEx Java. */
		this.determinerSiMotifConforme();
		
		/* alimente this.listeOccurencesMotif. */
		this.listeOccurencesMotif 
			= this.trouverOccurences();
		
		/* alimente this.listeOccurencesGroupesCapturant. */
		this.listeOccurencesGroupesCapturant 
			= this.texteMatcheMotif();
		
	} // Fin de rafraichirAttributsCalcules()._____________________________
	
	
	
	/**
	 * Détermine si <code>this.motifJava</code> respecte la syntaxe 
	 * des expressions régulières Java (RegEx).<br/>
	 * <ul>
	 * <li>alimente le boolean 
	 * <code>this.motifJavaRespecteSyntaxe</code>.</li>
	 * <li>passe <code>this.motifJavaRespecteSyntaxe</code> 
	 * à true si <code>this.motifJava</code> respecte 
	 * la syntaxe RegEx Java.</li>
	 * </ul>
	 *  - ne fait rien si <code>this.motifJava</code> est null.<br/>
	 *  <br/>
	 */
	private void determinerSiMotifConforme() {
		
		/* ne fait rien si this.motifJava est null. */
		if (!StringUtils.isBlank(this.motifJava)) {
			
			/* alimente this.motifJavaRespecteSyntaxe. */
			this.motifJavaRespecteSyntaxe 
				= this.motifRespecteSyntaxeRegex(this.motifJava);
		}
		
	} // Fin de determinerSiMotifConforme()._______________________________
	

		
	/**
	 * <b>retire les parenthèses globales (juste à côté des ancres)</b> 
	 * à un motif Regex Java.<br/>
	 * Ces parenthèses globales pour créer un groupe capturant 
	 * sont inutiles puisque le group(0) créé automatiquement
	 * par Java reprend toujours l'intégralité du motif.<br/>
	 * Les parenthèses globales rajoutent un group(1) en sus du group(0) 
	 * qui reprend l'intégralité du motif sans les éventuelles ancres.<br/>
	 * Par exemple :<br/>
	 * <code>
	 * <b>(</b>(abc)()(abc)<b>)</b> -----> (abc)()(abc)<br/>
	 * ^<b>(</b>(abc)()(abc)<b>)</b> ----> ^(abc)()(abc)<br/>
	 * (abc)()(abc) -------> (abc)()(abc)<br/>
	 * (abc)()(abc) -------> ^(abc)()(abc)$<br/>
	 * </code>
	 * <ul>
	 * <li>Ne concerne que les motifs commençant et se terminant 
	 * par des parenthèses (hormis les éventuelles ancres).</li>
	 * <li>retourne pMotif inchangé si pMotif ne respecte pas 
	 * MOTIF_PARENTHESES_ENGLOBANTES.</li>
	 * </ul>
	 * - retourne null si pMotif est blank.<br/>
	 * - retourne null si pMotif ne respecte pas la 
	 * syntaxe des RegEx Java.<br/>
	 * <br/>
	 *
	 * @param pMotif : String : 
	 * motif RegEx Java dont on veut retirer les 
	 * parenthèses englobantes.<br/>
	 * 
	 * @return : String : le motif pMotif éventuellement débarassé 
	 * de ses parenthèses englobantes.<br/>
	 */
	private String enleverParenthesesGlobales(
			final String pMotif) {
		
		/* retourne null si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return null;
		}
		
		/* retourne null si pMotif ne respecte pas la 
		 * syntaxe des RegEx Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return null;
		}
		
						
		final Pattern pattern 
			= Pattern.compile(MOTIF_PARENTHESES_ENGLOBANTES);
		final Matcher matcher 
			= pattern.matcher(pMotif);
		
		String resultat = null;
				
		if (matcher.matches()) {
			
			final String ancreDebut = matcher.group(1);
			final String motifMatche = matcher.group(2);
			final String ancreFin = matcher.group(3);
			
			final StringBuilder stb = new StringBuilder();
												
			if (!motifRespecteSyntaxeRegex(motifMatche)) {
				stb.append(pMotif);
			} else {
				if (!StringUtils.isBlank(ancreDebut)) {
					stb.append(ancreDebut);
					stb.append(motifMatche);
				} else {
					stb.append(motifMatche);
				}
				
				if (!StringUtils.isBlank(ancreFin)) {
					stb.append(ancreFin);
				}
				
			}
			
			resultat = stb.toString();
			
		} else {
			
			/* retourne pMotif inchangé si pMotif ne respecte pas 
			 * MOTIF_PARENTHESES_ENGLOBANTES.*/
			resultat = pMotif;
		}
		
		return resultat;

	} // Fin de enleverParenthesesGlobales(...).___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean motifRespecteSyntaxeRegex(
			final String pMotif) {
		
		/* retourne false si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return false;
		}
		
		boolean resultat = false;
		
		try {
			
			Pattern.compile(pMotif);
			
			resultat = true;
			
		} catch (PatternSyntaxException e) {
			
			resultat = false;
		}
		
		return resultat;
		
	} // Fin de motifRespecteSyntaxeRegex(...).____________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean texteCommenceParMotif() throws Exception {
		
		/* alimente this.listeOccurencesMotif. */
		this.listeOccurencesMotif 
			= this.trouverOccurences(this.chaineATester, this.motifJava);
		
		return this.texteCommenceParMotif(
				this.chaineATester, this.motifJava);
		
	} // Fin de texteCommenceParMotif().___________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean texteCommenceParMotif(
			final String pTexte
				, final String pMotif) 
						throws Exception {
		
		/* return false si pTexte est blank. */
		if (StringUtils.isBlank(pTexte)) {
			return false;
		}
		
		/* return false si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return false;
		}
		
		/* retourne false si pMotif ne respecte pas 
		 * la syntaxe des Regex Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return false;
		}
		
		boolean resultat = false;
		
		final Pattern pattern = Pattern.compile(pMotif);
		final Matcher matcher = pattern.matcher(pTexte);
		
		resultat = matcher.lookingAt();
		
		return resultat;
		
	} // Fin de texteCommenceParMotif(...).________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean texteContientMotif() throws Exception {
		
		/* alimente this.listeOccurencesMotif. */
		this.listeOccurencesMotif 
			= this.trouverOccurences(this.chaineATester, this.motifJava);
		
		return this.texteContientMotif(
				this.chaineATester, this.motifJava);
		
	} // Fin de texteContientMotif(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean texteContientMotif(
			final String pTexte
				, final String pMotif) 
						throws Exception {
		
		/* return false si pTexte est blank. */
		if (StringUtils.isBlank(pTexte)) {
			return false;
		}
		
		/* return false si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return false;
		}
		
		/* retourne false si pMotif ne respecte pas 
		 * la syntaxe des Regex Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return false;
		}

		
		boolean resultat = false;
		
		final Pattern pattern = Pattern.compile(pMotif);
		final Matcher matcher = pattern.matcher(pTexte);

		resultat = matcher.find();
		
		return resultat;
		
	} // Fin de texteContientMotif(...).___________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> trouverOccurences() 
											throws Exception {
		
		/* alimente this.listeOccurencesMotif. */
		this.listeOccurencesMotif 
			= this.trouverOccurences(this.chaineATester, this.motifJava);
		
		return this.listeOccurencesMotif;
		
	} // Fin de trouverOccurences()._______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> trouverOccurences(
			final String pTexte
				, final String pMotif) 
						throws Exception {
		
		/* return null si pTexte est blank. */
		if (StringUtils.isBlank(pTexte)) {
			return null;
		}
		
		/* return null si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return null;
		}
		
		/* return null si pMotif ne respecte pas la syntaxe des Regex Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return null;
		}
		
		final Pattern pattern = Pattern.compile(pMotif);
		final Matcher matcher = pattern.matcher(pTexte);
		
		int i = 0;
		
		final List<IOccurence> resultat = new LinkedList<IOccurence>();
		
		/* détecte toutes les occurences du motif. */
		while (matcher.find()) {
			
			/* incrémente le numéro (1-based). */
			i++;
			
			/* extrait le contenu. */
			final String trouve = matcher.group();
			
			/* extrait la position de début. */
			final int positionDebut = matcher.start();
			
			/* extrait la position de fin. */
			final int positionFin = matcher.end();
			
			/* instancie une Occurence (pure fabrication). */
			final IOccurence occurence 
				= new Occurence(i, trouve, pMotif, positionDebut, positionFin);
			
			/* ajoute l'Occurence au résultat. */
			resultat.add(occurence);
		}
		
		return resultat;

	} // Fin de trouverOccurences(...).____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean texteCorrespondEntierementAMotif() 
			throws Exception {
		return this.texteCorrespondEntierementAMotif(
				this.chaineATester, this.motifJava);
	} // Fin de texteCorrespondEntierementAMotif().________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean texteCorrespondEntierementAMotif(
			final String pTexte
				, final String pMotif) 
							throws Exception {
		
		/* retourne false si pTexte est blank. */
		if (StringUtils.isBlank(pTexte)) {
			return false;
		}
		
		/* retourne false si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return false;
		}
		
		/* retourne false si pMotif ne respecte pas 
		 * la syntaxe des Regex Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return false;
		}
		
		boolean resultat = false;
		
		resultat = Pattern.matches(pMotif, pTexte);
		
		return resultat;
		
	} // Fin de texteCorrespondEntierementAMotif(...)._____________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> texteMatcheMotif() 
								throws Exception {
		
		return this.texteMatcheMotif(this.chaineATester, this.motifJava);
		
	} // Fin de texteMatcheMotif().________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IOccurence> texteMatcheMotif(
			final String pTexte
				, final String pMotif) 
						throws Exception {
		
		/* retourne null si pTexte est blank. */
		if (StringUtils.isBlank(pTexte)) {
			return null;
		}
		
		/* retourne null si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return null;
		}
		
		/* retourne null si pMotif ne respecte pas 
		 * la syntaxe des Regex Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return null;
		}
		
		/* retire les éventuelles parenthèses englobantes de pMotif pour trouver les sous-groupes capturants. */
		final String motif = this.enleverParenthesesGlobales(pMotif);
		
		final Pattern pattern = Pattern.compile(motif);
		final Matcher matcher = pattern.matcher(pTexte);
		
		final boolean booleanMatches = matcher.matches();
		
		List<IOccurence> resultat = null;
		
		if (booleanMatches) {
						
			resultat = new LinkedList<IOccurence>();
			
			final int nombreOccurences = matcher.groupCount();
			
			for (int i = 0; i <= nombreOccurences; i++) {
				
				final int numero = i;
				final String contenu =  matcher.group(i);
				final int posDebut = matcher.start(i);
				final int posFin = matcher.end(i);
				
				final IOccurence occurence 
					= new Occurence(
							numero
							, contenu
							, this.explicateur.extraireSousGroupeCapturant(motif, i)
							, posDebut, posFin);
										
				resultat.add(occurence);
			}
		}
		
		return resultat;
		
	} // Fin de texteMatcheMotif(...)._____________________________________
	
	
	
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
	public final String getChaineATester() {
		return this.chaineATester;
	} // Fin de getChaineATester().________________________________________


	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public final void setChaineATester(
			final String pChaineATester) throws Exception {
		
		/* ne fait rien si pChaineATester.equals(this.chaineATester). */
		if (!StringUtils.isBlank(pChaineATester)) {
			
			this.chaineATester = pChaineATester;
			
			/* rafraîchit tous les attributs calculés. */
			this.rafraichirAttributsCalcules();
			
		}
				
	} // Fin de setChaineATester(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMotifJava() {
		return this.motifJava;
	} // Fin de getMotifJava().____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setMotifJava(
			final String pMotifJava) throws Exception {
		
		/* ne fait rien si pMotifJava.equals(this.motifJava). */
		if (!StringUtils.equals(pMotifJava, this.motifJava)) {
			
			this.motifJava = pMotifJava;
			
			/* rafraîchit tous les attributs calculés. */
			this.rafraichirAttributsCalcules();
		}
				
	} // Fin de setMotifJava(...)._________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMotifJavaScript() {
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
	public final boolean isMotifJavaRespecteSyntaxe() {
		return this.motifJavaRespecteSyntaxe;
	} // Fin de isMotifJavaRespecteSyntaxe().______________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> getListeOccurencesMotif() {
		return this.listeOccurencesMotif;
	} // Fin de getListeOccurencesMotif()._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> getListeOccurencesGroupesCapturant() {
		return this.listeOccurencesGroupesCapturant;
	} // Fin de getListeOccurencesGroupesCapturant().______________________
	
		
	
} // FIN DE LA CLASSE Regex.-------------------------------------------------
