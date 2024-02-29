'use client'
import React from "react";
import { useState } from "react";
import { InputMask } from 'primereact/inputmask';
import { estados } from "../resources/Content";
import { insertFailed, insertSuccess } from "./Alerts";


const FormsClient = () => {
  const [data, setData] = useState({
    id: '',
    nome: '',
    cpf: '',
    telefone: '',
    email: '',
    dataNascimento: '',
    sexo: '',
    endereco: '',
    numero: '',
    cep: '',
    cidade: '',
    estado: ''
  })


  const handleChange = (e) => {
    const { name, value } = e.target;
    setData({ ...data, [name]: value });
  };
  

  const onClick = async (e) => {
    e.preventDefault();
    const jsonData = JSON.stringify(data);
    await fetch('http://localhost:8080/api/v4/test/pacientes', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonData,
    })
      .then((response) => {
        if (response.status >= 200 && response.status < 300) {
          response.json()
          insertSuccess()
        }
        else {
          insertFailed(response.status)
        }
      })
      .then((data) => { console.log(data); })
      .catch((error) => { console.error(error); });
  };


  return (
    <form className="px-4" onSubmit={onClick}>
      <div className="row g-2 mb-4 d-flex justify-content-center">
        <div className="form-floating d-flex justify-content-center col-sm-10">
            <input name="nome" id="nome" type="text" className="form-control" placeholder="Nome Completo" value={data.nome} onChange={handleChange} />
            <label htmlFor="nome">Nome Completo</label>
        </div>
      </div>
      <div className="row g-2 mb-4 d-flex justify-content-center">
        <div className="form-floating col-sm-4">
          <InputMask 
            name="dataNascimento" 
            id="dataNascimento"
            className="form-control" 
            value={data.dataNascimento} 
            onChange={handleChange} 
            mask="99/99/9999" 
            placeholder="Data de Nascimento"
          />
          <label htmlFor="dataNascimento">Data de Nascimento</label>
        </div>
        <div className="form-floating col-sm-3">
          <select name="sexo" id="sexo" type="text" className="form-select" value={data.sexo} onChange={handleChange}>
            <option value="">--Escolha o Sexo--</option>
            <option value="M">Masculino</option>
            <option value="F">Feminino</option>
          </select>
          <label htmlFor="sexo">Sexo</label>
        </div>
        <div className="form-floating col-sm-3">
          <InputMask 
            name="cpf" 
            id="cpf"
            className="form-control" 
            value={data.cpf} 
            onChange={handleChange} 
            mask="999.999.999-99" 
            placeholder="CPF" 
          />
          <label htmlFor="cpf">CPF</label>
        </div>
      </div>

      <div className="row g-2 mb-4 d-flex justify-content-center">
        <div className="form-floating col-sm-4">
          <InputMask 
            name="telefone" 
            id="telefone"
            className="form-control" 
            value={data.telefone} 
            onChange={handleChange} 
            mask="(+9999) 99999-9999" 
            placeholder="Telefone/Celular" 
          />
          <label htmlFor="telefone">Telefone/Celular</label>
        </div>
        <div className="form-floating col-sm-6">
          <input name="email" id="email" type="email" className="form-control" placeholder="E-Mail" value={data.email} onChange={handleChange} />
          <label htmlFor="email">E-Mail</label>
        </div>
      </div>

      <div className="row g-2 mb-4 d-flex justify-content-center">
        <div className="form-floating col-sm-5">
          <input name="endereco" id="endereco" type="text" className="form-control" placeholder="Endereço" value={data.endereco} onChange={handleChange} />
          <label htmlFor="endereco">Endereço</label>
        </div>
        <div className="form-floating col-sm-2">
          <input name="numero" id="numero" type="number" className="form-control" placeholder="Número" value={data.numero} onChange={handleChange} />
          <label htmlFor="numero">Número</label>
        </div>
        <div className="form-floating col-sm-3">
          <InputMask 
            name="cep" 
            id="cep"
            className="form-control" 
            value={data.cep} 
            onChange={handleChange} 
            mask="99999-999" 
            placeholder="CEP" 
          />
          <label htmlFor="cep">CEP</label>
        </div>
      </div>

      <div className="row g-2 mb-4 d-flex justify-content-center">
        <div className="form-floating col-sm-4">
          <select name="estado" id="estado" type="text" className="form-select" value={data.estado} onChange={handleChange}>
            <option value="">--Escolha o Estado--</option>
            {estados.map((value, i) => {
              return <option key={i} value={value}>{value}</option>
            })}
          </select>
          <label htmlFor="estado">Estado</label>
        </div>
        <div className="form-floating col-sm-6">
          <input name="cidade" id="cidade" type="text" className="form-control" placeholder="Cidade" value={data.cidade} onChange={handleChange} />
          <label htmlFor="cidade">Cidade</label>
        </div>
      </div>

      <div className="d-flex justify-content-center">
        <div className="d-grid col-10">
          <button type="submit" className="btn btn-primary btn-lg">
              Registrar Paciente
          </button>
        </div>
      </div>
    </form>
  );
};

export default FormsClient;