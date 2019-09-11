<%@tag description="tag for displaying model" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" scope="session" class="beans.ModelBean" />
<canvas id="mycanvas" width="640" height="480">Your browser does not support HTML5 canvas.</canvas>
<script>
    <c:choose>
        <c:when test="${cookie.containsKey('theme')}">
            var color = "#1d68da";
            <c:if test="${cookie.theme.value == 'green'}">var color = "#184a20";</c:if>
            <c:if test="${cookie.theme.value == 'purple'}">var color = "#372f8e";</c:if>
        </c:when>
        <c:otherwise>
            var color = "#1d68da";
        </c:otherwise>
    </c:choose>

    var cameraYaw = 0;
    var cameraPitch = 0;

    var cx = (${model.model.minX} + ${model.model.maxX}) / 2;
    var cy = (${model.model.minY} + ${model.model.maxY}) / 2;
    var cz = (${model.model.minZ} + ${model.model.maxZ}) / 2;
    var r = Math.sqrt(
            Math.pow(${model.model.maxX} - cx, 2) +
            Math.pow(${model.model.maxY} - cy, 2) +
            Math.pow(${model.model.maxZ} - cz, 2)
    );

    class Vertex {
        constructor(x, y, z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    class Face {
        constructor(v1, v2, v3) {
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }
    }

    var vertices = [
    <c:forEach items="${model.model.vertices}" var="v">new Vertex((${v.getX()} - cx)/r, (${v.getY()} - cy)/r, (${v.getZ()} - cz)/r),</c:forEach>
    ];

    var transformedVertices = [];
    for(i = 0; i < vertices.length; i++){
        transformedVertices.push(new Vertex(vertices[i].x, vertices[i].y, vertices[i].z));
    }

    var faces = [
    <c:forEach items="${model.model.faces}" var="f"><c:if test="${!f.isInvalid()}">new Face(${f.getV1()}, ${f.getV2()}, ${f.getV3()}),</c:if></c:forEach>
    ];

    var isMouseDown = false;
    var lastMouseX = 0;
    var lastMouseY = 0;

    function onMouseDown(event){
        isMouseDown = true;
        lastMouseX = event.screenX;
        lastMouseY = event.screenY;
    }

    function onMouseUp(event){
        isMouseDown = false;
    }

    function onMouseMove(event){
        if(isMouseDown === true){
            var dx = event.screenX - lastMouseX;
            var dy = event.screenY - lastMouseY;
            cameraYaw = cameraYaw + dx / 100.0;
            cameraPitch = cameraPitch + dy / 100.0;
            if(cameraPitch > (Math.PI / 2)) cameraPitch = (Math.PI / 2);
            if(cameraPitch < (-Math.PI / 2)) cameraPitch = (-Math.PI / 2);
            lastMouseX = event.screenX;
            lastMouseY = event.screenY;
            draw();
        }
    }
    
    var c = document.getElementById("mycanvas");
    c.addEventListener("mousedown", onMouseDown, false);
    c.addEventListener("mouseup", onMouseUp, false);
    c.addEventListener("mousemove", onMouseMove, false);
    var g = c.getContext("2d");
    draw();

    function doTransform(){
        for(i = 0; i < vertices.length; i++){
            transform(vertices[i], transformedVertices[i]);
        }
    }

    function draw(){
        doTransform();
        g.fillStyle = "#ffffff";
        g.fillRect(0,0,c.width,c.height);
        g.strokeStyle = color;
        g.stroke(); 
        g.beginPath();
        for(i = 0; i < faces.length; i++){
            drawFace(faces[i]);
        }
        g.stroke();
        console.log("draws");
    }

    function drawFace(f){
        var v1 = transformedVertices[f.v1];
        var v2 = transformedVertices[f.v2];
        var v3 = transformedVertices[f.v3];
        g.moveTo(v1.x, v1.y);
        g.lineTo(v2.x, v2.y);
        g.lineTo(v3.x, v3.y);
        g.lineTo(v1.x, v1.y);
    }

    function transform(v, vdst){
        rotateY(v, vdst, cameraYaw);
        rotateX(vdst, vdst, cameraPitch);
        scale(vdst, vdst, 240, -240, 240);
        move(vdst, vdst, 320, 240, 0);
    }

    function move(v, vdst, dx, dy, dz){
        var nx = v.x + dx;
        var ny = v.y + dy;
        var nz = v.z + dz;
        vdst.x = nx;
        vdst.y = ny;
        vdst.z = nz;
    }

    function scale(v, vdst, sx, sy, sz){
        var nx = v.x * sx;
        var ny = v.y * sy;
        var nz = v.z * sz;
        vdst.x = nx;
        vdst.y = ny;
        vdst.z = nz;
    }

    function rotateY(v, vdst, angle){
        var nx = v.x * Math.cos(angle) + v.z * Math.sin(angle);
        var ny = v.y;
        var nz = -1 * v.x * Math.sin(angle) + v.z * Math.cos(angle);
        vdst.x = nx;
        vdst.y = ny;
        vdst.z = nz;
    }

    function rotateX(v, vdst, angle){
        var nx = v.x;
        var ny = v.y * Math.cos(angle) - v.z * Math.sin(angle);
        var nz = v.y * Math.sin(angle) + v.z * Math.cos(angle);
        vdst.x = nx;
        vdst.y = ny;
        vdst.z = nz;
    }
</script>