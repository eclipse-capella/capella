/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.i;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.IViewPart;
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
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.validation.rules.ju.TestValidationRulesPlugin;

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

    String projectPathName = "/project/project." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
    projectSkeleton = new CapellaModelSkeleton.Builder(manager)
        .setURI(URI.createPlatformResourceURI(projectPathName, false)) // must be platform:/resource or libs won't
                                                                       // work..
        .setName("project").build();

    String libraryPathName = "/library/library." + CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION;
    librarySkeleton = new CapellaModelSkeleton.Builder(manager)
        .setURI(URI.createPlatformResourceURI(libraryPathName, false)).setName("library")
        .setRootType(CapellamodellerPackage.Literals.LIBRARY).build();

    final ModelInformation projectInformation = CapellaLibraryExt
        .getModelInformation(projectSkeleton.getProject().eResource(), false);
    final ModelInformation libraryInformation = CapellaLibraryExt
        .getModelInformation(librarySkeleton.getProject().eResource(), false);

    manager.execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        LibraryReference ref = LibrariesFactory.eINSTANCE.createLibraryReference();
        ref.setLibrary(libraryInformation);
        ref.setAccessPolicy(AccessPolicy.READ_AND_WRITE);
        projectInformation.getOwnedReferences().add(ref);
      }
    });

  }

  @Override
  public void tearDown() throws Exception {
    ExecutionManagerRegistry.getInstance().removeManager(manager);
    super.tearDown();
  }

  /**
   * Executes the runnable in a read write command. RollbackExceptions and InterruptedExceptions are forwarded to the
   * caller. TODO move to ExecutionManager for 1.4 see https://bugs.polarsys.org/show_bug.cgi?id=1874
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

  protected void expectNoRollback(final Runnable r) throws InterruptedException, RollbackException {
    ReferentialConstraintsRecorder recorder = new ReferentialConstraintsRecorder(manager.getEditingDomain(),
        new String[0]);
    try {
      executeCommand(r);
    } finally {
      recorder.dispose();
    }
  }

  protected void expectRollback(final Runnable r, String... expectedErrors) throws InterruptedException {
    ReferentialConstraintsRecorder recorder = new ReferentialConstraintsRecorder(manager.getEditingDomain(),
        expectedErrors);
    try {
      executeCommand(r);
    } catch (RollbackException expected) {
      // that is expected
    } finally {
      recorder.dispose();
    }
    if (recorder.missingExpected.size() > 0) {
      fail(String.join(System.lineSeparator(), recorder.missingExpected));
    }
  }

  private static class ReferentialConstraintsRecorder {

    private Collection<String> missingExpected;
    private final TransactionalEditingDomain domain;
    private final ReferentialConstraintsResourceSetListener listener;

    public ReferentialConstraintsRecorder(TransactionalEditingDomain domain, String... expectedErrors) {
      this.missingExpected = new ArrayList<>(Arrays.asList(expectedErrors)); // we want to modify the collection later..
      this.domain = domain;
      listener = new ReferentialConstraintsResourceSetListener(this::handleDiagnostic);
      domain.addResourceSetListener(listener);
    }

    public void handleDiagnostic(Diagnostic ms) throws RollbackException {
      for (Iterator<String> it = missingExpected.iterator(); it.hasNext();) {
        String expected = it.next();
        if (findDiagnostic(expected, ms.getChildren()) != null) {
          it.remove();
        }
      }
      throw new RollbackException(new Status(IStatus.ERROR, TestValidationRulesPlugin.PLUGIN_ID, "rolling back")); //$NON-NLS-1$
    }

    private Diagnostic findDiagnostic(String message, Collection<Diagnostic> diagnostics) {
      for (Diagnostic d : diagnostics) {
        if (message.equals(d.getMessage())) {
          return d;
        }
      }
      return null;
    }

    private void dispose() {
      domain.removeResourceSetListener(listener);
    }
  }

}
