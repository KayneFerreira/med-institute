INSERT INTO tb_paciente (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado)
VALUES ('João Primeiro', '24705070143', 'M', '1911-01-01', 'joao.primeiro@email.com', '5511911111111', 'Rua do Primeiro Cara', '111', '11111111', 'Cidade 1', 'Estado 1');

INSERT INTO tb_paciente (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado)
VALUES ('João Segundo', '14744754694', 'M', '1922-02-02', 'joao.segundo@email.com', '5511922222222', 'Rua do Segundo Cara', '222', '22222222', 'Cidade 2', 'Estado 2');

INSERT INTO tb_paciente (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado)
VALUES ('João Terceiro', '62275604561', 'M', '1933-03-03', 'joao.terceiro@email.com', '5511933333333', 'Rua do Terceiro Cara', '333', '33333333', 'Cidade 3', 'Estado 3');

INSERT INTO tb_paciente (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado)
VALUES ('João Quarto', '62275604561', 'M', '1944-04-04', 'joao.quarto@email.com', '5511944444444', 'Rua do Quarto Cara', '444', '44444444', 'Cidade 4', 'Estado 4');

INSERT INTO tb_paciente (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado)
VALUES ('João Quinto', '62275604561', 'M', '1955-05-05', 'joao.quinto@email.com', '55119555555558', 'Rua do Quinto Cara', '555', '55555555', 'Cidade 5', 'Estado 5');





INSERT INTO tb_medico (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado, crm, especialidade)
VALUES ('Maria Neura', '24705070143', 'F', '1966-06-06', 'maria.neura@email.com', '5511966666666', 'Rua do Sexto Medico', '634', '66666666', 'Cidade 6', 'Estado 6', 'CRM/SP 666666', 'Neurologia');

INSERT INTO tb_medico (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado, crm, especialidade)
VALUES ('Maria Orto', '13553158832', 'F', '1977-07-07', 'maria.orto@email.com', '5511977777777', 'Rua do Setimo Medico', '777', '77777777', 'Cidade 7', 'Estado 7', 'CRM/SP 777777', 'Ortopedia');

INSERT INTO tb_medico (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado, crm, especialidade)
VALUES ('Maria Otorrino', '51576324605', 'F', '1988-08-08', 'maria.otorrino@email.com', '5511988888888', 'Rua do Oitavo Medico', '888', '88888888', 'Cidade 8', 'Estado 8', 'CRM/SP 888888', 'Otorrinolaringologia');

INSERT INTO tb_medico (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado, crm, especialidade)
VALUES ('Maria Trauma', '27384186510', 'F', '1999-09-09', 'maria.trauma@email.com', '5511999999999', 'Rua do Nono Medico', '999', '99999999', 'Cidade 9', 'Estado 9', 'CRM/SP 999999', 'Traumatologia');

INSERT INTO tb_medico (nome, cpf, sexo, data_nascimento, email, telefone, endereco, numero, cep, cidade, estado, crm, especialidade)
VALUES ('Maria Psicologa', '04677871310', 'F', '1900-01-01', 'maria.falida@email.com', '5511900000000', 'Rua do Medico Zero', '000', '00000000', 'Cidade 0', 'Estado 0', 'CRM/SP 000000', 'Psicologia');




INSERT INTO tb_consulta (data, hora, id_medico, id_paciente, convenio, numero_carteira)
VALUES ('2023-11-06', '14:30', 1, 1, 'Convenio 1', '1234567890');

INSERT INTO tb_consulta (data, hora, id_medico, id_paciente, convenio, numero_carteira)
VALUES ('2023-11-07', '15:00', 2, 2, 'Convenio 2', '9876543210');

INSERT INTO tb_consulta (data, hora, id_medico, id_paciente, forma_pagamento, valor)
VALUES ('2023-11-08', '16:30', 3, 3, 'Dinheiro', 100.00);

INSERT INTO tb_consulta (data, hora, id_medico, id_paciente, forma_pagamento, valor)
VALUES ('2023-11-09', '17:00', 4, 4, 'Cartão de Crédito', 150.00);

INSERT INTO tb_consulta (data, hora, id_medico, id_paciente, convenio, numero_carteira)
VALUES ('2023-11-10', '10:30', 5, 5, 'Convenio 3', '5432109876');
