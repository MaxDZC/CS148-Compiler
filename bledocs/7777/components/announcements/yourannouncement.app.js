angular.module('compiler.yourAnnouncements', [])

  .controller('yourAnnouncementsController', function($scope) {
    console.log("yourAnnouncementsController " + "start");

    $scope.text = "Hey bitch xoxo";
    
    console.log("yourAnnouncementsController " + "end");
  });
