<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="td" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<!DOCTYPE html>
<tags:layout>
    <jsp:attribute name="cabecalho">
    </jsp:attribute>
    <jsp:attribute name="rodape">
        <script src="${ctx}/resources/plugins/dataTables/datatables.js"><c:out value=""/></script>
        <script src="${ctx}/resources/plugins/dataTables/Buttons-1.4.2/js/buttons.html5.js"><c:out value=""/></script>
        <script src="${ctx}/resources/js/servicos/lista.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-sisint">
            <div class="panel-heading">
                <div class="panel-title">Gerenciamento de usuários</div>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <a class="btn btn-info" style="margin-bottom: 16px;" href="${linkTo[UsuariosController].form}">Cadastrar</a>
                <div class="tabela-servicos">
                    <table id="tabela-servico" class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Login</th>
                            <th>Email</th>
                            <th>Telefone</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${usuarios}" var="usuario">
                            <tr>
                                <td>${usuario.id}</td>
                                <td><span class="label">${usuario.nome}</span></td>
                                <td class="date-column">${usuario.login}</td>
                                <td>${usuario.email}</td>
                                <td>${usuario.telefone}</td>
                                <td><a href="#"><i class="fa fa-eye" aria-hidden="false"></i></a>
                                    <a href="${linkTo[UsuariosController].editar}?id=${usuario.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                    <a href="#"><i class="fa fa-trash"></i></a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>