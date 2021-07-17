package com.tempus.administracaodecliente.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tempus.administracaodecliente.model.Cliente;
import com.tempus.administracaodecliente.repository.Clientes;
import com.tempus.administracaodecliente.service.exception.CpfClienteJaCadastradoException;
import com.tempus.administracaodecliente.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes clientes;
	
	// Metodo salvar da class estilo. 
	@Transactional
	public Cliente salvar(Cliente cliente) {
		Optional<Cliente> cpfExistente = clientes.findByCpfIgnoreCase(cliente.getCpf());
		if (cpfExistente.isPresent() && !cpfExistente.get().equals(cliente)) {
			throw new CpfClienteJaCadastradoException("CPF já cadastrado");
		}
		
		return clientes.save(cliente);
	}

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			this.clientes.delete(cliente);
			this.clientes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já está atrelado.");
		}
	}
}
