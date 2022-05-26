package br.com.votacao.application.usecase.impl;

import static br.com.votacao.infrastructure.util.RetrofitUtil.createRepository;
import java.io.IOException;
import br.com.votacao.application.usecase.CpfAPIUseCase;
import br.com.votacao.entrypoint.dto.ResponseCpfApiDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import br.com.votacao.domain.repository.CpfAPIRepository;
import retrofit2.Call;
import retrofit2.Response;

@Service
public class CpfAPIUseCaseImpl implements CpfAPIUseCase {

    @Value("${url.api.cpf}")
    private String urlApiCpf;

    @Override
    public void validaCpf(String cpf) {
        Response<ResponseCpfApiDTO> response = request(criaRepository().validaCpf(cpf));
        validaResponse(response);
    }

    private void validaResponse(final Response<ResponseCpfApiDTO> response) {
        ResponseCpfApiDTO dto = response.body();
        if (response == null)
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Erro ao validar CPF!");
        if (!response.isSuccessful())
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "CPF inválido!");
        if(dto.getStatus().equals("UNABLE_TO_VOTE"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF não está apto para voto!");
    }

    private CpfAPIRepository criaRepository() {
        return createRepository(urlApiCpf, CpfAPIRepository.class);
    }

    private Response<ResponseCpfApiDTO> request(Call<ResponseCpfApiDTO> call) {
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
