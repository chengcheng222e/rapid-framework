<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dao;

import com.dianziq.dianping.model.${className};
import com.dianziq.common.core.dao.SimpleJdbcTemplate;
import com.dianziq.wenda.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.dao.EmptyResultDataAccessException;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ${className}Dao extends SimpleJdbcTemplate<${className}>{

    @Autowired
    public ${className}Dao(@Qualifier(value = "dataSource") javax.sql.DataSource ds) {
        super(ds, ${className}.class);
    }

	public ${className} getObjById(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>,</#if> </#list>) {
        StringBuilder sql = getSelectSql();
        sql.append(" WHERE <#list table.compositeIdColumns as column>${column.sqlName}=?<#if column_has_next> AND </#if> </#list>");
        Object[] params = {<#list table.compositeIdColumns as column> ${column.columnNameLower}<#if column_has_next>,</#if> </#list>};
        try {
            return this.queryForObject(sql.toString(), row, params);
        } catch (EmptyResultDataAccessException e) {
        }
        return null;
	}

    public int save(${className} ${classNameLower}) {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO ${table.sqlName}(");
<#list table.notPkColumns as column>
    <#if column.sqlName!='lmd'>
        sql.append("    `${column.sqlName}` <#if column_has_next>,</#if>");
    </#if>
</#list>
        sql.append("  ) VALUES ( ");
<#list table.notPkColumns as column>
    <#if column.sqlName=='ctime' >
        sql.append("  now() <#if column_has_next>,</#if> ");
    <#elseif column.sqlName!='lmd' >
        sql.append("    ? <#if column_has_next>,</#if> ");
    </#if>
</#list>
        sql.append("  ); ");

        Object[] params = {
<#list table.notPkColumns as column>
        <#if column.sqlName!='lmd' && column.sqlName!='ctime'>
            ${classNameLower}.get${column.columnName}() <#if column_has_next>,</#if>
        </#if>
</#list>
        };
        return this.insert(sql.toString(), params);
    }

    public int update(${className} ${classNameLower}){
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ${table.sqlName} SET ");
        int fieldCount = 0;
        MapSqlParameterSource params = new MapSqlParameterSource();
<#list table.notPkColumns as column>
    <#if column.sqlName!='lmd'>
        if( ${classNameLower}.get${column.columnName}() != null ){
            appendField(sql, fieldCount, " `${column.sqlName}`=:${column.columnNameFirstLower} ");
            params.addValue("${column.columnNameLower}", ${classNameLower}.get${column.columnName}());
            fieldCount++;
        }
    </#if>
</#list>
        sql.append(" WHERE ");
<#list table.compositeIdColumns as column>
        sql.append(" ${column.sqlName}=:${column.columnNameLower}<#if column_has_next> AND </#if> ");
        params.addValue("${column.columnNameLower}", ${classNameLower}.get${column.columnName}());
</#list>
        return this.update(sql.toString(), params);
    }



    public int delete(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>,</#if> </#list>) {
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM ${table.sqlName} ");
        sql.append(" WHERE <#list table.compositeIdColumns as column>${column.sqlName}=?<#if column_has_next> AND </#if> </#list>");
        Object[] params = {<#list table.compositeIdColumns as column> ${column.columnNameLower}<#if column_has_next>,</#if> </#list>};
        return this.update(sql.toString(), params);
    }

    public int findPageCount(Page page, ${className} ${classNameLower}) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(*) ");
        sql.append(" FROM ${table.sqlName} ");

        List<Object> params = new ArrayList<Object>();
        sql.append(getFindPageWhereSql(${classNameLower}, params));

        String groupBy = page.getGroupBy();
        if (!Strings.isNullOrEmpty(groupBy)) {
            sql.append(" GROUP BY  " + groupBy);
        }
        return this.queryForInt(sql.toString(), params.toArray());
    }

    public List<${className}> findPage(Page page, ${className} ${classNameLower}) {
        StringBuilder sql = getSelectSql();

        List<Object> params = new ArrayList<Object>();
        sql.append(getFindPageWhereSql(${classNameLower}, params));

        String groupBy = page.getGroupBy();
        if (!Strings.isNullOrEmpty(groupBy)) {
            sql.append(" GROUP BY  " + groupBy);
        }

        String orderBy = page.getOrderBy();
        if (!Strings.isNullOrEmpty(orderBy)) {
            sql.append(" ORDER BY  " + orderBy);
        }

        sql.append(" LIMIT  " + page.getStart() +","+ page.getPageSize() );
        return this.query(sql.toString(), row, params.toArray());
    }

    private StringBuilder getSelectSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
<#list table.columns as column>
        sql.append("    `${column.sqlName}` as `${column.columnNameLower}`<#if column_has_next>,</#if> ");
</#list>
        sql.append(" FROM ${table.sqlName} ");
        return sql;
    }

    private String getFindPageWhereSql(${className} ${classNameLower}, List<Object> params) {
        if( ${classNameLower}==null ){
            return "";
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" WHERE 1=1 ");
<#list table.columns as column>
        if( ${classNameLower}.get${column.columnName}() != null ){
            sql.append(" and `${column.sqlName}`=? " );
            params.add(${classNameLower}.get${column.columnName}());
        }
</#list>

        return sql.toString();
    }

    private void appendField(StringBuilder sql,int fieldCount,String setFieldSql){
        if(fieldCount>0){
            sql.append(" ,");
        }
        sql.append(setFieldSql);
    }

}
