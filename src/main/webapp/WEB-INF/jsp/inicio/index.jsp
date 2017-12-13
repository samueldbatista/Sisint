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
        <link href="${ctx}/resources/css/custom.css" rel="stylesheet"/>
    </jsp:attribute>

    <jsp:attribute name="rodape">
    </jsp:attribute>
    <jsp:body>
        <%--<div class="row">--%>
        <div class="panel painel-sisint">
        <div class="row tile_count">
            <div class="col-md-2 col-md-offset-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-tasks"></i> Total de serviços</span>
                <div class="count">${totalServicos}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-tasks"></i> Serviços em aberto</span>
                <div class="count">${servicosAbertos}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-tasks"></i> Serviços em execução</span>
                <div class="count green">${servicosExecucao}</div>
            </div>
            <div class="col-md-2 col-sm-4 col-xs-6 tile_stats_count">
                <span class="count_top"><i class="fa fa-tasks"></i> Total de Tarefas</span>
                <div class="count">${totalTarefas}</div>
            </div>
        </div>
        </div>
        <%--</div>--%>
        <%--<div class="row">--%>
            <div class="panel painel-sisint" style="min-height: 200px;">
                <div class="panel-body">
                    <div class="col-md-12">
                        <%--<div class="col-md-12 caixa-pontilhada">--%>
                            <h3 class="cor-titulo-azul" align="center">Informações gerais</h3>
                            <div class="col-md-6" align="center">
                                <h5 class="telefone">CENTAURO: (84) 3231 - 2272</h5>
                                <h5 class="telefone">CETINF : (84) 3232 - 1863</h5>
                                <h5 class="telefone">COTIC : (84) 3232 - 1050</h5>
                                <h5 class="telefone">VELOX : 0800 - 0318031</h5>
                                <h5 class="telefone">EMAIL INST: (84) 3232 - 1047</h5>
                                <h5 class="telefone">ITANILDO DEFD : (84) 98734 - 7034</h5>
                                <h5 class="telefone">TI RIBEIRA : (84) 3232 - 2859</h5>
                                <h5 class="telefone">LAB-LD RONALDO : (84) 3605 - 9646</h5>
                            </div>
                            <div class="col-md-6" align="center">
                                <h5 class="telefone">Email institucional: tipc@rn.gov.br</h5>
                            </div>

                        <%--</div>--%>
                    </div>
                </div>
            </div>
        <%--</div>--%>
    </jsp:body>
</tags:teste>
