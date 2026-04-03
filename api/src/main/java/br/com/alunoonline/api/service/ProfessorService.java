package br.com.alunoonline.api.service;

import br.com.alunoonline.api.model.Professor;
import br.com.alunoonline.api.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

=======
>>>>>>> 25177471b13ca1908837cb4f81c90a4fc4706492
@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public void criarProfessor(Professor professor) {
        professorRepository.save(professor);
    }
<<<<<<< HEAD

    public List<Professor> listarTodosProfessores() {
        return professorRepository.findAll();
    }

    public Optional<Professor>  buscarProfessorPorId(Long id) {
        return professorRepository.findById(id);
    }

    public void deletarProfessorPorId(Long id) {
        professorRepository.deleteById(id);
    }

    public void atualizarProfessorPorId(Long id, Professor professorEditado){
        professorEditado.setId(id);
        professorRepository.save(professorEditado);
    }
=======
>>>>>>> 25177471b13ca1908837cb4f81c90a4fc4706492
}
