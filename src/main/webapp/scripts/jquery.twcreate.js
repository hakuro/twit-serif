;(function($){ 
$.fn.twcreate = function(q, id, user, text) {

var wait = 1000;
var arrow = "<p><div class='arrow_box'>" + text + "</div></p>";
//var arrow = "<p><div>" + text + "</div></p>";

$("form.form-search").remove();
$("table.table-striped").remove();
$("div#tweetlist").append(arrow);

$.getJSON("/serifses/glimage/" + q, 
    function(json){
        var array = json["responseData"]["results"];
        var imageUrl = [];

        for (var i=0; i < array.length; i++) {
            imageUrl[imageUrl.length] = array[i]["url"];
        }

        var image_num = parseInt(Math.random()*imageUrl.length);
        var image = "<p><img align='center' src='" + imageUrl[image_num] + "' id='serif' /></p>";
        $("div#tweetlist").append(image);
        $("img#serif").hide();
        $("img#serif").fadeIn("slow");

        $("div#tweetlist").append("<p><button type='button' class='btn' onClick='location.href=\"/\";'>Back</button></p>");
    }
);


} })(jQuery);
