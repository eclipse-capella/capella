/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.ui.sirius;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.fa.FunctionalChainReference;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.preferences.Activator;

/**
 * This util class is used to determine if navigation shall be made on double click, based on the preference from @CapellaDiagramPreferencePage
 * It also provides a method to get RepresentationDescriptors 
 * 
 * @author etraisnel
 */
public class DoubleClickBehaviourUtil {

  public static DoubleClickBehaviourUtil INSTANCE = new DoubleClickBehaviourUtil();

  private DoubleClickBehaviourUtil() {

  }

  public boolean shouldOpenRelatedDiagramsOnDoubleClick(EObject source) {
    boolean navigateOnDoubleClick = Activator.getDefault().getPreferenceStore().getBoolean(Messages.NamePrefDisplayNavigateOnDoubleClick);
    return navigateOnDoubleClick && 
        (source instanceof FunctionalChain
            || source instanceof FunctionalChainReference
            || source instanceof PhysicalPath
            || source instanceof PhysicalPathReference
            || source instanceof InteractionUse
            || source instanceof Scenario);		
  }

  public Collection<DRepresentationDescriptor> getRepresentationsDescriptors(EObject target){				
    if(target instanceof FunctionalChainReference) {
      target = ((FunctionalChainReference) target).getReferencedFunctionalChain();
    } else if(target instanceof PhysicalPathReference) {
      target = ((PhysicalPathReference) target).getReferencedPhysicalPath();	
    } else if(target instanceof InteractionUse) {
      target = ((InteractionUse) target).getReferencedScenario();
    }

    return RepresentationHelper.getAllRepresentationDescriptorsTargetedBy(Collections.singleton(target));
  }

}
