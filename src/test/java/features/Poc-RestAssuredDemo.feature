Feature: Rest Assured Demo Feature File

  @RestAssuredDemo @addPlaceAPI
  Scenario Outline: To verify the place is successfully added using add place api
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls the "AddPlaceAPI" using http "POST" request
    Then the api call is success with status code 200
    And "status" in response body is "OK"
    And validate the "addplace_Response" vs "expected_AddPlace_JsonSchema"

    Examples:
      | name           | language | address        |
      | BharathKumar M | Tamil    | Newyork Street |

  @RestAssuredDemo @getPlaceAPI
  Scenario Outline: To verify the place is aaded successfully using addPlaceAPI and get the response using getPlaceAPI
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls the "AddPlaceAPI" using http "POST" request
    Then the api call is success with status code 200
    And "status" in response body is "OK"
    Then verify Place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name           | language | address        |
      | BharathKumar M | Tamil    | Newyork Street |


  @RestAssuredDemo @deletePlaceAPI
  Scenario: To verify the delete place functionality is working
    Given DeletePlace Payload
    When user calls the "deletePlaceAPI" using http "POST" request
    Then the api call is success with status code 200
    And "status" in response body is "OK"


  @RestAssuredDemo @updatePlaceAPI
  Scenario Outline: To verify the updatePlaceAPI With the already exisitng addPlaceAPI Payload
    Given update place payload with "<address>"
    When user calls the "updatePlaceAPI" using http "PUT" request
    And the api call is success with status code 200
#    Then verify the "msg" in response body is "Address successfully updated"

    Examples:
      | address              |
      | William Street North |












