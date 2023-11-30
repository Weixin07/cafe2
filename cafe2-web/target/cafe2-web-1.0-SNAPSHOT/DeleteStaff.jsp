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
    document.title = "Delete Managing Staff Info";
   
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

function populateFormFields(row) {
      const cells = row.getElementsByTagName('td');
      const form = document.getElementById('editStaffForm');

      // Populate form fields with cell values
      form.elements['stEmail'].value = cells[1].textContent;
    
    }

// Function to load the property data into the table
function loadStaffTable() {
      fetch('LoadStaffTable')
        .then(response => response.text())
        .then(data => {
          const propertyTableBody = document.getElementById('ViewStaffTableBody');
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
          console.error('Error loading Staff table:', error);
        });
        
        
    }

// Call the function to load the property table when the page loads
window.addEventListener('DOMContentLoaded', loadStaffTable);


</script>

<main>
  <div class="container">
    <form class="form-container" id="editStaffForm" action="DeleteStaff" method="POST">
        <h1>Delete Staff Info</h1>
        <input type="email" name="stEmail" placeholder="Email" readonly>
        <br>
        <input type="submit" value="Delete Staff Info">
        <br>
        <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
    </form>
    
    <div class="property-listing">
      <h1>Managing Staff List</h1>
      <table>
        <thead>
          <tr>
            <th>Full Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>Age</th>
            <th>Phone No. (+60)</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody id="ViewStaffTableBody"w>
          <!-- Property data will be dynamically loaded here -->
                        
        </tbody>
      </table>
    </div>
  </div>
</main>
