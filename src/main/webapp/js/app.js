(function() {
  angular.module('companyAdmin', ['ngRoute', 'ngResource', 'base64']).config(function($routeProvider) {
    return $routeProvider.when("/main", {
      templateUrl: "partials/company/index.html"
    }).when("/companies/:id", {
      templateUrl: "partials/company/detail.html"
    }).otherwise({
      redirectTo: "/main"
    });
  }).run(function($http, $base64) {
    return $http.defaults.headers.common['Authorization'] = 'Basic ' + $base64.encode('services' + ':' + '123456');
  });

  angular.module('companyAdmin').controller('CompanyDetailController', function($scope, $http, $routeParams) {
    var find, onComplete, onError;
    $scope.company = {};
    $scope.message = "";
    $scope.edit = false;
    onComplete = function(response) {
      $scope.company = response.data;
      return $scope;
    };
    onError = function(reason) {
      $scope.message = "Could not get the companies! " + reason;
      return $scope;
    };
    $scope.findEmployees = function(id) {
      return $http.get("/api/v1/employees/company/" + id).then(function(response) {
        return $scope.employees = response.data.content;
      });
    };
    $scope.enableEdit = function() {
      return $scope.edit = true;
    };
    find = function(id) {
      return $http.get("/api/v1/companies/" + id).then(onComplete, onError);
    };
    $scope.save = function() {
      var req;
      req = {
        method: "PUT",
        url: "/api/v1/companies/" + $scope.company.id,
        headers: {
          'Content-Type': 'application/json'
        },
        data: $scope.company
      };
      return $http(req).then(function() {
        return $scope.message = "Company saved successfully!";
      });
    };
    find($routeParams.id);
    $scope.findEmployees($routeParams.id);
    return $scope;
  });

  angular.module('companyAdmin').controller('CompanyIndexController', function($scope, $http, $location) {
    var onComplete, onError;
    $scope.companies = [];
    $scope.searchTerm = "";
    onComplete = function(response) {
      $scope.companies = response.data.content;
      if ($scope.companies.length === 0) {
        $scope.message = "Could not find any company with this term " + $scope.searchTerm + ".";
      }
      return $scope;
    };
    onError = function(reason) {
      $scope.message = "Could not get the companies! " + reason;
      return $scope;
    };
    $scope.viewDetail = function(id) {
      return $location.path("/companies/" + id);
    };
    $scope.search = function(searchTerm) {
      $scope.message = "";
      return $http.get("/api/v1/companies?name=" + searchTerm).then(onComplete, onError);
    };
    return $scope;
  });

}).call(this);
