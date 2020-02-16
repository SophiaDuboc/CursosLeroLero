var grid = $("#grid-data").bootgrid({
    ajax: true,
    ajaxSettings: {
        method: "GET",
        cache: false
    },
    url: "/CursosLeroLero/TurmasController",
    formatters: {
        "acoes": function (column, row)
        {
            return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-pencil\"></span></button> " +
                    "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-trash-o\"></span></button>";
        },
        "cursos_id": function (column, row)
        {
            return '<input type="text" class="form-control" id="cursos_id' + row.id + '" value="' + row.cursos_id + '"/>'
        },
        "instrutores_id": function (column, row)
        {
            return '<input type="text" class="form-control" id="instrutores_id' + row.id + '" value="' + row.instrutores_id + '"/>'
        },
        "data_inicio": function (column, row)
        {
            return '<input type="text" class="form-control" id="data_inicio' + row.id + '" value="' + row.data_inicio + '"/>'
        },
        "data_final": function (column, row)
        {
            return '<input type="text" class="form-control" id="data_final' + row.id + '" value="' + row.data_final + '"/>'
        },
        "carga_horaria": function (column, row)
        {
            return '<input type="text" class="form-control" id="carga_horaria' + row.id + '" value="' + row.carga_horaria + '"/>'
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
            url: '/CursosLeroLero/TurmasController',
            data: {
                cursos_id: row.cursos_id,
                data_inicio: row.data_inicio,
                data_final: row.data_final,
                instrutores_id: row.instrutores_id,
                carga_horaria: row.carga_horaria,
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
            url: '/CursosLeroLero/TurmasController?id=' + row.id,
            type: "DELETE"
        }).done(function (data, textStatus, jqXHR) {
            alert(data);
            $("#grid-data").bootgrid('reload');
        });
        alert("You pressed delete on row: " + $(this).data("row-id"));
    });
});
function atualizaEPegaRow(grid, id) {
    rowArray = $(grid).bootgrid("getCurrentRows");
    for (index = 0; index < rowArray.length; index++) {
        if (rowArray[index].id === id) {
            rowArray[index].data_inicio = $("#data_inicio" + rowArray[index].id).val();
            rowArray[index].data_final = $("#data_final" + rowArray[index].id).val();
            rowArray[index].carga_horaria = $("#carga_horaria" + rowArray[index].id).val();
            rowArray[index].instrutores_id = $("#instrutores_id" + rowArray[index].id).val();
            rowArray[index].cursos_id = $("#cursos_id" + rowArray[index].id).val();
            return rowArray[index];
        }
    }
    return null;
}
