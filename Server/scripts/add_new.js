$(document).ready(function () {

    $("#SelectTable").change(function () {

        if($(this).val() === "anime"){
            var formAnimeData = {
                action:"consult",
                table:$(this).val()
            };
            //send the data to the serve and receive the response in a certain function
            $.post("../php/EntryTransactions.php", formAnimeData, createAnimeInputs);
        }
        else if($(this).val() === "serie"){
            var formSerieData = {
                action:"consult",
                table:$(this).val()
            };
            //send the data to the serve and receive the response in a certain function
            $.post("../php/EntryTransactions.php", formSerieData, createSerieInputs);
        }
        else if($(this).val() === "category"){
            var formCategoryData = {
                action:"consult",
                table:$(this).val()
            };
            //send the data to the serve and receive the response in a certain function
            $.post("../php/EntryTransactions.php", formCategoryData, createCategoryInputs);
        }
        else if($(this).val() === "user"){
            var formUserData = {
                action:"consult",
                table:$(this).val()
            };
            //send the data to the serve and receive the response in a certain function
            $.post("../php/EntryTransactions.php", formUserData, createUserInputs);
        }

    });

});