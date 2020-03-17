package com.estudos.banco.spring.estudosbancodedados.configuratios;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.estudos.banco.spring.estudosbancodedados.applicationproperties.ApplicationConfigurationsBD;
import com.zaxxer.hikari.HikariDataSource;

//A anotação @Configuration indica que determinada classe possui métodos que expõe novos beans. 
//Geralmente é usado para classes que expõem algum objeto de configuração.
@Configuration
public class DataSourceConfiguration {
	
	@Autowired
	ApplicationConfigurationsBD applicationConfigurationDB;
	
	//Para gerenciar as dependencias no momento da execução da aplicação e não na hora da 
	//compilação, podemos usar a anotação @Bean, que basicamente seria uma anotação de um método que retorna um objeto 
	//que a partir desse momento será gerenciado pelo Spring.
	//Nesses casos é obrigação do programador instanciar o objeto, pois só depois disso ele será gerenciado pelo framework.
	//Por exmplo, no construtor da nossa classe Pessoa, podemos chamar um método que retorna uma Classe cliente totalmente 
	//configurada passando valores aos seus atributos. Esse método pode ser anotado com @Bean.
	//Outra utilidade importante seria anotar os métodos de configuração da aplicação.	
	@Bean
	public DataSource dataSource(DataSourceProperties dataSourceProperties) {
		
		return DataSourceBuilder.create()
					.type(HikariDataSource.class)
					.url(applicationConfigurationDB.getDataSourceUrl())
					.username(applicationConfigurationDB.getDataSourceUser())
					.password(applicationConfigurationDB.getDataSourcePassword())
					.build();
		
	}

}
