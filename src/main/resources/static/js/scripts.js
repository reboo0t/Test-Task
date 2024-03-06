const navLinks = document.querySelectorAll('.nav-link');

for (let i = 0; i < navLinks.length; i++) {
    if (navLinks[i].href === window.location.href) {
        navLinks[i].classList.add('active');
    } else {
        navLinks[i].classList.remove('active');
    }
}

// Function to check viewport's width and add/remove class
function checkViewport() {
    var formElement = document.getElementById('navbar-login-button');
    var mediaQuery = window.matchMedia('(max-width: 992px)');

    if (mediaQuery.matches) {
        formElement.classList.add('mb-2');
    } else {
        formElement.classList.remove('mb-2');
    }
}

// Call the function when the page loads
checkViewport();

// Call the function when the window resizes
window.addEventListener('resize', checkViewport);
