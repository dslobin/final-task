// change file input title
$(".custom-file-input").on("change", function () {
    let fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});

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

// disabling form submissions if there are invalid fields
(function() {
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        let forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        let validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

// set user role
$('.userRole option')
    .removeAttr('selected')
    .filter('[value=${requestScope.user.userRole}]')
    .attr('selected', true);