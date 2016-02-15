angular.module 'companyAdmin', ['ngRoute', 'ngResource', 'base64']
.config ($routeProvider)->
  $routeProvider
  .when("/main", {
    templateUrl: "partials/companies/index.html"
  })
  .when("/companies/:id", {
    templateUrl: "partials/companies/detail.html"
  })
  .when("/employees/:id", {
    templateUrl: "partials/employees/detail.html"
  })
  .otherwise({
    redirectTo: "/main"
  })
.run ($http, $base64) ->
  $http.defaults.headers.common['Authorization'] = 'Basic ' + $base64.encode('services' + ':' + '123456');
