angular.module 'companyAdmin'
.controller 'EmployeeIndexController', ($scope, $http, $location) ->
  $scope.employees = []
  $scope.searchTerm = ""

  onComplete = (response) ->
    $scope.employees = response.data.content
    if $scope.employees.length == 0
      $scope.message = "Could not find any employee."
    $scope

  onError = (reason) ->
    $scope.message = "Could not get the companies! #{reason}"
    $scope

  $scope.viewDetail = (id) ->
    $location.path "/employees/#{id}"

  $scope.searchByCompany = (companyId) ->
    $scope.message = ""
    $http.get("/api/v1/employees/company/#{companyId}").then onComplete, onError

  $scope.searchByCompany($scope.companyId)
  $scope
