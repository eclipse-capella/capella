/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.cii;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * The scope of Show/Hide CEI actors should be sibling actors and first-level root actors in the Structure package 
 *
 */
public class ShowHideCIIActorQuery extends AbstractDiagramTestCase {
  private String CII_DIAGRAM = "[CII] LC 1";
  public static final String LOGICAL_SYSTEM_LA_2 = "928cede2-ba3c-488f-8a5a-a173edf6272b"; //$NON-NLS-1$
  public static final String LC_PKG_1_LA_2 = "af4ec62d-4b16-45a1-a2b5-44b1620f1d5e"; //$NON-NLS-1$
  public static final String LA_1 = "9c822925-b126-403c-8c35-ebe1c05f79d5"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM_LC_1_LA_1 = "224ef6ae-539a-4a84-b8e6-2720e5f721cf"; //$NON-NLS-1$
  public static final String LOGICAL_SYSTEM_LC_1 = "7f544336-d382-40a9-8cd3-156414b8f4e6"; //$NON-NLS-1$

  @Override
  protected String getRequiredTestModel() {
    return "ContextualInterface";
  }

  @Override
  public void test() throws Exception {

    Session session = getSession(getRequiredTestModel());
    SessionContext context = new SessionContext(session);

    DiagramContext diagramContext = new OpenDiagramStep(context, CII_DIAGRAM).run();
    DDiagram ciiDiagram = diagramContext.getDiagram();

    ICapellaModel model = getTestModel(getRequiredTestModel());
    IScope scope = new ScopeModelWrapper(model);
    EObject logicalSystem_la2 = IdManager.getInstance().getEObject(LOGICAL_SYSTEM_LA_2, scope);
    EObject lcPkg1_la2 = IdManager.getInstance().getEObject(LC_PKG_1_LA_2, scope);
    EObject la1 = IdManager.getInstance().getEObject(LA_1, scope);
    EObject logicalSystem_lc1_la1 = IdManager.getInstance().getEObject(LOGICAL_SYSTEM_LC_1_LA_1, scope);
    EObject logicalSystem_lc1 = IdManager.getInstance().getEObject(LOGICAL_SYSTEM_LC_1, scope);

    if (ciiDiagram instanceof DSemanticDiagram) {
      Collection<Component> actorScope = CsServices.getService().getCCIIShowHideActor((DSemanticDiagram) ciiDiagram);
      assertTrue("The scope of Show/Hide CEI actors is not correct", actorScope.size() == 3
          && actorScope.contains(logicalSystem_la2) && actorScope.contains(lcPkg1_la2) && actorScope.contains(la1));
      Optional<DDiagramElement> lc1Opt = ciiDiagram.getDiagramElements().stream().filter(e -> e.getTarget() == logicalSystem_lc1).findFirst();
      if (lc1Opt.isPresent()) {
        DDiagramElement lc1Container = lc1Opt.get();
        Collection<Component> actorScopeForLC1 = CsServices.getService().getCCIIShowHideActor(lc1Container);
        assertTrue("The scope of Show/Hide CEI actors is not correct", actorScopeForLC1.size() == 1
            && actorScopeForLC1.contains(logicalSystem_lc1_la1));
      }
    }
  }
}
