angular.module('companyAdmin', ['companyModule']);

angular.module('companyModule', ['companyAdmin.company.factories', 'companyAdmin.company.controllers']);

angular.module('companyAdmin.company.factories', ['ngResource']).factory('CompanyService', [
    '$resource', '$location', function ($resource, $location) {
        return $resource("/api/v1/companies/:id", {
            id: '@id'
        });
    }
]);


angular.module('companyAdmin.company.controllers', ['companyAdmin.company.factories']);

angular.module('companyAdmin.company.controllers').controller('CompanyController', [
    '$scope', 'CompanyService', function ($scope, CompanyService) {
        return $scope.showDescription = function (companyId) {
            return CompanyService.query({
                company: 1
            }).$promise.then(function (data) {
                if (data.length > 0) {
                    return alert(data[0].name);
                } else {
                    return alert('nothing...');
                }
            });
        };
    }
]);

angular.module('companyAdmin.company.controllers').controller('ListCompaniesController', [
    '$scope', function ($scope) {
        return console.log('Teste ListCompaniesController...');
    }
]);


