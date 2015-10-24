function fillAddressSelect(elSelect, parentId) {
    $.ajax({
        url: "location/" + parentId,
        type: "GET"
    }).done(function (list) {
            var selectedId = elSelect.attr("selectedID");
            elSelect.html("");
            for (var i in list) {
                var optionSelected = selectedId == i ? " selected" : "";
                elSelect.append("<option value=\"" + i + "\"" + optionSelected +  ">" + list[i] + "</option>");
            }

            elSelect.trigger("change", selectedId);

        });
}

window.onload = function () {
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });

    $(".computedAddress").hide();

    $("#btnNotCorrectAddress").on("click", function(){
        $(".computedAddress").show();
        $(this).hide();
        $(this).prev().hide();
    });

    $("#address_province").on("change", function () {
        fillAddressSelect($("#address_city"), $(this).val());
    });

    $("#address_city").on("change", function () {
        fillAddressSelect($("#address_area"), $(this).val());
    });

};


