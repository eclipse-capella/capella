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

package org.polarsys.capella.core.model.utils;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
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
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 */
public class NamingHelper {

  private NamingHelper() {
    // To hide the implicit public one.
  }

  /**
   * get the default title for the selection wizard
   * 
   * @param modelElement
   * @return the default title
   */
  public static String getDefaultTitle(EObject modelElement) {
    String name = null;
    String title = getTitleLabel(modelElement);
    if (modelElement instanceof AbstractNamedElement) {
      name = ((AbstractNamedElement) modelElement).getName();
    } else {
      name = EObjectLabelProviderHelper.getText(modelElement);
    }
    if (title.length() > 0 && name != null) {
      title = title + " ";
    }
    title = title + (name == null ? ICommonConstants.EMPTY_STRING : name);
    return title;
  }

  /**
   * 
   * @param modelElement
   * @return
   */
  public static String getTitleLabel(EObject modelElement) {
    StringBuilder builder = new StringBuilder();
    String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(modelElement, true);
    if (null != metaclassLabel) {
      builder.append(metaclassLabel);
    }
    if (modelElement instanceof Component && ComponentExt.isActor((Component) modelElement)) {
      builder.append("[Actor]");
    }
    if (modelElement instanceof PhysicalComponent) {
      String nature = ((PhysicalComponent) modelElement).getNature().getName();
      builder.append("[" + capitalize(nature) + "]");
    }

    return builder.toString();
  }

  /**
   * 
   * @param resource
   *          the resource
   * @return the text for the given resource
   */
  public static String getTextForResource(IResource resource) {
    IContainer parent = resource.getParent();
    if (parent != null && parent.getType() != IResource.ROOT) {
      return resource.getName() + " - " + parent.getFullPath();

    }
    return resource.getName();
  }

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

  /**
   * get the default message for the selection wizard
   * 
   * @param currentObject
   * @return
   */
  public static String getDefaultMessage(EObject currentObject, String editedPropertyName) {
    String message = "Select " + editedPropertyName; //$NON-NLS-1$
    if (currentObject instanceof AbstractNamedElement) {
      String name = ((AbstractNamedElement) currentObject).getName();
      message = message + " of " + currentObject.eClass().getName() + " \"" //$NON-NLS-1$//$NON-NLS-2$
          + (name == null ? ICommonConstants.EMPTY_STRING : name) + "\"."; //$NON-NLS-1$
    }
    return message;
  }

  public static String toString(Collection<NamedElement> elements) {
    Assert.isLegal(elements != null);

    StringBuilder builder = new StringBuilder();
    Iterator<NamedElement> iterator = elements.iterator();
    while (iterator.hasNext()) {
      builder.append(iterator.next().getName());
      if (iterator.hasNext()) {
        builder.append(", ");
      }
    }
    return builder.toString();
  }

  /**
   * Make the given String starts with capital letter.
   * 
   * @param str
   * @return
   */
  public static String capitalize(String str) {
    if (str.length() == 0)
      return str;
    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
  }

  /**
   * Change the name of the given element to the given value
   */
  public static void synchronizeName(final AbstractNamedElement element, final String value) {
    if ((element != null) && !StringUtils.equals(element.getName(), value)) {
      element.setName(value);
    }
  }

  /**
   * get the element name , element could be diagram or model
   */
  public static String getElementName(EObject object) {
    String result = null;
    if (null != object) {
      result = CapellaElementExt.getName(object);
      if (object instanceof DRepresentation) {
        DRepresentation res = (DRepresentation) object;
        object = RepresentationHelper.getRepresentationDescriptor(res);
      }
      if (object instanceof DRepresentationDescriptor) {
        result = ((DRepresentationDescriptor) object).getName();
      }
    }
    return result;
  }
}
