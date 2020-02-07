$("#myform").validate({
    rules: {
        your_nome_curso: {
            required: true,
            nome_curso: true
        }
    },
    messages: {
        nome_curso: {
            required: "Informe o nome do curso"
        },

    },
    success: function (label, element) {
        if ($(element).hasClass("valid")) {
            label.addClass("valid");
        }
    },
    focusInvalid: false,
    focusCleanup: true,
    onkeyup: false,
});

$(document).ready(function () {
    var $campoMoney = $("#money").mask("#.##0,00", {
        reverse: true
    });
    var $campoCargaHoraria = $("#carga_horaria").mask("00", {
        reverse: true
    });
});