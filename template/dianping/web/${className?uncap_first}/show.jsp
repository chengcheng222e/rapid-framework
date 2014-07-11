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
    <link rel="stylesheet" href="<%= request.getContextPath()%>/bootstrap/css/bootstrap.min.css" type="text/css"
          media="screen"/>
    <link href="<%= request.getContextPath()%>/bgManage/dialog/css/zebra_dialog.css" rel="stylesheet" type="text/css"/>

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
        <h3>${table.tableAlias}查看</h3>
        <table class="table table-bordered table-hover table-condensed ">
            <#list table.columns as column>
                <tr>
                    <td>
                        ${column.columnAlias}：
                    </td>
                    <td>
                        <#if column.isDateTimeColumn>
                            <c:out value='<@jspEl classNameLower+"."+column.columnNameLower+"String"/>'/>&nbsp;
                            <#else>
                            <c:out value='<@jspEl classNameLower+"."+column.columnNameLower/>'/>&nbsp;
                        </#if>
                    </td>
                </tr>
            </#list>
        </table>
        <div class="span12">
            <a class="btn" href="<%= request.getContextPath()%>/bgMng/dianping/${classNameLower}/list.action">返回列表</a>
        </div>
    </div>
</div>
</body>
</html>

