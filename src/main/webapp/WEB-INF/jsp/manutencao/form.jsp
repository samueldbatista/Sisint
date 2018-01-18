<%--
  Created by IntelliJ IDEA.
  User: samue
  Date: 08/09/2017
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" uri="tagSisInt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>

    <jsp:attribute name="cabecalho">
        <link href="${ctx}/resources/plugins/dataPicker/dataPicker.css" rel="stylesheet"/>
        <link href="${ctx}/resources/css/componentes/checkbox.css" rel="stylesheet"/>
        <style>
            label {
                font-weight: 600;
                font-size: 12px;
            }

            .datepicker {
                z-index: 1151 !important;
            }

            .panel-title {
                border-bottom: 1px solid;
            }
        </style>
    </jsp:attribute>

    <jsp:attribute name="rodape">
        <%--<script src="${ctx}/resources/js/servicos/tarefas.js"></script>--%>
        <script>
            $('#form-manutencao-tarefa').validator();
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-cadastro-sisint">
            <form id="form-manutencao-tarefa" action="${linkTo[ManutencaoController].salvar}" method="post" enctype="multipart/form-data">
                <%--<input type="hidden" name="manutencao.codigoServico" value="${manutencao.codigoServico}"/>--%>
                <div id="container-inputs-tarefa"></div>
                <div class="panel-body">
                    <h4 class="tituloCadastro">Cadastro de manutenções</h4>
                    <div id="cadastro-manutencao">
                        <div class="row">
                            <input id="manutencao-id" type="hidden" name="manutencao.id" value="${manutencao.id}"/>
                            <input id="manutencao-dataAbertura" type="hidden" name="manutencao.dataAbertura" value="${manutencao.dataAbertura}"/>
                            <div class="form-group col-md-6">
                                <label for="titulo-manutencao">Título</label>
                                <input type="text" minlength="5" class="form-control input-sm" id="titulo-manutencao" required="true"
                                       value="${manutencao.titulo}"
                                       placeholder="Titulo do serviço"
                                       name="manutencao.titulo"/>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="nomeSolicitante-manutencao">Nome do solicitante</label>
                                <input type="text" minlength="3" class="form-control input-sm" id="nomeSolicitante-manutencao"
                                       required="true"
                                       value="${manutencao.nomeSolicitante}"
                                       placeholder="Nome do solicitante" name="manutencao.nomeSolicitante"/>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="setor-manutencao">Setor solicitante</label>
                                <select class="form-control input-sm" required id="setor-manutencao" name="manutencao.setor.id">
                                    <option value=""></option>
                                    <c:forEach items="${setores}" var="setor">
                                        <c:if test="${setor.valor == manutencao.setor.id}">
                                            <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                        </c:if>
                                        <c:if test="${!(setor.valor == manutencao.setor.id)}">
                                            <option value="${setor.valor}">${setor.chave}</option>
                                        </c:if>
                                    </c:forEach>

                                </select>
                            </div>

                            <div class="form-group col-md-3">
                                <label for="data-fechamento-manutencao">Data de Finalização</label>
                                <input type="text" class="form-control datePicker" id="data-fechamento-manutencao"
                                       required="true"
                                       value="${manutencao.dataFechamento}"
                                       placeholder="Data de finalização" readonly="readonly" name="manutencao.dataFechamento"/>
                            </div>

                            <%--<div class="form-group col-md-3">--%>
                                <%--<label for="prioridade-manutencao">Prioridade</label>--%>
                                <%--<select class="form-control input-sm" id="prioridade-manutencao" name="manutencao.prioridade">--%>
                                    <%--<option value=""></option>--%>
                                    <%--<c:forEach items="${prioridades}" var="prioridade">--%>
                                        <%--<c:if test="${prioridade.valor == manutencao.prioridade.valor}">--%>
                                            <%--<option value="${prioridade.valor}"--%>
                                                    <%--selected="true">${prioridade.chave}</option>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${!(prioridade.valor == manutencao.prioridade.valor)}">--%>
                                            <%--<option value="${prioridade.valor}">${prioridade.chave}</option>--%>
                                        <%--</c:if>--%>
                                    <%--</c:forEach>--%>

                                <%--</select>--%>
                            <%--</div>--%>
                            <div class="form-group col-md-6">
                                <label for="tecnico-manutencao">Ténico Responsável</label>
                                <select class="form-control input-sm" id="tecnico-manutencao" name="manutencao.tecnico.id">
                                    <option></option>
                                    <c:forEach items="${usuarios}" var="usuario">
                                        <c:if test="${usuario.valor == manutencao.tecnico.id}">
                                            <option value="${usuario.valor}" selected="true">${usuario.chave}</option>
                                        </c:if>
                                        <c:if test="${!(usuario.valor == manutencao.tecnico.id)}">
                                            <option value="${usuario.valor}">${usuario.chave}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-2" style="margin-top:2em;">
                                <input type="file" name="memorando"/>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="descricao">Descrição:</label>
                                <textarea class="form-control" name="manutencao.descricao" rows="2" required="true"
                                          id="descricao">${manutencao.descricao}</textarea>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel-footer" align="right">
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </div>
            </form>
        </div>
    </jsp:body>
</tags:layout>
