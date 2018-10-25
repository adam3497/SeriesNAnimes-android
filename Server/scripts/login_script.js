$(document).ready(function () {
    //hide the actions in the form, is only for server requests
    $("#action").hide();
    $("#Preloader").hide();

   $("#username").focus(function () {
      $("#response").text("");
   });
    $("#password").focus(function () {
        $("#response").text("");
    });

    $("#FormLogin").submit(function () {
        $("#Preloader").show();
        //data to send to the server
        var formData = {
            action:$("#action").val(),
            username:$("#username").val(),
            password:$("#password").val()
        };

        //send the data to the serve and receive the response in a certain function
        $.post("../php/EntryTransactions.php", formData, manageResponse);
        return false;
    });

    function manageResponse(response) {
        $("#Preloader").hide();
        if(response === "userOk"){
            $("#response").text("Usuario correcto");
            window.location.href = "../html/index.html";
        }
        else if(response === "userError"){
            $("#response").text("Usuario o contraseña incorrectos");
        }
        else if(response === "userNotRegister"){
            $("#response").text("El usuario no se encuentra registrado");
        }
        else {
            $("#response").text("Error interno, vuelva a intentarlo");
        }
    }

});