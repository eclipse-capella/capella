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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
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

    // That's a hack to activate the live validation context for move rule I_36
    // TODO maybe bind the move validation rule to an additional context which is only active when tests are running
    moveView = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("org.polarsys.capella.core.libraries.ui.moveview");


  }


  @Override
  public void tearDown() throws Exception {
    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(moveView);
    ExecutionManagerRegistry.getInstance().removeManager(manager);
    super.tearDown();
  }


  /**
   * Executes the runnable in a read write command and returns the rollback status for the command
   * TODO move to ExecutionManager for 1.3 see https://bugs.polarsys.org/show_bug.cgi?id=1874
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
    manager.execute(arwc);
    return !arwc.isRolledBack();
  }

  protected void expectRollback(final Runnable r) {
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
  protected void expectRollback(final Runnable r, String... expectedErrors) {

    Collection<IConstraintStatus> result = new ArrayList<IConstraintStatus>();
    IValidationListener listener = new IValidationListener() {
      @Override
      public void validationOccurred(ValidationEvent event) {
        result.addAll(event.getValidationResults());
      }
    };
    ModelValidationService.getInstance().addValidationListener(listener);

    try {
      assertFalse(executeCommand(r));
    } finally {
      ModelValidationService.getInstance().removeValidationListener(listener);
    }

    for (String expected : expectedErrors) {
      assertNotNull("Expected validation error: " + expected, find(expected, result));
    }

  }

  protected void expectNoRollback(final Runnable r) {
    assertTrue(executeCommand(r));
  }


  private IStatus find(String message, Collection<? extends IStatus> status) {
    for (IStatus s : status) {
      if (s.isMultiStatus()) {
        IStatus result = find(message, Arrays.asList(s.getChildren()));
        if (result != null) {
          return result;
        }
      } else {
        System.out.println(s.getMessage());
        if (message.equals(s.getMessage())) {
          return s;
        }
      }
    }
    return null;
  }


}
