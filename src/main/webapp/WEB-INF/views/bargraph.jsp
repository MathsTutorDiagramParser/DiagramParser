<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
		<meta charset="UTF-8" />
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src = "http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.6.3/fabric.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }

            /* Modal Content */
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 20%;
            }

            /* The Close Button */
            .close {
                color: #aaaaaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
	</head>
    <body>

    <div id="myModal" class="modal">

        <!-- Modal content -->

        <div class="modal-content">
            <span class="close">&times;</span>
            <label>Set Scale</label>
            <form id="scaling">
                <div class="form-group">
                    <input type="number" class="form-control" name="firstValue" placeholder=" Enter First value" maxlength="5" id="first"><br>
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" name="lastValue" placeholder="Enter Last Value" maxlength="5" id="last"><br>
                </div>
                <div class="form-group">
                    <input type="number" class="form-control" name="range" placeholder="Enter the Scale" maxlength="5" id="range"><br>
                </div>
                <input type="Button" class="btn btn-primary" value="Submit" id="submit1" onclick="submitForm()">
            </form>
        </div>
    </div>

    <div class="w3-content" style="max-width:1564px;height:30px;margin-top: 70px;" align="center">
        1.	ළමුන් 20 දෙනෙකු පරීක්ෂණයකදී ලබාගත් ලකුණු පහත දැක්වේ. වගුවේ දැක්වෙන පරිදි මෙම ලකුණු සමුහගත කර ඇත. මෙම වගුවට ජලරේඛකය නිර්මාණය කරන්න.
        <table style="width: 60%; border: thin;color: darkgray">
            <tr>
                <td>පන්ති  පාන්තරය</td>
                <td>50-60</td>
                <td>60-70</td>
                <td>70-80</td>
                <td>80-90</td>
            </tr>
            <tr>
                <td>සංඛ්‍යාතය</td>
                <td>15</td>
                <td>30</td>
                <td>10</td>
                <td>5</td>
            </tr>
        </table>
    </div>

    <div align="center">
    <div style="margin-top: 50px">

        </div>
        <div >
            <canvas id="myCanvas" width = 970 height = 520 style=":position:absolute" >
                <label style="position:relative;left:100px;top:0">0</label>
                <label>0</label>
            </canvas>
        </div>
    </div>
        <br>
        <div align="center">
            <button class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;" id = "setXScale">X Scale</button>
            <button class="btn btn-primary" style="font-family: Aharoni; font-weight: bold;" id = "setYScale">Y Scale</button>
            <!--          <button id = "getValue">Enter Values</button>-->
            <input class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;" type="Button" value="X Axis label" id="submitX" onclick=" xLabel()">
            <input class="btn btn-primary" style="font-family: Aharoni; font-weight: bold;"type="Button" value="Y Axis label" id="submitY" onclick=" yLabel()">
            <button class="btn btn-warning" style="font-family: Aharoni; font-weight: bold;" id = "drawGraph">Draw</button>
            <button class="btn btn-warning" style="font-family: Aharoni; font-weight: bold;" id = "drawRectangle">Bar</button>
            <button class="btn btn-info" style="font-family: Aharoni; font-weight: bold;" id ="undo">Undo</button>
            <button class="btn btn-info" style="font-family: Aharoni; font-weight: bold;" id="clearPlane">Clear Plane</button>
            <br>
            <br>
            <div align="center">
            <button class="btn btn-success" style="font-family: Aharoni; font-weight: bold;" id = "save">Grade</button>
            </div>
        </div>
    <div id="marksheet" align="center">
        <div class="w3-content w3-padding" style="width:1000px; border: solid; border-width: thin" >
            <!-- About Section -->
            <div style="margin-top: 10px" id="about">
                <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16" align="center"> Mark Sheet</h3>
            </div>
            <div class="w3-border-bottom w3-border-light-grey"> Qusetion :  <span id="question_type"> Histogram </span> </div>
            <div class="w3-border-bottom w3-border-light-grey"> Overall FeedBack : <span id="overall_feedback"> </span>  </div>
            <div class="w3-border-bottom w3-border-light-grey"> Total Mark : <span id="total_Mark"> </span>  ${total_Mark} Out of <span id="out_of_total_Mark"> </span> </div>
            <div  id="dvTable" style="padding-bottom: 20px"></div>

        </div>
    </div>










         <script type="text/javascript">
             document.getElementById("marksheet").style.visibility = 'hidden' ;

             var canvas = new fabric.Canvas('myCanvas', {
              isDrawingMode : false,
              selectable : false,
            });

// xList1 stores the cutting lines of x axis

        // rectangle Declaration
        var drawRectangle = false;
        var xList1=[];
        var xList2=[];
        var yList1=[];
        var yList2=[];
        var xList = [];
        var horizontalLines=[];
        var verticalLines = [];
        var textValues =[];
        var yValues=[];

             function xLabel(){
                 canvas.add(new fabric.IText('Enter X Axis label here', {
                     //fontFamily: 'Sinhala',
                     width: 480,
                     id: 'textArea',
                     left: 700,
                     top:475,
                     fontSize: 20
                 }));
             }

             function yLabel(){
                 canvas.add(new fabric.IText('Enter Y Axis label here', {
                     //fontFamily: 'Sinhala',
                     width: 500,
                     id: 'textArea',
                     left:30,
                     top:0,
                     bottom:15,
                     fontSize: 20
                 }));
             }

        function submitForm(){

            if(yScalePressed){
                if(yList1.length!=0){
                    for(var y in yList1){
                        canvas.remove(yList1[y]);
                    }
                    for(var y in yList2){
                        canvas.remove(yList2[y]);
                    }
                    for(var y in verticalLines){
                        canvas.remove(verticalLines[y]);
                    }

                yList1 = [];
                yList2 = [];
                verticalLines=[];
                }
                autoMarkYAxis();


            }if(xScalePressed){
                if(xList1.length!=0){
                    for(var x in xList1){
                        canvas.remove(xList1[x]);

                    }
                    for(var x in xList2){
                        canvas.remove(xList2[x]);
                    }
                     for(var x in xList){
                        console.log(xList[x]);
                    }
                    for(var x in horizontalLines){
                        canvas.remove(horizontalLines[x]);
                    }

                xList1 = [];
                xList2 = [];
                xList = [];
                horizontalLines=[];
                }

                autoMarkXAxis();
            }


        }

        function clearForm(){
            var frm = document.getElementById('scaling');
//              frm.submit(); // Submit
              frm.reset();  // Reset
              return false; // Prevent page refresh

        }
        var xScalePressed = false;
        var yScalePressed = false;
        var drawPressed = false;
        var text_Xclicked=false;
        var text_Yclicked=false;


        var xScaleButton = document.getElementById('setXScale');
        xScaleButton.onclick = function() {
            xScalePressed = true;
            modal.style.display = "block";
        }
        var yScaleButton = document.getElementById('setYScale');
        yScaleButton.onclick = function() {
            yScalePressed= true;
            modal.style.display = "block";
        }

        var drawButton = document.getElementById('drawGraph');
        var drawRectangleButton = document.getElementById('drawRectangle');
        drawRectangleButton.onclick = function() {
            drawRectangle= true;
        }
        drawButton.onclick = function() {
            drawPressed= true;

             if(xList1.length!=0 & yList1.length!=0){
               drawLineFunc();
            }
        }

        var undoButton = document.getElementById('undo');
        undoButton.onclick = function() {
            undo();
     }

            var clearButton = document.getElementById('clearPlane');
        clearButton.onclick = function() {
            clearPlane();
     }

        var firstY ;
        var lastY ;
        var rangeY ;
        var scaleY;
        var firstX ;
        var lastX ;
        var rangeX ;
        var scaleX;
        var zeroEntereted = false;


        function autoMarkYAxis(){
            firstY = document.getElementById('first').value;
            lastY = document.getElementById('last').value;
            rangeY = document.getElementById('range').value;
            var i = (lastY-firstY)/rangeY
            scaleY = 400/i;
            if(firstY==0){
                for(count = 0 ;count<=Math.floor(i);count++){
                var y = 440 - count*scaleY;
                var yVal = parseInt(firstY) + count*rangeY;
                drawCuttingLineInY(y,yVal.toString());
                zeroEntereted=true;
            }

            }else{
                  for(count = 0 ;count<=Math.floor(i);count++){
                var y = 420 - count*scaleY;
                var yVal = parseInt(firstY) + count*rangeY;
                drawCuttingLineInY(y,yVal.toString());
            }

            }

            yScalePressed=false;
            clearForm();
            modal.style.display = "none";
        }

        function autoMarkXAxis(){
            firstX = document.getElementById('first').value;
            lastX = document.getElementById('last').value;
            rangeX = document.getElementById('range').value;
            var i = (lastX-firstX)/rangeX
            scaleX = 720/i;
            if(firstX==0 ){
                for(count = 0 ;count<=Math.floor(i);count++){
                var x = parseInt(100) + count*scaleX;
                xList.push(x);

                var xVal = parseInt(firstX) + count*rangeX;

                drawCuttingLineInX(x,xVal.toString());
                zeroEntereted=true;
            }

            }else{
                for(count = 0 ;count<=Math.floor(i);count++){
                var x = parseInt(120) + count*scaleX;
                xList.push(x);

                var xVal = parseInt(firstX) + count*rangeX;

                drawCuttingLineInX(x,xVal.toString());
            }

            }

            xScalePressed=false;
            clearForm();
            modal.style.display = "none";
        }




        function drawCuttingLineInX(x,valueX){
            var xCut = new fabric.Line([x,450 , x, 440], {
                strokeWidth: 2,
                fill: 'black',
                stroke: 'black',

              });

            canvas.add(xCut);
            xList1.push(xCut);

            var hroizontelLine = new fabric.Line([x, 450, x, 20], {

                strokeDashArray: [5, 5],
                stroke: 'black',
                lockMovementX: true,
                lockMovementY: true,
                lockRotaion: true,
            });
                canvas.add(hroizontelLine);
                horizontalLines.push(hroizontelLine);
               var xVal= new fabric.Text(valueX, {
//                  fontFamily: 'arial black',
                  fontSize : 15,
                  left: x,
                  top: 455 ,
                });
            canvas.add(xVal);
            xList2.push(xVal);
        }


           function drawCuttingLineInY(y,valueY){
            var yCut = new fabric.Line([105, y, 93, y], {
                strokeWidth: 2,
                fill: 'black',
                stroke: 'black',

              });
               canvas.add(yCut);
               yList1.push(yCut);

            var verticalLine = new fabric.Line([100, y, 840, y], {

                strokeDashArray: [5, 5],
                stroke: 'black',
                lockMovementX: true,
                lockMovementY: true,
                lockRotaion: true,
            });
                canvas.add(verticalLine);
                verticalLines.push(verticalLine);
                var yVal =new fabric.Text(valueY, {
                  fontSize : 15,
                  left: 50,
                  top: y-10 ,
                });
               canvas.add(yVal);
               yList2.push(yVal);
        }


        function drawAxis(){

            var unit;
            var tick,text, st;

            //drawing x and y axises


            var xAxis = new fabric.Line([100, 440, 840, 440], {
                strokeWidth: 2,
                fill: 'black',
                stroke: 'black',
               originX: 'center',
                originY: 'center',

              });
            var yAxis = new fabric.Line([100, 20, 100, 440],{
              strokeWidth: 2,
              fill: 'black',
              stroke: 'black',

            });
            canvas.add(xAxis);
            canvas.add(yAxis);

        }



        var drawLine=false;
        var line = null;
        var isDown = false;

    canvas.on('mouse:down', function(o){
    var pointer = canvas.getPointer(o.e);
    if (drawRectangle) {
        	var box = new fabric.Rect({
        		left: pointer.x,
        		top: pointer.y,
        		fill: '#264d73',
        		width: 70,
        		height: 300,
        		hasBorders: true,
        		strokeWidth: 2.0,
        		stroke: 'black'
        	});

        	canvas.add(box);
        	drawRectangle = false;
        }

        if (drawLine){
            isDown = true;

            var pointer = canvas.getPointer(o.e);
            var points = [pointer.x, pointer.y, pointer.x, pointer.y]
            line = new fabric.Line(points, {
                strokeWidth: 5,
                fill: 'black',
                stroke: 'black',
                originX: 'center',
                originY: 'center',
                selectable:false,
                hasBorders:false,
                hasControls:false,
            });
            canvas.add(line);
        }

    });

    canvas.on('mouse:move', function(o){
        if(drawLine){
            if(isDown){
                var pointer = canvas.getPointer(o.e);
                line.set({x2:pointer.x, y2:pointer.y});
                canvas.renderAll();
            }
        }
    });

             canvas.on('mouse:move', function(o){
                 if (text_Xclicked) {
                     canvas.add(new fabric.IText('Enter X Axis label here', {
                         //fontFamily: 'Sinhala',
                         width: 480,
                         id: 'textArea',
                         left: 680,
                         top:480,
                         fontSize: 20
                     }));
                     text_Xclicked = false;
                 }

                 if (text_Yclicked) {
                     canvas.add(new fabric.IText('Enter Y Axis label here', {
                         //fontFamily: 'Sinhala',
                         width: 500,
                         id: 'textArea',
                         left:30,
                         top:0,
                         fontSize: 20
                     }));
                     text_Yclicked = false;
                 }

             });


             canvas.on('mouse:up', function(o){
        if(drawLine){
            isDown = false;
            var pointer = canvas.getPointer(o.e);
            line.setCoords();
        }
    });

    function drawLineFunc(){
        canvas.deactivateAll().renderAll();
        drawLine=true;

        var canvas_objects = canvas._objects;
        if(canvas_objects.length !== 0){
            for(i=0;i<canvas_objects.length;i++){
                var obj=canvas_objects[i];
                obj.selectable=false;
                obj.hasControls=false;
            }
        }
    }

        function undo(){
        canvas.deactivateAll().renderAll();
        drawLine = false;

        var canvas_objects = canvas._objects;
        if(canvas_objects.length !== 0){
            for(i=0;i<canvas_objects.length;i++){
                var obj=canvas_objects[i];
                obj.selectable=false;
                obj.hasControls=false;
            }
            var last = canvas_objects[canvas_objects.length -1]; //Get last object
            canvas.remove(last);
            canvas.renderAll();
        }

    }

    function clearPlane(){
        canvas.clear();
        drawAxis();
    }

             $('#save').click(function (e){

                 var svg = canvas.toSVG();
                 fabric.log(canvas.toSVG());
                 document.getElementById("save").textContent = "Evaluating..." ;
                 $.ajax({
                     url: 'http://localhost:8080/DiagramEvaluation/grade',
                     type: 'POST',
                     data: {
                         answer: svg,
                         diagramType : "HISTOGRAM"
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



             drawAxis();






        </script>
    <script>
        // Get the modal
        var modal = document.getElementById('myModal');

        // Get the button that opens the modal
        var btn = document.getElementById("myBtn");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks the button, open the modal


        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>

    </body>

</html>


