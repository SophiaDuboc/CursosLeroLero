
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
        nome_completo: {
            required: "Informe seu nome completo"
        },
        cpf: {
            required: "Informe seu cpf"
        },
        celular: {
            required: "Informe seu telefone"
        },
        username: {
            required: "Informe um nome de usuário"
        },
        your_email: {
            required: "Informe um email"
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

});

$(document).ready(function () {
    var $campoMoney = $("#money").mask("#.##0,00", {
        reverse: true
    });
});
