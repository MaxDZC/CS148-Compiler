angular.module('compiler.login', [])

  .controller('loginController', function($scope) {
    console.log("loginController " + "start");

    $scope.text = "Hey bitch xoxo";
    
    console.log("loginController " + "end");
  });
