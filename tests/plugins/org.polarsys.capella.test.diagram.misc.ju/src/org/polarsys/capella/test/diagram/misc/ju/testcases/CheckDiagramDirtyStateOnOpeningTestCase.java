/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.junit.Assert;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.SiriusElementHelper;
import org.polarsys.capella.test.diagram.misc.ju.Messages;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.SessionHelper;

/**
 * Test suite about dirty state of diagram on opening.
 * Open a diagram i.e its editor.
 * choice about refresh included, could be really useful default behavior of this test kept via the default constructor.
 */
public class CheckDiagramDirtyStateOnOpeningTestCase extends BasicTestCase {

  private static final String projectTestName = "EOLE_AF_UC";

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(projectTestName);
  }

  /**
   * {@inheritDoc}
   */
  public void test() {
    Session session = getSession(projectTestName);
    Assert.assertNotNull(session);

    ResourceSet resourceSet = TransactionHelper.getEditingDomain(session).getResourceSet();
    List<Resource> odesigns = ODesignHelper.getAvailableODesignFile(resourceSet);
    List<DiagramDescription> ddescs = ODesignHelper.getAllDiagramDescription(odesigns);

    for (DiagramDescription current : ddescs) {
      // no AF diagram, remove this case
      if (isDiagramSupported(current.getName()) && !(current.getName().equals("AD diagram"))) { //$NON-NLS-1$
        checkDiagram(session, current.getName(), false);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();

    // Refresh options
    DiagramHelper.setPreferenceAutoRefresh(true);
    DiagramHelper.setPrefereneRefreshOnOpening(true);
  }

  public static final String unsupportedDiagrams[] = { "Capella Architecture" }; //$NON-NLS-1$

  /**
   * Check whether an existing {@link DiagramDescription} is used...
   * 
   * @param id_p
   *          the {@link DiagramDescription} id.
   * @return
   */
  protected boolean isDiagramSupported(String id) {
    boolean result = true;
    for (String unsupportedDiagram : unsupportedDiagrams) {
      if (id.equals(unsupportedDiagram)) {
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * Implement a diagram open operation.
   */
  public void checkDiagram(Session session, String diagramType, boolean mustBeRefreshed) {
    DRepresentation dRepresentation = getDiagramToOpen(session, diagramType);
    Assert.assertNotNull(NLS.bind(Messages.nullDiagram, getName()), dRepresentation);

    DiagramHelper.opendiagramEditor(session, dRepresentation);
    if (mustBeRefreshed || dRepresentation.getOwnedRepresentationElements().isEmpty()) {
      boolean ret = DiagramHelper.refreshDiagram((DDiagram) dRepresentation);
      Assert.assertTrue(NLS.bind(Messages.failToRefreshDiagram, new Object[] { getName(), EObjectExt.getText(dRepresentation) }), ret);
    }

    // Let's check the state of the diagram
    Assert.assertTrue(NLS.bind(Messages.sessionDirtyAfterDiagramOpening, diagramType), SessionStatus.SYNC.equals(session.getStatus()));
    SessionHelper.saveSession(getSession(projectTestName));
  }

  protected DRepresentation getDiagramToOpen(Session session, String diagramType) {
    Collection<DRepresentation> allDSemanticDiagrams = SiriusElementHelper.getAllDSemanticDiagrams(session);
    for (DRepresentation current : allDSemanticDiagrams) {
      DDiagram d = (DDiagram) current;
      if (d.getDescription().getName().equals(diagramType)) {
        return d;
      }
    }

    Assert.fail("No diagram found in model with type " + diagramType);
    return null;
  }
}
