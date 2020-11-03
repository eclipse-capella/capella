/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.queries;

import static org.polarsys.capella.common.queries.QuerySchema.getQueryIdentifier;

import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetABInsertActor;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetABInsertActor__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetABInsertComponent;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetABInsertComponent__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCEIInsertInterfaceForLib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCEIShowHideActor__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCEIShowHideComponent__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCIIInsertComponent;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCIIShowHideActor;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCIIShowHideActor__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetCCIIShowHideComponent__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetIBShowHideActor;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetIBShowHideActor__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetIBShowHideComponent;
import org.polarsys.capella.core.sirius.analysis.queries.csServices.GetIBShowHideComponent__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.interactionServices.GetISScopeInsertActors;
import org.polarsys.capella.core.sirius.analysis.queries.interactionServices.GetISScopeInsertActors__Lib;
import org.polarsys.capella.core.sirius.analysis.queries.interactionServices.GetISScopeInsertComponents;
import org.polarsys.capella.core.sirius.analysis.queries.interactionServices.GetISScopeInsertComponents__Lib;

/**
 * 
 */
public interface QueryIdentifierConstants {
  String GET_CCE_INSERT_INTERFACE = getQueryIdentifier(GetCCEIInsertInterface.class);
  String GET_AVAILABLE_ARCHITECTURES = getQueryIdentifier(GetAvailableArchitectures.class);
  String GET_IB_SHOW_HIDE_COMPONENTS = getQueryIdentifier(GetIBShowHideComponent.class);
  String GET_IB_SHOW_HIDE_ACTORS = getQueryIdentifier(GetIBShowHideActor.class);
  String GET_CCII_Insert_Component = getQueryIdentifier(GetCCIIInsertComponent.class);
  String GET_IS_SCOPE_INSERT_ACTORS = getQueryIdentifier(GetISScopeInsertActors.class);
  String GET_IS_SCOPE_INSERT_COMPONENTS = getQueryIdentifier(GetISScopeInsertComponents.class);
  String GET_AB_INSERT_ACTOR = getQueryIdentifier(GetABInsertActor.class);
  String GET_AB_INSERT_COMPONENT = getQueryIdentifier(GetABInsertComponent.class);
  String GET_CCII_SHOW_HIDE_ACTORS = getQueryIdentifier(GetCCIIShowHideActor.class);
  
  String GET_CCE_INSERT_INTERFACE_FOR_LIB = getQueryIdentifier(GetCCEIInsertInterfaceForLib.class);
  String GET_CCII_SHOW_HIDE_COMPONENTS_FOR_LIB = getQueryIdentifier(GetCCIIShowHideComponent__Lib.class);
  String GET_CCII_SHOW_HIDE_ACTORS_FOR_LIB = getQueryIdentifier(GetCCIIShowHideActor__Lib.class);
  String GET_CCEI_SHOW_HIDE_COMPONENTS_FOR_LIB = getQueryIdentifier(GetCCEIShowHideComponent__Lib.class);
  String GET_CCEI_SHOW_HIDE_ACTORS_FOR_LIB = getQueryIdentifier(GetCCEIShowHideActor__Lib.class);
  String GET_IB_SHOW_HIDE_COMPONENTS_FOR_LIB = getQueryIdentifier(GetIBShowHideComponent__Lib.class);
  String GET_IB_SHOW_HIDE_ACTORS_FOR_LIB = getQueryIdentifier(GetIBShowHideActor__Lib.class);
  String GET_AB_INSERT_ACTOR_FOR_LIB = getQueryIdentifier(GetABInsertActor__Lib.class);
  String GET_AB_INSERT_COMPONENT_FOR_LIB = getQueryIdentifier(GetABInsertComponent__Lib.class);
  String GET_IS_SCOPE_INSERT_ACTORS_FOR_LIB = getQueryIdentifier(GetISScopeInsertActors__Lib.class);
  String GET_IS_SCOPE_INSERT_COMPONENTS_FOR_LIB = getQueryIdentifier(GetISScopeInsertComponents__Lib.class);

}
