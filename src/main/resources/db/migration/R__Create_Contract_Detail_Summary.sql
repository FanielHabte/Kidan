create or replace view guardian.v_contract_detail_summary as
select
    cr.id
     , c.dataset_id                             as dataset_id
     , cc.id                                    as contract_csv_id
     , cr.id                                    as contract_id
     , count(cr.id) over(partition by c.id)     as rules_count
     , count(case
                 when cr.rule_config::jsonb -> 'is_required'
                     = 'true' then 1 end)
       over(partition by c.id)                  as is_required_count
     , count(case
                 when cr.rule_config::jsonb -> 'is_unique'
                     = 'true' then 1 end)
       over(partition by c.id)                  as is_unique_count
from guardian.contract c
         left join guardian.contract_rule cr
                   on c.id = cr.contract_id
         left join guardian.csv_contract cc on c.id = cc.contract_id;