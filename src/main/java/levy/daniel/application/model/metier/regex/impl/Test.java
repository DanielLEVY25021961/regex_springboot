package levy.daniel.application.model.metier.regex.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CLASSE Test :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 18 août 2018
 *
 */
public final class Test {
	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(Test.class);


	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	private Test() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * .<br/>
	 * <ul>
	 * <li>.</li>
	 * </ul>
	 *
	 * @param args :  :  .<br/>
	 */
	public static void main(final String[] args) {
		
		final String motifRegex = "<a(?:.*)href=\"(.*?)\"(?:.*)>";
		
		final Pattern pattern = Pattern.compile(motifRegex);
		
		final String[] data 
			= {"<a href=\"http://www.google.be\" rel=\"popup\" class=\"extlink\">",
		        "<a target=\"_blank\" href=\"http://www.yahoo.be\" class=\"extlink\" rel=\"popup\">",
		        "<a href=\"http://www.alltheweb.com\">"};
		
		for (final String s : data) {
			
		    final Matcher matcher = pattern.matcher(s);
		    
		    if (matcher.matches())
		    {
		        System.out.println("OK");
		        for (int i = 1; i <= matcher.groupCount(); i++)
		        {
		            System.out.println(matcher.group(i));
		        }
		    }
		}
	}

	
	
}
