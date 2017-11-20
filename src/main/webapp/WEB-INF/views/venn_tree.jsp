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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <!--link rel="stylesheet" href="editor.css"-->

  <!-- Optional theme -->
  <link rel="stylesheet" href="./css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="resources/style.css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
    <h5> 01)A භාජනයේ කලු බෝල 3 ක් හා සුදු බෝල 2 ක් ද B  භාජනයේ කලු බෝල 4 ක් හා සුදු බෝල 3 ක් ද  ඇත. මෙම බෝල සියල්ලම තරමින් සමාන වේ.
      A භාජනයෙන් අහඹු ලෙස බෝලයක් ගෙන, එහි වර්ණය සළකුණු කර ගෙන, එය B භාජනයට දමනු ලැබේ. අනතුරුව B භාජනයෙන් අහඹු ලෙස බෝලයක් ගෙන
      එහි වර්ණය සලකුණු කර ගනු ලැබේ.

      </br>A භාජනයෙන් බොලයක් ගැනීම පළමුවන සිද්ධිය ලෙස ද B භාජනයෙන් බොලයක් ගැනීම දෙවන සිද්ධිය ලෙසද සලකා, මෙම සිද්ධි වලට අදාළ රුක් සටහන අදින්න.
    </h5>
  </div>


  <div id='c1' style="margin-top: 100px">
    <canvas id="myCanvas" width="800" height="400" style="border:1px solid #000000;"></canvas>
  </div>
  <br>

  <div align="center">
    <button class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;" id="drawBranch">Branch</button>
    <button class="btn btn-danger" style="font-family: Aharoni; font-weight: bold;" id="editBranch">Edit Branch</button>
    <button class="btn btn-warning" style="font-family: Aharoni; font-weight: bold;" id="enterText">Event Text Label</button>
    <button id="drawing-mode" class="btn btn-info" style = "display: none;">Colour</button>
    <div style="display: none;" id="drawing-mode-options">
      <label for="drawing-color">Line color:</label>
      <input type="color" value="#005E7A" id="drawing-color"><br>
    </div>
    <button class="btn btn-primary" style="font-family: Aharoni; font-weight: bold;" id="undo">Undo</button>
    <button class="btn btn-primary" style="font-family: Aharoni; font-weight: bold;" id="clearPlane">Clear Plane</button>
    <br>
    <br>
    <button class="btn btn-success" style="font-family: Aharoni; font-weight: bold;" id = "save">Grade</button>
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
    var branchXCoor;
    var branchYCoor;
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
        document.getElementById("save").textContent = "Evaluating..." ;
        $.ajax({
            url: 'http://localhost:8080/DiagramEvaluation/grade',
            type: 'POST',
            data: {
                answer: svg,
                diagramType : "TREEDIAGRAM"
            },
            success: function(marksheet){
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
                if(submarksheets !== null) {
                    for (var i = 0; i < submarksheets.length; i++) {
                        row = table.insertRow(-1);
                        var cell1 = row.insertCell(-1);
                        cell1.innerHTML = i + 1;
                        var cell2 = row.insertCell(-1);
                        cell2.innerHTML = submarksheets[i].totalMark + ' out Of ' + submarksheets[i].gainedMark;
                        var cell3 = row.insertCell(-1);
                        cell3.innerHTML = submarksheets[i].feedBack;
                    }
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
            branchXCoor = pointer.x;
            branchYCoor = pointer.y;
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
            var x = (pointer.x + branchXCoor)/2-50;
            var y;
            if(!(pointer.y === branchYCoor || pointer.x === branchXCoor)){

                if(pointer.y <= branchYCoor) {
                    y = (pointer.y + branchYCoor)/2-40;
                } else {
                    y = (pointer.y + branchYCoor)/2+10;
                }
                var probText = new fabric.IText('Probability', {
                    left: x,
                    top: y,
                    fontFamily: 'Bree Serif',
                    fontSize: 20,
                    cache: false
                });
                canvas.add(probText);

                var outText = new fabric.IText('Outcome', {
                    left: pointer.x,
                    top: pointer.y,
                    fontFamily: 'Bree Serif',
                    fontSize: 20,
                    cache: false
                });
                canvas.add(outText);
            }
            branch_clicked = false;
        }

    });


    canvas.on("text:editing:entered", clearText);

    function clearText(e) {
        if (e.target.type === "i-text") {
            if (e.target.text === "Enter text here") {
                e.target.text = "";
                canvas.renderAll();
            };
        }
    }


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