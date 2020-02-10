<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="../fragments/clientHeader.jsp"/>

<div class="col-md-8 order-md-1">
    <h4 class="mb-3">Sign up for repairs online</h4>
    <form class="needs-validation" novalidate>

        <!-- hidden input -->
        <input type="hidden" name="command" value="createOrder"/>
        <!-- /hidden input -->

        <div class="row">
            <div class="col-md-6 align-top">
                <h5 class="mb-3">Your personal details:</h5>

                <div class="mb-3">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        Valid first name is required.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                    <div class="invalid-feedback">
                        Valid last name is required.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="phoneNumber">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber" placeholder="+37529111-11-11" required>
                    <div class="invalid-feedback" style="width: 100%;">
                        Your phone number is required.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com">
                    <div class="invalid-feedback">
                        Please enter a valid email address.
                    </div>
                </div>

            </div>

            <div class="col-md-6 align-top">
                <h5 class="mb-3">Your car details:</h5>

                <div class="mb-3">
                    <label for="carModel">Car model</label>
                    <input type="text" class="form-control" id="carModel" placeholder="" required>
                    <div class="invalid-feedback">
                        Car model is required
                    </div>
                </div>

                <div class="mb-3">
                    <label for="serviceDatePicker">Desired service date</label>
                    <input type="date" class="form-control" id="serviceDatePicker" placeholder="" required>
                    <div class="invalid-feedback">
                        Desired service date is required
                    </div>
                </div>

                <div class="mb-3">
                    <label for="serviceTimePicker">Desired service time</label>
                    <input type="time" class="form-control" id="serviceTimePicker" placeholder="" required>
                    <div class="invalid-feedback">
                        Desired service time is required
                    </div>
                </div>

            </div>
        </div>

        <div class="mb-3">
            <label for="serviceDescription">Description of proposed services</label>
            <textarea class="form-control" id="serviceDescription" cols="40" rows="3" required></textarea>
            <div class="invalid-feedback">
                Service description is required
            </div>
        </div>

        <hr class="mb-4">
        <div class="custom-control custom-checkbox mb-3">
            <input type="checkbox" class="custom-control-input" id="dataProcessing" required>
            <label class="custom-control-label" for="dataProcessing">I consent to the processing of my data</label>
        </div>

        <button class="btn btn-primary btn-lg btn-block" type="submit">Send</button>
    </form>
</div>

<jsp:include page="../fragments/footer.jsp"/>

<!-- scripts -->
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.4.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.bundle.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
<!-- /scripts -->
</body>
</html>