
$("#myform").validate({
    rules: {
        your_instrutor: {
            required: true,
            instrutor: true
        },
        your_curso: {
            required: true,
            curso: true
        },
        data_inicio: {
            required: true,
            maxDate: true,
        },
        data_fim: {
            required: true,
            greaterThan: "#data_inicio",
            data_fim: true
        }
    },
    messages: {
        instrutor: {
            required: "Informe o instrutor"
        },
        curso: {
            required: "Informe o curso"
        },
        data_inicio: {
            pattern: "Data inválida!"
        },
        data_fim: {
            pattern: "Data inválida!"
        },
        carga_horaria: {
            pattern: "Carga Horaria Inválida!"
        }
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
    var $campoCargaHoraria = $("#carga_horaria").mask("00", {
        reverse: true
    });
    var $campoCpf = $("#cpf").mask('000.000.000-00', {
        reverse: true
    });
    var $campoCelular = $("#celular").mask('(00)0 0000-0000', {
        reverse: true
    });
    var $campoCep = $("#cep").mask('00000-000', {
        reverse: true
    });
    var $campoDatetime = $("[name^='data']").mask("00/00/0000", {
        reverse: true
    });
});

jQuery.validator.addMethod("greaterThan", function (value, element, params) {
    console.log("aq eu entrei");
    if (!/Invalid|NaN/.test(new Date(value))) {
        return new Date(value) > new Date($(params).val());
    }

    return isNaN(value) && isNaN($(params).val())
        || (Number(value) > Number($(params).val()));
}, 'Data fim precisa ser maior que a Data inicio.');

jQuery.validator.addMethod("maxDate", function (value, element) {
    console.log("entrei", value);
    var curDate = new Date();
    console.log("entrei1", curDate);
    var inputDate = new Date(value);
    console.log("entrei2", inputDate);
    if (inputDate.getTime() > curDate.getTime())
        return true;
    return false;
}, "Data precisa ser maior que o dia atual");