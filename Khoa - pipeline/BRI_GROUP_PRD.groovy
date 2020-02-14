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
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Group.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Libs']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Libs.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'IntegralAdminFramework']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-IntegralAdminFramework.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaApp/src/main/java/com/csc/group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-app.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCDiaryApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCDiaryApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCFsuApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/resources/com/bundle/common']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-bundle.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/diary']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-diary.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/fsu']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuweb.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/smart']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-smart.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaApp/src/main/resources/com/csc/groupasia']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-spring-config.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaApp/src/main/resources/group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-screen-xml.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-web.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'ReportOut/SpoolingPrint/ENGLISH/pdfTemplate/common']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-common-report.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCIntegralExtension']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCIntegralExtension.git']]])

            }
        }
        stage('Build Libs') {
            steps {
				script {
					/*if (isUnix()) {
						sh label: '', script: '''cd C:\\Program Files (x86)\\Jenkins\\workspace\\OK\\CSCGroupAsiaWeb\\target\\CSCGroupAsiaWeb\\target
                            mv csc-group-asia-web-1.0-SNAPSHOT.war CSCGroupAsiaWeb.war
                            cd C:\\Program Files (x86)\\Jenkins\\workspace\\OK\\CSCSmart400Batch\\target
                            rm -rf csc-smart400-batch.zip
                            7z a csc-smart400-batch.zip csc-smart400-batch
                            cd C:\\Program Files (x86)\\Jenkins\\workspace\\OK
                            rm -rf ReportOut.zip
                            7z a ReportOut.zip ReportOut'''
					    } 
                    else*/{
                        bat label: '', script: '''cd C:\\Program Files (x86)\\Jenkins\\workspace\\OK\\CSCGroupAsiaWeb\\target
                            rename "csc-group-asia-web-1.0-SNAPSHOT.war" "CSCGroupAsiaWeb.war"
                            cd C:\\Program Files (x86)\\Jenkins\\workspace\\OK\\CSCSmart400Batch\\target
                            del csc-smart400-batch.zip
                            7z a csc-smart400-batch.zip "csc-smart400-batch"
                            cd C:\\Program Files (x86)\\Jenkins\\workspace\\OK
                            del ReportOut.zip
                            7z a ReportOut.zip "ReportOut"'''
                        //bat label: '', script: 'clean install -Dmaven.repo.local=/var/lib/jenkins/.m2/bri-integral-repo'
						bat label: '', script: 'mvn clean install'
                        
					}
                }
            }
        }
    }       
}






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
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Group.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'Libs']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Libs.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'IntegralAdminFramework']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-IntegralAdminFramework.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaApp/src/main/java/com/csc/group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-app.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCDiaryApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCDiaryApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCFsuApp']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuApp.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/resources/com/bundle/common']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-bundle.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/diary']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-diary.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/fsu']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCFsuweb.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_COMMON_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/smart']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-smart.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaApp/src/main/resources/com/csc/groupasia']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-spring-config.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaApp/src/main/resources/group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-screen-xml.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCGroupAsiaWeb/src/main/webapp/group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-group-web.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'ReportOut/SpoolingPrint/ENGLISH/pdfTemplate/common']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-common-report.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'CSCIntegralExtension']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-CSCIntegralExtension.git']]])

            }
        }
        stage('Build Libs') {
            steps {
                bat label: '', script: '''cd %WORKSPACE%\\OK\\CSCGroupAsiaWeb\\target
                        rename "csc-group-asia-web-1.0-SNAPSHOT.war" "CSCGroupAsiaWeb.war"
                        cd %WORKSPACE%\\OK\\CSCSmart400Batch\\target
                        del csc-smart400-batch.zip
                        7z a csc-smart400-batch.zip "csc-smart400-batch"
                        cd %WORKSPACE%\\OK
                        del ReportOut.zip
                        7z a ReportOut.zip "ReportOut"'''
                //bat label: '', script: 'clean install -Dmaven.repo.local=/var/lib/jenkins/.m2/bri-integral-repo'
				bat label: '', script: 'mvn clean install'
			}
        }
    }
}       

