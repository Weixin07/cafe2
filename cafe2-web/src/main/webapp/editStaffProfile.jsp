<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-image: url('images/skylineBG.jpg'); /* Replace 'path/to/your/background.jpg' with the actual path to your background image */
                background-size: cover;
                background-repeat: no-repeat;
            }
            
            .container {
                background-color: #fff;
                padding: 20px;
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
            }
            
            .container form input[type="text"],
            .container form input[type="number"],
            .container form input[type="password"] {
                width: 200px;
                padding: 10px;
                margin-bottom: 15px;
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
                margin-bottom: 20px;
            }
            
            .container a {
                text-align: center;
                margin-top: 20px;
                color: #007bff;
                text-decoration: none;
                font-size: 14px;
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
                color: ${messageColor};
            }
        </style>
    </head>
    <body>
        <img id="back-image" src="images/door.png" alt="Back">
    
        <form hidden id="BackLabel"  action="Home" method="POST"> 
                <input id="submitEditProfile" type="submit" value="Back" />
        </form>
        <div class="container">
            <h1>Edit Your Profile</h1>
            <form action="EditStaffProfile" method="POST">
                <input type="text" name="stEditEmail" value="${uNameLabelText}" readonly>
                <br>
                <input type="text" name="stEditName" placeholder="Name" value="${nameLabelText}">
                <br>
                <input type="number" name="stEditAge" placeholder="Age" value="${ageLabelText}">
                <br>
                <input type="text" name="stEditPhone" placeholder="Phone No." value="${phNoLabelText}">
                <br>
                <input type="text" name="stEditAddress" placeholder="Address" value="${addressLabelText}">
                <br>
                <input type="password" name="stEditCurrentPass" placeholder="Current Password">
                <br>
                <input type="password" name="stEditNewPass" placeholder="New Password">
                <br>
                <input type="password" name="stEditConfNewPass" placeholder="Confirm New Password">
                <br>
                <input type="submit" value="Edit Profile">
            </form>
            <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
        </div>
        
    <script>
        // Add a click event listener to the image
        var image = document.getElementById('back-image'); // Replace with your actual image ID
        image.addEventListener('click', function() {
            // When the image is clicked, trigger the form submission
            var submitButton = document.getElementById('submitEditProfile');
            submitButton.click();
        });
    </script>
    </body>
    
   
</html>
