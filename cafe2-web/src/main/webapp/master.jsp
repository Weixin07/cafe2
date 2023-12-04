<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cafeteria System</title>
   
    <link rel="stylesheet" href="style/style.css">
    <script src="js/script.js"></script>
    
</head>
<body>
    
<nav>
    <div class="nav-left">
        <form action="Home" method="POST"> 
            <input type="submit" value="Home" class="custom-button"/>
        </form>
    </div>

    <div class="nav-right">
        
        <form style="display:inline-block" ${hidePropertyListingsLabel ? 'style="display:none;"' : ''} action="LoadPropertyListings" method="POST"> 
                <input type="submit" value="Menu" class="custom-button"/>
        </form>
              &nbsp; &nbsp; &nbsp; 
        <a id="loginLabel" ${hideLoginLabel ? 'style="display:none;"' : ''} href="login.jsp">Login</a>
        <label for="uNameLabel" id="uNameLabel" ${hideNameLabel ? 'style="display:none;"' : ''}>${uNameLabelText}</label>
        <label for="uNameLabel2" id="uNameLabel2" ></label>
        
        <div class="additional-labels" id="hoverDiv">
        <form id="EPLabel" ${hideEPLabel ? 'style="display:none;"' : ''} action="EditProfile" method="POST"> 
                <input type="submit" value="Edit Profile" class="custom-button" />
        </form>
                
        <form id="BVPHLabel" ${hideBVPHLabel ? 'style="display:none;"' : ''} action="ViewReceipt" method="POST"> 
                <input type="submit" value="Purchase History" class="custom-button" />
        </form>
 
   
        <form id="AMSLabel" ${hideAMSLabel ? 'style="display:none;"' : ''} action="StaffManagement" method="POST"> 
                <input type="submit" value="Manage Staff" class="custom-button" />
        </form>
          
         <form id="SMRELabel" ${hideSMRELabel ? 'style="display:none;"' : ''} action="ManageRealEstate" method="POST"> 
                <input type="submit" value="Manage Real Estate" class="custom-button" />
        </form>
                
        <form id="SMBLabel" ${hideSMBLabel ? 'style="display:none;"' : ''} action="ManageBuyer" method="POST"> 
                <input type="submit" value="Manage Buyers" class="custom-button" />
        </form>
                
        <form id="SMSLabel" ${hideSMSLabel ? 'style="display:none;"' : ''} action="ManageSeller" method="POST"> 
                <input type="submit" value="Manage Sellers" class="custom-button" />
        </form>  
                
        <form id="SFBALabel" ${hideSFBALabel ? 'style="display:none;"' : ''} action="FeedbackAnalysis" method="POST"> 
            <input type="submit" value="Feedback Analysis" class="custom-button" />
        </form>
                
        <form id="SAOLabel" ${hideSAOLabel ? 'style="display:none;"' : ''} action="ViewOffer" method="POST"> 
                <input type="submit" value="View Property Bids" class="custom-button" />
        </form>
          
        <form id="SVPHLabel" ${hideSEPHLabel ? 'style="display:none;"' : ''} action="ViewReceipt" method="POST"> 
                <input type="submit" value="View Sale Records" class="custom-button" />
        </form>
                
          
          <form action="Logout" method="POST">  
            <input type="submit" value="Log-Out" class="custom-button" />
        </form>
        </div>
    </div>
        
        
</nav>

    <main>
        <!-- Content of individual pages will be inserted here -->
    </main>
    
<footer id="mainFoot">
    <!-- Add footer content -->
    &copy; Hope you liked it!
</footer>
</body>
</html>


