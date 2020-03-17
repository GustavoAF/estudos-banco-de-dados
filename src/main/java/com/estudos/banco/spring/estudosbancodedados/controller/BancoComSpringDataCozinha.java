package com.estudos.banco.spring.estudosbancodedados.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudos.banco.spring.estudosbancodedados.domain.model.Cozinha;
import com.estudos.banco.spring.estudosbancodedados.service.CozinhaService;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RestController (dois em um)
//Esta anotação é um resumo de duas anotaçoes que seriam @Controller e @ResponseBody, onde o @Controller 
//indica que é um controlador, utilizada para a camada de apresentação. Normalmente anotamos classes que representam a interação com o usuário, 
//como managed beans, actions e controllers.@ResponseBody significa que esta classe retorna informações e a resposta é exatamente o return do método.
//Usada para abreviar ou seja nao precisar usar duas anotações pode-se usar somente esta.
@RestController
@RequestMapping("/cozinha")             //@RequestMapping é a anotação que indica qual uri fará o mapeamento desse controlador, ou seja todas as
public class BancoComSpringDataCozinha {//requisições mapeadas com a palavra passada como parametro para essa anotação será direcionada para esse controlador.

	//Pelo fato do Spring instanciar os objetos de forma automática, é evidente que algum construtor seja executado, 
	//e por default será o construtor padrão, ou seja aquele herdado da classe Object, aquele que não possui nenhum parâmetro. 
	//Mas ai surge a dúvida, e quando tivermos mais de um construtor em nossa classe? qual o framework vai saber que deve ser executado?  
	//para isso temos a anotação @Autowired. Essa anotação informa qual o método deve ser executado no momento da instanciação do objeto.
	//Essa anotação pode ser usada em três momentos, no construtor na definição de algum atributo ou ate mesmo num método Seter.
	//Essa anotação também pode ser usada como responsável por informar ao spring que a Classe atual depende de outra, 
	//e por isso o framework deverá providenciar a instanciação desse objeto, todo controle dessa dependencia será feito pelo Spring 
	//e guardado no container.Nessa anotação também é possivel informar a prioridade da instanciação, que por default é true, 
	//ou seja essa dependencia é obrigatória, caso seja informado false (@Autowired(required=false)) significa que a instancia não é obrigatoria, 
	//caso o spring nao consiga instanciar não será exibido um erro, mas o tratamento deve ser feito pelo programador.
	@Autowired
	CozinhaService cozinhaService;
		
	
	//@GetMapping (Retorno esperado)
	//Esta anotação informa que requisições com o metodo Get chegaram nesse método, que é um verbo Get ou seja uma consulta.
	@GetMapping
	public ResponseEntity<List<Cozinha>>  getCozinhaSpringData(){
		
		return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listarCozinhas());
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Cozinha>> buscaCozinhaId(@PathVariable Long codigo){
		
		Optional<Cozinha> cozinha = cozinhaService.buscaId(codigo);
		
		if (!cozinha.isPresent())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
		else
			return ResponseEntity.status(HttpStatus.OK).body(cozinha);
	}
	
	@PostMapping  //Esta anotação indica ao controledor que o verbo passado no HTTP, é referente a inserção de dados.
	public ResponseEntity<Cozinha> gravaCozinha(@RequestBody Cozinha cozinhaInput){
		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaService.grava(cozinhaInput));
	}

	@PutMapping("/{codigo}")                      //A anotação @PutMapping indica ao controlador que o verbo passado no HTTP, é referente a uma alteração 
	public ResponseEntity<Cozinha> alteraPorId(   //de registro. Nesse caso todo o registro será alterado com as informações do request. 
			                                      //Por esse motivo, todos os campos devem ser informados.
			
			@PathVariable Long codigo,            //A anotação @PathVariable indica que o parâmetro será passado na url do recurso.
			@RequestBody Cozinha cozinha){        //A anotação @RequestBody indica que o parâmetro é um objeto é será passado no corpo da requisição http. 
		 
		 Cozinha cozinhaAtual = cozinhaService.atualiza(codigo,cozinha);
		
		if (cozinhaAtual != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Cozinha> alteraRestaurante(@RequestBody Cozinha cozinha){
		
		Cozinha cozi = new Cozinha();
		cozi = cozinhaService.atualiza(cozinha);
		if (cozi != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cozi);
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

		
	@DeleteMapping  //Esta anotação indica ao controlador que o verbo passado no http, é referente a deleção de algum registro.
	public ResponseEntity<Object> apaga(@RequestBody Cozinha cozinha){
		cozinhaService.deleta(cozinha);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Object> apagaNaUrl(@PathVariable Long codigo){
		cozinhaService.deleta(codigo);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
		
	
	@PatchMapping("/{idCozinha}") //@PathMapping esta anotação indica ao controlador que o verbo passado no HTTP, é referente a 
	                              //uma alteração de registro. Mas nesse caso somente os campos passados no body da requisição deve 
	                              //ser alterados. Por esse motivo só é necessário informar os campos que devem ser alterados, e não o objeto completo.
	public ResponseEntity<?> alteracaoParcial(@PathVariable Long idCozinha, @RequestBody Map<String, Object> campos){
		
		Optional<Cozinha> cozinhaAtual = cozinhaService.buscaId(idCozinha);
		
		if (!cozinhaAtual.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		merge(campos, cozinhaAtual);
		
		Cozinha cozinhaRetorno = new Cozinha();
		
		cozinhaRetorno = cozinhaService.atualiza(cozinhaAtual.get());
				
		if (cozinhaAtual.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cozinhaRetorno);
		}	
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	private void merge(Map<String, Object> dadosOrigem, Optional<Cozinha> cozinhaAtual) {
		ObjectMapper objectMapper = new ObjectMapper();
		Cozinha cozinhaOrigem = objectMapper.convertValue(dadosOrigem,Cozinha.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Cozinha.class, nomePropriedade);
			field.setAccessible(true);
			Object novoValor = ReflectionUtils.getField(field, cozinhaOrigem);
			ReflectionUtils.setField(field, cozinhaAtual.get(), novoValor);
		});
	}
	


}
