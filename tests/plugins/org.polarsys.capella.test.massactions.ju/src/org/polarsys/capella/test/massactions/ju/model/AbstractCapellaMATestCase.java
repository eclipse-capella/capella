/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.massactions.ju.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.data.capellamodeller.Project;

import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Generic implementation of a Capella Mass Action test case.
 *
 * @author Sandu Postaru
 * 
 */
public abstract class AbstractCapellaMATestCase extends BasicTestCase {

  public static final String MODEL_NAME = "In-Flight Entertainment System";

  public static final String CAT_DISPLAYED_VOD_MOVIE_DATA = "f10533a0-4c99-4fa8-bf06-3fa9e6d6eafd";
  public static final String CAT_MOVIE_CONTENT = "f8464d6c-3085-463e-b624-225b8839209f";
  public static final String CAT_DISPLAYED_IMPOSED_VIDEO_DATA = "6ff020fc-4b56-45d1-8154-c24fe116bae1";
  public static final String CAT_MEDIA_CONTENT = "d3986bd2-066f-4be7-9218-25a47f3d8e40";

  public static final String SF_PERFORM_CABIN_MANAGEMENT_ACTIVITIES = "3398fdbf-3415-4d03-a457-477324d2e091";
  public static final String SF_ENTERTAIN_WITH_IFE_SYSTEM = "3688cf59-97ee-4446-8041-7941cee6c767";
  public static final String SF_IFE_SYSTEM = "6d10ccfa-1be8-4ab6-a0d8-74941051595b";

  public static final String PROP_SEAT = "7e013bf2-1999-4efe-95ed-1d1b0407bac3";
  public static final String PROP_NAME = "2b0362f6-7d62-4804-a024-bedb01a54407";

  public static final String PV_BOOLEAN = "975e8994-ee4e-44a8-87b9-d9fa6e9bff54";
  public static final String PV_STRING = "4233fe0d-a080-4864-a524-08b9d30697f2";
  public static final String PV_INTEGER = "e0d762f7-7114-46a9-9e3e-9450200f8f29";

  protected Resource modelResource;

  /**
   * Override this method to write the test code, we avoid overriding the {@link BasicTestCase#test()} method directly
   * since {@link AbstractCapellaMAReadWriteTestCase} introduces a custom behavior for the test method, and we want to
   * rest generic.
   **/
  public abstract void performTest() throws Exception;

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(MODEL_NAME);
  }

  protected Project getProject() {
    return (Project) getModelResource().getContents().iterator().next();
  }

  protected Resource getModelResource() {

    // lazy loading
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

  protected EObject getObject(String id) {
    return getModelResource().getEObject(id);
  }

  protected Collection<EObject> getObjects(String... ids) {
    return getObjects(Arrays.asList(ids));
  }

  protected Collection<EObject> getObjects(Collection<String> ids) {
    return ids.stream().map(id -> {
      EObject object = getObject(id);
      assertNotNull(object);
      return object;
    }).collect(Collectors.toList());
  }

  @Override
  public void test() throws Exception {
    performTest();
  }

  protected Optional<EStructuralFeature> getFeatureByName(EObject eObject, String name) {
    return eObject.eClass().getEAllStructuralFeatures().stream().filter(f -> f.getName().equals(name)).findFirst();
  }
}