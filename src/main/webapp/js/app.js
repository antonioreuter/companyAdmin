(function() {
  angular.module('companyAdmin', ['ngRoute', 'ngResource', 'base64']).config(function($routeProvider) {
    return $routeProvider.when("/main", {
      templateUrl: "partials/companies/index.html"
    }).when("/companies/new", {
      templateUrl: "partials/companies/new.html"
    }).when("/companies/:id", {
      templateUrl: "partials/companies/detail.html"
    }).when("/companies/:id/employees/new", {
      templateUrl: "partials/employees/new.html"
    }).when("/employees/:id", {
      templateUrl: "partials/employees/detail.html"
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
    $scope.companyId = $routeParams.id;
    onComplete = function(response) {
      $scope.company = response.data;
      return $scope;
    };
    onError = function(reason) {
      $scope.message = "Could not get the company! " + reason;
      return $scope;
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
        $scope.message = "Company saved successfully!";
        return $scope.edit = false;
      });
    };
    find($scope.companyId);
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
    $scope.newCompany = function() {
      return $location.path("/companies/new");
    };
    $scope.search = function(searchTerm) {
      $scope.message = "";
      return $http.get("/api/v1/companies?name=" + searchTerm).then(onComplete, onError);
    };
    return $scope;
  });

  angular.module('companyAdmin').controller('CompanyNewController', function($scope, $http, $location) {
    var onComplete, onError;
    $scope.company = {};
    $scope.edit = true;
    $scope.message = "";
    onComplete = function(response) {
      return $location.path("/companies/" + response.data.id);
    };
    onError = function(response) {
      $scope.message = "Sorry! We couldn't save the company! Try again.";
      return $scope;
    };
    $scope.save = function() {
      var req;
      req = {
        method: "POST",
        url: "/api/v1/companies",
        headers: {
          'Content-Type': 'application/json'
        },
        data: $scope.company
      };
      return $http(req).then(onComplete, onError);
    };
    return $scope;
  });

  angular.module('companyAdmin').controller('EmployeeDetailController', function($scope, $http, $routeParams) {
    var find, onComplete, onError;
    $scope.employee = {};
    $scope.message = "";
    $scope.edit = false;
    $scope.employeeId = $routeParams.id;
    onComplete = function(response) {
      $scope.employee = response.data;
      return $scope;
    };
    onError = function(reason) {
      $scope.message = "Could not get the employee! " + reason;
      return $scope;
    };
    $scope.enableEdit = function() {
      return $scope.edit = true;
    };
    find = function(id) {
      return $http.get("/api/v1/employees/" + id).then(onComplete, onError);
    };
    $scope.save = function() {
      var req;
      req = {
        method: "PUT",
        url: "/api/v1/employees/" + $scope.employee.id,
        headers: {
          'Content-Type': 'application/json'
        },
        data: $scope.employee
      };
      return $http(req).then(function() {
        $scope.message = "Employee saved successfully!";
        return $scope.edit = false;
      });
    };
    find($scope.employeeId);
    return $scope;
  });

  angular.module('companyAdmin').controller('EmployeeIndexController', function($scope, $http, $location) {
    var onComplete, onError;
    $scope.employees = [];
    $scope.searchTerm = "";
    onComplete = function(response) {
      $scope.employees = response.data.content;
      if ($scope.employees.length === 0) {
        $scope.message = "Could not find any employee.";
      }
      return $scope;
    };
    onError = function(reason) {
      $scope.message = "Could not get the companies! " + reason;
      return $scope;
    };
    $scope.viewDetail = function(id) {
      return $location.path("/employees/" + id);
    };
    $scope.newEmployee = function() {
      return $location.path("/companies/" + $scope.companyId + "/employees/new");
    };
    $scope.searchByCompany = function(companyId) {
      $scope.message = "";
      return $http.get("/api/v1/employees/company/" + companyId).then(onComplete, onError);
    };
    $scope.searchByCompany($scope.companyId);
    return $scope;
  });

  angular.module('companyAdmin').controller('EmployeeNewController', function($scope, $http, $location, $routeParams) {
    var onComplete, onError;
    $scope.edit = true;
    $scope.message = "";
    $scope.employee = {
      company: {
        id: $routeParams.id
      }
    };
    onComplete = function(response) {
      return $location.path("/companies/" + response.data.company.id);
    };
    onError = function(response) {
      $scope.message = "Sorry! We couldn't save the employee! Try again.";
      return $scope;
    };
    $scope.save = function() {
      var req;
      req = {
        method: "POST",
        url: "/api/v1/employees",
        headers: {
          'Content-Type': 'application/json'
        },
        data: $scope.employee
      };
      return $http(req).then(onComplete, onError);
    };
    return $scope;
  });

}).call(this);
