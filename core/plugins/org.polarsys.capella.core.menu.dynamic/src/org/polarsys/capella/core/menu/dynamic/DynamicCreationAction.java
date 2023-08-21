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
package org.polarsys.capella.core.menu.dynamic;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.EObjectCouple;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.menu.dynamic.util.DynamicCommandParameter;
import org.polarsys.capella.common.ui.menu.dynamic.DynamicCreateChildAction;
import org.polarsys.capella.common.ui.menu.dynamic.utils.ContributionItemComparator;
import org.polarsys.capella.core.data.capellacommon.FinalState;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.model.helpers.move.MoveHelper;
import org.polarsys.kitalpha.emde.model.ElementExtension;

/**
 * Dynamic Creation action.
 */
public class DynamicCreationAction extends DynamicModelElementAction {
  /**
   * Contribution item comparator.
   */
  private ContributionItemComparator contributionItemComparator;

  /**
   * Constructor.
   * 
   * @param shell
   * @param selectionProvider
   */
  public DynamicCreationAction(Shell shell, ISelectionProvider selectionProvider) {
    super(shell, selectionProvider);
    contributionItemComparator = new ContributionItemComparator();
  }

  /**
   * Because of the meta-model extension mechanism some child descriptors are duplicated.<br>
   * This method will filter the duplicated descriptors (descriptors applying to the same Feature and the same value's
   * EClass).
   * 
   * @param editingDomain
   *          the editing domain
   * @param modelElement
   *          the contextual element
   * @return a filtered collection of new child descriptors
   */
  @SuppressWarnings("unchecked")
  protected Collection<CommandParameter> getFilteredNewChildDescriptors(EditingDomain editingDomain,
      EObject modelElement) {
    Map<EObjectCouple, CommandParameter> filteredNewChildDescriptors = new HashMap<>();
    Set<CommandParameter> dynamicDescriptors = new HashSet<>();

    Collection<CommandParameter> newChildDescriptors = (Collection<CommandParameter>) editingDomain
        .getNewChildDescriptors(modelElement, null);
    ISelection selection = new StructuredSelection(modelElement);

    for (CommandParameter cmd : newChildDescriptors) {
      if (cmd instanceof DynamicCommandParameter) {
        dynamicDescriptors.add((cmd));
      } else {
        EObjectCouple key = new EObjectCouple(cmd.getEReference(), (cmd.getEValue()).eClass());
        filteredNewChildDescriptors.put(key, cmd);
      }
      cmd.setOwner(selection);
    }

    if (modelElement instanceof Part) {
      EObject element = ((Part) modelElement).getOwnedAbstractType();
      if (element != null) {
        selection = new StructuredSelection(element);
        newChildDescriptors = (Collection<CommandParameter>) editingDomain.getNewChildDescriptors(element, null);

        for (CommandParameter cmd : newChildDescriptors) {
          // descriptors with the same Feature and the same value's EClass will be filtered here
          EObjectCouple key = new EObjectCouple(cmd.getEReference(), (cmd.getEValue()).eClass());
          filteredNewChildDescriptors.put(key, cmd);
          cmd.setOwner(selection);
        }

      }
    }

    dynamicDescriptors.addAll(filteredNewChildDescriptors.values());
    return dynamicDescriptors;
  }

  /**
   * Get the dynamic actions.
   * 
   * @return
   */
  @Override
  public Collection<IContributionItem> getStructuralDynamicActions() {
    EObject modelElement = getModelElement();
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(modelElement);
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        Object value = getValue();
        return isStructureRelated(value) && !isExtensionRelated(value);
      }
    });
  }

  /**
   * @see org.polarsys.capella.core.menu.dynamic.DynamicModelElementAction#getNonStructuralDynamicActions()
   */
  @Override
  public Collection<IContributionItem> getPropertyValueDynamicActions() {
    EObject modelElement = getModelElement();
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(modelElement);
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        Object value = getValue();
        return isPropertyRelated(value);
      }
    });
  }

  /**
   * @see org.polarsys.capella.core.menu.dynamic.DynamicModelElementAction#getNonStructuralDynamicActions()
   */
  @Override
  public Collection<IContributionItem> getExtensionDynamicActions() {
    EObject modelElement = getModelElement();
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(modelElement);
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        Object value = getValue();
        return isExtensionRelated(value) && !isRelationshipButNotCMOrFCIRelated(value);
      }
    });
  }

  /**
   * @see org.polarsys.capella.core.menu.dynamic.DynamicModelElementAction#getNonStructuralDynamicActions()
   */
  @Override
  public Collection<IContributionItem> getNonStructuralDynamicActions() {
    EObject modelElement = getModelElement();
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(modelElement);
    Collection<CommandParameter> newChildDescriptors = getFilteredNewChildDescriptors(editingDomain, modelElement);
    return generateCreateChildActions(newChildDescriptors, editingDomain, new AbstractCondition() {
      @Override
      public boolean isValid() {
        Object value = getValue();
        return !isRelationshipButNotCMOrFCIRelated(value) && !isStructureRelated(value) && !isPropertyRelated(value)
            && !isExtensionRelated(value);
      }
    });
  }
  
  private boolean isStructureRelated(Object value) {
    return value instanceof Structure;
  }
  
  private boolean isPropertyRelated(Object value) {
    return value instanceof AbstractPropertyValue || value instanceof EnumerationPropertyType
    || value instanceof PropertyValueGroup;
  }

  private boolean isExtensionRelated(Object value) {
    return value instanceof ElementExtension;
  }
  
  private boolean isRelationshipButNotCMOrFCIRelated(Object value) {
    return (value instanceof Relationship)
        && !(value instanceof CommunicationMean || value instanceof FunctionalChainInvolvement);
  }
  /**
   * Generate create child actions.
   * 
   * @param descriptors
   * @param selection
   * @param editingDomain
   * @param condition
   * @return
   */
  protected Collection<IContributionItem> generateCreateChildActions(Collection<CommandParameter> descriptors,
      EditingDomain editingDomain, AbstractCondition condition) {
    TreeSet<IContributionItem> contributionItems = new TreeSet<>(contributionItemComparator);

    if (null != descriptors) {
      // Map to store descriptors by type.
      Map<EClass, Set<CommandParameter>> descriptorsSortedByType = new HashMap<>();

      // Map to store descriptors by feature.
      Map<EReference, Set<CommandParameter>> descriptorsSortedByFeature = new HashMap<>();

      // Set of features displayed as sub menu managers.
      Set<EReference> featuresDisplayedAsSubMenuManager = new HashSet<>(0);

      for (CommandParameter descriptor : descriptors) {
        // Handle feature
        EReference feature = descriptor.getEReference();
        Set<CommandParameter> featureDescriptors = descriptorsSortedByFeature.get(feature);

        if (null == featureDescriptors) {
          featureDescriptors = new HashSet<>(1);
          descriptorsSortedByFeature.put(feature, featureDescriptors);
        }

        // Add the current descriptor for this feature.
        featureDescriptors.add(descriptor);

        // Handle eClass
        EClass eClass = descriptor.getEValue().eClass();
        Set<CommandParameter> typeDescriptors = descriptorsSortedByType.get(eClass);

        if (null == typeDescriptors) {
          typeDescriptors = new HashSet<>(1);
          descriptorsSortedByType.put(eClass, typeDescriptors);
        }

        // Add the current descriptor for this type.
        typeDescriptors.add(descriptor);

        // More than one descriptors for this type, mark underlying feature to
        // display it as sub menu managers.
        if ((typeDescriptors.size() > 1) && (featureDescriptors.size() > 1)
            && typeDescriptors.stream().noneMatch(DynamicCommandParameter.class::isInstance)
            && featureDescriptors.stream().noneMatch(DynamicCommandParameter.class::isInstance)) {
          featuresDisplayedAsSubMenuManager.add(feature);
        }
      }

      // Iterate again over all descriptors to fill in the resulting list
      // according to features that should be displayed as sub menu manager.
      for (CommandParameter descriptor : descriptors) {
        EReference reference = descriptor.getEReference();

        if (featuresDisplayedAsSubMenuManager.contains(reference)) {
          // fill in as a sub menu manager.
          Set<CommandParameter> featureDescriptors = descriptorsSortedByFeature.get(reference);

          // Create a menu manager for this feature.
          MenuManager featureMenuManager = new MenuManager(getFeatureLabel(reference, descriptor.getEValue()));

          // Add it in the resulting list.
          contributionItems.add(featureMenuManager);

          // Create the list of available action items.
          TreeSet<IContributionItem> actionItems = new TreeSet<>(contributionItemComparator);

          for (CommandParameter featureDescriptor : featureDescriptors) {
            fillContributionItems(editingDomain, condition, actionItems, featureDescriptor);
          }

          // Add them in feature menu manager.
          for (IContributionItem dynamicItem : actionItems) {
            featureMenuManager.add(dynamicItem);
          }

        } else {
          // Fill in resulting list with a standalone descriptor.
          fillContributionItems(editingDomain, condition, contributionItems, descriptor);
        }
      }
    }
    return contributionItems;
  }

  /**
   * Fill contribution items.
   * 
   * @param selection
   * @param editingDomain
   * @param condition
   * @param items
   * @param descriptor
   */
  protected void fillContributionItems(EditingDomain editingDomain, AbstractCondition condition,
      TreeSet<IContributionItem> items, CommandParameter descriptor) {

    condition.setValue(descriptor.getValue());

    if (condition.isValid()) {
      DynamicCreateChildAction action = createChildAction(editingDomain, (ISelection) descriptor.getOwner(),
          descriptor);

      if (action.isExecutable() && action.isEnabled()) {
        items.add(new DynamicActionContributionItem(action));
      }
    }
  }

  /**
   * Create an action for the given CommandParameter
   */
  protected DynamicCreateChildAction createChildAction(EditingDomain editingDomain, ISelection selection,
      CommandParameter descriptor) {
    return new CapellaCreateChildAction(editingDomain, selection, descriptor);
  }

  /**
   * Get feature UI label.
   * 
   * @param feature
   * @param object
   * @return
   */
  protected String getFeatureLabel(EStructuralFeature feature, EObject object) {
    ItemProviderAdapter genericItemProvider = getItemProviderAdapter(object);
    String featureLabel = EObjectLabelProviderHelper.getFeatureLabel(feature, genericItemProvider);
    genericItemProvider.dispose();
    return featureLabel;
  }

  /**
   * Get feature UI label.
   * 
   * @param class
   * @param object
   * @return
   */
  protected String getMetaclassLabel(EClass clazz, EObject object) {
    ItemProviderAdapter genericItemProvider = getItemProviderAdapter(object);
    String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(clazz, genericItemProvider);
    genericItemProvider.dispose();
    return metaclassLabel;
  }

  /**
   * Get a generic item provider.
   * 
   * @return
   */
  protected ItemProviderAdapter getItemProviderAdapter(EObject object) {
    IItemLabelProvider itemLabelProvider = getItemLabelProvider(object);

    if (itemLabelProvider instanceof ItemProviderDecorator) {
      IChangeNotifier notifier = ((ItemProviderDecorator) itemLabelProvider).getDecoratedItemProvider();
      if (notifier instanceof ItemProviderAdapter) {
        return (ItemProviderAdapter) notifier;
      }
    }
    return (ItemProviderAdapter) itemLabelProvider;
  }

  protected IItemLabelProvider getItemLabelProvider(EObject object) {
    AdapterFactory adapterFactory = CapellaAdapterFactoryProvider.getInstance().getAdapterFactory();
    Adapter adapter = adapterFactory.adapt(object, IItemLabelProvider.class);
    return (IItemLabelProvider) adapter;
  }

  /**
   * Extends {@link DynamicCreateChildAction} to override the setText.
   */
  protected class CapellaCreateChildAction extends DynamicCreateChildAction {
    /**
     * @param editingDomain
     * @param selection
     * @param descriptor
     */
    public CapellaCreateChildAction(EditingDomain editingDomain, ISelection selection, Object descriptor) {
      super(editingDomain, selection, descriptor);
    }

    /**
     * @see org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction#configureAction(org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void configureAction(ISelection selection) {
      super.configureAction(selection);

      EObject object = ((CommandParameter) descriptor).getEValue();

      if (descriptor instanceof DynamicCommandParameter) {
        DynamicCommandParameter richCommandParameter = (DynamicCommandParameter) descriptor;
        setText(richCommandParameter.getLabel());
        // dynamic commands have the same object type and are created in the same feature
        // always reset their image descriptor
        ImageDescriptor imageDescriptor = findBestImageDescriptor(object, selection);
        setImageDescriptor(imageDescriptor);
      } else {
        EClass eClass = object.eClass();
        setText(getMetaclassLabel(eClass, object));
      }

      if (getImageDescriptor() == null) {
        ImageDescriptor imageDescriptor = findBestImageDescriptor(object, selection);
        setImageDescriptor(imageDescriptor);
      }

    }

    /**
     * Returns the best possible image descriptor for the current object
     * 
     * @param object
     *          the object
     * @param selection
     *          the user selection
     * @return the best possible image descriptor for the current object
     */
    private ImageDescriptor findBestImageDescriptor(EObject object, ISelection selection) {
      // look for a image descriptor from the current label provider of the editing domain
      ImageDescriptor imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(EObjectLabelProviderHelper.getImage(object));
      if (imageDescriptor == null) {

        // no editing domain so look for a different item label provider
        IItemLabelProvider itemLabelProvider = getItemLabelProvider(object);
        URL imageUrl = null;
        if (itemLabelProvider != null && itemLabelProvider.getImage(object) instanceof URL) {
          imageUrl = (URL) itemLabelProvider.getImage(object);
        } else {
          // no item label provider for the current element, try to figure one out from the parent
          Object parent = ((StructuredSelection) selection).getFirstElement();
          EStructuralFeature feature = ((CommandParameter) descriptor).getEStructuralFeature();
          ItemProviderAdapter itemProvider = getItemProviderAdapter(object);
          Object childImage = itemProvider.getCreateChildImage(parent, feature, object, null);
          imageUrl = (URL) childImage;
          itemProvider.dispose();
        }
        imageDescriptor = ImageDescriptor.createFromURL(imageUrl);
      }
      return imageDescriptor;
    }

    @Override
    protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection) {
      if (collection.size() == 1) {
        Object owner = collection.iterator().next();
        if (descriptor instanceof CommandParameter) {
          CommandParameter param = ((CommandParameter) descriptor);
          if (owner instanceof Region && param.getValue() instanceof State
              && !(param.getValue() instanceof FinalState)) {
            if (!MoveHelper.getInstance().canMoveModeState((State) param.getValue(), (Region) owner))
              return UnexecutableCommand.INSTANCE;
          }
        }
      }
      return super.createActionCommand(editingDomain, collection);
    }

  }

  /**
   * Condition used to drive a behavior.
   */
  protected abstract class AbstractCondition {
    private Object value;

    /**
     * Whether or not this condition is valid.
     * 
     * @return <code>true</code> condition is fulfilled.
     */
    public abstract boolean isValid();

    /**
     * Set value used to compute the validity of this condition.
     * 
     * @param value
     */
    public void setValue(Object value) {
      this.value = value;
    }

    /**
     * Get the value used to compute the validity of this condition.
     * 
     * @return the value
     */
    protected Object getValue() {
      return this.value;
    }
  }
}
