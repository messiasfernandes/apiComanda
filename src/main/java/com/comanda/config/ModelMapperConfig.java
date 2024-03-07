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
		Converter<Integer, SituacaoEstoque> SituacaoEstoqueConverter = ctx -> ctx.getSource() > 0
				? SituacaoEstoque.Disponvel
				: SituacaoEstoque.Estgotado;

		modelMapper.createTypeMap(Produto.class, ProdutoListagemDTo.class)
				.addMappings(mapper -> mapper.using(SituacaoEstoqueConverter).

						map(Produto::getQtdeEstoque, ProdutoListagemDTo::setSituacao));
		modelMapper.addMappings(new PropertyMap<Produto, ProdutoListagemDTo>() {

			@Override
			protected void configure() {
				String meuproduto = source.getNome();
				map().setProduto(meuproduto);
				map().setPrecovenda(source.getPreco().getPrecovenda());

			}
		});
	
		return modelMapper;
	}

}
