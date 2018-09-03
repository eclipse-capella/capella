/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.migration.ju.testcases.basic;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.migration.ju.helpers.MigrationHelper;

/**
 * In 1.2.x (x>0) uid is used as the main reference for representations.
 * In 1.3.0 uid is used as the main reference for representations.
 * 
 * Make sure:
 * 
 * DRepresentation.Uid.1.3.0 == DRepresentationDescriptor.RepPath.1.3.0 = DRepresentation.Uid.1.2.x
 * @author S0070513
 *
 */
public class MigrateAirdFrom_12X_To_130_PreservingRepresentationUid_TestCase extends BasicTestCase {
  private IProject project;
  private static final String PROJECT_NAME = "121";
  private static final String AIRD_FILE_NAME = "121.aird";
  private Map<String, String> representationNameToUidIn12X = new HashMap<String, String>();

  @Override
  public void test() throws Exception {
    project = IResourceHelpers.getEclipseProjectInWorkspace(getRequiredTestModels().get(0));

    if (project.exists()) {

      keepTrackingOriginalUid();

      // Activate DOREMI refresh viewpoints
      DiagramHelper.setPreferenceAutoRefresh(true);
      DiagramHelper.setPrefereneRefreshOnOpening(true);

      MigrationHelper.migrateProject(project);

      verifyPreservingRepresentationUid();
    }
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(PROJECT_NAME);
  }

  private void keepTrackingOriginalUid() {
    Resource airdResource = getAirdResource();
    try {
      airdResource.load(null);
      TreeIterator<EObject> resourceContentsIterator = airdResource.getAllContents();
      while (resourceContentsIterator.hasNext()) {
        EObject element = (EObject) resourceContentsIterator.next();
        if (element instanceof DRepresentation) {
          DRepresentation representation = (DRepresentation) element;
          String name = representation.getName();
          String uid = representation.getUid();
          representationNameToUidIn12X.put(name, uid);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      airdResource.unload();
    }
  }

  private Resource getAirdResource() {
    ResourceSet resourceSet = new ResourceSetImpl();
    // Use XMIResourceFactoryImpl simply to avoid AirDResourceFactory,
    // which will cause the automatic migration
    // and make getUid() return xmi:id instead of real uid
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("aird", new XMIResourceFactoryImpl());
    IFile airdFile = project.getFile(AIRD_FILE_NAME);
    URI airdResourceUri = URI.createURI(airdFile.getLocationURI().toString());
    Resource airdResource = resourceSet.getResource(airdResourceUri, true);
    return airdResource;
  }

  private void verifyPreservingRepresentationUid() {
    Resource airdResource = getAirdResource();
    try {
      airdResource.load(null);
      TreeIterator<EObject> resourceContentsIterator = airdResource.getAllContents();
      while (resourceContentsIterator.hasNext()) {
        EObject element = (EObject) resourceContentsIterator.next();
        if (element instanceof DRepresentation) {
          DRepresentation representation = (DRepresentation) element;
          String name = representation.getName();
          String uid = representation.getUid();
          String originalUid = representationNameToUidIn12X.get(name);
          String message = "Uid of the representation " + name + " must be unchanged after the migration";

          assertEquals(message, originalUid, uid);

          for (AnnotationEntry annotationEntry : representation.getOwnedAnnotationEntries()) {
            if (CustomDataConstants.GMF_DIAGRAMS.equals(annotationEntry.getSource())) {
              Diagram diagram = (Diagram) annotationEntry.getData();
              
              assertNotNull("Custom data GMF_DIAGRAMS must not be NULL", diagram);
              
              assertTrue("Element of GMF diagram must be the representation",
                  representation.equals(diagram.getElement()));
            }
          }
        }
        
        if (element instanceof DRepresentationDescriptor) {
          DRepresentationDescriptor descriptor = (DRepresentationDescriptor) element;
          String name = descriptor.getName();
          
          String repPath;
          try {
            repPath = descriptor.getRepPath().getResourceURI().fragment();
          } catch (NullPointerException e) {
            repPath = "";
          }
          
          String uid = representationNameToUidIn12X.get(name);

          String message = "RepPath of the representation descriptor " + name
              + " must be as same as the representation's uid";
          
          assertEquals(message, repPath, uid);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      airdResource.unload();
    }
  }
}
