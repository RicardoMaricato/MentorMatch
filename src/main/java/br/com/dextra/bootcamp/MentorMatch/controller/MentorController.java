package br.com.dextra.bootcamp.MentorMatch.controller;

import br.com.dextra.bootcamp.MentorMatch.exception.UnexistentEntityException;
import br.com.dextra.bootcamp.MentorMatch.model.Mentor;
import br.com.dextra.bootcamp.MentorMatch.model.MentorResponse;
import br.com.dextra.bootcamp.MentorMatch.service.MentorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/mentors")
public class MentorController {

    private final MentorService mentorService;

    @GetMapping
    @ApiOperation(value = "Listar mentores")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Listar mentores", response = MentorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Não foi encontrado o Mentores", response = MentorResponse.class)
    })
    public ResponseEntity<List<MentorResponse>> list() {
        List<MentorResponse> returnedList = mentorService.list();
        if(returnedList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(returnedList);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Mentor por Id")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Mentor por id", response = MentorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Não foi possível encontrar o mentor especificado", response = MentorResponse.class)
    })
    public ResponseEntity<Object> findOne(@PathVariable(name = "id") Long id) {
        try {
            MentorResponse mentorResponse = mentorService.findOne(id);
            return ResponseEntity.ok(mentorResponse);
        } catch (UnexistentEntityException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    @ApiOperation(value = "Criar mentor")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Mentor criado", response = Long.class),
    })
    public ResponseEntity<Long> create(@RequestBody Mentor mentor) {
        Mentor mentorResponse = mentorService.create(mentor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mentorResponse.getId());
    }

    @PutMapping
    @ApiOperation(value = "Alteração do mentor")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Mentor alterado", response = Long.class),
    })
    public ResponseEntity<Long> update(@PathVariable(name = "id")Long id, @RequestBody Mentor mentor) {
        mentor.setId(id);
        Mentor returnedMentor = mentorService.update(mentor);
        return ResponseEntity.status(HttpStatus.OK).body(returnedMentor.getId());
    }

    @DeleteMapping
    @ApiOperation(value = "Deleta mentor")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Mentor deletado", response = Long.class),
    })
    public ResponseEntity<Void> delete(@PathVariable(name = "id")Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
