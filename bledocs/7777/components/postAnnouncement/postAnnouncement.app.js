angular.module('compiler.postAnnouncement', [])

  .controller('postAnnouncementController', function($scope) {
    console.log("postAnnouncementController " + "start");

    $scope.text = "Hey bitch xoxo";
    
    console.log("postAnnouncementController " + "end");
  });
