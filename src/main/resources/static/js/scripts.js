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



window.onload = function() {
    // Get the divs
    var cardOriginals = document.querySelectorAll('.card .cardOriginal');
    var cardDetails = document.querySelectorAll('.card .cardDetails');

    // console.log(cardOriginals.length + ' cardOriginals found');
    // console.log(cardDetails.length + ' cardDetails found');

    // Set initial display properties
    cardOriginals.forEach(function(cardOriginal) {
        cardOriginal.style.display = 'block';
    });
    cardDetails.forEach(function(cardDetail) {
        cardDetail.style.display = 'none';
    });

    // Add click event listeners
    cardOriginals.forEach(function(cardOriginal, index) {
        var aInCardOriginal = cardOriginal.querySelector('a.info-button');
        aInCardOriginal.addEventListener('click', function(e) {
            e.preventDefault();
            // console.log('cardOriginal clicked');
            cardOriginal.style.display = 'none';
            cardDetails[index].style.display = 'block';
        });
    });

    cardDetails.forEach(function(cardDetail, index) {
        var aInCardDetail = cardDetail.querySelector('a.close-button');
        aInCardDetail.addEventListener('click', function(e) {
            e.preventDefault();
            // console.log('cardDetails clicked');
            cardDetail.style.display = 'none';
            cardOriginals[index].style.display = 'block';
        });
    });
};

