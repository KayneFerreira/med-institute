'use client'
import { useEffect } from "react";
import Image from "next/image";
import Link from "next/link";

export default function Home() {
  useEffect(() => {
    require('bootstrap/dist/js/bootstrap')
  })
  
  return (
    <div className='container d-flex flex-xl-row flex-sm-column-reverse flex-wrap-reverse align-items-center py-5'>
      <div className="px-5 text-xl-end text-center py-5">
        <h1>Instituto Med Center</h1>
        <h5>Medicina de qualidade ao seu alcance.</h5>
        <div>
          <Link className="btn btn-primary my-3 mx-3" href="/agenda">Agenda</Link>
          <Link className="btn btn-secondary my-3" href="/lista-pacientes">Lista de Pacientes</Link>
        </div>
      </div>
      <div>
        <Image 
          src="/images/stock_01.png"
          width={500}
          height={300}
          alt="Clinica foto 01"
        />
      </div>
    </div>
  )
}
