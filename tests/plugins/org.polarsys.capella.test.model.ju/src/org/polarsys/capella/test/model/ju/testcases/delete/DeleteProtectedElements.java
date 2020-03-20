/*******************************************************************************
* Copyright (c) 2019 THALES GLOBAL SERVICES.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*    Thales - initial API and implementation
*******************************************************************************/
package org.polarsys.capella.test.model.ju.testcases.delete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.model.preferences.IDeletePreferences;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaDeleteAction;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.model.ju.model.MiscModel;

public class DeleteProtectedElements extends MiscModel {

  protected ScopedPreferenceStore preferenceStore;
  protected List<String> protectedElementsIds;
  protected SessionContext context;

  protected static final String SHOULD_BE_DELETED = "The element {0} {1} should be able to be deleted, but is not";
  protected static final String SHOULD_NOT_BE_DELETED = "The element {0} {1} should NOT be able to be deleted, but is is";

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    Session session = getSession(getRequiredTestModels().get(0));
    context = new SessionContext(session);

    preferenceStore = (ScopedPreferenceStore) Activator.getDefault().getPreferenceStore();

    protectedElementsIds = new ArrayList<>();
    protectedElementsIds.addAll(getProjects());
    protectedElementsIds.addAll(getSystemEngineering());
    protectedElementsIds.addAll(getBlockArchitectures());
    protectedElementsIds.addAll(getRootComponents());
    protectedElementsIds.addAll(getRootParts());
    protectedElementsIds.addAll(getRootFunctions());
  }

  @Override
  public void test() {

    Collection<String> errors = new ArrayList<>();

    enableTheDeletionOfProtectedElements();

    for (String id : protectedElementsIds) {
      NamedElement element = context.getSemanticElement(id);
      boolean canDelete = CapellaDeleteAction.canDelete(Arrays.asList(element));
      if (!canDelete) {
        errors.add(NLS.bind(SHOULD_BE_DELETED, element.getName(), element.getId()));
      }
    }

    disableTheDeletionOfProtectedElements();

    for (String id : protectedElementsIds) {
      NamedElement element = context.getSemanticElement(id);
      boolean canDelete = CapellaDeleteAction.canDelete(Arrays.asList(element));
      if (canDelete) {
        errors.add(NLS.bind(SHOULD_NOT_BE_DELETED, element.getName(), element.getId()));
      }
    }

    assertTrue(errors.stream().collect(Collectors.joining("\n")), errors.isEmpty());

  }

  private void disableTheDeletionOfProtectedElements() {
    preferenceStore.putValue(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS, String.valueOf(true));
  }

  private void enableTheDeletionOfProtectedElements() {
    preferenceStore.putValue(IDeletePreferences.PREFERENCE_DELETE_PROTECTED_ELEMENTS, String.valueOf(false));
  }

  protected List<String> getProjects() {
    return Arrays.asList(PROJECT_MISCMODEL);
  }

  protected List<String> getSystemEngineering() {
    return Arrays.asList(MISCMODEL);
  }

  protected List<String> getBlockArchitectures() {
    return Arrays.asList(OA, SA, LA, PA, EPBS);
  }

  protected List<String> getRootComponents() {
    return Arrays.asList(SA__SYSTEM, LA__LOGICAL_SYSTEM, PA__PHYSICAL_SYSTEM, EPBS__SYSTEMCI_SYSTEM);
  }

  protected List<String> getRootParts() {
    return Arrays.asList(SA__SYSTEM_CONTEXT__PART_SYSTEM__SYSTEM,
        LA__LOGICAL_CONTEXT__PART_LOGICAL_SYSTEM__LOGICAL_SYSTEM,
        PA__PHYSICAL_CONTEXT__PART_PHYSICAL_SYSTEM__PHYSICAL_SYSTEM, EPBS__EPBS_CONTEXT__PART_SYSTEM__SYSTEM);
  }

  protected List<String> getRootFunctions() {
    return Arrays.asList(OA__OPERATIONAL_ACTIVITIES__ROOT_OA, SA__ROOT_SF, LA__ROOT_LF, PA__ROOT_PF);
  }

}
