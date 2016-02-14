'use strict';

module.exports = function (grunt) {
    require('time-grunt')(grunt);
    require('jit-grunt')(grunt);
    grunt
        .initConfig({
            app: {
                source: 'src/main/sourceapp',
                dist: 'src/main/webapp'
            },
            clean: {
                dist: ['.tmp', '<%= app.dist %>/css', '<%= app.dist %>/fonts', '<%= app.dist %>/js']
            },
            copy: {
                main: {
                    files: [
                        {
                            expand: true,
                            flatten: true,
                            src: ['bower_components/bootstrap/dist/css/*.min.css'],
                            dest: '<%= app.dist %>/css',
                            filter: 'isFile'
                        },
                        {
                            expand: true,
                            flatten: true,
                            src: ['bower_components/bootstrap/dist/fonts/*'],
                            dest: '<%= app.dist %>/fonts',
                            filter: 'isFile'
                        }
                    ],
                },
            },
            coffee: {
                compileJoined: {
                    options: {
                        join: true
                    },
                    files: {
                        'src/main/webapp/js/app.js': ['<%= app.source %>/js/**/*.coffee'] // concat then compile into single file
                    }
                },
            },
            uglify: {
                server: {
                    options: {
                        mangle: false,
                        beautify: true
                    },
                    files: {
                        '<%= app.dist %>/js/scripts.js': [
                            'bower_components/jquery/dist/jquery.js',
                            'bower_components/cofee-script/extras/coffee-script.js',
                            'bower_components/angular/angular.js',
                            'bower_components/angular-resource/angular-resource.js',
                            'bower_components/angular-route/angular-route.js',
                            'bower_components/bootstrap/dist/js/bootstrap.js'
                        ]
                    }
                },
                dist: {
                    options: {
                        compress: true,
                        preserveComments: false,
                        report: 'min'
                    },
                    files: {
                        '<%= app.dist %>/js/scripts.js': [
                            'bower_components/jquery/dist/jquery.js',
                            'bower_components/cofee-script/extras/coffee-script.js',
                            'bower_components/angular/angular.js',
                            'bower_components/angular-resource/angular-resource.js',
                            'bower_components/angular-route/angular-route.js',
                            'bower_components/bootstrap/dist/js/bootstrap.js'
                        ]
                    }
                }
            }
        });

    grunt.registerTask('build', ['clean', 'copy:main',
        'uglify:dist', 'coffee:compileJoined'
    ]);

    grunt.registerTask('default', ['build']);
};
