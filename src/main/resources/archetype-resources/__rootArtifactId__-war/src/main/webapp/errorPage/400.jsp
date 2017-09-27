#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page import="${package}.api.entity.RespWrapper" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true"%>
<%
    RespWrapper<String> resp = new RespWrapper<String>("1", "参数错误");
    if (exception != null) {
        resp.setResultDesc(exception.getMessage());
    }
    String json = JSONObject.toJSONString(resp);
%>
<%=json%>