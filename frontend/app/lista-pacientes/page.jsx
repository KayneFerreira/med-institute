'use client'
import React from 'react'
import { useState, useEffect } from "react";
import { actionCancelled, deleteFailed, deleteSuccess } from '../components/Alerts';
import Swal from "sweetalert2"
import Link from 'next/link';
import { toTitleCase } from '../components/Util';


const ListClient = () => {
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
        setTimeout(() => window.location.reload(), 2000);
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
  const [items, setItems] = useState()
  const clientDetails = (items) => {
    setItems(items)
  }


  return (
    <div>
      <h1 className='text-center py-4'>
        Lista de Pacientes
      </h1>
      <table className="table table-striped container text-nowrap">
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

              <Link 
                type="button" 
                className="btn btn-primary btn-sm mx-2" 
                href={{
                  pathname: '/agendar-consulta',
                  query: {
                    id: paciente.id,
                    nome: paciente.nome,
                    dataNascimento: paciente.dataNascimento,
                    sexo: paciente.sexo,
                  }
                }}>
                  Marcar Consulta
                </Link>

                <button 
                id='btnClientDetails'
                className='btn btn-success btn-sm' 
                data-bs-toggle="modal" 
                data-bs-target="#clientDetails"
                onClick={() => clientDetails(paciente)}>
                  Detalhes
                </button>

                <Link 
                type="button" 
                className="btn btn-warning btn-sm mx-2" 
                href={{
                  pathname: '/atualizar-paciente',
                  query: {
                    id: paciente.id,
                    nome: paciente.nome,
                    dataNascimento: paciente.dataNascimento,
                    sexo: paciente.sexo,
                    cpf: paciente.cpf,
                    telefone: paciente.telefone,
                    email: paciente.email,
                    endereco: paciente.endereco,
                    numero: paciente.numero,
                    cep: paciente.cep,
                    estado: paciente.estado,
                    cidade: paciente.cidade
                  }
                }}>
                  Editar
                </Link>

                <button 
                className='btn btn-danger btn-sm' 
                onClick={() => deletePrompt(paciente.id, paciente.nome)}>
                  Excluir
                </button>
                
                <div className="modal fade" id="clientDetails" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div className="modal-dialog">
                    {items && (
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1 className="modal-title fs-5" id="exampleModalLabel">Detalhes do Paciente</h1>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                          <div>
                            <ul>
                              {Object.keys(items).map((key, i) => {
                                return <li key={i}>
                                  <b>{toTitleCase(key)}</b>: {items[key]}
                                </li> 
                              })}
                            </ul>
                          </div>
                        </div>
                        <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Fechar</button>
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

export default ListClient