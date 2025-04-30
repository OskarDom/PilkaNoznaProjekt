package pl.wasko.praktyki.odomanski;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.wasko.praktyki.odomanski.model.Club;
import pl.wasko.praktyki.odomanski.model.Player;
import pl.wasko.praktyki.odomanski.model.Transaction;
import pl.wasko.praktyki.odomanski.repository.ClubRepository;
import pl.wasko.praktyki.odomanski.repository.PlayerRepository;
import pl.wasko.praktyki.odomanski.repository.TransactionRepository;

import java.util.List;

@SpringBootApplication
class PilkaNoznaProjektApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(PilkaNoznaProjektApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClubRepository klubRepo, PlayerRepository pilkarzRepo, TransactionRepository transakcjaRepo) {
		return args -> {
			// KLUBY
			Club barcelona = new Club("Barcelona", "Hiszpania");
			Club nassr = new Club("All-Nassr", "Arabia Saudyjska");
			Club interMiami = new Club("Inter Miami", "Ameryka");
			Club plymouth = new Club("Plymouth Argyle F.C.", "Anglia");

			klubRepo.saveAll(List.of(barcelona, nassr, interMiami, plymouth));

			// PIŁKARZE
			Player yamal = new Player("Lamine", "Yamal", 17, barcelona);
			Player lewy = new Player("Robert", "Lewandowski", 36, barcelona);
			Player puchacz = new Player("Tymoteusz", "Puchacz", 26, plymouth);
			Player ronaldo = new Player("Cristiano", "Ronaldo", 40, nassr);
			Player messi = new Player("Lionel", "Messi", 37, interMiami);

			pilkarzRepo.saveAll(List.of(yamal, lewy, puchacz, ronaldo, messi));

			// TRANSAKCJE
			Transaction t1 = new Transaction("Lionel Messi", "Barcelona", "PSG", 30.0);
			Transaction t2 = new Transaction("Cristiano Ronaldo", "Wolny Zawodnik", "All-Nassr", 20.0);
			Transaction t3 = new Transaction("Lamine Yamal", "Barcelona", "Barcelona", 0.0);
			Transaction t4 = new Transaction("Robert Lewandowski", "Bayern Monachium", "Barcelona", 45.0);
			Transaction t5 = new Transaction("Tymoteusz Puchacz", "Holstein Kiel", "Plymouth Argyle F.C.", 1.7);

			transakcjaRepo.saveAll(List.of(t1, t2, t3, t4, t5));
		};
	}
}

