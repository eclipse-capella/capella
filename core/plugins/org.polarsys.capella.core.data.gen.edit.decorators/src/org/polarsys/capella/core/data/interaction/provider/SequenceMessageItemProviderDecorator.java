/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.interaction.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.core.data.capellamodeller.provider.CapellaModellerEditPlugin;
import org.polarsys.capella.core.data.gen.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.capella.core.data.interaction.ArmTimerEvent;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.MessageKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;

public class SequenceMessageItemProviderDecorator extends ItemProviderAdapterDecorator
        implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

    private static final String FULL_OBJ16_SEQUENCE_MESSAGE = "full/obj16/SequenceMessage";

    public SequenceMessageItemProviderDecorator(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        return super.getText(object);
    }

    @Override
    public Object getImage(Object object) {
        MessageKind kind = ((SequenceMessage) object).getKind();

        final String imagePath; // $NON-NLS-1$
        switch (kind) {
        case REPLY:
            imagePath = "full/obj16/SequenceMessageReply"; //$NON-NLS-1$
            break;
        case CREATE:
            imagePath = "full/obj16/SequenceMessageCreate"; //$NON-NLS-1$
            break;
        case DELETE:
            imagePath = "full/obj16/SequenceMessageDelete"; //$NON-NLS-1$
            break;
        case TIMER:
            MessageEnd receivingEnd = ((SequenceMessage) object).getReceivingEnd();
            MessageEnd sendingEnd = ((SequenceMessage) object).getSendingEnd();
            if (receivingEnd == null && sendingEnd == null) {
                imagePath = FULL_OBJ16_SEQUENCE_MESSAGE; //$NON-NLS-1$
            } else if ((receivingEnd != null && receivingEnd.getEvent() instanceof ArmTimerEvent) || (sendingEnd != null && sendingEnd.getEvent() instanceof ArmTimerEvent)) {
                imagePath = "full/obj16/SequenceMessageArmTimer"; //$NON-NLS-1$
            } else {
                imagePath = "full/obj16/SequenceMessageCancelTimer"; //$NON-NLS-1$
            }
            break;
        case ASYNCHRONOUS_CALL:
            MessageEnd receivingEnd1 = ((SequenceMessage) object).getReceivingEnd();
            MessageEnd sendingEnd1 = ((SequenceMessage) object).getSendingEnd();
            if (receivingEnd1 == null && sendingEnd1 != null) {
                imagePath = "full/obj16/LostMessage"; //$NON-NLS-1$
            } else if (sendingEnd1 == null && receivingEnd1 != null) {
                imagePath = "full/obj16/FoundMessage"; //$NON-NLS-1$
            } else {
                imagePath = FULL_OBJ16_SEQUENCE_MESSAGE; //$NON-NLS-1$
            }
            break;
        default:
            imagePath = FULL_OBJ16_SEQUENCE_MESSAGE; //$NON-NLS-1$
            break;
        }
        return overlayImage(object, CapellaModellerEditPlugin.INSTANCE.getImage(imagePath));
    }
}
