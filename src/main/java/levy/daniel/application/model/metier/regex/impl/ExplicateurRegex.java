package levy.daniel.application.model.metier.regex.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.regex.IExplicateurRegex;
import levy.daniel.application.model.metier.regex.IOccurence;


/**
 * CLASSE <b>ExplicateurRegex</b> :<br/>
 * classe utilitaire ayant pour but d'expliciter une Regex Java.<br/>
 * Par exemple : le motif "\\d{1,3}?" signifie : 
 * 'entre 1 et 3 chiffres réticent (rechercher le plus petit motif 
 * possible dans le texte)'.<br/>
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
 * @since 14 août 2018
 *
 */
public class ExplicateurRegex implements IExplicateurRegex {
	
	
	// ************************ATTRIBUTS************************************/


	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");
	
	/**
	 * "^(\\^{0,1})(\\({0,1})(.*)(\\){0,1})(\\${0,1})$".<br/>
	 * Motif permettant de capturer le groupe capturant ZERO 
	 * automatiquement créé par Java dès lors qu'il y a un 
	 * groupe capturant dans un motif.<br/>
	 * Le groupe capturant ZERO correspond à la totalité du motif.<br/>
	 * <ul>
	 * <li>1 accent optionnel en début de motif pour exprimer le début de motif -------------> ^(\\^{0,1})</li>
	 * <li>1 parenthèse de début optionnelle pour grouper la totalité du motif ---------------> (\\({0,1})</li>
	 * <li>n'importe quels caractères décrivant le motif -----------------------------------------> (.*)</li>
	 * <li>1 parenthèse de fin optionnelle pour grouper la totalité du motif -------------------> (\\){0,1})</li>
	 * <li>1 dollar optionnel en fin de motif pour exprimer la fin du motif ---------------------> (\\${0,1})$</li>
	 * </ul>
	 */
	public static final String MOTIF_GROUPE_CAPTURANT_ZERO 
		= "^(\\^{0,1})(\\({0,1})(.*)(\\){0,1})(\\${0,1})$";
	
	/**
	 * "(\\({1})([^\\(\\)]*)(\\){1})".<br/>
	 * Motif permettant de capturer les groupes capturants internes 
	 * (hors groupe 0) dans une Regex.<br/>
	 * DEFINITION D'UN GROUPE CAPTURANT
	 * <ul>
	 * <li> 1 parenthèse obligatoire de début '(' --------------------------> (\\({1})</li>
	 * <li> n'importe quoi sauf une parenthèse début '(' ou de fin ')' -----> ([^\\(\\)]*?)</li>
	 * <li> 1 parenthèse obligatoire de fin ')' ----------------------------> (\\){1})</li>
	 * </ul>
	 */
	public static final String MOTIF_GROUPES_CAPTURANTS_INTERNES 
		= "(\\({1})([^\\(\\)]*?)(\\){1})";

		
	/**
	 * Map&lt;String, String&gt; des méta-caractères 
	 * (symboles remplaçant d'autres caractères).<br/>
	 * <ul>
	 * <li>String : méta-caractère.</li>
	 * <li>String : signification du méta-caractère.</li>
	 * </ul>
	 * Par exemple : . signifie "n'importe quel caractère".<br/>
	 * <br/>
	 */
	private static Map<String, String> metaCaracteresMap 
		= new ConcurrentHashMap<String, String>();
	
	/**
	 * Map&lt;String, String&gt; des méta-caractères 
	 * de positionnement (ancrages).<br/>
	 * <ul>
	 * <li>String : méta-caractère de positionnement (ancrage).</li>
	 * <li>String : signification de l'ancrage.</li>
	 * </ul>
	 * Par exemple : $ en fin de motif signifie "fin de chaîne".
	 */
	private static Map<String, String> ancragesMap 
		= new ConcurrentHashMap<String, String>();
	
	
	/**
	 * Map&lt;String, String&gt; des caractères 
	 * opérateurs (ou, ...).<br/>
	 * <ul>
	 * <li>String : opérateur.</li>
	 * <li>String : signification de l'opérateur.</li>
	 * </ul>
	 * Par exemple : | entre 2 motifs signifie "un motif ou l'autre".
	 */
	private static Map<String, String> operateursMap 
		= new ConcurrentHashMap<String, String>();

	
	static {
		
		metaCaracteresMap.put(".", "tout caractère");
		metaCaracteresMap.put("*", "toute chaine de caractères");
		
		ancragesMap.put("¨", "début de chaîne");
		ancragesMap.put("$", "fin de chaîne");
		
		operateursMap.put("|", "ou");
	}

	
	/**
	 * Signification du motif de l'expression régulière.<br/>
	 * Par exemple : 
	 */
	private transient String significationMotif;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ExplicateurRegex.class);
	
	
	// *************************METHODES************************************/

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String extraireSousGroupeCapturant(
			final String pMotif
				, final int pI) {
		
		/* retourne null si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return null;
		}
		
		/* retourne null si pMotif ne respecte pas la 
		 * syntaxe des RegEx Java. */
		if (!this.motifRespecteSyntaxeRegex(pMotif)) {
			return null;
		}
		
		String resultat = null;
		
		if (pI == 0) {
			
			resultat = pMotif;
			
		} else {
			
			final List<IOccurence> list 
				= this.trouverGroupesCapturantsInternes(pMotif);
			
			try {
				
				resultat = list.get(pI - 1).getContenu();
				
			} catch (IndexOutOfBoundsException e) {
				resultat = null;
			}
			
		}
		
		return resultat;
		
	} // Fin de extraireSousMotif(...).____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> trouverGroupeCapturantZero(
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
			= Pattern.compile(MOTIF_GROUPE_CAPTURANT_ZERO);
		final Matcher matcher 
			= pattern.matcher(pMotif);
		
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
				= new Occurence(
						i, trouve, pMotif, positionDebut, positionFin);
			
			/* ajoute l'Occurence au résultat. */
			resultat.add(occurence);
		}
		
		return resultat;

	} // Fin de trouverGroupeCapturantZero(...).___________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IOccurence> trouverGroupesCapturantsInternes(
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
			= Pattern.compile(MOTIF_GROUPES_CAPTURANTS_INTERNES);
		final Matcher matcher 
			= pattern.matcher(pMotif);
		
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
				= new Occurence(
						i, trouve, pMotif, positionDebut, positionFin);
			
			/* ajoute l'Occurence au résultat. */
			resultat.add(occurence);
		}
		
		return resultat;

	} // Fin de trouverGroupesCapturantsInternes(...)._____________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String expliquerMotif(
			final String pMotif) {
		
		/* return null si pMotif est blank. */
		if (StringUtils.isBlank(pMotif)) {
			return null;
		}
		
		String resultat = null;
		
		final StringBuilder stb = new StringBuilder();
		
		System.out.println("MOTIF : " + pMotif);
		
//		final String motifRegex = "^(\\^?)(.*)$";
		final String motifRegex = "^(\\^?)(\\[0-9\\]|.*)(\\+?)$";
		
		final Pattern pattern = Pattern.compile(motifRegex);
		
		final Matcher matcher = pattern.matcher(pMotif);
		
		final boolean caColle = matcher.matches();
		
		if (caColle) {
			
			for (int j = 0; j <= matcher.groupCount(); j++) {
				System.out.println("Groupe (" + j + ") = " + matcher.group(j));
				System.out.println("Le texte \"" + matcher.group(j) + "\" débute à " + matcher.start(j) + " et se termine à " + matcher.end(j));
			}
			
			if (StringUtils.isNotBlank(matcher.group(1))) {
				stb.append("^ au début du motif signifie : ");
				stb.append("commence par");
				stb.append(NEWLINE);
			}
					
		}
		
		resultat = stb.toString();
		
		this.significationMotif = resultat;
		
		return resultat;
	}


	
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
	 * Getter de la Map&lt;String, String&gt; des méta-caractères 
	 * (symboles remplaçant d'autres caractères).<br/>
	 * <ul>
	 * <li>String : méta-caractère.</li>
	 * <li>String : signification du méta-caractère.</li>
	 * </ul>
	 * Par exemple : . signifie "n'importe quel caractère".<br/>
	 * <br/>
	 *
	 * @return metaCaracteresMap : Map&lt;String, String&gt;.<br/>
	 */
	public static Map<String, String> getMetaCaracteresMap() {
		return metaCaracteresMap;
	} // Fin de getMetaCaracteresMap().____________________________________



	/**
	 * Getter de la Map&lt;String, String&gt; des méta-caractères 
	 * de positionnement (ancrages).<br/>
	 * <ul>
	 * <li>String : méta-caractère de positionnement (ancrage).</li>
	 * <li>String : signification de l'ancrage.</li>
	 * </ul>
	 * Par exemple : $ en fin de motif signifie "fin de chaîne".
	 *
	 * @return ancragesMap : Map&lt;String, String&gt;.<br/>
	 */
	public static Map<String, String> getAncragesMap() {
		return ancragesMap;
	} // Fin de getAncragesMap().__________________________________________


	
	/**
	 * Getter de la Map&lt;String, String&gt; des caractères 
	 * opérateurs (ou, ...).<br/>
	 * <ul>
	 * <li>String : opérateur.</li>
	 * <li>String : signification de l'opérateur.</li>
	 * </ul>
	 * Par exemple : | entre 2 motifs signifie "un motif ou l'autre".<br/>
	 * <br/>
	 *
	 * @return operateursMap : Map&lt;String, String&gt;.<br/>
	 */
	public static Map<String, String> getOperateursMap() {
		return operateursMap;
	} // Fin de getOperateursMap().________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSignificationMotif() {
		return this.significationMotif;
	} // Fin de getSignificationMotif().___________________________________
	
		


} // FIN DE LA CLASSE ExplicateurRegex.--------------------------------------
