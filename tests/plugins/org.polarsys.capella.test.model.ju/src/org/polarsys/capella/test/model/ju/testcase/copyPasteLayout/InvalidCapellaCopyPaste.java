/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.testcase.copyPasteLayout;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.platform.sirius.clipboard.util.LayerUtil;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class InvalidCapellaCopyPaste extends AbstractCopyPasteLayout {

  public static String MODEL_NAME = "copyPasteLayout"; //$NON-NLS-1$
  public static final String SAB_SYSTEM = "[SAB] System"; //$NON-NLS-1$
  public static final String SYSTEM_PART = "91e77aaf-8a53-46e9-af53-dbb16c7471b3"; //$NON-NLS-1$
  public static final String SYSTEMFUNCTION_1 = "cb326564-f8da-4d67-98e5-c30878030cc8"; //$NON-NLS-1$

  @Override
  public List<? extends View> getViewsToPasteTo() {
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, SAB_SYSTEM).run();
    View logicalSystemViewGMFElement = LayerUtil.getUpGmfElement((DSemanticDecorator) diagramContext.getDiagram());
    return Arrays.asList(logicalSystemViewGMFElement);
  }

  @Override
  public List<? extends View> getViewsToCopyFrom() {
    SessionContext context = new SessionContext(session);
    DiagramContext diagramContext = new OpenDiagramStep(context, SAB_SYSTEM).run();
    DSemanticDecorator sf1 = diagramContext.getView(SYSTEMFUNCTION_1);
    View sf1View = LayerUtil.getUpGmfElement(sf1);
    return Arrays.asList(sf1View);
  }

  @Override
  public List<EObject> getCapellaElementsToCopy() {
    EObject SF1 = IdManager.getInstance().getEObject(SYSTEMFUNCTION_1, scope);
    return Arrays.asList(SF1);
  }

  @Override
  public EObject getCapellaSourceContainer() {
    return IdManager.getInstance().getEObject(SYSTEM_PART, scope);
  }

  @Override
  public EObject getCapellaTargetContainer() {
    return IdManager.getInstance().getEObject(SYSTEM_PART, scope);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void test() {
    super.test();
    if (pastedSiriusElements != null)
      assertTrue("This is an invalid paste, no new element should be created!", pastedSiriusElements.isEmpty());
  }
}
