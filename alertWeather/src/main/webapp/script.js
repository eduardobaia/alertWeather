// Code goes here

var app = angular.module('app', []);


app.controller('mainApp', function ($scope, $http) {

    var onUserComplete = function (response) {
        $scope.user = response.data;
     

    }

    var onError = function (reason) {
        console.log(reason);
        $scope.error = "Could not to fath the user api"
    };


    $http.get("http://api.openweathermap.org/data/2.5/weather?q=Recife,Br&appid=856945123d9e1f73808eb8b6297e58f5")
        .then(onUserComplete, onError);

 

 $scope.alterarTempo = function (cidade, pais){
    $http.get("http://api.openweathermap.org/data/2.5/weather?q="+cidade+","+ pais+"Br&appid=856945123d9e1f73808eb8b6297e58f5")
        .then(function (response){
            $scope.user = response.data;
               console.log("Achou a cidade:"+ cidade +" pais:"+ pais);
        }, function(erro){
   $scope.erro = " Não foi possivel localizar a cidade/pais escolhido!"
   console.log("Entrou no erro! ");
        });
};




});
