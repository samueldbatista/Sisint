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
<!DOCTYPE html>
<tags:teste>
    <jsp:attribute name="cabecalho">
    </jsp:attribute>
    <jsp:attribute name="rodape">

    </jsp:attribute>

    <jsp:body>
        <div class="panel col-md-6 col-md-offset-3" style="margin-top: 50px;">
            <div class="panel-heading">
                <div class="panel-title">Cadastrar usuários</div>
            </div>
            <div class="panel-body" style="padding-top: 0px;">
                <form class="form-horizontal" action="${linkTo[UsuariosController].salvar}" method="post">
                    <input type="hidden" name="usuario.id" value="${usuario.id}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Nome</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="nome-usuario" name="usuario.nome" type="text" value="${usuario.nome}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Login</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="login-usuario" name="usuario.login" type="text" value="${usuario.login}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Tipo</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="tipo-usuario" name="usuario.tipoUsuario" type="text">
                                <option value=""></option>
                                <c:forEach items="${tipoUsuario}" var="tipo">
                                    <option value="${tipo.valor}">${tipo.chave}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Telefone</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="telefone-usuario" name="usuario.telefone" type="text" value="${usuario.telefone}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="email-usuario" name="usuario.email" type="text" value="${usuario.email}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Senha</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="senha-usuario" name="usuario.senha" type="password" value="${usuario.senha}">
                        </div>
                    </div>
                    <button class="btn btn-primary col-md-offset-10" type="submit">Cadastrar</button>

                </form>
            </div>

        </div>
    </jsp:body>
</tags:teste>
