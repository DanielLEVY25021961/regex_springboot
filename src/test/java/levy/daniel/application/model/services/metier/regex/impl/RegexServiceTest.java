package levy.daniel.application.model.services.metier.regex.impl;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.regex.IOccurence;
import levy.daniel.application.model.services.metier.regex.IRegexService;

/**
 * CLASSE RegexServiceTest :<br/>
 * Test JUnit de la classe RegexService.<br/>
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
public class RegexServiceTest {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Boolean qui commande l'affichage pour tous les tests.<br/>
	 */
	public static final Boolean AFFICHAGE_GENERAL = true;

	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * "' : "<br/>
	 */
	public static final String DEUX_POINTS = "' : ";
	
	/**
	 * "Texte : ".<br/>
	 */
	public static final String TEXTE = "Texte : ";
	
	/**
	 * "Le Texte '".<br/>
	 */
	public static final String LE_TEXTE = "Le Texte '";
	
	/**
	 * "Motif Regex : ".<br/>
	 */
	public static final String MOTIF = "Motif Regex : ";
		
	/**
	 * "Le motif '".<br/>
	 */
	public static final String LE_MOTIF = "Le motif '";
	
	/**
	 * "' respecte-t-il la syntaxe Regex ? : ".<br/>
	 */
	public static final String RESPECTE_SYNTAXE_REGEX 
		= "' respecte-t-il la syntaxe Regex ? : ";

	/**
	 * "' ? : ".<br/>
	 */
	public static final String INTERROGATION = "' ? : ";
	
	/**
	 * "' commence-t-il par le motif '".<br/>
	 */
	public static final String COMMENCE_PAR_MOTIF 
		= "' commence-t-il par le motif '";
	
	/**
	 * "' contient-il le motif '".<br/>
	 */
	public static final String CONTIENT_MOTIF 
		= "' contient-il le motif '";
	
	/**
	 * "' matche-t-il entièrement avec le motif '".<br/>
	 */
	public static final String MATCHE_MOTIF 
		= "' matche-t-il entièrement avec le motif '";
		
	/**
	 * "liste des occurences du motif : \n".<br/>
	 */
	public static final String LISTE_OCCURENCES 
		= "liste des occurences du motif : \n";
	
	/**
	 * "liste des occurences des groupes capturants du motif : \n".<br/>
	 */
	public static final String LISTE_GROUPES 
		= "liste des occurences des groupes capturants du motif : \n";



	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(RegexServiceTest.class);
	

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public RegexServiceTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * teste le fonctionnement global d'un Regex.<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testFonctionnementSetters() throws Exception {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = true;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE RegexServiceTest - méthode testFonctionnementSetters() ********** ");
		}
		
		IRegexService regexService = null;
		String texte = null;
		String motif = null;
		boolean motifRespecteSyntaxe = false;
		boolean texteCommenceParMotif = false;
		boolean texteContientMotif = false;
		List<IOccurence> occurencesMotif = null;		
		boolean texteMatcheMotif = false;
		List<IOccurence> occurencesGroupes = null;
		
		// ***********************************************************
		/* Instanciation d'un RegexService avec le constructeur d'arité nulle. */
		regexService = new RegexService();
		texte = regexService.getTexte();
		motif = regexService.getMotifJava();
		motifRespecteSyntaxe = regexService.isMotifJavaRespecteSyntaxe();
		texteCommenceParMotif = regexService.isTexteCommenceParMotif();
		texteContientMotif = regexService.isTexteContientMotif();
		occurencesMotif = regexService.getListeOccurencesMotif();		
		texteMatcheMotif = regexService.isTexteMatcheMotif();
		occurencesGroupes = regexService.getListeOccurencesGroupesCapturant();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE LA SIMPLE INSTANCIATION D'UN REGEXSERVICE AVEC LE CONSTRUCTEUR D'ARITE NULLE -----------------");
			System.out.println("IRegexService regexService = new RegexService();");
			System.out.println(TEXTE + texte);
			System.out.println(MOTIF + motif);
			System.out.println();
			System.out.println(LE_MOTIF + motif + RESPECTE_SYNTAXE_REGEX + motifRespecteSyntaxe);
			System.out.println(LE_TEXTE + texte + COMMENCE_PAR_MOTIF + motif + INTERROGATION + texteCommenceParMotif);
			System.out.println(LE_TEXTE + texte + CONTIENT_MOTIF + motif + INTERROGATION + texteContientMotif);
			System.out.println(LISTE_OCCURENCES + regexService.afficherListOccurences(occurencesMotif));
			System.out.println(LE_TEXTE + texte + MATCHE_MOTIF + motif + INTERROGATION + texteMatcheMotif);
			System.out.println(LISTE_GROUPES + regexService.afficherListOccurences(occurencesGroupes));
		}
		
		assertFalse("le motif ne doit pas respecter la syntaxe : "
				, motifRespecteSyntaxe);
		
		assertFalse("le texte ne doit pas commencer par le motif : "
				, texteCommenceParMotif);
		
		assertFalse("le texte ne doit pas contenir le motif : "
				, texteContientMotif);

		// ***********************************************************
		/* Passage d'un texte via un Setter. */
		final String textePasse = "27 ans et 23 piges d'âge mental !";
		regexService.setTexte(textePasse);
		
		texte = regexService.getTexte();
		motif = regexService.getMotifJava();
		motifRespecteSyntaxe = regexService.isMotifJavaRespecteSyntaxe();
		texteCommenceParMotif = regexService.isTexteCommenceParMotif();
		texteContientMotif = regexService.isTexteContientMotif();
		occurencesMotif = regexService.getListeOccurencesMotif();		
		texteMatcheMotif = regexService.isTexteMatcheMotif();
		occurencesGroupes = regexService.getListeOccurencesGroupesCapturant();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE L'UTILISTION DU SETTER DU TEXTE -----------------");
			System.out.println("String textePasse = \"27 ans et 23 piges d'âge mental !\";");
			System.out.println("regexService.setTexte(textePasse);");
			System.out.println(TEXTE + texte);
			System.out.println(MOTIF + motif);
			System.out.println();
			System.out.println(LE_MOTIF + motif + RESPECTE_SYNTAXE_REGEX + motifRespecteSyntaxe);
			System.out.println(LE_TEXTE + texte + COMMENCE_PAR_MOTIF + motif + INTERROGATION + texteCommenceParMotif);
			System.out.println(LE_TEXTE + texte + CONTIENT_MOTIF + motif + INTERROGATION + texteContientMotif);
			System.out.println(LISTE_OCCURENCES + regexService.afficherListOccurences(occurencesMotif));
			System.out.println(LE_TEXTE + texte + MATCHE_MOTIF + motif + INTERROGATION + texteMatcheMotif);
			System.out.println(LISTE_GROUPES + regexService.afficherListOccurences(occurencesGroupes));
		}
		
		// ***********************************************************
		/* Passage d'un motif via un Setter. */
		final String motifPasse = "(\\d{1,3} \\D*)+?";
		regexService.setMotifJava(motifPasse);
		
		texte = regexService.getTexte();
		motif = regexService.getMotifJava();
		motifRespecteSyntaxe = regexService.isMotifJavaRespecteSyntaxe();
		texteCommenceParMotif = regexService.isTexteCommenceParMotif();
		texteContientMotif = regexService.isTexteContientMotif();
		occurencesMotif = regexService.getListeOccurencesMotif();		
		texteMatcheMotif = regexService.isTexteMatcheMotif();
		occurencesGroupes = regexService.getListeOccurencesGroupesCapturant();
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE L'UTILISTION DU SETTER DU MOTIF -----------------");
			System.out.println("String motifPasse = \"(\\\\d{1,3} \\\\D*)+?\";");
			System.out.println("regexService.setMotifJava(motifPasse);");
			System.out.println(TEXTE + texte);
			System.out.println(MOTIF + motif);
			System.out.println();
			System.out.println(LE_MOTIF + motif + RESPECTE_SYNTAXE_REGEX + motifRespecteSyntaxe);
			System.out.println(LE_TEXTE + texte + COMMENCE_PAR_MOTIF + motif + INTERROGATION + texteCommenceParMotif);
			System.out.println(LE_TEXTE + texte + CONTIENT_MOTIF + motif + INTERROGATION + texteContientMotif);
			System.out.println(LISTE_OCCURENCES + regexService.afficherListOccurences(occurencesMotif));
			System.out.println(LE_TEXTE + texte + MATCHE_MOTIF + motif + INTERROGATION + texteMatcheMotif);
			System.out.println(LISTE_GROUPES + regexService.afficherListOccurences(occurencesGroupes));
		}

			
	} // Fin de testFonctionnementSetters()._______________________________
	




} // Fin de CLASSE RegexServiceTest.-----------------------------------------
