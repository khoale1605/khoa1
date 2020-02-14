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
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SubmoduleOption', disableSubmodules: false, parentCredentials: true, recursiveSubmodules: true, reference: '', timeout: 60, trackingSubmodules: true]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Life.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'IntegralAdminFramework']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-IntegralAdminFramework.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCDiaryApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCDiaryApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCFsuApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCIwaApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCIwaApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Libs']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Libs.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCLifeAsiaWeb/src/main/webapp/fsu']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuweb.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCLifeAsiaWeb/src/main/webapp/smart']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-smart.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCLifeAsiaWeb/src/main/resources/com/bundle/commonBundle']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-bundle.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCIntegralExtension']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCIntegralExtension.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CustomerAA']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CustomerAA.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'integral-common-modules']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-common-modules.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CustomerBRI']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CustomerBRI.git']]])

            }
        }
        stage('Build Libs') {
            steps {
                script {
					if (isUnix()) {
						sh label: '', script: '''cd ${WORKSPACE}/CustomerBRI/CustomerBRIWeb/target
                            mv CustomerBRIWeb-1.0-SNAPSHOT.war CustomerBRIWeb.war
                            cd ${WORKSPACE}/CSCSmart400Batch/target
                            rm -rf csc-smart400-batch.zip
                            7z a csc-smart400-batch.zip csc-smart400-batch
                            cd ${WORKSPACE}
                            rm -rf ReportOut.zip
                            7z a ReportOut.zip ReportOut'''
					} else {
						//bat 'mvn -U clean'
                        bat 'mvn clean install -U -PCustomerBRI'
					}
                }
            }
        }       
    }
}