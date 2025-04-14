alter table pacientes add ativo tinyint;
update pacientes SET ativo = 1;
