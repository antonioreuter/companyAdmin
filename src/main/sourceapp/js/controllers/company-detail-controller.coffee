angular.module 'companyAdmin'
.controller 'CompanyDetailController', ($scope, $http, $routeParams) ->
  $scope.company = {}
  $scope.message = ""
  $scope.edit = false

  onComplete = (response) ->
    $scope.company = response.data
    $scope

  onError = (reason) ->
    $scope.message = "Could not get the companies! #{reason}"
    $scope

  $scope.findEmployees = (id) ->
    $http.get("/api/v1/employees/company/#{id}").then (response) ->
      $scope.employees = response.data.content

  $scope.enableEdit = () ->
    $scope.edit = true

  find = (id) ->
    $http.get("/api/v1/companies/#{id}").then onComplete, onError

  $scope.save = ()->
    req = {
      method: "PUT",
      url: "/api/v1/companies/#{$scope.company.id}",
      headers: {
        'Content-Type': 'application/json'
      }
      data: $scope.company
    }
    $http(req).then ()->
      $scope.message = "Company saved successfully!"

  find($routeParams.id)
  $scope.findEmployees($routeParams.id)

  $scope
