<%--
  Created by IntelliJ IDEA.
  User: samue
  Date: 08/09/2017
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="td" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<!DOCTYPE html>
<tags:teste>
    <jsp:attribute name="cabecalho">
        <style>
            .panel {
                border-color: #a3aaff;
                border-radius: 0;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/plugins/dataTables/datatables.js"><c:out value=""/></script>
        <script src="${ctx}/resources/plugins/dataTables/Buttons-1.4.2/js/buttons.html5.js"><c:out value=""/></script>
        <script src="${ctx}/resources/js/servicos/lista.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel">
            <div class="panel-heading">
                <div class="panel-title">Serviços em abertos</div>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <a class="btn btn-info" style="margin-bottom: 16px;" href="${linkTo[ServicosController].form}">Cadastrar</a>
                <div class="tabela-servicos">
                    <table class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Status</th>
                            <th>Prioridade</th>
                            <th>Data de Fechamento</th>
                            <th>Técnico</th>
                            <th>Setor</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${servicos}" var="servico">
                            <tr>
                                <td>${servico.titulo}</td>
                                <td><span class="label">${servico.statusServico.chave}</span></td>
                                <td><span class="label">${servico.prioridade.chave}</span></td>
                                <td class="date-column">${servico.dataFechamento}</td>
                                <td>${servico.tecnico.nome}</td>
                                <td>${servico.setor.nome}</td>
                                <td><a href="${linkTo[ServicosController].detalhes}?id=${servico.id}"><i class="fa fa-eye" aria-hidden="false"></i></a>
                                    <a href="${linkTo[ServicosController].editar}?id=${servico.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                    <a href="${linkTo[ServicosController].logServico}?id=${servico.id}">
                                        <i class="fa fa-list-ul" aria-hidden="true"></i></a>
                                    <a href="#"><i class="fa fa-trash"></i></a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:teste>
