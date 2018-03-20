angular.module('compiler.viewAnnouncements', [])

  .controller('viewAnnouncementsController', function($scope) {
    console.log("viewAnnouncementsController " + "start");

    $scope.text = "VIEW ANNOUNCEMENTS";
    
    console.log("viewAnnouncementsController " + "end");
  });
