package com.comanda.api;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.comanda.domain.enumerado.ControlarEstoque;
import com.comanda.domain.enumerado.Operacao;
import com.comanda.model.dto.EstoqueMovimentoDTo;
import com.comanda.model.input.EstoqueMoviemtoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@Tag(name = "movimentações")
public interface EstoqueMovimentocontrollerOpenApi extends ControllerCroossOring {
	@Operation(summary = "Listar MOvimentações")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Movimentação não   Encotrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueMovimentoDTo.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Movimentação não encontrada", content = @Content) })
	public ResponseEntity<Page<EstoqueMovimentoDTo>> listar(String parametro, Operacao tipoOperacao,
			LocalDate datainicio, LocalDate datafim, Integer pagina, Integer size, Pageable page);
	@Operation(summary = "Salvar uma Movimentação ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Movimentação salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquição") })
	public ResponseEntity<EstoqueMovimentoDTo > adicionar( EstoqueMoviemtoInput movimentacoes ,
			  ControlarEstoque controlarEstoque, HttpServletResponse response);

	
	ResponseEntity<EstoqueMovimentoDTo> detallhar(@Param(value = "ID de um Produto") Long id);
}
