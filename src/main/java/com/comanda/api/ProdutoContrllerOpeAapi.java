package com.comanda.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.comanda.domain.entity.Produto;
import com.comanda.model.dto.ProdutoDto;
import com.comanda.model.input.ProdutoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Produtos")
public interface ProdutoContrllerOpeAapi extends ControllerCroossOring {

	@Operation(summary = "Listar Produtos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto  Encotrado", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content) })
	ResponseEntity<Page<ProdutoDto>> listar(String parametro, Integer pagina, Integer size, Pageable page);
	
	@Operation(summary = "Salvar um Produto ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<ProdutoDto> criar(@Param(value = "corpo") ProdutoInput produto);
	ResponseEntity<ProdutoDto> buscar(@Param(value = "ID de um Produto") Long id);
	@Operation(summary = "Atualizar um Produto ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<ProdutoDto> Atualizar( @Param(value = "id")Long id,  @Param(value = "corpo") ProdutoInput produto);
	
	ResponseEntity<Void> remover(@Param(value = "ID de uma Produto") Long id);
}
