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

	private static String sendResultsBeforeStayOrBust(BlackjackGame br) {
		Player winner = null;
		if (br.getDealer().has21()) winner = br.getDealer();
		else if (br.getPlayer().has21()) winner = br.getPlayer();
		return new Gson().toJson(new Object[]{br.getPlayers(), winner});
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/numOfDecks")
	public String dealNewCards(@RequestParam(value = "numOfDecks", defaultValue = "3")int numOfDecks) {
		bg = new BlackjackGame(numOfDecks);
		return sendResultsBeforeStayOrBust(bg);
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/hit")
	public String hit() {
		bg.hit();
		if (bg.getPlayer().hasGoneBust()) return new Gson().toJson(new Object[]{bg.getPlayers(), bg.getDealer()});
		return sendResultsBeforeStayOrBust(bg);
	}

	@CrossOrigin("http://localhost:3000")
	@GetMapping("/stay")
	public String stay() {
		bg.dealerPlay();
		return new Gson().toJson(new Object[]{bg.getPlayers(), bg.getWinner()});
	}
}
