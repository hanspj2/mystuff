import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import "../public/stylesheets/scss/main.scss"; //import bootstrap
import "./bootstrap.css";

let myCountry = null;
const title = 'Countries and Stats';

ReactDOM.render(	//render title
<div>{title}</div>,
document.getElementById('app')
);

'use strict';


class FlavorForm extends React.Component { //dropdown box class
  constructor(props) {
    super(props);
    let countryData = [];
    if(JSON.parse(localStorage.getItem('TheCountries')) != null){  //if localstorage is populated use local storage otherwise use empty array
	countryData = JSON.parse(localStorage.getItem('TheCountries'));
    }
    this.handleChange = this.handleChange.bind(this);
    this.state = {value: 'Please select', countryData: countryData}; //country object
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value}); //handle change
  }

  handleSubmit(event) {                           //on submit being pressed do this
    myCountry = this.state.value;
    event.preventDefault();
    pop()                                          //populate column 1 with pop
    life()				           //populate column 2 with life expectancy
    gdp()					   //populate column 3 with gdp per capita
  }

  componentDidMount() {                             //import statement run after first render
    console.log('outside import');
    if(localStorage.getItem('TheCountries') == null)  //if local storage is null do nothing
    { 
    fetch("http://10.25.137.142:80/country")           //api call to flask app
      .then(res => res.json())
      .then(
        (result) => {
            this.state.countryData = result; 	 //set json result to countryData object
	    localStorage.setItem('TheCountries', JSON.stringify(result));  //set json response into local storage
	    window.location.reload();                                      //refresh page to populate dropdown
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
   }
   else
	{
	}

}
  render() {
    return (				//render dropdown
      <form onSubmit={this.handleSubmit}>
        <label>					
          Pick a country:			
          <select value={this.state.value} onChange={this.handleChange}>
	    {this.state.countryData.map((countryData, i) => (             
    		<option key={i} value={countryData.name}>{countryData.name}</option>
  	    ))}
          </select>
        </label>
        <input type="submit" value="Submit" />
      </form>
    );
  }
}

let domContainer2 = document.querySelector('#dropdown_container');     //render dropdown box to id in html
ReactDOM.render(<FlavorForm />, domContainer2);		 	       //render dropdown box to id in html

function pop() { //function to render population
  let myJson = JSON.parse(localStorage.getItem('TheCountries'));  //get countries
  let popt = [];                                                  
  for(var k in myJson){                                           //for each through countries
	if(myJson[k].name == myCountry) {			  //if country == to selected country from dropdown box
	        if(myJson[k].data.population_total == undefined)	//if column  in empty render empty paragraph
		{
			popt.push(<p></p>);
		}
		else
		{
			popt.push(<p>Total population</p>);			//print countrys year and value to popt
			for (const [key, value] of Object.entries(myJson[k].data.population_total)) {
				 popt.push(<p>{key} : {value}</p>);
			}
    		}
        }
    }

  ReactDOM.render(popt, document.getElementById('column1')); //render out popt to column1
}


function gdp() {						//same same above
  let myJson = JSON.parse(localStorage.getItem('TheCountries'));
  let gdpp = [];
  for(var k in myJson){
	if(myJson[k].name == myCountry) {
		console.log("made it in");
		if(myJson[k].data.income_per_person_gdppercapita_ppp_inflation_adjusted == undefined)
		{
			gdpp.push(<p></p>);
		}
		else
		{
			gdpp.push(<p>GDP per capita</p>);
			for (const [key, value] of Object.entries(myJson[k].data.income_per_person_gdppercapita_ppp_inflation_adjusted)) {
				 gdpp.push(<p>{key} : {value}</p>);
			}
    		}
        }
    }

  ReactDOM.render(gdpp, document.getElementById('column2'));
}


function life() {			//same as above
  let myJson = JSON.parse(localStorage.getItem('TheCountries'));
  let lifeex = [];
  for(var k in myJson){
	if(myJson[k].name == myCountry) {
		console.log("made it in");
	        if(myJson[k].data.life_expectancy_years == undefined)
		{
			lifeex.push(<p></p>);
		}
		else
		{	
			lifeex.push(<p>Life expectancy</p>);
			for (const [key, value] of Object.entries(myJson[k].data.life_expectancy_years)) {
				 lifeex.push(<p>{key} : {value}</p>);
			}
    		}
        }
    }

  ReactDOM.render(lifeex, document.getElementById('column3'));
}


