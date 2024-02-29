import Link from "next/link";
import React from "react";

const Navbar = () => {
  return (
    <nav
      className="navbar navbar-expand-lg bg-dark border-bottom border-body mb-5"
      data-bs-theme="dark"
    >
      <div className="container-fluid px-4">
        <Link className="navbar-brand" href="/">
          Instituto Med Center
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNavDropdown"
          aria-controls="navbarNavDropdown"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
      </div>


      <div className="collapse navbar-collapse px-4" id="navbarNavDropdown">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link text-light text-nowrap" aria-current="page" href="/">
                Página Inicial
              </Link>
            </li>


            <li className="nav-item dropdown">
              <button className="btn btn-dark dropdown-toggle" 
                data-bs-toggle="dropdown" 
                aria-expanded="false"
              >
                Agenda/Listas
              </button>
              <ul className="dropdown-menu dropdown-menu-dark dropdown-menu-end">
                <li className="nav-item">
                  <Link className="nav-link" aria-current="page" href="/agenda">
                    Agenda
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" aria-current="page" href="/lista-pacientes">
                    Lista de Pacientes
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" aria-current="page" href="/lista-medicos">
                    Lista de Medicos
                  </Link>
                </li>
              </ul>
            </li>


            <li className="nav-item dropdown">
              <button className="btn btn-dark dropdown-toggle" 
                data-bs-toggle="dropdown" 
                aria-expanded="false"
              >
                Registros
              </button>
              <ul className="dropdown-menu dropdown-menu-dark dropdown-menu-end">
                <li className="nav-item">
                  <Link className="nav-link" aria-current="page" href="/registrar-paciente">
                    Registrar Paciente
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" aria-current="page" href="/registrar-medico">
                    Registrar Medico
                  </Link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
    </nav>
  );
};

export default Navbar;
