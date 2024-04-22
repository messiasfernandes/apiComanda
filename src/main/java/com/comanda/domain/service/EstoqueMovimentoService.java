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
import com.comanda.domain.entity.ItemMovimentacao;
import com.comanda.domain.entity.Produto;
import com.comanda.domain.enumerado.Operacao;
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
		System.out.println("total" + objeto.getItems().size());
		for (var item : objeto.getItems()) {
			item.setProduto(buscar(item.getProduto().getId()));
		}

		objeto.setDatamovimento(LocalDateTime.now());
		objeto.getItems().forEach(im -> im.setEstoqueMovimento(objeto));
		verificarMovimento(objeto);
		for (ItemMovimentacao item : objeto.getItems()) {
			if (!item.getProduto().getComponentes().isEmpty()) {
				if (objeto.getTipoMovimentacaoEstoque().getOperacao().equals(Operacao.Saida)) {
					for (Componente componente : item.getProduto().getComponentes()) {
						movimentoEstoqueRepository.save(VerificarComponente(componente,
								componente.getQtde().intValueExact(), objeto.getTipoMovimentacaoEstoque().getOperacao()));
					}
				}
			}

		}

		return movimentoEstoqueRepository.save(objeto);
	}

	private EstoqueMovimento verificarMovimento(EstoqueMovimento movimento) {
		System.out.println("verificando" + movimento.getItems().size());
	
		if (movimento.getTipoMovimentacaoEstoque().getOperacao().equals(Operacao.Entrada) || movimento.getTipoMovimentacaoEstoque().getOperacao().equals(Operacao.Devolucao)) {
			for ( var iTemM : movimento.getItems()) {
				System.out.println("entrada");
				if (iTemM.getProduto().getEstoque() != null) {
					SomarEstoque(iTemM);

					serviceEstoque.salvar(iTemM.getProduto().getEstoque());
				} else {
				iTemM.setSaldoanterior(BigDecimal.ZERO);
					serviceEstoque.salvar(adicionarEstoque(iTemM));
				}
			

			}
		}else {
			
		       System.out.println("saida");
	            movimento.getItems().forEach(mov -> {
	                mov.getProduto().getEstoque().getProduto().setEstoque(serviceEstoque
	                        .salvar(baixarEstoque(mov).getProduto().getEstoque()));
	            });
		}
		return movimento;
	}

	private ItemMovimentacao baixarEstoque(ItemMovimentacao movimento) {
		if (movimento.getProduto().getEstoque() == null) {
			throw new NegocioException("Não possivel baixar estoque de um produto que não tenha estoque");
		}

		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getSaldoanterior().subtract(movimento.getQtde()));
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());

		return movimento;
	}

	public Page<EstoqueMovimento> listar(String paramentro, Operacao tipo, LocalDate datanicio,
			LocalDate datafim, Pageable pageable) {
		Page<EstoqueMovimento> page = null;
		paramentro = TolowerCase.normalizarString(paramentro);
		if (datafim == null && datanicio == null) {
			page = movimentoEstoqueRepository.pesquisar(paramentro, tipo, pageable);
		} else {
			System.out.println(datafim);
			page = movimentoEstoqueRepository.listar(paramentro, tipo, datanicio, datafim, pageable);
		}
		System.out.println("total"+ page.getNumberOfElements());
		return page;
	}

	private ItemMovimentacao SomarEstoque(ItemMovimentacao movimento) {

		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque().setQuantidade(movimento.getSaldoanterior().add(movimento.getQtde()));
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());

		return movimento;

	}

	private Estoque adicionarEstoque(ItemMovimentacao movimento) {
		var estoque = new Estoque();
		estoque.setProduto(movimento.getProduto());
		estoque.setQuantidade(movimento.getQtde());
		return estoque;
	}

	private EstoqueMovimento VerificarComponente(Componente componente, Integer qtde, Operacao operacao) {
		var movimento = new ItemMovimentacao();
		movimento.setProduto(componente.getProduto());
		movimento.getEstoqueMovimento().setDatamovimento(LocalDateTime.now());
		//movimento.getEstoqueMovimento().setOperacao(operacao);
		movimento.setQtde(new BigDecimal(qtde));
		System.out.println("movineto" + movimento.getQtde());

		if (operacao == Operacao.Entrada) {
			if (movimento.getProduto().getEstoque() != null) {
				SomarEstoque(movimento);
				serviceEstoque.salvar(movimento.getProduto().getEstoque());
			}
		} else {
			movimento.setSaldoanterior(BigDecimal.ZERO);
			System.out.println("arr" + componente.getQtde());
			// var qtdec = componente.getQtde().intValue()*
			// componente.getProduto().getEstoque().getQuantidade().intValue();
			movimento.getProduto().setEstoque(
					serviceEstoque.salvar(BaixarEstoqueComponte(movimento, qtde).getProduto().getEstoque()));
		}
		return movimento.getEstoqueMovimento();
	}

	private ItemMovimentacao BaixarEstoqueComponte(ItemMovimentacao movimento, Integer qtde) {

		if (movimento.getProduto().getEstoque() == null) {
			throw new NegocioException("Não possivel baixar estoque de um produto que não tenha estoque");
		}
		movimento.setSaldoanterior(movimento.getProduto().getEstoque().getQuantidade());
		movimento.getProduto().getEstoque()
				.setQuantidade(movimento.getProduto().getEstoque().getQuantidade().subtract(new BigDecimal(qtde)));
		movimento.getProduto().getEstoque().setProduto(movimento.getProduto());
		System.out.println(movimento.getProduto().getEstoque().getQuantidade());
		return movimento;
	}

	private Produto buscar(Long id) {
		var produto = produtoService.buccarporid(id);
		return produto;
	}

}
