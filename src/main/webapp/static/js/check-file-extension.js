/**
 * Checks the extension of the selected file, if the extension is not valid,
 * displays a message and resets the file selection
 */
$("#carImageUploader").change(function () {
    let fileExtension = ['jpeg', 'jpg', 'png', 'gif', 'bmp'];
    if ($.inArray($(this).val().split('.').pop().toLowerCase(), fileExtension) === -1) {
        $(this).val('');
        let message = $('#allowedFormats').val();
        alert(message + " : " + fileExtension.join(', '));
    }
});