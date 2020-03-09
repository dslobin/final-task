/**
 * Sets limits on service order dates.
 * The minimum date is today, the maximum is the current date plus seven days
 */
$(function () {
    let today = new Date();
    let month = today.getMonth() + 1;
    let day = today.getDate();
    let year = today.getFullYear();
    if (month < 10) {
        month = '0' + month.toString();
    }
    if (day < 10) {
        day = '0' + day.toString();
    }
    let minDate = year + '-' + month + '-' + day;
    day = (today.getDate() + 7).toString();
    let maxDate = year + '-' + month + '-' + day;
    $('#serviceDate').attr({
        'min': minDate,
        'max': maxDate
    });
});

/**
 * Sets limits on service order time.
 */
const MIN_TIME = '09:00';
const MAX_TIME = '20:00';

$(function () {
    $('#serviceTime').attr({
        'min': MIN_TIME,
        'max': MAX_TIME
    });
});

/**
 * Checks the correctness of the time of ordering the service, if the time is entered incorrectly,
 * resets the selected time and prohibits sending the form
 */
$('#orderForm').on('submit', function () {
    let orderDate = new Date($('#serviceDate').val());
    let today = new Date();
    console.log(orderDate.getTime() === today.getTime());
    if (orderDate.getHours() <= (today.getHours() + 2)) {
        let message = $('#timeRestriction').val();
        alert(message);
        $('#serviceTime').val('');
        return false;
    }
});