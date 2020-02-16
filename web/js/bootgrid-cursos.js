var grid = $("#grid-data").bootgrid({
    ajax: true,
    ajaxSettings: {
        method: "GET",
        cache: false
    },
    url: "/CursosLeroLero/CursosController",
    formatters: {
        "acoes": function (column, row)
        {
            return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-pencil\"></span></button> " +
                    "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-trash-o\"></span></button>";
        },
        "nome": function (column, row)
        {
            return '<input type="text" class="form-control" id="nome' + row.id + '" value="' + row.nome + '"/>'
        },
        "requisito": function (column, row)
        {
            return '<input type="text" class="form-control" id="requisito' + row.id + '" value="' + row.requisito + '"/>'
        },
        "ementa": function (column, row)
        {
            return '<input type="text" class="form-control" id="ementa' + row.id + '" value="' + row.ementa + '"/>'
        },
        "carga_horaria": function (column, row)
        {
            return '<input type="text" class="form-control" id="carga_horaria' + row.id + '" value="' + row.carga_horaria + '"/>'
        },
        "preco": function (column, row)
        {
            return '<input type="text" class="form-control" id="preco' + row.id + '" value="' + row.preco + '"/>'
        }
    }
}).on("loaded.rs.jquery.bootgrid", function ()
{
    /* Executes after data is loaded and rendered */
    grid.find(".command-edit").on("click", function (e)
    {
        rowId = $(this).data("row-id");
        row = atualizaEPegaRow("#grid-data", rowId);
        $.ajax({
            url: '/CursosLeroLero/CursosController',
            data: {
                nome: row.nome,
                requisito: row.requisito,
                ementa: row.ementa,
                carga_horaria: row.carga_horaria,
                money: row.preco,
                id: row.id
            },
            type: "post"
        }).done(function (data, textStatus, jqXHR) {
            alert(data);
            $("#grid-data").bootgrid('reload');
        });

    }).end().find(".command-delete").on("click", function (e)
    {
        rowId = $(this).data("row-id");
        row = atualizaEPegaRow("#grid-data", rowId);
        $.ajax({
            url: '/CursosLeroLero/CursosController?id=' + row.id,
            type: "DELETE"
        }).done(function (data, textStatus, jqXHR) {
            alert(data);
            $("#grid-data").bootgrid('reload');
        });
    });
});

function atualizaEPegaRow(grid, id) {
    rowArray = $(grid).bootgrid("getCurrentRows");

    for (index = 0; index < rowArray.length; index++) {
        if (rowArray[index].id === id) {
            rowArray[index].nome = $("#nome" + rowArray[index].id).val();
            rowArray[index].requisito = $("#requisito" + rowArray[index].id).val();
            rowArray[index].ementa = $("#ementa" + rowArray[index].id).val();
            rowArray[index].carga_horaria = $("#carga_horaria" + rowArray[index].id).val();
            rowArray[index].preco = $("#preco" + rowArray[index].id).val();
            return rowArray[index];
        }
    }
    return null;
}