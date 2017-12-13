SELECT sessao.sessao_hora,
       filme.filme_id,
       filme.filme_nome,
       sala.sala_codigo,
       ing.ingressos_vendidos, 
       sala.sala_qtd_assentos,
       (sala.sala_qtd_assentos - ing.ingressos_vendidos) ingressos_disponiveis
  FROM sessao 
 INNER JOIN sala 
    ON sala.sala_id = sessao.sala_id
 INNER JOIN filme
    ON filme.filme_id = sessao.filme_id
  LEFT JOIN (SELECT SUM(ingresso_qtd) ingressos_vendidos, sessao_id
               FROM ingresso
              GROUP BY sessao_id) ing on ing.sessao_id = sessao.sessao_id

			  
SELECT sessao.*,
       sala.*,
       filme.*,
       ing.ingressos_vendidos, 
       CASE
         WHEN ing.ingressos_vendidos is null then sala.sala_qtd_assentos
         ELSE
           (sala.sala_qtd_assentos - ing.ingressos_vendidos)
       END saldo_ingressos
  FROM sessao 
 INNER JOIN sala 
    ON sala.sala_id = sessao.sala_id
 INNER JOIN filme
    ON filme.filme_id = sessao.filme_id
  LEFT JOIN (SELECT SUM(ingresso_qtd) ingressos_vendidos, sessao_id
               FROM ingresso
              GROUP BY sessao_id) ing on ing.sessao_id = sessao.sessao_id
 
 SELECT sessao.*,
       filme.filme_nome,
       sala.sala_codigo,
       sala.sala_qtd_assentos,
       ing.ingressos_vendidos, 
       CASE
         WHEN ing.ingressos_vendidos is null then sala.sala_qtd_assentos
         ELSE
           (sala.sala_qtd_assentos - ing.ingressos_vendidos)
       END saldo_ingressos
  FROM sessao 
 INNER JOIN sala 
    ON sala.sala_id = sessao.sala_id
 INNER JOIN filme
    ON filme.filme_id = sessao.filme_id
  LEFT JOIN (SELECT SUM(ingresso_qtd) ingressos_vendidos, sessao_id
               FROM ingresso
              GROUP BY sessao_id) ing on ing.sessao_id = sessao.sessao_id
 WHERE sessao.filme_id = ?
