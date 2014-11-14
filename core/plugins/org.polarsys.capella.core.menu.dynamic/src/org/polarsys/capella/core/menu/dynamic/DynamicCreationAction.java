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
package org.polarsys.capella.core.menu.dynamic;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;

import org.polarsys.capella.common.helpers.EObjectCouple;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.kitalpha.emde.model.Element;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.menu.dynamic.DynamicCreateChildAction;
import org.polarsys.capella.common.menu.dynamic.utils.ContributionItemComparator;

/**
 * Dynamic Creation action.
 */
public class DynamicCreationAction extends DynamicModelElementAction {
  /**
   * Contribution item comparator.
   */
  private ContributionItemComparator _contributionItemComparator;

  /**
   * Constructor.
   * @param shell_p
   * @param selectionProvider_p
   */
  public DynamicCreationAction(Shell shell_p, ISelectionProvider selectionProvider_p) {
    super(shell_p, selectionProvider_p);
    _contributionItemComparator = new ContributionItemComparator();
  }

  /**
   * Because of the meta-model extension mechanism some child descriptors are duplicated.<br>
   * This method will filter the duplicated descriptors (descriptors applying to the same Feature and the same value's EClass).
   * @param editingDomain_p the editing domain
   * @param modelElement_p the contextual element
   * @return a filtered collection of new child descriptors
   */
  @SuppressWarnings("unchecked")
  protected Collection<CommandParameter> getFilteredNewChildDescriptors(EditingDomain editingDomain_p, Element modelElement_p) {
    Map<EObjectCouple, CommandParameter> filteredNewChildDescriptors = new HashMap<EObjectCouple, CommandParameter>();

    Collection<CommandParameter> newChildDescriptors = (Collection<CommandParameter>) editingDomain_p.getNewChildDescriptors(modelElement_p, null);
    ISelection selection = new StructuredSelection(modelElement_p);

    for (CommandParameter cmd : newChildDescriptors) {
      EObjectCouple key = new EObjectCouple(cmd.getEReference(), (cmd.getEValue()).eClass());
      filteredNewChildDescriptors.put(key, cmd);
      cmd.setOwner(selection);
    }

    if (modelElement_p instanceof Part) {
      EObject element = ((Part) modelElement_p).getOwnedAbstractType();
      if (element != null) {
        selection = new StructuredSelection(element);
        newChildDescriptors = (Collection<CommandParameter>) editingDomain_p.getNewChildDescriptors(element, null);

        for (CommandParameter cmd : newChildDescriptors) {
          // descriptors with the same Feature and the same value's EClass will be filtered here
          EObjectCouple key = new EObjectCouple(cmd.getEReference(), (cmd.getEValue()).eClass());
          filteredNewChildDescriptors.put(key, cmd);
          cmd.setOwner(selection);
        }

      }
    }

    return filteredNewChildDescriptors.values();
  }

  /**
   * Get the dynamic actions.
   * @return
   */
  @Override
  public Collection<IContributionItem> getStructuralDynamicActions() {
    EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    Element modelElement = getModelElement();
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        return getValue() instanceof Structure;
      }
    });
  }

  /**
   * @see org.polarsys.capella.core.menu.dynamic.DynamicModelElementAction#getNonStructuralDynamicActions()
   */
  @Override
  public Collection<IContributionItem> getPropertyValueDynamicActions() {
    EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    Element modelElement = getModelElement();
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        Object value = getValue();
        return (value instanceof AbstractPropertyValue) || (value instanceof EnumerationPropertyType) || (value instanceof PropertyValueGroup);
      }
    });
  }

  /**
   * @see org.polarsys.capella.core.menu.dynamic.DynamicModelElementAction#getNonStructuralDynamicActions()
   */
  @Override
  public Collection<IContributionItem> getExtensionDynamicActions() {
    EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    Element modelElement = getModelElement();
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        return getValue() instanceof ElementExtension;
      }
    });
  }

  /**
   * @see org.polarsys.capella.core.menu.dynamic.DynamicModelElementAction#getNonStructuralDynamicActions()
   */
  @Override
  public Collection<IContributionItem> getNonStructuralDynamicActions() {
    EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    Element modelElement = getModelElement();
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        Object value = getValue();
        return (!(value instanceof Relationship) || (value instanceof CommunicationMean) || (value instanceof FunctionalChainInvolvement))
               && !(value instanceof Structure) && !(value instanceof AbstractPropertyValue) && !(value instanceof EnumerationPropertyType)
               && !(value instanceof PropertyValueGroup) && !(value instanceof ElementExtension);
      }
    });
  }

  /**
   * Generate create child actions.
   * @param descriptors_p
   * @param selection_p
   * @param editingDomain_p
   * @param condition_p
   * @return
   */
  protected Collection<IContributionItem> generateCreateChildActions(Collection<CommandParameter> descriptors_p, EditingDomain editingDomain_p,
      AbstractCondition condition_p) {
    TreeSet<IContributionItem> contributionItems = new TreeSet<IContributionItem>(_contributionItemComparator);
    if (null != descriptors_p) {
      // Map to store descriptors by type.
      Map<EClass, Set<CommandParameter>> descriptorsSortedByType = new HashMap<EClass, Set<CommandParameter>>();
      // Map to store descriptors by feature.
      Map<EReference, Set<CommandParameter>> descriptorsSortedByFeature = new HashMap<EReference, Set<CommandParameter>>();
      // Set of features displayed as sub menu managers.
      Set<EReference> featuresDisplayedAsSubMenuManager = new HashSet<EReference>(0);
      for (CommandParameter descriptor : descriptors_p) {
        // Handle feature
        EReference feature = descriptor.getEReference();
        Set<CommandParameter> featureDescriptors = descriptorsSortedByFeature.get(feature);
        if (null == featureDescriptors) {
          featureDescriptors = new HashSet<CommandParameter>(1);
          descriptorsSortedByFeature.put(feature, featureDescriptors);
        }
        // Add the current descriptor for this feature.
        featureDescriptors.add(descriptor);

        // Handle eClass
        EClass eClass = descriptor.getEValue().eClass();
        Set<CommandParameter> typeDescriptors = descriptorsSortedByType.get(eClass);
        if (null == typeDescriptors) {
          typeDescriptors = new HashSet<CommandParameter>(1);
          descriptorsSortedByType.put(eClass, typeDescriptors);
        }
        // Add the current descriptor for this type.
        typeDescriptors.add(descriptor);

        // More than one descriptors for this type, mark underlying feature to
        // display it as sub menu managers.
        if ((typeDescriptors.size() > 1) && (featureDescriptors.size() > 1)) {
          featuresDisplayedAsSubMenuManager.add(feature);
        }
      }

      // Iterate again over all descriptors to fill in the resulting list
      // according to features that should be displayed as sub menu manager.
      for (CommandParameter descriptor : descriptors_p) {
        EReference reference = descriptor.getEReference();
        if (featuresDisplayedAsSubMenuManager.contains(reference)) {
          // fill in as a sub menu manager.
          Set<CommandParameter> featureDescriptors = descriptorsSortedByFeature.get(reference);
          // Create a menu manager for this feature.
          MenuManager featureMenuManager = new MenuManager(getFeatureLabel(reference, descriptor.getEValue()));
          // Add it in the resulting list.
          contributionItems.add(featureMenuManager);
          // Create the list of available action items.
          TreeSet<IContributionItem> actionItems = new TreeSet<IContributionItem>(_contributionItemComparator);
          for (CommandParameter featureDescriptor : featureDescriptors) {
            fillContributionItems(editingDomain_p, condition_p, actionItems, featureDescriptor);
          }
          // Add them in feature menu manager.
          for (IContributionItem dynamicItem : actionItems) {
            featureMenuManager.add(dynamicItem);
          }
        } else {
          // Fill in resulting list with a standalone descriptor.
          fillContributionItems(editingDomain_p, condition_p, contributionItems, descriptor);
        }
      }
    }
    return contributionItems;
  }

  /**
   * Fill contribution items.
   * @param selection_p
   * @param editingDomain_p
   * @param condition_p
   * @param items_p
   * @param descriptor_p
   */
  protected void fillContributionItems(EditingDomain editingDomain_p, AbstractCondition condition_p, TreeSet<IContributionItem> items_p,
      CommandParameter descriptor_p) {
    // Set the condition value.
    condition_p.setValue(descriptor_p.getValue());
    // Create a "create child action" if condition is fulfilled.
    if (condition_p.isValid()) {
      CapellaCreateChildAction action = new CapellaCreateChildAction(editingDomain_p, (ISelection) descriptor_p.getOwner(), descriptor_p);
      if (action.isExecutable() && action.isEnabled()) {
        // Add it if enable and executable.
        items_p.add(new DynamicActionContributionItem(action));
      }
    }
  }

  /**
   * Get feature UI label.
   * @param feature_p
   * @param object_p
   * @return
   */
  protected String getFeatureLabel(EStructuralFeature feature_p, EObject object_p) {
    ItemProviderAdapter genericItemProvider = getItemProvider(object_p);
    String featureLabel = EObjectLabelProviderHelper.getFeatureLabel(feature_p, genericItemProvider);
    genericItemProvider.dispose();
    return featureLabel;
  }

  /**
   * Get feature UI label.
   * @param class_p
   * @param object_p
   * @return
   */
  protected String getMetaclassLabel(EClass class_p, EObject object_p) {
    ItemProviderAdapter genericItemProvider = getItemProvider(object_p);
    String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(class_p, genericItemProvider);
    genericItemProvider.dispose();
    return metaclassLabel;
  }

  /**
   * Get a generic item provider.
   * @return
   */
  protected ItemProviderAdapter getItemProvider(EObject object_p) {
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) MDEAdapterFactory.getEditingDomain();
    IItemLabelProvider provider = (IItemLabelProvider) editingDomain.getAdapterFactory().adapt(object_p, IItemLabelProvider.class);
    return (ItemProviderAdapter) provider;
  }

  /**
   * Extends {@link DynamicCreateChildAction} to override the setText.
   */
  class CapellaCreateChildAction extends DynamicCreateChildAction {
    /**
     * @param editingDomain_p
     * @param selection_p
     * @param descriptor_p
     */
    public CapellaCreateChildAction(EditingDomain editingDomain_p, ISelection selection_p, Object descriptor_p) {
      super(editingDomain_p, selection_p, descriptor_p);
    }

    /**
     * @see org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction#configureAction(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void configureAction(ISelection selection__p) {
      super.configureAction(selection__p);

      EObject object = ((CommandParameter) descriptor).getEValue();
      EClass eClass = object.eClass();
      setText(getMetaclassLabel(eClass, object));

      if (getImageDescriptor() == null) {
        ImageDescriptor imageDescriptor = EObjectLabelProviderHelper.getImageDescriptor(object);
        if (null != imageDescriptor) {
          setImageDescriptor(imageDescriptor);
        } else {
          Object selection = ((StructuredSelection) selection__p).getFirstElement();
          EStructuralFeature feature = ((CommandParameter) descriptor).getEStructuralFeature();
          ItemProviderAdapter itemProvider = getItemProvider(object);
          Object childImage = itemProvider.getCreateChildImage(selection, feature, object, null);
          setImageDescriptor(ImageDescriptor.createFromURL((URL) childImage));
          itemProvider.dispose();
        }
      }
    }
  }

  /**
   * Condition used to drive a behavior.
   */
  protected abstract class AbstractCondition {
    private Object _value;

    /**
     * Whether or not this condition is valid.
     * @return <code>true</code> condition is fulfilled.
     */
    public abstract boolean isValid();

    /**
     * Set value used to compute the validity of this condition.
     * @param value_p
     */
    public void setValue(Object value_p) {
      _value = value_p;
    }

    /**
     * Get the value used to compute the validity of this condition.
     * @return the value
     */
    protected Object getValue() {
      return _value;
    }
  }
}
