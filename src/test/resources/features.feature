Feature: Register user

  Scenario Outline: Successful registration
    Given the user is using "<Browser>" as a browser
    And the user is on the registration page
    When the user registers with the following details
      | DateOfBirth                   | <DateOfBirth>                   |
      | Forename                      | <Forename>                      |
      | Surname                       | <Surname>                       |
      | EmailAddress                  | <EmailAddress>                  |
      | ConfirmEmailAddress           | <ConfirmEmailAddress>           |
      | Password                      | <Password>                      |
      | ConfirmPassword               | <ConfirmPassword>               |
      | TermsAccept                   | <TermsAccept>                   |
      | AgeAccept                     | <AgeAccept>                     |
      | AgreeToCodeOfEthicsAndConduct | <AgreeToCodeOfEthicsAndConduct> |
    Then the registration should be "<Outcome>"

    Examples:
      | Browser | DateOfBirth | Forename | Surname | EmailAddress        | ConfirmEmailAddress | Password | ConfirmPassword | TermsAccept | AgeAccept | AgreeToCodeOfEthicsAndConduct | Outcome      |
      | chrome  | 01/01/1990  | John     | Doe     | john.doe11@test.com | john.doe11@test.com | john.doe | john.doe        | true        | true      | true                          | successful   |
      | chrome  | 01/01/1990  | John     |         | john.doe12@test.com | john.doe12@test.com | john.doe | john.doe        | true        | true      | true                          | unsuccessful |
      | chrome  | 01/01/1990  | John     | Doe     | john.doe13@test.com | john.doe13@test.com | john.doe | doe.john        | true        | true      | true                          | unsuccessful |
      | chrome  | 01/01/1990  | John     | Doe     | john.doe14@test.com | john.doe14@test.com | john.doe | john.doe        | false       | true      | true                          | unsuccessful |