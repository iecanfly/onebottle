window.onload = function () {
    $(".btn-register").on("click", function(){
        var id = $(this).closest("form").find("input").val();
        location.href = location.href.replace("index", "signup?id=" + id + "&location=" + y + "," + x);
        return false;
    });
};