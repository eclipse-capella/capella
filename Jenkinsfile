pipeline {
	agent {
		label 'migration'
	}
  
	tools {
		maven 'apache-maven-latest'
		jdk 'oracle-jdk8-latest'
	}
  
	environment {
		BUILD_KEY = (github.isPullRequest() ? CHANGE_TARGET : BRANCH_NAME).replaceFirst(/^v/, '')
		CAPELLA_PRODUCT_PATH = "${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products/org.polarsys.capella.rcp.product/linux/gtk/x86_64/eclipse"
  	}
  
  	stages {
  	
		stage('Generate Target Platform') {
	    	steps {        
	        	script { 
		        	if(github.isPullRequest()){
		        	    github.buildStartedComment()
		        	}
		
		        	currentBuild.description = BUILD_KEY
		        	
		        	sh 'env'
		        	sh 'mvn clean verify -f releng/plugins/org.polarsys.capella.targets/pom.xml'
	       		}         
	     	}
	    }
	    
    	stage('Build and Package') {
      		steps {
      			script {
      				def customParams = github.isPullRequest() ? '-DSKIP_SONAR=true' : '-Psign'
      	    
      	    		sh "mvn -Djacoco.skip=true -DjavaDocPhase=none -Pfull ${customParams} clean package -f pom.xml"
	       		}         
	     	}
	    }
    
		stage('Deploy to Nightly') {
      		steps {
				script {		
		    		def deploymentDirName = 
		    			(github.isPullRequest() ? "${BUILD_KEY}-${BRANCH_NAME}-${BUILD_ID}" : "${BRANCH_NAME}-${BUILD_ID}")
		    			.replaceAll('/','-')		
		
				    deployer.capellaNightlyProduct("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.zip", deploymentDirName)
				    
				    deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.test.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.test.site")
				    deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.egf.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.egf.site")
				    deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.rcp.site")
				    deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.targets/full/*", "${deploymentDirName}/targets")
				    
				    currentBuild.description = "${deploymentDirName} - <a href=\"https://ci-staging.eclipse.org/capella/view/Capella/job/capella-product/\">build</a> - <a href=\"https://download.eclipse.org/capella/core/products/nightly/${deploymentDirName}\">product</a>" 				
	       		}         
	     	}
	    }
    
    	stage('Deploy to Nightly Root') {      	
    		when {
	       		expression { 
	        		!github.isPullRequest()
	        	}
      		}
      			
      		steps {      			
      			script {
			        def deploymentDirName = BUILD_KEY		
			
					deployer.cleanCapellaNightlyArtefacts(deploymentDirName)
					
					deployer.capellaNightlyProduct("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.zip", deploymentDirName)
					    
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.test.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.test.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.egf.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.egf.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.rcp.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.targets/full/*", "${deploymentDirName}/targets")
			      
			      	currentBuild.description = "${BUILD_KEY} - <a href=\"https://ci-staging.eclipse.org/capella/view/Capella/job/capella-product/\">build</a> - <a href=\"https://download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}\">product</a>" } 
			}
		}

    	stage('Install test features') {
    		when {
	        	expression { 
	        		github.isPullRequest()
	        	}
	      	}
    	
        	steps {        	
        		script {
	        		sh "chmod 755 ${CAPELLA_PRODUCT_PATH}"
	        		
	        		eclipse.installFeature("${CAPELLA_PRODUCT_PATH}", 'http://download.eclipse.org/tools/orbit/downloads/drops/R20130827064939/repository', 'org.jsoup')	        		
	        		eclipse.installFeature("${CAPELLA_PRODUCT_PATH}", "file:/${WORKSPACE}/releng/plugins/org.polarsys.capella.test.site/target/repository".replace("\\", "/"), 'org.polarsys.capella.test.feature.feature.group')
	       		}         
	     	}
	    }
	    
	}
  
	post {
    	always {
       		archiveArtifacts artifacts: '**/*.log, *.log'
    	}
    	
    	success  {
    		script {
    			if(github.isPullRequest()){
        			github.buildSuccessfullComment()
        		}
        	}
    	}
    	
	    unstable {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildUnstableComment()
	        	}
	        }
	    }
    
	    failure {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildFailedComment()
	        	}
	        }
	    }
	    
	    aborted {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildAbortedComment()
	        	}
	        }
	    }
	}
}