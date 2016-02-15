angular.module 'companyAdmin'
.controller 'EmployeeNewController', ($scope, $http, $location, $routeParams) ->
  $scope.edit = true
  $scope.message = ""
  $scope.employee = {
    company: {
      id: $routeParams.id
    }
  }

  onComplete = (response)->
    $location.path "/companies/#{response.data.company.id}"

  onError = (response)->
    $scope.message = "Sorry! We couldn't save the employee! Try again."
    $scope

  $scope.save = ()->
    req = {
      method: "POST",
      url: "/api/v1/employees",
      headers: {
        'Content-Type': 'application/json'
      }
      data: $scope.employee
    }
    $http(req).then onComplete, onError

  $scope
