<%@page import="java.util.List"%>
<%@page import="Property.Property"%>
<%@page import="Property.Property"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="master.jsp" %>
<!DOCTYPE html>
<style>
* {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
}   

.search-bar {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.search-input-container {
  display: flex;
  align-items: center;
}

.sort-select-container {
  margin-left: 10px;
  display: flex;
  align-items: center;
}

.search-bar input[type="text"] {
  width: 250px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.search-bar button {
  padding: 10px 20px;
  background-color: #3eaba1;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.search-bar button:hover {
  background-color: #31978c;
}

.sort-select-container label {
  margin-right: 5px;
}

.sort-select-container select {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  background-color: #fff;
}

.container {
  width: 1100px;
   margin: 50px auto 100px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.container .box {
  width: calc((100% / 3) - 10px);
  /* Modify the width calculation to create space between cards */
  margin-bottom: 30px; /* Add margin at the bottom of each card */
}

.container .box .bottom {
  padding: 20px 15px;
  position: relative;
}

.container .box .bottom h3 {
  font-size: 15px;
}

.container .box .bottom > p {
  margin: 15px 0;
  font-size: 13px;
  line-height: 1.4;
  color: #777;
}

.container .box .bottom .advants {
  display: flex;
}

.container .box .bottom .advants > div {
  margin-right: 15px;
}

.container .box .bottom .advants > div > span {
  font-weight: bold;
  font-size: 12px;
}

.container .box .bottom .advants > div > div {
  margin-top: 10px;
  display: flex;
  align-items: center;
}

.container .box .bottom .advants > div > div i {
  color: #777;
  font-size: 22px;
}

.container .box .bottom .advants > div > div > span {
  font-size: 12px;
  font-weight: bold;
  margin-left: 10px;
}

.container .box .bottom .advants > div > div > span > span {
  color: #777;
  font-weight: normal;
  margin-left: 5px;
}

.container .box .bottom .price {
  margin-top: 20px;
}

.container .box .bottom .price span:first-of-type {
  display: block;
  font-size: 13px;
  font-weight: bold;
  margin-bottom: 5px;
}

.container .box .bottom .price span:last-of-type {
  color: #3eaba1;
  font-size: 22px;
}

.container .box .bottom button#buyButton {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #3eaba1;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.container .box .bottom button#buyButton:hover {
  background-color: #31978c;
}

#messageLabelText{
    color:red;
    text-align: center;
    
}

#mainFoot {
  display: none;
}

#plFoot {
  position: relative;
  bottom: 0;
  text-align: center;
}

</style>
<main>
  <h1 style="text-align: center; margin-bottom:50px;">Featured Properties</h1>
  
  <!-- Search bar -->
   <div class="search-bar">
    <div class="search-input-container">
      <input type="text" id="searchInput" placeholder="Search by address">
      <button class="search-button" onclick="filterProperties()">Search</button>
    </div>
    <div class="sort-select-container">
      <select id="sortSelect" onchange="sortProperties()">
        <option value="" selected disabled hidden>Sort By</option>
        <option value="price_asc">Price (Low to High)</option>
        <option value="price_desc">Price (High to Low)</option>
        <option value="area_asc">Area (Low to High)</option>
        <option value="area_desc">Area (High to Low)</option>
        <!-- Add more sorting options as needed -->
      </select>
    </div>
  </div>
  <p id="messageLabelText" ${hideMessageLabel ? 'style="display:none;"' : ''}>${messageLabelText}</p>
  
  <div class="container">
    <% List<Property> properties = (List<Property>) request.getAttribute("properties");
    for (Property property : properties) { %>
    <div class="box">
      <div class="bottom">
          
        <h3><%= property.getpAddress() %></h3>
        <p>
          <%= property.getpDescription() %>
        </p>
        <div class="advants">
          <div>
            <span>Beds + Baths</span>
            <div><i class="fas fa-th-large"><%= property.getpBuild()%></i><span> </span></div>
          </div>
          <div>
            <span>&nbsp;&nbsp;&nbsp;Furnish Quality</span>
            <div><i class="fas fa-shower"><%= property.getpFurnish() %></i><span></span></div>
          </div>
          <div>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;Area</span>
            <div>
              <i class="fas fa-vector-square"><%= property.getpSize() %></i
              ><span><span>Sq Ft</span></span>
            </div>
          </div>
        </div>

        <div class="price">
          <span><%= property.getpType() %> For <%= property.getpSaleType() %></span>

          <span>MYR <%= property.getpPrice() %></span>
        
        </div>
        <p id="pSeller">Posted By <%= property.getpSeller() %></p>
        <form ${hideBuyButton ? 'style="display:none;"' : ''} action="CreateOffer" method="POST"> 
            <input  hidden type="text" name="pAddress" value="<%= property.getpAddress() %>"></input>    
            <input  hidden type="number" name="pId" value=<%= property.getpId() %>></input>    
            <input  hidden type="text" name="pSeller" value="<%= property.getpSeller() %>"></input>  
            <input type="submit" value="Place Bid" class="custom-button"/>
        </form>
            
      </div>
    </div>
    <% } %>
  </div>
  <footer id="plFoot">&copy; 2023 UNREAL ESTATE. All rights reserved.</footer>
    <script>
    // Function to filter properties based on search criteria
    function filterProperties() {
      const searchInput = document.getElementById('searchInput').value.toLowerCase();
      const propertyBoxes = document.querySelectorAll('.container .box');

      propertyBoxes.forEach(box => {
        const propertyText = box.textContent.toLowerCase();
        const shouldDisplay = propertyText.includes(searchInput);
        box.style.display = shouldDisplay ? 'block' : 'none';
      });
    }

    // Function to sort properties based on selected option
    function sortProperties() {
      const sortSelect = document.getElementById('sortSelect');
      const sortBy = sortSelect.value;
      const propertyBoxes = document.querySelectorAll('.container .box');

      const propertyArray = Array.from(propertyBoxes);

      if (sortBy === 'price_asc') {
        propertyArray.sort((a, b) => {
          const priceA = parseFloat(a.querySelector('.price span:last-of-type').textContent.slice(4));
          const priceB = parseFloat(b.querySelector('.price span:last-of-type').textContent.slice(4));
          return priceA - priceB;
        });
      } else if (sortBy === 'price_desc') {
        propertyArray.sort((a, b) => {
          const priceA = parseFloat(a.querySelector('.price span:last-of-type').textContent.slice(4));
          const priceB = parseFloat(b.querySelector('.price span:last-of-type').textContent.slice(4));
          return priceB - priceA;
        });
      } else if (sortBy === 'area_asc') {
        propertyArray.sort((a, b) => {
          const areaA = parseInt(a.querySelector('.advants > div:last-child i').textContent);
          const areaB = parseInt(b.querySelector('.advants > div:last-child i').textContent);
          return areaA - areaB;
        });
      } else if (sortBy === 'area_desc') {
        propertyArray.sort((a, b) => {
          const areaA = parseInt(a.querySelector('.advants > div:last-child i').textContent);
          const areaB = parseInt(b.querySelector('.advants > div:last-child i').textContent);
          return areaB - areaA;
        });
      }

      const container = document.querySelector('.container');
      propertyArray.forEach(box => container.appendChild(box));
    }
  </script>
</main>
