angular.module 'companyAdmin'
.controller 'CompanyNewController', ($scope, $http, $location) ->
  $scope.company = {}
  $scope.edit = true
  $scope.message = ""

  onComplete = (response)->
    $location.path "/companies/#{response.data.id}"

  onError = (response)->
    $scope.message = "Sorry! We couldn't save the company! Try again."
    $scope

  $scope.save = ()->
    req = {
      method: "POST",
      url: "/api/v1/companies",
      headers: {
        'Content-Type': 'application/json'
      }
      data: $scope.company
    }
    $http(req).then onComplete, onError

  $scope
