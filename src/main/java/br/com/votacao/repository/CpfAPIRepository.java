package br.com.votacao.repository;

import br.com.votacao.dto.ResponseCpfApiDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CpfAPIRepository {

    @GET("/users/{cpf}")
    Call<ResponseCpfApiDTO> validaCpf(@Path("cpf") final String cpf);
}
