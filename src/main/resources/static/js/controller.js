
var app = angular.module('app', []);
app.controller('contr', function ($scope, $http) {

	$scope.sortType     = 'query';
	$scope.sortReverse  = false;

	//выгрузка всей таблички
	$scope.getStatAll = function () {

		$("#tableData").attr('hidden', 'true');
		$('#load').html("<div align='center'><img src='/img/load.gif' height='80' width='80' /></div>");

		$http.get("getStatAll?query=all")
				.then(function (response) {
					$scope.tablesData = response.data;
					//"Эмуляция работы сервера"
					setTimeout(function () {
						$('#load').html(" ");
						$("#tableData").removeAttr('hidden');
					}, 2000);
				}, function (response) {
					//выводим ошибку
					$('#load').html("<div align='center'><b>По данному запросу ничего не найдено.</b></div>");
				});

	};
});
