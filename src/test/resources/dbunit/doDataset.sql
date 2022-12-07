declare
tname VARCHAR2(100);
  vW    VARCHAR2(1000);
  vQH   VARCHAR2(1000);
  procedure doset is
    vQ   VARCHAR2(1000) := null;
begin
    vQH := 'SELECT XMLELEMENT("' ||
                          tname || '",XMLATTRIBUTES(';
    crep.do(vQH);

for cur in (select c.COLUMN_NAME
                  from user_tab_cols c
                 where table_name = tname
                 order by internal_column_id) loop
       if vQ IS NOT NULL then
           crep.do(vQ);
end if;
      vQ := cur.column_name || ' AS "' || cur.column_name || '",';
end loop;
    crep.do(SUBSTR(vQ,1,length(vQ)-1)||')).getStringVal()');
    crep.do('FROM ' || tname);
    if vW IS NOT NULL then crep.do('WHERE ' || vW); end if;
    crep.do(';');
end;
begin
  tname := 'AGRO_MOB_USER';
  vW := null;
  doset;
  tname  := 'AGRO_MOB_SESSION';
  vW     := 'last_action_date between date ''2022-01-01'' and date ''2022-10-31''';
  doset;
  tname  := 'AGRO_MOB_EMP_LOGIN';
  vW     := 'aemp_id in (select user_id from AGRO_MOB_SESSION'||CHR(10)||
         ' WHERE last_action_date between date ''2022-01-01'' and date ''2022-10-31'')';
  doset;

end;








