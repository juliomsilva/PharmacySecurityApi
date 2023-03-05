insert into usuario(email,senha) values ('julio@gmail.com','$2a$10$oQI7aBOVGgP/MKJpiuDfmOI8d6lFiVINKp6zKxc2R4n/dv6X.fTMS') --Cria usuario Administrador, senha 123teste
insert into role(cargo) values('ROLE_ADMINISTRADOR') -- cria role de administrador
insert into usuario_cargos(usuario_entity_id,cargos_id) values (1,1) -- //seta o usuario 1 como administrador



insert into usuario(email,senha) values ('jeni@gmail.com','$2a$10$NCYTbD1LkfWES0tCax4vyu81fHPZFstAhuLWellgixyqToQP4yc/6') --Cria usuario Gerente, senha venv
insert into role(cargo) values('ROLE_GERENTE') -- cria role de GERENTE
insert into usuario_cargos(usuario_entity_id,cargos_id) values (2,2) -- //seta o usuario 2 como gerente

insert into usuario(email,senha) values ('will@gmail.com','$2a$10$NH1K/Zed7OWl3Lige5D...VZCJbgZG/y.lb3S4DoUtQ8Z4.IjJovO') --Cria usuario Colaborador, senha batera
insert into role(cargo) values('ROLE_COLABORADOR') -- cria role de Colaborador
insert into usuario_cargos(usuario_entity_id,cargos_id) values (3,3) -- //seta o usuario 3 como colaborador