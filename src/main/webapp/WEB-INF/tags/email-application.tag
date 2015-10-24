<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="container">
    <hr>
    <div class="row centered text-center">
        <div class="col-sm-12 visible-lg visible-sm visible-md">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <input type="email" class="form-control signup-form-control email"
                           placeholder="<fmt:message key="landing.message.enteremail"/>">
                </div>
                <button type="submit" class="btn btn-warning btn-lg btn-register"><fmt:message
                        key="landing.message.letmeregister"/></button>
            </form>
        </div>
        <div class="col-xs-12 visible-xs">
            <form class="form-inline" role="form">
                <div class="col-xs-12">
                    <div class="form-group">
                        <input type="email" class="auto-margin form-control signup-form-control email"
                               placeholder="<fmt:message key="landing.message.enteremail"/>">
                    </div>
                </div>
                <div class="col-xs-12 text-center">
                    <button type="submit" class="btn btn-warning btn-lg btn-register"><fmt:message key="landing.message.letmeregister"/></button>
                </div>

            </form>
        </div>
    </div>

</div>