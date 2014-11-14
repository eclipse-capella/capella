/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.helpers.cs.services.CompositeStructureNamingHelper;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalAnalysisNamingHelper;
import org.polarsys.capella.core.data.helpers.information.services.DataValueNamingHelper;
import org.polarsys.capella.core.data.helpers.information.services.InformationNamingHelper;
import org.polarsys.capella.core.data.helpers.interaction.services.InteractionNamingHelper;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 */
public class NamingHelper {
  /**
   * @param element_p element whose value is requested
   * @param feature_p
   */
  public static String getValue(EObject element_p, EStructuralFeature feature_p) {
    if (element_p instanceof DataValue) {
      return DataValueNamingHelper.getValue((DataValue) element_p, feature_p);
    } else if (element_p instanceof Property) {
      return InformationNamingHelper.getValue((Property) element_p);
    } else if (element_p instanceof Operation) {
      return InformationNamingHelper.getValue((Operation) element_p);
    } else if (element_p instanceof SequenceMessage) {
      return InteractionNamingHelper.getValue((SequenceMessage) element_p);
    } else if (element_p instanceof FunctionalExchange) {
      return FunctionalAnalysisNamingHelper.getValue((FunctionalExchange) element_p);
    } else if (element_p instanceof ExchangeItemAllocation) {
      return CompositeStructureNamingHelper.getValue((ExchangeItemAllocation) element_p);
    } else if (element_p instanceof AbstractNamedElement && !(element_p instanceof ConfigurationItem)) {
      String name = ((AbstractNamedElement) element_p).getName();
      if (null == name || ICommonConstants.EMPTY_STRING.equals(name)) {
        return MessageFormat.format(Messages.getString("UnnamedValue"), element_p.eClass().getName()); //$NON-NLS-1$
      }
      return name;
    }

    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(element_p);
    if (null != editingDomain) {
      IItemLabelProvider labelProvider = (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(element_p, IItemLabelProvider.class);
      if (null != labelProvider) {
        return labelProvider.getText(element_p);
      }
    }

    return Messages.getString("UndefinedValue"); //$NON-NLS-1$
  }
}
