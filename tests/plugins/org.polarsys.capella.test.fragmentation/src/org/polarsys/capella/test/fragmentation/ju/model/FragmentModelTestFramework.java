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
package org.polarsys.capella.test.fragmentation.ju.model;

import static org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants.SLASH_CHARACTER;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.mdsofa.common.helper.FileHelper;
import org.polarsys.capella.core.data.capellamodeller.util.CapellamodellerResourceImpl;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateContainerTool;
import org.polarsys.capella.test.diagram.common.ju.step.tools.CreateDEdgeTool;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.fragmentation.ju.messages.FragmentationMessages;
import org.polarsys.capella.test.fragmentation.ju.utils.FragmentTest;
import org.polarsys.capella.test.fragmentation.ju.utils.FragmentUtils;
import org.polarsys.capella.test.fragmentation.ju.utils.NonAbusiveFragmentTest;
import org.polarsys.capella.test.fragmentation.ju.utils.UnfragmentTest;
import org.polarsys.capella.test.fragmentation.ju.utils.initegrity.IntegrityChecker;
import org.polarsys.capella.test.framework.api.NonDirtyTestCase;
import org.polarsys.capella.test.framework.api.step.AbstractTestStep;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.TestHelper;

public abstract class FragmentModelTestFramework extends NonDirtyTestCase {
  protected String _SFRoot = "252f9917-170a-4d65-83f8-a5b651164a95";
  protected String SF1Id = "1e4876cd-8cc0-4a97-a2fd-8d32f0c65848";
  protected String SF2Id = "932cc184-bcf7-497c-a844-3bbb8d902328";
  protected String _LC1Id = "2ebf9a78-92b3-4c19-82cf-94db30e1fee1";
  protected String _LC2Id = "9d407730-701d-4bdb-b0e5-510cd0204642";
  protected String _LC21Id = "4ab87051-68b9-47e8-8045-f41134c4e614";
  protected String UC1FromLogicalSystem_ID = "1e8b6132-a356-45d8-97cd-9a6bdcd5d768";//
  protected String LC3_ID = "3114de2b-4a24-4597-ab21-ca974f519013";
  protected String LC3_3_ID = "be72dcc7-6293-4909-8731-ce1c7f73f8a1";
  protected String LC3_1_ID = "66bfe889-7ce9-41c8-9c80-0e59c0309f4b";
  protected String LF2_3_ID = "06bf9abd-6e2c-4403-9330-65ed0d208533";
  protected String OA2_ID = "1baf12d4-c3c4-484a-9c72-f37227eb928a";
  protected String LA_LogicalArchitecture_ID = "0492f15a-1b43-4aa5-97bb-bd75a4eec935";
  protected String PA_PhysicalArchitecture_ID = "4278e180-b80e-49f4-bdd3-61c69f0c5946";
  protected String SA_OA2 = "b913cc1d-abd0-409a-b5c8-77328baf4f15";
  protected String OA2_1ID = "dc08ee20-0272-4a3e-975e-0d85c9c8a65d";
  protected String LFPkg1_ID = "ea8f951d-11c3-4265-ae7e-a18cb13a97bd";//
  protected String LC2_CapabilitiesID = "3aa26bfd-ec5b-4afd-9793-765a93bee2b5";//
  protected String PA_PhysicalSystemID = "e0b20cc0-2fd6-4e26-bf20-0e6b1a090d01";
  protected String PA_PhysicalFunctionsRPFID = "250d520b-e135-4089-97fc-83d502dd6d44";
  protected String LA_LogicalSystemID = "e6f8a038-0fc9-48b7-9910-2f665c9fad74";

  protected String _currentProjectName;

  protected String LA_LDFB_OA2 = "[LDFB] OA2 - Logical Data Flow Blank";
  protected String LA_LAB = "[LAB] Logical System with Pattern - Logical Architecture Blank";
  protected Map<String, EObject> mapSemanticObjects;
  protected Set<IFile> projectFiles;
  protected Session session;
  protected SessionContext context;

  protected IFile _airdFile;
  protected IFile _m2File;

  /**
   * Map containing the numbers of columns and lines for each table in session
   */
  protected Map<String, Dimension> _sessionTablesMap = new HashMap<String, Dimension>();

  EStructuralFeature idFeature = ModellingcorePackage.Literals.MODEL_ELEMENT__ID;

  /**
   * Map containing the number of diagram elements for each diagram in session
   */
  protected Map<String, Integer> _sessionDiagramsMap = new HashMap<String, Integer>();

  public String getCurrentProjectName() {
    return "FragmentTestModel";
  }

  public void setCurrentProjectName(String name) {
    _currentProjectName = name;
  }

  protected void init() {
    session = getSession(getCurrentProjectName());
    context = new SessionContext(session);

    DiagramHelper.setPreferenceAutoRefresh(false);
    DiagramHelper.setPrefereneRefreshOnOpening(false);

    mapSemanticObjects = new HashMap<String, EObject>();

    _airdFile = FileHelper.getPlatformFile(getCurrentProjectName() + SLASH_CHARACTER + FragmentationMessages.airdName);
    _m2File = FileHelper.getPlatformFile(getCurrentProjectName() + SLASH_CHARACTER + FragmentationMessages.m2Name);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getCurrentProjectName());
  }

  public void fragmentWithRefChecks(SessionContext context, String id) {
    new FragmentTest(context, getEObject(id)).run();
  }

  public void fragment(SessionContext context, String id) {
    new FragmentTest(context, getEObject(id)) {
      @Override
      protected void postFragmentChecks(EObject objectToFragment, int numberOfProperChildren,
          Map<EObject, Integer> eObjectRefCount) {
      }
    }.run();
  }

  public void fragmentNonAbusive(SessionContext context, String id) {
    new NonAbusiveFragmentTest(context, getEObject(id));
  }

  public void unfragmentWithRefChecks(SessionContext context, String id) {
    new UnfragmentTest(context, getEObject(id)).run();
  }

  public void unfragment(SessionContext context, String id) {
    new UnfragmentTest(context, getEObject(id)) {
      @Override
      protected void postUnfragmentChecks(EObject objectToUnfragment, int numberOfProperChildren,
          Map<EObject, Integer> eObjectRefCount) {
      }
    }.run();
  }

  protected EObject getEObject(String id) {
    EObject eObject = context.getSemanticElement(id);
    if (eObject == null) {
      eObject = mapSemanticObjects.get(id);
    }
    assertFalse(eObject == null);
    return eObject;
  }

  public DiagramContext openDiagram(SessionContext context, String diagramName, Set<IFile> files) {
    return new OpenDiagramStep(context, diagramName) {
      @Override
      protected void postRunTest() {
        // set the files in ReadOnly mode
        try {
          FragmentUtils.setIFileListReadOnly(files);
        } catch (CoreException e) {
          fail(e.getMessage());
        }
        super.postRunTest();
      }
    }.run();
  }

  public DiagramContext createDiagram(SessionContext context, String targetId, String diagramName,
      Set<IFile> files) {
    return new CreateDiagramStep(context, targetId, diagramName) {
      @Override
      protected void postRunTest() {
        // set the files in ReadOnly mode
        try {
          FragmentUtils.setIFileListReadOnly(files);
        } catch (CoreException e) {
          fail(e.getMessage());
        }
        super.postRunTest();
      }
    }.run();
  }

  public class FragmentCreateContainerTool extends CreateContainerTool {
    public FragmentCreateContainerTool(DiagramContext context, String toolName, String containerView) {
      super(context, toolName, containerView);
    }

    @Override
    protected void initToolArguments() {
      DSemanticDecorator parentContainerView = getContainerView();
      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER, parentContainerView.getTarget());
      _toolWrapper.setArgumentValue(ArgumentType.CONTAINER_VIEW, parentContainerView);
    }

    @Override
    protected DSemanticDecorator getContainerView() {
      return getView(containerView);
    }

    protected DSemanticDecorator getView(String id) {
      return getDiagramContext().getView(mapSemanticObjects.get(id));
    }
  }

  public class FragmentCreateDEdgeTool extends CreateDEdgeTool {
    public FragmentCreateDEdgeTool(DiagramContext context, String toolName, String idSource,
        String idTarget) {
      super(context, toolName, idSource, idTarget);
    }

    @Override
    protected void initToolArguments() {
      DSemanticDecorator edgeSourceView = getDiagramContext().getView(_sourceView);
      if (edgeSourceView == null)
        edgeSourceView = getView(_sourceView);
      _toolWrapper.setArgumentValue(ArgumentType.SOURCE, edgeSourceView);

      DSemanticDecorator edgeTargetView = getDiagramContext().getView(_targetView);
      if (edgeTargetView == null)
        edgeTargetView = getView(_targetView);
      _toolWrapper.setArgumentValue(ArgumentType.TARGET, edgeTargetView);
    }

    protected DSemanticDecorator getView(String id) {
      return getDiagramContext().getView(mapSemanticObjects.get(id));
    }
  }

  /**
   * Tests the model integrity of the current session
   */
  protected void checkModelIntegrity() {

    new AbstractTestStep(context) {

      @Override
      public Object getResult() {
        return null;
      }

      @Override
      protected void runTest() {
        assertNotNull(FragmentationMessages.nullSession, context);
        IntegrityChecker checker = new IntegrityChecker();
        IStatus status = checker.getStatus(session, null, new NullProgressMonitor());
        assertTrue(MessageFormat.format(FragmentationMessages.duplicatedModelElements, status.toString()),
            status.isOK());
      }
    }.run();
  }

  /**
   * Tests if the number of loaded fragments of the input session is the same than the expected one
   */
  protected void checkLoadedFragmentsNumber(final int expectedFragmentsNumber_p) {

    new AbstractTestStep(context) {

      @Override
      public Object getResult() {
        return null;
      }

      @Override
      protected void runTest() {
        assertNotNull(FragmentationMessages.nullSession, context);
        Set<Resource> allSessionResources = session.getAllSessionResources();
        int loadedFragmentsNb = allSessionResources.size() - 1; // all
        assertTrue(MessageFormat.format(FragmentationMessages.wrongFragmentsNumber, context.toString(),
            loadedFragmentsNb, expectedFragmentsNumber_p), loadedFragmentsNb == expectedFragmentsNumber_p);
      }
    }.run();
  }

  /**
   * Tests if the number of tables and diagrams of the input fragment is the same than expected
   * 
   * @param diagram_p
   * @param tests_p
   */
  protected void checkFragmentTablesDiagramsNumber(final String fragmentName_p, final int expectedNumber_p) {

    new AbstractTestStep(context) {

      @Override
      protected void runTest() {
        String airdFragmentPath = getCurrentProjectName() + "/fragments/" + fragmentName_p;

        IFile airdFragmentFile = FileHelper.getPlatformFile(airdFragmentPath);
        assertNotNull(MessageFormat.format(FragmentationMessages.errorInGetFile, airdFragmentPath), airdFragmentFile);
        Session fragmentSession = TestHelper.openOrGetSession(airdFragmentFile);
        assertNotNull(FragmentationMessages.nullSession, fragmentSession);

        // get the AirdResource related to the fragment session
        AirdResource fragmentAirdResource = getSessionResource(fragmentSession, airdFragmentFile);

        // get the DAnalysis related to the AirdResource
        DAnalysis fragmentAnalysis = (DAnalysis) fragmentAirdResource.getContents().get(0);

        int allRepresentationsNumber = 0;
        // for each view of the DAnalysis, get the number of Representations
        List<DView> ownedViews = fragmentAnalysis.getOwnedViews();
        for (DView currentView : ownedViews) {
          int viewRepresentationsNumber = new DViewQuery(currentView).getLoadedRepresentations().size();
          allRepresentationsNumber += viewRepresentationsNumber;
        }

        assertTrue(MessageFormat.format(FragmentationMessages.wrongTablesDiagramsNumber, fragmentName_p,
            allRepresentationsNumber, expectedNumber_p), allRepresentationsNumber == expectedNumber_p);
      }

      @Override
      public Object getResult() {
        // TODO Auto-generated method stub
        return null;
      }

    }.run();
  }

  /**
   * Check the number of model elements in semantic model elements for the input fragment
   */
  protected void checkFragmentModelElementsNumber(final String fragmentName_p, final int expectedNumber_p) {

    new AbstractTestStep(context) {
      @Override
      protected void runTest() {
        String airdFragmentPath = airdFragmentPath = getCurrentProjectName() + "/fragments/" + fragmentName_p;

        IFile airdFragmentFile = FileHelper.getPlatformFile(airdFragmentPath);
        assertNotNull(MessageFormat.format(FragmentationMessages.errorInGetFile, airdFragmentPath), airdFragmentFile);
        Session fragmentSession = TestHelper.openOrGetSession(airdFragmentFile);
        assertNotNull(FragmentationMessages.nullSession, fragmentSession);

        Collection<Resource> semanticResources = fragmentSession.getTransactionalEditingDomain().getResourceSet()
            .getResources();
        // fragmentSession.getSemanticResources();
        CapellamodellerResourceImpl m2Resource = null;
        String fragmentURIName = fragmentName_p.replace(" ", "%20");
        fragmentURIName = fragmentURIName.replace("[", "%5B");
        fragmentURIName = fragmentURIName.replace("]", "%5D");
        fragmentURIName = fragmentURIName.replace(".airdfragment", "");
        for (Resource current : semanticResources) {
          URI uri = current.getURI();
          if (uri.toString().contains("SYSOA2")) {
            System.out.println("aaa");
          }
          if (uri.toString().contains(fragmentURIName + ".melody")) {
            m2Resource = (CapellamodellerResourceImpl) current;
            break;
          }
        }
        assertNotNull("The expected CapellaModeller file was not found in the semantic resources.", m2Resource);
        Map<EObject, String> eObjectToIDMap = m2Resource.getEObjectToIDMap();
        // number of elements in semantic model
        int elementsNb = eObjectToIDMap.size();
        assertTrue(MessageFormat.format(FragmentationMessages.wrongModelElementsNumber, fragmentName_p, elementsNb,
            expectedNumber_p), elementsNb == expectedNumber_p);
      }

      @Override
      public Object getResult() {
        // TODO Auto-generated method stub
        return null;
      }
    }.run();
  }

  /**
   * retrieve the number of elements for each diagram of the current session in a map
   */
  protected void retrieveDiagramElementsNumber() {

    new AbstractTestStep(context) {

      @Override
      protected void runTest() {
        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE
            .getAllRepresentations(session);
        for (DRepresentation currentRepresentation : allRepresentations) {
          // table case: put the numbers of columns and lines in a map
          if (currentRepresentation instanceof DTable) {
            DTable currentTable = (DTable) currentRepresentation;
            // put columns and lines numbers in a dimension
            Dimension tableSize = new Dimension(currentTable.getColumns().size(), currentTable.getLines().size());
            // concat diagram name and its id to get an unique
            // identifier
            String key = currentTable.getUid() + ":" + (String) (currentTable.getTarget()).eGet(idFeature);
            _sessionTablesMap.put(key, tableSize);
          }

          // diagram case: put the number of diagram elements in a map
          if (currentRepresentation instanceof DSemanticDiagram) {
            DSemanticDiagram currentDiagram = (DSemanticDiagram) currentRepresentation;
            // concat diagram name and its id to get an unique
            // identifier
            String key = currentDiagram.getUid() + ":" + (String) (currentDiagram.getTarget()).eGet(idFeature);
            Integer number = currentDiagram.getDiagramElements().size();
            _sessionDiagramsMap.put(key, number);
          }
        }
      }

      @Override
      public Object getResult() {
        return null;
      }

    }.run();
  }

  /**
   * open all diagrams/tables and check if they are not modified
   */
  protected void openAndCheckAllSessionRepresentations() {

    new AbstractTestStep(context) {

      @Override
      protected void runTest() {
        // check on diagrams opening in dirty
        // get the aird resources
        List<Resource> resources = new ArrayList<Resource>();
        resources.add(session.getSessionResource());
        resources.addAll(session.getReferencedSessionResources());

        // check the status
        for (Resource currentResource : resources) {
          ResourceStatus resourceStatus = ResourceSetSync.getStatus(currentResource);

          assertTrue(MessageFormat.format(FragmentationMessages.resourceStatusNotSync, currentResource.toString(),
              resourceStatus), (resourceStatus == ResourceStatus.SYNC));
        }

        // check any changes in diagrams and tables
        List<DRepresentation> allRepresentations = (List<DRepresentation>) DialectManager.INSTANCE
            .getAllRepresentations(session);

        for (DRepresentation currentRepresentation : allRepresentations) {

          // table case: compare the numbers of lines and columns with
          // the data in the map
          if (currentRepresentation instanceof DTable) {
            DTable currentTable = (DTable) currentRepresentation;
            String key = currentTable.getUid() + ":" + (String) (currentTable.getTarget()).eGet(idFeature);
            Dimension expectedDimension = _sessionTablesMap.get(key);
            // check the number of columns
            assertTrue(
                MessageFormat.format(FragmentationMessages.wrongNumberOfColumns, currentRepresentation.toString(),
                    currentTable.getColumns().size(), expectedDimension.width),
                currentTable.getColumns().size() == expectedDimension.width);
            // check the number of lines
            assertTrue(
                MessageFormat.format(FragmentationMessages.wrongNumberOfLines, currentRepresentation.toString(),
                    currentTable.getLines().size(), expectedDimension.height),
                currentTable.getLines().size() == expectedDimension.height);
          }

          // diagram case: compare the number of diagram elements with
          // the data in the map
          if (currentRepresentation instanceof DSemanticDiagram) {
            DSemanticDiagram currentDiagram = (DSemanticDiagram) currentRepresentation;
            Integer currentDiagramElementsNumber = currentDiagram.getDiagramElements().size();

            String key = currentDiagram.getUid() + ":" + (String) (currentDiagram.getTarget()).eGet(idFeature);
            Integer expectedNumber = _sessionDiagramsMap.get(key);
            assertTrue(
                MessageFormat.format(FragmentationMessages.wrongNumberOfDiagramElements,
                    currentRepresentation.toString(), currentDiagramElementsNumber, expectedNumber),
                currentDiagramElementsNumber.equals(expectedNumber));
          }
        }
      }

      @Override
      public Object getResult() {
        // TODO Auto-generated method stub
        return null;
      }
    }.run();
  }

  private AirdResource getSessionResource(Session session_p, IFile file_p) {
    String fileFullPath = file_p.getFullPath().toOSString();
    URI uri = URI.createPlatformResourceURI(fileFullPath, true);

    for (Resource res : session_p.getAllSessionResources()) {
      if (res.getURI().equals(uri)) {
        return (AirdResource) res;
      }
    }
    fail("There is no resource for the session");
    return null;
  }

  protected void saveAndCloseSession() {
    new AbstractTestStep(context) {
      @Override
      public Object getResult() {
        return null;
      }

      @Override
      protected void runTest() {
        GuiActions.closeSession(session, true);
      }
    }.run();
  }

  protected void openFragmentSession(IFile file) {
    new AbstractTestStep(context) {
      @Override
      public Object getResult() {
        return null;
      }

      @Override
      protected void runTest() {
        session = TestHelper.openOrGetSession(file);
        session.open(new NullProgressMonitor());
        assertNotNull(session);
      }
    }.run();
  }
}
