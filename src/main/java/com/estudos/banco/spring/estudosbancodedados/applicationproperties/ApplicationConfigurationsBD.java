package com.estudos.banco.spring.estudosbancodedados.applicationproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

//A anotação @Component, é usada a nível de classe, sempre que usada o Spring irá definir o objeto como um 
//Bean gerenciado por ele, e será instanciado no momento da compilação e suas dependencias controladas pelo mesmo.
//Por exemplo se anotarmos nossa classe Cliente e Pessoa com @Component, em nenhum momento será necessário instanciar tais objetos. 
//E a ordem de instanciação será respeitada de acordo com a necessidade.
@Component
@Getter  // Esta anotação faz parte da biblioteca lombok
@Setter  // Esta anotação faz parte da biblioteca lombok
public class ApplicationConfigurationsBD {
	
	@Value("${url_banco}")
	private String dataSourceUrl;
	
	@Value("${username_banco}")
	private String dataSourceUser;
	
	@Value("${password_banco}")
	private String dataSourcePassword;
	

}
