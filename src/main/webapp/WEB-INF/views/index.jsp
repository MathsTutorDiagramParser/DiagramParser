<%--
  Created by IntelliJ IDEA.
  User: Madhavi Ruwandika
  Date: 9/29/2017
  Time: 8:22 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>ouragan</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/logo.jpg">
    <c:url value="/css/main.css" var="jstlCss" />
</head>

<%--<head>--%>

    <%--<!-- Access the bootstrap Css like this,--%>
        <%--Spring boot will handle the resource mapping automcatically -->--%>
    <%--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />--%>

    <%--<!----%>
	<%--<spring:url value="/css/main.css" var="springCss" />--%>
	<%--<link href="${springCss}" rel="stylesheet" />--%>
	 <%---->--%>
    <%--<c:url value="/css/main.css" var="jstlCss" />--%>
    <%--<link href="${jstlCss}" rel="stylesheet" />--%>

<%--</head>--%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
<body>


<!-- Navbar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar w3-white w3-wide w3-padding w3-card-2">
        <a href="#home" class="w3-bar-item w3-button"> <b>TEAM OURAGAN </b> (e-learning) </a>
        <!-- Float links to the right. Hide them on small screens -->
    </div>
</div>

<!-- Header -->
<!--<header class="w3-display-container w3-content w3-wide" style="max-width:1500px;" id="home">-->
<!--<div class="w3-display-middle w3-margin-top w3-center">-->
<!--<h1 class="w3-xxlarge w3-text-white"><span class="w3-padding w3-black w3-opacity-min"><b>OURAGAN</b></span> <span class="w3-hide-small w3-text-light-grey">e-learning</span></h1>-->
<!--</div>-->
<!--</header>-->

<!-- Page content -->
<div class="w3-content w3-padding" style="max-width:1564px">

    <!-- About Section -->
    <div class="w3-container w3-padding-32" id="about">
        <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16" align="center"> Automatic assessment and feedback generation system for mathematical Diagrams</h3>
        <br>
        This is an ongoing final year project where we are creating a system for automatically assessing the students' answers which contains mathematical diagrams in Ordinary level examination in Sri Lanka.
        For now we are hoping to collect images of diagrams for testing.
        <br><br>
        We are kindly request you to draw diagrams in given 4 types of editors and save them.Please try to draw invalid diagrams as well as correct diagrams.
        <br><br>
    </div>
</div>

<!--Project Section-->
<!--<div class="w3-container " id="projects">-->
<!--<h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Diagram Types</h3>-->
<!--</div>-->

<div class="w3-row-padding" style="background-color: lightgrey" align="center">

    <div class="w3-col l3 m6 w3-margin-bottom w3-margin-top" id="d1">
        <a href="http://localhost:8080/mathsTutor/histogram">
        <%--<a href="http://mathstutordiagrams.projects.mrt.ac.lk:8080/DiargamEvaluation/histogram">--%>
            <div class="w3-display-container">
                <div class="w3-display-topleft w3-black w3-padding" align="center">Histogram</div>
                <img src="${pageContext.request.contextPath}/resources/img/histogram.jpg"  style="width:75%">
            </div>
        </a>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom  w3-margin-top" id="d2" >
        <%--<a href="http://mathstutordiagrams.projects.mrt.ac.lk:8080/DiargamEvaluation/numberLine">--%>
        <a href="http://localhost:8080/DiargamEvaluation/numberLine">
            <div class="w3-display-container">
                <div class="w3-display-topleft w3-black w3-padding" align="center">Numberline</div>
                <img src="${pageContext.request.contextPath}/resources/img/numberline.jpg"  style="width:75%">
            </div>
        </a>
    </div>

    <div class="w3-col l3 m6 w3-margin-bottom  w3-margin-top" id="d3">
        <a href="http://localhost:8080/mathsTutor/treeDiagram">
        <%--<a href="http://mathstutordiagrams.projects.mrt.ac.lk:8080/DiargamEvaluation/treeDiagram">--%>
            <div class="w3-display-container">
                <div class="w3-display-topleft w3-black w3-padding" align="center">Tree Diagram</div>
                <img src="${pageContext.request.contextPath}/resources/img/tree.jpg"  style="width:75%">
            </div>
        </a>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom  w3-margin-top" id="d4">
        <a href="http://localhost:8080/mathsTutor/TrignometricDiagram">
        <%--<a href="http://mathstutordiagrams.projects.mrt.ac.lk:8080/DiargamEvaluation/TrignometricDiagram">--%>
            <div class="w3-display-container">
                <div class="w3-display-topleft w3-black w3-padding" align="center">Trignometric Diagram</div>
                <img src="${pageContext.request.contextPath}/resources/img/trig.jpg"  style="width:75%">
            </div>
        </a>
    </div>

</div>

<div class="w3-row-padding w3-grayscale"></div>
<!--<footer class="w3-center w3-black w3-padding-16">-->
<!--<p> </p>-->
<!--</footer>-->



<!-- Add Google Maps -->
<script>
    function myMap()
    {
        myCenter=new google.maps.LatLng(41.878114, -87.629798);
        var mapOptions= {
            center:myCenter,
            zoom:12, scrollwheel: false, draggable: false,
            mapTypeId:google.maps.MapTypeId.ROADMAP
        };
        var map=new google.maps.Map(document.getElementById("googleMap"),mapOptions);

        var marker = new google.maps.Marker({
            position: myCenter,
        });
        marker.setMap(map);
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBu-916DdpKAjTmJNIgngS6HL_kDIKU0aU&callback=myMap"></script>
<!--
To use this code on your website, get a free API key from Google.
Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
-->

</body>

</html>