package com.comanda.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.comanda.domain.entity.SubGrupo;
import com.comanda.model.dto.SubGrupoDTO;
import com.comanda.model.input.SubGrupoInput;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Subgrupos")
public interface SubGrupoControllerOpenApi extends ControllerCroossOring {
	@Operation(summary = "Listar Subcategoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "SubGrupo  Encotrada", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = SubGrupo.class)) }),
			@ApiResponse(responseCode = "400", description = "Requisão Invaldia", content = @Content),
			@ApiResponse(responseCode = "404", description = "Subgrupo não encontrada", content = @Content) })
	ResponseEntity<Page<SubGrupoDTO>> listar(String parametro, Integer pagina, Integer size,
			Pageable page);

	@Operation(summary = "Excluir um Sub Grupo por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Subcategoria excluído"),
			@ApiResponse(responseCode = "404", description = "SubGrupo não encontrado") })

	ResponseEntity<Void> remover(@Param(value = "ID de uma Subcategoria") Long id);

	ResponseEntity<SubGrupoDTO> buscar(@Param(value = "ID de um SubGrupo") Long id);
	@Operation(summary = "Atualizar um SubGrupo ")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "SubGrupo Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquição") })
	ResponseEntity<SubGrupoDTO> Atualizar( @Param(value = "id")Long id,  @Param(value = "corpo") SubGrupoInput subgrupo);

	@Operation(summary = "Salvar um SubGrupo ")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "SubGrupo salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "problema com resquição") })
	public ResponseEntity<SubGrupoDTO> criar(@Param(value = "corpo") SubGrupoInput subgrupo);

}
