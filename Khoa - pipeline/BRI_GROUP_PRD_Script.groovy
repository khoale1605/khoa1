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
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SparseCheckoutPaths', sparseCheckoutPaths: [[path: 'Documentation/DataChange/MSSQL']]], [$class: 'RelativeTargetDirectory', relativeTargetDir: 'Group']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Group.git']]])
                checkout([$class: 'GitSCM', branches: [[name: '*/BRI_GROUP_PROD_RL5.0']], doGenerateSubmoduleConfigurations: false, extensions: [[$class: 'SparseCheckoutPaths', sparseCheckoutPaths: [[path: 'Documentation/DataChange (Batch)/MSSQL']]], [$class: 'RelativeTargetDirectory', relativeTargetDir: 'Group_Batch']], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'khoale', url: 'https://github.dxc.com/kvo/integral-Group.git']]])

            }
        }
        stage('Build Libs') {
            steps {
                script {
					if (isUnix()) {
						sh label: '', script: '''DIR="${WORKSPACE}/BRILDBscript"
                            mkdir -p "${WORKSPACE}/BRILDBscript"
                            cd ${WORKSPACE}/BRILDBscript
                            rm -rf *
                            rm -rf ${WORKSPACE}/BRILDBscript.zip'''
                        
                        sh label: '', script: '''cd Group
                            GroupGIT_REVISION=$(git rev-parse HEAD)

                            if [ ! -e ${WORKSPACE}/Group/Group_revision.txt ]; then
                            echo $GroupGIT_REVISION > ${WORKSPACE}/Group/Group_revision.txt
                            fi

                            read previousrevision < Group_revision.txt

                            git diff --name-only --diff-filter=ACM $previousrevision $GroupGIT_REVISION Documentation/DataChange/MSSQL | grep -i .sql | sed \'s/^ *//\' > listGroupscriptdb.txt

                            while IFS=\'\' read -r line || [[ -n "$line" ]]; do

                                if [ -f "${WORKSPACE}/Group/$line" ] ; then
                                    cp -rf "${WORKSPACE}/Group/$line" "${WORKSPACE}/BRILDBscript" 
                                else
                                    echo "File not Exist or has been Deleted"    
                                fi		
                                
                            done < "listGroupscriptdb.txt"'''
                        
                        sh label: '', script: '''cd Group_Batch
                            Group_BatchGIT_REVISION=$(git rev-parse HEAD)

                            if [ ! -e ${WORKSPACE}/Group_Batch/Group_Batch_revision.txt ]; then
                            echo $Group_BatchGIT_REVISION > ${WORKSPACE}/Group_Batch/Group_Batch_revision.txt
                            fi

                            read previousrevision < Group_Batch_revision.txt

                            git diff --name-only --diff-filter=ACM $previousrevision $Group_BatchGIT_REVISION \'Documentation/DataChange (Batch)\' | grep -i .sql | sed \'s/^ *//\' > listGroup_Batchscriptdb.txt

                            while IFS=\'\' read -r line || [[ -n "$line" ]]; do
                                if [ -f "${WORKSPACE}/Group_Batch/$line" ] ; then
                                    cp -rf "${WORKSPACE}/Group_Batch/$line" "${WORKSPACE}/BRILDBscript"
                                else
                                    echo "File not Exist or has been Deleted"    
                                fi	
                                
                            done < "listGroup_Batchscriptdb.txt"'''
					} else {
						//bat 'clean install -Dmaven.repo.local=/var/lib/jenkins/.m2/bri-integral-repo'
                        bat 'mvn -U clean'
					}
                }
            }
        }       
    }
}