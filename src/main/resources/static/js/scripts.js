/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
// Get all nav links
const navLinks = document.querySelectorAll('.nav-link');

// Loop through all links
for (let i = 0; i < navLinks.length; i++) {
    // If the href of the link matches the current URL
    if (navLinks[i].href == window.location.href) {
        // Add the "active" class
        navLinks[i].classList.add('active');
    } else {
        // Otherwise, remove it
        navLinks[i].classList.remove('active');
    }
}