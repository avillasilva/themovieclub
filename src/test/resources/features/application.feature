Feature: Register in the plataform

Scenario: one user with correct data must be registered
	Given the email, password and password confirmation
	When the register request is received
	Then the user is registered
	