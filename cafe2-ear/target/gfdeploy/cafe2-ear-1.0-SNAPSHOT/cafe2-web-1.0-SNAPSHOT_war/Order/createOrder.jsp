<%@page import="javax.ejb.EJB"%>
<%@page import="java.util.List"%>
<%@page import="Property.PropertyFacade"%>
<%@page import="Property.Property"%>
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
        flex: 1 0 25%; /* Use flex property to fill available space with a minimum of one-third width */
        width: 75%;
        padding: 30px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    .container table {
        flex: 1; /* Use flex property to fill available space */
        width: 50%;
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
    .container form input[type="number"],
    .container form input[type="text"],
    .container form input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 3px;
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


    #messageLabelText {
        color: red;
        color: ${messageColor};
    }
</style>
<script>
    document.title = "Add Managing Order";

    document.getElementById('EPLabel').style.display = 'none';
    document.getElementById('SMBLabel').style.display = 'none';
    document.getElementById('SMSLabel').style.display = 'none';
    document.getElementById('SFBALabel').style.display = 'none';
    document.getElementById('BVPHLabel').style.display = 'none';
    document.getElementById('SMRELabel').style.display = 'none';
    document.getElementById('SVPHLabel').style.display = 'none';
    document.getElementById('SAOLabel').style.display = 'none';
    document.getElementById('loginLabel').style.display = 'none';


    // Retrieve the value from the query parameter
    var uNameLabelText = getQueryParam('uNameLabel2');

    // Set the value as the text content of the label element
    document.getElementById('uNameLabel2').textContent = uNameLabelText;


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
    function loadOrderTable() {
        fetch('LoadOrderTable')
                .then(response => response.text())
                .then(data => {
                    const propertyTableBody = document.getElementById('OrderTableBody');
                    propertyTableBody.innerHTML = data;
                })
                .catch(error => {
                    console.error('Error loading property table:', error);
                });
    }

// Call the function to load the property table when the page loads
    window.addEventListener('DOMContentLoaded', loadOrderTable);


</script>
<main>
    <div class="container">
        <form class="form-container" id="addOrder" action="AddOrder" method="POST">
            <h1>Add Order</h1>
            <input type="number" name="menuID" placeholder="Menu Item ID">
            <br>
            <input type="text" name="menuName" placeholder="Menu Item Name">
            <br>
            <input type="float" name="price" placeholder="Price">
            <br>
            <input type="number" name="quantity" placeholder="Quantity">
            <br>
            <input type="text" name="cEmail" placeholder="Customer Email">
            <br>
            <input type="text" name="ssUsername" placeholder="Stall Staff Username">
            <br>
            <input type="submit" value="Add Order Member">
            <br>
            <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
        </form>

        <div class="property-listing">
            <h1>Managing Order List</h1>
            <table>
                <thead>
                    <tr>
                        <th>Item ID</th>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Customer Email</th>
                        <th>Stall Staff</th>
                    </tr>
                </thead>
                <tbody id="OrderTableBody">
                    <!-- Property data will be dynamically loaded here -->
                </tbody>
            </table>
        </div>
    </div>
</main>



