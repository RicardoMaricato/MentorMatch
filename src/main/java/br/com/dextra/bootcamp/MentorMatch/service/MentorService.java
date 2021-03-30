package br.com.dextra.bootcamp.MentorMatch.service;

import br.com.dextra.bootcamp.MentorMatch.exception.UnexistentEntityException;
import br.com.dextra.bootcamp.MentorMatch.model.Mentor;
import br.com.dextra.bootcamp.MentorMatch.model.MentorResponse;
import br.com.dextra.bootcamp.MentorMatch.repository.MentorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MentorService {

    private final MentorRepository mentorRepository;

    public Mentor create(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public List<MentorResponse> list() {
        return mentorRepository.findAll().stream().map(mentor -> new MentorResponse(mentor))
                .collect(Collectors.toList());
    }

    public MentorResponse findOne(Long id) throws UnexistentEntityException {
        Mentor mentor = this.findOnDataBaseById(id);
        return new MentorResponse(mentor);
    }

    public Mentor update(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    public Mentor save(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    private Mentor findOnDataBaseById(Long id) throws UnexistentEntityException {
        Optional<Mentor> mentor = mentorRepository.findById(id);
        if(mentor.isEmpty()) {
            throw new UnexistentEntityException("O mentor não existe no banco de dados");
        }
        return mentor.get();
    }

    public Mentor patch(Long id, JsonPatch patch) throws UnexistentEntityException
            , JsonProcessingException, JsonPatchException {
        MentorResponse mentorResponse = this.findOne(id);
        Mentor mentor = new Mentor(mentorResponse);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode mentorJsonNode = objectMapper.convertValue(mentor, JsonNode.class);
        JsonNode patchJsonNode = patch.apply(mentorJsonNode);
        Mentor mentorPersist = objectMapper.treeToValue(patchJsonNode, Mentor.class);
        return this.save(mentorPersist);
    }
}

