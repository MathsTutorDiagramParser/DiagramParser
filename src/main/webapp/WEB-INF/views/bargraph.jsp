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
		<meta charset="UTF-8" />
		<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src = "http://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.6.3/fabric.min.js"></script>
	</head>
    
    <body>
        
         <div>
            <input id="xName" type=text size="30" style="border:2px solid" >       
        
        </div>
        <div >
            <canvas id="myCanvas" width = 970 height = 470 style=":position:absolute" >
                <label style="position:relative;left:100px;top:0">0</label>
                <label>0</label>
            
            </canvas>
            
            <input id="yName" type=text size="30" style="position:relative;left:600px;border:2px solid" > 
        </div>
        
        <div>
		 <button id = "setXScale">X Scale</button>
		  <button id = "setYScale">Y Scale</button>
<!--          <button id = "getValue">Enter Values</button>-->
	      <button id = "drawGraph">Draw</button>
            <button id = "drawRectangle">Bar</button>
	     
      	  <button id ="undo">Undo</button>
      	  
      	  <button id="clearPlane">Clear Plane</button>
            <button id = "save">Save</button>
        </div>
        

        
            <form id="AutoSetScale"  style="visibility:hidden" >
              First value:
              <input type="number" name="firstValue" id="first"><br>
              Last value:
              <input type="number" name="lastValue" id="last"><br>
              Scale:
              <input type="number" name="range" id="range"><br>
             <input type="Button" value="Submit" id="submit" onclick=" submitForm()">
        </form>
        

        
        
        


        
            
        
       
        
         <script type="text/javascript">
             
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
              var frm = document.getElementById('AutoSetScale');
//              frm.submit(); // Submit
              frm.reset();  // Reset
              return false; // Prevent page refresh
            
        }
        var xScalePressed = false;
        var yScalePressed = false;
        var drawPressed = false;
        
             
        var xScaleButton = document.getElementById('setXScale');
        xScaleButton.onclick = function() { 
            xScalePressed = true;
        document.getElementById('AutoSetScale').style.visibility='visible';   
        }
        
        var yScaleButton = document.getElementById('setYScale');
        yScaleButton.onclick = function() { 
            yScalePressed= true;
        document.getElementById('AutoSetScale').style.visibility='visible';   
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
            document.getElementById('AutoSetScale').style.visibility='hidden';          
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
            document.getElementById('AutoSetScale').style.visibility='hidden';          
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
        		fill: 'blue',
        		width: 70,
        		height: 300,
        		hasBorders: true,
        		strokeWidth: 2.5,
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
 
        
        function nameAxis(){
            var Xname = xName.value;
            var Yname = yName.value;
        
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
        fabric.log(canvas.toSVG());
      })
    
             
             
            drawAxis();
            nameAxis();
             
            

              
            

        </script>
    
    </body>

</html>
	
    
    