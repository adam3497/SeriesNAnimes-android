$(document).ready(function () {

    $("#SelectTable").change(function () {

        if($(this).val() === "anime"){
            $("#AjaxTest").load("../html/login.html #BodyLogin");
        }
        else if($(this).val() === "serie"){
            $("#AjaxTest").load("../html/login.html #BodyLogin");
        }
        else if($(this).val() === "category"){
            $("#AjaxTest").load("../html/login.html #BodyLogin");
        }
        else if($(this.val()) === "user"){
            $("#AjaxTest").load("../html/login.html #BodyLogin");
        }
    });
});