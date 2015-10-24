/**
 * Created by iecanfly on 4/4/14.
 */
window.onload = function(){
    $(".takeorder").on("click", function(){
        var orderId = $(this).attr("orderid");

        $.ajax({
            url: baseURL + "/orders/take",
            type: "POST",
            dataType : 'json',
            contentType : "application/json",
            data : JSON.stringify({ id : orderId })
        }).done(function(msg) {
            alert(msg.detail);
            window.location.reload();
        });
    });

    $(".markDelivered").on("click", function(){
        var orderId = $(this).attr("orderid");

        $.ajax({
            url: baseURL + "/orders/mark/delivered",
            type: "POST",
            dataType : 'json',
            contentType : "application/json",
            data : JSON.stringify({ id : orderId })
        }).done(function(msg) {
            alert(msg.detail);
            window.location.reload();
        });
    });

    $(".deleteOrder").on("click", function(){
        var orderId = $(this).attr("orderid");

        $.ajax({
            url: baseURL + "/orders/delete",
            type: "POST",
            dataType : 'json',
            contentType : "application/json",
            data : JSON.stringify({ id : orderId })
        }).done(function(msg) {
            alert(msg.detail);
            window.location.reload();
        });
    });


};