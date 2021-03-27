package br.com.dextra.bootcamp.MentorMatch.repository;

import br.com.dextra.bootcamp.MentorMatch.model.Mentored;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentoredRepository extends JpaRepository<Mentored, Integer> {
}
