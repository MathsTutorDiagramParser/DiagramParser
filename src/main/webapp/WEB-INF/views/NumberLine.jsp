<!DOCTYPE HTML>
<%--<%@page contentType = "text/html;charset = UTF-8" language = "java" %>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
  <title>ouragan</title>
  <link rel="shortcut icon" href="resources/img/logo.jpg">
  <meta charset="utf-8"/>
  <style>
    body {
      margin: 0px;
      padding: 0px;
    }
  </style>
  <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src = "http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.6.3/fabric.min.js"></script>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="resources/style.css">
</head>

<body >

<!-- Navbar (sit on top) -->
<div class="w3-top" style="margin-bottom: 100px">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card-2" align="center">
    <b>Number line </b>
    <!-- Float links to the right. Hide them on small screens -->
  </div>
</div>

<div id="main_div" style="max-width: 1250px">
  <div class="w3-content" style="max-width:1564px;height:30px;margin-top: 70px;" align="center">

    <h4> 01)     x > -1  අසමානතාවයේ විසදුම් සංඛ්‍යා රේඛාව මත නිරූපනය කරන්න.</h4>

  </div>
  <div  align="center" >
    <canvas id="myCanvas" width = 650 height = 400 style="border:1px solid #000000;margin-top: 50px;" ></canvas>
  </div>

  <div style="margin-top: 60px;" align="center">
    <INPUT type = "Button" value = '&#9899'  onclick="createArrowWithFilledDot()"/>
    <INPUT type = "Button" value = '&#9898'  onclick="createArrowWithDot()"/>
    <INPUT type = "Button" value = "Mark Line"  onclick="createMarkLine()"/>
    <INPUT type = "Button" value = "Undo" onclick="undo()"/>
    <INPUT type = "Button" value = "Clear Plane" onclick="clearPlane()"/>
  </div>

  <div align="center" style="margin-top: 10px;margin-bottom: 10px">
    <button id ="save" style="width:200px;background-color: forestgreen;">Grade</button>
  </div>

  <div id="marksheet" >
    <div class="w3-content w3-padding" style="width:1000px; border: solid; border-width: thin" >
      <!-- About Section -->
      <div style="margin-top: 10px" id="about">
        <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16" align="center"> Mark Sheet</h3>
      </div>
      <div class="w3-border-bottom w3-border-light-grey"> Qusetion :  <span id="question_type"> Number Line </span> </div>
      <div class="w3-border-bottom w3-border-light-grey"> Overall FeedBack : <span id="overall_feedback"> </span>  </div>
      <div class="w3-border-bottom w3-border-light-grey"> Total Mark : <span id="total_Mark"> </span>  ${total_Mark} Out of <span id="out_of_total_Mark"> </span> </div>
      <div  id="dvTable" style="padding-bottom: 20px"></div>

    </div>
  </div>
</div>



<script type="text/javascript">

    document.getElementById("marksheet").style.visibility = 'hidden' ;

    var canvas = new fabric.Canvas("myCanvas",{
        isDrawingMode : false,
        selectable : false,
    });

    canvas.selection = false;
    var filled_dot_clicked = false, non_filled_dot_clicked = false, mark_line_clicked = false;
    var start_of_line = false;
    var isMoving = false;
    var pointX=0;
    var pointY=0;

    var scale = 40;
    var numberLineY = 200;
    var numberLinePos = [50, numberLineY, 600, numberLineY]; // **if numberLinePos[1],numberLinePos[3] should be equal.
    var dotPlaced = false; // check dot placed before draw arrow

    function drawNumLine(){
        //main line
        var numLine = new fabric.Line(numberLinePos, {
            strokeWidth: 2,
            fill: 'black',
            stroke: 'black',
            originX: 'center',
            originY: 'center',
            targetFindTolerance: true,
            selectable: false
        });



        //small scale line
        sclPos = 100 - scale;
        unit = -5;
        while(sclPos > 50 && sclPos < 600){
            tick = new fabric.Line([sclPos, 190, sclPos, 210], {
                selectable: false,
                strokeWidth: 2,
                fill: 'black',
                stroke: 'black',
                originX: 'center',
                originY: 'center',
                targetFindTolerance: true
            });
            st = unit.toString() + '';
            text = new fabric.IText(st, {
                fontFamily: 'Arial',
                left: sclPos,
                top: 213,
                fontSize: 10,
                fontWeight: 'bold',
                textAlign: "center",
                fill: "#000000"
            });
            canvas.add(tick);
            canvas.add(text);
            unit += 1;
            sclPos = sclPos + scale;
        }

        //arrow heads
        var triLeft = new fabric.Triangle({
            angle: 270,
            fill: 'black',
            top: 205,
            left: 40,
            height: 10,
            width: 10,
            selectable: false
        });

        var triRight = new fabric.Triangle({
            angle: 90,
            fill: 'black',
            top: 195,
            left: 605,
            height: 10,
            width: 10,
            selectable: false
        });

        canvas.add(numLine);
        canvas.add(triLeft);
        canvas.add(triRight);


    }

    function drawMarkedLine(markLinePos) {
        var markLine = new fabric.Line(markLinePos, {
            strokeWidth: 5,
            stroke: '#7db9e8',
            originX: 'center',
            originY: 'center',
            targetFindTolerance: true,
            selectable: false,
            hasControls: false,
            hasRotatingPoint: false,
            hoverCursor: 'default',
            lockScalingY: true
        });
        canvas.add(markLine);
    }

    //draw number Line
    drawNumLine();


    //Draw Arrow
    function createLineArrow(points,side) {
        var line = new fabric.Line(points, {
            strokeWidth: 5,
            stroke: '#7db9e8',
            originX: 'center',
            originY: 'center',
            hoverCursor: 'default',
            selectable: true,
        });

        //create arrow head
        var headLength = 15,

            x1 = points[0],
            y1 = points[1],
            x2 = points[2],
            y2 = points[3],

            dx = x2 - x1,
            dy = y2 - y1,

            angle = Math.atan2(dy, dx);

        angle *= 180 / Math.PI;
        angle += 90;
        // temporary removed the triangle in markrd line
        var triangle = new fabric.Triangle({
            // angle: angle,
            fill: '#207cca',
            top: y2,
            left: x2,
            height: headLength,
            width: headLength,
            originX: 'center',
            originY: 'center',
            selectable: false
        });


        if (side == 'left'){
            var groupArrow = new fabric.Group([line],{left: points[0],top: points[1]-4, id: 'arrow', hasRotatingPoint: false, lockScalingY: true});
        }
        if (side == 'right'){
            var groupArrow = new fabric.Group([line],{right: points[2],top: points[3]-4, id: 'arrow', hasRotatingPoint: false, lockScalingY: true});
        }

        return groupArrow;

    }

    function createMarkLine() {
        filled_dot_clicked = false;
        non_filled_dot_clicked = false;
        mark_line_clicked = true;
        console.log("mark line");
    }

    //create arrow with dot (not filled)
    function createArrowWithDot(){

        filled_dot_clicked = false;
        non_filled_dot_clicked = true;
        mark_line_clicked = false;
        console.log("not filled dot");
    }

    //create arrow with filled dot
    function createArrowWithFilledDot(){
        filled_dot_clicked = true;
        non_filled_dot_clicked = false;
        mark_line_clicked = false;
        console.log("filled dot");
    }

    //reset All
    function clearPlane(){
        canvas.clear();
        drawNumLine();

    }
    //get object by custom id
    function getItemByMyID(myID) {
        var object = null,
            objects = canvas.getObjects();
        for (var i = 0, len = canvas.size(); i < len; i++) {
            if (objects[i].id&& objects[i].id=== myID) {
                object = objects[i];
                break;
            }
        }
        return object;
    };



    //set xy to dot
    function placeDot(x,y,fill,id){
        var dot = new fabric.Circle({
            strokeWidth: 2,
            stroke: 'blue',
            fill: fill,
            radius: 8,
            originX: 'center',
            originY: 'center',
            top : numberLinePos[1],
            left: x,
            id: id,
            selectable: false

        });
        return dot;
    }

    //check whether dot place is on the line
    function checkDotPlace(x,y){
        if(y < numberLinePos[1]+5 && y > numberLinePos[1]-5 && x<numberLinePos[2] && x>numberLinePos[0] ){
            return true;
        }
        else{
            return false;
        }
    }



    canvas.on( 'mouse:down', function(o) {

        var pointer = canvas.getPointer(o.e);
        //adding filled dot
        if (filled_dot_clicked) {

            pointX = pointer.x;
            pointY = pointer.y;
            //appear dot where click
            if(checkDotPlace(pointX,pointY)){
                filledDot = placeDot(pointX,pointY,'blue','filledDot');
                canvas.add(filledDot);
                dotPlaced = true;
                canvas.renderAll();
            }

            if(dotPlaced){
                dotPlaced = false;
//                    saveObject('arrow');
//                    saveObject('filledDot');
                canvas.renderAll();
            }
            else{
                dotPlaced = false;
            }

            filled_dot_clicked =false;

        }

        //creating branches. This is for tree diagrams
        if (non_filled_dot_clicked) {

            pointX = pointer.x;
            pointY = pointer.y;
            //appear dot where click
            if(checkDotPlace(pointX,pointY)){
                dot = placeDot(pointX,pointY,'transparent','dot');
                canvas.add(dot);
                dotPlaced = true;
                canvas.renderAll();
            }

            if (dotPlaced){
                dotPlaced = false;
//                saveObject('arrow');
//                saveObject('dot');
                canvas.renderAll();
            }
            else{
                dotPlaced = false;
            }

            non_filled_dot_clicked = false;
        }

        if(mark_line_clicked){
            point_X = pointer.x;
            point_Y = numberLineY;
            start_of_line = true;
        }

    });

    canvas.on('mouse:move', function(o){
        var pointer = canvas.getPointer(o.e);
        isMoving = true;

        //for tree diagrams
        if (mark_line_clicked) {
            if (start_of_line){
                //draw arrow to left
                if (pointX < pointer.x){
                    //remove older arrow
                    canvas.remove(getItemByMyID('arrow'));
                    canvas.add(createLineArrow([point_X, point_Y, pointer.x, point_Y],'left'));
                }
                else if( pointX != (pointer.x)){
                    //remove older arrow
                    canvas.remove(getItemByMyID('arrow'));
                    canvas.add(createLineArrow([point_X, point_Y, pointer.x, point_Y],'right'));
                }
            }
        }

    });

    canvas.on('mouse:up', function(o){

        var pointer = canvas.getPointer(o.e);
        isMoving = false;
        //for tree diagram
        if (mark_line_clicked) {
            start_of_line = false;
            if (start_of_line){
                //draw arrow to left
                if (point_X < pointer.x){
                    //remove older arrow
                    canvas.remove(getItemByMyID('arrow'));
                    canvas.add(drawMarkedLine([pointX, pointY, pointer.x, pointY]));
                }
                else if (pointX > pointer.x ){
                    //remove older arrow
                    canvas.remove(getItemByMyID('arrow'));
                    canvas.add(drawMarkedLine([pointX, pointY, pointer.x, pointY]));
                }
            }
            mark_line_clicked =false;
        }

    });

    $('#save').click(function (e){

        var svg = canvas.toSVG();
        document.getElementById("save").textContent = "Evaluating..." ;
        $.ajax({
            url: 'http://localhost:8080/DiargamEvaluation/grade',
            type: 'POST',
            data: {
                answer: svg,
                diagramType : "NUMBRELINE"
            },
            success: function(marksheet){
                alert("success");
                document.getElementById("total_Mark").textContent = marksheet.totalMark ;
                document.getElementById("out_of_total_Mark").textContent = marksheet.totalMark_gainMark ;
//                document.getElementById("overall_feedback").textContent = marksheet.feedback ;
                $('#overall_feedback').text(marksheet.feedback);
                var submarksheets = marksheet.subMarkSheets;

//                for (var i = 0; i < submarksheets.length; i++) {
//                    var tr='';
//                    tr = "<tr>"+
//                            "<td>" + submarksheets[i].QNo + "</td>" +
//                            "<td>" + submarksheets[i].totalMark + ' out Of ' + submarksheets[i].gainedMark+ "</td>" +
//                            "<td>" + submarksheets[i]. feedBack+ "</td> </tr> ";
//                    console.log(tr);
//                    document.getElementById("marksheet").append(tr);
//                }

                //Create a HTML Table element.
                var table = document.createElement("TABLE");

                hrow = table.insertRow(-1);
                var h1 = hrow.insertCell(-1);
                h1.innerHTML = "Sub Question";

                var h2 = hrow.insertCell(-1);
                h2.innerHTML = "Marks";

                var h3 = hrow.insertCell(-1);
                h3.innerHTML = "FeedBack";


                //Add the data rows.
                for (var i = 0; i < submarksheets.length; i++) {
                    row = table.insertRow(-1);

                    var cell1 = row.insertCell(-1);
                    cell1.innerHTML = i+1;

                    var cell2 = row.insertCell(-1);
                    cell2.innerHTML = submarksheets[i].totalMark + ' out Of ' + submarksheets[i].gainedMark;

                    var cell3 = row.insertCell(-1);
                    cell3.innerHTML = submarksheets[i]. feedBack;

                }

                var dvTable = document.getElementById("dvTable");
                dvTable.innerHTML = "";
                dvTable.appendChild(table);
                document.getElementById("marksheet").style.visibility = 'visible' ;
                document.getElementById("save").textContent = "Grade" ;

            },
            error: function() {
                alert("error");
            }
        });

    });

    function undo(){
        var canvas_objects = canvas._objects;
        if(canvas_objects.length > 31)
        {
            var last = canvas_objects[canvas_objects.length -1]; //Get last object
            canvas.remove(last);
            canvas.renderAll();
        }
    }



</script>
</body>
</html>