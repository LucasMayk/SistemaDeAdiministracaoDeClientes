package com.tempus.administracaodecliente.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tempus.administracaodecliente.model.Cliente;
import com.tempus.administracaodecliente.repository.filter.ClienteFilter;

public interface ClientesQueries {
	public Page<Cliente> filtrar(ClienteFilter filtro, Pageable pageable);
}
