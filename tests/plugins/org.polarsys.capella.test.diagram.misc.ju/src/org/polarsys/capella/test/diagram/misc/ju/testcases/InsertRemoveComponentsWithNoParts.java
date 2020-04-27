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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.sirius.analysis.ContextServices;
import org.polarsys.capella.core.sirius.analysis.CsServices;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;
import org.polarsys.capella.core.sirius.analysis.OAServices;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;

/**
 * This tests ensures that the methods used to populate the Show/Hide Actors/Components dialogs with components ONLY
 * contain components that have at least one representing part.
 *
 */
public class InsertRemoveComponentsWithNoParts extends BasicTestCase {

  private static final String PROJECT_NAME = "insert-remove-components-with-no-parts"; //$NON-NLS-1$

  public static final String LOGICAL_SYSTEM = "0f76d62b-5ba5-435c-b3a9-d3ce55934cd6"; //$NON-NLS-1$
  public static final String LC_1 = "dbcac4d4-a4c2-4bfc-a937-93af979ff1c2"; //$NON-NLS-1$

  public static final String CII_LOGICAL_SYSTEM = "[CII] Logical System"; //$NON-NLS-1$
  public static final String IDB_LOGICAL_SYSTEM = "[IDB] Logical System"; //$NON-NLS-1$
  public static final String CRI_CAPABILITY_REALIZATION = "[CRI] CapabilityRealization"; //$NON-NLS-1$
  public static final String OAB_OPERATIONAL_ENTITIES = "[OAB] Operational Entities"; //$NON-NLS-1$
  public static final String OCB_OPERATIONAL_CAPABILITIES = "[OCB] Operational Capabilities"; //$NON-NLS-1$
  public static final String PAB_PHYSICAL_SYSTEM = "[PAB] Physical System"; //$NON-NLS-1$

  protected Session session;
  protected SessionContext context;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.session = getSession(PROJECT_NAME);
    this.context = new SessionContext(session);

  }

  @Override
  public void test() throws Exception {
    Collection<String> errorMessages = new ArrayList<>();

    testCCEI(errorMessages);
    testCCII(errorMessages);
    testIDB(errorMessages);
    testCRI_CRB_CM_MB_CC_MCB(errorMessages);
    testOCB_COC(errorMessages);
    testSAB_LAB_PAB(errorMessages);

    assertTrue(errorMessages.stream().collect(Collectors.joining("\n")), errorMessages.isEmpty());
  }

  protected void testCCEI(Collection<String> errorMessages) {
    LogicalComponent logicalSystem = context.getSemanticElement(LOGICAL_SYSTEM);

    Function<Component, Collection<Component>> componentMethod = CsServices.getService()::getCCEIShowHideComponent;
    Function<Component, Collection<Component>> actorMethod = CsServices.getService()::getCCEIShowHideActors;

    evaluateAndAccumulateErrors(Arrays.asList(componentMethod, actorMethod), logicalSystem, errorMessages);
  }

  protected void testCCII(Collection<String> errorMessages) {
    LogicalComponent logicalSystem = context.getSemanticElement(LOGICAL_SYSTEM);
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, CII_LOGICAL_SYSTEM);
    DDiagramElement logicalSystemElement = DiagramServices.getDiagramServices().getDiagramElement(diagram,
        logicalSystem);

    Function<DSemanticDecorator, Collection<Component>> componentMethod = CsServices
        .getService()::getCCIIShowHideComponent;
    Function<DSemanticDecorator, Collection<Component>> actorMethod = CsServices.getService()::getCCIIShowHideActor;

    evaluateAndAccumulateErrors(Arrays.asList(componentMethod, actorMethod), logicalSystemElement, errorMessages);
  }

  protected void testIDB(Collection<String> errorMessages) {
    LogicalComponent lc1 = context.getSemanticElement(LC_1);
    DDiagram diagram = (DDiagram) DiagramHelper.getDRepresentation(session, IDB_LOGICAL_SYSTEM);
    DDiagramElement lc1Element = DiagramServices.getDiagramServices().getDiagramElement(diagram, lc1);

    Function<DSemanticDecorator, Collection<Component>> componentMethod = CsServices
        .getService()::getIBShowHideComponent;
    Function<DSemanticDecorator, Collection<Component>> actorMethod = CsServices.getService()::getIBShowHideActor;

    evaluateAndAccumulateErrors(Arrays.asList(componentMethod, actorMethod), lc1Element, errorMessages);
  }

  protected void testCRI_CRB_CM_MB_CC_MCB(Collection<String> errorMessages) {
    DSemanticDecorator diagram = (DSemanticDecorator) DiagramHelper.getDRepresentation(session,
        CRI_CAPABILITY_REALIZATION);

    Function<DSemanticDecorator, Collection<Component>> componentMethod = ContextServices
        .getServices()::getCRBComponents;
    evaluateAndAccumulateErrors(componentMethod, diagram, errorMessages);

    Function<DSemanticDecorator, List<EObject>> actorMethod = ContextServices.getServices()::getMCBActors;
    evaluateAndAccumulateErrors(actorMethod, diagram, errorMessages);
  }

  protected void testOAB(Collection<String> errorMessages) {
    DSemanticDecorator diagram = (DSemanticDecorator) DiagramHelper.getDRepresentation(session,
        OAB_OPERATIONAL_ENTITIES);

    Function<DSemanticDecorator, Collection<? extends Component>> actorEntitiesMethod = OAServices
        .getService()::getOEBEntities;
    evaluateAndAccumulateErrors(actorEntitiesMethod, diagram, errorMessages);
  }

  protected void testOCB_COC(Collection<String> errorMessages) {
    DSemanticDecorator diagram = (DSemanticDecorator) DiagramHelper.getDRepresentation(session,
        OAB_OPERATIONAL_ENTITIES);

    Function<DSemanticDecorator, List<EObject>> components = ContextServices.getServices()::getOCBEntities;
    Function<DSemanticDecorator, List<EObject>> actors = ContextServices.getServices()::getOCBActors;

    evaluateAndAccumulateErrors(Arrays.asList(components, actors), diagram, errorMessages);
  }

  protected void testSAB_LAB_PAB(Collection<String> errorMessages) {
    DSemanticDecorator diagram = (DSemanticDecorator) DiagramHelper.getDRepresentation(session, PAB_PHYSICAL_SYSTEM);

    Function<DSemanticDecorator, Collection<? extends CapellaElement>> actors = CsServices
        .getService()::getABShowHideActor;
    Function<DSemanticDecorator, Collection<? extends CapellaElement>> components = CsServices
        .getService()::getABShowHideComponent;

    evaluateAndAccumulateErrors(Arrays.asList(actors, components), diagram, errorMessages);
  }

  /**
   * This method applies each function to the parameter argument and evaluates that the result is a list of components
   * that have each at least one representing part.
   * 
   * @param <P>
   *          the parameter type
   * @param <R>
   *          the result type
   * @param functions
   *          the list of functions to evaluate
   * @param parameter
   *          the function parameter used for the apply
   * @param errorsAcumulator
   *          an accumulator of textual error messages
   */
  protected <P, R> void evaluateAndAccumulateErrors(List<Function<P, R>> functions, P parameter,
      Collection<String> errorsAcumulator) {

    for (Function<P, R> function : functions) {

      R result = function.apply(parameter);
      String message = null;

      if (!(result instanceof Collection<?>)) {
        message = function.toString() + " did not return a Collection for " + parameter.toString();
      } else {

        Collection<?> collectionResult = (Collection<?>) result;
        if (collectionResult.isEmpty()) {
          message = function.toString() + " returned a empty Collection for " + parameter.toString();
        } else {
          Object first = collectionResult.iterator().next();

          if (!(first instanceof Component)) {
            message = function.toString() + "did not returned a Component Collection for " + parameter.toString();
          } else {
            Collection<Component> components = (Collection<Component>) collectionResult;

            for (Component component : components) {
              if (component.getRepresentingParts().isEmpty()) {
                String error = "Component " + component.getName() + "[" + component.getId() + "][" + function.toString()
                    + "] has no representing parts";
                errorsAcumulator.add(error);
              }
            }
          }
        }
      }

      if (message != null) {
        errorsAcumulator.add(message);
      }
    }
  }

  protected <P, R> void evaluateAndAccumulateErrors(Function<P, R> function, P parameter,
      Collection<String> errorsAcumulator) {
    evaluateAndAccumulateErrors(Arrays.asList(function), parameter, errorsAcumulator);
  }
}
