package pl.wasko.praktyki.odomanski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wasko.praktyki.odomanski.model.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
