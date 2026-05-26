DROP VIEW IF EXISTS guardian.v_contracts_page_summary;
CREATE VIEW guardian.v_contracts_page_summary AS
SELECT
    (c.id || d.id) || cast(c.version as text)            AS id
     , c.id                                               AS contract_id
     , d.id                                               AS dataset_id
     , d.name                                             AS dataset_name
     , c.name
     , c.description
     , c.version
     , c.updated_at
     , CASE
           WHEN c.is_active IS TRUE THEN 'Active'::text
           ELSE 'Inactive'::text
    END                                              AS status
     , d.updated_by                                       AS last_updated_by
     , d.user_id                                          AS owner
     , count(cr.id)                                       AS rules_count
     , count(
       CASE
           WHEN c.is_active IS TRUE THEN 1
           ELSE NULL::int
           END) OVER ()                                 AS active_contracts
     , count(
       CASE
           WHEN c.is_active IS FALSE THEN 1
           ELSE NULL::int
           END) OVER ()                                 AS inactive_contracts
     , count(c.id)                                        AS total_contracts
     , avg(count(cr.id)) OVER ()::double precision        AS avg_rules
FROM guardian.contract c
         LEFT JOIN guardian.dataset d ON c.dataset_id = d.id
         LEFT JOIN guardian.contract_rule cr ON c.id = cr.contract_id
GROUP BY 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11