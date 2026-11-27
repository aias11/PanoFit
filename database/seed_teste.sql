-- Dados de TESTE para conseguir logar no app PanoFit (ambiente local).
-- Login: digite o CPF exatamente como está aqui (só números) e a senha abaixo.
USE PanoFit;

INSERT INTO funcionarios (cargo, nome, cpf, senha, telefone, genero, data_nasc, salario, status)
SELECT 'Gerente', 'Gerente Teste', '00000000000', 'admin123', '(00) 00000-0000', 'Outro', '01/01/1990', '5000.00', 'Pago'
WHERE NOT EXISTS (SELECT 1 FROM funcionarios WHERE cpf = '00000000000');

-- Plano de teste
INSERT INTO assinaturas (nome_assinatura, valor, duracao)
SELECT 'Mensal', '99.90', '1 mês'
WHERE NOT EXISTS (SELECT 1 FROM assinaturas WHERE nome_assinatura = 'Mensal');

-- Cliente de teste (login: CPF e senha abaixo)
INSERT INTO clientes (nome, cpf, telefone, senha, data_nascimento, genero, assinatura, valor, status)
SELECT 'Cliente Teste da Silva', '11111111111', '(11) 98888-7777', 'cliente123', '15/05/2000', 'Masculino', 'Mensal', '99.90', 'Pendente'
WHERE NOT EXISTS (SELECT 1 FROM clientes WHERE cpf = '11111111111');

-- Ficha de treino do cliente de teste (só insere se ele ainda não tiver ficha)
INSERT INTO fichas_treino (cliente_id, dia_semana, exercicio, series, repeticoes)
SELECT c.id, t.dia, t.ex, t.se, t.re
FROM clientes c
JOIN (
    SELECT 'Segunda' AS dia, 'Supino reto' AS ex, '4' AS se, '10' AS re UNION ALL
    SELECT 'Segunda', 'Crucifixo', '3', '12' UNION ALL
    SELECT 'Segunda', 'Tríceps corda', '3', '15' UNION ALL
    SELECT 'Terça', 'Agachamento livre', '4', '8' UNION ALL
    SELECT 'Terça', 'Leg press', '4', '12' UNION ALL
    SELECT 'Quinta', 'Puxada frontal', '4', '10' UNION ALL
    SELECT 'Quinta', 'Remada curvada', '3', '10' UNION ALL
    SELECT 'Sexta', 'Desenvolvimento', '4', '10' UNION ALL
    SELECT 'Sexta', 'Elevação lateral', '3', '15'
) t
WHERE c.cpf = '11111111111'
  AND NOT EXISTS (SELECT 1 FROM fichas_treino f WHERE f.cliente_id = c.id);
