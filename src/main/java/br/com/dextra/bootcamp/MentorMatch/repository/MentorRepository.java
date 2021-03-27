package br.com.dextra.bootcamp.MentorMatch.repository;

import br.com.dextra.bootcamp.MentorMatch.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {
}
