// disabling form submissions if there are invalid fields
(function () {
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        let forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        let validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

$('form[id="loginForm"]').validate({
    rules: {
        username: {
            required: true,
            maxLength: 30,
        },
        password: {
            required: true,
            minlength: 6,
            maxLength: 30,
        }
    },
    messages: {
        username: {
            required: 'This field is required',
            maxLength: 'Username must be no more than 30 characters'
        },
        password: {
            required: 'This field is required',
            minlength: 'Password must be at least 6 characters long',
            maxLength: 'Password must be no more than 30 characters'
        }
    },
    submitHandler: function (form) {
        form.submit();
    }
});