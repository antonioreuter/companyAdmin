angular.module 'companyAdmin'
.controller 'EmployeeDetailController', ($scope, $http, $routeParams) ->
  $scope.employee = {}
  $scope.message = ""
  $scope.edit = false
  $scope.employeeId = $routeParams.id

  onComplete = (response) ->
    $scope.employee = response.data
    $scope

  onError = (reason) ->
    $scope.message = "Could not get the employee! #{reason}"
    $scope

  $scope.enableEdit = () ->
    $scope.edit = true

  find = (id) ->
    $http.get("/api/v1/employees/#{id}").then onComplete, onError

  $scope.save = ()->
    req = {
      method: "PUT",
      url: "/api/v1/employees/#{$scope.employee.id}",
      headers: {
        'Content-Type': 'application/json'
      }
      data: $scope.employee
    }
    $http(req).then ()->
      $scope.message = "Employee saved successfully!"
      $scope.edit = false

  find($scope.employeeId)

  $scope
