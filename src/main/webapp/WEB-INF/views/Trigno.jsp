<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE HTML>
<%@page contentType = "text/html;charset = UTF-8" language = "java" %>
<%@page isELIgnored = "false" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
  <title>ouragan</title>
  <link rel="shortcut icon" href="resources/img/logo.jpg">
  <meta charset="utf-8"/>
  <link rel="stylesheet" href="./css/bootstrap.min.css">
  <!--link rel="stylesheet" href="editor.css"-->

  <!-- Optional theme -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="./css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="resources/style.css">
  <style>
    body {
      margin: 0px;
      padding: 0px;
    }
  </style>
  <script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
  <script type="text/javascript" src="https://www.google.com/jsapi"></script>
  <script type="text/javascript" src = "http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.6.3/fabric.min.js"></script>
</head>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top" style="margin-bottom: 50px" >
  <div class="w3-bar w3-white w3-wide w3-padding w3-card-2" align="center">
    <b>Trignometric diagram </b>
    <!-- Float links to the right. Hide them on small screens -->
  </div>
</div>
<div style="margin-top: 50px;"align="center">
  <div id='c1'>
    <canvas id="myCanvas" width="800" height="400" style="border:1px solid #000000;"></canvas>
  </div>

  <div class="col-lg-10" align="center">
    <button class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;"  id="drawCircle">Circle</button>
    <button class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;"  id="drawLine">Arrow</button>
    <button class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;"  id="drawBranch">Line</button>
    <button class="btn btn-warning" style="font-family: Aharoni; font-weight: bold;"  id="editBranch">Edit Line</button>
    <button class="btn btn-warning" style="font-family: Aharoni; font-weight: bold;" id="enterText">Text Label</button>
    <button class="btn btn-warning" style="font-family: Aharoni; font-weight: bold;"  id="drawing-mode" class="btn btn-info" style="display:none;">Colour</button>
    <div style="display: none;" id="drawing-mode-options">
      <label for="drawing-color">Line color:</label>
      <input type="color" value="#005E7A" id="drawing-color"><br>
    </div>
    <button class="btn btn-primary" style="font-family: Aharoni; font-weight: bold;"  id="undo">Undo</button>
    <!--button id="delete">Delete</button-->
    <button class="btn btn-primary" style="font-family: Aharoni; font-weight: bold;"  id="clearPlane">Clear Plane</button>
    <button class="btn btn-success" style="font-family: Aharoni; font-weight: bold;"  id ="save">Save</button>
  </div>

  <div class="w3-content" style="max-width:1564px ;background-color: lightgrey" align="center">
    <h4 style="color: blue"> Example Qusetions </h4>
    <div style="width: 800px; height: 200px">
      <img src="resources/img/trigQ.png" width="75%">
    </div>

    <h4 style="color: blue"> Instruction To Draw </h4>
    <div style="margin-left: 300px" align="left">
      * Use the Line option to draw the lines and copy the diagram given in the question.
      <br>
      * DO NOT use the RECTANGLE option to draw.
      <br>
      * Mark the labels for angles and sides using the TEXT LABEL option
      <br>
      <br>
    </div>
  </div>

</div>
<script type="text/javascript">
    function onLoad(){
        var options = {
            sourceLanguage: google.elements.transliteration.LanguageCode.ENGLISH,
            destinationLanguage: [google.elements.transliteration.LanguageCode.SINHALESE],
            transliterationEnabled: true
        };
        var control = google.elements.transliteration.TransliterationControl(options);
        control.makeTransliteratable(['textArea']);
    }
</script>

<script type="text/javascript">
    var canvas = new fabric.Canvas('myCanvas', {
        isDrawingMode : false,
        selectable : false,
    });

    canvas.selection = false;
    var circle_clicked = false, label_clicked = false, text_clicked = false, complete_set = false, branch_clicked = false;
    var isDown = false, isMoving = false;
    var originX, originY, startX, startY, lastX, lastY;
    var line, arrow, dot, dot_left, dot_top, branch;
    var drawingModeEl = document.getElementById('drawing-mode'),
        drawingOptionsEl = document.getElementById('drawing-mode-options'),
        drawingColorEl = document.getElementById('drawing-color'),
        drawingLineWidthEl = 5;

    drawingModeEl.onclick = function() {
        canvas.isDrawingMode = !canvas.isDrawingMode;
        if (canvas.isDrawingMode) {
            drawingModeEl.innerHTML = 'Hide Colour';
            drawingOptionsEl.style.display = '';
        }
        else {
            drawingModeEl.innerHTML = 'Colour';
            drawingOptionsEl.style.display = 'none';
        }
    }


    drawingColorEl.onchange = function() {
        canvas.freeDrawingBrush.color = drawingColorEl.value;
    };

    if (canvas.freeDrawingBrush) {
        canvas.freeDrawingBrush.color = drawingColorEl.value;
        canvas.freeDrawingBrush.width = drawingLineWidthEl;
    }

    //this one works on delete. But will have to test some more
    // $('#delete').click(function (){
    //   canvas.getActiveObject().remove();
    // })

    $('#editBranch').click(function (){
        var objects = canvas._objects;
        branch_clicked = false;
        if (objects.length != 0) {
            for (var i = 0; i < objects.length; i++) {
                var object = objects[i];
                if (object.id === "branch") {
                    object.set({
                        selectable: true,
                        hasControls: true
                    });
                }
            }
        }
    })

    $('#drawBranch').click(function (){
        branch_clicked = true;
        circle_clicked = false;
        label_clicked = false;
        text_clicked = false;
        complete_set = false;
    });

    $('#drawCircle').click(function (e){
        circle_clicked = true;
        label_clicked = false;
        text_clicked = false;
        complete_set = false;
        branch_clicked = false;
    });

    $('#drawcompleteSet').click(function (){
        complete_set = true;
        circle_clicked = false;
        text_clicked = false;
        label_clicked = false;
        branch_clicked = false;
    });

    $('#drawLine').click(function (e){
        label_clicked = true;
        circle_clicked = false;
        text_clicked = false;
        complete_set = false;
        branch_clicked = false;
    });

    $('#enterText').click(function (e){
        text_clicked = true;
        circle_clicked = false;
        label_clicked = false;
        complete_set = false;
        branch_clicked = false;
    });

    $('#save').click(function (e){
        var svg = canvas.toSVG();
        fabric.log(svg);

        $.ajax({
            crossDomain: true,
            url: 'http://mathstutordiagrams.projects.mrt.ac.lk:8080/mathsTutor/saveTrig',
            type: 'POST',
            data: {
                answer: svg,
            },
            success: function(){alert('Saved Successfully.');}
        });
        //sending the svg to the server
        //07.09 - Commented for testing
      /*$.ajax({
       crossDomain: true,
       url: 'http://127.0.0.1:8000/svg/',
       type: 'POST',
       data: {
       'str': svg,
       },
       success: function(){alert('PUT completed');}
       });*/
    });

    $('#undo').click(function (e){
        var canvas_objects = canvas._objects;
        if(canvas_objects.length !== 0){
            var last = canvas_objects[canvas_objects.length -1]; //Get last object

            //this is to undo an arrow in a single step. Otherwise, it would take three undo's
            if (last.customType == 'arrow' || last.customType == 'completedArrow') {
                var arrowline = canvas_objects[canvas_objects.length - 2];
                var arrowhead = canvas_objects[canvas_objects.length - 3];
                canvas.remove(last);
                canvas.remove(arrowline);
                canvas.remove(arrowhead);
            }
            else{
                canvas.remove(last);
            }

            canvas.renderAll();
        }
    });

    $('#clearPlane').click(function (e){
        canvas.clear();
    });

    canvas.on('mouse:down', function(o){

        isDown = true;
        var pointer = canvas.getPointer(o.e);

        //creating a set
        if (circle_clicked) {
            label_clicked = false;
            originX = pointer.x;
            originY = pointer.y;
            var circle = new fabric.Circle({
                left: pointer.x,
                top: pointer.y,
                radius: 100,
                strokeWidth: 2,
                fill: "transparent",
                stroke: 'black',
                selectable: true,
                originX: 'center',
                originY: 'center'
            });
            canvas.add(circle);
            circle_clicked = false;
        }

        //creating an arrow
        if (label_clicked) {
            var points = [pointer.x, pointer.y, pointer.x, pointer.y];
            startX = points[0];
            startY = points[1];

            //This for loop makes all the other objects in the canvas immobile while the drawing of the arrow.
            for (var i = 0; i < canvas._objects.length; i++) {
                var object = canvas._objects[i];
                object.set({
                    lockMovementX: true,
                    lockMovementY: true
                });
            }

            addArrow(points);
        }

        //adding text labels
        if (text_clicked) {
            canvas.add(new fabric.IText('Enter your label here', {
                //fontFamily: 'Sinhala',
                width: 500,
                id: 'textArea',
                left: pointer.x,
                top: pointer.y,
                fontSize: 20
            }));
            text_clicked = false;
        }

        if (complete_set) {
            var box = new fabric.Rect({
                left: pointer.x,
                top: pointer.y,
                fill: 'transparent',
                width: 400,
                height: 300,
                hasBorders: true,
                strokeWidth: 0.5,
                stroke: 'black'
            });

            canvas.add(box);
            complete_set = false;
        }

        //creating branches. This is for tree diagrams
        if (branch_clicked) {
            points = [pointer.x, pointer.y, pointer.x, pointer.y];
            branch = new fabric.Line(points, {
                strokeWidth: 5,
                fill: 'black',
                stroke: 'black',
                originX: 'center',
                originY: 'center',
                selectable:false,
                hasBorders:false,
                hasControls:false,
                id: 'branch'
            });
            canvas.add(branch);
        }
    });

    canvas.on('mouse:move', function(o){

        isMoving = true;
        var pointer = canvas.getPointer(o.e);
        if (!isDown) return;

        if (label_clicked) {

            var points = [startX, startY, pointer.x, pointer.y];

            //this is to remove the complete arrow. i.e, arrow head, line, and the arrow end
            for (var i = 0; i < 3; i++) {
                canvas.remove(getElementByMyId('arrow'))
            }

            for (var i = 0; i < canvas._objects.length; i++) {
                var object = canvas._objects[i];
                object.set({
                    lockMovementX: true,
                    lockMovementY: true
                });
            }

            addArrow(points);
            //line.set({x2:pointer.x, y2:pointer.y});
        }

        //for tree diagrams
        if (branch_clicked) {
            branch.set({x2:pointer.x, y2:pointer.y});
            canvas.renderAll();
        }

    });

    canvas.on('mouse:up', function(o){
        isDown = false;
        isMoving = false;
        var pointer = canvas.getPointer(o.e);
        lastX = pointer.x;
        lastY = pointer.y;

        var points = [startX, startY, lastX, lastY];
        if (label_clicked) {

            label_clicked = false;
            for (var i = 0; i < 3; i++) {
                canvas.remove(getElementByMyId('arrow'));
            }

            for (var i = 0; i < canvas._objects.length; i++) {
                var object = canvas._objects[i];
                object.set({
                    lockScalingX: true,
                    lockMovementY: true
                });
            }

            points = [startX, startY, lastX, lastY];

            addArrow(points);

            //This for loop enables the movement of the objects after the drawing of the arrow.
            for (var i = 0; i < canvas._objects.length; i++) {
                var object = canvas._objects[i];
                object.set({
                    lockMovementX: false,
                    lockMovementY: false
                });
            }
        }

        //for tree diagram
        if (branch_clicked) {
            var pointer = canvas.getPointer(o.e);
            branch.setCoords();
            //branch.set('selectable', true);
        }
    });


    function getElementByMyId(ID){
        var object = null;
        var objects = canvas.getObjects();
        for (var i = 0, len = canvas.size(); i < len; i++) {
            if (objects[i].customType&& objects[i].customType === ID) {
                object = objects[i];
                break;
            }
        }
        return object;
    }


    function addArrow(points){

        line = new fabric.Line(points, {
            strokeWidth: 2,
            stroke: 'black',
            fill: 'black',
            originX: 'center',
            originY: 'center',
            lockScalingX: true,
            lockScalingY: true,
            selectable: true,
            hasControls:true
        });

        var centerX = (line.x1 + line.x2)/2,
            centerY = (line.y1 + line.y2)/2;
        deltaX = line.left - centerX,
            deltaY = line.top - centerY;

        arrow = new fabric.Triangle({
            left: line.get('x1') + deltaX,
            top: line.get('y1') + deltaY,
            originX: 'center',
            originY: 'center',
            hasBorders: false,
            hasControls: true,
            lockScalingX: true,
            lockScalingY: true,
            lockRotation: true,
            pointType: 'arrow_start',
            angle: calcArrowAngle(),
            width: 10,
            height: 10,
            fill: '#000'
        });

        arrow.line = line;

        dot = new fabric.Circle({
            left: line.get('x2') + deltaX,
            top: line.get('y2') + deltaY,
            radius: 2,
            stroke: '#000',
            strokeWidth: 3,
            originX: 'center',
            originY: 'center',
            hasBorders: false,
            hasControls: true,
            lockScalingX: true,
            lockScalingY: true,
            lockRotation: true,
            pointType: 'arrow_end',
            fill: '#000'
        });
        dot.line = line;

        if (isDown) {
            line.customType = arrow.customType = dot.customType = 'arrow';
        }
        else	{
            line.customType = arrow.customType = dot.customType = 'completedArrow';
        }
        line.dot = arrow.dot = dot;
        line.arrow = dot.arrow = arrow;
        canvas.add(line, arrow, dot);


        function moveEnd(obj){
            var p = obj, x1, y1, x2, y2;

            if (obj.pointType === 'arrow_end') {
                obj.line.set('x2', obj.get('left'));
                obj.line.set('y2', obj.get('top'));
            } else{
                obj.line.set('x1', obj.get('left'));
                obj.line.set('y1', obj.get('top'));
            }

            obj.line._setWidthHeight();

            x1 = obj.line.get('x1');
            y1 = obj.line.get('y1');
            x2 = obj.line.get('x2');
            y2 = obj.line.get('y2');

            angle = calcArrowAngle(x1, y1, x2, y2);

            if (obj.pointType === 'arrow_end') {
                obj.arrow.set('angle', angle - 90);
            } else{
                obj.set('angle', angle - 90);
            }
            obj.line.setCoords();
            canvas.renderAll();
        }

        function moveLine(){
            var oldCenterX = (line.x1 + line.x2)/2,
                oldCenterY = (line.y1 + line.y2)/2,
                deltaX = line.left - oldCenterX,
                deltaY = line.top - oldCenterY;

            line.arrow.set({
                'left': line.x1 + deltaX,
                'top': line.y1 + deltaY
            }).setCoords();

            line.dot.set({
                'left': line.x2 + deltaX,
                'top': line.y2 + deltaY
            }).setCoords();

            line.set({
                'x1': line.x1 + deltaX,
                'y1': line.y1 + deltaY,
                'x2': line.x2 + deltaX,
                'y2': line.y2 + deltaY
            });

            line.set({
                'left': (line.x1 + line.x2) / 2,
                'top': (line.y1 + line.y2) / 2
            });
        }

        arrow.on('moving', function(){
            moveEnd(arrow);
        });

        dot.on('moving', function(){
            moveEnd(dot);
        });

        line.on('moving', function(){
            moveLine();
        });

        // label_clicked = false;
    }

    function calcArrowAngle(x1, y1, x2, y2){
        var angle = 0, x, y;

        x = x2 - x1;
        y = y2 - y1;

        if (x === 0) {
            angle = (y === 0) ? 0 : (y > 0) ? Math.PI /2 : Math.PI *3 /2;
        } else if (y === 0) {
            angle = (x > 0) ? 0 : Math.PI;
        } else {
            angle = (x < 0) ? Math.atan(y/x) + Math.PI : (y < 0) ? Math.atan(y/x) + (2*Math.PI) :Math.atan(y/x);
        }

        return ((angle*180/Math.PI));
    }

    function createLabel(){

        var dot = new fabric.Circle({
            fill: 'black',
            radius: 8,
            originX: 'center',
            originY: 'center',
            selectable: false
        });
        label_clicked = true;
    }

</script>
</body>
</html>