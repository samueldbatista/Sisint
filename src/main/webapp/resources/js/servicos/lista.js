/**
 * Created by samue on 15/09/2017.
 */

$(document).ready(function () {
    var $tabela = $(this).find('table');
    var $span =  $(this).find('span');
    var status = {
        concluido: "Concluído",
        emExecucao: "Em execução",
        cancelado: "Em execução",
        aguardando: "Aguardando execução",
    };
    var prioridade = {
        alta: "Alta",
        media: "Média",
        baixa: "Baixa",
    };
    $span.each(function () {
        var descricao =  $(this).text();
        console.log(descricao);
        if(descricao == status.concluido) {
            $(this).addClass('label-success');
            $(this).removeClass('label-info');
        } else if(descricao == status.emExecucao || descricao == prioridade.baixa){
            $(this).addClass('label-info');
            $(this).removeClass('label-success');
        } else if(descricao == status.cancelado || descricao == prioridade.alta){
            $(this).addClass('label-danger');
        } else if(descricao == status.aguardando || descricao == prioridade.media){
            $(this).addClass('label-warning');
        }
    });

    // $(".date-column").each(function () {
    //    var data =  $(this).text();
    //    data = moment(data, 'YYYY-MM-DD').format('DD/MM/YYYY');
    //    $(this).text(data);
    // });


});





