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

package org.polarsys.capella.core.data.fa.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.polarsys.capella.common.model.copypaste.SharedInitializeCopyCommand;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.provider.NamedElementItemProvider;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.kitalpha.emde.extension.ExtensionModelManager;
import org.polarsys.kitalpha.emde.extension.ModelExtensionHelper;
import org.polarsys.kitalpha.emde.model.edit.provider.NewChildDescriptorHelper;

/**
 * This is the item provider adapter for a {@link org.polarsys.capella.core.data.fa.FunctionalChain} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FunctionalChainItemProvider
	extends NamedElementItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor preConditionPropertyDescriptor;
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IItemPropertyDescriptor postConditionPropertyDescriptor;

	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public FunctionalChainItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void checkChildCreationExtender(Object object) {
    super.checkChildCreationExtender(object);
    if (object instanceof EObject) {
      EObject eObject = (EObject) object;
      // Process FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION
      if (preConditionPropertyDescriptor != null) {
        Object preConditionValue = eObject.eGet(FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION, true);
        if (preConditionValue != null && preConditionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) preConditionValue)) {
          itemPropertyDescriptors.remove(preConditionPropertyDescriptor);
        } else if (preConditionValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION) != null) {
          itemPropertyDescriptors.remove(preConditionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(preConditionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(preConditionPropertyDescriptor);
        }
      }
      // Process FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION
      if (postConditionPropertyDescriptor != null) {
        Object postConditionValue = eObject.eGet(FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION, true);
        if (postConditionValue != null && postConditionValue instanceof EObject && ModelExtensionHelper.getInstance(eObject).isExtensionModelDisabled((EObject) postConditionValue)) {
          itemPropertyDescriptors.remove(postConditionPropertyDescriptor);
        } else if (postConditionValue == null && ExtensionModelManager.getAnyType(eObject, FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION) != null) {
          itemPropertyDescriptors.remove(postConditionPropertyDescriptor);				  					
        } else if (itemPropertyDescriptors.contains(postConditionPropertyDescriptor) == false) {
          itemPropertyDescriptors.add(postConditionPropertyDescriptor);
        }
      }
    }		
  }

	/**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
    if (itemPropertyDescriptors == null) {
      super.getPropertyDescriptors(object);

      addInvolvedInvolvementsPropertyDescriptor(object);
      addInvolvingInvolvementsPropertyDescriptor(object);
      addKindPropertyDescriptor(object);
      addInvolvedFunctionalChainInvolvementsPropertyDescriptor(object);
      addInvolvedFunctionsPropertyDescriptor(object);
      addInvolvedFunctionalExchangesPropertyDescriptor(object);
      addInvolvedElementsPropertyDescriptor(object);
      addEnactedFunctionsPropertyDescriptor(object);
      addEnactedFunctionalBlocksPropertyDescriptor(object);
      addAvailableInStatesPropertyDescriptor(object);
      addFirstFunctionalChainInvolvementsPropertyDescriptor(object);
      addInvolvingCapabilitiesPropertyDescriptor(object);
      addInvolvingCapabilityRealizationsPropertyDescriptor(object);
      addRealizedFunctionalChainsPropertyDescriptor(object);
      addRealizingFunctionalChainsPropertyDescriptor(object);
      addPreConditionPropertyDescriptor(object);
      addPostConditionPropertyDescriptor(object);
    }
    // begin-extension-code
    checkChildCreationExtender(object);
    // end-extension-code
    return itemPropertyDescriptors;
  }

	/**
   * This adds a property descriptor for the Involved Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InvolverElement_involvedInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InvolverElement_involvedInvolvements_feature", "_UI_InvolverElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.INVOLVER_ELEMENT__INVOLVED_INVOLVEMENTS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involving Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_InvolvedElement_involvingInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_InvolvedElement_involvingInvolvements_feature", "_UI_InvolvedElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         CapellacorePackage.Literals.INVOLVED_ELEMENT__INVOLVING_INVOLVEMENTS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Kind feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addKindPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_kind_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_kind_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__KIND,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involved Functional Chain Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedFunctionalChainInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_involvedFunctionalChainInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_involvedFunctionalChainInvolvements_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_CHAIN_INVOLVEMENTS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involved Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_involvedFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_involvedFunctions_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involved Functional Exchanges feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedFunctionalExchangesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_involvedFunctionalExchanges_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_involvedFunctionalExchanges_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_FUNCTIONAL_EXCHANGES,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involved Elements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvedElementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_involvedElements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_involvedElements_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVED_ELEMENTS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Enacted Functions feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addEnactedFunctionsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_enactedFunctions_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_enactedFunctions_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Enacted Functional Blocks feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addEnactedFunctionalBlocksPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_enactedFunctionalBlocks_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_enactedFunctionalBlocks_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__ENACTED_FUNCTIONAL_BLOCKS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Available In States feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addAvailableInStatesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_availableInStates_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_availableInStates_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__AVAILABLE_IN_STATES,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the First Functional Chain Involvements feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addFirstFunctionalChainInvolvementsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_firstFunctionalChainInvolvements_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_firstFunctionalChainInvolvements_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__FIRST_FUNCTIONAL_CHAIN_INVOLVEMENTS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involving Capabilities feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingCapabilitiesPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_involvingCapabilities_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_involvingCapabilities_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITIES,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Involving Capability Realizations feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addInvolvingCapabilityRealizationsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_involvingCapabilityRealizations_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_involvingCapabilityRealizations_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__INVOLVING_CAPABILITY_REALIZATIONS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Realized Functional Chains feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizedFunctionalChainsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_realizedFunctionalChains_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_realizedFunctionalChains_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZED_FUNCTIONAL_CHAINS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Realizing Functional Chains feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addRealizingFunctionalChainsPropertyDescriptor(Object object) {

    // begin-extension-code
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
    // end-extension-code
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_realizingFunctionalChains_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_realizingFunctionalChains_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__REALIZING_FUNCTIONAL_CHAINS,
         false,
         false,
         false,
         null,
         null,
    // begin-extension-code
         null));
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Pre Condition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPreConditionPropertyDescriptor(Object object) {
    // begin-extension-code
    preConditionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_preCondition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_preCondition_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__PRE_CONDITION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(preConditionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This adds a property descriptor for the Post Condition feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addPostConditionPropertyDescriptor(Object object) {
    // begin-extension-code
    postConditionPropertyDescriptor = createItemPropertyDescriptor
    // end-extension-code		
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_FunctionalChain_postCondition_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_FunctionalChain_postCondition_feature", "_UI_FunctionalChain_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         FaPackage.Literals.FUNCTIONAL_CHAIN__POST_CONDITION,
         true,
         false,
         true,
         null,
         null,
    // begin-extension-code
         null);
    itemPropertyDescriptors.add(postConditionPropertyDescriptor);
    // end-extension-code
  }

	/**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
    if (childrenFeatures == null) {
      super.getChildrenFeatures(object);
      childrenFeatures.add(FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS);
      childrenFeatures.add(FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS);
      childrenFeatures.add(FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES);
      childrenFeatures.add(FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS);
    }
    return childrenFeatures;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

	/**
   * This returns FunctionalChain.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public Object getImage(Object object) {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/FunctionalChain")); //$NON-NLS-1$
  }

	/**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public String getText(Object object) {
   String[] result = new String[] { null };

    	//begin-capella-code
    String label = ((FunctionalChain)object).getName();
    //end-capella-code
    
  
      result[0] = label == null || label.length() == 0 ?
      //begin-capella-code
      "[" + getString("_UI_FunctionalChain_type") + "]" : label; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      //end-capella-code

    return result[0];

  }

	/**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public void notifyChanged(Notification notification) {
    updateChildren(notification);

    switch (notification.getFeatureID(FunctionalChain.class)) {
      case FaPackage.FUNCTIONAL_CHAIN__KIND:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS:
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS:
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES:
      case FaPackage.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

	/**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
    super.collectNewChildDescriptors(newChildDescriptors, object);
                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS,
                         FaFactory.eINSTANCE.createFunctionalChainReference());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS,
                         FaFactory.eINSTANCE.createFunctionalChainInvolvementLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_INVOLVEMENTS,
                         FaFactory.eINSTANCE.createFunctionalChainInvolvementFunction());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_FUNCTIONAL_CHAIN_REALIZATIONS,
                         FaFactory.eINSTANCE.createFunctionalChainRealization());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_NODES,
                         FaFactory.eINSTANCE.createControlNode());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


                // begin-extension-code
                {
                    CommandParameter commandParameter = createChildParameter
                        (FaPackage.Literals.FUNCTIONAL_CHAIN__OWNED_SEQUENCE_LINKS,
                         FaFactory.eINSTANCE.createSequenceLink());
                    if (NewChildDescriptorHelper.isValidCommand(object, commandParameter)) {
                        newChildDescriptors.add(commandParameter);      
                    }
                }
                // end-extension-code


  }

	// begin-capella-code
	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper) {
    return new SharedInitializeCopyCommand(domain, owner, helper);
  }
	// end-capella-code
}
