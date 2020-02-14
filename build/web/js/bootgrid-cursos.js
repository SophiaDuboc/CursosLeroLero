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
        "nome_curso" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="nome_curso'+ row.id +'" value="' + row.nome_curso +'"/>'
        },
        "requisitos" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="requisitos'+ row.id +'" value="' + row.requisitos +'"/>'
        },
        "ementa" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="ementa'+ row.id +'" value="' + row.ementa +'"/>'
        },
        "carga_horaria" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="carga_horaria'+ row.id +'" value="' + row.carga_horaria +'"/>'
        },
        "preco" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="preco'+ row.id +'" value="' + row.preco +'"/>'
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
            data: { //CORRIGIIIIIIIIIIIIIIIIIIIIIIIIIIIIR
                nome_curso: row.nome_curso,
                requisitos: row.requisitos,
                ementa: row.ementa,
                carga_horaria: row.carga_horaria,
                id: row.id
            },
            type: "post"
        }).done(function (data, textStatus, jqXHR) {
            alert (data);
            $("#grid-data").bootgrid('reload');
        });

    }).end().find(".command-delete").on("click", function (e)
    {
        rowId = $(this).data("row-id");
        row = atualizaEPegaRow("#grid-data", rowId);
        $.ajax({
            url: '/CursosLeroLero/InstrutoresController?id=' + row.id,
            type: "DELETE"
        }).done(function (data, textStatus, jqXHR) {
            alert (data);
            $("#grid-data").bootgrid('reload');
        });
        alert("You pressed delete on row: " + $(this).data("row-id"));
    });
});


function atualizaEPegaRow (grid, id) {
    rowArray = $(grid).bootgrid("getCurrentRows");

    for (index = 0; index < rowArray.length; index ++) {
        if (rowArray[index].id === id) {
            rowArray[index].nome = $("#nome" + rowArray[index].id).val();
            rowArray[index].email = $("#email" + rowArray[index].id).val();
            rowArray[index].valor_hora = $("#valor_hora" + rowArray[index].id).val();
            return rowArray[index];
        }
    }
    return null;
}