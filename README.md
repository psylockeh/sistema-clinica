Este sistema é uma aplicação desktop desenvolvida em Java e Swing para gerenciar agendamentos de exames em uma clínica. Ele simula o uso de um funcionário com restrições de ações (CRUD - Create, Read, Update, Delete) devido ao seu baixo nível de permissão.

Funcionalidades:

Cadastrar novos agendamentos de exames.
Atualizar informações de agendamentos existentes (somente funcionários autorizados).
Buscar agendamentos por ID.
Listar todos os agendamentos cadastrados.
Integração com API Restful para comunicação com banco de dados.
Interface:

A interface do sistema é composta por campos de texto para inserir informações como ID do paciente, exame, médico, técnico, data e horário do agendamento. Possui também botões para realizar as ações de cadastro, atualização, busca, exclusão e listagem de agendamentos. Uma tabela exibe a lista de todos os agendamentos cadastrados, permitindo fácil visualização e seleção.

Restrições de Funcionário:

Este sistema simula o uso de um funcionário com restrições de permissão. O funcionário pode realizar as seguintes ações:

Cadastrar novos agendamentos.
Buscar agendamentos por ID.
Listar todos os agendamentos.
