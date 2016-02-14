(function() {
  angular.module('companyAdmin', ['ngRoute', 'ngResource']);

  angular.module('companyAdmin').controller('CompanyController', function($scope) {
    $scope.name = "Company Admin 2...";
    return $scope;
  });

}).call(this);
