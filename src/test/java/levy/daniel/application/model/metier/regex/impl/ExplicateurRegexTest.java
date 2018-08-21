package levy.daniel.application.model.metier.regex.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import levy.daniel.application.model.metier.regex.IExplicateurRegex;
import levy.daniel.application.model.metier.regex.IOccurence;

/**
 * CLASSE ExplicateurRegexTest :<br/>
 * Test JUnit de la classe ExplicateurRegex.<br/>
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
 * @since 17 août 2018
 *
 */
public class ExplicateurRegexTest {

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
	 * "MOTIF : ".<br/>
	 */
	public static final String MOTIF = "MOTIF : ";
	
	/**
	 * "INDICE : ".<br/>
	 */
	public static final String INDICE = "INDICE : ";
	
	/**
	 * "Liste des groupes capturants : \n".<br/>
	 */
	public static final String LISTE_GROUPES_CAPTURANT 
		= "Liste des groupes capturants : \n";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ExplicateurRegexTest.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ExplicateurRegexTest() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	/**
	 * teste la méthode extraireSousGroupeCapturant(pMotif, i).<br/>
	 * <ul>
	 * <li>garantit que extraireSousGroupeCapturant(null, i)
	 *  retourne toujours null.</li>
	 * <li>garantit que extraireSousGroupeCapturant(motifSansGroupe, i) 
	 * retourne toujours null.</li>
	 * <li>garantit que extraireSousGroupeCapturant(motif, i hors borne)
	 *  retourne toujours null.</li>
	 * <li>garantit le bon fonctionnement de 
	 * extraireSousGroupeCapturant(motif, pI).</li>
	 * <li>garantit qu'un motif sans parenthèse globales de groupe 0 
	 * retourne les mêmes valeurs qu'un motif avec parenthèses globales.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testExtraireSousGroupeCapturant() {
				
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE ExplicateurRegexTest - méthode testExtraireSousGroupeCapturant() ********** ");
		}
		
		// INSTANCIATION D'UN ExplicateurRegex.
		final IExplicateurRegex explicateur = new ExplicateurRegex();
		
		final String motif1 = "^((\\d{1,3})([a-zA-Z]{1,5})(.*))$";
		final String motif11 = "^(\\d{1,3})([a-zA-Z]{1,5})(.*)$";
		final String motif2 = "^\\d{1,3}[a-zA-Z]{1,5}.*$";
		final int indiceZero = 0;
		final int indice = 2;
		final int indiceHorsBorne = 7;
		
		final String sousGroupeMotifNull 
			= explicateur.extraireSousGroupeCapturant(null, indice);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN MOTIF NULL -------------------");
			System.out.println(INDICE + indice);
			System.out.println("SOUS-GROUPE SI MOTIF NULL : " + sousGroupeMotifNull);
		}
		
		/* garantit que extraireSousGroupeCapturant(null, i) 
		 * retourne toujours null. */
		assertNull(
				"extraireSousGroupeCapturant(null, i) doit toujours retourner null : "
					, sousGroupeMotifNull);
		
		
		final String sousGroupeMotifNonCapturant 
			= explicateur.extraireSousGroupeCapturant(motif2, indice);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN MOTIF NE CONTENANT AUCUN GROUPE CAPTURANT -------------------");
			System.out.println(MOTIF + motif2);
			System.out.println(INDICE + indice);
			System.out.println();
			System.out.println("SOUS-GROUPE SI MOTIF NON CAPTURANT : " + sousGroupeMotifNonCapturant);
		}
		
		/* garantit que extraireSousGroupeCapturant(motifSansGroupe, i) 
		 * retourne toujours null. */
		assertNull(
				"extraireSousGroupeCapturant(motifSansGroupe, i) doit toujours retourner null : "
					, sousGroupeMotifNonCapturant);
		
		final String sousGroupeIndiceHorsBorne 
			= explicateur.extraireSousGroupeCapturant(motif1, indiceHorsBorne);
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN INDICE SUPERIEUR AU NOMBRE DE SOUS-GROUPES -------------------");
			System.out.println(MOTIF + motif1);
			System.out.println(INDICE + indiceHorsBorne);
			System.out.println();
			System.out.println("SOUS-GROUPE SI INDICE HORS BORNE : " + sousGroupeIndiceHorsBorne);
		}
		
		/* garantit que extraireSousGroupeCapturant(motif, i hors borne) 
		 * retourne toujours null. */
		assertNull(
				"extraireSousGroupeCapturant(motif, i hors borne) doit toujours retourner null : "
					, sousGroupeIndiceHorsBorne);
		
		final String sousGroupemotif1IndiceZero 
			= explicateur.extraireSousGroupeCapturant(motif1, indiceZero);
		
		final String sousGroupemotif11IndiceZero 
			= explicateur.extraireSousGroupeCapturant(motif11, indiceZero);

		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS D'UN INDICE ZERO AVEC GROUPE 0 -------------------");
			System.out.println(MOTIF + motif1);
			System.out.println(INDICE + indiceZero);
			System.out.println();
			System.out.println("SOUS-GROUPE 0 AVEC GROUPE CAPTURANT 0 : " + sousGroupemotif1IndiceZero);
			System.out.println("indice 0 : " + explicateur.extraireSousGroupeCapturant(motif1, 0));
			System.out.println("indice 1 : " + explicateur.extraireSousGroupeCapturant(motif1, 1));
			System.out.println("indice 2 : " + explicateur.extraireSousGroupeCapturant(motif1, 2));
			System.out.println("indice 3 : " + explicateur.extraireSousGroupeCapturant(motif1, 3));
			System.out.println("indice 4 : " + explicateur.extraireSousGroupeCapturant(motif1, 4));
			System.out.println();
			System.out.println("CAS D'UN INDICE ZERO SANS GROUPE 0 -------------------");
			System.out.println(MOTIF + motif11);
			System.out.println(INDICE + indiceZero);
			System.out.println();
			System.out.println("SOUS-GROUPE 0 SANS GROUPE CAPTURANT 0 : " + sousGroupemotif11IndiceZero);
			System.out.println("indice 0 : " + explicateur.extraireSousGroupeCapturant(motif11, 0));
			System.out.println("indice 1 : " + explicateur.extraireSousGroupeCapturant(motif11, 1));
			System.out.println("indice 2 : " + explicateur.extraireSousGroupeCapturant(motif11, 2));
			System.out.println("indice 3 : " + explicateur.extraireSousGroupeCapturant(motif11, 3));
			System.out.println("indice 4 : " + explicateur.extraireSousGroupeCapturant(motif11, 4));
		}
		
		/* garantit le bon fonctionnement de 
		 * extraireSousGroupeCapturant(motif, pI). */
		assertEquals(
				"le groupeO doit toujours valoir le motif"
					, motif1
						, sousGroupemotif1IndiceZero);
		assertEquals(
				"le groupeO doit toujours valoir le motif"
					, motif11
						, sousGroupemotif11IndiceZero);
		
		assertEquals("le groupe1 doit valoir d{1,3} : "
				, "(\\d{1,3})"
					, explicateur.extraireSousGroupeCapturant(motif1, 1));
		assertEquals("le groupe1 doit valoir d{1,3} : "
				, "(\\d{1,3})"
					, explicateur.extraireSousGroupeCapturant(motif11, 1));
		
		/* garantit qu'un motif sans parenthèse globales de groupe 0 
		 * retourne les mêmes valeurs qu'un motif 
		 * avec parenthèses globales. */
		assertEquals("un motif avec parenthèses globales doit retourner les mêmes valeurs qu'un motif sans : "
				, explicateur.extraireSousGroupeCapturant(motif1, 1)
					, explicateur.extraireSousGroupeCapturant(motif11, 1));
		
	} // Fin de testExtraireSousGroupeCapturant()._________________________
	
	
	
	/**
	 * teste la méthode trouverGroupeCapturantZero(String pMotif).<br/>
	 * <ul>
	 * <li>garantit que trouverGroupeCapturantZero(motif) 
	 * retourne une liste non vide.</li>
	 * <li>garantit que trouverGroupeCapturantZero(motif) 
	 * retourne une liste avec un seul élément.</li>
	 * <li>garantit que trouverGroupeCapturantZero(motif) 
	 * retourne une Liste avec une seule Occurence contenant 
	 * le motif complet.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testTrouverGroupeCapturantZero() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE ExplicateurRegexTest - méthode testTrouverGroupeCapturantZero() ********** ");
		}
		
		final String motif1 = "^((\\d{1,3})([a-zA-Z]{1,5})(.*))$";
		final String motif2 = "^\\d{1,3}[a-zA-Z]{1,5}.*$";
		
		// INSTANCIATION D'UN ExplicateurRegex.
		final IExplicateurRegex explicateur = new ExplicateurRegex();
		
		final List<IOccurence> list1 
			= explicateur.trouverGroupeCapturantZero(motif1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("GOUPE CAPTURANT ZERO -------------------------------");
			System.out.println(MOTIF + motif1);
			System.out.println();
			System.out.println("Groupe capturant zéro : " + explicateur.afficherListOccurences(list1));
		}
		
		/* garantit que trouverGroupeCapturantZero(motif1) 
		 * retourne une liste non vide. */
		assertFalse(
				"trouverGroupeCapturantZero(motif1) ne doit pas retourner une liste vide : "
					, list1.isEmpty());
		
		/* garantit que trouverGroupeCapturantZero(motif1) 
		 * retourne une liste avec un seul élément. */
		assertTrue(
				"trouverGroupeCapturantZero(motif1) doit retourner une liste singleton : "
					, list1.size() == 1);
		
		/* garantit que trouverGroupeCapturantZero(motif1) 
		 * retourne une Liste avec une seule Occurence 
		 * contenant le motif complet. */
		assertEquals(
				"le contenu doit être le motif complet : "
					, motif1
						, list1.get(0).getContenu());
		
		final List<IOccurence> list2 
		= explicateur.trouverGroupeCapturantZero(motif2);
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("GOUPE CAPTURANT ZERO ---------------------------------");
			System.out.println(MOTIF + motif2);
			System.out.println();
			System.out.println("Groupe capturant zéro : " + explicateur.afficherListOccurences(list2));
		}
		
		/* garantit que trouverGroupeCapturantZero(motif2) 
		 * retourne une liste non vide. */
		assertFalse(
				"trouverGroupeCapturantZero(motif2) ne doit pas retourner une liste vide : "
					, list2.isEmpty());
		
		/* garantit que trouverGroupeCapturantZero(motif2) 
		 * retourne une liste avec un seul élément. */
		assertTrue(
				"trouverGroupeCapturantZero(motif2) doit retourner une liste singleton : "
					, list2.size() == 1);
		
		/* garantit que trouverGroupeCapturantZero(motif2) 
		 * retourne une Liste avec une seule Occurence 
		 * contenant le motif complet. */
		assertEquals(
				"le contenu doit être le motif complet : "
					, motif2
						, list2.get(0).getContenu());
		
	} // Fin de testTrouverGroupeCapturantZero().__________________________
	

	
	/**
	 * teste la méthode trouverGroupesCapturants(String pMotif).<br/>
	 * <ul>
	 * <li>garantit que trouverGroupesCapturantsInternes(motif) 
	 * retourne les groupes capturants internes.</li>
	 * <li>garantit que trouverGroupesCapturantsInternes(motif) 
	 * retourne une liste vide si il n'y a pas de groupe 
	 * capturant dans le motif.</li>
	 * </ul>
	 */
	@SuppressWarnings(UNUSED)
	@Test
	public void testTrouverGroupesCapturantsInternes() {
		
		// **********************************
		// AFFICHAGE DANS LE TEST ou NON
		final boolean affichage = false;
		// **********************************
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("********** CLASSE ExplicateurRegexTest - méthode testTrouverGroupesCapturantsInternes() ********** ");
		}
		
		final String motif1 = "^((\\d{1,3})([a-zA-Z]{1,5})(.*))$";
		final String motif2 = "^\\d{1,3}[a-zA-Z]{1,5}.*$";
		
		final IExplicateurRegex explicateur = new ExplicateurRegex();
		
		final List<IOccurence> list1 
			= explicateur.trouverGroupesCapturantsInternes(motif1);
		
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS DE GROUPES CAPTURANTS -----------------------------");
			System.out.println(MOTIF + motif1);
			System.out.println();
			System.out.println(LISTE_GROUPES_CAPTURANT + explicateur.afficherListOccurences(list1));
		}
		
		/* garantit que trouverGroupesCapturantsInternes(motif) 
		 * retourne les groupes capturants internes. */
		assertTrue(
				"le motif1 doit contenir 3 groupes capturants : "
					, list1.size() == 3);
		
		final List<IOccurence> list2 
		= explicateur.trouverGroupesCapturantsInternes(motif2);
	
		/* AFFICHAGE A LA CONSOLE. */
		if (AFFICHAGE_GENERAL && affichage) {
			System.out.println();
			System.out.println("CAS SANS GROUPES CAPTURANTS -----------------------------");
			System.out.println(MOTIF + motif2);
			System.out.println();
			System.out.println(LISTE_GROUPES_CAPTURANT + explicateur.afficherListOccurences(list2));
		}
		
		/* garantit que trouverGroupesCapturantsInternes(motif) 
		 * retourne une liste vide si il n'y a pas de 
		 * groupe capturant dans le motif. */
		assertTrue(
				"la liste retournée doit être vide : "
					, list2.isEmpty());
		
	} // Fin de testTrouverGroupesCapturantsInternes().--------------------

	

} // FIN DE LA CLASSE ExplicateurRegexTest.----------------------------------
