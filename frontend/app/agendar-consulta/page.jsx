'use client'
import React from "react";
import { useState, useEffect } from "react";
import { insertFailed, insertSuccess } from "../components/Alerts"
import DatePicker from "../components/DataPicker";
import TimePicker from "../components/TimePicker";
import moment from 'moment'; 
import { tabelaPrecos } from "../resources/Content";


const AppointmentRegister = ({ searchParams }) => {
  const urlConsulta = 'http://localhost:8080/api/v4/test/consultas'
  const urlMedico = 'http://localhost:8080/api/v4/test/medicos'
  
  const [data, setData] = useState({
    paciente: {id: searchParams.id},
    medico: {id: ''},
    data: '',
    hora: '',
    formaPagamento: '',
    valor: '',
    convenio: '',
    numeroCarteira: '',
  })


  /**
   * Check for existing appointments
   */
  const [consultas,  setConsultas] = useState([])
  useEffect(() => {
    fetch(urlConsulta)
      .then((response) => response.json())
      .then((data) => setConsultas(data))
      .catch((error) => console.error(error));
  }, []);
  
  const [medicos, setMedico] = useState({})
  const [especialidade, setEspecialidade] = useState("");
  const especialidades = [...new Set(Object.values(medicos).map(medico => medico.especialidade))];
  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedTime, setSelectedTime] = useState(null);


  /**
   * Handle data change on DatePicker
   */
  const handleDateChange = (name, date) => {
    const formattedDate = date.format("YYYY-MM-DD")
    setData( { ...data, [name]: formattedDate } )
    setSelectedDate(date);
  };


  /**
   * Handle time change on TimePicker
   */
  const handleTimeChange = (name, time) => {
    const roundedTime = moment(time).minutes(Math.round(time.minute() / 15) * 15);
    const formattedTime = moment(roundedTime, "HH:mm").format("HH:mm");
    setData({ ...data, [name]: formattedTime });
    setSelectedTime(time)
  };


   /**
   * Use effect for listing all doctors
   */
  useEffect(() => {
    fetch(urlMedico)
      .then((response) => response.json())
      .then((data) => setMedico(data))
      .catch((error) => console.error(error));
  }, []);


  /**
   * Handle input change from forms
   */
  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name === "especialidade") {
      setEspecialidade(value);
      const preco = tabelaPrecos[value]
      setData({ ...data, [name]: value, valor: preco.toFixed(2) });
    }
    else if (name === "medico") {
      setData({ ...data, medico: { id: value } });
    } 
    else {
      setData({ ...data, [name]: value });
    }
  };


  // !!! TEST FUNCTION
  const testData = () => {
    for (const key in data) {
      console.log(`${key}: ${data[key]}`)
    }
    console.log('PACIENTE ID: ' + data.paciente.id)
    console.log('MEDICO ID: ' + data.medico.id)
    console.log('DATA SET: ' + selectedDate)
    console.log('TIME SET: ' + selectedTime)
  }


  /**
   * Handle click event
   * POST request
   */
  const handleSubmit = async (e) => {
    e.preventDefault();
    testData()
    const jsonData = JSON.stringify(data);
    await fetch(urlConsulta, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: jsonData,
    })
      .then((response) => {
        if (response.status === 201) {
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
    <div>
      <h1 className="text-center py-4">
        Registro de Consulta
      </h1>
      
      {medicos && (
      <form className="px-4" onSubmit={handleSubmit}>
        <div className="row g-2 mb-4 d-flex justify-content-center">
          {searchParams && (
            <h5 className="col-sm-10">
              <b>Nome do Paciente: </b> {searchParams.nome} <br />
              <b>Data de Nascimento: </b> {searchParams.dataNascimento} <br />
              <b>Sexo: </b> {searchParams.sexo} <br />
            </h5>
          )}
        </div>
        
        <div className="row g-2 mb-4 d-flex justify-content-center">
          <div className="form-floating col-sm-4">
            <select 
            name="especialidade" 
            id="especialidade" 
            type="text" 
            className="form-select" 
            value={data.especialidade} 
            onChange={handleChange}>
              <option value="">-- Escolha a Especialidade --</option>
              {especialidades.map((esp, i) => {
                return <option key={i}> { esp } </option> 
              })}
            </select>
            <label htmlFor="especialidade">Especialidade</label>
          </div>

          <div className="form-floating col-sm-6">
            <select 
            name="medico" 
            id="medico" 
            className="form-control" 
            placeholder="Nome do Médico" 
            value={data.medico.id}
            onChange={handleChange}>
              <option value="">-- Escolha o Medico Especialista --</option>
              {Object.keys(medicos)
                .filter((key) => medicos[key].especialidade === especialidade)
                .map((key, i) => (
                  <option key={i} value={medicos[key].id}>{medicos[key].nome}</option>
              ))}
            </select>
            <label htmlFor="medico">Nome do Médico</label>
          </div>
        </div>

        <div className="row g-2 mb-4 d-flex justify-content-center">
          <div className="form-text col-sm-3">
            <label htmlFor="data">Data da Consulta</label>
            <DatePicker 
            name="data" 
            id="data"
            type="text" 
            className="form-control" 
            dateFormat="yyyy-MM-dd"
            value={data.data} 
            onChange={handleChange} 
            selectedDate={selectedDate} 
            handleDateChange={handleDateChange} />
          </div>

          <div className="form-text col-sm-2">
            <label htmlFor="hora">Horário</label>
            <TimePicker 
            name="hora" 
            id="hora" 
            type="text" 
            className="form-control" 
            value={data.hora} 
            onChange={handleChange} 
            selectedTime={selectedTime}
            handleTimeChange={handleTimeChange} />
          </div>

          <div className="form-floating col-sm-3">
            <select 
            name="formaPagamento" 
            id="formaPagamento" 
            type="text" 
            className="form-control" 
            placeholder="Forma de Pagamento" 
            value={data.formaPagamento} 
            onChange={handleChange}>
              <option value="">-- Escolha a Forma de Pagamento --</option>
              <option value="Dinheiro">Dinheiro</option>
              <option value="Credito">Cartão de Crédito</option>
              <option value="Pix">Pix</option>
            </select>
            <label htmlFor="formaPagamento">Forma de Pagamento</label>
          </div>

          <div className="form-floating col-sm-2">
            <input 
            name="valor" 
            id="valor" 
            type="text" 
            className="form-control" 
            placeholder="Valor Total" 
            value={data.valor} 
            onChange={handleChange} 
            readOnly/>
            <label htmlFor="valor">Valor Total</label>
          </div>
        </div>

        <div className="row g-2 mb-4 d-flex justify-content-center">
          <div className="form-floating col-sm-5">
            <input 
            name="convenio" 
            id="convenio" 
            type="text" 
            className="form-control" 
            placeholder="Convênio" 
            value={data.convenio} 
            onChange={handleChange} />
            <label htmlFor="convenio">Convênio</label>
          </div>

          <div className="form-floating col-sm-5">
            <input 
            name="numeroCarteira" 
            id="numeroCarteira" 
            type="text" 
            className="form-control" 
            placeholder="Número da Carteira" 
            value={data.numeroCarteira} 
            onChange={handleChange} />
            <label htmlFor="numeroCarteira">Número da Carteira</label>
          </div>
        </div>

        <div className="d-flex justify-content-center">
          <div className="d-grid col-10">
            <button type="submit" className="btn btn-primary btn-lg">
                Agendar Consulta
            </button>
          </div>
        </div>
      </form>
      )}
    </div>
  )
}

export default AppointmentRegister