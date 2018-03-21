var app = angular.module('compiler', [
  'ngRoute',
  'compiler.login',
  'compiler.userAnnouncements',
  'compiler.viewAnnouncements',
  'compiler.signup',
  'compiler.postAnnouncement'
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
