/* When the user scrolls down, hide the navbar. When the user scrolls up, show the navbar */
var prevScrollpos = window.pageYOffset;
window.onscroll = function() {
  var currentScrollPos = window.pageYOffset;
  if (prevScrollpos > currentScrollPos) {
    document.getElementById("navbar").style.top = "0";
    document.getElementById("navbarcont").style.top = "0";
  } else {
    document.getElementById("navbar").style.top = "-120px";
    document.getElementById("navbarcont").style.top = "-120px";
  }
  prevScrollpos = currentScrollPos;
} 

//scrollToTop
var mybtn = $('#button');

$(window).scroll(function() {
  if ($(window).scrollTop() > 300) {
    mybtn.addClass('show');
  } else {
    mybtn.removeClass('show');
  }
});

mybtn.on('click', function(e) {
  e.preventDefault();
  $('html, body').animate({scrollTop:0}, '300');
});

//Modal Funcionamento
// Get the modal
var modal1 = document.getElementById("myModal1");
var modal2 = document.getElementById("myModal2");
var modal3 = document.getElementById("myModal3");
var modal4 = document.getElementById("myModal4");
var modal5 = document.getElementById("myModal5");
var modal6 = document.getElementById("myModal6");
var modal7 = document.getElementById("myModal7");
var modal8 = document.getElementById("myModal8");
var modal9 = document.getElementById("myModal9");
var modalreg = document.getElementById("myModalReg");

// Get the button that opens the modal
var btn1 = document.getElementById("myBtn1");
var btn2 = document.getElementById("myBtn2");
var btn3 = document.getElementById("myBtn3");
var btn4 = document.getElementById("myBtn4");
var btn5 = document.getElementById("myBtn5");
var btn6 = document.getElementById("myBtn6");
var btn7 = document.getElementById("myBtn7");
var btn8 = document.getElementById("myBtn8");
var btn9 = document.getElementById("myBtn9");
var btnreg = document.getElementById("myBtnReg");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn1.onclick = function() {
  modal1.style.display = "block";
}
btn2.onclick = function() {
  modal2.style.display = "block";
}
btn3.onclick = function() {
  modal3.style.display = "block";
}
btn4.onclick = function() {
  modal4.style.display = "block";
}
btn5.onclick = function() {
  modal5.style.display = "block";
}
btn6.onclick = function() {
  modal6.style.display = "block";
}
btn7.onclick = function() {
  modal7.style.display = "block";
}
btn8.onclick = function() {
  modal8.style.display = "block";
}
btn9.onclick = function() {
  modal9.style.display = "block";
}
btnreg.onclick = function() {
  modalreg.style.display = "block";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal1) {
    modal1.style.display = "none";
  } else if (event.target == modal2) {
    modal2.style.display = "none";
  } else if (event.target == modal3){
    modal3.style.display = "none";
  } else if (event.target == modal4){
    modal4.style.display = "none";
  } else if (event.target == modal5){
    modal5.style.display = "none";
  } else if (event.target == modal6){
    modal6.style.display = "none";
  } else if (event.target == modal7){
    modal7.style.display = "none";
  } else if (event.target == modal8){
    modal8.style.display = "none";
  } else if (event.target == modal9){
    modal9.style.display = "none";
  } else if (event.target == modalreg){
    modalreg.style.display = "none";
  }
}
//fecha navar toda vez que um click for feito dentro dos itens dela
$('.nav-link').on('click', function(){ $('.navbar-collapse').collapse('hide'); });