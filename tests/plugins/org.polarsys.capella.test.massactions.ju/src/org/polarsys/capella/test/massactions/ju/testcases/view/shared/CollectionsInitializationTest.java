/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.testcases.view.shared;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.polarsys.capella.test.massactions.ju.model.AbstractCapellaMATestCase;

/**
 * 
 * @author Erwann Traisnel
 * 
 *         When a SVG has been displayed before the mass code is called org.eclipse.collection throw an error as its
 *         implementations cannot be loaded Since there is no way to catch this exception, and since it is not logged,
 *         the only way is to directly call Sets.mutable
 *
 */
public class CollectionsInitializationTest extends AbstractCapellaMATestCase {

  private final String DIAGRAM_WITH_PORTS = "_yYhrgHjqEea__MYrXGSERA";

  @Override
  public void performTest() throws Exception {

    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE
        .getAllRepresentationDescriptors(session);
    Optional<DRepresentationDescriptor> result = descriptors.stream()
        .filter(desc -> desc.getUid().equals(DIAGRAM_WITH_PORTS)).findAny();
    DRepresentationDescriptor descriptor = result.get();

    // Ensure that the welcome page is closed
    IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
    PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);

    synchronizationWithUIThread();

    IEditorPart part = DialectUIManager.INSTANCE.openEditor(session, descriptor.getRepresentation(),
        new NullProgressMonitor());
    part.setFocus();

    synchronizationWithUIThread();

    try {
      Sets.mutable.empty();
    } catch (IllegalStateException e) {
      fail("Couldn't initialize mutable sets from org.eclipse.collections");
    }

  }

  /**
   * Wait the end of the asynchronous calls waiting in UI thread.
   */
  public static void synchronizationWithUIThread() {
    while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
      // Do nothing, just wait
    }
  }
}
