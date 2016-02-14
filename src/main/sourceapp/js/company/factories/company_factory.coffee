angular.module 'companyAdmin.company.factories', ['ngResource']
.factory 'CompanyService', ['$resource', '$location', ($resource, $location) ->
  return $resource("/api/v1/companies/:id", {id: '@id'})
]
