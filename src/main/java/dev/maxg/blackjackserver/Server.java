package dev.maxg.blackjackserver;


import com.google.gson.Gson;
import dev.maxg.blackjack.BlackjackGame;
import dev.maxg.blackjack.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Server {

	static BlackjackGame bg;

	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

	private static String sendPreStayResults(BlackjackGame bg) {
		Player winner = null;
		if (bg.getDealer().has21() || bg.getPlayer().hasGoneBust()) winner = bg.getDealer();
		else if (bg.getPlayer().has21()) winner = bg.getPlayer();
		return new Gson().toJson(new Object[]{bg.getPlayers(), winner});
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/numOfDecks")
	public String dealNewCards(@RequestParam(value = "numOfDecks", defaultValue = "3")int numOfDecks) {
		bg = new BlackjackGame(numOfDecks);
		return sendPreStayResults(bg);
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/hit")
	public String hit() {
		bg.hit();
		return sendPreStayResults(bg);
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/stay")
	public String stay() {
		bg.dealerPlay();
		return new Gson().toJson(new Object[]{bg.getPlayers(), bg.getWinner()});
	}
}
