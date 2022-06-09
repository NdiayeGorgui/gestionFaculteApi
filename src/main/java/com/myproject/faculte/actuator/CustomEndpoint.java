package com.myproject.faculte.actuator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/*Spring appelle les méthodes annotées avec @PostConstruct une seule fois,
 *  juste après l'initialisation des propriétés du bean . 
 * Gardez à l'esprit que ces méthodes s'exécuteront même s'il n'y a rien à initialiser
 * La méthode annotée avec @PostConstruct peut avoir n'importe quel niveau d'accès, mais elle ne peut pas être statique.

Une utilisation possible de @PostConstruct est de remplir une base de données. Par exemple, pendant le développement,
 nous pourrions vouloir créer des utilisateurs par défaut 
*/

//cette classe permet d'établir les versions de notre application

@Component
@Endpoint(id="release-notes")
public class CustomEndpoint {
	
	Map<String,List<String>>releaseNoteMap=new LinkedHashMap<>();
	
	@PostConstruct
	public void initNotes() {
		releaseNoteMap.put("version 1.0", Arrays.asList("création de l'application","création de la base de données"));
		releaseNoteMap.put("version 1.1", Arrays.asList("création des test controller","création des test jpa","création des test service"));
		releaseNoteMap.put("version 1.2", Arrays.asList("création du package config pour swagger","création du package exception pour gérerer les excetions","création du controller advice pour centraliser les exceptions" ));
		releaseNoteMap.put("version 1.3", Arrays.asList("création du package sécurity pour sécuriser les api","création jwt"));
		releaseNoteMap.put("version 2.0", Arrays.asList("création du package actuator","ajout des classes customEndpoint et Enseignant statusCountContributor  de actuator/info"));
		
	}
	@ReadOperation
	public Map<String,List<String>>getReleaseNoteMap(){
		return releaseNoteMap;
	}
	
	@ReadOperation
	public List<String>getNotesByVersion(@Selector String version){
		return releaseNoteMap.get(version);
	}
	
	@WriteOperation
	public void addReleaseNotes(@Selector String version, String releaseNotes) {
		releaseNoteMap.put(version, Arrays.stream(releaseNotes.split(",")).collect(Collectors.toList()));
		
		
	}
	
	@DeleteOperation
	public void deleteReleaseNotes(@Selector String version) {
		releaseNoteMap.remove(version);
		
		
	}

}
