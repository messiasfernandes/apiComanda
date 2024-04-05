package com.comanda.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.comanda.model.dto.ProdutoDetalheDtoLista;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "ProdutosDetalhe")
public interface ProdutoDetalheContrllerOpeAapi  extends ControllerCroossOring {
	@Operation(summary = "Listagem Produtos Detalhados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDetalheDtoLista.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content) })
	ResponseEntity<Page<ProdutoDetalheDtoLista>> listar(String parametro, Integer pagina, Integer size, Pageable page);
}
