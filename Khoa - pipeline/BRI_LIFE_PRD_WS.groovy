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
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SubmoduleOption', disableSubmodules: false, parentCredentials: true, recursiveSubmodules: true, reference: '', timeout: 90, trackingSubmodules: true]], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-IntegralServiceEnablement.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Life.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/product']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/insurance/integral-Group.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/product']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Polisy']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/insurance/integral-polisy.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/product']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/ITS']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/insurance/integral-integralTableServices.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life/CustomerBRI']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CustomerBRI.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life/CSCDiaryApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCDiaryApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life/IntegralAdminFramework']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-IntegralAdminFramework.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life/CSCFsuApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life/CSCIwaApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCIwaApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_LIFE_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Implementation/IServices/IntegralAdmin/Life/Libs']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Libs.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'devops']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/insurance/ife-continous-delivery.git']]])

            }
        }
        stage('Build Libs') {
            steps {
                //script {
					//if (isUnix()) {
						//sh label: '', script: '''echo "make new file on repo"
                            //cd ${WORKSPACE}
                           //cp devops/life-uat-ws/logback.xml \'Implementation/IServices/Source Code/src/main/resources/logback.xml\''''
					//} 
                    //else {
						//bat "clean install -Plife-soap-services -Dimportfsu=true -Drampartsecurity=false"
                        bat 'mvn -U clean'
					}
                }
            }
        }       
    //}
//}