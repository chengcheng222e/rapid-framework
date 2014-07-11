<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "action">

package ${basepackage}.action;

import com.dianziq.dianping.model.${className};
import com.dianziq.dianping.service.${className}Service;
import com.dianziq.wenda.admin.BaseAction;
import com.dianziq.wenda.domain.Page;
import com.google.common.base.Strings;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ${className}Action extends BaseAction{
    protected static final String LIST_JSP = "list";
    protected static final String EDIT_JSP = "edit";
    protected static final String SHOW_JSP = "show";
    private static final Logger logger = LoggerFactory.getLogger(${className}Action.class);

    @Autowired
	private ${className}Service ${classNameLower}Service;
	
	private ${className} ${classNameLower};

    <#list table.compositeIdColumns as column>
    private ${column.javaType} ${column.columnNameLower};
	</#list>

	/** 执行搜索 */
	public String list() {
        Page listPage = getPage();
        if (pageIndex > 0) {
            listPage.setPageIndex(pageIndex);
        }
        List<${className}> ${classNameLower}List = ${classNameLower}Service.findPage(listPage, ${classNameLower});
        return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
        ${classNameLower} = ${classNameLower}Service.getById(<#list table.compositeIdColumns as column>${column.columnNameLower}<#if column_has_next>,</#if> </#list>);
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return EDIT_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		int row = ${classNameLower}Service.save(${classNameLower});
        return "redirect";
	}
	
	/**进入更新页面*/
	public String edit() {
        ${classNameLower} = ${classNameLower}Service.getById(<#list table.compositeIdColumns as column>${column.columnNameLower}<#if column_has_next>,</#if> </#list>);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
        int row = ${classNameLower}Service.update(${classNameLower});
        return "redirect";
	}
	
	/**删除对象*/
	public String delete() {
        int count = ${classNameLower}Service.delete(<#list table.compositeIdColumns as column>${column.columnNameLower}<#if column_has_next>,</#if> </#list>);
        if (count == 1) {
            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("code", "ok");
            resultMap.put("msg", "删除成功！");
            try {
                outJson(JSONUtil.serialize(resultMap));
            } catch (JSONException e) {
                logger.error("数据转json时出错,resultMap={},e={},{}", resultMap, e, e.getStackTrace());
            }
        }
        return null;
	}


<#list table.compositeIdColumns as column>
    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
    public ${column.javaType} get${column.columnName}() {
        return ${column.columnNameLower};
    }
</#list>
    public void set${className}(${className} ${classNameLower}) {
        this.${classNameLower} = ${classNameLower};
    }
    public ${className} get${className}() {
        return this.${classNameLower};
    }

}
