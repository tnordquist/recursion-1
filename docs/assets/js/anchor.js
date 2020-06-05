// The function actually applying the offset
function offsetAnchor() {
  if (location.hash.length !== 0) {
    const banner = $('#banner');
    const bottom = banner.position().top + banner.outerHeight(true);
    console.log(bottom);
    window.scrollTo(window.scrollX, window.scrollY - bottom);
  }
}

function scheduleOffset(delay) {
  window.setTimeout(function() {
    offsetAnchor();
  }, delay);
}

// Captures click events of all <a> elements with href starting with #
$(document).on('click', 'a[href^="#"]', function(event) {
  console.log("Scheduling from click");
  scheduleOffset(1);
});

// When page is loaded, enqueue the adjustment in MathJax rendering queue.
$(window).on("load", function() {
  MathJax.Hub.Queue(function () {
    console.log("Scheduling from MathJax queue");
    scheduleOffset(100);
  });
});