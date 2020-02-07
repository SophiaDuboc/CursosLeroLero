    $("#myform").validate({
        messages: {
            name: {
                required: "Informe seu nome completo"
            },
            username: {
                required: "Informe um nome de usuário"
            },
            password: {
                required: "Informe uma senha"
            },
            comfirm_password: {
                required: "Confirme sua senha",
                equalTo: "Senha não confere"
            }
        },
        rules: {
            your_username: {
                required: true,
                username: true
            },
            your_name: {
                required: true,
                name: true
            },
            password: "required",
            comfirm_password: {
                equalTo: "#password"
            }
        },
        success: function (label, element) {
            if ($(element).hasClass("valid")) {
                label.addClass("valid");
            }
        },
        focusInvalid: false,
        focusCleanup: true,
        onkeyup: false
    });