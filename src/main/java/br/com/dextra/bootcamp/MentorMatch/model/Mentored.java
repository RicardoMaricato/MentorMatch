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
public class Mentored {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String bio;

    private String knowledge;

    @ManyToMany
    private List<Mentor> likedMentor;

    @ManyToOne
    private Mentor mentor;
}
