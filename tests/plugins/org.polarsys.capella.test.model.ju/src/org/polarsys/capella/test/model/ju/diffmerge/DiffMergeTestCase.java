/*******************************************************************************
 * Copyright (c) 2019, THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.diffmerge;

import static org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants.SLASH_CHARACTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EMatch;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.diffdata.impl.EElementPresenceImpl;
import org.eclipse.emf.diffmerge.ui.specification.IModelScopeDefinition;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.compare.CapellaComparisonMethod;
import org.polarsys.capella.core.compare.CapellaComparisonMethodFactory;
import org.polarsys.capella.core.compare.CapellaDiffPolicy;
import org.polarsys.capella.core.compare.CapellaMatchPolicy;
import org.polarsys.capella.core.compare.CapellaMergePolicy;
import org.polarsys.capella.core.compare.CapellaScopeFactory;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public abstract class DiffMergeTestCase extends NonDirtyTestCase {
  protected String sourceModel;
  protected String targetModel;

  protected IProject sourceModelProject;
  protected IProject targetMigratedModelProject;

  protected SessionContext sourcePrjContext;
  protected SessionContext targetPrjContext;

  // model DiffMergeDourcePrj
  protected static final String sourceSystemFunction1Id = "aae39f34-777e-4b43-bca6-c3138251ad77";
  protected static final String sourceCapabilityRealization1 = "6c05898f-9f08-4dd5-8c36-c71c29e41dc1";

  protected static final String sourcePartLA2Id = "f89b1897-875f-422d-97c8-a33eaacf15bf";
  protected static final String sourcePartLA3Id = "c5dbe972-d618-4086-b13d-e0e1b4519454";
  protected static final String sourceLA2Id = "d8e713e4-dfc9-4792-af32-346481959b32";
  protected static final String sourceLA3Id = "b73ca85b-9890-4ac3-b842-f50ff91a74b8";
  protected static final String sourceCEId = "ad69d748-731e-490a-b441-d00d25340564";
  protected static final String sourceLA2_CP1Id = "d714d416-3baa-43e8-9836-2cb50f6cba64";
  protected static final String sourceLA3_CP1Id = "e2592592-da1c-4f64-9a87-e6fb07457672";

  protected static final String refSystemFunction2Id = "0f060607-cea4-4865-adbb-53adb607d880";
  protected static final String sourcePhysicalComponentLibId = "be012193-229a-460b-aaab-ac14f23e14e3";
  protected static final String classFromLibId = "83e649c9-86d3-4537-a898-64a664b2e689";

  // model DiffMergeSourceV1Prj
  protected static final String physicalComponentId = "5bb4af52-2d44-4959-af8a-81f4131786db";
  protected static final String physicalPartId = "8a27678b-5a3a-4934-b384-96017673005f";
  protected static final String capabilityRealization1Id = "118ea742-c94b-46f9-90e7-da4641c3b075";
  protected static final String capabilityRealization2Id = "20be556b-8ae3-432a-9e60-b0e5b2bfef71";
  protected static final String capabilityGeneralizationId = "7f926bf5-28aa-4ee0-a7c1-f0885cc49a49";

  // model DiffMergeTargetPrj
  protected static final String targetOperationalActivity1Id = "5998954a-ada1-4223-b1ed-e19a35732797";
  protected static final String targetSystemFunction1Id = "8089b61e-1fbf-49bf-bc4c-2e9baee8e3d5";
  protected static final String targetClassId = "e5284017-5634-4925-852a-c341935a2d78";

  // model DiffMergeSourceT4CPrj
  protected static final String t4cPhysicalFunction1Id = "43c10214-a408-4735-ac5a-0f1e59315d60";
  protected static final String t4cPhysicalFunction2Id = "0a07fd21-257f-4bc1-ab3d-3e2f24ff794c";
  protected static final String t4cFOP1PhysicalFunction1Id = "c2c1a751-d455-49be-b807-5e414a07594d";
  protected static final String t4cFIP1PhysicalFunction2Id = "c77f716c-4912-4a36-8ee5-1601c28c0e69";
  protected static final String t4cFunctionalExchange1Id = "7cb3d859-c9e3-4e76-b363-adf5709386c2";
  protected static final String t4cFunctionRealizationId = "1d8b3c70-874a-41cc-86a5-48bcceaebf6f";
  protected static final String t4cFunctionalChain1Id = "06fa1726-3307-460c-b4a3-06c21a01bf7a";
  protected static final String t4cFunctionalChainInvolvementLinkId = "ea73cee0-d439-4f0f-b3f8-4478ecb10fdf";
  protected static final String t4cFunctionalChainInvolvementFunction2Id = "8d04f0b8-835b-496e-bd02-322fd3e61b89";
  protected static final String t4cFunctionalChainInvolvementFunction1Id = "409b227a-0869-47dd-bcaa-ba259bd6c793";

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    sourceModel = getSourcePrjName();
    targetModel = getTargetPrjName();
    sourceModelProject = IResourceHelpers.getEclipseProjectInWorkspace(sourceModel);
    targetMigratedModelProject = IResourceHelpers.getEclipseProjectInWorkspace(targetModel);

    sourcePrjContext = getSessionContextSourceProject();
    targetPrjContext = getSessionContextTargetProject();
  }

  @Override
  public void test() throws Exception {
    IFile file1 = FileHelper.getPlatformFile(sourceModel + SLASH_CHARACTER + getSourceResourceName());
    IFile file2 = FileHelper.getPlatformFile(targetModel + SLASH_CHARACTER + getTargetResourceName());

    CapellaScopeFactory scopFct = new CapellaScopeFactory();
    IModelScopeDefinition leftScopeSpec = scopFct.createScopeDefinition(file1, null, true);
    IModelScopeDefinition rightScopeSpec = scopFct.createScopeDefinition(file2, null, true);

    CapellaComparisonMethodFactory compFactory = new CapellaComparisonMethodFactory();
    CapellaComparisonMethod method = (CapellaComparisonMethod) compFactory.createComparisonMethod(leftScopeSpec,
        rightScopeSpec, null);
    method.setVerbose(false);

    Role leftRole = method.getLeftRole();
    IEditableModelScope _leftScope = leftScopeSpec.createScope(method.getResourceSet(leftRole));

    IEditableModelScope _rightScope = method.getModelScopeDefinition(leftRole.opposite())
        .createScope(method.getResourceSet(leftRole.opposite()));

    EComparisonImpl comparison = new EComparisonImpl(_leftScope, _rightScope, null);
    IStatus result = comparison.compute(getMatchPolicy(), new CapellaDiffPolicy(), new CapellaMergePolicy(),
        new NullProgressMonitor());

    method.dispose();

    if (result.isOK()) {
      if (comparison.hasRemainingDifferences()) {
        checkDifferences(comparison);
      } else {
        fail("No differences found");
      }
    } else {
      fail("Comparation failed");
    }
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getSourcePrjName(), getTargetPrjName());
  }

  private Resource getResourceToTest(IProject project, String fileName) {
    IFile file = project.getFile(fileName);
    URI fileURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
    ResourceSet resourceSet = new ResourceSetImpl();

    return resourceSet.getResource(fileURI, true);
  }

  public void assertCheckDifferences(EComparisonImpl comparison, Role role, List<ModelElement> elements) {
    List<IDifference> differences = comparison.getDifferences(role);
    List<ModelElement> diffModelElements = getModelElementsFromDiff(differences, role);

    assertTrue("Not all the elements resulted in the diff operation are checked.",
        diffModelElements.size() == elements.size());

    List<String> notFoundElements = new ArrayList<String>();
    for (ModelElement element : elements) {
      if (!diffModelElements.contains(element)) {
        notFoundElements.add("{ " + element.getId() + ": " + element.getClass() + " }");
      }
    }
    assertTrue("The following elements are not found in the diff result: " + notFoundElements,
        notFoundElements.isEmpty());
  }

  protected List<ModelElement> getModelElementsFromDiff(List<IDifference> differences, Role role) {
    List<ModelElement> elements = new ArrayList<ModelElement>();
    for (IDifference diff : differences) {
      if (diff instanceof EElementPresenceImpl) {
        EMatch eMatch = ((EElementPresenceImpl) diff).getElementMatch();
        EObject object = null;
        if (role == Role.TARGET) {
          object = eMatch.getTarget();

        } else {
          object = eMatch.getReference();
        }
        if (object != null && object instanceof ModelElement) {
          elements.add((ModelElement) object);
        }
      }
    }
    return elements;
  }

  protected void checkDifferences(EComparisonImpl comparison) {
    assertCheckDifferences(comparison, Role.TARGET, getModelElementsSourceProject(getTargetDiffList()));
    assertCheckDifferences(comparison, Role.REFERENCE, getModelElementsTargetProject(getReferenceDiffList()));
  }

  protected abstract CapellaMatchPolicy getMatchPolicy();

  protected abstract List<String> getTargetDiffList();

  protected abstract List<String> getReferenceDiffList();

  protected abstract String getSourcePrjName();

  protected abstract String getTargetPrjName();

  protected String getSourceResourceName() {
    return getSourcePrjName() + ".aird";
  }

  protected String getTargetResourceName() {
    return getTargetPrjName() + ".aird";
  }

  protected SessionContext getSessionContextSourceProject() {
    Session session = getSession(getSourcePrjName());
    return new SessionContext(session);
  }

  protected SessionContext getSessionContextTargetProject() {
    Session session = getSession(getTargetPrjName());
    return new SessionContext(session);
  }

  protected List<ModelElement> getModelElementsSourceProject(List<String> ids) {
    List<ModelElement> elements = new ArrayList<ModelElement>();
    for (String id : ids) {
      elements.add(getModelElement(id, sourcePrjContext));
    }
    return elements;
  }

  protected List<ModelElement> getModelElementsTargetProject(List<String> ids) {
    List<ModelElement> elements = new ArrayList<ModelElement>();
    for (String id : ids) {
      elements.add(getModelElement(id, targetPrjContext));
    }
    return elements;
  }

  protected ModelElement getModelElement(String id, SessionContext context) {
    return context.getSemanticElement(id);
  }
}
