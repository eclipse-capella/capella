pipeline {
	agent {
		kubernetes {
			inheritFrom 'ubuntu-2404'
			yaml """
spec:
  containers:
  - name: jnlp
    resources:
      requests:
        memory: "6Gi"
      limits:
        memory: "6Gi"
"""
		}
	}

	options {
		timeout(time: 8, unit: 'HOURS')
	}
	
	tools {
		maven 'apache-maven-latest'
		jdk 'openjdk-jdk21-latest'
	}
  
	environment {
		BUILD_KEY = (github.isPullRequest() ? CHANGE_TARGET : BRANCH_NAME).replaceFirst(/^v/, '')
		CAPELLA_PRODUCT_PATH = "${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products/org.polarsys.capella.rcp.product/linux/gtk/x86_64/capella"
		PACKAGE_JDK_VERSION = '21'
  	}
  
  	stages {
  	
		stage('Generate Target Platform') {
	    	steps {        
	        	script { 
		        	if(github.isPullRequest()){
		        	    github.buildStartedComment()
		        	    
		        	    github.removeCustomPullRequestLabels()
		            	github.buildStartedLabel()
		        	}
		
		        	currentBuild.description = BUILD_KEY
		        	
		        	sh 'env'
		        	sh 'mvn clean verify -f releng/plugins/org.polarsys.capella.targets/pom.xml'
	       		}
	     	}
	    }
	    
	    stage('Download JDK') {
	    	steps {        
	        	script { 
	        		def os = 'win'
	        		def jre = 'JRE'
		            def jdkWinFolder = os + jre
		            downloader.retrieveTemurinJDK(jdkWinFolder, env.PACKAGE_JDK_VERSION, os)
		            
	        		os = 'linux'
		            def jdkLinuxFolder = os + jre
		            downloader.retrieveTemurinJDK(jdkLinuxFolder, env.PACKAGE_JDK_VERSION, os)
		            
	        		os = 'mac'
		            def jdkMacFolder = os + jre
		            downloader.retrieveTemurinJDK(jdkMacFolder, env.PACKAGE_JDK_VERSION, os)
		            
		            os = 'mac-aarch64'
		            def jdkMacSiliconFolder = os + jre
		            downloader.retrieveTemurinJDK(jdkMacSiliconFolder, env.PACKAGE_JDK_VERSION, os)
		            
		            os = 'linux-aarch64'
		            def jdkLinuxSiliconFolder = os + jre
		            downloader.retrieveTemurinJDK(jdkLinuxSiliconFolder, env.PACKAGE_JDK_VERSION, os)
	       		}
	     	}
	    }

		stage('Build and Package') {
			steps {
				script {
					withEnv(['MAVEN_OPTS=-Xmx3g']) {
						def sign = github.isPullRequest() ? '' : '-Psign'
						sh "mvn verify -Pfull ${sign}"
					}
				}
		 	}
		}

		stage('Notarize macOS products') {
			when {
				expression { !github.isPullRequest() }
			}
			options {
				timeout(time: 90, unit: 'MINUTES')
			}
			steps {
				macOsNotarizer(
					"${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products",
					[
						'capella-*-macosx-cocoa-x86_64.dmg',
						'capella-*-macosx-cocoa-aarch64.dmg'
					]
				)
			}
		}

		stage('Generate product checksums') {
			when {
				expression { !github.isPullRequest() }
			}
			steps {
				sh '''#!/usr/bin/env bash
set -euo pipefail
products="$WORKSPACE/releng/plugins/org.polarsys.capella.rcp.product/target/products"
shopt -s nullglob
# Reused workspaces and the EPP notarizer may leave obsolete checksum files.
rm -f -- "$products"/capella-*.{md5,sha1,sha256,sha512} "$products/SHA256SUMS" "$products/SHA512SUMS"
for pattern in 'capella-*-win32-win32-x86_64.zip' \
               'capella-*-linux-gtk-x86_64.tar.gz' 'capella-*-linux-gtk-aarch64.tar.gz' \
               'capella-*-macosx-cocoa-x86_64.dmg' 'capella-*-macosx-cocoa-aarch64.dmg'; do
    matches=("$products"/$pattern)
    if [[ ${#matches[@]} != 1 || ! -f ${matches[0]} || ! -s ${matches[0]} ]]; then
        echo "Expected exactly one nonempty product matching $pattern in $products" >&2
        exit 1
    fi
done
# The standalone descriptor avoids Tycho resolution and runs only checksum goals.
mvn -B -N -f releng/plugins/org.polarsys.capella.rcp.product/checksums-pom.xml \
    net.nicoulaj.maven.plugins:checksum-maven-plugin:1.11:files@product-sha256 \
    net.nicoulaj.maven.plugins:checksum-maven-plugin:1.11:files@product-sha512
cd "$products"
sha256sum --check SHA256SUMS
sha512sum --check SHA512SUMS
'''
				archiveArtifacts artifacts: 'releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.sha256,' +
					'releng/plugins/org.polarsys.capella.rcp.product/target/products/capella-*.sha512,' +
					'releng/plugins/org.polarsys.capella.rcp.product/target/products/SHA256SUMS,' +
					'releng/plugins/org.polarsys.capella.rcp.product/target/products/SHA512SUMS'
			}
		}

		stage('Deploy to Nightly') {
      		steps {
				script {		
		    		def deploymentDirName = 
		    			(github.isPullRequest() ? "${BUILD_KEY}-${BRANCH_NAME}-${BUILD_ID}" : "${BRANCH_NAME}-${BUILD_ID}")
		    			.replaceAll('/','-')		
		
					// Products
					def productsDirectory = "${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products"
					deployer.capellaNightlyProduct("${productsDirectory}/capella-*-win32-*.zip", deploymentDirName)
					deployer.capellaNightlyProduct("${productsDirectory}/capella-*-linux-*.tar.gz", deploymentDirName)
					if (github.isPullRequest()) {
						deployer.capellaNightlyProduct("${productsDirectory}/capella-*-macosx-*.tar.gz", deploymentDirName)
					} else {
						deployer.capellaNightlyProduct("${productsDirectory}/capella-*-macosx-*.dmg", deploymentDirName)
					}

					// Update sites and associated files
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.test.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.test.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.egf.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.egf.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.rcp.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.targets/full/*", "${deploymentDirName}/targets")
					deployer.capellaNightlyProduct("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/bom.json", deploymentDirName)

					// Checksums
					if (!github.isPullRequest()) {
						deployer.capellaNightlyProduct("${productsDirectory}/*.sha256", deploymentDirName)
						deployer.capellaNightlyProduct("${productsDirectory}/*.sha512", deploymentDirName)
						deployer.capellaNightlyProduct("${productsDirectory}/SHA256SUMS", deploymentDirName)
						deployer.capellaNightlyProduct("${productsDirectory}/SHA512SUMS", deploymentDirName)
					}

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
			
					// Products
					def productsDirectory = "${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/products"
					deployer.capellaNightlyProduct("${productsDirectory}/capella-*-win32-*.zip", deploymentDirName)
					deployer.capellaNightlyProduct("${productsDirectory}/capella-*-linux-*.tar.gz", deploymentDirName)
					deployer.capellaNightlyProduct("${productsDirectory}/capella-*-macosx-*.dmg", deploymentDirName)

					// Update sites and associated files
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.test.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.test.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.egf.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.egf.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.site/target/repository/**", "${deploymentDirName}/org.polarsys.capella.rcp.site")
					deployer.capellaNightlyUpdateSite("${WORKSPACE}/releng/plugins/org.polarsys.capella.targets/full/*", "${deploymentDirName}/targets")
					deployer.capellaNightlyProduct("${WORKSPACE}/releng/plugins/org.polarsys.capella.rcp.product/target/bom.json", deploymentDirName)

					// Checksums
					deployer.capellaNightlyProduct("${productsDirectory}/*.sha256", deploymentDirName)
					deployer.capellaNightlyProduct("${productsDirectory}/*.sha512", deploymentDirName)
					deployer.capellaNightlyProduct("${productsDirectory}/SHA256SUMS", deploymentDirName)
					deployer.capellaNightlyProduct("${productsDirectory}/SHA512SUMS", deploymentDirName)

					currentBuild.description = "${BUILD_KEY} - <a href=\"https://ci-staging.eclipse.org/capella/view/Capella/job/capella-product/\">build</a> - <a href=\"https://download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}\">product</a>" }
			}
		}

    	stage('Install test features') {
    		
        	steps {
        		script {
	        		sh "chmod 755 ${CAPELLA_PRODUCT_PATH}"
	        			        		       		
	        		eclipse.installFeature("${CAPELLA_PRODUCT_PATH}", "file:/${WORKSPACE}/releng/plugins/org.polarsys.capella.test.site/target/repository".replace("\\", "/"), 'org.polarsys.capella.test.feature.feature.group')
	       		}         
	     	}
	    }
	    
    	stage('Run tests') {
    		
        	steps {
        		script {
        			wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
		        		
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'ModelQueriesValidation', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.business.queries.ju.testSuites.main.BusinessQueryTestSuite',
		        			 'org.polarsys.capella.test.semantic.queries.ju.testsuites.SemanticQueriesTestSuite', 
		        			 'org.polarsys.capella.test.validation.rules.ju.testsuites.main.ValidationRulesTestSuite'])
		        			 
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'LibRecTransition', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.libraries.ju.testsuites.main.LibrariesTestSuite',
							  'org.polarsys.capella.test.libraries.ui.ju.testsuites.main.LibrariesUITestSuite',
		        			  'org.polarsys.capella.test.recrpl.ju.testsuites.main.RecRplTestSuite',
		        			  'org.polarsys.capella.test.transition.ju.testsuites.main.TransitionTestSuite',
		        			  'org.polarsys.capella.test.re.updateconnections.ju.UpdateConnectionsTestSuite'])
		        		
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'DiagramTools1', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.diagram.tools.ju.testsuites.main.DiagramToolsStep1TestSuite'])
		        			
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'DiagramTools2', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.diagram.tools.ju.testsuites.main.DiagramToolsStep2TestSuite'])
		        			        			
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'DiagramMiscFilters', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.diagram.misc.ju.testsuites.DiagramMiscTestSuite',
		        			  'org.polarsys.capella.test.diagram.filters.ju.testsuites.DiagramFiltersTestSuite',
		        			  'org.polarsys.capella.test.table.ju.testsuite.TableTestSuite'])
		        			    
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'Fragmentation', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.fragmentation.ju.testsuites.FragmentationTestSuite'])
							
						tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'Odesign', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.odesign.ju.maintestsuite.ODesignTestSuite'])
		   
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'Views', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.model.ju.testsuites.main.ModelTestSuite', 
		        			 'org.polarsys.capella.test.massactions.ju.testsuites.MassActionsTestSuite',
		        			 'org.polarsys.capella.test.platform.ju.testsuites.PlatformTestSuite', 
		        			 'org.polarsys.capella.test.richtext.ju.testsuites.RichtextTestSuite',
		        			 'org.polarsys.capella.test.fastlinker.ju.testsuites.FastLinkerTestsSuite',
		        			 'org.polarsys.capella.test.explorer.activity.ju.testsuites.ActivityExplorerTestsSuite',
		        			 'org.polarsys.capella.test.progressmonitoring.ju.testsuites.SetProgressTestSuite',
		        			 'org.polarsys.capella.test.navigator.ju.testsuites.main.NavigatorUITestSuite',
		        			 'org.polarsys.capella.test.semantic.ui.ju.testsuites.SemanticUITestSuite'])
		        			 
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'MigrationCommandLine', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.migration.ju.testsuites.main.MigrationTestSuite',
		        			 'org.polarsys.capella.test.diagram.layout.ju.testsuites.LayoutTestSuite',
		        			 'org.polarsys.capella.test.commandline.ju.testsuites.CommandLineTestSuite'])
		 	
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'Benchmark', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.benchmarks.ju.suites.AllBenchmarksTestSuite'])
		   
		  				tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'Detach', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.model.ju.testsuites.partial.DetachTestSuite'])
		        			
		        		tester.runUITests("${CAPELLA_PRODUCT_PATH}", 'Documentation', 'org.polarsys.capella.test.doc.ju', 
		        			['org.polarsys.capella.test.doc.ju.testsuites.DocTestSuite'])
		        			
		        		tester.runNONUITests("${CAPELLA_PRODUCT_PATH}", 'NotUINavigator', 'org.polarsys.capella.test.suites.ju', 
		        			['org.polarsys.capella.test.navigator.ju.testsuites.main.NavigatorTestSuite'])
		        			
	        		}
	        		
	        		tester.publishTests()
				}
			}
		}
		
		stage('Sonar') {
			steps {
				script {
					sonar.runSonar("eclipse-capella_capella", "eclipse/capella", "sonarcloud-token-capella")
				}
			}
		}
		
	}
  
	post {
    	always {
       		archiveArtifacts artifacts: '**/*.log, *.log, *.xml, **/*.json, **/*.layout, *.exec'
       		
       		script {
       		    github.removeBuildStartedLabel()
       		}
    	}
    	
    	success  {
    		script {
    			if(github.isPullRequest()){
        			github.buildSuccessfullComment()
        			github.buildSuccessfullLabel()
        			
        		}
        	}
    	}
    	
	    unstable {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildUnstableComment()
	        		github.buildUnstableLabel()
	        	}
	        }
	    }
    
	    failure {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildFailedComment()
	        		github.buildFailedLabel()
	        	}
	        }
	    }
	    
	    aborted {
	    	script {
	    		if(github.isPullRequest()){
	        		github.buildAbortedComment()
	        		github.buildAbortedLabel()
	        	}
	        }
	    }
	}
}
