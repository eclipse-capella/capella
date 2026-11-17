/*******************************************************************************
 * Copyright (c) 2006, 2026 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ODesignHelper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.SiriusElementHelper;
import org.polarsys.capella.test.diagram.misc.ju.Messages;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.SessionHelper;

/**
 * Test suite about dirty state of diagram on opening.
 * <p>
 * Open a diagram i.e its editor.
 * </p>
 * choice about refresh included, could be really useful default behavior of this test kept via the default constructor.
 */
public class CheckDiagramDirtyStateOnOpeningTestCase extends BasicTestCase {

  private static final String projectTestName = "EOLE_AF_UC"; //$NON-NLS-1$

  private static final List<String> IGNORED_DIAGRAM_TYPES = List.of( // DiagramDescription names
      "Capella Architecture", //$NON-NLS-1$
      "AD diagram"); //$NON-NLS-1$

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
      if (!IGNORED_DIAGRAM_TYPES.contains(current.getName())) {
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

  /**
   * Implement a diagram open operation.
   */
  public void checkDiagram(Session session, String diagramType, boolean mustBeRefreshed) {
    DRepresentation dRepresentation = getDiagramToOpen(session, diagramType);
    Assert.assertNotNull(NLS.bind(Messages.nullDiagram, getName()), dRepresentation);

    DiagramHelper.opendiagramEditor(session, dRepresentation);
    if (mustBeRefreshed || dRepresentation.getOwnedRepresentationElements().isEmpty()) {
      boolean ret = DiagramHelper.refreshDiagram((DDiagram) dRepresentation);
      Assert.assertTrue(NLS.bind(Messages.failToRefreshDiagram, getName(), EObjectExt.getText(dRepresentation)), ret);
    }

    // Let's check the state of the diagram: if it changed then session is dirty.
    Assert.assertEquals(NLS.bind(Messages.sessionDirtyAfterDiagramOpening, diagramType),
        SessionStatus.SYNC, session.getStatus());
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

    Assert.fail("No diagram found in model with type " + diagramType); //$NON-NLS-1$
    return null;
  }
}
