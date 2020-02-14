pipeline {
    agent any
    tools {
        maven 'mvn'
        jdk 'jdk'
    }
    stages {
        stage ('Build Stage') {
            steps {
                withMaven(maven : 'mvn') {
                    bat 'mvn clean install'
                }
            }
        }
        stage ('Test Stage') {
            steps {
                withMaven(maven : 'mvn') {
                    bat 'mvn clean install'
                }
            }
        }
        stage ('Deployment Stage') {
            steps {
                withMaven(maven : 'mvn') {
                    bat 'mvn clean install'
                }
            }
        }
    }
}
