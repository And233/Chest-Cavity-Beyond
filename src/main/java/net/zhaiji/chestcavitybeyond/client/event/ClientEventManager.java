package net.zhaiji.chestcavitybeyond.client.event;

import net.neoforged.bus.api.IEventBus;

public class ClientEventManager {
    public static void init(IEventBus modBus, IEventBus gameBus) {
        ClientEventManager.modBusListener(modBus);
        ClientEventManager.gameBusListener(gameBus);
    }

    public static void modBusListener(IEventBus modBus) {
        modBus.addListener(ClientEventHandler::handlerRegisterMenuScreensEvent);
        modBus.addListener(ClientEventHandler::handlerRegisterKeyMappingsEvent);
        modBus.addListener(ClientEventHandler::handlerEntityRenderersEvent$RegisterRenderers);
        modBus.addListener(ClientEventHandler::handlerRegisterGuiLayersEvent);
    }

    public static void gameBusListener(IEventBus gameBus) {
        gameBus.addListener(ClientEventHandler::handlerRenderLevelStageEvent);
        gameBus.addListener(ClientEventHandler::handlerItemTooltipEvent);
        gameBus.addListener(ClientEventHandler::handlerInputEvent$MouseButton$Pre);
        gameBus.addListener(ClientEventHandler::handlerInputEvent$Key);
        gameBus.addListener(ClientEventHandler::handlerRenderGuiLayerEvent$Pre);
        gameBus.addListener(ClientEventHandler::handlerMovementInputUpdateEvent);
    }
}
