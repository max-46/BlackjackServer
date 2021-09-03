package dev.maxg.blackjackserver;


import com.google.gson.Gson;
import dev.maxg.blackjack.BlackjackGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Server {

	static BlackjackGame br;

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/numOfDecks")
	public String dealNewCards(@RequestParam(value = "numOfDecks", defaultValue = "3")int numOfDecks) {
		br = new BlackjackGame(numOfDecks);
		if (br.getDealer().getTotal() == 21) return new Gson().toJson(new Object[]{br.getPlayers(), br.getDealer()});
		return new Gson().toJson(new Object[]{br.getPlayers(), null});
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/hit")
	public String hit() {
		br.hit();
		if (br.getPlayer().hasGoneBust()) return new Gson().toJson(new Object[]{br.getPlayers(), br.getDealer()});
		return new Gson().toJson(new Object[]{br.getPlayers(), null});
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/stay")
	public String stay() {
		br.dealerPlay();
		return new Gson().toJson(new Object[]{br.getPlayers(), br.getWinner()});
	}
}
