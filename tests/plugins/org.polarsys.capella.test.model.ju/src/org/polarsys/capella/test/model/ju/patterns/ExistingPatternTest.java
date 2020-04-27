/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.patterns;

import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.AbstractPattern;
import org.eclipse.emf.diffmerge.patterns.core.gen.corepatterns.PatternRepository;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogResourceHelper;
import org.eclipse.emf.diffmerge.patterns.repositories.catalogs.PatternCatalogsPlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.test.model.ju.model.MiscModel;
import org.polarsys.capella.test.model.ju.patterns.Messages;

/**
 * Generic test for testing patterns
 */
public class ExistingPatternTest extends MiscModel {

  // the name of the project
  protected String projectName;

  // the name of the pattern file
  protected String patternFileName;

  // List containing pattern IDs
  protected List<String> patternCatalog = new ArrayList<String>();

  /**
   * @param checkDelta_p
   * @return
   */
  public ExistingPatternTest(String project_p, String fileName_p, List<String> catalog_p) {
    projectName = project_p;
    patternFileName = fileName_p;
    patternCatalog = catalog_p;
  }

  @Override
  public void test() throws Exception {
    final ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();

    // Used only to load the model so that the ResourceSet becomes available
    @SuppressWarnings("unused")
    Session session = getSession(getRequiredTestModels().get(0));
   
    final AbstractReadWriteCommand cmd = new AbstractReadWriteCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        URL fileURL; 
        try {

          String URLpatternFile = "platform:/resource/" + projectName + "/" + patternFileName + "."
              + PatternCatalogResourceHelper.PATTERN_CATALOG_FILE_EXTENSION;
          
          // URL of the pattern file
          fileURL = new URL(URLpatternFile);
        
          // get the URI (emf) from the URL (java)
          java.net.URI uri = fileURL.toURI();
          URI fileURI = URI.createURI(uri.toString());

          PatternCatalogsPlugin patternPlugin = new PatternCatalogsPlugin();

          ResourceSet resourceSet = manager.getEditingDomain().getResourceSet();
          PatternRepository openCatalog = patternPlugin.getAccessor().openCatalog(fileURI, resourceSet);

          // check if the pattern file exists
          assertNotNull(MessageFormat.format(Messages.wrongPatternFile, patternFileName), openCatalog);

          EList<AbstractPattern> patterns = openCatalog.getPatterns();
          List<String> allPatternIds = patterns.stream().map(AbstractPattern::getId).collect(Collectors.toList());

          for (String patternIdToTest : patternCatalog) {

            boolean containedInCatalog = false;

            if (allPatternIds.contains(patternIdToTest)) {
              containedInCatalog = true;
            }

            assertTrue(MessageFormat.format(Messages.notFoundPatternInCatalog, patternIdToTest, patternFileName),
                containedInCatalog);

          }
           
        } catch (Exception exception) {
          fail(exception.getMessage());
        }

      }
    };

    // Let's perform the job
    manager.execute(cmd);
    ExecutionManagerRegistry.getInstance().removeManager(manager);
  }
}