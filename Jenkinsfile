pipeline {
  agent { label 'migration' }
  
  tools {
        maven 'apache-maven-latest'
        jdk 'oracle-jdk8-latest'
  }
  environment {
    SSH_ACCOUNT = "genie.capella@projects-storage.eclipse.org";
    
    PRODUCT_BRANCH = "master"
    PRODUCT_KEY = "${env.PRODUCT_BRANCH}".replaceFirst(/^v/, "");
	
    BUILD_KEY = "${PRODUCT_KEY}-${BRANCH_NAME}".replaceAll('/','-');
    PRODUCT_DIR = "/home/data/httpd/download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}";
    UPDATE_DIR = "/home/data/httpd/download.eclipse.org/capella/core/updates/nightly/${env.BUILD_KEY}";
    
    PRODUCT_DIRN = "/home/data/httpd/download.eclipse.org/capella/core/products/nightly/${env.PRODUCT_KEY}";
    UPDATE_DIRN = "/home/data/httpd/download.eclipse.org/capella/core/updates/nightly/${env.PRODUCT_KEY}";
    
    GIT_REFSPEC = "${env.FROM_GITHUB}".replace("true", "+refs/pull/${env.PULL_REQUEST}/head:refs/remotes/origin/pr/${env.PULL_REQUEST}/merge").replace("false", "+refs/heads/${env.GIT_BRANCH}/head:refs/remotes/origin/${env.GIT_BRANCH}");
    
    MAVEN_SIGN = "${env.FROM_GITHUB}".replace("true", " ").replace("false", "-Psign");
    MAVEN_SONAR = "${env.FROM_GITHUB}".replace("true", "-DSKIP_SONAR=true").replace("false", " ");
  }
  
  stages {
    
    stage('Generate target platform') {
      steps {
        script { currentBuild.description = "${env.BUILD_KEY}" } 
        sh 'env'
        sh "mvn clean verify -f releng/plugins/org.polarsys.capella.targets/pom.xml"
      }
    }
    
    stage('Package') {
      steps {
		sh "mvn ${env.MAVEN_SONAR} -Djacoco.skip=true -DjavaDocPhase=none -Pfull ${env.MAVEN_SIGN} clean package -f pom.xml"
      }
    }
    
    stage('Archive to download.eclipse') {
      steps {
        //archiveArtifacts artifacts: 'releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.zip,releng/plugins/org.polarsys.capella.rcp.site/target/org.polarsys.capella.rcp-*.zip,releng/plugins/org.polarsys.capella.rcp.site/target/repository/**,releng/plugins/org.polarsys.capella.egf.site/target/repository/**,releng/plugins/org.polarsys.capella.richtext.site/target/repository/**,releng/plugins/org.polarsys.capella.test.site/target/org.polarsys.capella.test.site-*.zip,releng/plugins/org.polarsys.capella.test.site/target/repository/**,tests/plugins/org.polarsys.capella.test.suite.inui.ju/target/work/data/.metadata/.log'
        echo "${PRODUCT_DIR}/${BUILD_KEY}"
        
        sh "cd \"$WORKSPACE/releng/plugins/org.polarsys.capella.rcp.product/target/products/\"; ls -1 capella-*.zip | head -1 > $WORKSPACE/build.txt"
        sh "cat $WORKSPACE/build.txt"
        
        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
            sh "ssh $SSH_ACCOUNT mkdir -p ${PRODUCT_DIR}"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.zip $SSH_ACCOUNT:${PRODUCT_DIR}"
            sh "scp -rp $WORKSPACE/build.txt $SSH_ACCOUNT:${PRODUCT_DIR}"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIR}/org.polarsys.capella.test.site"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.test.site/target/repository/** $SSH_ACCOUNT:${UPDATE_DIR}/org.polarsys.capella.test.site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIR}/org.polarsys.capella.egf.site"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.egf.site/target/repository/** $SSH_ACCOUNT:${UPDATE_DIR}/org.polarsys.capella.egf.site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIR}/org.polarsys.capella.rcp.site"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.rcp.site/target/repository/** $SSH_ACCOUNT:${UPDATE_DIR}/org.polarsys.capella.rcp.site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIR}/targets"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.targets/full/** $SSH_ACCOUNT:${UPDATE_DIR}/targets"
            
        }
        
        script { currentBuild.description = "${env.BUILD_KEY} - <a href=\"https://download.eclipse.org/capella/core/updates/nightly/${env.BUILD_KEY}\">site</a> - <a href=\"https://download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}\">product</a>" } 
      }
    }
    
    stage('Update nightly fixed url') {
      //when {
      //  expression { return "${env.FROM_GITHUB}".contains("false") } 
      //}
      steps {
        
        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
            
            sh "ssh $SSH_ACCOUNT rm -rf ${PRODUCT_DIRN}"
            sh "ssh $SSH_ACCOUNT rm -rf ${UPDATE_DIRN}"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${PRODUCT_DIRN}"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.zip $SSH_ACCOUNT:${PRODUCT_DIRN}"
            sh "scp -rp $WORKSPACE/build.txt $SSH_ACCOUNT:${PRODUCT_DIRN}"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIRN}/org.polarsys.capella.test.site"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.test.site/target/repository/** $SSH_ACCOUNT:${UPDATE_DIRN}/org.polarsys.capella.test.site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIRN}/org.polarsys.capella.egf.site"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.egf.site/target/repository/** $SSH_ACCOUNT:${UPDATE_DIRN}/org.polarsys.capella.egf.site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIRN}/org.polarsys.capella.rcp.site"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.rcp.site/target/repository/** $SSH_ACCOUNT:${UPDATE_DIRN}/org.polarsys.capella.rcp.site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p ${UPDATE_DIRN}/targets"
            sh "scp -rp $WORKSPACE/releng/plugins/org.polarsys.capella.targets/full/** $SSH_ACCOUNT:${UPDATE_DIRN}/targets"
            
        }
      }
    }
    
    //stage('Launch tests') {
      //when {
      //  expression { return "${env.FROM_GITHUB}".contains("true") } 
      //}
      //steps {
      //  build job: 'capella-product-tests', wait: false, parameters: [[$class: 'StringParameterValue', name: 'GIT_BRANCH', value: "${env.PRODUCT_BRANCH}"], [$class: 'StringParameterValue', name: 'BUILD_KEY', value: "${env.BUILD_KEY}"]]
      //}
    //}
  }
}