package com.morecreepsrevival.morecreeps.common.networking;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.networking.message.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CreepsPacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MoreCreepsAndWeirdos.modid);

    private static int discriminatorId = 0;

    public static void registerMessages()
    {
        registerMessage(MessageOpenGuiTamableEntity.MessageHandler.class, MessageOpenGuiTamableEntity.class, Side.CLIENT);

        registerMessage(MessageOpenGuiTamableEntityTraining.MessageHandler.class, MessageOpenGuiTamableEntityTraining.class, Side.CLIENT);

        registerMessage(MessageChangeTamedEntityName.MessageHandler.class, MessageChangeTamedEntityName.class, Side.SERVER);

        registerMessage(MessageSetEntityWanderState.MessageHandler.class, MessageSetEntityWanderState.class, Side.SERVER);

        registerMessage(MessageSendBloodEffect.MessageHandler.class, MessageSendBloodEffect.class, Side.CLIENT);

        registerMessage(MessagePlayWelcomeSound.MessageHandler.class, MessagePlayWelcomeSound.class, Side.CLIENT);

        registerMessage(MessageLevelUpTamedEntity.MessageHandler.class, MessageLevelUpTamedEntity.class, Side.SERVER);

        registerMessage(MessageLevelUpGuineaPigSkill.MessageHandler.class, MessageLevelUpGuineaPigSkill.class, Side.SERVER);

        registerMessage(MessagePlayPyramidDiscoveredSound.MessageHandler.class, MessagePlayPyramidDiscoveredSound.class, Side.CLIENT);

        registerMessage(MessagePlayBattleCastleSound.MessageHandler.class, MessagePlayBattleCastleSound.class, Side.CLIENT);

        registerMessage(MessageLevelUpHotdogSkill.MessageHandler.class, MessageLevelUpHotdogSkill.class, Side.SERVER);

        registerMessage(MessageDismountEntity.MessageHandler.class, MessageDismountEntity.class, Side.SERVER);

        registerMessage(MessageDismountEntity.MessageHandler.class, MessageDismountEntity.class, Side.CLIENT);

        registerMessage(MessageOpenGuiSneakySal.MessageHandler.class, MessageOpenGuiSneakySal.class, Side.CLIENT);

        registerMessage(MessageRipOffSal.MessageHandler.class, MessageRipOffSal.class, Side.SERVER);

        registerMessage(MessageBuyItemFromSal.MessageHandler.class, MessageBuyItemFromSal.class, Side.SERVER);

        registerMessage(MessageSetLawyerFine.MessageHandler.class, MessageSetLawyerFine.class, Side.CLIENT);

        registerMessage(MessageOpenGuiTamableEntityName.MessageHandler.class, MessageOpenGuiTamableEntityName.class, Side.CLIENT);

        registerMessage(MessageSetJumping.MessageHandler.class, MessageSetJumping.class, Side.SERVER);
    }

    public static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
    {
        INSTANCE.registerMessage(messageHandler, requestMessageType, discriminatorId++, side);
    }
}
