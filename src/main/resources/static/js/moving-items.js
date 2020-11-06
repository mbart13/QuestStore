const submitFormButton = document.querySelector("#mentors-form");

$(function() {
    $("#right-arrow").click(function() {
        $("#left-box option:selected").each(function() {
            $(this).remove().appendTo("#right-box");
        });
    });

    $("#left-arrow").click(function() {
        $("#right-box option:selected").each(function() {
            $(this).remove().appendTo("#left-box");
        });
    });
});

submitFormButton.addEventListener("submit", function(e) {
    e.preventDefault();
    const elements = document.querySelector("#right-box").options;
    for (let elem of elements) {
        elem.selected = true;
    }
    this.submit();
});
