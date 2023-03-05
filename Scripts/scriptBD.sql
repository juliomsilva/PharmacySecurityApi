create table role(
                     id bigserial primary key,
                     Cargo varchar(255) NOT NULL
);

create table usuario(
                        id bigserial primary key,
                        email varchar(320) NOT NULL,
                        senha varchar(50) NOT NULL,
                        id_cargo bigint NOT NULL,
                        foreign key(id_cargo) references role (id)

);

create table endereco(
                         id bigserial primary key,
                         cep varchar(50) NOT NULL,
                         logradouro varchar(255) NOT NULL,
                         numero int NOT NULL,
                         bairro varchar(255) NOT NULL,
                         cidade varchar(255) NOT NULL,
                         estado varchar(255) NOT NULL,
                         complemento varchar(255),
                         latitude varchar(255)NOT NULL,
                         longitude varchar(255) NOT NULL
);



create table farmacia(
                         id bigserial primary key,
                         razao_social varchar(255) NOT NULL,
                         cnpj varchar(255) NOT NULL,
                         nome_fantasia varchar(255) NOT NULL,
                         email varchar(320) NOT NULL,
                         telefone varchar(50),
                         celular varchar(50) NOT NULL,
                         id_endereco bigint NOT NULL,
                         foreign key(id_endereco) references endereco (id)
);

create table medicamento (
                             id bigserial primary key,
                             nome_medicamento varchar(255) NOT NULL,
                             nome_laboratorio varchar(255) NOT NULL,
                             dosagem varchar(255) NOT NULL,
                             desc_medicamento varchar(320),
                             preco_unitario varchar(255),
                             tipo_medicamento varchar(255)
);

