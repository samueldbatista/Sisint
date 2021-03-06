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
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<tags:layout>
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

        <script>
            var idServico = "";
            var url = $('#btnSalvarTarefa').attr('href');
            var urlServicoPadrao = $('#urlServicoPadrao').val();
            console.log(url);
//            var $btnAssumir = $('#assumir-servico');
//            atribuirListennerBtnEdicao();
//            function atribuirListennerBtnEdicao($btnEditar) {
                $('.assumir-servico').off('click');
                $('.assumir-servico').each(function () {
                    console.log('oi');
                    $(this).click(function () {
                        var novaUrl;
                        console.log("entrou")
                        idServico = $(this).attr('id-servico');
                        novaUrl = url + idServico;
                        console.log(url);
                        var novaUrlServPadrao = urlServicoPadrao +"?id="+idServico;
                        $('#btnSalvarTarefa').attr('href', novaUrl);
                        $('#salvarServPadrao').attr('href', novaUrlServPadrao);
                    });
                });


//            }
//            var a = $("#assumir-servico").attr('id-servico');
//            console.log(a);

        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="panel">
            <div class="panel-heading">
                <div class="panel-title">Serviços em abertos</div>
            </div>
            <input id="urlServicoPadrao" type="hidden" value="${linkTo[ServicosController].assumirServicoComTarefa}">
            <div class="panel-body" style="padding-top: 0px;">
                <a class="btn btn-info" style="margin-bottom: 16px;" href="${linkTo[ServicosController].form}">Cadastrar</a>
                <div class="tabela-servicos">
                    <table class="table table-bordered tabela">
                        <thead>
                        <tr>
                            <th>Titulo</th>
                            <th>Status</th>
                            <th>Prioridade</th>
                            <th>Data de Abertura</th>
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
                                <td class="date-column">${servico.dataAbertura}</td>
                                <td class="date-column">${servico.dataFechamento}</td>
                                <td>${servico.tecnico.nome}</td>
                                <td>${servico.setor.nome}</td>
                                <td><a title="Detalhes" href="${linkTo[ServicosController].detalhes}?id=${servico.id}"><i class="fa fa-eye" aria-hidden="false"></i></a>
                                    <a class="assumir-servico" id-servico="${servico.id}" title="Assumir serviço" data-toggle="modal" href="#myModal"><i class="fa fa-check-circle-o" aria-hidden="true"></i></a>
                                    <a title="Visualizar log" href="${linkTo[ServicosController].logServico}?id=${servico.id}">
                                        <i class="fa fa-list-ul" aria-hidden="true"></i></a></td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog">
                <input type="hidden" name="tarefa.id" value="${tarefa.id}"/>
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title primary">Serviço aberto</h4>
                    </div>
                    <div class="modal-body">
                        <h5> Deseja assumir o serviço?</h5>
                    </div>
                    <div id="btns-modal" class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        <a id="salvarServPadrao" href="#" class="btn btn-success">Salvar e criar tarefa</a>
                        <a id="btnSalvarTarefa" href="${linkTo[ServicosController].assumirServico}?id=" class="btn btn-primary">
                        Salvar
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</tags:layout>
