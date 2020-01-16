pipeline {
  agent { label 'migration' }
  
  tools {
        maven 'apache-maven-latest'
        jdk 'oracle-jdk8-latest'
  }
  environment {
    SSH_ACCOUNT = "genie.capella@projects-storage.eclipse.org";
    
    FROM_PR = "${env.BRANCH_NAME}".contains("PR-");
    
    PRODUCT_BRANCH = "master"
    PRODUCT_KEY = "${env.PRODUCT_BRANCH}".replaceFirst(/^v/, "");
	
    BUILD_KEY = "${env.FROM_PR}".replace("true", "${env.PRODUCT_KEY}-${env.BRANCH_NAME}-${env.BUILD_ID}").replace("false", "${env.BRANCH_NAME}-${env.BUILD_ID}").replaceAll('/','-');
    PRODUCT_DIR = "/home/data/httpd/download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}";
    UPDATE_DIR = "/home/data/httpd/download.eclipse.org/capella/core/updates/nightly/${env.BUILD_KEY}";
    
    PRODUCT_DIRN = "/home/data/httpd/download.eclipse.org/capella/core/products/nightly/${env.PRODUCT_KEY}";
    UPDATE_DIRN = "/home/data/httpd/download.eclipse.org/capella/core/updates/nightly/${env.PRODUCT_KEY}";
    
    MAVEN_SIGN = "${env.FROM_PR}".replace("true", " ").replace("false", "-Psign");
    MAVEN_SONAR = "${env.FROM_PR}".replace("true", "-DSKIP_SONAR=true").replace("false", " ");
    
    RUNNER = "./capella/eclipse/eclipse -port 8081 -application org.polarsys.capella.test.run.application -data ${env.WORKSPACE}/runner >>${env.WORKSPACE}/capella/runner.log "
    JUNIT = "sleep 10 && java -Xms1024m -Xmx3500m -XX:+CMSPermGenSweepingEnabled -XX:+CMSClassUnloadingEnabled -ea -Declipse.p2.data.area=@config.dir/../p2 -Dfile.encoding=Cp1252 -classpath `ls ${WORKSPACE}/capella/eclipse/plugins/org.eclipse.equinox.launcher_*` org.eclipse.equinox.launcher.Main -os linux -ws gtk -arch x86_64 -version 3 -port 8081 -testLoaderClass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader -loaderpluginname org.eclipse.jdt.junit4.runtime -application org.eclipse.pde.junit.runtime.uitestapplication -product org.polarsys.capella.rcp.product -testApplication org.polarsys.capella.core.platform.sirius.ui.perspective.id -configuration file:${env.WORKSPACE}/capella/eclipse/configuration -buildKey ${BUILD_KEY}"
    HJUNIT = "sleep 10 && java -Xms1024m -Xmx3500m -XX:+CMSPermGenSweepingEnabled -XX:+CMSClassUnloadingEnabled -ea -Declipse.p2.data.area=@config.dir/../p2 -Dfile.encoding=Cp1252 -classpath `ls ${WORKSPACE}/capella/eclipse/plugins/org.eclipse.equinox.launcher_*` org.eclipse.equinox.launcher.Main -os linux -ws gtk -arch x86_64 -version 3 -port 8081 -testLoaderClass org.eclipse.jdt.internal.junit4.runner.JUnit4TestLoader -loaderpluginname org.eclipse.jdt.junit4.runtime -application org.eclipse.pde.junit.runtime.nonuithreadtestapplication -product org.polarsys.capella.rcp.product -testApplication org.polarsys.capella.core.platform.sirius.ui.perspective.id -configuration file:${env.WORKSPACE}/capella/eclipse/configuration -buildKey ${BUILD_KEY}"
  
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
        
        script { 
          
          currentBuild.description = "${env.BUILD_KEY} - <a href=\"https://download.eclipse.org/capella/core/updates/nightly/${env.BUILD_KEY}\">site</a> - <a href=\"https://download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}\">product</a>" 
        
          if (env.CHANGE_ID) {
            pullRequest.createStatus(status: 'success',
                           context: 'ci/build-product',
                           description: 'Capella product is available',
                           targetUrl: "https://download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}")
          }
          
        } 
        
        
        //if ("${env.FROM_PR}".contains("true")) {
        //   pullRequest.addLabel('Build OK')
        //}
        
      }
    }
    
    stage('Update nightly fixed url') {
      when {
        expression { return "${env.FROM_PR}".contains("false") } 
      }
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
    
    
    
    stage('Tests: Download Capella') {
        steps {
            script { currentBuild.description = "${env.BUILD_KEY} - <a href=\"https://ci-staging.eclipse.org/capella/view/Capella/job/capella-product/\">build</a> - <a href=\"https://download.eclipse.org/capella/core/products/nightly/${env.BUILD_KEY}\">product</a>" } 
            sh "wget https://download.eclipse.org/capella/core/products/nightly/${BUILD_KEY}/build.txt"
            sh "PRODUCT_ZIP=`head -n 1 $WORKSPACE/build.txt`; echo \$PRODUCT_ZIP; wget -q https://download.eclipse.org/capella/core/products/nightly/${BUILD_KEY}/\$PRODUCT_ZIP; unzip -q \$PRODUCT_ZIP -d ."
        }
    }
    stage('Tests: Install Tests on Capella') {
        steps {
            sh "chmod 755 ./capella/eclipse/eclipse"
            sh "./capella/eclipse/eclipse -application org.eclipse.equinox.p2.director -repository http://download.eclipse.org/tools/orbit/downloads/drops/R20130827064939/repository -installIU org.jsoup -tag InitialState -noSplash || true"
            sh "./capella/eclipse/eclipse -application org.eclipse.equinox.p2.director -repository https://download.eclipse.org/capella/core/updates/nightly/${BUILD_KEY}/org.polarsys.capella.test.site -installIU org.polarsys.capella.test.feature.feature.group -tag InitialState -noSplash"
        }
    }
    
    stage('Tests: Run Tests on Capella') {
        steps {
            wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
		        
	sh "$RUNNER -title ModelQueriesValidation   & $JUNIT -data ${env.WORKSPACE}/ModelQueriesValidation  -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.business.queries.ju.testSuites.main.BusinessQueryTestSuite org.polarsys.capella.test.semantic.queries.ju.testsuites.SemanticQueriesTestSuite org.polarsys.capella.test.validation.rules.ju.testsuites.main.ValidationRulesTestSuite"
    sh "$RUNNER -title LibRecTransition         & $JUNIT -data ${env.WORKSPACE}/LibRecTransition        -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.libraries.ju.testsuites.main.LibrariesTestSuite org.polarsys.capella.test.recrpl.ju.testsuites.main.RecRplTestSuite org.polarsys.capella.test.transition.ju.testsuites.main.TransitionTestSuite org.polarsys.capella.test.re.updateconnections.ju.UpdateConnectionsTestSuite"
    sh "$RUNNER -title DiagramTools1            & $JUNIT -data ${env.WORKSPACE}/DiagramTools1           -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.diagram.tools.ju.testsuites.main.DiagramToolsStep1TestSuite"
    sh "$RUNNER -title DiagramTools2            & $JUNIT -data ${env.WORKSPACE}/DiagramTools2           -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.diagram.tools.ju.testsuites.main.DiagramToolsStep2TestSuite"
    sh "$RUNNER -title DiagramMiscFilters       & $JUNIT -data ${env.WORKSPACE}/DiagramMiscFilters      -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.diagram.misc.ju.testsuites.DiagramMiscTestSuite org.polarsys.capella.test.diagram.filters.ju.testsuites.DiagramFiltersTestSuite org.polarsys.capella.test.table.ju.testsuite.TableTestSuite"
    sh "$RUNNER -title Odesign                  & $JUNIT -data ${env.WORKSPACE}/Odesign                 -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.odesign.ju.maintestsuite.ODesignTestSuite"
    sh "$RUNNER -title Fragmentation            & $JUNIT -data ${env.WORKSPACE}/Fragmentation           -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.fragmentation.ju.testsuites.FragmentationTestSuite"
    sh "$RUNNER -title Views                    & $JUNIT -data ${env.WORKSPACE}/Views                   -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.model.ju.testsuites.main.ModelTestSuite org.polarsys.capella.test.massactions.ju.testsuites.MassActionsTestSuite org.polarsys.capella.test.platform.ju.testsuites.PlatformTestSuite org.polarsys.capella.test.richtext.ju.testsuites.RichtextTestSuite org.polarsys.capella.test.fastlinker.ju.testsuites.FastLinkerTestsSuite org.polarsys.capella.test.explorer.activity.ju.testsuites.ActivityExplorerTestsSuite org.polarsys.capella.test.progressmonitoring.ju.testsuites.SetProgressTestSuite org.polarsys.capella.test.navigator.ju.testsuites.main.NavigatorUITestSuite"
    sh "$RUNNER -title MigrationCommandLine     & $JUNIT -data ${env.WORKSPACE}/MigrationCommandLine    -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.migration.ju.testsuites.main.MigrationTestSuite org.polarsys.capella.test.diagram.layout.ju.testsuites.LayoutTestSuite org.polarsys.capella.test.commandline.ju.testsuites.CommandLineTestSuite"
    sh "$RUNNER -title Benchmark                & $JUNIT -data ${env.WORKSPACE}/Benchmark               -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.benchmarks.ju.suites.AllBenchmarksTestSuite"
    sh "$RUNNER -title Detach                   & $JUNIT -data ${env.WORKSPACE}/Detach                  -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.model.ju.testsuites.partial.DetachTestSuite"
    sh "$RUNNER -title NotUINavigator           & $HJUNIT -data ${env.WORKSPACE}/NotUINavigator         -testpluginname org.polarsys.capella.test.suites.ju -classNames org.polarsys.capella.test.navigator.ju.testsuites.main.NavigatorTestSuite"
	
	
        script { 
        
            if (env.CHANGE_ID) {
                  pullRequest.createStatus(status: 'success',
                                 context: 'ci/build-test',
                                 description: 'Tests have ended',
                                 targetUrl: "${env.JOB_URL}/testResults")
            }
        }
        
		    }
        }
    }
    
  }
  
  post {
    always {
       archiveArtifacts artifacts: '**/*.log,*.xml,**/*.layout'
       junit '*.xml'
    }
    //failure {
    //   script {
          //if ("${env.FROM_PR}".contains("true")) {
          //   pullRequest.addLabel('Tests KO')
          //}
    //   }
    //}
  }
}