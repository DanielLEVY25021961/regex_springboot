package levy.daniel.application.model.metier.regex;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * INTERFACE <b>IOccurence</b> :<br/>
 * Interface factorisant les comportements des Occurence concrets.<br/>
 * <br/>
 * Encapsulation et pure fabrication java.<br/>
 * Modélisation de l'occurence d'un motif RegEx Java dans un texte.<br/>
 * Par exemple : <br/>
 * pour le motif Regex Java <code>"avec"</code> 
 * dans le texte :<br/>
 * <b>avec</b> le temps, <b>avec</b> le temps va...<br/>
 * <b>Occurence</b> va servir à encapsuler toutes les occurences 
 * <b>(numéro, contenu, motifRegex, position de début, position de fin)</b> 
 * du motif "avec" dans le texte.<br/>
 * <br/>
 * <br/>
 * <img src="../../../../../../../../../javadoc/images/diagramme_classes_occurence.png" 
 * alt="diagramme des classes Occurence" border="1" align="center" />
 * <br/><br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code><i>// instanciation d'occurences dans la boucle 
 * de la méthode find() d'un Matcher.</i></code><br/>
 * <code>IOccurence occurence = new Occurence(i, "texte_trouvé", "motifRegex"
 * , positionDebut, positionFin);</code><br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 16 août 2018
 *
 */
public interface IOccurence extends 
	Comparable<IOccurence>, Cloneable
			, IExportateurCsv, IExportateurJTable {

	/**
	 * ';'.<br/>
	 */
	char POINT_VIRGULE = ';';
	
	/**
	 * NULL : String :<br/>
	 * "null".<br/>
	 */
	String NULL = "null";
	
	/**
	 * ", ".<br/>
	 */
	String VIRGULE = ", ";
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int hashCode();


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean equals(Object pObject);
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int compareTo(IOccurence pObject);
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();
	
	

	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête csv pour un Occurence</b> :<br/>
	 * "numéro;contenu;motifRegex;indexDebut;indexFin;".<br/>
	 * <br/>
	 */
	@JsonIgnore
	@Override
	String getEnTeteCsv();
	
	

	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne csv pour un Occurence</b> :<br/>
	 * "numéro;contenu;motifRegex;indexDebut;indexFin;".<br/>
	 * <br/>
	 */
	@Override
	String toStringCsv();
	
	

	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>en-tête Jtable pour un Occurence</b> :<br/>
	 * "numéro;contenu;motifRegex;indexDebut;indexFin;".<br/>
	 * <br/>
	 */
	@JsonIgnore
	@Override
	String getEnTeteColonne(int pI);
	
	

	/**
	 * {@inheritDoc}
	 * <br/>
	 * <b>ligne Jtable pour un Occurence</b> :<br/>
	 * "numéro;contenu;motifRegex;indexDebut;indexFin;".<br/>
	 * <br/>
	 */
	@JsonIgnore
	@Override
	Object getValeurColonne(int pI);
	
	

	/**
	 * Getter du numero d'ordre (1-based) de l'occurence 
	 * du motif dans un texte.<br/>
	 * (1ère occurence, 2ème occurence, ...).<br/>
	 *
	 * @return this.numero : int.<br/>
	 */
	int getNumero();
	
	

	/**
	 * Getter du contenu de l'occurence du motif dans le texte.<br/>
	 *
	 * @return this.contenu : String.<br/>
	 */
	String getContenu();
	
	
	
	/**
	 * Getter du motif Regex Java.<br/>
	 *
	 * @return this.motifRegex : String.<br/>
	 */
	String getMotifRegex();
	
	
	
	/**
	 * Getter de la position (0-based) du début 
	 * de l'occurence du motif.<br/>
	 *
	 * @return this.indexDebut : int.<br/>
	 */
	int getIndexDebut();
	
	

	/**
	 * Getter de la position (0-based) de la fin 
	 * de l'occurence du motif.<br/>
	 * <br/>
	 *
	 * @return this.indexFin : int.<br/>
	 */
	int getIndexFin();
	
	

} // FIn DE L'INTERFACE IOccurence.------------------------------------------