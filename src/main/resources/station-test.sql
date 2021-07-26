insert into station (cs_id, addr, cs_nm, lat, lng) VALUE ('cs1', 'addr1','cs_nm1', 123131.123,12313.123123);

insert into charger (charge_tp, cp_id, cp_nm, cp_stat, cp_tp, stat_update_datetime, cs_id) value ('STANDARD_CHARGE', '1234','112323', 'RE_CHARGEABLE','BC_TYPE_5','2099-12-01','cs1');
insert into charger (charge_tp, cp_id, cp_nm, cp_stat, cp_tp, stat_update_datetime, cs_id) value ('QUICK_CHARGE', '1253','131223', 'RE_CHARGEABLE','BC_TYPE_5','2099-12-01','cs1');

insert into station (cs_id, addr, cs_nm, lat, lng) VALUE ('cs2', 'addr2','cs_nm2', 123131.123,12313.123123);
insert into charger (charge_tp, cp_id, cp_nm, cp_stat, cp_tp, stat_update_datetime, cs_id) value ('STANDARD_CHARGE', '123','123', 'RE_CHARGEABLE','BC_TYPE_5','2099-12-01','cs2');

insert into station (cs_id, addr, cs_nm, lat, lng) VALUE ('cs3', 'addr3','cs_nm3', 123131.123,12313.123123);
