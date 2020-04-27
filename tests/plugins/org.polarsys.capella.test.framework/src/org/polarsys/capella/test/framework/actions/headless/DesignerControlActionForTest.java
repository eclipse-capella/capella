/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.actions.headless;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.control.SiriusControlHandler;
import org.eclipse.sirius.ui.tools.api.control.SiriusUncontrolHandler;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.model.label.LabelRetriever;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.platform.sirius.ui.actions.CapellaActionsActivator;
import org.polarsys.capella.core.sirius.ui.actions.DesignerControlAction;

/**
 * FIXME Copied from plugin tests.fragment.ju. All this kind of headless stuff
 * should be stored in tests.common when test framework will be refactored.
 * 
 * @author Erwan Brottier
 */
public class DesignerControlActionForTest extends DesignerControlAction {

  /** For fragmentation purpose */
  protected List<DRepresentationDescriptor> _dRepresentationsToMove = null;

  /** For unfragmentation purpose */
  protected boolean _shouldUncontrolRepresentations;

  /**
   * @param domain_p
   */
  public DesignerControlActionForTest() {
    super();
  }

  /** Write accessor */
  final public void setDRepresentationDescriptorsToMove(List<DRepresentationDescriptor> dRepresentations_p) {
    _dRepresentationsToMove = dRepresentations_p;
  }

  /** Write accessor */
  final public void setShouldUncontrolRepresentations(boolean value_p) {
    _shouldUncontrolRepresentations = value_p;
  }

  @Override
  protected void fragment(Shell shell__p) {

    SiriusControlHandler siriusControlHandler = new CapellaSiriusControlHandler(shell__p) {

      @Override
      protected URI getControledResourceURI(final Shell shell, final EObject semanticRoot) {
        String uriValue = computeFragmentUri(semanticRoot);

        return URI.createURI(uriValue, true);
      }

      /**
       * @see org.eclipse.sirius.ui.tools.api.control.SiriusControlHandler#getDefaultCorrespondingAird(org.eclipse.emf.common.util.URI)
       */
      @Override
      protected URI getDefaultCorrespondingAird(URI semanticModelUri) {
        // Not very clean:
        // - getDefaultCorresponding uses the preference
        // ICapellaPreferences.PREFERENCE_CAPELLA_AIRD_FRAGMENT_FILE_EXTENSION to get the airdfragment file extension
        // - Problem : the initializer for this preference is not yet called -> force it by starting the plugin...
        CapellaActionsActivator.getDefault();
        return super.getDefaultCorrespondingAird(semanticModelUri);
      }

      protected String computeFragmentUri(EObject _controledObject) {
        StringBuilder defaultURI = new StringBuilder();
        URI fragmentFolderUri = _controledObject.eResource().getURI().trimSegments(1);
        // Add fragments folder if necessary.
        if (!CapellaResourceHelper.FRAGMENTS_DEFAULT_FOLDER.equals(fragmentFolderUri.lastSegment())) {
          fragmentFolderUri = fragmentFolderUri.appendSegment(CapellaResourceHelper.FRAGMENTS_DEFAULT_FOLDER);
        }
        defaultURI.append(URI.decode(fragmentFolderUri.toString()));
        defaultURI.append(ICommonConstants.SLASH_CHARACTER);

        // Format name = full label path, with Camel Case to shorten the path.
        ModelElement controlledModelElement = (ModelElement) _controledObject;

        // special characters need a specific encoding
        IPath fullObjectPath = new Path(getFullLabel(controlledModelElement));
        // Remove Project name and SystemEngineering name
        fullObjectPath = fullObjectPath.removeFirstSegments(2);
        // Make analysis level name shortest as possible (camel case based).
        String analyisName = fullObjectPath.segment(0);
        if (null != analyisName) {
          // Remove analysis long name.
          fullObjectPath = fullObjectPath.removeFirstSegments(1);
          IPath fragmentRelativePath = new Path(getCamelCaseRepresentation(analyisName));
          for (String segment : fullObjectPath.segments()) {
            String appendedSegment = segment;
            // Replacing "Root XXXX Function" expression by its camel case
            // representation.
            if (segment.matches("(Root\\s).*(\\sFunction)")) { //$NON-NLS-1$
              appendedSegment = getCamelCaseRepresentation(segment);
            }
            fragmentRelativePath = fragmentRelativePath.append(appendedSegment);
          }
          // Append the new name starting with came case analysis level.
          String fragmentRelative = fragmentRelativePath.toString();
          // Replace '/' character to avoid sub folders.
          fragmentRelative = fragmentRelative.replace(ICommonConstants.SLASH_CHARACTER, '-');
          defaultURI.append(fragmentRelative);
          defaultURI.append(ICommonConstants.POINT_CHARACTER);
          defaultURI.append(CapellaResourceHelper.CAPELLA_FRAGMENT_FILE_EXTENSION);
          System.out.println(defaultURI);
        }
        return defaultURI.toString();
      }

      private String getCamelCaseRepresentation(String word_p) {
        StringBuilder camelCaseAnalysisLevel = new StringBuilder();
        for (char currentCharacter : word_p.toCharArray()) {
          if (Character.isUpperCase(currentCharacter)) {
            camelCaseAnalysisLevel.append(currentCharacter);
          }
        }
        return camelCaseAnalysisLevel.length() == 0 ? word_p : camelCaseAnalysisLevel.toString();
      }

      private String getFullLabel(ModelElement object_p) {
        String result;
        result = getFullPath(object_p) + getLabel(object_p);
        return result;
      }

      private String getFullPath(EObject object_p) {
        String result;
        EObject eContainer = object_p.eContainer();
        if (eContainer == object_p) {
          return ICommonConstants.EMPTY_STRING;
        }
        if (eContainer != null) {
          result = getFullPath(eContainer) + getLabel(eContainer) + ICommonConstants.SLASH_CHARACTER;
        } else {
          result = Character.toString(ICommonConstants.SLASH_CHARACTER);
        }
        return result;
      }

      private String getLabel(EObject object_p) {
        StringBuilder result = new StringBuilder();
        for (char car : LabelRetriever.getLabel(object_p).toCharArray()) {
          if (Character.isJavaIdentifierPart(car) || Character.isWhitespace(car)) {
            result.append(car);
          }
        }

        return result.toString();
      }

      @Override
      protected Collection<DRepresentationDescriptor> getRepresentationDescriptorsToMove(Shell shell_p,
          Session session_p, EObject semanticRoot_p) throws InterruptedException {
        return null == _dRepresentationsToMove ? new ArrayList<DRepresentationDescriptor>() : _dRepresentationsToMove;
      }
    };
    siriusControlHandler.performControl(shell__p, _eObject, new NullProgressMonitor());

    return;
  }

  @Override
  protected void unFragment(final Shell shell__p) {

    SiriusUncontrolHandler siriusUncontrolHandler = new CapellaSiriusUncontrolHandler() {

      @Override
      protected boolean shouldUncontrolRepresentations(Shell shell_p) {
        return _shouldUncontrolRepresentations;
      }

    };

    siriusUncontrolHandler.performUncontrol(shell__p, _eObject, new NullProgressMonitor());

    return;
  }

}
