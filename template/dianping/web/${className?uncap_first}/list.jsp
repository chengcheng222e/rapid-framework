<%@ page session="false" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<#assign className = table.className>
<#assign classNameLower = className?uncap_first>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="keywords" content=""/>
    <meta name="description" content=""/>
    <title>电子圈后台运营管理</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" type="text/css" media="screen"/>
    <link href="<%= request.getContextPath()%>/bgManage/dialog/css/zebra_dialog.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery.1.7.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
    <script charset="utf-8" src="<%= request.getContextPath()%>/bgManage/dialog/js/zebra_dialog.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/bgManage/js/dianping-base.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bgManage/js/dianping-page.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bgManage/js/dianping-ajax.js"></script>

</head>

<body>
<div class="container">
    <div class="row">
        <h3>${table.tableAlias}列表</h3>
        <div class="span12">
            <form id="adminSearchForm"
                  name="adminSearchForm"
                  action="<%= request.getContextPath()%>/bgMng/dianping/${classNameLower}/list.action"
                  class="form-search"
                  method="post">
                <input type="hidden" id="pageIndex" name="pageIndex" value="<@jspEl 'page.pageIndex'/>" />
                <a href="<%= request.getContextPath()%>/bgMng/dianping/${classNameLower}/create.action" class="btn btn-info">添加</a>
                <#list table.pkColumns as column>
                    ${column.columnAlias}:
                    <input type="text" class="input-medium" name="${column.columnNameLower}" value="<@jspEl column.columnNameLower/>" />
                </#list>
                <button type="submit" class="btn">查询</button>
            </form>
        </div>
    </div>

    <div class="row">
        <table class="span12 table table-bordered table-hover table-condensed ">
            <thead>
            <tr >
                <#list table.columns as column>
                    <th>
                        <span>${column.columnAlias}</span>
                    </th>
                </#list>
                <th>
                    <span>操作</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="<@jspEl 'page.resultList'/>">
                <tr>
                    <#list table.columns as column>
                        <td>
                        <#if column.isDateTimeColumn>
                            <c:out value='<@jspEl "row."+column.columnNameLower+"String"/>'/>&nbsp;
                        <#else>
                            <c:out value='<@jspEl "row."+column.columnNameLower/>'/>&nbsp;
                        </#if>
                        </td>
                    </#list>

                    <td>
                        <a href="<%=request.getContextPath()%>/bgMng/dianping/${classNameLower}/show.action?<@generateIdQueryString/>">查看</a>&nbsp;
                        <a href="<%=request.getContextPath()%>/bgMng/dianping/${classNameLower}/edit.action?<@generateIdQueryString/>">编辑</a>&nbsp;
                        <a href="javascript:void(0);"
                           onclick="doAjaxRequest('<%=request.getContextPath()%>/bgMng/dianping/${classNameLower}/delete.action?<@generateIdQueryString/>',{},'是否删除?')">删除</a>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="/bgManage/dianping/page_split.jsp"/>
</div>

</body>
</html>

<#macro generateIdQueryString>
		<#assign itemPrefix = 'row.'>
<#compress>
		<#list table.compositeIdColumns as column>
			<#t>${column.columnNameLower}=<@jspEl itemPrefix + column.columnNameLower/>&
		</#list>				
</#compress>
</#macro>