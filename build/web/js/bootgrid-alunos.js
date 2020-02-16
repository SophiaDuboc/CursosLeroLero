var grid = $("#grid-data").bootgrid({
    ajax: true,
    ajaxSettings: {
        method: "GET",
        cache: false
    },
    url: "/CursosLeroLero/AlunosController",
    formatters: {
        "acoes": function (column, row)
        {
            return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-pencil\"></span></button> " +
                    "<button type=\"button\" class=\"btn btn-xs btn-default command-delete\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-trash-o\"></span></button>";
        },
        "nome" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="nome'+ row.id +'" value="' + row.nome +'"/>'
        },
        "email" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="email'+ row.id +'" value="' + row.email +'"/>'
        },
        "celular" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="celular'+ row.id +'" value="' + row.celular +'"/>'
        },
        "login" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="login'+ row.id +'" value="' + row.login +'"/>'
        },
        "endereco" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="endereco'+ row.id +'" value="' + row.endereco +'"/>'
        },
        "cidade" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="cidade'+ row.id +'" value="' + row.cidade +'"/>'
        },
        "bairro" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="bairro'+ row.id +'" value="' + row.bairro +'"/>'
        },
        "cep" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="cep'+ row.id +'" value="' + row.cep +'"/>'
        }
       /* "comentario" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="comentario'+ row.id +'" value="' + row.comentario +'"/>'
        },
        "aprovado" : function (column, row) 
        {
            return '<input type="text" class="form-control" id="aprovado'+ row.id +'" value="' + row.aprovado +'"/>'
        }*/
    }
}).on("loaded.rs.jquery.bootgrid", function ()
{
    /* Executes after data is loaded and rendered */
    grid.find(".command-edit").on("click", function (e)
    {
        rowId = $(this).data("row-id");
        row = atualizaEPegaRow("#grid-data", rowId);
        $.ajax({
            url: '/CursosLeroLero/AlunosController',
            data: {
                nome_completo: row.nome,
                cpf: row.cpf,
                your_email: row.email,
                celular: row.celular,
                username:row.login,
                endereco:row.endereco,
                cidade:row.cidade,
                bairro:row.bairro,
                cep:row.cep,
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
            url: '/CursosLeroLero/AlunosController?id=' + row.id,
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
            rowArray[index].cpf = $("#cpf" + rowArray[index].id).val();
            rowArray[index].email = $("#email" + rowArray[index].id).val();
            rowArray[index].celular = $("#celular" + rowArray[index].id).val();
            rowArray[index].login = $("#login" + rowArray[index].id).val();
            rowArray[index].endereco = $("#endereco" + rowArray[index].id).val();
            rowArray[index].cidade = $("#cidade" + rowArray[index].id).val();
            rowArray[index].bairro = $("#bairro" + rowArray[index].id).val();
            rowArray[index].cep = $("#cep" + rowArray[index].id).val();
            return rowArray[index];
        }
    }
    return null;
}