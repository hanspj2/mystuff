# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
from flask import Flask, render_template
from flask_cors import CORS
from mongoengine import *
import csv
import os

app = Flask(__name__)
CORS(app) #avoid cors violations
app.config.from_object('config')  #import config settigns from file

connect('mydatabase')   #connect to databaase

class Country(Document):  #create country object
    name = StringField()
    data = DictField()
    


@app.route('/makeCountry', methods=['PUT'])  # make a country object, mainly used for debugging
def makeCountries():
    class Country(Document):
        name = StringField()
   
    for u in Country.objects:
        u['name'] = 'NZ'
        u.save()
    return "something",200  # return success

@app.route('/country', methods=['GET'])
@app.route('/country/<country_name>', methods=['GET'])   #api route for getting country(s) 
def getCountries(country_name=None):
    Countries = None
    if country_name is None:  #if no country specified return all countries
        Countries = Country.objects
    else:
        Countries = Country.objects.get(name=country_name) #if country specified return specific country
    return Countries.to_json(),200

@app.route('/deleteCountry/<country_name>', methods=['DELETE'])
def deleteCountry():
    return deleteCountry

@app.route('/editCountry/<country_name>', methods=['PUT'])
def editCountry():
    return editCountry

@app.route('/Documentation')
def documentation():
    myName = "Phill"
    return render_template('Documentation.html', name=myName, title='Documentation')

@app.route('/')   #default route
def hello_world():
    myName = "Phill"
    return render_template('HomePage.html', name=myName, title='Your title here')

@app.route('/loaddata') # loads csv files into mongo database
def loaddata():
	basedir = os.path.abspath(os.path.dirname(__file__))
	FILES_FOLDER = os.path.join(basedir, 'files') # get all csv files 
	for file in os.listdir(app.config['FILES_FOLDER']):
		filename = os.fsdecode(file)
		path = os.path.join(app.config['FILES_FOLDER'],filename)
		f = open(path)
		r = csv.DictReader(f)
		d = list(r)
		for data in d:                #read in csv data
#			print(data)
			country = Country()
			dict = {}
			for key in data:
				if key == "country":
#					print(data["country"])
					for u in Country.objects:					
						if(u['name'] == data["country"]):
							country = u
							dict = u['data']
							print(country['name'])
							break
						else:
							country['name'] = data["country"]
				else:
					f = filename.replace(".csv","")
					if f in dict:
						dict[f][key] = data[key]
					else:
						dict[f] = {key:data[key]}          #write to database
				country['data'] = dict
			country.save()	#save country to database
	return'done'

@app.route('/index')  #render homepage if user goes to index
def index():
    myName = "Phill"
    return render_template('HomePage.html', name=myName, title='Your title here'), 200

@app.route('/home')   #render home if user goes to home
def home():
    myName = "Phill"
    return render_template('HomePage.html', name=myName, title='Your title here'), 200

@app.route('/inspiration')    #render inspiration if user goes to inspiration
def inspiration():
    myName = "Phill"
    return render_template('Inspiration.html', name=myName, title='Your title here'), 200

if __name__ =="__main__":
    app.run(host='0.0.0.0',port=80)    #run on localhost port 80
    
