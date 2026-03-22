/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.elk;

import java.util.List;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.CustomLayoutConfiguration;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;

/** 
 * Configuration Choice binding a configuration to a name.
 *
 * @param name label of the configuration
 * @param configuration configuration of layout
 * @author nperansin
 */
public record DiagramLayoutMode(String name, CustomLayoutConfiguration configuration) {
    
    /** Key for annotation. */
    public static final String LAYOUT_ANNOTATION_KEY = "elk.properties"; //$NON-NLS-1$
    
    /** Key for detail. */
    public static final String LAYOUT_MODE_KEY = "algorithm"; //$NON-NLS-1$

    /**
     * Returns the mode of diagram.
     * 
     * @param diagram 
     * @return the mode of layout or null if the diagram 
     */
    public static DiagramLayoutMode getMode(DDiagram diagram) {
        List<DiagramLayoutMode> modes = CapellaGmfLayoutProvider.getDiagramConfigurations(diagram.getDescription());
        if (modes.isEmpty()) {
            return null;
        }
        String modeName = getAnnotationDetail(diagram, LAYOUT_ANNOTATION_KEY, LAYOUT_MODE_KEY);
        return modes.stream()
                .filter(it -> it.name.equals(modeName))
                .findFirst()
                .orElse(modes.get(0));
    }
    
    /**
     * Returns the configuration of diagram mode.
     * 
     * @param diagram the diagram to layout
     * @return layout configuration
     */
    public static CustomLayoutConfiguration getModeConfiguration(DDiagram diagram) {
        DiagramLayoutMode mode = getMode(diagram);
        return mode != null ? mode.configuration : null;
    }
    
    /**
     * Sets the layout mode of a diagram.
     * 
     * @param diagram element to set
     * @param value mode to set
     */
    public static void setMode(DDiagram diagram, DiagramLayoutMode value) {
        EMap<String, String> details = ensureAnnotation(diagram, LAYOUT_ANNOTATION_KEY).getDetails();
        if (value == null) {
            details.removeKey(LAYOUT_MODE_KEY);
        } else {
            details.put(LAYOUT_MODE_KEY, value.name());
        }
    }
    
    private static String getAnnotationDetail(DDiagram owner, String source, String key) {
        DAnnotation annotation = owner.getDAnnotation(source);
        if (annotation == null) {
            return null;
        }
        return annotation.getDetails().get(key);
    }
    
    private static DAnnotation ensureAnnotation(DDiagram owner, String source) {
        DAnnotation result = owner.getDAnnotation(source);
        if (result == null) {
            result = DescriptionFactory.eINSTANCE.createDAnnotation();
            result.setSource(source);
            owner.getEAnnotations().add(result);
        }
        return result;
    }
    
    
}
