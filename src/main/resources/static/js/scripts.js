window.onload = function () {
    // Get the divs
    let cardOriginals = document.querySelectorAll('.card .cardOriginal');
    let cardDetails = document.querySelectorAll('.card .cardDetails');

    // console.log(cardOriginals.length + ' cardOriginals found');
    // console.log(cardDetails.length + ' cardDetails found');

    // Set initial display properties
    cardOriginals.forEach(function (cardOriginal) {
        cardOriginal.style.display = 'block';
    });
    cardDetails.forEach(function (cardDetail) {
        cardDetail.style.display = 'none';
    });

    // Add click event listeners
    cardOriginals.forEach(function (cardOriginal, index) {
        let aInCardOriginal = cardOriginal.querySelector('a.info-button');
        aInCardOriginal.addEventListener('click', function (e) {
            e.preventDefault();
            // console.log('cardOriginal clicked');
            cardOriginal.style.display = 'none';
            cardDetails[index].style.display = 'block';
        });
    });

    cardDetails.forEach(function (cardDetail, index) {
        let aInCardDetail = cardDetail.querySelector('a.close-button');
        aInCardDetail.addEventListener('click', function (e) {
            e.preventDefault();
            // console.log('cardDetails clicked');
            cardDetail.style.display = 'none';
            cardOriginals[index].style.display = 'block';
        });
    });
};