/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.utils;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cs.delegates.PartNamingHelper;
import org.polarsys.capella.core.data.helpers.cs.services.CompositeStructureNamingHelper;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalAnalysisNamingHelper;
import org.polarsys.capella.core.data.helpers.information.services.DataValueNamingHelper;
import org.polarsys.capella.core.data.helpers.information.services.InformationNamingHelper;
import org.polarsys.capella.core.data.helpers.interaction.services.InteractionNamingHelper;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

/**
 */
public class NamingHelper {
  /**
   * @param element
   *          element whose value is requested
   * @param feature
   */
  public static String getValue(EObject element, EStructuralFeature feature) {
    if (element instanceof DataValue) {
      return DataValueNamingHelper.getValue((DataValue) element, feature);
    } else if (element instanceof Part) {
      return PartNamingHelper.getValue((Part) element);
    } else if (element instanceof Property) {
      return InformationNamingHelper.getValue((Property) element);
    } else if (element instanceof Operation) {
      return InformationNamingHelper.getValue((Operation) element);
    } else if (element instanceof SequenceMessage) {
      return InteractionNamingHelper.getValue((SequenceMessage) element);
    } else if (element instanceof FunctionalExchange) {
      return FunctionalAnalysisNamingHelper.getValue((FunctionalExchange) element);
    } else if (element instanceof ExchangeItemAllocation) {
      return CompositeStructureNamingHelper.getValue((ExchangeItemAllocation) element);
    } else if (element instanceof AbstractNamedElement && !(element instanceof ConfigurationItem)) {
      String name = ((AbstractNamedElement) element).getName();
      if (null == name || ICommonConstants.EMPTY_STRING.equals(name)) {
        return MessageFormat.format(Messages.getString("UnnamedValue"), element.eClass().getName()); //$NON-NLS-1$
      }
      return name;
    }

    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain
        .getEditingDomainFor(element);
    if (null != editingDomain) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(element,
          IItemLabelProvider.class);
      if (null != labelProvider) {
        return labelProvider.getText(element);
      }
    }

    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
