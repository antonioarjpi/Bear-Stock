package simpledev.beerstock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import simpledev.beerstock.domain.model.Beer;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByName(String name);
}
