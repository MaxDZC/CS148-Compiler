var app = angular.module('compiler', [
  'ngRoute',
  'compiler.login',
  'compiler.userAnnouncements',
  'compiler.viewAnnouncements'
]);

app.config(function ($routeProvider){  
  $routeProvider   
    .when('/login', {
      templateUrl: '/components/login/login.html',
      controller: 'loginController'
    }) 
    .when('/viewAnnouncements', {
      templateUrl: '/components/announcements/viewAnnouncements.html',
      controller: 'viewAnnouncementsController'
    }) 
    .when('/', {
      templateUrl: '/components/announcements/announcements.html',
      controller: 'userAnnouncementsController'
    })
    .otherwise({
      redirectTo: '/'
    });  
});
