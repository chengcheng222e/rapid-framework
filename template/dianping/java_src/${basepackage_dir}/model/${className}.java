<#include "/macro.include"/>

<#assign className = table.className>
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.model;

import com.dianziq.wenda.util.DateUtil;

public class ${className}  implements java.io.Serializable{
private static final long serialVersionUID = 5454155825314635342L;

    //columns START
    <#list table.columns as column>
    /* ${column.columnAlias} */
    private ${column.javaType} ${column.columnNameLower};

    </#list>
    //columns END

<@generateConstructor className/>
<@generateJavaColumns/>

    @Override
    public String toString() {
            return "${className}{" +
<#list table.columns as column>
            "<#if column_index!=0>,</#if>${column.columnNameLower}=<#if column.javaType=='java.lang.String'>'</#if>" + ${column.columnNameLower} + <#if column.javaType=='java.lang.String'>'\''+</#if>
</#list>
            '}';
    }


}

<#macro generateJavaColumns>
<#list table.columns as column>
    public void set${column.columnName}(${column.javaType} value) {
            this.${column.columnNameLower} = value;
    }

    public ${column.javaType} get${column.columnName}() {
            return this.${column.columnNameLower};
    }
    <#if column.isDateTimeColumn>
    public String get${column.columnName}String() {
            return DateUtil.date2String(this.${column.columnNameLower}, DateUtil.FORMAT_DATETIME);
    }
    </#if>
</#list>
</#macro>
