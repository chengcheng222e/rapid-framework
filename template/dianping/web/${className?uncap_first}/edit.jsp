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
    <script type="text/javascript" src="<%=request.getContextPath()%>/bgManage/js/jquery.form.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bgManage/js/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/bgManage/js/jquery.validate.methods.js"></script>

</head>
<body>
<div class="container">
    <div class="row">
        <h3>${table.tableAlias}</h3>
        <s:if test="hasFieldErrors()">
            <div class="alert alert-error">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>错误提示：</strong>
                <s:fielderror></s:fielderror>
            </div>
        </s:if>

        <c:set var="formMethod" value='update' />
        <c:if test="<@jspEl  "empty "+classNameLower + ".pk"/>">
        <c:set var="formMethod" value='save' />
        </c:if>

        <form
                action="<%=request.getContextPath()%>/bgMng/dianping/${classNameLower}/<@jspEl  "formMethod"/>.action"
                id="editForm"
                class="form-horizontal"
                method="post">

        <#list table.columns as column>
            <div class="control-group">
                <label class="control-label">
                    ${column.columnAlias}：
                </label>
                <div class="controls">
                <#if column.pk>
                    <c:choose>
                        <c:when test='<@jspEl  "empty "+classNameLower + ".pk"/>'>
                            <input type="text" value='<@jspEl  classNameLower + "." + column.columnNameLower/>' name="${classNameLower}.${column.columnNameLower}" />
                        </c:when>
                        <c:otherwise>
                            <@jspEl  classNameLower + "." + column.columnNameLower/>
                            <input type="hidden" value='<@jspEl  classNameLower + "." + column.columnNameLower/>' name="${classNameLower}.${column.columnNameLower}" />
                        </c:otherwise>
                    </c:choose>
                <#else>
                        <input  type="text" value='<@jspEl  classNameLower + "." + column.columnNameLower/>' name="${classNameLower}.${column.columnNameLower}" />
                </#if>
                </div>
            </div>
        </#list>
            <div class="control-group">
                <label class="control-label">&nbsp;</label>

                <div class="controls">
                    <button type="submit" class="btn btn-primary">保存</button>
                    <a class="btn" href="<%= request.getContextPath()%>/bgMng/dianping/${classNameLower}/list.action">返回</a>
                </div>
            </div>

        </form>
    </div>
</div>
</body>
</html>

