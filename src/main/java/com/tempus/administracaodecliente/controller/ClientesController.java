package com.tempus.administracaodecliente.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tempus.administracaodecliente.controller.page.PageWrapper;
import com.tempus.administracaodecliente.model.Cliente;
import com.tempus.administracaodecliente.repository.Clientes;
import com.tempus.administracaodecliente.repository.filter.ClienteFilter;
import com.tempus.administracaodecliente.service.CadastroClienteService;
import com.tempus.administracaodecliente.service.exception.CpfClienteJaCadastradoException;
import com.tempus.administracaodecliente.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private Clientes clientes;
	
	// Mapeamento da URL - GET.
	@RequestMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		return new ModelAndView("cliente/CadastroClientes");
	}
	
	// Metodo POST - Salvar
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, 
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cliente);
		}
		
		try {			
			cadastroClienteService.salvar(cliente);	
		} catch (CpfClienteJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(cliente);
		}
		
		attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");
		return new ModelAndView("redirect:/clientes/novo");
	}

	// Metodo GET - Pesquisar
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("cliente/PesquisaClientes");
		
		PageWrapper<Cliente> paginaWrapper = new PageWrapper<>(clientes.filtrar(clienteFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	// Metodo GET - Alterar.
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable("id") Cliente cliente) {
		ModelAndView mv = this.novo(cliente);
		mv.addObject(cliente);
		return mv;
	}
	
	// Metodo DELETE - Deletar.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable("id") Cliente cliente) {
		try {
			this.cadastroClienteService.excluir(cliente);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
}
