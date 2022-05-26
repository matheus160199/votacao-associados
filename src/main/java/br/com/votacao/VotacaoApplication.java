package br.com.votacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@EnableKafka
@SpringBootApplication
public class VotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

	@KafkaListener(topics = "resultado-pautas-topic", groupId = "votacao")
	public void resultadoPautaListener(Object message) {
		System.out.println("Sessão de pauta finalizada: " + message);
	}
}
