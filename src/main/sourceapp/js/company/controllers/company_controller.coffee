angular.module 'companyAdmin.company.controllers'
.controller 'CompanyController', ['$scope', 'CompanyService', ($scope, CompanyService) ->
  $scope.showDescription = (companyId)->
    CompanyService.query({company: 1}).$promise.then (data)->
      if  data.length > 0 then alert(data[0].name) else alert('nothing...')
]
