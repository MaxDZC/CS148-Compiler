angular.module('compiler.userAnnouncements', [])

  .controller('userAnnouncementsController', function($scope) {
    console.log("userAnnouncementsController " + "start");

    $scope.text = "Hey bitch xoxo";
    
    console.log("userAnnouncementsController " + "end");
  });
