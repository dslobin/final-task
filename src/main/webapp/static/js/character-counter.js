/**
 * Function for counting the number of entered characters
 *
 * @param observedObject object in which the number of entered characters is counted
 * @param max maximum number of characters
 * @param typeChars count of typed chars
 */
function limitChars(observedObject, max, typeChars) {
    $(observedObject).keydown(function () {
        let enteredCharsCount = $(this).val().length;
        let remainingCharsNumber = max - enteredCharsCount;
        $(typeChars).text(enteredCharsCount + '/' + max);
        if (remainingCharsNumber > 0) {
            $(this).removeClass('is-invalid');
        } else {
            $(this).addClass('is-invalid');
        }
    });
}

$(document).ready(function () {
    let serviceDescription = '#serviceDescription';
    let max = 2048;
    let typeChars = '#typeChars';
    limitChars(serviceDescription, max, typeChars);
});