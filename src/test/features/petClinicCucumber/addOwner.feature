Feature: Test Adding Owner 

	Background: To Launch the web site
		Given Launch web site
		And Click the find owners button
		And Hit the "Add Owner" button
		
	Scenario Outline: Adding new owner
		When Type following data in the box by map
			|box			|data			|
			|firstName		|Gözde			|
			|lastName		|Dereli			|
			|address		|Maltepe		|
			|city			|İstanbul		|
			|telephone		|1234567890		|
		And Click "Add Owner" button
		Then Owner table should be visible


	