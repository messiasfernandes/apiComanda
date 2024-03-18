package com.comanda.domain.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.comanda.domain.entity.Componente;
import com.comanda.domain.entity.Estoque;
import com.comanda.domain.entity.EstoqueMovimento;
import com.comanda.domain.enumerado.TipoMovimentacao;
import com.comanda.domain.enumerado.TipoProduto;
import com.comanda.domain.repository.MovimentoEstoqueRepository;
import com.comanda.domain.service.exeption.NegocioException;
import com.comanda.utils.ServiceFuncoes;
import com.comanda.utils.TolowerCase;

import jakarta.transaction.Transactional;

@Service
public class EstoqueMovimentoService extends ServiceFuncoes implements ServiceModel<EstoqueMovimento> {
	@Autowired
	private MovimentoEstoqueRepository movimentoEstoqueRepository;
	@Autowired
	private EstoqueService serviceEstoque;
	@Autowired
	private ProdutoService produtoService;

	@Override
	public Page<EstoqueMovimento> buscar(String nome, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub

	}

	@Override
	public EstoqueMovimento buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public EstoqueMovimento salvar(EstoqueMovimento objeto) {
		Integer qtde = 0;
		var produto = produtoService.buccarporid(objeto.getProduto().getId());
		objeto.setProduto(produto);
		if (
				 !objeto.getProduto().getComponentes().isEmpty()) {

			for (int i = 0; i < produto.getComponentes().size(); i++) {
			
				qtde = objeto.getQtde().intValue() * produto.getProdutoDetalhe().get(i).getMutiplicador();
			//	componente2.setProduto(produto);

				System.out.println(qtde);
				System.out.println(produto.getComponentes().get(i).getProduto().getNome());
				if(objeto.getTipoMovimentacao()==(TipoMovimentacao.Saida)) {
				movimentoEstoqueRepository.save(	VerificarComponente(produto.getComponentes().get(i), qtde, objeto.getTipoMovimentacao()));
				}
				
			}

			// objeto.setQtde( new
			// BigDecimal(componente.getProduto().getEstoque().getQuantidade().intValue()
			// /componente.getQtde().intValue())) ;

			verificarMovimento(objeto);

		} else {
			verificarMovimento(objeto);
		}
		objeto.setDatamovimento(LocalDateTime.now());
		// objeto.setDatamovimento(LocalDateTime.of(2023, Month.APRIL, 12, 22, 30));
		System.out.println(objeto.getDatamovimento());
		return movimentoEstoqueRepository.save(objeto);
	}

	public Page<EstoqueMovimento> listar(String paramentro, TipoMovimentacao tipo, LocalDate datanicio,
			LocalDate datafim, Pageable pageable) {
		paramentro = TolowerCase.normalizarString(paramentro);
		return movimentoEstoqueRepository.listar(paramentro, tipo, datanicio, datafim, pageable);
	}

	private EstoqueMovimento verificarMovimento(EstoqueMovimento movimento) {
		if (movimento.getTipoMovimentacao() == TipoMovimentacao.Entrada) {
			if (movimento.getProduto().getEstoque() != null) {

				SomarEstoque(movimento);

				serviceEstoque.salvar(movimento.getProduto().getEstoque());
			} else {
				movimento.setSaldoanterior(BigDecimal.ZERO);
				serviceEstoque.salvar(adicionarEstoque(movimento));
			}

		} else {
			movimento.getProduto()
					.setEstoque(serviceEstoque.salvar(baixarEstoque(movimento).getProduto().getEstoque()));
		}

		return movimento;

	}

	private EstoqueMovimento baixarEstoque(EstoqueMovimento movimento) {
		if (movimento.getProduto().getEstoque() == null) {
			throw new NegocioException("Não possivel baixar estoque de um produto que não tenha estoque");
		}

		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getSaldoanterior().subtract(movimento.getQtde()));
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());

		return movimento;
	}

	private EstoqueMovimento SomarEstoque(EstoqueMovimento movimento) {

		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getSaldoanterior().add(movimento.getQtde()));
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());

		return movimento;

	}

	private Estoque adicionarEstoque(EstoqueMovimento movimento) {
		var estoque = new Estoque();
		estoque.setProduto(movimento.getProduto());
		estoque.setQuantidade(movimento.getQtde());
		return estoque;
	}

	private EstoqueMovimento VerificarComponente(Componente componente, Integer qtde, TipoMovimentacao tipo) {
	    var movimento = new EstoqueMovimento();
	    movimento.setProduto(componente.getProduto());
	    movimento.setDatamovimento(LocalDateTime.now());
	    movimento.setTipoMovimentacao(tipo);
	    movimento.setQtde(new BigDecimal(qtde));
    System.out.println( "movineto"+   movimento.getQtde());
    
	    if (tipo == TipoMovimentacao.Entrada) {
	        if (movimento.getProduto().getEstoque() != null) {
	            SomarEstoque(movimento);
	            serviceEstoque.salvar(movimento.getProduto().getEstoque());
	        }
	    }else {
	        movimento.setSaldoanterior(BigDecimal.ZERO);
	        System.out.println( "arr"+componente.getQtde());
	     //   var qtdec = componente.getQtde().intValue()* componente.getProduto().getEstoque().getQuantidade().intValue();
	        movimento.getProduto()
			.setEstoque(serviceEstoque.salvar(BaixarEstoqueComponte(movimento, qtde).getProduto().getEstoque()));
	    }
	    return movimento;
	}

   private EstoqueMovimento BaixarEstoqueComponte(EstoqueMovimento movimento, Integer qtde) {
	   if (movimento.getProduto().getEstoque() == null) {
			throw new NegocioException("Não possivel baixar estoque de um produto que não tenha estoque");
		}

		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getProduto().getEstoque().getQuantidade().subtract(new BigDecimal(qtde)));
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());
  System.out.println(movimento.getProduto().getEstoque().getQuantidade());
		return movimento;
   }
}
//	
//	@Transactional(rollbackOn = Exception.class)
//	void entradaEstoquue(EntradaNotaCabecario pEntrada) {
//	
//	
//		
//
//		for (int i = 0; i < pEntrada.getItems_entrada().size(); i++) {
//	     EstoqueMovimento estoquemovimento = new EstoqueMovimento();
//	     estoquemovimento.setProduto(pEntrada.getItems_entrada().get(i).getProduto());
//	     estoquemovimento.setTipoMovimentacao(TipoMovimentacao.Entrada);
//	     estoquemovimento.setQtde(pEntrada.getItems_entrada().get(i).getQtde());
//	     salvar(estoquemovimento);
//		}
//
//	}
//	public void CancelarEstoqueNota(EntradaNotaCabecario entrada) {
//		
//		for (int i = 0; i < entrada.getItems_entrada().size(); i++) {
//		     EstoqueMovimento estoquemovimento = new EstoqueMovimento();
//		     estoquemovimento.setProduto(entrada.getItems_entrada().get(i).getProduto());
//		     estoquemovimento.setTipoMovimentacao(TipoMovimentacao.Devolucao);
//		     estoquemovimento.setDatamovimento(LocalDateTime.now());
//		     estoquemovimento.setQtde(entrada.getItems_entrada().get(i).getQtde());
//		     verificarMovimento(estoquemovimento);
//		
//		     daoMovementacaoEstoque.save(estoquemovimento);
//			}
//		
//	}
