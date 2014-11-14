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
package org.polarsys.capella.core.transition.system.topdown.handlers.log;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.transition.common.handlers.log.DiffModelViewer;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
import org.polarsys.capella.core.transition.system.handlers.log.CapellaLogHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TopDownLogHandler extends CapellaLogHandler {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return super.dispose(context_p);
  }

  @Override
  public String getReadableText(EObject object_p) {
    return NLS.bind("''{0}'' [{1}]", EObjectLabelProviderHelper.getText(object_p), EObjectLabelProviderHelper.getMetaclassLabel(object_p, false));
  }

  @Override
  public String getViewerText(DiffModelViewer view) {

    IDifference diff_p = view.getRelatedDiff();
    DiffScope diffScope_p = view.getScopeDiff();
    String _textDiff = "";
    EObject diffelt = null;
    Role scope = diffScope_p == DiffScope.Source ? Role.REFERENCE : Role.TARGET;

    // Difference on Reference of element
    if (diff_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence rvp = (IReferenceValuePresence) diff_p;

      if (diffScope_p == DiffScope.Source) {
        diffelt = rvp.getValue().get(Role.REFERENCE);

        _textDiff = !rvp.isOrder() ? "Add element " : "Ordering of ";

        if (diffelt != null) {
          EObject meMatch = rvp.getElementMatch().get(Role.REFERENCE);
          if (meMatch != null) {
            _textDiff = _textDiff + getReadableText(diffelt);
          }

          _textDiff = _textDiff + " on " + getReadableText(meMatch);

          if (!rvp.isOrder()) {
            Collection<IMergeableDifference> listMergeDiff = new ArrayList<IMergeableDifference>();
            listMergeDiff.addAll(rvp.getDirectRequiresDependencies(Role.TARGET));
            listMergeDiff.addAll(rvp.getDirectImpliesDependencies(Role.TARGET));
            for (IMergeableDifference mergeDiff : listMergeDiff) {
              if (mergeDiff instanceof IReferenceValuePresence) {
                EObject meMergeDiff = ((IReferenceValuePresence) mergeDiff).getElementMatch().get(Role.TARGET);
                if (meMergeDiff != null) {
                  _textDiff = _textDiff + " was " + getReadableText(meMergeDiff);
                }
              }
            }
          }

          if (rvp.getFeature() != null) {
            String featureName = rvp.getFeature().getName();
            _textDiff = _textDiff + " {" + featureName + "}";
          }
        }
      } else {
        diffelt = rvp.getValue().get(Role.TARGET);

        _textDiff = !rvp.isOrder() ? "Add element " : "Ordering of ";

        if (diffelt != null) {
          EObject meMatch = rvp.getElementMatch().get(Role.TARGET);
          if (meMatch != null) {
            _textDiff = _textDiff + getReadableText(diffelt);
          }

          _textDiff = _textDiff + " on " + getReadableText(meMatch);

          if (!rvp.isOrder()) {
            Collection<IMergeableDifference> listMergeDiff = new ArrayList<IMergeableDifference>();
            listMergeDiff.addAll(rvp.getDirectRequiresDependencies(Role.REFERENCE));
            listMergeDiff.addAll(rvp.getDirectImpliesDependencies(Role.REFERENCE));
            for (IMergeableDifference mergeDiff : listMergeDiff) {
              if (mergeDiff instanceof IReferenceValuePresence) {
                EObject meMergeDiff = ((IReferenceValuePresence) mergeDiff).getElementMatch().get(Role.REFERENCE);
                if (meMergeDiff != null) {
                  _textDiff = _textDiff + " was " + getReadableText(meMergeDiff) + "";
                }
              }
            }
          }

          if (rvp.getFeature() != null) {
            String featureName = rvp.getFeature().getName();
            _textDiff = _textDiff + " {" + featureName + "}";
          }
        }

      }
    }

    // Difference on Presence of new element
    if (diff_p instanceof IElementPresence) {
      IElementPresence ep = (IElementPresence) diff_p;
      diffelt = ep.getElement();

      if (diffelt != null) {
        _textDiff = "Presence of " + getReadableText(diffelt);
        if (ep.getOwnerMatch() != null) {

          EObject parent = ep.getOwnerMatch().get(ep.getPresenceRole());
          if (parent != null) {
            _textDiff = _textDiff + " into " + getReadableText(parent);
          }
        }
      }
    }

    // Difference on Attribute of an element
    if (diff_p instanceof IAttributeValuePresence) {
      IAttributeValuePresence avp = (IAttributeValuePresence) diff_p;

      diffelt = avp.getElementMatch().get(Role.REFERENCE);
      String ordering = avp.isOrder() ? "of order " : "";

      if (diffelt != null) {
        String featureName = avp.getFeature().getName();
        _textDiff = _textDiff + "' : " + featureName + "";

        _textDiff = "Modification " + ordering + "of '" + featureName + "' on " + getReadableText(diffelt);
        if (diffelt instanceof CapellaElement) {
          String value = avp.getValue().toString();
          _textDiff = _textDiff + " = '" + value + "'";

          for (IMergeableDifference mergeDiff : avp.getDirectImpliesDependencies(Role.TARGET)) {
            if (mergeDiff instanceof IAttributeValuePresence) {
              String featureMergeDiffName = ((IAttributeValuePresence) mergeDiff).getValue().toString();
              _textDiff = _textDiff + " (was '" + featureMergeDiffName + "')";
            }
          }

        }
      }
    }
    return _textDiff;
  }

  /**
   * @param reportComponent_p
   */
  public TopDownLogHandler(String reportComponent_p) {
    super(reportComponent_p);
  }

}
