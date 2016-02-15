angular.module 'companyAdmin'
.controller 'CompanyIndexController', ($scope, $http, $location) ->
  $scope.companies = []
  $scope.searchTerm = ""

  onComplete = (response) ->
    $scope.companies = response.data.content
    if $scope.companies.length == 0
      $scope.message = "Could not find any company with this term #{$scope.searchTerm}."
    $scope

  onError = (reason) ->
    $scope.message = "Could not get the companies! #{reason}"
    $scope

  $scope.viewDetail = (id) ->
    $location.path "/companies/#{id}"

  $scope.search = (searchTerm) ->
    $scope.message = ""
    $http.get("/api/v1/companies?name=#{searchTerm}").then onComplete, onError

  $scope
