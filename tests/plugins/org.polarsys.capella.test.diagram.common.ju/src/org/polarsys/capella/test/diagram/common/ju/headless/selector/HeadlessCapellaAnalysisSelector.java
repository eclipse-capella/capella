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
package org.polarsys.capella.test.diagram.common.ju.headless.selector;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.ui.business.api.session.analysis.SmartDialogAnalysisSelector;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Headless analysis selector.
 */
public class HeadlessCapellaAnalysisSelector extends SmartDialogAnalysisSelector {

  public static HeadlessCapellaAnalysisSelector INSTANCE = new HeadlessCapellaAnalysisSelector();

  private URI airdURI;

  protected URI getSelectedURI() {
    return airdURI;
  }

  public void setSelectedURI(Session session, URI uri) {
    this.airdURI = uri;
    assertNotNull("Session must not be null while using the HeadlessCapellaAnalysisSelector", session);
    ((DAnalysisSession) session).setAnalysisSelector(HeadlessCapellaAnalysisSelector.INSTANCE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DAnalysis selectSmartlyAnalysisForAddedRepresentation(DRepresentation representation,
      Collection<DAnalysis> allAnalysis) {
    for (DAnalysis analysis : allAnalysis) {
      if (isEquals(analysis.eResource().getURI(), getSelectedURI()))
        return analysis;
    }
    return null;
  }

  protected boolean isEquals(URI uri, URI uri2) {
    return uri.equals(uri2);
  }

}
