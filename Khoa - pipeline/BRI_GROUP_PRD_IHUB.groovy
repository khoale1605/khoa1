pipeline {
    agent any
	options {
        buildDiscarder(logRotator(numToKeepStr: '3', artifactNumToKeepStr: '3', daysToKeepStr: '3'))
    }
    tools {
       maven "mvn"
       jdk "jdk"
    }
    stages {
        stage ('Clone Code') {
            steps {                
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'dnguyen217_bri', url: 'https://github.dxc.com/BRI/bri-Ihub.git']]])

            }
        }
        stage('Build Libs') {
            steps {
					//bat 'clean install -Dmaven.repo.local=/var/lib/jenkins/.m2/bri-integral-repo'
                    //bat 'mvn -U clean'
                    bat 'mvn clean install -DskipTests=true -U'
			}
        }
    }
}       
