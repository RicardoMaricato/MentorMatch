package br.com.dextra.bootcamp.MentorMatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorResponse {

    private String name;
    private String bio;
    private String knowledge;
    private List<Mentored> mentored;

    public MentorResponse(Mentor mentor) {
        this.name = name;
        this.bio = bio;
        this.knowledge = knowledge;
        this.mentored = mentored;
    }
}
