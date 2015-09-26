/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var xmlHttpObj = null;
var isPostBack = false;
var email = document.getElementById("form:inputemail");
var name = document.getElementById("form:inputname");
var password = document.getElementById("form:inputpassword");
var confirmPassword = document.getElementById("form:inputconfirmpassword");
var path = "http://localhost:8080/BacfWebApp/webresources/";
var indexMailOptActive = '0';
var mailOptions = new Array("Inbox", "Sent");
var disp, disp1, disp2, disp3, disp4, disp5, disp6;
var dispOptions = new Array("Manh&atilde;", "Tarde", "Noite", "Sem");
var selectedRadioButtons = new Array(0, 0, 0, 0, 0, 0, 0);
var searchFilterOption = 'name';
var materiais;
var voluntarioCampanha;
var voluntarioID;
var generatedRelPontID;
var materials;
var pesquisaSideBar;
var pesquisaRelPonto;
var tableEntradas;
var tableSaidas;
var dataSetSaidas;
var dataSetEntradas; //= [
//    ['PILAS', 'Internet Explorer 4.0', 'Win 95+', '4', 'X'],
//    ['Trident', 'Internet Explorer 5.0', 'Win 95+', '5', 'C'],
//    ['Trident', 'Internet Explorer 5.5', 'Win 95+', '5.5', 'A'],
//    ['Trident', 'Internet Explorer 6', 'Win 98+', '6', 'A'],
//    ['Trident', 'Internet Explorer 7', 'Win XP SP2+', '7', 'A'],
//    ['Trident', 'AOL browser (AOL desktop)', 'Win XP', '6', 'A'],
//    ['Gecko', 'Firefox 1.0', 'Win 98+ / OSX.2+', '1.7', 'A'],
//    ['Gecko', 'Firefox 1.5', 'Win 98+ / OSX.2+', '1.8', 'A'],
//    ['Gecko', 'Firefox 2.0', 'Win 98+ / OSX.2+', '1.8', 'A'],
//    ['Gecko', 'Firefox 3.0', 'Win 2k+ / OSX.3+', '1.9', 'A'],
//    ['Gecko', 'Camino 1.0', 'OSX.2+', '1.8', 'A'],
//    ['Gecko', 'Camino 1.5', 'OSX.3+', '1.8', 'A'],
//    ['Gecko', 'Netscape 7.2', 'Win 95+ / Mac OS 8.6-9.2', '1.7', 'A'],
//    ['Gecko', 'Netscape Browser 8', 'Win 98SE+', '1.7', 'A'],
//    ['Gecko', 'Netscape Navigator 9', 'Win 98+ / OSX.2+', '1.8', 'A'],
//    ['Gecko', 'Mozilla 1.0', 'Win 95+ / OSX.1+', 1, 'A'],
//    ['Gecko', 'Mozilla 1.1', 'Win 95+ / OSX.1+', 1.1, 'A'],
//    ['Gecko', 'Mozilla 1.2', 'Win 95+ / OSX.1+', 1.2, 'A'],
//    ['Gecko', 'Mozilla 1.3', 'Win 95+ / OSX.1+', 1.3, 'A'],
//    ['Gecko', 'Mozilla 1.4', 'Win 95+ / OSX.1+', 1.4, 'A'],
//    ['Gecko', 'Mozilla 1.5', 'Win 95+ / OSX.1+', 1.5, 'A'],
//    ['Gecko', 'Mozilla 1.6', 'Win 95+ / OSX.1+', 1.6, 'A'],
//    ['Gecko', 'Mozilla 1.7', 'Win 98+ / OSX.1+', 1.7, 'A'],
//    ['Gecko', 'Mozilla 1.8', 'Win 98+ / OSX.1+', 1.8, 'A'],
//    ['Gecko', 'Seamonkey 1.1', 'Win 98+ / OSX.2+', '1.8', 'A'],
//    ['Gecko', 'Epiphany 2.20', 'Gnome', '1.8', 'A'],
//    ['Webkit', 'Safari 1.2', 'OSX.3', '125.5', 'A'],
//    ['Webkit', 'Safari 1.3', 'OSX.3', '312.8', 'A'],
//    ['Webkit', 'Safari 2.0', 'OSX.4+', '419.3', 'A'],
//    ['Webkit', 'Safari 3.0', 'OSX.4+', '522.1', 'A'],
//    ['Webkit', 'OmniWeb 5.5', 'OSX.4+', '420', 'A'],
//    ['Webkit', 'iPod Touch / iPhone', 'iPod', '420.1', 'A'],
//    ['Webkit', 'S60', 'S60', '413', 'A'],
//    ['Presto', 'Opera 7.0', 'Win 95+ / OSX.1+', '-', 'A'],
//    ['Presto', 'Opera 7.5', 'Win 95+ / OSX.2+', '-', 'A'],
//    ['Presto', 'Opera 8.0', 'Win 95+ / OSX.2+', '-', 'A'],
//    ['Presto', 'Opera 8.5', 'Win 95+ / OSX.2+', '-', 'A'],
//    ['Presto', 'Opera 9.0', 'Win 95+ / OSX.3+', '-', 'A'],
//    ['Presto', 'Opera 9.2', 'Win 88+ / OSX.3+', '-', 'A'],
//    ['Presto', 'Opera 9.5', 'Win 88+ / OSX.3+', '-', 'A'],
//    ['Presto', 'Opera for Wii', 'Wii', '-', 'A'],
//    ['Presto', 'Nokia N800', 'N800', '-', 'A'],
//    ['Presto', 'Nintendo DS browser', 'Nintendo DS', '8.5', 'C/A<sup>1</sup>'],
//    ['KHTML', 'Konqureror 3.1', 'KDE 3.1', '3.1', 'C'],
//    ['KHTML', 'Konqureror 3.3', 'KDE 3.3', '3.3', 'A'],
//    ['KHTML', 'Konqureror 3.5', 'KDE 3.5', '3.5', 'A'],
//    ['Tasman', 'Internet Explorer 4.5', 'Mac OS 8-9', '-', 'X'],
//    ['Tasman', 'Internet Explorer 5.1', 'Mac OS 7.6-9', '1', 'C'],
//    ['Tasman', 'Internet Explorer 5.2', 'Mac OS 8-X', '1', 'C'],
//    ['Misc', 'NetFront 3.1', 'Embedded devices', '-', 'C'],
//    ['Misc', 'NetFront 3.4', 'Embedded devices', '-', 'A'],
//    ['Misc', 'Dillo 0.8', 'Embedded devices', '-', 'X'],
//    ['Misc', 'Links', 'Text only', '-', 'X'],
//    ['Misc', 'Lynx', 'Text only', '-', 'X'],
//    ['Misc', 'IE Mobile', 'Windows Mobile 6', '-', 'C'],
//    ['Misc', 'PSP browser', 'PSP', '-', 'C'],
//    ['Other browsers', 'All others', '-', '-', 'U']
//];

var dataArray;
var options;
var chart;
var dataArray1;
var options1;
var chart1;

function drawChart1() {
    dataArray1 = google.visualization.arrayToDataTable([
        ['NºVoluntários', 'Masculino', 'Feminino'],
        ['Sexo', 0, 0],
    ]);

    options1 = {
        title: 'Voluntários em Campanha +3H',
        hAxis: {title: '', titleTextStyle: {color: 'red'}}
    };

    chart1 = new google.visualization.ColumnChart(document.getElementById('secondChart'));
    chart1.draw(dataArray1, options1);

}

function drawChart() {
    dataArray = google.visualization.arrayToDataTable([
        ['NºVoluntários', 'Masculino', 'Feminino'],
        ['Sexo', 1170, 460],
    ]);

    options = {
        title: 'Voluntário em Campanha',
        hAxis: {title: '', titleTextStyle: {color: 'red'}}
    };

    chart = new google.visualization.ColumnChart(document.getElementById('firstChart'));
    chart.draw(dataArray, options);

}

function d() {
    $(document).ready(function() {
        countUsers();
        emFCampannha();
        done();
    });

    function done() {
        setTimeout(function() {
            countUsers();
            emFCampannha();
            done();
        }, 20000);
    }
}

function emFCampannha() {
    $.ajax({
        url: path + 'persistence.entities.relogioponto/list',
        type: 'GET',
        contentType: 'text/plain; charset=utf-8',
        success: function(data1) {

            var x = data1.split(" ");
            var y = x[0];
            var y1 = x[1];

            data.setValue(0, 1, parseInt(x[0]));
            data.setValue(0, 2, parseInt(x[1]));
            chart.draw(data, options);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });

    $.ajax({
        url: path + 'persistence.entities.relogioponto/moreThan3Hours',
        type: 'GET',
        contentType: 'text/plain; charset=utf-8',
        success: function(data1) {

            var x = data1.split(" ");
            var y = x[0];
            var y1 = x[1];
            // alert(y+y1);
            dataArray1.setValue(0, 1, parseInt(x[0]));
            dataArray1.setValue(0, 2, parseInt(x[1]));
            chart1.draw(dataArray1, options1);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}

function countUsers() {
    $.ajax({
        url: path + 'persistence.entities.voluntario/count',
        type: 'GET',
        dataType: 'JSON',
        contentType: 'text/plain; charset=utf-8',
        success: function(data) {
            document.getElementById("utilizadores").innerHTML = data;
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
    $.ajax({
        url: path + 'persistence.entities.voluntario/list',
        type: 'GET',
        contentType: 'application/json; charset=utf-8 ',
        success: function(data) {
            var x = data.split(" ");

            document.getElementById("emptyField").innerHTML = x[1];

        },
        error: function(err) {
            alert("ERRO" + JSON.stringify(err));
        }
    });
    $.ajax({
        url: path + 'persistence.entities.voluntario/list',
        type: 'GET',
        contentType: 'text/plain; charset=utf-8',
        success: function(data) {
            var x = data.split(" ");
            document.getElementById("volNRegist").innerHTML = x[2];
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });

    return 0;
}



function initDataTables()
{
    $.ajax({
        url: path + 'persistence.entities.relogioponto/voluntarios/entradas',
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            createTableEnt(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
    $.ajax({
        url: path + 'persistence.entities.relogioponto/voluntarios/saidas',
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            createTableSaid(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });

}

function createTableEnt(data)
{
    dataSetEntradas = initArray(dataSetEntradas, data.length, 5);
    for (var i = 0; i < data.length; i++)
    {
        alert(data[i].toString());
        dataSetEntradas[i][0] = data[i].nome;
        dataSetEntradas[i][1] = data[i].correiosID.localidade;
        dataSetEntradas[i][2] = data[i].voluntarioID;
        dataSetEntradas[i][3] = data[i].bi;
        dataSetEntradas[i][4] = data[i].email;
    }

    createTableEntradas("#dataTables-example");

}

function initArray(array, rows, col)
{
    array = new Array(rows);
    for (var i = 0; i < rows; i++) {
        array[i] = new Array(col);
    }

    return array;
}

function createTableSaid(data)
{
    dataSetSaidas = initArray(dataSetSaidas, data.length, 5);

    for (var i = 0; i < data.length; i++)
    {
        alert(data[i].toString());
        dataSetSaidas[i][0] = data[i].nome;
        dataSetSaidas[i][1] = data[i].correiosID.localidade;
        dataSetSaidas[i][2] = data[i].voluntarioID;
        dataSetSaidas[i][3] = data[i].bi;
        dataSetSaidas[i][4] = data[i].email;
    }

    createTableSaidas("#dataTables-example1");

}

function createTableEntradas(tablename)
{
    tableEntradas = $(tablename).dataTable({
        "data": dataSetEntradas,
        "columns": [
            {"title": "Nome"},
            {"title": "Localidade"},
            {"title": "Num&eacute;ro Volunt&aacute;rio"},
            {"title": "Cart&atilde;o Cidad&atilde;o"},
            {"title": "Email"}
        ],
        "iDisplayLength": 5,
        "lengthMenu": [5, 10, 25, 50, "All"],
        destroy: true
    });

}

function createTableSaidas(tablename)
{
    tableSaidas = $(tablename).dataTable({
        "data": dataSetSaidas,
        "columns": [
            {"title": "Nome"},
            {"title": "Localidade"},
            {"title": "Num&eacute;ro Volunt&aacute;rio"},
            {"title": "Cart&atilde;o Cidad&atilde;o"},
            {"title": "Email"}
        ],
        "iDisplayLength": 5,
        "lengthMenu": [5, 10, 25, 50, "All"],
        destroy: true
    });
}

function updateTables()
{
    $.ajax({
        url: path + 'persistence.entities.relogioponto/voluntarios/entradas',
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            updateTableEntradas(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
    $.ajax({
        url: path + 'persistence.entities.relogioponto/voluntarios/saidas',
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            updateTableSaidas(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}

function updateTableSaidas(data)
{
//    tableSaidas.fnDestroy();  
    dataSetSaidas = initArray(dataSetSaidas, data.length, 5);

    for (var i = 0; i < data.length; i++)
    {
        dataSetSaidas[i][0] = data[i].nome;
        dataSetSaidas[i][1] = data[i].correiosID.localidade;
        dataSetSaidas[i][2] = data[i].voluntarioID;
        dataSetSaidas[i][3] = data[i].bi;
        dataSetSaidas[i][4] = data[i].email;
    }


//    createTableSaidas("dataTables-example1");
    tableSaidas.fnClearTable();
    tableSaidas.fnAddData(dataSetSaidas);
    tableSaidas.fnDraw();
}

function updateTableEntradas(data)
{
//    tableEntradas.fnDestroy();
    dataSetEntradas = initArray(dataSetEntradas, data.length, 5);

    for (var i = 0; i < data.length; i++)
    {
        dataSetEntradas[i][0] = data[i].nome;
        dataSetEntradas[i][1] = data[i].correiosID.localidade;
        dataSetEntradas[i][2] = data[i].voluntarioID;
        dataSetEntradas[i][3] = data[i].bi;
        dataSetEntradas[i][4] = data[i].email;
    }

    tableEntradas.fnClearTable();
    tableEntradas.fnAddData(dataSetEntradas);
    tableEntradas.fnDraw();
}

function findVoluntarios()
{
    pesquisaSideBar = true;
    document.getElementById("materiais").innerHTML = '';
    var search = document.getElementById("inputsearch");
    CallWebServiceVoluntario(search.value);
}

function updateFilterSearch(obj)
{
    searchFilterOption = obj;

}

function CallWebServiceVoluntario(obj) {

    var regExprEmail = /^[a-zA-Z0-9\._-]+@[a-zA-Z\-]+.([a-zA-Z]{2,3})$/;
    var regExprName = /^[a-zA-Z\s]+$/;
    var regExprBI = /^([0-9]{1,8})$/;
    var regExprTelemovel = /^([0-9]{9})$/;

    if ((regExprName.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/name/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {

                showVoluntarios(data);

            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

    if ((regExprEmail.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/email/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {

                showVoluntarios(data);

            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

    if ((regExprBI.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/bi/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {

                showVoluntarios(data);

            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

    if ((regExprTelemovel.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/telemovel/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {

                showVoluntarios(data);

            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

}

function findVoluntario(obj) {

    var regExprEmail = /^[a-zA-Z0-9\._-]+@[a-zA-Z\-]+.([a-zA-Z]{2,3})$/;
    var regExprName = /^[a-zA-Z\s]+$/;
    var regExprBI = /^([0-9]{1,8})$/;
    var regExprTelemovel = /^([0-9]{9})$/;

    if ((regExprName.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/name/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {

                showVoluntario(data);

            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

    if ((regExprEmail.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/email/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                showVoluntario(data);
            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

    if ((regExprBI.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/bi/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                showVoluntario(data);
            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

    if ((regExprTelemovel.test(obj))) {
        $.ajax({
            url: path + 'persistence.entities.voluntario/telemovel/' + obj,
            type: 'GET',
            dataType: 'JSON',
            contentType: 'application/json; charset=utf-8',
            success: function(data) {
                showVoluntario(data);
            },
            error: function(err) {
                alert(JSON.stringify(err));
            }
        });
    }

}

function showVoluntario(data)
{
    showVoluntarioInRelogio(data[0].nome + ',' + data[0].voluntarioID);
}

function showVoluntarios(data)
{
    // alert(JSON.stringify(data).toString());
    var sideBarTable = document.getElementById("voluntariostable");
    var content = createTableResponsiveHover(data);

    sideBarTable.innerHTML = content;
}


function createTableResponsiveHover(data)
{

    var initSideBarSow = '<div class="sidebar-show">' +
            '<div class="box-header">' +
            '<h3 class="box-title">Voluntarios</h3>' +
            '<div class="box-tools">' +
            '<div class="input-group">' +
            '</div>' +
            '</div>' +
            '</div><!-- /.box-header -->';
    var initTable = '<div class="box-body table-responsive">' +
            '<table class="table table-hover">' +
            '<tbody>';
    var rows = '';
    var finaliseTable =
            '</tbody>' +
            '</table>' +
            '</div><!-- /.box-body -->';
    var finaliseSideBarShow = '</div>';


    for (var i = 0; i < data.length; i++)
    {
        var camp = isInCampanha(data[i].voluntarioID);
        if (camp === 2)
        {
            var rowVoluntarioEmCampanha = '<tr onclick="search(\'' + data[i].nome + ',' + data[i].voluntarioID + '\')">' +
                    '<td>' + data[i].nome + '</td>' +
                    '<td>' + data[i].correiosID.localidade + '</td>' +
                    '<td><span id="' + data[i].voluntarioID + '" class = "label label-success"> participou </span></td>' +
                    '</tr>';
            rows += rowVoluntarioEmCampanha;
        }
        if (camp === 1)
        {
            var rowVoluntarioEmCampanha = '<tr onclick="search(\'' + data[i].nome + ',' + data[i].voluntarioID + '\')">' +
                    '<td>' + data[i].nome + '</td>' +
                    '<td>' + data[i].correiosID.localidade + '</td>' +
                    '<td><span id="' + data[i].voluntarioID + '" class = "label label-warning"> em campanha </span></td>' +
                    '</tr>';
            rows += rowVoluntarioEmCampanha;
        }
        if (camp === 0)
        {
            var rowVoluntarioSCampanha = '<tr onclick="search(\'' + data[i].nome + ',' + data[i].voluntarioID + '\')">' +
                    '<td>' + data[i].nome + '</td>' +
                    '<td>' + data[i].correiosID.localidade + '</td>' +
                    '<td> <span id="' + data[i].voluntarioID + '" class="label label-danger"> n&atildeo participou </span></td>' +
                    '</tr>';
            rows += rowVoluntarioSCampanha;
        }
    }

    if (data.length === 0)
    {
        rows = '<h4 class="text-danger">N&atilde;o foram encontrados resultados</h4>';
    }

    return initSideBarSow += initTable + rows + finaliseTable + finaliseSideBarShow;

}

function isInCampanha(voluntarioID)
{
    /*
     * 
     * @type @exp;JSON@call;stringify
     * Procurar Voluntario na Campanha 
     */
    CallWebServiceVoluntarioRelogioPonto(voluntarioID);
    var aux = JSON.stringify(voluntarioCampanha);
    /*
     * Verfica se esta em campanha
     */
    if (typeof aux === 'undefined')
        return 0;
    /*
     * 
     * Verifica se ainda esta na Campanha
     */
    if (voluntarioCampanha.tipoRegisto === false)
        return 1;
    /*
     * 
     * Verifica se jÃ¡ saiu da campanha
     */
    if (voluntarioCampanha.tipoRegisto === true)
        return 2;
}

function search(obj)
{
    pesquisaSideBar = true;
    showVoluntarioInRelogio(obj);

}

function showVoluntarioInRelogio(obj)
{
    var regExp1 = /^[a-zA-Z\._-\s]+,[0-9]+$/;
    var regExp2 = /^[a-zA-Z\._-\s]+$/;

    document.getElementById("error").innerHTML = '';


    if (regExp1.test(removeDiacritics(obj)))
    {


        var aux = obj.split(",");

        var inputTextVolName = document.getElementById("voluntarioname");
        inputTextVolName.value = aux[0];

        voluntarioID = aux[1];

    }

    if (regExp2.test(removeDiacritics(obj)))
    {
        /*
         * Funcionalidade para pesquisar voluntario no relogio ponto
         */

    }

    /*
     * Mostrar os Materiais Disponiveis na BD chamar webservice
     */

    callWebServiceMateriais();

}
/*
 * Permite atualizar a resposta 
 * @param {JSON} data AJAX response to GET materiais request
 * @returns {undefined}
 */
function updateMateriais(data)
{
    materiais = data;
}

/*
 * Obter os Materias disponiveis para ceder aos voluntarios
 * @param {type} obj
 * @returns {undefined}
 */
function callWebServiceMateriais()
{
    $.ajax({
        url: path + 'persistence.entities.material',
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            showMateriais(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });

}
/*
 * Permite mostrar os materias disponiveis
 * @param {JSON} List<Material>
 * @returns {html material disponivel}
 */
function showMateriais(obj)
{

    var isInCamp = isInCampanha(voluntarioID);

    if (isInCamp === 0)
    {
        var materiaisDisp = '<div class="col-md-6">';
        var tableResponsive = '<div class="sidebar-show">' +
                '<div class="box-header">' +
                '<h3 class="box-title">Materiais</h3>' +
                '<div class="box-tools">' +
                '<div class="input-group">' +
                '</div>' +
                '</div>' +
                '</div><!-- /.box-header -->';
        var initTable = '<div class="box-body table-responsive">' +
                '<table class="table table-hover">' +
                '<tbody id="materialdisponivel">';

        var rows = '';
        var finaliseTable = '</tbody>' +
                '</table>' +
                '</div><!-- /.box-body -->' +
                '</div>' +
                '</div><!-- /.center -->';
        /*
         * Percorre o JSON e cria as linhas da tabela
         */
        for (var i = 0; i < obj.length; i++)
        {
            var row = '<tr id="tr' + obj[i].materialID + '">' +
                    '<td> <button onclick="showMaterialInMateriaisCedidos(\'' + obj[i].materialID + ',' + obj[i].descricao + '\')" type="button" class="btn btn-success btn-circle"><i class="fa fa-check"></i>' +
                    '</button></td>' +
                    '<td>' + obj[i].descricao + '</td>' +
                    '</tr>';
            rows += row;
        }
        /*
         * criar a tabela Materiais a disponibilizar
         */
        materiaisDisp += tableResponsive + initTable + rows + finaliseTable;

        /*
         * Inicializa a tabela de materiais cedidos
         */
        var tableRespMatCed = '<div class="col-md-6">' +
                '<div class="sidebar-show">' +
                '<div class="box-header">' +
                '<h3 class="box-title">Materiais Cedidos</h3>' +
                '<div class="box-tools">' +
                '<div class="input-group">' +
                '</div>' +
                '</div>' +
                '</div><!-- /.box-header -->' +
                '<div class="box-body table-responsive">' +
                '<table class="table table-hover">' +
                '<tbody id="materialcedido">' +
                '</tbody>' +
                '</table>' +
                '</div><!-- /.box-body -->' +
                '</div>' +
                '</div>';
        /*
         * alterar a div com id="materiais"
         */
        document.getElementById("materiais").innerHTML = materiaisDisp + tableRespMatCed;
    }
    if (isInCamp === 1)
    {
        /*
         * 
         * @type @exp;JSON@call;stringify
         * Obter os materiais do voluntario 
         */
        CallWebServiceVoluntarioRelogioPonto(voluntarioID);
        callWebServiceFindMaterialsVoluntario(voluntarioCampanha.relogioPontoID);


        var materiaisCedidos = '<div class="col-md-6">';
        var tableResponsive = '<div class="sidebar-show">' +
                '<div class="box-header">' +
                '<h3 class="box-title">Materiais</h3>' +
                '<div class="box-tools">' +
                '<div class="input-group">' +
                '</div>' +
                '</div>' +
                '</div><!-- /.box-header -->';
        var initTable = '<div class="box-body table-responsive">' +
                '<table class="table table-hover">' +
                '<tbody id="materialdisponivel">';

        var rows = '';
        var finaliseTable = '</tbody>' +
                '</table>' +
                '</div><!-- /.box-body -->' +
                '</div>' +
                '</div><!-- /.center -->';
        /*
         * Percorre o JSON e cria as linhas da tabela
         */

        for (var i = 0; i < materials.length; i++)
        {
            var row = '<tr id="tr' + materials[i].material.materialID + '">' +
                    '<td> <button onclick="showMaterialInMateriaisCedidos(\'' + materials[i].material.materialID + ',' + materials[i].material.descricao + '\')" type="button" class="btn btn-success btn-circle"><i class="fa fa-check"></i>' +
                    '</button></td>' +
                    '<td>' + materials[i].material.descricao + '</td>' +
                    '</tr>';
            rows += row;
        }
        /*
         * criar a tabela Materiais Cedidos e Materiais a devolver
         */
        materiaisCedidos += tableResponsive + initTable + rows + finaliseTable;

        /*
         * Inicializa a tabela de materiais cedidos
         */
        var tableRespMatCed = '<div class="col-md-6">' +
                '<div class="sidebar-show">' +
                '<div class="box-header">' +
                '<h3 class="box-title">Materiais Devolvidos</h3>' +
                '<div class="box-tools">' +
                '<div class="input-group">' +
                '</div>' +
                '</div>' +
                '</div><!-- /.box-header -->' +
                '<div class="box-body table-responsive">' +
                '<table class="table table-hover">' +
                '<tbody id="materialcedido">' +
                '</tbody>' +
                '</table>' +
                '</div><!-- /.box-body -->' +
                '</div>' +
                '</div>';
        /*
         * alterar a div com id="materiais"
         */
        document.getElementById("materiais").innerHTML = materiaisCedidos + tableRespMatCed;
    }

    if (isInCamp === 2)
    {
        var materiaisDisp = '<div class="col-md-6">';
        var tableResponsive = '<div class="sidebar-show">' +
                '<div class="box-header">' +
                '<h3 class="box-title">Materiais</h3>' +
                '<div class="box-tools">' +
                '<div class="input-group">' +
                '</div>' +
                '</div>' +
                '</div><!-- /.box-header -->';
        var initTable = '<div class="box-body table-responsive">' +
                '<table class="table table-hover">' +
                '<tbody id="materialdisponivel">';

        var rows = '';
        var finaliseTable = '</tbody>' +
                '</table>' +
                '</div><!-- /.box-body -->' +
                '</div>' +
                '</div><!-- /.center -->';
        /*
         * Percorre o JSON e cria as linhas da tabela
         */
        for (var i = 0; i < obj.length; i++)
        {
            var row = '<tr id="tr' + obj[i].materialID + '">' +
                    '<td> <button onclick="showMaterialInMateriaisCedidos(\'' + obj[i].materialID + ',' + obj[i].descricao + '\')" type="button" class="btn btn-success btn-circle"><i class="fa fa-check"></i>' +
                    '</button></td>' +
                    '<td>' + obj[i].descricao + '</td>' +
                    '</tr>';
            rows += row;
        }
        /*
         * criar a tabela Materiais a disponibilizar
         */
        materiaisDisp += tableResponsive + initTable + rows + finaliseTable;

        /*
         * Inicializa a tabela de materiais cedidos
         */
        var tableRespMatCed = '<div class="col-md-6">' +
                '<div class="sidebar-show">' +
                '<div class="box-header">' +
                '<h3 class="box-title">Materiais Cedidos</h3>' +
                '<div class="box-tools">' +
                '<div class="input-group">' +
                '</div>' +
                '</div>' +
                '</div><!-- /.box-header -->' +
                '<div class="box-body table-responsive">' +
                '<table class="table table-hover">' +
                '<tbody id="materialcedido">' +
                '</tbody>' +
                '</table>' +
                '</div><!-- /.box-body -->' +
                '</div>' +
                '</div>';
        /*
         * alterar a div com id="materiais"
         */
        document.getElementById("materiais").innerHTML = materiaisDisp + tableRespMatCed;
    }


    return true;

}

function callWebServiceFindMaterialsVoluntario(relogiopontoid)
{
    $.ajax({
        url: path + 'persistence.entities.voluntariomaterial/relogioponto/' + relogiopontoid,
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        async: false,
        timeout: 2000,
        success: function(data) {
            updateMaterials(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}

function updateMaterials(data)
{
    materials = data;
}

/*
 * Permite colocar o material selecionado
 * na lista de materiais cedidos
 * @param {materialID} obj
 * @returns {void}
 */
function showMaterialInMateriaisCedidos(materialID)
{
    /*
     * Procurar tr do material selecionado tr+materialID (na tabela Materiais)
     * e eliminar
     * atualizar a lista de materiais cedidos
     * procurar id material cedido 
     * fazer innerHtml
     */

    var aux = materialID.split(",");
    /*
     * 
     * remove row na tabela materiais
     */
    //document.getElementById("tr" + aux[0]).innerHTML = '';
    var rowChildElement = document.getElementById("tr" + aux[0]);
    var rowParentElement = document.getElementById("materialdisponivel");

    rowParentElement.removeChild(rowChildElement);

    /*
     * 
     * acrescenta row na tabela Materiais Cedidos
     */
    var materiaisCedidos = '<tr id="trC' + aux[0] + '">' +
            '<td> <button  onclick="removeMaterialCedido(\'' + materialID + '\');" type="button" class="btn btn-danger btn-circle"><i class="fa fa-times"></i>' +
            '</button></td>' +
            '<td>' + aux[1] + '</td>' +
            '</tr>';
    var vals = document.getElementById("materialcedido").innerHTML;
    vals += materiaisCedidos;
    document.getElementById("materialcedido").innerHTML = vals;
}

function removeMaterialCedido(materialID)
{
    /*
     * 
     * Procurar tr do material selecionado tr+materialID (na tabela Materiais)
     * e eliminar
     * atualizar a lista de materiais cedidos
     * procurar id material cedido 
     * fazer innerHtml
     *
     */

    var aux = materialID.split(",");
    /*
     * 
     * remove row na tabela materiais
     */
    var rowChildElement = document.getElementById("trC" + aux[0]);
    var rowParentElement = document.getElementById("materialcedido");

    rowParentElement.removeChild(rowChildElement);

    /*
     * 
     * acrescenta row na tabela Materiais Cedidos
     */
    var materiaisDisp = '<tr id="tr' + aux[0] + '">' +
            '<td> <button onclick="showMaterialInMateriaisCedidos(\'' + materialID + '\')" type="button" class="btn btn-success btn-circle"><i class="fa fa-check"></i>' +
            '</button></td>' +
            '<td>' + aux[1] + '</td>' +
            '</tr>';
    var vals = document.getElementById("materialdisponivel").innerHTML;
    vals += materiaisDisp;
    document.getElementById("materialdisponivel").innerHTML = vals;


}


/*
 * @returns {void}
 * Permite registar saida e entrada voluntario
 */
function registoVoluntarioInRelogioPonto()
{
    var name = document.getElementById("voluntarioname");
    var regExprName = /^[a-zA-Z\s]+$/;

    if (pesquisaSideBar === true || pesquisaRelPonto === true) {

        if (!isBlank(name.value) && !isEmpty(name.value) && regExprName.test(name.value))
        {
            isInCamp = isInCampanha(voluntarioID);

            if (isInCamp === 2)
            {
                /*
                 * Registar de novo
                 */

                /*
                 * 
                 * @param {voluntarioID} Voluntario
                 * VoluntarioID selecionado
                 * Criar Registo em Relogioponto (chamar Webservice)
                 * @returns {void}
                 */
                callWebServiceRegistarEntradaVoluntario(voluntarioID);

                /*
                 * Registar material cedido
                 */

                /*
                 * Registar o material cedido
                 */
                // Obter os materiais
                var rows = document.getElementById("materialcedido");

                for (var i = 0; i < rows.childElementCount; i++)
                {
                    /*
                     * val ID de cada tr = trC+ID
                     */
                    var matID = rows.childNodes[i].id;

                    var aux = matID.split("C");
                    callWebServiceRegisterMaterialCedido(aux[1], generatedRelPontID);
                }

                /*
                 * Procurar span pelo id (VoluntarioID)
                 * Alterar class
                 * label label-warning
                 */

                document.getElementById("voluntarioname").value = '';
                if (pesquisaRelPonto === false)
                {
                    document.getElementById(voluntarioID).className = "label label-warning";
                    document.getElementById(voluntarioID).innerHTML = "em campanha";
                }
                document.getElementById("materiais").innerHTML = '';
                document.getElementById("error").innerHTML = '';

                updateTables();
                pesquisaRelPonto = false;
                pesquisaSideBar = false;
            }




            if (isInCamp === 1)
            {
                var res = validateMateriaisDevolvidos();
                /*
                 * verificar se todos os materiais foram devolvidos
                 * 
                 */
                if (res === true)
                {
                    /*
                     * Obter os materiais cedidos
                     */

                    // Obter os materiais
                    var rows = document.getElementById("materialcedido");

                    for (var i = 0; i < rows.childElementCount; i++)
                    {
                        /*
                         * val ID de cada tr = trC+ID
                         */
                        var matID = rows.childNodes[i].id;

                        var aux = matID.split("C");
                        callWebServiceUpdateMaterialsDevolvidos(aux[1], voluntarioCampanha.relogioPontoID);
                    }


                    /*
                     * @param {voluntarioID} Voluntario
                     * Voluntario Esta no Relogioponto (chamar Webservice)
                     * Permite registar Saida
                     * Set Tipo Registo to true
                     * Regista Saida
                     * @returns {void}
                     */

                    callWebServiceRegistarSaidaVoluntario(voluntarioID);

                    /*
                     * limpar os campos do input text voluntario
                     * Procurar span pelo id (VoluntarioID)
                     * Alterar class
                     * label label-success
                     */
                    document.getElementById("voluntarioname").value = '';
                    if (pesquisaRelPonto === false)
                    {
                        document.getElementById(voluntarioID).className = "label label-success";
                        document.getElementById(voluntarioID).innerHTML = "participou";
                    }
                    document.getElementById("materiais").innerHTML = '';
                    document.getElementById("error").innerHTML = '';
                    updateTables();

                    pesquisaRelPonto = false;
                    pesquisaSideBar = false;

                }
                else
                {
                    document.getElementById("error").innerHTML = "N&atilde;o foram devolvidos todos os materiais";
                }
            }
            if (isInCamp === 0) {
                /*
                 * 
                 * @param {voluntarioID} Voluntario
                 * VoluntarioID selecionado
                 * Criar Registo em Relogioponto (chamar Webservice)
                 * @returns {void}
                 */
                callWebServiceRegistarEntradaVoluntario(voluntarioID);


                /*
                 * 
                 * Registar material cedido
                 */


                // Obter os materiais
                var rows = document.getElementById("materialcedido");

                for (var i = 0; i < rows.childElementCount; i++)
                {
                    /*
                     * 
                     * val ID de cada tr = trC+ID
                     */
                    var matID = rows.childNodes[i].id;

                    var aux = matID.split("C");
                    callWebServiceRegisterMaterialCedido(aux[1], generatedRelPontID);
                }

                /*
                 * Procurar span pelo id (VoluntarioID)
                 * Alterar class
                 * label label-warning
                 */


                document.getElementById("voluntarioname").value = '';
                if (pesquisaRelPonto === false)
                {
                    document.getElementById(voluntarioID).className = "label label-warning";
                    document.getElementById(voluntarioID).innerHTML = "em campanha";
                }
                document.getElementById("materiais").innerHTML = '';
                updateTables();

                pesquisaRelPonto = false;
                pesquisaSideBar = false;
            }
        }
        else {
            document.getElementById("error").innerHTML = "Nenhum volunt&aacute;rio foi selecionado ";

        }
    } else {
        pesquisaRelPonto = true;

        if (!isBlank(name.value) && !isEmpty(name.value))
        {
            findVoluntario(name.value);
        } else {
            document.getElementById("error").innerHTML = "Nenhum volunt&aacute;rio foi selecionado ";
        }
    }
}


function validateMateriaisDevolvidos()
{
    var rows = document.getElementById("materialdisponivel");

    if (rows.childElementCount > 0) {
        return false;
    }

    return true;
}

/*
 * Registar os materiais devolvidos
 * @param {type} relogiopontoid
 * @returns {undefined}
 */
function callWebServiceUpdateMaterialsDevolvidos(materialid, relogiopontoid)
{
    $.ajax({
        url: path + 'persistence.entities.voluntariomaterial/Material/' + relogiopontoid + '&' + materialid,
        type: 'PUT',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {

        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}

function callWebServiceRegisterMaterialCedido(materialid, relogiopontoid)
{
    $.ajax({
        url: path + 'persistence.entities.voluntariomaterial/' + materialid + "&" + relogiopontoid,
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            updateGeneratedRelPontID(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}

/*
 * Registar a entrada de voluntarios na Campanha
 * @param {VoluntarioID} Voluntario
 * @returns {void}
 */
function callWebServiceRegistarEntradaVoluntario(obj) {

    $.ajax({
        url: path + 'persistence.entities.relogioponto/RegistarEntradaVoluntario/' + obj,
        type: 'POST',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        async: false,
        timeout: 1000,
        success: function(data) {
            updateGeneratedRelPontID(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}

function updateGeneratedRelPontID(data)
{
    generatedRelPontID = data;
}

/*
 * Registar a saida de voluntarios na Campanha
 * @param {VoluntarioID} Voluntario
 * @returns {void}
 */
function callWebServiceRegistarSaidaVoluntario(obj) {

    $.ajax({
        url: path + 'persistence.entities.relogioponto/RegistarSaidaVoluntario/' + obj,
        type: 'PUT',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}



/*
 * Permite saber se o Voluntario esta na Campanha
 * @param {VoluntarioID} obj
 * @returns {Relogioponto} Relogioponto em Campanha (ainda nao foi registada a saida)
 * 
 */
function CallWebServiceVoluntarioRelogioPonto(obj) {

    $.ajax({
        url: path + 'persistence.entities.relogioponto/VoluntarioCampanha/' + obj,
        type: 'GET',
        dataType: 'JSON',
        timeout: 3000,
        contentType: 'application/json; charset=utf-8',
        async: false,
        success: function(data) {
            updateVoluntarioCampanha(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });


}

function updateVoluntarioCampanha(obj)
{
    voluntarioCampanha = obj;
}

function stateHandlerVoluntarioByEmail()
{

    if (xmlHttpObj.readyState === 4 && xmlHttpObj.status === 200)
    {
        // recebe resposta
        var voluntario = xmlHttpObj.responseXML;
        showVoluntarioInRelPont(voluntario);
    }
}

function stateHandlerVoluntarioByName()
{

    if (xmlHttpObj.readyState === 4 && xmlHttpObj.status === 200)
    {
        // recebe resposta
        var voluntario = xmlHttpObj.responseXML;
        createMailBox(voluntario);
    }
}

function stateHandlerVoluntarioByBI()
{

    if (xmlHttpObj.readyState === 4 && xmlHttpObj.status === 200)
    {
        // recebe resposta
        var voluntario = xmlHttpObj.responseXML;
        createMailBox(voluntario);
    }
}


function showDisp(obj) {
    var data = null;
    alert(obj);
    $.ajax({
        url: path + 'persistence.entities.disponibilidadecampanhas/Voluntario/' + obj,
        type: 'GET',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        success: function(data) {
            fillRadios(data);
        },
        error: function(err) {
            alert(JSON.stringify(err));
        }
    });
}


function changeCheckedRadioButton(obj)
{

//    <label class="">
//<div class="iradio_minimal" aria-checked="false" aria-disabled="false" style="position: relative;">
//<input type="radio" name="optionsSeg" id="optionsRadiosSeg2" value="2" style="position: absolute; opacity: 0;">
//<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">
//</ins>
//</div> Noite
//</label>
}



function fillRadios(data)
{
    selectedRadioButtons[0] = data.segunda;
    selectedRadioButtons[1] = data.terca;
    selectedRadioButtons[2] = data.quarta;
    selectedRadioButtons[3] = data.quinta;
    selectedRadioButtons[4] = data.sexta;
    selectedRadioButtons[5] = data.sabado;
    selectedRadioButtons[6] = data.domingo;



//    document.getElementById("optionsRadiosSeg" + data.segunda).attributes.create("checked=true");

    document.getElementById("divoptionsRadiosSeg" + data.segunda).innerHTML =
            '<label>' +
            //'<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
            '<input type="radio" name="optionsSeg" id="optionsRadiosSeg' + data.segunda + '" value="' + data.segunda + '" checked="checked">' +
            //'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
            //'</ins>' +
            dispOptions[data.segunda] +
            '<i class="fa fa-circle-o"></i></input>' +
            //'</div>' + 
            '</label>';
//    document.getElementById("divoptionsRadiosTer" + data.terca).innerHTML =
//            '<label class="">' +
//            '<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
//            '<input type="radio" name="optionsTer" id="optionsRadiosTer'+data.terca+'" value="'+data.terca+'" style="position: absolute; opacity: 0;">' +
//            '<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
//            '</ins>' +
//            '</div>' + dispOptions[data.terca] +
//            '</label>';
//    document.getElementById("divoptionsRadiosQuar" + data.quarta).innerHTML =
//            '<label class="">' +
//            '<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
//            '<input type="radio" name="optionsQuar" id="optionsRadiosQuar'+data.quarta+'" value="'+data.quarta+'" style="position: absolute; opacity: 0;">' +
//            '<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
//            '</ins>' +
//            '</div>' + dispOptions[data.quarta] +
//            '</label>';
//    document.getElementById("divoptionsRadiosQuin" + data.quinta).innerHTML =
//            '<label class="">' +
//            '<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
//            '<input type="radio" name="optionsQuin" id="optionsRadiosQuin'+data.quinta+'" value="'+data.quinta+'" style="position: absolute; opacity: 0;">' +
//            '<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
//            '</ins>' +
//            '</div>' + dispOptions[data.quinta] +
//            '</label>';
//    document.getElementById("divoptionsRadiosSex" + data.sexta).innerHTML =
//            '<label class="">' +
//            '<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
//            '<input type="radio" name="optionsSex" id="optionsRadiosSex'+data.sexta+'" value="'+data.sexta+'" style="position: absolute; opacity: 0;">' +
//            '<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
//            '</ins>' +
//            '</div>' + dispOptions[data.sexta] +
//            '</label>';
//    document.getElementById("divoptionsRadiosSab" + data.sabado).innerHTML =
//            '<label class="">' +
//            '<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
//            '<input type="radio" name="optionsSab" id="optionsRadiosSab'+data.sabado+'" value="'+data.sabado+'" style="position: absolute; opacity: 0;">' +
//            '<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
//            '</ins>' +
//            '</div>' + dispOptions[data.sabado] +
//            '</label>';
//    
//    document.getElementById("divoptionsRadiosDom" + data.domingo).innerHTML =
//            '<label class="">' +
//            '<div class="iradio_minimal checked" aria-checked="true" aria-disabled="false" style="position: relative;">' +
//            '<input type="radio" name="optionsDom" id="optionsRadiosDom'+data.domingo+'" value="'+data.domingo+'" style="position: absolute; opacity: 0;">' +
//            '<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);">' +
//            '</ins>' +
//            '</div>' + dispOptions[data.domingo] +
//            '</label>';
}

function wichDisp(obj)
{

    var element = document.getElementsByName("optionsSeg");
    var element1 = document.getElementsByName("optionsTer");
    var element2 = document.getElementsByName("optionsQuar");
    var element3 = document.getElementsByName("optionsQuin");
    var element4 = document.getElementsByName("optionsSex");
    var element5 = document.getElementsByName("optionsSab");
    var element6 = document.getElementsByName("optionsDom");

    for (var i = 0; i < element.length; i++)
    {
        if (element[i].checked)
        {
            disp = element[i].value;
        }
    }
    for (var i = 0; i < element1.length; i++)
    {
        if (element1[i].checked)
        {
            disp1 = element1[i].value;
        }
    }
    for (var i = 0; i < element2.length; i++)
    {
        if (element2[i].checked)
        {
            disp2 = element2[i].value;
        }
    }
    for (var i = 0; i < element3.length; i++)
    {
        if (element3[i].checked)
        {
            disp3 = element3[i].value;
        }
    }
    for (var i = 0; i < element4.length; i++)
    {
        if (element4[i].checked)
        {
            disp4 = element4[i].value;
        }
    }
    for (var i = 0; i < element5.length; i++)
    {
        if (element5[i].checked)
        {
            disp5 = element5[i].value;
        }
    }
    for (var i = 0; i < element6.length; i++)
    {
        if (element6[i].checked)
        {
            disp6 = element6[i].value;
        }
    }

    createDisp(obj, disp, disp1, disp2, disp3, disp4, disp5, disp6);
}


function createDisp(userid, obj, obj1, obj2, obj3, obj4, obj5, obj6) {

    var disp = {};
    disp.voluntarioVoluntarioID = 0;
    disp.disponibilidadeCampanhasID = 0;
    disp.segunda = parseInt(obj);
    disp.terca = parseInt(obj1);
    disp.quarta = parseInt(obj2);
    disp.quinta = parseInt(obj3);
    disp.sexta = parseInt(obj4);
    disp.sabado = parseInt(obj5);
    disp.domingo = parseInt(obj6);
    //alert("Domingo="+parseInt(obj6));

    $.ajax({
        url: path + 'persistence.entities.disponibilidadecampanhas/Voluntario/' + userid,
        type: 'PUT',
        dataType: 'JSON',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(disp),
        error: function(err) {
            alert("ERRO!");
        }
    });

}

function CallWebServiceCreateCampanha()
{
//    xmlHttpObj = CreateXmlHttpRequestObject();
//    var maxresult = document.getElementById("maxtags");
//    // efectuar pedido Ajax ( open, registar eventHandler, send , etc)                 
//    if (xmlHttpObj) {
//        xmlHttpObj.open("GET", path + "persistence.entities.email/UtilizadorDestinatario/" + obj, true);
//        xmlHttpObj.onreadystatechange = stateHandlerEmail;
//        xmlHttpObj.send(null);
//    }
}



function CallWebServiceGetEmails(obj)
{
    var regexpr = /^[0-9]+$/;
    if ((regexpr.test(obj))) {
        xmlHttpObj = CreateXmlHttpRequestObject();
        var maxresult = document.getElementById("maxtags");
        // efectuar pedido Ajax ( open, registar eventHandler, send , etc)                 
        if (xmlHttpObj) {
            xmlHttpObj.open("GET", path + "persistence.entities.email/UtilizadorDestinatario/" + obj, true);
            xmlHttpObj.onreadystatechange = stateHandlerEmail;
            xmlHttpObj.send(null);
        }
    }

}

function stateHandlerEmail()
{

    if (xmlHttpObj.readyState === 4 && xmlHttpObj.status === 200)
    {
        // recebe resposta
        var emails = xmlHttpObj.responseXML;
        createMailBox(emails);
    }
}

function CallWebServiceDeleteEmail(obj)
{

//    xmlHttpObj = CreateXmlHttpRequestObject();
//    var maxresult = document.getElementById("maxtags");
//    // efectuar pedido Ajax ( open, registar eventHandler, send , etc)                 
//    if (xmlHttpObj) {
//        xmlHttpObj.open("DELETE", path + "persistence.entities.email/UtilizadorDestinatario/" + obj, true);
//        xmlHttpObj.onreadystatechange = stateHandlerEmail;
//        xmlHttpObj.send(null);
//    }
}


function changeMailBox(obj1, obj2)
{
    document.getElementById(indexMailOptActive).className = "";
    document.getElementById(obj1).className = "active";

    indexMailOptActive = obj1;

    if (mailOptions[obj1] === "Inbox")
        CallWebServiceGetEmails(obj2);
}


function createMailBox(obj)
{
    var emails = obj.childNodes[0];
    var i = 0;


    var mailBox = "<div class=\"panel-group\" id=\"accordion\">";
    var collapseNumber = 0;

    for (var i = 0; i < emails.childNodes.length; i++)
    {
        collapseNumber++;
//        alert(emails.childNodes[i].nodeName);
        var corpo = emails.childNodes[i].getElementsByTagName("corpo")[0].childNodes[0].nodeValue;

        var dataEnvio = emails.childNodes[i].getElementsByTagName("dataEnvio")[0].childNodes[0].nodeValue;
        var lead = emails.childNodes[i].getElementsByTagName("lead")[0].childNodes[0].nodeValue;
        var emailID = emails.childNodes[i].getElementsByTagName("emailID")[0].childNodes[0].nodeValue;
        var util = emails.childNodes[i].getElementsByTagName("utilizadorRemetenteID")[0].childNodes;

        var remetenteEmail = util[0].childNodes.item(0).nodeValue;
        var remetente = util[3].childNodes.item(0).nodeValue;

//        alert(remetenteEmail);

        mailBox += emailBox("collapse" + collapseNumber, lead, corpo, remetente, remetenteEmail, dataEnvio, emailID);
    }

    mailBox += "</div>";

    document.getElementById("accordion").innerHTML = mailBox;

}

function emailBox(collapseNumber, leadEmail, corpoEmail, emailFrom, contactEmail, emailDate, emailID)
{
    var email = "<!-- Default box -->" +
            "<div class=\"panel box-info\">" +
            "<div class=\"panel-heading\">" +
            "<span class=\"panel-title\">" +
            "<div class=\"pull-right\">" +
            "<a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#" + collapseNumber + "\">" + //#collapseOne
            "<i class=\"fa fa-minus\"> </i>" +
            "</a>" +
            "</div>" +
            "<input type=\"checkbox\" /> " + leadEmail +
            "</span>" +
            "</div>" +
            "<div id=\"" + collapseNumber + "\" class=\"panel-collapse collapse in\" >" +
            "<div class=\"panel-body\" >" +
            "<h5> " + emailFrom + "[" + contactEmail + "]" + " </h5>" + "<hr></hr>" +
            corpoEmail +
            "<hr> </hr>" +
            "<small class=\"label label-danger\" > <i class=\"fa fa-clock-o\"> </i> " + emailDate +
            "</small>" +
            "<div class=\"pull-right\" >" +
            "<div class=\"tools\">" +
            "<a class=\"\" data-toggle=\"modal\" data-target=\"#compose-modal\" > <i class=\"fa fa-edit\" > </i></a> " +
            "<a class=\"\" onclick=\"CallWebServiceDeleteEmail(" + emailID + ");\" > <i class=\"fa fa-trash-o\"> </i></a>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div><!-- /.box -- >";

    return email;
}

function validateVolName(obj)
{
    var regexpr = /^[A-Za-z\s]+$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
        document.getElementById("divvolname").className = "form-group has-error";
        document.getElementById("labelvolname").innerHTML = "<label id=\"labelvolname\" class=\"control-label\" for=\"inputError\">Nome</label>";
        obj.focus();
        return false;
    } else
    {
        document.getElementById("divvolname").className = "form-group has-success";
        document.getElementById("labelvolname").innerHTML = "<label id=\"labelvolname\" class=\"control-label\" for=\"inputSuccess\">Nome</label>";
        return true;
    }

}

function validateSexo(obj)
{
    var regexpr = /^[A-Za-z]+$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
        document.getElementById("divsexo").className = "form-group has-error";
        document.getElementById("labelsexo").innerHTML = "<label id=\"labelsexo\" class=\"control-label\" for=\"inputError\">Sexo</label>";   
        return false;
    } else
    {
        document.getElementById("divsexo").className = "form-group has-success";
        document.getElementById("labelsexo").innerHTML = "<label id=\"labelsexo\" class=\"control-label\" for=\"inputSuccess\">Sexo</label>";
        return true;
    }
}

function validateString(obj, obj1,obj2)
{
    var regexpr = /^([A-Za-z\s])+$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
        document.getElementById("div" + obj1).className = "form-group has-error";
        document.getElementById("label" + obj1).innerHTML = "<label id=\"label" + obj1 + "\" class=\"control-label\" for=\"inputError\">" + obj2 + "</label>";
        obj.value = '';
        
        return false;
    } else
    {
        document.getElementById("div" + obj1).className = "form-group has-success";
        document.getElementById("label" + obj1).innerHTML = "<label id=\"label" + obj1 + "\" class=\"control-label\" for=\"inputSuccess\">" + obj2 + "</label>";
        return true;
    }
}

function validateTel(obj, obj1)
{
    var regexpr = /^([0-9])+$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
        document.getElementById("div" + obj1).className = "form-group has-error";
        document.getElementById("label" + obj1).innerHTML = "<label id=\"label" + obj1 + "\" class=\"control-label\" for=\"inputError\">Telefone</label>";
        obj.value = '';
        
        return false;
    } else
    {
        document.getElementById("div" + obj1).className = "form-group has-success";
        document.getElementById("label" + obj1).innerHTML = "<label id=\"label" + obj1 + "\" class=\"control-label\" for=\"inputSuccess\">Telefone</label>";
        return true;
    }
}

function validateCodPostal(obj){
    
    var regexpr = /^\d{4}(-\d{3})?$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
        document.getElementById("divcodpostal").className = "form-group has-error";
        document.getElementById("labelcodpostal").innerHTML = "<label id=\"labelpostal\" class=\"control-label\" for=\"inputError\">C&ccedil;digo-Postal</label>";
        obj.value = '';
        
        return false;
    } else
    {
        document.getElementById("divcodpostal").className = "form-group has-success";
        document.getElementById("labelcodpostal").innerHTML = "<label id=\"labelcodpostal\" class=\"control-label\" for=\"inputSuccess\">C&ccedil;digo-Postal</label>";
        return true;
    }
}
function validateBI(obj)
{
    var regexpr = /^([0-9]{8})$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
        document.getElementById("divbi").className = "form-group has-error";
        document.getElementById("labelbi").innerHTML = "<label id=\"labelbi\" class=\"control-label\" for=\"inputError\">BI</label>";
        obj.value = '';
       
        return false;
    } else
    {
        document.getElementById("divbi").className = "form-group has-success";
        document.getElementById("labelbi").innerHTML = "<label id=\"labelebi\" class=\"control-label\" for=\"inputSuccess\">BI</label>";
        return true;
    }
}


function validateName(obj)
{
//    alert();
    var regexpr = /^[A-Za-z\s]+$/;
    if (!(regexpr.test(obj.value)))
    {

//        var divError = "<div id=\"divname\" class=\"form-group has-error\"> " +
//                "<label class=\"control-label\" for=\"inputError\">Name is invalid!</label>" +
//                "<h:inputText p:type=\"text\" class=\"form-control\" id=\"inputError\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validateName(this)\"" +
//                "onkeydown=\"if (event.keyCode == '9') validateName(this)\" h:autofocus=\"\" />" +
//                "</div>";
        obj.value = '';
        document.getElementById("divname").className = "form-group has-error";
        document.getElementById("labelname").innerHTML = "<label id=\"labelname\" class=\"control-label\" for=\"inputError\">Name is invalid!</label>";
        obj.focus();
//        alert("return false");
        return false;
    } else
    {
//        var divSuccess = "<div id=\"divname\" class=\"form-group has-success\"> " +
//                "<label class=\"control-label\" for=\"inputSuccess\">Name is valid</label>" +
//                "<h:inputText p:type=\"text\" value=\"" + obj.value + "\" class=\"form-control\" id=\"inputSuccess\"" +
//                "onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validateName(this)\"" +
//                "onkeydown=\"if ( event.keyCode == '9') validateName(this)\" />" +
//                "</div>";


        document.getElementById("divname").className = "form-group has-success";
        document.getElementById("labelname").innerHTML = "<label id=\"labelname\" class=\"control-label\" for=\"inputSuccess\">Name is valid</label>";
        email.focus();
//        alert("return true");
        return true;
    }

}

function validateEmail(obj)
{
    var regexpr = /^[a-zA-Z0-9\._-]+@[a-zA-Z\-]+.([a-zA-Z]{2,3})$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
//        var divError = "<div id=\"divemail\" class=\"form-group has-error\"> " +
//                "<label class=\"control-label\" for=\"inputError\">Email is invalid!</label>" +
//                "<input type=\"text\" class=\"form-control\" id=\"inputError\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validateEmail(this)\"" +
//                "onkeydown=\"if (event.keyCode == '9') validateEmail(this)\" autofocus>" +
//                "</div>";
        document.getElementById("divemail").className = "form-group has-error";
        document.getElementById("labelemail").innerHTML = "<label id=\"labelemail\" class=\"control-label\" for=\"inputError\">Email is invalid!</label>";
        obj.value = '';
        obj.focus();
//        alert("return false");
        return false;
    } else
    {
        document.getElementById("divemail").className = "form-group has-success";
        document.getElementById("labelemail").innerHTML = "<label id=\"labelemail\" class=\"control-label\" for=\"inputSuccess\">Email is valid</label>";
//        alert(password);
        password.focus();
//        alert("return true");
        return true;
    }
}

function validateSignIn(obj)
{
    var regexpr = /^[a-zA-Z0-9\._-]+@[a-zA-Z\-]+.([a-zA-Z]{2,3})$/;
    if (!(regexpr.test(obj.value)))
    {
        obj.value = '';
//        var divError = "<div id=\"divemail\" class=\"form-group has-error\"> " +
//                "<label class=\"control-label\" for=\"inputError\">Email is invalid!</label>" +
//                "<input type=\"text\" class=\"form-control\" id=\"inputError\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validateEmail(this)\"" +
//                "onkeydown=\"if (event.keyCode == '9') validateEmail(this)\" autofocus>" +
//                "</div>";
        document.getElementById("divemail").className = "form-group has-error";
        document.getElementById("labelemail").innerHTML = "<label id=\"labelemail\" class=\"control-label\" for=\"inputError\">Email is invalid!</label>";
        obj.value = '';
        obj.focus();
//        alert("return false");
        return false;
    } else
    {
        document.getElementById("divemail").className = "form-group";
        document.getElementById("labelemail").innerHTML = "<label id=\"labelemail\" class=\"control-label\" for=\"inputSuccess\">Email</label>";
//        alert(password);
        password.focus();
//        alert("return true");
        return true;
    }
}


function validatePassword(obj)
{
    var regexpr = /^([a-zA-Z0-9]{6,15})$/;
    if (!(regexpr.test(obj.value)))
    {
//        var divError = "<div id=\"divpassword\" class=\"form-group has-warning\"> " +
//                "<label class=\"control-label\" for=\"inputWarning\">Password is weak!</label>" +
//                "<input id=\"inputpassword\" type=\"password\" placeholder=\"Password\" class=\"form-control\" id=\"inputWarning\"" +
//                "onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validatePassword(this)\"" +
//                "onkeydown=\"if (event.keyCode == '9') validatePassword(this)\" value=\"" + obj.value + "\" autofocus>" +
//                "</div>";
        document.getElementById("divpassword").className = "form-group has-warning";
        document.getElementById("labelpassword").innerHTML = "<label id=\"labelpassword\" class=\"control-label\" for=\"inputWarning\">Password is weak! Put a password with at least 6 characters</label>";
//        alert("return false");
        return false;
    } else
    {
//        var divSuccess = "<div id=\"divpassword\" class=\"form-group has-success\"> " +
//                "<label class=\"control-label\" for=\"inputSuccess\">Password is strong</label>" +
//                "<input id=\"inputpassword\" type=\"password\" placeholder=\"Password\" value=\"" + obj.value +
//                "\" class=\"form-control\" id=\"inputSuccess\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validatePassword(this)\"" +
//                "onkeydown=\"if ( event.keyCode == '9') validatePassword(this)\">" +
//                "</div>";
        document.getElementById("divpassword").className = "form-group has-success";
        document.getElementById("labelpassword").innerHTML = "<label id=\"labelpassword\" class=\"control-label\" for=\"inputSuccess\">Password is strong</label>";
        if (confirmPassword !== "undifined")
            confirmPassword.focus();
        if (!isBlank(confirmPassword.value) && password.value === confirmPassword.value)
        {
            document.getElementById("divconfirmpassword").className = "form-group has-success";
            document.getElementById("labelconfirmpassword").innerHTML = "<label id=\"labelconfirmpassword\" class=\"control-label\" for=\"inputSuccess\">Password is valid!</label>";
        }

//        alert("return true");
        return true;
    }
}

function validateConfirmPassword(obj)
{

//    alert(password.value);
    if (password.value === (obj.value))
    {
//        alert("success");
//            var divSuccess = "<div id=\"divconfirmpassword\" class=\"form-group has-success\"> " +
//                    "<label id=\"labelconfirmpassword\" class=\"control-label\" for=\"inputSuccess\">Password is valid!</label>" +
//                    "<input id=\"inputconfirmpassword\" type=\"password\" placeholder=\"Password\" value=\"" + obj.value + "\" class=\"form-control\" id=\"inputSuccess\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '0') validateName(this)\"" +
//                    "onkeydown=\"if ( event.keyCode == '9') validatePassword(this)\">" +
//                    "</div>";
        document.getElementById("divconfirmpassword").className = "form-group has-success";
        document.getElementById("labelconfirmpassword").innerHTML = "<label id=\"labelconfirmpassword\" class=\"control-label\" for=\"inputSuccess\">Password is valid!</label>";
//        alert("return true");
        return true;
    }
    if ((isEmpty(password.value)) || (isBlank(password.value)) || !(password.value === (obj.value)))
    {
//        var divPassError = "<div id=\"divpassword\" class=\"form-group has-error\"> " +
//                "<label id=\"labelconfirmpassword\" class=\"control-label\" for=\"inputError\">Passwords are not the same!</label>" +
//                "<input id=\"inputpassword\" type=\"password\" placeholder=\"Password\" class=\"form-control\" id=\"inputError\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '9') validatePassword(this)\"" +
//                "onkeydown=\"if (event.keyCode == '9') validatePassword(this)\" value=\"\">" +
//                "</div>";
        document.getElementById("divpassword").className = "form-group has-error";
        document.getElementById("labelpassword").innerHTML = "<label id=\"labelpassword\" class=\"control-label\" for=\"inputError\">Passwords are not the same!</label>";
        obj.value = '';
//        var divConfPassError = "<div id=\"divconfirmpassword\" class=\"form-group has-error\"> " +
//                "<label id=\"labelconfirmpassword\" class=\"control-label\" for=\"inputError\">Passwords are not the same!</label>" +
//                "<input id=\"inputconfirmpassword\" type=\"password\" placeholder=\"Confirm Password\" class=\"form-control\" id=\"inputError\" onkeyup=\"if (event.keyCode == '13' || event.keyCode == '9') validateConfirmPassword(this)\"" +
//                "onkeydown=\"if (event.keyCode == '9') validateConfirmPassword(this)\" value=\"\">" +
//                "</div>";
        document.getElementById("divconfirmpassword").className = "form-group has-error";
        document.getElementById("labelconfirmpassword").innerHTML = "<label id=\"labelconfirmpassword\" class=\"control-label\" for=\"inputError\">Passwords are not the same!</label>";
        password.value = '';
        password.focus();
//        alert("return false");
        return false;
    }
}

function validateSignUp()
{
    if (!validateName(name))
        return false;
    if (!validateEmail(email))
        return false;
    if (!validatePassword(password))
        return false;
    if (!validateConfirmPassword(confirmPassword))
        return false;

    return true;
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}



function deleteInfoTrack() {
    var selecttrack = document.getElementById("infoB");
    var children = selecttrack.childNodes; //Now get all children of node


    for (var i = 0; i < children.length; i++) {
        selecttrack.removeChild((children[i]));
        if (children[i] !== null)
            if (children[i].hasChildNodes() === true) {

                var childs = children[i].childNodes;
                for (var j = 0; j < childs.length; j++) {
                    children[i].removeChild((childs[j]));
                }
            }

    }

}

/*
 * Replace Special Characters 
 */

function removeDiacritics(str) {

    var defaultDiacriticsRemovalMap = [
        {'base': 'A', 'letters': /[\u0041\u24B6\uFF21\u00C0\u00C1\u00C2\u1EA6\u1EA4\u1EAA\u1EA8\u00C3\u0100\u0102\u1EB0\u1EAE\u1EB4\u1EB2\u0226\u01E0\u00C4\u01DE\u1EA2\u00C5\u01FA\u01CD\u0200\u0202\u1EA0\u1EAC\u1EB6\u1E00\u0104\u023A\u2C6F]/g},
        {'base': 'AA', 'letters': /[\uA732]/g},
        {'base': 'AE', 'letters': /[\u00C6\u01FC\u01E2]/g},
        {'base': 'AO', 'letters': /[\uA734]/g},
        {'base': 'AU', 'letters': /[\uA736]/g},
        {'base': 'AV', 'letters': /[\uA738\uA73A]/g},
        {'base': 'AY', 'letters': /[\uA73C]/g},
        {'base': 'B', 'letters': /[\u0042\u24B7\uFF22\u1E02\u1E04\u1E06\u0243\u0182\u0181]/g},
        {'base': 'C', 'letters': /[\u0043\u24B8\uFF23\u0106\u0108\u010A\u010C\u00C7\u1E08\u0187\u023B\uA73E]/g},
        {'base': 'D', 'letters': /[\u0044\u24B9\uFF24\u1E0A\u010E\u1E0C\u1E10\u1E12\u1E0E\u0110\u018B\u018A\u0189\uA779]/g},
        {'base': 'DZ', 'letters': /[\u01F1\u01C4]/g},
        {'base': 'Dz', 'letters': /[\u01F2\u01C5]/g},
        {'base': 'E', 'letters': /[\u0045\u24BA\uFF25\u00C8\u00C9\u00CA\u1EC0\u1EBE\u1EC4\u1EC2\u1EBC\u0112\u1E14\u1E16\u0114\u0116\u00CB\u1EBA\u011A\u0204\u0206\u1EB8\u1EC6\u0228\u1E1C\u0118\u1E18\u1E1A\u0190\u018E]/g},
        {'base': 'F', 'letters': /[\u0046\u24BB\uFF26\u1E1E\u0191\uA77B]/g},
        {'base': 'G', 'letters': /[\u0047\u24BC\uFF27\u01F4\u011C\u1E20\u011E\u0120\u01E6\u0122\u01E4\u0193\uA7A0\uA77D\uA77E]/g},
        {'base': 'H', 'letters': /[\u0048\u24BD\uFF28\u0124\u1E22\u1E26\u021E\u1E24\u1E28\u1E2A\u0126\u2C67\u2C75\uA78D]/g},
        {'base': 'I', 'letters': /[\u0049\u24BE\uFF29\u00CC\u00CD\u00CE\u0128\u012A\u012C\u0130\u00CF\u1E2E\u1EC8\u01CF\u0208\u020A\u1ECA\u012E\u1E2C\u0197]/g},
        {'base': 'J', 'letters': /[\u004A\u24BF\uFF2A\u0134\u0248]/g},
        {'base': 'K', 'letters': /[\u004B\u24C0\uFF2B\u1E30\u01E8\u1E32\u0136\u1E34\u0198\u2C69\uA740\uA742\uA744\uA7A2]/g},
        {'base': 'L', 'letters': /[\u004C\u24C1\uFF2C\u013F\u0139\u013D\u1E36\u1E38\u013B\u1E3C\u1E3A\u0141\u023D\u2C62\u2C60\uA748\uA746\uA780]/g},
        {'base': 'LJ', 'letters': /[\u01C7]/g},
        {'base': 'Lj', 'letters': /[\u01C8]/g},
        {'base': 'M', 'letters': /[\u004D\u24C2\uFF2D\u1E3E\u1E40\u1E42\u2C6E\u019C]/g},
        {'base': 'N', 'letters': /[\u004E\u24C3\uFF2E\u01F8\u0143\u00D1\u1E44\u0147\u1E46\u0145\u1E4A\u1E48\u0220\u019D\uA790\uA7A4]/g},
        {'base': 'NJ', 'letters': /[\u01CA]/g},
        {'base': 'Nj', 'letters': /[\u01CB]/g},
        {'base': 'O', 'letters': /[\u004F\u24C4\uFF2F\u00D2\u00D3\u00D4\u1ED2\u1ED0\u1ED6\u1ED4\u00D5\u1E4C\u022C\u1E4E\u014C\u1E50\u1E52\u014E\u022E\u0230\u00D6\u022A\u1ECE\u0150\u01D1\u020C\u020E\u01A0\u1EDC\u1EDA\u1EE0\u1EDE\u1EE2\u1ECC\u1ED8\u01EA\u01EC\u00D8\u01FE\u0186\u019F\uA74A\uA74C]/g},
        {'base': 'OI', 'letters': /[\u01A2]/g},
        {'base': 'OO', 'letters': /[\uA74E]/g},
        {'base': 'OU', 'letters': /[\u0222]/g},
        {'base': 'P', 'letters': /[\u0050\u24C5\uFF30\u1E54\u1E56\u01A4\u2C63\uA750\uA752\uA754]/g},
        {'base': 'Q', 'letters': /[\u0051\u24C6\uFF31\uA756\uA758\u024A]/g},
        {'base': 'R', 'letters': /[\u0052\u24C7\uFF32\u0154\u1E58\u0158\u0210\u0212\u1E5A\u1E5C\u0156\u1E5E\u024C\u2C64\uA75A\uA7A6\uA782]/g},
        {'base': 'S', 'letters': /[\u0053\u24C8\uFF33\u1E9E\u015A\u1E64\u015C\u1E60\u0160\u1E66\u1E62\u1E68\u0218\u015E\u2C7E\uA7A8\uA784]/g},
        {'base': 'T', 'letters': /[\u0054\u24C9\uFF34\u1E6A\u0164\u1E6C\u021A\u0162\u1E70\u1E6E\u0166\u01AC\u01AE\u023E\uA786]/g},
        {'base': 'TZ', 'letters': /[\uA728]/g},
        {'base': 'U', 'letters': /[\u0055\u24CA\uFF35\u00D9\u00DA\u00DB\u0168\u1E78\u016A\u1E7A\u016C\u00DC\u01DB\u01D7\u01D5\u01D9\u1EE6\u016E\u0170\u01D3\u0214\u0216\u01AF\u1EEA\u1EE8\u1EEE\u1EEC\u1EF0\u1EE4\u1E72\u0172\u1E76\u1E74\u0244]/g},
        {'base': 'V', 'letters': /[\u0056\u24CB\uFF36\u1E7C\u1E7E\u01B2\uA75E\u0245]/g},
        {'base': 'VY', 'letters': /[\uA760]/g},
        {'base': 'W', 'letters': /[\u0057\u24CC\uFF37\u1E80\u1E82\u0174\u1E86\u1E84\u1E88\u2C72]/g},
        {'base': 'X', 'letters': /[\u0058\u24CD\uFF38\u1E8A\u1E8C]/g},
        {'base': 'Y', 'letters': /[\u0059\u24CE\uFF39\u1EF2\u00DD\u0176\u1EF8\u0232\u1E8E\u0178\u1EF6\u1EF4\u01B3\u024E\u1EFE]/g},
        {'base': 'Z', 'letters': /[\u005A\u24CF\uFF3A\u0179\u1E90\u017B\u017D\u1E92\u1E94\u01B5\u0224\u2C7F\u2C6B\uA762]/g},
        {'base': 'a', 'letters': /[\u0061\u24D0\uFF41\u1E9A\u00E0\u00E1\u00E2\u1EA7\u1EA5\u1EAB\u1EA9\u00E3\u0101\u0103\u1EB1\u1EAF\u1EB5\u1EB3\u0227\u01E1\u00E4\u01DF\u1EA3\u00E5\u01FB\u01CE\u0201\u0203\u1EA1\u1EAD\u1EB7\u1E01\u0105\u2C65\u0250]/g},
        {'base': 'aa', 'letters': /[\uA733]/g},
        {'base': 'ae', 'letters': /[\u00E6\u01FD\u01E3]/g},
        {'base': 'ao', 'letters': /[\uA735]/g},
        {'base': 'au', 'letters': /[\uA737]/g},
        {'base': 'av', 'letters': /[\uA739\uA73B]/g},
        {'base': 'ay', 'letters': /[\uA73D]/g},
        {'base': 'b', 'letters': /[\u0062\u24D1\uFF42\u1E03\u1E05\u1E07\u0180\u0183\u0253]/g},
        {'base': 'c', 'letters': /[\u0063\u24D2\uFF43\u0107\u0109\u010B\u010D\u00E7\u1E09\u0188\u023C\uA73F\u2184]/g},
        {'base': 'd', 'letters': /[\u0064\u24D3\uFF44\u1E0B\u010F\u1E0D\u1E11\u1E13\u1E0F\u0111\u018C\u0256\u0257\uA77A]/g},
        {'base': 'dz', 'letters': /[\u01F3\u01C6]/g},
        {'base': 'e', 'letters': /[\u0065\u24D4\uFF45\u00E8\u00E9\u00EA\u1EC1\u1EBF\u1EC5\u1EC3\u1EBD\u0113\u1E15\u1E17\u0115\u0117\u00EB\u1EBB\u011B\u0205\u0207\u1EB9\u1EC7\u0229\u1E1D\u0119\u1E19\u1E1B\u0247\u025B\u01DD]/g},
        {'base': 'f', 'letters': /[\u0066\u24D5\uFF46\u1E1F\u0192\uA77C]/g},
        {'base': 'g', 'letters': /[\u0067\u24D6\uFF47\u01F5\u011D\u1E21\u011F\u0121\u01E7\u0123\u01E5\u0260\uA7A1\u1D79\uA77F]/g},
        {'base': 'h', 'letters': /[\u0068\u24D7\uFF48\u0125\u1E23\u1E27\u021F\u1E25\u1E29\u1E2B\u1E96\u0127\u2C68\u2C76\u0265]/g},
        {'base': 'hv', 'letters': /[\u0195]/g},
        {'base': 'i', 'letters': /[\u0069\u24D8\uFF49\u00EC\u00ED\u00EE\u0129\u012B\u012D\u00EF\u1E2F\u1EC9\u01D0\u0209\u020B\u1ECB\u012F\u1E2D\u0268\u0131]/g},
        {'base': 'j', 'letters': /[\u006A\u24D9\uFF4A\u0135\u01F0\u0249]/g},
        {'base': 'k', 'letters': /[\u006B\u24DA\uFF4B\u1E31\u01E9\u1E33\u0137\u1E35\u0199\u2C6A\uA741\uA743\uA745\uA7A3]/g},
        {'base': 'l', 'letters': /[\u006C\u24DB\uFF4C\u0140\u013A\u013E\u1E37\u1E39\u013C\u1E3D\u1E3B\u017F\u0142\u019A\u026B\u2C61\uA749\uA781\uA747]/g},
        {'base': 'lj', 'letters': /[\u01C9]/g},
        {'base': 'm', 'letters': /[\u006D\u24DC\uFF4D\u1E3F\u1E41\u1E43\u0271\u026F]/g},
        {'base': 'n', 'letters': /[\u006E\u24DD\uFF4E\u01F9\u0144\u00F1\u1E45\u0148\u1E47\u0146\u1E4B\u1E49\u019E\u0272\u0149\uA791\uA7A5]/g},
        {'base': 'nj', 'letters': /[\u01CC]/g},
        {'base': 'o', 'letters': /[\u006F\u24DE\uFF4F\u00F2\u00F3\u00F4\u1ED3\u1ED1\u1ED7\u1ED5\u00F5\u1E4D\u022D\u1E4F\u014D\u1E51\u1E53\u014F\u022F\u0231\u00F6\u022B\u1ECF\u0151\u01D2\u020D\u020F\u01A1\u1EDD\u1EDB\u1EE1\u1EDF\u1EE3\u1ECD\u1ED9\u01EB\u01ED\u00F8\u01FF\u0254\uA74B\uA74D\u0275]/g},
        {'base': 'oi', 'letters': /[\u01A3]/g},
        {'base': 'ou', 'letters': /[\u0223]/g},
        {'base': 'oo', 'letters': /[\uA74F]/g},
        {'base': 'p', 'letters': /[\u0070\u24DF\uFF50\u1E55\u1E57\u01A5\u1D7D\uA751\uA753\uA755]/g},
        {'base': 'q', 'letters': /[\u0071\u24E0\uFF51\u024B\uA757\uA759]/g},
        {'base': 'r', 'letters': /[\u0072\u24E1\uFF52\u0155\u1E59\u0159\u0211\u0213\u1E5B\u1E5D\u0157\u1E5F\u024D\u027D\uA75B\uA7A7\uA783]/g},
        {'base': 's', 'letters': /[\u0073\u24E2\uFF53\u00DF\u015B\u1E65\u015D\u1E61\u0161\u1E67\u1E63\u1E69\u0219\u015F\u023F\uA7A9\uA785\u1E9B]/g},
        {'base': 't', 'letters': /[\u0074\u24E3\uFF54\u1E6B\u1E97\u0165\u1E6D\u021B\u0163\u1E71\u1E6F\u0167\u01AD\u0288\u2C66\uA787]/g},
        {'base': 'tz', 'letters': /[\uA729]/g},
        {'base': 'u', 'letters': /[\u0075\u24E4\uFF55\u00F9\u00FA\u00FB\u0169\u1E79\u016B\u1E7B\u016D\u00FC\u01DC\u01D8\u01D6\u01DA\u1EE7\u016F\u0171\u01D4\u0215\u0217\u01B0\u1EEB\u1EE9\u1EEF\u1EED\u1EF1\u1EE5\u1E73\u0173\u1E77\u1E75\u0289]/g},
        {'base': 'v', 'letters': /[\u0076\u24E5\uFF56\u1E7D\u1E7F\u028B\uA75F\u028C]/g},
        {'base': 'vy', 'letters': /[\uA761]/g},
        {'base': 'w', 'letters': /[\u0077\u24E6\uFF57\u1E81\u1E83\u0175\u1E87\u1E85\u1E98\u1E89\u2C73]/g},
        {'base': 'x', 'letters': /[\u0078\u24E7\uFF58\u1E8B\u1E8D]/g},
        {'base': 'y', 'letters': /[\u0079\u24E8\uFF59\u1EF3\u00FD\u0177\u1EF9\u0233\u1E8F\u00FF\u1EF7\u1E99\u1EF5\u01B4\u024F\u1EFF]/g},
        {'base': 'z', 'letters': /[\u007A\u24E9\uFF5A\u017A\u1E91\u017C\u017E\u1E93\u1E95\u01B6\u0225\u0240\u2C6C\uA763]/g}
    ];

    for (var i = 0; i < defaultDiacriticsRemovalMap.length; i++) {
        str = str.replace(defaultDiacriticsRemovalMap[i].letters, defaultDiacriticsRemovalMap[i].base);
    }

    return str;

}

function CreateXmlHttpRequestObject()
{

    if (window.XMLHttpRequest)
    {
        try {
            xmlHttpObj = new XMLHttpRequest();
        } catch (e) {
            xmlHttpObj = false;
        }

    }
    else if (window.ActiveXObject)
    {
        try {
            xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttpObj = false;
        }
    }
    return xmlHttpObj;
}