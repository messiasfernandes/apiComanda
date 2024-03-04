package com.comanda.converter;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.stereotype.Component;

import com.comanda.domain.entity.Produto;
import com.comanda.model.dto.ProdutoDto;
import com.comanda.model.dto.ProdutoListagemDTo;
import com.comanda.model.input.ProdutoInput;
import com.comanda.model.input.produtoDetalheInput;
import com.comanda.model.recorddto.DetalharProdutoR;
import com.comanda.model.recorddto.ProdutoListaDtoR;

@Component
public class ProdutoConverter {
	@Autowired
	private ModelMapper modelMapper;
	 private TypeMap<Produto, ProdutoListagemDTo> typeMap;
	public ProdutoDto toDtoDetalhe(Produto objeto) {

		return modelMapper.map(objeto, ProdutoDto.class);
			//	new DetalharProdutoR(objeto);
				//modelMapper.map(objeto, ProdutoDto.class);
	}
	public ProdutoListagemDTo toDto(Produto objeto) {
		
	
//	    PropertyMap<Produto, ProdutoListagemDTo> customMapping = new PropertyMap<Produto, ProdutoListagemDTo>() {
//	        protected void configure() {
//	            map().setProdutonom((source.getNome() !=null ? source.getNome(): null));    
////	            if (source.getMarca() != null) {
////	                map().setMarca(source.getMarca() != null ? source.getMarca().getNomeMarca() : null);
////           }
//////	            if (source.getPreco() != null && source.getPreco().getPrecovenda() != null) {
////	                map().setPrecovenda(source.getPreco().getPrecovenda());
////	            }
////	            if (source.getSubgrupo() != null && source.getSubgrupo().getNomeSubgrupo() != null) {
////	                map().setSubgrupo(source.getSubgrupo().getNomeSubgrupo());
////	            }
//       }
//	    };
//
//	    modelMapper.addMappings(customMapping);
		
	      if (typeMap == null) {
	            typeMap = modelMapper.createTypeMap(Produto.class, ProdutoListagemDTo.class);
	            typeMap.<String>addMapping(src -> src.getNome() != null ? src.getNome() : null,
	                                            (det, valor) -> det.setProduto(valor));
	            // Adicione outros mapeamentos, se necessário
//	            if (objeto.getMarca() != null) {
//	            typeMap.<String>addMapping(src -> src.getMarca() != null ? src.getMarca().getNomeMarca() : null,
//                        (det, valor) -> det.setMarca(valor));
//	            }
	        }
	    
	    return modelMapper.map(objeto, ProdutoListagemDTo.class);
	}
	public Produto toEntity(ProdutoInput objeto) {
		return modelMapper.map(objeto, Produto.class);
	}

	public Page<ProdutoListagemDTo> topage(Page<Produto> objetos) {

		return objetos.map(obj -> toDto(obj));
	}
	
	public ProdutoListaDtoR toRecDTo(Produto produto) {
		return new ProdutoListaDtoR(produto);
	}
	public Page<ProdutoListaDtoR> topageRecDto(Page<Produto> objetos) {

		return objetos.map(obj -> toRecDTo(obj));
	}
	public DetalharProdutoR  toRecDto(Produto produto) {
		return new DetalharProdutoR(produto);
	}
}
