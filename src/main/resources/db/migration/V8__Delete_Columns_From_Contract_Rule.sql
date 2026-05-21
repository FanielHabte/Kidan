alter table guardian.contract_rule
    drop column is_required cascade;

alter table guardian.contract_rule
drop column is_unique cascade;