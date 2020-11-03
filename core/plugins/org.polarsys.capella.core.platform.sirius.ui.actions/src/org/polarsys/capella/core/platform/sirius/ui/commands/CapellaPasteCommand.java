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


package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.Involvement;
import org.polarsys.capella.core.data.capellacore.InvolverElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.AbstractFunctionAllocation;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvement;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.EnumerationLiteral;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;

/**
 * The Capella command allowing to paste Capella elements. It generates a new object identifier each time the command is
 * called.
 */
public class CapellaPasteCommand extends PasteFromClipboardCommand {

  private NameCollisionHelper ncHelper;

  private boolean pasteAfterCut;
  private Helper _copyHelper;

  /**
   * Constructs the Capella command allowing to paste Capella elements. It generates a new object identifier each time
   * the command is called.
   * 
   * @param domain
   *          The editing domain.
   * @param owner
   *          The mode object owner.
   * @param feature
   *          The feature.
   * @param index
   *          The command index.
   */
  public CapellaPasteCommand(EditingDomain domain, Object owner, Object feature, int index) {
    super(domain, owner, feature, index);
    ncHelper = NameCollisionHelper.getDefault();
  }

  /**
   * @see org.eclipse.emf.edit.command.PasteFromClipboardCommand#doExecute()
   */
  @Override
  public void doExecute() {
    // Updates the identifier of all selected objects.
    Collection<?> clipboard = domain.getClipboard();

    if (pasteAfterCut) {
      clipboard = SharedCutPasteClipboard.getCutClipboard().getClipboard();
      clipboard.clear();
    }

    // Collect objects that collide with already contained elements.
    Collection<AbstractNamedElement> collidingObjects = ncHelper.findConflictingElements(((EObject) owner).eContents(), clipboard);

    // Perform the paste operation.
    super.doExecute();

    // Handle pasted objects that collide existing ones.
    ArrayList<AbstractNamedElement> pastedObjectsWithNameCollision = new ArrayList<AbstractNamedElement>(collidingObjects.size());
    // Collect pasted objects related that collide existing ones.
    for (AbstractNamedElement object : collidingObjects) {
      pastedObjectsWithNameCollision.add((AbstractNamedElement) _copyHelper.getCopy(object));
    }
    ncHelper.handleNamingCollision(pastedObjectsWithNameCollision, ((EObject) owner));
    // Only necessary from copy action.
    if (null != _copyHelper) {
      _copyHelper.clear();
    }
  }

  protected boolean doPrepare() {
    // Create a strict compound command to do a copy and then add the result
    //
    command = new StrictCompoundCommand() {

      /**
       * {@inheritDoc}
       */
      @Override
      protected boolean prepare() {
        // Force pessimistic to true to make sure undo and redo, take
        // into account all sub commands.
        isPessimistic = true;
        return super.prepare();
      }
    };

    // Create a command to copy the clipboard.
    //
    final Command copyCommand = createCopyCommand();
    if (copyCommand != UnexecutableCommand.INSTANCE) {
      command.append(copyCommand);
    }

    if (domain != null) {
      command.append(new CommandWrapper() {

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("synthetic-access")
        @Override
        protected Command createCommand() {
          // Add some commands for Association case.
          CompoundCommand compoundCommand = new CompoundCommand();
          // Handle specific case.
          for (Object objectToCopy : domain.getClipboard()) {
            if (objectToCopy instanceof Association) {
              final Association association = (Association) objectToCopy;
              prepareAssociationPaste(association, compoundCommand);
            } else if (objectToCopy instanceof ComponentFunctionalAllocation) {
              final ComponentFunctionalAllocation componentFunctionalAllocation = (ComponentFunctionalAllocation) objectToCopy;
              prepareComponentFunctionalAllocationPaste(componentFunctionalAllocation, compoundCommand);
            } else if (objectToCopy instanceof EnumerationLiteral) {
              final EnumerationLiteral enumerationLiteral = (EnumerationLiteral) objectToCopy;
              prepareEnumerationLiteralPaste(enumerationLiteral, compoundCommand);
            } else if (objectToCopy instanceof FunctionalChainInvolvement || objectToCopy instanceof PhysicalPathInvolvement) {
              final Involvement involvement = (Involvement) objectToCopy;
              prepareInvolvementPaste(involvement, compoundCommand);
            } else if (objectToCopy instanceof Component) {
              Component component = (Component) objectToCopy;
              if (owner instanceof EObject
                  && !TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((EObject) owner))) {
                prepareComponentPaste(component, compoundCommand);
              }
            }
          }
          if (compoundCommand.isEmpty()) {
            compoundCommand.appendAndExecute(new IdentityCommand());
          }
          return compoundCommand;
        }

      });
    }

    // Create a proxy that will create an add command.
    //
    command.append(new CommandWrapper() {
      @SuppressWarnings("synthetic-access")
      @Override
      protected Command createCommand() {
        Collection<?> pasteElements = copyCommand.getResult();
        CompoundCommand addCommands = new CompoundCommand();
        final IStatus status = PasteCommandHelper.createPasteCommands(pasteElements, addCommands, (EObject) owner, (EStructuralFeature) feature, domain, index, true);

        // Check if something went wrong
        if (!status.isOK()) {

          // Remove holding resources created by the command creation
          // if (domain instanceof SemanticEditingDomain) {
          // SemanticEditingDomain sed = ((SemanticEditingDomain) domain);
          // if ((sed.getHoldingResource() != null) && (sed.getHoldingResource().getContents() != null)) {
          // sed.getHoldingResource().getContents().removeAll(pasteElements);
          // }
          // }

          // Show error message
          PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            public void run() {
              StatusManager.getManager().handle(status, StatusManager.SHOW);
            }
          });
        }

        return addCommands;
      }
    });

    boolean result = false;
    if (optimize && domain != null) {
      // This will determine canExecute as efficiently as possible.
      result = optimizedCanExecute();
    } else {
      // This will actually execute the copy command in order to check if
      // the add can execute.
      result = command.canExecute();
    }

    return result;
  }

  /**
   * @return
   */
  private Command createCopyCommand() {
    Collection<Object> collection = null;
    if (domain != null) {
      collection = domain.getClipboard();
    }
    if ((collection == null) || collection.isEmpty()) {
      return UnexecutableCommand.INSTANCE;
    }
    _copyHelper = new Helper();
    CompoundCommand copyCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);

    // We should use CapellaCopyCommand instead of CopyCommand for not to contribute to the holding resource (See
    // ModelElementImpl constructor) that could cause
    // problems if the paste is not executed (for example copying between different projects). But this caused a
    // regression because Enumeration literals
    // abstract types were not updated correctly when copy/paste
    // We will use normal CopyCommand and then remove the holding resource references before showing Error message

    for (Object object : collection) {
      copyCommand.append(domain.createCommand(CopyCommand.class, new CommandParameter(object, null, _copyHelper)));
    }
    return copyCommand.unwrap();
  }

  /**
   * Return the property opposite to the given one in the given association, if any
   * 
   * @param association
   *          a non-null association
   * @param property
   *          a non-null property
   * @return a non-null property if the association is bidirectional and property is one end
   */
  private Property getOppositeEnd(Association association, Property property) {
    Property result = null;
    if (association.getNavigableMembers().size() == 2) {
      // The association is bidirectional
      List<Property> ends = new ArrayList<Property>(association.getNavigableMembers());
      ends.remove(property);
      if (ends.size() == 1) {
        // The given property was one of the ends: success
        result = ends.get(0);
      }
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.edit.command.PasteFromClipboardCommand#prepare()
   */
  @Override
  protected boolean prepare() {
    if (!(owner instanceof EObject)) {
      return false;
    }
    if (SharedCutPasteClipboard.getCutClipboard().hasCut()) {
      Collection<?> cutElements = SharedCutPasteClipboard.getCutClipboard().getClipboard();
      // The clipboard contains a cut model, so we will only paste it
      // using an AddCommand
      command = new StrictCompoundCommand();
      // Create a compound commands to store all AddCommand.
      // DON'T use a StrictCompound command here, because
      // StrictCompound.prepare call execute...
      CompoundCommand addCommands = new CompoundCommand();

      PasteCommandHelper.createPasteCommands(cutElements, addCommands, (EObject) owner, (EStructuralFeature) feature, domain, index, true);
      command.append(addCommands);
      boolean result = command.canExecute();
      pasteAfterCut = true;
      return result;
    }
    return doPrepare();
  }

  /**
   * Extend the given preparation command for properly pasting the given enumeration literal
   * 
   * @param enumerationLiteral
   *          a non-null enumeration literal
   * @param compoundCommand
   *          a non-null command
   */
  protected void prepareEnumerationLiteralPaste(EnumerationLiteral enumerationLiteral, CompoundCommand compoundCommand) {
    EnumerationLiteral copiedEnumerationLiteral = (EnumerationLiteral) _copyHelper.getCopy(enumerationLiteral);

    if (owner instanceof Enumeration) {
      compoundCommand.appendAndExecute(SetCommand.create(domain, copiedEnumerationLiteral, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, owner));
    }
  }

  /**
   * Extend the given preparation command for componentFunctionalAllocation pasting to set a source element
   * 
   * @param componentFunctionalAllocation
   * @param compoundCommand
   */
  private void prepareComponentFunctionalAllocationPaste(ComponentFunctionalAllocation componentFunctionalAllocation, CompoundCommand compoundCommand) {
    if (owner != null && componentFunctionalAllocation != null && componentFunctionalAllocation.getTargetElement() != null) {
      if (owner instanceof TraceableElement) {
        AbstractFunctionAllocation functionalAllocation = (AbstractFunctionAllocation) _copyHelper.get(componentFunctionalAllocation);
        compoundCommand.appendAndExecute(SetCommand.create(domain, functionalAllocation, ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT, owner));
      }
    }
  }

  /**
   * Extend the given preparation command for Involvement pasting to set an involver
   * 
   * @param involvement
   * @param compoundCommand
   */
  private void prepareInvolvementPaste(Involvement involvement, CompoundCommand compoundCommand) {
    if (involvement != null && involvement.getInvolver() != null) {
      Involvement inv = (Involvement) _copyHelper.getCopy(involvement);
      if (owner instanceof InvolverElement) {
        
        if (inv instanceof FunctionalChainInvolvement) {
          for (FunctionalChainInvolvement nextInv : ((FunctionalChainInvolvement) inv).getNextFunctionalChainInvolvements()) {
            if (null != nextInv.eContainer()) {
              compoundCommand.append(RemoveCommand.create(domain, inv, FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT__NEXT_FUNCTIONAL_CHAIN_INVOLVEMENTS, nextInv));
            }
          }
        } else if (inv instanceof PhysicalPathInvolvement) {
          for (PhysicalPathInvolvement nextInv : ((PhysicalPathInvolvement) inv).getNextInvolvements()) {
            if (null != nextInv.eContainer()) {
              compoundCommand.append(RemoveCommand.create(domain, inv, CsPackage.Literals.PHYSICAL_PATH_INVOLVEMENT__NEXT_INVOLVEMENTS, nextInv));
            }
          }
        }
      }
    }
  }

  /**
   * Extend the given preparation command for Component pasting to create a new Part that reference the component
   * 
   * @param component
   * @param compoundCommand
   */
  private void prepareComponentPaste(Component component, CompoundCommand compoundCommand) {
    EObject pastedComponent = _copyHelper.getCopy(component);
    EStructuralFeature ownedPartFeature = null;
    if (owner instanceof Component) {
      ownedPartFeature = CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
    } else if (owner instanceof ComponentPkg) {
      ownedPartFeature = CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS;
    }
    if (pastedComponent instanceof Component && ownedPartFeature != null) {
      // Creates the part.
      final Command createPartCmd = CreateChildCommand.create(domain, owner,
          new CommandParameter(owner, ownedPartFeature, CsFactory.eINSTANCE.createPart()), Collections.emptyList());
      compoundCommand.append(createPartCmd);

      Collection<?> result = createPartCmd.getResult();
      if (result.size() == 1) {
        Object createdPart = result.iterator().next();
        if (createdPart instanceof Part) {
          // Sets the part name.
          compoundCommand.append(SetCommand.create(domain, createdPart,
              ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, ((Component) pastedComponent).getName()));
          // Sets the part type.
          compoundCommand.append(SetCommand.create(domain, createdPart,
              ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, pastedComponent));
        }
      }
    }
  }
  
  /**
   * Extend the given preparation command for properly pasting the given association
   * 
   * @param association
   *          a non-null association
   * @param compoundCommand
   *          a non-null command
   */
  @SuppressWarnings("synthetic-access")
  protected void prepareAssociationPaste(Association association, CompoundCommand compoundCommand) {
    List<Property> navigableMembers = association.getNavigableMembers();
    Association copiedAssociation = (Association) _copyHelper.getCopy(association);
    // Loop over properties owned by the association could
    // be in [0,2] range depending on isNavigable relation.
    for (final Property property : navigableMembers) {
      // Only if the property is not being copied, enforce its copy
      if (_copyHelper.get(property) == null) {
        // Clear navigable members that are pointing to
        // initial properties.
        copiedAssociation.getNavigableMembers().remove(property);
        // Copy command to duplicate the property.
        compoundCommand.appendAndExecute(domain.createCommand(CopyCommand.class, new CommandParameter(property, null, _copyHelper)));
        final Property copiedProperty = (Property) _copyHelper.getCopy(property);
        // Distinguish the bidirectional / unidirectional cases
        if ((association.getNavigableMembers().size() == 2) && (_copyHelper.get(property.eContainer()) == null)
            && (_copyHelper.get(getOppositeEnd(association, property).eContainer()) != null)) {
          // Only one end of the bidirectional association is being copied:
          // the new association must be unidirectional, hence the navigable
          // member on the non-copied side must belong to the association
          // -> Containment
          compoundCommand.appendAndExecute(AddCommand.create(domain, copiedAssociation, InformationPackage.Literals.ASSOCIATION__OWNED_MEMBERS, copiedProperty));
          // -> Type
          Object clipboardType = SharedCopyPasteElements.getInstance().getCopyObject(property.getAbstractType());
          AbstractType copiedType = (AbstractType) _copyHelper.get(clipboardType);
          compoundCommand.appendAndExecute(SetCommand.create(domain, copiedProperty, ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT__ABSTRACT_TYPE, copiedType));
        } else {
          // Don't add the copied property to association as its container is
          // the owner of the initial property.
          final EObject newPropertyOwner = property.eContainer();
          // -> Containment
          compoundCommand.appendAndExecute(AddCommand.create(domain, newPropertyOwner, CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES, copiedProperty));
          // -> Name
          compoundCommand.appendAndExecute(new CommandWrapper() {
            /**
             * {@inheritDoc}
             */
            @Override
            protected Command createCommand() {
              // The computed name is overridden
              // at command execution time.
              ncHelper.handleNamingCollision(Collections.singletonList(copiedProperty), newPropertyOwner);
              // Create a nested set command to
              // set it.
              return SetCommand.create(domain, copiedProperty, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, copiedProperty.getName());
            }
          });
          // -> Navigable members
          compoundCommand.appendAndExecute(AddCommand.create(domain, copiedAssociation, InformationPackage.Literals.ASSOCIATION__NAVIGABLE_MEMBERS, copiedProperty));
        }
      }
    }
  }
}
