/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IValidationListener;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.emf.validation.service.ValidationEvent;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.libraries.AccessPolicy;
import org.polarsys.capella.common.libraries.LibrariesFactory;
import org.polarsys.capella.common.libraries.LibraryReference;
import org.polarsys.capella.common.libraries.ModelInformation;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.core.validation.constraint.ReferentialConstraintsResourceSetListener;
import org.polarsys.capella.core.libraries.model.CapellaLibraryExt;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Tests ability to move elements.
 */
public abstract class Rule_I37_38 extends BasicTestCase {

  ExecutionManager manager;
  CapellaModelSkeleton projectSkeleton;
  CapellaModelSkeleton librarySkeleton;
  IViewPart moveView;
  ResourceSetListener validateExecuteListener;
  
  @SuppressWarnings("nls")
  @Override
  public void setUp() throws Exception {
    super.setUp();
    manager = ExecutionManagerRegistry.getInstance().addNewManager();

    projectSkeleton = new CapellaModelSkeleton.Builder(manager)
        .setURI(URI.createPlatformResourceURI("/project/project.melodymodeller", false)) // must be platform:/resource or libs won't work..
        .setName("project")
        .build();

    librarySkeleton = new CapellaModelSkeleton.Builder(manager)
        .setURI(URI.createPlatformResourceURI("/library/library.melodymodeller", false))
        .setName("library")
        .setRootType(CapellamodellerPackage.Literals.LIBRARY)
        .build();

    final ModelInformation projectInformation = CapellaLibraryExt.getModelInformation(projectSkeleton.getProject().eResource(), false);
    final ModelInformation libraryInformation = CapellaLibraryExt.getModelInformation(librarySkeleton.getProject().eResource(), false);

    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        LibraryReference ref = LibrariesFactory.eINSTANCE.createLibraryReference();
        ref.setLibrary(libraryInformation);
        ref.setAccessPolicy(AccessPolicy.READ_AND_WRITE);
        projectInformation.getOwnedReferences().add(ref);
      }
    });

    // Tests a variant that always rolls back the transaction when a reference error is encountered
    validateExecuteListener = new ReferentialConstraintsResourceSetListener(ms -> { throw new RollbackException(ms); });
    manager.getEditingDomain().addResourceSetListener(validateExecuteListener);

  }


  @Override
  public void tearDown() throws Exception {
    manager.getEditingDomain().removeResourceSetListener(validateExecuteListener);
    ExecutionManagerRegistry.getInstance().removeManager(manager);
    super.tearDown();
  }


  /**
   * Executes the runnable in a read write command.
   * RollbackExceptions and InterruptedExceptions are forwarded to the caller.
   * TODO move to ExecutionManager for 1.4 see https://bugs.polarsys.org/show_bug.cgi?id=1874
   * 
   * @param r
   * @throws RollbackException 
   * @throws InterruptedException 
   */
  protected void executeCommand(final Runnable r) throws InterruptedException, RollbackException {
    RecordingCommand rc = new RecordingCommand(manager.getEditingDomain()) {
      @Override
      protected void doExecute() {
        r.run();
      }
    };
    TransactionalCommandStack stack = (TransactionalCommandStack) manager.getEditingDomain().getCommandStack();
    stack.execute(rc, Collections.emptyMap());
  }

  protected void expectRollback(final Runnable r) throws InterruptedException {
    expectRollback(r, new String[0]);
  }

  /**
   * Wrap the given runnable into an ICommand and execute it.
   * If the command succeeds fail.
   * If the command is rolled back, check validation events
   * and search for expected validation error messages.
   * If no validation status is found for any expected errors fail.
   *
   *
   *
   * @param r
   * @param expectedErrors
   */
  @SuppressWarnings("nls")
  // This could be improved a bit, by checking rule id and severity but
  // for now it's sufficient. TODO, move somewhere else
  protected void expectRollback(final Runnable r, String... expectedErrors) throws InterruptedException {

    RollbackException expectedException = null;

    try {
      executeCommand(r);
    } catch (RollbackException e){
      expectedException = e;
    }

    assertNotNull(expectedException);

    for (String expected : expectedErrors) {
      assertNotNull("Expected validation error: " + expected, find(expected, Collections.singleton(expectedException.getStatus())));
    }

  }


  private IStatus find(String message, Collection<? extends IStatus> status) {
    for (IStatus s : status) {
      if (s.isMultiStatus()) {
        IStatus result = find(message, Arrays.asList(s.getChildren()));
        if (result != null) {
          return result;
        }
      } else {
        if (message.equals(s.getMessage())) {
          return s;
        }
      }
    }
    return null;
  }


}
