<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Receipt.Receipt"%>
<%@page import="java.util.List"%>
<%@page import="Property.Property"%>
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
  .container form input[type="number"],
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
  
#left-side{
    padding-top:25px;
    display:inline-block;
    width:50%;
  }
  
  #messageLabelText {
                color: red;
                color: ${messageColor};
            }
</style>
<script>
    document.title = "Feedback Analysis";
    
    document.getElementById('AMSLabel').style.display = 'none';
    document.getElementById('BVPHLabel').style.display = 'none';
    document.getElementById('SMRELabel').style.display = 'none';
    document.getElementById('SVPHLabel').style.display = 'none';
    document.getElementById('SAOLabel').style.display = 'none';
    document.getElementById('loginLabel').style.display = 'none';
   

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
  
  // Retrieve the value from the query parameter
var uNameLabelText = getQueryParam('uNameLabel2');

// Set the value as the text content of the label element
document.getElementById('uNameLabel2').textContent = uNameLabelText;

// Function to retrieve the value from the query parameter
function getQueryParam(name) {
  name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
  var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
  var results = regex.exec(location.search);
  return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

// Function to load the property data into the table
function loadReceiptTable() {
    fetch('LoadReceiptTable')
        .then(response => response.text())
        .then(data => {
            const propertyTableBody = document.getElementById('ReceiptViewTableBody');
            propertyTableBody.innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading property table:', error);
        });
}

// Call the function to load the property table when the page loads
window.addEventListener('DOMContentLoaded', loadReceiptTable);
</script>



<main>
  <div class="container">
      <div id="left-side">
    <form class="form-container" action="SearchReceipt" method="POST">
      <h1>Search Receipt</h1>
      <input type="text" name="searchReceipt" placeholder="Search Receipt ID">
      <br>
      <input type="submit" value="Search">
      <br>
      <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
      
    </form>
    <form id="ChartLabel"  class="form-container" ${hideChartLabel ? 'style="display:none;"' : ''} action="ViewCharts" method="POST"> 
        <h1>Visualize Data</h1>
        <input type="submit" value="Generate Charts"/>
    </form>
    </div>

    <div class="property-listing">
      <h1>Receipt List</h1>
      <table>
        <thead>
          <tr>
            <th>Receipt ID</th>
            <th>Feedback</th>
            <th>Rating</th>
            <th>Date Of Sale</th>
            <th>Offer ID</th>
          </tr>
        </thead>
        <tbody id="ReceiptViewTableBody" ${hideReceiptTableLabel ? 'style="display:none;"' : ''}>
          <!-- Property data will be dynamically loaded here -->
                        
        </tbody>
        <tbody id="ReceiptSearchTableBody" >
          <!-- Property data will be dynamically loaded here -->
                       
                        <%  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
                            List<Receipt> filteredReceipt = (List<Receipt>) request.getAttribute("filteredReceipt");
                            if (filteredReceipt != null) {
                                for (Receipt stf : filteredReceipt) { %>
                                    <tr>
                                        <td><%= stf.getId() %></td>
                                        <td><%= stf.getrFeedback() %></td>
                                        <td><%= stf.getrRating() %></td>
                                        <td><%= sdf.format(stf.getrDateOfSale()) %></td>
                                        <td><%= stf.getoId() %></td>
                                    </tr>
                                <% }
                            } %>
        </tbody>
      </table>
    </div>
  </div>
</main>
