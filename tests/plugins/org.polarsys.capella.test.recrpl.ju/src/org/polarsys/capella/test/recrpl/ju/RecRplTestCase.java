/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementKind;
import org.polarsys.capella.common.re.CatalogElementLink;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.helpers.ReplicableElementExt;
import org.polarsys.capella.common.re.queries.CatalogElement_UsedElements;
import org.polarsys.capella.core.data.capellamodeller.Library;
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
import org.polarsys.capella.core.re.project.handlers.ProjectRecHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.DefaultMergeHandler;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.kitalpha.cadence.core.api.parameter.GenericParameter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

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

  /**
   * Override this method to write the test code
   **/
  public void performTest() throws Exception {
    // this does nothing by default
  }
  
  
  
  protected CatalogElement createRECWholeContent(Library library) {
    ProjectRecHandler handler = new ProjectRecHandler.Create();
    try {
      handler.execute(Collections.singletonList(library));
    } catch (ExecutionException e) {
      assertFalse(e.getMessage(), true);
    }
    
    CatalogElementPkg pkg = (CatalogElementPkg)EObjectExt.getAll(library, RePackage.Literals.CATALOG_ELEMENT_PKG).iterator().next();
    return pkg.getOwnedElements().get(pkg.getOwnedElements().size() - 1);
    
  }
  
  protected CatalogElement createREC(Collection<? extends EObject> elements) {
    CreateRecCommand command = new CreateRecCommand(elements, new NullProgressMonitor());
    executeCommand(command);
    assertFalse(command.isRolledBack());

    // Check if a new REC is connected to all given elements
    CatalogElement newREC = null;
    Iterator<? extends EObject> element = elements.iterator();
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

  protected CatalogElement createReplica(Collection<? extends EObject> selection, CatalogElement REC, String suffix) {
    Collection<CatalogElement> RPLS = ReplicableElementExt.getReplicas(REC);

    CreateReplicaCommand command = new CreateReplicaCommand(selection, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE, REC);
    if (suffix != null) {
      RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX, suffix);
    }
    executeCommand(command);
    assertFalse(command.isRolledBack());

    // A new RPL must be created
    Collection<CatalogElement> RPLS2 = ReplicableElementExt.getReplicas(REC);
    RPLS2.removeAll(RPLS);
    assertTrue(RPLS2.size() == 1);

    // Check Origin and Kind of the new RPL
    CatalogElement RPL = RPLS2.iterator().next();
    assertTrue(RPL.getKind() == CatalogElementKind.RPL);
    assertTrue(RPL.getOrigin().equals(REC));

    // All elements from REC should be newly created and linked to the RPL
    for (Object element : QueryInterpretor.executeQuery(CatalogElement_UsedElements.class.getSimpleName(), REC, new QueryContext())) {
      EObject ref = ReplicableElementExt.getReferencingElement(RPL, (EObject) element);
      assertTrue((ref != null) && (ref != element));
    }

    return RPL;
  }

  protected void updateCur(Collection<EObject> selection, CatalogElement rec) {
    UpdateCurCommand command = new UpdateCurCommand(selection, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, rec);
    executeCommand(command);
    assertFalse(command.isRolledBack());
  }

  protected void updateReplica(Collection<EObject> elements, CatalogElement replica) {
    UpdateReplicaCommand command = new UpdateReplicaCommand(elements, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
    executeCommand(command);
    assertFalse(command.isRolledBack());
  }

  protected void updateReplica(Collection<EObject> elements, CatalogElement replica, String suffix) {
    UpdateReplicaCommand command = new UpdateReplicaCommand(elements, new NullProgressMonitor());
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIX, suffix);
    executeCommand(command);
    assertFalse(command.isRolledBack());
  }

  protected boolean updateReplica(Collection<EObject> elements, CatalogElement replica, final Collection<String> disabledCategoryFilters){
    UpdateReplicaCommand command = new UpdateReplicaCommand(elements, new NullProgressMonitor());
    command.addSharedParameter(new GenericParameter<IHandler>(ITransitionConstants.MERGE_DIFFERENCES_HANDLER, new DefaultMergeHandler(true){

              @Override
              public void addCategory(ICategoryItem filter, IContext context) {
                super.addCategory(filter, context);
                if (disabledCategoryFilters.contains(filter.getId())){
                  filter.setActive(false);
                }
              }

            }, "Merge")); //$NON-NLS-1$

    RecRplCommandManager.push(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET, replica);
    executeCommand(command);
    return !command.isRolledBack();
  }

  protected void updateDef(Collection<EObject> elements) {
    ICommand command = new UpdateDefCommand(elements, new NullProgressMonitor());
    executeCommand(command);
  }

  protected void deleteReplicaAndRelatedElements(Collection<EObject> elements) {
    ICommand command = new DeleteReplicaAndRelatedElementsCommand(elements, new NullProgressMonitor());
    executeCommand(command);
  }

  protected void deleteReplicaPreserveRelatedElements(Collection<EObject> elements) {
    ICommand command = new DeleteReplicaPreserveRelatedElementsCommand(elements, new NullProgressMonitor());
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
      for (Resource resource : session.getSemanticResources()) {
        // Exclude AFM's Metadata resource
        if (resource.getContents().get(0) instanceof Project) {
          modelResource = resource;
        }
      }
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

  /**
   * Executes the runnable in a read write command and returns the rollback status for the command
   * @param r
   * @return true if the command wasn't rolled back, false if the command was rolled back
   */
  protected boolean executeCommand(final Runnable r) {
    AbstractReadWriteCommand arwc = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        r.run();
      }
    };
    executeCommand(arwc);
    return !arwc.isRolledBack();
  }

  protected void expectRollback(final Runnable r) {
    assertFalse(executeCommand(r));
  }

  protected void expectNoRollback(final Runnable r) {
    assertTrue(executeCommand(r));
  }

  protected void expectNoRollback(boolean b) {
    assertTrue(b);
  }

  protected EObject getObject(String id) {
    return getModelResource().getEObject(id);
  }

  protected Collection<EObject> getObjects(String... ids) {
    Collection<EObject> objects = new ArrayList<EObject>();
    for (String id : ids) {
      EObject object = getObject(id);
      assertNotNull(object);
      objects.add(object);
    }
    return objects;
  }
}
