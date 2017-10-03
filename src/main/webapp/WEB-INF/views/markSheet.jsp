<%--
  Created by IntelliJ IDEA.
  User: Madhavi Ruwandika
  Date: 9/30/2017
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>ouragan</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/logo.jpg">
    <c:url value="/css/main.css" var="jstlCss" />
    <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src = "http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.6.3/fabric.min.js"></script>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
<body>


    <!-- Navbar (sit on top) -->
    <div class="w3-top">
        <div class="w3-bar w3-white w3-wide w3-padding w3-card-2">
            <a href="#home" class="w3-bar-item w3-button"> <b>TEAM OURAGAN </b> (e-learning) </a>
        </div>
    </div>

    <!-- Page content -->
    <div class="w3-content w3-padding" style="max-width:1564px">

        <!-- About Section -->
        <div class="w3-container w3-padding-32" id="about">
            <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16" align="center"> Mark Sheet</h3>
        </div>

        <div class="w3-border-bottom w3-border-light-grey"> Qusetion :  ${Question_type} </div>
        <div class="w3-border-bottom w3-border-light-grey"> Overall FeedBack :  ${overall_feedback} </div>
        <div class="w3-border-bottom w3-border-light-grey"> Total Mark :  ${total_Mark} Out of  ${out_of_total_Mark} </div>

        <%--<div>--%>
            <%--<canvas id="myCanvas" width = 800 height = 400 style="border:1px solid #000000;margin-top: 50px;margin-left:300px" ></canvas>--%>
        <%--</div>--%>

    </div>

    <div>

        <div style="padding-bottom: 20px">
            <table>
                <tr>
                    <th>Sub Question</th>
                    <th>Marks</th>
                    <th>FeedBack</th>
                </tr>

                <c:forEach items="${submarkSheets}" var="submarksheet">
                    <tr>
                        <td> <c:out value="${submarksheet.QNo}"/> </td>
                        <td> <c:out value="${submarksheet.totalMark}"/>  out Of  <c:out value="${submarksheet.gainedMark}"/> </td>
                        <td> <c:out value="${submarksheet.feedBack}"/> </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

</body>

<script type="text/javascript">
    var canvas = new fabric.Canvas("myCanvas",{
        selectable : false,
    });
    fabric.loadSVGFromString( ${answer} , function(objects, options) {
        var obj = fabric.util.groupSVGElements(objects, options);
        canvas.add(obj).renderAll();
    });


</script>

</html>