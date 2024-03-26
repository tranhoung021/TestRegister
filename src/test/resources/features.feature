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
      | Browser | DateOfBirth | Forename | Surname | EmailAddress       | ConfirmEmailAddress | Password | ConfirmPassword | TermsAccept | AgeAccept | AgreeToCodeOfEthicsAndConduct | Outcome      |
      | chrome  | 01/01/1990  | Abc      | Def     | abc.def11@test.com | abc.def11@test.com  | abc.def  | abc.def         | true        | true      | true                          | successful   |
      | chrome  | 01/01/1990  | Abc      |         | abc.def12@test.com | abc.def12@test.com  | abc.def  | abc.def         | true        | true      | true                          | unsuccessful |
      | chrome  | 01/01/1990  | Abc      | Def     | abc.def3@test.com  | abc.def13@test.com  | abc.def  | def.abc         | true        | true      | true                          | unsuccessful |
      | chrome  | 01/01/1990  | Abc      | Def     | abc.def14@test.com | abc.def14@test.com  | abc.def  | abc.def         | false       | true      | true                          | unsuccessful |