import Swal from "sweetalert2"


export const insertSuccess = () => {
  Swal.fire({
    title: "Sucesso!",
    text: "O registro foi efetuado com sucesso!",
    icon: "success",
  })
}


export const insertFailed = (status) => {
  Swal.fire({
    title: "Ocorreu um erro!",
    text: "Falha ao tentar efetuar o registro. ERRO: " + status,
    icon: "error",
  })
}


export const updateSuccess = () => {
  Swal.fire({
    title: "Sucesso!",
    text: "O registro foi atualizado com sucesso!",
    icon: "success",
  })
}


export const updateFailed = (status) => {
  Swal.fire({
    title: "Ocorreu um erro!",
    text: "Falha ao tentar atualizar o registro. ERRO: " + status,
    icon: "error",
  })
}


export const deleteSuccess = (nome) => {
  Swal.fire(
    'Sucesso!',
    nome + ' foi removido da lista.',
    'success'
  )
}


export const deleteFailed = (status) => {
  Swal.fire({
    title: "Não foi possível remover o registro.",
    text: " ERRO: " + status,
    icon: "error",
  })
}


export const actionCancelled = () => {
  Swal.fire(
    'Ação cancelada!',
    'Nenhuma alteração foi efetuada.',
    'info'
  )
}


export const cpfNotValid = () => {
  Swal.fire(
    'CPF inválido!',
    'Verifique se digitou corretamente seu CPF.',
    'warning'
  )
}


export const requiredFields = () => {
  Swal.fire(
    'Campos obrigatórios em branco!',
    'Verifique se preencheu todos os campos obrigatórios do formulário.',
    'warning'
  )
}