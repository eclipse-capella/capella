/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.diffmerge;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.scopes.IEditableTreeDataScope;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;
import org.polarsys.capella.core.compare.CapellaComparisonMethod;
import org.polarsys.capella.core.compare.CapellaComparisonMethodFactory;
import org.polarsys.capella.core.compare.CapellaScopeFactory;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * This test ensures that diffmerge is properly creating Editing domains or reuse the sessions one.
 */
public class DiffmergeDomain extends BasicTestCase {

  private String CAPELLA1 = "DiffMergeSourcePrj/DiffMergeSourcePrj.capella";
  private String CAPELLA2 = "DiffMergeSourceV1Prj/DiffMergeSourcePrj.capella";

  private String AIRD1 = "DiffMergeSourcePrj/DiffMergeSourcePrj.aird";
  private String AIRD2 = "DiffMergeSourceV1Prj/DiffMergeSourcePrj.aird";

  /**
   * We ensure that sessions are closed before the test.
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    IFile file1 = FileHelper.getPlatformFile(AIRD1);
    IFile file2 = FileHelper.getPlatformFile(AIRD2);

    Session session = SessionManager.INSTANCE.getExistingSession(EcoreUtil2.getURI(file1));
    if (session != null) {
      session.close(new NullProgressMonitor());
    }
    Session session2 = SessionManager.INSTANCE.getExistingSession(EcoreUtil2.getURI(file2));
    if (session2 != null) {
      session2.close(new NullProgressMonitor());
    }
  }

  @Override
  public void test() throws Exception {
    onCapellaResource();
    onClosedSession();
    onOpenedSession1();
    onOpenedSession2();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("DiffMergeSourcePrj", "DiffMergeSourceV1Prj");
  }

  /**
   * On Capella Resource, we don't have a session, so we create a Semantic Domain.
   * 
   * Diffmerge has two way to create an EditingDomain. We ensure that both way have created a domain using
   * ExecutionManagerRegistry API.
   */
  public void onCapellaResource() throws Exception {
    IFile file1 = FileHelper.getPlatformFile(CAPELLA1);
    IFile file2 = FileHelper.getPlatformFile(CAPELLA2);
    CapellaComparisonMethod method = createComparison(file1, file2);

    // First way, method.getEditingDomain.
    EditingDomain commonDomain = method.getEditingDomain();
    assertTrue(commonDomain instanceof SemanticEditingDomain);
    assertNotNull(ExecutionManagerRegistry.getInstance().getExecutionManager(commonDomain));

    // Second way, when we create a SpecificDomain
    EditingDomain specificDomain = getSpecificDomain(method, Role.TARGET);
    assertTrue(specificDomain instanceof SemanticEditingDomain);
    assertNotNull(ExecutionManagerRegistry.getInstance().getExecutionManager(specificDomain));
  }

  /**
   * On Closed session, we don't have any session, so we create domains
   */
  public void onClosedSession() throws Exception {
    IFile file1 = FileHelper.getPlatformFile(AIRD1);
    IFile file2 = FileHelper.getPlatformFile(AIRD2);

    CapellaComparisonMethod method = createComparison(file1, file2);
    EditingDomain commonDomain = method.getEditingDomain();
    assertTrue(commonDomain instanceof SemanticEditingDomain);

    EditingDomain specificDomain = getSpecificDomain(method, Role.TARGET);
    assertTrue(specificDomain instanceof SemanticEditingDomain);
  }

  /**
   * When one of session is opened, then we can use this as a commonDomain.
   */
  public void onOpenedSession1() throws Exception {
    IFile file1 = FileHelper.getPlatformFile(AIRD1);
    IFile file2 = FileHelper.getPlatformFile(AIRD2);
    Session session = getSession("DiffMergeSourcePrj");

    CapellaComparisonMethod method = createComparison(file1, file2);

    // The commonDomain will be the session one
    EditingDomain commonDomain = method.getEditingDomain();
    assertTrue(commonDomain instanceof SemanticEditingDomain);
    assertTrue(commonDomain.equals(session.getTransactionalEditingDomain()));
    assertNotNull(ExecutionManagerRegistry.getInstance().getExecutionManager(commonDomain));

    // The specificDomain of 1 will be the session one
    EditingDomain specificDomain = getSpecificDomain(method, Role.TARGET);
    assertEquals(commonDomain, specificDomain);

    // The specificDomain of session2 will be a new one.
    // (In a real context, we will load the aird of the closed session in the opened one though)
    EditingDomain specificDomain2 = getSpecificDomain(method, Role.REFERENCE);
    assertNotEquals(commonDomain, specificDomain2);
    assertTrue(specificDomain2 instanceof SemanticEditingDomain);
    assertNotNull(ExecutionManagerRegistry.getInstance().getExecutionManager(specificDomain2));
  }

  /**
   * When two sessions are opened, then we use these two domains and there will be no commonDomain.
   */
  public void onOpenedSession2() throws Exception {
    IFile file1 = FileHelper.getPlatformFile(AIRD1);
    IFile file2 = FileHelper.getPlatformFile(AIRD2);
    Session session = getSession("DiffMergeSourcePrj");
    Session session2 = getSession("DiffMergeSourceV1Prj");
    assertNotEquals(session, session2);

    CapellaComparisonMethod method = createComparison(file1, file2);

    EditingDomain commonDomain = method.getEditingDomain();
    assertTrue(commonDomain == null);

    EditingDomain specificDomain = getSpecificDomain(method, Role.TARGET);
    assertTrue(specificDomain instanceof SemanticEditingDomain);
    assertTrue(specificDomain.equals(session.getTransactionalEditingDomain()));
    assertNotNull(ExecutionManagerRegistry.getInstance().getExecutionManager(specificDomain));

    EditingDomain specificDomain2 = getSpecificDomain(method, Role.REFERENCE);
    assertTrue(specificDomain2 instanceof SemanticEditingDomain);
    assertTrue(specificDomain2.equals(session2.getTransactionalEditingDomain()));
    assertNotNull(ExecutionManagerRegistry.getInstance().getExecutionManager(specificDomain2));
  }

  protected CapellaComparisonMethod createComparison(IFile file1, IFile file2) {
    CapellaScopeFactory scopFct = new CapellaScopeFactory();
    IModelScopeDefinition left = scopFct.createScopeDefinition(file1, null, true);
    IModelScopeDefinition right = scopFct.createScopeDefinition(file2, null, true);
    CapellaComparisonMethodFactory compFactory = new CapellaComparisonMethodFactory();
    CapellaComparisonMethod method = (CapellaComparisonMethod) compFactory.createComparisonMethod(left, right, null);
    method.setVerbose(false);
    return method;
  }

  /**
   * This method return a domain of the given role. It will create a domain if there is no opened session or reuse the
   * session one.
   * 
   * Note: When we use the diffmerge with the UI, this specific domain is not always used.
   * 
   * @see EMFDiffMergeEditorInput.loadScopes
   */
  protected EditingDomain getSpecificDomain(CapellaComparisonMethod method, Role role) {
    IEditableTreeDataScope<?> _leftScope = method.getModelScopeDefinition(role).createScope(method.getResourceSet(role));
    EditingDomain specificDomain = ((IEditingDomainProvider) _leftScope).getEditingDomain();
    return specificDomain;
  }

}
