'use client'
import React from 'react'
import { useState, useEffect } from "react";
import { actionCancelled, deleteFailed, deleteSuccess } from '../components/Alerts';
import Swal from "sweetalert2"
import Link from 'next/link';


const AppointmentList = () => {
  const url = "http://localhost:8080/api/v4/test/consultas"

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
   * Set appointment details
   */
  const [item, setItem] = useState()
  const appointmentDetails = (item) => {
    setItem(item)
  }


  return (
    <div>
      <h1 className='text-center py-4'>
        Consultas Marcadas
      </h1>

      <table className="table table-striped container text-nowrap">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Paciente</th>
            <th scope="col">Especialidade</th>
            <th scope="col">Data</th>
            <th scope="col">Horário</th>
            <th scope="col">Médico</th>
            <th scope="col">Opções</th>
          </tr>
        </thead>

        <tbody>
          {data.map((consulta) => (
            <tr key={consulta.id}>
              <th id='clientId'>{consulta.id}</th>
              <td>{consulta.paciente.nome}</td>
              <td>{consulta.medico.especialidade}</td>
              <td>{consulta.data}</td>
              <td>{consulta.hora}</td>
              <td>{consulta.medico.nome}</td>
              <td>
                <button 
                id='btnClientDetails'
                className='btn btn-success btn-sm' 
                data-bs-toggle="modal" 
                data-bs-target="#appointmentDetails"
                onClick={() => appointmentDetails(consulta)}>
                  Detalhes
                </button>

                <Link 
                type="button" 
                className="btn btn-warning btn-sm mx-2" 
                href={{
                  pathname: '/atualizar-consulta',
                  query: {
                    id: consulta.id,
                    pacienteId: consulta.paciente.id,
                    nomePaciente: consulta.paciente.nome,
                    dataNascimento: consulta.paciente.dataNascimento,
                    sexo: consulta.paciente.sexo,
                    nomeMedico: consulta.medico.nome,
                    especialidade: consulta.medico.especialidade,
                    data: consulta.data,
                    hora: consulta.hora,
                    formaPagamento: consulta.formaPagamento,
                    valor: consulta.valor,
                    convenio: consulta.convenio,
                    numeroCarteira: consulta.numeroCarteira
                  }
                }}>
                  Editar
                </Link>

                <button 
                className='btn btn-danger btn-sm' 
                onClick={() => deletePrompt(consulta.id, consulta.nome)}>
                  Excluir
                </button>
                
                <div className="modal fade" id="appointmentDetails" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                  <div className="modal-dialog">
                    {item && (
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1 className="modal-title fs-5" id="exampleModalLabel">Detalhes da Consulta</h1>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                          <div>
                            <ul>
                               <li><b>Paciente: </b>{item.paciente.nome}</li>
                               <li><b>Sexo: </b>{item.paciente.sexo}</li>
                               <li><b>Medico: </b>: {item.medico.nome}</li>
                               <li><b>Especialidade: </b> {item.medico.especialidade}</li>
                               <li><b>CRM: </b> {item.medico.crm}</li>
                               <li><b>Data da consulta: </b> {item.data}</li>
                               <li><b>Horario da consulta: </b> {item.hora}</li>
                               <li><b>Telefone do paciente: </b> {item.paciente.telefone}</li>
                               <li><b>Email do paciente: </b> {item.paciente.email}</li>
                               <li><b>Forma de Pagamento: </b> {item.formaPagamento}</li>
                               <li><b>Valor: </b>R$ {item.valor}</li>
                               <li><b>Convênio Médico: </b> {item.convenio}</li>
                               <li><b>Número da Carteira: </b> {item.numeroCarteira}</li>
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

export default AppointmentList