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
.container form input[type="password"],
.container form input[type="tel"],
.container form input[type="number"],
.container form input[type="email"] {
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
        display:inline-block;
        width:inherit;
    }
  
  #messageLabelText {
                color: red;
                color: ${messageColor};
            }     
</style>
<script>
    document.title = "View Purchase History";
   
    document.getElementById('SMRELabel').style.display = 'none';
    document.getElementById('SVPHLabel').style.display = 'none';
    document.getElementById('AMSLabel').style.display = 'none';
    document.getElementById('SAOLabel').style.display = 'none';
    document.getElementById('SMBLabel').style.display = 'none';
    document.getElementById('SMSLabel').style.display = 'none';
    document.getElementById('SFBALabel').style.display = 'none';
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

function populateFormFields(row) {
      const cells = row.getElementsByTagName('td');
      const form = document.getElementById('editReceiptForm');

      // Populate form fields with cell values
      form.elements['rId'].value = cells[0].textContent;
      form.elements['rFeedback'].value = cells[1].textContent;
      form.elements['rRating'].value = cells[2].textContent;
      form.elements['rDateOfSale'].value = cells[3].textContent;
      form.elements['oId'].value = cells[4].textContent;
    }

// Function to load the property data into the table
function loadReceiptTable() {
      fetch('LoadReceiptTable')
        .then(response => response.text())
        .then(data => {
          const propertyTableBody = document.getElementById('EditReceiptTableBody');
          propertyTableBody.innerHTML = data;

          // Add event listeners to table rows
          const rows = propertyTableBody.getElementsByTagName('tr');
          for (let i = 0; i < rows.length; i++) {
            rows[i].addEventListener('click', function () {
              populateFormFields(this);
            });
          }
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
    <form class="form-container" id="editReceiptForm" action="EditReceipt" method="POST">
        <h1>Provide Feedback</h1>
        <input  type="number" name="rId" placeholder="Receipt ID" readonly>
        <br>
        <input type="text" name="rFeedback" placeholder="Feedback">
        <br>
        <input type="number" name="rRating" placeholder="Rating (1-5)">
        <br>
        <input type="submit" value="Send Feedback">
        <br>
        <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
        <br>
        <input hidden type="text" name="rDateOfSale" placeholder="Date Of Sale" readonly>
        <br>
        <input hidden type="number" name="oId" placeholder="Offer ID" readonly>
        
    </form>
    
    <div class="property-listing">
      <h1>Purchase History</h1>
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
        <tbody id="EditReceiptTableBody"w>
          <!-- Property data will be dynamically loaded here -->
                        
        </tbody>
      </table>
    </div>
  </div>
</main>
