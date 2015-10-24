<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=7a47709620e13098e9ac9539b39ddf9a"></script>
    <script type="application/javascript">
        var ORDER_DELETE_CONFIRM_MSG = '<fmt:message key="orders.delete.confirm"/>';
        var ORDER_FORCE_DELETE_CONFIRM_MSG = '<fmt:message key="orders.delete.force.confirm"/>';
        var x, y;
        $.ajax({
            url: "http://api.map.baidu.com/location/ip?ak=7a47709620e13098e9ac9539b39ddf9a&coor=bd09ll",
            type: "GET",
            dataType: 'jsonp'
        }).done(function (r) {
            x = r.content.point.x;
            y = r.content.point.y;
        });
    </script>
</head>
<body class="home">


<div id="headerwrap" class="visible-sm visible-md visible-lg headerwrap">
    <div class="container">
        <div class="row text-center-xs">
            <div class="col-sm-8">
                <h1><fmt:message key="landing.message.main1"/>
                    <br><fmt:message key="landing.message.main2"/></h1>

                <form class="form-inline" role="form">
                    <div class="form-group">
                        <input type="email" class="form-control signup-form-control" id="exampleInputEmail1"
                               placeholder="<fmt:message key="landing.message.enteremail"/>">
                    </div>
                    <button type="submit" class="btn btn-warning btn-lg btn-register"><fmt:message
                            key="landing.message.letmeregister"/></button>
                </form>
            </div>
            <div class="col-sm-4">
                <img class="img-responsive auto-margin" src="<c:url value='/images/iphone_webapp.png'/>" alt="">
            </div>
        </div>
    </div>
</div>

<div id="headerwrap-xs" class="visible-xs headerwrap">
    <div class="container">
        <div class="row text-center">
            <div class="col-xs-12">
                <h1><fmt:message key="landing.message.main1"/>
                    <br><fmt:message key="landing.message.main2"/></h1>

                <form class="form-inline" role="form">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <input type="email" class="auto-margin form-control signup-form-control email"
                                   placeholder="<fmt:message key="landing.message.enteremail"/>">
                        </div>
                    </div>
                    <div class="col-xs-12 text-center">
                        <button type="submit" class="btn btn-warning btn-lg btn-register"><fmt:message
                                key="landing.message.letmeregister"/></button>
                    </div>

                </form>
            </div>
            <div class="col-sm-4 col-xs-12">
                <img class="img-responsive auto-margin" src="<c:url value='/images/iphone_webapp.png'/>" alt="">
            </div>
        </div>
    </div>
</div>
<!-- /headerwrap -->


<div class="container">
    <div class="row mt centered">
        <div class="col-lg-6 col-lg-offset-3 text-center">
            <h1><fmt:message key="landing.message.complain.1"/><br/>
                <fmt:message key="landing.message.complain.2"/></h1>

            <h3><fmt:message key="landing.message.complain.3"/><br/>
                <fmt:message key="landing.message.complain.4"/><br/>
                <fmt:message key="landing.message.complain.5"/>
            </h3>
        </div>
    </div>

    <div class="row mt centered text-center">
        <div class="col-lg-4 col-sm-6 text-center">
            <img src="<c:url value='/images/ser03.png'/>" width="180" alt="">
            <h4>1 - <fmt:message key="landing.step.1"/></h4>

            <p><fmt:message key="landing.step.1.detail"/></p>
        </div>
        <div class="col-lg-4 col-sm-6 text-center">
            <img src="<c:url value='/images/ipad-hand.png'/>" width="150" alt="">
            <h4>2 - <fmt:message key="landing.step.2"/></h4>

            <p><fmt:message key="landing.step.2.detail"/></p>
        </div>
        <div class="col-lg-4 col-sm-12 text-center">
            <img src="<c:url value='/images/hd10.jpg'/>" width="200" alt="">
            <h4>3 - <fmt:message key="landing.step.3"/></h4>

            <p><fmt:message key="landing.step.3.detail"/></p>
        </div>
    </div>
    <!-- /row -->
</div>

<t:email-application/>

<div class="container">
    <hr>
    <div class="row mt centered">
        <div class="col-xs-12 text-center">
            <h1><fmt:message key="landing.goodapp"/></h1>

            <h3><fmt:message key="landing.goodapp.message"/></h3>
        </div>
    </div>

    <div class="row mt centered">
        <div class="col-xs-12 text-center">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="<c:url value='/images/p01.png'/>" alt="">
                    </div>
                    <div class="item">
                        <img src="<c:url value='/images/p02.png'/>" alt="">
                    </div>
                    <div class="item">
                        <img src="<c:url value='/images/p03.png'/>" alt="">
                    </div>
                </div>
            </div>
        </div>
        <!-- /col-lg-8 -->

    </div>
</div>


<t:email-application/>
<div class="container">
    <hr>
    <div class="row mt centered">
        <div class="col-sm-12 text-center">
            <h1><fmt:message key="landing.thankyou"/></h1>

            <h3><fmt:message key="landing.thankyou.detail"/></h3>
        </div>
    </div>
    <div class="row mt centered">
        <div class="col-sm-1">&nbsp</div>
        <div class="col-sm-3 col-sm-offset-0 col-xs-offset-3 col-xs-6">
            <img class="img-circle" src="<c:url value='/images/pic1.jpg'/>" width="140" alt="">
            <h4><fmt:message key="landing.testimonial.name.1"/></h4>
            <p class="index-p"><fmt:message key="landing.testimonial.message.1"/></p>

        </div>
        <div class="col-sm-1 col-xs-2">&nbsp</div>
        <div class="col-sm-3 col-sm-offset-0 col-xs-offset-3 col-xs-6">
            <img class="img-circle" src="<c:url value='/images/IMG_0082.jpg'/>" width="140" alt="">
            <h4><fmt:message key="landing.testimonial.name.2"/></h4>
            <div class="index-p"><p><fmt:message key="landing.testimonial.message.2"/></p></div>

        </div>
        <div class="col-sm-1 col-xs-2">&nbsp</div>
        <div class="col-sm-3 col-sm-offset-0 col-xs-offset-3 col-xs-6">
            <img class="img-circle" src="<c:url value='/images/pic3.jpg'/>" width="140" alt="">
            <h4><fmt:message key="landing.testimonial.name.3"/></h4>
            <p class="index-p"><fmt:message key="landing.testimonial.message.3"/></p>

        </div>
    </div>
</div>

<t:email-application/>
</body>
