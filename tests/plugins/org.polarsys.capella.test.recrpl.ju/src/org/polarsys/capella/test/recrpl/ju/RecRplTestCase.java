/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.recrpl.ju;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.common.re.launcher.ReLauncher;
import org.polarsys.capella.common.re.queries.CatalogElement_AllUsedElements;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.re.commands.CreateRecCommand;
import org.polarsys.capella.core.re.commands.CreateReplicaCommand;
import org.polarsys.capella.core.re.commands.DeleteReplicaAndRelatedElementsCommand;
import org.polarsys.capella.core.re.commands.DeleteReplicaPreserveRelatedElementsCommand;
import org.polarsys.capella.core.re.commands.UpdateCurCommand;
import org.polarsys.capella.core.re.commands.UpdateDefCommand;
import org.polarsys.capella.core.re.commands.UpdateReplicaCommand;
import org.polarsys.capella.core.re.launcher.UpdateDefLauncher;
import org.polarsys.capella.core.re.launcher.UpdateReplicaLauncher;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.transposer.SharedWorkflowActivityParameter;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.recrpl.ju.handlers.filter.CheckedFilteringHandler;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;

/**
 * This class is a generic test case for REC/REPL tests.<br>
 * To use it, create a test case that inherits from this class and implement abstract methods, that are<br>
 * (see method documentation for more details) :<br>
 * <br>
 * - performTest()<br>
 * - getRequiredTestModels()<br>
 * @author Erwan Brottier
 */
public abstract class RecRplTestCase extends BasicTestCase {

  protected Resource modelResource;

  protected void setSuffixed(CatalogElement rec, String target) {
    // a element should be suffixed
    EObject REC_LF1 = getObject(target);
    for (CatalogElementLink link : rec.getOwnedLinks()) {
      if (link.getTarget() == REC_LF1) {
        link.setSuffixed(true);
      }
    }
  }

  @Override
  public void test() throws Exception {
    final Exception[] t = new Exception[] { null };
    TransactionHelper.getExecutionManager(getProject()).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        try {
          performTest();
        } catch (Exception e) {
          t[0] = e;
        }
      }
    });
    if (t[0] != null) {
      throw t[0];
    }
  }

  /**
   * @param lF1rpl
   * @param object
   */
  protected void mustBeOwnedBy(EObject object, EObject container) {
    assertTrue(object != null);
    assertTrue(object.eContainer().equals(container));
  }

  /** overrides this method to write the test code */
  public abstract void performTest() throws Exception;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CatalogElement createREC(Collection<EObject> elements) {
    ICommand command = new CreateRecCommand((Collection) elements, new NullProgressMonitor());
    executeCommand(command);

    // for (EObject element : elements) {
    // mustReference(REC, element);
    // }

    // Check if a new REC is connected to all given elements
    CatalogElement newREC = null;
    Iterator<EObject> element = elements.iterator();
    while (element.hasNext()) {
      EObject object = element.next();
      Collection<CatalogElement> relatedRecs = ReplicableElementExt.getReferencingReplicableElements(object);
      CatalogElement lastRec = relatedRecs.toArray(new CatalogElement[0])[relatedRecs.size() - 1];
      assertTrue(lastRec != null);
      assertTrue((newREC == null) || (lastRec == newREC));
      newREC = lastRec;
    }
    assertTrue(newREC.eContainer() != null);
    return newREC;
  }

  protected CatalogElement createReplica(Collection<EObject> elements, CatalogElement REC) {
    return createReplica(elements, REC, null);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected CatalogElement createReplica(Collection<EObject> elements, CatalogElement REC, String suffix) {
    Collection<CatalogElement> RPLS = ReplicableElementExt.getReplicas(REC);

    ICommand command = new CreateReplicaCommand((Collection) elements, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE, REC);
    if (suffix != null) {
      RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX, suffix);
    }
    executeCommand(command);

    // A new RPL must be created
    Collection<CatalogElement> RPLS2 = ReplicableElementExt.getReplicas(REC);
    RPLS2.removeAll(RPLS);
    assertTrue(RPLS2.size() == 1);

    // CHeck Origin and Kind of the new RPL
    CatalogElement RPL = RPLS2.iterator().next();
    assertTrue(RPL.getKind() == CatalogElementKind.RPL);
    assertTrue(RPL.getOrigin().equals(REC));

    // All elements from REC should be newly created and linked to the RPL
    for (Object element : QueryInterpretor.executeQuery(CatalogElement_AllUsedElements.class.getSimpleName(), REC, new QueryContext())) {
      EObject ref = ReplicableElementExt.getReferencingElement(RPL, (EObject) element);
      assertTrue((ref != null) && (ref != element));
    }

    return RPL;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void updateCur(Collection<EObject> elements, CatalogElement rec) {
    ICommand command = new UpdateCurCommand((Collection) elements, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, rec);
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void updateReplica(Collection<EObject> elements, CatalogElement replica) {
    ICommand command = new UpdateReplicaCommand((Collection) elements, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void updateReplica(Collection<EObject> elements, CatalogElement replica, String suffix) {
    ICommand command = new UpdateReplicaCommand((Collection) elements, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX, suffix);
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void updateReplicaCheck(Collection<EObject> elements, CatalogElement replica) {
    ICommand command = new UpdateReplicaCommand((Collection) elements, new NullProgressMonitor()) {

      @Override
      protected ReLauncher createLauncher() {
        return new UpdateReplicaLauncher() {

          @Override
          protected SharedWorkflowActivityParameter getSharedParameter(String workflowId) {
            SharedWorkflowActivityParameter param = super.getSharedParameter(workflowId);
            param.addSharedParameter(new GenericParameter<IHandler>(ITransitionConstants.FILTERING_DIFFERENCES_HANDLER, new CheckedFilteringHandler(),
                workflowId));
            return param;
          }
        };
      }

    };

    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void updateDefCheck(Collection<EObject> elements) {
    ICommand command = new UpdateDefCommand((Collection) elements, new NullProgressMonitor()) {

      @Override
      protected ReLauncher createLauncher() {
        return new UpdateDefLauncher() {

          @Override
          protected SharedWorkflowActivityParameter getSharedParameter(String workflowId) {
            SharedWorkflowActivityParameter param = super.getSharedParameter(workflowId);
            param.addSharedParameter(new GenericParameter<IHandler>(ITransitionConstants.FILTERING_DIFFERENCES_HANDLER, new CheckedFilteringHandler(),
                workflowId));
            return param;
          }
        };
      }

    };
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void updateDef(Collection<EObject> elements) {
    ICommand command = new UpdateDefCommand((Collection) elements, new NullProgressMonitor());
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void deleteReplicaAndRelatedElements(Collection<EObject> elements) {
    ICommand command = new DeleteReplicaAndRelatedElementsCommand((Collection) elements, new NullProgressMonitor());
    executeCommand(command);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  protected void deleteReplicaPreserveRelatedElements(Collection<EObject> elements) {
    ICommand command = new DeleteReplicaPreserveRelatedElementsCommand((Collection) elements, new NullProgressMonitor());
    executeCommand(command);
  }

  protected void mustReference(CatalogElement rec, EObject object) {
    assertTrue(ReplicableElementExt.getReferencingReplicableElements(object).contains(rec));
  }

  protected void mustNotReference(CatalogElement rec, EObject object) {
    assertTrue(!ReplicableElementExt.getReferencingReplicableElements(object).contains(rec));
  }

  protected RecCatalog getRecCatalog() {
    Project project = getProject();
    SystemEngineering sys = SystemEngineeringExt.getSystemEngineering(project);
    for (EObject element : sys.getOwnedExtensions()) {
      if (element instanceof RecCatalog) {
        return (RecCatalog) element;
      }
    }
    return null;
  }

  protected CatalogElement getREC(String name) {
    for (CatalogElement element : getRecCatalog().getOwnedElements()) {
      if ((element.getKind() == CatalogElementKind.REC) && element.getName().equals(name)) {
        return element;
      }
    }
    return null;
  }

  protected Resource getModelResource() {
    if (modelResource == null) {
      Session session = getSessionForTestModel(getRequiredTestModels().get(0));
      modelResource = (Resource) session.getSemanticResources().toArray()[0];
    }
    return modelResource;
  }

  protected Project getProject() {
    return (Project) getModelResource().getContents().iterator().next();
  }

  protected void executeCommand(ICommand command) {
    try {
      TransactionHelper.getExecutionManager(getProject()).execute(command);
    } finally {
      RecRplCommandManager.clear();
    }
  }

  protected EObject getObject(String id) {
    return getModelResource().getEObject(id);
  }

  protected Collection<EObject> getObjects(String... ids) {
    Collection<EObject> objects = new ArrayList<EObject>();
    for (String id : ids) {
      objects.add(getObject(id));
    }
    return objects;
  }
}
