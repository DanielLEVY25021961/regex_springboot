package levy.daniel.application.controllers.web.metier.regex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import levy.daniel.application.controllers.web.dto.RegexDto;
import levy.daniel.application.controllers.web.dto.RequeteDto;
import levy.daniel.application.model.services.metier.regex.IRegexService;
import levy.daniel.application.model.services.metier.regex.impl.RegexService;

/**
 * CLASSE RegexController :<br/>
 * CONTROLLER REST.<br/>
 * l'annotation RestController permet à Spring 
 * de détecter que le présent CONTROLLER est Rest 
 * (produit automatiquement du JSON).<br/>
 * l'annotation RequestMapping sert à indiquer 
 * la route de base du CONTROLLER.<br/>
 * <br/>
 * On va créer une route POST chargée de l'envoi des données de la VUE (texte, motif) au présent CONTROLLER.<br/>
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
@RestController
@RequestMapping("/regex")
public class RegexController {

	// ************************ATTRIBUTS************************************/

	/**
	 * .<br/>
	 */
	private IRegexService regexService;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(RegexController.class);

	// *************************METHODES************************************/

	
	
	
	/**
	 * .<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 *
	 * @param pRequeteDto
	 * @return RegexDto
	 * @throws Exception : RegexDto :  .<br/>
	 */
	/* précise qu'au sein de la route /regex, la route /valider passée 
	 * dans une méthode POST permet d'appeler la méthode. */
	@RequestMapping(value = "/valider", method = RequestMethod.POST)
	public RegexDto valider(
			@RequestBody final RequeteDto pRequeteDto) throws Exception {
		
		final String texte = pRequeteDto.getTexte();
		final String motif = pRequeteDto.getMotifJava();
		
		this.regexService = new RegexService();
		
		this.regexService.setTexte(texte);
		this.regexService.setMotifJava(motif);
		
		final RegexDto regexDto = new RegexDto(this.regexService);
		
		return regexDto;
		
	}

	
	
}

