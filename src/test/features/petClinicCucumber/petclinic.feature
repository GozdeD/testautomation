Feature: Test Searching Owner and add if it is not existing

	Background: To Launch the web site
		Given Launch the web site
		And Click find owners button
		And Hit the search box
		
	Scenario: Searching non-existing user
		When Type "Asdf" in the search box
		And Click find owner button
		Then We should see the "has not been found" message
		
	