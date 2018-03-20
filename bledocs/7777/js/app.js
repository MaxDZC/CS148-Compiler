var app = angular.module('compiler', [
  'ngRoute',
  'compiler.login',
  'compiler.signup',
  'compiler.postAnnouncement',
  'compiler.userAnnouncements'
]);

app.config(function ($routeProvider){  
  $routeProvider   
    .when('/login', {
      templateUrl: '/components/login/login.html',
      controller: 'loginController'
    })   
    .when('/signup', {
      templateUrl: '/components/signup/signup.html',
      controller: 'signupController'
    })    
    .when('/postAnnouncement', {
      templateUrl: '/components/postAnnouncement/postAnnouncement.html',
      controller: 'postAnnouncementController'
    }) 
    .when('/', {
      templateUrl: 'components/announcements/announcements.html',
      controller: 'userAnnouncementsController'
    })
    .otherwise({
      redirectTo: '/'
    });  
});
