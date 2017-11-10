<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
<div class="w3-top" style="margin-bottom: 100px" align="center">
  <div class="w3-bar w3-white w3-wide w3-padding w3-card-2" align="center">
    <b>Tree Diagram </b>
    <!-- Float links to the right. Hide them on small screens -->
  </div>
</div>
<div style="margin-top: 50px;" align="center">

  <div class="w3-content" style="max-width:1564px;height:30px;margin-top: 70px;" align="left">
    <h4> 01) A භාජනයේ කලු බෝල 3 ක් හා සුදු බෝල 2 ක් ද B  භාජනයේ කලු බෝල 4 ක් හා සුදු බෝල 3 ක් ද  ඇත. මෙම බෝල සියල්ලම තරමින් සමාන වේ. A භාජනයෙන් අහඹු ලෙස බෝලයක් ගෙන, එහි වර්ණය සළකුණු කර ගෙන, එය B භාජනයට දමනු ලැබේ. අනතුරුව B භාජනයෙන් අහඹු ලෙස බෝලයක් ගෙන එහි වර්ණය සලකුණු කර ගනු ලැබේ.
      A භාජනයෙන් බොලයක් ගැනීම පළමුවන සිද්ධිය ලෙස ද B භාජනයෙන් බොලයක් ගැනීම දෙවන සිද්ධිය ලෙසද සලකා,
      <br>I.	මෙම සිද්ධි වලට අදාළ රුක් සටහන අදින්න
    </h4>
  </div>


  <div id='c1' style="padding-top: 100px">
    <canvas id="myCanvas" width="800" height="400" style="border:1px solid #000000;"></canvas>
  </div>

  <div class="col-lg-10">
    <button id="drawBranch">Branch</button>
    <button id="editBranch">Edit Branch</button>
    <button id="enterText">Text Label</button>
    <button id="drawing-mode" class="btn btn-info" style="display: none;">Colour</button>
    <div style="display: none;" id="drawing-mode-options">
      <label for="drawing-color">Line color:</label>
      <input type="color" value="#005E7A" id="drawing-color"><br>
    </div>
    <button id="undo">Undo</button>
    <button id="clearPlane">Clear Plane</button>

  </div>
  <div align="center" style="margin-top: 10px;margin-bottom: 20px">
    <button id ="save" style="width:200px;background-color: forestgreen;">Save</button>
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
    var label_clicked = false, text_clicked = false, branch_clicked = false;
    var isDown = false, isMoving = false;
    var branch;
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
        label_clicked = false;
        text_clicked = false;
    });


    $('#drawLine').click(function (e){
        label_clicked = true;
        text_clicked = false;
        branch_clicked = false;
    });

    $('#enterText').click(function (e){
        text_clicked = true;
        label_clicked = false;
        branch_clicked = false;
    });

    $('#save').click(function (e){
        var svg = canvas.toSVG();
        fabric.log(svg);

        var $this = $(this);
        $this.toggleClass('Grade');
        if($this.hasClass('Grade')){
            $this.text('Evaluating...');
        } else {
            $this.text('Grade');
        }

        $.ajax({
            crossDomain: true,
//            url: 'http://localhost:8080/mathsTutor/grade',
            url: 'http://localhost:8080/DiargamEvaluation/grade',
            type: 'POST',
            data: {
                answer: svg,
                diagramType : "TREEDIAGRAM"
            },
            success: function(page){
//                alert("answer Saved Successfully");
                $("html").empty();
                $("html").append(page);
            }
        });
    });

    $('#undo').click(function (e){
        var canvas_objects = canvas._objects;
        if(canvas_objects.length !== 0){
            var last = canvas_objects[canvas_objects.length -1]; //Get last object

            canvas.remove(last);

            canvas.renderAll();
        }
    });

    $('#clearPlane').click(function (e){
        canvas.clear();
    });

    canvas.on('mouse:down', function(o){
        isDown = true;
        var pointer = canvas.getPointer(o.e);

        //adding text labels
        if (text_clicked) {
            canvas.add(new fabric.IText('Enter text here', {
                //fontFamily: 'Sinhala',
                width: 500,
                id: 'textArea',
                left: pointer.x,
                top: pointer.y,
                fontSize: 20
            }));
            text_clicked = false;
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

        //for tree diagrams
        if (branch_clicked) {
            branch.set({x2:pointer.x, y2:pointer.y});
            canvas.renderAll();
        }

    });

    canvas.on('mouse:up', function(o){
        isDown = false;
        isMoving = false;
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