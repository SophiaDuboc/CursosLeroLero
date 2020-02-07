
$("#myform").validate({
    rules: {
        your_email: {
            required: true,
            email: true,
        },
        your_username: {
            required: true,
            username: true
        },
        password: "required",
        comfirm_password: {
            equalTo: "#password"
        }
    },
    messages: {
        your_nome_completo: {
            required: "Informe seu nome completo",
            nome_completo: true
        },
        your_cpf: {
            required: "Informe seu cpf",
            cpf: true
        },
        your_celular: {
            required: "Informe seu telefone",
            celular: true
        },
        username: {
            required: "Informe um nome de usuário",
            username: true
        },
        your_email: {
            required: "Informe um email",
            email: true
        },
        password: {
            required: "Informe uma senha"
        },
        comfirm_password: {
            required: "Confirme sua senha",
            equalTo: "Senha não confere"
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

    submitHandler: function (form) {
        $(".form-registro").hide();
        $(".form-detalhe").show();
    }
});

$(document).ready(function () {
    var $campoCpf = $("#cpf").mask('000.000.000-00', {
        reverse: true
    });
    var $campoCelular = $("#celular").mask('(00)0 0000-0000', {
        reverse: true
    });
    var $campoCep = $("#cep").mask('00000-000', {
        reverse: true
    });
});