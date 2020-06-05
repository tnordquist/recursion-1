// The function actually applying the offset
function offsetAnchor() {
  if (location.hash.length !== 0) {
    const banner = $('#banner');
    const bottom = banner.position().top + banner.outerHeight(true);
    window.scrollTo(window.scrollX, window.scrollY - bottom);
  }
}

// Captures click events of all <a> elements with href starting with #
$(document).on('click', 'a[href^="#"]', function(event) {
  // Click events are captured before hashchanges. Timeout
  // causes offsetAnchor to be called after the page jump.
  window.setTimeout(function() {
    offsetAnchor();
  }, 1);
});

// Set the offset when entering page with hash present in the url
window.setTimeout(offsetAnchor, 1);