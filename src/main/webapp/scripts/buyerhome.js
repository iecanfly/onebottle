/**
 * Created by iecanfly on 4/4/14.
 */
window.onload = function () {
    $("#divOrder").on("click", function () {
        var numBottle = $("#numBottle").val();
        if (numBottle == "-") return;
        $.ajax({
            url: baseURL + "/orders",
            type: "POST",
            dataType: 'json',
            contentType: "application/json"
        }).done(function (msg) {
                alert(msg.detail);
                window.location.reload();
            });
    });

    $("#btnCancelOrder").on("click", function () {
        if (confirm(ORDER_CANCEL_CONFIRM_MSG)) {
            var orderid = $(this).attr("orderid");

            $.ajax({
                url: baseURL + "/orders/cancel/" + orderid,
                type: "POST"
            }).done(function (msg) {
                    alert(msg.detail);
                    window.location.reload();
                });
        }
    });

    $("#btnForceCancelOrder").on("click", function () {
        if (confirm(ORDER_FORCE_CANCEL_CONFIRM_MSG)) {
            var orderid = $(this).attr("orderid");

            $.ajax({
                url: baseURL + "/orders/cancel/force/" + orderid,
                type: "POST"
            }).done(function (msg) {
                    alert(msg.detail);
                    window.location.reload();
                });
        }
    });

    $("#btnConfirmDelivered").on("click", function () {
        var orderid = $(this).attr("orderid");

        $.ajax({
            url: baseURL + "/orders/confirmDelivery/" + orderid,
            type: "POST"
        }).done(function (msg) {
                alert(msg.detail);
                window.location.reload();
            });
    });

    $("#favSellerDiv").on("click", "#favTable .seller", function(){
        $("#favTable .seller").removeClass("bg-light-blue selected");
        $(this).addClass("bg-light-blue selected");
        $.ajax({
            url: baseURL + "/ajax/sellerItemsList/" + $("#favTable .selected").attr("sellerId")
        }).done(function (html) {
            $("#itemTable").html(html);
        });
    });

    $("#favItemDiv").on("click", "#itemTable .item", function(){
        $("#favItemDiv .item").removeClass("bg-light-blue selected");
        $(this).addClass("bg-light-blue selected");
    });

    $("#btnSubmitFav").on("click", function(){
        if($("#favTable .selected").attr("sellerId") == undefined || $("#itemTable .selected").attr("itemId") == undefined) {
            alert("select");
            return;
        }
        $.ajax({
            url: baseURL + "/buyerFavOrder",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({ numBottles: $("#numBottleSelect").val(), sellerId : $("#favTable .selected").attr("sellerId"), itemId : $("#itemTable .selected").attr("itemId") })
        }).done(function (result) {
            alert(result.detail);
            if(result.message == "success") {
                window.location.reload();
            }
        });
    });

    $("#btnSellerPrev").on("click", function(){
        var currentPage = parseInt($("#favTable").attr("page"));
        if(currentPage <= 1) {
            return;
        }

        $.ajax({
            url: baseURL + "/ajax/sellerList/" + (currentPage - 1)
        }).done(function (html) {
            $("#favTable").html(html);
            $("#itemTable .item").remove();
            $("#favTable").attr("page", currentPage - 1);
            $("#btnSellerNext").removeClass("disabled");
            if(currentPage - 1 == 1) {
                $("#btnSellerPrev").addClass("disabled");
            }
        });
    });

    $("#btnSellerNext").on("click", function(){
        var currentPage = parseInt($("#favTable").attr("page"));
        if($("#favTable .seller").size < 5) {
            return;
        }

        $.ajax({
            url: baseURL + "/ajax/sellerList/" + (currentPage + 1)
        }).done(function (html) {
            $("#favTable").html(html);
            $("#favTable").attr("page", currentPage + 1)
            $("#itemTable .item").remove();
            $("#btnSellerPrev").removeClass("disabled");
            if($("#favTable .seller").size() < 6) {
                $("#btnSellerNext").addClass("disabled");
            }
        });
    });

    $("#btnDeleteFav").on("click", function(){
        if(confirm(FAV_ORDER_DELETE_MSG)) {
            $.ajax({
                url: baseURL + "/buyerFavOrder/delete?id=" + $(this).attr("favid"),
                type: "POST"
            }).done(function (msg) {
                alert(msg.detail);
                window.location.reload();
            });
        }
    });


};