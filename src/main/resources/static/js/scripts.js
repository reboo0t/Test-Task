const navLinks = document.querySelectorAll('.nav-link');

for (let i = 0; i < navLinks.length; i++) {
    if (navLinks[i].href === window.location.href) {
        navLinks[i].classList.add('active');
    } else {
        navLinks[i].classList.remove('active');
    }
}