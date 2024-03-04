package com.comanda.config;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.comanda.domain.entity.Estoque;
import com.comanda.domain.entity.Produto;
import com.comanda.domain.enumerado.SituacaoEstoque;
import com.comanda.model.dto.ProdutoListagemDTo;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper modelMapper() {
    	
    	
    	var modelMapper = new ModelMapper();
    	
    
		modelMapper.addMappings(new PropertyMap<Produto, ProdutoListagemDTo>() {

			@Override
			protected void configure() {
			      String meuproduto = source.getNome();
			      map().setProduto(meuproduto);
			      map().setPrecovenda( source.getPreco().getPrecovenda() );
			     
			      
			}
		});
		Converter<Integer, SituacaoEstoque> SituacaoEstoqueConverter =ctx
    			->ctx.getSource()>0 ?SituacaoEstoque.Disponviel: SituacaoEstoque.Estgotado;
    			
    			modelMapper.createTypeMap(Estoque.class, ProdutoListagemDTo.class)
    			.addMappings(mapper-> mapper.using(SituacaoEstoqueConverter).

    			map(Estoque::getQuantidade, ProdutoListagemDTo:: setSituacao));
		return modelMapper;
	}
	
}
