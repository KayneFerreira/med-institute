'use client'
import React from 'react'
import { useState, useEffect } from "react";
import { actionCancelled, deleteFailed, deleteSuccess } from '../components/Alerts';
import Swal from "sweetalert2"
import Link from 'next/link';
import { toTitleCase } from '../components/Util';


const ListDoctors = () => {
  const url = "http://localhost:8080/api/v4/test/medicos"

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
  const [item, setItem] = useState()
  const medicDetails = (item) => {
    setItem(item)
  }


  return (
    <div>
      <h1 className='text-center py-4'>
        Lista de Medicos
      </h1>

      <table className="table table-striped container text-nowrap">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Nome</th>
            <th scope="col">Especialidade</th>
            <th scope="col">CRM</th>
            <th scope="col">Sexo</th>
            <th scope="col">Telefone</th>
            <th scope="col">E-Mail</th>
            <th scope="col">Opções</th>
          </tr>
        </thead>

        <tbody>
          {data.map((medico) => (
            <tr key={medico.id}>
              <th id='clientId'>{medico.id}</th>
              <td>{medico.nome}</td>
              <td>{medico.especialidade}</td>
              <td>{medico.crm}</td>
              <td>{medico.sexo}</td>
              <td>{medico.telefone}</td>
              <td>{medico.email}</td>
              <td>
                <button 
                id='btnMedicDetails'
                className='btn btn-success btn-sm' 
                data-bs-toggle="modal" 
                data-bs-target="#medicDetails"
                onClick={() => medicDetails(medico)}>
                  Detalhes
                </button>

                <Link 
                type="button" 
                className="btn btn-warning btn-sm mx-2" 
                href={{
                  pathname: '/atualizar-medico',
                  query: {
                    id: medico.id,
                    nome: medico.nome,
                    dataNascimento: medico.dataNascimento,
                    sexo: medico.sexo,
                    cpf: medico.cpf,
                    telefone: medico.telefone,
                    email: medico.email,
                    endereco: medico.endereco,
                    numero: medico.numero,
                    cep: medico.cep,
                    estado: medico.estado,
                    cidade: medico.cidade,
                    especialidade: medico.especialidade,
                    crm: medico.crm,
                  }
                }}>
                  Editar
                </Link>

                <button 
                className='btn btn-danger btn-sm' 
                onClick={() => deletePrompt(medico.id, medico.nome)}>
                  Excluir
                </button>
                
                <div className="modal fade" id="medicDetails" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div className="modal-dialog">
                    {item && (
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1 className="modal-title fs-5" id="exampleModalLabel">Detalhes do Medico</h1>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                          <div>
                            <ul>
                              {Object.keys(item).map((key, i) => {
                                return <li key={i}>
                                  <b>{toTitleCase(key)}</b>: {item[key]}
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

export default ListDoctors