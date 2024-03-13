package com.comanda.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.comanda.model.dto.MarcaDto;
import com.comanda.model.input.MarcaInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
@Tag(name = "Marca")
public interface ControllerMarcaOpenApi extends ControllerCroossOring{
	
	@Operation(summary = "Listar Marcas")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Marca  Encotrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MarcaDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Marca não encontrada", content = @Content) })
	ResponseEntity<Page<MarcaDto>> listar(String parametro, Integer pagina, Integer size,
			Pageable page);

	@Operation(summary = "Excluir uma Marca por id")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Marca excluída"),
			@ApiResponse(responseCode = "404", description = "Marca não encontrada") })

	ResponseEntity<Void> remover(@Param(value = "ID de uma Marca") Long id);

	ResponseEntity<MarcaDto> buscar(@Param(value = "ID de um Marca") Long id);
	@Operation(summary = "Atualizar uma Marca ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Marca Atualizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	ResponseEntity<MarcaDto> Atualizar( @Param(value = "id")Long id,  @Param(value = "corpo") MarcaInput marca, HttpServletResponse response);

	@Operation(summary = "Salvar um Marca ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Marca salva com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquisão") })
	public ResponseEntity<MarcaDto> criar(@Param(value = "corpo") MarcaInput marca, HttpServletResponse response);

}
