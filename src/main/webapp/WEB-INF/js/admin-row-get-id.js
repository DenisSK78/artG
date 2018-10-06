
(function() {
    'use strict';
    var rows = document.getElementsByClassName("row-table");
    for (var i = 0; i<rows.length; i++){
        rows[i].addEventListener('click' , redirectById)
    }
    function redirectById() {
        window.location.href = '<c:url value="/gallery?do=admin&get="/>'
            + '${requestScope.get("fragment")}'
            + '&id='
            + this.id;
    }
})();