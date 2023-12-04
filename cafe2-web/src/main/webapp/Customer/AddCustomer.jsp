<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-image: url('images/skylineBG.jpg'); /* Replace 'images/background.jpg' with the path to your background image */
            background-size: cover;
            background-repeat: no-repeat;
        }
        
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        
        .container h1 {
            margin-top: 0;
            font-size: 32px;
            color: #333;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
            letter-spacing: 2px;
        }
        
        .container form {
            margin-top: 20px;
            text-align: center;
            overflow: hidden;
            transition: height 0.3s ease-in-out;
        }
        
        .container form select {
            width: 222px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 3px;
            background-color: #fff;
      }
        
        .container form input[type="text"],
        .container form input[type="password"],
        .container form input[type="tel"],
        .container form input[type="number"],
        .container form input[type="email"] {
            width: 200px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        
        
        .container form input[type="submit"] {
            width: 200px;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            
        }
        
        .container .slider-toggle {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        
        .container .slider-toggle label {
            background-color: #ccc;
            padding: 10px 20px;
            color: #fff;
            cursor: pointer;
            border-radius: 20px;
            transition: background-color 0.3s ease-in-out;
        }
        
        .container .slider-toggle input[type="radio"] {
            display: none;
        }
        
        .container .slider-toggle input[type="radio"]:checked + label {
            background-color: #4CAF50;
        }
        
        .container #buyerForm {
            height: auto;
        }
        
        .container #sellerForm {
            height: 0;
            overflow: hidden;
        }
        
        .container #sellerForm.visible {
            height: auto;
        }
        
        #back-image {
            position:absolute;
            top:10px;
            left:10px;
            width: 48px;
            height: 48px;/* Adjust the image size as needed */
        }
        
        #messageLabelText {
            color: red;
        }
    </style>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const buyerButton = document.getElementById('buyerButton');
            const sellerButton = document.getElementById('sellerButton');
            const buyerForm = document.getElementById('buyerForm');
            const sellerForm = document.getElementById('sellerForm');
            
            buyerButton.addEventListener('click', function() {
                buyerForm.style.height = 'auto';
                sellerForm.style.height = '0';
            });
            
            sellerButton.addEventListener('click', function() {
                buyerForm.style.height = '0';
                sellerForm.style.height = 'auto';
            });
        });
    </script>
</head>
<body>
    <a href='login.jsp'>
        <img id="back-image" src="images/door.png" alt="Back">
    </a>
    <div class="container">
        <h1>Sign-Up To Unreal Estate</h1>
        <div class="slider-toggle">
            <input type="radio" name="loginType" id="buyerButton" checked>
            <label for="buyerButton">Buyer</label>
            &nbsp;
            <input type="radio" name="loginType" id="sellerButton">
            <label for="sellerButton">Seller</label>
        </div>
        <form id="buyerForm" action="BuyerRegister" method="POST">
            <input type="email" name="bEmail" placeholder="Email">
            <br>
            <input type="password" name="bPass" placeholder="Password">
            <br>
            <input type="submit" value="Register As a Buyer">
        </form>
        <form  id="sellerForm" action="SellerRegister" method="POST">
            <input type="text" name="sName" placeholder="Full Name">
            <br>
            <input type="email" name="sEmail" placeholder="Email">
            <br>
            <input type="password" name="sPass" placeholder="Password">
            <br>
            <input type="tel" name="sPhoneNo" placeholder="Phone Number (+60)">
            <br>
            <input type="number" name="sAge" placeholder="Age">
            <br>
            <select name="sGender">
                <option value="" selected disabled hidden>Gender</option>
                <option value="M">Male</option>
                <option value="F">Female</option>
            </select>
            <br>
            <input type="text" name="sAddress" placeholder="Address">
            <br>
            <input type="submit" value="Register As a Seller">
        </form>
        <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
    </div>
</body>
</html>
