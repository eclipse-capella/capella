/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.odesign.directeditlabel;

import static org.junit.Assert.fail;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.COMMON;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.CONTEXT;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.CURRENT_ODESIGN_PATH;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.EPBS;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.LOGICAL;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.OA;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.ODESIGN_EXT;
import static org.polarsys.capella.test.odesign.directeditlabel.DirectEditLabelConstants.PHYSICAL;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.polarsys.capella.core.sirius.analysis.FaServices;
import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public class DirectEditLabelTestSuite extends BasicTestSuite {

  private void findEditLabelClassesCurrent(Map<String, List<DirectEditLabel>> directEditLabelsList) {
    try {
      // load current odesign
      loadCurrentODesign(COMMON, directEditLabelsList);
      loadCurrentODesign(OA, directEditLabelsList);
      loadCurrentODesign(CONTEXT, directEditLabelsList);
      loadCurrentODesign(LOGICAL, directEditLabelsList);
      loadCurrentODesign(PHYSICAL, directEditLabelsList);
      loadCurrentODesign(EPBS, directEditLabelsList);
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  @Override
  protected List<BasicTestArtefact> getTests() {

    Map<String, List<DirectEditLabel>> currentDirectEditLabels = new HashMap<>();

    findEditLabelClassesCurrent(currentDirectEditLabels);

    List<BasicTestArtefact> tests = new ArrayList<>();
    tests.add(new CheckDirectEditLabelHasMappingsTest(currentDirectEditLabels));
    tests.add(new CheckMappingsHasDirectEditLabelTest(currentDirectEditLabels));

    return tests;
  }

  public void loadCurrentODesign(String odesign_p, Map<String, List<DirectEditLabel>> directEditLabelsList)
      throws IOException {
    File folder = IResourceHelpers.getPluginFolder(FaServices.class);
    String path = folder.getAbsolutePath() + CURRENT_ODESIGN_PATH + odesign_p + ODESIGN_EXT;
    URI uri = URI.createFileURI(path);
    directEditLabelsList.put(odesign_p, DirectEditLabelHelper.allInstanceOf(DirectEditLabel.class, uri));
  }
}
