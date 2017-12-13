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
<!DOCTYPE html>
<tags:teste>

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
        <script src="${ctx}/resources/js/ConverterObjetoParaVRaptor.js"></script>
        <script src="${ctx}/resources/plugins/jquerySteps/jquery.steps.min.js"></script>
        <script src="${ctx}/resources/js/servicos/tarefas.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel painel-cadastro-sisint">
            <form id="form-servico-tarefa" action="${linkTo[ServicosController].salvar}" method="post">
                <c:forEach items="${listaLogs}" var="log" varStatus="status">
                    <input type="hidden" name="servico.logServicos[${status.index}].id" value="${log.id}"/>
                    <input type="hidden" name="servico.logServicos[${status.index}].log" value="${log.log}"/>
                    <input type="hidden" name="servico.logServicos[${status.index}].usuario.id" value="${log.usuario.id}"/>
                    <input type="hidden" name="servico.logServicos[${status.index}].servico.id" value="${log.servico.id}"/>
                    <input type="hidden" name="servico.logServicos[${status.index}].dataAlteracao" value="${log.dataAlteracao}"/>
                </c:forEach>
                <input type="hidden" name="servico.codigoServico" value="${servico.codigoServico}"/>
                <div id="container-inputs-tarefa"></div>
                <div class="panel-body">
                    <input id="urlSalvar" type="hidden" value="${linkTo[ServicosController].salvar}"/>
                    <h4 class="tituloCadastro">Cadastro de Serviços</h4>
                    <div id="cadastro-servico">
                        <div class="row">
                            <input id="servico-id" type="hidden" name="servico.id" value="${servico.id}"/>
                            <input id="servico-dataAbertura" type="hidden" name="servico.dataAbertura" value="${servico.dataAbertura}"/>
                            <div class="form-group col-md-6">
                                <label for="titulo-servico">Título</label>
                                <input type="text" class="form-control input-sm" id="titulo-servico" required="true"
                                       value="${servico.titulo}"
                                       placeholder="Titulo do serviço"
                                       name="servico.titulo"/>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="nomeSolicitante-servico">Nome do solicitante</label>
                                <input type="text" class="form-control input-sm" id="nomeSolicitante-servico"
                                       required="true"
                                       value="${servico.nomeSolicitante}"
                                       placeholder="Nome do solicitante" name="servico.nomeSolicitante"/>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="setor-servico">Setor solicitante</label>
                                <select class="form-control input-sm" id="setor-servico" name="servico.setor.id">
                                    <option value=""></option>
                                    <c:forEach items="${setores}" var="setor">
                                        <c:if test="${setor.valor == servico.setor.id}">
                                            <option value="${setor.valor}" selected="true">${setor.chave}</option>
                                        </c:if>
                                        <c:if test="${!(setor.valor == servico.setor.id)}">
                                            <option value="${setor.valor}">${setor.chave}</option>
                                        </c:if>
                                    </c:forEach>

                                </select>
                            </div>

                            <div class="form-group col-md-3">
                                <label for="data-fechamento-servico">Data de Finalização</label>
                                <input type="text" class="form-control datePicker" id="data-fechamento-servico"
                                       required="true"
                                       value="${servico.dataFechamento}"
                                       placeholder="Data de finalização" name="servico.dataFechamento"/>
                            </div>

                            <div class="form-group col-md-3">
                                <label for="prioridade-servico">Prioridade</label>
                                <select class="form-control input-sm" id="prioridade-servico" name="servico.prioridade">
                                    <option value=""></option>
                                    <c:forEach items="${prioridades}" var="prioridade">
                                        <c:if test="${prioridade.valor == servico.prioridade.valor}">
                                            <option value="${prioridade.valor}"
                                                    selected="true">${prioridade.chave}</option>
                                        </c:if>
                                        <c:if test="${!(prioridade.valor == servico.prioridade.valor)}">
                                            <option value="${prioridade.valor}">${prioridade.chave}</option>
                                        </c:if>
                                    </c:forEach>

                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="tecnico-servico">Ténico Responsável</label>
                                <select class="form-control input-sm" id="tecnico-servico" name="servico.tecnico.id">
                                    <option></option>
                                    <c:forEach items="${usuarios}" var="usuario">
                                        <c:if test="${usuario.valor == servico.tecnico.id}">
                                            <option value="${usuario.valor}" selected="true">${usuario.chave}</option>
                                        </c:if>
                                        <c:if test="${!(usuario.valor == servico.tecnico.id)}">
                                            <option value="${usuario.valor}">${usuario.chave}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group col-md-12">
                                <label for="descricao">Descrição:</label>
                                <textarea class="form-control" name="servico.descricao" rows="2" required="true"
                                          id="descricao">${servico.descricao}</textarea>
                            </div>
                        </div>
                    </div>
                    <div id="cadastro-tarefa">
                        <div class="row" align="right">
                            <button id="btnAdicionarTarefa" type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">
                                Adicionar Tarefa
                            </button>
                        </div>
                    </div>
                    <div id="tarefas-cadastradas" class="list-group" style="margin-top: 16px;">
                    </div>

                </div>
                <div class="panel-footer" align="right">
                    <button class="btn btn-primary" type="submit">Salvar</button>
                </div>
            </form>
                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">
                        <input type="hidden" name="tarefa.id" value="${tarefa.id}"/>
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Cadastro de tarefa</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="titulo-tarefa">Título:</label>
                                        <div class="col-sm-10">
                                            <input type="email" name="tarefa.titulo" class="form-control"
                                                   id="titulo-tarefa" placeholder="Titulo">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="data-fechamento-tarefa">Data de
                                            Finalização:</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control datePicker"
                                                   id="data-fechamento-tarefa"
                                                   required="true"
                                                   value="${tarefa.dataFechamento}"
                                                   placeholder="Data de finalização" name="tarefa.dataFechamento"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="status-tarefa">Status</label>
                                        <div class="col-md-10">
                                            <select type="text" class="form-control" id="status-tarefa"
                                                    placeholder="Status"
                                                    required="true"
                                                    name="tarefa.statusTarefa">
                                                <option value=""></option>
                                                <c:forEach items="${status}" var="s">
                                                    <c:if test="${s.valor == tarefa.statusTarefa.valor}">
                                                        <option value="${s.valor}" selected="true">${s.chave}</option>
                                                    </c:if>
                                                    <c:if test="${!(s.valor == tarefa.statusTarefa.valor)}">
                                                        <option value="${s.valor}">${s.chave}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="tecnico-tarefa">Ténico
                                            Responsável</label>
                                        <div class="col-md-10">
                                            <select class="form-control" id="tecnico-tarefa" name="tarefa.tecnico">
                                                <option value=""></option>
                                                <c:forEach items="${usuarios}" var="usuario">
                                                    <c:if test="${usuario.valor == servico.tecnico.id}">
                                                        <option value="${usuario.valor}"
                                                                selected="true">${usuario.chave}</option>
                                                    </c:if>
                                                    <c:if test="${!(usuario.valor == servico.tecnico.id)}">
                                                        <option value="${usuario.valor}">${usuario.chave}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <label>Possui pendência:
                                        <div style="margin-left: 16px; float: right;">
                                            <input type="checkbox" name="tarefa.pendente" value="${tarefa.pendente}"/>
                                        </div>
                                    </label>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" for="descricao-tarefa">Descrição:</label>
                                        <div class="col-md-10">
                                            <textarea class="form-control" name="tarefa.descricao" rows="2"
                                                      required="true"
                                                      id="descricao-tarefa">${tarefa.descricao}</textarea>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                <button id="btnSalvarTarefa" type="button" class="btn btn-primary" data-dismiss="modal">
                                    Salvar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

        </div>
    </jsp:body>
</tags:teste>
