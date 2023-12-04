<%@page import="javax.ejb.EJB"%>
<%@page import="java.util.List"%>
<%@page import="Menu.MenuFacade"%>
<%@page import="Menu.Menu"%>
<%@ include file="master.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .container {
        display: flex;
        justify-content: center;
        align-items: flex-start;
        height: 100vh; /* Set container height to occupy the entire page */
    }

    .container form {
        flex: 1 0 50%; /* Use flex property to fill available space with a minimum of one-third width */
        width: 95%;
        padding: 30px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .container table {
        flex: 1; /* Use flex property to fill available space */
        width: 10%;
        padding: 30px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .container h1 {
        margin-top: 0;
        font-size: 32px;
        color: #333;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
        letter-spacing: 2px;
        font-size: 24px;
        margin-bottom: 10px;
        text-align: center;
    }

    .container form input[type="text"],
    .container form input[type="float"],
    .container form textarea {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 3px;
    }

    .container form select {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 3px;
        background-color: #fff;
    }

    .container form input[type="submit"] {
        width: 100%;
        padding: 10px;
        background-color: #4CAF50;
        color: #fff;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    .container table {
        border-collapse: collapse;
        width: 100%;
    }

    .container table th,
    .container table td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    .container table th {
        background-color: #f2f2f2;
    }


    .property-listing {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 100%;
    }

    /* Additional styles to horizontally align the headers */
    .property-listing h1,
    .property-listing table {
        width: 100%;
    }

    .property-listing th {
        text-align: center;
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

    .container #saleForm {
        display:block;
    }

    .container #rentalForm {
        display:none;
        overflow: hidden;
    }

    #left-side{
        padding-top:30px;
        display:inline-block;
        width:33%;
    }

    #messageLabelText {
        color: red;
        color: ${messageColor};
    }
</style>
<script>
    document.title = "Add Menu Item";
    document.getElementById('AMSLabel').style.display = 'none';
    document.getElementById('BVPHLabel').style.display = 'none';
    document.getElementById('SMBLabel').style.display = 'none';
    document.getElementById('SMSLabel').style.display = 'none';
    document.getElementById('SFBALabel').style.display = 'none';
    document.getElementById('loginLabel').style.display = 'none';

    // Retrieve the value from the query parameter
    var uNameLabelText = getQueryParam('uNameLabel2');

    // Set the value as the text content of the label element
    document.getElementById('uNameLabel2').textContent = uNameLabelText;

    document.addEventListener('DOMContentLoaded', function () {
        const buyerButton = document.getElementById('saleButton');
        const sellerButton = document.getElementById('rentalButton');
        const buyerForm = document.getElementById('saleForm');
        const sellerForm = document.getElementById('rentalForm');


        buyerButton.addEventListener('click', function () {
            buyerForm.style.display = 'block';
            sellerForm.style.display = 'none';
            //document.getElementById('pSaleType').value = 'Sale';
        });

        sellerButton.addEventListener('click', function () {
            buyerForm.style.display = 'none';
            sellerForm.style.display = 'block';
            //document.getElementById('pSaleType').value = 'Rent';
        });
    });

    // Example: Smooth scroll to anchor links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();

            document.querySelector(this.getAttribute('href')).scrollIntoView({
                behavior: 'smooth'
            });
        });
    });

    // Example: Show/hide additional labels on hover
    const hoverDiv = document.getElementById('hoverDiv');
    hoverDiv.addEventListener('mouseover', function () {
        hoverDiv.classList.add('hovered');
    });
    hoverDiv.addEventListener('mouseout', function () {
        hoverDiv.classList.remove('hovered');
    });

// Function to retrieve the value from the query parameter
    function getQueryParam(name) {
        name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
        var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
        var results = regex.exec(location.search);
        return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
    }

// Function to load the property data into the table
    function loadMenuTable() {
        fetch('LoadMenuTable')
                .then(response => response.text())
                .then(data => {
                    const menuTableBody = document.getElementById('menuTableBody');
                    menuTableBody.innerHTML = data;
                })
                .catch(error => {
                    console.error('Error loading menu table:', error);
                });
    }

// Call the function to load the property table when the page loads
    window.addEventListener('DOMContentLoaded', loadMenuTable);


</script>
<main>

    <div class="container">
        <div id=left-side>
            <h1>Add Menu</h1>
            <form class="form-container" id="saleForm" action="CreateMenu" method="POST" enctype="multipart/form-data">
                <input type="text" name="menuName" placeholder="Name of Item">
                <br>
                <input type="float" name="price" placeholder="Price">
                <br>
                <input type="submit" value="List Menu">
                <br>
                <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
            </form>

            <div class="menu-listing">
                <h1>Menu List</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody id="menuTableBody">
                        <!-- Property data will be dynamically loaded here -->
                    </tbody>
                </table>
            </div>
        </div>
</main>
