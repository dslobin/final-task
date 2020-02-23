// set date picker restrictions
$('body').on('focus', ".serviceDatePicker", function () {
    let currentDay = getDate();
    let currentMonth = getMonth();
    let currentYear = getFullYear();
    const DAY_ORDER_INTERVAL = 14;
    let day = currentDay + DAY_ORDER_INTERVAL;
    $(this).datepicker({
        minDate: 0, // can't pick anything previous to today
        maxDate: new Date(day, currentMonth, currentYear)
    }, {dateFormat: "dd/mm/yy"});
});

// set time picker restrictions
$('body').on('focus', ".serviceTimePicker", function () {
    $(this).timepicker({
        timeFormat: 'HH:mm:ss', // 24 hour with leading 0, minutes and seconds with leading 0
        minHour: 9,
        maxHour: 20,
        interval: 15 // 15 minutes
    });
});