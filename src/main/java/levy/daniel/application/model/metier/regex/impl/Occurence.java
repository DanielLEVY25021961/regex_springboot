package levy.daniel.application.model.metier.regex.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.regex.IOccurence;


/**
 * CLASSE <b>Occurence</b> :<br/>
 * Encapsulation et pure fabrication java.<br/>
 * Modélisation de l'occurence d'un motif RegEx Java dans un texte.<br/>
 * Par exemple : <br/>
 * pour le motif Regex Java <code>"avec"</code> 
 * dans le texte :<br/>
 * <b>avec</b> le temps, <b>avec</b> le temps va...<br/>
 * <b>Occurence</b> va servir à encapsuler toutes les occurences 
 * <b>(numéro, contenu, motifRegx, position de début, position de fin)</b> 
 * du motif "avec" dans le texte.<br/>
 * <br/>
 * <br/>
 * <img src="../../../../../../../../../../javadoc/images/diagramme_classes_occurence.png" 
 * alt="diagramme des classes Occurence" border="1" align="center" />
 * <br/><br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code><i>// instanciation d'occurences dans la boucle 
 * de la méthode find() d'un Matcher.</i></code><br/>
 * <code>IOccurence occurence = new Occurence(i, "texte_trouvé"
 * , "motifRegex", positionDebut, positionFin);</code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Expression régulière, expression reguliere, RegEx, Regex, regex, <br/>
 * pure fabrication, encapsulation, ordre occurence, ordre apparition, <br/>
 * position occurence, début occurence, debut occurence, motif, pattern,<br/>
 * fin occurence, position fin, position début, <br/>
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
public class Occurence implements IOccurence {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * numero d'ordre (1-based) de l'occurence 
	 * du motif dans un texte.<br/>
	 * (1ère occurence, 2nde occurence, ...)
	 */
	public transient int numero;
	
	/**
	 * contenu de l'occurence du motif dans le texte.<br/>
	 */
	public transient String contenu;
	
	/**
	 * motif Regex Java.<br/>
	 */
	public transient String motifRegex;
	
	/**
	 * position (0-based) du début de l'occurence du motif.<br/>
	 */
	public transient int indexDebut;
	
	/**
	 * position (0-based) de la fin de l'occurence du motif.<br/>
	 */
	public transient int indexFin;
	
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(Occurence.class);


	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pNumero : int : 
	 * numero d'ordre (1-based) de l'occurence 
	 * du motif dans un texte.<br/>
	 * (1ère occurence, 2nde occurence, ...)
	 * @param pContenu : String : 
	 * contenu de l'occurence du motif dans le texte.<br/>
	 * @param pMotifRegex : String : motif Regex Java.<br/> 
	 * @param pIndexDebut : int : 
	 * position (0-based) du début de l'occurence du motif.<br/>
	 * @param pIndexFin : int : 
	 * position (0-based) de la fin de l'occurence du motif.<br/>
	 */
	public Occurence(
			final int pNumero
				, final String pContenu
					, final String pMotifRegex
						, final int pIndexDebut
							, final int pIndexFin) {
		
		super();
		
		this.numero = pNumero;
		this.contenu = pContenu;
		this.motifRegex = pMotifRegex;
		this.indexDebut = pIndexDebut;
		this.indexFin = pIndexFin;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.numero;
		result = prime * result + ((this.contenu == null) ? 0 : this.contenu.hashCode());		
		return result;
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof Occurence)) {
			return false;
		}
		
		final Occurence other = (Occurence) pObject;
		
		if (this.contenu == null) {
			if (other.contenu != null) {
				return false;
			}
		}
		else if (!this.contenu.equals(other.contenu)) {
			return false;
		}
		if (this.numero != other.numero) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final IOccurence pObject) {
		
		if (this == pObject) {
			return 0;
		}

		if (pObject == null) {
			return -1;
		}
		
		int compareContenu = 0;
		int compareNumero = 0;
		
		if (this.getContenu() == null) {
			if (pObject.getContenu() != null) {
				return +1;
			}
		} else {
			if (pObject.getContenu() == null) {
				return -1;
			}
		}
		
		if (this.getContenu() != null) {
			
			compareContenu 
			= this.getContenu().compareToIgnoreCase(pObject.getContenu());
		
			if (compareContenu != 0) {
				return compareContenu;
			}
			
		}
				
		compareNumero = this.getNumero() - pObject.getNumero();
		
		return compareNumero;
		
	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Occurence clone() throws CloneNotSupportedException {
		
		final Occurence occurenceClone = (Occurence) super.clone();
		
		occurenceClone.numero = this.numero;
		occurenceClone.contenu = this.contenu;
		occurenceClone.motifRegex = this.motifRegex;
		occurenceClone.indexDebut = this.indexDebut;
		occurenceClone.indexFin = this.indexFin;
		
		return occurenceClone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("Occurence [numero=");
		builder.append(this.numero);
		
		builder.append(VIRGULE);
		
		builder.append("contenu=");
		if (this.contenu != null) {			
			builder.append(this.contenu);
			
		} else {
			builder.append(NULL);
		}
		builder.append(VIRGULE);
		
		builder.append("motifRegex=");
		if (this.motifRegex != null) {			
			builder.append(this.motifRegex);
			
		} else {
			builder.append(NULL);
		}
		builder.append(VIRGULE);
		
		builder.append("indexDebut=");
		builder.append(this.indexDebut);
		
		builder.append(", indexFin=");
		builder.append(this.indexFin);
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEnTeteCsv() {
		return "numéro;contenu;motifRegex;indexDebut;indexFin;";
	} // Fin de getEnTeteCsv().____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toStringCsv() {

		final StringBuilder stb = new StringBuilder();
		
		/* numero. */
		stb.append(this.numero);
		stb.append(POINT_VIRGULE);
		
		/* contenu. */
		stb.append(this.contenu);
		stb.append(POINT_VIRGULE);
		
		/* motifRegex. */
		stb.append(this.motifRegex);
		stb.append(POINT_VIRGULE);
		
		/* indexDebut. */
		stb.append(this.indexDebut);
		stb.append(POINT_VIRGULE);
		
		/* indexFin. */
		stb.append(this.indexFin);
		stb.append(POINT_VIRGULE);
		
		return stb.toString();
		
	} // Fin de toStringCsv()._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEnTeteColonne(
			final int pI) {

		String entete = null;

		switch (pI) {

		case 0:
			entete = "numéro";
			break;
			
		case 1:
			entete = "contenu";
			break;
		
		case 2:
			entete = "motifRegex";
			break;
			
		case 3:
			entete = "indexDebut";
			break;
			
		case 4:
			entete = "indexFin";
			break;
			
		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de getEnTeteColonne(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object getValeurColonne(
			final int pI) {

		Object valeur = null;

		switch (pI) {

		case 0:
			valeur = String.valueOf(this.numero);			
			break;

		case 1:
			if (this.contenu != null) {
				valeur = this.contenu;
			}			
			break;
			
		case 2:
			if (this.motifRegex != null) {
				valeur = this.motifRegex;
			}			
			break;
			
		case 3:
			valeur = String.valueOf(this.indexDebut);
			break;
			
		case 4:
			valeur = String.valueOf(this.indexFin);
			break;
			
		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;
		
	} // Fin de getValeurColonne(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumero() {
		return this.numero;
	} // Fin de getNumero()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getContenu() {
		return this.contenu;
	} // Fin de getContenu().______________________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMotifRegex() {
		return this.motifRegex;
	} // Fin de getMotifRegex().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getIndexDebut() {
		return this.indexDebut;
	} // Fin de getIndexDebut().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getIndexFin() {
		return this.indexFin;
	} // Fin de getIndexFin()._____________________________________________


	
} // FIN DE LA CLASSE Occurence.---------------------------------------------
