console.log("hello");

slider = document.getElementById("myRange"); //get slider element on html
var output = document.getElementById("demo");  //get demo element on html
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;  //print slected year on slider to html
}

$.get("/country", function(response){   //ajax on getting a response from /country
    //$("#testID").html(response);
    var responseObj = JSON.parse(response); //parse response with json
    var selectedYear = document.getElementById('myRange').value;  //get currently selected year from slider
    //console.log(response);
    //console.log(responseObj);
	
    var margin = { //declare margins for d3 graph
	  top: 50,
	  right: 20,
	  bottom: 30,
	  left: 50
	};
    var w = 1100 - margin.left - margin.right; //declare height and width for d3 
    var h = 700 - margin.top - margin.bottom;

    // a common thing is to 'wrap' some elements in a 'g' container (group)
    // this is like wrapping html elements in a container div
    var selYear = 1800;    //set current year for first runthrough of d3
    var svg = d3.select("svg");   //set svg element 
    var g = svg.selectAll("g").data(responseObj);  //set g

    svg.append("text")   //add x axis label
    .attr("class", "x label")
    .attr("text-anchor", "end")
    .attr("x", w)
    .attr("y", h - 6)
    .text("Life expectancy (years)");

    svg.append("text") //add y axis label
    .attr("class", "y label")
    .attr("text-anchor", "end")
    .attr("y", margin.top + 6)
    .attr("dy", ".75em")
    .attr("transform", "rotate(-90)")
    .text("Income per capita inflation adjusted (dollars)");

    var xScale = d3.scaleLinear() //create x scale for d3
                  .domain([0, 90])
                  .range([0, w - 150]);

    var yScale = d3.scaleLinear() //create y scale for d3
		 .domain([0,100000])
		 .range([h,0+10]);

    var zScale = d3.scaleLinear() //create z scale for d3 (circles)
		 .domain([1,1000000000])
		 .range([1.5,50]);

g.exit().remove(); //remove old date from d3

var en = g.enter().append("g") //enter new data into d3
		        .attr("transform",function(d){		
                        var x = 0;
                        var y = 0;
                if (d && d.data && d.data["life_expectancy_years"] && d.data["income_per_person_gdppercapita_ppp_inflation_adjusted"] && d.data["life_expectancy_years"][selYear] && d.data["income_per_person_gdppercapita_ppp_inflation_adjusted"][selYear]){
                        x = xScale(d.data.life_expectancy_years[selYear]);
                        y = yScale(d.data.income_per_person_gdppercapita_ppp_inflation_adjusted[selYear]);
                }
                return "translate("+ x + "," + y +")" //read in x and y of circles to d3
                         })
                          .on('mouseover', function(d){
              		this.style.opacity = 0.5;
              		this.querySelectorAll("text")[0].style.opacity = 1;
           	})
           	.on('mouseout', function(d){
console.log("and here");
              		this.style.opacity = 1;
              		this.querySelectorAll("text")[0].style.opacity = 0;
           	});


	     en.append("text").text(function(d){ return d.name });

		svg.selectAll("text").attr("style","opacity:0"); //read in name of country to circle
 

            var circle = en.append("circle")
                .attr("r",function(d){ return zScale(d.data.population_total[selYear])}) //set size of circle
                .attr("fill",function(d,i){ return i % 2 == 0 ? "red" : "blue" })
		 .on('mouseover', function(d){
              		this.style.opacity = 0.5;
              		en.select("text").style.opacity = 1;
           	})
           	.on('mouseout', function(d){
console.log("and here");
              		this.style.opacity = 1;
              		en.select("text").style.opacity = 0;
           	});

            
              

    // Add scales to axis
    var x_axis = d3.axisBottom()
                 .scale(xScale);

    var y_axis = d3.axisLeft()
		 .scale(yScale);

	svg.append("g").attr("transform","translate(50,"+h+")").call(x_axis);
        svg.append("g").attr("transform","translate(50,0)").call(y_axis);

    // create new 'g' elements for each country
    function drawChart(selYear) //repeat draw function to update chart when slider is moved
    {
		en 
		.attr("transform",function(d){
			var x = 0;
			var y = 0;
		if (d && d.data && d.data["life_expectancy_years"] && d.data["income_per_person_gdppercapita_ppp_inflation_adjusted"] && d.data["life_expectancy_years"][selYear] && d.data["income_per_person_gdppercapita_ppp_inflation_adjusted"][selYear]){
			x = xScale(d.data.life_expectancy_years[selYear]);
			y = yScale(d.data.income_per_person_gdppercapita_ppp_inflation_adjusted[selYear]);
		} 
		return "translate("+ x + "," + y +")" 
	    });

		circle
                .attr("r",function(d){ return zScale(d.data.population_total[selYear])})
                .attr("fill",function(d,i){ return i % 2 == 0 ? "red" : "blue" })

		  

            //en.append("text").text(function(d){ return d.name });
	    //g.exit().remove()

	    
    }

    drawChart(selectedYear); 

        d3.select("#myRange").on("change", function(d){
     selectedYear = this.value 
     //d3.select("svg").remove();
     //var g = d3.select("svg").selectAll("g").data(responseObj);
     drawChart(selectedYear);
   })

    console.log(selectedYear);

    

}).fail(function(response){
    console.log("fail");
}).always(function(response){
    console.log("always");
});
