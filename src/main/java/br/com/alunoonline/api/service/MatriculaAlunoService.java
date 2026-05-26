package br.com.alunoonline.api.service;

import br.com.alunoonline.api.MatriculaAlunoStatusEnum;
import br.com.alunoonline.api.dtos.AtualizarNotasRequestDTO;
import br.com.alunoonline.api.model.MatriculaAluno;
import br.com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MatriculaAlunoService {
    @Autowired
    MatriculaAlunoRepository matriculaAlunoRepository;

    private static final Double MEDIA_PARA_APROVACAO = 7.0;

    public void atualizarNotas(Long id, AtualizarNotasRequestDTO dto) {

        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        if (dto.getNota1() != null)
            matriculaAluno.setNota1(dto.getNota1());
        if (dto.getNota2() != null)
            matriculaAluno.setNota2(dto.getNota2());

        if (matriculaAluno.getNota1() != null
            && matriculaAluno.getNota2() != null) {
            Double media = (matriculaAluno.getNota1() + matriculaAluno.getNota2()) / 2;
            matriculaAluno.setStatus(
                    media >= MEDIA_PARA_APROVACAO
                            ? MatriculaAlunoStatusEnum.APROVADO
                            : MatriculaAlunoStatusEnum.REPROVADO);

        }

        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void criarMatricula(MatriculaAluno matriculaAluno) {
        matriculaAluno.setStatus(MatriculaAlunoStatusEnum.MATRICULADO);
        matriculaAlunoRepository.save(matriculaAluno);
    }

    public void trancarMatricula(Long id) {
        MatriculaAluno matriculaAluno = matriculaAlunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Matricula não encontrada!"));

        if (matriculaAluno.getStatus()
                .equals(MatriculaAlunoStatusEnum.MATRICULADO)) {
            matriculaAluno.setStatus(
                    MatriculaAlunoStatusEnum.TRANCADO);
            matriculaAlunoRepository.save(matriculaAluno);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Só é possivel trancar com status MATRICULADO");
        }
    }
}