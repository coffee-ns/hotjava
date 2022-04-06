var slider = document.getElementById("minRange");
var output = document.getElementById("minScore");
output.innerHTML = slider.value;

slider.oninput = function() {
    output.innerHTML = this.value;
}
