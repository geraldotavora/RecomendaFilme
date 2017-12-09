--REMOVE DEPENDENCIA DE GENERO

create trigger rmv_genero
before delete
on genero
for each row
execute procedure rmv_usuario_genero(); 

create or replace function rmv_usuario_genero() returns trigger as $$
begin
	delete from preferencia_genero where id_genero = old.id;
    return old;
end;
$$ language plpgsql;

-- REMOVE DEPENDENCIA DE USUARIO

create trigger rmv_dep_user
before delete
on usuario
for each row
execute procedure rmv_dep_usuario();

create or replace function rmv_dep_usuario() returns trigger as $$
begin
    delete from preferencia_genero where id_usuario = old.id; 
	delete from preferencia_ator where id_usuario = old.id;
   	delete from filme_assistido where id_usuario = old.id;
    return old;
end;
$$ language plpgsql;

--REMOVE DEPENDENCIA DE ATOR

create trigger rmv_ator
before delete
on ator
for each row
execute procedure rmv_usuario_ator(); 

create or replace function rmv_usuario_ator() returns trigger as $$
begin
	delete from preferencia_ator where id_ator = old.id;
    return old;
end;
$$ language plpgsql;

--REMOVE DEPENDENCIA DE FILME

create trigger rmv_filme
before delete
on filme
for each row
execute procedure rmv_dep_filme();

create or replace function rmv_dep_filme() returns trigger as $$
begin
	delete from filme_assistido where id_filme = old.id;
    return old;
end;
$$ language plpgsql;