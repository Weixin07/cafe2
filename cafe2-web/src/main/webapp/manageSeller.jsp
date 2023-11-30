<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manage Seller</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html,
        body {
            height: 100%;
        }

        body {
            display: flex;
            flex-wrap: wrap;
            overflow: hidden;
            background-color: #111;
        }

        .quadrant {
            width: 50%;
            height: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
            transform-style: preserve-3d;
            perspective: 800px;
            background-color: #222;
            position: relative;
            transition: transform 0.5s, box-shadow 0.5s, background-color 0.5s;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }

        .quadrant:before {
            content: "";
            position: absolute;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.3);
            transform-origin: center;
            opacity: 1;
            transition: opacity 0.5s;
        }

        #quadrantC:before {
            top: 0;
            right: 0;
            box-shadow: -10px 10px 30px rgba(0, 0, 0, 0.6);
        }

        #quadrantR:before {
            top: 0;
            left: 0;
            box-shadow: 10px 10px 30px rgba(0, 0, 0, 0.6);
        }

        #quadrantU:before {
            bottom: 0;
            right: 0;
            box-shadow: -10px -10px 30px rgba(0, 0, 0, 0.6);
        }

        #quadrantD:before {
            bottom: 0;
            left: 0;
            box-shadow: 10px -10px 30px rgba(0, 0, 0, 0.6);
        }

        .quadrant:hover {
            transform: translateZ(30px);
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.6);
            background-color: #444;
        }

        .quadrant:hover:before {
            opacity: 0;
        }

        .label {
            font-family: Arial, sans-serif;
            font-size: 36px;
            font-weight: bold;
            color: #fff;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
            pointer-events: none;
        }

        #quadrantC {
            background-color: #00c853;
        }

        #quadrantR {
            background-color: #1565c0;
        }

        #quadrantU {
            background-color: #ffc400;
        }

        #quadrantD {
            background-color: #e53935;
        }
    </style>

    <script>
        function handleClick(quadrant) {
            switch (quadrant) {
                case 'A':
                    window.location.href = "approveSeller.jsp?uNameLabel2=" + encodeURIComponent(document.getElementById("uNameLabelText").textContent);
                    break;
                case 'S':
                    window.location.href = "searchSeller.jsp?uNameLabel2=" + encodeURIComponent(document.getElementById("uNameLabelText").textContent);
                    break;
                case 'E':
                    window.location.href = "editSeller.jsp?uNameLabel2=" + encodeURIComponent(document.getElementById("uNameLabelText").textContent);
                    break;
                case 'D':
                    window.location.href = "deleteSeller.jsp?uNameLabel2=" + encodeURIComponent(document.getElementById("uNameLabelText").textContent);
                    break;
            }
        }

        function changeText(elementId, newText) {
            const element = document.getElementById(elementId);
            element.textContent = newText;
        }
    </script>
</head>
<body>
    <a id="uNameLabelText" style="display: none;">${uNameLabelText}</a>
    <div id="quadrantC" class="quadrant" onmouseover="changeText('cText', 'Approve')" onmouseout="changeText('cText', 'A')" onclick="handleClick('A')">
        <span id="cText" class="label">A</span>
    </div>
    <div id="quadrantR" class="quadrant" onmouseover="changeText('rText', 'Search')" onmouseout="changeText('rText', 'S')" onclick="handleClick('S')">
        <span id="rText" class="label">S</span>
    </div>
    <div id="quadrantU" class="quadrant" onmouseover="changeText('uText', 'Edit')" onmouseout="changeText('uText', 'E')" onclick="handleClick('E')">
        <span id="uText" class="label">E</span>
    </div>
    <div id="quadrantD" class="quadrant" onmouseover="changeText('dText', 'Delete')" onmouseout="changeText('dText', 'D')" onclick="handleClick('D')">
        <span id="dText" class="label">D</span>
    </div>
</body>
</html>
