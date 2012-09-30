;(function($){ 
$.fn.twprepend = function(q) {
if (q.match(/[^A-Za-z0-9]+/)) {
    alert("検索欄には半角英数を入力してください。");
    return false;
}

var twFadeIn = function(tweets) {
    var wait = 1000;

    $("tr#row").remove();

    for (var i=0; i < tweets.length; i++) {
        $("#serifs").prepend(tweets[i]);
        $("tr#row").hide();
        setTimeout(function(){
            $("tr:hidden:first").fadeIn("slow");
        }, wait * i);
    }
};

$.getJSON("/serifses/timeline/" + q, 
    function(json){
        var array = json["results"];
        var tweets = [];

        for (var i=0; i < array.length; i++) {
            var text = array[i]["text"];

            if ((0 > text.indexOf("@")) && (0 > text.indexOf("http://"))) {
                var line = "<tr id='row'>";
                var index = ["profile_image_url", "from_user", "text", "id"];

                for (var j=0; j < index.length; j++) {
                    if (index[j] === "profile_image_url") {
                        line += "<td><img id='icon' src='" + array[i][index[j]] + "'></td>";
                    }
                    else if (index[j] === "id") {
                        line += "<td><button type='button' class='btn' onClick='$(this).twcreate(\""
                        line += q + "\",\""
                        line += array[i]["id"] + "\",\""
                        line += array[i]["from_user"] + "\",\""
                        line += array[i]["text"] + "\""
                        line += ");'>create serif</button></td>";
                    }
                    else {
                        line += "<td>" + array[i][index[j]] + "</td>";
                    }
                }
                line += "</tr>";
                tweets[tweets.length] = line;
            }
        }

        twFadeIn(tweets);
    }
);

} })(jQuery);

