<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <!--Load the AJAX API-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', '날짜');
        data.addColumn('number', '판매합계');	[{},{}]
        
        var arr = [];
        //ajax
        //ajax는 기본이 비동기, 따라서 데이터 들어오기 전에 data.addRows(arr);실행되므로 동기방식으로 바꿔준다
        $.ajax({
        	url : "getChartData",
        	async : false, //동기로 바꿈
        	dataType : 'json',
        	success : function(result) {
        		//서버에서 받아온 데이터 [{},{}] -> [[],[]]
        		for(o of result) {		//for 의 in of 차이 알아오기
        			arr.push([o.날짜, parseInt(o.일별합계)]  ); //parseInt(o.cnt)는 data.addColumn('number', '인원수');때문에
        		}
        	}
        });
        data.addRows(arr);

        // Set chart options
        var options = {'title':'일별 판매합계',
                       'width':400,
                       'height':300
                       vAxis: {format:"$#,###", gridlines: {count : 10}}; //보조눈금선

        // Instantiate and draw our chart, passing in some options.
        //차트 종류 변경 가능(LineChart, pieChart , BarChart등등)
        //자세한 차트 종류는 구글에 구글차트 검색하면 chart types 나온다
        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>