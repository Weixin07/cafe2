<%@page import="Receipt.Receipt"%>
<%@page import="java.util.List"%>
<%@ include file="master.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    h1{
        margin-bottom:25px;
    }
    
    canvas {
        max-width: 100%;
        max-height: 100%;
    }
    
        main {  
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh; /* Set the main container to occupy full viewport height */
    }

    .chart-container {
        flex: 1; /* Allow the chart container to occupy remaining space */
        display: flex;
        align-items: center;
        justify-content: center;
        overflow: hidden;
        margin-bottom: 25px;
    }
</style>

<main>
    <h1>Receipt Rating Chart</h1>
    <div class="chart-container">
        <canvas id="bar_chart" width="1200" height="600"></canvas>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
            document.title = "View Charts";
   
            document.getElementById('AMSLabel').style.display = 'none';
            document.getElementById('BVPHLabel').style.display = 'none';
            document.getElementById('SMRELabel').style.display = 'none';
            document.getElementById('SVPHLabel').style.display = 'none';
            document.getElementById('SAOLabel').style.display = 'none';
            document.getElementById('loginLabel').style.display = 'none';
        
        
        // Create a JavaScript array from the JSP List
        var receiptData = [
            <% List<Receipt> receiptList = (List<Receipt>) request.getAttribute("receiptList");
               for (int i = 0; i < receiptList.size(); i++) {
                   Receipt receipt = receiptList.get(i);
                   if (i > 0) {
                       out.print(",");
                   }
            %>
            {
                id: <%= receipt.getId() %>,
                rating: <%= receipt.getrRating() %>,
            }
            <% } %>
        ];

        // Count occurrences of each rating
        var ratingCounts = new Array(6).fill(0); // Initialize an array for rating counts
        receiptData.forEach(item => {
            ratingCounts[item.rating] += 1;
        });

        // Create separate arrays for ratings and counts
        var ratingValues = ratingCounts.map((count, index) => index);
    var ratingCountData = ratingCounts.slice(1); // Exclude the count of rating 0

    // Function to draw the chart
    function drawChart() {
        var ctx = document.getElementById('bar_chart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ratingValues.slice(1), // Start from 1
                datasets: [{
                    label: 'Receipt Count',
                    data: ratingCountData,
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 2
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true,
                        stepSize: 1 // Ensure y-axis increments by 1
                    },
                    x: {
                        beginAtZero: false,
                        ticks: {
                            stepSize: 1,
                            suggestedMin: 1, // Start X axis ticks from 1
                            suggestedMax: 5   // End X axis ticks at 5
                        }
                    }
                }
            }
        });
    }

    // Call the drawChart function when the page loads
    window.onload = drawChart;
    </script>
