var app = angular.module('compiler', [
  'ngRoute',
  'compiler.login',
  'compiler.userAnnouncements'
]);

app.config(function ($routeProvider){  
  $routeProvider   
    .when('/login', {
      templateUrl: '/components/login/login.html',
      controller: 'loginController'
    }) 
    .when('/', {
      templateUrl: 'components/announcements/announcements.html',
      controller: 'userAnnouncementsController'
    })
    .otherwise({
      redirectTo: '/'
    });  
});
