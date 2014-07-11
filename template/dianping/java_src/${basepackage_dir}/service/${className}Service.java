<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.service;

import com.dianziq.dianping.dao.${className}Dao;
import com.dianziq.dianping.model.${className};
import org.springframework.stereotype.Service;
import com.dianziq.wenda.domain.Page;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ${className}Service {

    @Resource
    private ${className}Dao ${classNameLower}Dao;

    public ${className} getById(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>,</#if> </#list>) {
        return ${classNameLower}Dao.getObjById(<#list table.compositeIdColumns as column>${column.columnNameLower}<#if column_has_next>,</#if> </#list>);
    }

    public int save(${className} ${classNameLower}) {
        return ${classNameLower}Dao.save(${classNameLower});
    }

    public int update(${className} ${classNameLower}) {
        return ${classNameLower}Dao.update(${classNameLower});
    }

    public int delete(<#list table.compositeIdColumns as column>${column.javaType} ${column.columnNameLower}<#if column_has_next>,</#if> </#list>) {
        return ${classNameLower}Dao.delete(<#list table.compositeIdColumns as column>${column.columnNameLower}<#if column_has_next>,</#if> </#list>);
    }

    public List<${className}> findPage(Page page, ${className} ${classNameLower}) {
        int count = ${classNameLower}Dao.findPageCount(page, ${classNameLower});
        page.setCount(count);
        List<${className}> resultList =  ${classNameLower}Dao.findPage(page, ${classNameLower});
        page.setResultList(resultList);
        return resultList;
    }
}

