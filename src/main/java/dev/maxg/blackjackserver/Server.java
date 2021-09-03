package dev.maxg.blackjackserver;


import com.google.gson.Gson;
import dev.maxg.blackjack.BlackjackGame;
import dev.maxg.blackjack.Card;
import dev.maxg.blackjack.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class Server {

	static BlackjackGame br;

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/numOfDecks")
	public String myResponse(@RequestParam(value = "numOfDecks", defaultValue = "3")int numOfDecks) {
		br = new BlackjackGame(numOfDecks);
		return new Gson().toJson(new Player[]{br.getPlayer(), br.getDealer()});
	}
}
