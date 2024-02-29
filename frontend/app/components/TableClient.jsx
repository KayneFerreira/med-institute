'use client'
import React from 'react'
import { useState, useEffect } from "react";
import { actionCancelled, deleteFailed, deleteSuccess } from './Alerts';
import Swal from "sweetalert2"


const TableClient = () => {
  const url = "http://localhost:8080/api/v4/test/pacientes"

  useEffect(() => {
    require('bootstrap/dist/js/bootstrap')
  })

  const [data, setData] = useState([]);
  useEffect(() => {
    fetch(url)
      .then((response) => response.json())
      .then((data) => setData(data))
      .catch((error) => console.error(error));
  }, []);


  /**
   * Delete request
   */
  const handleDelete = async (id, nome) => {
    await fetch(`${url}/${id}`, {
      method: 'DELETE'
    })
    .then((response) => {
      if (response.status === 204) {
        deleteSuccess(nome)
      }
      else {
        deleteFailed(response.status)
      }
    })
    .then((data) => { console.log(data); })
    .catch((error) => { console.error(error); });
  }


  /**
   * Delete prompt
   */
  const deletePrompt = (id, nome) => {
    Swal.fire({
      title: 'Você está prestes a remover ' + nome,
      text: "Deseja mesmo continuar? ",
      icon: 'warning',
      showCancelButton: true,
      reverseButtons: true,
      confirmButtonText: 'Remover',
      confirmButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      cancelButtonColor: '#3085d6',
      allowEnterKey: true,
      allowEscapeKey: true,
    }).then((result) => {
      if (result.isConfirmed) {
        handleDelete(id, nome)
      } 
      else {
        actionCancelled()
      }
    })
  }


  /**
   * Set client details
   */
  const [item, setItem] = useState()
  const clientDetails = (item) => {
    setItem(item)
  }


  return (
    <div>
      <h1 className='text-center py-4'>
        Lista de Pacientes
      </h1>

      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">Sexo</th>
            <th scope="col">Data de Nascimento</th>
            <th scope="col">Telefone</th>
            <th scope="col">E-Mail</th>
            <th scope="col">Opções</th>
          </tr>
        </thead>

        <tbody>
          {data.map((paciente) => (
            <tr key={paciente.id}>
              <th id='clientId'>{paciente.id}</th>
              <td>{paciente.nome}</td>
              <td>{paciente.sexo}</td>
              <td>{paciente.dataNascimento}</td>
              <td>{paciente.telefone}</td>
              <td>{paciente.email}</td>
              <td>
                <button 
                id='btnClientDetails'
                className='btn btn-success btn-sm' 
                data-bs-toggle="modal" 
                data-bs-target="#clientDetails"
                onClick={() => clientDetails(paciente)}>
                  Detalhes/Editar
                </button>

                <button 
                className='btn btn-danger btn-sm mx-2' 
                onClick={() => deletePrompt(paciente.id, paciente.nome)}>
                  Excluir
                </button>
                
                <div className="modal fade" id="clientDetails" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div className="modal-dialog">
                    {item && (
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1 className="modal-title fs-5" id="exampleModalLabel">Detalhes do Paciente</h1>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                          <div>
                            Nome: {item.nome} <br />
                            Sexo: {item.sexo} <br />
                            Data de Nascimento: {item.dataNascimento} <br />
                            CPF: {item.cpf} <br />
                            Telefone: {item.telefone} <br />
                            Email: {item.email} <br />
                            Endereço: {item.endereco} <br />
                            Número: {item.numero} <br />
                            CEP: {item.cep} <br />
                            Cidade: {item.cidade} <br />
                            Estado: {item.estado} <br />
                          </div>
                        </div>
                        <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
                          <button type="button" className="btn btn-primary">Editar</button>
                        </div>
                      </div>
                    )}
                  </div>
                </div>
                
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default TableClient