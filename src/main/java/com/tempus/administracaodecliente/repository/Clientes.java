package com.tempus.administracaodecliente.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tempus.administracaodecliente.model.Cliente;
import com.tempus.administracaodecliente.repository.helper.cliente.ClientesQueries;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>, ClientesQueries {

	Optional<Cliente> findByCpfIgnoreCase(String cpf);
}
