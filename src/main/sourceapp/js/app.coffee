angular.module 'companyAdmin', ['ngRoute', 'ngResource', 'base64']
.config ($routeProvider)->
  $routeProvider
  .when("/main", {
    templateUrl: "partials/company/index.html"
  })
  .when("/companies/:id", {
    templateUrl: "partials/company/detail.html"
  })
  .otherwise({
    redirectTo: "/main"
  })
.run ($http, $base64) ->
  $http.defaults.headers.common['Authorization'] = 'Basic ' + $base64.encode('services' + ':' + '123456');
