document.addEventListener('DOMContentLoaded', function() {
  const label = document.querySelector('label[for="uNameLabel"]');
  const hoverDiv = document.getElementById('hoverDiv');
  let timeoutId;

  label.addEventListener('mouseenter', function() {
    clearTimeout(timeoutId);
    hoverDiv.style.display = 'block';
    hoverDiv.style.marginTop = '10px';
  });

  label.addEventListener('mouseleave', function() {
    timeoutId = setTimeout(function() {
      hoverDiv.style.display = 'none';
    }, 3000);
  });
});

document.addEventListener('DOMContentLoaded', function() {
  const label = document.querySelector('label[for="uNameLabel2"]');
  const hoverDiv = document.getElementById('hoverDiv');
  let timeoutId;

  label.addEventListener('mouseenter', function() {
    clearTimeout(timeoutId);
    hoverDiv.style.display = 'block';
    hoverDiv.style.marginTop = '10px';
  });

  label.addEventListener('mouseleave', function() {
    timeoutId = setTimeout(function() {
      hoverDiv.style.display = 'none';
    }, 3000);
  });
});







  

