package br.com.dextra.bootcamp.MentorMatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String bio;

    private String knowledge;

    @ManyToMany
    private List<Mentored> likedMentored;

    @OneToMany(mappedBy = "mentor")
    private List<Mentored> mentored;

    public Mentor(MentorResponse mentorResponse) {
        this.id = mentorResponse.getId();
        this.name = mentorResponse.getName();
        this.bio = mentorResponse.getBio();
        this.knowledge = mentorResponse.getKnowledge();
        this.mentored = mentorResponse.getMentored();
    }
}
